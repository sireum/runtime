/*
 Copyright (c) 2017-2026, Robby, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.sireum.lang.optimizer;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.util.CheckClassAdapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Bytecode optimizer for Sireum {@code @bits} types (S8, S16, S32, S64, U8, U16, U32, U64,
 * and user-defined {@code @bits} types).
 *
 * <h2>Pass 1: Identity apply elimination</h2>
 * For wrapped {@code @bits} types, the companion object's {@code Int.apply(int)} (or
 * {@code Long.apply(long)}, etc.) is an identity function. This pass removes the
 * {@code GETSTATIC MODULE$} and {@code INVOKEVIRTUAL apply} bookends, leaving just
 * the primitive value on the stack.
 *
 * <h2>Pass 2: Box-arithmetic-unbox elimination</h2>
 * Eliminates boxing for binary arithmetic operations. The pattern:
 * <pre>
 *   NEW T; DUP; [load_x]; T.&lt;init&gt;(I)V
 *   NEW T; DUP; [load_y]; T.&lt;init&gt;(I)V
 *   INVOKEVIRTUAL T.op(Z$BV$Int)Z$BV$Int
 *   CHECKCAST T
 *   INVOKEVIRTUAL T.value()I
 * </pre>
 * Becomes: {@code [load_x]; [load_y]; IADD} (or ISUB, IMUL, etc.)
 *
 * <h2>Pass 3: Box-comparison elimination</h2>
 * Eliminates boxing for comparison operations on signed types:
 * <pre>
 *   NEW T; DUP; [load_x]; T.&lt;init&gt;(I)V
 *   NEW T; DUP; [load_y]; T.&lt;init&gt;(I)V
 *   INVOKEVIRTUAL T.$less(Z$BV$Int)Z
 * </pre>
 * Becomes: {@code [load_x]; [load_y]; IF_ICMPxx → ICONST_0/1}
 */
public class BitsOptimizer {

    // BV trait internal names
    static final String BV_INT_TRAIT = "org/sireum/Z$BV$Int";
    static final String BV_LONG_TRAIT = "org/sireum/Z$BV$Long";
    static final String BV_SHORT_TRAIT = "org/sireum/Z$BV$Short";
    static final String BV_BYTE_TRAIT = "org/sireum/Z$BV$Byte";

    // Arithmetic ops (safe for all wrapped types — two's complement wrapping)
    static final Set<String> ARITH_OPS = new HashSet<>(Arrays.asList(
            "$plus", "$minus", "$times"
    ));

    // Division ops
    static final Set<String> DIV_OPS = new HashSet<>(Arrays.asList(
            "$div", "$percent"
    ));

    // Comparison ops
    static final Set<String> COMP_OPS = new HashSet<>(Arrays.asList(
            "$less", "$less$eq", "$greater", "$greater$eq"
    ));

    // Known signed types (comparison uses signed JVM opcodes)
    static final Set<String> SIGNED_BV_TYPES = new HashSet<>(Arrays.asList(
            "org/sireum/S8", "org/sireum/S16", "org/sireum/S32", "org/sireum/S64"
    ));

    // Known unsigned types (comparison/division use unsigned JVM intrinsics)
    static final Set<String> UNSIGNED_BV_TYPES = new HashSet<>(Arrays.asList(
            "org/sireum/U8", "org/sireum/U16", "org/sireum/U32", "org/sireum/U64"
    ));

    // Map: arithmetic op name → int opcode
    static final Map<String, Integer> INT_ARITH_OPCODES;
    static {
        INT_ARITH_OPCODES = new HashMap<>();
        INT_ARITH_OPCODES.put("$plus", Opcodes.IADD);
        INT_ARITH_OPCODES.put("$minus", Opcodes.ISUB);
        INT_ARITH_OPCODES.put("$times", Opcodes.IMUL);
        INT_ARITH_OPCODES.put("$div", Opcodes.IDIV);
        INT_ARITH_OPCODES.put("$percent", Opcodes.IREM);
    }

    // Map: arithmetic op name → long opcode
    static final Map<String, Integer> LONG_ARITH_OPCODES;
    static {
        LONG_ARITH_OPCODES = new HashMap<>();
        LONG_ARITH_OPCODES.put("$plus", Opcodes.LADD);
        LONG_ARITH_OPCODES.put("$minus", Opcodes.LSUB);
        LONG_ARITH_OPCODES.put("$times", Opcodes.LMUL);
        LONG_ARITH_OPCODES.put("$div", Opcodes.LDIV);
        LONG_ARITH_OPCODES.put("$percent", Opcodes.LREM);
    }

    // Map: signed comparison op name → inverse IF_ICMP opcode (for false branch)
    // We jump to "false" label when the condition does NOT hold
    static final Map<String, Integer> SIGNED_INT_COMP_OPCODES;
    static {
        SIGNED_INT_COMP_OPCODES = new HashMap<>();
        SIGNED_INT_COMP_OPCODES.put("$less", Opcodes.IF_ICMPGE);          // jump if NOT less
        SIGNED_INT_COMP_OPCODES.put("$less$eq", Opcodes.IF_ICMPGT);       // jump if NOT less-or-equal
        SIGNED_INT_COMP_OPCODES.put("$greater", Opcodes.IF_ICMPLE);       // jump if NOT greater
        SIGNED_INT_COMP_OPCODES.put("$greater$eq", Opcodes.IF_ICMPLT);    // jump if NOT greater-or-equal
    }

    private final boolean verbose;
    private Path baseDir;
    private final Map<String, Boolean> wrappedCache = new HashMap<>();

    public BitsOptimizer() {
        this(false);
    }

    public BitsOptimizer(boolean verbose) {
        this.verbose = verbose;
    }

    /**
     * Check if a BV type is wrapped (full bit-width range, no custom min/max).
     * Only wrapped types can be safely optimized — non-wrapped types have range
     * checks in their arithmetic methods that must not be eliminated.
     *
     * Reads the companion class (T$) and checks if its {@code isWrapped()} method
     * returns true.
     */
    boolean isWrappedType(String bvType) {
        Boolean cached = wrappedCache.get(bvType);
        if (cached != null) return cached;
        boolean result = checkIsWrapped(bvType);
        wrappedCache.put(bvType, result);
        return result;
    }

    private boolean checkIsWrapped(String bvType) {
        String companionClassFile = bvType + "$.class";

        // Try baseDir (output directory during compilation)
        if (baseDir != null) {
            Path companionPath = baseDir.resolve(companionClassFile);
            if (Files.exists(companionPath)) {
                try {
                    return readIsWrapped(Files.readAllBytes(companionPath));
                } catch (IOException e) {
                    // fall through
                }
            }
        }

        // Try classpath (for types compiled in earlier modules)
        try (var is = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(companionClassFile)) {
            if (is != null) {
                return readIsWrapped(is.readAllBytes());
            }
        } catch (IOException e) {
            // fall through
        }

        // Can't determine — assume not wrapped (safe: don't optimize)
        return false;
    }

    /**
     * Read the {@code isWrapped} method from a companion class's bytecode.
     * Returns true if the method body starts with ICONST_1 (wrapped),
     * false otherwise.
     */
    private boolean readIsWrapped(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);
        for (MethodNode mn : cn.methods) {
            if ("isWrapped".equals(mn.name)) {
                for (AbstractInsnNode insn = mn.instructions.getFirst();
                     insn != null; insn = insn.getNext()) {
                    if (insn instanceof LabelNode || insn instanceof LineNumberNode
                            || insn instanceof FrameNode) continue;
                    return insn.getOpcode() == Opcodes.ICONST_1;
                }
            }
        }
        return false;
    }

    /**
     * Transform all .class files under the given directory path.
     */
    public int transformDirectory(String path) throws IOException {
        Path dir = Paths.get(path);
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Not a directory: " + path);
        }
        this.baseDir = dir;
        int[] count = {0};
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".class")) {
                    count[0] += transformClassFile(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return count[0];
    }

    /**
     * Transform a single .class file in place.
     */
    public int transformClassFile(Path classFile) throws IOException {
        byte[] original = Files.readAllBytes(classFile);
        byte[] transformed = transformClassBytes(original);
        if (transformed != null) {
            Files.write(classFile, transformed);
            return 1;
        }
        return 0;
    }

    /**
     * Transform class bytes. Returns null if no transformation was needed.
     */
    public byte[] transformClassBytes(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        boolean changed = false;

        for (MethodNode mn : cn.methods) {
            if (mn.instructions.size() == 0) continue;
            if (transformMethod(cn.name, mn)) {
                changed = true;
            }
        }

        if (!changed) return null;

        // Strip all frame nodes — COMPUTE_FRAMES recomputes from scratch.
        for (MethodNode mn : cn.methods) {
            Iterator<AbstractInsnNode> it = mn.instructions.iterator();
            while (it.hasNext()) {
                if (it.next() instanceof FrameNode) {
                    it.remove();
                }
            }
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES) {
            @Override
            protected String getCommonSuperClass(String type1, String type2) {
                if ("java/lang/Object".equals(type1) || "java/lang/Object".equals(type2)) {
                    return "java/lang/Object";
                }
                try {
                    return super.getCommonSuperClass(type1, type2);
                } catch (Exception e) {
                    return "java/lang/Object";
                }
            }
        };
        cn.accept(cw);
        byte[] result = cw.toByteArray();

        // Verify the transformed bytecode
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            CheckClassAdapter.verify(new ClassReader(result), false, pw);
        } catch (Exception e) {
            if (verbose) {
                System.err.println("BitsOptimizer: verify threw for " + cn.name + ": " + e.getMessage());
            }
            return null;
        }
        String verifyErrors = sw.toString();
        if (!verifyErrors.isEmpty()) {
            if (verbose) {
                System.err.println("BitsOptimizer: verify failed for " + cn.name + ":\n" + verifyErrors);
            }
            return null;
        }

        return result;
    }

    /**
     * Transform a single method.
     */
    boolean transformMethod(String className, MethodNode mn) {
        boolean changed = false;

        // Pass 1: Eliminate identity applies (T$Int$.apply, T$Long$.apply, etc.)
        if (eliminateIdentityApply(mn)) {
            changed = true;
            if (verbose) {
                System.out.println("  " + className + "." + mn.name + mn.desc +
                        ": eliminated BV identity applies");
            }
        }

        // Pass 2: Eliminate box-arith-unbox sequences
        if (eliminateBoxArithUnbox(mn)) {
            changed = true;
            if (verbose) {
                System.out.println("  " + className + "." + mn.name + mn.desc +
                        ": eliminated BV box-arith-unbox");
            }
        }

        // Pass 3: Eliminate box-compare sequences (signed types)
        if (eliminateBoxCompare(mn)) {
            changed = true;
            if (verbose) {
                System.out.println("  " + className + "." + mn.name + mn.desc +
                        ": eliminated BV box-compare");
            }
        }

        return changed;
    }

    // ===== Pass 1: Identity apply elimination =====

    /**
     * Detect and remove identity apply patterns:
     * <pre>
     *   GETSTATIC T$Int$.MODULE$:LT$Int$;
     *   &lt;value push&gt;
     *   INVOKEVIRTUAL T$Int$.apply:(I)I
     * </pre>
     * Also handles T$Long$.apply:(J)J, T$Short$.apply:(S)S, T$Byte$.apply:(B)B.
     */
    boolean eliminateIdentityApply(MethodNode mn) {
        boolean changed = false;
        InsnList insns = mn.instructions;

        for (AbstractInsnNode insn = insns.getFirst(); insn != null; ) {
            AbstractInsnNode next = insn.getNext();

            if (insn instanceof MethodInsnNode && insn.getOpcode() == Opcodes.INVOKEVIRTUAL) {
                MethodInsnNode call = (MethodInsnNode) insn;

                // Check if this is a BV companion apply: T$Int$.apply:(I)I etc.
                if ("apply".equals(call.name) && isBVCompanionApply(call)) {
                    // Extract the BV type from companion name (e.g. "org/sireum/S32$Int$" → "org/sireum/S32")
                    String bvType = extractBVTypeFromCompanion(call.owner);
                    if (bvType != null && isWrappedType(bvType)) {
                        // Find the GETSTATIC before the value push
                        AbstractInsnNode valuePush = previousSignificant(call);
                        if (valuePush != null) {
                            AbstractInsnNode getstatic = previousSignificant(valuePush);
                            if (getstatic instanceof FieldInsnNode) {
                                FieldInsnNode field = (FieldInsnNode) getstatic;
                                if (field.getOpcode() == Opcodes.GETSTATIC &&
                                        "MODULE$".equals(field.name) &&
                                        call.owner.equals(field.owner)) {
                                    // Remove GETSTATIC and INVOKEVIRTUAL, keep the value push
                                    insns.remove(getstatic);
                                    insns.remove(call);
                                    changed = true;
                                    insn = insns.getFirst();
                                    continue;
                                }
                            }
                        }
                    }
                }
            }

            insn = next;
        }

        return changed;
    }

    /**
     * Check if a method call is a BV companion identity apply.
     * Pattern: owner ends with "$Int$", "$Long$", "$Short$", or "$Byte$"
     * and descriptor is (X)X where X matches the width.
     */
    static boolean isBVCompanionApply(MethodInsnNode call) {
        String owner = call.owner;
        String desc = call.desc;

        if (owner.endsWith("$Int$") && "(I)I".equals(desc)) return true;
        if (owner.endsWith("$Long$") && "(J)J".equals(desc)) return true;
        if (owner.endsWith("$Short$") && "(S)S".equals(desc)) return true;
        if (owner.endsWith("$Byte$") && "(B)B".equals(desc)) return true;
        return false;
    }

    /**
     * Extract the BV type internal name from a companion apply owner.
     * "org/sireum/S32$Int$" → "org/sireum/S32"
     * "org/sireum/U64$Long$" → "org/sireum/U64"
     */
    static String extractBVTypeFromCompanion(String owner) {
        for (String suffix : new String[]{"$Int$", "$Long$", "$Short$", "$Byte$"}) {
            if (owner.endsWith(suffix)) {
                return owner.substring(0, owner.length() - suffix.length());
            }
        }
        return null;
    }

    // ===== Pass 2: Box-arithmetic-unbox elimination =====

    /**
     * Holds information about a matched box sequence: NEW T; DUP; [load]; T.&lt;init&gt;.
     * The "load" instructions are everything between DUP and &lt;init&gt; (exclusive).
     */
    static class BoxMatch {
        final TypeInsnNode newInsn;      // NEW T
        final AbstractInsnNode dupInsn;  // DUP
        final MethodInsnNode initInsn;   // INVOKESPECIAL T.<init>:(X)V
        final String valueDesc;          // "I", "J", "S", or "B"

        BoxMatch(TypeInsnNode newInsn, AbstractInsnNode dupInsn,
                 MethodInsnNode initInsn, String valueDesc) {
            this.newInsn = newInsn;
            this.dupInsn = dupInsn;
            this.initInsn = initInsn;
            this.valueDesc = valueDesc;
        }
    }

    /**
     * Try to match a box sequence starting at the given NEW instruction.
     * Returns null if the pattern doesn't match.
     */
    BoxMatch matchBox(AbstractInsnNode start, String bvType) {
        if (!(start instanceof TypeInsnNode) || start.getOpcode() != Opcodes.NEW) return null;
        TypeInsnNode newInsn = (TypeInsnNode) start;
        if (!bvType.equals(newInsn.desc)) return null;

        AbstractInsnNode dup = nextSignificant(newInsn);
        if (dup == null || dup.getOpcode() != Opcodes.DUP) return null;

        // Scan forward to find INVOKESPECIAL T.<init>:(X)V
        int count = 0;
        for (AbstractInsnNode n = dup.getNext(); n != null && count < 20; n = n.getNext()) {
            if (n instanceof LabelNode || n instanceof LineNumberNode || n instanceof FrameNode) {
                continue;
            }
            if (n instanceof MethodInsnNode) {
                MethodInsnNode mi = (MethodInsnNode) n;
                if (mi.getOpcode() == Opcodes.INVOKESPECIAL &&
                        "<init>".equals(mi.name) &&
                        bvType.equals(mi.owner)) {
                    String vd = initDescToValueDesc(mi.desc);
                    if (vd != null) {
                        return new BoxMatch(newInsn, dup, mi, vd);
                    }
                }
            }
            // Bail on branches, returns, or nested NEW of same type
            if (n instanceof JumpInsnNode) return null;
            if (n.getOpcode() >= Opcodes.IRETURN && n.getOpcode() <= Opcodes.RETURN) return null;
            if (n instanceof TypeInsnNode && n.getOpcode() == Opcodes.NEW &&
                    bvType.equals(((TypeInsnNode) n).desc)) return null;
            count++;
        }
        return null;
    }

    /**
     * Extract value descriptor from init descriptor.
     * "(I)V" → "I", "(J)V" → "J", "(S)V" → "S", "(B)V" → "B".
     * Returns null if not a recognized BV init descriptor.
     */
    static String initDescToValueDesc(String desc) {
        if ("(I)V".equals(desc)) return "I";
        if ("(J)V".equals(desc)) return "J";
        if ("(S)V".equals(desc)) return "S";
        if ("(B)V".equals(desc)) return "B";
        return null;
    }

    /**
     * Extract BV trait internal name from an arithmetic method descriptor.
     * "(Lorg/sireum/Z$BV$Int;)Lorg/sireum/Z$BV$Int;" → "org/sireum/Z$BV$Int"
     * Returns null if not a BV arithmetic descriptor.
     */
    static String extractBVArithTrait(String desc) {
        if (!desc.startsWith("(L") || !desc.contains(";)L")) return null;
        int semi = desc.indexOf(';');
        String argType = desc.substring(2, semi);
        String retPart = desc.substring(semi + 3);  // skip ";)L"
        if (!retPart.endsWith(";")) return null;
        String retType = retPart.substring(0, retPart.length() - 1);
        if (argType.equals(retType) && argType.startsWith("org/sireum/Z$BV$")) {
            return argType;
        }
        return null;
    }

    /**
     * Extract BV trait internal name from a comparison method descriptor.
     * "(Lorg/sireum/Z$BV$Int;)Z" → "org/sireum/Z$BV$Int"
     * Returns null if not a BV comparison descriptor.
     */
    static String extractBVCompTrait(String desc) {
        if (!desc.startsWith("(L") || !desc.endsWith(";)Z")) return null;
        String argType = desc.substring(2, desc.length() - 3);
        if (argType.startsWith("org/sireum/Z$BV$")) {
            return argType;
        }
        return null;
    }

    /**
     * Get the native arithmetic instruction for the given op name, trait, and BV type.
     * Returns an InsnNode (for simple opcodes) or MethodInsnNode (for unsigned div/rem).
     * Returns null if the op cannot be optimized.
     */
    static AbstractInsnNode getNativeArithInsn(String opName, String trait, String bvType) {
        boolean isLong = BV_LONG_TRAIT.equals(trait);
        boolean isUnsigned = UNSIGNED_BV_TYPES.contains(bvType);

        if (DIV_OPS.contains(opName)) {
            if (isUnsigned) {
                // Use Integer/Long.divideUnsigned or remainderUnsigned
                String methodName = "$div".equals(opName) ? "divideUnsigned" : "remainderUnsigned";
                if (isLong) {
                    return new MethodInsnNode(Opcodes.INVOKESTATIC,
                            "java/lang/Long", methodName, "(JJ)J", false);
                } else {
                    return new MethodInsnNode(Opcodes.INVOKESTATIC,
                            "java/lang/Integer", methodName, "(II)I", false);
                }
            } else {
                // Signed: use raw IDIV/LDIV/IREM/LREM
                if (isLong) {
                    Integer op = LONG_ARITH_OPCODES.get(opName);
                    return op != null ? new InsnNode(op) : null;
                } else {
                    Integer op = INT_ARITH_OPCODES.get(opName);
                    return op != null ? new InsnNode(op) : null;
                }
            }
        }

        // +, -, * — same opcode for signed and unsigned (two's complement)
        if (isLong) {
            Integer op = LONG_ARITH_OPCODES.get(opName);
            return op != null ? new InsnNode(op) : null;
        }
        Integer op = INT_ARITH_OPCODES.get(opName);
        return op != null ? new InsnNode(op) : null;
    }

    boolean eliminateBoxArithUnbox(MethodNode mn) {
        boolean changed = false;
        InsnList insns = mn.instructions;

        for (AbstractInsnNode insn = insns.getFirst(); insn != null; ) {
            AbstractInsnNode next = insn.getNext();

            // Look for NEW <bvType> as potential start of a box-arith-unbox sequence
            if (insn instanceof TypeInsnNode && insn.getOpcode() == Opcodes.NEW) {
                String bvType = ((TypeInsnNode) insn).desc;

                // Only optimize wrapped types (no range checks in arithmetic)
                if (!isWrappedType(bvType)) {
                    insn = next;
                    continue;
                }

                // Try to match: box1 → box2 → arith_op → checkcast → value()
                BoxMatch box1 = matchBox(insn, bvType);
                if (box1 != null) {
                    // After box1, look for box2
                    AbstractInsnNode afterInit1 = nextSignificant(box1.initInsn);
                    BoxMatch box2 = matchBox(afterInit1, bvType);
                    if (box2 != null && box2.valueDesc.equals(box1.valueDesc)) {
                        // After box2, look for arithmetic op
                        AbstractInsnNode afterInit2 = nextSignificant(box2.initInsn);
                        if (afterInit2 instanceof MethodInsnNode) {
                            MethodInsnNode opCall = (MethodInsnNode) afterInit2;
                            String trait = extractBVArithTrait(opCall.desc);

                            // Check if it's an arithmetic op we can optimize
                            boolean isArith = trait != null && ARITH_OPS.contains(opCall.name);
                            boolean isDiv = trait != null && DIV_OPS.contains(opCall.name);

                            if (isArith || isDiv) {
                                // After arith op, look for CHECKCAST T
                                AbstractInsnNode afterOp = nextSignificant(opCall);
                                if (afterOp instanceof TypeInsnNode &&
                                        afterOp.getOpcode() == Opcodes.CHECKCAST &&
                                        bvType.equals(((TypeInsnNode) afterOp).desc)) {
                                    // After CHECKCAST, look for value()
                                    AbstractInsnNode afterCast = nextSignificant(afterOp);
                                    if (afterCast instanceof MethodInsnNode) {
                                        MethodInsnNode valueCall = (MethodInsnNode) afterCast;
                                        if ("value".equals(valueCall.name) &&
                                                bvType.equals(valueCall.owner)) {

                                            AbstractInsnNode nativeInsn =
                                                    getNativeArithInsn(opCall.name, trait, bvType);
                                            if (nativeInsn != null) {
                                                // MATCH! Replace with native arithmetic
                                                replaceBoxArithUnbox(insns, box1, box2,
                                                        opCall, (TypeInsnNode) afterOp,
                                                        valueCall, nativeInsn);
                                                changed = true;
                                                insn = insns.getFirst();
                                                continue;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            insn = next;
        }

        return changed;
    }

    /**
     * Replace a box-arith-unbox sequence with a native arithmetic operation.
     *
     * Before:
     *   NEW T; DUP; [load_x]; INVOKESPECIAL T.&lt;init&gt;;
     *   NEW T; DUP; [load_y]; INVOKESPECIAL T.&lt;init&gt;;
     *   INVOKEVIRTUAL T.op; CHECKCAST T; INVOKEVIRTUAL T.value
     *
     * After:
     *   [load_x]; [load_y]; native_op
     */
    void replaceBoxArithUnbox(InsnList insns, BoxMatch box1, BoxMatch box2,
                              MethodInsnNode opCall, TypeInsnNode checkcast,
                              MethodInsnNode valueCall, AbstractInsnNode nativeInsn) {
        // Remove box1 framing: NEW, DUP, <init>
        insns.remove(box1.newInsn);
        insns.remove(box1.dupInsn);
        insns.remove(box1.initInsn);
        // [load_x] instructions remain in place

        // Remove box2 framing: NEW, DUP, <init>
        insns.remove(box2.newInsn);
        insns.remove(box2.dupInsn);
        insns.remove(box2.initInsn);
        // [load_y] instructions remain in place

        // Replace opCall with native instruction (simple opcode or static method call)
        insns.set(opCall, nativeInsn);

        // Remove CHECKCAST and value()
        insns.remove(checkcast);
        insns.remove(valueCall);
    }

    // ===== Pass 3: Box-comparison elimination =====

    boolean eliminateBoxCompare(MethodNode mn) {
        boolean changed = false;
        InsnList insns = mn.instructions;

        for (AbstractInsnNode insn = insns.getFirst(); insn != null; ) {
            AbstractInsnNode next = insn.getNext();

            if (insn instanceof TypeInsnNode && insn.getOpcode() == Opcodes.NEW) {
                String bvType = ((TypeInsnNode) insn).desc;

                // Only optimize wrapped types that are signed or unsigned
                if (!isWrappedType(bvType) ||
                        (!SIGNED_BV_TYPES.contains(bvType) && !UNSIGNED_BV_TYPES.contains(bvType))) {
                    insn = next;
                    continue;
                }

                BoxMatch box1 = matchBox(insn, bvType);
                if (box1 != null) {
                    AbstractInsnNode afterInit1 = nextSignificant(box1.initInsn);
                    BoxMatch box2 = matchBox(afterInit1, bvType);
                    if (box2 != null && box2.valueDesc.equals(box1.valueDesc)) {
                        AbstractInsnNode afterInit2 = nextSignificant(box2.initInsn);
                        if (afterInit2 instanceof MethodInsnNode) {
                            MethodInsnNode opCall = (MethodInsnNode) afterInit2;
                            String trait = extractBVCompTrait(opCall.desc);
                            if (trait != null && COMP_OPS.contains(opCall.name) &&
                                    bvType.equals(opCall.owner)) {

                                boolean isSigned = SIGNED_BV_TYPES.contains(bvType);
                                boolean isIntWidth = BV_INT_TRAIT.equals(trait) ||
                                        BV_SHORT_TRAIT.equals(trait) ||
                                        BV_BYTE_TRAIT.equals(trait);
                                boolean isLongWidth = BV_LONG_TRAIT.equals(trait);

                                if (isIntWidth) {
                                    if (isSigned) {
                                        Integer inverseBranch = SIGNED_INT_COMP_OPCODES.get(opCall.name);
                                        if (inverseBranch != null) {
                                            replaceBoxCompare(insns, box1, box2, opCall, inverseBranch);
                                            changed = true;
                                            insn = insns.getFirst();
                                            continue;
                                        }
                                    } else {
                                        // Unsigned int-width: Integer.compareUnsigned + IFxx
                                        replaceBoxCompareUnsignedInt(insns, box1, box2, opCall);
                                        changed = true;
                                        insn = insns.getFirst();
                                        continue;
                                    }
                                } else if (isLongWidth) {
                                    if (isSigned) {
                                        replaceBoxCompareLong(insns, box1, box2, opCall);
                                    } else {
                                        // Unsigned long-width: Long.compareUnsigned + IFxx
                                        replaceBoxCompareUnsignedLong(insns, box1, box2, opCall);
                                    }
                                    changed = true;
                                    insn = insns.getFirst();
                                    continue;
                                }
                            }
                        }
                    }
                }
            }

            insn = next;
        }

        return changed;
    }

    /**
     * Replace a box-compare sequence for int-width types with native comparison.
     *
     * Before:
     *   NEW T; DUP; [load_x]; T.&lt;init&gt;; NEW T; DUP; [load_y]; T.&lt;init&gt;;
     *   INVOKEVIRTUAL T.$less(Z$BV$Int)Z
     *
     * After:
     *   [load_x]; [load_y]; IF_ICMPxx false_label; ICONST_1; GOTO end_label;
     *   false_label: ICONST_0; end_label:
     */
    void replaceBoxCompare(InsnList insns, BoxMatch box1, BoxMatch box2,
                           MethodInsnNode opCall, int inverseBranchOpcode) {
        // Remove box framing
        insns.remove(box1.newInsn);
        insns.remove(box1.dupInsn);
        insns.remove(box1.initInsn);
        insns.remove(box2.newInsn);
        insns.remove(box2.dupInsn);
        insns.remove(box2.initInsn);

        // Replace opCall with: IF_ICMPxx false; ICONST_1; GOTO end; false: ICONST_0; end:
        LabelNode falseLabel = new LabelNode();
        LabelNode endLabel = new LabelNode();

        InsnList replacement = new InsnList();
        replacement.add(new JumpInsnNode(inverseBranchOpcode, falseLabel));
        replacement.add(new InsnNode(Opcodes.ICONST_1));
        replacement.add(new JumpInsnNode(Opcodes.GOTO, endLabel));
        replacement.add(falseLabel);
        replacement.add(new InsnNode(Opcodes.ICONST_0));
        replacement.add(endLabel);

        insns.insert(opCall, replacement);
        insns.remove(opCall);
    }

    /**
     * Replace a box-compare sequence for unsigned int-width types.
     *
     * After:
     *   [load_x]; [load_y]; INVOKESTATIC Integer.compareUnsigned(II)I;
     *   IFxx false_label; ICONST_1; GOTO end_label; false_label: ICONST_0; end_label:
     */
    void replaceBoxCompareUnsignedInt(InsnList insns, BoxMatch box1, BoxMatch box2,
                                      MethodInsnNode opCall) {
        int ifOpcode = getCompareIfOpcode(opCall.name);
        if (ifOpcode < 0) return;

        insns.remove(box1.newInsn);
        insns.remove(box1.dupInsn);
        insns.remove(box1.initInsn);
        insns.remove(box2.newInsn);
        insns.remove(box2.dupInsn);
        insns.remove(box2.initInsn);

        LabelNode falseLabel = new LabelNode();
        LabelNode endLabel = new LabelNode();

        InsnList replacement = new InsnList();
        replacement.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                "java/lang/Integer", "compareUnsigned", "(II)I", false));
        replacement.add(new JumpInsnNode(ifOpcode, falseLabel));
        replacement.add(new InsnNode(Opcodes.ICONST_1));
        replacement.add(new JumpInsnNode(Opcodes.GOTO, endLabel));
        replacement.add(falseLabel);
        replacement.add(new InsnNode(Opcodes.ICONST_0));
        replacement.add(endLabel);

        insns.insert(opCall, replacement);
        insns.remove(opCall);
    }

    /**
     * Replace a box-compare sequence for unsigned long-width types.
     *
     * After:
     *   [load_x]; [load_y]; INVOKESTATIC Long.compareUnsigned(JJ)I;
     *   IFxx false_label; ICONST_1; GOTO end_label; false_label: ICONST_0; end_label:
     */
    void replaceBoxCompareUnsignedLong(InsnList insns, BoxMatch box1, BoxMatch box2,
                                       MethodInsnNode opCall) {
        int ifOpcode = getCompareIfOpcode(opCall.name);
        if (ifOpcode < 0) return;

        insns.remove(box1.newInsn);
        insns.remove(box1.dupInsn);
        insns.remove(box1.initInsn);
        insns.remove(box2.newInsn);
        insns.remove(box2.dupInsn);
        insns.remove(box2.initInsn);

        LabelNode falseLabel = new LabelNode();
        LabelNode endLabel = new LabelNode();

        InsnList replacement = new InsnList();
        replacement.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                "java/lang/Long", "compareUnsigned", "(JJ)I", false));
        replacement.add(new JumpInsnNode(ifOpcode, falseLabel));
        replacement.add(new InsnNode(Opcodes.ICONST_1));
        replacement.add(new JumpInsnNode(Opcodes.GOTO, endLabel));
        replacement.add(falseLabel);
        replacement.add(new InsnNode(Opcodes.ICONST_0));
        replacement.add(endLabel);

        insns.insert(opCall, replacement);
        insns.remove(opCall);
    }

    /**
     * Get the inverse IF opcode for a comparison result int (from compareUnsigned/LCMP).
     * The result is -1/0/1, and we jump to false when the condition does NOT hold.
     */
    static int getCompareIfOpcode(String opName) {
        switch (opName) {
            case "$less":        return Opcodes.IFGE;   // jump if NOT less (result >= 0)
            case "$less$eq":     return Opcodes.IFGT;   // jump if NOT less-or-equal (result > 0)
            case "$greater":     return Opcodes.IFLE;   // jump if NOT greater (result <= 0)
            case "$greater$eq":  return Opcodes.IFLT;   // jump if NOT greater-or-equal (result < 0)
            default: return -1;
        }
    }

    /**
     * Replace a box-compare sequence for signed long-width types with LCMP + branch.
     *
     * After:
     *   [load_x]; [load_y]; LCMP; IFxx false_label; ICONST_1; GOTO end_label;
     *   false_label: ICONST_0; end_label:
     */
    void replaceBoxCompareLong(InsnList insns, BoxMatch box1, BoxMatch box2,
                               MethodInsnNode opCall) {
        int ifOpcode = getCompareIfOpcode(opCall.name);
        if (ifOpcode < 0) return;

        insns.remove(box1.newInsn);
        insns.remove(box1.dupInsn);
        insns.remove(box1.initInsn);
        insns.remove(box2.newInsn);
        insns.remove(box2.dupInsn);
        insns.remove(box2.initInsn);

        LabelNode falseLabel = new LabelNode();
        LabelNode endLabel = new LabelNode();

        InsnList replacement = new InsnList();
        replacement.add(new InsnNode(Opcodes.LCMP));
        replacement.add(new JumpInsnNode(ifOpcode, falseLabel));
        replacement.add(new InsnNode(Opcodes.ICONST_1));
        replacement.add(new JumpInsnNode(Opcodes.GOTO, endLabel));
        replacement.add(falseLabel);
        replacement.add(new InsnNode(Opcodes.ICONST_0));
        replacement.add(endLabel);

        insns.insert(opCall, replacement);
        insns.remove(opCall);
    }

    // ===== Utility methods =====

    static AbstractInsnNode previousSignificant(AbstractInsnNode insn) {
        AbstractInsnNode prev = insn.getPrevious();
        while (prev != null && (prev instanceof LabelNode || prev instanceof LineNumberNode ||
                prev instanceof FrameNode)) {
            prev = prev.getPrevious();
        }
        return prev;
    }

    static AbstractInsnNode nextSignificant(AbstractInsnNode insn) {
        if (insn == null) return null;
        AbstractInsnNode next = insn.getNext();
        while (next != null && (next instanceof LabelNode || next instanceof LineNumberNode ||
                next instanceof FrameNode)) {
            next = next.getNext();
        }
        return next;
    }

    static String insnToString(AbstractInsnNode insn) {
        if (insn == null) return "null";
        String[] OPCODES = org.objectweb.asm.util.Printer.OPCODES;
        int op = insn.getOpcode();
        String opName = (op >= 0 && op < OPCODES.length) ? OPCODES[op] : "op" + op;
        if (insn instanceof MethodInsnNode) {
            MethodInsnNode m = (MethodInsnNode) insn;
            return opName + " " + m.owner + "." + m.name + m.desc;
        }
        if (insn instanceof FieldInsnNode) {
            FieldInsnNode f = (FieldInsnNode) insn;
            return opName + " " + f.owner + "." + f.name + ":" + f.desc;
        }
        if (insn instanceof TypeInsnNode) {
            return opName + " " + ((TypeInsnNode) insn).desc;
        }
        if (insn instanceof VarInsnNode) {
            return opName + " " + ((VarInsnNode) insn).var;
        }
        if (insn instanceof IntInsnNode) {
            return opName + " " + ((IntInsnNode) insn).operand;
        }
        if (insn instanceof LdcInsnNode) {
            return "LDC " + ((LdcInsnNode) insn).cst;
        }
        return opName;
    }
}
