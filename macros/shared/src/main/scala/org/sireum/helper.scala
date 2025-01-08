/*
 * Copyright (c) 2017-2025, Robby, Kansas State University
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

import org.sireum.$internal.{ImmutableMarker, MutableMarker}

import scala.annotation.StaticAnnotation

object helper {

  private val topValueError = "Unexpected a value not implementing either Slang Immutable or Mutable."
  val BigInt0: BigInt = BigInt(0)
  val UByteMax: BigInt = BigInt(255)
  val UShortMax: BigInt = BigInt(65535)
  val UIntMax: BigInt = BigInt(4294967295L)
  val ULongMax: BigInt = BigInt("18446744073709551615")

  def halt(msg: Any): Nothing = {
    val e = new Error(msg.toString)
    if ("true" != System.getProperty("org.sireum.silenthalt")) {
      e.printStackTrace()
    }
    throw e
  }

  def clone[T](o: T): T = {
    val r = o match {
      case o: ImmutableMarker => o.$clone
      case o: MutableMarker => o.$clone
      case o: Function0[_] => o
      case o: Function1[_, _] => o
      case o: Function2[_, _, _] => o
      case o: Function3[_, _, _, _] => o
      case o: Function4[_, _, _, _, _] => o
      case o: Function5[_, _, _, _, _, _] => o
      case o: Function6[_, _, _, _, _, _, _] => o
      case o: Function7[_, _, _, _, _, _, _, _] => o
      case o: Function8[_, _, _, _, _, _, _, _, _] => o
      case o: Function9[_, _, _, _, _, _, _, _, _, _] => o
      case o: Function10[_, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function11[_, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function12[_, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function13[_, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function14[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Tuple1[_] => Tuple1(cloneAssign(o._1))
      case o: Tuple2[_, _] => Tuple2(cloneAssign(o._1), cloneAssign(o._2))
      case o: Tuple3[_, _, _] => Tuple3(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3))
      case o: Tuple4[_, _, _, _] => Tuple4(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4))
      case o: Tuple5[_, _, _, _, _] => Tuple5(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5))
      case o: Tuple6[_, _, _, _, _, _] => Tuple6(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6))
      case o: Tuple7[_, _, _, _, _, _, _] => Tuple7(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7))
      case o: Tuple8[_, _, _, _, _, _, _, _] => Tuple8(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8))
      case o: Tuple9[_, _, _, _, _, _, _, _, _] => Tuple9(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9))
      case o: Tuple10[_, _, _, _, _, _, _, _, _, _] => Tuple10(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10))
      case o: Tuple11[_, _, _, _, _, _, _, _, _, _, _] => Tuple11(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11))
      case o: Tuple12[_, _, _, _, _, _, _, _, _, _, _, _] => Tuple12(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12))
      case o: Tuple13[_, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple13(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13))
      case o: Tuple14[_, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple14(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14))
      case o: Tuple15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple15(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15))
      case o: Tuple16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple16(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16))
      case o: Tuple17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple17(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17))
      case o: Tuple18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple18(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18))
      case o: Tuple19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple19(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18), cloneAssign(o._19))
      case o: Tuple20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple20(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18), cloneAssign(o._19), cloneAssign(o._20))
      case o: Tuple21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple21(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18), cloneAssign(o._19), cloneAssign(o._20), cloneAssign(o._21))
      case o: Tuple22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple22(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18), cloneAssign(o._19), cloneAssign(o._20), cloneAssign(o._21), cloneAssign(o._22))
      case o: Unit => o
      case _ => halt(topValueError + s": $o (of ${o.getClass.getName})")
    }
    return r.asInstanceOf[T]
  }

  def cloneAssign[T](o: T): T = {
    val r = o match {
      case o: MutableMarker => (o.$clone.$owned = true)
      case o: ImmutableMarker => o.$clone
      case o: Function0[_] => o
      case o: Function1[_, _] => o
      case o: Function2[_, _, _] => o
      case o: Function3[_, _, _, _] => o
      case o: Function4[_, _, _, _, _] => o
      case o: Function5[_, _, _, _, _, _] => o
      case o: Function6[_, _, _, _, _, _, _] => o
      case o: Function7[_, _, _, _, _, _, _, _] => o
      case o: Function8[_, _, _, _, _, _, _, _, _] => o
      case o: Function9[_, _, _, _, _, _, _, _, _, _] => o
      case o: Function10[_, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function11[_, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function12[_, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function13[_, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function14[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Function22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => o
      case o: Tuple1[_] => Tuple1(cloneAssign(o._1))
      case o: Tuple2[_, _] => Tuple2(cloneAssign(o._1), cloneAssign(o._2))
      case o: Tuple3[_, _, _] => Tuple3(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3))
      case o: Tuple4[_, _, _, _] => Tuple4(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4))
      case o: Tuple5[_, _, _, _, _] => Tuple5(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5))
      case o: Tuple6[_, _, _, _, _, _] => Tuple6(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6))
      case o: Tuple7[_, _, _, _, _, _, _] => Tuple7(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7))
      case o: Tuple8[_, _, _, _, _, _, _, _] => Tuple8(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8))
      case o: Tuple9[_, _, _, _, _, _, _, _, _] => Tuple9(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9))
      case o: Tuple10[_, _, _, _, _, _, _, _, _, _] => Tuple10(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10))
      case o: Tuple11[_, _, _, _, _, _, _, _, _, _, _] => Tuple11(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11))
      case o: Tuple12[_, _, _, _, _, _, _, _, _, _, _, _] => Tuple12(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12))
      case o: Tuple13[_, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple13(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13))
      case o: Tuple14[_, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple14(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14))
      case o: Tuple15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple15(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15))
      case o: Tuple16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple16(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16))
      case o: Tuple17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple17(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17))
      case o: Tuple18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple18(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18))
      case o: Tuple19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple19(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18), cloneAssign(o._19))
      case o: Tuple20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple20(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18), cloneAssign(o._19), cloneAssign(o._20))
      case o: Tuple21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple21(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18), cloneAssign(o._19), cloneAssign(o._20), cloneAssign(o._21))
      case o: Tuple22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => Tuple22(cloneAssign(o._1), cloneAssign(o._2), cloneAssign(o._3), cloneAssign(o._4), cloneAssign(o._5), cloneAssign(o._6), cloneAssign(o._7), cloneAssign(o._8), cloneAssign(o._9), cloneAssign(o._10), cloneAssign(o._11), cloneAssign(o._12), cloneAssign(o._13), cloneAssign(o._14), cloneAssign(o._15), cloneAssign(o._16), cloneAssign(o._17), cloneAssign(o._18), cloneAssign(o._19), cloneAssign(o._20), cloneAssign(o._21), cloneAssign(o._22))
      case o: Unit => o
      case _ => halt(topValueError + s": $o (of ${o.getClass.getName})")
    }
    return r.asInstanceOf[T]
  }

  def assignMut[T](x: MutableMarker): T =
    (if (x.$owned) x.$clone.$owned = true else x.$owned = true).asInstanceOf[T]

  def assign[T](arg: T): T = {
    arg match {
      case x: MutableMarker => assignMut[T](x)
      case _ => arg
    }
  }

  def retMut[T](x: MutableMarker): T =
    (if (x.$owned && x.$clonable) x.$clone else x).asInstanceOf[T]

  def ret[T](arg: T): T = {
    arg match {
      case x: MutableMarker => retMut[T](x)
      case _ => arg
    }
  }

  def isUByte(n: BigInt): Boolean = BigInt0 <= n && n <= UByteMax

  def isByte(n: BigInt): Boolean = Byte.MinValue.toInt <= n && n <= Byte.MaxValue.toInt

  def isUShort(n: BigInt): Boolean = BigInt0 <= n && n <= UShortMax

  def isShort(n: BigInt): Boolean = Short.MinValue.toInt <= n && n <= Short.MaxValue.toInt

  def isUInt(n: BigInt): Boolean = BigInt0 <= n && n <= UIntMax

  def isInt(n: BigInt): Boolean = Int.MinValue <= n && n <= Int.MaxValue

  def isULong(n: BigInt): Boolean = BigInt0 <= n && n <= ULongMax

  def isLong(n: BigInt): Boolean = Long.MinValue <= n && n <= Long.MaxValue

  def bits(min: BigInt, max: BigInt): Option[(Boolean, Int)] =
    if (isUByte(min) && isUByte(max)) Some((false, 8))
    else if (isByte(min) && isByte(max)) Some((true, 8))
    else if (isUShort(min) && isUShort(max)) Some((false, 16))
    else if (isShort(min) && isShort(max)) Some((true, 16))
    else if (isUInt(min) && isUInt(max)) Some((false, 32))
    else if (isInt(min) && isInt(max)) Some((true, 32))
    else if (isULong(min) && isULong(max)) Some((false, 64))
    else if (isLong(min) && isLong(max)) Some((true, 64))
    else None

  def normNum(s: Predef.String): Predef.String = {
    val sb = new _root_.java.lang.StringBuilder(s.length)
    for (c <- s) c match {
      case ',' | ' ' | '_' =>
      case _ => sb.append(c)
    }
    sb.toString
  }

  def escape(raw: Predef.String): Predef.String = {
    val sb = new _root_.java.lang.StringBuilder

    def escapeChar(ch: Char): Unit = ch match {
      case '\b' => sb.append("\\b")
      case '\t' => sb.append("\\t")
      case '\n' => sb.append("\\n")
      case '\f' => sb.append("\\f")
      case '\r' => sb.append("\\r")
      case '"' => sb.append("\\\"")
      case '\'' => sb.append("\\\'")
      case '\\' => sb.append("\\\\")
      case _ =>
        if (ch.isControl) {
          sb.append("\\0")
          sb.append(Integer.toOctalString(ch.toInt))
        }
        else sb.append(ch)
    }

    sb.append('"')
    raw.foreach(escapeChar)
    sb.append('"')
    sb.toString
  }

  import scala.language.experimental.macros

  def $assign[T](arg: T): T = macro $internal.Macro.$assign

  def $ret[T](arg: T): T = macro $internal.Macro.$ret

  def $tmatch[T](arg: T): T = macro $internal.Macro.$tmatch

}

class helper extends StaticAnnotation

