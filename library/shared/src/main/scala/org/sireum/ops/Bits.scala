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

package org.sireum.ops

import org.sireum._
import org.sireum.U1._
import org.sireum.U2._
import org.sireum.U3._
import org.sireum.U4._
import org.sireum.U5._
import org.sireum.U6._
import org.sireum.U7._
import org.sireum.U8._
import org.sireum.U9._
import org.sireum.U10._
import org.sireum.U11._
import org.sireum.U12._
import org.sireum.U13._
import org.sireum.U14._
import org.sireum.U15._
import org.sireum.U16._
import org.sireum.U17._
import org.sireum.U18._
import org.sireum.U19._
import org.sireum.U20._
import org.sireum.U21._
import org.sireum.U22._
import org.sireum.U23._
import org.sireum.U24._
import org.sireum.U25._
import org.sireum.U26._
import org.sireum.U27._
import org.sireum.U28._
import org.sireum.U29._
import org.sireum.U30._
import org.sireum.U31._
import org.sireum.U32._
import org.sireum.U33._
import org.sireum.U34._
import org.sireum.U35._
import org.sireum.U36._
import org.sireum.U37._
import org.sireum.U38._
import org.sireum.U39._
import org.sireum.U40._
import org.sireum.U41._
import org.sireum.U42._
import org.sireum.U43._
import org.sireum.U44._
import org.sireum.U45._
import org.sireum.U46._
import org.sireum.U47._
import org.sireum.U48._
import org.sireum.U49._
import org.sireum.U50._
import org.sireum.U51._
import org.sireum.U52._
import org.sireum.U53._
import org.sireum.U54._
import org.sireum.U55._
import org.sireum.U56._
import org.sireum.U57._
import org.sireum.U58._
import org.sireum.U59._
import org.sireum.U60._
import org.sireum.U61._
import org.sireum.U62._
import org.sireum.U63._
import org.sireum.U64._


object Bits {

  object Context {
    def create: Context = {
      return Context(0, 0, 0)
    }
  }

  @record class Context(var offset: Z,
                        var errorCode: Z,
                        var errorOffset: Z) {
    def hasError: B = {
      return errorCode != 0
    }

    def signalError(code: Z): Unit = {
      if (!hasError) {
        errorCode = code
        errorOffset = offset
      }
    }

    def skip(size: Z, n: Z, errorCode: Z): Unit = {
      if (offset + n > size) {
        signalError(errorCode)
        return
      }
      offset = offset + n
    }

    def updateErrorCode(code: Z): Unit = {
      errorCode = code
    }
  }

  object Reader {
    val INCOMPLETE_INPUT: Z = 1

    object IS {

      def bleB(input: ISZ[B], context: Context): B = {
        val offset = context.offset
        if (offset + 1 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return F
        }
        val r = input(offset)
        context.offset = offset + 1
        return r
      }

      def beBS(input: ISZ[B], context: Context, result: MSZ[B], size: Z): Unit = {
        bleRaw(input, context, result, size)
      }

      def leBS(input: ISZ[B], context: Context, result: MSZ[B], size: Z): Unit = {
        if (context.offset + size > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- size - 1 to 0 by -1) {
          result(i) = bleB(input, context)
        }
      }

      def bleRaw(input: ISZ[B], context: Context, result: MSZ[B], size: Z): Unit = {
        if (context.offset + size > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = bleB(input, context)
        }
      }

      def bleU1(input: ISZ[B], context: Context): U1 = {
        val offset = context.offset
        if (offset + 1 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u1"0"
        }
        val r = input(offset)
        context.offset = offset + 1
        return if (r) u1"1" else u1"0"
      }

      def bleU2(input: ISZ[B], context: Context): U2 = {
        val offset = context.offset
        if (offset + 2 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u2"0"
        }
        var r = u2"0"
        var mask = u2"1"
        for (i <- 0 until 1) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u2"1"
        }
        if (input(offset + 1)) {
          r = r | mask
        }
        context.offset = offset + 2
        return r
      }

      def bleU3(input: ISZ[B], context: Context): U3 = {
        val offset = context.offset
        if (offset + 3 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u3"0"
        }
        var r = u3"0"
        var mask = u3"1"
        for (i <- 0 until 2) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u3"1"
        }
        if (input(offset + 2)) {
          r = r | mask
        }
        context.offset = offset + 3
        return r
      }

      def bleU4(input: ISZ[B], context: Context): U4 = {
        val offset = context.offset
        if (offset + 4 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u4"0"
        }
        var r = u4"0"
        var mask = u4"1"
        for (i <- 0 until 3) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u4"1"
        }
        if (input(offset + 3)) {
          r = r | mask
        }
        context.offset = offset + 4
        return r
      }

      def bleU5(input: ISZ[B], context: Context): U5 = {
        val offset = context.offset
        if (offset + 5 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u5"0"
        }
        var r = u5"0"
        var mask = u5"1"
        for (i <- 0 until 4) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u5"1"
        }
        if (input(offset + 4)) {
          r = r | mask
        }
        context.offset = offset + 5
        return r
      }

      def bleU6(input: ISZ[B], context: Context): U6 = {
        val offset = context.offset
        if (offset + 6 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u6"0"
        }
        var r = u6"0"
        var mask = u6"1"
        for (i <- 0 until 5) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u6"1"
        }
        if (input(offset + 5)) {
          r = r | mask
        }
        context.offset = offset + 6
        return r
      }

      def bleU7(input: ISZ[B], context: Context): U7 = {
        val offset = context.offset
        if (offset + 7 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u7"0"
        }
        var r = u7"0"
        var mask = u7"1"
        for (i <- 0 until 6) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u7"1"
        }
        if (input(offset + 6)) {
          r = r | mask
        }
        context.offset = offset + 7
        return r
      }

      def beU8S(input: ISZ[B], context: Context, result: MSZ[U8], size: Z): Unit = {
        leU8S(input, context, result, size)
      }

      def beS8S(input: ISZ[B], context: Context, result: MSZ[S8], size: Z): Unit = {
        leS8S(input, context, result, size)
      }

      def beU16S(input: ISZ[B], context: Context, result: MSZ[U16], size: Z): Unit = {
        if (context.offset + size * 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beU16(input, context)
        }
      }

      def beS16S(input: ISZ[B], context: Context, result: MSZ[S16], size: Z): Unit = {
        if (context.offset + size * 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beS16(input, context)
        }
      }

      def beU32S(input: ISZ[B], context: Context, result: MSZ[U32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beU32(input, context)
        }
      }

      def beS32S(input: ISZ[B], context: Context, result: MSZ[S32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beS32(input, context)
        }
      }

      def beU64S(input: ISZ[B], context: Context, result: MSZ[U64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beU64(input, context)
        }
      }

      def beS64S(input: ISZ[B], context: Context, result: MSZ[S64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beS64(input, context)
        }
      }

      def beF32S(input: ISZ[B], context: Context, result: MSZ[F32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beF32(input, context)
        }
      }

      def beF64S(input: ISZ[B], context: Context, result: MSZ[F64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beF64(input, context)
        }
      }

      def leU8S(input: ISZ[B], context: Context, result: MSZ[U8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = bleU8(input, context)
        }
      }

      def leS8S(input: ISZ[B], context: Context, result: MSZ[S8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = bleS8(input, context)
        }
      }

      def leU16S(input: ISZ[B], context: Context, result: MSZ[U16], size: Z): Unit = {
        if (context.offset + size * 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leU16(input, context)
        }
      }

      def leS16S(input: ISZ[B], context: Context, result: MSZ[S16], size: Z): Unit = {
        if (context.offset + size * 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leS16(input, context)
        }
      }

      def leU32S(input: ISZ[B], context: Context, result: MSZ[U32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leU32(input, context)
        }
      }

      def leS32S(input: ISZ[B], context: Context, result: MSZ[S32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leS32(input, context)
        }
      }

      def leU64S(input: ISZ[B], context: Context, result: MSZ[U64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leU64(input, context)
        }
      }

      def leS64S(input: ISZ[B], context: Context, result: MSZ[S64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leS64(input, context)
        }
      }

      def leF32S(input: ISZ[B], context: Context, result: MSZ[F32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leF32(input, context)
        }
      }

      def leF64S(input: ISZ[B], context: Context, result: MSZ[F64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leF64(input, context)
        }
      }

      def bleU8(input: ISZ[B], context: Context): U8 = {
        val offset = context.offset
        if (offset + 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u8"0"
        }
        var r = u8"0"
        var mask = u8"1"
        for (i <- 0 until 7) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u8"1"
        }
        if (input(offset + 7)) {
          r = r | mask
        }
        context.offset = offset + 8
        return r
      }

      def bleS8(input: ISZ[B], context: Context): S8 = {
        return conversions.U8.toRawS8(bleU8(input, context))
      }

      // Slang script gen:
      /*
      for (i <- 9 to 15) {
        println(
          st"""      def beU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U16.toU$i(conversions.U${i - 8}.toU16(bleU${i - 8}(input, context))) << u$i"8")
              |        r = r | conversions.U16.toU$i(conversions.U8.toU16(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 17 to 24) {
        println(
          st"""      def beU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U32.toU$i(conversions.U${i - 16}.toU32(bleU${i - 16}(input, context))) << u$i"16")
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 25 to 31) {
        println(
          st"""      def beU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U32.toU$i(conversions.U${i - 24}.toU32(bleU${i - 24}(input, context))) << u$i"24")
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 33 to 40) {
        println(
          st"""      def beU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 32}.toU64(bleU${i - 32}(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 41 to 48) {
        println(
          st"""      def beU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 40}.toU64(bleU${i - 40}(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 49 to 56) {
        println(
          st"""      def beU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 48}.toU64(bleU${i - 48}(input, context))) << u$i"48")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 57 to 63) {
        println(
          st"""      def beU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 56}.toU64(bleU${i - 56}(input, context))) << u$i"56")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"48")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }
      */

      def beU9(input: ISZ[B], context: Context): U9 = {
        var r = u9"0"
        r = r | (conversions.U16.toU9(conversions.U1.toU16(bleU1(input, context))) << u9"8")
        r = r | conversions.U16.toU9(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u9"0"
        }
        return r
      }

      def beU10(input: ISZ[B], context: Context): U10 = {
        var r = u10"0"
        r = r | (conversions.U16.toU10(conversions.U2.toU16(bleU2(input, context))) << u10"8")
        r = r | conversions.U16.toU10(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u10"0"
        }
        return r
      }

      def beU11(input: ISZ[B], context: Context): U11 = {
        var r = u11"0"
        r = r | (conversions.U16.toU11(conversions.U3.toU16(bleU3(input, context))) << u11"8")
        r = r | conversions.U16.toU11(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u11"0"
        }
        return r
      }

      def beU12(input: ISZ[B], context: Context): U12 = {
        var r = u12"0"
        r = r | (conversions.U16.toU12(conversions.U4.toU16(bleU4(input, context))) << u12"8")
        r = r | conversions.U16.toU12(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u12"0"
        }
        return r
      }

      def beU13(input: ISZ[B], context: Context): U13 = {
        var r = u13"0"
        r = r | (conversions.U16.toU13(conversions.U5.toU16(bleU5(input, context))) << u13"8")
        r = r | conversions.U16.toU13(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u13"0"
        }
        return r
      }

      def beU14(input: ISZ[B], context: Context): U14 = {
        var r = u14"0"
        r = r | (conversions.U16.toU14(conversions.U6.toU16(bleU6(input, context))) << u14"8")
        r = r | conversions.U16.toU14(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u14"0"
        }
        return r
      }

      def beU15(input: ISZ[B], context: Context): U15 = {
        var r = u15"0"
        r = r | (conversions.U16.toU15(conversions.U7.toU16(bleU7(input, context))) << u15"8")
        r = r | conversions.U16.toU15(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u15"0"
        }
        return r
      }

      def beU16(input: ISZ[B], context: Context): U16 = {
        val r = (conversions.U8.toU16(bleU8(input, context)) << u16"8") |
          conversions.U8.toU16(bleU8(input, context))
        if (context.hasError) {
          return u16"0"
        }
        return r
      }

      def beS16(input: ISZ[B], context: Context): S16 = {
        return conversions.U16.toRawS16(beU16(input, context))
      }

      def beU17(input: ISZ[B], context: Context): U17 = {
        var r = u17"0"
        r = r | (conversions.U32.toU17(conversions.U1.toU32(bleU1(input, context))) << u17"16")
        r = r | (conversions.U32.toU17(conversions.U8.toU32(bleU8(input, context))) << u17"8")
        r = r | conversions.U32.toU17(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u17"0"
        }
        return r
      }

      def beU18(input: ISZ[B], context: Context): U18 = {
        var r = u18"0"
        r = r | (conversions.U32.toU18(conversions.U2.toU32(bleU2(input, context))) << u18"16")
        r = r | (conversions.U32.toU18(conversions.U8.toU32(bleU8(input, context))) << u18"8")
        r = r | conversions.U32.toU18(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u18"0"
        }
        return r
      }

      def beU19(input: ISZ[B], context: Context): U19 = {
        var r = u19"0"
        r = r | (conversions.U32.toU19(conversions.U3.toU32(bleU3(input, context))) << u19"16")
        r = r | (conversions.U32.toU19(conversions.U8.toU32(bleU8(input, context))) << u19"8")
        r = r | conversions.U32.toU19(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u19"0"
        }
        return r
      }

      def beU20(input: ISZ[B], context: Context): U20 = {
        var r = u20"0"
        r = r | (conversions.U32.toU20(conversions.U4.toU32(bleU4(input, context))) << u20"16")
        r = r | (conversions.U32.toU20(conversions.U8.toU32(bleU8(input, context))) << u20"8")
        r = r | conversions.U32.toU20(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u20"0"
        }
        return r
      }

      def beU21(input: ISZ[B], context: Context): U21 = {
        var r = u21"0"
        r = r | (conversions.U32.toU21(conversions.U5.toU32(bleU5(input, context))) << u21"16")
        r = r | (conversions.U32.toU21(conversions.U8.toU32(bleU8(input, context))) << u21"8")
        r = r | conversions.U32.toU21(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u21"0"
        }
        return r
      }

      def beU22(input: ISZ[B], context: Context): U22 = {
        var r = u22"0"
        r = r | (conversions.U32.toU22(conversions.U6.toU32(bleU6(input, context))) << u22"16")
        r = r | (conversions.U32.toU22(conversions.U8.toU32(bleU8(input, context))) << u22"8")
        r = r | conversions.U32.toU22(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u22"0"
        }
        return r
      }

      def beU23(input: ISZ[B], context: Context): U23 = {
        var r = u23"0"
        r = r | (conversions.U32.toU23(conversions.U7.toU32(bleU7(input, context))) << u23"16")
        r = r | (conversions.U32.toU23(conversions.U8.toU32(bleU8(input, context))) << u23"8")
        r = r | conversions.U32.toU23(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u23"0"
        }
        return r
      }

      def beU24(input: ISZ[B], context: Context): U24 = {
        var r = u24"0"
        r = r | (conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context))) << u24"16")
        r = r | (conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context))) << u24"8")
        r = r | conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u24"0"
        }
        return r
      }

      def beU25(input: ISZ[B], context: Context): U25 = {
        var r = u25"0"
        r = r | (conversions.U32.toU25(conversions.U1.toU32(bleU1(input, context))) << u25"24")
        r = r | (conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context))) << u25"16")
        r = r | (conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context))) << u25"8")
        r = r | conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u25"0"
        }
        return r
      }

      def beU26(input: ISZ[B], context: Context): U26 = {
        var r = u26"0"
        r = r | (conversions.U32.toU26(conversions.U2.toU32(bleU2(input, context))) << u26"24")
        r = r | (conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context))) << u26"16")
        r = r | (conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context))) << u26"8")
        r = r | conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u26"0"
        }
        return r
      }

      def beU27(input: ISZ[B], context: Context): U27 = {
        var r = u27"0"
        r = r | (conversions.U32.toU27(conversions.U3.toU32(bleU3(input, context))) << u27"24")
        r = r | (conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context))) << u27"16")
        r = r | (conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context))) << u27"8")
        r = r | conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u27"0"
        }
        return r
      }

      def beU28(input: ISZ[B], context: Context): U28 = {
        var r = u28"0"
        r = r | (conversions.U32.toU28(conversions.U4.toU32(bleU4(input, context))) << u28"24")
        r = r | (conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context))) << u28"16")
        r = r | (conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context))) << u28"8")
        r = r | conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u28"0"
        }
        return r
      }

      def beU29(input: ISZ[B], context: Context): U29 = {
        var r = u29"0"
        r = r | (conversions.U32.toU29(conversions.U5.toU32(bleU5(input, context))) << u29"24")
        r = r | (conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context))) << u29"16")
        r = r | (conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context))) << u29"8")
        r = r | conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u29"0"
        }
        return r
      }

      def beU30(input: ISZ[B], context: Context): U30 = {
        var r = u30"0"
        r = r | (conversions.U32.toU30(conversions.U6.toU32(bleU6(input, context))) << u30"24")
        r = r | (conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context))) << u30"16")
        r = r | (conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context))) << u30"8")
        r = r | conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u30"0"
        }
        return r
      }

      def beU31(input: ISZ[B], context: Context): U31 = {
        var r = u31"0"
        r = r | (conversions.U32.toU31(conversions.U7.toU32(bleU7(input, context))) << u31"24")
        r = r | (conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context))) << u31"16")
        r = r | (conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context))) << u31"8")
        r = r | conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u31"0"
        }
        return r
      }

      def beU32(input: ISZ[B], context: Context): U32 = {
        val r = (conversions.U8.toU32(bleU8(input, context)) << u32"24") |
          (conversions.U8.toU32(bleU8(input, context)) << u32"16") |
          (conversions.U8.toU32(bleU8(input, context)) << u32"8") |
          conversions.U8.toU32(bleU8(input, context))
        if (context.hasError) {
          return u32"0"
        }
        return r
      }

      def beS32(input: ISZ[B], context: Context): S32 = {
        return conversions.U32.toRawS32(beU32(input, context))
      }

      def beU33(input: ISZ[B], context: Context): U33 = {
        var r = u33"0"
        r = r | (conversions.U64.toU33(conversions.U1.toU64(bleU1(input, context))) << u33"32")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"24")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"16")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"8")
        r = r | conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u33"0"
        }
        return r
      }

      def beU34(input: ISZ[B], context: Context): U34 = {
        var r = u34"0"
        r = r | (conversions.U64.toU34(conversions.U2.toU64(bleU2(input, context))) << u34"32")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"24")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"16")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"8")
        r = r | conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u34"0"
        }
        return r
      }

      def beU35(input: ISZ[B], context: Context): U35 = {
        var r = u35"0"
        r = r | (conversions.U64.toU35(conversions.U3.toU64(bleU3(input, context))) << u35"32")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"24")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"16")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"8")
        r = r | conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u35"0"
        }
        return r
      }

      def beU36(input: ISZ[B], context: Context): U36 = {
        var r = u36"0"
        r = r | (conversions.U64.toU36(conversions.U4.toU64(bleU4(input, context))) << u36"32")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"24")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"16")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"8")
        r = r | conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u36"0"
        }
        return r
      }

      def beU37(input: ISZ[B], context: Context): U37 = {
        var r = u37"0"
        r = r | (conversions.U64.toU37(conversions.U5.toU64(bleU5(input, context))) << u37"32")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"24")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"16")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"8")
        r = r | conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u37"0"
        }
        return r
      }

      def beU38(input: ISZ[B], context: Context): U38 = {
        var r = u38"0"
        r = r | (conversions.U64.toU38(conversions.U6.toU64(bleU6(input, context))) << u38"32")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"24")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"16")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"8")
        r = r | conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u38"0"
        }
        return r
      }

      def beU39(input: ISZ[B], context: Context): U39 = {
        var r = u39"0"
        r = r | (conversions.U64.toU39(conversions.U7.toU64(bleU7(input, context))) << u39"32")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"24")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"16")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"8")
        r = r | conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u39"0"
        }
        return r
      }

      def beU40(input: ISZ[B], context: Context): U40 = {
        var r = u40"0"
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"32")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"24")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"16")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"8")
        r = r | conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u40"0"
        }
        return r
      }

      def beU41(input: ISZ[B], context: Context): U41 = {
        var r = u41"0"
        r = r | (conversions.U64.toU41(conversions.U1.toU64(bleU1(input, context))) << u41"40")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"32")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"24")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"16")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"8")
        r = r | conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u41"0"
        }
        return r
      }

      def beU42(input: ISZ[B], context: Context): U42 = {
        var r = u42"0"
        r = r | (conversions.U64.toU42(conversions.U2.toU64(bleU2(input, context))) << u42"40")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"32")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"24")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"16")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"8")
        r = r | conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u42"0"
        }
        return r
      }

      def beU43(input: ISZ[B], context: Context): U43 = {
        var r = u43"0"
        r = r | (conversions.U64.toU43(conversions.U3.toU64(bleU3(input, context))) << u43"40")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"32")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"24")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"16")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"8")
        r = r | conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u43"0"
        }
        return r
      }

      def beU44(input: ISZ[B], context: Context): U44 = {
        var r = u44"0"
        r = r | (conversions.U64.toU44(conversions.U4.toU64(bleU4(input, context))) << u44"40")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"32")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"24")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"16")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"8")
        r = r | conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u44"0"
        }
        return r
      }

      def beU45(input: ISZ[B], context: Context): U45 = {
        var r = u45"0"
        r = r | (conversions.U64.toU45(conversions.U5.toU64(bleU5(input, context))) << u45"40")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"32")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"24")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"16")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"8")
        r = r | conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u45"0"
        }
        return r
      }

      def beU46(input: ISZ[B], context: Context): U46 = {
        var r = u46"0"
        r = r | (conversions.U64.toU46(conversions.U6.toU64(bleU6(input, context))) << u46"40")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"32")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"24")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"16")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"8")
        r = r | conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u46"0"
        }
        return r
      }

      def beU47(input: ISZ[B], context: Context): U47 = {
        var r = u47"0"
        r = r | (conversions.U64.toU47(conversions.U7.toU64(bleU7(input, context))) << u47"40")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"32")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"24")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"16")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"8")
        r = r | conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u47"0"
        }
        return r
      }

      def beU48(input: ISZ[B], context: Context): U48 = {
        var r = u48"0"
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"40")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"32")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"24")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"16")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"8")
        r = r | conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u48"0"
        }
        return r
      }

      def beU49(input: ISZ[B], context: Context): U49 = {
        var r = u49"0"
        r = r | (conversions.U64.toU49(conversions.U1.toU64(bleU1(input, context))) << u49"48")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"40")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"32")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"24")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"16")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"8")
        r = r | conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u49"0"
        }
        return r
      }

      def beU50(input: ISZ[B], context: Context): U50 = {
        var r = u50"0"
        r = r | (conversions.U64.toU50(conversions.U2.toU64(bleU2(input, context))) << u50"48")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"40")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"32")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"24")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"16")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"8")
        r = r | conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u50"0"
        }
        return r
      }

      def beU51(input: ISZ[B], context: Context): U51 = {
        var r = u51"0"
        r = r | (conversions.U64.toU51(conversions.U3.toU64(bleU3(input, context))) << u51"48")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"40")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"32")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"24")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"16")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"8")
        r = r | conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u51"0"
        }
        return r
      }

      def beU52(input: ISZ[B], context: Context): U52 = {
        var r = u52"0"
        r = r | (conversions.U64.toU52(conversions.U4.toU64(bleU4(input, context))) << u52"48")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"40")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"32")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"24")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"16")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"8")
        r = r | conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u52"0"
        }
        return r
      }

      def beU53(input: ISZ[B], context: Context): U53 = {
        var r = u53"0"
        r = r | (conversions.U64.toU53(conversions.U5.toU64(bleU5(input, context))) << u53"48")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"40")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"32")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"24")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"16")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"8")
        r = r | conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u53"0"
        }
        return r
      }

      def beU54(input: ISZ[B], context: Context): U54 = {
        var r = u54"0"
        r = r | (conversions.U64.toU54(conversions.U6.toU64(bleU6(input, context))) << u54"48")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"40")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"32")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"24")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"16")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"8")
        r = r | conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u54"0"
        }
        return r
      }

      def beU55(input: ISZ[B], context: Context): U55 = {
        var r = u55"0"
        r = r | (conversions.U64.toU55(conversions.U7.toU64(bleU7(input, context))) << u55"48")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"40")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"32")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"24")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"16")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"8")
        r = r | conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u55"0"
        }
        return r
      }

      def beU56(input: ISZ[B], context: Context): U56 = {
        var r = u56"0"
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"48")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"40")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"32")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"24")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"16")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"8")
        r = r | conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u56"0"
        }
        return r
      }

      def beU57(input: ISZ[B], context: Context): U57 = {
        var r = u57"0"
        r = r | (conversions.U64.toU57(conversions.U1.toU64(bleU1(input, context))) << u57"56")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"48")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"40")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"32")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"24")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"16")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"8")
        r = r | conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u57"0"
        }
        return r
      }

      def beU58(input: ISZ[B], context: Context): U58 = {
        var r = u58"0"
        r = r | (conversions.U64.toU58(conversions.U2.toU64(bleU2(input, context))) << u58"56")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"48")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"40")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"32")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"24")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"16")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"8")
        r = r | conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u58"0"
        }
        return r
      }

      def beU59(input: ISZ[B], context: Context): U59 = {
        var r = u59"0"
        r = r | (conversions.U64.toU59(conversions.U3.toU64(bleU3(input, context))) << u59"56")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"48")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"40")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"32")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"24")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"16")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"8")
        r = r | conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u59"0"
        }
        return r
      }

      def beU60(input: ISZ[B], context: Context): U60 = {
        var r = u60"0"
        r = r | (conversions.U64.toU60(conversions.U4.toU64(bleU4(input, context))) << u60"56")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"48")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"40")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"32")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"24")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"16")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"8")
        r = r | conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u60"0"
        }
        return r
      }

      def beU61(input: ISZ[B], context: Context): U61 = {
        var r = u61"0"
        r = r | (conversions.U64.toU61(conversions.U5.toU64(bleU5(input, context))) << u61"56")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"48")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"40")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"32")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"24")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"16")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"8")
        r = r | conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u61"0"
        }
        return r
      }

      def beU62(input: ISZ[B], context: Context): U62 = {
        var r = u62"0"
        r = r | (conversions.U64.toU62(conversions.U6.toU64(bleU6(input, context))) << u62"56")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"48")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"40")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"32")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"24")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"16")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"8")
        r = r | conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u62"0"
        }
        return r
      }

      def beU63(input: ISZ[B], context: Context): U63 = {
        var r = u63"0"
        r = r | (conversions.U64.toU63(conversions.U7.toU64(bleU7(input, context))) << u63"56")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"48")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"40")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"32")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"24")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"16")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"8")
        r = r | conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u63"0"
        }
        return r
      }

      def beU64(input: ISZ[B], context: Context): U64 = {
        val r = (conversions.U8.toU64(bleU8(input, context)) << u64"56") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"48") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"40") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"32") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"24") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"16") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"8") |
          conversions.U8.toU64(bleU8(input, context))
        if (context.hasError) {
          return u64"0"
        }
        return r
      }

      def beS64(input: ISZ[B], context: Context): S64 = {
        return conversions.U64.toRawS64(beU64(input, context))
      }

      def beF32(input: ISZ[B], context: Context): F32 = {
        return conversions.U32.toRawF32(beU32(input, context))
      }

      def beF64(input: ISZ[B], context: Context): F64 = {
        return conversions.U64.toRawF64(beU64(input, context))
      }

      // Slang script gen:
      /*
      for (i <- 9 to 15) {
        println(
          st"""      def leU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U16.toU$i(conversions.U8.toU16(bleU8(input, context)))
              |        r = r | (conversions.U16.toU$i(conversions.U${i - 8}.toU16(bleU${i - 8}(input, context))) << u$i"8")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }
      
      for (i <- 17 to 24) {
        println(
          st"""      def leU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context)))
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U32.toU$i(conversions.U${i - 16}.toU32(bleU${i - 16}(input, context))) << u$i"16")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }
      
      for (i <- 25 to 31) {
        println(
          st"""      def leU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context)))
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U32.toU$i(conversions.U${i - 24}.toU32(bleU${i - 24}(input, context))) << u$i"24")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }
      
      for (i <- 33 to 40) {
        println(
          st"""      def leU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 32}.toU64(bleU${i - 32}(input, context))) << u$i"32")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }
      
      for (i <- 41 to 48) {
        println(
          st"""      def leU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 40}.toU64(bleU${i - 40}(input, context))) << u$i"40")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }
      
      for (i <- 49 to 56) {
        println(
          st"""      def leU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 48}.toU64(bleU${i - 48}(input, context))) << u$i"48")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }
      
      for (i <- 57 to 63) {
        println(
          st"""      def leU$i(input: ISZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"48")
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 56}.toU64(bleU${i - 56}(input, context))) << u$i"56")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

       */

      def leU9(input: ISZ[B], context: Context): U9 = {
        var r = u9"0"
        r = r | conversions.U16.toU9(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU9(conversions.U1.toU16(bleU1(input, context))) << u9"8")
        if (context.hasError) {
          return u9"0"
        }
        return r
      }

      def leU10(input: ISZ[B], context: Context): U10 = {
        var r = u10"0"
        r = r | conversions.U16.toU10(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU10(conversions.U2.toU16(bleU2(input, context))) << u10"8")
        if (context.hasError) {
          return u10"0"
        }
        return r
      }

      def leU11(input: ISZ[B], context: Context): U11 = {
        var r = u11"0"
        r = r | conversions.U16.toU11(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU11(conversions.U3.toU16(bleU3(input, context))) << u11"8")
        if (context.hasError) {
          return u11"0"
        }
        return r
      }

      def leU12(input: ISZ[B], context: Context): U12 = {
        var r = u12"0"
        r = r | conversions.U16.toU12(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU12(conversions.U4.toU16(bleU4(input, context))) << u12"8")
        if (context.hasError) {
          return u12"0"
        }
        return r
      }

      def leU13(input: ISZ[B], context: Context): U13 = {
        var r = u13"0"
        r = r | conversions.U16.toU13(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU13(conversions.U5.toU16(bleU5(input, context))) << u13"8")
        if (context.hasError) {
          return u13"0"
        }
        return r
      }

      def leU14(input: ISZ[B], context: Context): U14 = {
        var r = u14"0"
        r = r | conversions.U16.toU14(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU14(conversions.U6.toU16(bleU6(input, context))) << u14"8")
        if (context.hasError) {
          return u14"0"
        }
        return r
      }

      def leU15(input: ISZ[B], context: Context): U15 = {
        var r = u15"0"
        r = r | conversions.U16.toU15(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU15(conversions.U7.toU16(bleU7(input, context))) << u15"8")
        if (context.hasError) {
          return u15"0"
        }
        return r
      }

      def leU16(input: ISZ[B], context: Context): U16 = {
        val r = conversions.U8.toU16(bleU8(input, context)) |
          (conversions.U8.toU16(bleU8(input, context)) << u16"8")
        if (context.hasError) {
          return u16"0"
        }
        return r
      }

      def leS16(input: ISZ[B], context: Context): S16 = {
        return conversions.U16.toRawS16(leU16(input, context))
      }

      def leU17(input: ISZ[B], context: Context): U17 = {
        var r = u17"0"
        r = r | conversions.U32.toU17(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU17(conversions.U8.toU32(bleU8(input, context))) << u17"8")
        r = r | (conversions.U32.toU17(conversions.U1.toU32(bleU1(input, context))) << u17"16")
        if (context.hasError) {
          return u17"0"
        }
        return r
      }

      def leU18(input: ISZ[B], context: Context): U18 = {
        var r = u18"0"
        r = r | conversions.U32.toU18(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU18(conversions.U8.toU32(bleU8(input, context))) << u18"8")
        r = r | (conversions.U32.toU18(conversions.U2.toU32(bleU2(input, context))) << u18"16")
        if (context.hasError) {
          return u18"0"
        }
        return r
      }

      def leU19(input: ISZ[B], context: Context): U19 = {
        var r = u19"0"
        r = r | conversions.U32.toU19(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU19(conversions.U8.toU32(bleU8(input, context))) << u19"8")
        r = r | (conversions.U32.toU19(conversions.U3.toU32(bleU3(input, context))) << u19"16")
        if (context.hasError) {
          return u19"0"
        }
        return r
      }

      def leU20(input: ISZ[B], context: Context): U20 = {
        var r = u20"0"
        r = r | conversions.U32.toU20(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU20(conversions.U8.toU32(bleU8(input, context))) << u20"8")
        r = r | (conversions.U32.toU20(conversions.U4.toU32(bleU4(input, context))) << u20"16")
        if (context.hasError) {
          return u20"0"
        }
        return r
      }

      def leU21(input: ISZ[B], context: Context): U21 = {
        var r = u21"0"
        r = r | conversions.U32.toU21(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU21(conversions.U8.toU32(bleU8(input, context))) << u21"8")
        r = r | (conversions.U32.toU21(conversions.U5.toU32(bleU5(input, context))) << u21"16")
        if (context.hasError) {
          return u21"0"
        }
        return r
      }

      def leU22(input: ISZ[B], context: Context): U22 = {
        var r = u22"0"
        r = r | conversions.U32.toU22(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU22(conversions.U8.toU32(bleU8(input, context))) << u22"8")
        r = r | (conversions.U32.toU22(conversions.U6.toU32(bleU6(input, context))) << u22"16")
        if (context.hasError) {
          return u22"0"
        }
        return r
      }

      def leU23(input: ISZ[B], context: Context): U23 = {
        var r = u23"0"
        r = r | conversions.U32.toU23(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU23(conversions.U8.toU32(bleU8(input, context))) << u23"8")
        r = r | (conversions.U32.toU23(conversions.U7.toU32(bleU7(input, context))) << u23"16")
        if (context.hasError) {
          return u23"0"
        }
        return r
      }

      def leU24(input: ISZ[B], context: Context): U24 = {
        var r = u24"0"
        r = r | conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context))) << u24"8")
        r = r | (conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context))) << u24"16")
        if (context.hasError) {
          return u24"0"
        }
        return r
      }

      def leU25(input: ISZ[B], context: Context): U25 = {
        var r = u25"0"
        r = r | conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context))) << u25"8")
        r = r | (conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context))) << u25"16")
        r = r | (conversions.U32.toU25(conversions.U1.toU32(bleU1(input, context))) << u25"24")
        if (context.hasError) {
          return u25"0"
        }
        return r
      }

      def leU26(input: ISZ[B], context: Context): U26 = {
        var r = u26"0"
        r = r | conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context))) << u26"8")
        r = r | (conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context))) << u26"16")
        r = r | (conversions.U32.toU26(conversions.U2.toU32(bleU2(input, context))) << u26"24")
        if (context.hasError) {
          return u26"0"
        }
        return r
      }

      def leU27(input: ISZ[B], context: Context): U27 = {
        var r = u27"0"
        r = r | conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context))) << u27"8")
        r = r | (conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context))) << u27"16")
        r = r | (conversions.U32.toU27(conversions.U3.toU32(bleU3(input, context))) << u27"24")
        if (context.hasError) {
          return u27"0"
        }
        return r
      }

      def leU28(input: ISZ[B], context: Context): U28 = {
        var r = u28"0"
        r = r | conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context))) << u28"8")
        r = r | (conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context))) << u28"16")
        r = r | (conversions.U32.toU28(conversions.U4.toU32(bleU4(input, context))) << u28"24")
        if (context.hasError) {
          return u28"0"
        }
        return r
      }

      def leU29(input: ISZ[B], context: Context): U29 = {
        var r = u29"0"
        r = r | conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context))) << u29"8")
        r = r | (conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context))) << u29"16")
        r = r | (conversions.U32.toU29(conversions.U5.toU32(bleU5(input, context))) << u29"24")
        if (context.hasError) {
          return u29"0"
        }
        return r
      }

      def leU30(input: ISZ[B], context: Context): U30 = {
        var r = u30"0"
        r = r | conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context))) << u30"8")
        r = r | (conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context))) << u30"16")
        r = r | (conversions.U32.toU30(conversions.U6.toU32(bleU6(input, context))) << u30"24")
        if (context.hasError) {
          return u30"0"
        }
        return r
      }

      def leU31(input: ISZ[B], context: Context): U31 = {
        var r = u31"0"
        r = r | conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context))) << u31"8")
        r = r | (conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context))) << u31"16")
        r = r | (conversions.U32.toU31(conversions.U7.toU32(bleU7(input, context))) << u31"24")
        if (context.hasError) {
          return u31"0"
        }
        return r
      }

      def leU32(input: ISZ[B], context: Context): U32 = {
        val r = conversions.U8.toU32(bleU8(input, context)) |
          (conversions.U8.toU32(bleU8(input, context)) << u32"8") |
          (conversions.U8.toU32(bleU8(input, context)) << u32"16") |
          (conversions.U8.toU32(bleU8(input, context)) << u32"24")
        if (context.hasError) {
          return u32"0"
        }
        return r
      }

      def leS32(input: ISZ[B], context: Context): S32 = {
        return conversions.U32.toRawS32(leU32(input, context))
      }

      def leU33(input: ISZ[B], context: Context): U33 = {
        var r = u33"0"
        r = r | conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"8")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"16")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"24")
        r = r | (conversions.U64.toU33(conversions.U1.toU64(bleU1(input, context))) << u33"32")
        if (context.hasError) {
          return u33"0"
        }
        return r
      }

      def leU34(input: ISZ[B], context: Context): U34 = {
        var r = u34"0"
        r = r | conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"8")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"16")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"24")
        r = r | (conversions.U64.toU34(conversions.U2.toU64(bleU2(input, context))) << u34"32")
        if (context.hasError) {
          return u34"0"
        }
        return r
      }

      def leU35(input: ISZ[B], context: Context): U35 = {
        var r = u35"0"
        r = r | conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"8")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"16")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"24")
        r = r | (conversions.U64.toU35(conversions.U3.toU64(bleU3(input, context))) << u35"32")
        if (context.hasError) {
          return u35"0"
        }
        return r
      }

      def leU36(input: ISZ[B], context: Context): U36 = {
        var r = u36"0"
        r = r | conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"8")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"16")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"24")
        r = r | (conversions.U64.toU36(conversions.U4.toU64(bleU4(input, context))) << u36"32")
        if (context.hasError) {
          return u36"0"
        }
        return r
      }

      def leU37(input: ISZ[B], context: Context): U37 = {
        var r = u37"0"
        r = r | conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"8")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"16")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"24")
        r = r | (conversions.U64.toU37(conversions.U5.toU64(bleU5(input, context))) << u37"32")
        if (context.hasError) {
          return u37"0"
        }
        return r
      }

      def leU38(input: ISZ[B], context: Context): U38 = {
        var r = u38"0"
        r = r | conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"8")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"16")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"24")
        r = r | (conversions.U64.toU38(conversions.U6.toU64(bleU6(input, context))) << u38"32")
        if (context.hasError) {
          return u38"0"
        }
        return r
      }

      def leU39(input: ISZ[B], context: Context): U39 = {
        var r = u39"0"
        r = r | conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"8")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"16")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"24")
        r = r | (conversions.U64.toU39(conversions.U7.toU64(bleU7(input, context))) << u39"32")
        if (context.hasError) {
          return u39"0"
        }
        return r
      }

      def leU40(input: ISZ[B], context: Context): U40 = {
        var r = u40"0"
        r = r | conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"8")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"16")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"24")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"32")
        if (context.hasError) {
          return u40"0"
        }
        return r
      }

      def leU41(input: ISZ[B], context: Context): U41 = {
        var r = u41"0"
        r = r | conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"8")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"16")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"24")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"32")
        r = r | (conversions.U64.toU41(conversions.U1.toU64(bleU1(input, context))) << u41"40")
        if (context.hasError) {
          return u41"0"
        }
        return r
      }

      def leU42(input: ISZ[B], context: Context): U42 = {
        var r = u42"0"
        r = r | conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"8")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"16")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"24")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"32")
        r = r | (conversions.U64.toU42(conversions.U2.toU64(bleU2(input, context))) << u42"40")
        if (context.hasError) {
          return u42"0"
        }
        return r
      }

      def leU43(input: ISZ[B], context: Context): U43 = {
        var r = u43"0"
        r = r | conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"8")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"16")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"24")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"32")
        r = r | (conversions.U64.toU43(conversions.U3.toU64(bleU3(input, context))) << u43"40")
        if (context.hasError) {
          return u43"0"
        }
        return r
      }

      def leU44(input: ISZ[B], context: Context): U44 = {
        var r = u44"0"
        r = r | conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"8")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"16")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"24")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"32")
        r = r | (conversions.U64.toU44(conversions.U4.toU64(bleU4(input, context))) << u44"40")
        if (context.hasError) {
          return u44"0"
        }
        return r
      }

      def leU45(input: ISZ[B], context: Context): U45 = {
        var r = u45"0"
        r = r | conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"8")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"16")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"24")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"32")
        r = r | (conversions.U64.toU45(conversions.U5.toU64(bleU5(input, context))) << u45"40")
        if (context.hasError) {
          return u45"0"
        }
        return r
      }

      def leU46(input: ISZ[B], context: Context): U46 = {
        var r = u46"0"
        r = r | conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"8")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"16")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"24")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"32")
        r = r | (conversions.U64.toU46(conversions.U6.toU64(bleU6(input, context))) << u46"40")
        if (context.hasError) {
          return u46"0"
        }
        return r
      }

      def leU47(input: ISZ[B], context: Context): U47 = {
        var r = u47"0"
        r = r | conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"8")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"16")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"24")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"32")
        r = r | (conversions.U64.toU47(conversions.U7.toU64(bleU7(input, context))) << u47"40")
        if (context.hasError) {
          return u47"0"
        }
        return r
      }

      def leU48(input: ISZ[B], context: Context): U48 = {
        var r = u48"0"
        r = r | conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"8")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"16")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"24")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"32")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"40")
        if (context.hasError) {
          return u48"0"
        }
        return r
      }

      def leU49(input: ISZ[B], context: Context): U49 = {
        var r = u49"0"
        r = r | conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"8")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"16")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"24")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"32")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"40")
        r = r | (conversions.U64.toU49(conversions.U1.toU64(bleU1(input, context))) << u49"48")
        if (context.hasError) {
          return u49"0"
        }
        return r
      }

      def leU50(input: ISZ[B], context: Context): U50 = {
        var r = u50"0"
        r = r | conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"8")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"16")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"24")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"32")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"40")
        r = r | (conversions.U64.toU50(conversions.U2.toU64(bleU2(input, context))) << u50"48")
        if (context.hasError) {
          return u50"0"
        }
        return r
      }

      def leU51(input: ISZ[B], context: Context): U51 = {
        var r = u51"0"
        r = r | conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"8")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"16")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"24")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"32")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"40")
        r = r | (conversions.U64.toU51(conversions.U3.toU64(bleU3(input, context))) << u51"48")
        if (context.hasError) {
          return u51"0"
        }
        return r
      }

      def leU52(input: ISZ[B], context: Context): U52 = {
        var r = u52"0"
        r = r | conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"8")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"16")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"24")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"32")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"40")
        r = r | (conversions.U64.toU52(conversions.U4.toU64(bleU4(input, context))) << u52"48")
        if (context.hasError) {
          return u52"0"
        }
        return r
      }

      def leU53(input: ISZ[B], context: Context): U53 = {
        var r = u53"0"
        r = r | conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"8")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"16")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"24")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"32")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"40")
        r = r | (conversions.U64.toU53(conversions.U5.toU64(bleU5(input, context))) << u53"48")
        if (context.hasError) {
          return u53"0"
        }
        return r
      }

      def leU54(input: ISZ[B], context: Context): U54 = {
        var r = u54"0"
        r = r | conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"8")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"16")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"24")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"32")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"40")
        r = r | (conversions.U64.toU54(conversions.U6.toU64(bleU6(input, context))) << u54"48")
        if (context.hasError) {
          return u54"0"
        }
        return r
      }

      def leU55(input: ISZ[B], context: Context): U55 = {
        var r = u55"0"
        r = r | conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"8")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"16")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"24")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"32")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"40")
        r = r | (conversions.U64.toU55(conversions.U7.toU64(bleU7(input, context))) << u55"48")
        if (context.hasError) {
          return u55"0"
        }
        return r
      }

      def leU56(input: ISZ[B], context: Context): U56 = {
        var r = u56"0"
        r = r | conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"8")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"16")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"24")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"32")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"40")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"48")
        if (context.hasError) {
          return u56"0"
        }
        return r
      }

      def leU57(input: ISZ[B], context: Context): U57 = {
        var r = u57"0"
        r = r | conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"8")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"16")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"24")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"32")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"40")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"48")
        r = r | (conversions.U64.toU57(conversions.U1.toU64(bleU1(input, context))) << u57"56")
        if (context.hasError) {
          return u57"0"
        }
        return r
      }

      def leU58(input: ISZ[B], context: Context): U58 = {
        var r = u58"0"
        r = r | conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"8")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"16")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"24")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"32")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"40")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"48")
        r = r | (conversions.U64.toU58(conversions.U2.toU64(bleU2(input, context))) << u58"56")
        if (context.hasError) {
          return u58"0"
        }
        return r
      }

      def leU59(input: ISZ[B], context: Context): U59 = {
        var r = u59"0"
        r = r | conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"8")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"16")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"24")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"32")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"40")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"48")
        r = r | (conversions.U64.toU59(conversions.U3.toU64(bleU3(input, context))) << u59"56")
        if (context.hasError) {
          return u59"0"
        }
        return r
      }

      def leU60(input: ISZ[B], context: Context): U60 = {
        var r = u60"0"
        r = r | conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"8")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"16")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"24")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"32")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"40")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"48")
        r = r | (conversions.U64.toU60(conversions.U4.toU64(bleU4(input, context))) << u60"56")
        if (context.hasError) {
          return u60"0"
        }
        return r
      }

      def leU61(input: ISZ[B], context: Context): U61 = {
        var r = u61"0"
        r = r | conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"8")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"16")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"24")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"32")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"40")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"48")
        r = r | (conversions.U64.toU61(conversions.U5.toU64(bleU5(input, context))) << u61"56")
        if (context.hasError) {
          return u61"0"
        }
        return r
      }

      def leU62(input: ISZ[B], context: Context): U62 = {
        var r = u62"0"
        r = r | conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"8")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"16")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"24")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"32")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"40")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"48")
        r = r | (conversions.U64.toU62(conversions.U6.toU64(bleU6(input, context))) << u62"56")
        if (context.hasError) {
          return u62"0"
        }
        return r
      }

      def leU63(input: ISZ[B], context: Context): U63 = {
        var r = u63"0"
        r = r | conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"8")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"16")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"24")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"32")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"40")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"48")
        r = r | (conversions.U64.toU63(conversions.U7.toU64(bleU7(input, context))) << u63"56")
        if (context.hasError) {
          return u63"0"
        }
        return r
      }

      def leU64(input: ISZ[B], context: Context): U64 = {
        val r = conversions.U8.toU64(bleU8(input, context)) |
          (conversions.U8.toU64(bleU8(input, context)) << u64"8") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"16") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"24") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"32") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"40") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"48") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"56")
        if (context.hasError) {
          return u64"0"
        }
        return r
      }

      def leS64(input: ISZ[B], context: Context): S64 = {
        return conversions.U64.toRawS64(leU64(input, context))
      }

      def leF32(input: ISZ[B], context: Context): F32 = {
        return conversions.U32.toRawF32(leU32(input, context))
      }

      def leF64(input: ISZ[B], context: Context): F64 = {
        return conversions.U64.toRawF64(leU64(input, context))
      }
    }

    object MS {

      def bleB(input: MSZ[B], context: Context): B = {
        val offset = context.offset
        if (offset + 1 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return F
        }
        val r = input(offset)
        context.offset = offset + 1
        return r
      }

      def beBS(input: MSZ[B], context: Context, result: MSZ[B], size: Z): Unit = {
        bleRaw(input, context, result, size)
      }

      def leBS(input: MSZ[B], context: Context, result: MSZ[B], size: Z): Unit = {
        if (context.offset + size > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- size - 1 to 0 by -1) {
          result(i) = bleB(input, context)
        }
      }

      def bleRaw(input: MSZ[B], context: Context, result: MSZ[B], size: Z): Unit = {
        if (context.offset + size > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = bleB(input, context)
        }
      }

      def bleU1(input: MSZ[B], context: Context): U1 = {
        val offset = context.offset
        if (offset + 1 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u1"0"
        }
        val r = input(offset)
        context.offset = offset + 1
        return if (r) u1"1" else u1"0"
      }

      def bleU2(input: MSZ[B], context: Context): U2 = {
        val offset = context.offset
        if (offset + 2 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u2"0"
        }
        var r = u2"0"
        var mask = u2"1"
        for (i <- 0 until 1) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u2"1"
        }
        if (input(offset + 1)) {
          r = r | mask
        }
        context.offset = offset + 2
        return r
      }

      def bleU3(input: MSZ[B], context: Context): U3 = {
        val offset = context.offset
        if (offset + 3 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u3"0"
        }
        var r = u3"0"
        var mask = u3"1"
        for (i <- 0 until 2) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u3"1"
        }
        if (input(offset + 2)) {
          r = r | mask
        }
        context.offset = offset + 3
        return r
      }

      def bleU4(input: MSZ[B], context: Context): U4 = {
        val offset = context.offset
        if (offset + 4 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u4"0"
        }
        var r = u4"0"
        var mask = u4"1"
        for (i <- 0 until 3) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u4"1"
        }
        if (input(offset + 3)) {
          r = r | mask
        }
        context.offset = offset + 4
        return r
      }

      def bleU5(input: MSZ[B], context: Context): U5 = {
        val offset = context.offset
        if (offset + 5 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u5"0"
        }
        var r = u5"0"
        var mask = u5"1"
        for (i <- 0 until 4) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u5"1"
        }
        if (input(offset + 4)) {
          r = r | mask
        }
        context.offset = offset + 5
        return r
      }

      def bleU6(input: MSZ[B], context: Context): U6 = {
        val offset = context.offset
        if (offset + 6 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u6"0"
        }
        var r = u6"0"
        var mask = u6"1"
        for (i <- 0 until 5) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u6"1"
        }
        if (input(offset + 5)) {
          r = r | mask
        }
        context.offset = offset + 6
        return r
      }

      def bleU7(input: MSZ[B], context: Context): U7 = {
        val offset = context.offset
        if (offset + 7 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u7"0"
        }
        var r = u7"0"
        var mask = u7"1"
        for (i <- 0 until 6) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u7"1"
        }
        if (input(offset + 6)) {
          r = r | mask
        }
        context.offset = offset + 7
        return r
      }

      def beU8S(input: MSZ[B], context: Context, result: MSZ[U8], size: Z): Unit = {
        leU8S(input, context, result, size)
      }

      def beS8S(input: MSZ[B], context: Context, result: MSZ[S8], size: Z): Unit = {
        leS8S(input, context, result, size)
      }

      def beU16S(input: MSZ[B], context: Context, result: MSZ[U16], size: Z): Unit = {
        if (context.offset + size * 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beU16(input, context)
        }
      }

      def beS16S(input: MSZ[B], context: Context, result: MSZ[S16], size: Z): Unit = {
        if (context.offset + size * 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beS16(input, context)
        }
      }

      def beU32S(input: MSZ[B], context: Context, result: MSZ[U32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beU32(input, context)
        }
      }

      def beS32S(input: MSZ[B], context: Context, result: MSZ[S32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beS32(input, context)
        }
      }

      def beU64S(input: MSZ[B], context: Context, result: MSZ[U64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beU64(input, context)
        }
      }

      def beS64S(input: MSZ[B], context: Context, result: MSZ[S64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beS64(input, context)
        }
      }

      def beF32S(input: MSZ[B], context: Context, result: MSZ[F32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beF32(input, context)
        }
      }

      def beF64S(input: MSZ[B], context: Context, result: MSZ[F64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beF64(input, context)
        }
      }

      def leU8S(input: MSZ[B], context: Context, result: MSZ[U8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = bleU8(input, context)
        }
      }

      def leS8S(input: MSZ[B], context: Context, result: MSZ[S8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = bleS8(input, context)
        }
      }

      def leU16S(input: MSZ[B], context: Context, result: MSZ[U16], size: Z): Unit = {
        if (context.offset + size * 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leU16(input, context)
        }
      }

      def leS16S(input: MSZ[B], context: Context, result: MSZ[S16], size: Z): Unit = {
        if (context.offset + size * 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leS16(input, context)
        }
      }

      def leU32S(input: MSZ[B], context: Context, result: MSZ[U32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leU32(input, context)
        }
      }

      def leS32S(input: MSZ[B], context: Context, result: MSZ[S32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leS32(input, context)
        }
      }

      def leU64S(input: MSZ[B], context: Context, result: MSZ[U64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leU64(input, context)
        }
      }

      def leS64S(input: MSZ[B], context: Context, result: MSZ[S64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leS64(input, context)
        }
      }

      def leF32S(input: MSZ[B], context: Context, result: MSZ[F32], size: Z): Unit = {
        if (context.offset + size * 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leF32(input, context)
        }
      }

      def leF64S(input: MSZ[B], context: Context, result: MSZ[F64], size: Z): Unit = {
        if (context.offset + size * 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leF64(input, context)
        }
      }

      def bleU8(input: MSZ[B], context: Context): U8 = {
        val offset = context.offset
        if (offset + 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u8"0"
        }
        var r = u8"0"
        var mask = u8"1"
        for (i <- 0 until 7) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u8"1"
        }
        if (input(offset + 7)) {
          r = r | mask
        }
        context.offset = offset + 8
        return r
      }

      def bleS8(input: MSZ[B], context: Context): S8 = {
        return conversions.U8.toRawS8(bleU8(input, context))
      }

      // Slang script gen:
      /*
      for (i <- 9 to 15) {
        println(
          st"""      def beU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U16.toU$i(conversions.U${i - 8}.toU16(bleU${i - 8}(input, context))) << u$i"8")
              |        r = r | conversions.U16.toU$i(conversions.U8.toU16(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 17 to 24) {
        println(
          st"""      def beU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U32.toU$i(conversions.U${i - 16}.toU32(bleU${i - 16}(input, context))) << u$i"16")
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 25 to 31) {
        println(
          st"""      def beU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U32.toU$i(conversions.U${i - 24}.toU32(bleU${i - 24}(input, context))) << u$i"24")
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 33 to 40) {
        println(
          st"""      def beU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 32}.toU64(bleU${i - 32}(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 41 to 48) {
        println(
          st"""      def beU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 40}.toU64(bleU${i - 40}(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 49 to 56) {
        println(
          st"""      def beU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 48}.toU64(bleU${i - 48}(input, context))) << u$i"48")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 57 to 63) {
        println(
          st"""      def beU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 56}.toU64(bleU${i - 56}(input, context))) << u$i"56")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"48")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }
      */

      def beU9(input: MSZ[B], context: Context): U9 = {
        var r = u9"0"
        r = r | (conversions.U16.toU9(conversions.U1.toU16(bleU1(input, context))) << u9"8")
        r = r | conversions.U16.toU9(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u9"0"
        }
        return r
      }

      def beU10(input: MSZ[B], context: Context): U10 = {
        var r = u10"0"
        r = r | (conversions.U16.toU10(conversions.U2.toU16(bleU2(input, context))) << u10"8")
        r = r | conversions.U16.toU10(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u10"0"
        }
        return r
      }

      def beU11(input: MSZ[B], context: Context): U11 = {
        var r = u11"0"
        r = r | (conversions.U16.toU11(conversions.U3.toU16(bleU3(input, context))) << u11"8")
        r = r | conversions.U16.toU11(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u11"0"
        }
        return r
      }

      def beU12(input: MSZ[B], context: Context): U12 = {
        var r = u12"0"
        r = r | (conversions.U16.toU12(conversions.U4.toU16(bleU4(input, context))) << u12"8")
        r = r | conversions.U16.toU12(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u12"0"
        }
        return r
      }

      def beU13(input: MSZ[B], context: Context): U13 = {
        var r = u13"0"
        r = r | (conversions.U16.toU13(conversions.U5.toU16(bleU5(input, context))) << u13"8")
        r = r | conversions.U16.toU13(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u13"0"
        }
        return r
      }

      def beU14(input: MSZ[B], context: Context): U14 = {
        var r = u14"0"
        r = r | (conversions.U16.toU14(conversions.U6.toU16(bleU6(input, context))) << u14"8")
        r = r | conversions.U16.toU14(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u14"0"
        }
        return r
      }

      def beU15(input: MSZ[B], context: Context): U15 = {
        var r = u15"0"
        r = r | (conversions.U16.toU15(conversions.U7.toU16(bleU7(input, context))) << u15"8")
        r = r | conversions.U16.toU15(conversions.U8.toU16(bleU8(input, context)))
        if (context.hasError) {
          return u15"0"
        }
        return r
      }

      def beU16(input: MSZ[B], context: Context): U16 = {
        val r = (conversions.U8.toU16(bleU8(input, context)) << u16"8") |
          conversions.U8.toU16(bleU8(input, context))
        if (context.hasError) {
          return u16"0"
        }
        return r
      }

      def beS16(input: MSZ[B], context: Context): S16 = {
        return conversions.U16.toRawS16(beU16(input, context))
      }

      def beU17(input: MSZ[B], context: Context): U17 = {
        var r = u17"0"
        r = r | (conversions.U32.toU17(conversions.U1.toU32(bleU1(input, context))) << u17"16")
        r = r | (conversions.U32.toU17(conversions.U8.toU32(bleU8(input, context))) << u17"8")
        r = r | conversions.U32.toU17(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u17"0"
        }
        return r
      }

      def beU18(input: MSZ[B], context: Context): U18 = {
        var r = u18"0"
        r = r | (conversions.U32.toU18(conversions.U2.toU32(bleU2(input, context))) << u18"16")
        r = r | (conversions.U32.toU18(conversions.U8.toU32(bleU8(input, context))) << u18"8")
        r = r | conversions.U32.toU18(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u18"0"
        }
        return r
      }

      def beU19(input: MSZ[B], context: Context): U19 = {
        var r = u19"0"
        r = r | (conversions.U32.toU19(conversions.U3.toU32(bleU3(input, context))) << u19"16")
        r = r | (conversions.U32.toU19(conversions.U8.toU32(bleU8(input, context))) << u19"8")
        r = r | conversions.U32.toU19(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u19"0"
        }
        return r
      }

      def beU20(input: MSZ[B], context: Context): U20 = {
        var r = u20"0"
        r = r | (conversions.U32.toU20(conversions.U4.toU32(bleU4(input, context))) << u20"16")
        r = r | (conversions.U32.toU20(conversions.U8.toU32(bleU8(input, context))) << u20"8")
        r = r | conversions.U32.toU20(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u20"0"
        }
        return r
      }

      def beU21(input: MSZ[B], context: Context): U21 = {
        var r = u21"0"
        r = r | (conversions.U32.toU21(conversions.U5.toU32(bleU5(input, context))) << u21"16")
        r = r | (conversions.U32.toU21(conversions.U8.toU32(bleU8(input, context))) << u21"8")
        r = r | conversions.U32.toU21(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u21"0"
        }
        return r
      }

      def beU22(input: MSZ[B], context: Context): U22 = {
        var r = u22"0"
        r = r | (conversions.U32.toU22(conversions.U6.toU32(bleU6(input, context))) << u22"16")
        r = r | (conversions.U32.toU22(conversions.U8.toU32(bleU8(input, context))) << u22"8")
        r = r | conversions.U32.toU22(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u22"0"
        }
        return r
      }

      def beU23(input: MSZ[B], context: Context): U23 = {
        var r = u23"0"
        r = r | (conversions.U32.toU23(conversions.U7.toU32(bleU7(input, context))) << u23"16")
        r = r | (conversions.U32.toU23(conversions.U8.toU32(bleU8(input, context))) << u23"8")
        r = r | conversions.U32.toU23(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u23"0"
        }
        return r
      }

      def beU24(input: MSZ[B], context: Context): U24 = {
        var r = u24"0"
        r = r | (conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context))) << u24"16")
        r = r | (conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context))) << u24"8")
        r = r | conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u24"0"
        }
        return r
      }

      def beU25(input: MSZ[B], context: Context): U25 = {
        var r = u25"0"
        r = r | (conversions.U32.toU25(conversions.U1.toU32(bleU1(input, context))) << u25"24")
        r = r | (conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context))) << u25"16")
        r = r | (conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context))) << u25"8")
        r = r | conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u25"0"
        }
        return r
      }

      def beU26(input: MSZ[B], context: Context): U26 = {
        var r = u26"0"
        r = r | (conversions.U32.toU26(conversions.U2.toU32(bleU2(input, context))) << u26"24")
        r = r | (conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context))) << u26"16")
        r = r | (conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context))) << u26"8")
        r = r | conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u26"0"
        }
        return r
      }

      def beU27(input: MSZ[B], context: Context): U27 = {
        var r = u27"0"
        r = r | (conversions.U32.toU27(conversions.U3.toU32(bleU3(input, context))) << u27"24")
        r = r | (conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context))) << u27"16")
        r = r | (conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context))) << u27"8")
        r = r | conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u27"0"
        }
        return r
      }

      def beU28(input: MSZ[B], context: Context): U28 = {
        var r = u28"0"
        r = r | (conversions.U32.toU28(conversions.U4.toU32(bleU4(input, context))) << u28"24")
        r = r | (conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context))) << u28"16")
        r = r | (conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context))) << u28"8")
        r = r | conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u28"0"
        }
        return r
      }

      def beU29(input: MSZ[B], context: Context): U29 = {
        var r = u29"0"
        r = r | (conversions.U32.toU29(conversions.U5.toU32(bleU5(input, context))) << u29"24")
        r = r | (conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context))) << u29"16")
        r = r | (conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context))) << u29"8")
        r = r | conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u29"0"
        }
        return r
      }

      def beU30(input: MSZ[B], context: Context): U30 = {
        var r = u30"0"
        r = r | (conversions.U32.toU30(conversions.U6.toU32(bleU6(input, context))) << u30"24")
        r = r | (conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context))) << u30"16")
        r = r | (conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context))) << u30"8")
        r = r | conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u30"0"
        }
        return r
      }

      def beU31(input: MSZ[B], context: Context): U31 = {
        var r = u31"0"
        r = r | (conversions.U32.toU31(conversions.U7.toU32(bleU7(input, context))) << u31"24")
        r = r | (conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context))) << u31"16")
        r = r | (conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context))) << u31"8")
        r = r | conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context)))
        if (context.hasError) {
          return u31"0"
        }
        return r
      }

      def beU32(input: MSZ[B], context: Context): U32 = {
        val r = (conversions.U8.toU32(bleU8(input, context)) << u32"24") |
          (conversions.U8.toU32(bleU8(input, context)) << u32"16") |
          (conversions.U8.toU32(bleU8(input, context)) << u32"8") |
          conversions.U8.toU32(bleU8(input, context))
        if (context.hasError) {
          return u32"0"
        }
        return r
      }

      def beS32(input: MSZ[B], context: Context): S32 = {
        return conversions.U32.toRawS32(beU32(input, context))
      }

      def beU33(input: MSZ[B], context: Context): U33 = {
        var r = u33"0"
        r = r | (conversions.U64.toU33(conversions.U1.toU64(bleU1(input, context))) << u33"32")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"24")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"16")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"8")
        r = r | conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u33"0"
        }
        return r
      }

      def beU34(input: MSZ[B], context: Context): U34 = {
        var r = u34"0"
        r = r | (conversions.U64.toU34(conversions.U2.toU64(bleU2(input, context))) << u34"32")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"24")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"16")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"8")
        r = r | conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u34"0"
        }
        return r
      }

      def beU35(input: MSZ[B], context: Context): U35 = {
        var r = u35"0"
        r = r | (conversions.U64.toU35(conversions.U3.toU64(bleU3(input, context))) << u35"32")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"24")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"16")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"8")
        r = r | conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u35"0"
        }
        return r
      }

      def beU36(input: MSZ[B], context: Context): U36 = {
        var r = u36"0"
        r = r | (conversions.U64.toU36(conversions.U4.toU64(bleU4(input, context))) << u36"32")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"24")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"16")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"8")
        r = r | conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u36"0"
        }
        return r
      }

      def beU37(input: MSZ[B], context: Context): U37 = {
        var r = u37"0"
        r = r | (conversions.U64.toU37(conversions.U5.toU64(bleU5(input, context))) << u37"32")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"24")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"16")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"8")
        r = r | conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u37"0"
        }
        return r
      }

      def beU38(input: MSZ[B], context: Context): U38 = {
        var r = u38"0"
        r = r | (conversions.U64.toU38(conversions.U6.toU64(bleU6(input, context))) << u38"32")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"24")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"16")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"8")
        r = r | conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u38"0"
        }
        return r
      }

      def beU39(input: MSZ[B], context: Context): U39 = {
        var r = u39"0"
        r = r | (conversions.U64.toU39(conversions.U7.toU64(bleU7(input, context))) << u39"32")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"24")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"16")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"8")
        r = r | conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u39"0"
        }
        return r
      }

      def beU40(input: MSZ[B], context: Context): U40 = {
        var r = u40"0"
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"32")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"24")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"16")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"8")
        r = r | conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u40"0"
        }
        return r
      }

      def beU41(input: MSZ[B], context: Context): U41 = {
        var r = u41"0"
        r = r | (conversions.U64.toU41(conversions.U1.toU64(bleU1(input, context))) << u41"40")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"32")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"24")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"16")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"8")
        r = r | conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u41"0"
        }
        return r
      }

      def beU42(input: MSZ[B], context: Context): U42 = {
        var r = u42"0"
        r = r | (conversions.U64.toU42(conversions.U2.toU64(bleU2(input, context))) << u42"40")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"32")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"24")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"16")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"8")
        r = r | conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u42"0"
        }
        return r
      }

      def beU43(input: MSZ[B], context: Context): U43 = {
        var r = u43"0"
        r = r | (conversions.U64.toU43(conversions.U3.toU64(bleU3(input, context))) << u43"40")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"32")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"24")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"16")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"8")
        r = r | conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u43"0"
        }
        return r
      }

      def beU44(input: MSZ[B], context: Context): U44 = {
        var r = u44"0"
        r = r | (conversions.U64.toU44(conversions.U4.toU64(bleU4(input, context))) << u44"40")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"32")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"24")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"16")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"8")
        r = r | conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u44"0"
        }
        return r
      }

      def beU45(input: MSZ[B], context: Context): U45 = {
        var r = u45"0"
        r = r | (conversions.U64.toU45(conversions.U5.toU64(bleU5(input, context))) << u45"40")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"32")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"24")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"16")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"8")
        r = r | conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u45"0"
        }
        return r
      }

      def beU46(input: MSZ[B], context: Context): U46 = {
        var r = u46"0"
        r = r | (conversions.U64.toU46(conversions.U6.toU64(bleU6(input, context))) << u46"40")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"32")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"24")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"16")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"8")
        r = r | conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u46"0"
        }
        return r
      }

      def beU47(input: MSZ[B], context: Context): U47 = {
        var r = u47"0"
        r = r | (conversions.U64.toU47(conversions.U7.toU64(bleU7(input, context))) << u47"40")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"32")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"24")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"16")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"8")
        r = r | conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u47"0"
        }
        return r
      }

      def beU48(input: MSZ[B], context: Context): U48 = {
        var r = u48"0"
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"40")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"32")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"24")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"16")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"8")
        r = r | conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u48"0"
        }
        return r
      }

      def beU49(input: MSZ[B], context: Context): U49 = {
        var r = u49"0"
        r = r | (conversions.U64.toU49(conversions.U1.toU64(bleU1(input, context))) << u49"48")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"40")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"32")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"24")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"16")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"8")
        r = r | conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u49"0"
        }
        return r
      }

      def beU50(input: MSZ[B], context: Context): U50 = {
        var r = u50"0"
        r = r | (conversions.U64.toU50(conversions.U2.toU64(bleU2(input, context))) << u50"48")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"40")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"32")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"24")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"16")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"8")
        r = r | conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u50"0"
        }
        return r
      }

      def beU51(input: MSZ[B], context: Context): U51 = {
        var r = u51"0"
        r = r | (conversions.U64.toU51(conversions.U3.toU64(bleU3(input, context))) << u51"48")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"40")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"32")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"24")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"16")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"8")
        r = r | conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u51"0"
        }
        return r
      }

      def beU52(input: MSZ[B], context: Context): U52 = {
        var r = u52"0"
        r = r | (conversions.U64.toU52(conversions.U4.toU64(bleU4(input, context))) << u52"48")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"40")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"32")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"24")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"16")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"8")
        r = r | conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u52"0"
        }
        return r
      }

      def beU53(input: MSZ[B], context: Context): U53 = {
        var r = u53"0"
        r = r | (conversions.U64.toU53(conversions.U5.toU64(bleU5(input, context))) << u53"48")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"40")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"32")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"24")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"16")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"8")
        r = r | conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u53"0"
        }
        return r
      }

      def beU54(input: MSZ[B], context: Context): U54 = {
        var r = u54"0"
        r = r | (conversions.U64.toU54(conversions.U6.toU64(bleU6(input, context))) << u54"48")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"40")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"32")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"24")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"16")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"8")
        r = r | conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u54"0"
        }
        return r
      }

      def beU55(input: MSZ[B], context: Context): U55 = {
        var r = u55"0"
        r = r | (conversions.U64.toU55(conversions.U7.toU64(bleU7(input, context))) << u55"48")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"40")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"32")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"24")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"16")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"8")
        r = r | conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u55"0"
        }
        return r
      }

      def beU56(input: MSZ[B], context: Context): U56 = {
        var r = u56"0"
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"48")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"40")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"32")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"24")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"16")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"8")
        r = r | conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u56"0"
        }
        return r
      }

      def beU57(input: MSZ[B], context: Context): U57 = {
        var r = u57"0"
        r = r | (conversions.U64.toU57(conversions.U1.toU64(bleU1(input, context))) << u57"56")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"48")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"40")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"32")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"24")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"16")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"8")
        r = r | conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u57"0"
        }
        return r
      }

      def beU58(input: MSZ[B], context: Context): U58 = {
        var r = u58"0"
        r = r | (conversions.U64.toU58(conversions.U2.toU64(bleU2(input, context))) << u58"56")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"48")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"40")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"32")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"24")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"16")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"8")
        r = r | conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u58"0"
        }
        return r
      }

      def beU59(input: MSZ[B], context: Context): U59 = {
        var r = u59"0"
        r = r | (conversions.U64.toU59(conversions.U3.toU64(bleU3(input, context))) << u59"56")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"48")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"40")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"32")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"24")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"16")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"8")
        r = r | conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u59"0"
        }
        return r
      }

      def beU60(input: MSZ[B], context: Context): U60 = {
        var r = u60"0"
        r = r | (conversions.U64.toU60(conversions.U4.toU64(bleU4(input, context))) << u60"56")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"48")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"40")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"32")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"24")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"16")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"8")
        r = r | conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u60"0"
        }
        return r
      }

      def beU61(input: MSZ[B], context: Context): U61 = {
        var r = u61"0"
        r = r | (conversions.U64.toU61(conversions.U5.toU64(bleU5(input, context))) << u61"56")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"48")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"40")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"32")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"24")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"16")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"8")
        r = r | conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u61"0"
        }
        return r
      }

      def beU62(input: MSZ[B], context: Context): U62 = {
        var r = u62"0"
        r = r | (conversions.U64.toU62(conversions.U6.toU64(bleU6(input, context))) << u62"56")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"48")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"40")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"32")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"24")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"16")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"8")
        r = r | conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u62"0"
        }
        return r
      }

      def beU63(input: MSZ[B], context: Context): U63 = {
        var r = u63"0"
        r = r | (conversions.U64.toU63(conversions.U7.toU64(bleU7(input, context))) << u63"56")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"48")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"40")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"32")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"24")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"16")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"8")
        r = r | conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context)))
        if (context.hasError) {
          return u63"0"
        }
        return r
      }

      def beU64(input: MSZ[B], context: Context): U64 = {
        val r = (conversions.U8.toU64(bleU8(input, context)) << u64"56") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"48") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"40") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"32") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"24") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"16") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"8") |
          conversions.U8.toU64(bleU8(input, context))
        if (context.hasError) {
          return u64"0"
        }
        return r
      }

      def beS64(input: MSZ[B], context: Context): S64 = {
        return conversions.U64.toRawS64(beU64(input, context))
      }

      def beF32(input: MSZ[B], context: Context): F32 = {
        return conversions.U32.toRawF32(beU32(input, context))
      }

      def beF64(input: MSZ[B], context: Context): F64 = {
        return conversions.U64.toRawF64(beU64(input, context))
      }

      // Slang script gen:
      /*
      for (i <- 9 to 15) {
        println(
          st"""      def leU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U16.toU$i(conversions.U8.toU16(bleU8(input, context)))
              |        r = r | (conversions.U16.toU$i(conversions.U${i - 8}.toU16(bleU${i - 8}(input, context))) << u$i"8")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 17 to 24) {
        println(
          st"""      def leU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context)))
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U32.toU$i(conversions.U${i - 16}.toU32(bleU${i - 16}(input, context))) << u$i"16")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 25 to 31) {
        println(
          st"""      def leU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context)))
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U32.toU$i(conversions.U8.toU32(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U32.toU$i(conversions.U${i - 24}.toU32(bleU${i - 24}(input, context))) << u$i"24")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 33 to 40) {
        println(
          st"""      def leU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 32}.toU64(bleU${i - 32}(input, context))) << u$i"32")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 41 to 48) {
        println(
          st"""      def leU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 40}.toU64(bleU${i - 40}(input, context))) << u$i"40")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 49 to 56) {
        println(
          st"""      def leU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 48}.toU64(bleU${i - 48}(input, context))) << u$i"48")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

      for (i <- 57 to 63) {
        println(
          st"""      def leU$i(input: MSZ[B], context: Context): U$i = {
              |        var r = u$i"0"
              |        r = r | conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context)))
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"8")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"16")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"24")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"32")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"40")
              |        r = r | (conversions.U64.toU$i(conversions.U8.toU64(bleU8(input, context))) << u$i"48")
              |        r = r | (conversions.U64.toU$i(conversions.U${i - 56}.toU64(bleU${i - 56}(input, context))) << u$i"56")
              |        if (context.hasError) {
              |          return u$i"0"
              |        }
              |        return r
              |      }
              |""".render
        )
      }

       */

      def leU9(input: MSZ[B], context: Context): U9 = {
        var r = u9"0"
        r = r | conversions.U16.toU9(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU9(conversions.U1.toU16(bleU1(input, context))) << u9"8")
        if (context.hasError) {
          return u9"0"
        }
        return r
      }

      def leU10(input: MSZ[B], context: Context): U10 = {
        var r = u10"0"
        r = r | conversions.U16.toU10(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU10(conversions.U2.toU16(bleU2(input, context))) << u10"8")
        if (context.hasError) {
          return u10"0"
        }
        return r
      }

      def leU11(input: MSZ[B], context: Context): U11 = {
        var r = u11"0"
        r = r | conversions.U16.toU11(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU11(conversions.U3.toU16(bleU3(input, context))) << u11"8")
        if (context.hasError) {
          return u11"0"
        }
        return r
      }

      def leU12(input: MSZ[B], context: Context): U12 = {
        var r = u12"0"
        r = r | conversions.U16.toU12(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU12(conversions.U4.toU16(bleU4(input, context))) << u12"8")
        if (context.hasError) {
          return u12"0"
        }
        return r
      }

      def leU13(input: MSZ[B], context: Context): U13 = {
        var r = u13"0"
        r = r | conversions.U16.toU13(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU13(conversions.U5.toU16(bleU5(input, context))) << u13"8")
        if (context.hasError) {
          return u13"0"
        }
        return r
      }

      def leU14(input: MSZ[B], context: Context): U14 = {
        var r = u14"0"
        r = r | conversions.U16.toU14(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU14(conversions.U6.toU16(bleU6(input, context))) << u14"8")
        if (context.hasError) {
          return u14"0"
        }
        return r
      }

      def leU15(input: MSZ[B], context: Context): U15 = {
        var r = u15"0"
        r = r | conversions.U16.toU15(conversions.U8.toU16(bleU8(input, context)))
        r = r | (conversions.U16.toU15(conversions.U7.toU16(bleU7(input, context))) << u15"8")
        if (context.hasError) {
          return u15"0"
        }
        return r
      }

      def leU16(input: MSZ[B], context: Context): U16 = {
        val r = conversions.U8.toU16(bleU8(input, context)) |
          (conversions.U8.toU16(bleU8(input, context)) << u16"8")
        if (context.hasError) {
          return u16"0"
        }
        return r
      }

      def leS16(input: MSZ[B], context: Context): S16 = {
        return conversions.U16.toRawS16(leU16(input, context))
      }

      def leU17(input: MSZ[B], context: Context): U17 = {
        var r = u17"0"
        r = r | conversions.U32.toU17(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU17(conversions.U8.toU32(bleU8(input, context))) << u17"8")
        r = r | (conversions.U32.toU17(conversions.U1.toU32(bleU1(input, context))) << u17"16")
        if (context.hasError) {
          return u17"0"
        }
        return r
      }

      def leU18(input: MSZ[B], context: Context): U18 = {
        var r = u18"0"
        r = r | conversions.U32.toU18(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU18(conversions.U8.toU32(bleU8(input, context))) << u18"8")
        r = r | (conversions.U32.toU18(conversions.U2.toU32(bleU2(input, context))) << u18"16")
        if (context.hasError) {
          return u18"0"
        }
        return r
      }

      def leU19(input: MSZ[B], context: Context): U19 = {
        var r = u19"0"
        r = r | conversions.U32.toU19(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU19(conversions.U8.toU32(bleU8(input, context))) << u19"8")
        r = r | (conversions.U32.toU19(conversions.U3.toU32(bleU3(input, context))) << u19"16")
        if (context.hasError) {
          return u19"0"
        }
        return r
      }

      def leU20(input: MSZ[B], context: Context): U20 = {
        var r = u20"0"
        r = r | conversions.U32.toU20(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU20(conversions.U8.toU32(bleU8(input, context))) << u20"8")
        r = r | (conversions.U32.toU20(conversions.U4.toU32(bleU4(input, context))) << u20"16")
        if (context.hasError) {
          return u20"0"
        }
        return r
      }

      def leU21(input: MSZ[B], context: Context): U21 = {
        var r = u21"0"
        r = r | conversions.U32.toU21(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU21(conversions.U8.toU32(bleU8(input, context))) << u21"8")
        r = r | (conversions.U32.toU21(conversions.U5.toU32(bleU5(input, context))) << u21"16")
        if (context.hasError) {
          return u21"0"
        }
        return r
      }

      def leU22(input: MSZ[B], context: Context): U22 = {
        var r = u22"0"
        r = r | conversions.U32.toU22(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU22(conversions.U8.toU32(bleU8(input, context))) << u22"8")
        r = r | (conversions.U32.toU22(conversions.U6.toU32(bleU6(input, context))) << u22"16")
        if (context.hasError) {
          return u22"0"
        }
        return r
      }

      def leU23(input: MSZ[B], context: Context): U23 = {
        var r = u23"0"
        r = r | conversions.U32.toU23(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU23(conversions.U8.toU32(bleU8(input, context))) << u23"8")
        r = r | (conversions.U32.toU23(conversions.U7.toU32(bleU7(input, context))) << u23"16")
        if (context.hasError) {
          return u23"0"
        }
        return r
      }

      def leU24(input: MSZ[B], context: Context): U24 = {
        var r = u24"0"
        r = r | conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context))) << u24"8")
        r = r | (conversions.U32.toU24(conversions.U8.toU32(bleU8(input, context))) << u24"16")
        if (context.hasError) {
          return u24"0"
        }
        return r
      }

      def leU25(input: MSZ[B], context: Context): U25 = {
        var r = u25"0"
        r = r | conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context))) << u25"8")
        r = r | (conversions.U32.toU25(conversions.U8.toU32(bleU8(input, context))) << u25"16")
        r = r | (conversions.U32.toU25(conversions.U1.toU32(bleU1(input, context))) << u25"24")
        if (context.hasError) {
          return u25"0"
        }
        return r
      }

      def leU26(input: MSZ[B], context: Context): U26 = {
        var r = u26"0"
        r = r | conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context))) << u26"8")
        r = r | (conversions.U32.toU26(conversions.U8.toU32(bleU8(input, context))) << u26"16")
        r = r | (conversions.U32.toU26(conversions.U2.toU32(bleU2(input, context))) << u26"24")
        if (context.hasError) {
          return u26"0"
        }
        return r
      }

      def leU27(input: MSZ[B], context: Context): U27 = {
        var r = u27"0"
        r = r | conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context))) << u27"8")
        r = r | (conversions.U32.toU27(conversions.U8.toU32(bleU8(input, context))) << u27"16")
        r = r | (conversions.U32.toU27(conversions.U3.toU32(bleU3(input, context))) << u27"24")
        if (context.hasError) {
          return u27"0"
        }
        return r
      }

      def leU28(input: MSZ[B], context: Context): U28 = {
        var r = u28"0"
        r = r | conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context))) << u28"8")
        r = r | (conversions.U32.toU28(conversions.U8.toU32(bleU8(input, context))) << u28"16")
        r = r | (conversions.U32.toU28(conversions.U4.toU32(bleU4(input, context))) << u28"24")
        if (context.hasError) {
          return u28"0"
        }
        return r
      }

      def leU29(input: MSZ[B], context: Context): U29 = {
        var r = u29"0"
        r = r | conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context))) << u29"8")
        r = r | (conversions.U32.toU29(conversions.U8.toU32(bleU8(input, context))) << u29"16")
        r = r | (conversions.U32.toU29(conversions.U5.toU32(bleU5(input, context))) << u29"24")
        if (context.hasError) {
          return u29"0"
        }
        return r
      }

      def leU30(input: MSZ[B], context: Context): U30 = {
        var r = u30"0"
        r = r | conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context))) << u30"8")
        r = r | (conversions.U32.toU30(conversions.U8.toU32(bleU8(input, context))) << u30"16")
        r = r | (conversions.U32.toU30(conversions.U6.toU32(bleU6(input, context))) << u30"24")
        if (context.hasError) {
          return u30"0"
        }
        return r
      }

      def leU31(input: MSZ[B], context: Context): U31 = {
        var r = u31"0"
        r = r | conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context)))
        r = r | (conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context))) << u31"8")
        r = r | (conversions.U32.toU31(conversions.U8.toU32(bleU8(input, context))) << u31"16")
        r = r | (conversions.U32.toU31(conversions.U7.toU32(bleU7(input, context))) << u31"24")
        if (context.hasError) {
          return u31"0"
        }
        return r
      }

      def leU32(input: MSZ[B], context: Context): U32 = {
        val r = conversions.U8.toU32(bleU8(input, context)) |
          (conversions.U8.toU32(bleU8(input, context)) << u32"8") |
          (conversions.U8.toU32(bleU8(input, context)) << u32"16") |
          (conversions.U8.toU32(bleU8(input, context)) << u32"24")
        if (context.hasError) {
          return u32"0"
        }
        return r
      }

      def leS32(input: MSZ[B], context: Context): S32 = {
        return conversions.U32.toRawS32(leU32(input, context))
      }

      def leU33(input: MSZ[B], context: Context): U33 = {
        var r = u33"0"
        r = r | conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"8")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"16")
        r = r | (conversions.U64.toU33(conversions.U8.toU64(bleU8(input, context))) << u33"24")
        r = r | (conversions.U64.toU33(conversions.U1.toU64(bleU1(input, context))) << u33"32")
        if (context.hasError) {
          return u33"0"
        }
        return r
      }

      def leU34(input: MSZ[B], context: Context): U34 = {
        var r = u34"0"
        r = r | conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"8")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"16")
        r = r | (conversions.U64.toU34(conversions.U8.toU64(bleU8(input, context))) << u34"24")
        r = r | (conversions.U64.toU34(conversions.U2.toU64(bleU2(input, context))) << u34"32")
        if (context.hasError) {
          return u34"0"
        }
        return r
      }

      def leU35(input: MSZ[B], context: Context): U35 = {
        var r = u35"0"
        r = r | conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"8")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"16")
        r = r | (conversions.U64.toU35(conversions.U8.toU64(bleU8(input, context))) << u35"24")
        r = r | (conversions.U64.toU35(conversions.U3.toU64(bleU3(input, context))) << u35"32")
        if (context.hasError) {
          return u35"0"
        }
        return r
      }

      def leU36(input: MSZ[B], context: Context): U36 = {
        var r = u36"0"
        r = r | conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"8")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"16")
        r = r | (conversions.U64.toU36(conversions.U8.toU64(bleU8(input, context))) << u36"24")
        r = r | (conversions.U64.toU36(conversions.U4.toU64(bleU4(input, context))) << u36"32")
        if (context.hasError) {
          return u36"0"
        }
        return r
      }

      def leU37(input: MSZ[B], context: Context): U37 = {
        var r = u37"0"
        r = r | conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"8")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"16")
        r = r | (conversions.U64.toU37(conversions.U8.toU64(bleU8(input, context))) << u37"24")
        r = r | (conversions.U64.toU37(conversions.U5.toU64(bleU5(input, context))) << u37"32")
        if (context.hasError) {
          return u37"0"
        }
        return r
      }

      def leU38(input: MSZ[B], context: Context): U38 = {
        var r = u38"0"
        r = r | conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"8")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"16")
        r = r | (conversions.U64.toU38(conversions.U8.toU64(bleU8(input, context))) << u38"24")
        r = r | (conversions.U64.toU38(conversions.U6.toU64(bleU6(input, context))) << u38"32")
        if (context.hasError) {
          return u38"0"
        }
        return r
      }

      def leU39(input: MSZ[B], context: Context): U39 = {
        var r = u39"0"
        r = r | conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"8")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"16")
        r = r | (conversions.U64.toU39(conversions.U8.toU64(bleU8(input, context))) << u39"24")
        r = r | (conversions.U64.toU39(conversions.U7.toU64(bleU7(input, context))) << u39"32")
        if (context.hasError) {
          return u39"0"
        }
        return r
      }

      def leU40(input: MSZ[B], context: Context): U40 = {
        var r = u40"0"
        r = r | conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"8")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"16")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"24")
        r = r | (conversions.U64.toU40(conversions.U8.toU64(bleU8(input, context))) << u40"32")
        if (context.hasError) {
          return u40"0"
        }
        return r
      }

      def leU41(input: MSZ[B], context: Context): U41 = {
        var r = u41"0"
        r = r | conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"8")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"16")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"24")
        r = r | (conversions.U64.toU41(conversions.U8.toU64(bleU8(input, context))) << u41"32")
        r = r | (conversions.U64.toU41(conversions.U1.toU64(bleU1(input, context))) << u41"40")
        if (context.hasError) {
          return u41"0"
        }
        return r
      }

      def leU42(input: MSZ[B], context: Context): U42 = {
        var r = u42"0"
        r = r | conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"8")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"16")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"24")
        r = r | (conversions.U64.toU42(conversions.U8.toU64(bleU8(input, context))) << u42"32")
        r = r | (conversions.U64.toU42(conversions.U2.toU64(bleU2(input, context))) << u42"40")
        if (context.hasError) {
          return u42"0"
        }
        return r
      }

      def leU43(input: MSZ[B], context: Context): U43 = {
        var r = u43"0"
        r = r | conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"8")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"16")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"24")
        r = r | (conversions.U64.toU43(conversions.U8.toU64(bleU8(input, context))) << u43"32")
        r = r | (conversions.U64.toU43(conversions.U3.toU64(bleU3(input, context))) << u43"40")
        if (context.hasError) {
          return u43"0"
        }
        return r
      }

      def leU44(input: MSZ[B], context: Context): U44 = {
        var r = u44"0"
        r = r | conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"8")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"16")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"24")
        r = r | (conversions.U64.toU44(conversions.U8.toU64(bleU8(input, context))) << u44"32")
        r = r | (conversions.U64.toU44(conversions.U4.toU64(bleU4(input, context))) << u44"40")
        if (context.hasError) {
          return u44"0"
        }
        return r
      }

      def leU45(input: MSZ[B], context: Context): U45 = {
        var r = u45"0"
        r = r | conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"8")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"16")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"24")
        r = r | (conversions.U64.toU45(conversions.U8.toU64(bleU8(input, context))) << u45"32")
        r = r | (conversions.U64.toU45(conversions.U5.toU64(bleU5(input, context))) << u45"40")
        if (context.hasError) {
          return u45"0"
        }
        return r
      }

      def leU46(input: MSZ[B], context: Context): U46 = {
        var r = u46"0"
        r = r | conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"8")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"16")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"24")
        r = r | (conversions.U64.toU46(conversions.U8.toU64(bleU8(input, context))) << u46"32")
        r = r | (conversions.U64.toU46(conversions.U6.toU64(bleU6(input, context))) << u46"40")
        if (context.hasError) {
          return u46"0"
        }
        return r
      }

      def leU47(input: MSZ[B], context: Context): U47 = {
        var r = u47"0"
        r = r | conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"8")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"16")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"24")
        r = r | (conversions.U64.toU47(conversions.U8.toU64(bleU8(input, context))) << u47"32")
        r = r | (conversions.U64.toU47(conversions.U7.toU64(bleU7(input, context))) << u47"40")
        if (context.hasError) {
          return u47"0"
        }
        return r
      }

      def leU48(input: MSZ[B], context: Context): U48 = {
        var r = u48"0"
        r = r | conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"8")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"16")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"24")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"32")
        r = r | (conversions.U64.toU48(conversions.U8.toU64(bleU8(input, context))) << u48"40")
        if (context.hasError) {
          return u48"0"
        }
        return r
      }

      def leU49(input: MSZ[B], context: Context): U49 = {
        var r = u49"0"
        r = r | conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"8")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"16")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"24")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"32")
        r = r | (conversions.U64.toU49(conversions.U8.toU64(bleU8(input, context))) << u49"40")
        r = r | (conversions.U64.toU49(conversions.U1.toU64(bleU1(input, context))) << u49"48")
        if (context.hasError) {
          return u49"0"
        }
        return r
      }

      def leU50(input: MSZ[B], context: Context): U50 = {
        var r = u50"0"
        r = r | conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"8")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"16")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"24")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"32")
        r = r | (conversions.U64.toU50(conversions.U8.toU64(bleU8(input, context))) << u50"40")
        r = r | (conversions.U64.toU50(conversions.U2.toU64(bleU2(input, context))) << u50"48")
        if (context.hasError) {
          return u50"0"
        }
        return r
      }

      def leU51(input: MSZ[B], context: Context): U51 = {
        var r = u51"0"
        r = r | conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"8")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"16")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"24")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"32")
        r = r | (conversions.U64.toU51(conversions.U8.toU64(bleU8(input, context))) << u51"40")
        r = r | (conversions.U64.toU51(conversions.U3.toU64(bleU3(input, context))) << u51"48")
        if (context.hasError) {
          return u51"0"
        }
        return r
      }

      def leU52(input: MSZ[B], context: Context): U52 = {
        var r = u52"0"
        r = r | conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"8")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"16")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"24")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"32")
        r = r | (conversions.U64.toU52(conversions.U8.toU64(bleU8(input, context))) << u52"40")
        r = r | (conversions.U64.toU52(conversions.U4.toU64(bleU4(input, context))) << u52"48")
        if (context.hasError) {
          return u52"0"
        }
        return r
      }

      def leU53(input: MSZ[B], context: Context): U53 = {
        var r = u53"0"
        r = r | conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"8")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"16")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"24")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"32")
        r = r | (conversions.U64.toU53(conversions.U8.toU64(bleU8(input, context))) << u53"40")
        r = r | (conversions.U64.toU53(conversions.U5.toU64(bleU5(input, context))) << u53"48")
        if (context.hasError) {
          return u53"0"
        }
        return r
      }

      def leU54(input: MSZ[B], context: Context): U54 = {
        var r = u54"0"
        r = r | conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"8")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"16")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"24")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"32")
        r = r | (conversions.U64.toU54(conversions.U8.toU64(bleU8(input, context))) << u54"40")
        r = r | (conversions.U64.toU54(conversions.U6.toU64(bleU6(input, context))) << u54"48")
        if (context.hasError) {
          return u54"0"
        }
        return r
      }

      def leU55(input: MSZ[B], context: Context): U55 = {
        var r = u55"0"
        r = r | conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"8")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"16")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"24")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"32")
        r = r | (conversions.U64.toU55(conversions.U8.toU64(bleU8(input, context))) << u55"40")
        r = r | (conversions.U64.toU55(conversions.U7.toU64(bleU7(input, context))) << u55"48")
        if (context.hasError) {
          return u55"0"
        }
        return r
      }

      def leU56(input: MSZ[B], context: Context): U56 = {
        var r = u56"0"
        r = r | conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"8")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"16")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"24")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"32")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"40")
        r = r | (conversions.U64.toU56(conversions.U8.toU64(bleU8(input, context))) << u56"48")
        if (context.hasError) {
          return u56"0"
        }
        return r
      }

      def leU57(input: MSZ[B], context: Context): U57 = {
        var r = u57"0"
        r = r | conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"8")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"16")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"24")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"32")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"40")
        r = r | (conversions.U64.toU57(conversions.U8.toU64(bleU8(input, context))) << u57"48")
        r = r | (conversions.U64.toU57(conversions.U1.toU64(bleU1(input, context))) << u57"56")
        if (context.hasError) {
          return u57"0"
        }
        return r
      }

      def leU58(input: MSZ[B], context: Context): U58 = {
        var r = u58"0"
        r = r | conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"8")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"16")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"24")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"32")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"40")
        r = r | (conversions.U64.toU58(conversions.U8.toU64(bleU8(input, context))) << u58"48")
        r = r | (conversions.U64.toU58(conversions.U2.toU64(bleU2(input, context))) << u58"56")
        if (context.hasError) {
          return u58"0"
        }
        return r
      }

      def leU59(input: MSZ[B], context: Context): U59 = {
        var r = u59"0"
        r = r | conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"8")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"16")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"24")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"32")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"40")
        r = r | (conversions.U64.toU59(conversions.U8.toU64(bleU8(input, context))) << u59"48")
        r = r | (conversions.U64.toU59(conversions.U3.toU64(bleU3(input, context))) << u59"56")
        if (context.hasError) {
          return u59"0"
        }
        return r
      }

      def leU60(input: MSZ[B], context: Context): U60 = {
        var r = u60"0"
        r = r | conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"8")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"16")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"24")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"32")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"40")
        r = r | (conversions.U64.toU60(conversions.U8.toU64(bleU8(input, context))) << u60"48")
        r = r | (conversions.U64.toU60(conversions.U4.toU64(bleU4(input, context))) << u60"56")
        if (context.hasError) {
          return u60"0"
        }
        return r
      }

      def leU61(input: MSZ[B], context: Context): U61 = {
        var r = u61"0"
        r = r | conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"8")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"16")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"24")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"32")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"40")
        r = r | (conversions.U64.toU61(conversions.U8.toU64(bleU8(input, context))) << u61"48")
        r = r | (conversions.U64.toU61(conversions.U5.toU64(bleU5(input, context))) << u61"56")
        if (context.hasError) {
          return u61"0"
        }
        return r
      }

      def leU62(input: MSZ[B], context: Context): U62 = {
        var r = u62"0"
        r = r | conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"8")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"16")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"24")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"32")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"40")
        r = r | (conversions.U64.toU62(conversions.U8.toU64(bleU8(input, context))) << u62"48")
        r = r | (conversions.U64.toU62(conversions.U6.toU64(bleU6(input, context))) << u62"56")
        if (context.hasError) {
          return u62"0"
        }
        return r
      }

      def leU63(input: MSZ[B], context: Context): U63 = {
        var r = u63"0"
        r = r | conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context)))
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"8")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"16")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"24")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"32")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"40")
        r = r | (conversions.U64.toU63(conversions.U8.toU64(bleU8(input, context))) << u63"48")
        r = r | (conversions.U64.toU63(conversions.U7.toU64(bleU7(input, context))) << u63"56")
        if (context.hasError) {
          return u63"0"
        }
        return r
      }

      def leU64(input: MSZ[B], context: Context): U64 = {
        val r = conversions.U8.toU64(bleU8(input, context)) |
          (conversions.U8.toU64(bleU8(input, context)) << u64"8") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"16") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"24") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"32") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"40") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"48") |
          (conversions.U8.toU64(bleU8(input, context)) << u64"56")
        if (context.hasError) {
          return u64"0"
        }
        return r
      }

      def leS64(input: MSZ[B], context: Context): S64 = {
        return conversions.U64.toRawS64(leU64(input, context))
      }

      def leF32(input: MSZ[B], context: Context): F32 = {
        return conversions.U32.toRawF32(leU32(input, context))
      }

      def leF64(input: MSZ[B], context: Context): F64 = {
        return conversions.U64.toRawF64(leU64(input, context))
      }
    }  }

  object Writer {
    val INSUFFICIENT_BUFFER_SIZE: Z = 1

    def resultIS(output: ISZ[B], context: Context): ISZ[B] = {
      return ops.ISZOps(output).slice(0, context.offset)
    }

    def resultMS(output: MSZ[B], context: Context): MSZ[B] = {
      return ops.MSZOps(output).slice(0, context.offset)
    }

    def bleB(output: MSZ[B], context: Context, v: B): Unit = {
      val offset = context.offset
      if (offset + 1 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      output(offset) = v
      context.offset = offset + 1
    }

    def beBS(output: MSZ[B], context: Context, v: MSZ[B]): Unit = {
      bleRaw(output, context, v, v.size)
    }

    def leBS(output: MSZ[B], context: Context, v: MSZ[B]): Unit = {
      bleRaw(output, context, v, v.size)
    }

    def bleRaw(output: MSZ[B], context: Context, v: MSZ[B], size: Z): Unit = {
      val offset = context.offset
      if (offset + size > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        bleB(output, context, v(i))
      }
    }

    def bleU1(output: MSZ[B], context: Context, v: U1): Unit = {
      val offset = context.offset
      if (offset + 1 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      output(offset) = v == u1"1"
      context.offset = offset + 1
    }

    def bleU2(output: MSZ[B], context: Context, v: U2): Unit = {
      val offset = context.offset
      if (offset + 2 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u2"1"
      for (i <- 0 until 1) {
        if ((v & mask) != u2"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u2"1"
      }
      if ((v & mask) != u2"0") {
        output(offset + 1) = T
      } else {
        output(offset + 1) = F
      }
      context.offset = offset + 2
    }

    def bleU3(output: MSZ[B], context: Context, v: U3): Unit = {
      val offset = context.offset
      if (offset + 3 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u3"1"
      for (i <- 0 until 2) {
        if ((v & mask) != u3"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u3"1"
      }
      if ((v & mask) != u3"0") {
        output(offset + 2) = T
      } else {
        output(offset + 2) = F
      }
      context.offset = offset + 3
    }

    def bleU4(output: MSZ[B], context: Context, v: U4): Unit = {
      val offset = context.offset
      if (offset + 4 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u4"1"
      for (i <- 0 until 3) {
        if ((v & mask) != u4"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u4"1"
      }
      if ((v & mask) != u4"0") {
        output(offset + 3) = T
      } else {
        output(offset + 3) = F
      }
      context.offset = offset + 4
    }

    def bleU5(output: MSZ[B], context: Context, v: U5): Unit = {
      val offset = context.offset
      if (offset + 5 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u5"1"
      for (i <- 0 until 4) {
        if ((v & mask) != u5"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u5"1"
      }
      if ((v & mask) != u5"0") {
        output(offset + 4) = T
      } else {
        output(offset + 4) = F
      }
      context.offset = offset + 5
    }

    def bleU6(output: MSZ[B], context: Context, v: U6): Unit = {
      val offset = context.offset
      if (offset + 6 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u6"1"
      for (i <- 0 until 5) {
        if ((v & mask) != u6"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u6"1"
      }
      if ((v & mask) != u6"0") {
        output(offset + 5) = T
      } else {
        output(offset + 5) = F
      }
      context.offset = offset + 6
    }

    def bleU7(output: MSZ[B], context: Context, v: U7): Unit = {
      val offset = context.offset
      if (offset + 7 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u7"1"
      for (i <- 0 until 6) {
        if ((v & mask) != u7"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u7"1"
      }
      if ((v & mask) != u7"0") {
        output(offset + 6) = T
      } else {
        output(offset + 6) = F
      }
      context.offset = offset + 7
    }

    def bleU8(output: MSZ[B], context: Context, v: U8): Unit = {
      val offset = context.offset
      if (offset + 8 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u8"1"
      for (i <- 0 until 7) {
        if ((v & mask) != u8"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u8"1"
      }
      if ((v & mask) != u8"0") {
        output(offset + 7) = T
      } else {
        output(offset + 7) = F
      }
      context.offset = offset + 8
    }

    def bleS8(output: MSZ[B], context: Context, v: S8): Unit = {
      bleU8(output, context, conversions.S8.toRawU8(v))
    }

    def beU8S(output: MSZ[B], context: Context, v: MSZ[U8]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 8 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        bleU8(output, context, v(i))
      }
    }

    def beS8S(output: MSZ[B], context: Context, v: MSZ[S8]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 8 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        bleS8(output, context, v(i))
      }
    }

    def beU16S(output: MSZ[B], context: Context, v: MSZ[U16]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 16 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beU16(output, context, v(i))
      }
    }

    def beS16S(output: MSZ[B], context: Context, v: MSZ[S16]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 16 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beS16(output, context, v(i))
      }
    }

    def beU32S(output: MSZ[B], context: Context, v: MSZ[U32]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 32 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beU32(output, context, v(i))
      }
    }

    def beS32S(output: MSZ[B], context: Context, v: MSZ[S32]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 32 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beS32(output, context, v(i))
      }
    }

    def beU64S(output: MSZ[B], context: Context, v: MSZ[U64]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 64 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beU64(output, context, v(i))
      }
    }

    def beS64S(output: MSZ[B], context: Context, v: MSZ[S64]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 64 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beS64(output, context, v(i))
      }
    }

    def beF32S(output: MSZ[B], context: Context, v: MSZ[F32]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 32 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beF32(output, context, v(i))
      }
    }

    def beF64S(output: MSZ[B], context: Context, v: MSZ[F64]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 64 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beF64(output, context, v(i))
      }
    }

    def leU8S(output: MSZ[B], context: Context, v: MSZ[U8]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 8 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        bleU8(output, context, v(i))
      }
    }

    def leS8S(output: MSZ[B], context: Context, v: MSZ[S8]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 8 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        bleS8(output, context, v(i))
      }
    }

    def leU16S(output: MSZ[B], context: Context, v: MSZ[U16]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 16 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leU16(output, context, v(i))
      }
    }

    def leS16S(output: MSZ[B], context: Context, v: MSZ[S16]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 16 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leS16(output, context, v(i))
      }
    }

    def leU32S(output: MSZ[B], context: Context, v: MSZ[U32]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 32 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leU32(output, context, v(i))
      }
    }

    def leS32S(output: MSZ[B], context: Context, v: MSZ[S32]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 32 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leS32(output, context, v(i))
      }
    }

    def leU64S(output: MSZ[B], context: Context, v: MSZ[U64]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 64 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leU64(output, context, v(i))
      }
    }

    def leS64S(output: MSZ[B], context: Context, v: MSZ[S64]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 64 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leS64(output, context, v(i))
      }
    }

    def leF32S(output: MSZ[B], context: Context, v: MSZ[F32]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 32 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leF32(output, context, v(i))
      }
    }

    def leF64S(output: MSZ[B], context: Context, v: MSZ[F64]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 64 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leF64(output, context, v(i))
      }
    }

    // Slang script gen:
    /*
    for (i <- 9 to 15) {
      println(
        st"""def beU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU${i - 8}(output, context, conversions.U8.toU${i - 16}(conversions.U16.toU8(conversions.U$i.toU16(v >> u$i"8"))))
            |  bleU8(output, context, conversions.U16.toU8(conversions.U$i.toU32(v & u$i"0xFF")))
            |}
            |""".render)
    }

    for (i <- 17 to 24) {
      println(
        st"""def beU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU${i - 16}(output, context, conversions.U8.toU${i - 16}(conversions.U32.toU8(conversions.U$i.toU32(v >> u$i"16"))))
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32(v & u$i"0xFF")))
            |}
            |""".render)
    }

    for (i <- 25 to 31) {
      println(
        st"""def beU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU${i - 24}(output, context, conversions.U8.toU${i - 24}(conversions.U32.toU8(conversions.U$i.toU32(v >> u$i"24"))))
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32((v >> u$i"16") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32(v & u$i"0xFF")))
            |}
            |""".render)
    }

    for (i <- 33 to 40) {
      println(
        st"""def beU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU${i - 32}(output, context, conversions.U8.toU${i - 32}(conversions.U64.toU8(conversions.U$i.toU64(v >> u$i"32"))))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"24") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"16") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64(v & u$i"0xFF")))
            |}
            |""".render)
    }

    for (i <- 41 to 48) {
      println(
        st"""def beU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU${i - 40}(output, context, conversions.U8.toU${i - 40}(conversions.U64.toU8(conversions.U$i.toU64(v >> u$i"40"))))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"32") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"24") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"16") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64(v & u$i"0xFF")))
            |}
            |""".render)
    }

    for (i <- 49 to 56) {
      println(
        st"""def beU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU${i - 48}(output, context, conversions.U8.toU${i - 48}(conversions.U64.toU8(conversions.U$i.toU64(v >> u$i"48"))))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"40") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"32") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"24") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"16") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64(v & u$i"0xFF")))
            |}
            |""".render)
    }

    for (i <- 57 to 63) {
      println(
        st"""def beU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU${i - 56}(output, context, conversions.U8.toU${i - 56}(conversions.U64.toU8(conversions.U$i.toU64(v >> u$i"56"))))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"48") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"40") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"32") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"24") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"16") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64(v & u$i"0xFF")))
            |}
            |""".render)
    }
    */
    def beU9(output: MSZ[B], context: Context, v: U9): Unit = {
      bleU1(output, context, conversions.U8.toU1(conversions.U16.toU8(conversions.U9.toU16(v >> u9"8"))))
      bleU8(output, context, conversions.U16.toU8(conversions.U9.toU16(v & u9"0xFF")))
    }

    def beU10(output: MSZ[B], context: Context, v: U10): Unit = {
      bleU2(output, context, conversions.U8.toU2(conversions.U16.toU8(conversions.U10.toU16(v >> u10"8"))))
      bleU8(output, context, conversions.U16.toU8(conversions.U10.toU16(v & u10"0xFF")))
    }

    def beU11(output: MSZ[B], context: Context, v: U11): Unit = {
      bleU3(output, context, conversions.U8.toU3(conversions.U16.toU8(conversions.U11.toU16(v >> u11"8"))))
      bleU8(output, context, conversions.U16.toU8(conversions.U11.toU16(v & u11"0xFF")))
    }

    def beU12(output: MSZ[B], context: Context, v: U12): Unit = {
      bleU4(output, context, conversions.U8.toU4(conversions.U16.toU8(conversions.U12.toU16(v >> u12"8"))))
      bleU8(output, context, conversions.U16.toU8(conversions.U12.toU16(v & u12"0xFF")))
    }

    def beU13(output: MSZ[B], context: Context, v: U13): Unit = {
      bleU5(output, context, conversions.U8.toU5(conversions.U16.toU8(conversions.U13.toU16(v >> u13"8"))))
      bleU8(output, context, conversions.U16.toU8(conversions.U13.toU16(v & u13"0xFF")))
    }

    def beU14(output: MSZ[B], context: Context, v: U14): Unit = {
      bleU6(output, context, conversions.U8.toU6(conversions.U16.toU8(conversions.U14.toU16(v >> u14"8"))))
      bleU8(output, context, conversions.U16.toU8(conversions.U14.toU16(v & u14"0xFF")))
    }

    def beU15(output: MSZ[B], context: Context, v: U15): Unit = {
      bleU7(output, context, conversions.U8.toU7(conversions.U16.toU8(conversions.U15.toU16(v >> u15"8"))))
      bleU8(output, context, conversions.U16.toU8(conversions.U15.toU16(v & u15"0xFF")))
    }

    def beU16(output: MSZ[B], context: Context, v: U16): Unit = {
      bleU8(output, context, conversions.U16.toU8(v >> u16"8"))
      bleU8(output, context, conversions.U16.toU8(v & u16"0xFF"))
    }

    def beS16(output: MSZ[B], context: Context, v: S16): Unit = {
      beU16(output, context, conversions.S16.toRawU16(v))
    }

    def beU17(output: MSZ[B], context: Context, v: U17): Unit = {
      bleU1(output, context, conversions.U8.toU1(conversions.U32.toU8(conversions.U17.toU32(v >> u17"16"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U17.toU32((v >> u17"8") & u17"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U17.toU32(v & u17"0xFF")))
    }

    def beU18(output: MSZ[B], context: Context, v: U18): Unit = {
      bleU2(output, context, conversions.U8.toU2(conversions.U32.toU8(conversions.U18.toU32(v >> u18"16"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U18.toU32((v >> u18"8") & u18"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U18.toU32(v & u18"0xFF")))
    }

    def beU19(output: MSZ[B], context: Context, v: U19): Unit = {
      bleU3(output, context, conversions.U8.toU3(conversions.U32.toU8(conversions.U19.toU32(v >> u19"16"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U19.toU32((v >> u19"8") & u19"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U19.toU32(v & u19"0xFF")))
    }

    def beU20(output: MSZ[B], context: Context, v: U20): Unit = {
      bleU4(output, context, conversions.U8.toU4(conversions.U32.toU8(conversions.U20.toU32(v >> u20"16"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U20.toU32((v >> u20"8") & u20"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U20.toU32(v & u20"0xFF")))
    }

    def beU21(output: MSZ[B], context: Context, v: U21): Unit = {
      bleU5(output, context, conversions.U8.toU5(conversions.U32.toU8(conversions.U21.toU32(v >> u21"16"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U21.toU32((v >> u21"8") & u21"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U21.toU32(v & u21"0xFF")))
    }

    def beU22(output: MSZ[B], context: Context, v: U22): Unit = {
      bleU6(output, context, conversions.U8.toU6(conversions.U32.toU8(conversions.U22.toU32(v >> u22"16"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U22.toU32((v >> u22"8") & u22"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U22.toU32(v & u22"0xFF")))
    }

    def beU23(output: MSZ[B], context: Context, v: U23): Unit = {
      bleU7(output, context, conversions.U8.toU7(conversions.U32.toU8(conversions.U23.toU32(v >> u23"16"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U23.toU32((v >> u23"8") & u23"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U23.toU32(v & u23"0xFF")))
    }

    def beU24(output: MSZ[B], context: Context, v: U24): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U24.toU32(v >> u24"16")))
      bleU8(output, context, conversions.U32.toU8(conversions.U24.toU32((v >> u24"8") & u24"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U24.toU32(v & u24"0xFF")))
    }

    def beU25(output: MSZ[B], context: Context, v: U25): Unit = {
      bleU1(output, context, conversions.U8.toU1(conversions.U32.toU8(conversions.U25.toU32(v >> u25"24"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U25.toU32((v >> u25"16") & u25"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U25.toU32((v >> u25"8") & u25"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U25.toU32(v & u25"0xFF")))
    }

    def beU26(output: MSZ[B], context: Context, v: U26): Unit = {
      bleU2(output, context, conversions.U8.toU2(conversions.U32.toU8(conversions.U26.toU32(v >> u26"24"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U26.toU32((v >> u26"16") & u26"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U26.toU32((v >> u26"8") & u26"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U26.toU32(v & u26"0xFF")))
    }

    def beU27(output: MSZ[B], context: Context, v: U27): Unit = {
      bleU3(output, context, conversions.U8.toU3(conversions.U32.toU8(conversions.U27.toU32(v >> u27"24"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U27.toU32((v >> u27"16") & u27"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U27.toU32((v >> u27"8") & u27"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U27.toU32(v & u27"0xFF")))
    }

    def beU28(output: MSZ[B], context: Context, v: U28): Unit = {
      bleU4(output, context, conversions.U8.toU4(conversions.U32.toU8(conversions.U28.toU32(v >> u28"24"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U28.toU32((v >> u28"16") & u28"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U28.toU32((v >> u28"8") & u28"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U28.toU32(v & u28"0xFF")))
    }

    def beU29(output: MSZ[B], context: Context, v: U29): Unit = {
      bleU5(output, context, conversions.U8.toU5(conversions.U32.toU8(conversions.U29.toU32(v >> u29"24"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U29.toU32((v >> u29"16") & u29"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U29.toU32((v >> u29"8") & u29"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U29.toU32(v & u29"0xFF")))
    }

    def beU30(output: MSZ[B], context: Context, v: U30): Unit = {
      bleU6(output, context, conversions.U8.toU6(conversions.U32.toU8(conversions.U30.toU32(v >> u30"24"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U30.toU32((v >> u30"16") & u30"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U30.toU32((v >> u30"8") & u30"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U30.toU32(v & u30"0xFF")))
    }

    def beU31(output: MSZ[B], context: Context, v: U31): Unit = {
      bleU7(output, context, conversions.U8.toU7(conversions.U32.toU8(conversions.U31.toU32(v >> u31"24"))))
      bleU8(output, context, conversions.U32.toU8(conversions.U31.toU32((v >> u31"16") & u31"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U31.toU32((v >> u31"8") & u31"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U31.toU32(v & u31"0xFF")))
    }

    def beU32(output: MSZ[B], context: Context, v: U32): Unit = {
      bleU8(output, context, conversions.U32.toU8(v >> u32"24"))
      bleU8(output, context, conversions.U32.toU8((v >> u32"16") & u32"0xFF"))
      bleU8(output, context, conversions.U32.toU8((v >> u32"8") & u32"0xFF"))
      bleU8(output, context, conversions.U32.toU8(v & u32"0xFF"))
    }

    def beS32(output: MSZ[B], context: Context, v: S32): Unit = {
      beU32(output, context, conversions.S32.toRawU32(v))
    }

    def beU33(output: MSZ[B], context: Context, v: U33): Unit = {
      bleU1(output, context, conversions.U8.toU1(conversions.U64.toU8(conversions.U33.toU64(v >> u33"32"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U33.toU64((v >> u33"24") & u33"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U33.toU64((v >> u33"16") & u33"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U33.toU64((v >> u33"8") & u33"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U33.toU64(v & u33"0xFF")))
    }

    def beU34(output: MSZ[B], context: Context, v: U34): Unit = {
      bleU2(output, context, conversions.U8.toU2(conversions.U64.toU8(conversions.U34.toU64(v >> u34"32"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U34.toU64((v >> u34"24") & u34"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U34.toU64((v >> u34"16") & u34"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U34.toU64((v >> u34"8") & u34"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U34.toU64(v & u34"0xFF")))
    }

    def beU35(output: MSZ[B], context: Context, v: U35): Unit = {
      bleU3(output, context, conversions.U8.toU3(conversions.U64.toU8(conversions.U35.toU64(v >> u35"32"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U35.toU64((v >> u35"24") & u35"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U35.toU64((v >> u35"16") & u35"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U35.toU64((v >> u35"8") & u35"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U35.toU64(v & u35"0xFF")))
    }

    def beU36(output: MSZ[B], context: Context, v: U36): Unit = {
      bleU4(output, context, conversions.U8.toU4(conversions.U64.toU8(conversions.U36.toU64(v >> u36"32"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U36.toU64((v >> u36"24") & u36"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U36.toU64((v >> u36"16") & u36"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U36.toU64((v >> u36"8") & u36"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U36.toU64(v & u36"0xFF")))
    }

    def beU37(output: MSZ[B], context: Context, v: U37): Unit = {
      bleU5(output, context, conversions.U8.toU5(conversions.U64.toU8(conversions.U37.toU64(v >> u37"32"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U37.toU64((v >> u37"24") & u37"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U37.toU64((v >> u37"16") & u37"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U37.toU64((v >> u37"8") & u37"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U37.toU64(v & u37"0xFF")))
    }

    def beU38(output: MSZ[B], context: Context, v: U38): Unit = {
      bleU6(output, context, conversions.U8.toU6(conversions.U64.toU8(conversions.U38.toU64(v >> u38"32"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U38.toU64((v >> u38"24") & u38"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U38.toU64((v >> u38"16") & u38"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U38.toU64((v >> u38"8") & u38"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U38.toU64(v & u38"0xFF")))
    }

    def beU39(output: MSZ[B], context: Context, v: U39): Unit = {
      bleU7(output, context, conversions.U8.toU7(conversions.U64.toU8(conversions.U39.toU64(v >> u39"32"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U39.toU64((v >> u39"24") & u39"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U39.toU64((v >> u39"16") & u39"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U39.toU64((v >> u39"8") & u39"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U39.toU64(v & u39"0xFF")))
    }

    def beU40(output: MSZ[B], context: Context, v: U40): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64(v >> u40"32")))
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64((v >> u40"24") & u40"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64((v >> u40"16") & u40"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64((v >> u40"8") & u40"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64(v & u40"0xFF")))
    }

    def beU41(output: MSZ[B], context: Context, v: U41): Unit = {
      bleU1(output, context, conversions.U8.toU1(conversions.U64.toU8(conversions.U41.toU64(v >> u41"40"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64((v >> u41"32") & u41"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64((v >> u41"24") & u41"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64((v >> u41"16") & u41"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64((v >> u41"8") & u41"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64(v & u41"0xFF")))
    }

    def beU42(output: MSZ[B], context: Context, v: U42): Unit = {
      bleU2(output, context, conversions.U8.toU2(conversions.U64.toU8(conversions.U42.toU64(v >> u42"40"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64((v >> u42"32") & u42"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64((v >> u42"24") & u42"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64((v >> u42"16") & u42"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64((v >> u42"8") & u42"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64(v & u42"0xFF")))
    }

    def beU43(output: MSZ[B], context: Context, v: U43): Unit = {
      bleU3(output, context, conversions.U8.toU3(conversions.U64.toU8(conversions.U43.toU64(v >> u43"40"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64((v >> u43"32") & u43"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64((v >> u43"24") & u43"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64((v >> u43"16") & u43"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64((v >> u43"8") & u43"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64(v & u43"0xFF")))
    }

    def beU44(output: MSZ[B], context: Context, v: U44): Unit = {
      bleU4(output, context, conversions.U8.toU4(conversions.U64.toU8(conversions.U44.toU64(v >> u44"40"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64((v >> u44"32") & u44"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64((v >> u44"24") & u44"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64((v >> u44"16") & u44"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64((v >> u44"8") & u44"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64(v & u44"0xFF")))
    }

    def beU45(output: MSZ[B], context: Context, v: U45): Unit = {
      bleU5(output, context, conversions.U8.toU5(conversions.U64.toU8(conversions.U45.toU64(v >> u45"40"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64((v >> u45"32") & u45"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64((v >> u45"24") & u45"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64((v >> u45"16") & u45"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64((v >> u45"8") & u45"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64(v & u45"0xFF")))
    }

    def beU46(output: MSZ[B], context: Context, v: U46): Unit = {
      bleU6(output, context, conversions.U8.toU6(conversions.U64.toU8(conversions.U46.toU64(v >> u46"40"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64((v >> u46"32") & u46"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64((v >> u46"24") & u46"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64((v >> u46"16") & u46"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64((v >> u46"8") & u46"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64(v & u46"0xFF")))
    }

    def beU47(output: MSZ[B], context: Context, v: U47): Unit = {
      bleU7(output, context, conversions.U8.toU7(conversions.U64.toU8(conversions.U47.toU64(v >> u47"40"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64((v >> u47"32") & u47"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64((v >> u47"24") & u47"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64((v >> u47"16") & u47"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64((v >> u47"8") & u47"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64(v & u47"0xFF")))
    }

    def beU48(output: MSZ[B], context: Context, v: U48): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64(v >> u48"40")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64((v >> u48"32") & u48"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64((v >> u48"24") & u48"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64((v >> u48"16") & u48"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64((v >> u48"8") & u48"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64(v & u48"0xFF")))
    }

    def beU49(output: MSZ[B], context: Context, v: U49): Unit = {
      bleU1(output, context, conversions.U8.toU1(conversions.U64.toU8(conversions.U49.toU64(v >> u49"48"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"40") & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"32") & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"24") & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"16") & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"8") & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64(v & u49"0xFF")))
    }

    def beU50(output: MSZ[B], context: Context, v: U50): Unit = {
      bleU2(output, context, conversions.U8.toU2(conversions.U64.toU8(conversions.U50.toU64(v >> u50"48"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"40") & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"32") & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"24") & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"16") & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"8") & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64(v & u50"0xFF")))
    }

    def beU51(output: MSZ[B], context: Context, v: U51): Unit = {
      bleU3(output, context, conversions.U8.toU3(conversions.U64.toU8(conversions.U51.toU64(v >> u51"48"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"40") & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"32") & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"24") & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"16") & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"8") & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64(v & u51"0xFF")))
    }

    def beU52(output: MSZ[B], context: Context, v: U52): Unit = {
      bleU4(output, context, conversions.U8.toU4(conversions.U64.toU8(conversions.U52.toU64(v >> u52"48"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"40") & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"32") & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"24") & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"16") & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"8") & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64(v & u52"0xFF")))
    }

    def beU53(output: MSZ[B], context: Context, v: U53): Unit = {
      bleU5(output, context, conversions.U8.toU5(conversions.U64.toU8(conversions.U53.toU64(v >> u53"48"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"40") & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"32") & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"24") & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"16") & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"8") & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64(v & u53"0xFF")))
    }

    def beU54(output: MSZ[B], context: Context, v: U54): Unit = {
      bleU6(output, context, conversions.U8.toU6(conversions.U64.toU8(conversions.U54.toU64(v >> u54"48"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"40") & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"32") & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"24") & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"16") & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"8") & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64(v & u54"0xFF")))
    }

    def beU55(output: MSZ[B], context: Context, v: U55): Unit = {
      bleU7(output, context, conversions.U8.toU7(conversions.U64.toU8(conversions.U55.toU64(v >> u55"48"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"40") & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"32") & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"24") & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"16") & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"8") & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64(v & u55"0xFF")))
    }

    def beU56(output: MSZ[B], context: Context, v: U56): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64(v >> u56"48")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"40") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"32") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"24") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"16") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"8") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64(v & u56"0xFF")))
    }

    def beU57(output: MSZ[B], context: Context, v: U57): Unit = {
      bleU1(output, context, conversions.U8.toU1(conversions.U64.toU8(conversions.U57.toU64(v >> u57"56"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"48") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"40") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"32") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"24") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"16") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"8") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64(v & u57"0xFF")))
    }

    def beU58(output: MSZ[B], context: Context, v: U58): Unit = {
      bleU2(output, context, conversions.U8.toU2(conversions.U64.toU8(conversions.U58.toU64(v >> u58"56"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"48") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"40") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"32") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"24") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"16") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"8") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64(v & u58"0xFF")))
    }

    def beU59(output: MSZ[B], context: Context, v: U59): Unit = {
      bleU3(output, context, conversions.U8.toU3(conversions.U64.toU8(conversions.U59.toU64(v >> u59"56"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"48") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"40") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"32") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"24") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"16") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"8") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64(v & u59"0xFF")))
    }

    def beU60(output: MSZ[B], context: Context, v: U60): Unit = {
      bleU4(output, context, conversions.U8.toU4(conversions.U64.toU8(conversions.U60.toU64(v >> u60"56"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"48") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"40") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"32") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"24") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"16") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"8") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64(v & u60"0xFF")))
    }

    def beU61(output: MSZ[B], context: Context, v: U61): Unit = {
      bleU5(output, context, conversions.U8.toU5(conversions.U64.toU8(conversions.U61.toU64(v >> u61"56"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"48") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"40") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"32") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"24") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"16") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"8") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64(v & u61"0xFF")))
    }

    def beU62(output: MSZ[B], context: Context, v: U62): Unit = {
      bleU6(output, context, conversions.U8.toU6(conversions.U64.toU8(conversions.U62.toU64(v >> u62"56"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"48") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"40") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"32") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"24") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"16") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"8") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64(v & u62"0xFF")))
    }

    def beU63(output: MSZ[B], context: Context, v: U63): Unit = {
      bleU7(output, context, conversions.U8.toU7(conversions.U64.toU8(conversions.U63.toU64(v >> u63"56"))))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"48") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"40") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"32") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"24") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"16") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"8") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64(v & u63"0xFF")))
    }

    def beU64(output: MSZ[B], context: Context, v: U64): Unit = {
      bleU8(output, context, conversions.U64.toU8(v >> u64"56"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"48") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"40") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"32") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"24") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"16") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"8") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8(v & u64"0xFF"))
    }

    def beS64(output: MSZ[B], context: Context, v: S64): Unit = {
      beU64(output, context, conversions.S64.toRawU64(v))
    }

    def beF32(output: MSZ[B], context: Context, v: F32): Unit = {
      beU32(output, context, conversions.F32.toRawU32(v))
    }

    def beF64(output: MSZ[B], context: Context, v: F64): Unit = {
      beU64(output, context, conversions.F64.toRawU64(v))
    }

    // Slang script gen:
    /*
    for (i <- 9 to 15) {
      println(
        st"""def leU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU8(output, context, conversions.U16.toU8(conversions.U$i.toU32(v & u$i"0xFF")))
            |  bleU${i - 8}(output, context, conversions.U8.toU${i - 16}(conversions.U16.toU8(conversions.U$i.toU16(v >> u$i"8"))))
            |}
            |""".render)
    }

    for (i <- 17 to 24) {
      println(
        st"""def leU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32(v & u$i"0xFF")))
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32((v >> u$i"8") & u$i"0xFF")))
            |  bleU${i - 16}(output, context, conversions.U8.toU${i - 16}(conversions.U32.toU8(conversions.U$i.toU32(v >> u$i"16"))))
            |}
            |""".render)
    }

    for (i <- 25 to 31) {
      println(
        st"""def leU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32(v & u$i"0xFF")))
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U32.toU8(conversions.U$i.toU32((v >> u$i"16") & u$i"0xFF")))
            |  bleU${i - 24}(output, context, conversions.U8.toU${i - 24}(conversions.U32.toU8(conversions.U$i.toU32(v >> u$i"24"))))
            |}
            |""".render)
    }

    for (i <- 33 to 40) {
      println(
        st"""def leU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64(v & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"16") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"24") & u$i"0xFF")))
            |  bleU${i - 32}(output, context, conversions.U8.toU${i - 32}(conversions.U64.toU8(conversions.U$i.toU64(v >> u$i"32"))))
            |}
            |""".render)
    }

    for (i <- 41 to 48) {
      println(
        st"""def leU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64(v & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"16") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"24") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"32") & u$i"0xFF")))
            |  bleU${i - 40}(output, context, conversions.U8.toU${i - 40}(conversions.U64.toU8(conversions.U$i.toU64(v >> u$i"40"))))
            |}
            |""".render)
    }

    for (i <- 49 to 56) {
      println(
        st"""def leU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64(v & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"16") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"24") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"32") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"40") & u$i"0xFF")))
            |  bleU${i - 48}(output, context, conversions.U8.toU${i - 48}(conversions.U64.toU8(conversions.U$i.toU64(v >> u$i"48"))))
            |}
            |""".render)
    }

    for (i <- 57 to 63) {
      println(
        st"""def leU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64(v & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"8") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"16") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"24") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"32") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"40") & u$i"0xFF")))
            |  bleU8(output, context, conversions.U64.toU8(conversions.U$i.toU64((v >> u$i"48") & u$i"0xFF")))
            |  bleU${i - 56}(output, context, conversions.U8.toU${i - 56}(conversions.U64.toU8(conversions.U$i.toU64(v >> u$i"56"))))
            |}
            |""".render)
    }
     */
    def leU9(output: MSZ[B], context: Context, v: U9): Unit = {
      bleU8(output, context, conversions.U16.toU8(conversions.U9.toU16(v & u9"0xFF")))
      bleU1(output, context, conversions.U8.toU1(conversions.U16.toU8(conversions.U9.toU16(v >> u9"8"))))
    }

    def leU10(output: MSZ[B], context: Context, v: U10): Unit = {
      bleU8(output, context, conversions.U16.toU8(conversions.U10.toU16(v & u10"0xFF")))
      bleU2(output, context, conversions.U8.toU2(conversions.U16.toU8(conversions.U10.toU16(v >> u10"8"))))
    }

    def leU11(output: MSZ[B], context: Context, v: U11): Unit = {
      bleU8(output, context, conversions.U16.toU8(conversions.U11.toU16(v & u11"0xFF")))
      bleU3(output, context, conversions.U8.toU3(conversions.U16.toU8(conversions.U11.toU16(v >> u11"8"))))
    }

    def leU12(output: MSZ[B], context: Context, v: U12): Unit = {
      bleU8(output, context, conversions.U16.toU8(conversions.U12.toU16(v & u12"0xFF")))
      bleU4(output, context, conversions.U8.toU4(conversions.U16.toU8(conversions.U12.toU16(v >> u12"8"))))
    }

    def leU13(output: MSZ[B], context: Context, v: U13): Unit = {
      bleU8(output, context, conversions.U16.toU8(conversions.U13.toU16(v & u13"0xFF")))
      bleU5(output, context, conversions.U8.toU5(conversions.U16.toU8(conversions.U13.toU16(v >> u13"8"))))
    }

    def leU14(output: MSZ[B], context: Context, v: U14): Unit = {
      bleU8(output, context, conversions.U16.toU8(conversions.U14.toU16(v & u14"0xFF")))
      bleU6(output, context, conversions.U8.toU6(conversions.U16.toU8(conversions.U14.toU16(v >> u14"8"))))
    }

    def leU15(output: MSZ[B], context: Context, v: U15): Unit = {
      bleU8(output, context, conversions.U16.toU8(conversions.U15.toU16(v & u15"0xFF")))
      bleU7(output, context, conversions.U8.toU7(conversions.U16.toU8(conversions.U15.toU16(v >> u15"8"))))
    }

    def leU16(output: MSZ[B], context: Context, v: U16): Unit = {
      bleU8(output, context, conversions.U16.toU8(v & u16"0xFF"))
      bleU8(output, context, conversions.U16.toU8(v >> u16"8"))
    }

    def leS16(output: MSZ[B], context: Context, v: S16): Unit = {
      leU16(output, context, conversions.S16.toRawU16(v))
    }

    def leU17(output: MSZ[B], context: Context, v: U17): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U17.toU32(v & u17"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U17.toU32((v >> u17"8") & u17"0xFF")))
      bleU1(output, context, conversions.U8.toU1(conversions.U32.toU8(conversions.U17.toU32(v >> u17"16"))))
    }

    def leU18(output: MSZ[B], context: Context, v: U18): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U18.toU32(v & u18"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U18.toU32((v >> u18"8") & u18"0xFF")))
      bleU2(output, context, conversions.U8.toU2(conversions.U32.toU8(conversions.U18.toU32(v >> u18"16"))))
    }

    def leU19(output: MSZ[B], context: Context, v: U19): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U19.toU32(v & u19"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U19.toU32((v >> u19"8") & u19"0xFF")))
      bleU3(output, context, conversions.U8.toU3(conversions.U32.toU8(conversions.U19.toU32(v >> u19"16"))))
    }

    def leU20(output: MSZ[B], context: Context, v: U20): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U20.toU32(v & u20"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U20.toU32((v >> u20"8") & u20"0xFF")))
      bleU4(output, context, conversions.U8.toU4(conversions.U32.toU8(conversions.U20.toU32(v >> u20"16"))))
    }

    def leU21(output: MSZ[B], context: Context, v: U21): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U21.toU32(v & u21"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U21.toU32((v >> u21"8") & u21"0xFF")))
      bleU5(output, context, conversions.U8.toU5(conversions.U32.toU8(conversions.U21.toU32(v >> u21"16"))))
    }

    def leU22(output: MSZ[B], context: Context, v: U22): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U22.toU32(v & u22"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U22.toU32((v >> u22"8") & u22"0xFF")))
      bleU6(output, context, conversions.U8.toU6(conversions.U32.toU8(conversions.U22.toU32(v >> u22"16"))))
    }

    def leU23(output: MSZ[B], context: Context, v: U23): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U23.toU32(v & u23"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U23.toU32((v >> u23"8") & u23"0xFF")))
      bleU7(output, context, conversions.U8.toU7(conversions.U32.toU8(conversions.U23.toU32(v >> u23"16"))))
    }

    def leU24(output: MSZ[B], context: Context, v: U24): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U24.toU32(v & u24"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U24.toU32((v >> u24"8") & u24"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U24.toU32(v >> u24"16")))
    }

    def leU25(output: MSZ[B], context: Context, v: U25): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U25.toU32(v & u25"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U25.toU32((v >> u25"8") & u25"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U25.toU32((v >> u25"16") & u25"0xFF")))
      bleU1(output, context, conversions.U8.toU1(conversions.U32.toU8(conversions.U25.toU32(v >> u25"24"))))
    }

    def leU26(output: MSZ[B], context: Context, v: U26): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U26.toU32(v & u26"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U26.toU32((v >> u26"8") & u26"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U26.toU32((v >> u26"16") & u26"0xFF")))
      bleU2(output, context, conversions.U8.toU2(conversions.U32.toU8(conversions.U26.toU32(v >> u26"24"))))
    }

    def leU27(output: MSZ[B], context: Context, v: U27): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U27.toU32(v & u27"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U27.toU32((v >> u27"8") & u27"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U27.toU32((v >> u27"16") & u27"0xFF")))
      bleU3(output, context, conversions.U8.toU3(conversions.U32.toU8(conversions.U27.toU32(v >> u27"24"))))
    }

    def leU28(output: MSZ[B], context: Context, v: U28): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U28.toU32(v & u28"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U28.toU32((v >> u28"8") & u28"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U28.toU32((v >> u28"16") & u28"0xFF")))
      bleU4(output, context, conversions.U8.toU4(conversions.U32.toU8(conversions.U28.toU32(v >> u28"24"))))
    }

    def leU29(output: MSZ[B], context: Context, v: U29): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U29.toU32(v & u29"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U29.toU32((v >> u29"8") & u29"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U29.toU32((v >> u29"16") & u29"0xFF")))
      bleU5(output, context, conversions.U8.toU5(conversions.U32.toU8(conversions.U29.toU32(v >> u29"24"))))
    }

    def leU30(output: MSZ[B], context: Context, v: U30): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U30.toU32(v & u30"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U30.toU32((v >> u30"8") & u30"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U30.toU32((v >> u30"16") & u30"0xFF")))
      bleU6(output, context, conversions.U8.toU6(conversions.U32.toU8(conversions.U30.toU32(v >> u30"24"))))
    }

    def leU31(output: MSZ[B], context: Context, v: U31): Unit = {
      bleU8(output, context, conversions.U32.toU8(conversions.U31.toU32(v & u31"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U31.toU32((v >> u31"8") & u31"0xFF")))
      bleU8(output, context, conversions.U32.toU8(conversions.U31.toU32((v >> u31"16") & u31"0xFF")))
      bleU7(output, context, conversions.U8.toU7(conversions.U32.toU8(conversions.U31.toU32(v >> u31"24"))))
    }

    def leU32(output: MSZ[B], context: Context, v: U32): Unit = {
      bleU8(output, context, conversions.U32.toU8(v & u32"0xFF"))
      bleU8(output, context, conversions.U32.toU8((v >> u32"8") & u32"0xFF"))
      bleU8(output, context, conversions.U32.toU8((v >> u32"16") & u32"0xFF"))
      bleU8(output, context, conversions.U32.toU8(v >> u32"24"))
    }

    def leS32(output: MSZ[B], context: Context, v: S32): Unit = {
      leU32(output, context, conversions.S32.toRawU32(v))
    }

    def leU33(output: MSZ[B], context: Context, v: U33): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U33.toU64(v & u33"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U33.toU64((v >> u33"8") & u33"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U33.toU64((v >> u33"16") & u33"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U33.toU64((v >> u33"24") & u33"0xFF")))
      bleU1(output, context, conversions.U8.toU1(conversions.U64.toU8(conversions.U33.toU64(v >> u33"32"))))
    }

    def leU34(output: MSZ[B], context: Context, v: U34): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U34.toU64(v & u34"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U34.toU64((v >> u34"8") & u34"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U34.toU64((v >> u34"16") & u34"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U34.toU64((v >> u34"24") & u34"0xFF")))
      bleU2(output, context, conversions.U8.toU2(conversions.U64.toU8(conversions.U34.toU64(v >> u34"32"))))
    }

    def leU35(output: MSZ[B], context: Context, v: U35): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U35.toU64(v & u35"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U35.toU64((v >> u35"8") & u35"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U35.toU64((v >> u35"16") & u35"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U35.toU64((v >> u35"24") & u35"0xFF")))
      bleU3(output, context, conversions.U8.toU3(conversions.U64.toU8(conversions.U35.toU64(v >> u35"32"))))
    }

    def leU36(output: MSZ[B], context: Context, v: U36): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U36.toU64(v & u36"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U36.toU64((v >> u36"8") & u36"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U36.toU64((v >> u36"16") & u36"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U36.toU64((v >> u36"24") & u36"0xFF")))
      bleU4(output, context, conversions.U8.toU4(conversions.U64.toU8(conversions.U36.toU64(v >> u36"32"))))
    }

    def leU37(output: MSZ[B], context: Context, v: U37): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U37.toU64(v & u37"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U37.toU64((v >> u37"8") & u37"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U37.toU64((v >> u37"16") & u37"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U37.toU64((v >> u37"24") & u37"0xFF")))
      bleU5(output, context, conversions.U8.toU5(conversions.U64.toU8(conversions.U37.toU64(v >> u37"32"))))
    }

    def leU38(output: MSZ[B], context: Context, v: U38): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U38.toU64(v & u38"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U38.toU64((v >> u38"8") & u38"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U38.toU64((v >> u38"16") & u38"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U38.toU64((v >> u38"24") & u38"0xFF")))
      bleU6(output, context, conversions.U8.toU6(conversions.U64.toU8(conversions.U38.toU64(v >> u38"32"))))
    }

    def leU39(output: MSZ[B], context: Context, v: U39): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U39.toU64(v & u39"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U39.toU64((v >> u39"8") & u39"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U39.toU64((v >> u39"16") & u39"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U39.toU64((v >> u39"24") & u39"0xFF")))
      bleU7(output, context, conversions.U8.toU7(conversions.U64.toU8(conversions.U39.toU64(v >> u39"32"))))
    }

    def leU40(output: MSZ[B], context: Context, v: U40): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64(v & u40"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64((v >> u40"8") & u40"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64((v >> u40"16") & u40"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64((v >> u40"24") & u40"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U40.toU64(v >> u40"32")))
    }

    def leU41(output: MSZ[B], context: Context, v: U41): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64(v & u41"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64((v >> u41"8") & u41"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64((v >> u41"16") & u41"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64((v >> u41"24") & u41"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U41.toU64((v >> u41"32") & u41"0xFF")))
      bleU1(output, context, conversions.U8.toU1(conversions.U64.toU8(conversions.U41.toU64(v >> u41"40"))))
    }

    def leU42(output: MSZ[B], context: Context, v: U42): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64(v & u42"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64((v >> u42"8") & u42"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64((v >> u42"16") & u42"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64((v >> u42"24") & u42"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U42.toU64((v >> u42"32") & u42"0xFF")))
      bleU2(output, context, conversions.U8.toU2(conversions.U64.toU8(conversions.U42.toU64(v >> u42"40"))))
    }

    def leU43(output: MSZ[B], context: Context, v: U43): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64(v & u43"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64((v >> u43"8") & u43"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64((v >> u43"16") & u43"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64((v >> u43"24") & u43"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U43.toU64((v >> u43"32") & u43"0xFF")))
      bleU3(output, context, conversions.U8.toU3(conversions.U64.toU8(conversions.U43.toU64(v >> u43"40"))))
    }

    def leU44(output: MSZ[B], context: Context, v: U44): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64(v & u44"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64((v >> u44"8") & u44"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64((v >> u44"16") & u44"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64((v >> u44"24") & u44"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U44.toU64((v >> u44"32") & u44"0xFF")))
      bleU4(output, context, conversions.U8.toU4(conversions.U64.toU8(conversions.U44.toU64(v >> u44"40"))))
    }

    def leU45(output: MSZ[B], context: Context, v: U45): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64(v & u45"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64((v >> u45"8") & u45"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64((v >> u45"16") & u45"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64((v >> u45"24") & u45"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U45.toU64((v >> u45"32") & u45"0xFF")))
      bleU5(output, context, conversions.U8.toU5(conversions.U64.toU8(conversions.U45.toU64(v >> u45"40"))))
    }

    def leU46(output: MSZ[B], context: Context, v: U46): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64(v & u46"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64((v >> u46"8") & u46"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64((v >> u46"16") & u46"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64((v >> u46"24") & u46"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U46.toU64((v >> u46"32") & u46"0xFF")))
      bleU6(output, context, conversions.U8.toU6(conversions.U64.toU8(conversions.U46.toU64(v >> u46"40"))))
    }

    def leU47(output: MSZ[B], context: Context, v: U47): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64(v & u47"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64((v >> u47"8") & u47"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64((v >> u47"16") & u47"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64((v >> u47"24") & u47"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U47.toU64((v >> u47"32") & u47"0xFF")))
      bleU7(output, context, conversions.U8.toU7(conversions.U64.toU8(conversions.U47.toU64(v >> u47"40"))))
    }

    def leU48(output: MSZ[B], context: Context, v: U48): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64(v & u48"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64((v >> u48"8") & u48"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64((v >> u48"16") & u48"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64((v >> u48"24") & u48"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64((v >> u48"32") & u48"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U48.toU64(v >> u48"40")))
    }

    def leU49(output: MSZ[B], context: Context, v: U49): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64(v & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"8") & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"16") & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"24") & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"32") & u49"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U49.toU64((v >> u49"40") & u49"0xFF")))
      bleU1(output, context, conversions.U8.toU1(conversions.U64.toU8(conversions.U49.toU64(v >> u49"48"))))
    }

    def leU50(output: MSZ[B], context: Context, v: U50): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64(v & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"8") & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"16") & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"24") & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"32") & u50"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U50.toU64((v >> u50"40") & u50"0xFF")))
      bleU2(output, context, conversions.U8.toU2(conversions.U64.toU8(conversions.U50.toU64(v >> u50"48"))))
    }

    def leU51(output: MSZ[B], context: Context, v: U51): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64(v & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"8") & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"16") & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"24") & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"32") & u51"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U51.toU64((v >> u51"40") & u51"0xFF")))
      bleU3(output, context, conversions.U8.toU3(conversions.U64.toU8(conversions.U51.toU64(v >> u51"48"))))
    }

    def leU52(output: MSZ[B], context: Context, v: U52): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64(v & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"8") & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"16") & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"24") & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"32") & u52"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U52.toU64((v >> u52"40") & u52"0xFF")))
      bleU4(output, context, conversions.U8.toU4(conversions.U64.toU8(conversions.U52.toU64(v >> u52"48"))))
    }

    def leU53(output: MSZ[B], context: Context, v: U53): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64(v & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"8") & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"16") & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"24") & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"32") & u53"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U53.toU64((v >> u53"40") & u53"0xFF")))
      bleU5(output, context, conversions.U8.toU5(conversions.U64.toU8(conversions.U53.toU64(v >> u53"48"))))
    }

    def leU54(output: MSZ[B], context: Context, v: U54): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64(v & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"8") & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"16") & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"24") & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"32") & u54"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U54.toU64((v >> u54"40") & u54"0xFF")))
      bleU6(output, context, conversions.U8.toU6(conversions.U64.toU8(conversions.U54.toU64(v >> u54"48"))))
    }

    def leU55(output: MSZ[B], context: Context, v: U55): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64(v & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"8") & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"16") & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"24") & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"32") & u55"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U55.toU64((v >> u55"40") & u55"0xFF")))
      bleU7(output, context, conversions.U8.toU7(conversions.U64.toU8(conversions.U55.toU64(v >> u55"48"))))
    }

    def leU56(output: MSZ[B], context: Context, v: U56): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64(v & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"8") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"16") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"24") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"32") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64((v >> u56"40") & u56"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U56.toU64(v >> u56"48")))
    }

    def leU57(output: MSZ[B], context: Context, v: U57): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64(v & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"8") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"16") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"24") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"32") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"40") & u57"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U57.toU64((v >> u57"48") & u57"0xFF")))
      bleU1(output, context, conversions.U8.toU1(conversions.U64.toU8(conversions.U57.toU64(v >> u57"56"))))
    }

    def leU58(output: MSZ[B], context: Context, v: U58): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64(v & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"8") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"16") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"24") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"32") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"40") & u58"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U58.toU64((v >> u58"48") & u58"0xFF")))
      bleU2(output, context, conversions.U8.toU2(conversions.U64.toU8(conversions.U58.toU64(v >> u58"56"))))
    }

    def leU59(output: MSZ[B], context: Context, v: U59): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64(v & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"8") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"16") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"24") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"32") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"40") & u59"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U59.toU64((v >> u59"48") & u59"0xFF")))
      bleU3(output, context, conversions.U8.toU3(conversions.U64.toU8(conversions.U59.toU64(v >> u59"56"))))
    }

    def leU60(output: MSZ[B], context: Context, v: U60): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64(v & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"8") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"16") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"24") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"32") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"40") & u60"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U60.toU64((v >> u60"48") & u60"0xFF")))
      bleU4(output, context, conversions.U8.toU4(conversions.U64.toU8(conversions.U60.toU64(v >> u60"56"))))
    }

    def leU61(output: MSZ[B], context: Context, v: U61): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64(v & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"8") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"16") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"24") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"32") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"40") & u61"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U61.toU64((v >> u61"48") & u61"0xFF")))
      bleU5(output, context, conversions.U8.toU5(conversions.U64.toU8(conversions.U61.toU64(v >> u61"56"))))
    }

    def leU62(output: MSZ[B], context: Context, v: U62): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64(v & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"8") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"16") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"24") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"32") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"40") & u62"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U62.toU64((v >> u62"48") & u62"0xFF")))
      bleU6(output, context, conversions.U8.toU6(conversions.U64.toU8(conversions.U62.toU64(v >> u62"56"))))
    }

    def leU63(output: MSZ[B], context: Context, v: U63): Unit = {
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64(v & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"8") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"16") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"24") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"32") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"40") & u63"0xFF")))
      bleU8(output, context, conversions.U64.toU8(conversions.U63.toU64((v >> u63"48") & u63"0xFF")))
      bleU7(output, context, conversions.U8.toU7(conversions.U64.toU8(conversions.U63.toU64(v >> u63"56"))))
    }

    def leU64(output: MSZ[B], context: Context, v: U64): Unit = {
      bleU8(output, context, conversions.U64.toU8(v & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"8") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"16") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"24") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"32") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"40") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8((v >> u64"48") & u64"0xFF"))
      bleU8(output, context, conversions.U64.toU8(v >> u64"56"))
    }

    def leS64(output: MSZ[B], context: Context, v: S64): Unit = {
      leU64(output, context, conversions.S64.toRawU64(v))
    }

    def leF32(output: MSZ[B], context: Context, v: F32): Unit = {
      leU32(output, context, conversions.F32.toRawU32(v))
    }

    def leF64(output: MSZ[B], context: Context, v: F64): Unit = {
      leU64(output, context, conversions.F64.toRawU64(v))
    }
  }

  def hex2u8(n: U8): Option[U8] = {
    if (u8"48" <= n && n <= u8"57") { // '0' .. '9'
      return Some(n - u8"48")
    }
    if (u8"65" <= n && n <= u8"70") { // 'A' .. 'F'
      return Some(n - u8"55")
    }
    if (u8"97" <= n && n <= u8"102") { // 'a' .. 'f'
      return Some(n - u8"87")
    }
    return None()
  }

  def fromHexString(s: String): ISZ[B] = {
    assert(s.size % 2 == 0)
    val ms = MSZ.create(s.size * 4, F)
    var i = 0
    val sz = s.size
    val u8s = conversions.String.toU8is(s)
    val ctx = Bits.Context.create
    while (i < sz) {
      val b1Opt = hex2u8(u8s(i))
      i = i + 1
      val b2Opt = hex2u8(u8s(i))
      i = i + 1
      (b1Opt, b2Opt) match {
        case (Some(b1), Some(b2)) =>
          val b = b1 << u8"4" | b2
          Bits.Writer.bleU8(ms, ctx, b)
        case (_, _) =>
          halt(s"Invalid hex input string at offset: ${i - (if (b1Opt.isEmpty) 2 else 1)}")
      }
    }
    assert(ctx.errorCode == 0)
    return Bits.Writer.resultMS(ms, ctx).toIS
  }
}
