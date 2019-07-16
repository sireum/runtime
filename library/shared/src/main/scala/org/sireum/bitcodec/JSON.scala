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
        case o: Spec.PredUnion => return printSpecPredUnion(o)
        case o: Spec.PredRepeatWhile => return printSpecPredRepeatWhile(o)
        case o: Spec.PredRepeatUntil => return printSpecPredRepeatUntil(o)
        case o: Spec.GenUnion => return printSpecGenUnion(o)
        case o: Spec.GenRepeat => return printSpecGenRepeat(o)
        case o: Spec.GenRaw => return printSpecGenRaw(o)
        case o: Spec.Pads => return printSpecPads(o)
      }
    }

    @pure def printSpecBase(o: Spec.Base): ST = {
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
        case o: Spec.PredUnion => return printSpecPredUnion(o)
        case o: Spec.GenUnion => return printSpecGenUnion(o)
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
        ("subs", printISZ(F, o.subs, printSpec _))
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

    @pure def printSpecPredUnion(o: Spec.PredUnion): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.PredUnion""""),
        ("name", printString(o.name)),
        ("subs", printISZ(F, o.subs, printSpecPredSpec _))
      ))
    }

    @pure def printSpecPredRepeatWhile(o: Spec.PredRepeatWhile): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.PredRepeatWhile""""),
        ("name", printString(o.name)),
        ("element", printSpecBase(o.element)),
        ("preds", printISZ(F, o.preds, printSpecPred _))
      ))
    }

    @pure def printSpecPredRepeatUntil(o: Spec.PredRepeatUntil): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.PredRepeatUntil""""),
        ("name", printString(o.name)),
        ("element", printSpecBase(o.element)),
        ("preds", printISZ(F, o.preds, printSpecPred _))
      ))
    }

    @pure def printSpecGenUnion(o: Spec.GenUnion): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.GenUnion""""),
        ("name", printString(o.name)),
        ("subs", printISZ(F, o.subs, printSpec _))
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

    @pure def printSpecPredSpec(o: Spec.PredSpec): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.PredSpec""""),
        ("preds", printISZ(F, o.preds, printSpecPred _))
      ))
    }

    @pure def printSpecPred(o: Spec.Pred): ST = {
      o match {
        case o: Spec.Pred.Boolean => printSpecPredBoolean(o)
        case o: Spec.Pred.Bits => printSpecPredBits(o)
        case o: Spec.Pred.Bytes => printSpecPredBytes(o)
        case o: Spec.Pred.Shorts => printSpecPredShorts(o)
        case o: Spec.Pred.Ints => printSpecPredInts(o)
        case o: Spec.Pred.Longs => printSpecPredLongs(o)
        case o: Spec.Pred.Skip => printSpecPredSkip(o)
      }
    }

    @pure def printSpecPredBoolean(o: Spec.Pred.Boolean): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Boolean""""),
        ("value", printB(o.value))
      ))
    }

    @pure def printSpecPredBits(o: Spec.Pred.Bits): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Bits""""),
        ("size", printZ(o.size)),
        ("value", printZ(o.value))
      ))
    }

    @pure def printSpecPredBytes(o: Spec.Pred.Bytes): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Bytes""""),
        ("size", printZ(o.size)),
        ("value", printISZ(T, o.value, printZ _))
      ))
    }

    @pure def printSpecPredShorts(o: Spec.Pred.Shorts): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Shorts""""),
        ("size", printZ(o.size)),
        ("value", printISZ(T, o.value, printZ _))
      ))
    }

    @pure def printSpecPredInts(o: Spec.Pred.Ints): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Ints""""),
        ("size", printZ(o.size)),
        ("value", printISZ(T, o.value, printZ _))
      ))
    }

    @pure def printSpecPredLongs(o: Spec.Pred.Longs): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Longs""""),
        ("size", printZ(o.size)),
        ("value", printISZ(T, o.value, printZ _))
      ))
    }

    @pure def printSpecPredSkip(o: Spec.Pred.Skip): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Skip""""),
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
      val t = parser.parseObjectTypes(ISZ("Spec.Boolean", "Spec.Bits", "Spec.Bytes", "Spec.Shorts", "Spec.Ints", "Spec.Longs", "Spec.Enum", "Spec.Concat", "Spec.Union", "Spec.Repeat", "Spec.Raw", "Spec.PredUnion", "Spec.PredRepeatWhile", "Spec.PredRepeatUtil", "Spec.GenUnion", "Spec.GenRepeat", "Spec.GenRaw", "Spec.Pads"))
      t.native match {
        case "Spec.Boolean" => val r = parseSpecBooleanT(T); return r
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
        case "Spec.PredUnion" => val r = parseSpecPredUnionT(T); return r
        case "Spec.PredRepeatWhile" => val r = parseSpecPredRepeatWhileT(T); return r
        case "Spec.PredRepeatUntil" => val r = parseSpecPredRepeatUntilT(T); return r
        case "Spec.GenUnion" => val r = parseSpecGenUnionT(T); return r
        case "Spec.GenRepeat" => val r = parseSpecGenRepeatT(T); return r
        case "Spec.GenRaw" => val r = parseSpecGenRawT(T); return r
        case "Spec.Pads" => val r = parseSpecPadsT(T); return r
        case _ => val r = parseSpecPadsT(T); return r
      }
    }

    def parseSpecBase(): Spec.Base = {
      val t = parser.parseObjectTypes(ISZ("Spec.Boolean", "Spec.Bits", "Spec.Bytes", "Spec.Shorts", "Spec.Ints", "Spec.Longs", "Spec.Enum", "Spec.Concat", "Spec.Union", "Spec.PredUnion", "Spec.GenUnion"))
      t.native match {
        case "Spec.Boolean" => val r = parseSpecBooleanT(T); return r
        case "Spec.Bits" => val r = parseSpecBitsT(T); return r
        case "Spec.Bytes" => val r = parseSpecBytesT(T); return r
        case "Spec.Shorts" => val r = parseSpecShortsT(T); return r
        case "Spec.Ints" => val r = parseSpecIntsT(T); return r
        case "Spec.Longs" => val r = parseSpecLongsT(T); return r
        case "Spec.Enum" => val r = parseSpecEnumT(T); return r
        case "Spec.Concat" => val r = parseSpecConcatT(T); return r
        case "Spec.Union" => val r = parseSpecUnionT(T); return r
        case "Spec.PredUnion" => val r = parseSpecPredUnionT(T); return r
        case "Spec.GenUnion" => val r = parseSpecGenUnionT(T); return r
        case _ => val r = parseSpecGenUnionT(T); return r
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
      parser.parseObjectKey("subs")
      val subs = parser.parseISZ(parseSpec _)
      parser.parseObjectNext()
      return Spec.Union[Any](name, dependsOn, _ => ???, subs)
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
      val element = parseSpecBase()
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

    def parseSpecPredUnion(): Spec.PredUnion = {
      val r = parseSpecPredUnionT(F)
      return r
    }

    def parseSpecPredUnionT(typeParsed: B): Spec.PredUnion = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.PredUnion")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("subs")
      val subs = parser.parseISZ(parseSpecPredSpec _)
      parser.parseObjectNext()
      return Spec.PredUnion(name, subs)
    }

    def parseSpecPredRepeatWhile(): Spec.PredRepeatWhile = {
      val r = parseSpecPredRepeatWhileT(F)
      return r
    }

    def parseSpecPredRepeatWhileT(typeParsed: B): Spec.PredRepeatWhile = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.PredRepeatWhile")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("element")
      val element = parseSpecBase()
      parser.parseObjectNext()
      parser.parseObjectKey("preds")
      val preds = parser.parseISZ(parseSpecPred _)
      parser.parseObjectNext()
      return Spec.PredRepeatWhile(name, element, preds)
    }

    def parseSpecPredRepeatUntil(): Spec.PredRepeatUntil = {
      val r = parseSpecPredRepeatUntilT(F)
      return r
    }

    def parseSpecPredRepeatUntilT(typeParsed: B): Spec.PredRepeatUntil = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.PredRepeatUntil")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("element")
      val element = parseSpecBase()
      parser.parseObjectNext()
      parser.parseObjectKey("preds")
      val preds = parser.parseISZ(parseSpecPred _)
      parser.parseObjectNext()
      return Spec.PredRepeatUntil(name, element, preds)
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
      parser.parseObjectKey("subs")
      val subs = parser.parseISZ(parseSpec _)
      parser.parseObjectNext()
      return Spec.GenUnion(name, subs)
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
      val element = parseSpecBase()
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

    def parseSpecPredSpec(): Spec.PredSpec = {
      val r = parseSpecPredSpecT(F)
      return r
    }

    def parseSpecPredSpecT(typeParsed: B): Spec.PredSpec = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.PredSpec")
      }
      parser.parseObjectKey("preds")
      val preds = parser.parseISZ(parseSpecPred _)
      parser.parseObjectNext()
      parser.parseObjectKey("spec")
      val spec = parseSpec()
      parser.parseObjectNext()
      return Spec.PredSpec(preds, spec)
    }

    def parseSpecPred(): Spec.Pred = {
      val t = parser.parseObjectTypes(ISZ("Spec.Pred.Boolean", "Spec.Pred.Bits", "Spec.Pred.Bytes", "Spec.Pred.Shorts", "Spec.Pred.Ints", "Spec.Pred.Longs", "Spec.Pred.Skip"))
      t.native match {
        case "Spec.Pred.Boolean" => val r = parseSpecPredBooleanT(T); return r
        case "Spec.Pred.Bits" => val r = parseSpecPredBitsT(T); return r
        case "Spec.Pred.Bytes" => val r = parseSpecPredBytesT(T); return r
        case "Spec.Pred.Shorts" => val r = parseSpecPredShortsT(T); return r
        case "Spec.Pred.Ints" => val r = parseSpecPredIntsT(T); return r
        case "Spec.Pred.Longs" => val r = parseSpecPredLongsT(T); return r
        case "Spec.Pred.Skip" => val r = parseSpecPredSkipT(T); return r
        case _ => val r = parseSpecPredSkipT(T); return r
      }
    }

    def parseSpecPredBoolean(): Spec.Pred.Boolean = {
      val r = parseSpecPredBooleanT(F)
      return r
    }

    def parseSpecPredBooleanT(typeParsed: B): Spec.Pred.Boolean = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Boolean")
      }
      parser.parseObjectKey("value")
      val value = parser.parseB()
      parser.parseObjectNext()
      return Spec.Pred.Boolean(value)
    }

    def parseSpecPredBits(): Spec.Pred.Bits = {
      val r = parseSpecPredBitsT(F)
      return r
    }

    def parseSpecPredBitsT(typeParsed: B): Spec.Pred.Bits = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Bits")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parser.parseZ()
      parser.parseObjectNext()
      return Spec.Pred.Bits(size, value)
    }

    def parseSpecPredBytes(): Spec.Pred.Bytes = {
      val r = parseSpecPredBytesT(F)
      return r
    }

    def parseSpecPredBytesT(typeParsed: B): Spec.Pred.Bytes = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Bytes")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseZ _)
      parser.parseObjectNext()
      return Spec.Pred.Bytes(size, value)
    }

    def parseSpecPredShorts(): Spec.Pred.Shorts = {
      val r = parseSpecPredShortsT(F)
      return r
    }

    def parseSpecPredShortsT(typeParsed: B): Spec.Pred.Shorts = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Shorts")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseZ _)
      parser.parseObjectNext()
      return Spec.Pred.Shorts(size, value)
    }

    def parseSpecPredInts(): Spec.Pred.Ints = {
      val r = parseSpecPredIntsT(F)
      return r
    }

    def parseSpecPredIntsT(typeParsed: B): Spec.Pred.Ints = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Ints")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseZ _)
      parser.parseObjectNext()
      return Spec.Pred.Ints(size, value)
    }

    def parseSpecPredLongs(): Spec.Pred.Longs = {
      val r = parseSpecPredLongsT(F)
      return r
    }

    def parseSpecPredLongsT(typeParsed: B): Spec.Pred.Longs = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Longs")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseZ _)
      parser.parseObjectNext()
      return Spec.Pred.Longs(size, value)
    }

    def parseSpecPredSkip(): Spec.Pred.Skip = {
      val r = parseSpecPredSkipT(F)
      return r
    }

    def parseSpecPredSkipT(typeParsed: B): Spec.Pred.Skip = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Skip")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return Spec.Pred.Skip(size)
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