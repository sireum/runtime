/*
 Copyright (c) 2017, Robby, Kansas State University
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

package org

package object sireum extends $internal.PackageTrait with contract {

  import language.experimental.macros

  import $internal.Macro

  final implicit class $Arrow[T1](val _t1: T1) extends AnyVal {

    @inline def ~>(o: scala.Boolean): (T1, B) = (_t1, B(o))

    @inline def ~>(o: scala.Char): (T1, C) = (_t1, C(o))

    @inline def ~>(o: scala.Int): (T1, Z) = (_t1, Z(o))

    @inline def ~>(o: scala.Long): (T1, Z) = (_t1, Z(o))

    @inline def ~>(o: scala.Float): (T1, F32) = (_t1, F32(o))

    @inline def ~>(o: scala.Double): (T1, F64) = (_t1, F64(o))

    @inline def ~>(o: Predef.String): (T1, String) = (_t1, String(o))

    def ~>[T2](t2: T2): (T1, T2) = (_t1, t2)
  }

  final implicit class $Slang(val $sc: StringContext) {

    def l[T](args: Any*): T = macro Macro.l[T]

    def lUnit(args: Any*): Unit = macro Macro.lUnit

    def lDef[T](args: Any*): T = macro Macro.lDef[T]

    final object z {
      def apply(args: Any*): Z = macro Macro.zApply
      def unapply(n: Z): scala.Boolean = {
        assume(n.isInstanceOf[Z] && $sc.parts.size == 1)
        n == Z.$String($sc.parts.head)
      }
    }

    final object c {
      def apply(args: Any*): C = macro Macro.cApply
      def unapply(c: C): scala.Boolean = {
        require($sc.parts.length == 1)
        val part = StringContext.processEscapes($sc.parts.head)
        require(part.codePointCount(0, part.length) == 1)
        c.value == part.codePointAt(0)
      }
    }

    final object r {
      def apply(args: Any*): R = macro Macro.rApply
      def unapply(n: R): scala.Boolean = {
        assume($sc.parts.size == 1)
        n == R.$String($sc.parts.head)
      }
    }

    final  object f32 {
      def apply(args: Any*): F32 = macro Macro.f32Apply
      def unapply(n: F32): scala.Boolean = {
        assume($sc.parts.size == 1)
        n == F32.$String($sc.parts.head)
      }
    }

    final object f64 {
      def apply(args: Any*): F64 = macro Macro.f64Apply
      def unapply(n: F64): scala.Boolean = {
        assume($sc.parts.size == 1)
        n == F64.$String($sc.parts.head)
      }
    }

    final object string {
      def apply(args: Any*): String = macro Macro.stringApply
      def unapply(s: String): scala.Boolean = {
        assume($sc.parts.size == 1)
        val other = StringContext.processEscapes($sc.parts.head)
        val r = s.value == other
        r
      }
    }

    final def st(args: Any*): ST = macro Macro.st
  }

  final implicit class Any2HashString(val _o: Any) extends AnyVal {
    def hash: Z = _o.hashCode
    def string: String = _o.toString
  }

  final implicit class $Boolean2B(val _value: scala.Boolean) extends AnyVal {

    @inline def |^(other: B): B = _value ^ other.value

    @inline def ===(other: B): B = _value == other.value

    @inline def =!=(other: B): B = _value != other.value

    @inline def imply_:(other: B): B = !other.value | _value

    @inline def simply_:(other: => B): B = !other.value || _value

    @inline def hash: Z = B(_value).hash

    @inline def string: String = _value.toString

    @inline def ~>(o: scala.Boolean): (B, B) = (B(_value), B(o))

    @inline def ~>(o: scala.Char): (B, C) = (B(_value), C(o))

    @inline def ~>(o: scala.Int): (B, Z) = (B(_value), Z(o))

    @inline def ~>(o: scala.Long): (B, Z) = (B(_value), Z(o))

    @inline def ~>(o: scala.Float): (B, F32) = (B(_value), F32(o))

    @inline def ~>(o: scala.Double): (B, F64) = (B(_value), F64(o))

    @inline def ~>(o: Predef.String): (B, String) = (B(_value), String(o))

    @inline def ~>[T](o: T): (B, T) = (B(_value), o)
  }

  final implicit class $Char2C(val _value: scala.Char) extends AnyVal {

    @inline def hash: Z = C(_value).hash

    @inline def string: String = C(_value).string

    @inline def <(other: C): B = C(_value) < other

    @inline def <=(other: C): B = C(_value) <= other

    @inline def >(other: C): B = C(_value) > other

    @inline def >=(other: C): B = C(_value) >= other

    @inline def ===(other: C): B = _value == other.value

    @inline def =!=(other: C): B = _value != other.value

    @inline def >>>(other: C): C = C(_value) >>> other

    @inline def <<(other: C): C = C(_value) << other

    @inline def &(other: C): C = C(_value) & other

    @inline def |(other: C): C = C(_value) | other

    @inline def |^(other: C): C = C(_value) |^ other

    @inline def ~>(o: scala.Boolean): (C, B) = (C(_value), B(o))

    @inline def ~>(o: scala.Char): (C, C) = (C(_value), C(o))

    @inline def ~>(o: scala.Int): (C, Z) = (C(_value), Z(o))

    @inline def ~>(o: scala.Long): (C, Z) = (C(_value), Z(o))

    @inline def ~>(o: scala.Float): (C, F32) = (C(_value), F32(o))

    @inline def ~>(o: scala.Double): (C, F64) = (C(_value), F64(o))

    @inline def ~>(o: Predef.String): (C, String) = (C(_value), String(o))

    @inline def ~>[T](o: T): (C, T) = (C(_value), o)
  }

  final implicit class $Int2Z(val _value: scala.Int) extends AnyVal {

    @inline def hash: Z = Z(_value).hash

    @inline def string: String = Z(_value).string

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

    @inline def increase: Z = Z(_value).increase

    @inline def decrease: Z = Z(_value).decrease

    @inline def to(other: Z): ZRange[Z] = Z(_value) to other

    @inline def until(other: Z): ZRange[Z] = Z(_value) until other

    @inline def ~>(o: scala.Boolean): (Z, B) = (Z(_value), B(o))

    @inline def ~>(o: scala.Char): (Z, C) = (Z(_value), C(o))

    @inline def ~>(o: scala.Int): (Z, Z) = (Z(_value), Z(o))

    @inline def ~>(o: scala.Long): (Z, Z) = (Z(_value), Z(o))

    @inline def ~>(o: scala.Float): (Z, F32) = (Z(_value), F32(o))

    @inline def ~>(o: scala.Double): (Z, F64) = (Z(_value), F64(o))

    @inline def ~>(o: Predef.String): (Z, String) = (Z(_value), String(o))

    @inline def ~>[T](o: T): (Z, T) = (Z(_value), o)
  }

  final implicit class $Long2Z(val _value: scala.Long) extends AnyVal {

    @inline def hash: Z = Z(_value).hash

    @inline def string: String = Z(_value).string

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

    @inline def increase: Z = Z(_value).increase

    @inline def decrease: Z = Z(_value).decrease

    @inline def to(other: Z): ZRange[Z] = Z(_value) to other

    @inline def until(other: Z): ZRange[Z] = Z(_value) until other

    @inline def ~>(o: scala.Boolean): (Z, B) = (Z(_value), B(o))

    @inline def ~>(o: scala.Char): (Z, C) = (Z(_value), C(o))

    @inline def ~>(o: scala.Int): (Z, Z) = (Z(_value), Z(o))

    @inline def ~>(o: scala.Long): (Z, Z) = (Z(_value), Z(o))

    @inline def ~>(o: scala.Float): (Z, F32) = (Z(_value), F32(o))

    @inline def ~>(o: scala.Double): (Z, F64) = (Z(_value), F64(o))

    @inline def ~>(o: Predef.String): (Z, String) = (Z(_value), String(o))

    @inline def ~>[T](o: T): (Z, T) = (Z(_value), o)
  }

  final implicit class $Float2F32(val _value: scala.Float) extends AnyVal {

    @inline def hash: Z = F32(_value).hash

    @inline def string: String = F32(_value).string

    @inline def <(other: F32): B = _value < other.value

    @inline def <=(other: F32): B = _value <= other.value

    @inline def >(other: F32): B = _value > other.value

    @inline def >=(other: F32): B = _value >= other.value

    @inline def ===(other: F32): B = _value == other.value

    @inline def =!=(other: F32): B = _value != other.value

    @inline def +(other: F32): F32 = _value + other.value

    @inline def -(other: F32): F32 = _value - other.value

    @inline def *(other: F32): F32 = _value * other.value

    @inline def /(other: F32): F32 = _value / other.value

    @inline def %(other: F32): F32 = _value % other.value

    @inline def ~>(o: scala.Boolean): (F32, B) = (F32(_value), B(o))

    @inline def ~>(o: scala.Char): (F32, C) = (F32(_value), C(o))

    @inline def ~>(o: scala.Int): (F32, Z) = (F32(_value), Z(o))

    @inline def ~>(o: scala.Long): (F32, Z) = (F32(_value), Z(o))

    @inline def ~>(o: scala.Float): (F32, F32) = (F32(_value), F32(o))

    @inline def ~>(o: scala.Double): (F32, F64) = (F32(_value), F64(o))

    @inline def ~>(o: Predef.String): (F32, String) = (F32(_value), String(o))

    @inline def ~>[T](o: T): (F32, T) = (F32(_value), o)
  }

  final implicit class $Double2F64(val _value: scala.Double) extends AnyVal {

    @inline def hash: Z = F64(_value).hash

    @inline def string: String = F64(_value).string

    @inline def <(other: F64): B = _value < other.value

    @inline def <=(other: F64): B = _value <= other.value

    @inline def >(other: F64): B = _value > other.value

    @inline def >=(other: F64): B = _value >= other.value

    @inline def ===(other: F64): B = _value == other.value

    @inline def =!=(other: F64): B = _value != other.value

    @inline def +(other: F64): F64 = F64(_value) + other

    @inline def -(other: F64): F64 = F64(_value) - other

    @inline def *(other: F64): F64 = F64(_value) * other

    @inline def /(other: F64): F64 = F64(_value) / other

    @inline def %(other: F64): F64 = F64(_value) % other

    @inline def ~>(o: scala.Boolean): (F64, B) = (F64(_value), B(o))

    @inline def ~>(o: scala.Char): (F64, C) = (F64(_value), C(o))

    @inline def ~>(o: scala.Int): (F64, Z) = (F64(_value), Z(o))

    @inline def ~>(o: scala.Long): (F64, Z) = (F64(_value), Z(o))

    @inline def ~>(o: scala.Float): (F64, F32) = (F64(_value), F32(o))

    @inline def ~>(o: scala.Double): (F64, F64) = (F64(_value), F64(o))

    @inline def ~>(o: Predef.String): (F64, String) = (F64(_value), String(o))

    @inline def ~>[T](o: T): (F64, T) = (F64(_value), o)
  }

  final implicit class $PredefString2String(val _value: Predef.String) extends AnyVal {

    @inline def hash: Z = String(_value).hash

    @inline def string: String = String(_value)

    @inline def size: Z = String(_value).size

    @inline def ===(other: String): B = _value == other.value

    @inline def =!=(other: String): B = _value != other.value

    @inline def ~>(o: scala.Boolean): (String, B) = (String(_value), B(o))

    @inline def ~>(o: scala.Char): (String, C) = (String(_value), C(o))

    @inline def ~>(o: scala.Int): (String, Z) = (String(_value), Z(o))

    @inline def ~>(o: scala.Long): (String, Z) = (String(_value), Z(o))

    @inline def ~>(o: scala.Float): (String, F32) = (String(_value), F32(o))

    @inline def ~>(o: scala.Double): (String, F64) = (String(_value), F64(o))

    @inline def ~>(o: Predef.String): (String, String) = (String(_value), String(o))

    @inline def ~>[T](o: T): (String, T) = (String(_value), o)
  }

}