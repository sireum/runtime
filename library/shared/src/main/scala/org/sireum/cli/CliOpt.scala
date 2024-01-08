// #Sireum
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

package org.sireum.cli

import org.sireum._

@datatype trait CliOpt {
  @pure def name: String

  @pure def command: String

  @pure def description: String

  @pure def header: String

  @pure def unlisted: B
}

object CliOpt {

  val tqs: String = "\"\"\""

  @datatype class Group(
    val name: String,
    val description: String,
    val header: String,
    val unlisted: B,
    val subs: ISZ[CliOpt]
  ) extends CliOpt {

    @pure def command: String = {
      return name
    }
  }

  @datatype class Tool(
    val name: String,
    val command: String,
    val description: String,
    val header: String,
    val usage: String,
    val usageDescOpt: Option[String],
    val opts: ISZ[Opt],
    val groups: ISZ[OptGroup]
  ) extends CliOpt {
    override def unlisted: B = {
      return F
    }
  }

  @datatype class OptGroup(val name: String, val opts: ISZ[Opt])

  @datatype class Opt(val name: String, val longKey: String, val shortKey: Option[C], val tpe: Type, val description: String)

  @datatype trait Type

  object Type {

    @datatype class Flag(val default: B) extends Type

    @datatype class Num(val sep: Option[C], val default: Z, val min: Option[Z], val max: Option[Z]) extends Type

    @datatype class NumFlag(val default: Z, val min: Option[Z], val max: Option[Z]) extends Type

    @datatype class NumChoice(val sep: Option[C], val choices: ISZ[Z]) extends Type

    @datatype class Str(val sep: Option[C], val default: Option[String]) extends Type

    @datatype class Choice(val name: String, val sep: Option[C], val elements: ISZ[String]) extends Type

    @datatype class Path(val multiple: B, val default: Option[String]) extends Type

  }

}
