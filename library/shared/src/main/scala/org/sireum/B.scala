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

import scala.collection.immutable.{Map => M}

object B {

  object Boxer extends $internal.Boxer {
    val sz: Z = Z.MP($internal.Boxer.MaxArraySize) * 8
    val bigEndianHexMap: M[scala.Long, scala.Char] = M(
      /* 0000 */ 0x0L -> '0',
      /* 1000 */ 0x8L -> '1',
      /* 0100 */ 0x4L -> '2',
      /* 1100 */ 0xCL -> '3',
      /* 0010 */ 0x2L -> '4',
      /* 1010 */ 0xAL -> '5',
      /* 0110 */ 0x6L -> '6',
      /* 1110 */ 0xEL -> '7',
      /* 0001 */ 0x1L -> '8',
      /* 1001 */ 0x9L -> '9',
      /* 0101 */ 0x5L -> 'A',
      /* 1101 */ 0xDL -> 'B',
      /* 0011 */ 0x3L -> 'C',
      /* 1011 */ 0xBL -> 'D',
      /* 0111 */ 0x7L -> 'E',
      /* 1111 */ 0xFL -> 'F')

    def at(value: scala.Array[scala.Byte], index: scala.Int): scala.Boolean = {
      val mask = U8(1) << U8(index % 8)
      (U8(value(index / 8)) & mask).value != 0
    }

    def up(value: scala.Array[scala.Byte], index: scala.Int, v: scala.Boolean): Unit = {
      val mask = U8(1) << U8(index % 8)
      val i = index / 8
      if (v) {
        value(i) = (U8(value(i)) | mask).value
      } else {
        value(i) = (U8(value(i)) & ~mask).value
      }
    }

    def len(length: Z): scala.Int = length / 8 + (if (length % 8 == 0) 0 else 1)

    def box[T](o: scala.Any): T = (o match {
      case true => T
      case false => F
    }).asInstanceOf[T]

    def unbox(o: scala.Any): scala.Boolean = o match {
      case T => true
      case F => false
    }

    override def create(length: Z): scala.AnyRef = new scala.Array[scala.Byte](len(length))

    override def copy(src: scala.AnyRef, srcPos: Z, dest: scala.AnyRef, destPos: Z, length: Z): Unit =
      ((src, dest): @unchecked) match {
        case (src: scala.Array[scala.Byte], dest: scala.Array[scala.Byte]) =>
          val sp = srcPos.toInt
          val dp = destPos.toInt
          if (sp == 0 && dp == 0) {
            val n = length / 8
            System.arraycopy(src, 0, dest, 0, n)
            for (i <- n * 8 until length) {
              up(dest, i, at(src, i))
            }
          } else {
            for (i <- 0 until length) {
              up(dest, dp + i, at(src, sp + i))
            }
          }
      }

    override def copyMut(src: scala.AnyRef, srcPos: Z, dest: scala.AnyRef, destPos: Z, length: Z): Unit =
      copy(src, srcPos, dest, destPos, length)

    override def lookup[T](a: scala.AnyRef, i: Z): T = a match {
      case a: scala.Array[scala.Byte] => box(at(a, i))
    }

    override def store(a: scala.AnyRef, i: Z, v: scala.Any): Unit = a match {
      case a: scala.Array[scala.Byte] => up(a, i, unbox(v))
    }

    override def size(a: scala.AnyRef): Z = a match {
      case a: scala.Array[scala.Byte] => a.length * 8
    }

    override def clone(a: scala.AnyRef, length: Z, newLength: Z, offset: Z): scala.AnyRef = {
      val size = this.size(a)
      if (newLength <= size) {
        val r = create(size)
        copy(a, Z.MP.zero, r, offset, length)
        r
      } else {
        assert(newLength <= Boxer.sz, s"Slang currently only supports IS[_,B]/MS[_,B] size up to ${Boxer.sz}.")
        var newSize = newLength * 3 / 2
        if (newSize > Boxer.sz) newSize = Boxer.sz
        val r = create(newSize)
        copy(a, Z.MP.zero, r, offset, length)
        r
      }
    }

    override def cloneMut(a: scala.AnyRef, length: Z, newLength: Z, offset: Z): scala.AnyRef =
      clone(a, length, newLength, offset)

    override def toString(a: scala.AnyRef, length: Z): Predef.String = a match {
      case a: scala.Array[scala.Byte] =>
        val sb = new _root_.java.lang.StringBuilder
        sb.append('[')
        for (i <- 0 until len(length)) {
          sb.append(U8(a(i)).toString)
        }
        sb.append(']')
        sb.toString
    }
  }

  val T = new B(true)
  val F = new B(false)

  def random: B = Random.Ext.instance.nextB()

  def randomSeed(seed: Z): B = {
    Random.setSeed(seed)
    Random.Ext.instance.nextB()
  }

  def unapply(b: B): scala.Option[scala.Boolean] = scala.Some(b.value)

  import scala.language.implicitConversions

  @inline implicit def apply(b: Boolean): B = if (b) T else F

  @inline implicit def $4B(b: B): Boolean = b.value

}

final class B(val value: Boolean) extends AnyVal with Immutable with $internal.HasBoxer
  with Contract.SequentBuilder with Contract.StepBuilder2 {

  @inline def &(other: B): B = value & other.value

  @inline def |(other: B): B = value | other.value

  @inline def |^(other: B): B = value ^ other.value

  @inline def &&(other: => B): B = value && other.value

  @inline def ||(other: => B): B = value || other.value

  @inline def ->:(other: B): B = !value | other.value

  @inline def -->:(other: => B): B = !value || other.value

  @inline def __>:(other: B): B = !value | other.value

  @inline def ___>:(other: => B): B = !value || other.value

  @inline def =>:(other: B): B = this ->: other

  @inline def unary_! : B = !value

  @inline def unary_~ : B = !value

  @inline def ===(other: B): B = value == other.value

  @inline def =!=(other: B): B = value != other.value

  @inline def string: String = toString

  @inline override def toString: Predef.String = value.toString

  @inline def boxer: $internal.Boxer = B.Boxer

}
