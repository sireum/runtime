/*
 * Copyright (c) 2017-2024, Robby, Kansas State University
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

package org.sireum.conversions

import org.sireum._
import org.sireum.Z8._
import org.sireum.Z16._
import org.sireum.Z32._
import org.sireum.Z64._
import org.sireum.N._
import org.sireum.N8._
import org.sireum.N16._
import org.sireum.N32._
import org.sireum.N64._
import org.sireum.S8._
import org.sireum.S16._
import org.sireum.S32._
import org.sireum.S64._
import org.sireum.U8._
import org.sireum.U16._
import org.sireum.U32._
import org.sireum.U64._

object B_Ext {

  @pure def toB(b: B): B = b

  @pure def toZ(b: B): Z = if (b) 1 else 0

  @pure def toZ8(b: B): Z8 = if (b) z8"1" else z8"0"

  @pure def toZ16(b: B): Z16 = if (b) z16"1" else z16"0"

  @pure def toZ32(b: B): Z32 = if (b) z32"1" else z32"0"

  @pure def toZ64(b: B): Z64 = if (b) z64"1" else z64"0"

  @pure def toN(b: B): N = if (b) n"1" else n"0"

  @pure def toN8(b: B): N8 = if (b) n8"1" else n8"0"

  @pure def toN16(b: B): N16 = if (b) n16"1" else n16"0"

  @pure def toN32(b: B): N32 = if (b) n32"1" else n32"0"

  @pure def toN64(b: B): N64 = if (b) n64"1" else n64"0"

  @pure def toS8(b: B): S8 = if (b) s8"1" else s8"0"

  @pure def toS16(b: B): S16 = if (b) s16"1" else s16"0"

  @pure def toS32(b: B): S32 = if (b) s32"1" else s32"0"

  @pure def toS64(b: B): S64 = if (b) s64"1" else s64"0"

  @pure def toU8(b: B): U8 = if (b) u8"1" else u8"0"

  @pure def toU16(b: B): U16 = if (b) u16"1" else u16"0"

  @pure def toU32(b: B): U32 = if (b) u32"1" else u32"0"

  @pure def toU64(b: B): U64 = if (b) u64"1" else u64"0"

  @pure def toF32(b: B): F32 = if (b) f32"1.0" else f32"0.0"

  @pure def toF64(b: B): F64 = if (b) f64"1.0" else f64"0.0"

  @pure def toR(b: B): R = if (b) r"1.0" else r"0.0"
}

object C_Ext {
  @pure def toU32(c: C): U32 = org.sireum.U32(c.value)

  @pure def toCodePoints(c: C): ISZ[C] = ISZ(Character.toChars(c.value).map(C(_)).toSeq: _*)
}

object Z_Ext {

  @pure def isInRangeSigned8(n: Z): B = -128 <= n && n <= 127

  @pure def isInRangeSigned16(n: Z): B = -32768 <= n && n <= 32767

  @pure def isInRangeSigned32(n: Z): B = -2147483648 <= n && n <= 2147483647

  @pure def isInRangeSigned64(n: Z): B = -9223372036854775808L <= n && n <= 9223372036854775807L

  @pure def isInRangeN(n: Z): B = 0 <= n

  @pure def isInRangeUnsigned8(n: Z): B = 0 <= n && n <= 255

  @pure def isInRangeUnsigned16(n: Z): B = 0 <= n && n <= 65535

  @pure def isInRangeUnsigned32(n: Z): B = 0 <= n && n <= 4294967295L

  @pure def isInRangeUnsigned64(n: Z): B = 0 <= n && n <= z"18446744073709551615"

  @pure def toB(n: Z): B = n != z"0"

  @pure def toZ(n: Z): Z = n

  @pure def toZ8(n: Z): Z8 = org.sireum.Z8(n)

  @pure def toZ16(n: Z): Z16 = org.sireum.Z16(n)

  @pure def toZ32(n: Z): Z32 = org.sireum.Z32(n)

  @pure def toZ64(n: Z): Z64 = org.sireum.Z64(n)

  @pure def toN(n: Z): N = org.sireum.N(n)

  @pure def toN8(n: Z): N8 = org.sireum.N8(n)

  @pure def toN16(n: Z): N16 = org.sireum.N16(n)

  @pure def toN32(n: Z): N32 = org.sireum.N32(n)

  @pure def toN64(n: Z): N64 = org.sireum.N64(n)

  @pure def toS8(n: Z): S8 = {
    require(isInRangeSigned8(n.toMP))
    org.sireum.S8(n)
  }

  @pure def toS16(n: Z): S16 = {
    require(isInRangeSigned16(n.toMP))
    org.sireum.S16(n)
  }

  @pure def toS32(n: Z): S32 = {
    require(isInRangeSigned32(n.toMP))
    org.sireum.S32(n)
  }

  @pure def toS64(n: Z): S64 = {
    require(isInRangeSigned64(n.toMP))
    org.sireum.S64(n)
  }

  @pure def toU8(n: Z): U8 = {
    require(isInRangeUnsigned8(n.toMP))
    org.sireum.U8(n)
  }

  @pure def toU16(n: Z): U16 = {
    require(isInRangeUnsigned16(n.toMP))
    org.sireum.U16(n)
  }

  @pure def toU32(n: Z): U32 = {
    require(isInRangeUnsigned32(n.toMP))
    org.sireum.U32(n)
  }

  @pure def toU64(n: Z): U64 = {
    require(isInRangeUnsigned64(n))
    org.sireum.U64(n)
  }

  @pure def toR(n: Z): R = org.sireum.R.$String(n.toString)

  @pure def toBinary(n: Z): ISZ[U8] = {
    var r = ISZ[U8]()
    for (e <- n.toBigInt.toByteArray) {
      r = r :+ org.sireum.U8(e)
    }
    r
  }

  @pure def fromBinary(bin: ISZ[U8]): Z = {
    org.sireum.Z(scala.BigInt(bin.elements.toArray.map(_.value)))
  }
}

object Z8_Ext {

  @pure def toB(n: Z8): B = n != z8"0"

  @pure def toZ(n: Z8): Z = n.toMP

  @pure def toZ8(n: Z8): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: Z8): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: Z8): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: Z8): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: Z8): N = org.sireum.N(n.toMP)

  @pure def toN8(n: Z8): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: Z8): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: Z8): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: Z8): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: Z8): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: Z8): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: Z8): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: Z8): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: Z8): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: Z8): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: Z8): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: Z8): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: Z8): R = org.sireum.R.$String(n.toString)
}

object Z16_Ext {

  @pure def toB(n: Z16): B = n != z16"0"

  @pure def toZ(n: Z16): Z = n.toMP

  @pure def toZ8(n: Z16): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: Z16): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: Z16): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: Z16): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: Z16): N = org.sireum.N(n.toMP)

  @pure def toN8(n: Z16): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: Z16): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: Z16): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: Z16): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: Z16): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: Z16): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: Z16): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: Z16): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: Z16): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: Z16): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: Z16): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: Z16): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: Z16): R = org.sireum.R.$String(n.toString)
}

object Z32_Ext {

  @pure def toB(n: Z32): B = n != z32"0"

  @pure def toZ(n: Z32): Z = n.toMP

  @pure def toZ8(n: Z32): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: Z32): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: Z32): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: Z32): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: Z32): N = org.sireum.N(n.toMP)

  @pure def toN8(n: Z32): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: Z32): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: Z32): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: Z32): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: Z32): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: Z32): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: Z32): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: Z32): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: Z32): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: Z32): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: Z32): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: Z32): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: Z32): R = org.sireum.R.$String(n.toString)
}

object Z64_Ext {

  @pure def toB(n: Z64): B = n != z64"0"

  @pure def toZ(n: Z64): Z = n.toMP

  @pure def toZ8(n: Z64): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: Z64): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: Z64): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: Z64): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: Z64): N = org.sireum.N(n.toMP)

  @pure def toN8(n: Z64): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: Z64): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: Z64): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: Z64): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: Z64): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: Z64): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: Z64): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: Z64): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: Z64): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: Z64): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: Z64): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: Z64): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: Z64): R = org.sireum.R.$String(n.toString)
}

object N_Ext {

  @pure def toB(n: N): B = n != n"0"

  @pure def toZ(n: N): Z = n.toMP

  @pure def toZ8(n: N): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: N): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: N): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: N): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: N): N = org.sireum.N(n.toMP)

  @pure def toN8(n: N): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: N): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: N): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: N): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: N): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: N): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: N): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: N): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: N): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: N): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: N): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: N): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: N): R = org.sireum.R.$String(n.toString)
}

object N8_Ext {

  @pure def toB(n: N8): B = n != n8"0"

  @pure def toZ(n: N8): Z = n.toMP

  @pure def toZ8(n: N8): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: N8): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: N8): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: N8): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: N8): N = org.sireum.N(n.toMP)

  @pure def toN8(n: N8): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: N8): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: N8): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: N8): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: N8): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: N8): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: N8): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: N8): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: N8): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: N8): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: N8): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: N8): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: N8): R = org.sireum.R.$String(n.toString)
}

object N16_Ext {

  @pure def toB(n: N16): B = n != n16"0"

  @pure def toZ(n: N16): Z = n.toMP

  @pure def toZ8(n: N16): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: N16): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: N16): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: N16): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: N16): N = org.sireum.N(n.toMP)

  @pure def toN8(n: N16): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: N16): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: N16): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: N16): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: N16): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: N16): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: N16): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: N16): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: N16): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: N16): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: N16): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: N16): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: N16): R = org.sireum.R.$String(n.toString)
}

object N32_Ext {

  @pure def toB(n: N32): B = n != n32"0"

  @pure def toZ(n: N32): Z = n.toMP

  @pure def toZ8(n: N32): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: N32): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: N32): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: N32): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: N32): N = org.sireum.N(n.toMP)

  @pure def toN8(n: N32): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: N32): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: N32): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: N32): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: N32): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: N32): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: N32): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: N32): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: N32): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: N32): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: N32): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: N32): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: N32): R = org.sireum.R.$String(n.toString)
}

object N64_Ext {

  @pure def toB(n: N64): B = n != n64"0"

  @pure def toZ(n: N64): Z = n.toMP

  @pure def toZ8(n: N64): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: N64): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: N64): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: N64): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: N64): N = org.sireum.N(n.toMP)

  @pure def toN8(n: N64): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: N64): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: N64): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: N64): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: N64): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: N64): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: N64): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: N64): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: N64): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: N64): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: N64): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: N64): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: N64): R = org.sireum.R.$String(n.toString)
}

object S8_Ext {

  @pure def toB(n: S8): B = n != s8"0"

  @pure def toZ(n: S8): Z = n.toMP

  @pure def toZ8(n: S8): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: S8): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: S8): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: S8): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: S8): N = org.sireum.N(n.toMP)

  @pure def toN8(n: S8): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: S8): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: S8): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: S8): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: S8): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: S8): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: S8): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: S8): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: S8): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: S8): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: S8): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: S8): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: S8): R = org.sireum.R.$String(n.toString)

  @pure def toRawU8(n: S8): U8 = org.sireum.U8(n.value)
}

object S16_Ext {

  @pure def toB(n: S16): B = n != s16"0"

  @pure def toZ(n: S16): Z = n.toMP

  @pure def toZ8(n: S16): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: S16): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: S16): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: S16): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: S16): N = org.sireum.N(n.toMP)

  @pure def toN8(n: S16): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: S16): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: S16): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: S16): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: S16): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: S16): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: S16): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: S16): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: S16): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: S16): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: S16): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: S16): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: S16): R = org.sireum.R.$String(n.toString)

  @pure def toRawU16(n: S16): U16 = org.sireum.U16(n.value)
}

object S32_Ext {

  @pure def toB(n: S32): B = n != s32"0"

  @pure def toZ(n: S32): Z = n.toMP

  @pure def toZ8(n: S32): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: S32): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: S32): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: S32): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: S32): N = org.sireum.N(n.toMP)

  @pure def toN8(n: S32): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: S32): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: S32): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: S32): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: S32): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: S32): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: S32): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: S32): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: S32): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: S32): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: S32): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: S32): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: S32): R = org.sireum.R.$String(n.toString)

  @pure def toRawU32(n: S32): U32 = org.sireum.U32(n.value)
}

object S64_Ext {

  @pure def toB(n: S64): B = n != s64"0"

  @pure def toZ(n: S64): Z = n.toMP

  @pure def toZ8(n: S64): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: S64): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: S64): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: S64): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: S64): N = org.sireum.N(n.toMP)

  @pure def toN8(n: S64): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: S64): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: S64): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: S64): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: S64): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: S64): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: S64): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: S64): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: S64): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: S64): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: S64): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: S64): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: S64): R = org.sireum.R.$String(n.toString)

  @pure def toRawU64(n: S64): U64 = org.sireum.U64(n.value)
}

object U1_Ext {
  @pure def toU8(n: U1): U8 = org.sireum.U8(n.toMP)
  @pure def toU16(n: U1): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U1): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U1): U64 = org.sireum.U64(n.toMP)
}

object U2_Ext {
  @pure def toU8(n: U2): U8 = org.sireum.U8(n.toMP)
  @pure def toU16(n: U2): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U2): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U2): U64 = org.sireum.U64(n.toMP)
}

object U3_Ext {
  @pure def toU8(n: U3): U8 = org.sireum.U8(n.toMP)
  @pure def toU16(n: U3): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U3): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U3): U64 = org.sireum.U64(n.toMP)
}

object U4_Ext {
  @pure def toU8(n: U4): U8 = org.sireum.U8(n.toMP)
  @pure def toU16(n: U4): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U4): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U4): U64 = org.sireum.U64(n.toMP)
}

object U5_Ext {
  @pure def toU8(n: U5): U8 = org.sireum.U8(n.toMP)
  @pure def toU16(n: U5): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U5): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U5): U64 = org.sireum.U64(n.toMP)
}

object U6_Ext {
  @pure def toU8(n: U6): U8 = org.sireum.U8(n.toMP)
  @pure def toU16(n: U6): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U6): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U6): U64 = org.sireum.U64(n.toMP)
}

object U7_Ext {
  @pure def toU8(n: U7): U8 = org.sireum.U8(n.toMP)
  @pure def toU16(n: U7): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U7): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U7): U64 = org.sireum.U64(n.toMP)
}

object U8_Ext {

  @pure def toB(n: U8): B = n != u8"0"

  @pure def toZ(n: U8): Z = n.toMP

  @pure def toZ8(n: U8): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: U8): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: U8): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: U8): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: U8): N = org.sireum.N(n.toMP)

  @pure def toN8(n: U8): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: U8): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: U8): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: U8): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: U8): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: U8): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: U8): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: U8): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU1(n: U8): U1 = {
    val v = n.toMP
    require(0 <= v && v < 2)
    org.sireum.U1(v)
  }

  @pure def toU2(n: U8): U2 = {
    val v = n.toMP
    require(0 <= v && v < 4)
    org.sireum.U2(v)
  }

  @pure def toU3(n: U8): U3 = {
    val v = n.toMP
    require(0 <= v && v < 8)
    org.sireum.U3(v)
  }

  @pure def toU4(n: U8): U4 = {
    val v = n.toMP
    require(0 <= v && v < 16)
    org.sireum.U4(v)
  }

  @pure def toU5(n: U8): U5 = {
    val v = n.toMP
    require(0 <= v && v < 32)
    org.sireum.U5(v)
  }

  @pure def toU6(n: U8): U6 = {
    val v = n.toMP
    require(0 <= v && v < 64)
    org.sireum.U6(v)
  }

  @pure def toU7(n: U8): U7 = {
    val v = n.toMP
    require(0 <= v && v < 128)
    org.sireum.U7(v)
  }

  @pure def toU8(n: U8): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: U8): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: U8): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: U8): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: U8): R = org.sireum.R.$String(n.toString)

  @pure def toRawS8(n: U8): S8 = org.sireum.S8(n.value)
}

object U9_Ext {
  @pure def toU16(n: U9): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U9): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U9): U64 = org.sireum.U64(n.toMP)
}

object U10_Ext {
  @pure def toU16(n: U10): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U10): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U10): U64 = org.sireum.U64(n.toMP)
}

object U11_Ext {
  @pure def toU16(n: U11): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U11): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U11): U64 = org.sireum.U64(n.toMP)
}

object U12_Ext {
  @pure def toU16(n: U12): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U12): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U12): U64 = org.sireum.U64(n.toMP)
}

object U13_Ext {
  @pure def toU16(n: U13): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U13): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U13): U64 = org.sireum.U64(n.toMP)
}

object U14_Ext {
  @pure def toU16(n: U14): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U14): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U14): U64 = org.sireum.U64(n.toMP)
}

object U15_Ext {
  @pure def toU16(n: U15): U16 = org.sireum.U16(n.toMP)
  @pure def toU32(n: U15): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U15): U64 = org.sireum.U64(n.toMP)
}

object U16_Ext {

  @pure def toB(n: U16): B = n != u16"0"

  @pure def toZ(n: U16): Z = n.toMP

  @pure def toZ8(n: U16): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: U16): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: U16): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: U16): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: U16): N = org.sireum.N(n.toMP)

  @pure def toN8(n: U16): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: U16): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: U16): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: U16): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: U16): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: U16): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: U16): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: U16): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: U16): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU9(n: U16): U9 = {
    val v = n.toMP
    require(0 <= v && v < z"511")
    return org.sireum.U9(v)
  }

  @pure def toU10(n: U16): U10 = {
    val v = n.toMP
    require(0 <= v && v < z"1023")
    return org.sireum.U10(v)
  }

  @pure def toU11(n: U16): U11 = {
    val v = n.toMP
    require(0 <= v && v < z"2047")
    return org.sireum.U11(v)
  }

  @pure def toU12(n: U16): U12 = {
    val v = n.toMP
    require(0 <= v && v < z"4095")
    return org.sireum.U12(v)
  }

  @pure def toU13(n: U16): U13 = {
    val v = n.toMP
    require(0 <= v && v < z"8191")
    return org.sireum.U13(v)
  }

  @pure def toU14(n: U16): U14 = {
    val v = n.toMP
    require(0 <= v && v < z"16383")
    return org.sireum.U14(v)
  }

  @pure def toU15(n: U16): U15 = {
    val v = n.toMP
    require(0 <= v && v < z"32767")
    return org.sireum.U15(v)
  }

  @pure def toU16(n: U16): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: U16): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: U16): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: U16): R = org.sireum.R.$String(n.toString)

  @pure def toRawS16(n: U16): S16 = org.sireum.S16(n.value)
}

object U17_Ext {
  @pure def toU32(n: U17): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U17): U64 = org.sireum.U64(n.toMP)
}

object U18_Ext {
  @pure def toU32(n: U18): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U18): U64 = org.sireum.U64(n.toMP)
}

object U19_Ext {
  @pure def toU32(n: U19): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U19): U64 = org.sireum.U64(n.toMP)
}

object U20_Ext {
  @pure def toU32(n: U20): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U20): U64 = org.sireum.U64(n.toMP)
}

object U21_Ext {
  @pure def toU32(n: U21): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U21): U64 = org.sireum.U64(n.toMP)
}

object U22_Ext {
  @pure def toU32(n: U22): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U22): U64 = org.sireum.U64(n.toMP)
}

object U23_Ext {
  @pure def toU32(n: U23): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U23): U64 = org.sireum.U64(n.toMP)
}

object U24_Ext {
  @pure def toU32(n: U24): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U24): U64 = org.sireum.U64(n.toMP)
}

object U25_Ext {
  @pure def toU32(n: U25): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U25): U64 = org.sireum.U64(n.toMP)
}

object U26_Ext {
  @pure def toU32(n: U26): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U26): U64 = org.sireum.U64(n.toMP)
}

object U27_Ext {
  @pure def toU32(n: U27): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U27): U64 = org.sireum.U64(n.toMP)
}

object U28_Ext {
  @pure def toU32(n: U28): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U28): U64 = org.sireum.U64(n.toMP)
}

object U29_Ext {
  @pure def toU32(n: U29): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U29): U64 = org.sireum.U64(n.toMP)
}

object U30_Ext {
  @pure def toU32(n: U30): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U30): U64 = org.sireum.U64(n.toMP)
}

object U31_Ext {
  @pure def toU32(n: U31): U32 = org.sireum.U32(n.toMP)
  @pure def toU64(n: U31): U64 = org.sireum.U64(n.toMP)
}

object U32_Ext {

  @pure def toB(n: U32): B = n != u32"0"

  @pure def toZ(n: U32): Z = n.toMP

  @pure def toZ8(n: U32): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: U32): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: U32): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: U32): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: U32): N = org.sireum.N(n.toMP)

  @pure def toN8(n: U32): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: U32): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: U32): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: U32): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: U32): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: U32): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: U32): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: U32): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: U32): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: U32): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU17(n: U32): U17 = $

  @pure def toU18(n: U32): U18 = $

  @pure def toU19(n: U32): U19 = $

  @pure def toU20(n: U32): U20 = $

  @pure def toU21(n: U32): U21 = $

  @pure def toU22(n: U32): U22 = $

  @pure def toU23(n: U32): U23 = $

  @pure def toU24(n: U32): U24 = $

  @pure def toU25(n: U32): U25 = $

  @pure def toU26(n: U32): U26 = $

  @pure def toU27(n: U32): U27 = $

  @pure def toU28(n: U32): U28 = $

  @pure def toU29(n: U32): U29 = $

  @pure def toU30(n: U32): U30 = $

  @pure def toU31(n: U32): U31 = $

  @pure def toU32(n: U32): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU64(n: U32): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: U32): R = org.sireum.R.$String(n.toString)

  @pure def toRawS32(n: U32): S32 = org.sireum.S32(n.value)

  @pure def toRawF32(n: U32): F32 = org.sireum.F32(_root_.java.lang.Float.intBitsToFloat(n.value))

  @pure def toF32(n: U32): F32 = n.value.asInstanceOf[Float]

  @pure def toC(n: U32): C = org.sireum.C(n.value)
}
object U33_Ext {
  @pure def toU64(n: U33): U64 = org.sireum.U64(n.toMP)
}

object U34_Ext {
  @pure def toU64(n: U34): U64 = org.sireum.U64(n.toMP)
}

object U35_Ext {
  @pure def toU64(n: U35): U64 = org.sireum.U64(n.toMP)
}

object U36_Ext {
  @pure def toU64(n: U36): U64 = org.sireum.U64(n.toMP)
}

object U37_Ext {
  @pure def toU64(n: U37): U64 = org.sireum.U64(n.toMP)
}

object U38_Ext {
  @pure def toU64(n: U38): U64 = org.sireum.U64(n.toMP)
}

object U39_Ext {
  @pure def toU64(n: U39): U64 = org.sireum.U64(n.toMP)
}

object U40_Ext {
  @pure def toU64(n: U40): U64 = org.sireum.U64(n.toMP)
}

object U41_Ext {
  @pure def toU64(n: U41): U64 = org.sireum.U64(n.toMP)
}

object U42_Ext {
  @pure def toU64(n: U42): U64 = org.sireum.U64(n.toMP)
}

object U43_Ext {
  @pure def toU64(n: U43): U64 = org.sireum.U64(n.toMP)
}

object U44_Ext {
  @pure def toU64(n: U44): U64 = org.sireum.U64(n.toMP)
}

object U45_Ext {
  @pure def toU64(n: U45): U64 = org.sireum.U64(n.toMP)
}

object U46_Ext {
  @pure def toU64(n: U46): U64 = org.sireum.U64(n.toMP)
}

object U47_Ext {
  @pure def toU64(n: U47): U64 = org.sireum.U64(n.toMP)
}

object U48_Ext {
  @pure def toU64(n: U48): U64 = org.sireum.U64(n.toMP)
}

object U49_Ext {
  @pure def toU64(n: U49): U64 = org.sireum.U64(n.toMP)
}

object U50_Ext {
  @pure def toU64(n: U50): U64 = org.sireum.U64(n.toMP)
}

object U51_Ext {
  @pure def toU64(n: U51): U64 = org.sireum.U64(n.toMP)
}

object U52_Ext {
  @pure def toU64(n: U52): U64 = org.sireum.U64(n.toMP)
}

object U53_Ext {
  @pure def toU64(n: U53): U64 = org.sireum.U64(n.toMP)
}

object U54_Ext {
  @pure def toU64(n: U54): U64 = org.sireum.U64(n.toMP)
}

object U55_Ext {
  @pure def toU64(n: U55): U64 = org.sireum.U64(n.toMP)
}

object U56_Ext {
  @pure def toU64(n: U56): U64 = org.sireum.U64(n.toMP)
}

object U57_Ext {
  @pure def toU64(n: U57): U64 = org.sireum.U64(n.toMP)
}

object U58_Ext {
  @pure def toU64(n: U58): U64 = org.sireum.U64(n.toMP)
}

object U59_Ext {
  @pure def toU64(n: U59): U64 = org.sireum.U64(n.toMP)
}

object U60_Ext {
  @pure def toU64(n: U60): U64 = org.sireum.U64(n.toMP)
}

object U61_Ext {
  @pure def toU64(n: U61): U64 = org.sireum.U64(n.toMP)
}

object U62_Ext {
  @pure def toU64(n: U62): U64 = org.sireum.U64(n.toMP)
}

object U63_Ext {
  @pure def toU64(n: U63): U64 = org.sireum.U64(n.toMP)
}

object U64_Ext {

  @pure def toB(n: U64): B = n != u64"0"

  @pure def toZ(n: U64): Z = n.toMP

  @pure def toZ8(n: U64): Z8 = org.sireum.Z8(n.toMP)

  @pure def toZ16(n: U64): Z16 = org.sireum.Z16(n.toMP)

  @pure def toZ32(n: U64): Z32 = org.sireum.Z32(n.toMP)

  @pure def toZ64(n: U64): Z64 = org.sireum.Z64(n.toMP)

  @pure def toN(n: U64): N = org.sireum.N(n.toMP)

  @pure def toN8(n: U64): N8 = org.sireum.N8(n.toMP)

  @pure def toN16(n: U64): N16 = org.sireum.N16(n.toMP)

  @pure def toN32(n: U64): N32 = org.sireum.N32(n.toMP)

  @pure def toN64(n: U64): N64 = org.sireum.N64(n.toMP)

  @pure def toS8(n: U64): S8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned8(mp))
    org.sireum.S8(mp)
  }

  @pure def toS16(n: U64): S16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned16(mp))
    org.sireum.S16(mp)
  }

  @pure def toS32(n: U64): S32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned32(mp))
    org.sireum.S32(mp)
  }

  @pure def toS64(n: U64): S64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeSigned64(mp))
    org.sireum.S64(mp)
  }

  @pure def toU8(n: U64): U8 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned8(mp))
    org.sireum.U8(mp)
  }

  @pure def toU16(n: U64): U16 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned16(mp))
    org.sireum.U16(mp)
  }

  @pure def toU32(n: U64): U32 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned32(mp))
    org.sireum.U32(mp)
  }

  @pure def toU33(n: U64): U33 = {
    val v = n.toMP
    require(0 <= v && v < z"8589934591")
    return org.sireum.U33(v)
  }

  @pure def toU34(n: U64): U34 = {
    val v = n.toMP
    require(0 <= v && v < z"17179869183")
    return org.sireum.U34(v)
  }

  @pure def toU35(n: U64): U35 = {
    val v = n.toMP
    require(0 <= v && v < z"34359738367")
    return org.sireum.U35(v)
  }

  @pure def toU36(n: U64): U36 = {
    val v = n.toMP
    require(0 <= v && v < z"68719476735")
    return org.sireum.U36(v)
  }

  @pure def toU37(n: U64): U37 = {
    val v = n.toMP
    require(0 <= v && v < z"137438953471")
    return org.sireum.U37(v)
  }

  @pure def toU38(n: U64): U38 = {
    val v = n.toMP
    require(0 <= v && v < z"274877906943")
    return org.sireum.U38(v)
  }

  @pure def toU39(n: U64): U39 = {
    val v = n.toMP
    require(0 <= v && v < z"549755813887")
    return org.sireum.U39(v)
  }

  @pure def toU40(n: U64): U40 = {
    val v = n.toMP
    require(0 <= v && v < z"1099511627775")
    return org.sireum.U40(v)
  }

  @pure def toU41(n: U64): U41 = {
    val v = n.toMP
    require(0 <= v && v < z"2199023255551")
    return org.sireum.U41(v)
  }

  @pure def toU42(n: U64): U42 = {
    val v = n.toMP
    require(0 <= v && v < z"4398046511103")
    return org.sireum.U42(v)
  }

  @pure def toU43(n: U64): U43 = {
    val v = n.toMP
    require(0 <= v && v < z"8796093022207")
    return org.sireum.U43(v)
  }

  @pure def toU44(n: U64): U44 = {
    val v = n.toMP
    require(0 <= v && v < z"17592186044415")
    return org.sireum.U44(v)
  }

  @pure def toU45(n: U64): U45 = {
    val v = n.toMP
    require(0 <= v && v < z"35184372088831")
    return org.sireum.U45(v)
  }

  @pure def toU46(n: U64): U46 = {
    val v = n.toMP
    require(0 <= v && v < z"70368744177663")
    return org.sireum.U46(v)
  }

  @pure def toU47(n: U64): U47 = {
    val v = n.toMP
    require(0 <= v && v < z"140737488355327")
    return org.sireum.U47(v)
  }

  @pure def toU48(n: U64): U48 = {
    val v = n.toMP
    require(0 <= v && v < z"281474976710655")
    return org.sireum.U48(v)
  }

  @pure def toU49(n: U64): U49 = {
    val v = n.toMP
    require(0 <= v && v < z"562949953421311")
    return org.sireum.U49(v)
  }

  @pure def toU50(n: U64): U50 = {
    val v = n.toMP
    require(0 <= v && v < z"1125899906842623")
    return org.sireum.U50(v)
  }

  @pure def toU51(n: U64): U51 = {
    val v = n.toMP
    require(0 <= v && v < z"2251799813685247")
    return org.sireum.U51(v)
  }

  @pure def toU52(n: U64): U52 = {
    val v = n.toMP
    require(0 <= v && v < z"4503599627370495")
    return org.sireum.U52(v)
  }

  @pure def toU53(n: U64): U53 = {
    val v = n.toMP
    require(0 <= v && v < z"9007199254740991")
    return org.sireum.U53(v)
  }

  @pure def toU54(n: U64): U54 = {
    val v = n.toMP
    require(0 <= v && v < z"18014398509481983")
    return org.sireum.U54(v)
  }

  @pure def toU55(n: U64): U55 = {
    val v = n.toMP
    require(0 <= v && v < z"36028797018963967")
    return org.sireum.U55(v)
  }

  @pure def toU56(n: U64): U56 = {
    val v = n.toMP
    require(0 <= v && v < z"72057594037927935")
    return org.sireum.U56(v)
  }

  @pure def toU57(n: U64): U57 = {
    val v = n.toMP
    require(0 <= v && v < z"144115188075855871")
    return org.sireum.U57(v)
  }

  @pure def toU58(n: U64): U58 = {
    val v = n.toMP
    require(0 <= v && v < z"288230376151711743")
    return org.sireum.U58(v)
  }

  @pure def toU59(n: U64): U59 = {
    val v = n.toMP
    require(0 <= v && v < z"576460752303423487")
    return org.sireum.U59(v)
  }

  @pure def toU60(n: U64): U60 = {
    val v = n.toMP
    require(0 <= v && v < z"1152921504606846975")
    return org.sireum.U60(v)
  }

  @pure def toU61(n: U64): U61 = {
    val v = n.toMP
    require(0 <= v && v < z"2305843009213693951")
    return org.sireum.U61(v)
  }

  @pure def toU62(n: U64): U62 = {
    val v = n.toMP
    require(0 <= v && v < z"4611686018427387903")
    return org.sireum.U62(v)
  }

  @pure def toU63(n: U64): U63 = {
    val v = n.toMP
    require(0 <= v && v < z"9223372036854775807")
    return org.sireum.U63(v)
  }

  @pure def toU64(n: U64): U64 = {
    val mp = n.toMP
    require(Z_Ext.isInRangeUnsigned64(mp))
    org.sireum.U64(mp)
  }

  @pure def toR(n: U64): R = org.sireum.R.$String(n.toString)

  @pure def toRawS64(n: U64): S64 = org.sireum.S64(n.value)

  @pure def toRawF64(n: U64): F64 = org.sireum.F64(_root_.java.lang.Double.longBitsToDouble(n.value))

  @pure def toF64(n: U64): F64 = n.value.asInstanceOf[Double]
}

object F32_Ext {

  @pure def toB(n: F32): B = n != f32"0.0"

  @pure def toRawU32(n: F32): U32 = org.sireum.U32($internal.Macro.f32Bit(n.value))

  @pure def toF64(n: F32): F64 = n.value

  @pure def toF32(n: F32): F32 = n

  @pure def toR(n: F32): R = R(n.value)
}

object F64_Ext {

  @pure def toB(n: F64): B = n != f64"0.0"

  @pure def toRawU64(n: F64): U64 = org.sireum.U64($internal.Macro.f64Bit(n.value))

  @pure def toF64(n: F64): F64 = n

  @pure def toR(n: F64): R = R(n.value)
}

object R_Ext {

  @pure def toB(n: R): B = n != r"0.0"

  @pure def toZ(n: R): Z = org.sireum.Z(n.value.toBigInt)

  @pure def toN(n: R): N = {
    require(n >= r"0.0")
    org.sireum.N(n.value.toBigInt)
  }

  @pure def toR(n: R): R = n
}

object String_Ext {

  import java.nio.charset.StandardCharsets.UTF_8

  @pure def fromCis[@index I](cs: IS[I, C]): String = {
    if (cs.isEmpty) return ""
    new Predef.String(cs.data.asInstanceOf[scala.Array[scala.Int]], 0, cs.length.toInt)
  }

  @pure def fromCms[@index I](cs: MS[I, C]): String = {
    if (cs.isEmpty) return ""
    new Predef.String(cs.data.asInstanceOf[scala.Array[scala.Int]], 0, cs.length.toInt)
  }

  @pure def fromU8is(u8s: IS[Z, U8]): String = {
    if (u8s.isEmpty) return ""
    new _root_.java.lang.String(u8s.elements.toArray.map(_.value), UTF_8)
  }

  @pure def fromU8ms(u8s: MS[Z, U8]): String = {
    if (u8s.isEmpty) return ""
    new _root_.java.lang.String(u8s.elements.toArray.map(_.value), UTF_8)
  }

  @pure def fromBase64(s: String): Either[IS[Z, U8], String] = {
    try {
      val array = _root_.java.util.Base64.getDecoder.decode(s.value)
      val r = MSZ.create(array.size, u8"0")
      for (i <- 0 until array.length) {
        r(i) = org.sireum.U8(array(i))
      }
      Either.Left(r.toIS)
    } catch {
      case e: Throwable => Either.Right(e.getMessage)
    }
  }

  @pure def toBase64(data: IS[Z, U8]): String = {
    val size = data.size.toBigInt.toInt
    val array = data.data.asInstanceOf[scala.Array[scala.Byte]].slice(0, size)
    _root_.java.util.Base64.getEncoder.encodeToString(array)
  }

  @pure def toBis(s: String): IS[Z, U8] =
    IS[Z, U8](s.value.getBytes("UTF-8").toIndexedSeq.map(org.sireum.U8(_)): _*)

  @pure def toBms(s: String): MS[Z, U8] =
    MS[Z, U8](s.value.getBytes("UTF-8").toIndexedSeq.map(org.sireum.U8(_)): _*)

  @pure def toCis(s: String): IS[Z, C] = {
    toCms(s).toIS
  }

  @pure def toCStream(s: String): Jen[C] =
    new Jen[C] {
      override def generate(f: C => Jen.Action): Jen.Action = {
        var i = 0
        val ps = s.value
        var last = Jen.Continue
        val len = ps.codePointCount(0, ps.length)
        while (i < len) {
          val e = ps.codePointAt(i)
          last = f(C(e))
          if (!last) {
            return Jen.End
          }
          i = i + 1
        }
        return last
      }
      override def string: String = {
        return s"Jen($s)"
      }
    }

  @pure def toCms(s: String): MS[Z, C] = {
    import $internal.###
    var r: MS[Z, C] = null
    ###(!("true" == System.getenv("PROYEK_JS") || scala.util.Try(Class.forName("scala.scalajs.js.Any", false, getClass.getClassLoader)).isSuccess)) {
      val str = s.value
      val n = str.codePointCount(0, str.length)
      val ms = MSZ.create[C](n, '\u0000')
      for (i <- 0 until n) {
        ms(i) = org.sireum.C(str.codePointAt(i))
      }
      r = ms
    }
    ###("true" == System.getenv("PROYEK_JS") || scala.util.Try(Class.forName("scala.scalajs.js.Any", false, getClass.getClassLoader)).isSuccess) {
      val str = s.value
      val a = str.codePoints.toArray
      val n = a.length
      val ms = MSZ.create[C](n, '\u0000')
      for (i <- 0 until n) {
        ms(i) = org.sireum.C(a(i))
      }
      r = ms
    }
    r
  }

  @pure def toU8is(s: String): IS[Z, U8] =
    IS[Z, U8](s.value.getBytes(UTF_8).toIndexedSeq.map(org.sireum.U8(_)): _*)

  @pure def toU8ms(s: String): MS[Z, U8] =
    MS[Z, U8](s.value.getBytes(UTF_8).toIndexedSeq.map(org.sireum.U8(_)): _*)

  @pure def toCisLineStream(s: String): Jen[ISZ[C]] = {
    return new Jen[ISZ[C]] {
      override def generate(f: ISZ[C] => Jen.Action): Jen.Action = {
        val st = new java.util.StringTokenizer(s.value, "\n\r", true)
        var last = Jen.Continue
        var seenN = F
        while (st.hasMoreTokens) {
          st.nextToken match {
            case "\n" =>
              if (seenN) {
                last = f(ISZ())
                if (!last) {
                  return Jen.End
                }
                seenN = F
              } else {
                seenN = T
              }
            case "\r" =>
            case token =>
              seenN = F
              last = f(conversions.String.toCis(token))
              if (!last) {
                return Jen.End
              }
          }
        }
        return last
      }

      override def string: String = s"Jen[ISZ[C]]($s)"
    }
  }
}

@ext object ISB_Ext {
  @pure def fromISU8[@index I](s: IS[I, U8]): IS[I, B] =
    new IS(s.companion, s.data.asInstanceOf[Array[Byte]].clone, s.length * 8, org.sireum.B.Boxer)

  @pure def toISU8[@index I](s: IS[I, B]): IS[I, U8] =
    new IS(s.companion, s.data.asInstanceOf[Array[Byte]].clone, s.length / 8 + (if (s.length % 8 == 0) 0 else 1), org.sireum.U8.Boxer)

  @pure def toMSU8[@index I](s: IS[I, B]): MS[I, U8] =
    new MS(s.companion, s.data.asInstanceOf[Array[Byte]].clone, s.length / 8 + (if (s.length % 8 == 0) 0 else 1), org.sireum.U8.Boxer)
}

@ext object MSB_Ext {
  @pure def fromMSU8[@index I](s: MS[I, U8]): MS[I, B] =
    new MS(s.companion, s.data.asInstanceOf[Array[Byte]].clone, s.length * 8, org.sireum.B.Boxer)

  @pure def toISU8[@index I](s: MS[I, B]): IS[I, U8] =
    new IS(s.companion, s.data.asInstanceOf[Array[Byte]].clone, s.length / 8 + (if (s.length % 8 == 0) 0 else 1), org.sireum.U8.Boxer)

  @pure def toMSU8[@index I](s: MS[I, B]): MS[I, U8] =
    new MS(s.companion, s.data.asInstanceOf[Array[Byte]].clone, s.length / 8 + (if (s.length % 8 == 0) 0 else 1), org.sireum.U8.Boxer)
}

