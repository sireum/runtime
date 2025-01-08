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

object R {

  object Boxer extends $internal.Boxer {
    def box[T](o: scala.Any): T = o match {
      case o: BigDecimal => R(o).asInstanceOf[T]
    }

    def unbox(o: scala.Any): BigDecimal = o match {
      case o: R => o.value
    }

    override def copyMut(src: AnyRef, srcPos: Z, dest: AnyRef, destPos: Z, length: Z): Unit =
      copy(src, srcPos, dest, destPos, length)
  }

  def unapply(r: R): scala.Option[Predef.String] = scala.Some(r.toString)

  def apply(s: String): Option[R] = try Some(R.$String(s.value)) catch {
    case _: Throwable => None[R]()
  }

  def apply(f: scala.Float): R = BigDecimal(f.toDouble)

  def apply(d: scala.Double): R = BigDecimal(d)

  def apply(n: Z): R = R(BigDecimal(n.toBigInt))

  object $String {
    def apply(s: Predef.String): R = R(BigDecimal(s))
  }

  def random: R = Random.Ext.instance.nextR()

  def randomSeed(seed: Z): R = {
    Random.setSeed(seed)
    Random.Ext.instance.nextR()
  }

  def randomBetween(min: R, max: R): R = {
    assert(min <= max)
    Random.Ext.instance.nextRBetween(min, max)
  }

  def randomSeedBetween(seed: Z, min: R, max: R): R = {
    assert(min <= max)
    Random.setSeed(seed)
    Random.Ext.instance.nextRBetween(min, max)
  }

  import scala.language.implicitConversions

  implicit def apply(r: BigDecimal): R = new R(r)

}

final class R(val value: BigDecimal) extends AnyVal with Number with $internal.HasBoxer {

  @inline def unary_- : R = -value

  @inline def <(other: R): B = value.compare(other.value) < 0

  @inline def <=(other: R): B = value.compare(other.value) <= 0

  @inline def >(other: R): B = value.compare(other.value) > 0

  @inline def >=(other: R): B = value.compare(other.value) >= 0

  @inline def ===(other: R): B = value == other.value

  @inline def =!=(other: R): B = value != other.value

  @inline def +(other: R): R = value + other.value

  @inline def -(other: R): R = value - other.value

  @inline def *(other: R): R = value * other.value

  @inline def /(other: R): R = value / other.value

  @inline def string: String = toString

  @inline override def toString: Predef.String = value.toString

  def boxer: $internal.Boxer = R.Boxer
}
