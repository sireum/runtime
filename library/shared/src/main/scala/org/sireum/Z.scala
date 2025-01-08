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

package org.sireum

object Z extends $ZCompanion[Z] {

  type Index = Z

  val longMin = scala.BigInt(scala.Long.MinValue)
  val longMax = scala.BigInt(scala.Long.MaxValue)

  object MP {

    val zero: Z = MP.Long(0)
    val one: Z = MP.Long(1)
    val mone: Z = MP.Long(-1)

    final case class Long(value: scala.Long) extends Z {

      override def toBigInt: scala.BigInt = scala.BigInt(value)

      override def toIntOpt: scala.Option[scala.Int] =
        if (scala.Int.MinValue <= value && value <= scala.Int.MaxValue) scala.Some(value.toInt)
        else scala.None

      override def toLongOpt: scala.Option[scala.Long] = scala.Some(value)

      override def toString: Predef.String = value.toString

      override def toInt: scala.Int = value.toInt

      override def toLong: scala.Long = value.toLong

      override def hashCode: scala.Int = value.toInt

    }

    final case class BigInt(value: scala.BigInt) extends Z {

      override def toBigInt: scala.BigInt = value

      override def toIntOpt: scala.Option[scala.Int] =
        if (scala.Int.MinValue <= value && value <= scala.Int.MaxValue) scala.Some(value.toInt)
        else scala.None

      override def toLongOpt: scala.Option[scala.Long] =
        if (scala.Long.MinValue <= value && value <= scala.Long.MaxValue) scala.Some(value.toLong)
        else scala.None

      override def toInt: scala.Int = value.toInt

      override def toLong: scala.Long = value.toLong

      override def toString: Predef.String = value.toString

      def pack: Z =
        if ((value.compareTo(Z.longMin) >= 0) &&
          (value.compareTo(Z.longMax) <= 0)) Long(value.longValue)
        else this

      override def hashCode: scala.Int = value.toInt
    }

    @inline def unsupported(op: Predef.String, other: Z): Nothing =
      halt(s"Unsupported Z operation '$op' with ${other.Name}")

    @inline def unary_-(n: Z): Z = {
      n match {
        case Long(m) =>
          if (m != scala.Long.MinValue)
            return Long(-m)
        case _: Z =>
      }
      BigInt(-n.toBigInt)
    }

    @inline def +(n: Z, other: Z): Z = {
      (n, other) match {
        case (Long(n1), Long(n2)) =>
          val r = n1 + n2
          if (((n1 ^ r) & (n2 ^ r)) >= 0L)
            return Long(r)
        case (_: Z, _: Z) =>
      }
      BigInt(n.toBigInt + other.toBigInt)
    }

    @inline def -(n: Z, other: Z): Z = this.+(n, -other)

    @inline def *(n: Z, other: Z): Z = {
      (n, other) match {
        case (Long(n1), Long(n2)) =>
          val r = n1 * n2
          if (r == 0) return zero
          var upgrade = false
          if (n2 > n1) {
            if (((n2 == -1) && (n1 == scala.Long.MinValue)) || (r / n2 != n1))
              upgrade = true
          } else {
            if (((n1 == -1) && (n2 == scala.Long.MinValue)) || (r / n1 != n2))
              upgrade = true
          }
          if (!upgrade) return Long(r)
        case (_: Z, _: Z) =>
      }
      BigInt(n.toBigInt * other.toBigInt)
    }

    @inline def /(n: Z, other: Z): Z = {
      (n, other) match {
        case (Long(n1), Long(n2)) =>
          val r = n1 / n2
          if (!((n1 == scala.Long.MinValue) && (n2 == -1)))
            return Long(r)
        case (_: Z, _: Z) =>
      }
      BigInt(n.toBigInt / other.toBigInt).pack
    }

    @inline def %(n: Z, other: Z): Z = {
      (n, other) match {
        case (Long(n1), Long(n2)) => return Long(n1 % n2)
        case (_: Z, _: Z) =>
      }
      BigInt(n.toBigInt % other.toBigInt).pack
    }

    @inline def >(n: Z, other: Z): B = (n, other) match {
      case (Long(n1), Long(n2)) => n1 > n2
      case (_: Z, _: Z) => n.toBigInt > other.toBigInt
    }

    @inline def >=(n: Z, other: Z): B = (n, other) match {
      case (Long(n1), Long(n2)) => n1 >= n2
      case (_: Z, _: Z) => n.toBigInt >= other.toBigInt
    }

    @inline def <(n: Z, other: Z): B = (n, other) match {
      case (Long(n1), Long(n2)) => n1 < n2
      case (_: Z, _: Z) => n.toBigInt < other.toBigInt
    }

    @inline def <=(n: Z, other: Z): B = (n, other) match {
      case (Long(n1), Long(n2)) => n1 <= n2
      case (_: Z, _: Z) => n.toBigInt <= other.toBigInt
    }

    @inline def isEqual(n: Z, other: Z): B = (n, other) match {
      case (n: Long, other: Long) => n.value == other.value
      case (_: Z, _: Z) => n.toBigInt == other.toBigInt
    }

    @inline def apply(n: scala.Int): Z = MP.Long(n)

    @inline def apply(n: scala.Long): Z = MP.Long(n)

    @inline def apply(n: scala.BigInt): Z = MP.BigInt(n).pack

    @inline def apply(n: _root_.java.math.BigInteger): Z = MP(scala.BigInt(n))

    @inline def apply(s: String): Z = {
      val ns = helper.normNum(s.value)
      if (ns.length > 2 && ns.head == '0' && ns.charAt(1).toLower == 'x') MP(scala.BigInt(ns.substring(2), 16))
      else MP(scala.BigInt(ns))
    }

  }

  object Boxer {

    trait Byte extends $internal.Boxer {

      def box[T](o: scala.Any): T = o match {
        case o: scala.Byte => make(o).asInstanceOf[T]
      }

      def unbox(o: scala.Any): scala.Byte = o match {
        case o: BV.Byte[_] => o.value
        case o: Range[_] =>
          val v: scala.Int = o.value
          v.toByte
      }

      override def copyMut(src: AnyRef, srcPos: Index, dest: AnyRef, destPos: Index, length: Index): Unit =
        copy(src, srcPos, dest, destPos, length)

      override def create(length: Z): scala.AnyRef = new Array[scala.Byte](length)

      override def lookup[T](a: scala.AnyRef, i: Z): T = a match {
        case a: Array[scala.Byte] => box(a(i))
      }

      override def store(a: scala.AnyRef, i: Z, v: scala.Any): Unit = a match {
        case a: Array[scala.Byte] => a(i) = unbox(v)
      }

      def make(o: scala.Byte): scala.Any
    }

    trait Short extends $internal.Boxer {

      def box[T](o: scala.Any): T = o match {
        case o: scala.Short => make(o).asInstanceOf[T]
      }

      def unbox(o: scala.Any): scala.Short = o match {
        case o: BV.Short[_] => o.value
        case o: Range[_] =>
          val v: scala.Int = o.value
          v.toShort
      }

      override def copyMut(src: AnyRef, srcPos: Index, dest: AnyRef, destPos: Index, length: Index): Unit =
        copy(src, srcPos, dest, destPos, length)

      override def create(length: Z): scala.AnyRef = new Array[scala.Short](length)

      override def lookup[T](a: scala.AnyRef, i: Z): T = a match {
        case a: Array[scala.Short] => box(a(i))
      }

      override def store(a: scala.AnyRef, i: Z, v: scala.Any): Unit = a match {
        case a: Array[scala.Short] => a(i) = unbox(v)
      }

      def make(o: scala.Short): scala.Any
    }

    trait Int extends $internal.Boxer {

      def box[T](o: scala.Any): T = o match {
        case o: scala.Int => make(o).asInstanceOf[T]
      }

      def unbox(o: scala.Any): scala.Int = o match {
        case o: BV.Int[_] => o.value
        case o: Range[_] => o.value
      }

      override def copyMut(src: AnyRef, srcPos: Index, dest: AnyRef, destPos: Index, length: Index): Unit =
        copy(src, srcPos, dest, destPos, length)

      override def create(length: Z): scala.AnyRef = new Array[scala.Int](length)

      override def lookup[T](a: scala.AnyRef, i: Z): T = a match {
        case a: Array[scala.Int] => box(a(i))
      }

      override def store(a: scala.AnyRef, i: Z, v: scala.Any): Unit = a match {
        case a: Array[scala.Int] => a(i) = unbox(v)
      }

      def make(o: scala.Int): scala.Any
    }

    trait Long extends $internal.Boxer {

      def box[T](o: scala.Any): T = o match {
        case o: scala.Long => make(o).asInstanceOf[T]
      }

      def unbox(o: scala.Any): scala.Long = o match {
        case o: BV.Long[_] => o.value
        case o: Range[_] => o.value
      }

      override def copyMut(src: AnyRef, srcPos: Index, dest: AnyRef, destPos: Index, length: Index): Unit =
        copy(src, srcPos, dest, destPos, length)

      override def create(length: Z): scala.AnyRef = new Array[scala.Long](length)

      override def lookup[T](a: scala.AnyRef, i: Z): T = a match {
        case a: Array[scala.Long] => box(a(i))
      }

      override def store(a: scala.AnyRef, i: Z, v: scala.Any): Unit = a match {
        case a: Array[scala.Long] => a(i) = unbox(v)
      }

      def make(o: scala.Long): scala.Any
    }

    object Z extends $internal.Boxer {

      def box[T](o: scala.Any): T = o match {
        case o: MP.Long => o.asInstanceOf[T]
        case o: scala.BigInt => MP.BigInt(o).asInstanceOf[T]
        case o: org.sireum.Z.Range[_] => o.asInstanceOf[T]
      }

      def unbox(o: scala.Any): scala.Any = o match {
        case o: MP.Long => o
        case o: MP.BigInt => o.value
        case o: org.sireum.Z.Range[_] => o
      }

      override def copyMut(src: AnyRef, srcPos: Index, dest: AnyRef, destPos: Index, length: Index): Unit =
        copy(src, srcPos, dest, destPos, length)
    }

  }

  object U {

    object _8 {

      def apply(value: scala.Byte): _8 = new _8(value)

      def apply(value: scala.Int): _8 = new _8(value.toByte)

    }

    class _8(val value: scala.Byte) extends AnyVal {
      def unary_- : _8 = _8(-value)
      def unary_~ : _8 = _8(~value)
      def +(other: _8): _8 = _8(value + other.value)
      def -(other: _8): _8 = _8(value - other.value)
      def *(other: _8): _8 = _8(value * other.value)
      def /(other: _8): _8 = _8(toInt / other.toInt)
      def %(other: _8): _8 = _8(toInt % other.toInt)
      def <<(n: scala.Int): _8 = _8((value & 0xff) << (n & 7))
      def >>(n: scala.Int): _8 = _8((value & 0xff) >>> (n & 7))
      def >>>(n: scala.Int): _8 = _8((value & 0xff) >>> (n & 7))
      def &(other: _8): _8 = _8((value & 0xff) & (other.value & 0xff))
      def |(other: _8): _8 = _8((value & 0xff) | (other.value & 0xff))
      def ^(other: _8): _8 = _8((value & 0xff) ^ (other.value & 0xff))
      def <(other: _8): scala.Boolean = toInt < other.toInt
      def <=(other: _8): scala.Boolean = toInt <= other.toInt
      def >(other: _8): scala.Boolean = toInt > other.toInt
      def >=(other: _8): scala.Boolean = toInt >= other.toInt
      def toInt: scala.Int = value & 0xff
      override def toString: Predef.String = f"$value%02X"
    }

    object _16 {

      def apply(value: scala.Short): _16 = new _16(value)

      def apply(value: scala.Int): _16 = new _16(value.toShort)

    }

    class _16(val value: scala.Short) extends AnyVal {
      def unary_- : _16 = _16(-value)
      def unary_~ : _16 = _16(~value)
      def +(other: _16): _16 = _16(value + other.value)
      def -(other: _16): _16 = _16(value - other.value)
      def *(other: _16): _16 = _16(value * other.value)
      def /(other: _16): _16 = _16(toInt / other.toInt)
      def %(other: _16): _16 = _16(toInt % other.toInt)
      def <<(n: scala.Int): _16 = _16((value & 0xFFFF) << (n & 15))
      def >>(n: scala.Int): _16 = _16((value & 0xFFFF) >>> (n & 15))
      def >>>(n: scala.Int): _16 = _16((value & 0xFFFF) >>> (n & 15))
      def &(other: _16): _16 = _16((value & 0xFFFF) & (other.value & 0xFFFF))
      def |(other: _16): _16 = _16((value & 0xFFFF) | (other.value & 0xFFFF))
      def ^(other: _16): _16 = _16((value & 0xFFFF) ^ (other.value & 0xFFFF))
      def <(other: _16): scala.Boolean = toInt < other.toInt
      def <=(other: _16): scala.Boolean = toInt <= other.toInt
      def >(other: _16): scala.Boolean = toInt > other.toInt
      def >=(other: _16): scala.Boolean = toInt >= other.toInt
      def toInt: scala.Int = value & 0xFFFF
      override def toString: Predef.String = f"$value%04X"
    }

    object _32 {

      def apply(value: scala.Int): _32 = new _32(value)

      def apply(value: scala.Long): _32 = new _32(value.toInt)

    }

    class _32(val value: scala.Int) extends AnyVal {
      def unary_- : _32 = _32(-value)
      def unary_~ : _32 = _32(~value)
      def +(other: _32): _32 = _32(value + other.value)
      def -(other: _32): _32 = _32(value - other.value)
      def *(other: _32): _32 = _32(value * other.value)
      def /(other: _32): _32 = _32(_root_.java.lang.Integer.divideUnsigned(value, other.value))
      def %(other: _32): _32 = _32(_root_.java.lang.Integer.remainderUnsigned(value, other.value))
      def <<(n: scala.Int): _32 = _32(value << n)
      def >>(n: scala.Int): _32 = _32(value >> n)
      def >>>(n: scala.Int): _32 = _32(value >>> n)
      def &(other: _32): _32 = _32(value & other.value)
      def |(other: _32): _32 = _32(value | other.value)
      def ^(other: _32): _32 = _32(value ^ other.value)
      def <(other: _32): scala.Boolean = _root_.java.lang.Integer.compareUnsigned(value, other.value) < 0
      def <=(other: _32): scala.Boolean = _root_.java.lang.Integer.compareUnsigned(value, other.value) <= 0
      def >(other: _32): scala.Boolean = _root_.java.lang.Integer.compareUnsigned(value, other.value) > 0
      def >=(other: _32): scala.Boolean = _root_.java.lang.Integer.compareUnsigned(value, other.value) >= 0
      def toLong: scala.Long = value & 0xFFFFFFFFL
      override def toString: Predef.String =  f"$value%08X"
    }

    object _64 {

      val NumValues: scala.BigInt = scala.BigInt(1) << 64

      def apply(value: scala.Long): _64 = new _64(value)

    }

    class _64(val value: scala.Long) extends AnyVal {
      def unary_- : _64 = _64(-value)
      def unary_~ : _64 = _64(~value)
      def +(other: _64): _64 = _64(value + other.value)
      def -(other: _64): _64 = _64(value - other.value)
      def *(other: _64): _64 = _64(value * other.value)
      def /(other: _64): _64 = _64(_root_.java.lang.Long.divideUnsigned(value, other.value))
      def %(other: _64): _64 = _64(_root_.java.lang.Long.remainderUnsigned(value, other.value))
      def <<(n: scala.Int): _64 = _64(value << n)
      def >>(n: scala.Int): _64 = _64(value >> n)
      def >>>(n: scala.Int): _64 = _64(value >>> n)
      def &(other: _64): _64 = _64(value & other.value)
      def |(other: _64): _64 = _64(value | other.value)
      def ^(other: _64): _64 = _64(value ^ other.value)
      def <(other: _64): scala.Boolean = _root_.java.lang.Long.compareUnsigned(value, other.value) < 0
      def <=(other: _64): scala.Boolean = _root_.java.lang.Long.compareUnsigned(value, other.value) <= 0
      def >(other: _64): scala.Boolean = _root_.java.lang.Long.compareUnsigned(value, other.value) > 0
      def >=(other: _64): scala.Boolean = _root_.java.lang.Long.compareUnsigned(value, other.value) >= 0

      def toBigInt: scala.BigInt =
        if (value < 0) _64.NumValues + value else scala.BigInt(value)
      override def toString: Predef.String =  f"$value%016X"
    }

  }

  trait BV[T <: BV[T]] extends Any with ZLike[T] {
    this: T =>

    def >>(other: T): T

    def >>>(other: T): T

    def <<(other: T): T

    def &(other: T): T

    def |(other: T): T

    def |^(other: T): T

    def unary_~ : T
  }

  object BV {

    trait Byte[T <: Byte[T]] extends Any with BV[T] with $internal.HasBoxer {
      this: T =>

      final def isBitVector: scala.Boolean = true

      final def hasMin: scala.Boolean = true

      final def hasMax: scala.Boolean = true

      def value: scala.Byte

      def make(value: scala.Byte): T

      def Min: T

      def Max: T

      def Index: T

      def isZeroIndex: scala.Boolean

      def isWrapped: scala.Boolean

      def BitWidth: scala.Int

      @inline private final def toByte: scala.Byte = value

      @inline private final def toU8: U._8 = U._8(toByte)

      @inline private final def make(value: Z): T = {
        assert(Min.toMP <= value, s"$value should not be less than $Name.Min ($Min)")
        assert(value <= Max.toMP, s"$value should not be greater than $Name.Max ($Max)")
        make(value match {
          case MP.Long(n) => n.toByte
          case MP.BigInt(n) => n.toByte
        })
      }

      @inline private final def umake(value: U._8): T = make(value.value)

      @inline private final def makeByte(value: scala.Int): T =
        if (isSigned) make(value.toByte) else make(U._8(value).value)

      final def unary_- : T =
        if (!isWrapped) make(-toMP)
        else if (isSigned) makeByte(-toByte)
        else umake(-toU8)

      final def +(other: T): T = {
        if (!isWrapped) make(toMP + other.toMP)
        else if (isSigned) makeByte(toByte + other.toByte)
        else umake(toU8 + other.toU8)
      }

      final def -(other: T): T = {
        if (!isWrapped) make(toMP - other.toMP)
        else if (isSigned) makeByte(toByte - other.toByte)
        else umake(toU8 - other.toU8)
      }

      final def *(other: T): T = {
        if (!isWrapped) make(toMP * other.toMP)
        else if (isSigned) makeByte(toByte * other.toByte)
        else umake(toU8 * other.toU8)
      }

      final def /(other: T): T = {
        if (!isWrapped) make(toMP / other.toMP)
        else if (isSigned) makeByte(toByte / other.toByte)
        else umake(toU8 / other.toU8)
      }

      final def %(other: T): T = {
        if (!isWrapped) make(toMP % other.toMP)
        else if (isSigned) makeByte(toByte % other.toByte)
        else umake(toU8 % other.toU8)
      }

      final def >(other: T): B = {
        if (isSigned) toByte > other.toByte
        else toU8 > other.toU8
      }

      final def >=(other: T): B = {
        if (isSigned) toByte >= other.toByte
        else toU8 >= other.toU8
      }

      final def <(other: T): B = {
        if (isSigned) toByte < other.toByte
        else toU8 < other.toU8
      }

      final def <=(other: T): B = {
        if (isSigned) toByte <= other.toByte
        else toU8 <= other.toU8
      }

      final def >>(other: T): T = {
        if (isSigned) makeByte(toByte >> other.toByte)
        else this >>> other
      }

      final def >>>(other: T): T = {
        if (isSigned) makeByte(toByte >>> other.toByte)
        else umake(toU8 >>> other.toU8.toInt)
      }

      final def <<(other: T): T = {
        if (isSigned) makeByte(toByte << other.toByte)
        else umake(toU8 << other.toU8.toInt)
      }

      final def &(other: T): T = {
        if (isSigned) makeByte(toByte & other.toByte)
        else umake(toU8 & other.toU8)
      }

      final def |(other: T): T = {
        if (isSigned) makeByte(toByte | other.toByte)
        else umake(toU8 | other.toU8)
      }

      final def |^(other: T): T = {
        if (isSigned) makeByte(toByte ^ other.toByte)
        else umake(toU8 ^ other.toU8)
      }

      final def unary_~ : T =
        if (isSigned) makeByte(~toByte)
        else umake(~toU8)

      final def increase: T =
        if (isSigned) makeByte(toByte + 1)
        else umake(toU8 + U._8(1))

      final def decrease: T =
        if (isSigned) makeByte(toByte - 1)
        else umake(toU8 - U._8(1))

      final override def toString: Predef.String =
        if (isSigned) toByte.toString
        else toU8.toString

      final override def toBigInt: scala.BigInt =
        if (isSigned) scala.BigInt(toByte)
        else toU8.toInt.toBigInt

      final override def toMP: Z =
        if (isSigned) MP(toByte)
        else MP(toU8.toInt)

      final override def toIndex: Z.Index =
        if (isZeroIndex) toMP else toMP - Index.toMP
    }

    trait Short[T <: Short[T]] extends Any with BV[T] with $internal.HasBoxer {
      this: T =>

      final def isBitVector: scala.Boolean = true

      final def hasMin: scala.Boolean = true

      final def hasMax: scala.Boolean = true

      def value: scala.Short

      def make(value: scala.Short): T

      def Min: T

      def Max: T

      def Index: T

      def isZeroIndex: scala.Boolean

      def isWrapped: scala.Boolean

      @inline private final def toShort: scala.Short = value

      @inline private final def toU16: U._16 = U._16(toShort)

      @inline private final def make(value: Z): T = {
        assert(Min.toMP <= value, s"$value should not be less than $Name.Min ($Min)")
        assert(value <= Max.toMP, s"$value should not be greater than $Name.Max ($Max)")
        make(value match {
          case MP.Long(n) => n.toShort
          case MP.BigInt(n) => n.toShort
        })
      }

      @inline private final def umake(value: U._16): T = make(value.value)

      @inline private final def makeShort(value: scala.Int): T =
        if (isSigned) make(value.toShort) else make(U._16(value).value)

      final def unary_- : T =
        if (!isWrapped) make(-toMP)
        else if (isSigned) makeShort(-toShort)
        else umake(-toU16)

      final def +(other: T): T = {
        if (!isWrapped) make(toMP + other.toMP)
        else if (isSigned) makeShort(toShort + other.toShort)
        else umake(toU16 + other.toU16)
      }

      final def -(other: T): T = {
        if (!isWrapped) make(toMP - other.toMP)
        else if (isSigned) makeShort(toShort - other.toShort)
        else umake(toU16 - other.toU16)
      }

      final def *(other: T): T = {
        if (!isWrapped) make(toMP * other.toMP)
        else if (isSigned) makeShort(toShort * other.toShort)
        else umake(toU16 * other.toU16)
      }

      final def /(other: T): T = {
        if (!isWrapped) make(toMP / other.toMP)
        else if (isSigned) makeShort(toShort / other.toShort)
        else umake(toU16 / other.toU16)
      }

      final def %(other: T): T = {
        if (!isWrapped) make(toMP % other.toMP)
        else if (isSigned) makeShort(toShort % other.toShort)
        else umake(toU16 % other.toU16)
      }

      final def >(other: T): B = {
        if (isSigned) toShort > other.toShort
        else toU16 > other.toU16
      }

      final def >=(other: T): B = {
        if (isSigned) toShort >= other.toShort
        else toU16 >= other.toU16
      }

      final def <(other: T): B = {
        if (isSigned) toShort < other.toShort
        else toU16 < other.toU16
      }

      final def <=(other: T): B = {
        if (isSigned) toShort <= other.toShort
        else toU16 <= other.toU16
      }

      final def >>(other: T): T = {
        if (isSigned) makeShort(toShort >> other.toShort)
        else this >>> other
      }

      final def >>>(other: T): T = {
        if (isSigned) makeShort(toShort >>> other.toShort)
        else umake(toU16 >>> other.toU16.toInt)
      }

      final def <<(other: T): T = {
        if (isSigned) makeShort(toShort << other.toShort)
        else umake(toU16 << other.toU16.toInt)
      }

      final def &(other: T): T = {
        if (isSigned) makeShort(toShort & other.toShort)
        else umake(toU16 & other.toU16)
      }

      final def |(other: T): T = {
        if (isSigned) makeShort(toShort | other.toShort)
        else umake(toU16 | other.toU16)
      }

      final def |^(other: T): T = {
        if (isSigned) makeShort(toShort ^ other.toShort)
        else umake(toU16 ^ other.toU16)
      }

      final def unary_~ : T =
        if (isSigned) makeShort(~toShort)
        else umake(~toU16)

      final def increase: T =
        if (isSigned) makeShort(toShort + 1)
        else umake(toU16 + U._16(1))

      final def decrease: T =
        if (isSigned) makeShort(toShort - 1)
        else umake(toU16 - U._16(1))

      final override def toString: Predef.String =
        if (isSigned) toShort.toString
        else toU16.toString

      final override def toBigInt: scala.BigInt =
        if (isSigned) scala.BigInt(toShort)
        else toU16.toInt.toBigInt

      final override def toMP: Z =
        if (isSigned) MP(toShort)
        else MP(toU16.toInt)

      final override def toIndex: Z.Index =
        if (isZeroIndex) toMP else toMP - Index.toMP
    }

    trait Int[T <: Int[T]] extends Any with BV[T] with $internal.HasBoxer {
      this: T =>

      final def isBitVector: scala.Boolean = true

      final def hasMin: scala.Boolean = true

      final def hasMax: scala.Boolean = true

      def value: scala.Int

      def make(value: scala.Int): T

      def Min: T

      def Max: T

      def Index: T

      def isZeroIndex: scala.Boolean

      def isWrapped: scala.Boolean

      @inline private final def toU32: U._32 = U._32(value)

      @inline private final def make(value: Z): T = {
        assert(Min.toMP <= value, s"$value should not be less than $Name.Min ($Min)")
        assert(value <= Max.toMP, s"$value should not be greater than $Name.Max ($Max)")
        make(value match {
          case MP.Long(n) => n.toInt
          case MP.BigInt(n) => n.toInt
        })
      }

      @inline private final def umake(value: U._32): T = make(value.value)

      final def unary_- : T =
        if (!isWrapped) make(-toMP)
        else if (isSigned) make(-value)
        else umake(-toU32)

      final def +(other: T): T = {
        if (!isWrapped) make(toMP + other.toMP)
        else if (isSigned) make(value + other.value)
        else umake(toU32 + other.toU32)
      }

      final def -(other: T): T = {
        if (!isWrapped) make(toMP - other.toMP)
        else if (isSigned) make(value - other.value)
        else umake(toU32 - other.toU32)
      }

      final def *(other: T): T = {
        if (!isWrapped) make(toMP * other.toMP)
        else if (isSigned) make(value * other.value)
        else umake(toU32 * other.toU32)
      }

      final def /(other: T): T = {
        if (!isWrapped) make(toMP / other.toMP)
        else if (isSigned) make(value / other.value)
        else umake(toU32 / other.toU32)
      }

      final def %(other: T): T = {
        if (!isWrapped) make(toMP % other.toMP)
        else if (isSigned) make(value % other.value)
        else umake(toU32 % other.toU32)
      }

      final def >(other: T): B = {
        if (isSigned) value > other.value
        else toU32 > other.toU32
      }

      final def >=(other: T): B = {
        if (isSigned) value >= other.value
        else toU32 >= other.toU32
      }

      final def <(other: T): B = {
        if (isSigned) value < other.value
        else toU32 < other.toU32
      }

      final def <=(other: T): B = {
        if (isSigned) value <= other.value
        else toU32 <= other.toU32
      }

      final def >>(other: T): T = {
        if (isSigned) make(value >> other.value)
        else this >>> other
      }

      final def >>>(other: T): T = {
        if (isSigned) make(value >>> other.value)
        else umake(toU32 >>> other.value)
      }

      final def <<(other: T): T = {
        if (isSigned) make(value << other.value)
        else umake(toU32 << other.value)
      }

      final def &(other: T): T = {
        if (isSigned) make(value & other.value)
        else umake(toU32 & other.toU32)
      }

      final def |(other: T): T = {
        if (isSigned) make(value | other.value)
        else umake(toU32 | other.toU32)
      }

      final def |^(other: T): T = {
        if (isSigned) make(value ^ other.value)
        else umake(toU32 ^ other.toU32)
      }

      final def unary_~ : T =
        if (isSigned) make(~value)
        else umake(~toU32)

      final def increase: T =
        if (isSigned) make(value + 1)
        else umake(toU32 + U._32(1))

      final def decrease: T =
        if (isSigned) make(value - 1)
        else umake(toU32 - U._32(1))

      final override def toString: Predef.String =
        if (isSigned) value.toString
        else toU32.toString

      final override def toBigInt: scala.BigInt =
        if (isSigned) scala.BigInt(value)
        else toU32.toLong.toBigInt

      final override def toMP: Z =
        if (isSigned) MP(value)
        else MP(toU32.toLong)

      final override def toIndex: Z.Index =
        if (isZeroIndex) toMP else toMP - Index.toMP
    }

    trait Long[T <: Long[T]] extends Any with BV[T] with $internal.HasBoxer {
      this: T =>

      final def isBitVector: scala.Boolean = true

      final def hasMin: scala.Boolean = true

      final def hasMax: scala.Boolean = true

      def value: scala.Long

      def make(value: scala.Long): T

      def Min: T

      def Max: T

      def Index: T

      def isZeroIndex: scala.Boolean

      def isWrapped: scala.Boolean

      @inline private final def toU64: U._64 = U._64(value)

      @inline private final def make(value: Z): T = {
        assert(Min.toMP <= value, s"$value should not be less than $Name.Min ($Min)")
        assert(value <= Max.toMP, s"$value should not be greater than $Name.Max ($Max)")
        make(value match {
          case MP.Long(n) => n
          case MP.BigInt(n) => n.toLong
        })
      }

      @inline private final def umake(value: U._64): T = make(value.value)

      final def unary_- : T =
        if (!isWrapped) make(-toMP)
        else if (isSigned) make(-value)
        else umake(-toU64)

      final def +(other: T): T = {
        if (!isWrapped) make(toMP + other.toMP)
        else if (isSigned) make(value + other.value)
        else umake(toU64 + other.toU64)
      }

      final def -(other: T): T = {
        if (!isWrapped) make(toMP - other.toMP)
        else if (isSigned) make(value - other.value)
        else umake(toU64 - other.toU64)
      }

      final def *(other: T): T = {
        if (!isWrapped) make(toMP * other.toMP)
        else if (isSigned) make(value * other.value)
        else umake(toU64 * other.toU64)
      }

      final def /(other: T): T = {
        if (!isWrapped) make(toMP / other.toMP)
        else if (isSigned) make(value / other.value)
        else umake(toU64 / other.toU64)
      }

      final def %(other: T): T = {
        if (!isWrapped) make(toMP % other.toMP)
        else if (isSigned) make(value % other.value)
        else umake(toU64 % other.toU64)
      }

      final def >(other: T): B = {
        if (isSigned) value > other.value
        else toU64 > other.toU64
      }

      final def >=(other: T): B = {
        if (isSigned) value >= other.value
        else toU64 >= other.toU64
      }

      final def <(other: T): B = {
        if (isSigned) value < other.value
        else toU64 < other.toU64
      }

      final def <=(other: T): B = {
        if (isSigned) value <= other.value
        else toU64 <= other.toU64
      }

      final def >>(other: T): T = {
        if (isSigned) make(value >> other.value)
        else this >>> other
      }

      final def >>>(other: T): T = {
        if (isSigned) make(value >>> other.value)
        else umake(toU64 >>> (other.value & 0x3F).toInt)
      }

      final def <<(other: T): T = {
        if (isSigned) make(value << other.value)
        else umake(toU64 << (other.value & 0x3F).toInt)
      }

      final def &(other: T): T = {
        if (isSigned) make(value & other.value)
        else umake(toU64 & other.toU64)
      }

      final def |(other: T): T = {
        if (isSigned) make(value | other.value)
        else umake(toU64 | other.toU64)
      }

      final def |^(other: T): T = {
        if (isSigned) make(value ^ other.value)
        else umake(toU64 ^ other.toU64)
      }

      final def unary_~ : T =
        if (isSigned) make(~value)
        else umake(~toU64)

      final def increase: T =
        if (isSigned) make(value + 1)
        else umake(toU64 + U._64(1))

      final def decrease: T =
        if (isSigned) make(value - 1)
        else umake(toU64 - U._64(1))

      final override def toString: Predef.String =
        if (isSigned) value.toString
        else toU64.toString

      final override def toBigInt: scala.BigInt =
        if (isSigned) scala.BigInt(value)
        else toU64.toBigInt

      final override def toMP: Z =
        if (isSigned) MP(value)
        else MP(toU64.toBigInt)

      final override def toIndex: Z.Index =
        if (isZeroIndex) toMP else toMP - Index.toMP
    }

  }

  trait Range[T <: Range[T]] extends Any with ZLike[T] with $internal.HasBoxer {
    this: T =>

    def value: Z

    def make(n: Z): T

    def Min: T

    def Max: T

    def Index: T

    def isZeroIndex: scala.Boolean

    @inline final def isBitVector: scala.Boolean = false

    @inline final def BitWidth: Int = unsupported("BitWidth")

    @inline final def toMP: Z = value

    @inline final def unary_- : T = make(-value)

    @inline final def +(other: T): T = {
      make(value + other.value)
    }

    @inline final def -(other: T): T = {
      make(value - other.value)
    }

    @inline final def *(other: T): T = {
      make(value * other.value)
    }

    @inline final def /(other: T): T = {
      make(value / other.value)
    }

    @inline final def %(other: T): T = {
      make(value % other.value)
    }

    @inline final def <(other: T): B = {
      value < other.value
    }

    @inline final def <=(other: T): B = {
      value <= other.value
    }

    @inline final def >(other: T): B = {
      value > other.value
    }

    @inline final def >=(other: T): B = {
      value >= other.value
    }

    @inline final def decrease: T = make(value - MP.one)

    @inline final def increase: T = make(value + MP.one)

    @inline final override def toBigInt: BigInt = value.toBigInt

    @inline final def toIndex: Z.Index = if (isZeroIndex) value else value - Index.value

    @inline final override def toString: Predef.String = value.toString

    @inline private final def unsupported(op: Predef.String): Nothing =
      halt(s"Unsupported $Name operation '$op'.")

  }

  def apply(n: Z): Z = n match {
    case n: Z => n
    case _ => halt(s"Unsupported Z creation from ${n.Name}.")
  }

  def apply(s: String): Option[Z] =
    try Some(Z.$String(s.value))
    catch {
      case _: Throwable => None[Z]()
    }

  object Int extends $ZCompanionInt[Z] {
    @inline def apply(n: scala.Int): Z = MP(n)

    def unapply(n: Z): scala.Option[scala.Int] = n match {
      case n: Z => n.toIntOpt
      case _ => scala.None
    }
  }

  object Long extends $ZCompanionLong[Z] {
    @inline def apply(n: scala.Long): Z = MP(n)

    def unapply(n: Z): scala.Option[scala.Long] = n match {
      case n: Z => n.toLongOpt
      case _ => scala.None
    }
  }

  object $String extends $ZCompanionString[Z] {
    @inline def apply(s: Predef.String): Z = MP(s)

    def unapply(n: Z): scala.Option[Predef.String] = n match {
      case n: Z => scala.Some(n.toString)
      case _ => scala.None
    }
  }

  object BigInt extends $ZCompanionBigInt[Z] {
    @inline def apply(n: scala.BigInt): Z = MP(n)

    def unapply(n: Z): scala.Option[scala.BigInt] = n match {
      case n: Z => scala.Some(n.toBigInt)
      case _ => scala.None
    }
  }

  val Name: Predef.String = "Z"

  val isBitVector: scala.Boolean = false

  val isSigned: scala.Boolean = true

  val isZeroIndex: scala.Boolean = true

  val Index: Z = MP.zero

  val hasMin: scala.Boolean = false

  val hasMax: scala.Boolean = false

  def Min: Z = halt(s"Unsupported $Name operation 'Min'")

  def Max: Z = halt(s"Unsupported $Name operation 'Max'")

  def BitWidth: scala.Int = halt(s"Unsupported $Name operation 'BitWidth'")

  def random: Z = {
    return Random.Ext.instance.nextZ()
  }

  def randomBetween(min: Z, max: Z): Z = {
    assert(min <= max)
    return Random.Ext.instance.nextZBetween(min, max)
  }

  def randomSeed(seed: Z): Z = {
    Random.setSeed(seed)
    return Random.Ext.instance.nextZ()
  }

  def randomSeedBetween(seed: Z, min: Z, max: Z): Z = {
    assert(min <= max)
    Random.setSeed(seed)
    return Random.Ext.instance.nextZBetween(min, max)
  }

  def read(): Z = {
    prompt("Enter an integer: ")
  }

  def prompt(msg: String): Z = {
    while (true) {
      System.out.print(msg.value)
      System.out.flush()
      val bs = new java.io.ByteArrayOutputStream
      var n = System.in.read().toByte
      while (n != -1 && n != '\n') {
        bs.write(n)
        n = System.in.read().toByte
      }
      val ba = bs.toByteArray
      val s = new java.lang.String(ba, 0,
        if (scala.util.Properties.isWin && ba.nonEmpty && ba.last == '\r') ba.length - 1 else ba.length, "UTF-8")
      try {
        return Z.$String(s)
      } catch {
        case _: Throwable =>
          System.err.println(s"Invalid integer format: $s.")
          System.err.flush()
      }
    }
    Z.MP.zero
  }

  import scala.language.implicitConversions

  @inline implicit def apply(n: scala.Int): Z = Int(n)

  @inline implicit def apply(n: scala.Long): Z = Long(n)

  @inline implicit def apply(n: scala.BigInt): Z = BigInt(n)

}

trait $ZCompanion[T] {

  def Name: Predef.String

  def isBitVector: scala.Boolean

  def isSigned: scala.Boolean

  def isZeroIndex: scala.Boolean

  def Index: T

  def hasMin: scala.Boolean

  def hasMax: scala.Boolean

  def Min: T

  def Max: T

  def BitWidth: scala.Int

  def random: T

  def randomSeed(seed: Z): T

  def Int: $ZCompanionInt[T]

  def Long: $ZCompanionLong[T]

  def $String: $ZCompanionString[T]

  def BigInt: $ZCompanionBigInt[T]

  def apply(n: Z): T

}

trait $ZCompanionInt[T] {
  def apply(n: scala.Int): T

  def unapply(n: T): scala.Option[scala.Int]
}

trait $ZCompanionLong[T] {
  def apply(n: scala.Long): T

  def unapply(n: T): scala.Option[scala.Long]
}

trait $ZCompanionString[T] {
  def apply(s: Predef.String): T

  def unapply(n: T): scala.Option[Predef.String]
}

trait $ZCompanionBigInt[T] {
  def apply(s: scala.BigInt): T

  def unapply(n: T): scala.Option[scala.BigInt]
}

trait ZLike[T <: ZLike[T]] extends Any with Number with Comparable[T] {
  this: T =>

  def Name: Predef.String

  def isBitVector: scala.Boolean

  def isSigned: scala.Boolean

  def isZeroIndex: scala.Boolean

  def Index: T

  def hasMin: scala.Boolean

  def hasMax: scala.Boolean

  def Min: T

  def Max: T

  def BitWidth: scala.Int

  def <(other: T): B

  def <=(other: T): B

  def >(other: T): B

  def >=(other: T): B

  def +(other: T): T

  def -(other: T): T

  def *(other: T): T

  def /(other: T): T

  def %(other: T): T

  def increase: T

  def decrease: T

  def unary_- : T

  def toIndex: Z.Index

  def add(n: Z, opName: String, companion: $ZCompanion[_]): T = {
    val r = toMP + n
    if (hasMax) {
      assert(r <= Max.toMP, s"Insufficient Max for $opName")
    }
    companion(r).asInstanceOf[T]
  }

  final override def string: String = toString

  def toBigInt: scala.BigInt

  def toMP: Z

  def toZ: Z

  def to(n: T): ZRange[T] = ZRange[T](T, this, n, 1, new ZRange.CondIncDec[T] {
    @pure def cond(i: T): B = T
    @pure def increase(i: T): T = i.increase
    @pure def decrease(i: T): T = i.decrease
  })

  def until(n: T): ZRange[T] = ZRange[T](F, this, n, 1, new ZRange.CondIncDec[T] {
    @pure def cond(i: T): B = T
    @pure def increase(i: T): T = i.increase
    @pure def decrease(i: T): T = i.decrease
  })

  def compareTo(other: T): scala.Int =
    if (this < other) -1 else if (this > other) 1 else 0
}

sealed trait Z extends ZLike[Z] with $internal.HasBoxer {

  final def Name: Predef.String = Z.Name

  final def isBitVector: scala.Boolean = Z.isBitVector

  final def isSigned: scala.Boolean = Z.isSigned

  final def Index: Z = Z.Index

  final def isZeroIndex: scala.Boolean = Z.isZeroIndex

  final def hasMin: scala.Boolean = Z.hasMin

  final def hasMax: scala.Boolean = Z.hasMax

  final def Min: Z = Z.Min

  final def Max: Z = Z.Max

  final def BitWidth: Int = Z.BitWidth

  final def toIndex: Z.Index = this

  final def unary_- : Z = Z.MP.unary_-(this)

  final def +(other: Z): Z = Z.MP.+(this, other)

  final def -(other: Z): Z = Z.MP.-(this, other)

  final def *(other: Z): Z = Z.MP.*(this, other)

  final def /(other: Z): Z = Z.MP./(this, other)

  final def %(other: Z): Z = Z.MP.%(this, other)

  final def >(other: Z): B = Z.MP.>(this, other)

  final def >=(other: Z): B = Z.MP.>=(this, other)

  final def <(other: Z): B = Z.MP.<(this, other)

  final def <=(other: Z): B = Z.MP.<=(this, other)

  @inline def ===(other: Z): B = this == other

  @inline def =!=(other: Z): B = this != other

  final def increase: Z = this + Z.MP.one

  final def decrease: Z = this - Z.MP.one

  final override def equals(other: scala.Any): scala.Boolean = other match {
    case other: Z => if (this eq other) true else Z.MP.isEqual(this, other).value
    case other: scala.Int => Z.MP.isEqual(this, other)
    case other: scala.Long => Z.MP.isEqual(this, other)
    case other: scala.BigInt => Z.MP.isEqual(this, other)
    case other: _root_.java.lang.Integer => Z.MP.isEqual(this, other.intValue)
    case other: _root_.java.lang.Long => Z.MP.isEqual(this, other.longValue)
    case other: _root_.java.math.BigInteger => Z.MP.isEqual(this, scala.BigInt(other))
    case _ => false
  }

  final def toMP: Z = this

  final def toZ: Z = this

  def toIntOpt: scala.Option[scala.Int]

  def toLongOpt: scala.Option[scala.Long]

  def toInt: scala.Int

  def toLong: scala.Long

  def boxer: $internal.Boxer = Z.Boxer.Z

}

object ZRange {
  trait CondIncDec[I] {
    @pure def cond(i: I): B
    @pure def increase(i: I): I
    @pure def decrease(i: I): I
  }
}

final case class ZRange[I](
  isInclusive: B,
  init: I,
  to: I,
  by: Z,
  cid: ZRange.CondIncDec[I]
) {

  def foreach[V](f: I => V): Unit = {
    var i = init
    def updateI(): Unit = {
      var j = Z.MP.zero
      if (by < 0) {
        val n = -by
        while (j < n) {
          i = cid.decrease(i)
          j = j + 1
        }
      } else {
        val n = by
        while (j < n) {
          i = cid.increase(i)
          j = j + 1
        }
      }
    }
    val toZ = to.asInstanceOf[ZLike[_]].toMP
    var iZ = i.asInstanceOf[ZLike[_]].toMP
    def loopCond: B = {
      if (isInclusive) (iZ <= toZ && by > 0) || (iZ >= toZ && by < 0)
      else (iZ < toZ && by > 0) || (iZ > toZ && by < 0)
    }
    if (loopCond) {
      if (cid.cond(i)) {
        f(i)
      }
    } else {
      return
    }
    iZ = iZ + by
    while (loopCond) {
      updateI()
      if (cid.cond(i)) {
        f(i)
      }
      iZ = iZ + by
    }
  }

  @pure def map[V](f: I => V): ISZ[V] = {
    var i = init
    var r = ISZ[V]()
    def updateI(): Unit = {
      var j = Z.MP.zero
      if (by < 0) {
        val n = -by
        while (j < n) {
          i = cid.decrease(i)
          j = j + 1
        }
      } else {
        val n = by
        while (j < n) {
          i = cid.increase(i)
          j = j + 1
        }
      }
    }
    val toZ = to.asInstanceOf[ZLike[_]].toMP
    var iZ = i.asInstanceOf[ZLike[_]].toMP
    def loopCond: B = {
      if (isInclusive) (iZ <= toZ && by > 0) || (iZ >= toZ && by < 0)
      else (iZ < toZ && by > 0) || (iZ > toZ && by < 0)
    }
    if (loopCond) {
      if (cid.cond(i)) {
        r = r :+ f(i)
      }
    } else {
      return r
    }
    iZ = iZ + by
    while (loopCond) {
      updateI()
      if (cid.cond(i)) {
        r = r :+ f(i)
      }
      iZ = iZ + by
    }
    r
  }

  @pure def flatMap[V](f: I => ISZ[V]): ISZ[V] = {
    var i = init
    var r = ISZ[V]()
    def updateI(): Unit = {
      var j = Z.MP.zero
      if (by < 0) {
        val n = -by
        while (j < n) {
          i = cid.decrease(i)
          j = j + 1
        }
      } else {
        val n = by
        while (j < n) {
          i = cid.increase(i)
          j = j + 1
        }
      }
    }
    val toZ = to.asInstanceOf[ZLike[_]].toMP
    var iZ = i.asInstanceOf[ZLike[_]].toMP
    def loopCond: B = {
      if (isInclusive) (iZ <= toZ && by > 0) || (iZ >= toZ && by < 0)
      else (iZ < toZ && by > 0) || (iZ > toZ && by < 0)
    }
    if (loopCond) {
      if (cid.cond(i)) {
        r = r ++ f(i)
      }
    } else {
      return r
    }
    iZ = iZ + by
    while (loopCond) {
      updateI()
      if (cid.cond(i)) {
        r = r ++ f(i)
      }
      iZ = iZ + by
    }
    r
  }

  @pure def by(n: Z): ZRange[I] = ZRange(isInclusive, init, to, n, cid)

  def withFilter(filter: I => B): ZRange[I] =
    ZRange(isInclusive, init, to, by, new ZRange.CondIncDec[I] {
      @pure def cond(i: I): B = cid.cond(i) && filter(i)
      @pure def increase(i: I): I = cid.increase(i)
      @pure def decrease(i: I): I = cid.decrease(i)
    })

  @pure def reverse: ZRange[I] =
    ZRange(T, if (isInclusive) to else if (by > 0) cid.decrease(to) else cid.increase(to), init, -by, cid)

}
