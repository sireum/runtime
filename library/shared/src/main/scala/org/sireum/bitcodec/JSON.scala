// @formatter:off

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

package org.sireum.bitcodec

import org.sireum._
import org.sireum.Json.Printer._

object JSON {

  object Printer {

    @pure def printSpec(o: Spec): ST = {
      o match {
        case o: Spec.Boolean => return printSpecBoolean(o)
        case o: Spec.Bits => return printSpecBits(o)
        case o: Spec.Bytes => return printSpecBytes(o)
        case o: Spec.Shorts => return printSpecShorts(o)
        case o: Spec.Ints => return printSpecInts(o)
        case o: Spec.Longs => return printSpecLongs(o)
        case o: Spec.Enum => return printSpecEnum(o)
        case o: Spec.Concat => return printSpecConcat(o)
        case o: Spec.Union[_] => return printSpecUnion(o)
        case o: Spec.Repeat[_] => return printSpecRepeat(o)
        case o: Spec.Raw[_] => return printSpecRaw(o)
        case o: Spec.GenUnion => return printSpecGenUnion(o)
        case o: Spec.GenRepeat => return printSpecGenRepeat(o)
        case o: Spec.GenRaw => return printSpecGenRaw(o)
        case o: Spec.Pads => return printSpecPads(o)
      }
    }

    @pure def printSpecBoolean(o: Spec.Boolean): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Boolean""""),
        ("name", printString(o.name))
      ))
    }

    @pure def printSpecBits(o: Spec.Bits): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Bits""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size))
      ))
    }

    @pure def printSpecBytes(o: Spec.Bytes): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Bytes""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size))
      ))
    }

    @pure def printSpecShorts(o: Spec.Shorts): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Shorts""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size))
      ))
    }

    @pure def printSpecInts(o: Spec.Ints): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Ints""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size))
      ))
    }

    @pure def printSpecLongs(o: Spec.Longs): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Longs""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size))
      ))
    }

    @pure def printSpecEnum(o: Spec.Enum): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Enum""""),
        ("name", printString(o.name)),
        ("objectName", printString(o.objectName))
      ))
    }

    @pure def printSpecConcat(o: Spec.Concat): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Concat""""),
        ("name", printString(o.name)),
        ("elements", printISZ(F, o.elements, printSpec _))
      ))
    }

    @pure def printSpecUnion(o: Spec.Union[_]): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Union""""),
        ("name", printString(o.name)),
        ("dependsOn", printISZ(T, o.dependsOn, printString _)),
        ("choice", printString("")),
        ("elements", printISZ(F, o.elements, printSpec _))
      ))
    }

    @pure def printSpecRepeat(o: Spec.Repeat[_]): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Repeat""""),
        ("name", printString(o.name)),
        ("dependsOn", printISZ(T, o.dependsOn, printString _)),
        ("size", printString("")),
        ("element", printSpec(o.element))
      ))
    }

    @pure def printSpecRaw(o: Spec.Raw[_]): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Raw""""),
        ("name", printString(o.name)),
        ("dependsOn", printISZ(T, o.dependsOn, printString _)),
        ("size", printString(""))
      ))
    }

    @pure def printSpecGenUnion(o: Spec.GenUnion): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.GenUnion""""),
        ("name", printString(o.name)),
        ("elements", printISZ(F, o.elements, printSpec _))
      ))
    }

    @pure def printSpecGenRepeat(o: Spec.GenRepeat): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.GenRepeat""""),
        ("name", printString(o.name)),
        ("element", printSpec(o.element))
      ))
    }

    @pure def printSpecGenRaw(o: Spec.GenRaw): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.GenRaw""""),
        ("name", printString(o.name))
      ))
    }

    @pure def printSpecPads(o: Spec.Pads): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pads""""),
        ("size", printZ(o.size))
      ))
    }

  }

  @record class Parser(input: String) {
    val parser: Json.Parser = Json.Parser.create(input)

    def errorOpt: Option[Json.ErrorMsg] = {
      return parser.errorOpt
    }

    def parseSpec(): Spec = {
      val t = parser.parseObjectTypes(ISZ("Spec.Bits", "Spec.Bytes", "Spec.Shorts", "Spec.Ints", "Spec.Longs", "Spec.Enum", "Spec.Concat", "Spec.Union", "Spec.Repeat", "Spec.Raw", "Spec.GenUnion", "Spec.GenRepeat", "Spec.GenRaw"))
      t.native match {
        case "Spec.Bits" => val r = parseSpecBitsT(T); return r
        case "Spec.Bytes" => val r = parseSpecBytesT(T); return r
        case "Spec.Shorts" => val r = parseSpecShortsT(T); return r
        case "Spec.Ints" => val r = parseSpecIntsT(T); return r
        case "Spec.Longs" => val r = parseSpecLongsT(T); return r
        case "Spec.Enum" => val r = parseSpecEnumT(T); return r
        case "Spec.Concat" => val r = parseSpecConcatT(T); return r
        case "Spec.Union" => val r = parseSpecUnionT(T); return r
        case "Spec.Repeat" => val r = parseSpecRepeatT(T); return r
        case "Spec.Raw" => val r = parseSpecRawT(T); return r
        case "Spec.GenUnion" => val r = parseSpecGenUnionT(T); return r
        case "Spec.GenRepeat" => val r = parseSpecGenRepeatT(T); return r
        case "Spec.GenRaw" => val r = parseSpecGenRawT(T); return r
        case "Spec.Pads" => val r = parseSpecPadsT(T); return r
        case _ => val r = parseSpecGenRawT(T); return r
      }
    }

    def parseSpecBoolean(): Spec.Boolean = {
      val r = parseSpecBooleanT(F)
      return r
    }

    def parseSpecBooleanT(typeParsed: B): Spec.Boolean = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Boolean")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      return Spec.Boolean(name)
    }

    def parseSpecBits(): Spec.Bits = {
      val r = parseSpecBitsT(F)
      return r
    }

    def parseSpecBitsT(typeParsed: B): Spec.Bits = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Bits")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return Spec.Bits(name, size)
    }

    def parseSpecBytes(): Spec.Bytes = {
      val r = parseSpecBytesT(F)
      return r
    }

    def parseSpecBytesT(typeParsed: B): Spec.Bytes = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Bytes")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return Spec.Bytes(name, size)
    }

    def parseSpecShorts(): Spec.Shorts = {
      val r = parseSpecShortsT(F)
      return r
    }

    def parseSpecShortsT(typeParsed: B): Spec.Shorts = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Shorts")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return Spec.Shorts(name, size)
    }

    def parseSpecInts(): Spec.Ints = {
      val r = parseSpecIntsT(F)
      return r
    }

    def parseSpecIntsT(typeParsed: B): Spec.Ints = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Ints")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return Spec.Ints(name, size)
    }

    def parseSpecLongs(): Spec.Longs = {
      val r = parseSpecLongsT(F)
      return r
    }

    def parseSpecLongsT(typeParsed: B): Spec.Longs = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Longs")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return Spec.Longs(name, size)
    }

    def parseSpecEnum(): Spec.Enum = {
      val r = parseSpecEnumT(F)
      return r
    }

    def parseSpecEnumT(typeParsed: B): Spec.Enum = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Enum")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("objectName")
      val objectName = parser.parseString()
      parser.parseObjectNext()
      return Spec.Enum(name, objectName)
    }

    def parseSpecConcat(): Spec.Concat = {
      val r = parseSpecConcatT(F)
      return r
    }

    def parseSpecConcatT(typeParsed: B): Spec.Concat = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Concat")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("elements")
      val elements = parser.parseISZ(parseSpec _)
      parser.parseObjectNext()
      return Spec.Concat(name, elements)
    }

    def parseSpecUnion(): Spec.Union[_] = {
      val r = parseSpecUnionT(F)
      return r
    }

    def parseSpecUnionT(typeParsed: B): Spec.Union[_] = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Union")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("dependsOn")
      val dependsOn = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("choice")
      parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("elements")
      val elements = parser.parseISZ(parseSpec _)
      parser.parseObjectNext()
      return Spec.Union[Any](name, dependsOn, _ => ???, elements)
    }

    def parseSpecRepeat(): Spec.Repeat[_] = {
      val r = parseSpecRepeatT(F)
      return r
    }

    def parseSpecRepeatT(typeParsed: B): Spec.Repeat[_] = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Repeat")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("dependsOn")
      val dependsOn = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("element")
      val element = parseSpec()
      parser.parseObjectNext()
      return Spec.Repeat[Any](name, dependsOn, _ => ???, element)
    }

    def parseSpecRaw(): Spec.Raw[_] = {
      val r = parseSpecRawT(F)
      return r
    }

    def parseSpecRawT(typeParsed: B): Spec.Raw[_] = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Raw")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("dependsOn")
      val dependsOn = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      parser.parseString()
      parser.parseObjectNext()
      return Spec.Raw[Any](name, dependsOn, _ => ???)
    }

    def parseSpecGenUnion(): Spec.GenUnion = {
      val r = parseSpecGenUnionT(F)
      return r
    }

    def parseSpecGenUnionT(typeParsed: B): Spec.GenUnion = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.GenUnion")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("elements")
      val elements = parser.parseISZ(parseSpec _)
      parser.parseObjectNext()
      return Spec.GenUnion(name, elements)
    }

    def parseSpecGenRepeat(): Spec.GenRepeat = {
      val r = parseSpecGenRepeatT(F)
      return r
    }

    def parseSpecGenRepeatT(typeParsed: B): Spec.GenRepeat = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.GenRepeat")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("element")
      val element = parseSpec()
      parser.parseObjectNext()
      return Spec.GenRepeat(name, element)
    }

    def parseSpecGenRaw(): Spec.GenRaw = {
      val r = parseSpecGenRawT(F)
      return r
    }

    def parseSpecGenRawT(typeParsed: B): Spec.GenRaw = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.GenRaw")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      return Spec.GenRaw(name)
    }

    def parseSpecPads(): Spec.Pads = {
      val r = parseSpecPadsT(F)
      return r
    }

    def parseSpecPadsT(typeParsed: B): Spec.Pads = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pads")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return Spec.Pads(size)
    }

    def eof(): B = {
      val r = parser.eof()
      return r
    }

  }

  def to[T](s: String, f: Parser => T): Either[T, Json.ErrorMsg] = {
    val parser = new Parser(s)
    val r = f(parser)
    parser.eof()
    parser.errorOpt match {
      case Some(e) => return Either.Right(e)
      case _ => return Either.Left(r)
    }
  }

  def fromSpec(o: Spec, isCompact: B): String = {
    val st = Printer.printSpec(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toSpec(s: String): Either[Spec, Json.ErrorMsg] = {
    def fSpec(parser: Parser): Spec = {
      val r = parser.parseSpec()
      return r
    }
    val r = to(s, fSpec _)
    return r
  }
}