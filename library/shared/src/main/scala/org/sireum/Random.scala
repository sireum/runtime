// #Sireum
/*
 Copyright (c) 2017-2023, Robby, Kansas State University
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
import org.sireum.U32._
import org.sireum.U64._

object Random {

  @record class Gen64(val gen: Impl.Xoshiro256) {
    def nextB(): B = {
      Contract(Modifies(gen))
      return (nextU64() >>> u64"63") == u64"1"
    }

    def nextC(): C = {
      Contract(Modifies(gen))
      return conversions.U32.toC(nextU32() % u32"0x110000")
    }

    def nextZ(): Z = {
      Contract(Modifies(gen))
      val n = nextU8Between(u8"0", u8"16").toZ
      var r: Z = nextS64().toZ
      for (_ <- 0 until n) {
        r = r * nextS64().toZ
      }
      return r
    }

    def nextF32_01(): F32 = {
      Contract(
        Modifies(gen),
        Ensures(0f <= Res, 1f > Res)
      )
      return conversions.U32.toF32(nextU32() >> u32"7") * f32"0x1.0p-25"
    }

    def nextF64_01(): F64 = {
      Contract(
        Modifies(gen),
        Ensures(0d <= Res, 1d > Res)
      )
      return conversions.U64.toF64(nextU64() >> u64"11") * f64"0x1.0p-53"
    }

    def nextF32(): F32 = {
      Contract(Modifies(gen))
      return conversions.U32.toRawF32(nextU32())
    }

    def nextF64(): F64 = {
      Contract(Modifies(gen))
      return conversions.U64.toRawF64(nextU64())
    }

    def nextR(): R = {
      Contract(Modifies(gen))
      val n = nextU8Between(u8"0", u8"16").toZ
      var r = conversions.F64.toR(nextF64())
      for (_ <- 0 until n) {
        r = r * conversions.F64.toR(nextF64())
      }
      return r
    }

    def nextCBetween(min: C, max: C): C = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextC().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return C.fromZ(r)
    }

    def nextZBetween(min: Z, max: Z): Z = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextZ()
      if (r < 0) {
        r = -r
      }
      r = r % (max - min + 1) + min
      return r
    }

    def nextF32Between(min: F32, max: F32): F32 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
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
        Modifies(gen),
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
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      val r = conversions.F64.toR(nextF64Between(0d, 1d)) * (max - min) + min
      return r
    }

    def nextS8(): S8 = {
      Contract(Modifies(gen))
      return conversions.U8.toRawS8(conversions.U64.toU8(nextU64() >>> u64"56"))
    }

    def nextS16(): S16 = {
      Contract(Modifies(gen))
      return conversions.U16.toRawS16(conversions.U64.toU16(nextU64() >>> u64"48"))
    }

    def nextS32(): S32 = {
      Contract(Modifies(gen))
      return conversions.U32.toRawS32(conversions.U64.toU32(nextU64() >>> u64"32"))
    }

    def nextS64(): S64 = {
      Contract(Modifies(gen))
      return conversions.U64.toRawS64(nextU64())
    }

    def nextU8(): U8 = {
      Contract(Modifies(gen))
      return U8.fromZ((nextU64() >>> u64"56").toZ)
    }

    def nextU16(): U16 = {
      Contract(Modifies(gen))
      return U16.fromZ((nextU64() >>> u64"48").toZ)
    }

    def nextU32(): U32 = {
      Contract(Modifies(gen))
      return U32.fromZ((nextU64() >>> u64"32").toZ)
    }

    def nextU64(): U64 = {
      Contract(Modifies(gen))
      return gen.pp()
    }

    def nextU8Between(min: U8, max: U8): U8 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextU8().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return U8.fromZ(r)
    }

    def nextU16Between(min: U16, max: U16): U16 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextU16().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return U16.fromZ(r)
    }

    def nextU32Between(min: U32, max: U32): U32 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextU32().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return U32.fromZ(r)
    }

    def nextU63Between(min: U64, max: U64): U64 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextU64().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return U64.fromZ(r)
    }

    def nextN8(): N8 = {
      Contract(Modifies(gen))
      return conversions.U8.toN8(nextU8())
    }

    def nextN16(): N16 = {
      Contract(Modifies(gen))
      return conversions.U16.toN16(nextU16())
    }

    def nextN32(): N32 = {
      Contract(Modifies(gen))
      return conversions.U32.toN32(nextU32())
    }

    def nextN64(): N64 = {
      Contract(Modifies(gen))
      return conversions.U64.toN64(nextU64())
    }

    def nextZ8(): Z8 = {
      Contract(Modifies(gen))
      return conversions.S8.toZ8(nextS8())
    }

    def nextZ16(): Z16 = {
      Contract(Modifies(gen))
      return conversions.S16.toZ16(nextS16())
    }

    def nextZ32(): Z32 = {
      Contract(Modifies(gen))
      return conversions.S32.toZ32(nextS32())
    }

    def nextZ64(): Z64 = {
      Contract(Modifies(gen))
      return conversions.S64.toZ64(nextS64())
    }

    def nextN8Between(min: N8, max: N8): N8 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextN8().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return N8.fromZ(r)
    }

    def nextN16Between(min: N16, max: N16): N16 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextN16().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return N16.fromZ(r)
    }

    def nextN32Between(min: N32, max: N32): N32 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextN32().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return N32.fromZ(r)
    }

    def nextN64Between(min: N64, max: N64): N64 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextN64().toZ
      r = r % ((max - min).toZ + 1) + min.toZ
      return N64.fromZ(r)
    }

    def nextS8Between(min: S8, max: S8): S8 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextS8().toZ
      if (r < 0) {
        r = -r
      }
      r = r % ((max - min).toZ + 1) + min.toZ
      return S8.fromZ(r)
    }

    def nextS16Between(min: S16, max: S16): S16 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextS16().toZ
      if (r < 0) {
        r = -r
      }
      r = r % ((max - min).toZ + 1) + min.toZ
      return S16.fromZ(r)
    }

    def nextS32Between(min: S32, max: S32): S32 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextS32().toZ
      if (r < 0) {
        r = -r
      }
      r = r % ((max - min).toZ + 1) + min.toZ
      return S32.fromZ(r)
    }

    def nextS64Between(min: S64, max: S64): S64 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextS64().toZ
      if (r < 0) {
        r = -r
      }
      r = r % ((max - min).toZ + 1) + min.toZ
      return S64.fromZ(r)
    }

    def nextZ8Between(min: Z8, max: Z8): Z8 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextZ8().toZ
      if (r < 0) {
        r = -r
      }
      r = r % ((max - min).toZ + 1) + min.toZ
      return Z8.fromZ(r)
    }

    def nextZ16Between(min: Z16, max: Z16): Z16 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextZ16().toZ
      if (r < 0) {
        r = -r
      }
      r = r % ((max - min).toZ + 1) + min.toZ
      return Z16.fromZ(r)
    }

    def nextZ32Between(min: Z32, max: Z32): Z32 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextZ32().toZ
      if (r < 0) {
        r = -r
      }
      r = r % ((max - min).toZ + 1) + min.toZ
      return Z32.fromZ(r)
    }

    def nextZ64Between(min: Z64, max: Z64): Z64 = {
      Contract(
        Requires(min <= max),
        Modifies(gen),
        Ensures(min <= Res, max >= Res)
      )
      var r = nextZ64().toZ
      if (r < 0) {
        r = -r
      }
      r = r % ((max - min).toZ + 1) + min.toZ
      return Z64.fromZ(r)
    }
  }

  @strictpure def create64: Gen64 = Gen64(Impl.Xoshiro256.create)

  @strictpure def createSeed64(seed: U64): Gen64 = Gen64(Impl.Xoshiro256.createSeed(seed))

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
        return SplitMix64(U64.fromZ(extension.Time.currentMillis))
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
        return result;
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
    def gen64: Gen64 = $
    def setSeed(seed: Z): Unit = $
  }

}
