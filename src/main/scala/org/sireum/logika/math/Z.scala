/*
 * Copyright (c) 2016, Robby, Kansas State University
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

package org.sireum.logika.math

import org.apfloat._
import org.sireum.logika._

import scala.math.ScalaNumericConversions

object Z {
  final private[logika] val intMin = new Apint(Int.MinValue)
  final private[logika] val intMax = new Apint(Int.MaxValue)
  final private[logika] val longMin = new Apint(Long.MinValue)
  final private[logika] val longMax = new Apint(Long.MaxValue)

  final val zero = Z(0)
  final val one = Z(1)

  @inline
  final def apply(z: Int): Z = apply(new Apint(z))

  @inline
  final def apply(z: Long): Z = apply(new Apint(z))

  @inline
  final def apply(z: String): Z = apply(new java.math.BigInteger(z))

  @inline
  final def apply(z: BigInt): Z = apply(z.bigInteger)

  @inline
  final def apply(z: java.math.BigInteger): Z = apply(new Apint(z))

  @inline
  final def apply(z: Apint): Z = ZApint(z).pack
}

trait Z extends ScalaNumericConversions {
  def unary_- : Z

  def +(other: Z): Z

  def -(other: Z): Z

  def *(other: Z): Z

  def /(other: Z): Z

  def %(other: Z): Z

  def >(other: Z): B

  def >=(other: Z): B

  def <(other: Z): B

  def <=(other: Z): B

  def toBigInteger: java.math.BigInteger

  def toBigInt: BigInt

  def toZApint: Apint

  final override def doubleValue = toBigInt.doubleValue

  final override def floatValue = toBigInt.floatValue

  final override def intValue = toBigInt.intValue

  final override def longValue = toBigInt.longValue

  final override def underlying = toBigInt

  final override def isWhole = true
}

private final case class ZLong(value: Long) extends Z {
  override def unary_- : Z =
    if (value == Long.MinValue) -upgrade else Z(-value)

  override def +(other: Z): Z = other match {
    case ZLong(n) =>
      val r = value + n
      if (((value ^ r) & (n ^ r)) < 0L) upgrade + other
      else ZLong(r)
    case _ => upgrade + other
  }

  override def -(other: Z): Z = other match {
    case ZLong(n) =>
      val r = value - n
      if (((value ^ r) & (n ^ r)) < 0L) upgrade - other
      else ZLong(r)
    case _ => upgrade - other
  }

  override def *(other: Z): Z = other match {
    case ZLong(n) =>
      val r = value * n
      if (r == 0) return Z(r)
      if (n > value) {
        if (((n == -1) && (value == Long.MinValue)) || (r / n != value))
          return upgrade * other
      } else {
        if (((value == -1) && (n == Long.MinValue)) || (r / value != n))
          return upgrade * other
      }
      ZLong(r)
    case _ => upgrade * other
  }

  override def /(other: Z): Z = other match {
    case ZLong(n) =>
      val r = value / n
      if ((value == Long.MinValue) && (n == -1))
        upgrade / other
      else
        ZLong(r)
    case _ => upgrade / other
  }

  override def %(other: Z): Z = upgrade % other

  override def hashCode: Int = value.hashCode

  override def equals(other: Any): B = other match {
    case ZLong(n) => value == n
    case other: ZApint => upgrade == other
    case other: Byte => other.toLong == value
    case other: Char => other.toLong == value
    case other: Short => other.toLong == value
    case other: Int => other.toLong == value
    case other: Long => other == value
    case other: java.math.BigInteger => other == toBigInteger
    case other: BigInt => other == toBigInt
    case _ => false
  }

  override def >(other: Z): B = other match {
    case ZLong(n) => value > n
    case _ => upgrade > other
  }

  override def >=(other: Z): B = other match {
    case ZLong(n) => value >= n
    case _ => upgrade >= other
  }

  override def <(other: Z): B = other match {
    case ZLong(n) => value < n
    case _ => upgrade < other
  }

  override def <=(other: Z): B = other match {
    case ZLong(n) => value <= n
    case _ => upgrade <= other
  }

  override def toInt: Int = {
    assert(Int.MinValue <= value && value <= Int.MaxValue)
    value.toInt
  }

  override def toLong: Long = value

  override def toBigInteger: java.math.BigInteger =
    new java.math.BigInteger(value.toString)

  override def toBigInt: BigInt = BigInt(value)

  override def toZApint: Apint = new Apint(value)

  override def toString: String = value.toString

  private def upgrade: ZApint = ZApint(new Apint(value))
}

private final case class ZApint(value: Apint) extends Z {
  def unary_- : Z = ZApint(value.negate)

  def +(other: Z): Z = ZApint(value.add(other.toZApint))

  def -(other: Z): Z = ZApint(value.subtract(other.toZApint)).pack

  def *(other: Z): Z = ZApint(value.multiply(other.toZApint))

  def /(other: Z): Z = ZApint(value.divide(other.toZApint)).pack

  def %(other: Z): Z = ZApint(value.mod(other.toZApint)).pack

  override lazy val hashCode: Int = toBigInt.hashCode

  override def equals(other: Any): B =
    other match {
      case ZLong(n) => value.equals(new Apint(n))
      case other: ZApint => (this eq other) || value.equals(other.value)
      case other: Byte => new Apint(other) == value
      case other: Char => new Apint(other) == value
      case other: Short => new Apint(other) == value
      case other: Int => new Apint(other) == value
      case other: Long => new Apint(other) == value
      case other: java.math.BigInteger => other == toBigInteger
      case other: BigInt => other == toBigInt
      case _ => false
    }

  def <(other: Z): B = value.compareTo(other.toZApint) < 0

  def <=(other: Z): B = {
    val c = value.compareTo(other.toZApint)
    c < 0 || c == 0
  }

  def >(other: Z): B = value.compareTo(other.toZApint) > 0

  def >=(other: Z): B = {
    val c = value.compareTo(other.toZApint)
    c < 0 || c == 0
  }

  def +(other: Int): Z = this + Z(other)

  def -(other: Int): Z = this - Z(other)

  def *(other: Int): Z = this * Z(other)

  def /(other: Int): Z = this * Z(other)

  def %(other: Int): Z = this * Z(other)

  def ==(other: Int): B = this == Z(other)

  def !=(other: Int): B = this != Z(other)

  def <(other: Int): B = this < Z(other)

  def <=(other: Int): B = this <= Z(other)

  def >(other: Int): B = this > Z(other)

  def >=(other: Int): B = this >= Z(other)

  override def toInt: Int = {
    assert(Z(Z.intMin) <= this && this <= Z(Z.intMax))
    value.toBigInteger.intValue
  }

  override def toLong: Long = {
    assert(Z(Z.longMin) <= this && this <= Z(Z.longMax))
    value.toBigInteger.longValue
  }

  override def toBigInteger: java.math.BigInteger =
    value.toBigInteger

  override def toBigInt: BigInt = BigInt(value.toBigInteger)

  override def toZApint: Apint = value

  override def toString: String = value.toString

  private[math] def pack: Z =
    if ((value.compareTo(Z.longMin) >= 0) && (value.compareTo(Z.longMax) <= 0))
      ZLong(value.longValue)
    else this
}
