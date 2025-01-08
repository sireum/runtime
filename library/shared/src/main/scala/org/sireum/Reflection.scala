// #Sireum
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

import U32._
import U64._

object Reflection {

  @enum object Kind {
    "Object"
    "Sig"
    "DatatypeTrait"
    "DatatypeClass"
    "MSig"
    "RecordTrait"
    "RecordClass"
    "Enum"
    "Range"
    "Bits"
    "Ext"
  }

  object Field {
    @enum object Kind {
      "Normal"
      "Param"
      "Hidden"
    }
  }

  @datatype class Field(val isInObject: B, val isVal: B, val kind: Field.Kind.Type, val name: String)

  @datatype class Method(val isInObject: B, val isByName: B, val name: String, val params: ISZ[String])

  @datatype class Info(val kind: Kind.Type, val fields: ISZ[Field], val methods: ISZ[Method])

  val constructorName: String = "apply"
  val extractorName: String = "unapply"

  @strictpure def assignName(name: String): String = s"${name}_="

  @pure def objectOrTypeKey(name: String): U32 = {
    val sha3 = crypto.SHA3.init512
    sha3.update(conversions.String.toU8is(name))
    val a = sha3.finalise()
    return conversions.U8.toU32(a(0)) << u32"24" | conversions.U8.toU32(a(1)) << u32"16" |
      conversions.U8.toU32(a(2)) << u32"8" | conversions.U8.toU32(a(3))
  }

  @pure def methodKey(isInObject: B, owner: String, name: String): U64 = {
    val sha3 = crypto.SHA3.init512
    sha3.update(conversions.String.toU8is(s"$owner${if (isInObject) "." else "#"}$name"))
    val a = sha3.finalise()
    return conversions.U8.toU64(a(0)) << u64"56" | conversions.U8.toU64(a(1)) << u64"48" |
      conversions.U8.toU64(a(2)) << u64"40" | conversions.U8.toU64(a(3)) << u64"32" |
      conversions.U8.toU64(a(4)) << u64"24" | conversions.U8.toU64(a(5)) << u64"16" |
      conversions.U8.toU64(a(6)) << u64"8" | conversions.U8.toU64(a(7))
  }

  @pure def find(reflections: ISZ[Reflection], name: ISZ[String]): Option[Reflection] = {
    val n = st"${(name, ".")}".render
    for (r <- reflections if r.info(n).nonEmpty) {
      return Some(r)
    }
    return None()
  }
}

import Reflection._

@sig trait Reflection {
  def info(name: String): Option[Info] = {
    return None()
  }
  def classNameOf[T](o: T): Option[String] = {
    return None()
  }
  def invoke0[@mut T, @mut R](owner: String, name: String, receiver: T): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke1[@mut T, @mut T1, @mut R](owner: String, name: String, receiver: T, o1: T1): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke2[@mut T, @mut T1, @mut T2, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke3[@mut T, @mut T1, @mut T2, @mut T3, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke4[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke5[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke6[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke7[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke8[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke9[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke10[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke11[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke12[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke13[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke14[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke15[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke16[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke17[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke18[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke19[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke20[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke21[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke22[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke23[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke24[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke25[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke26[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke27[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke28[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke29[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut T29, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28, o29: T29): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke30[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut T29, @mut T30, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28, o29: T29, o30: T30): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke31[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut T29, @mut T30, @mut T31, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28, o29: T29, o30: T30, o31: T31): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invoke32[@mut T, @mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut T29, @mut T30, @mut T31, @mut T32, @mut R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28, o29: T29, o30: T30, o31: T31, o32: T32): R = {
    halt(s"Unavailable reflection on $owner#$name")
  }
  def invokeStatic0[@mut R](owner: String, name: String): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic1[@mut T1, @mut R](owner: String, name: String, o1: T1): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic2[@mut T1, @mut T2, @mut R](owner: String, name: String, o1: T1, o2: T2): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic3[@mut T1, @mut T2, @mut T3, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic4[@mut T1, @mut T2, @mut T3, @mut T4, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic5[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic6[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic7[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic8[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic9[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic10[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic11[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic12[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic13[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic14[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic15[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic16[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic17[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic18[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic19[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic20[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic21[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic22[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic23[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic24[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic25[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic26[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic27[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic28[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic29[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut T29, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28, o29: T29): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic30[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut T29, @mut T30, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28, o29: T29, o30: T30): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic31[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut T29, @mut T30, @mut T31, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28, o29: T29, o30: T30, o31: T31): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def invokeStatic32[@mut T1, @mut T2, @mut T3, @mut T4, @mut T5, @mut T6, @mut T7, @mut T8, @mut T9, @mut T10, @mut T11, @mut T12, @mut T13, @mut T14, @mut T15, @mut T16, @mut T17, @mut T18, @mut T19, @mut T20, @mut T21, @mut T22, @mut T23, @mut T24, @mut T25, @mut T26, @mut T27, @mut T28, @mut T29, @mut T30, @mut T31, @mut T32, @mut R](owner: String, name: String, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22, o23: T23, o24: T24, o25: T25, o26: T26, o27: T27, o28: T28, o29: T29, o30: T30, o31: T31, o32: T32): R = {
    halt(s"Unavailable reflection on $owner.$name")
  }
  def assign[T, T1](owner: String, name: String, receiver: T, value: T1): Unit = {
    invoke1[T, T1, Unit](owner, s"${name}_=", receiver, value)
  }
  def extract[T, R](name: String, receiver: T): MOption[R] = {
    return invokeStatic1[T, MOption[R]](name, extractorName, receiver)
  }
  override def string: String = {
    return "Reflection"
  }
}
