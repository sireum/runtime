// #Sireum
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

import U32._
import U64._

object Reflection {

  val constructorName: String = "apply"
  val extractorName: String = "unapply"

  @strictpure def assignName(name: String): String = s"${name}_="

  def objectOrTypeKey(name: String): U32 = {
    val sha3 = crypto.SHA3.init512
    sha3.update(conversions.String.toU8is(name))
    val a = sha3.finalise()
    return conversions.U8.toU32(a(0)) << u32"24" | conversions.U8.toU32(a(1)) << u32"16" |
      conversions.U8.toU32(a(2)) << u32"8" | conversions.U8.toU32(a(3))
  }

  def methodKey(isInObject: B, owner: String, name: String): U64 = {
    val sha3 = crypto.SHA3.init512
    sha3.update(conversions.String.toU8is(s"$owner${if (isInObject) "." else "#"}$name"))
    val a = sha3.finalise()
    return conversions.U8.toU64(a(0)) << u64"56" | conversions.U8.toU64(a(1)) << u64"48" |
      conversions.U8.toU64(a(2)) << u64"40" | conversions.U8.toU64(a(3)) << u64"32" |
      conversions.U8.toU64(a(4)) << u64"24" | conversions.U8.toU64(a(5)) << u64"16" |
      conversions.U8.toU64(a(6)) << u64"8" | conversions.U8.toU64(a(7))
  }

}

import Reflection._

@sig trait Reflection {
  def isObjectOrTypeReflected(name: String): B
  def objectMethods(name: String): ISZ[String]
  def typeMethods(name: String): ISZ[String]
  def invoke0[T, R](owner: String, name: String, receiverOpt: Option[T]): R
  def invoke1[T, T1, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1): R
  def invoke2[T, T1, T2, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2): R
  def invoke3[T, T1, T2, T3, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3): R
  def invoke4[T, T1, T2, T3, T4, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4): R
  def invoke5[T, T1, T2, T3, T4, T5, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): R
  def invoke6[T, T1, T2, T3, T4, T5, T6, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6): R
  def invoke7[T, T1, T2, T3, T4, T5, T6, T7, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7): R
  def invoke8[T, T1, T2, T3, T4, T5, T6, T7, T8, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8): R
  def invoke9[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9): R
  def invoke10[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10): R
  def invoke11[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11): R
  def invoke12[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12): R
  def invoke13[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13): R
  def invoke14[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14): R
  def invoke15[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15): R
  def invoke16[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16): R
  def invoke17[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17): R
  def invoke18[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18): R
  def invoke19[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18, arg19: T19): R
  def invoke20[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18, arg19: T19, arg20: T20): R
  def invoke21[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18, arg19: T19, arg20: T20, arg21: T21): R
  def invoke22[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](owner: String, name: String, receiverOpt: Option[T], arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18, arg19: T19, arg20: T20, arg21: T21, arg22: T22): R

  def assign[T, T1](owner: String, name: String, receiverOpt: Option[T], value: T1): Unit = {
    invoke1[T, T1, Unit](owner, s"${name}_=", receiverOpt, value)
  }
  def construct0[R](name: String): R = {
    return invoke0[Unit, R](name, constructorName, None())
  }
  def construct1[T1, R](name: String, arg1: T1): R = {
    return invoke1[Unit, T1, R](name, constructorName, None(), arg1)
  }
  def construct2[T1, T2, R](name: String, arg1: T1, arg2: T2): R = {
    return invoke2[Unit, T1, T2, R](name, constructorName, None(), arg1, arg2)
  }
  def construct3[T1, T2, T3, R](name: String, arg1: T1, arg2: T2, arg3: T3): R = {
    return invoke3[Unit, T1, T2, T3, R](name, constructorName, None(), arg1, arg2, arg3)
  }
  def construct4[T1, T2, T3, T4, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4): R = {
    return invoke4[Unit, T1, T2, T3, T4, R](name, constructorName, None(), arg1, arg2, arg3, arg4)
  }
  def construct5[T1, T2, T3, T4, T5, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): R = {
    return invoke5[Unit, T1, T2, T3, T4, T5, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5)
  }
  def construct6[T1, T2, T3, T4, T5, T6, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6): R = {
    return invoke6[Unit, T1, T2, T3, T4, T5, T6, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6)
  }
  def construct7[T1, T2, T3, T4, T5, T6, T7, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7): R = {
    return invoke7[Unit, T1, T2, T3, T4, T5, T6, T7, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7)
  }
  def construct8[T1, T2, T3, T4, T5, T6, T7, T8, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8): R = {
    return invoke8[Unit, T1, T2, T3, T4, T5, T6, T7, T8, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)
  }
  def construct9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9): R = {
    return invoke9[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)
  }
  def construct10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10): R = {
    return invoke10[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10)
  }
  def construct11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11): R = {
    return invoke11[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11)
  }
  def construct12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12): R = {
    return invoke12[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12)
  }
  def construct13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13): R = {
    return invoke13[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13)
  }
  def construct14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14): R = {
    return invoke14[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14)
  }
  def construct15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15): R = {
    return invoke15[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15)
  }
  def construct16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16): R = {
    return invoke16[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16)
  }
  def construct17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17): R = {
    return invoke17[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17)
  }
  def construct18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18): R = {
    return invoke18[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18)
  }
  def construct19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18, arg19: T19): R = {
    return invoke19[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19)
  }
  def construct20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18, arg19: T19, arg20: T20): R = {
    return invoke20[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20)
  }
  def construct21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18, arg19: T19, arg20: T20, arg21: T21): R = {
    return invoke21[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21)
  }
  def construct22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](name: String, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, arg6: T6, arg7: T7, arg8: T8, arg9: T9, arg10: T10, arg11: T11, arg12: T12, arg13: T13, arg14: T14, arg15: T15, arg16: T16, arg17: T17, arg18: T18, arg19: T19, arg20: T20, arg21: T21, arg22: T22): R = {
    return invoke22[Unit, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](name, constructorName, None(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22)
  }
  def extract[T, R](name: String, receiver: T): R = {
    return invoke1[Unit, T, R](name, extractorName, None(), receiver)
  }
}
