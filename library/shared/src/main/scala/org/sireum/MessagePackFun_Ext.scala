/*
 Copyright (c) 2017-2024, Robby, Kansas State University
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

object MessagePackFun_Ext {

  def write[T](f: T): ISZ[U8] = ISZ(JsonFun_Ext.printObj(f).toIndexedSeq.map(U8(_)): _*)

  def read[T](reader: MessagePack.Reader.Impl, f: ISZ[U8]): Option[T] = {
    try {
      JsonFun_Ext.parseObj(f.data.asInstanceOf[Array[Byte]].slice(0, f.size.toInt))
    } catch {
      case _: Throwable => None()
    }
  }

  def writePure0[R](f: () => R@pure): ISZ[U8] = write(f)

  def readPure0[R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): () => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write0[R](f: () => R): ISZ[U8] = write(f)

  def read0[R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): () => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure1[T1, R](f: T1 => R@pure): ISZ[U8] = write(f)

  def readPure1[T1, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): T1 => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write1[T1, R](f: T1 => R): ISZ[U8] = write(f)

  def read1[T1, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): T1 => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure2[T1, T2, R](f: (T1, T2) => R@pure): ISZ[U8] = write(f)

  def readPure2[T1, T2, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write2[T1, T2, R](f: (T1, T2) => R): ISZ[U8] = write(f)

  def read2[T1, T2, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure3[T1, T2, T3, R](f: (T1, T2, T3) => R@pure): ISZ[U8] = write(f)

  def readPure3[T1, T2, T3, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write3[T1, T2, T3, R](f: (T1, T2, T3) => R): ISZ[U8] = write(f)

  def read3[T1, T2, T3, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure4[T1, T2, T3, T4, R](f: (T1, T2, T3, T4) => R@pure): ISZ[U8] = write(f)

  def readPure4[T1, T2, T3, T4, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write4[T1, T2, T3, T4, R](f: (T1, T2, T3, T4) => R): ISZ[U8] = write(f)

  def read4[T1, T2, T3, T4, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure5[T1, T2, T3, T4, T5, R](f: (T1, T2, T3, T4, T5) => R@pure): ISZ[U8] = write(f)

  def readPure5[T1, T2, T3, T4, T5, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write5[T1, T2, T3, T4, T5, R](f: (T1, T2, T3, T4, T5) => R): ISZ[U8] = write(f)

  def read5[T1, T2, T3, T4, T5, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure6[T1, T2, T3, T4, T5, T6, R](f: (T1, T2, T3, T4, T5, T6) => R@pure): ISZ[U8] = write(f)

  def readPure6[T1, T2, T3, T4, T5, T6, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write6[T1, T2, T3, T4, T5, T6, R](f: (T1, T2, T3, T4, T5, T6) => R): ISZ[U8] = write(f)

  def read6[T1, T2, T3, T4, T5, T6, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure7[T1, T2, T3, T4, T5, T6, T7, R](f: (T1, T2, T3, T4, T5, T6, T7) => R@pure): ISZ[U8] = write(f)

  def readPure7[T1, T2, T3, T4, T5, T6, T7, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write7[T1, T2, T3, T4, T5, T6, T7, R](f: (T1, T2, T3, T4, T5, T6, T7) => R): ISZ[U8] = write(f)

  def read7[T1, T2, T3, T4, T5, T6, T7, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure8[T1, T2, T3, T4, T5, T6, T7, T8, R](f: (T1, T2, T3, T4, T5, T6, T7, T8) => R@pure): ISZ[U8] = write(f)

  def readPure8[T1, T2, T3, T4, T5, T6, T7, T8, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write8[T1, T2, T3, T4, T5, T6, T7, T8, R](f: (T1, T2, T3, T4, T5, T6, T7, T8) => R): ISZ[U8] = write(f)

  def read8[T1, T2, T3, T4, T5, T6, T7, T8, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => R@pure): ISZ[U8] = write(f)

  def readPure9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => R): ISZ[U8] = write(f)

  def read9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => R@pure): ISZ[U8] = write(f)

  def readPure10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => R): ISZ[U8] = write(f)

  def read10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => R@pure): ISZ[U8] = write(f)

  def readPure11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => R): ISZ[U8] = write(f)

  def read11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => R@pure): ISZ[U8] = write(f)

  def readPure12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => R): ISZ[U8] = write(f)

  def read12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => R@pure): ISZ[U8] = write(f)

  def readPure13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => R): ISZ[U8] = write(f)

  def read13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => R@pure): ISZ[U8] = write(f)

  def readPure14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => R): ISZ[U8] = write(f)

  def read14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => R@pure): ISZ[U8] = write(f)

  def readPure15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => R): ISZ[U8] = write(f)

  def read15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => R@pure): ISZ[U8] = write(f)

  def readPure16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => R): ISZ[U8] = write(f)

  def read16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => R@pure): ISZ[U8] = write(f)

  def readPure17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => R): ISZ[U8] = write(f)

  def read17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => R@pure): ISZ[U8] = write(f)

  def readPure18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => R): ISZ[U8] = write(f)

  def read18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => R@pure): ISZ[U8] = write(f)

  def readPure19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => R): ISZ[U8] = write(f)

  def read19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => R@pure): ISZ[U8] = write(f)

  def readPure20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => R): ISZ[U8] = write(f)

  def read20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => R@pure): ISZ[U8] = write(f)

  def readPure21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => R): ISZ[U8] = write(f)

  def read21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def writePure22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R@pure): ISZ[U8] = write(f)

  def readPure22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R@pure = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def write22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R): ISZ[U8] = write(f)

  def read22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](reader: MessagePack.Reader.Impl, f: ISZ[U8]): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R = read(reader, f) match {
    case Some(g) => g
    case _ => reader.error(reader.curr, "Failed parsing closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }
}
