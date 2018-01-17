// #Sireum
/*
Adapted from: https://github.com/msgpack4z/msgpack4z-native with the following license:

Copyright (c) 2015 msgpack4z-core contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package org.sireum

import U8._
import U16._
import U32._
import U64._
import S8._
import S16._
import S32._
import S64._

object MessagePack {

  @enum object Kind {
    'NIL
    'BOOLEAN
    'INTEGER
    'FLOAT
    'STRING
    'ARRAY
    'MAP
    'BINARY
    'EXTENSION
    'NONE
  }

  object Code {
    val POSFIXINT_MASK: U8 = u8"0x80"
    val FIXMAP_PREFIX: U8 = u8"0x80"
    val FIXARRAY_PREFIX: U8 = u8"0x90"
    val FIXSTR_PREFIX: U8 = u8"0xA0"
    val NIL: U8 = u8"0xC0"
    val FALSE: U8 = u8"0xC2"
    val TRUE: U8 = u8"0xC3"
    val BIN8: U8 = u8"0xC4"
    val BIN16: U8 = u8"0xC5"
    val BIN32: U8 = u8"0xC6"
    val EXT8: U8 = u8"0xC7"
    val EXT16: U8 = u8"0xC8"
    val EXT32: U8 = u8"0xC9"
    val FLOAT32: U8 = u8"0xCA"
    val FLOAT64: U8 = u8"0xCB"
    val UINT8: U8 = u8"0xCC"
    val UINT16: U8 = u8"0xCD"
    val UINT32: U8 = u8"0xCE"
    val UINT64: U8 = u8"0xCF"
    val INT8: U8 = u8"0xD0"
    val INT16: U8 = u8"0xD1"
    val INT32: U8 = u8"0xD2"
    val INT64: U8 = u8"0xD3"
    val FIXEXT1: U8 = u8"0xD4"
    val FIXEXT2: U8 = u8"0xD5"
    val FIXEXT4: U8 = u8"0xD6"
    val FIXEXT8: U8 = u8"0xD7"
    val FIXEXT16: U8 = u8"0xD8"
    val STR8: U8 = u8"0xD9"
    val STR16: U8 = u8"0xDA"
    val STR32: U8 = u8"0xDB"
    val ARRAY16: U8 = u8"0xDC"
    val ARRAY32: U8 = u8"0xDD"
    val MAP16: U8 = u8"0xDE"
    val MAP32: U8 = u8"0xDF"
    val NEGFIXINT_PREFIX: U8 = u8"0xE0"

    val formatTable: IS[U8, Kind.Type] = {
      def getType0(n: U8): Kind.Type = {
        if (isPosFixInt(n) || isNegFixInt(n)) {
          return Kind.INTEGER
        } else if (Code.isFixStr(n)) {
          return Kind.STRING
        } else if (Code.isFixedArray(n)) {
          return Kind.ARRAY
        } else if (Code.isFixedMap(n)) {
          return Kind.MAP
        } else {
          n match {
            case Code.NIL => return Kind.NIL
            case Code.FALSE => return Kind.BOOLEAN
            case Code.TRUE => return Kind.BOOLEAN
            case Code.BIN8 => return Kind.BINARY
            case Code.BIN16 => return Kind.BINARY
            case Code.BIN32 => return Kind.BINARY
            case Code.FLOAT32 => return Kind.FLOAT
            case Code.FLOAT64 => return Kind.FLOAT
            case Code.UINT8 => return Kind.INTEGER
            case Code.UINT16 => return Kind.INTEGER
            case Code.UINT32 => return Kind.INTEGER
            case Code.UINT64 => return Kind.INTEGER
            case Code.INT8 => return Kind.INTEGER
            case Code.INT16 => return Kind.INTEGER
            case Code.INT32 => return Kind.INTEGER
            case Code.INT64 => return Kind.INTEGER
            case Code.STR8 => return Kind.STRING
            case Code.STR16 => return Kind.STRING
            case Code.STR32 => return Kind.STRING
            case Code.ARRAY16 => return Kind.ARRAY
            case Code.ARRAY32 => return Kind.ARRAY
            case Code.MAP16 => return Kind.MAP
            case Code.MAP32 => return Kind.MAP
            case Code.FIXEXT1 => return Kind.EXTENSION
            case Code.FIXEXT2 => return Kind.EXTENSION
            case Code.FIXEXT4 => return Kind.EXTENSION
            case Code.FIXEXT8 => return Kind.EXTENSION
            case Code.FIXEXT16 => return Kind.EXTENSION
            case Code.EXT8 => return Kind.EXTENSION
            case Code.EXT16 => return Kind.EXTENSION
            case Code.EXT32 => return Kind.EXTENSION
            case _ => return Kind.NONE
          }
        }
      }

      var a = IS[U8, Kind.Type]()
      var n = 0
      var i = u8"0"
      while (n <= 256) {
        a = a :+ getType0(i)
        n = n + 1
        i = i + u8"1"
      }
      a
    }

    def getType(n: U8): Kind.Type = {
      return formatTable(n)
    }

    def isFixInt(n: U8): B = {
      val v = n & u8"0xFF"
      return v <= u8"0x7F" || v >= u8"0xE0"
    }

    def isPosFixInt(n: U8): B = {
      return (n & POSFIXINT_MASK) == u8"0"
    }

    def isNegFixInt(n: U8): B = {
      return (n & NEGFIXINT_PREFIX) == NEGFIXINT_PREFIX
    }

    def isFixStr(n: U8): B = {
      return (n & u8"0xE0") == Code.FIXSTR_PREFIX
    }

    def isFixedArray(n: U8): B = {
      return (n & u8"0xF0") == Code.FIXARRAY_PREFIX
    }

    def isFixedMap(n: U8): B = {
      return (n & u8"0xF0") == Code.FIXMAP_PREFIX
    }

    def isFixedRaw(n: U8): B = {
      return (n & u8"0xE0") == Code.FIXSTR_PREFIX
    }

  }

  @record trait Writer {

    def result: ISZ[U8]

    def resultBase64: String = {
      val r = result
      return conversions.String.toBase64(r)
    }

    def writeB(b: B): Unit

    def writeC(c: C): Unit = {
      writeU16(conversions.C.toU16(c))
    }

    def writeZ(n: Z): Unit

    def writeZ8(n: Z8): Unit = {
      writeS8(conversions.Z8.toS8(n))
    }

    def writeZ16(n: Z16): Unit = {
      writeS16(conversions.Z16.toS16(n))
    }

    def writeZ32(n: Z32): Unit = {
      writeS32(conversions.Z32.toS32(n))
    }

    def writeZ64(n: Z64): Unit = {
      writeS64(conversions.Z64.toS64(n))
    }

    def writeN(n: N): Unit = {
      writeZ(conversions.N.toZ(n))
    }

    def writeN8(n: N8): Unit = {
      writeU8(conversions.N8.toU8(n))
    }

    def writeN16(n: N16): Unit = {
      writeU16(conversions.N16.toU16(n))
    }

    def writeN32(n: N32): Unit = {
      writeU32(conversions.N32.toU32(n))
    }

    def writeN64(n: N64): Unit = {
      writeU64(conversions.N64.toU64(n))
    }

    def writeS8(n: S8): Unit

    def writeS16(n: S16): Unit

    def writeS32(n: S32): Unit

    def writeS64(n: S64): Unit

    def writeU8(n: U8): Unit

    def writeU16(n: U16): Unit

    def writeU32(n: U32): Unit

    def writeU64(n: U64): Unit

    def writeR(n: R): Unit = {
      writeString(n.string)
    }

    def writeF32(n: F32): Unit

    def writeF64(n: F64): Unit

    def writeString(s: String): Unit

    def writeArrayHeader(n: Z): Unit

    def writeBinary(array: ISZ[U8]): Unit

    def writeNil(): Unit

    def writeMapHeader(n: Z): Unit

    def writeExtTypeHeader(extType: S8, payloadLen: Z): Unit

    def writePayload(data: ISZ[U8]): Unit
  }

  object Writer {

    @record class Impl(var buf: MSZ[U8], var size: Z) extends Writer {

      def result: ISZ[U8] = {
        val r = MSZ.create(size, u8"0")
        var i = 0
        while (i < size) {
          r(i) = buf(i)
          i = i + 1
        }
        return r.toIS
      }

      def addU8(n: U8): Unit = {
        if (size == buf.size) {
          val newBuf = MSZ.create(size * 3 / 2, u8"0")
          var i = 0
          while (i < size) {
            newBuf(i) = buf(i)
            i = i + 1
          }
          buf = newBuf
        }
        buf(size) = n
        size = size + 1
      }

      def addU16(n: U16): Unit = {
        addU8(conversions.U16.toU8((n >>> u16"8") & u16"0xFF"))
        addU8(conversions.U16.toU8(n & u16"0xFF"))
      }

      def addU32(n: U32): Unit = {
        addU8(conversions.U32.toU8((n >>> u32"24") & u32"0xFF"))
        addU8(conversions.U32.toU8((n >>> u32"16") & u32"0xFF"))
        addU8(conversions.U32.toU8((n >>> u32"8") & u32"0xFF"))
        addU8(conversions.U32.toU8(n & u32"0xFF"))
      }

      def addU64(n: U64): Unit = {
        addU8(conversions.U64.toU8((n >>> u64"56") & u64"0xFF"))
        addU8(conversions.U64.toU8((n >>> u64"48") & u64"0xFF"))
        addU8(conversions.U64.toU8((n >>> u64"40") & u64"0xFF"))
        addU8(conversions.U64.toU8((n >>> u64"32") & u64"0xFF"))
        addU8(conversions.U64.toU8((n >>> u64"24") & u64"0xFF"))
        addU8(conversions.U64.toU8((n >>> u64"16") & u64"0xFF"))
        addU8(conversions.U64.toU8((n >>> u64"8") & u64"0xFF"))
        addU8(conversions.U64.toU8(n & u64"0xFF"))
      }

      def addS8(n: S8): Unit = {
        addU8(conversions.S8.toRawU8(n))
      }

      def addS16(n: S16): Unit = {
        addU8(conversions.S16.toU8((n >>> s16"8") & s16"0xFF"))
        addU8(conversions.S16.toU8(n & s16"0xFF"))
      }

      def addS32(n: S32): Unit = {
        addU8(conversions.S32.toU8((n >>> s32"24") & s32"0xFF"))
        addU8(conversions.S32.toU8((n >>> s32"16") & s32"0xFF"))
        addU8(conversions.S32.toU8((n >>> s32"8") & s32"0xFF"))
        addU8(conversions.S32.toU8(n & s32"0xFF"))
      }

      def addS64(n: S64): Unit = {
        addU8(conversions.S64.toU8((n >>> s64"56") & s64"0xFF"))
        addU8(conversions.S64.toU8((n >>> s64"48") & s64"0xFF"))
        addU8(conversions.S64.toU8((n >>> s64"40") & s64"0xFF"))
        addU8(conversions.S64.toU8((n >>> s64"32") & s64"0xFF"))
        addU8(conversions.S64.toU8((n >>> s64"24") & s64"0xFF"))
        addU8(conversions.S64.toU8((n >>> s64"16") & s64"0xFF"))
        addU8(conversions.S64.toU8((n >>> s64"8") & s64"0xFF"))
        addU8(conversions.S64.toU8(n & s64"0xFF"))
      }

      def writeU8(n: U8): Unit = {
        if (n > u8"127") {
          addU8(Code.UINT8)
          addU8(n)
        } else {
          writeS8(conversions.U8.toS8(n))
        }
      }

      def writeS8(n: S8): Unit = {
        if (n < -(s8"1" << s8"5")) {
          addU8(Code.INT8)
          addS8(n)
        } else {
          addS8(n)
        }
      }

      def writeU16(n: U16): Unit = {
        if (n > u16"32767") {
          addU8(Code.UINT16)
          addU16(n)
        } else {
          writeS16(conversions.U16.toS16(n))
        }
      }

      def writeS16(n: S16): Unit = {
        if (n < -(s16"1" << s16"5")) {
          if (n < -(s16"1" << s16"7")) {
            addU8(Code.INT16)
            addS16(n)
          } else {
            addU8(Code.INT8)
            addS8(conversions.S16.toS8(n))
          }
        } else if (n < (s16"1" << s16"7")) {
          addS8(conversions.S16.toS8(n))
        } else {
          if (n < (s16"1" << s16"8")) {
            addU8(Code.UINT8)
            addU8(conversions.S16.toU8(n))
          }
          else {
            addU8(Code.UINT16)
            addU16(conversions.S16.toU16(n))
          }
        }
      }

      def writeU32(n: U32): Unit = {
        if (n > u32"2147483647") {
          addU8(Code.UINT32)
          addU32(n)
        } else {
          writeS32(conversions.U32.toS32(n))
        }
      }

      def writeS32(n: S32): Unit = {
        if (n < -(s32"1" << s32"5")) {
          if (n < -(s32"1" << s32"15")) {
            addU8(Code.INT32)
            addS32(n)
          } else if (n < -(s32"1" << s32"7")) {
            addU8(Code.INT16)
            addS16(conversions.S32.toS16(n))
          } else {
            addU8(Code.INT8)
            addS8(conversions.S32.toS8(n))
          }
        } else if (n < (s32"1" << s32"7")) {
          addS8(conversions.S32.toS8(n))
        } else {
          if (n < (s32"1" << s32"8")) {
            addU8(Code.UINT8)
            addU8(conversions.S32.toU8(n))
          } else if (n < (s32"1" << s32"16")) {
            addU8(Code.UINT16)
            addU16(conversions.S32.toU16(n))
          } else {
            addU8(Code.UINT32)
            addU32(conversions.S32.toU32(n))
          }
        }
      }

      def writeU64(n: U64): Unit = {
        if (n > u64"9223372036854775807") {
          addU8(Code.UINT64)
          addU64(n)
        } else {
          writeS64(conversions.U64.toS64(n))
        }
      }

      def writeS64(n: S64): Unit = {
        if (n < -(s64"1" << s64"5")) {
          if (n < -(s64"1" << s64"15")) {
            if (n < -(s64"1" << s64"31")) {
              addU8(Code.INT64)
              addS64(n)
            } else {
              addU8(Code.INT32)
              addS32(conversions.S64.toS32(n))
            }
          } else {
            if (n < -(s64"1" << s64"7")) {
              addU8(Code.INT16)
              addS16(conversions.S64.toS16(n))
            } else {
              addU8(Code.INT8)
              addS8(conversions.S64.toS8(n))
            }
          }
        } else if (n < (s64"1" << s64"7")) {
          addS8(conversions.S64.toS8(n))
        } else {
          if (n < (s64"1" << s64"16")) {
            if (n < (s64"1" << s64"8")) {
              addU8(Code.UINT8)
              addU8(conversions.S64.toU8(n))
            } else {
              addU8(Code.UINT16)
              addU16(conversions.S64.toU16(n))
            }
          } else {
            if (n < (s64"1" << s64"32")) {
              addU8(Code.UINT32)
              addU32(conversions.S64.toU32(n))
            } else {
              addU8(Code.UINT64)
              addU64(conversions.S64.toU64(n))
            }
          }
        }
      }

      def writeZ(n: Z): Unit = {
        if (-9223372036854775808l <= n && n <= 9223372036854775807l) {
          writeS64(conversions.Z.toS64(n))
        } else if (0 <= n && n <= z"18446744073709551615") {
          writeU64(conversions.Z.toU64(n))
        } else {
          writeBinary(conversions.Z.toBinary(n))
        }
      }

      def writeF32(n: F32): Unit = {
        addU8(Code.FLOAT32)
        addU32(conversions.F32.toRawU32(n))
      }

      def writeF64(n: F64): Unit = {
        addU8(Code.FLOAT64)
        addU64(conversions.F64.toRawU64(n))
      }

      def writeArrayHeader(n: Z): Unit = {
        l""" requires 0 <= n ∧ n <= z"4294967295" """

        if (n < 16 /* 1 << 4 */ ) {
          addU8(Code.FIXARRAY_PREFIX | conversions.Z.toU8(n))
        } else if (n < 65536 /* 1 << 16 */ ) {
          addU8(Code.ARRAY16)
          addS16(conversions.Z.toS16(n))
        } else {
          addU8(Code.ARRAY32)
          addS32(conversions.Z.toS32(n))
        }
      }

      def writeBinary(array: ISZ[U8]): Unit = {
        l""" requires 0 <= array.size ∧ array.size <= z"4294967295" """

        val len = array.size
        if (len < 256 /* 1 << 8 */ ) {
          addU8(Code.BIN8)
          addU8(conversions.Z.toU8(len))
        } else if (len < 65536 /* 1 << 16 */ ) {
          addU8(Code.BIN16)
          addU16(conversions.Z.toU16(len))
        } else {
          addU8(Code.BIN32)
          addU32(conversions.Z.toU32(len))
        }
        for (e <- array) {
          addU8(e)
        }
      }

      def writeNil(): Unit = {
        addU8(Code.NIL)
      }

      def writeMapHeader(n: Z): Unit = {
        l""" requires 0 <= n ∧ n <= z"4294967295" """
        if (n < 16 /* 1 << 4 */ ) {
          addU8(Code.FIXMAP_PREFIX | conversions.Z.toU8(n))
        } else if (n < 65536 /* 1 << 16 */ ) {
          addU8(Code.MAP16)
          addU16(conversions.Z.toU16(n))
        } else {
          addU8(Code.MAP32)
          addU32(conversions.Z.toU32(n))
        }
      }

      def writeB(b: B): Unit = {
        addU8(if (b) Code.TRUE else Code.FALSE)
      }

      def writeString(s: String): Unit = {
        l""" requires 0 <= s.size * 2 ∧ s.size * 2 <= z"4294967295" """

        val len = s.size
        if (len < 32 /* 1 << 5 */ ) {
          addU8(Code.FIXSTR_PREFIX | conversions.Z.toU8(len))
        } else if (len < 256 /* 1 << 8 */ ) {
          addU8(Code.STR8)
          addU8(conversions.Z.toU8(len))
        } else if (len < 65536 /* 1 << 16 */ ) {
          addU8(Code.STR16)
          addU16(conversions.Z.toU16(len))
        } else {
          addU8(Code.STR32)
          addU32(conversions.Z.toU32(len))
        }
        for (e <- conversions.String.toBis(s)) {
          addU8(e)
        }
      }

      def writeExtTypeHeader(extType: S8, payloadLen: Z): Unit = {
        l""" requires extType >= s8"0" ∧ 0 <= payloadLen ∧ payloadLen <= z"4294967295""""

        if (payloadLen < 256 /* 1 << 8 */ ) {
          payloadLen match {
            case z"1" =>
              addU8(u8"0xD4")
              addS8(extType)
            case z"2" =>
              addU8(u8"0xD5")
              addS8(extType)
            case z"4" =>
              addU8(u8"0xD6")
              addS8(extType)
            case z"8" =>
              addU8(u8"0xD7")
              addS8(extType)
            case z"16" =>
              addU8(u8"0xD8")
              addS8(extType)
            case _ =>
              addU8(u8"0xC7")
              addU8(conversions.Z.toU8(payloadLen))
              addS8(extType)
          }
        } else if (payloadLen < 65536 /* 1 << 16 */ ) {
          addU8(u8"0xC8")
          addU16(conversions.Z.toU16(payloadLen))
          addS8(extType)
        } else {
          addU8(u8"0xC9")
          addU32(conversions.Z.toU32(payloadLen))
          addS8(extType)
        }
      }

      def writePayload(data: ISZ[U8]): Unit = {
        for (e <- data) {
          addU8(e)
        }
      }
    }

  }

  @record trait Reader {

    def readB(): B

    def readC(): C = {
      val n = readU16()
      return conversions.U16.toC(n)
    }

    def readZ(): Z

    @pure def fix8(n: Z): Z = {
      if (n > 127) {
        return n - 256
      }
      return n
    }

    def readZ8(): Z8 = {
      val n = readZ()
      return conversions.Z.toZ8(fix8(n))
    }

    def readZ16(): Z16 = {
      val n = readZ()
      return conversions.Z.toZ16(n)
    }

    def readZ32(): Z32 = {
      val n = readZ()
      return conversions.Z.toZ32(n)
    }

    def readZ64(): Z64 = {
      val n = readZ()
      return conversions.Z.toZ64(n)
    }

    def readN(): N = {
      val n = readZ()
      return conversions.Z.toN(n)
    }

    def readN8(): N8 = {
      val n = readZ()
      return conversions.Z.toN8(n)
    }

    def readN16(): N16 = {
      val n = readZ()
      return conversions.Z.toN16(n)
    }

    def readN32(): N32 = {
      val n = readZ()
      return conversions.Z.toN32(n)
    }

    def readN64(): N64 = {
      val n = readZ()
      return conversions.Z.toN64(n)
    }

    def readS8(): S8 = {
      val n = readZ()
      return conversions.Z.toS8(fix8(n))
    }

    def readS16(): S16 = {
      val n = readZ()
      return conversions.Z.toS16(n)
    }

    def readS32(): S32 = {
      val n = readZ()
      return conversions.Z.toS32(n)
    }

    def readS64(): S64 = {
      val n = readZ()
      return conversions.Z.toS64(n)
    }

    def readU8(): U8 = {
      val n = readZ()
      return conversions.Z.toU8(n)
    }

    def readU16(): U16 = {
      val n = readZ()
      return conversions.Z.toU16(n)
    }

    def readU32(): U32 = {
      val n = readZ()
      return conversions.Z.toU32(n)
    }

    def readU64(): U64 = {
      val n = readZ()
      return conversions.Z.toU64(n)
    }

    def readR(): R = {
      val s = readString()
      R(s) match {
        case Some(r) => return r
        case _ => halt(s"Expecting a R, but found $s.")
      }
    }

    def readF32(): F32

    def readF64(): F64

    def readString(): String

    def readArrayHeader(): Z

    def readBinary(): ISZ[U8]

    def isNil: B

    def readMapHeader(): Z

    def readExtTypeHeader(): (S8, Z)

    def readPayload(n: Z): ISZ[U8]

    def skip(n: Z): Unit
  }

  object Reader {

    @record class Impl(buf: ISZ[U8], var curr: Z) extends Reader {

      def peek(): U8 = {
        return buf(curr)
      }

      def read8(): U8 = {
        val r = peek()
        skip(1)
        return r
      }

      def read16(): U16 = {
        val ch1 = read8()
        val ch2 = read8()
        return (conversions.U8.toU16(ch1) << u16"8") +
          conversions.U8.toU16(ch2)
      }

      def read32(): U32 = {
        val ch1 = read8()
        val ch2 = read8()
        val ch3 = read8()
        val ch4 = read8()
        return (conversions.U8.toU32(ch1) << u32"24") +
          (conversions.U8.toU32(ch2) << u32"16") +
          (conversions.U8.toU32(ch3) << u32"8") +
          conversions.U8.toU32(ch4)
      }

      def read64(): U64 = {
        val ch1 = read8()
        val ch2 = read8()
        val ch3 = read8()
        val ch4 = read8()
        val ch5 = read8()
        val ch6 = read8()
        val ch7 = read8()
        val ch8 = read8()
        return (conversions.U8.toU64(ch1) << u64"56") +
          (conversions.U8.toU64(ch2) << u64"48") +
          (conversions.U8.toU64(ch3) << u64"40") +
          (conversions.U8.toU64(ch4) << u64"32") +
          (conversions.U8.toU64(ch5) << u64"24") +
          (conversions.U8.toU64(ch6) << u64"16") +
          (conversions.U8.toU64(ch7) << u64"8") +
          conversions.U8.toU64(ch8)
      }

      def readB(): B = {
        val code = read8()
        code match {
          case Code.TRUE => return T
          case Code.FALSE => return F
          case _ => halt(s"Expecting a B, but found code $code.")
        }
      }

      def readZ(): Z = {
        val code = read8()
        if (Code.isFixInt(code)) {
          return conversions.U8.toZ(code)
        }
        code match {
          case Code.INT8 =>
            val n = read8()
            return conversions.S8.toZ(conversions.U8.toRawS8(n))
          case Code.INT16 =>
            val n = read16()
            return conversions.S16.toZ(conversions.U16.toRawS16(n))
          case Code.INT32 =>
            val n = read32()
            return conversions.S32.toZ(conversions.U32.toRawS32(n))
          case Code.INT64 =>
            val n = read64()
            return conversions.S64.toZ(conversions.U64.toRawS64(n))
          case Code.UINT8 =>
            val n = read8()
            return conversions.U8.toZ(n)
          case Code.UINT16 =>
            val n = read16()
            return conversions.U16.toZ(n)
          case Code.UINT32 =>
            val n = read32()
            return conversions.U32.toZ(n)
          case Code.UINT64 =>
            val n = read64()
            return conversions.U64.toZ(n)
          case _ =>
            if (code == Code.BIN8 || code == Code.BIN16 || code == Code.BIN32) {
              skip(-1)
              val bin = readBinary()
              return conversions.Z.fromBinary(bin)
            } else {
              halt(s"Expecting an integer, but found code $code.")
            }
        }
      }

      def readF32(): F32 = {
        val code = read8()
        code match {
          case Code.FLOAT32 =>
          case _ => halt(s"Expecting a F32, but found code $code.")
        }
        val n = read32()
        return conversions.U32.toRawF32(n)
      }

      def readF64(): F64 = {
        val code = read8()
        code match {
          case Code.FLOAT64 =>
          case _ => halt(s"Expecting a F64, but found code $code.")
        }
        val n = read64()
        return conversions.U64.toRawF64(n)
      }

      def readString(): String = {
        val code = read8()
        val len: Z = {
          var r: Z = 0
          if (Code.isFixStr(code)) {
            r = conversions.U8.toZ(u8"0x1F" & code)
          } else {
            code match {
              case Code.STR8 =>
                val n = read8()
                r = conversions.U8.toZ(n)
              case Code.STR16 =>
                val n = read16()
                r = conversions.U16.toZ(n)
              case Code.STR32 =>
                val n = read32()
                r = conversions.U32.toZ(n)
              case _ => halt(s"Expecting a String, but found $code")
            }
          }
          r
        }
        val a = MSZ.create(len, u8"0")
        var i = 0
        while (i < len) {
          a(i) = read8()
          i = i + 1
        }
        return conversions.String.fromBms(a)
      }

      def readArrayHeader(): Z = {
        val code = read8()
        if (Code.isFixedArray(code)) {
          return conversions.U8.toZ(code & u8"0x0F")
        } else {
          code match {
            case Code.ARRAY16 =>
              val r = read16()
              return conversions.U16.toZ(r)
            case Code.ARRAY32 =>
              val r = read32()
              return conversions.U32.toZ(r)
            case _ =>
              halt(s"Expecting an array, but found code $code")
          }
        }
      }

      def readBinary(): ISZ[U8] = {
        val code = read8()
        val len: Z = {
          var r: Z = 0
          if (Code.isFixedRaw(code)) {
            r = conversions.U8.toZ(u8"0x1F" & code)
          } else {
            code match {
              case Code.BIN8 =>
                val n = read8()
                r = conversions.U8.toZ(n)
              case Code.BIN16 =>
                val n = read16()
                r = conversions.U16.toZ(n)
              case Code.BIN32 =>
                val n = read32()
                r = conversions.U32.toZ(n)
              case _ => halt(s"Expecting a binary, but found $code")
            }
          }
          r
        }
        val a = MSZ.create(len, u8"0")
        var i = 0
        while (i < len) {
          a(i) = read8()
          i = i + 1
        }
        return a.toIS
      }

      def isNil: B = {
        val n = read8()
        return n == Code.NIL
      }

      def readMapHeader(): Z = {
        val code = read8()
        if (Code.isFixedMap(code)) {
          return conversions.U8.toZ(code & u8"0x0F")
        } else {
          code match {
            case Code.MAP16 =>
              val r = read16()
              return conversions.U16.toZ(r)
            case Code.MAP32 =>
              val r = read32()
              return conversions.U32.toZ(r)
            case _ =>
              halt(s"Expecting a map, but found code $code")
          }
        }
      }

      def readExtTypeHeader(): (S8, Z) = {
        val code = read8()
        code match {
          case Code.FIXEXT1 =>
            val extType = readS8()
            return (extType, 1)
          case Code.FIXEXT2 =>
            val extType = readS8()
            return (extType, 2)
          case Code.FIXEXT4 =>
            val extType = readS8()
            return (extType, 4)
          case Code.FIXEXT8 =>
            val extType = readS8()
            return (extType, 8)
          case Code.FIXEXT16 =>
            val extType = readS8()
            return (extType, 16)
          case Code.EXT8 =>
            val n = read8()
            val length = conversions.U8.toZ(n & u8"0xFF")
            val extType = readS8()
            return (extType, length)
          case Code.EXT16 =>
            val n = read16()
            val length = conversions.U16.toZ(n & u16"0xFFFF")
            val extType = readS8()
            return (extType, length)
          case Code.EXT32 =>
            val length = conversions.U32.toZ(read32())
            val extType = readS8()
            return (extType, length)
          case _ =>
            halt(s"Expecting an ext type, but found code $code")
        }
      }

      def readPayload(n: Z): ISZ[U8] = {
        val r = MSZ.create(n, u8"0")
        var i = 0
        while (i < n) {
          r(i) = read8()
          i = i + 1
        }
        return r.toIS
      }

      def skip(n: Z): Unit = {
        l""" requires 0 <= curr + n ∧ curr + n <= buf.size """
        curr = curr + n
      }
    }

  }

  def writer: Writer = {
    return Writer.Impl(MS.create(1024, u8"0"), 0)
  }

  def reader(data: ISZ[U8]): Reader = {
    return Reader.Impl(data, 0)
  }

  def readerBase64(data: String): Reader = {
    return Reader.Impl(conversions.String.fromBase64(data), 0)
  }
}