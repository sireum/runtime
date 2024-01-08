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


private[sireum] sealed trait FloatingPoint extends Any with Number {

  def BitWidth: Z

  def SignificandBitWidth: Z

  def ExponentBitWidth: Z

}

object F32 {

  object Boxer extends $internal.Boxer {
    def box[T](o: scala.Any): T = o match {
      case o: scala.Float => F32(o).asInstanceOf[T]
    }

    def unbox(o: scala.Any): scala.Float = o match {
      case o: F32 => o.value
    }

    override def copyMut(src: AnyRef, srcPos: Z, dest: AnyRef, destPos: Z, length: Z): Unit =
      copy(src, srcPos, dest, destPos, length)

    override def create(length: Z): scala.AnyRef = new Array[scala.Float](length)

    override def lookup[T](a: scala.AnyRef, i: Z): T = a match {
      case a: Array[scala.Float] => box(a(i))
    }

    override def store(a: scala.AnyRef, i: Z, v: scala.Any): Unit = a match {
      case a: Array[scala.Float] => a(i) = unbox(v)
    }
  }

  val PInf: F32 = Float.PositiveInfinity

  val NInf: F32 = Float.NegativeInfinity

  val NaN: F32 = Float.NaN

  val MaxValue: F32 = Float.MaxValue

  val MinValue: F32 = Float.MinValue

  def apply(s: String): Option[F32] = try Some($String(s.value)) catch {
    case _: Throwable => None[F32]()
  }

  object $String {
    def apply(s: Predef.String): F32 = s.toFloat

    def unapply(n: F32): scala.Option[Predef.String] = scala.Some(n.toString)
  }

  def random: F32 = Random.Ext.instance.nextF32()

  def randomSeed(seed: Z): F32 = {
    Random.setSeed(seed)
    Random.Ext.instance.nextF32()
  }

  def randomBetween(min: F32, max: F32): F32 = {
    assert(min <= max)
    Random.Ext.instance.nextF32Between(min, max)
  }

  def randomSeedBetween(seed: Z, min: F32, max: F32): F32 = {
    assert(min <= max)
    Random.setSeed(seed)
    Random.Ext.instance.nextF32Between(min, max)
  }

  def unapply(f: F32): scala.Option[scala.Float] = scala.Some(f.value)

  import scala.language.implicitConversions

  implicit def apply(f: scala.Float): F32 = new F32(f)

}

final class F32(val value: scala.Float) extends FloatingPoint with $internal.HasBoxer {

  def BitWidth: Z = 32

  def SignificandBitWidth: Z = 24

  def ExponentBitWidth: Z = 7

  def unary_- : F32 = -value

  def native: scala.Float = value

  @inline def <(other: F32): B = value < other.value

  @inline def <=(other: F32): B = value <= other.value

  @inline def >(other: F32): B = value > other.value

  @inline def >=(other: F32): B = value >= other.value

  @inline def ===(other: F32): B = value == other.value

  @inline def =!=(other: F32): B = value != other.value

  @inline def +(other: F32): F32 = value + other.value

  @inline def -(other: F32): F32 = value - other.value

  @inline def *(other: F32): F32 = value * other.value

  @inline def /(other: F32): F32 = value / other.value

  @inline def %(other: F32): F32 = value % other.value

  @inline def isNaN: B = value.isNaN

  @inline def isInfinite: B = value.isInfinite

  @inline def ~~(other: F32): B = value == other.value

  @inline def !~(other: F32): B = value != other.value

  override def equals(other: Any): scala.Boolean = other match {
    case other: F32 => $internal.Macro.f32Bit(value) == $internal.Macro.f32Bit(other.value)
    case _ => return false
  }

  override def hashCode: scala.Int = $internal.Macro.f32Bit(value)

  def string: String = toString

  def boxer: $internal.Boxer = F32.Boxer

  override def toString: _root_.java.lang.String = _root_.java.lang.Float.toString(value)
}

object F64 {

  object Boxer extends $internal.Boxer {
    def box[T](o: scala.Any): T = o match {
      case o: scala.Double => F64(o).asInstanceOf[T]
    }

    def unbox(o: scala.Any): scala.Double = o match {
      case o: F64 => o.value
    }

    override def copyMut(src: AnyRef, srcPos: Z, dest: AnyRef, destPos: Z, length: Z): Unit =
      copy(src, srcPos, dest, destPos, length)

    override def create(length: Z): scala.AnyRef = new Array[scala.Double](length)

    override def lookup[T](a: scala.AnyRef, i: Z): T = a match {
      case a: Array[scala.Double] => box(a(i))
    }

    override def store(a: scala.AnyRef, i: Z, v: scala.Any): Unit = a match {
      case a: Array[scala.Double] => a(i) = unbox(v)
    }
  }

  val PInf: F64 = Double.PositiveInfinity

  val NInf: F64 = Double.NegativeInfinity

  val NaN: F64 = Double.NaN

  val MaxValue: F64 = Double.MaxValue

  val MinValue: F64 = Double.MinValue

  def apply(s: String): Option[F64] = try Some($String(s.value)) catch {
    case _: Throwable => None[F64]()
  }

  object $String {
    def apply(s: Predef.String): F64 = s.toDouble

    def unapply(n: F64): scala.Option[Predef.String] = scala.Some(n.toString)
  }

  def random: F64 = Random.Ext.instance.nextF64()

  def randomSeed(seed: Z): F64 = {
    Random.setSeed(seed)
    Random.Ext.instance.nextF64()
  }

  def randomBetween(min: F64, max: F64): F64 = {
    assert(min <= max)
    Random.Ext.instance.nextF64Between(min, max)
  }

  def randomSeedBetween(seed: Z, min: F64, max: F64): F64 = {
    assert(min <= max)
    Random.setSeed(seed)
    Random.Ext.instance.nextF64Between(min, max)
  }

  def unapply(d: F64): scala.Option[scala.Double] = scala.Some(d.value)

  import scala.language.implicitConversions

  implicit def apply(d: scala.Double): F64 = new F64(d)

}

final class F64(val value: scala.Double) extends FloatingPoint with $internal.HasBoxer {

  def BitWidth: Z = 64

  def SignificandBitWidth: Z = 53

  def ExponentBitWidth: Z = 10

  def unary_- : F64 = -value

  def native: scala.Double = value

  @inline def <(other: F64): B = value < other.value

  @inline def <=(other: F64): B = value <= other.value

  @inline def >(other: F64): B = value > other.value

  @inline def >=(other: F64): B = value >= other.value

  @inline def ===(other: F64): B = value == other.value

  @inline def =!=(other: F64): B = value != other.value

  @inline def +(other: F64): F64 = value + other.value

  @inline def -(other: F64): F64 = value - other.value

  @inline def *(other: F64): F64 = value * other.value

  @inline def /(other: F64): F64 = value / other.value

  @inline def %(other: F64): F64 = value % other.value

  @inline def isNaN: B = value.isNaN

  @inline def isInfinite: B = value.isInfinite

  @inline def ~~(other: F64): B = value == other.value

  @inline def !~(other: F64): B = value != other.value

  override def equals(other: Any): _root_.scala.Boolean = other match {
    case other: F64 => $internal.Macro.f64Bit(value) == $internal.Macro.f64Bit(other.value)
    case _ => return false
  }

  override def hashCode: scala.Int = $internal.Macro.f64Bit(value).hashCode

  def string: String = toString

  def boxer: $internal.Boxer = F64.Boxer

  override def toString: _root_.java.lang.String = _root_.java.lang.Double.toString(value)
}
