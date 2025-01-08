/*
 Copyright (c) 2017-2025, Robby, Kansas State University
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

  type tailrec = scala.annotation.tailrec

  trait StepId extends Any

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

    final object f32 {
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

    final def proc(args: Any*): OsProto.Proc = macro Macro.proc

    final def st(args: Any*): ST = macro Macro.st
  }

  final implicit class Any2HashStringEqToZ(val _o: Any) extends AnyVal {
    def hash: Z = _o.hashCode
    def string: String = _o.toString
    @inline def ≡(other: Any): B = this === other
    @inline def ≢(other: Any): B = !(this === other)
    def ===(other: Any): B = {
      (_o, other) match {
        case (o1: SigTrait, o2: SigTrait) => o1 === o2
        case (o1: Immutable, o2: Immutable) => o1 == o2
        case (o1: MSigTrait, o2: MSigTrait) => o1 === o2
        case (o1: Mutable, o2: Mutable) => o1 == o2
        case (o1: Product, o2: Product) =>
          o1 match {
            case _: Tuple1[_] =>
            case _: Tuple2[_, _] =>
            case _: Tuple3[_, _, _] =>
            case _: Tuple4[_, _, _, _] =>
            case _: Tuple5[_, _, _, _, _] =>
            case _: Tuple6[_, _, _, _, _, _] =>
            case _: Tuple7[_, _, _, _, _, _, _] =>
            case _: Tuple8[_, _, _, _, _, _, _, _] =>
            case _: Tuple9[_, _, _, _, _, _, _, _, _] =>
            case _: Tuple10[_, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple11[_, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple12[_, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple13[_, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple14[_, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _: Tuple22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] =>
            case _ => return false
          }
          if (o1.asInstanceOf[AnyRef] eq o2.asInstanceOf[AnyRef]) return true
          if (o1.productArity != o2.productArity) return false
          for (i <- 0 until o1.productArity if o1.productElement(i) =!= o2.productElement(i)) return false
          return true
        case (_, _) => _o == other
      }
    }
    def =!=(other: Any): B = !(this === other)

    def toZ: Z = _o match {
      case _o: ZLike[_] => _o.toZ
      case _ => halt(s"Cannot use toZ on ${_o}")
    }
  }

  final implicit class $Boolean2B(val _value: scala.Boolean) extends AnyVal {

    @inline def |^(other: B): B = _value ^ other.value

    @inline def ===(other: B): B = _value == other.value

    @inline def =!=(other: B): B = _value != other.value

    @inline def __>:(other: B): B = !other.value | _value

    @inline def ->:(other: B): B = !other.value | _value

    @inline def ___>:(other: => B): B = !other.value || _value

    @inline def -->:(other: => B): B = !other.value || _value

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

    @inline def apply(arg: AnyRef): Nothing = halt("")

    @inline def apply(arg: Boolean): Nothing = halt("")

    @inline def apply(arg: Byte): Nothing = halt("")

    @inline def apply(arg: Short): Nothing = halt("")

    @inline def apply(arg: Char): Nothing = halt("")

    @inline def apply(arg0: Any, arg1: Any, args: Any*): Nothing = halt("")

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

  def ?[S, T <: S](o: S)(f: T => B): B = $
  def ?[S1, S2, T1 <: S1, T2 <: S2](o1: S1, o2: S2)(f: (T1, T2) => B): B = $
  def ?[S1, S2, S3, T1 <: S1, T2 <: S2, T3 <: S3](o1: S1, o2: S2, o3: S3)(f: (T1, T2, T3) => B): B = $
  def ?[S1, S2, S3, S4, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4](o1: S1, o2: S2, o3: S3, o4: S4)(f: (T1, T2, T3, T4) => B): B = $
  def ?[S1, S2, S3, S4, S5, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5)(f: (T1, T2, T3, T4, T5) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6)(f: (T1, T2, T3, T4, T5, T6) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7)(f: (T1, T2, T3, T4, T5, T6, T7) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8)(f: (T1, T2, T3, T4, T5, T6, T7, T8) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13, T14 <: S14](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13, o14: S14)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13, T14 <: S14, T15 <: S15](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13, o14: S14, o15: S15)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, S16, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13, T14 <: S14, T15 <: S15, T16 <: S16](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13, o14: S14, o15: S15, o16: S16)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, S16, S17, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13, T14 <: S14, T15 <: S15, T16 <: S16, T17 <: S17](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13, o14: S14, o15: S15, o16: S16, o17: S17)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, S16, S17, S18, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13, T14 <: S14, T15 <: S15, T16 <: S16, T17 <: S17, T18 <: S18](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13, o14: S14, o15: S15, o16: S16, o17: S17, o18: S18)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, S16, S17, S18, S19, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13, T14 <: S14, T15 <: S15, T16 <: S16, T17 <: S17, T18 <: S18, T19 <: S19](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13, o14: S14, o15: S15, o16: S16, o17: S17, o18: S18, o19: S19)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, S16, S17, S18, S19, S20, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13, T14 <: S14, T15 <: S15, T16 <: S16, T17 <: S17, T18 <: S18, T19 <: S19, T20 <: S20](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13, o14: S14, o15: S15, o16: S16, o17: S17, o18: S18, o19: S19, o20: S20)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, S16, S17, S18, S19, S20, S21, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13, T14 <: S14, T15 <: S15, T16 <: S16, T17 <: S17, T18 <: S18, T19 <: S19, T20 <: S20, T21 <: S21](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13, o14: S14, o15: S15, o16: S16, o17: S17, o18: S18, o19: S19, o20: S20, o21: S21)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => B): B = $
  def ?[S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, S16, S17, S18, S19, S20, S21, S22, T1 <: S1, T2 <: S2, T3 <: S3, T4 <: S4, T5 <: S5, T6 <: S6, T7 <: S7, T8 <: S8, T9 <: S9, T10 <: S10, T11 <: S11, T12 <: S12, T13 <: S13, T14 <: S14, T15 <: S15, T16 <: S16, T17 <: S17, T18 <: S18, T19 <: S19, T20 <: S20, T21 <: S21, T22 <: S22](o1: S1, o2: S2, o3: S3, o4: S4, o5: S5, o6: S6, o7: S7, o8: S8, o9: S9, o10: S10, o11: S11, o12: S12, o13: S13, o14: S14, o15: S15, o16: S16, o17: S17, o18: S18, o19: S19, o20: S20, o21: S21, o22: S22)(f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => B): B = $

  def At[T](name: String, n: Z, linesFresh: Z*): T = $
  def At[T](o: T, n: Z, linesFresh: Z*): T = $
  def setOptions(tool: String, options: String): Unit = macro $internal.Macro.setOptions
}