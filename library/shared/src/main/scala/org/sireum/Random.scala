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

import org.sireum.U8._
import org.sireum.U16._
import org.sireum.U32._
import org.sireum.U64._
import org.sireum.S8._
import org.sireum.S16._
import org.sireum.S32._
import org.sireum.S64._

object Random {

  object Gen {

    @msig trait TestRunner[T] {

      def next(): T

      def toCompactJson(o: T): String

      def fromJson(json: String): T

      def test(o: T): B

    }

  }

  @msig trait Gen {
    @spec var seedRep: Z = $

    def nextB(): B = {
      Contract(Modifies(seedRep))
      return (nextU64() >>> u64"63") == u64"1"
    }

    def nextC(): C = {
      Contract(Modifies(seedRep))
      val n = nextU32()
      return conversions.U32.toC(n % u32"0x110000")
    }

    def nextZ(): Z = {
      Contract(Modifies(seedRep))
      val n = nextU8Between(u8"0", u8"16").toZ
      var r: Z = nextS64().toZ
      for (_ <- 0 until n) {
        r = r * nextS64().toZ
      }
      return r
    }

    def nextF32_01(): F32 = {
      Contract(
        Modifies(seedRep),
        Ensures(0f <= Res, 1f > Res)
      )
      return conversions.U32.toF32(nextU32() >> u32"7") * f32"0x1.0p-25"
    }

    def nextF64_01(): F64 = {
      Contract(
        Modifies(seedRep),
        Ensures(0d <= Res, 1d > Res)
      )
      return conversions.U64.toF64(nextU64() >> u64"11") * f64"0x1.0p-53"
    }

    def nextF32(): F32 = {
      Contract(Modifies(seedRep))
      val r = conversions.U32.toRawF32(nextU32())
      return if (r.isNaN) F32.NaN else r
    }

    def nextF64(): F64 = {
      Contract(Modifies(seedRep))
      val r = conversions.U64.toRawF64(nextU64())
      return if (r.isNaN) F64.NaN else r
    }

    def nextR(): R = {
      Contract(Modifies(seedRep))
      val r = conversions.F64.toR(nextF64_01()) * conversions.Z.toR(nextZ())
      return r
    }

    def nextCBetween(min: C, max: C): C = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextC().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return C.fromZ(r)
    }

    def nextZBetween(min: Z, max: Z): Z = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      val d = max - min + 1
      var r: Z = if (U64.Min.toZ <= d && d <= U64.Max.toZ) {
        var m = u64"1"
        while (m < u64"64" && (u64"1" << m).toZ <= d) {
          m = m + u64"1"
        }
        (nextU64() >>> (u64"64" - m)).toZ
      } else {
        var t = nextZ()
        if (t < 0) {
          t = -t
        }
        t
      }
      r = r % d + min
      return r
    }

    def nextF32Between(min: F32, max: F32): F32 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextF32_01() * (max - min) + min
      if (r >= max) {
        r = max
      }
      return r
    }

    def nextF64Between(min: F64, max: F64): F64 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextF64_01() * (max - min) + min
      if (r >= max) {
        r = max
      }
      return r
    }

    def nextRBetween(min: R, max: R): R = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      val r = conversions.F64.toR(nextF64Between(0d, 1d)) * (max - min) + min
      return r
    }

    def nextS8(): S8 = {
      Contract(Modifies(seedRep))
      return conversions.U8.toRawS8(conversions.U64.toU8(nextU64() >>> u64"56"))
    }

    def nextS16(): S16 = {
      Contract(Modifies(seedRep))
      return conversions.U16.toRawS16(conversions.U64.toU16(nextU64() >>> u64"48"))
    }

    def nextS32(): S32 = {
      Contract(Modifies(seedRep))
      return conversions.U32.toRawS32(conversions.U64.toU32(nextU64() >>> u64"32"))
    }

    def nextS64(): S64 = {
      Contract(Modifies(seedRep))
      return conversions.U64.toRawS64(nextU64())
    }

    def nextU8(): U8 = {
      Contract(Modifies(seedRep))
      return U8.fromZ((nextU64() >>> u64"56").toZ)
    }

    def nextU16(): U16 = {
      Contract(Modifies(seedRep))
      return U16.fromZ((nextU64() >>> u64"48").toZ)
    }

    def nextU32(): U32 = Contract.Only(Modifies(seedRep))

    def nextU64(): U64 = Contract.Only(Modifies(seedRep))

    def nextU8Between(min: U8, max: U8): U8 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      if (U8.Min == min && U8.Max == max) {
        return nextU8()
      }
      val d = max - min + u8"1"
      val r: U8 = {
        var m = u8"1"
        while (m < u8"8" && (u8"1" << m) <= d) {
          m = m + u8"1"
        }
        nextU8() >>> (u8"8" - m)
      }
      return r % d + min
    }

    def nextU16Between(min: U16, max: U16): U16 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      if (U16.Min == min && U16.Max == max) {
        return nextU16()
      }
      val d = max - min + u16"1"
      val r: U16 = {
        var m = u16"1"
        while (m < u16"16" && (u16"1" << m) <= d) {
          m = m + u16"1"
        }
        nextU16() >>> (u16"16" - m)
      }
      return r % d + min
    }

    def nextU32Between(min: U32, max: U32): U32 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      if (U32.Min == min && U32.Max == max) {
        return nextU32()
      }
      val d = max - min + u32"1"
      val r: U32 = {
        var m = u32"1"
        while (m < u32"32" && (u32"1" << m) <= d) {
          m = m + u32"1"
        }
        nextU32() >>> (u32"32" - m)
      }
      return r % d + min
    }

    def nextU64Between(min: U64, max: U64): U64 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      if (U64.Min == min && U64.Max == max) {
        return nextU64()
      }
      val d = max - min + u64"1"
      val r: U64 = {
        var m = u64"1"
        while (m < u64"64" && (u64"1" << m) <= d) {
          m = m + u64"1"
        }
        nextU64() >>> (u64"64" - m)
      }
      return r % d + min
    }

    def nextN8(): N8 = {
      Contract(Modifies(seedRep))
      return conversions.U8.toN8(nextU8())
    }

    def nextN16(): N16 = {
      Contract(Modifies(seedRep))
      return conversions.U16.toN16(nextU16())
    }

    def nextN32(): N32 = {
      Contract(Modifies(seedRep))
      return conversions.U32.toN32(nextU32())
    }

    def nextN64(): N64 = {
      Contract(Modifies(seedRep))
      return conversions.U64.toN64(nextU64())
    }

    def nextZ8(): Z8 = {
      Contract(Modifies(seedRep))
      return conversions.S8.toZ8(nextS8())
    }

    def nextZ16(): Z16 = {
      Contract(Modifies(seedRep))
      return conversions.S16.toZ16(nextS16())
    }

    def nextZ32(): Z32 = {
      Contract(Modifies(seedRep))
      return conversions.S32.toZ32(nextS32())
    }

    def nextZ64(): Z64 = {
      Contract(Modifies(seedRep))
      return conversions.S64.toZ64(nextS64())
    }

    def nextN8Between(min: N8, max: N8): N8 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      return N8.fromZ(nextU8Between(U8.fromZ(min.toZ), U8.fromZ(max.toZ)).toZ)
    }

    def nextN16Between(min: N16, max: N16): N16 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      return N16.fromZ(nextU16Between(U16.fromZ(min.toZ), U16.fromZ(max.toZ)).toZ)
    }

    def nextN32Between(min: N32, max: N32): N32 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      return N32.fromZ(nextU32Between(U32.fromZ(min.toZ), U32.fromZ(max.toZ)).toZ)
    }

    def nextN64Between(min: N64, max: N64): N64 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      return N64.fromZ(nextU64Between(U64.fromZ(min.toZ), U64.fromZ(max.toZ)).toZ)
    }

    def nextS8Between(min: S8, max: S8): S8 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      if (S8.Min == min && S8.Max == max) {
        return nextS8()
      }
      val d = max.toZ - min.toZ + 1
      val r: Z = {
        var m = u8"1"
        while (m < u8"8" && (u8"1" << m).toZ <= d) {
          m = m + u8"1"
        }
        (nextU8() >>> (u8"8" - m)).toZ
      }
      return S8.fromZ(r % d + min.toZ)
    }

    def nextS16Between(min: S16, max: S16): S16 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      if (S16.Min == min && S16.Max == max) {
        return nextS16()
      }
      val d = max.toZ - min.toZ + 1
      val r: Z = {
        var m = u16"1"
        while (m < u16"16" && (u16"1" << m).toZ <= d) {
          m = m + u16"1"
        }
        (nextU16() >>> (u16"16" - m)).toZ
      }
      return S16.fromZ(r % d + min.toZ)
    }

    def nextS32Between(min: S32, max: S32): S32 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      if (S32.Min == min && S32.Max == max) {
        return nextS32()
      }
      val d = max.toZ - min.toZ + 1
      val r: Z = {
        var m = u32"1"
        while (m < u32"32" && (u32"1" << m).toZ <= d) {
          m = m + u32"1"
        }
        (nextU32() >>> (u32"32" - m)).toZ
      }
      return S32.fromZ(r % d + min.toZ)
    }

    def nextS64Between(min: S64, max: S64): S64 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      if (S64.Min == min && S64.Max == max) {
        return nextS64()
      }
      val d = max.toZ - min.toZ + 1
      val r: Z = {
        var m = u64"1"
        while (m < u64"64" && (u64"1" << m).toZ <= d) {
          m = m + u64"1"
        }
        (nextU64() >>> (u64"64" - m)).toZ
      }
      return S64.fromZ(r % d + min.toZ)
    }

    def nextZ8Between(min: Z8, max: Z8): Z8 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      return Z8.fromZ(nextS8Between(S8.fromZ(min.toZ), S8.fromZ(max.toZ)).toZ)
    }

    def nextZ16Between(min: Z16, max: Z16): Z16 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      return Z16.fromZ(nextS16Between(S16.fromZ(min.toZ), S16.fromZ(max.toZ)).toZ)
    }

    def nextZ32Between(min: Z32, max: Z32): Z32 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      return Z32.fromZ(nextS32Between(S32.fromZ(min.toZ), S32.fromZ(max.toZ)).toZ)
    }

    def nextZ64Between(min: Z64, max: Z64): Z64 = {
      Contract(
        Requires(min <= max),
        Modifies(seedRep),
        Ensures(min <= Res, max >= Res)
      )
      return Z64.fromZ(nextS64Between(S64.fromZ(min.toZ), S64.fromZ(max.toZ)).toZ)
    }
  }

  @msig trait Gen64 extends Gen {
    def genU64(): U64 = Contract.Only(Modifies(seedRep))

    override def nextU32(): U32 = {
      Contract(Modifies(seedRep))
      return U32.fromZ((nextU64() >>> u64"32").toZ)
    }

    override def nextU64(): U64 = {
      Contract(Modifies(seedRep))
      return genU64()
    }
  }

  @record class Gen64Impl(val gen: Impl.Xoshiro256) extends Gen64 {
    override def genU64(): U64 = {
      return gen.pp()
    }
  }

  def setSeed(seed: Z): Unit = {
    var r = seed
    if (r < 0) {
      r = -r
    }
    r = r % (U64.Max.toZ + 1)
    Ext.setSeed(U64.fromZ(r))
  }

  @pure def create64: Gen64Impl = {
    return Gen64Impl(Impl.Xoshiro256.create)
  }

  @pure def createSeed64(seed: U64): Gen64Impl = {
    return Gen64Impl(Impl.Xoshiro256.createSeed(seed))
  }

  @strictpure def rotl32(x: U32, k: U32): U32 = (x << k) | (x >> (u32"32" - k))

  @strictpure def rotl64(x: U64, k: U64): U64 = (x << k) | (x >> (u64"64" - k))

  object Impl {

    /*  Written in 2019 by David Blackman and Sebastiano Vigna (vigna@acm.org)

    To the extent possible under law, the author has dedicated all copyright
    and related and neighboring rights to this software to the public domain
    worldwide. This software is distributed without any warranty.

    See <http://creativecommons.org/publicdomain/zero/1.0/>. */

    // Adapted from: https://prng.di.unimi.it/splitmix64.c
    object SplitMix64 {
      def create: SplitMix64 = {
        return SplitMix64(U64.fromZ(extension.Time.currentNanos))
      }
    }

    @record class SplitMix64(var seed: U64) {
      def next(): U64 = {
        seed = seed + u64"0x9e3779b97f4a7c15"
        var z = (seed |^ (seed >>> u64"30")) * u64"0xbf58476d1ce4e5b9"
        z = (z |^ (z >>> u64"27")) * u64"0x94d049bb133111eb"
        return z |^ (z >>> u64"31")
      }
    }

    object Xoshiro256 {
      @pure def create: Xoshiro256 = {
        val r = SplitMix64.create
        return Xoshiro256(r.next(), r.next(), r.next(), r.next())
      }

      @pure def createSeed(seed: U64): Xoshiro256 = {
        val r = SplitMix64(seed)
        return Xoshiro256(r.next(), r.next(), r.next(), r.next())
      }
    }

    @record class Xoshiro256(var seed0: U64, var seed1: U64, var seed2: U64, var seed3: U64) {
      def update(): Unit = {
        val t = seed1 << u64"17"
        seed2 = seed2 |^ seed0
        seed3 = seed3 |^ seed1
        seed1 = seed1 |^ seed2
        seed0 = seed0 |^ seed3
        seed2 = seed2 |^ t
        seed3 = rotl64(seed3, u64"45")
      }

      // Adapted from: https://prng.di.unimi.it/xoshiro256plusplus.c
      def pp(): U64 = {
        val result = rotl64(seed0 + seed3, u64"23") + seed0
        update()
        return result
      }

      // Adapted from: https://prng.di.unimi.it/xoshiro256starstar.c
      def ss(): U64 = {
        val result = rotl64(seed1 * u64"5", u64"7") * u64"9"
        update()
        return result
      }

      // Adapted from: https://prng.di.unimi.it/xoshiro256plus.c
      def p(): U64 = {
        val result = seed0 + seed3
        update()
        return result
      }
    }


    object Xoroshiro128 {
      @pure def create: Xoroshiro128 = {
        val r = SplitMix64.create
        val n0 = r.next()
        val n1 = r.next()
        return Xoroshiro128(conversions.U64.toU32(n0 >> u64"32"), conversions.U64.toU32(n0 & u64"0xFFFFFFFF"),
          conversions.U64.toU32(n1 >> u64"32"), conversions.U64.toU32(n1 & u64"0xFFFFFFFF"))
      }

      @pure def createSeed(seed: U64): Xoroshiro128 = {
        val r = SplitMix64(seed)
        val n0 = r.next()
        val n1 = r.next()
        return Xoroshiro128(conversions.U64.toU32(n0 >> u64"32"), conversions.U64.toU32(n0 & u64"0xFFFFFFFF"),
          conversions.U64.toU32(n1 >> u64"32"), conversions.U64.toU32(n1 & u64"0xFFFFFFFF"))
      }
    }

    @record class Xoroshiro128(var seed0: U32, var seed1: U32, var seed2: U32, var seed3: U32) {
      def update(): Unit = {
        val t = seed1 << u32"9"
        seed2 = seed2 |^ seed0
        seed3 = seed3 |^ seed1
        seed1 = seed1 |^ seed2
        seed0 = seed0 |^ seed3
        seed2 = seed2 |^ t
        seed3 = rotl32(seed3, u32"11")
      }

      // Adapted from: https://prng.di.unimi.it/xoshiro128plusplus.c
      def pp(): U32 = {
        val result = rotl32(seed0 + seed3, u32"7") + seed0
        update()
        return result
      }

      // Adapted from: https://prng.di.unimi.it/xoshiro128starstar.c
      def ss(): U32 = {
        val result = rotl32(seed1 * u32"5", u32"7") * u32"9"
        update()
        return result
      }

      // Adapted from: https://prng.di.unimi.it/xoshiro128plus.c
      def p(): U32 = {
        val result = seed0 + seed3
        update()
        return result
      }
    }
  }

  @ext("Random_Ext") object Ext {
    def instance: Gen = $
    def setSeed(n: U64): Unit = $
  }

}
