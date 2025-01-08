// #Sireum
// @formatter:off

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

// This file is auto-generated from CliOpt.scala

package org.sireum.cli

import org.sireum._
import org.sireum.Json.Printer._
import org.sireum.cli.CliOpt

object JSON {

  object Printer {

    @pure def printCliOpt(o: CliOpt): ST = {
      o match {
        case o: CliOpt.Group => return printCliOptGroup(o)
        case o: CliOpt.Tool => return printCliOptTool(o)
      }
    }

    @pure def printCliOptGroup(o: CliOpt.Group): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Group""""),
        ("name", printString(o.name)),
        ("description", printString(o.description)),
        ("header", printString(o.header)),
        ("unlisted", printB(o.unlisted)),
        ("subs", printISZ(F, o.subs, printCliOpt _))
      ))
    }

    @pure def printCliOptTool(o: CliOpt.Tool): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Tool""""),
        ("name", printString(o.name)),
        ("command", printString(o.command)),
        ("description", printString(o.description)),
        ("header", printString(o.header)),
        ("usage", printString(o.usage)),
        ("usageDescOpt", printOption(T, o.usageDescOpt, printString _)),
        ("opts", printISZ(F, o.opts, printCliOptOpt _)),
        ("groups", printISZ(F, o.groups, printCliOptOptGroup _))
      ))
    }

    @pure def printCliOptOptGroup(o: CliOpt.OptGroup): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.OptGroup""""),
        ("name", printString(o.name)),
        ("opts", printISZ(F, o.opts, printCliOptOpt _))
      ))
    }

    @pure def printCliOptOpt(o: CliOpt.Opt): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Opt""""),
        ("name", printString(o.name)),
        ("longKey", printString(o.longKey)),
        ("shortKey", printOption(T, o.shortKey, printC _)),
        ("tpe", printCliOptType(o.tpe)),
        ("description", printString(o.description))
      ))
    }

    @pure def printCliOptType(o: CliOpt.Type): ST = {
      o match {
        case o: CliOpt.Type.Flag => return printCliOptTypeFlag(o)
        case o: CliOpt.Type.Num => return printCliOptTypeNum(o)
        case o: CliOpt.Type.NumFlag => return printCliOptTypeNumFlag(o)
        case o: CliOpt.Type.NumChoice => return printCliOptTypeNumChoice(o)
        case o: CliOpt.Type.Str => return printCliOptTypeStr(o)
        case o: CliOpt.Type.Choice => return printCliOptTypeChoice(o)
        case o: CliOpt.Type.Path => return printCliOptTypePath(o)
      }
    }

    @pure def printCliOptTypeFlag(o: CliOpt.Type.Flag): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Type.Flag""""),
        ("default", printB(o.default))
      ))
    }

    @pure def printCliOptTypeNum(o: CliOpt.Type.Num): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Type.Num""""),
        ("sep", printOption(T, o.sep, printC _)),
        ("default", printZ(o.default)),
        ("min", printOption(T, o.min, printZ _)),
        ("max", printOption(T, o.max, printZ _))
      ))
    }

    @pure def printCliOptTypeNumFlag(o: CliOpt.Type.NumFlag): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Type.NumFlag""""),
        ("default", printZ(o.default)),
        ("min", printOption(T, o.min, printZ _)),
        ("max", printOption(T, o.max, printZ _))
      ))
    }

    @pure def printCliOptTypeNumChoice(o: CliOpt.Type.NumChoice): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Type.NumChoice""""),
        ("sep", printOption(T, o.sep, printC _)),
        ("choices", printISZ(T, o.choices, printZ _))
      ))
    }

    @pure def printCliOptTypeStr(o: CliOpt.Type.Str): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Type.Str""""),
        ("sep", printOption(T, o.sep, printC _)),
        ("default", printOption(T, o.default, printString _))
      ))
    }

    @pure def printCliOptTypeChoice(o: CliOpt.Type.Choice): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Type.Choice""""),
        ("name", printString(o.name)),
        ("sep", printOption(T, o.sep, printC _)),
        ("elements", printISZ(T, o.elements, printString _))
      ))
    }

    @pure def printCliOptTypePath(o: CliOpt.Type.Path): ST = {
      return printObject(ISZ(
        ("type", st""""CliOpt.Type.Path""""),
        ("multiple", printB(o.multiple)),
        ("default", printOption(T, o.default, printString _))
      ))
    }

  }

  @record class Parser(val input: String) {
    val parser: Json.Parser = Json.Parser.create(input)

    def errorOpt: Option[Json.ErrorMsg] = {
      return parser.errorOpt
    }

    def parseCliOpt(): CliOpt = {
      val t = parser.parseObjectTypes(ISZ("CliOpt.Group", "CliOpt.Tool"))
      t.native match {
        case "CliOpt.Group" => val r = parseCliOptGroupT(T); return r
        case "CliOpt.Tool" => val r = parseCliOptToolT(T); return r
        case _ => val r = parseCliOptToolT(T); return r
      }
    }

    def parseCliOptGroup(): CliOpt.Group = {
      val r = parseCliOptGroupT(F)
      return r
    }

    def parseCliOptGroupT(typeParsed: B): CliOpt.Group = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Group")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("description")
      val description = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("header")
      val header = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("unlisted")
      val unlisted = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("subs")
      val subs = parser.parseISZ(parseCliOpt _)
      parser.parseObjectNext()
      return CliOpt.Group(name, description, header, unlisted, subs)
    }

    def parseCliOptTool(): CliOpt.Tool = {
      val r = parseCliOptToolT(F)
      return r
    }

    def parseCliOptToolT(typeParsed: B): CliOpt.Tool = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Tool")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("command")
      val command = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("description")
      val description = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("header")
      val header = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("usage")
      val usage = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("usageDescOpt")
      val usageDescOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("opts")
      val opts = parser.parseISZ(parseCliOptOpt _)
      parser.parseObjectNext()
      parser.parseObjectKey("groups")
      val groups = parser.parseISZ(parseCliOptOptGroup _)
      parser.parseObjectNext()
      return CliOpt.Tool(name, command, description, header, usage, usageDescOpt, opts, groups)
    }

    def parseCliOptOptGroup(): CliOpt.OptGroup = {
      val r = parseCliOptOptGroupT(F)
      return r
    }

    def parseCliOptOptGroupT(typeParsed: B): CliOpt.OptGroup = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.OptGroup")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("opts")
      val opts = parser.parseISZ(parseCliOptOpt _)
      parser.parseObjectNext()
      return CliOpt.OptGroup(name, opts)
    }

    def parseCliOptOpt(): CliOpt.Opt = {
      val r = parseCliOptOptT(F)
      return r
    }

    def parseCliOptOptT(typeParsed: B): CliOpt.Opt = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Opt")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("longKey")
      val longKey = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("shortKey")
      val shortKey = parser.parseOption(parser.parseC _)
      parser.parseObjectNext()
      parser.parseObjectKey("tpe")
      val tpe = parseCliOptType()
      parser.parseObjectNext()
      parser.parseObjectKey("description")
      val description = parser.parseString()
      parser.parseObjectNext()
      return CliOpt.Opt(name, longKey, shortKey, tpe, description)
    }

    def parseCliOptType(): CliOpt.Type = {
      val t = parser.parseObjectTypes(ISZ("CliOpt.Type.Flag", "CliOpt.Type.Num", "CliOpt.Type.NumFlag", "CliOpt.Type.NumChoice", "CliOpt.Type.Str", "CliOpt.Type.Choice", "CliOpt.Type.Path"))
      t.native match {
        case "CliOpt.Type.Flag" => val r = parseCliOptTypeFlagT(T); return r
        case "CliOpt.Type.Num" => val r = parseCliOptTypeNumT(T); return r
        case "CliOpt.Type.NumFlag" => val r = parseCliOptTypeNumFlagT(T); return r
        case "CliOpt.Type.NumChoice" => val r = parseCliOptTypeNumChoiceT(T); return r
        case "CliOpt.Type.Str" => val r = parseCliOptTypeStrT(T); return r
        case "CliOpt.Type.Choice" => val r = parseCliOptTypeChoiceT(T); return r
        case "CliOpt.Type.Path" => val r = parseCliOptTypePathT(T); return r
        case _ => val r = parseCliOptTypePathT(T); return r
      }
    }

    def parseCliOptTypeFlag(): CliOpt.Type.Flag = {
      val r = parseCliOptTypeFlagT(F)
      return r
    }

    def parseCliOptTypeFlagT(typeParsed: B): CliOpt.Type.Flag = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Type.Flag")
      }
      parser.parseObjectKey("default")
      val default = parser.parseB()
      parser.parseObjectNext()
      return CliOpt.Type.Flag(default)
    }

    def parseCliOptTypeNum(): CliOpt.Type.Num = {
      val r = parseCliOptTypeNumT(F)
      return r
    }

    def parseCliOptTypeNumT(typeParsed: B): CliOpt.Type.Num = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Type.Num")
      }
      parser.parseObjectKey("sep")
      val sep = parser.parseOption(parser.parseC _)
      parser.parseObjectNext()
      parser.parseObjectKey("default")
      val default = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("min")
      val min = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      parser.parseObjectKey("max")
      val max = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      return CliOpt.Type.Num(sep, default, min, max)
    }

    def parseCliOptTypeNumFlag(): CliOpt.Type.NumFlag = {
      val r = parseCliOptTypeNumFlagT(F)
      return r
    }

    def parseCliOptTypeNumFlagT(typeParsed: B): CliOpt.Type.NumFlag = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Type.NumFlag")
      }
      parser.parseObjectKey("default")
      val default = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("min")
      val min = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      parser.parseObjectKey("max")
      val max = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      return CliOpt.Type.NumFlag(default, min, max)
    }

    def parseCliOptTypeNumChoice(): CliOpt.Type.NumChoice = {
      val r = parseCliOptTypeNumChoiceT(F)
      return r
    }

    def parseCliOptTypeNumChoiceT(typeParsed: B): CliOpt.Type.NumChoice = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Type.NumChoice")
      }
      parser.parseObjectKey("sep")
      val sep = parser.parseOption(parser.parseC _)
      parser.parseObjectNext()
      parser.parseObjectKey("choices")
      val choices = parser.parseISZ(parser.parseZ _)
      parser.parseObjectNext()
      return CliOpt.Type.NumChoice(sep, choices)
    }

    def parseCliOptTypeStr(): CliOpt.Type.Str = {
      val r = parseCliOptTypeStrT(F)
      return r
    }

    def parseCliOptTypeStrT(typeParsed: B): CliOpt.Type.Str = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Type.Str")
      }
      parser.parseObjectKey("sep")
      val sep = parser.parseOption(parser.parseC _)
      parser.parseObjectNext()
      parser.parseObjectKey("default")
      val default = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      return CliOpt.Type.Str(sep, default)
    }

    def parseCliOptTypeChoice(): CliOpt.Type.Choice = {
      val r = parseCliOptTypeChoiceT(F)
      return r
    }

    def parseCliOptTypeChoiceT(typeParsed: B): CliOpt.Type.Choice = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Type.Choice")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("sep")
      val sep = parser.parseOption(parser.parseC _)
      parser.parseObjectNext()
      parser.parseObjectKey("elements")
      val elements = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return CliOpt.Type.Choice(name, sep, elements)
    }

    def parseCliOptTypePath(): CliOpt.Type.Path = {
      val r = parseCliOptTypePathT(F)
      return r
    }

    def parseCliOptTypePathT(typeParsed: B): CliOpt.Type.Path = {
      if (!typeParsed) {
        parser.parseObjectType("CliOpt.Type.Path")
      }
      parser.parseObjectKey("multiple")
      val multiple = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("default")
      val default = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      return CliOpt.Type.Path(multiple, default)
    }

    def eof(): B = {
      val r = parser.eof()
      return r
    }

  }

  def to[T](s: String, f: Parser => T): Either[T, Json.ErrorMsg] = {
    val parser = Parser(s)
    val r = f(parser)
    parser.eof()
    parser.errorOpt match {
      case Some(e) => return Either.Right(e)
      case _ => return Either.Left(r)
    }
  }

  def fromCliOpt(o: CliOpt, isCompact: B): String = {
    val st = Printer.printCliOpt(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOpt(s: String): Either[CliOpt, Json.ErrorMsg] = {
    def fCliOpt(parser: Parser): CliOpt = {
      val r = parser.parseCliOpt()
      return r
    }
    val r = to(s, fCliOpt _)
    return r
  }

  def fromCliOptGroup(o: CliOpt.Group, isCompact: B): String = {
    val st = Printer.printCliOptGroup(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptGroup(s: String): Either[CliOpt.Group, Json.ErrorMsg] = {
    def fCliOptGroup(parser: Parser): CliOpt.Group = {
      val r = parser.parseCliOptGroup()
      return r
    }
    val r = to(s, fCliOptGroup _)
    return r
  }

  def fromCliOptTool(o: CliOpt.Tool, isCompact: B): String = {
    val st = Printer.printCliOptTool(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptTool(s: String): Either[CliOpt.Tool, Json.ErrorMsg] = {
    def fCliOptTool(parser: Parser): CliOpt.Tool = {
      val r = parser.parseCliOptTool()
      return r
    }
    val r = to(s, fCliOptTool _)
    return r
  }

  def fromCliOptOptGroup(o: CliOpt.OptGroup, isCompact: B): String = {
    val st = Printer.printCliOptOptGroup(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptOptGroup(s: String): Either[CliOpt.OptGroup, Json.ErrorMsg] = {
    def fCliOptOptGroup(parser: Parser): CliOpt.OptGroup = {
      val r = parser.parseCliOptOptGroup()
      return r
    }
    val r = to(s, fCliOptOptGroup _)
    return r
  }

  def fromCliOptOpt(o: CliOpt.Opt, isCompact: B): String = {
    val st = Printer.printCliOptOpt(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptOpt(s: String): Either[CliOpt.Opt, Json.ErrorMsg] = {
    def fCliOptOpt(parser: Parser): CliOpt.Opt = {
      val r = parser.parseCliOptOpt()
      return r
    }
    val r = to(s, fCliOptOpt _)
    return r
  }

  def fromCliOptType(o: CliOpt.Type, isCompact: B): String = {
    val st = Printer.printCliOptType(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptType(s: String): Either[CliOpt.Type, Json.ErrorMsg] = {
    def fCliOptType(parser: Parser): CliOpt.Type = {
      val r = parser.parseCliOptType()
      return r
    }
    val r = to(s, fCliOptType _)
    return r
  }

  def fromCliOptTypeFlag(o: CliOpt.Type.Flag, isCompact: B): String = {
    val st = Printer.printCliOptTypeFlag(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptTypeFlag(s: String): Either[CliOpt.Type.Flag, Json.ErrorMsg] = {
    def fCliOptTypeFlag(parser: Parser): CliOpt.Type.Flag = {
      val r = parser.parseCliOptTypeFlag()
      return r
    }
    val r = to(s, fCliOptTypeFlag _)
    return r
  }

  def fromCliOptTypeNum(o: CliOpt.Type.Num, isCompact: B): String = {
    val st = Printer.printCliOptTypeNum(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptTypeNum(s: String): Either[CliOpt.Type.Num, Json.ErrorMsg] = {
    def fCliOptTypeNum(parser: Parser): CliOpt.Type.Num = {
      val r = parser.parseCliOptTypeNum()
      return r
    }
    val r = to(s, fCliOptTypeNum _)
    return r
  }

  def fromCliOptTypeNumFlag(o: CliOpt.Type.NumFlag, isCompact: B): String = {
    val st = Printer.printCliOptTypeNumFlag(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptTypeNumFlag(s: String): Either[CliOpt.Type.NumFlag, Json.ErrorMsg] = {
    def fCliOptTypeNumFlag(parser: Parser): CliOpt.Type.NumFlag = {
      val r = parser.parseCliOptTypeNumFlag()
      return r
    }
    val r = to(s, fCliOptTypeNumFlag _)
    return r
  }

  def fromCliOptTypeNumChoice(o: CliOpt.Type.NumChoice, isCompact: B): String = {
    val st = Printer.printCliOptTypeNumChoice(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptTypeNumChoice(s: String): Either[CliOpt.Type.NumChoice, Json.ErrorMsg] = {
    def fCliOptTypeNumChoice(parser: Parser): CliOpt.Type.NumChoice = {
      val r = parser.parseCliOptTypeNumChoice()
      return r
    }
    val r = to(s, fCliOptTypeNumChoice _)
    return r
  }

  def fromCliOptTypeStr(o: CliOpt.Type.Str, isCompact: B): String = {
    val st = Printer.printCliOptTypeStr(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptTypeStr(s: String): Either[CliOpt.Type.Str, Json.ErrorMsg] = {
    def fCliOptTypeStr(parser: Parser): CliOpt.Type.Str = {
      val r = parser.parseCliOptTypeStr()
      return r
    }
    val r = to(s, fCliOptTypeStr _)
    return r
  }

  def fromCliOptTypeChoice(o: CliOpt.Type.Choice, isCompact: B): String = {
    val st = Printer.printCliOptTypeChoice(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptTypeChoice(s: String): Either[CliOpt.Type.Choice, Json.ErrorMsg] = {
    def fCliOptTypeChoice(parser: Parser): CliOpt.Type.Choice = {
      val r = parser.parseCliOptTypeChoice()
      return r
    }
    val r = to(s, fCliOptTypeChoice _)
    return r
  }

  def fromCliOptTypePath(o: CliOpt.Type.Path, isCompact: B): String = {
    val st = Printer.printCliOptTypePath(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCliOptTypePath(s: String): Either[CliOpt.Type.Path, Json.ErrorMsg] = {
    def fCliOptTypePath(parser: Parser): CliOpt.Type.Path = {
      val r = parser.parseCliOptTypePath()
      return r
    }
    val r = to(s, fCliOptTypePath _)
    return r
  }

}