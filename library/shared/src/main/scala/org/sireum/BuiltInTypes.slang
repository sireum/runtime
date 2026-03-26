/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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


type @sig @ext B {

  def @pure hash: Z

  def @pure string: String

  def @pure &(other: B): B

  def @pure |(other: B): B

  def @pure |^(other: B): B

  def @pure `__>:`(other: B): B

  def @pure ->:(other: B): B

  def @pure =!=(other: B): B

  def @pure ===(other: B): B

  def @pure &&(other: => B): B

  def @pure ||(other: => B): B

  def @pure `___>:`(other: => B): B

  def @pure -->:(other: => B): B

  def @pure =>:(other: B): B

  def @pure unary_! : B

  def @pure unary_~ : B

}


type @sig @ext C {

  def @pure hash: Z

  def @pure string: String

  def @pure `<`(other: C): B

  def @pure <=(other: C): B

  def @pure `>`(other: C): B

  def @pure >=(other: C): B

  def @pure ===(other: C): B

  def @pure =!=(other: C): B

  def @pure >>>(other: C): C

  def @pure <<(other: C): C

  def @pure &(other: C): C

  def @pure |(other: C): C

  def @pure |^(other: C): C

  def @pure isWhitespace: B

  def @pure toZ: Z
}


type @sig @ext Z {

  def @pure hash: Z

  def @pure string: String

  def @pure `<`(other: Z): B

  def @pure <=(other: Z): B

  def @pure `>`(other: Z): B

  def @pure >=(other: Z): B

  def @pure ===(other: Z): B

  def @pure =!=(other: Z): B

  def @pure +(other: Z): Z

  def @pure -(other: Z): Z

  def @pure `*`(other: Z): Z

  def @pure /(other: Z): Z

  def @pure %(other: Z): Z

  def @pure unary_- : Z

  def @pure increase: Z

  def @pure decrease: Z

}


type @sig @ext F32 {

  def @pure hash: Z

  def @pure string: String

  def @pure `<`(other: F32): B

  def @pure <=(other: F32): B

  def @pure `>`(other: F32): B

  def @pure >=(other: F32): B

  def @pure +(other: F32): F32

  def @pure -(other: F32): F32

  def @pure `*`(other: F32): F32

  def @pure /(other: F32): F32

  def @pure %(other: F32): F32

  def @pure unary_- : F32

  def @pure isNaN: B

  def @pure isInfinite: B

  def @pure ~~(other: F32): B

  def @pure !~(other: F32): B

}


type @sig @ext F64 {

  def @pure hash: Z

  def @pure string: String

  def @pure `<`(other: F64): B

  def @pure <=(other: F64): B

  def @pure `>`(other: F64): B

  def @pure >=(other: F64): B

  def @pure +(other: F64): F64

  def @pure -(other: F64): F64

  def @pure `*`(other: F64): F64

  def @pure /(other: F64): F64

  def @pure %(other: F64): F64

  def @pure unary_- : F64

  def @pure isNaN: B

  def @pure isInfinite: B

  def @pure ~~(other: F64): B

  def @pure !~(other: F64): B
}


type @sig @ext R {

  def @pure hash: Z

  def @pure string: String

  def @pure `<`(other: R): B

  def @pure <=(other: R): B

  def @pure `>`(other: R): B

  def @pure >=(other: R): B

  def @pure ===(other: R): B

  def @pure =!=(other: R): B

  def @pure +(other: R): R

  def @pure -(other: R): R

  def @pure `*`(other: R): R

  def @pure /(other: R): R

  def @pure unary_- : R

}

type @sig @ext String {

  def @pure ===(other: String): B

  def @pure =!=(other: String): B

  def @pure `<`(other: String): B

  def @pure <=(other: String): B

  def @pure `>`(other: String): B

  def @pure >=(other: String): B

  def @pure hash: Z

  def @pure string: String

  def @pure size: Z

  def @pure stripMargin: String

}


type @sig @ext ST {

  def @pure hash: Z

  def @pure string: String

  def @pure render: String

  def @pure renderCompact: String

}


type @sig @ext IS[@index I, @imm E] {

  def @pure hash: Z

  def @pure string: String

  def @pure isEmpty: B @[
    ensures Res == (this.size == 0)
  ]

  def @pure nonEmpty: B @[
    ensures Res == (this.size != 0)
  ]

  def @pure isInBound(i: I): B

  def @pure :+(e: E): IS[I, E]

  def @pure +:(e: E): IS[I, E]

  def @pure ++[@index I2](other: IS[I2, E]): IS[I, E]

  def @pure --[@index I2](other: IS[I2, E]): IS[I, E]

  def @pure -(e: E): IS[I, E]

  def @pure map[@imm E2](f: E => @pure E2): IS[I, E2]

  def @pure flatMap[@imm E2](f: E => @pure IS[I, E2]): IS[I, E2]

  def @pure filter(p: E => @pure B): IS[I, E]

  def foreach[V](p: E => V)

  def @pure size: Z

  def @pure sizeS32: S32

  def @pure sizeU32: U32

  def @pure sizeS64: S64

  def @pure sizeU64: U64

  def @pure firstIndex: I

  def @pure lastIndex: I

  def @pure indices: ISZ[I]

  def @pure atZ(index: Z): E

  def @pure atS32(index: S32): E

  def @pure atU32(index: U32): E

  def @pure atS64(index: S64): E

  def @pure atU64(index: U64): E

  def @pure toMS: MS[I, E] @[
    ensures Res.size == this.size, ∀ i: Res.indices => Res(i) == this(i)
  ]

  def @pure toISZ: ISZ[E] @[
    ensures Res.size == this.size, ∀ e: this => ∃ i: Res.indices => Res(i) == e
  ]

  def @pure toMSZ: MSZ[E] @[
    ensures Res.size == this.size, ∀ e: this => ∃ i: Res.indices => Res(i) == e
  ]
}


type @msig @ext MS[@index I, @mut E] {

  def @pure hash: Z

  def @pure string: String

  def @pure isEmpty: B @[
    ensures Res == (this.size == 0)
  ]

  def @pure nonEmpty: B @[
    ensures Res == (this.size != 0)
  ]

  def @pure isInBound(i: I): B

  def @pure :+(e: E): MS[I, E]

  def @pure +:(e: E): MS[I, E]

  def @pure ++[@index I2](other: MS[I2, E]): MS[I, E]

  def @pure --[@index I2](other: MS[I2, E]): MS[I, E]

  def @pure -(e: E): MS[I, E]

  def @pure map[@mut E2](f: E => @pure E2): MS[I, E2]

  def @pure flatMap[@mut E2](f: E => @pure MS[I, E2]): MS[I, E2]

  def @pure filter(p: E => @pure B): MS[I, E]

  def foreach[@mut V](p: E => V)

  def @pure size: Z

  def @pure sizeS32: S32

  def @pure sizeU32: U32

  def @pure sizeS64: S64

  def @pure sizeU64: U64

  def @pure firstIndex: I

  def @pure lastIndex: I

  def @pure indices: ISZ[I]

  def @pure atZ(index: Z): E

  def @pure atS32(index: S32): E

  def @pure atU32(index: U32): E

  def @pure atS64(index: S64): E

  def @pure atU64(index: U64): E

  def @pure updateZ(index: Z, e: E)

  def @pure updateS32(index: S32, e: E)

  def @pure updateU32(index: U32, e: E)

  def @pure updateS64(index: S64, e: E)

  def @pure updateU64(index: U64, e: E)

  def @pure toIS[@imm E2]: IS[I, E2]

  def @pure toISZ[@imm E2]: ISZ[E2]

  def @pure toMSZ: MSZ[E] @[
    ensures Res.size == this.size, ∀ e: this => ∃ i: Res.indices => Res(i) == e
  ]
}


type @sig @ext StepId

type @sig @ext RS {
  def @pure +(other: RS): RS
  def @pure -(other: RS): RS
}


package @ext B {

  def random: B

  def randomSeed(seed: Z): B

}

package @ext C {

  def fromZ(n: Z): C

  def random: C

  def randomSeed(seed: Z): Z

  def randomBetween(min: C, max: C): C

  def randomSeedBetween(seed: Z, min: C, max: C): C

}

package @ext Z {

  def random: Z

  def randomSeed(seed: Z): Z

  def randomBetween(min: Z, max: Z): Z

  def randomSeedBetween(seed: Z, min: Z, max: Z): Z

  def read(): Z

  def prompt(msg: String): Z

}

package @ext F32 {

  val PInf: F32

  val NInf: F32

  val NaN: F32

  def MaxValue: F32 @[
    ensures Res == 3.4028235E38f
  ]

  def MinValue: F32 @[
    ensures Res == -3.4028235E38f
  ]

  def random: F32

  def randomSeed(seed: Z): F32

  def randomBetween(min: F32, max: F32): F32

  def randomSeedBetween(seed: Z, min: F32, max: F32): F32

}

package @ext F64 {

  val PInf: F64

  val NInf: F64

  val NaN: F64

  def MaxValue: F64 @[
    ensures Res == 1.7976931348623157E308d
  ]

  def MinValue: F64 @[
    ensures Res == -1.7976931348623157E308d
  ]

  def random: F64

  def randomSeed(seed: Z): F64

  def randomBetween(min: F64, max: F64): F64

  def randomSeedBetween(seed: Z, min: F64, max: F64): F64

}

package @ext R {

  def random: R

  def randomSeed(seed: Z): R

  def randomBetween(min: R, max: R): R

  def randomSeedBetween(seed: Z, min: R, max: R): R

}

package @ext String {

  def random: String

}

package @ext IS {

  def @pure create[@index I, E](size: Z, default: E): IS[I, E] @[
    requires seqIndexValidSize[I](size)
    ensures Res.size == size, ∀ i: Res.indices => Res(i) == default
  ]

}

package @ext ISZ {

  def @pure create[E](size: Z, default: E): ISZ[E] @[
    requires 0 <= size
    ensures Res.size == size, ∀ i: Res.indices => Res(i) == default
  ]

}

package @ext MS {

  def @pure create[@index I, @mut E](size: Z, default: E): MS[I, E] @[
    requires seqIndexValidSize[I](size)
    ensures Res.size == size, ∀ i: Res.indices => Res(i) == default
  ]

}

package @ext MSZ {

  def @pure create[@mut E](size: Z, default: E): MSZ[E] @[
    requires 0 <= size
    ensures Res.size == size, ∀ i: Res.indices => Res(i) == default
  ]

}

package @ext ZS {

  def random: ZS

  def @pure create(size: Z, default: Z): ZS @[
    requires 0 <= size
    ensures Res.size == size, ∀ i: Res.indices => Res(i) == default
  ]
}

package @ext Sireum {

  def initRuntimeLibrary()

  def proc(p: OsProto.Proc, reporter: message.Reporter): OsProto.Proc.Result

  def procCheck(p: OsProto.Proc, reporter: message.Reporter): OsProto.Proc.Result

  def run(args: ISZ[String]): Z

  def runWithInputAndReporter(args: ISZ[String], input: String, reporter: message.Reporter): (Z, String, String)

  def runWithReporter(args: ISZ[String], reporter: message.Reporter): (Z, String, String)

  def versions: Map[String, String]

  def javaVer: String

  def scalaVer: String

  def scalacPluginVer: String

  def version: String

  def commitHash: String

  def homePathString: String

  def javaHomePathString: String

  def scalaHomePathString: String

  def commitSha: String

  def platform: String

  def isNative: B

  def scalacPluginJarPathString: String

  def sireumJarPathString: String

  def nativ()

}
