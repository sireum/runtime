/*
 Copyright (c) 2017-2021, Robby, Kansas State University
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
package org.sireum

import org.objectweb.asm.{
  AnnotationVisitor, Attribute, ClassReader, ClassVisitor, ClassWriter, MethodVisitor, Opcodes, Type, TypePath
}

import java.nio.file.{FileSystems, Files, Path, Paths}

object Asm_Ext {
  val api: Int = Opcodes.ASM9

  def rewriteReleaseFence(path: Os.Path): Unit = {

    def process(p: Path): Unit = {
      if (!Files.exists(p)) {
        return
      }
      val is = Files.newInputStream(p)
      val cr = new ClassReader(is)
      val cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES)
      val cv = new ReleaseFence.CVisitor(cw)
      cr.accept(cv, ClassReader.SKIP_FRAMES)
      is.close()
      Files.write(p, cw.toByteArray)
    }

    if (path.isDir) {
      process(Paths.get((path / "scala" / "collection" / "immutable" / "VM.class").value.value))
      process(Paths.get((path / "scala" / "runtime" / "Statics.class").value.value))
    } else {
      val fs = FileSystems.newFileSystem(Paths.get(path.value.value), null.asInstanceOf[ClassLoader])
      process(fs.getPath("/scala/collection/immutable/VM.class"))
      process(fs.getPath("/scala/runtime/Statics.class"))
      fs.close()
    }

  }

  object ReleaseFence {

    class MVisitor(visitor: MethodVisitor) extends MethodVisitor(api) {

      override def visitParameter(name: Predef.String, access: Int): Unit =
        visitor.visitParameter(name, access)

      override def visitAnnotationDefault(): AnnotationVisitor =
        visitor.visitAnnotationDefault()

      override def visitAnnotation(descriptor: Predef.String, visible: Boolean): AnnotationVisitor =
        visitor.visitAnnotation(descriptor, visible)

      override def visitAnnotableParameterCount(parameterCount: Int, visible: Boolean): Unit =
        visitor.visitAnnotableParameterCount(parameterCount, visible)

      override def visitParameterAnnotation(parameter: Int, descriptor: Predef.String, visible: Boolean): AnnotationVisitor =
        visitor.visitParameterAnnotation(parameter, descriptor, visible)

      override def visitTypeAnnotation(typeRef: Int, typePath: TypePath, descriptor: Predef.String, visible: Boolean): AnnotationVisitor =
        visitor.visitTypeAnnotation(typeRef, typePath, descriptor, visible)

      override def visitAttribute(attribute: Attribute): Unit =
        visitor.visitAttribute(attribute)

      override def visitCode(): Unit = {
        visitor.visitCode()
        visitor.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(classOf[org.sireum.$internal.UnsafeUtils]),
          "releaseFence", Type.getMethodDescriptor(Type.VOID_TYPE), false)
        visitor.visitInsn(Opcodes.RETURN)
      }

      override def visitEnd(): Unit = {
        visitor.visitEnd()
      }
    }

    class CVisitor(cw: ClassWriter) extends ClassVisitor(api, cw) {
      override def visitMethod(access: Int, name: Predef.String, descriptor: Predef.String, signature: Predef.String,
                               exceptions: Array[Predef.String]): MethodVisitor = {
        val visitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        return if (name == "releaseFence") new MVisitor(visitor) else visitor
      }
    }

  }
}
