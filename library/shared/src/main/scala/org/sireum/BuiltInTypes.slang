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


@ext object B {

  def random: B = $

  def randomSeed(seed: Z): B = $

}

@ext trait B {

  @pure def hash: Z

  @pure def string: String

  @pure def &(other: B): B

  @pure def |(other: B): B

  @pure def |^(other: B): B

  @pure def __>:(other: B): B

  @pure def ->:(other: B): B

  @pure def =!=(other: B): B

  @pure def ===(other: B): B

  @pure def &&(other: => B): B

  @pure def ||(other: => B): B

  @pure def ___>:(other: => B): B

  @pure def -->:(other: => B): B

  @pure def =>:(other: B): B

  @pure def unary_! : B

  @pure def unary_~ : B

}


@ext object C {

  def fromZ(n: Z): C = $

  def random: C = $

  def randomSeed(seed: Z): Z = $

  def randomBetween(min: C, max: C): C = $

  def randomSeedBetween(seed: Z, min: C, max: C): C = $

}

@ext trait C {

  @pure def hash: Z

  @pure def string: String

  @pure def <(other: C): B

  @pure def <=(other: C): B

  @pure def >(other: C): B

  @pure def >=(other: C): B

  @pure def ===(other: C): B

  @pure def =!=(other: C): B

  @pure def >>>(other: C): C

  @pure def <<(other: C): C

  @pure def &(other: C): C

  @pure def |(other: C): C

  @pure def |^(other: C): C

  @pure def isWhitespace: B

  @pure def toZ: Z
}


@ext object Z {

  def random: Z = $

  def randomSeed(seed: Z): Z = $

  def randomBetween(min: Z, max: Z): Z = $

  def randomSeedBetween(seed: Z, min: Z, max: Z): Z = $

  def read(): Z = $

  def prompt(msg: String): Z = $

}

@ext trait Z {

  @pure def hash: Z

  @pure def string: String

  @pure def <(other: Z): B

  @pure def <=(other: Z): B

  @pure def >(other: Z): B

  @pure def >=(other: Z): B

  @pure def ===(other: Z): B

  @pure def =!=(other: Z): B

  @pure def +(other: Z): Z

  @pure def -(other: Z): Z

  @pure def *(other: Z): Z

  @pure def /(other: Z): Z

  @pure def %(other: Z): Z

  @pure def unary_- : Z

  @pure def increase: Z

  @pure def decrease: Z

}


@ext object F32 {

  val PInf: F32 = $

  val NInf: F32 = $

  val NaN: F32 = $

  def MaxValue: F32 = Contract.Only(Ensures(Res == f32"3.4028235E38"))

  def MinValue: F32 = Contract.Only(Ensures(Res == f32"-3.4028235E38"))

  def random: F32 = $

  def randomSeed(seed: Z): F32 = $

  def randomBetween(min: F32, max: F32): F32 = $

  def randomSeedBetween(seed: Z, min: F32, max: F32): F32 = $

}

@ext trait F32 {

  @pure def hash: Z

  @pure def string: String

  @pure def <(other: F32): B

  @pure def <=(other: F32): B

  @pure def >(other: F32): B

  @pure def >=(other: F32): B

  @pure def +(other: F32): F32

  @pure def -(other: F32): F32

  @pure def *(other: F32): F32

  @pure def /(other: F32): F32

  @pure def %(other: F32): F32

  @pure def unary_- : F32

  @pure def isNaN: B

  @pure def isInfinite: B

  @pure def ~~(other: F32): B

  @pure def !~(other: F32): B

}


@ext object F64 {

  val PInf: F64 = $

  val NInf: F64 = $

  val NaN: F64 = $

  def MaxValue: F64 = Contract.Only(Ensures(Res == 1.7976931348623157E308d))

  def MinValue: F64 = Contract.Only(Ensures(Res == -1.7976931348623157E308d))

  def random: F64 = $

  def randomSeed(seed: Z): F64 = $

  def randomBetween(min: F64, max: F64): F64 = $

  def randomSeedBetween(seed: Z, min: F64, max: F64): F64 = $

}

@ext trait F64 {

  @pure def hash: Z

  @pure def string: String

  @pure def <(other: F64): B

  @pure def <=(other: F64): B

  @pure def >(other: F64): B

  @pure def >=(other: F64): B

  @pure def +(other: F64): F64

  @pure def -(other: F64): F64

  @pure def *(other: F64): F64

  @pure def /(other: F64): F64

  @pure def %(other: F64): F64

  @pure def unary_- : F64

  @pure def isNaN: B

  @pure def isInfinite: B

  @pure def ~~(other: F64): B

  @pure def !~(other: F64): B
}


@ext object R {

  def random: R = $

  def randomSeed(seed: Z): R = $

  def randomBetween(min: R, max: R): R = $

  def randomSeedBetween(seed: Z, min: R, max: R): R = $

}

@ext trait R {

  @pure def hash: Z

  @pure def string: String

  @pure def <(other: R): B

  @pure def <=(other: R): B

  @pure def >(other: R): B

  @pure def >=(other: R): B

  @pure def ===(other: R): B

  @pure def =!=(other: R): B

  @pure def +(other: R): R

  @pure def -(other: R): R

  @pure def *(other: R): R

  @pure def /(other: R): R

  @pure def unary_- : R

}

@ext object String {

  def random: String = $

}

@ext trait String {

  @pure def ===(other: String): B

  @pure def =!=(other: String): B

  @pure def <(other: String): B

  @pure def <=(other: String): B

  @pure def >(other: String): B

  @pure def >=(other: String): B

  @pure def hash: Z

  @pure def string: String

  @pure def size: Z

  @pure def stripMargin: String

}


@ext trait ST {

  @pure def hash: Z

  @pure def string: String

  @pure def render: String

  @pure def renderCompact: String

}


@ext object IS {

  @pure def create[@index I, E](size: Z, default: E): IS[I, E] =
    Contract.Only(
      Requires(seqIndexValidSize[I](size)),
      Ensures(
        Res.size == size,
        ∀(Res.indices)(i => Res(i) == default)
      )
    )

}

@ext trait IS[@index I, @imm E] {

  @pure def hash: Z

  @pure def string: String

  @pure def isEmpty: B = Contract.Only(Ensures(Res == (this.size == 0)))

  @pure def nonEmpty: B = Contract.Only(Ensures(Res == (this.size != 0)))

  @pure def isInBound(i: I): B

  @pure def :+(e: E): IS[I, E]

  @pure def +:(e: E): IS[I, E]

  @pure def ++[@index I2](other: IS[I2, E]): IS[I, E]

  @pure def --[@index I2](other: IS[I2, E]): IS[I, E]

  @pure def -(e: E): IS[I, E]

  @pure def map[E2](f: E => E2 @pure): IS[I, E2]

  @pure def flatMap[E2](f: E => IS[I, E2] @pure): IS[I, E2]

  @pure def filter(p: E => B @pure): IS[I, E]

  def foreach[V](p: E => V): Unit

  @pure def size: Z

  @pure def firstIndex: I

  @pure def lastIndex: I

  @pure def indices: ISZ[I]

  @pure def atZ(index: Z): E

  @pure def toMS: MS[I, E] =
    Contract.Only(
      Ensures(
        Res.size == this.size,
        ∀(Res.indices)(i => Res(i) == this(i))
      )
    )

  @pure def toISZ: ISZ[E] =
    Contract.Only(
      Ensures(
        Res.size == this.size,
        ∀(this)(e => ∃(Res.indices)(i => Res(i) == e))
      )
    )

  @pure def toMSZ: MSZ[E] =
    Contract.Only(
      Ensures(
        Res.size == this.size,
        ∀(this)(e => ∃(Res.indices)(i => Res(i) == e))
      )
    )
}

@ext object ISZ {

  @pure def create[E](size: Z, default: E): ISZ[E] =
    Contract.Only(
      Requires(0 <= size),
      Ensures(
        Res.size == size,
        ∀(Res.indices)(i => Res(i) == default)
      )
    )

}

@ext object MS {

  @pure def create[@index I, @mut E](size: Z, default: E): MS[I, E] =
    Contract.Only(
      Requires(seqIndexValidSize[I](size)),
      Ensures(
        Res.size == size,
        ∀(Res.indices)(i => Res(i) == default)
      )
    )

}

@ext trait MS[@index I, @mut E] {

  @pure def hash: Z

  @pure def string: String

  @pure def isEmpty: B = Contract.Only(Ensures(Res == (this.size == 0)))

  @pure def nonEmpty: B = Contract.Only(Ensures(Res == (this.size != 0)))

  @pure def isInBound(i: I): B

  @pure def :+(e: E): MS[I, E]

  @pure def +:(e: E): MS[I, E]

  @pure def ++[@index I2](other: MS[I2, E]): MS[I, E]

  @pure def --[@index I2](other: MS[I2, E]): MS[I, E]

  @pure def -(e: E): MS[I, E]

  @pure def map[@mut E2](f: E => E2 @pure): MS[I, E2]

  @pure def flatMap[@mut E2](f: E => MS[I, E2] @pure): MS[I, E2]

  @pure def filter(p: E => B @pure): MS[I, E]

  def foreach[@mut V](p: E => V): Unit

  @pure def size: Z

  @pure def firstIndex: I

  @pure def lastIndex: I

  @pure def indices: ISZ[I]

  @pure def atZ(index: Z): E

  @pure def toIS[@imm E2]: IS[I, E2]

  @pure def toISZ[@imm E2]: ISZ[E2]

  @pure def toMSZ: MSZ[E] =
    Contract.Only(
      Ensures(
        Res.size == this.size,
        ∀(this)(e => ∃(Res.indices)(i => Res(i) == e))
      )
    )
}

@ext object MSZ {

  @pure def create[@mut E](size: Z, default: E): MSZ[E] =
    Contract.Only(
      Requires(0 <= size),
      Ensures(
        Res.size == size,
        ∀(Res.indices)(i => Res(i) == default)
      )
    )

}

@ext object ZS {

  def random: ZS = $

  @pure def create(size: Z, default: Z): ZS =
    Contract.Only(
      Requires(0 <= size),
      Ensures(
        Res.size == size,
        ∀(Res.indices)(i => Res(i) == default)
      )
    )
}

@ext object Sireum {

  def initRuntimeLibrary(): Unit = $

  def proc(p: OsProto.Proc, reporter: message.Reporter): OsProto.Proc.Result = $

  def procCheck(p: OsProto.Proc, reporter: message.Reporter): OsProto.Proc.Result = $

  def run(args: ISZ[String]): Z = $

  def runWithInputAndReporter(args: ISZ[String], input: String, reporter: message.Reporter): (Z, String, String) = $

  def runWithReporter(args: ISZ[String], reporter: message.Reporter): (Z, String, String) = $

  def versions: Map[String, String] = $

  def javaVer: String = $

  def scalaVer: String = $

  def scalacPluginVer: String = $

  def version: String = $

  def commitHash: String = $

  def homePathString: String = $

  def javaHomePathString: String = $

  def scalaHomePathString: String = $

  def commitSha: String = $

  def platform: String = $

  def isNative: B = $

  def scalacPluginJarPathString: String = $

  def sireumJarPathString: String = $

}

@ext trait StepId

@ext trait RS {
  @pure def +(other: RS): RS
  @pure def -(other: RS): RS
}