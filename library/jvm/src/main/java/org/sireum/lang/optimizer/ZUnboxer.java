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
 * Bytecode optimizer for Sireum Z (org/sireum/Z) operations.
 *
 * <h2>Pass 1: Literal argument unboxing (peephole, no escape analysis)</h2>
 * Rewrites {@code z.op(Z.MP$.apply(longLiteral))} to {@code z.op(longLiteral)}
 * using the {@code (scala.Long)} overloads on Z. This eliminates boxing of
 * literal arguments to Z arithmetic and comparison methods.
 * <p>
 * Bytecode pattern:
 * <pre>
 *   GETSTATIC Z$MP$.MODULE$          // singleton for apply
 *   LCONST/LDC long                  // literal value
 *   INVOKEVIRTUAL Z$MP$.apply(J)Z    // box long → Z
 *   INVOKEVIRTUAL Z.op(Z)Z           // z.op(boxedZ)
 * </pre>
 * Becomes:
 * <pre>
 *   LCONST/LDC long                  // literal value (stays as long)
 *   INVOKEVIRTUAL Z.op(J)Z           // z.op(long) — uses Long overload
 * </pre>
 *
 * <h2>Pass 2: Z local variable unboxing (escape analysis)</h2>
 * For each method:
 * <ol>
 *   <li>Identifies local variables of type Z (excluding method parameters)</li>
 *   <li>Performs escape analysis: a Z local "escapes" if it is passed as an
 *       argument to a method that expects Z (except recognized Z arithmetic/comparison
 *       methods), stored to a field, returned, or used in any other non-local way</li>
 *   <li>For non-escaping Z locals, remaps ASTORE/ALOAD to LSTORE/LLOAD at new
 *       slots (avoiding slot size conflicts), eliminates boxing at stores, and
 *       re-boxes at load sites that need a Z reference</li>
 * </ol>
 * Pass 2 requires COMPUTE_FRAMES (local types change from reference to long).
 * If frame computation fails for a class, it falls back to Pass 1 only.
 */
public class ZUnboxer {

    // Internal names
    static final String Z_TYPE = "org/sireum/Z";
    static final String Z_COMP = "org/sireum/Z$";          // Z companion object
    static final String Z_MP = "org/sireum/Z$MP$";
    static final String Z_MP_LONG = "org/sireum/Z$MP$Long";
    static final String Z_DESC = "L" + Z_TYPE + ";";
    static final String IS_TYPE = "org/sireum/IS";
    static final String MS_TYPE = "org/sireum/MS";

    // IS/MS.apply(Object)Object — erased index lookup (apply(I): V)
    static final String IS_APPLY_DESC = "(Ljava/lang/Object;)Ljava/lang/Object;";
    // IS/MS.apply(long)Object — long overload for unboxed Z index
    static final String IS_APPLY_LONG_DESC = "(J)Ljava/lang/Object;";
    // MS.update(Object, Object)void — erased index update (update(I, V): Unit)
    static final String MS_UPDATE_DESC = "(Ljava/lang/Object;Ljava/lang/Object;)V";
    // MS.update(long, Object)void — long overload for unboxed Z index
    static final String MS_UPDATE_LONG_DESC = "(JLjava/lang/Object;)V";

    // Singleton MODULE$ field (used by both Z$ and Z$MP$)
    static final String Z_MP_MODULE = "MODULE$";

    // Z.MP$.apply(long) → Z
    static final String MP_APPLY_DESC = "(J)" + Z_DESC;

    // Z.MP$.Long$.apply(long) → Z$MP$Long
    // Actually Z.MP.Long(value) is a case class constructor
    static final String Z_MP_LONG_INIT_DESC = "(J)V";

    // Z arithmetic method descriptors (Z, Z) → Z
    static final String ZZ_TO_Z = "(" + Z_DESC + Z_DESC + ")" + Z_DESC;
    // Z comparison method descriptors (Z, Z) → boolean
    static final String ZZ_TO_B = "(" + Z_DESC + Z_DESC + ")Z";
    // Z arithmetic (Z, long) → Z
    static final String ZL_TO_Z = "(" + Z_DESC + "J)" + Z_DESC;
    // Z comparison (Z, long) → boolean
    static final String ZL_TO_B = "(" + Z_DESC + "J)Z";

    // Z instance method descriptor: (Z) → Z
    static final String Z_TO_Z = "(" + Z_DESC + ")" + Z_DESC;
    // Z instance method descriptor: (Z) → boolean
    static final String Z_TO_B = "(" + Z_DESC + ")Z";
    // Z instance method descriptor: (long) → Z
    static final String L_TO_Z = "(J)" + Z_DESC;
    // Z instance method descriptor: (long) → boolean
    static final String L_TO_B = "(J)Z";
    // Z instance method descriptor: (int) → Z
    static final String I_TO_Z = "(I)" + Z_DESC;
    // Z instance method descriptor: (int) → boolean
    static final String I_TO_B = "(I)Z";

    // Z.toLong() → long
    static final String TO_LONG_DESC = "()J";

    // Math.addExact/subtractExact/multiplyExact
    static final String MATH_TYPE = "java/lang/Math";
    static final String EXACT_LL_TO_L = "(JJ)J";

    // Static dispatch descriptors: (long, long) → Z or boolean
    static final String LL_TO_Z = "(JJ)" + Z_DESC;
    static final String LL_TO_B = "(JJ)Z";

    // Internal name of this class (for INVOKESTATIC to our static helper methods)
    static final String ZUNBOXER_TYPE = "org/sireum/lang/optimizer/ZUnboxer";

    // Z binary operations that have (scala.Long) overloads
    static final Set<String> Z_BIN_OPS = new HashSet<>(Arrays.asList(
            "$plus", "$minus", "$times", "$div", "$percent",
            "$greater", "$greater$eq", "$less", "$less$eq"
    ));

    // Map from Scala operator name → static Java method name in ZUnboxer
    static final Map<String, String> Z_STATIC_OPS = new HashMap<>();
    static {
        Z_STATIC_OPS.put("$plus", "zAdd");
        Z_STATIC_OPS.put("$minus", "zSub");
        Z_STATIC_OPS.put("$times", "zMul");
        Z_STATIC_OPS.put("$div", "zDiv");
        Z_STATIC_OPS.put("$percent", "zRem");
        Z_STATIC_OPS.put("$greater", "zGt");
        Z_STATIC_OPS.put("$greater$eq", "zGe");
        Z_STATIC_OPS.put("$less", "zLt");
        Z_STATIC_OPS.put("$less$eq", "zLe");
    }

    // classifyZUse return values
    static final int USE_OPTIMIZABLE = 0;   // No re-boxing needed
    static final int USE_NEEDS_REBOX = 1;   // Safe to unbox but may need re-boxing at use site
    static final int USE_ESCAPES = 2;       // Cannot unbox — value escapes

    private final boolean verbose;

    public ZUnboxer() {
        this(false);
    }

    public ZUnboxer(boolean verbose) {
        this.verbose = verbose;
    }

    /**
     * Transform all .class files under the given directory path.
     *
     * @param path directory containing .class files to transform
     * @return number of methods optimized
     * @throws IOException if file I/O fails
     */
    public int transformDirectory(String path) throws IOException {
        Path dir = Paths.get(path);
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Not a directory: " + path);
        }
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
     *
     * @param classFile path to the .class file
     * @return number of methods optimized in this class
     * @throws IOException if file I/O fails
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
     *
     * Strategy:
     * 1. Try both Pass 1 and Pass 2. Pass 2 requires COMPUTE_FRAMES (local types change).
     * 2. If COMPUTE_FRAMES fails (e.g., class hierarchy resolution issue), fall back to
     *    re-reading the class and applying Pass 1 only with COMPUTE_MAXS.
     */
    public byte[] transformClassBytes(byte[] classBytes) {
        // Try with both passes
        try {
            byte[] result = doTransform(classBytes, true);
            if (result != null) return result;
        } catch (Exception e) {
            // Pass 2 caused frame computation error — fall back to Pass 1 only
            if (verbose) {
                System.err.println("ZUnboxer: Pass 2 frame error, falling back to Pass 1: " + e.getMessage());
            }
        }

        // Fall back: Pass 1 only (re-read class from original bytes)
        return doTransform(classBytes, false);
    }

    /**
     * Internal transform implementation.
     *
     * @param classBytes original class bytes
     * @param enablePass2 whether to run Pass 2 (escape analysis)
     * @return transformed bytes, or null if no changes
     */
    private byte[] doTransform(byte[] classBytes, boolean enablePass2) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        boolean changed = false;
        for (MethodNode mn : cn.methods) {
            boolean[] result = transformMethod(cn.name, mn, enablePass2);
            if (result[0]) changed = true;
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

        // Verify the transformed bytecode — if ASM produced bad frames,
        // fall back to original bytes (return null).
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            CheckClassAdapter.verify(new ClassReader(result), false, pw);
        } catch (Exception e) {
            if (verbose) {
                System.err.println("ZUnboxer: verify threw for " + cn.name + ": " + e.getMessage());
            }
            return null;
        }
        String verifyErrors = sw.toString();
        if (!verifyErrors.isEmpty()) {
            if (verbose) {
                System.err.println("ZUnboxer: verify failed for " + cn.name + ":\n" + verifyErrors);
            }
            return null;
        }

        return result;
    }

    /**
     * Attempt to optimize Z operations in a single method.
     * Runs two passes:
     *   1. Peephole: rewrite z.op(Z.apply(literal)) → z.op(literal) using Long overloads
     *   2. Escape analysis: unbox non-escaping Z locals to primitive long
     *
     * @param enablePass2 whether to run Pass 2
     * @return boolean[2]: [0] = any change made, [1] = (unused, reserved)
     */
    boolean[] transformMethod(String className, MethodNode mn, boolean enablePass2) {
        boolean[] result = {false, false};
        if (mn.instructions.size() == 0) return result;

        // Pass 1: Peephole literal argument unboxing (no escape analysis needed)
        if (rewriteLiteralArgs(mn)) {
            result[0] = true;
            if (verbose) {
                System.out.println("  " + className + "." + mn.name + mn.desc +
                        ": rewrote Z literal args to use Long overloads");
            }
        }

        // Pass 2: Z local variable unboxing (escape analysis)
        if (enablePass2) {
            Map<Integer, LocalInfo> zLocals = findZLocals(mn);
            if (!zLocals.isEmpty()) {
                markEscapingLocals(mn, zLocals);

                Set<Integer> unboxable = new HashSet<>();
                for (Map.Entry<Integer, LocalInfo> entry : zLocals.entrySet()) {
                    LocalInfo info = entry.getValue();
                    if (!info.escapes) {
                        // Cost heuristic: only unbox if saves at stores outweigh re-boxing at loads.
                        // Each store saves one Z allocation; each rebox load adds one Z allocation.
                        if (info.reboxCount <= info.stores.size()) {
                            unboxable.add(entry.getKey());
                        } else if (verbose) {
                            System.out.println("  " + className + "." + mn.name + mn.desc +
                                    ": skipping slot " + entry.getKey() +
                                    " (stores=" + info.stores.size() + " rebox=" + info.reboxCount + ")");
                        }
                    }
                }

                if (!unboxable.isEmpty()) {
                    if (verbose) {
                        System.out.println("  " + className + "." + mn.name + mn.desc +
                                ": unboxing Z locals " + unboxable);
                    }
                    if (rewriteInstructions(mn, unboxable)) {
                        result[0] = true;
                    }
                }
            }
        }

        return result;
    }

    /**
     * Pass 1: Peephole rewrite of Z literal argument boxing.
     *
     * Matches the bytecode pattern:
     * <pre>
     *   [receiver on stack]
     *   GETSTATIC Z$MP$.MODULE$           // (1) load singleton
     *   <long push: LCONST/LDC/I2L etc.>  // (2) push long value
     *   INVOKEVIRTUAL Z$MP$.apply(J)Z     // (3) box to Z
     *   INVOKEVIRTUAL Z.op(Z)Z or Z.op(Z)Z // (4) call with Z arg
     * </pre>
     *
     * Rewrites to:
     * <pre>
     *   [receiver on stack]
     *   <long push>                        // keep the long value
     *   INVOKEVIRTUAL Z.op(J)Z            // use Long overload
     * </pre>
     *
     * Also handles the (int) → (long) case where Z.MP$.apply(I)Z is used,
     * by converting to I2L + Long overload.
     *
     * @return true if any rewrites were performed
     */
    boolean rewriteLiteralArgs(MethodNode mn) {
        boolean changed = false;
        InsnList insns = mn.instructions;

        for (AbstractInsnNode insn = insns.getFirst(); insn != null; ) {
            AbstractInsnNode next = insn.getNext();

            // Look for Z.op(Lorg/sireum/Z;)... where op is a binary Z op
            // Z is a Scala trait → may be INVOKEVIRTUAL or INVOKEINTERFACE
            if (insn instanceof MethodInsnNode) {
                MethodInsnNode call = (MethodInsnNode) insn;
                if (isZInstanceCall(call) &&
                        Z_BIN_OPS.contains(call.name)) {

                    boolean isArith = Z_TO_Z.equals(call.desc);
                    boolean isComp = Z_TO_B.equals(call.desc);

                    if (isArith || isComp) {
                        // Check if the argument was boxed from a literal: Z.MP$.apply(J/I)
                        AbstractInsnNode boxCall = previousSignificant(insn);
                        if (boxCall instanceof MethodInsnNode && isZBoxingCall((MethodInsnNode) boxCall)) {
                            MethodInsnNode boxing = (MethodInsnNode) boxCall;
                            boolean isLongApply = MP_APPLY_DESC.equals(boxing.desc);
                            boolean isIntApply = ("(I)" + Z_DESC).equals(boxing.desc);

                            if (isLongApply || isIntApply) {
                                // Find the GETSTATIC Z$MP$.MODULE$ before the long/int push
                                AbstractInsnNode valuePush = previousSignificant(boxCall);
                                if (valuePush != null) {
                                    AbstractInsnNode getstatic = previousSignificant(valuePush);
                                    if (getstatic instanceof FieldInsnNode) {
                                        FieldInsnNode field = (FieldInsnNode) getstatic;
                                        if (field.getOpcode() == Opcodes.GETSTATIC &&
                                                Z_MP_MODULE.equals(field.name) &&
                                                (Z_MP.equals(field.owner) || Z_COMP.equals(field.owner))) {

                                            // Remove GETSTATIC and boxing call
                                            insns.remove(getstatic);
                                            insns.remove(boxCall);

                                            if (isIntApply) {
                                                // Replace int push with long push (avoids ICONST+I2L
                                                // two-instruction sequence that complicates Pass 2 matching)
                                                insns.set(valuePush, intPushToLongPush(valuePush));
                                            }

                                            // Change the op call descriptor from (Z)Z/(Z)boolean to (J)Z/(J)boolean
                                            String newDesc = isArith ? L_TO_Z : L_TO_B;
                                            insns.set(insn, new MethodInsnNode(
                                                    call.getOpcode(), call.owner, call.name,
                                                    newDesc, call.itf));

                                            changed = true;
                                            // Restart from beginning (conservative — instructions were removed)
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

            insn = next;
        }

        return changed;
    }

    /**
     * Information about a local variable that holds Z.
     */
    static class LocalInfo {
        final int slot;
        boolean escapes;
        /** Number of load sites that would require re-boxing (receiver of Z method, IS/MS.apply arg) */
        int reboxCount;
        /** Set of instruction indices where this local is stored */
        final Set<AbstractInsnNode> stores = new HashSet<>();
        /** Set of instruction indices where this local is loaded */
        final Set<AbstractInsnNode> loads = new HashSet<>();

        LocalInfo(int slot) {
            this.slot = slot;
            this.escapes = false;
            this.reboxCount = 0;
        }
    }

    /**
     * Calculate the number of local variable slots occupied by method parameters.
     * Includes slot 0 for 'this' in non-static methods.
     */
    static int parameterSlotCount(MethodNode mn) {
        int slots = (mn.access & Opcodes.ACC_STATIC) != 0 ? 0 : 1;
        Type[] argTypes = Type.getArgumentTypes(mn.desc);
        for (Type t : argTypes) {
            slots += t.getSize();
        }
        return slots;
    }

    /**
     * Find local variable slots that store Z values.
     * We identify them by:
     * 1. LocalVariableNode entries with descriptor "Lorg/sireum/Z;"
     * 2. ASTORE instructions preceded by Z-producing instructions
     *
     * Method parameters are excluded — they arrive from callers as object references
     * and cannot have their storage type changed.
     */
    Map<Integer, LocalInfo> findZLocals(MethodNode mn) {
        Map<Integer, LocalInfo> result = new HashMap<>();
        int paramSlots = parameterSlotCount(mn);

        // Use local variable table if available
        if (mn.localVariables != null) {
            for (LocalVariableNode lvn : mn.localVariables) {
                if (Z_DESC.equals(lvn.desc) && lvn.index >= paramSlots) {
                    result.put(lvn.index, new LocalInfo(lvn.index));
                }
            }
        }

        // Also scan for ASTORE after Z-producing calls (for cases without debug info)
        for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
            if (insn.getOpcode() == Opcodes.ASTORE) {
                VarInsnNode store = (VarInsnNode) insn;
                if (store.var < paramSlots) continue; // skip method parameters
                AbstractInsnNode prev = previousSignificant(insn);
                if (prev != null && producesZ(prev)) {
                    result.computeIfAbsent(store.var, LocalInfo::new);
                    result.get(store.var).stores.add(insn);
                }
            }
            if (insn.getOpcode() == Opcodes.ALOAD) {
                VarInsnNode load = (VarInsnNode) insn;
                LocalInfo info = result.get(load.var);
                if (info != null) {
                    info.loads.add(insn);
                }
            }
        }

        return result;
    }

    /**
     * Mark Z locals that escape (are used in non-arithmetic/comparison contexts).
     */
    void markEscapingLocals(MethodNode mn, Map<Integer, LocalInfo> zLocals) {
        for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
            if (insn.getOpcode() == Opcodes.ALOAD) {
                VarInsnNode load = (VarInsnNode) insn;
                LocalInfo info = zLocals.get(load.var);
                if (info == null) continue;

                // Check what happens after this ALOAD
                AbstractInsnNode next = nextSignificant(insn);
                if (next == null) {
                    info.escapes = true;
                    continue;
                }

                int safety = classifyZUse(next, load, mn, zLocals);
                if (safety == USE_ESCAPES) {
                    info.escapes = true;
                } else if (safety == USE_NEEDS_REBOX) {
                    info.reboxCount++;
                }
                // USE_OPTIMIZABLE: no cost — toLong/toInt/long-overload-arg/astore
            }
        }

        // Also mark locals that are stored from non-Z-producing sources
        for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
            if (insn.getOpcode() == Opcodes.ASTORE) {
                VarInsnNode store = (VarInsnNode) insn;
                LocalInfo info = zLocals.get(store.var);
                if (info == null) continue;

                AbstractInsnNode prev = previousSignificant(insn);
                if (prev == null) {
                    info.escapes = true;
                    continue;
                }

                // The value being stored must come from a Z-producing instruction
                // or from another Z local (ALOAD of a Z local)
                if (!producesZ(prev) && !isZLocalLoad(prev, zLocals)) {
                    info.escapes = true;
                }
            }
        }
    }

    /**
     * Classify how a Z local is used at this point.
     * Returns USE_OPTIMIZABLE, USE_NEEDS_REBOX, or USE_ESCAPES.
     *
     * Safe uses (USE_OPTIMIZABLE — no re-boxing needed at rewrite time):
     * - As argument of Z instance method: z.op(thisLocal) — will use long overload
     * - Z.toLong, Z.toInt extraction
     * - Stored to another unboxable Z local
     * - As receiver of Z.op(long): thisLocal.op(long) — will use Z.MP$ static dispatch
     * - As receiver of Z.op(Z) where arg is another ALOAD — may optimize to Z.MP$(JJ)
     *
     * Safe uses (USE_NEEDS_REBOX — safe to unbox but needs re-boxing at use site):
     * - IS.apply(Z) / MS.apply(Z) — array indexing
     * - As receiver/argument in patterns we can't optimize to (JJ) at rewrite time
     *
     * Unsafe uses (USE_ESCAPES — cannot unbox):
     * - Passed to unknown method, returned, stored to field, etc.
     */
    int classifyZUse(AbstractInsnNode next, VarInsnNode load, MethodNode mn,
                     Map<Integer, LocalInfo> zLocals) {
        // Case 1: next instruction IS a method call — z is the last argument (or sole receiver)
        if (next instanceof MethodInsnNode) {
            MethodInsnNode call = (MethodInsnNode) next;

            // Z instance methods (z is argument position)
            if (isZInstanceCall(call)) {
                return isRecognizedZInstanceMethod(call) ? USE_OPTIMIZABLE : USE_ESCAPES;
            }

            // Z.MP$ methods
            if (call.getOpcode() == Opcodes.INVOKEVIRTUAL && Z_MP.equals(call.owner)) {
                return isRecognizedZMPMethod(call) ? USE_OPTIMIZABLE : USE_ESCAPES;
            }

            // IS/MS.apply(Object)Object — array indexing with Z index
            // With long overloads available, we can directly use IS/MS.apply(J)Object
            if ((call.getOpcode() == Opcodes.INVOKEVIRTUAL || call.getOpcode() == Opcodes.INVOKEINTERFACE) &&
                    (IS_TYPE.equals(call.owner) || MS_TYPE.equals(call.owner)) &&
                    "apply".equals(call.name) &&
                    IS_APPLY_DESC.equals(call.desc)) {
                return USE_OPTIMIZABLE;
            }
            return USE_ESCAPES;
        }

        // Case 2: stored to another unboxable Z local
        if (next.getOpcode() == Opcodes.ASTORE) {
            VarInsnNode store = (VarInsnNode) next;
            return zLocals.containsKey(store.var) ? USE_OPTIMIZABLE : USE_ESCAPES;
        }

        // Case 3: z is the receiver — ALOAD z → <long push> → Z.op(J)Z/B
        if (isLongPush(next)) {
            AbstractInsnNode afterArg = nextSignificant(next);
            if (afterArg instanceof MethodInsnNode) {
                MethodInsnNode call = (MethodInsnNode) afterArg;
                if (isZInstanceCall(call) && Z_BIN_OPS.contains(call.name) &&
                        (L_TO_Z.equals(call.desc) || L_TO_B.equals(call.desc))) {
                    return USE_OPTIMIZABLE;
                }
            }
        }

        // Case 4: z is the receiver — ALOAD z → ALOAD z2 → Z.op(Z/J)Z/B
        if (next.getOpcode() == Opcodes.ALOAD) {
            AbstractInsnNode afterArg = nextSignificant(next);
            if (afterArg instanceof MethodInsnNode) {
                MethodInsnNode call = (MethodInsnNode) afterArg;
                if (isZInstanceCall(call) && Z_BIN_OPS.contains(call.name)) {
                    if (Z_TO_Z.equals(call.desc) || Z_TO_B.equals(call.desc) ||
                            L_TO_Z.equals(call.desc) || L_TO_B.equals(call.desc)) {
                        return USE_NEEDS_REBOX; // may optimize to (JJ) if arg is also unboxable
                    }
                }
            }
        }

        // Case 5: z is the receiver, arg is a boxing sequence (pre-Pass-1 pattern)
        // ALOAD z → GETSTATIC Z$MP$.MODULE$ (or Z$.MODULE$) → <push> → apply(I/J)Z → Z.op(Z)Z
        if (next instanceof FieldInsnNode) {
            FieldInsnNode field = (FieldInsnNode) next;
            if (field.getOpcode() == Opcodes.GETSTATIC &&
                    Z_MP_MODULE.equals(field.name) &&
                    (Z_MP.equals(field.owner) || Z_COMP.equals(field.owner))) {
                return USE_NEEDS_REBOX; // Pass 1 will simplify this, then Pass 2 can optimize
            }
        }

        return USE_ESCAPES;
    }

    boolean isRecognizedZInstanceMethod(MethodInsnNode call) {
        String name = call.name;
        String desc = call.desc;

        // Z.+(Z), Z.-(Z), Z.*(Z), Z./(Z), Z.%(Z) → all return Z
        if (("$plus".equals(name) || "$minus".equals(name) || "$times".equals(name) ||
                "$div".equals(name) || "$percent".equals(name))) {
            if (("(" + Z_DESC + ")" + Z_DESC).equals(desc) || ("(J)" + Z_DESC).equals(desc) ||
                    ("(I)" + Z_DESC).equals(desc)) {
                return true;
            }
        }

        // Z.>(Z), Z.<(Z), Z.>=(Z), Z.<=(Z) → return boolean (B)
        if ("$greater".equals(name) || "$less".equals(name) ||
                "$greater$eq".equals(name) || "$less$eq".equals(name)) {
            if (("(" + Z_DESC + ")Z").equals(desc) || "(J)Z".equals(desc) || "(I)Z".equals(desc)) {
                return true;
            }
        }

        // Z.===(Z), Z.=!=(Z)
        if ("$eq$eq$eq".equals(name) || "$eq$bang$eq".equals(name)) {
            if (("(" + Z_DESC + ")Z").equals(desc)) {
                return true;
            }
        }

        // Z.toLong, Z.toInt
        if ("toLong".equals(name) && "()J".equals(desc)) return true;
        if ("toInt".equals(name) && "()I".equals(desc)) return true;

        // Z.increase, Z.decrease
        if ("increase".equals(name) && ("()" + Z_DESC).equals(desc)) return true;
        if ("decrease".equals(name) && ("()" + Z_DESC).equals(desc)) return true;

        return false;
    }

    boolean isRecognizedZMPMethod(MethodInsnNode call) {
        String name = call.name;
        // Z.MP$.$plus, $minus, etc. (Z, Z) → Z, (Z, long) → Z, (long, long) → Z
        if ("$plus".equals(name) || "$minus".equals(name) || "$times".equals(name) ||
                "$div".equals(name) || "$percent".equals(name)) {
            if (ZZ_TO_Z.equals(call.desc) || ZL_TO_Z.equals(call.desc) ||
                    LL_TO_Z.equals(call.desc)) return true;
        }
        // Comparisons: (Z, Z) → B, (Z, long) → B, (long, long) → B
        if ("$greater".equals(name) || "$less".equals(name) ||
                "$greater$eq".equals(name) || "$less$eq".equals(name)) {
            if (ZZ_TO_B.equals(call.desc) || ZL_TO_B.equals(call.desc) ||
                    LL_TO_B.equals(call.desc)) return true;
        }
        if ("isEqual".equals(name)) return true;
        return false;
    }

    /**
     * Rewrite instructions to use primitive long for unboxable Z locals.
     *
     * The key transformations:
     *
     * 1. Z.MP$.apply(long) → ASTORE zLocal  becomes  LSTORE zLocal
     *    (the long is already on stack from the apply argument; we remove the apply call)
     *
     * 2. ALOAD zLocal → INVOKEVIRTUAL Z.toLong()
     *    becomes  LLOAD zLocal (long already on stack)
     *
     * 3. ALOAD zLocal → INVOKEVIRTUAL Z.toInt()
     *    becomes  LLOAD zLocal → L2I
     *
     * 4. ALOAD zLocal → [any other use]
     *    becomes  LLOAD zLocal → [re-box to Z] → [original use]
     */
    boolean rewriteInstructions(MethodNode mn, Set<Integer> unboxable) {
        boolean changed = false;
        InsnList insns = mn.instructions;

        // Allocate new slot indices for long locals (each takes 2 slots)
        // We remap each unboxable Z local to a new slot at the end of the frame
        int nextSlot = mn.maxLocals;
        Map<Integer, Integer> slotRemap = new HashMap<>();
        for (int slot : unboxable) {
            slotRemap.put(slot, nextSlot);
            nextSlot += 2; // long takes 2 slots
        }
        mn.maxLocals = nextSlot;

        for (AbstractInsnNode insn = insns.getFirst(); insn != null; ) {
            AbstractInsnNode next = insn.getNext();

            // ASTORE to unboxable Z local
            if (insn.getOpcode() == Opcodes.ASTORE) {
                VarInsnNode store = (VarInsnNode) insn;
                Integer newSlot = slotRemap.get(store.var);
                if (newSlot != null) {
                    // The value on stack should be a Z reference.
                    // Check if the previous instruction is Z.MP$.apply(long) —
                    // in that case we can eliminate the apply entirely.
                    AbstractInsnNode prev = previousSignificant(insn);
                    if (prev instanceof MethodInsnNode) {
                        MethodInsnNode prevCall = (MethodInsnNode) prev;
                        if (isZBoxingCall(prevCall)) {
                            boolean isIntApply = ("(I)" + Z_DESC).equals(prevCall.desc);
                            // Remove boxing (GETSTATIC + apply), raw value stays on stack
                            removeZBoxingSequence(insns, prevCall);
                            if (isIntApply) {
                                // apply(I)Z left an int on the stack; convert to long for LSTORE
                                insns.insertBefore(insn, new InsnNode(Opcodes.I2L));
                            }
                            // Replace ASTORE with LSTORE
                            insns.set(insn, new VarInsnNode(Opcodes.LSTORE, newSlot));
                            changed = true;
                            insn = insns.getFirst(); // restart scan
                            continue;
                        } else if (isZArithmeticResult(prevCall)) {
                            // Result of Z arithmetic — it returns Z, cast to Z$MP$Long and extract
                            insns.insertBefore(insn, new TypeInsnNode(Opcodes.CHECKCAST, Z_MP_LONG));
                            insns.insertBefore(insn, new MethodInsnNode(
                                    Opcodes.INVOKEVIRTUAL, Z_MP_LONG, "toLong", TO_LONG_DESC, false));
                            insns.set(insn, new VarInsnNode(Opcodes.LSTORE, newSlot));
                            changed = true;
                            insn = insns.getFirst();
                            continue;
                        }
                    }
                    // General case: Z ref on stack → cast to Z$MP$Long → toLong → LSTORE
                    insns.insertBefore(insn, new TypeInsnNode(Opcodes.CHECKCAST, Z_MP_LONG));
                    insns.insertBefore(insn, new MethodInsnNode(
                            Opcodes.INVOKEVIRTUAL, Z_MP_LONG, "toLong", TO_LONG_DESC, false));
                    insns.set(insn, new VarInsnNode(Opcodes.LSTORE, newSlot));
                    changed = true;
                    insn = insns.getFirst();
                    continue;
                }
            }

            // ALOAD of unboxable Z local
            if (insn.getOpcode() == Opcodes.ALOAD) {
                VarInsnNode load = (VarInsnNode) insn;
                Integer newSlot = slotRemap.get(load.var);
                if (newSlot != null) {
                    AbstractInsnNode usage = nextSignificant(insn);

                    if (usage instanceof MethodInsnNode) {
                        MethodInsnNode call = (MethodInsnNode) usage;

                        // Z.toLong() — just load the long directly
                        if (isZInstanceCall(call) && "toLong".equals(call.name) &&
                                TO_LONG_DESC.equals(call.desc)) {
                            insns.set(insn, new VarInsnNode(Opcodes.LLOAD, newSlot));
                            insns.remove(usage); // remove the toLong call
                            changed = true;
                            insn = insns.getFirst();
                            continue;
                        }

                        // Z.toInt() — load long, L2I
                        if (isZInstanceCall(call) && "toInt".equals(call.name) &&
                                "()I".equals(call.desc)) {
                            insns.set(insn, new VarInsnNode(Opcodes.LLOAD, newSlot));
                            insns.set(usage, new InsnNode(Opcodes.L2I));
                            changed = true;
                            insn = insns.getFirst();
                            continue;
                        }

                        // Z.op(Z)Z or Z.op(Z)boolean — unboxed local is the argument.
                        // Use the (long) overload to avoid re-boxing.
                        if (isZInstanceCall(call) && Z_BIN_OPS.contains(call.name)) {
                            if (Z_TO_Z.equals(call.desc)) {
                                insns.set(insn, new VarInsnNode(Opcodes.LLOAD, newSlot));
                                insns.set(usage, new MethodInsnNode(
                                        call.getOpcode(), call.owner, call.name, L_TO_Z, call.itf));
                                changed = true;
                                insn = insns.getFirst();
                                continue;
                            }
                            if (Z_TO_B.equals(call.desc)) {
                                insns.set(insn, new VarInsnNode(Opcodes.LLOAD, newSlot));
                                insns.set(usage, new MethodInsnNode(
                                        call.getOpcode(), call.owner, call.name, L_TO_B, call.itf));
                                changed = true;
                                insn = insns.getFirst();
                                continue;
                            }
                        }

                        // IS/MS.apply(Object)Object — unboxed Z local is the index argument
                        // Rewrite to IS/MS.apply(J)Object — use long overload
                        if ((call.getOpcode() == Opcodes.INVOKEVIRTUAL ||
                                call.getOpcode() == Opcodes.INVOKEINTERFACE) &&
                                (IS_TYPE.equals(call.owner) || MS_TYPE.equals(call.owner)) &&
                                "apply".equals(call.name) &&
                                IS_APPLY_DESC.equals(call.desc)) {
                            insns.set(insn, new VarInsnNode(Opcodes.LLOAD, newSlot));
                            insns.set(usage, new MethodInsnNode(
                                    call.getOpcode(), call.owner, call.name,
                                    IS_APPLY_LONG_DESC, call.itf));
                            changed = true;
                            insn = insns.getFirst();
                            continue;
                        }
                    }

                    // Receiver case A: ALOAD z (unboxable) → <long push> → Z.op(J)Z/B
                    // Rewrite to: LLOAD z_long → <long push> → INVOKESTATIC ZUnboxer.zOp(JJ)Z/B
                    if (usage != null && isLongPush(usage)) {
                        AbstractInsnNode afterArg = nextSignificant(usage);
                        if (afterArg instanceof MethodInsnNode) {
                            MethodInsnNode call = (MethodInsnNode) afterArg;
                            if (isZInstanceCall(call) && Z_BIN_OPS.contains(call.name)) {
                                String staticName = Z_STATIC_OPS.get(call.name);
                                if (staticName != null && L_TO_Z.equals(call.desc)) {
                                    insns.set(insn, new VarInsnNode(Opcodes.LLOAD, newSlot));
                                    insns.set(afterArg, new MethodInsnNode(
                                            Opcodes.INVOKESTATIC, ZUNBOXER_TYPE, staticName, LL_TO_Z, false));
                                    changed = true;
                                    insn = insns.getFirst();
                                    continue;
                                }
                                if (staticName != null && L_TO_B.equals(call.desc)) {
                                    insns.set(insn, new VarInsnNode(Opcodes.LLOAD, newSlot));
                                    insns.set(afterArg, new MethodInsnNode(
                                            Opcodes.INVOKESTATIC, ZUNBOXER_TYPE, staticName, LL_TO_B, false));
                                    changed = true;
                                    insn = insns.getFirst();
                                    continue;
                                }
                            }
                        }
                    }

                    // Receiver case B: ALOAD z1 (unboxable) → ALOAD z2 (unboxable) → Z.op(Z)Z/B
                    // Both operands are unboxable: LLOAD z1 → LLOAD z2 → INVOKESTATIC ZUnboxer.zOp(JJ)Z/B
                    if (usage != null && usage.getOpcode() == Opcodes.ALOAD) {
                        VarInsnNode argLoad = (VarInsnNode) usage;
                        Integer argNewSlot = slotRemap.get(argLoad.var);
                        if (argNewSlot != null) {
                            AbstractInsnNode afterArg = nextSignificant(usage);
                            if (afterArg instanceof MethodInsnNode) {
                                MethodInsnNode call = (MethodInsnNode) afterArg;
                                if (isZInstanceCall(call) && Z_BIN_OPS.contains(call.name)) {
                                    String staticName = Z_STATIC_OPS.get(call.name);
                                    if (staticName != null && Z_TO_Z.equals(call.desc)) {
                                        insns.set(insn, new VarInsnNode(Opcodes.LLOAD, newSlot));
                                        insns.set(usage, new VarInsnNode(Opcodes.LLOAD, argNewSlot));
                                        insns.set(afterArg, new MethodInsnNode(
                                                Opcodes.INVOKESTATIC, ZUNBOXER_TYPE, staticName, LL_TO_Z, false));
                                        changed = true;
                                        insn = insns.getFirst();
                                        continue;
                                    }
                                    if (staticName != null && Z_TO_B.equals(call.desc)) {
                                        insns.set(insn, new VarInsnNode(Opcodes.LLOAD, newSlot));
                                        insns.set(usage, new VarInsnNode(Opcodes.LLOAD, argNewSlot));
                                        insns.set(afterArg, new MethodInsnNode(
                                                Opcodes.INVOKESTATIC, ZUNBOXER_TYPE, staticName, LL_TO_B, false));
                                        changed = true;
                                        insn = insns.getFirst();
                                        continue;
                                    }
                                }
                            }
                        }
                    }

                    // Default: re-box the long back to Z$MP$Long for any use we don't optimize
                    // NEW + DUP before LLOAD so objectref is under long for <init>(J)V
                    TypeInsnNode newInsn = new TypeInsnNode(Opcodes.NEW, Z_MP_LONG);
                    insns.set(insn, newInsn);
                    InsnList tail = new InsnList();
                    tail.add(new InsnNode(Opcodes.DUP));
                    tail.add(new VarInsnNode(Opcodes.LLOAD, newSlot));
                    tail.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, Z_MP_LONG, "<init>",
                            Z_MP_LONG_INIT_DESC, false));
                    AbstractInsnNode tailLast = tail.getLast();
                    insns.insert(newInsn, tail);
                    changed = true;
                    // Skip past the newly inserted instructions
                    insn = tailLast.getNext();
                    continue;
                }
            }

            insn = next;
        }

        // Update local variable table if present
        if (mn.localVariables != null) {
            for (LocalVariableNode lvn : mn.localVariables) {
                Integer newSlot = slotRemap.get(lvn.index);
                if (newSlot != null) {
                    lvn.index = newSlot;
                    lvn.desc = "J"; // long
                    lvn.signature = null;
                }
            }
        }

        return changed;
    }

    /**
     * Check if a method call boxes an int/long to Z.
     * Matches both Z$MP$.apply(I/J)Z and Z$.apply(I/J)Z (the Z companion).
     */
    boolean isZBoxingCall(MethodInsnNode call) {
        if (call.getOpcode() == Opcodes.INVOKEVIRTUAL && "apply".equals(call.name)) {
            if (Z_MP.equals(call.owner) || Z_COMP.equals(call.owner)) {
                return MP_APPLY_DESC.equals(call.desc) || ("(I)" + Z_DESC).equals(call.desc);
            }
        }
        return false;
    }

    /**
     * Check if a method call produces a Z result from Z arithmetic.
     */
    boolean isZArithmeticResult(MethodInsnNode call) {
        if (call.desc.endsWith(")" + Z_DESC)) {
            String name = call.name;
            return "$plus".equals(name) || "$minus".equals(name) || "$times".equals(name) ||
                    "$div".equals(name) || "$percent".equals(name) ||
                    "increase".equals(name) || "decrease".equals(name);
        }
        return false;
    }

    /**
     * Remove the Z boxing sequence: GETSTATIC (Z$MP$ or Z$).MODULE$ → [value push] → apply(I/J)Z.
     * After removal, only the int/long value remains on the stack.
     */
    void removeZBoxingSequence(InsnList insns, MethodInsnNode applyCall) {
        AbstractInsnNode scan = applyCall.getPrevious();
        insns.remove(applyCall);

        // Scan backwards for the nearest GETSTATIC MODULE$ (Z$MP$ or Z$)
        for (AbstractInsnNode node = previousSignificant(scan); node != null; node = previousSignificant(node)) {
            if (node instanceof FieldInsnNode) {
                FieldInsnNode field = (FieldInsnNode) node;
                if (field.getOpcode() == Opcodes.GETSTATIC &&
                        Z_MP_MODULE.equals(field.name) &&
                        (Z_MP.equals(field.owner) || Z_COMP.equals(field.owner))) {
                    insns.remove(field);
                    return;
                }
            }
            // Don't scan too far back
            if (node.getOpcode() == Opcodes.ASTORE || node.getOpcode() == Opcodes.LSTORE ||
                    node.getOpcode() == Opcodes.ISTORE || node.getOpcode() == Opcodes.RETURN ||
                    node instanceof JumpInsnNode) {
                break;
            }
        }
    }

    /**
     * Check if an instruction produces a Z value.
     */
    boolean producesZ(AbstractInsnNode insn) {
        if (insn instanceof MethodInsnNode) {
            MethodInsnNode call = (MethodInsnNode) insn;
            return call.desc.endsWith(")" + Z_DESC);
        }
        return false;
    }

    /**
     * Check if an instruction is an ALOAD of a known Z local.
     */
    boolean isZLocalLoad(AbstractInsnNode insn, Map<Integer, LocalInfo> zLocals) {
        if (insn.getOpcode() == Opcodes.ALOAD) {
            return zLocals.containsKey(((VarInsnNode) insn).var);
        }
        return false;
    }

    /**
     * Convert an int-pushing instruction to a long-pushing instruction.
     * ICONST_0 → LCONST_0, ICONST_1 → LCONST_1, other → LDC (long value).
     */
    static AbstractInsnNode intPushToLongPush(AbstractInsnNode intPush) {
        long value;
        switch (intPush.getOpcode()) {
            case Opcodes.ICONST_M1: value = -1L; break;
            case Opcodes.ICONST_0: return new InsnNode(Opcodes.LCONST_0);
            case Opcodes.ICONST_1: return new InsnNode(Opcodes.LCONST_1);
            case Opcodes.ICONST_2: value = 2L; break;
            case Opcodes.ICONST_3: value = 3L; break;
            case Opcodes.ICONST_4: value = 4L; break;
            case Opcodes.ICONST_5: value = 5L; break;
            case Opcodes.BIPUSH: value = ((IntInsnNode) intPush).operand; break;
            case Opcodes.SIPUSH: value = ((IntInsnNode) intPush).operand; break;
            case Opcodes.LDC:
                value = ((Number) ((LdcInsnNode) intPush).cst).longValue(); break;
            default:
                // Fallback: keep original and insert I2L after (caller handles)
                return intPush;
        }
        return new LdcInsnNode(value);
    }

    /**
     * Check if a method call is a Z instance method call (INVOKEVIRTUAL or INVOKEINTERFACE).
     * Z is a Scala trait (Java interface), so Z instance methods may use either opcode.
     */
    static boolean isZInstanceCall(MethodInsnNode call) {
        return (call.getOpcode() == Opcodes.INVOKEVIRTUAL || call.getOpcode() == Opcodes.INVOKEINTERFACE) &&
                isZType(call.owner);
    }

    /**
     * Check if an instruction pushes a long value onto the stack.
     */
    static boolean isLongPush(AbstractInsnNode insn) {
        int op = insn.getOpcode();
        if (op == Opcodes.LCONST_0 || op == Opcodes.LCONST_1 || op == Opcodes.LLOAD) return true;
        if (op == Opcodes.LDC) {
            return ((LdcInsnNode) insn).cst instanceof Long;
        }
        return false;
    }

    /**
     * Check if a type is Z or a Z subtype.
     */
    static boolean isZType(String internalName) {
        return Z_TYPE.equals(internalName) || Z_MP_LONG.equals(internalName) ||
                "org/sireum/Z$MP$BigInt".equals(internalName);
    }

    /**
     * Get the previous non-label, non-line-number instruction.
     */
    static AbstractInsnNode previousSignificant(AbstractInsnNode insn) {
        AbstractInsnNode prev = insn.getPrevious();
        while (prev != null && (prev instanceof LabelNode || prev instanceof LineNumberNode ||
                prev instanceof FrameNode)) {
            prev = prev.getPrevious();
        }
        return prev;
    }

    /**
     * Get the next non-label, non-line-number instruction.
     */
    static AbstractInsnNode nextSignificant(AbstractInsnNode insn) {
        AbstractInsnNode next = insn.getNext();
        while (next != null && (next instanceof LabelNode || next instanceof LineNumberNode ||
                next instanceof FrameNode)) {
            next = next.getNext();
        }
        return next;
    }

    // ========================================================================
    // Static Z operations: (long, long) → Z or boolean
    //
    // Called via INVOKESTATIC by the bytecode optimizer, avoiding the
    // Z$MP$ MODULE$ singleton indirection. The fast path (no overflow)
    // constructs Z$MP$Long directly. The rare overflow path delegates
    // to the Scala Z$MP$ methods (MODULE$ overhead is irrelevant there).
    // ========================================================================

    private static org.sireum.Z bigInt(java.math.BigInteger v) {
        return new org.sireum.Z$MP$BigInt(new scala.math.BigInt(v));
    }

    /** Z addition: n1 + n2 with overflow check. */
    public static org.sireum.Z zAdd(long n1, long n2) {
        long r = n1 + n2;
        if (((n1 ^ r) & (n2 ^ r)) >= 0L)
            return new org.sireum.Z$MP$Long(r);
        return bigInt(java.math.BigInteger.valueOf(n1).add(java.math.BigInteger.valueOf(n2)));
    }

    /** Z subtraction: n1 - n2 with overflow check. */
    public static org.sireum.Z zSub(long n1, long n2) {
        long r = n1 - n2;
        if (((n1 ^ n2) & (n1 ^ r)) >= 0L)
            return new org.sireum.Z$MP$Long(r);
        return bigInt(java.math.BigInteger.valueOf(n1).subtract(java.math.BigInteger.valueOf(n2)));
    }

    /** Z multiplication: n1 * n2 with overflow check. */
    public static org.sireum.Z zMul(long n1, long n2) {
        long r = n1 * n2;
        if (r == 0L) return new org.sireum.Z$MP$Long(0L);
        boolean upgrade = false;
        if (n2 > n1) {
            if (((n2 == -1L) && (n1 == Long.MIN_VALUE)) || (r / n2 != n1))
                upgrade = true;
        } else {
            if (((n1 == -1L) && (n2 == Long.MIN_VALUE)) || (r / n1 != n2))
                upgrade = true;
        }
        if (!upgrade) return new org.sireum.Z$MP$Long(r);
        return bigInt(java.math.BigInteger.valueOf(n1).multiply(java.math.BigInteger.valueOf(n2)));
    }

    /** Z division: n1 / n2 (only overflows for Long.MIN_VALUE / -1). */
    public static org.sireum.Z zDiv(long n1, long n2) {
        long r = n1 / n2;
        if (!((n1 == Long.MIN_VALUE) && (n2 == -1L)))
            return new org.sireum.Z$MP$Long(r);
        return bigInt(java.math.BigInteger.valueOf(n1).divide(java.math.BigInteger.valueOf(n2)));
    }

    /** Z remainder: n1 % n2 (never overflows for long). */
    public static org.sireum.Z zRem(long n1, long n2) {
        return new org.sireum.Z$MP$Long(n1 % n2);
    }

    /** Z greater-than: n1 > n2. */
    public static boolean zGt(long n1, long n2) { return n1 > n2; }

    /** Z greater-or-equal: n1 >= n2. */
    public static boolean zGe(long n1, long n2) { return n1 >= n2; }

    /** Z less-than: n1 < n2. */
    public static boolean zLt(long n1, long n2) { return n1 < n2; }

    /** Z less-or-equal: n1 <= n2. */
    public static boolean zLe(long n1, long n2) { return n1 <= n2; }

    /**
     * Entry point for command-line usage.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: ZUnboxer <directory> [--verbose]");
            System.exit(1);
        }
        boolean verbose = args.length > 1 && "--verbose".equals(args[1]);
        ZUnboxer optimizer = new ZUnboxer(verbose);
        try {
            int count = optimizer.transformDirectory(args[0]);
            System.out.println("Optimized " + count + " class files.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
