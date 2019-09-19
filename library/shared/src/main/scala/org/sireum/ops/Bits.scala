// #Sireum
/*
 Copyright (c) 2019, Robby, Kansas State University
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

      def beU8S(input: ISZ[B], context: Context, result: MSZ[U8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beU8(input, context)
        }
      }

      def beS8S(input: ISZ[B], context: Context, result: MSZ[S8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beS8(input, context)
        }
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

      def leU8S(input: ISZ[B], context: Context, result: MSZ[U8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leU8(input, context)
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
          result(i) = leS8(input, context)
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

      // Slang script gen:
      // for (i <- 2 to 64) {
      //   val sizeM1 = i - 1
      //   println(
      //     st"""def beU$i(input: ISZ[B], context: Context): U$i = {
      //         |  val offset = context.offset
      //         |  if (offset + $i > input.size) {
      //         |    context.signalError(INSUFFICIENT_BUFFER_SIZE)
      //         |  }
      //         |  if (context.hasError) {
      //         |    return u$i"0"
      //         |  }
      //         |  var r = u$i"0"
      //         |  var mask = u$i"1"
      //         |  for (i <- 0 until $sizeM1) {
      //         |    if (input(offset + i)) {
      //         |      r = r | mask
      //         |    }
      //         |    mask = mask << u$i"1"
      //         |  }
      //         |  if (input(offset + $sizeM1)) {
      //         |    r = r | mask
      //         |  }
      //         |  context.offset = offset + $i
      //         |  return r
      //         |}""".render)
      //   println()
      // }

      def beU2(input: ISZ[B], context: Context): U2 = {
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

      def beU3(input: ISZ[B], context: Context): U3 = {
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

      def beU4(input: ISZ[B], context: Context): U4 = {
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

      def beU5(input: ISZ[B], context: Context): U5 = {
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

      def beU6(input: ISZ[B], context: Context): U6 = {
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

      def beU7(input: ISZ[B], context: Context): U7 = {
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

      def beU8(input: ISZ[B], context: Context): U8 = {
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

      def beS8(input: ISZ[B], context: Context): S8 = {
        return conversions.U8.toS8(beU8(input, context))
      }

      def beU9(input: ISZ[B], context: Context): U9 = {
        val offset = context.offset
        if (offset + 9 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u9"0"
        }
        var r = u9"0"
        var mask = u9"1"
        for (i <- 0 until 8) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u9"1"
        }
        if (input(offset + 8)) {
          r = r | mask
        }
        context.offset = offset + 9
        return r
      }

      def beU10(input: ISZ[B], context: Context): U10 = {
        val offset = context.offset
        if (offset + 10 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u10"0"
        }
        var r = u10"0"
        var mask = u10"1"
        for (i <- 0 until 9) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u10"1"
        }
        if (input(offset + 9)) {
          r = r | mask
        }
        context.offset = offset + 10
        return r
      }

      def beU11(input: ISZ[B], context: Context): U11 = {
        val offset = context.offset
        if (offset + 11 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u11"0"
        }
        var r = u11"0"
        var mask = u11"1"
        for (i <- 0 until 10) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u11"1"
        }
        if (input(offset + 10)) {
          r = r | mask
        }
        context.offset = offset + 11
        return r
      }

      def beU12(input: ISZ[B], context: Context): U12 = {
        val offset = context.offset
        if (offset + 12 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u12"0"
        }
        var r = u12"0"
        var mask = u12"1"
        for (i <- 0 until 11) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u12"1"
        }
        if (input(offset + 11)) {
          r = r | mask
        }
        context.offset = offset + 12
        return r
      }

      def beU13(input: ISZ[B], context: Context): U13 = {
        val offset = context.offset
        if (offset + 13 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u13"0"
        }
        var r = u13"0"
        var mask = u13"1"
        for (i <- 0 until 12) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u13"1"
        }
        if (input(offset + 12)) {
          r = r | mask
        }
        context.offset = offset + 13
        return r
      }

      def beU14(input: ISZ[B], context: Context): U14 = {
        val offset = context.offset
        if (offset + 14 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u14"0"
        }
        var r = u14"0"
        var mask = u14"1"
        for (i <- 0 until 13) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u14"1"
        }
        if (input(offset + 13)) {
          r = r | mask
        }
        context.offset = offset + 14
        return r
      }

      def beU15(input: ISZ[B], context: Context): U15 = {
        val offset = context.offset
        if (offset + 15 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u15"0"
        }
        var r = u15"0"
        var mask = u15"1"
        for (i <- 0 until 14) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u15"1"
        }
        if (input(offset + 14)) {
          r = r | mask
        }
        context.offset = offset + 15
        return r
      }

      def beU16(input: ISZ[B], context: Context): U16 = {
        val offset = context.offset
        if (offset + 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u16"0"
        }
        var r = u16"0"
        var mask = u16"1"
        for (i <- 0 until 15) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u16"1"
        }
        if (input(offset + 15)) {
          r = r | mask
        }
        context.offset = offset + 16
        return r
      }

      def beS16(input: ISZ[B], context: Context): S16 = {
        return conversions.U16.toS16(beU16(input, context))
      }

      def beU17(input: ISZ[B], context: Context): U17 = {
        val offset = context.offset
        if (offset + 17 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u17"0"
        }
        var r = u17"0"
        var mask = u17"1"
        for (i <- 0 until 16) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u17"1"
        }
        if (input(offset + 16)) {
          r = r | mask
        }
        context.offset = offset + 17
        return r
      }

      def beU18(input: ISZ[B], context: Context): U18 = {
        val offset = context.offset
        if (offset + 18 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u18"0"
        }
        var r = u18"0"
        var mask = u18"1"
        for (i <- 0 until 17) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u18"1"
        }
        if (input(offset + 17)) {
          r = r | mask
        }
        context.offset = offset + 18
        return r
      }

      def beU19(input: ISZ[B], context: Context): U19 = {
        val offset = context.offset
        if (offset + 19 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u19"0"
        }
        var r = u19"0"
        var mask = u19"1"
        for (i <- 0 until 18) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u19"1"
        }
        if (input(offset + 18)) {
          r = r | mask
        }
        context.offset = offset + 19
        return r
      }

      def beU20(input: ISZ[B], context: Context): U20 = {
        val offset = context.offset
        if (offset + 20 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u20"0"
        }
        var r = u20"0"
        var mask = u20"1"
        for (i <- 0 until 19) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u20"1"
        }
        if (input(offset + 19)) {
          r = r | mask
        }
        context.offset = offset + 20
        return r
      }

      def beU21(input: ISZ[B], context: Context): U21 = {
        val offset = context.offset
        if (offset + 21 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u21"0"
        }
        var r = u21"0"
        var mask = u21"1"
        for (i <- 0 until 20) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u21"1"
        }
        if (input(offset + 20)) {
          r = r | mask
        }
        context.offset = offset + 21
        return r
      }

      def beU22(input: ISZ[B], context: Context): U22 = {
        val offset = context.offset
        if (offset + 22 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u22"0"
        }
        var r = u22"0"
        var mask = u22"1"
        for (i <- 0 until 21) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u22"1"
        }
        if (input(offset + 21)) {
          r = r | mask
        }
        context.offset = offset + 22
        return r
      }

      def beU23(input: ISZ[B], context: Context): U23 = {
        val offset = context.offset
        if (offset + 23 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u23"0"
        }
        var r = u23"0"
        var mask = u23"1"
        for (i <- 0 until 22) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u23"1"
        }
        if (input(offset + 22)) {
          r = r | mask
        }
        context.offset = offset + 23
        return r
      }

      def beU24(input: ISZ[B], context: Context): U24 = {
        val offset = context.offset
        if (offset + 24 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u24"0"
        }
        var r = u24"0"
        var mask = u24"1"
        for (i <- 0 until 23) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u24"1"
        }
        if (input(offset + 23)) {
          r = r | mask
        }
        context.offset = offset + 24
        return r
      }

      def beU25(input: ISZ[B], context: Context): U25 = {
        val offset = context.offset
        if (offset + 25 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u25"0"
        }
        var r = u25"0"
        var mask = u25"1"
        for (i <- 0 until 24) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u25"1"
        }
        if (input(offset + 24)) {
          r = r | mask
        }
        context.offset = offset + 25
        return r
      }

      def beU26(input: ISZ[B], context: Context): U26 = {
        val offset = context.offset
        if (offset + 26 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u26"0"
        }
        var r = u26"0"
        var mask = u26"1"
        for (i <- 0 until 25) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u26"1"
        }
        if (input(offset + 25)) {
          r = r | mask
        }
        context.offset = offset + 26
        return r
      }

      def beU27(input: ISZ[B], context: Context): U27 = {
        val offset = context.offset
        if (offset + 27 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u27"0"
        }
        var r = u27"0"
        var mask = u27"1"
        for (i <- 0 until 26) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u27"1"
        }
        if (input(offset + 26)) {
          r = r | mask
        }
        context.offset = offset + 27
        return r
      }

      def beU28(input: ISZ[B], context: Context): U28 = {
        val offset = context.offset
        if (offset + 28 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u28"0"
        }
        var r = u28"0"
        var mask = u28"1"
        for (i <- 0 until 27) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u28"1"
        }
        if (input(offset + 27)) {
          r = r | mask
        }
        context.offset = offset + 28
        return r
      }

      def beU29(input: ISZ[B], context: Context): U29 = {
        val offset = context.offset
        if (offset + 29 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u29"0"
        }
        var r = u29"0"
        var mask = u29"1"
        for (i <- 0 until 28) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u29"1"
        }
        if (input(offset + 28)) {
          r = r | mask
        }
        context.offset = offset + 29
        return r
      }

      def beU30(input: ISZ[B], context: Context): U30 = {
        val offset = context.offset
        if (offset + 30 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u30"0"
        }
        var r = u30"0"
        var mask = u30"1"
        for (i <- 0 until 29) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u30"1"
        }
        if (input(offset + 29)) {
          r = r | mask
        }
        context.offset = offset + 30
        return r
      }

      def beU31(input: ISZ[B], context: Context): U31 = {
        val offset = context.offset
        if (offset + 31 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u31"0"
        }
        var r = u31"0"
        var mask = u31"1"
        for (i <- 0 until 30) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u31"1"
        }
        if (input(offset + 30)) {
          r = r | mask
        }
        context.offset = offset + 31
        return r
      }

      def beU32(input: ISZ[B], context: Context): U32 = {
        val offset = context.offset
        if (offset + 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u32"0"
        }
        var r = u32"0"
        var mask = u32"1"
        for (i <- 0 until 31) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u32"1"
        }
        if (input(offset + 31)) {
          r = r | mask
        }
        context.offset = offset + 32
        return r
      }

      def beS32(input: ISZ[B], context: Context): S32 = {
        return conversions.U32.toS32(beU32(input, context))
      }

      def beU33(input: ISZ[B], context: Context): U33 = {
        val offset = context.offset
        if (offset + 33 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u33"0"
        }
        var r = u33"0"
        var mask = u33"1"
        for (i <- 0 until 32) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u33"1"
        }
        if (input(offset + 32)) {
          r = r | mask
        }
        context.offset = offset + 33
        return r
      }

      def beU34(input: ISZ[B], context: Context): U34 = {
        val offset = context.offset
        if (offset + 34 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u34"0"
        }
        var r = u34"0"
        var mask = u34"1"
        for (i <- 0 until 33) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u34"1"
        }
        if (input(offset + 33)) {
          r = r | mask
        }
        context.offset = offset + 34
        return r
      }

      def beU35(input: ISZ[B], context: Context): U35 = {
        val offset = context.offset
        if (offset + 35 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u35"0"
        }
        var r = u35"0"
        var mask = u35"1"
        for (i <- 0 until 34) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u35"1"
        }
        if (input(offset + 34)) {
          r = r | mask
        }
        context.offset = offset + 35
        return r
      }

      def beU36(input: ISZ[B], context: Context): U36 = {
        val offset = context.offset
        if (offset + 36 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u36"0"
        }
        var r = u36"0"
        var mask = u36"1"
        for (i <- 0 until 35) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u36"1"
        }
        if (input(offset + 35)) {
          r = r | mask
        }
        context.offset = offset + 36
        return r
      }

      def beU37(input: ISZ[B], context: Context): U37 = {
        val offset = context.offset
        if (offset + 37 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u37"0"
        }
        var r = u37"0"
        var mask = u37"1"
        for (i <- 0 until 36) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u37"1"
        }
        if (input(offset + 36)) {
          r = r | mask
        }
        context.offset = offset + 37
        return r
      }

      def beU38(input: ISZ[B], context: Context): U38 = {
        val offset = context.offset
        if (offset + 38 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u38"0"
        }
        var r = u38"0"
        var mask = u38"1"
        for (i <- 0 until 37) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u38"1"
        }
        if (input(offset + 37)) {
          r = r | mask
        }
        context.offset = offset + 38
        return r
      }

      def beU39(input: ISZ[B], context: Context): U39 = {
        val offset = context.offset
        if (offset + 39 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u39"0"
        }
        var r = u39"0"
        var mask = u39"1"
        for (i <- 0 until 38) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u39"1"
        }
        if (input(offset + 38)) {
          r = r | mask
        }
        context.offset = offset + 39
        return r
      }

      def beU40(input: ISZ[B], context: Context): U40 = {
        val offset = context.offset
        if (offset + 40 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u40"0"
        }
        var r = u40"0"
        var mask = u40"1"
        for (i <- 0 until 39) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u40"1"
        }
        if (input(offset + 39)) {
          r = r | mask
        }
        context.offset = offset + 40
        return r
      }

      def beU41(input: ISZ[B], context: Context): U41 = {
        val offset = context.offset
        if (offset + 41 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u41"0"
        }
        var r = u41"0"
        var mask = u41"1"
        for (i <- 0 until 40) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u41"1"
        }
        if (input(offset + 40)) {
          r = r | mask
        }
        context.offset = offset + 41
        return r
      }

      def beU42(input: ISZ[B], context: Context): U42 = {
        val offset = context.offset
        if (offset + 42 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u42"0"
        }
        var r = u42"0"
        var mask = u42"1"
        for (i <- 0 until 41) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u42"1"
        }
        if (input(offset + 41)) {
          r = r | mask
        }
        context.offset = offset + 42
        return r
      }

      def beU43(input: ISZ[B], context: Context): U43 = {
        val offset = context.offset
        if (offset + 43 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u43"0"
        }
        var r = u43"0"
        var mask = u43"1"
        for (i <- 0 until 42) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u43"1"
        }
        if (input(offset + 42)) {
          r = r | mask
        }
        context.offset = offset + 43
        return r
      }

      def beU44(input: ISZ[B], context: Context): U44 = {
        val offset = context.offset
        if (offset + 44 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u44"0"
        }
        var r = u44"0"
        var mask = u44"1"
        for (i <- 0 until 43) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u44"1"
        }
        if (input(offset + 43)) {
          r = r | mask
        }
        context.offset = offset + 44
        return r
      }

      def beU45(input: ISZ[B], context: Context): U45 = {
        val offset = context.offset
        if (offset + 45 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u45"0"
        }
        var r = u45"0"
        var mask = u45"1"
        for (i <- 0 until 44) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u45"1"
        }
        if (input(offset + 44)) {
          r = r | mask
        }
        context.offset = offset + 45
        return r
      }

      def beU46(input: ISZ[B], context: Context): U46 = {
        val offset = context.offset
        if (offset + 46 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u46"0"
        }
        var r = u46"0"
        var mask = u46"1"
        for (i <- 0 until 45) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u46"1"
        }
        if (input(offset + 45)) {
          r = r | mask
        }
        context.offset = offset + 46
        return r
      }

      def beU47(input: ISZ[B], context: Context): U47 = {
        val offset = context.offset
        if (offset + 47 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u47"0"
        }
        var r = u47"0"
        var mask = u47"1"
        for (i <- 0 until 46) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u47"1"
        }
        if (input(offset + 46)) {
          r = r | mask
        }
        context.offset = offset + 47
        return r
      }

      def beU48(input: ISZ[B], context: Context): U48 = {
        val offset = context.offset
        if (offset + 48 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u48"0"
        }
        var r = u48"0"
        var mask = u48"1"
        for (i <- 0 until 47) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u48"1"
        }
        if (input(offset + 47)) {
          r = r | mask
        }
        context.offset = offset + 48
        return r
      }

      def beU49(input: ISZ[B], context: Context): U49 = {
        val offset = context.offset
        if (offset + 49 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u49"0"
        }
        var r = u49"0"
        var mask = u49"1"
        for (i <- 0 until 48) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u49"1"
        }
        if (input(offset + 48)) {
          r = r | mask
        }
        context.offset = offset + 49
        return r
      }

      def beU50(input: ISZ[B], context: Context): U50 = {
        val offset = context.offset
        if (offset + 50 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u50"0"
        }
        var r = u50"0"
        var mask = u50"1"
        for (i <- 0 until 49) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u50"1"
        }
        if (input(offset + 49)) {
          r = r | mask
        }
        context.offset = offset + 50
        return r
      }

      def beU51(input: ISZ[B], context: Context): U51 = {
        val offset = context.offset
        if (offset + 51 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u51"0"
        }
        var r = u51"0"
        var mask = u51"1"
        for (i <- 0 until 50) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u51"1"
        }
        if (input(offset + 50)) {
          r = r | mask
        }
        context.offset = offset + 51
        return r
      }

      def beU52(input: ISZ[B], context: Context): U52 = {
        val offset = context.offset
        if (offset + 52 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u52"0"
        }
        var r = u52"0"
        var mask = u52"1"
        for (i <- 0 until 51) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u52"1"
        }
        if (input(offset + 51)) {
          r = r | mask
        }
        context.offset = offset + 52
        return r
      }

      def beU53(input: ISZ[B], context: Context): U53 = {
        val offset = context.offset
        if (offset + 53 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u53"0"
        }
        var r = u53"0"
        var mask = u53"1"
        for (i <- 0 until 52) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u53"1"
        }
        if (input(offset + 52)) {
          r = r | mask
        }
        context.offset = offset + 53
        return r
      }

      def beU54(input: ISZ[B], context: Context): U54 = {
        val offset = context.offset
        if (offset + 54 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u54"0"
        }
        var r = u54"0"
        var mask = u54"1"
        for (i <- 0 until 53) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u54"1"
        }
        if (input(offset + 53)) {
          r = r | mask
        }
        context.offset = offset + 54
        return r
      }

      def beU55(input: ISZ[B], context: Context): U55 = {
        val offset = context.offset
        if (offset + 55 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u55"0"
        }
        var r = u55"0"
        var mask = u55"1"
        for (i <- 0 until 54) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u55"1"
        }
        if (input(offset + 54)) {
          r = r | mask
        }
        context.offset = offset + 55
        return r
      }

      def beU56(input: ISZ[B], context: Context): U56 = {
        val offset = context.offset
        if (offset + 56 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u56"0"
        }
        var r = u56"0"
        var mask = u56"1"
        for (i <- 0 until 55) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u56"1"
        }
        if (input(offset + 55)) {
          r = r | mask
        }
        context.offset = offset + 56
        return r
      }

      def beU57(input: ISZ[B], context: Context): U57 = {
        val offset = context.offset
        if (offset + 57 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u57"0"
        }
        var r = u57"0"
        var mask = u57"1"
        for (i <- 0 until 56) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u57"1"
        }
        if (input(offset + 56)) {
          r = r | mask
        }
        context.offset = offset + 57
        return r
      }

      def beU58(input: ISZ[B], context: Context): U58 = {
        val offset = context.offset
        if (offset + 58 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u58"0"
        }
        var r = u58"0"
        var mask = u58"1"
        for (i <- 0 until 57) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u58"1"
        }
        if (input(offset + 57)) {
          r = r | mask
        }
        context.offset = offset + 58
        return r
      }

      def beU59(input: ISZ[B], context: Context): U59 = {
        val offset = context.offset
        if (offset + 59 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u59"0"
        }
        var r = u59"0"
        var mask = u59"1"
        for (i <- 0 until 58) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u59"1"
        }
        if (input(offset + 58)) {
          r = r | mask
        }
        context.offset = offset + 59
        return r
      }

      def beU60(input: ISZ[B], context: Context): U60 = {
        val offset = context.offset
        if (offset + 60 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u60"0"
        }
        var r = u60"0"
        var mask = u60"1"
        for (i <- 0 until 59) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u60"1"
        }
        if (input(offset + 59)) {
          r = r | mask
        }
        context.offset = offset + 60
        return r
      }

      def beU61(input: ISZ[B], context: Context): U61 = {
        val offset = context.offset
        if (offset + 61 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u61"0"
        }
        var r = u61"0"
        var mask = u61"1"
        for (i <- 0 until 60) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u61"1"
        }
        if (input(offset + 60)) {
          r = r | mask
        }
        context.offset = offset + 61
        return r
      }

      def beU62(input: ISZ[B], context: Context): U62 = {
        val offset = context.offset
        if (offset + 62 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u62"0"
        }
        var r = u62"0"
        var mask = u62"1"
        for (i <- 0 until 61) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u62"1"
        }
        if (input(offset + 61)) {
          r = r | mask
        }
        context.offset = offset + 62
        return r
      }

      def beU63(input: ISZ[B], context: Context): U63 = {
        val offset = context.offset
        if (offset + 63 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u63"0"
        }
        var r = u63"0"
        var mask = u63"1"
        for (i <- 0 until 62) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u63"1"
        }
        if (input(offset + 62)) {
          r = r | mask
        }
        context.offset = offset + 63
        return r
      }

      def beU64(input: ISZ[B], context: Context): U64 = {
        val offset = context.offset
        if (offset + 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u64"0"
        }
        var r = u64"0"
        var mask = u64"1"
        for (i <- 0 until 63) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u64"1"
        }
        if (input(offset + 63)) {
          r = r | mask
        }
        context.offset = offset + 64
        return r
      }

      def beS64(input: ISZ[B], context: Context): S64 = {
        return conversions.U64.toS64(beU64(input, context))
      }

      // Slang script gen:
      // for (i <- 2 to 64) {
      //   val sizeM1 = i - 1
      //   println(
      //     st"""def leU$i(input: ISZ[B], context: Context): U$i = {
      //         |  val offset = context.offset
      //         |  if (offset + $i > input.size) {
      //         |    context.signalError(INSUFFICIENT_BUFFER_SIZE)
      //         |  }
      //         |  if (context.hasError) {
      //         |    return u$i"0"
      //         |  }
      //         |  var r = u$i"0"
      //         |  var mask = u$i"1" << u$i"$sizeM1"
      //         |  for (i <- 0 until $sizeM1) {
      //         |    if (input(offset + i)) {
      //         |      r = r | mask
      //         |    }
      //         |    mask = mask >>> u$i"1"
      //         |  }
      //         |  if (input(offset + $sizeM1)) {
      //         |    r = r | mask
      //         |  }
      //         |  context.offset = offset + $i
      //         |  return r
      //         |}""".render)
      //   println()
      // }

      def leU2(input: ISZ[B], context: Context): U2 = {
        val offset = context.offset
        if (offset + 2 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u2"0"
        }
        var r = u2"0"
        var mask = u2"1" << u2"1"
        for (i <- 0 until 1) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u2"1"
        }
        if (input(offset + 1)) {
          r = r | mask
        }
        context.offset = offset + 2
        return r
      }

      def leU3(input: ISZ[B], context: Context): U3 = {
        val offset = context.offset
        if (offset + 3 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u3"0"
        }
        var r = u3"0"
        var mask = u3"1" << u3"2"
        for (i <- 0 until 2) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u3"1"
        }
        if (input(offset + 2)) {
          r = r | mask
        }
        context.offset = offset + 3
        return r
      }

      def leU4(input: ISZ[B], context: Context): U4 = {
        val offset = context.offset
        if (offset + 4 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u4"0"
        }
        var r = u4"0"
        var mask = u4"1" << u4"3"
        for (i <- 0 until 3) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u4"1"
        }
        if (input(offset + 3)) {
          r = r | mask
        }
        context.offset = offset + 4
        return r
      }

      def leU5(input: ISZ[B], context: Context): U5 = {
        val offset = context.offset
        if (offset + 5 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u5"0"
        }
        var r = u5"0"
        var mask = u5"1" << u5"4"
        for (i <- 0 until 4) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u5"1"
        }
        if (input(offset + 4)) {
          r = r | mask
        }
        context.offset = offset + 5
        return r
      }

      def leU6(input: ISZ[B], context: Context): U6 = {
        val offset = context.offset
        if (offset + 6 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u6"0"
        }
        var r = u6"0"
        var mask = u6"1" << u6"5"
        for (i <- 0 until 5) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u6"1"
        }
        if (input(offset + 5)) {
          r = r | mask
        }
        context.offset = offset + 6
        return r
      }

      def leU7(input: ISZ[B], context: Context): U7 = {
        val offset = context.offset
        if (offset + 7 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u7"0"
        }
        var r = u7"0"
        var mask = u7"1" << u7"6"
        for (i <- 0 until 6) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u7"1"
        }
        if (input(offset + 6)) {
          r = r | mask
        }
        context.offset = offset + 7
        return r
      }

      def leU8(input: ISZ[B], context: Context): U8 = {
        val offset = context.offset
        if (offset + 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u8"0"
        }
        var r = u8"0"
        var mask = u8"1" << u8"7"
        for (i <- 0 until 7) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u8"1"
        }
        if (input(offset + 7)) {
          r = r | mask
        }
        context.offset = offset + 8
        return r
      }

      def leS8(input: ISZ[B], context: Context): S8 = {
        return conversions.U8.toS8(leU8(input, context))
      }

      def leU9(input: ISZ[B], context: Context): U9 = {
        val offset = context.offset
        if (offset + 9 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u9"0"
        }
        var r = u9"0"
        var mask = u9"1" << u9"8"
        for (i <- 0 until 8) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u9"1"
        }
        if (input(offset + 8)) {
          r = r | mask
        }
        context.offset = offset + 9
        return r
      }

      def leU10(input: ISZ[B], context: Context): U10 = {
        val offset = context.offset
        if (offset + 10 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u10"0"
        }
        var r = u10"0"
        var mask = u10"1" << u10"9"
        for (i <- 0 until 9) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u10"1"
        }
        if (input(offset + 9)) {
          r = r | mask
        }
        context.offset = offset + 10
        return r
      }

      def leU11(input: ISZ[B], context: Context): U11 = {
        val offset = context.offset
        if (offset + 11 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u11"0"
        }
        var r = u11"0"
        var mask = u11"1" << u11"10"
        for (i <- 0 until 10) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u11"1"
        }
        if (input(offset + 10)) {
          r = r | mask
        }
        context.offset = offset + 11
        return r
      }

      def leU12(input: ISZ[B], context: Context): U12 = {
        val offset = context.offset
        if (offset + 12 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u12"0"
        }
        var r = u12"0"
        var mask = u12"1" << u12"11"
        for (i <- 0 until 11) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u12"1"
        }
        if (input(offset + 11)) {
          r = r | mask
        }
        context.offset = offset + 12
        return r
      }

      def leU13(input: ISZ[B], context: Context): U13 = {
        val offset = context.offset
        if (offset + 13 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u13"0"
        }
        var r = u13"0"
        var mask = u13"1" << u13"12"
        for (i <- 0 until 12) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u13"1"
        }
        if (input(offset + 12)) {
          r = r | mask
        }
        context.offset = offset + 13
        return r
      }

      def leU14(input: ISZ[B], context: Context): U14 = {
        val offset = context.offset
        if (offset + 14 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u14"0"
        }
        var r = u14"0"
        var mask = u14"1" << u14"13"
        for (i <- 0 until 13) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u14"1"
        }
        if (input(offset + 13)) {
          r = r | mask
        }
        context.offset = offset + 14
        return r
      }

      def leU15(input: ISZ[B], context: Context): U15 = {
        val offset = context.offset
        if (offset + 15 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u15"0"
        }
        var r = u15"0"
        var mask = u15"1" << u15"14"
        for (i <- 0 until 14) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u15"1"
        }
        if (input(offset + 14)) {
          r = r | mask
        }
        context.offset = offset + 15
        return r
      }

      def leU16(input: ISZ[B], context: Context): U16 = {
        val offset = context.offset
        if (offset + 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u16"0"
        }
        var r = u16"0"
        var mask = u16"1" << u16"15"
        for (i <- 0 until 15) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u16"1"
        }
        if (input(offset + 15)) {
          r = r | mask
        }
        context.offset = offset + 16
        return r
      }

      def leS16(input: ISZ[B], context: Context): S16 = {
        return conversions.U16.toS16(leU16(input, context))
      }

      def leU17(input: ISZ[B], context: Context): U17 = {
        val offset = context.offset
        if (offset + 17 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u17"0"
        }
        var r = u17"0"
        var mask = u17"1" << u17"16"
        for (i <- 0 until 16) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u17"1"
        }
        if (input(offset + 16)) {
          r = r | mask
        }
        context.offset = offset + 17
        return r
      }

      def leU18(input: ISZ[B], context: Context): U18 = {
        val offset = context.offset
        if (offset + 18 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u18"0"
        }
        var r = u18"0"
        var mask = u18"1" << u18"17"
        for (i <- 0 until 17) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u18"1"
        }
        if (input(offset + 17)) {
          r = r | mask
        }
        context.offset = offset + 18
        return r
      }

      def leU19(input: ISZ[B], context: Context): U19 = {
        val offset = context.offset
        if (offset + 19 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u19"0"
        }
        var r = u19"0"
        var mask = u19"1" << u19"18"
        for (i <- 0 until 18) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u19"1"
        }
        if (input(offset + 18)) {
          r = r | mask
        }
        context.offset = offset + 19
        return r
      }

      def leU20(input: ISZ[B], context: Context): U20 = {
        val offset = context.offset
        if (offset + 20 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u20"0"
        }
        var r = u20"0"
        var mask = u20"1" << u20"19"
        for (i <- 0 until 19) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u20"1"
        }
        if (input(offset + 19)) {
          r = r | mask
        }
        context.offset = offset + 20
        return r
      }

      def leU21(input: ISZ[B], context: Context): U21 = {
        val offset = context.offset
        if (offset + 21 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u21"0"
        }
        var r = u21"0"
        var mask = u21"1" << u21"20"
        for (i <- 0 until 20) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u21"1"
        }
        if (input(offset + 20)) {
          r = r | mask
        }
        context.offset = offset + 21
        return r
      }

      def leU22(input: ISZ[B], context: Context): U22 = {
        val offset = context.offset
        if (offset + 22 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u22"0"
        }
        var r = u22"0"
        var mask = u22"1" << u22"21"
        for (i <- 0 until 21) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u22"1"
        }
        if (input(offset + 21)) {
          r = r | mask
        }
        context.offset = offset + 22
        return r
      }

      def leU23(input: ISZ[B], context: Context): U23 = {
        val offset = context.offset
        if (offset + 23 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u23"0"
        }
        var r = u23"0"
        var mask = u23"1" << u23"22"
        for (i <- 0 until 22) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u23"1"
        }
        if (input(offset + 22)) {
          r = r | mask
        }
        context.offset = offset + 23
        return r
      }

      def leU24(input: ISZ[B], context: Context): U24 = {
        val offset = context.offset
        if (offset + 24 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u24"0"
        }
        var r = u24"0"
        var mask = u24"1" << u24"23"
        for (i <- 0 until 23) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u24"1"
        }
        if (input(offset + 23)) {
          r = r | mask
        }
        context.offset = offset + 24
        return r
      }

      def leU25(input: ISZ[B], context: Context): U25 = {
        val offset = context.offset
        if (offset + 25 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u25"0"
        }
        var r = u25"0"
        var mask = u25"1" << u25"24"
        for (i <- 0 until 24) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u25"1"
        }
        if (input(offset + 24)) {
          r = r | mask
        }
        context.offset = offset + 25
        return r
      }

      def leU26(input: ISZ[B], context: Context): U26 = {
        val offset = context.offset
        if (offset + 26 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u26"0"
        }
        var r = u26"0"
        var mask = u26"1" << u26"25"
        for (i <- 0 until 25) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u26"1"
        }
        if (input(offset + 25)) {
          r = r | mask
        }
        context.offset = offset + 26
        return r
      }

      def leU27(input: ISZ[B], context: Context): U27 = {
        val offset = context.offset
        if (offset + 27 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u27"0"
        }
        var r = u27"0"
        var mask = u27"1" << u27"26"
        for (i <- 0 until 26) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u27"1"
        }
        if (input(offset + 26)) {
          r = r | mask
        }
        context.offset = offset + 27
        return r
      }

      def leU28(input: ISZ[B], context: Context): U28 = {
        val offset = context.offset
        if (offset + 28 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u28"0"
        }
        var r = u28"0"
        var mask = u28"1" << u28"27"
        for (i <- 0 until 27) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u28"1"
        }
        if (input(offset + 27)) {
          r = r | mask
        }
        context.offset = offset + 28
        return r
      }

      def leU29(input: ISZ[B], context: Context): U29 = {
        val offset = context.offset
        if (offset + 29 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u29"0"
        }
        var r = u29"0"
        var mask = u29"1" << u29"28"
        for (i <- 0 until 28) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u29"1"
        }
        if (input(offset + 28)) {
          r = r | mask
        }
        context.offset = offset + 29
        return r
      }

      def leU30(input: ISZ[B], context: Context): U30 = {
        val offset = context.offset
        if (offset + 30 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u30"0"
        }
        var r = u30"0"
        var mask = u30"1" << u30"29"
        for (i <- 0 until 29) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u30"1"
        }
        if (input(offset + 29)) {
          r = r | mask
        }
        context.offset = offset + 30
        return r
      }

      def leU31(input: ISZ[B], context: Context): U31 = {
        val offset = context.offset
        if (offset + 31 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u31"0"
        }
        var r = u31"0"
        var mask = u31"1" << u31"30"
        for (i <- 0 until 30) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u31"1"
        }
        if (input(offset + 30)) {
          r = r | mask
        }
        context.offset = offset + 31
        return r
      }

      def leU32(input: ISZ[B], context: Context): U32 = {
        val offset = context.offset
        if (offset + 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u32"0"
        }
        var r = u32"0"
        var mask = u32"1" << u32"31"
        for (i <- 0 until 31) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u32"1"
        }
        if (input(offset + 31)) {
          r = r | mask
        }
        context.offset = offset + 32
        return r
      }

      def leS32(input: ISZ[B], context: Context): S32 = {
        return conversions.U32.toS32(leU32(input, context))
      }

      def leU33(input: ISZ[B], context: Context): U33 = {
        val offset = context.offset
        if (offset + 33 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u33"0"
        }
        var r = u33"0"
        var mask = u33"1" << u33"32"
        for (i <- 0 until 32) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u33"1"
        }
        if (input(offset + 32)) {
          r = r | mask
        }
        context.offset = offset + 33
        return r
      }

      def leU34(input: ISZ[B], context: Context): U34 = {
        val offset = context.offset
        if (offset + 34 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u34"0"
        }
        var r = u34"0"
        var mask = u34"1" << u34"33"
        for (i <- 0 until 33) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u34"1"
        }
        if (input(offset + 33)) {
          r = r | mask
        }
        context.offset = offset + 34
        return r
      }

      def leU35(input: ISZ[B], context: Context): U35 = {
        val offset = context.offset
        if (offset + 35 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u35"0"
        }
        var r = u35"0"
        var mask = u35"1" << u35"34"
        for (i <- 0 until 34) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u35"1"
        }
        if (input(offset + 34)) {
          r = r | mask
        }
        context.offset = offset + 35
        return r
      }

      def leU36(input: ISZ[B], context: Context): U36 = {
        val offset = context.offset
        if (offset + 36 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u36"0"
        }
        var r = u36"0"
        var mask = u36"1" << u36"35"
        for (i <- 0 until 35) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u36"1"
        }
        if (input(offset + 35)) {
          r = r | mask
        }
        context.offset = offset + 36
        return r
      }

      def leU37(input: ISZ[B], context: Context): U37 = {
        val offset = context.offset
        if (offset + 37 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u37"0"
        }
        var r = u37"0"
        var mask = u37"1" << u37"36"
        for (i <- 0 until 36) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u37"1"
        }
        if (input(offset + 36)) {
          r = r | mask
        }
        context.offset = offset + 37
        return r
      }

      def leU38(input: ISZ[B], context: Context): U38 = {
        val offset = context.offset
        if (offset + 38 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u38"0"
        }
        var r = u38"0"
        var mask = u38"1" << u38"37"
        for (i <- 0 until 37) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u38"1"
        }
        if (input(offset + 37)) {
          r = r | mask
        }
        context.offset = offset + 38
        return r
      }

      def leU39(input: ISZ[B], context: Context): U39 = {
        val offset = context.offset
        if (offset + 39 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u39"0"
        }
        var r = u39"0"
        var mask = u39"1" << u39"38"
        for (i <- 0 until 38) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u39"1"
        }
        if (input(offset + 38)) {
          r = r | mask
        }
        context.offset = offset + 39
        return r
      }

      def leU40(input: ISZ[B], context: Context): U40 = {
        val offset = context.offset
        if (offset + 40 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u40"0"
        }
        var r = u40"0"
        var mask = u40"1" << u40"39"
        for (i <- 0 until 39) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u40"1"
        }
        if (input(offset + 39)) {
          r = r | mask
        }
        context.offset = offset + 40
        return r
      }

      def leU41(input: ISZ[B], context: Context): U41 = {
        val offset = context.offset
        if (offset + 41 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u41"0"
        }
        var r = u41"0"
        var mask = u41"1" << u41"40"
        for (i <- 0 until 40) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u41"1"
        }
        if (input(offset + 40)) {
          r = r | mask
        }
        context.offset = offset + 41
        return r
      }

      def leU42(input: ISZ[B], context: Context): U42 = {
        val offset = context.offset
        if (offset + 42 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u42"0"
        }
        var r = u42"0"
        var mask = u42"1" << u42"41"
        for (i <- 0 until 41) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u42"1"
        }
        if (input(offset + 41)) {
          r = r | mask
        }
        context.offset = offset + 42
        return r
      }

      def leU43(input: ISZ[B], context: Context): U43 = {
        val offset = context.offset
        if (offset + 43 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u43"0"
        }
        var r = u43"0"
        var mask = u43"1" << u43"42"
        for (i <- 0 until 42) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u43"1"
        }
        if (input(offset + 42)) {
          r = r | mask
        }
        context.offset = offset + 43
        return r
      }

      def leU44(input: ISZ[B], context: Context): U44 = {
        val offset = context.offset
        if (offset + 44 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u44"0"
        }
        var r = u44"0"
        var mask = u44"1" << u44"43"
        for (i <- 0 until 43) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u44"1"
        }
        if (input(offset + 43)) {
          r = r | mask
        }
        context.offset = offset + 44
        return r
      }

      def leU45(input: ISZ[B], context: Context): U45 = {
        val offset = context.offset
        if (offset + 45 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u45"0"
        }
        var r = u45"0"
        var mask = u45"1" << u45"44"
        for (i <- 0 until 44) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u45"1"
        }
        if (input(offset + 44)) {
          r = r | mask
        }
        context.offset = offset + 45
        return r
      }

      def leU46(input: ISZ[B], context: Context): U46 = {
        val offset = context.offset
        if (offset + 46 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u46"0"
        }
        var r = u46"0"
        var mask = u46"1" << u46"45"
        for (i <- 0 until 45) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u46"1"
        }
        if (input(offset + 45)) {
          r = r | mask
        }
        context.offset = offset + 46
        return r
      }

      def leU47(input: ISZ[B], context: Context): U47 = {
        val offset = context.offset
        if (offset + 47 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u47"0"
        }
        var r = u47"0"
        var mask = u47"1" << u47"46"
        for (i <- 0 until 46) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u47"1"
        }
        if (input(offset + 46)) {
          r = r | mask
        }
        context.offset = offset + 47
        return r
      }

      def leU48(input: ISZ[B], context: Context): U48 = {
        val offset = context.offset
        if (offset + 48 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u48"0"
        }
        var r = u48"0"
        var mask = u48"1" << u48"47"
        for (i <- 0 until 47) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u48"1"
        }
        if (input(offset + 47)) {
          r = r | mask
        }
        context.offset = offset + 48
        return r
      }

      def leU49(input: ISZ[B], context: Context): U49 = {
        val offset = context.offset
        if (offset + 49 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u49"0"
        }
        var r = u49"0"
        var mask = u49"1" << u49"48"
        for (i <- 0 until 48) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u49"1"
        }
        if (input(offset + 48)) {
          r = r | mask
        }
        context.offset = offset + 49
        return r
      }

      def leU50(input: ISZ[B], context: Context): U50 = {
        val offset = context.offset
        if (offset + 50 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u50"0"
        }
        var r = u50"0"
        var mask = u50"1" << u50"49"
        for (i <- 0 until 49) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u50"1"
        }
        if (input(offset + 49)) {
          r = r | mask
        }
        context.offset = offset + 50
        return r
      }

      def leU51(input: ISZ[B], context: Context): U51 = {
        val offset = context.offset
        if (offset + 51 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u51"0"
        }
        var r = u51"0"
        var mask = u51"1" << u51"50"
        for (i <- 0 until 50) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u51"1"
        }
        if (input(offset + 50)) {
          r = r | mask
        }
        context.offset = offset + 51
        return r
      }

      def leU52(input: ISZ[B], context: Context): U52 = {
        val offset = context.offset
        if (offset + 52 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u52"0"
        }
        var r = u52"0"
        var mask = u52"1" << u52"51"
        for (i <- 0 until 51) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u52"1"
        }
        if (input(offset + 51)) {
          r = r | mask
        }
        context.offset = offset + 52
        return r
      }

      def leU53(input: ISZ[B], context: Context): U53 = {
        val offset = context.offset
        if (offset + 53 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u53"0"
        }
        var r = u53"0"
        var mask = u53"1" << u53"52"
        for (i <- 0 until 52) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u53"1"
        }
        if (input(offset + 52)) {
          r = r | mask
        }
        context.offset = offset + 53
        return r
      }

      def leU54(input: ISZ[B], context: Context): U54 = {
        val offset = context.offset
        if (offset + 54 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u54"0"
        }
        var r = u54"0"
        var mask = u54"1" << u54"53"
        for (i <- 0 until 53) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u54"1"
        }
        if (input(offset + 53)) {
          r = r | mask
        }
        context.offset = offset + 54
        return r
      }

      def leU55(input: ISZ[B], context: Context): U55 = {
        val offset = context.offset
        if (offset + 55 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u55"0"
        }
        var r = u55"0"
        var mask = u55"1" << u55"54"
        for (i <- 0 until 54) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u55"1"
        }
        if (input(offset + 54)) {
          r = r | mask
        }
        context.offset = offset + 55
        return r
      }

      def leU56(input: ISZ[B], context: Context): U56 = {
        val offset = context.offset
        if (offset + 56 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u56"0"
        }
        var r = u56"0"
        var mask = u56"1" << u56"55"
        for (i <- 0 until 55) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u56"1"
        }
        if (input(offset + 55)) {
          r = r | mask
        }
        context.offset = offset + 56
        return r
      }

      def leU57(input: ISZ[B], context: Context): U57 = {
        val offset = context.offset
        if (offset + 57 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u57"0"
        }
        var r = u57"0"
        var mask = u57"1" << u57"56"
        for (i <- 0 until 56) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u57"1"
        }
        if (input(offset + 56)) {
          r = r | mask
        }
        context.offset = offset + 57
        return r
      }

      def leU58(input: ISZ[B], context: Context): U58 = {
        val offset = context.offset
        if (offset + 58 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u58"0"
        }
        var r = u58"0"
        var mask = u58"1" << u58"57"
        for (i <- 0 until 57) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u58"1"
        }
        if (input(offset + 57)) {
          r = r | mask
        }
        context.offset = offset + 58
        return r
      }

      def leU59(input: ISZ[B], context: Context): U59 = {
        val offset = context.offset
        if (offset + 59 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u59"0"
        }
        var r = u59"0"
        var mask = u59"1" << u59"58"
        for (i <- 0 until 58) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u59"1"
        }
        if (input(offset + 58)) {
          r = r | mask
        }
        context.offset = offset + 59
        return r
      }

      def leU60(input: ISZ[B], context: Context): U60 = {
        val offset = context.offset
        if (offset + 60 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u60"0"
        }
        var r = u60"0"
        var mask = u60"1" << u60"59"
        for (i <- 0 until 59) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u60"1"
        }
        if (input(offset + 59)) {
          r = r | mask
        }
        context.offset = offset + 60
        return r
      }

      def leU61(input: ISZ[B], context: Context): U61 = {
        val offset = context.offset
        if (offset + 61 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u61"0"
        }
        var r = u61"0"
        var mask = u61"1" << u61"60"
        for (i <- 0 until 60) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u61"1"
        }
        if (input(offset + 60)) {
          r = r | mask
        }
        context.offset = offset + 61
        return r
      }

      def leU62(input: ISZ[B], context: Context): U62 = {
        val offset = context.offset
        if (offset + 62 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u62"0"
        }
        var r = u62"0"
        var mask = u62"1" << u62"61"
        for (i <- 0 until 61) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u62"1"
        }
        if (input(offset + 61)) {
          r = r | mask
        }
        context.offset = offset + 62
        return r
      }

      def leU63(input: ISZ[B], context: Context): U63 = {
        val offset = context.offset
        if (offset + 63 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u63"0"
        }
        var r = u63"0"
        var mask = u63"1" << u63"62"
        for (i <- 0 until 62) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u63"1"
        }
        if (input(offset + 62)) {
          r = r | mask
        }
        context.offset = offset + 63
        return r
      }

      def leU64(input: ISZ[B], context: Context): U64 = {
        val offset = context.offset
        if (offset + 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u64"0"
        }
        var r = u64"0"
        var mask = u64"1" << u64"63"
        for (i <- 0 until 63) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u64"1"
        }
        if (input(offset + 63)) {
          r = r | mask
        }
        context.offset = offset + 64
        return r
      }

      def leS64(input: ISZ[B], context: Context): S64 = {
        return conversions.U64.toS64(leU64(input, context))
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

      def beU8S(input: MSZ[B], context: Context, result: MSZ[U8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beU8(input, context)
        }
      }

      def beS8S(input: MSZ[B], context: Context, result: MSZ[S8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = beS8(input, context)
        }
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

      def leU8S(input: MSZ[B], context: Context, result: MSZ[U8], size: Z): Unit = {
        if (context.offset + size * 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return
        }
        for (i <- 0 until size) {
          result(i) = leU8(input, context)
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
          result(i) = leS8(input, context)
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

      // Slang script gen:
      // for (i <- 2 to 64) {
      //   val sizeM1 = i - 1
      //   println(
      //     st"""def beU$i(input: MSZ[B], context: Context): U$i = {
      //         |  val offset = context.offset
      //         |  if (offset + $i > input.size) {
      //         |    context.signalError(INSUFFICIENT_BUFFER_SIZE)
      //         |  }
      //         |  if (context.hasError) {
      //         |    return u$i"0"
      //         |  }
      //         |  var r = u$i"0"
      //         |  var mask = u$i"1"
      //         |  for (i <- 0 until $sizeM1) {
      //         |    if (input(offset + i)) {
      //         |      r = r | mask
      //         |    }
      //         |    mask = mask << u$i"1"
      //         |  }
      //         |  if (input(offset + $sizeM1)) {
      //         |    r = r | mask
      //         |  }
      //         |  context.offset = offset + $i
      //         |  return r
      //         |}""".render)
      //   println()
      // }

      def beU2(input: MSZ[B], context: Context): U2 = {
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

      def beU3(input: MSZ[B], context: Context): U3 = {
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

      def beU4(input: MSZ[B], context: Context): U4 = {
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

      def beU5(input: MSZ[B], context: Context): U5 = {
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

      def beU6(input: MSZ[B], context: Context): U6 = {
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

      def beU7(input: MSZ[B], context: Context): U7 = {
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

      def beU8(input: MSZ[B], context: Context): U8 = {
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

      def beS8(input: MSZ[B], context: Context): S8 = {
        return conversions.U8.toS8(beU8(input, context))
      }

      def beU9(input: MSZ[B], context: Context): U9 = {
        val offset = context.offset
        if (offset + 9 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u9"0"
        }
        var r = u9"0"
        var mask = u9"1"
        for (i <- 0 until 8) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u9"1"
        }
        if (input(offset + 8)) {
          r = r | mask
        }
        context.offset = offset + 9
        return r
      }

      def beU10(input: MSZ[B], context: Context): U10 = {
        val offset = context.offset
        if (offset + 10 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u10"0"
        }
        var r = u10"0"
        var mask = u10"1"
        for (i <- 0 until 9) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u10"1"
        }
        if (input(offset + 9)) {
          r = r | mask
        }
        context.offset = offset + 10
        return r
      }

      def beU11(input: MSZ[B], context: Context): U11 = {
        val offset = context.offset
        if (offset + 11 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u11"0"
        }
        var r = u11"0"
        var mask = u11"1"
        for (i <- 0 until 10) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u11"1"
        }
        if (input(offset + 10)) {
          r = r | mask
        }
        context.offset = offset + 11
        return r
      }

      def beU12(input: MSZ[B], context: Context): U12 = {
        val offset = context.offset
        if (offset + 12 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u12"0"
        }
        var r = u12"0"
        var mask = u12"1"
        for (i <- 0 until 11) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u12"1"
        }
        if (input(offset + 11)) {
          r = r | mask
        }
        context.offset = offset + 12
        return r
      }

      def beU13(input: MSZ[B], context: Context): U13 = {
        val offset = context.offset
        if (offset + 13 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u13"0"
        }
        var r = u13"0"
        var mask = u13"1"
        for (i <- 0 until 12) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u13"1"
        }
        if (input(offset + 12)) {
          r = r | mask
        }
        context.offset = offset + 13
        return r
      }

      def beU14(input: MSZ[B], context: Context): U14 = {
        val offset = context.offset
        if (offset + 14 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u14"0"
        }
        var r = u14"0"
        var mask = u14"1"
        for (i <- 0 until 13) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u14"1"
        }
        if (input(offset + 13)) {
          r = r | mask
        }
        context.offset = offset + 14
        return r
      }

      def beU15(input: MSZ[B], context: Context): U15 = {
        val offset = context.offset
        if (offset + 15 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u15"0"
        }
        var r = u15"0"
        var mask = u15"1"
        for (i <- 0 until 14) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u15"1"
        }
        if (input(offset + 14)) {
          r = r | mask
        }
        context.offset = offset + 15
        return r
      }

      def beU16(input: MSZ[B], context: Context): U16 = {
        val offset = context.offset
        if (offset + 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u16"0"
        }
        var r = u16"0"
        var mask = u16"1"
        for (i <- 0 until 15) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u16"1"
        }
        if (input(offset + 15)) {
          r = r | mask
        }
        context.offset = offset + 16
        return r
      }

      def beS16(input: MSZ[B], context: Context): S16 = {
        return conversions.U16.toS16(beU16(input, context))
      }

      def beU17(input: MSZ[B], context: Context): U17 = {
        val offset = context.offset
        if (offset + 17 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u17"0"
        }
        var r = u17"0"
        var mask = u17"1"
        for (i <- 0 until 16) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u17"1"
        }
        if (input(offset + 16)) {
          r = r | mask
        }
        context.offset = offset + 17
        return r
      }

      def beU18(input: MSZ[B], context: Context): U18 = {
        val offset = context.offset
        if (offset + 18 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u18"0"
        }
        var r = u18"0"
        var mask = u18"1"
        for (i <- 0 until 17) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u18"1"
        }
        if (input(offset + 17)) {
          r = r | mask
        }
        context.offset = offset + 18
        return r
      }

      def beU19(input: MSZ[B], context: Context): U19 = {
        val offset = context.offset
        if (offset + 19 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u19"0"
        }
        var r = u19"0"
        var mask = u19"1"
        for (i <- 0 until 18) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u19"1"
        }
        if (input(offset + 18)) {
          r = r | mask
        }
        context.offset = offset + 19
        return r
      }

      def beU20(input: MSZ[B], context: Context): U20 = {
        val offset = context.offset
        if (offset + 20 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u20"0"
        }
        var r = u20"0"
        var mask = u20"1"
        for (i <- 0 until 19) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u20"1"
        }
        if (input(offset + 19)) {
          r = r | mask
        }
        context.offset = offset + 20
        return r
      }

      def beU21(input: MSZ[B], context: Context): U21 = {
        val offset = context.offset
        if (offset + 21 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u21"0"
        }
        var r = u21"0"
        var mask = u21"1"
        for (i <- 0 until 20) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u21"1"
        }
        if (input(offset + 20)) {
          r = r | mask
        }
        context.offset = offset + 21
        return r
      }

      def beU22(input: MSZ[B], context: Context): U22 = {
        val offset = context.offset
        if (offset + 22 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u22"0"
        }
        var r = u22"0"
        var mask = u22"1"
        for (i <- 0 until 21) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u22"1"
        }
        if (input(offset + 21)) {
          r = r | mask
        }
        context.offset = offset + 22
        return r
      }

      def beU23(input: MSZ[B], context: Context): U23 = {
        val offset = context.offset
        if (offset + 23 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u23"0"
        }
        var r = u23"0"
        var mask = u23"1"
        for (i <- 0 until 22) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u23"1"
        }
        if (input(offset + 22)) {
          r = r | mask
        }
        context.offset = offset + 23
        return r
      }

      def beU24(input: MSZ[B], context: Context): U24 = {
        val offset = context.offset
        if (offset + 24 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u24"0"
        }
        var r = u24"0"
        var mask = u24"1"
        for (i <- 0 until 23) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u24"1"
        }
        if (input(offset + 23)) {
          r = r | mask
        }
        context.offset = offset + 24
        return r
      }

      def beU25(input: MSZ[B], context: Context): U25 = {
        val offset = context.offset
        if (offset + 25 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u25"0"
        }
        var r = u25"0"
        var mask = u25"1"
        for (i <- 0 until 24) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u25"1"
        }
        if (input(offset + 24)) {
          r = r | mask
        }
        context.offset = offset + 25
        return r
      }

      def beU26(input: MSZ[B], context: Context): U26 = {
        val offset = context.offset
        if (offset + 26 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u26"0"
        }
        var r = u26"0"
        var mask = u26"1"
        for (i <- 0 until 25) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u26"1"
        }
        if (input(offset + 25)) {
          r = r | mask
        }
        context.offset = offset + 26
        return r
      }

      def beU27(input: MSZ[B], context: Context): U27 = {
        val offset = context.offset
        if (offset + 27 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u27"0"
        }
        var r = u27"0"
        var mask = u27"1"
        for (i <- 0 until 26) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u27"1"
        }
        if (input(offset + 26)) {
          r = r | mask
        }
        context.offset = offset + 27
        return r
      }

      def beU28(input: MSZ[B], context: Context): U28 = {
        val offset = context.offset
        if (offset + 28 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u28"0"
        }
        var r = u28"0"
        var mask = u28"1"
        for (i <- 0 until 27) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u28"1"
        }
        if (input(offset + 27)) {
          r = r | mask
        }
        context.offset = offset + 28
        return r
      }

      def beU29(input: MSZ[B], context: Context): U29 = {
        val offset = context.offset
        if (offset + 29 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u29"0"
        }
        var r = u29"0"
        var mask = u29"1"
        for (i <- 0 until 28) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u29"1"
        }
        if (input(offset + 28)) {
          r = r | mask
        }
        context.offset = offset + 29
        return r
      }

      def beU30(input: MSZ[B], context: Context): U30 = {
        val offset = context.offset
        if (offset + 30 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u30"0"
        }
        var r = u30"0"
        var mask = u30"1"
        for (i <- 0 until 29) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u30"1"
        }
        if (input(offset + 29)) {
          r = r | mask
        }
        context.offset = offset + 30
        return r
      }

      def beU31(input: MSZ[B], context: Context): U31 = {
        val offset = context.offset
        if (offset + 31 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u31"0"
        }
        var r = u31"0"
        var mask = u31"1"
        for (i <- 0 until 30) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u31"1"
        }
        if (input(offset + 30)) {
          r = r | mask
        }
        context.offset = offset + 31
        return r
      }

      def beU32(input: MSZ[B], context: Context): U32 = {
        val offset = context.offset
        if (offset + 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u32"0"
        }
        var r = u32"0"
        var mask = u32"1"
        for (i <- 0 until 31) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u32"1"
        }
        if (input(offset + 31)) {
          r = r | mask
        }
        context.offset = offset + 32
        return r
      }

      def beS32(input: MSZ[B], context: Context): S32 = {
        return conversions.U32.toS32(beU32(input, context))
      }

      def beU33(input: MSZ[B], context: Context): U33 = {
        val offset = context.offset
        if (offset + 33 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u33"0"
        }
        var r = u33"0"
        var mask = u33"1"
        for (i <- 0 until 32) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u33"1"
        }
        if (input(offset + 32)) {
          r = r | mask
        }
        context.offset = offset + 33
        return r
      }

      def beU34(input: MSZ[B], context: Context): U34 = {
        val offset = context.offset
        if (offset + 34 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u34"0"
        }
        var r = u34"0"
        var mask = u34"1"
        for (i <- 0 until 33) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u34"1"
        }
        if (input(offset + 33)) {
          r = r | mask
        }
        context.offset = offset + 34
        return r
      }

      def beU35(input: MSZ[B], context: Context): U35 = {
        val offset = context.offset
        if (offset + 35 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u35"0"
        }
        var r = u35"0"
        var mask = u35"1"
        for (i <- 0 until 34) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u35"1"
        }
        if (input(offset + 34)) {
          r = r | mask
        }
        context.offset = offset + 35
        return r
      }

      def beU36(input: MSZ[B], context: Context): U36 = {
        val offset = context.offset
        if (offset + 36 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u36"0"
        }
        var r = u36"0"
        var mask = u36"1"
        for (i <- 0 until 35) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u36"1"
        }
        if (input(offset + 35)) {
          r = r | mask
        }
        context.offset = offset + 36
        return r
      }

      def beU37(input: MSZ[B], context: Context): U37 = {
        val offset = context.offset
        if (offset + 37 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u37"0"
        }
        var r = u37"0"
        var mask = u37"1"
        for (i <- 0 until 36) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u37"1"
        }
        if (input(offset + 36)) {
          r = r | mask
        }
        context.offset = offset + 37
        return r
      }

      def beU38(input: MSZ[B], context: Context): U38 = {
        val offset = context.offset
        if (offset + 38 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u38"0"
        }
        var r = u38"0"
        var mask = u38"1"
        for (i <- 0 until 37) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u38"1"
        }
        if (input(offset + 37)) {
          r = r | mask
        }
        context.offset = offset + 38
        return r
      }

      def beU39(input: MSZ[B], context: Context): U39 = {
        val offset = context.offset
        if (offset + 39 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u39"0"
        }
        var r = u39"0"
        var mask = u39"1"
        for (i <- 0 until 38) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u39"1"
        }
        if (input(offset + 38)) {
          r = r | mask
        }
        context.offset = offset + 39
        return r
      }

      def beU40(input: MSZ[B], context: Context): U40 = {
        val offset = context.offset
        if (offset + 40 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u40"0"
        }
        var r = u40"0"
        var mask = u40"1"
        for (i <- 0 until 39) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u40"1"
        }
        if (input(offset + 39)) {
          r = r | mask
        }
        context.offset = offset + 40
        return r
      }

      def beU41(input: MSZ[B], context: Context): U41 = {
        val offset = context.offset
        if (offset + 41 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u41"0"
        }
        var r = u41"0"
        var mask = u41"1"
        for (i <- 0 until 40) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u41"1"
        }
        if (input(offset + 40)) {
          r = r | mask
        }
        context.offset = offset + 41
        return r
      }

      def beU42(input: MSZ[B], context: Context): U42 = {
        val offset = context.offset
        if (offset + 42 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u42"0"
        }
        var r = u42"0"
        var mask = u42"1"
        for (i <- 0 until 41) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u42"1"
        }
        if (input(offset + 41)) {
          r = r | mask
        }
        context.offset = offset + 42
        return r
      }

      def beU43(input: MSZ[B], context: Context): U43 = {
        val offset = context.offset
        if (offset + 43 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u43"0"
        }
        var r = u43"0"
        var mask = u43"1"
        for (i <- 0 until 42) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u43"1"
        }
        if (input(offset + 42)) {
          r = r | mask
        }
        context.offset = offset + 43
        return r
      }

      def beU44(input: MSZ[B], context: Context): U44 = {
        val offset = context.offset
        if (offset + 44 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u44"0"
        }
        var r = u44"0"
        var mask = u44"1"
        for (i <- 0 until 43) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u44"1"
        }
        if (input(offset + 43)) {
          r = r | mask
        }
        context.offset = offset + 44
        return r
      }

      def beU45(input: MSZ[B], context: Context): U45 = {
        val offset = context.offset
        if (offset + 45 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u45"0"
        }
        var r = u45"0"
        var mask = u45"1"
        for (i <- 0 until 44) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u45"1"
        }
        if (input(offset + 44)) {
          r = r | mask
        }
        context.offset = offset + 45
        return r
      }

      def beU46(input: MSZ[B], context: Context): U46 = {
        val offset = context.offset
        if (offset + 46 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u46"0"
        }
        var r = u46"0"
        var mask = u46"1"
        for (i <- 0 until 45) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u46"1"
        }
        if (input(offset + 45)) {
          r = r | mask
        }
        context.offset = offset + 46
        return r
      }

      def beU47(input: MSZ[B], context: Context): U47 = {
        val offset = context.offset
        if (offset + 47 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u47"0"
        }
        var r = u47"0"
        var mask = u47"1"
        for (i <- 0 until 46) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u47"1"
        }
        if (input(offset + 46)) {
          r = r | mask
        }
        context.offset = offset + 47
        return r
      }

      def beU48(input: MSZ[B], context: Context): U48 = {
        val offset = context.offset
        if (offset + 48 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u48"0"
        }
        var r = u48"0"
        var mask = u48"1"
        for (i <- 0 until 47) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u48"1"
        }
        if (input(offset + 47)) {
          r = r | mask
        }
        context.offset = offset + 48
        return r
      }

      def beU49(input: MSZ[B], context: Context): U49 = {
        val offset = context.offset
        if (offset + 49 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u49"0"
        }
        var r = u49"0"
        var mask = u49"1"
        for (i <- 0 until 48) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u49"1"
        }
        if (input(offset + 48)) {
          r = r | mask
        }
        context.offset = offset + 49
        return r
      }

      def beU50(input: MSZ[B], context: Context): U50 = {
        val offset = context.offset
        if (offset + 50 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u50"0"
        }
        var r = u50"0"
        var mask = u50"1"
        for (i <- 0 until 49) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u50"1"
        }
        if (input(offset + 49)) {
          r = r | mask
        }
        context.offset = offset + 50
        return r
      }

      def beU51(input: MSZ[B], context: Context): U51 = {
        val offset = context.offset
        if (offset + 51 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u51"0"
        }
        var r = u51"0"
        var mask = u51"1"
        for (i <- 0 until 50) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u51"1"
        }
        if (input(offset + 50)) {
          r = r | mask
        }
        context.offset = offset + 51
        return r
      }

      def beU52(input: MSZ[B], context: Context): U52 = {
        val offset = context.offset
        if (offset + 52 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u52"0"
        }
        var r = u52"0"
        var mask = u52"1"
        for (i <- 0 until 51) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u52"1"
        }
        if (input(offset + 51)) {
          r = r | mask
        }
        context.offset = offset + 52
        return r
      }

      def beU53(input: MSZ[B], context: Context): U53 = {
        val offset = context.offset
        if (offset + 53 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u53"0"
        }
        var r = u53"0"
        var mask = u53"1"
        for (i <- 0 until 52) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u53"1"
        }
        if (input(offset + 52)) {
          r = r | mask
        }
        context.offset = offset + 53
        return r
      }

      def beU54(input: MSZ[B], context: Context): U54 = {
        val offset = context.offset
        if (offset + 54 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u54"0"
        }
        var r = u54"0"
        var mask = u54"1"
        for (i <- 0 until 53) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u54"1"
        }
        if (input(offset + 53)) {
          r = r | mask
        }
        context.offset = offset + 54
        return r
      }

      def beU55(input: MSZ[B], context: Context): U55 = {
        val offset = context.offset
        if (offset + 55 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u55"0"
        }
        var r = u55"0"
        var mask = u55"1"
        for (i <- 0 until 54) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u55"1"
        }
        if (input(offset + 54)) {
          r = r | mask
        }
        context.offset = offset + 55
        return r
      }

      def beU56(input: MSZ[B], context: Context): U56 = {
        val offset = context.offset
        if (offset + 56 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u56"0"
        }
        var r = u56"0"
        var mask = u56"1"
        for (i <- 0 until 55) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u56"1"
        }
        if (input(offset + 55)) {
          r = r | mask
        }
        context.offset = offset + 56
        return r
      }

      def beU57(input: MSZ[B], context: Context): U57 = {
        val offset = context.offset
        if (offset + 57 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u57"0"
        }
        var r = u57"0"
        var mask = u57"1"
        for (i <- 0 until 56) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u57"1"
        }
        if (input(offset + 56)) {
          r = r | mask
        }
        context.offset = offset + 57
        return r
      }

      def beU58(input: MSZ[B], context: Context): U58 = {
        val offset = context.offset
        if (offset + 58 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u58"0"
        }
        var r = u58"0"
        var mask = u58"1"
        for (i <- 0 until 57) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u58"1"
        }
        if (input(offset + 57)) {
          r = r | mask
        }
        context.offset = offset + 58
        return r
      }

      def beU59(input: MSZ[B], context: Context): U59 = {
        val offset = context.offset
        if (offset + 59 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u59"0"
        }
        var r = u59"0"
        var mask = u59"1"
        for (i <- 0 until 58) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u59"1"
        }
        if (input(offset + 58)) {
          r = r | mask
        }
        context.offset = offset + 59
        return r
      }

      def beU60(input: MSZ[B], context: Context): U60 = {
        val offset = context.offset
        if (offset + 60 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u60"0"
        }
        var r = u60"0"
        var mask = u60"1"
        for (i <- 0 until 59) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u60"1"
        }
        if (input(offset + 59)) {
          r = r | mask
        }
        context.offset = offset + 60
        return r
      }

      def beU61(input: MSZ[B], context: Context): U61 = {
        val offset = context.offset
        if (offset + 61 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u61"0"
        }
        var r = u61"0"
        var mask = u61"1"
        for (i <- 0 until 60) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u61"1"
        }
        if (input(offset + 60)) {
          r = r | mask
        }
        context.offset = offset + 61
        return r
      }

      def beU62(input: MSZ[B], context: Context): U62 = {
        val offset = context.offset
        if (offset + 62 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u62"0"
        }
        var r = u62"0"
        var mask = u62"1"
        for (i <- 0 until 61) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u62"1"
        }
        if (input(offset + 61)) {
          r = r | mask
        }
        context.offset = offset + 62
        return r
      }

      def beU63(input: MSZ[B], context: Context): U63 = {
        val offset = context.offset
        if (offset + 63 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u63"0"
        }
        var r = u63"0"
        var mask = u63"1"
        for (i <- 0 until 62) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u63"1"
        }
        if (input(offset + 62)) {
          r = r | mask
        }
        context.offset = offset + 63
        return r
      }

      def beU64(input: MSZ[B], context: Context): U64 = {
        val offset = context.offset
        if (offset + 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u64"0"
        }
        var r = u64"0"
        var mask = u64"1"
        for (i <- 0 until 63) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask << u64"1"
        }
        if (input(offset + 63)) {
          r = r | mask
        }
        context.offset = offset + 64
        return r
      }

      def beS64(input: MSZ[B], context: Context): S64 = {
        return conversions.U64.toS64(beU64(input, context))
      }

      // Slang script gen:
      // for (i <- 2 to 64) {
      //   val sizeM1 = i - 1
      //   println(
      //     st"""def leU$i(input: MSZ[B], context: Context): U$i = {
      //         |  val offset = context.offset
      //         |  if (offset + $i > input.size) {
      //         |    context.signalError(INSUFFICIENT_BUFFER_SIZE)
      //         |  }
      //         |  if (context.hasError) {
      //         |    return u$i"0"
      //         |  }
      //         |  var r = u$i"0"
      //         |  var mask = u$i"1" << u$i"$sizeM1"
      //         |  for (i <- 0 until $sizeM1) {
      //         |    if (input(offset + i)) {
      //         |      r = r | mask
      //         |    }
      //         |    mask = mask >>> u$i"1"
      //         |  }
      //         |  if (input(offset + $sizeM1)) {
      //         |    r = r | mask
      //         |  }
      //         |  context.offset = offset + $i
      //         |  return r
      //         |}""".render)
      //   println()
      // }

      def leU2(input: MSZ[B], context: Context): U2 = {
        val offset = context.offset
        if (offset + 2 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u2"0"
        }
        var r = u2"0"
        var mask = u2"1" << u2"1"
        for (i <- 0 until 1) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u2"1"
        }
        if (input(offset + 1)) {
          r = r | mask
        }
        context.offset = offset + 2
        return r
      }

      def leU3(input: MSZ[B], context: Context): U3 = {
        val offset = context.offset
        if (offset + 3 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u3"0"
        }
        var r = u3"0"
        var mask = u3"1" << u3"2"
        for (i <- 0 until 2) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u3"1"
        }
        if (input(offset + 2)) {
          r = r | mask
        }
        context.offset = offset + 3
        return r
      }

      def leU4(input: MSZ[B], context: Context): U4 = {
        val offset = context.offset
        if (offset + 4 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u4"0"
        }
        var r = u4"0"
        var mask = u4"1" << u4"3"
        for (i <- 0 until 3) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u4"1"
        }
        if (input(offset + 3)) {
          r = r | mask
        }
        context.offset = offset + 4
        return r
      }

      def leU5(input: MSZ[B], context: Context): U5 = {
        val offset = context.offset
        if (offset + 5 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u5"0"
        }
        var r = u5"0"
        var mask = u5"1" << u5"4"
        for (i <- 0 until 4) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u5"1"
        }
        if (input(offset + 4)) {
          r = r | mask
        }
        context.offset = offset + 5
        return r
      }

      def leU6(input: MSZ[B], context: Context): U6 = {
        val offset = context.offset
        if (offset + 6 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u6"0"
        }
        var r = u6"0"
        var mask = u6"1" << u6"5"
        for (i <- 0 until 5) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u6"1"
        }
        if (input(offset + 5)) {
          r = r | mask
        }
        context.offset = offset + 6
        return r
      }

      def leU7(input: MSZ[B], context: Context): U7 = {
        val offset = context.offset
        if (offset + 7 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u7"0"
        }
        var r = u7"0"
        var mask = u7"1" << u7"6"
        for (i <- 0 until 6) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u7"1"
        }
        if (input(offset + 6)) {
          r = r | mask
        }
        context.offset = offset + 7
        return r
      }

      def leU8(input: MSZ[B], context: Context): U8 = {
        val offset = context.offset
        if (offset + 8 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u8"0"
        }
        var r = u8"0"
        var mask = u8"1" << u8"7"
        for (i <- 0 until 7) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u8"1"
        }
        if (input(offset + 7)) {
          r = r | mask
        }
        context.offset = offset + 8
        return r
      }

      def leS8(input: MSZ[B], context: Context): S8 = {
        return conversions.U8.toS8(leU8(input, context))
      }

      def leU9(input: MSZ[B], context: Context): U9 = {
        val offset = context.offset
        if (offset + 9 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u9"0"
        }
        var r = u9"0"
        var mask = u9"1" << u9"8"
        for (i <- 0 until 8) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u9"1"
        }
        if (input(offset + 8)) {
          r = r | mask
        }
        context.offset = offset + 9
        return r
      }

      def leU10(input: MSZ[B], context: Context): U10 = {
        val offset = context.offset
        if (offset + 10 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u10"0"
        }
        var r = u10"0"
        var mask = u10"1" << u10"9"
        for (i <- 0 until 9) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u10"1"
        }
        if (input(offset + 9)) {
          r = r | mask
        }
        context.offset = offset + 10
        return r
      }

      def leU11(input: MSZ[B], context: Context): U11 = {
        val offset = context.offset
        if (offset + 11 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u11"0"
        }
        var r = u11"0"
        var mask = u11"1" << u11"10"
        for (i <- 0 until 10) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u11"1"
        }
        if (input(offset + 10)) {
          r = r | mask
        }
        context.offset = offset + 11
        return r
      }

      def leU12(input: MSZ[B], context: Context): U12 = {
        val offset = context.offset
        if (offset + 12 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u12"0"
        }
        var r = u12"0"
        var mask = u12"1" << u12"11"
        for (i <- 0 until 11) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u12"1"
        }
        if (input(offset + 11)) {
          r = r | mask
        }
        context.offset = offset + 12
        return r
      }

      def leU13(input: MSZ[B], context: Context): U13 = {
        val offset = context.offset
        if (offset + 13 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u13"0"
        }
        var r = u13"0"
        var mask = u13"1" << u13"12"
        for (i <- 0 until 12) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u13"1"
        }
        if (input(offset + 12)) {
          r = r | mask
        }
        context.offset = offset + 13
        return r
      }

      def leU14(input: MSZ[B], context: Context): U14 = {
        val offset = context.offset
        if (offset + 14 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u14"0"
        }
        var r = u14"0"
        var mask = u14"1" << u14"13"
        for (i <- 0 until 13) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u14"1"
        }
        if (input(offset + 13)) {
          r = r | mask
        }
        context.offset = offset + 14
        return r
      }

      def leU15(input: MSZ[B], context: Context): U15 = {
        val offset = context.offset
        if (offset + 15 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u15"0"
        }
        var r = u15"0"
        var mask = u15"1" << u15"14"
        for (i <- 0 until 14) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u15"1"
        }
        if (input(offset + 14)) {
          r = r | mask
        }
        context.offset = offset + 15
        return r
      }

      def leU16(input: MSZ[B], context: Context): U16 = {
        val offset = context.offset
        if (offset + 16 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u16"0"
        }
        var r = u16"0"
        var mask = u16"1" << u16"15"
        for (i <- 0 until 15) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u16"1"
        }
        if (input(offset + 15)) {
          r = r | mask
        }
        context.offset = offset + 16
        return r
      }

      def leS16(input: MSZ[B], context: Context): S16 = {
        return conversions.U16.toS16(leU16(input, context))
      }

      def leU17(input: MSZ[B], context: Context): U17 = {
        val offset = context.offset
        if (offset + 17 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u17"0"
        }
        var r = u17"0"
        var mask = u17"1" << u17"16"
        for (i <- 0 until 16) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u17"1"
        }
        if (input(offset + 16)) {
          r = r | mask
        }
        context.offset = offset + 17
        return r
      }

      def leU18(input: MSZ[B], context: Context): U18 = {
        val offset = context.offset
        if (offset + 18 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u18"0"
        }
        var r = u18"0"
        var mask = u18"1" << u18"17"
        for (i <- 0 until 17) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u18"1"
        }
        if (input(offset + 17)) {
          r = r | mask
        }
        context.offset = offset + 18
        return r
      }

      def leU19(input: MSZ[B], context: Context): U19 = {
        val offset = context.offset
        if (offset + 19 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u19"0"
        }
        var r = u19"0"
        var mask = u19"1" << u19"18"
        for (i <- 0 until 18) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u19"1"
        }
        if (input(offset + 18)) {
          r = r | mask
        }
        context.offset = offset + 19
        return r
      }

      def leU20(input: MSZ[B], context: Context): U20 = {
        val offset = context.offset
        if (offset + 20 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u20"0"
        }
        var r = u20"0"
        var mask = u20"1" << u20"19"
        for (i <- 0 until 19) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u20"1"
        }
        if (input(offset + 19)) {
          r = r | mask
        }
        context.offset = offset + 20
        return r
      }

      def leU21(input: MSZ[B], context: Context): U21 = {
        val offset = context.offset
        if (offset + 21 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u21"0"
        }
        var r = u21"0"
        var mask = u21"1" << u21"20"
        for (i <- 0 until 20) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u21"1"
        }
        if (input(offset + 20)) {
          r = r | mask
        }
        context.offset = offset + 21
        return r
      }

      def leU22(input: MSZ[B], context: Context): U22 = {
        val offset = context.offset
        if (offset + 22 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u22"0"
        }
        var r = u22"0"
        var mask = u22"1" << u22"21"
        for (i <- 0 until 21) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u22"1"
        }
        if (input(offset + 21)) {
          r = r | mask
        }
        context.offset = offset + 22
        return r
      }

      def leU23(input: MSZ[B], context: Context): U23 = {
        val offset = context.offset
        if (offset + 23 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u23"0"
        }
        var r = u23"0"
        var mask = u23"1" << u23"22"
        for (i <- 0 until 22) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u23"1"
        }
        if (input(offset + 22)) {
          r = r | mask
        }
        context.offset = offset + 23
        return r
      }

      def leU24(input: MSZ[B], context: Context): U24 = {
        val offset = context.offset
        if (offset + 24 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u24"0"
        }
        var r = u24"0"
        var mask = u24"1" << u24"23"
        for (i <- 0 until 23) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u24"1"
        }
        if (input(offset + 23)) {
          r = r | mask
        }
        context.offset = offset + 24
        return r
      }

      def leU25(input: MSZ[B], context: Context): U25 = {
        val offset = context.offset
        if (offset + 25 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u25"0"
        }
        var r = u25"0"
        var mask = u25"1" << u25"24"
        for (i <- 0 until 24) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u25"1"
        }
        if (input(offset + 24)) {
          r = r | mask
        }
        context.offset = offset + 25
        return r
      }

      def leU26(input: MSZ[B], context: Context): U26 = {
        val offset = context.offset
        if (offset + 26 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u26"0"
        }
        var r = u26"0"
        var mask = u26"1" << u26"25"
        for (i <- 0 until 25) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u26"1"
        }
        if (input(offset + 25)) {
          r = r | mask
        }
        context.offset = offset + 26
        return r
      }

      def leU27(input: MSZ[B], context: Context): U27 = {
        val offset = context.offset
        if (offset + 27 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u27"0"
        }
        var r = u27"0"
        var mask = u27"1" << u27"26"
        for (i <- 0 until 26) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u27"1"
        }
        if (input(offset + 26)) {
          r = r | mask
        }
        context.offset = offset + 27
        return r
      }

      def leU28(input: MSZ[B], context: Context): U28 = {
        val offset = context.offset
        if (offset + 28 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u28"0"
        }
        var r = u28"0"
        var mask = u28"1" << u28"27"
        for (i <- 0 until 27) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u28"1"
        }
        if (input(offset + 27)) {
          r = r | mask
        }
        context.offset = offset + 28
        return r
      }

      def leU29(input: MSZ[B], context: Context): U29 = {
        val offset = context.offset
        if (offset + 29 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u29"0"
        }
        var r = u29"0"
        var mask = u29"1" << u29"28"
        for (i <- 0 until 28) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u29"1"
        }
        if (input(offset + 28)) {
          r = r | mask
        }
        context.offset = offset + 29
        return r
      }

      def leU30(input: MSZ[B], context: Context): U30 = {
        val offset = context.offset
        if (offset + 30 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u30"0"
        }
        var r = u30"0"
        var mask = u30"1" << u30"29"
        for (i <- 0 until 29) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u30"1"
        }
        if (input(offset + 29)) {
          r = r | mask
        }
        context.offset = offset + 30
        return r
      }

      def leU31(input: MSZ[B], context: Context): U31 = {
        val offset = context.offset
        if (offset + 31 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u31"0"
        }
        var r = u31"0"
        var mask = u31"1" << u31"30"
        for (i <- 0 until 30) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u31"1"
        }
        if (input(offset + 30)) {
          r = r | mask
        }
        context.offset = offset + 31
        return r
      }

      def leU32(input: MSZ[B], context: Context): U32 = {
        val offset = context.offset
        if (offset + 32 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u32"0"
        }
        var r = u32"0"
        var mask = u32"1" << u32"31"
        for (i <- 0 until 31) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u32"1"
        }
        if (input(offset + 31)) {
          r = r | mask
        }
        context.offset = offset + 32
        return r
      }

      def leS32(input: MSZ[B], context: Context): S32 = {
        return conversions.U32.toS32(leU32(input, context))
      }

      def leU33(input: MSZ[B], context: Context): U33 = {
        val offset = context.offset
        if (offset + 33 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u33"0"
        }
        var r = u33"0"
        var mask = u33"1" << u33"32"
        for (i <- 0 until 32) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u33"1"
        }
        if (input(offset + 32)) {
          r = r | mask
        }
        context.offset = offset + 33
        return r
      }

      def leU34(input: MSZ[B], context: Context): U34 = {
        val offset = context.offset
        if (offset + 34 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u34"0"
        }
        var r = u34"0"
        var mask = u34"1" << u34"33"
        for (i <- 0 until 33) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u34"1"
        }
        if (input(offset + 33)) {
          r = r | mask
        }
        context.offset = offset + 34
        return r
      }

      def leU35(input: MSZ[B], context: Context): U35 = {
        val offset = context.offset
        if (offset + 35 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u35"0"
        }
        var r = u35"0"
        var mask = u35"1" << u35"34"
        for (i <- 0 until 34) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u35"1"
        }
        if (input(offset + 34)) {
          r = r | mask
        }
        context.offset = offset + 35
        return r
      }

      def leU36(input: MSZ[B], context: Context): U36 = {
        val offset = context.offset
        if (offset + 36 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u36"0"
        }
        var r = u36"0"
        var mask = u36"1" << u36"35"
        for (i <- 0 until 35) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u36"1"
        }
        if (input(offset + 35)) {
          r = r | mask
        }
        context.offset = offset + 36
        return r
      }

      def leU37(input: MSZ[B], context: Context): U37 = {
        val offset = context.offset
        if (offset + 37 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u37"0"
        }
        var r = u37"0"
        var mask = u37"1" << u37"36"
        for (i <- 0 until 36) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u37"1"
        }
        if (input(offset + 36)) {
          r = r | mask
        }
        context.offset = offset + 37
        return r
      }

      def leU38(input: MSZ[B], context: Context): U38 = {
        val offset = context.offset
        if (offset + 38 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u38"0"
        }
        var r = u38"0"
        var mask = u38"1" << u38"37"
        for (i <- 0 until 37) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u38"1"
        }
        if (input(offset + 37)) {
          r = r | mask
        }
        context.offset = offset + 38
        return r
      }

      def leU39(input: MSZ[B], context: Context): U39 = {
        val offset = context.offset
        if (offset + 39 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u39"0"
        }
        var r = u39"0"
        var mask = u39"1" << u39"38"
        for (i <- 0 until 38) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u39"1"
        }
        if (input(offset + 38)) {
          r = r | mask
        }
        context.offset = offset + 39
        return r
      }

      def leU40(input: MSZ[B], context: Context): U40 = {
        val offset = context.offset
        if (offset + 40 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u40"0"
        }
        var r = u40"0"
        var mask = u40"1" << u40"39"
        for (i <- 0 until 39) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u40"1"
        }
        if (input(offset + 39)) {
          r = r | mask
        }
        context.offset = offset + 40
        return r
      }

      def leU41(input: MSZ[B], context: Context): U41 = {
        val offset = context.offset
        if (offset + 41 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u41"0"
        }
        var r = u41"0"
        var mask = u41"1" << u41"40"
        for (i <- 0 until 40) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u41"1"
        }
        if (input(offset + 40)) {
          r = r | mask
        }
        context.offset = offset + 41
        return r
      }

      def leU42(input: MSZ[B], context: Context): U42 = {
        val offset = context.offset
        if (offset + 42 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u42"0"
        }
        var r = u42"0"
        var mask = u42"1" << u42"41"
        for (i <- 0 until 41) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u42"1"
        }
        if (input(offset + 41)) {
          r = r | mask
        }
        context.offset = offset + 42
        return r
      }

      def leU43(input: MSZ[B], context: Context): U43 = {
        val offset = context.offset
        if (offset + 43 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u43"0"
        }
        var r = u43"0"
        var mask = u43"1" << u43"42"
        for (i <- 0 until 42) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u43"1"
        }
        if (input(offset + 42)) {
          r = r | mask
        }
        context.offset = offset + 43
        return r
      }

      def leU44(input: MSZ[B], context: Context): U44 = {
        val offset = context.offset
        if (offset + 44 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u44"0"
        }
        var r = u44"0"
        var mask = u44"1" << u44"43"
        for (i <- 0 until 43) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u44"1"
        }
        if (input(offset + 43)) {
          r = r | mask
        }
        context.offset = offset + 44
        return r
      }

      def leU45(input: MSZ[B], context: Context): U45 = {
        val offset = context.offset
        if (offset + 45 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u45"0"
        }
        var r = u45"0"
        var mask = u45"1" << u45"44"
        for (i <- 0 until 44) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u45"1"
        }
        if (input(offset + 44)) {
          r = r | mask
        }
        context.offset = offset + 45
        return r
      }

      def leU46(input: MSZ[B], context: Context): U46 = {
        val offset = context.offset
        if (offset + 46 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u46"0"
        }
        var r = u46"0"
        var mask = u46"1" << u46"45"
        for (i <- 0 until 45) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u46"1"
        }
        if (input(offset + 45)) {
          r = r | mask
        }
        context.offset = offset + 46
        return r
      }

      def leU47(input: MSZ[B], context: Context): U47 = {
        val offset = context.offset
        if (offset + 47 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u47"0"
        }
        var r = u47"0"
        var mask = u47"1" << u47"46"
        for (i <- 0 until 46) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u47"1"
        }
        if (input(offset + 46)) {
          r = r | mask
        }
        context.offset = offset + 47
        return r
      }

      def leU48(input: MSZ[B], context: Context): U48 = {
        val offset = context.offset
        if (offset + 48 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u48"0"
        }
        var r = u48"0"
        var mask = u48"1" << u48"47"
        for (i <- 0 until 47) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u48"1"
        }
        if (input(offset + 47)) {
          r = r | mask
        }
        context.offset = offset + 48
        return r
      }

      def leU49(input: MSZ[B], context: Context): U49 = {
        val offset = context.offset
        if (offset + 49 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u49"0"
        }
        var r = u49"0"
        var mask = u49"1" << u49"48"
        for (i <- 0 until 48) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u49"1"
        }
        if (input(offset + 48)) {
          r = r | mask
        }
        context.offset = offset + 49
        return r
      }

      def leU50(input: MSZ[B], context: Context): U50 = {
        val offset = context.offset
        if (offset + 50 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u50"0"
        }
        var r = u50"0"
        var mask = u50"1" << u50"49"
        for (i <- 0 until 49) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u50"1"
        }
        if (input(offset + 49)) {
          r = r | mask
        }
        context.offset = offset + 50
        return r
      }

      def leU51(input: MSZ[B], context: Context): U51 = {
        val offset = context.offset
        if (offset + 51 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u51"0"
        }
        var r = u51"0"
        var mask = u51"1" << u51"50"
        for (i <- 0 until 50) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u51"1"
        }
        if (input(offset + 50)) {
          r = r | mask
        }
        context.offset = offset + 51
        return r
      }

      def leU52(input: MSZ[B], context: Context): U52 = {
        val offset = context.offset
        if (offset + 52 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u52"0"
        }
        var r = u52"0"
        var mask = u52"1" << u52"51"
        for (i <- 0 until 51) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u52"1"
        }
        if (input(offset + 51)) {
          r = r | mask
        }
        context.offset = offset + 52
        return r
      }

      def leU53(input: MSZ[B], context: Context): U53 = {
        val offset = context.offset
        if (offset + 53 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u53"0"
        }
        var r = u53"0"
        var mask = u53"1" << u53"52"
        for (i <- 0 until 52) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u53"1"
        }
        if (input(offset + 52)) {
          r = r | mask
        }
        context.offset = offset + 53
        return r
      }

      def leU54(input: MSZ[B], context: Context): U54 = {
        val offset = context.offset
        if (offset + 54 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u54"0"
        }
        var r = u54"0"
        var mask = u54"1" << u54"53"
        for (i <- 0 until 53) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u54"1"
        }
        if (input(offset + 53)) {
          r = r | mask
        }
        context.offset = offset + 54
        return r
      }

      def leU55(input: MSZ[B], context: Context): U55 = {
        val offset = context.offset
        if (offset + 55 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u55"0"
        }
        var r = u55"0"
        var mask = u55"1" << u55"54"
        for (i <- 0 until 54) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u55"1"
        }
        if (input(offset + 54)) {
          r = r | mask
        }
        context.offset = offset + 55
        return r
      }

      def leU56(input: MSZ[B], context: Context): U56 = {
        val offset = context.offset
        if (offset + 56 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u56"0"
        }
        var r = u56"0"
        var mask = u56"1" << u56"55"
        for (i <- 0 until 55) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u56"1"
        }
        if (input(offset + 55)) {
          r = r | mask
        }
        context.offset = offset + 56
        return r
      }

      def leU57(input: MSZ[B], context: Context): U57 = {
        val offset = context.offset
        if (offset + 57 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u57"0"
        }
        var r = u57"0"
        var mask = u57"1" << u57"56"
        for (i <- 0 until 56) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u57"1"
        }
        if (input(offset + 56)) {
          r = r | mask
        }
        context.offset = offset + 57
        return r
      }

      def leU58(input: MSZ[B], context: Context): U58 = {
        val offset = context.offset
        if (offset + 58 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u58"0"
        }
        var r = u58"0"
        var mask = u58"1" << u58"57"
        for (i <- 0 until 57) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u58"1"
        }
        if (input(offset + 57)) {
          r = r | mask
        }
        context.offset = offset + 58
        return r
      }

      def leU59(input: MSZ[B], context: Context): U59 = {
        val offset = context.offset
        if (offset + 59 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u59"0"
        }
        var r = u59"0"
        var mask = u59"1" << u59"58"
        for (i <- 0 until 58) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u59"1"
        }
        if (input(offset + 58)) {
          r = r | mask
        }
        context.offset = offset + 59
        return r
      }

      def leU60(input: MSZ[B], context: Context): U60 = {
        val offset = context.offset
        if (offset + 60 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u60"0"
        }
        var r = u60"0"
        var mask = u60"1" << u60"59"
        for (i <- 0 until 59) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u60"1"
        }
        if (input(offset + 59)) {
          r = r | mask
        }
        context.offset = offset + 60
        return r
      }

      def leU61(input: MSZ[B], context: Context): U61 = {
        val offset = context.offset
        if (offset + 61 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u61"0"
        }
        var r = u61"0"
        var mask = u61"1" << u61"60"
        for (i <- 0 until 60) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u61"1"
        }
        if (input(offset + 60)) {
          r = r | mask
        }
        context.offset = offset + 61
        return r
      }

      def leU62(input: MSZ[B], context: Context): U62 = {
        val offset = context.offset
        if (offset + 62 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u62"0"
        }
        var r = u62"0"
        var mask = u62"1" << u62"61"
        for (i <- 0 until 61) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u62"1"
        }
        if (input(offset + 61)) {
          r = r | mask
        }
        context.offset = offset + 62
        return r
      }

      def leU63(input: MSZ[B], context: Context): U63 = {
        val offset = context.offset
        if (offset + 63 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u63"0"
        }
        var r = u63"0"
        var mask = u63"1" << u63"62"
        for (i <- 0 until 62) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u63"1"
        }
        if (input(offset + 62)) {
          r = r | mask
        }
        context.offset = offset + 63
        return r
      }

      def leU64(input: MSZ[B], context: Context): U64 = {
        val offset = context.offset
        if (offset + 64 > input.size) {
          context.signalError(INCOMPLETE_INPUT)
        }
        if (context.hasError) {
          return u64"0"
        }
        var r = u64"0"
        var mask = u64"1" << u64"63"
        for (i <- 0 until 63) {
          if (input(offset + i)) {
            r = r | mask
          }
          mask = mask >>> u64"1"
        }
        if (input(offset + 63)) {
          r = r | mask
        }
        context.offset = offset + 64
        return r
      }

      def leS64(input: MSZ[B], context: Context): S64 = {
        return conversions.U64.toS64(leU64(input, context))
      }
    }
  }

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
      if (offset + 1 >= output.size) {
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
      val offset = context.offset
      val size = v.size
      if (offset + size > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- size - 1 to 0 by -1) {
        bleB(output, context, v(i))
      }
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
      if (offset + 1 >= output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      output(offset) = v == u1"1"
      context.offset = offset + 1
    }

    def beU8S(output: MSZ[B], context: Context, v: MSZ[U8]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 8 >= output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beU8(output, context, v(i))
      }
    }

    def beS8S(output: MSZ[B], context: Context, v: MSZ[S8]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 8 >= output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beS8(output, context, v(i))
      }
    }

    def beU16S(output: MSZ[B], context: Context, v: MSZ[U16]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 16 >= output.size) {
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
      if (offset + size * 16 >= output.size) {
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
      if (offset + size * 32 >= output.size) {
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
      if (offset + size * 32 >= output.size) {
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
      if (offset + size * 64 >= output.size) {
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
      if (offset + size * 64 >= output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        beS64(output, context, v(i))
      }
    }

    def leU8S(output: MSZ[B], context: Context, v: MSZ[U8]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 8 >= output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leU8(output, context, v(i))
      }
    }

    def leS8S(output: MSZ[B], context: Context, v: MSZ[S8]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 8 >= output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leS8(output, context, v(i))
      }
    }

    def leU16S(output: MSZ[B], context: Context, v: MSZ[U16]): Unit = {
      val size = v.size
      val offset = context.offset
      if (offset + size * 16 >= output.size) {
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
      if (offset + size * 16 >= output.size) {
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
      if (offset + size * 32 >= output.size) {
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
      if (offset + size * 32 >= output.size) {
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
      if (offset + size * 64 >= output.size) {
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
      if (offset + size * 64 >= output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      for (i <- 0 until size) {
        leS64(output, context, v(i))
      }
    }

    // Slang script gen:
    // for (i <- 2 to 64) {
    //   val sizeM1 = i - 1
    //   println(
    //     st"""def beU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
    //         |  val offset = context.offset
    //         |  if (offset + $i > output.size) {
    //         |    context.signalError(INSUFFICIENT_BUFFER_SIZE)
    //         |  }
    //         |  if (context.hasError) {
    //         |    return
    //         |  }
    //         |  var mask = u$i"1"
    //         |  for (i <- 0 until $sizeM1) {
    //         |    if ((v & mask) != u$i"0") {
    //         |      output(offset + i) = T
    //         |    } else {
    //         |      output(offset + i) = F
    //         |    }
    //         |    mask = mask << u$i"1"
    //         |  }
    //         |  if ((v & mask) != u$i"0") {
    //         |    output(offset + $sizeM1) = T
    //         |  } else {
    //         |    output(offset + $sizeM1) = F
    //         |  }
    //         |  context.offset = offset + $i
    //         |}""".render)
    //   println()
    // }

    def beU2(output: MSZ[B], context: Context, v: U2): Unit = {
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

    def beU3(output: MSZ[B], context: Context, v: U3): Unit = {
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

    def beU4(output: MSZ[B], context: Context, v: U4): Unit = {
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

    def beU5(output: MSZ[B], context: Context, v: U5): Unit = {
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

    def beU6(output: MSZ[B], context: Context, v: U6): Unit = {
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

    def beU7(output: MSZ[B], context: Context, v: U7): Unit = {
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

    def beU8(output: MSZ[B], context: Context, v: U8): Unit = {
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

    def beS8(output: MSZ[B], context: Context, v: S8): Unit = {
      beU8(output, context, conversions.S8.toU8(v))
    }

    def beU9(output: MSZ[B], context: Context, v: U9): Unit = {
      val offset = context.offset
      if (offset + 9 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u9"1"
      for (i <- 0 until 8) {
        if ((v & mask) != u9"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u9"1"
      }
      if ((v & mask) != u9"0") {
        output(offset + 8) = T
      } else {
        output(offset + 8) = F
      }
      context.offset = offset + 9
    }

    def beU10(output: MSZ[B], context: Context, v: U10): Unit = {
      val offset = context.offset
      if (offset + 10 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u10"1"
      for (i <- 0 until 9) {
        if ((v & mask) != u10"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u10"1"
      }
      if ((v & mask) != u10"0") {
        output(offset + 9) = T
      } else {
        output(offset + 9) = F
      }
      context.offset = offset + 10
    }

    def beU11(output: MSZ[B], context: Context, v: U11): Unit = {
      val offset = context.offset
      if (offset + 11 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u11"1"
      for (i <- 0 until 10) {
        if ((v & mask) != u11"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u11"1"
      }
      if ((v & mask) != u11"0") {
        output(offset + 10) = T
      } else {
        output(offset + 10) = F
      }
      context.offset = offset + 11
    }

    def beU12(output: MSZ[B], context: Context, v: U12): Unit = {
      val offset = context.offset
      if (offset + 12 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u12"1"
      for (i <- 0 until 11) {
        if ((v & mask) != u12"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u12"1"
      }
      if ((v & mask) != u12"0") {
        output(offset + 11) = T
      } else {
        output(offset + 11) = F
      }
      context.offset = offset + 12
    }

    def beU13(output: MSZ[B], context: Context, v: U13): Unit = {
      val offset = context.offset
      if (offset + 13 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u13"1"
      for (i <- 0 until 12) {
        if ((v & mask) != u13"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u13"1"
      }
      if ((v & mask) != u13"0") {
        output(offset + 12) = T
      } else {
        output(offset + 12) = F
      }
      context.offset = offset + 13
    }

    def beU14(output: MSZ[B], context: Context, v: U14): Unit = {
      val offset = context.offset
      if (offset + 14 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u14"1"
      for (i <- 0 until 13) {
        if ((v & mask) != u14"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u14"1"
      }
      if ((v & mask) != u14"0") {
        output(offset + 13) = T
      } else {
        output(offset + 13) = F
      }
      context.offset = offset + 14
    }

    def beU15(output: MSZ[B], context: Context, v: U15): Unit = {
      val offset = context.offset
      if (offset + 15 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u15"1"
      for (i <- 0 until 14) {
        if ((v & mask) != u15"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u15"1"
      }
      if ((v & mask) != u15"0") {
        output(offset + 14) = T
      } else {
        output(offset + 14) = F
      }
      context.offset = offset + 15
    }

    def beU16(output: MSZ[B], context: Context, v: U16): Unit = {
      val offset = context.offset
      if (offset + 16 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u16"1"
      for (i <- 0 until 15) {
        if ((v & mask) != u16"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u16"1"
      }
      if ((v & mask) != u16"0") {
        output(offset + 15) = T
      } else {
        output(offset + 15) = F
      }
      context.offset = offset + 16
    }

    def beS16(output: MSZ[B], context: Context, v: S16): Unit = {
      beU16(output, context, conversions.S16.toU16(v))
    }

    def beU17(output: MSZ[B], context: Context, v: U17): Unit = {
      val offset = context.offset
      if (offset + 17 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u17"1"
      for (i <- 0 until 16) {
        if ((v & mask) != u17"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u17"1"
      }
      if ((v & mask) != u17"0") {
        output(offset + 16) = T
      } else {
        output(offset + 16) = F
      }
      context.offset = offset + 17
    }

    def beU18(output: MSZ[B], context: Context, v: U18): Unit = {
      val offset = context.offset
      if (offset + 18 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u18"1"
      for (i <- 0 until 17) {
        if ((v & mask) != u18"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u18"1"
      }
      if ((v & mask) != u18"0") {
        output(offset + 17) = T
      } else {
        output(offset + 17) = F
      }
      context.offset = offset + 18
    }

    def beU19(output: MSZ[B], context: Context, v: U19): Unit = {
      val offset = context.offset
      if (offset + 19 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u19"1"
      for (i <- 0 until 18) {
        if ((v & mask) != u19"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u19"1"
      }
      if ((v & mask) != u19"0") {
        output(offset + 18) = T
      } else {
        output(offset + 18) = F
      }
      context.offset = offset + 19
    }

    def beU20(output: MSZ[B], context: Context, v: U20): Unit = {
      val offset = context.offset
      if (offset + 20 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u20"1"
      for (i <- 0 until 19) {
        if ((v & mask) != u20"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u20"1"
      }
      if ((v & mask) != u20"0") {
        output(offset + 19) = T
      } else {
        output(offset + 19) = F
      }
      context.offset = offset + 20
    }

    def beU21(output: MSZ[B], context: Context, v: U21): Unit = {
      val offset = context.offset
      if (offset + 21 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u21"1"
      for (i <- 0 until 20) {
        if ((v & mask) != u21"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u21"1"
      }
      if ((v & mask) != u21"0") {
        output(offset + 20) = T
      } else {
        output(offset + 20) = F
      }
      context.offset = offset + 21
    }

    def beU22(output: MSZ[B], context: Context, v: U22): Unit = {
      val offset = context.offset
      if (offset + 22 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u22"1"
      for (i <- 0 until 21) {
        if ((v & mask) != u22"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u22"1"
      }
      if ((v & mask) != u22"0") {
        output(offset + 21) = T
      } else {
        output(offset + 21) = F
      }
      context.offset = offset + 22
    }

    def beU23(output: MSZ[B], context: Context, v: U23): Unit = {
      val offset = context.offset
      if (offset + 23 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u23"1"
      for (i <- 0 until 22) {
        if ((v & mask) != u23"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u23"1"
      }
      if ((v & mask) != u23"0") {
        output(offset + 22) = T
      } else {
        output(offset + 22) = F
      }
      context.offset = offset + 23
    }

    def beU24(output: MSZ[B], context: Context, v: U24): Unit = {
      val offset = context.offset
      if (offset + 24 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u24"1"
      for (i <- 0 until 23) {
        if ((v & mask) != u24"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u24"1"
      }
      if ((v & mask) != u24"0") {
        output(offset + 23) = T
      } else {
        output(offset + 23) = F
      }
      context.offset = offset + 24
    }

    def beU25(output: MSZ[B], context: Context, v: U25): Unit = {
      val offset = context.offset
      if (offset + 25 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u25"1"
      for (i <- 0 until 24) {
        if ((v & mask) != u25"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u25"1"
      }
      if ((v & mask) != u25"0") {
        output(offset + 24) = T
      } else {
        output(offset + 24) = F
      }
      context.offset = offset + 25
    }

    def beU26(output: MSZ[B], context: Context, v: U26): Unit = {
      val offset = context.offset
      if (offset + 26 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u26"1"
      for (i <- 0 until 25) {
        if ((v & mask) != u26"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u26"1"
      }
      if ((v & mask) != u26"0") {
        output(offset + 25) = T
      } else {
        output(offset + 25) = F
      }
      context.offset = offset + 26
    }

    def beU27(output: MSZ[B], context: Context, v: U27): Unit = {
      val offset = context.offset
      if (offset + 27 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u27"1"
      for (i <- 0 until 26) {
        if ((v & mask) != u27"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u27"1"
      }
      if ((v & mask) != u27"0") {
        output(offset + 26) = T
      } else {
        output(offset + 26) = F
      }
      context.offset = offset + 27
    }

    def beU28(output: MSZ[B], context: Context, v: U28): Unit = {
      val offset = context.offset
      if (offset + 28 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u28"1"
      for (i <- 0 until 27) {
        if ((v & mask) != u28"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u28"1"
      }
      if ((v & mask) != u28"0") {
        output(offset + 27) = T
      } else {
        output(offset + 27) = F
      }
      context.offset = offset + 28
    }

    def beU29(output: MSZ[B], context: Context, v: U29): Unit = {
      val offset = context.offset
      if (offset + 29 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u29"1"
      for (i <- 0 until 28) {
        if ((v & mask) != u29"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u29"1"
      }
      if ((v & mask) != u29"0") {
        output(offset + 28) = T
      } else {
        output(offset + 28) = F
      }
      context.offset = offset + 29
    }

    def beU30(output: MSZ[B], context: Context, v: U30): Unit = {
      val offset = context.offset
      if (offset + 30 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u30"1"
      for (i <- 0 until 29) {
        if ((v & mask) != u30"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u30"1"
      }
      if ((v & mask) != u30"0") {
        output(offset + 29) = T
      } else {
        output(offset + 29) = F
      }
      context.offset = offset + 30
    }

    def beU31(output: MSZ[B], context: Context, v: U31): Unit = {
      val offset = context.offset
      if (offset + 31 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u31"1"
      for (i <- 0 until 30) {
        if ((v & mask) != u31"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u31"1"
      }
      if ((v & mask) != u31"0") {
        output(offset + 30) = T
      } else {
        output(offset + 30) = F
      }
      context.offset = offset + 31
    }

    def beU32(output: MSZ[B], context: Context, v: U32): Unit = {
      val offset = context.offset
      if (offset + 32 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u32"1"
      for (i <- 0 until 31) {
        if ((v & mask) != u32"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u32"1"
      }
      if ((v & mask) != u32"0") {
        output(offset + 31) = T
      } else {
        output(offset + 31) = F
      }
      context.offset = offset + 32
    }

    def beS32(output: MSZ[B], context: Context, v: S32): Unit = {
      beU32(output, context, conversions.S32.toU32(v))
    }

    def beU33(output: MSZ[B], context: Context, v: U33): Unit = {
      val offset = context.offset
      if (offset + 33 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u33"1"
      for (i <- 0 until 32) {
        if ((v & mask) != u33"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u33"1"
      }
      if ((v & mask) != u33"0") {
        output(offset + 32) = T
      } else {
        output(offset + 32) = F
      }
      context.offset = offset + 33
    }

    def beU34(output: MSZ[B], context: Context, v: U34): Unit = {
      val offset = context.offset
      if (offset + 34 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u34"1"
      for (i <- 0 until 33) {
        if ((v & mask) != u34"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u34"1"
      }
      if ((v & mask) != u34"0") {
        output(offset + 33) = T
      } else {
        output(offset + 33) = F
      }
      context.offset = offset + 34
    }

    def beU35(output: MSZ[B], context: Context, v: U35): Unit = {
      val offset = context.offset
      if (offset + 35 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u35"1"
      for (i <- 0 until 34) {
        if ((v & mask) != u35"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u35"1"
      }
      if ((v & mask) != u35"0") {
        output(offset + 34) = T
      } else {
        output(offset + 34) = F
      }
      context.offset = offset + 35
    }

    def beU36(output: MSZ[B], context: Context, v: U36): Unit = {
      val offset = context.offset
      if (offset + 36 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u36"1"
      for (i <- 0 until 35) {
        if ((v & mask) != u36"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u36"1"
      }
      if ((v & mask) != u36"0") {
        output(offset + 35) = T
      } else {
        output(offset + 35) = F
      }
      context.offset = offset + 36
    }

    def beU37(output: MSZ[B], context: Context, v: U37): Unit = {
      val offset = context.offset
      if (offset + 37 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u37"1"
      for (i <- 0 until 36) {
        if ((v & mask) != u37"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u37"1"
      }
      if ((v & mask) != u37"0") {
        output(offset + 36) = T
      } else {
        output(offset + 36) = F
      }
      context.offset = offset + 37
    }

    def beU38(output: MSZ[B], context: Context, v: U38): Unit = {
      val offset = context.offset
      if (offset + 38 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u38"1"
      for (i <- 0 until 37) {
        if ((v & mask) != u38"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u38"1"
      }
      if ((v & mask) != u38"0") {
        output(offset + 37) = T
      } else {
        output(offset + 37) = F
      }
      context.offset = offset + 38
    }

    def beU39(output: MSZ[B], context: Context, v: U39): Unit = {
      val offset = context.offset
      if (offset + 39 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u39"1"
      for (i <- 0 until 38) {
        if ((v & mask) != u39"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u39"1"
      }
      if ((v & mask) != u39"0") {
        output(offset + 38) = T
      } else {
        output(offset + 38) = F
      }
      context.offset = offset + 39
    }

    def beU40(output: MSZ[B], context: Context, v: U40): Unit = {
      val offset = context.offset
      if (offset + 40 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u40"1"
      for (i <- 0 until 39) {
        if ((v & mask) != u40"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u40"1"
      }
      if ((v & mask) != u40"0") {
        output(offset + 39) = T
      } else {
        output(offset + 39) = F
      }
      context.offset = offset + 40
    }

    def beU41(output: MSZ[B], context: Context, v: U41): Unit = {
      val offset = context.offset
      if (offset + 41 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u41"1"
      for (i <- 0 until 40) {
        if ((v & mask) != u41"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u41"1"
      }
      if ((v & mask) != u41"0") {
        output(offset + 40) = T
      } else {
        output(offset + 40) = F
      }
      context.offset = offset + 41
    }

    def beU42(output: MSZ[B], context: Context, v: U42): Unit = {
      val offset = context.offset
      if (offset + 42 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u42"1"
      for (i <- 0 until 41) {
        if ((v & mask) != u42"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u42"1"
      }
      if ((v & mask) != u42"0") {
        output(offset + 41) = T
      } else {
        output(offset + 41) = F
      }
      context.offset = offset + 42
    }

    def beU43(output: MSZ[B], context: Context, v: U43): Unit = {
      val offset = context.offset
      if (offset + 43 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u43"1"
      for (i <- 0 until 42) {
        if ((v & mask) != u43"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u43"1"
      }
      if ((v & mask) != u43"0") {
        output(offset + 42) = T
      } else {
        output(offset + 42) = F
      }
      context.offset = offset + 43
    }

    def beU44(output: MSZ[B], context: Context, v: U44): Unit = {
      val offset = context.offset
      if (offset + 44 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u44"1"
      for (i <- 0 until 43) {
        if ((v & mask) != u44"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u44"1"
      }
      if ((v & mask) != u44"0") {
        output(offset + 43) = T
      } else {
        output(offset + 43) = F
      }
      context.offset = offset + 44
    }

    def beU45(output: MSZ[B], context: Context, v: U45): Unit = {
      val offset = context.offset
      if (offset + 45 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u45"1"
      for (i <- 0 until 44) {
        if ((v & mask) != u45"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u45"1"
      }
      if ((v & mask) != u45"0") {
        output(offset + 44) = T
      } else {
        output(offset + 44) = F
      }
      context.offset = offset + 45
    }

    def beU46(output: MSZ[B], context: Context, v: U46): Unit = {
      val offset = context.offset
      if (offset + 46 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u46"1"
      for (i <- 0 until 45) {
        if ((v & mask) != u46"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u46"1"
      }
      if ((v & mask) != u46"0") {
        output(offset + 45) = T
      } else {
        output(offset + 45) = F
      }
      context.offset = offset + 46
    }

    def beU47(output: MSZ[B], context: Context, v: U47): Unit = {
      val offset = context.offset
      if (offset + 47 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u47"1"
      for (i <- 0 until 46) {
        if ((v & mask) != u47"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u47"1"
      }
      if ((v & mask) != u47"0") {
        output(offset + 46) = T
      } else {
        output(offset + 46) = F
      }
      context.offset = offset + 47
    }

    def beU48(output: MSZ[B], context: Context, v: U48): Unit = {
      val offset = context.offset
      if (offset + 48 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u48"1"
      for (i <- 0 until 47) {
        if ((v & mask) != u48"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u48"1"
      }
      if ((v & mask) != u48"0") {
        output(offset + 47) = T
      } else {
        output(offset + 47) = F
      }
      context.offset = offset + 48
    }

    def beU49(output: MSZ[B], context: Context, v: U49): Unit = {
      val offset = context.offset
      if (offset + 49 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u49"1"
      for (i <- 0 until 48) {
        if ((v & mask) != u49"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u49"1"
      }
      if ((v & mask) != u49"0") {
        output(offset + 48) = T
      } else {
        output(offset + 48) = F
      }
      context.offset = offset + 49
    }

    def beU50(output: MSZ[B], context: Context, v: U50): Unit = {
      val offset = context.offset
      if (offset + 50 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u50"1"
      for (i <- 0 until 49) {
        if ((v & mask) != u50"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u50"1"
      }
      if ((v & mask) != u50"0") {
        output(offset + 49) = T
      } else {
        output(offset + 49) = F
      }
      context.offset = offset + 50
    }

    def beU51(output: MSZ[B], context: Context, v: U51): Unit = {
      val offset = context.offset
      if (offset + 51 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u51"1"
      for (i <- 0 until 50) {
        if ((v & mask) != u51"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u51"1"
      }
      if ((v & mask) != u51"0") {
        output(offset + 50) = T
      } else {
        output(offset + 50) = F
      }
      context.offset = offset + 51
    }

    def beU52(output: MSZ[B], context: Context, v: U52): Unit = {
      val offset = context.offset
      if (offset + 52 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u52"1"
      for (i <- 0 until 51) {
        if ((v & mask) != u52"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u52"1"
      }
      if ((v & mask) != u52"0") {
        output(offset + 51) = T
      } else {
        output(offset + 51) = F
      }
      context.offset = offset + 52
    }

    def beU53(output: MSZ[B], context: Context, v: U53): Unit = {
      val offset = context.offset
      if (offset + 53 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u53"1"
      for (i <- 0 until 52) {
        if ((v & mask) != u53"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u53"1"
      }
      if ((v & mask) != u53"0") {
        output(offset + 52) = T
      } else {
        output(offset + 52) = F
      }
      context.offset = offset + 53
    }

    def beU54(output: MSZ[B], context: Context, v: U54): Unit = {
      val offset = context.offset
      if (offset + 54 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u54"1"
      for (i <- 0 until 53) {
        if ((v & mask) != u54"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u54"1"
      }
      if ((v & mask) != u54"0") {
        output(offset + 53) = T
      } else {
        output(offset + 53) = F
      }
      context.offset = offset + 54
    }

    def beU55(output: MSZ[B], context: Context, v: U55): Unit = {
      val offset = context.offset
      if (offset + 55 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u55"1"
      for (i <- 0 until 54) {
        if ((v & mask) != u55"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u55"1"
      }
      if ((v & mask) != u55"0") {
        output(offset + 54) = T
      } else {
        output(offset + 54) = F
      }
      context.offset = offset + 55
    }

    def beU56(output: MSZ[B], context: Context, v: U56): Unit = {
      val offset = context.offset
      if (offset + 56 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u56"1"
      for (i <- 0 until 55) {
        if ((v & mask) != u56"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u56"1"
      }
      if ((v & mask) != u56"0") {
        output(offset + 55) = T
      } else {
        output(offset + 55) = F
      }
      context.offset = offset + 56
    }

    def beU57(output: MSZ[B], context: Context, v: U57): Unit = {
      val offset = context.offset
      if (offset + 57 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u57"1"
      for (i <- 0 until 56) {
        if ((v & mask) != u57"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u57"1"
      }
      if ((v & mask) != u57"0") {
        output(offset + 56) = T
      } else {
        output(offset + 56) = F
      }
      context.offset = offset + 57
    }

    def beU58(output: MSZ[B], context: Context, v: U58): Unit = {
      val offset = context.offset
      if (offset + 58 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u58"1"
      for (i <- 0 until 57) {
        if ((v & mask) != u58"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u58"1"
      }
      if ((v & mask) != u58"0") {
        output(offset + 57) = T
      } else {
        output(offset + 57) = F
      }
      context.offset = offset + 58
    }

    def beU59(output: MSZ[B], context: Context, v: U59): Unit = {
      val offset = context.offset
      if (offset + 59 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u59"1"
      for (i <- 0 until 58) {
        if ((v & mask) != u59"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u59"1"
      }
      if ((v & mask) != u59"0") {
        output(offset + 58) = T
      } else {
        output(offset + 58) = F
      }
      context.offset = offset + 59
    }

    def beU60(output: MSZ[B], context: Context, v: U60): Unit = {
      val offset = context.offset
      if (offset + 60 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u60"1"
      for (i <- 0 until 59) {
        if ((v & mask) != u60"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u60"1"
      }
      if ((v & mask) != u60"0") {
        output(offset + 59) = T
      } else {
        output(offset + 59) = F
      }
      context.offset = offset + 60
    }

    def beU61(output: MSZ[B], context: Context, v: U61): Unit = {
      val offset = context.offset
      if (offset + 61 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u61"1"
      for (i <- 0 until 60) {
        if ((v & mask) != u61"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u61"1"
      }
      if ((v & mask) != u61"0") {
        output(offset + 60) = T
      } else {
        output(offset + 60) = F
      }
      context.offset = offset + 61
    }

    def beU62(output: MSZ[B], context: Context, v: U62): Unit = {
      val offset = context.offset
      if (offset + 62 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u62"1"
      for (i <- 0 until 61) {
        if ((v & mask) != u62"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u62"1"
      }
      if ((v & mask) != u62"0") {
        output(offset + 61) = T
      } else {
        output(offset + 61) = F
      }
      context.offset = offset + 62
    }

    def beU63(output: MSZ[B], context: Context, v: U63): Unit = {
      val offset = context.offset
      if (offset + 63 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u63"1"
      for (i <- 0 until 62) {
        if ((v & mask) != u63"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u63"1"
      }
      if ((v & mask) != u63"0") {
        output(offset + 62) = T
      } else {
        output(offset + 62) = F
      }
      context.offset = offset + 63
    }

    def beU64(output: MSZ[B], context: Context, v: U64): Unit = {
      val offset = context.offset
      if (offset + 64 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u64"1"
      for (i <- 0 until 63) {
        if ((v & mask) != u64"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask << u64"1"
      }
      if ((v & mask) != u64"0") {
        output(offset + 63) = T
      } else {
        output(offset + 63) = F
      }
      context.offset = offset + 64
    }

    def beS64(output: MSZ[B], context: Context, v: S64): Unit = {
      beU64(output, context, conversions.S64.toU64(v))
    }

    // Slang script gen:
    // for (i <- 2 to 64) {
    //   val sizeM1 = i - 1
    //   println(
    //     st"""def leU$i(output: MSZ[B], context: Context, v: U$i): Unit = {
    //         |  val offset = context.offset
    //         |  if (offset + $i > output.size) {
    //         |    context.signalError(INSUFFICIENT_BUFFER_SIZE)
    //         |  }
    //         |  if (context.hasError) {
    //         |    return
    //         |  }
    //         |  var mask = u$i"1" << u$i"$sizeM1"
    //         |  for (i <- 0 until $sizeM1) {
    //         |    if ((v & mask) != u$i"0") {
    //         |      output(offset + i) = T
    //         |    } else {
    //         |      output(offset + i) = F
    //         |    }
    //         |    mask = mask >>> u$i"1"
    //         |  }
    //         |  if ((v & mask) != u$i"0") {
    //         |    output(offset + $sizeM1) = T
    //         |  } else {
    //         |    output(offset + $sizeM1) = F
    //         |  }
    //         |  context.offset = offset + $i
    //         |}""".render)
    //   println()
    // }

    def leU2(output: MSZ[B], context: Context, v: U2): Unit = {
      val offset = context.offset
      if (offset + 2 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u2"1" << u2"1"
      for (i <- 0 until 1) {
        if ((v & mask) != u2"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u2"1"
      }
      if ((v & mask) != u2"0") {
        output(offset + 1) = T
      } else {
        output(offset + 1) = F
      }
      context.offset = offset + 2
    }

    def leU3(output: MSZ[B], context: Context, v: U3): Unit = {
      val offset = context.offset
      if (offset + 3 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u3"1" << u3"2"
      for (i <- 0 until 2) {
        if ((v & mask) != u3"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u3"1"
      }
      if ((v & mask) != u3"0") {
        output(offset + 2) = T
      } else {
        output(offset + 2) = F
      }
      context.offset = offset + 3
    }

    def leU4(output: MSZ[B], context: Context, v: U4): Unit = {
      val offset = context.offset
      if (offset + 4 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u4"1" << u4"3"
      for (i <- 0 until 3) {
        if ((v & mask) != u4"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u4"1"
      }
      if ((v & mask) != u4"0") {
        output(offset + 3) = T
      } else {
        output(offset + 3) = F
      }
      context.offset = offset + 4
    }

    def leU5(output: MSZ[B], context: Context, v: U5): Unit = {
      val offset = context.offset
      if (offset + 5 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u5"1" << u5"4"
      for (i <- 0 until 4) {
        if ((v & mask) != u5"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u5"1"
      }
      if ((v & mask) != u5"0") {
        output(offset + 4) = T
      } else {
        output(offset + 4) = F
      }
      context.offset = offset + 5
    }

    def leU6(output: MSZ[B], context: Context, v: U6): Unit = {
      val offset = context.offset
      if (offset + 6 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u6"1" << u6"5"
      for (i <- 0 until 5) {
        if ((v & mask) != u6"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u6"1"
      }
      if ((v & mask) != u6"0") {
        output(offset + 5) = T
      } else {
        output(offset + 5) = F
      }
      context.offset = offset + 6
    }

    def leU7(output: MSZ[B], context: Context, v: U7): Unit = {
      val offset = context.offset
      if (offset + 7 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u7"1" << u7"6"
      for (i <- 0 until 6) {
        if ((v & mask) != u7"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u7"1"
      }
      if ((v & mask) != u7"0") {
        output(offset + 6) = T
      } else {
        output(offset + 6) = F
      }
      context.offset = offset + 7
    }

    def leU8(output: MSZ[B], context: Context, v: U8): Unit = {
      val offset = context.offset
      if (offset + 8 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u8"1" << u8"7"
      for (i <- 0 until 7) {
        if ((v & mask) != u8"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u8"1"
      }
      if ((v & mask) != u8"0") {
        output(offset + 7) = T
      } else {
        output(offset + 7) = F
      }
      context.offset = offset + 8
    }

    def leS8(output: MSZ[B], context: Context, v: S8): Unit = {
      leU8(output, context, conversions.S8.toU8(v))
    }

    def leU9(output: MSZ[B], context: Context, v: U9): Unit = {
      val offset = context.offset
      if (offset + 9 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u9"1" << u9"8"
      for (i <- 0 until 8) {
        if ((v & mask) != u9"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u9"1"
      }
      if ((v & mask) != u9"0") {
        output(offset + 8) = T
      } else {
        output(offset + 8) = F
      }
      context.offset = offset + 9
    }

    def leU10(output: MSZ[B], context: Context, v: U10): Unit = {
      val offset = context.offset
      if (offset + 10 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u10"1" << u10"9"
      for (i <- 0 until 9) {
        if ((v & mask) != u10"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u10"1"
      }
      if ((v & mask) != u10"0") {
        output(offset + 9) = T
      } else {
        output(offset + 9) = F
      }
      context.offset = offset + 10
    }

    def leU11(output: MSZ[B], context: Context, v: U11): Unit = {
      val offset = context.offset
      if (offset + 11 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u11"1" << u11"10"
      for (i <- 0 until 10) {
        if ((v & mask) != u11"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u11"1"
      }
      if ((v & mask) != u11"0") {
        output(offset + 10) = T
      } else {
        output(offset + 10) = F
      }
      context.offset = offset + 11
    }

    def leU12(output: MSZ[B], context: Context, v: U12): Unit = {
      val offset = context.offset
      if (offset + 12 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u12"1" << u12"11"
      for (i <- 0 until 11) {
        if ((v & mask) != u12"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u12"1"
      }
      if ((v & mask) != u12"0") {
        output(offset + 11) = T
      } else {
        output(offset + 11) = F
      }
      context.offset = offset + 12
    }

    def leU13(output: MSZ[B], context: Context, v: U13): Unit = {
      val offset = context.offset
      if (offset + 13 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u13"1" << u13"12"
      for (i <- 0 until 12) {
        if ((v & mask) != u13"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u13"1"
      }
      if ((v & mask) != u13"0") {
        output(offset + 12) = T
      } else {
        output(offset + 12) = F
      }
      context.offset = offset + 13
    }

    def leU14(output: MSZ[B], context: Context, v: U14): Unit = {
      val offset = context.offset
      if (offset + 14 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u14"1" << u14"13"
      for (i <- 0 until 13) {
        if ((v & mask) != u14"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u14"1"
      }
      if ((v & mask) != u14"0") {
        output(offset + 13) = T
      } else {
        output(offset + 13) = F
      }
      context.offset = offset + 14
    }

    def leU15(output: MSZ[B], context: Context, v: U15): Unit = {
      val offset = context.offset
      if (offset + 15 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u15"1" << u15"14"
      for (i <- 0 until 14) {
        if ((v & mask) != u15"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u15"1"
      }
      if ((v & mask) != u15"0") {
        output(offset + 14) = T
      } else {
        output(offset + 14) = F
      }
      context.offset = offset + 15
    }

    def leU16(output: MSZ[B], context: Context, v: U16): Unit = {
      val offset = context.offset
      if (offset + 16 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u16"1" << u16"15"
      for (i <- 0 until 15) {
        if ((v & mask) != u16"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u16"1"
      }
      if ((v & mask) != u16"0") {
        output(offset + 15) = T
      } else {
        output(offset + 15) = F
      }
      context.offset = offset + 16
    }

    def leS16(output: MSZ[B], context: Context, v: S16): Unit = {
      leU16(output, context, conversions.S16.toU16(v))
    }

    def leU17(output: MSZ[B], context: Context, v: U17): Unit = {
      val offset = context.offset
      if (offset + 17 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u17"1" << u17"16"
      for (i <- 0 until 16) {
        if ((v & mask) != u17"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u17"1"
      }
      if ((v & mask) != u17"0") {
        output(offset + 16) = T
      } else {
        output(offset + 16) = F
      }
      context.offset = offset + 17
    }

    def leU18(output: MSZ[B], context: Context, v: U18): Unit = {
      val offset = context.offset
      if (offset + 18 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u18"1" << u18"17"
      for (i <- 0 until 17) {
        if ((v & mask) != u18"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u18"1"
      }
      if ((v & mask) != u18"0") {
        output(offset + 17) = T
      } else {
        output(offset + 17) = F
      }
      context.offset = offset + 18
    }

    def leU19(output: MSZ[B], context: Context, v: U19): Unit = {
      val offset = context.offset
      if (offset + 19 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u19"1" << u19"18"
      for (i <- 0 until 18) {
        if ((v & mask) != u19"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u19"1"
      }
      if ((v & mask) != u19"0") {
        output(offset + 18) = T
      } else {
        output(offset + 18) = F
      }
      context.offset = offset + 19
    }

    def leU20(output: MSZ[B], context: Context, v: U20): Unit = {
      val offset = context.offset
      if (offset + 20 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u20"1" << u20"19"
      for (i <- 0 until 19) {
        if ((v & mask) != u20"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u20"1"
      }
      if ((v & mask) != u20"0") {
        output(offset + 19) = T
      } else {
        output(offset + 19) = F
      }
      context.offset = offset + 20
    }

    def leU21(output: MSZ[B], context: Context, v: U21): Unit = {
      val offset = context.offset
      if (offset + 21 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u21"1" << u21"20"
      for (i <- 0 until 20) {
        if ((v & mask) != u21"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u21"1"
      }
      if ((v & mask) != u21"0") {
        output(offset + 20) = T
      } else {
        output(offset + 20) = F
      }
      context.offset = offset + 21
    }

    def leU22(output: MSZ[B], context: Context, v: U22): Unit = {
      val offset = context.offset
      if (offset + 22 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u22"1" << u22"21"
      for (i <- 0 until 21) {
        if ((v & mask) != u22"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u22"1"
      }
      if ((v & mask) != u22"0") {
        output(offset + 21) = T
      } else {
        output(offset + 21) = F
      }
      context.offset = offset + 22
    }

    def leU23(output: MSZ[B], context: Context, v: U23): Unit = {
      val offset = context.offset
      if (offset + 23 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u23"1" << u23"22"
      for (i <- 0 until 22) {
        if ((v & mask) != u23"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u23"1"
      }
      if ((v & mask) != u23"0") {
        output(offset + 22) = T
      } else {
        output(offset + 22) = F
      }
      context.offset = offset + 23
    }

    def leU24(output: MSZ[B], context: Context, v: U24): Unit = {
      val offset = context.offset
      if (offset + 24 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u24"1" << u24"23"
      for (i <- 0 until 23) {
        if ((v & mask) != u24"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u24"1"
      }
      if ((v & mask) != u24"0") {
        output(offset + 23) = T
      } else {
        output(offset + 23) = F
      }
      context.offset = offset + 24
    }

    def leU25(output: MSZ[B], context: Context, v: U25): Unit = {
      val offset = context.offset
      if (offset + 25 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u25"1" << u25"24"
      for (i <- 0 until 24) {
        if ((v & mask) != u25"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u25"1"
      }
      if ((v & mask) != u25"0") {
        output(offset + 24) = T
      } else {
        output(offset + 24) = F
      }
      context.offset = offset + 25
    }

    def leU26(output: MSZ[B], context: Context, v: U26): Unit = {
      val offset = context.offset
      if (offset + 26 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u26"1" << u26"25"
      for (i <- 0 until 25) {
        if ((v & mask) != u26"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u26"1"
      }
      if ((v & mask) != u26"0") {
        output(offset + 25) = T
      } else {
        output(offset + 25) = F
      }
      context.offset = offset + 26
    }

    def leU27(output: MSZ[B], context: Context, v: U27): Unit = {
      val offset = context.offset
      if (offset + 27 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u27"1" << u27"26"
      for (i <- 0 until 26) {
        if ((v & mask) != u27"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u27"1"
      }
      if ((v & mask) != u27"0") {
        output(offset + 26) = T
      } else {
        output(offset + 26) = F
      }
      context.offset = offset + 27
    }

    def leU28(output: MSZ[B], context: Context, v: U28): Unit = {
      val offset = context.offset
      if (offset + 28 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u28"1" << u28"27"
      for (i <- 0 until 27) {
        if ((v & mask) != u28"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u28"1"
      }
      if ((v & mask) != u28"0") {
        output(offset + 27) = T
      } else {
        output(offset + 27) = F
      }
      context.offset = offset + 28
    }

    def leU29(output: MSZ[B], context: Context, v: U29): Unit = {
      val offset = context.offset
      if (offset + 29 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u29"1" << u29"28"
      for (i <- 0 until 28) {
        if ((v & mask) != u29"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u29"1"
      }
      if ((v & mask) != u29"0") {
        output(offset + 28) = T
      } else {
        output(offset + 28) = F
      }
      context.offset = offset + 29
    }

    def leU30(output: MSZ[B], context: Context, v: U30): Unit = {
      val offset = context.offset
      if (offset + 30 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u30"1" << u30"29"
      for (i <- 0 until 29) {
        if ((v & mask) != u30"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u30"1"
      }
      if ((v & mask) != u30"0") {
        output(offset + 29) = T
      } else {
        output(offset + 29) = F
      }
      context.offset = offset + 30
    }

    def leU31(output: MSZ[B], context: Context, v: U31): Unit = {
      val offset = context.offset
      if (offset + 31 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u31"1" << u31"30"
      for (i <- 0 until 30) {
        if ((v & mask) != u31"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u31"1"
      }
      if ((v & mask) != u31"0") {
        output(offset + 30) = T
      } else {
        output(offset + 30) = F
      }
      context.offset = offset + 31
    }

    def leU32(output: MSZ[B], context: Context, v: U32): Unit = {
      val offset = context.offset
      if (offset + 32 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u32"1" << u32"31"
      for (i <- 0 until 31) {
        if ((v & mask) != u32"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u32"1"
      }
      if ((v & mask) != u32"0") {
        output(offset + 31) = T
      } else {
        output(offset + 31) = F
      }
      context.offset = offset + 32
    }

    def leS32(output: MSZ[B], context: Context, v: S32): Unit = {
      leU32(output, context, conversions.S32.toU32(v))
    }

    def leU33(output: MSZ[B], context: Context, v: U33): Unit = {
      val offset = context.offset
      if (offset + 33 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u33"1" << u33"32"
      for (i <- 0 until 32) {
        if ((v & mask) != u33"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u33"1"
      }
      if ((v & mask) != u33"0") {
        output(offset + 32) = T
      } else {
        output(offset + 32) = F
      }
      context.offset = offset + 33
    }

    def leU34(output: MSZ[B], context: Context, v: U34): Unit = {
      val offset = context.offset
      if (offset + 34 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u34"1" << u34"33"
      for (i <- 0 until 33) {
        if ((v & mask) != u34"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u34"1"
      }
      if ((v & mask) != u34"0") {
        output(offset + 33) = T
      } else {
        output(offset + 33) = F
      }
      context.offset = offset + 34
    }

    def leU35(output: MSZ[B], context: Context, v: U35): Unit = {
      val offset = context.offset
      if (offset + 35 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u35"1" << u35"34"
      for (i <- 0 until 34) {
        if ((v & mask) != u35"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u35"1"
      }
      if ((v & mask) != u35"0") {
        output(offset + 34) = T
      } else {
        output(offset + 34) = F
      }
      context.offset = offset + 35
    }

    def leU36(output: MSZ[B], context: Context, v: U36): Unit = {
      val offset = context.offset
      if (offset + 36 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u36"1" << u36"35"
      for (i <- 0 until 35) {
        if ((v & mask) != u36"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u36"1"
      }
      if ((v & mask) != u36"0") {
        output(offset + 35) = T
      } else {
        output(offset + 35) = F
      }
      context.offset = offset + 36
    }

    def leU37(output: MSZ[B], context: Context, v: U37): Unit = {
      val offset = context.offset
      if (offset + 37 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u37"1" << u37"36"
      for (i <- 0 until 36) {
        if ((v & mask) != u37"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u37"1"
      }
      if ((v & mask) != u37"0") {
        output(offset + 36) = T
      } else {
        output(offset + 36) = F
      }
      context.offset = offset + 37
    }

    def leU38(output: MSZ[B], context: Context, v: U38): Unit = {
      val offset = context.offset
      if (offset + 38 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u38"1" << u38"37"
      for (i <- 0 until 37) {
        if ((v & mask) != u38"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u38"1"
      }
      if ((v & mask) != u38"0") {
        output(offset + 37) = T
      } else {
        output(offset + 37) = F
      }
      context.offset = offset + 38
    }

    def leU39(output: MSZ[B], context: Context, v: U39): Unit = {
      val offset = context.offset
      if (offset + 39 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u39"1" << u39"38"
      for (i <- 0 until 38) {
        if ((v & mask) != u39"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u39"1"
      }
      if ((v & mask) != u39"0") {
        output(offset + 38) = T
      } else {
        output(offset + 38) = F
      }
      context.offset = offset + 39
    }

    def leU40(output: MSZ[B], context: Context, v: U40): Unit = {
      val offset = context.offset
      if (offset + 40 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u40"1" << u40"39"
      for (i <- 0 until 39) {
        if ((v & mask) != u40"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u40"1"
      }
      if ((v & mask) != u40"0") {
        output(offset + 39) = T
      } else {
        output(offset + 39) = F
      }
      context.offset = offset + 40
    }

    def leU41(output: MSZ[B], context: Context, v: U41): Unit = {
      val offset = context.offset
      if (offset + 41 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u41"1" << u41"40"
      for (i <- 0 until 40) {
        if ((v & mask) != u41"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u41"1"
      }
      if ((v & mask) != u41"0") {
        output(offset + 40) = T
      } else {
        output(offset + 40) = F
      }
      context.offset = offset + 41
    }

    def leU42(output: MSZ[B], context: Context, v: U42): Unit = {
      val offset = context.offset
      if (offset + 42 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u42"1" << u42"41"
      for (i <- 0 until 41) {
        if ((v & mask) != u42"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u42"1"
      }
      if ((v & mask) != u42"0") {
        output(offset + 41) = T
      } else {
        output(offset + 41) = F
      }
      context.offset = offset + 42
    }

    def leU43(output: MSZ[B], context: Context, v: U43): Unit = {
      val offset = context.offset
      if (offset + 43 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u43"1" << u43"42"
      for (i <- 0 until 42) {
        if ((v & mask) != u43"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u43"1"
      }
      if ((v & mask) != u43"0") {
        output(offset + 42) = T
      } else {
        output(offset + 42) = F
      }
      context.offset = offset + 43
    }

    def leU44(output: MSZ[B], context: Context, v: U44): Unit = {
      val offset = context.offset
      if (offset + 44 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u44"1" << u44"43"
      for (i <- 0 until 43) {
        if ((v & mask) != u44"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u44"1"
      }
      if ((v & mask) != u44"0") {
        output(offset + 43) = T
      } else {
        output(offset + 43) = F
      }
      context.offset = offset + 44
    }

    def leU45(output: MSZ[B], context: Context, v: U45): Unit = {
      val offset = context.offset
      if (offset + 45 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u45"1" << u45"44"
      for (i <- 0 until 44) {
        if ((v & mask) != u45"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u45"1"
      }
      if ((v & mask) != u45"0") {
        output(offset + 44) = T
      } else {
        output(offset + 44) = F
      }
      context.offset = offset + 45
    }

    def leU46(output: MSZ[B], context: Context, v: U46): Unit = {
      val offset = context.offset
      if (offset + 46 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u46"1" << u46"45"
      for (i <- 0 until 45) {
        if ((v & mask) != u46"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u46"1"
      }
      if ((v & mask) != u46"0") {
        output(offset + 45) = T
      } else {
        output(offset + 45) = F
      }
      context.offset = offset + 46
    }

    def leU47(output: MSZ[B], context: Context, v: U47): Unit = {
      val offset = context.offset
      if (offset + 47 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u47"1" << u47"46"
      for (i <- 0 until 46) {
        if ((v & mask) != u47"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u47"1"
      }
      if ((v & mask) != u47"0") {
        output(offset + 46) = T
      } else {
        output(offset + 46) = F
      }
      context.offset = offset + 47
    }

    def leU48(output: MSZ[B], context: Context, v: U48): Unit = {
      val offset = context.offset
      if (offset + 48 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u48"1" << u48"47"
      for (i <- 0 until 47) {
        if ((v & mask) != u48"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u48"1"
      }
      if ((v & mask) != u48"0") {
        output(offset + 47) = T
      } else {
        output(offset + 47) = F
      }
      context.offset = offset + 48
    }

    def leU49(output: MSZ[B], context: Context, v: U49): Unit = {
      val offset = context.offset
      if (offset + 49 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u49"1" << u49"48"
      for (i <- 0 until 48) {
        if ((v & mask) != u49"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u49"1"
      }
      if ((v & mask) != u49"0") {
        output(offset + 48) = T
      } else {
        output(offset + 48) = F
      }
      context.offset = offset + 49
    }

    def leU50(output: MSZ[B], context: Context, v: U50): Unit = {
      val offset = context.offset
      if (offset + 50 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u50"1" << u50"49"
      for (i <- 0 until 49) {
        if ((v & mask) != u50"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u50"1"
      }
      if ((v & mask) != u50"0") {
        output(offset + 49) = T
      } else {
        output(offset + 49) = F
      }
      context.offset = offset + 50
    }

    def leU51(output: MSZ[B], context: Context, v: U51): Unit = {
      val offset = context.offset
      if (offset + 51 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u51"1" << u51"50"
      for (i <- 0 until 50) {
        if ((v & mask) != u51"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u51"1"
      }
      if ((v & mask) != u51"0") {
        output(offset + 50) = T
      } else {
        output(offset + 50) = F
      }
      context.offset = offset + 51
    }

    def leU52(output: MSZ[B], context: Context, v: U52): Unit = {
      val offset = context.offset
      if (offset + 52 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u52"1" << u52"51"
      for (i <- 0 until 51) {
        if ((v & mask) != u52"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u52"1"
      }
      if ((v & mask) != u52"0") {
        output(offset + 51) = T
      } else {
        output(offset + 51) = F
      }
      context.offset = offset + 52
    }

    def leU53(output: MSZ[B], context: Context, v: U53): Unit = {
      val offset = context.offset
      if (offset + 53 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u53"1" << u53"52"
      for (i <- 0 until 52) {
        if ((v & mask) != u53"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u53"1"
      }
      if ((v & mask) != u53"0") {
        output(offset + 52) = T
      } else {
        output(offset + 52) = F
      }
      context.offset = offset + 53
    }

    def leU54(output: MSZ[B], context: Context, v: U54): Unit = {
      val offset = context.offset
      if (offset + 54 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u54"1" << u54"53"
      for (i <- 0 until 53) {
        if ((v & mask) != u54"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u54"1"
      }
      if ((v & mask) != u54"0") {
        output(offset + 53) = T
      } else {
        output(offset + 53) = F
      }
      context.offset = offset + 54
    }

    def leU55(output: MSZ[B], context: Context, v: U55): Unit = {
      val offset = context.offset
      if (offset + 55 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u55"1" << u55"54"
      for (i <- 0 until 54) {
        if ((v & mask) != u55"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u55"1"
      }
      if ((v & mask) != u55"0") {
        output(offset + 54) = T
      } else {
        output(offset + 54) = F
      }
      context.offset = offset + 55
    }

    def leU56(output: MSZ[B], context: Context, v: U56): Unit = {
      val offset = context.offset
      if (offset + 56 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u56"1" << u56"55"
      for (i <- 0 until 55) {
        if ((v & mask) != u56"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u56"1"
      }
      if ((v & mask) != u56"0") {
        output(offset + 55) = T
      } else {
        output(offset + 55) = F
      }
      context.offset = offset + 56
    }

    def leU57(output: MSZ[B], context: Context, v: U57): Unit = {
      val offset = context.offset
      if (offset + 57 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u57"1" << u57"56"
      for (i <- 0 until 56) {
        if ((v & mask) != u57"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u57"1"
      }
      if ((v & mask) != u57"0") {
        output(offset + 56) = T
      } else {
        output(offset + 56) = F
      }
      context.offset = offset + 57
    }

    def leU58(output: MSZ[B], context: Context, v: U58): Unit = {
      val offset = context.offset
      if (offset + 58 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u58"1" << u58"57"
      for (i <- 0 until 57) {
        if ((v & mask) != u58"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u58"1"
      }
      if ((v & mask) != u58"0") {
        output(offset + 57) = T
      } else {
        output(offset + 57) = F
      }
      context.offset = offset + 58
    }

    def leU59(output: MSZ[B], context: Context, v: U59): Unit = {
      val offset = context.offset
      if (offset + 59 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u59"1" << u59"58"
      for (i <- 0 until 58) {
        if ((v & mask) != u59"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u59"1"
      }
      if ((v & mask) != u59"0") {
        output(offset + 58) = T
      } else {
        output(offset + 58) = F
      }
      context.offset = offset + 59
    }

    def leU60(output: MSZ[B], context: Context, v: U60): Unit = {
      val offset = context.offset
      if (offset + 60 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u60"1" << u60"59"
      for (i <- 0 until 59) {
        if ((v & mask) != u60"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u60"1"
      }
      if ((v & mask) != u60"0") {
        output(offset + 59) = T
      } else {
        output(offset + 59) = F
      }
      context.offset = offset + 60
    }

    def leU61(output: MSZ[B], context: Context, v: U61): Unit = {
      val offset = context.offset
      if (offset + 61 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u61"1" << u61"60"
      for (i <- 0 until 60) {
        if ((v & mask) != u61"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u61"1"
      }
      if ((v & mask) != u61"0") {
        output(offset + 60) = T
      } else {
        output(offset + 60) = F
      }
      context.offset = offset + 61
    }

    def leU62(output: MSZ[B], context: Context, v: U62): Unit = {
      val offset = context.offset
      if (offset + 62 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u62"1" << u62"61"
      for (i <- 0 until 61) {
        if ((v & mask) != u62"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u62"1"
      }
      if ((v & mask) != u62"0") {
        output(offset + 61) = T
      } else {
        output(offset + 61) = F
      }
      context.offset = offset + 62
    }

    def leU63(output: MSZ[B], context: Context, v: U63): Unit = {
      val offset = context.offset
      if (offset + 63 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u63"1" << u63"62"
      for (i <- 0 until 62) {
        if ((v & mask) != u63"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u63"1"
      }
      if ((v & mask) != u63"0") {
        output(offset + 62) = T
      } else {
        output(offset + 62) = F
      }
      context.offset = offset + 63
    }

    def leU64(output: MSZ[B], context: Context, v: U64): Unit = {
      val offset = context.offset
      if (offset + 64 > output.size) {
        context.signalError(INSUFFICIENT_BUFFER_SIZE)
      }
      if (context.hasError) {
        return
      }
      var mask = u64"1" << u64"63"
      for (i <- 0 until 63) {
        if ((v & mask) != u64"0") {
          output(offset + i) = T
        } else {
          output(offset + i) = F
        }
        mask = mask >>> u64"1"
      }
      if ((v & mask) != u64"0") {
        output(offset + 63) = T
      } else {
        output(offset + 63) = F
      }
      context.offset = offset + 64
    }

    def leS64(output: MSZ[B], context: Context, v: S64): Unit = {
      leU64(output, context, conversions.S64.toU64(v))
    }
  }
}
