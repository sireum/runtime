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
package org.sireum.anvil

import org.sireum._
import org.sireum.U8._
import org.sireum.U32._
import org.sireum.S32._
import org.sireum.U64._
import org.sireum.S64._

import org.sireum.anvil.PrinterIndex._
import org.sireum.anvil.PrinterIndex.I16._
import org.sireum.anvil.PrinterIndex.I20._
import org.sireum.anvil.PrinterIndex.I50._
import org.sireum.anvil.PrinterIndex.I320._
import org.sireum.anvil.PrinterIndex.U._

object Runtime {

  @pure def shlU32(n: U32, m: U32): U32 = {
    var r = n
    var shift = m
    while (shift >= u32"20") {
      r = r << u32"20"
      shift = shift - u32"20"
    }
    r = r << shift
    return r
  }

  @pure def shlU64(n: U64, m: U64): U64 = {
    var r = n
    var shift = m
    while (shift >= u64"20") {
      r = r << u64"20"
      shift = shift - u64"20"
    }
    r = r << shift
    return r
  }

  @pure def shrU32(n: U32, m: U32): U32 = {
    var r = n
    var shift = m
    while (shift >= u32"20") {
      r = r >>> u32"20"
      shift = shift - u32"20"
    }
    r = r >>> shift
    return r
  }

  @pure def shrU64(n: U64, m: U64): U64 = {
    var r = n
    var shift = m
    while (shift >= u64"20") {
      r = r >>> u64"20"
      shift = shift - u64"20"
    }
    r = r >>> shift
    return r
  }

  @pure def shrS32(n: S32, m: S32): S32 = {
    var r = n
    var shift = m
    while (shift >= s32"20") {
      r = r >> s32"20"
      shift = shift - s32"20"
    }
    r = r >> shift
    return r
  }

  @pure def shrS64(n: S64, m: S64): S64 = {
    var r = n
    var shift = m
    while (shift >= s64"20") {
      r = r >> s64"20"
      shift = shift - s64"20"
    }
    r = r >> shift
    return r
  }

  @ext("org.sireum.conversions.Printer_Ext") object Ext {
    @pure def u2z(n: U): Z = $
    @pure def z2u(n: Z): U = $
  }

  def printB(buffer: MS[U, U8], index: U, mask: U, b: B): U64 = {
    buffer(index & mask) = if (b) u8"84" else u8"70"
    return u64"1"
  }

  def printC(buffer: MS[U, U8], index: U, mask: U, c: C): U64 = {
    val raw = conversions.C.toU32(c)
    if (u32"0" <= raw && raw <= u32"0x7F") {
      buffer(index & mask) = conversions.U32.toU8(raw)
      return u64"1"
    } else if (u32"80" <= raw && raw <= u32"0x7FF") {
      buffer(index & mask) = conversions.U32.toU8(u32"0xC0" | ((raw >>> u32"6") & u32"0x1F"))
      buffer((index + u"1") & mask) = conversions.U32.toU8(u32"0x80" | (raw & u32"0x3F"))
      return u64"2"
    } else if (u32"800" <= raw && raw <= u32"0xFFFF") {
      buffer(index & mask) = conversions.U32.toU8(u32"0xE0" | ((raw >>> u32"12") & u32"0xF"))
      buffer((index + u"1") & mask) = conversions.U32.toU8(u32"0x80" | ((raw >>> u32"6") & u32"0x3F"))
      buffer((index + u"2") & mask) = conversions.U32.toU8(u32"0x80" | (raw & u32"0x3F"))
      return u64"3"
    } else {
      buffer(index & mask) = conversions.U32.toU8(u32"0xF0" | ((raw >>> u32"18") & u32"0x7"))
      buffer((index + u"1") & mask) = conversions.U32.toU8(u32"0x80" | ((raw >>> u32"12") & u32"0x3F"))
      buffer((index + u"2") & mask) = conversions.U32.toU8(u32"0x80" | ((raw >>> u32"6") & u32"0x3F"))
      buffer((index + u"3") & mask) = conversions.U32.toU8(u32"0x80" | (raw & u32"0x3F"))
      return u64"4"
    }
  }

  def printS64(buffer: MS[U, U8], index: U, mask: U, n: S64): U64 = {
    if (n == s64"-9223372036854775808") {
      // -9,223,372,036,854,775,808
      buffer(index & mask) = u8"45"           // -
      buffer((index + u"1") & mask) = u8"57"  // 9
      buffer((index + u"2") & mask) = u8"50"  // 2
      buffer((index + u"3") & mask) = u8"50"  // 2
      buffer((index + u"4") & mask) = u8"51"  // 3
      buffer((index + u"5") & mask) = u8"51"  // 3
      buffer((index + u"6") & mask) = u8"55"  // 7
      buffer((index + u"7") & mask) = u8"50"  // 2
      buffer((index + u"8") & mask) = u8"48"  // 0
      buffer((index + u"9") & mask) = u8"51"  // 3
      buffer((index + u"10") & mask) = u8"54" // 6
      buffer((index + u"11") & mask) = u8"56" // 8
      buffer((index + u"12") & mask) = u8"53" // 5
      buffer((index + u"13") & mask) = u8"52" // 4
      buffer((index + u"14") & mask) = u8"55" // 7
      buffer((index + u"15") & mask) = u8"55" // 7
      buffer((index + u"16") & mask) = u8"53" // 5
      buffer((index + u"17") & mask) = u8"56" // 8
      buffer((index + u"18") & mask) = u8"48" // 0
      buffer((index + u"19") & mask) = u8"56" // 8
      return u64"20"
    }
    if (n == s64"0") {
      buffer(index & mask) = u8"48"
      return u64"1"
    }
    val buff = MS[I20, U8](
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0")
    var i = i20"0"
    val neg = n < s64"0"
    var m: S64 = if (neg) n * s64"-1" else n
    while (m > s64"0") {
      m % s64"10" match {
        case s64"0" => buff(i) = u8"48"
        case s64"1" => buff(i) = u8"49"
        case s64"2" => buff(i) = u8"50"
        case s64"3" => buff(i) = u8"51"
        case s64"4" => buff(i) = u8"52"
        case s64"5" => buff(i) = u8"53"
        case s64"6" => buff(i) = u8"54"
        case s64"7" => buff(i) = u8"55"
        case s64"8" => buff(i) = u8"56"
        case s64"9" => buff(i) = u8"57"
      }
      m = m / s64"10"
      i = i + i20"1"
    }
    if (neg) {
      buff(i) = u8"45"
      i = i + i20"1"
    }
    var j = i - i20"1"
    var idx = index
    var r = u64"0"
    while (j >= i20"0") {
      buffer(idx & mask) = buff(j)
      j = j - i20"1"
      idx = idx + u"1"
      r = r + u64"1"
    }
    return r
  }

  def printU64(buffer: MS[U, U8], index: U, mask: U, n: U64): U64 = {
    if (n == u64"0") {
      buffer(index & mask) = u8"48"
      return u64"1"
    }
    val buff = MS[I20, U8](
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0")
    var i = i20"0"
    var m = n
    while (m > u64"0") {
      m % u64"10" match {
        case u64"0" => buff(i) = u8"48"
        case u64"1" => buff(i) = u8"49"
        case u64"2" => buff(i) = u8"50"
        case u64"3" => buff(i) = u8"51"
        case u64"4" => buff(i) = u8"52"
        case u64"5" => buff(i) = u8"53"
        case u64"6" => buff(i) = u8"54"
        case u64"7" => buff(i) = u8"55"
        case u64"8" => buff(i) = u8"56"
        case u64"9" => buff(i) = u8"57"
      }
      m = m / u64"10"
      i = i + i20"1"
    }
    var j = i - i20"1"
    var idx = index
    var r = u64"0"
    while (j >= i20"0") {
      buffer(idx & mask) = buff(j)
      j = j - i20"1"
      idx = idx + u"1"
      r = r + u64"1"
    }
    return r
  }

  def printU64Hex(buffer: MS[U, U8], index: U, mask: U, n: U64, digits: Z): U64 = {
    val buff = MS[I16, U8](
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0"
    )
    var i = i16"0"
    var m = n
    var d = digits
    while (m > u64"0") {
      m & u64"0xF" match {
        case u64"0" => buff(i) = u8"48"
        case u64"1" => buff(i) = u8"49"
        case u64"2" => buff(i) = u8"50"
        case u64"3" => buff(i) = u8"51"
        case u64"4" => buff(i) = u8"52"
        case u64"5" => buff(i) = u8"53"
        case u64"6" => buff(i) = u8"54"
        case u64"7" => buff(i) = u8"55"
        case u64"8" => buff(i) = u8"56"
        case u64"9" => buff(i) = u8"57"
        case u64"10" => buff(i) = u8"65"
        case u64"11" => buff(i) = u8"66"
        case u64"12" => buff(i) = u8"67"
        case u64"13" => buff(i) = u8"68"
        case u64"14" => buff(i) = u8"69"
        case u64"15" => buff(i) = u8"70"
      }
      m = m >>> u64"4"
      i = i + i16"1"
      d = d - 1
    }
    var idx = index
    while (d > 0) {
      buffer(idx & mask) = u8"48"
      d = d - 1
      idx = idx + u"1"
    }
    var j = i - i16"1"
    while (j >= i16"0") {
      buffer(idx & mask) = buff(j)
      j = j - i16"1"
      idx = idx + u"1"
    }
    return conversions.Z.toU64(digits)
  }

  def f32Digit(buffer: MS[I50, U8], index: I50, n: F32): Unit = {
    if (n > 9f) {
      buffer(index) = u8"57"  // 9
      return
    }
    if (n > 8f) {
      buffer(index) = u8"56"  // 8
      return
    }
    if (n > 7f) {
      buffer(index) = u8"55"  // 7
      return
    }
    if (n > 6f) {
      buffer(index) = u8"54"  // 6
      return
    }
    if (n > 5f) {
      buffer(index) = u8"53"  // 5
      return
    }
    if (n > 4f) {
      buffer(index) = u8"52"  // 4
      return
    }
    if (n > 3f) {
      buffer(index) = u8"51"  // 3
      return
    }
    if (n > 2f) {
      buffer(index) = u8"50"  // 2
      return
    }
    if (n > 1f) {
      buffer(index) = u8"49"  // 1
      return
    }
    buffer(index) = u8"48"    // 0
  }

  def f64Digit(buffer: MS[I320, U8], index: I320, n: F64): Unit = {
    if (n > 9d) {
      buffer(index) = u8"57"  // 9
      return
    }
    if (n > 8d) {
      buffer(index) = u8"56"  // 8
      return
    }
    if (n > 7d) {
      buffer(index) = u8"55"  // 7
      return
    }
    if (n > 6d) {
      buffer(index) = u8"54"  // 6
      return
    }
    if (n > 5d) {
      buffer(index) = u8"53"  // 5
      return
    }
    if (n > 4d) {
      buffer(index) = u8"52"  // 4
      return
    }
    if (n > 3d) {
      buffer(index) = u8"51"  // 3
      return
    }
    if (n > 2d) {
      buffer(index) = u8"50"  // 2
      return
    }
    if (n > 1d) {
      buffer(index) = u8"49"  // 1
      return
    }
    buffer(index) = u8"48"    // 0
  }

  def printF32_2(buffer: MS[U, U8], index: U, mask: U, n: F32): U64 = {
    if (n == 0f) {
      buffer(index & mask) = u8"48"           // 0
      buffer((index + u"1") & mask) = u8"46"  // .
      buffer((index + u"2") & mask) = u8"48"  // 0
      return u64"3"
    } else if (n == -0f) {
      buffer(index & mask) = u8"45"           // -
      buffer((index + u"1") & mask) = u8"48"  // 0
      buffer((index + u"2") & mask) = u8"46"  // .
      buffer((index + u"3") & mask) = u8"48"  // 0
      return u64"4"
    }
    val raw = conversions.F32.toRawU32(n)
    if (raw == u32"0x7F800000") {
      buffer(index & mask) = u8"73"             // I
      buffer((index + u"1") & mask) = u8"110"   // n
      buffer((index + u"2") & mask) = u8"102"   // f
      buffer((index + u"3") & mask) = u8"105"   // i
      buffer((index + u"4") & mask) = u8"110"   // n
      buffer((index + u"5") & mask) = u8"105"   // i
      buffer((index + u"6") & mask) = u8"116"   // t
      buffer((index + u"7") & mask) = u8"121"   // y
      return u64"8"
    } else if (raw == u32"0xFF800000") {
      buffer(index & mask) = u8"45"             // -
      buffer((index + u"1") & mask) = u8"73"    // I
      buffer((index + u"2") & mask) = u8"110"   // n
      buffer((index + u"3") & mask) = u8"102"   // f
      buffer((index + u"4") & mask) = u8"105"   // i
      buffer((index + u"5") & mask) = u8"110"   // n
      buffer((index + u"6") & mask) = u8"105"   // i
      buffer((index + u"7") & mask) = u8"116"   // t
      buffer((index + u"8") & mask) = u8"121"   // y
      return u64"9"
    } else if ((raw << u32"1") >>> u32"23" == u32"0x1FF") {
      buffer(index & mask) = u8"78"             // N
      buffer((index + u"1") & mask) = u8"97"    // a
      buffer((index + u"2") & mask) = u8"78"    // N
      return u64"3"
    }
    val buff = MS[I50, U8](
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0"
    )
    val neg: B = if (n < 0f) T else F
    var m: F32 = if (neg) n * -1f else n
    val om = m
    var i = i50"0"
    if (m < 1f) {
      buff(i) = u8"48"
      i = i + i50"1"
    } else {
      var stop = F
      while (!stop) {
        f32Digit(buff, i, m % 10f)
        m = m / 10f
        i = i + i50"1"
        if (m < 1f) {
          stop = T
        }
      }
    }
    if (neg) {
      buff(i) = u8"45"
      i = i + i50"1"
    }
    var idx = index
    var j = i50"0"
    var r = u64"0"
    while (j < i) {
      buffer(idx & mask) = buff(i - j - i50"1")
      j = j + i50"1"
      idx = idx + u"1"
      r = r + u64"1"
    }
    buffer(idx & mask) = u8"46"
    idx = idx + u"1"
    m = om
    m = m * 10f
    i = i50"0"
    f32Digit(buff, i, m % 10f)
    i = i + i50"1"
    m = (m * 10f) % 10f
    val k = (m * 10f) % 10f
    if (k >= 5f) {
      m = m + 1f
    }
    f32Digit(buff, i, m)
    i = i + i50"1"
    if (buff(i - i50"1") == u8"48" && buff(i) == u8"48") {
      i = i - i50"1"
    }
    j = i50"0"
    while (j < i) {
      buffer(idx & mask) = buff(j)
      j = j + i50"1"
      idx = idx + u"1"
      r = r + u64"1"
    }
    return r
  }

  def printF64_2(buffer: MS[U, U8], index: U, mask: U, n: F64): U64 = {
    if (n == 0d) {
      buffer(index & mask) = u8"48"           // 0
      buffer((index + u"1") & mask) = u8"46"  // .
      buffer((index + u"2") & mask) = u8"48"  // 0
      return u64"3"
    } else if (n == -0d) {
      buffer(index & mask) = u8"45"           // -
      buffer((index + u"1") & mask) = u8"48"  // 0
      buffer((index + u"2") & mask) = u8"46"  // .
      buffer((index + u"3") & mask) = u8"48"  // 0
      return u64"4"
    }
    val raw = conversions.F64.toRawU64(n)
    if (raw == u64"0x7FF0000000000000") {
      buffer(index & mask) = u8"73"             // I
      buffer((index + u"1") & mask) = u8"110"   // n
      buffer((index + u"2") & mask) = u8"102"   // f
      buffer((index + u"3") & mask) = u8"105"   // i
      buffer((index + u"4") & mask) = u8"110"   // n
      buffer((index + u"5") & mask) = u8"105"   // i
      buffer((index + u"6") & mask) = u8"116"   // t
      buffer((index + u"7") & mask) = u8"121"   // y
      return u64"8"
    } else if (raw == u64"0xFFF0000000000000") {
      buffer(index & mask) = u8"45"             // -
      buffer((index + u"1") & mask) = u8"73"    // I
      buffer((index + u"2") & mask) = u8"110"   // n
      buffer((index + u"3") & mask) = u8"102"   // f
      buffer((index + u"4") & mask) = u8"105"   // i
      buffer((index + u"5") & mask) = u8"110"   // n
      buffer((index + u"6") & mask) = u8"105"   // i
      buffer((index + u"7") & mask) = u8"116"   // t
      buffer((index + u"8") & mask) = u8"121"   // y
      return u64"9"
    } else if ((raw << u64"1") >>> u64"53" == u64"0x7FF") {
      buffer(index & mask) = u8"78"             // N
      buffer((index + u"1") & mask) = u8"97"    // a
      buffer((index + u"2") & mask) = u8"78"    // N
      return u64"3"
    }
    val buff = MS[I320, U8](
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0",
      u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0", u8"0"
    )
    val neg: B = if (n < 0d) T else F
    var m: F64 = if (neg) n * -1d else n
    val om = m
    var i = i320"0"
    if (m < 1d) {
      buff(i) = u8"48"
      i = i + i320"1"
    } else {
      var stop = F
      while (!stop) {
        f64Digit(buff, i, m % 10d)
        m = m / 10d
        i = i + i320"1"
        if (m < 1d) {
          stop = T
        }
      }
    }
    if (neg) {
      buff(i) = u8"45"
      i = i + i320"1"
    }
    var idx = index
    var j = i320"0"
    var r = u64"0"
    while (j < i) {
      buffer(idx & mask) = buff(i - j - i320"1")
      j = j + i320"1"
      idx = idx + u"1"
      r = r + u64"1"
    }
    buffer(idx & mask) = u8"46"
    idx = idx + u"1"
    m = om
    m = m * 10d
    i = i320"0"
    f64Digit(buff, i, m % 10d)
    i = i + i320"1"
    m = (m * 10d) % 10d
    val k = (m * 10d) % 10d
    if (k >= 5d) {
      m = m + 1d
    }
    f64Digit(buff, i, m)
    i = i + i320"1"
    if (buff(i - i320"1") == u8"48" && buff(i) == u8"48") {
      i = i - i320"1"
    }
    j = i320"0"
    while (j < i) {
      buffer(idx & mask) = buff(j)
      j = j + i320"1"
      idx = idx + u"1"
      r = r + u64"1"
    }
    return r
  }

  def printString(buffer: MS[U, U8], index: U, mask: U, s: String): U64 = {
    val u8is = conversions.String.toU8is(s)
    var i: Z = 0
    val size = s.size
    while (i < size) {
      buffer((index + Ext.z2u(i)) & mask) = u8is(i)
      i = i + 1
    }
    return conversions.Z.toU64(i)
  }

  def load(memory: MS[U, U8], offset: U, size: U): U = {
    var r = u"0"
    var i = u"0"
    while (i < size) {
      val b = Ext.z2u(conversions.U8.toZ(memory(offset + i)))
      r = r | (b << (i * u"8"))
      i = i + u"1"
    }
    return r
  }

  def store(memory: MS[U, U8], offset: U, size: U, value: U): Unit = {
    var i = u"0"
    while (i < size) {
      val b = conversions.Z.toU8(Ext.u2z((value >> (i * u"8")) & u"0xFF"))
      memory(offset + i) = b
      i = i + u"1"
    }
  }

  def printStackTrace(buffer: MS[U, U8], index: U, memory: MS[U, U8], mask: U, spSize: U, typeShaSize: U, locSize: U, sizeSize: U, sfCallerOffset: U): U64 = {
    var sfCaller = sfCallerOffset
    var r = u64"0"
    var idx = index
    while (sfCaller != u"0") {
      var offset = sfCaller + spSize - typeShaSize - sizeSize
      val sfLoc = conversions.Z.toU64(Ext.u2z(load(memory, offset, locSize)))
      offset = offset + locSize + typeShaSize
      val sfDescSize = load(memory, offset, sizeSize)
      offset = offset + sizeSize
      buffer(idx & mask) = u8"32"          // ' '
      buffer((idx + u"1") & mask) = u8"32" // ' '
      idx = idx + u"2"
      r = r + u64"2"
      var i = u"0"
      while (i < sfDescSize) {
        buffer((idx + i) & mask) = memory(offset + i)
        r = r + u64"1"
        i = i + u"1"
      }
      idx = idx + i
      val n = printU64(buffer, idx, mask, sfLoc)
      r = r + n
      idx = idx + Ext.z2u(conversions.U64.toZ(n))
      buffer(idx & mask) = u8"41"          // ')'
      buffer((idx + u"1") & mask) = u8"10" // '\n'
      r = r + u64"2"
      idx = idx + u"2"
      sfCaller = load(memory,  sfCaller - typeShaSize - sizeSize, spSize)
    }
    return r
  }

}