/*
 * Copyright (c) 2017-2023, Robby, Kansas State University
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package org.sireum

package object logika {

  private val MaxRandomSeqSize: Z = 1024

  type B = org.sireum.B
  object B { def random: B = org.sireum.B.random }

  type Z = org.sireum.Z
  object Z { def random: Z = org.sireum.Z.random }

  type Z8 = org.sireum.Z8
  object Z8 { def random: Z8 = org.sireum.Z8.random }

  type Z16 = org.sireum.Z16
  object Z16 { def random: Z16 = org.sireum.Z16.random }

  type Z32 = org.sireum.Z32
  object Z32 { def random: Z32 = org.sireum.Z32.random }

  type Z64 = org.sireum.Z64
  object Z64 { def random: Z64 = org.sireum.Z64.random }

  type S8 = org.sireum.S8
  object S8 { def random: S8 = org.sireum.S8.random }

  type S16 = org.sireum.S16
  object S16 { def random: S16 = org.sireum.S16.random }

  type S32 = org.sireum.S32
  object S32 { def random: S32 = org.sireum.S32.random }

  type S64 = org.sireum.S64
  object S64 { def random: S64 = org.sireum.S64.random }

  type N = org.sireum.N
  object N { def random: N = org.sireum.N.random }

  type N8 = org.sireum.N8
  object N8 { def random: N8 = org.sireum.N8.random }

  type N16 = org.sireum.N16
  object N16 { def random: N16 = org.sireum.N16.random }

  type N32 = org.sireum.N32
  object N32 { def random: N32 = org.sireum.N32.random }

  type N64 = org.sireum.N64
  object N64 { def random: N64 = org.sireum.N64.random }

  type U8 = org.sireum.U8
  object U8 { def random: U8 = org.sireum.U8.random }

  type U16 = org.sireum.U16
  object U16 { def random: U16 = org.sireum.U16.random }

  type U32 = org.sireum.U32
  object U32 { def random: U32 = org.sireum.U32.random }

  type U64 = org.sireum.U64
  object U64 { def random: U64 = org.sireum.U64.random }

  type R = org.sireum.R
  object R { def random: R = org.sireum.R.random }

  type F32 = org.sireum.F32
  object F32 { def random: F32 = org.sireum.F32.random }

  type F64 = org.sireum.F64
  object F64 { def random: F64 = org.sireum.F64.random }

  type ZS = org.sireum.MS[Z, Z]
  type BS = org.sireum.MS[Z, B]
  type Z8S = org.sireum.MS[Z, Z8]
  type Z16S = org.sireum.MS[Z, Z16]
  type Z32S = org.sireum.MS[Z, Z32]
  type Z64S = org.sireum.MS[Z, Z64]
  type NS = org.sireum.MS[Z, N]
  type N8S = org.sireum.MS[Z, N8]
  type N16S = org.sireum.MS[Z, N16]
  type N32S = org.sireum.MS[Z, N32]
  type N64S = org.sireum.MS[Z, N64]
  type S8S = org.sireum.MS[Z, S8]
  type S16S = org.sireum.MS[Z, S16]
  type S32S = org.sireum.MS[Z, S32]
  type S64S = org.sireum.MS[Z, S64]
  type U8S = org.sireum.MS[Z, U8]
  type U16S = org.sireum.MS[Z, U16]
  type U32S = org.sireum.MS[Z, U32]
  type U64S = org.sireum.MS[Z, U64]
  type F32S = org.sireum.MS[Z, F32]
  type F64S = org.sireum.MS[Z, F64]
  type RS = org.sireum.MS[Z, R]
  type helper = org.sireum.helper
  type pure = org.sireum.pure

  val helper: org.sireum.helper.type = org.sireum.helper
  val ZS: org.sireum.ZS.type = org.sireum.ZS
  val T: B = org.sireum.T
  val F: B = org.sireum.F

  object BS {
    def apply(values: B*): BS = MSZ[B](values: _*)

    def create(size: Z, default: B): BS = MS.create(size, default)

    def random: BS = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, B.random)
      for (i <- 0 until r.size) {
        r(i) = B.random
      }
      r
    }
  }

  object Z8S {
    def apply(values: Z8*): Z8S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: Z8): Z8S = org.sireum.MSZ.create(size, default)

    def random: Z8S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, Z8.random)
      for (i <- 0 until r.size) {
        r(i) = Z8.random
      }
      r
    }
  }

  object Z16S {
    def apply(values: Z16*): Z16S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: Z16): Z16S = org.sireum.MSZ.create(size, default)

    def random: Z16S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, Z16.random)
      for (i <- 0 until r.size) {
        r(i) = Z16.random
      }
      r
    }
  }

  object Z32S {
    def apply(values: Z32*): Z32S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: Z32): Z32S = org.sireum.MSZ.create(size, default)

    def random: Z32S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, Z32.random)
      for (i <- 0 until r.size) {
        r(i) = Z32.random
      }
      r
    }
  }

  object Z64S {
    def apply(values: Z64*): Z64S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: Z64): Z64S = org.sireum.MSZ.create(size, default)

    def random: Z64S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, Z64.random)
      for (i <- 0 until r.size) {
        r(i) = Z64.random
      }
      r
    }
  }

  object NS {
    def apply(values: N*): NS = org.sireum.MSZ(values: _*)

    def create(size: Z, default: N): NS = org.sireum.MSZ.create(size, default)

    def random: NS = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, N.random)
      for (i <- 0 until r.size) {
        r(i) = N.random
      }
      r
    }
  }

  object N8S {
    def apply(values: N8*): N8S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: N8): N8S = org.sireum.MSZ.create(size, default)

    def random: N8S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, N8.random)
      for (i <- 0 until r.size) {
        r(i) = N8.random
      }
      r
    }
  }

  object N16S {
    def apply(values: N16*): N16S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: N16): N16S = org.sireum.MSZ.create(size, default)

    def random: N16S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, N16.random)
      for (i <- 0 until r.size) {
        r(i) = N16.random
      }
      r
    }
  }

  object N32S {
    def apply(values: N32*): N32S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: N32): N32S = org.sireum.MSZ.create(size, default)

    def random: N32S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, N32.random)
      for (i <- 0 until r.size) {
        r(i) = N32.random
      }
      r
    }
  }

  object N64S {
    def apply(values: N64*): N64S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: N64): N64S = org.sireum.MSZ.create(size, default)

    def random: N64S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, N64.random)
      for (i <- 0 until r.size) {
        r(i) = N64.random
      }
      r
    }
  }

  object S8S {
    def apply(values: S8*): S8S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: S8): S8S = org.sireum.MSZ.create(size, default)

    def random: S8S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, S8.random)
      for (i <- 0 until r.size) {
        r(i) = S8.random
      }
      r
    }
  }

  object S16S {
    def apply(values: S16*): S16S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: S16): S16S = org.sireum.MSZ.create(size, default)

    def random: S16S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, S16.random)
      for (i <- 0 until r.size) {
        r(i) = S16.random
      }
      r
    }
  }

  object S32S {
    def apply(values: S32*): S32S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: S32): S32S = org.sireum.MSZ.create(size, default)

    def random: S32S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, S32.random)
      for (i <- 0 until r.size) {
        r(i) = S32.random
      }
      r
    }
  }

  object S64S {
    def apply(values: S64*): S64S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: S64): S64S = org.sireum.MSZ.create(size, default)

    def random: S64S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, S64.random)
      for (i <- 0 until r.size) {
        r(i) = S64.random
      }
      r
    }
  }

  object U8S {
    def apply(values: U8*): U8S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: U8): U8S = org.sireum.MSZ.create(size, default)

    def random: U8S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, U8.random)
      for (i <- 0 until r.size) {
        r(i) = U8.random
      }
      r
    }
  }

  object U16S {
    def apply(values: U16*): U16S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: U16): U16S = org.sireum.MSZ.create(size, default)

    def random: U16S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, U16.random)
      for (i <- 0 until r.size) {
        r(i) = U16.random
      }
      r
    }
  }

  object U32S {
    def apply(values: U32*): U32S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: U32): U32S = org.sireum.MSZ.create(size, default)

    def random: U32S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, U32.random)
      for (i <- 0 until r.size) {
        r(i) = U32.random
      }
      r
    }
  }

  object U64S {
    def apply(values: U64*): U64S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: U64): U64S = org.sireum.MSZ.create(size, default)

    def random: U64S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, U64.random)
      for (i <- 0 until r.size) {
        r(i) = U64.random
      }
      r
    }
  }

  object F32S {
    def apply(values: F32*): F32S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: F32): F32S = org.sireum.MSZ.create(size, default)

    def random: F32S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, F32.random)
      for (i <- 0 until r.size) {
        r(i) = F32.random
      }
      r
    }
  }

  object F64S {
    def apply(values: F64*): F64S = org.sireum.MSZ(values: _*)

    def create(size: Z, default: F64): F64S = org.sireum.MSZ.create(size, default)

    def random: F64S = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, F64.random)
      for (i <- 0 until r.size) {
        r(i) = F64.random
      }
      r
    }
  }

  object RS {
    def apply(values: R*): RS = org.sireum.MSZ(values: _*)

    def create(size: Z, default: R): RS = org.sireum.MSZ.create(size, default)

    def random: RS = {
      val r = MS.create(N.random.toMP % MaxRandomSeqSize, R.random)
      for (i <- 0 until r.size) {
        r(i) = R.random
      }
      r
    }
  }

  final implicit class _MSClone[I, V](val _ms: MS[I, V]) extends AnyVal {
    def clone: MS[I, V] = _ms.$clone
  }

  final implicit class _Slang(val _sc: StringContext) extends AnyVal {

    def z(args: scala.Any*): Z = org.sireum.Z.$String(_sc.parts.mkString(""))

    def z8(args: scala.Any*): Z8 = org.sireum.Z8.$String(_sc.parts.mkString(""))

    def z16(args: scala.Any*): Z16 = org.sireum.Z16.$String(_sc.parts.mkString(""))

    def z32(args: scala.Any*): Z32 = org.sireum.Z32.$String(_sc.parts.mkString(""))

    def z64(args: scala.Any*): Z64 = org.sireum.Z64.$String(_sc.parts.mkString(""))

    def n(args: scala.Any*): N = org.sireum.N.$String(_sc.parts.mkString(""))

    def n8(args: scala.Any*): N8 = org.sireum.N8.$String(_sc.parts.mkString(""))

    def n16(args: scala.Any*): N16 = org.sireum.N16.$String(_sc.parts.mkString(""))

    def n32(args: scala.Any*): N32 = org.sireum.N32.$String(_sc.parts.mkString(""))

    def n64(args: scala.Any*): N64 = org.sireum.N64.$String(_sc.parts.mkString(""))

    def s8(args: scala.Any*): S8 = org.sireum.S8.$String(_sc.parts.mkString(""))

    def s16(args: scala.Any*): S16 = org.sireum.S16.$String(_sc.parts.mkString(""))

    def s32(args: scala.Any*): S32 = org.sireum.S32.$String(_sc.parts.mkString(""))

    def s64(args: scala.Any*): S64 = org.sireum.S64.$String(_sc.parts.mkString(""))

    def u8(args: scala.Any*): U8 = org.sireum.U8.$String(_sc.parts.mkString(""))

    def u16(args: scala.Any*): U16 = org.sireum.U16.$String(_sc.parts.mkString(""))

    def u32(args: scala.Any*): U32 = org.sireum.U32.$String(_sc.parts.mkString(""))

    def u64(args: scala.Any*): U64 = org.sireum.U64.$String(_sc.parts.mkString(""))

    def f32(args: scala.Any*): F32 = org.sireum.F32.$String(_sc.parts.mkString(""))

    def f64(args: scala.Any*): F64 = org.sireum.F64.$String(_sc.parts.mkString(""))

    def r(args: scala.Any*): R = org.sireum.R.$String(_sc.parts.mkString(""))

    import scala.language.experimental.macros

    def l(args: scala.Any*): Unit = macro org.sireum.$internal.Macro.lUnit
  }

  final def readInt(msg: String = "Enter an integer: "): Z = org.sireum.promptInt(msg)

  final def println(as: Any*): Unit = org.sireum.println(as: _*)

  final def print(as: Any*): Unit = org.sireum.print(as:_ *)

  final def randomInt(): Z = org.sireum.randomInt()

  final def Z(n: scala.Int): Z = org.sireum.Z(n)

  final def Z(n: scala.Long): Z = org.sireum.Z(n)

  final def Z(n: scala.BigInt): Z = org.sireum.Z(n)

  final def String(s: Predef.String): String = org.sireum.String(s)


  final implicit class $Boolean2B(val _value: scala.Boolean) extends AnyVal {

    @inline def |^(other: B): B = _value ^ other.value

    @inline def ===(other: B): B = _value == other.value

    @inline def =!=(other: B): B = _value != other.value

  }

  final implicit class $Int2Z(val _value: scala.Int) extends AnyVal {

    @inline def <(other: Z): B = Z(_value) < other

    @inline def <=(other: Z): B = Z(_value) <= other

    @inline def >(other: Z): B = Z(_value) > other

    @inline def >=(other: Z): B = Z(_value) >= other

    @inline def ===(other: Z): B = Z(_value) == other

    @inline def =!=(other: Z): B = Z(_value) != other

    @inline def +(other: Z): Z = Z(_value) + other

    @inline def -(other: Z): Z = Z(_value) - other

    @inline def *(other: Z): Z = Z(_value) * other

    @inline def /(other: Z): Z = Z(_value) / other

    @inline def %(other: Z): Z = Z(_value) % other

    @inline def to(other: Z): ZRange[Z] = Z(_value) to other

    @inline def until(other: Z): ZRange[Z] = Z(_value) until other
  }

  final implicit class $Long2Z(val _value: scala.Long) extends AnyVal {

    @inline def <(other: Z): B = Z(_value) < other

    @inline def <=(other: Z): B = Z(_value) <= other

    @inline def >(other: Z): B = Z(_value) > other

    @inline def >=(other: Z): B = Z(_value) >= other

    @inline def ===(other: Z): B = Z(_value) == other

    @inline def =!=(other: Z): B = Z(_value) != other

    @inline def +(other: Z): Z = Z(_value) + other

    @inline def -(other: Z): Z = Z(_value) - other

    @inline def *(other: Z): Z = Z(_value) * other

    @inline def /(other: Z): Z = Z(_value) / other

    @inline def %(other: Z): Z = Z(_value) % other

    @inline def to(other: Z): ZRange[Z] = Z(_value) to other

    @inline def until(other: Z): ZRange[Z] = Z(_value) until other

  }

}
