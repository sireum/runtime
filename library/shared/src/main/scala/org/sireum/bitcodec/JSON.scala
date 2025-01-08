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

package org.sireum.bitcodec

import org.sireum._
import org.sireum.Json.Printer._

object JSON {

  object Printer {

    @pure def printSpec(o: bitcodec.Spec): ST = {
      o match {
        case o: bitcodec.Spec.Boolean => return printSpecBoolean(o)
        case o: bitcodec.Spec.Bits => return printSpecBits(o)
        case o: bitcodec.Spec.BytesImpl => return printSpecBytes(o)
        case o: bitcodec.Spec.ShortsImpl => return printSpecShorts(o)
        case o: bitcodec.Spec.IntsImpl => return printSpecInts(o)
        case o: bitcodec.Spec.LongsImpl => return printSpecLongs(o)
        case o: bitcodec.Spec.FloatsImpl => return printSpecFloats(o)
        case o: bitcodec.Spec.DoublesImpl => return printSpecDoubles(o)
        case o: bitcodec.Spec.Enum => return printSpecEnum(o)
        case o: bitcodec.Spec.ConcatImpl => return printSpecConcat(o)
        case o: bitcodec.Spec.UnionImpl[_] => return printSpecUnion(o)
        case o: bitcodec.Spec.RepeatImpl[_] => return printSpecRepeat(o)
        case o: bitcodec.Spec.RawImpl[_] => return printSpecRaw(o)
        case o: bitcodec.Spec.PredUnionImpl => return printSpecPredUnion(o)
        case o: bitcodec.Spec.PredRepeatWhileImpl => return printSpecPredRepeatWhile(o)
        case o: bitcodec.Spec.PredRepeatUntilImpl => return printSpecPredRepeatUntil(o)
        case o: bitcodec.Spec.GenUnionImpl => return printSpecGenUnion(o)
        case o: bitcodec.Spec.GenRepeatImpl => return printSpecGenRepeat(o)
        case o: bitcodec.Spec.GenRawImpl => return printSpecGenRaw(o)
        case o: bitcodec.Spec.Pads => return printSpecPads(o)
      }
    }

    @pure def printSpecBase(o: bitcodec.Spec.Base): ST = {
      o match {
        case o: bitcodec.Spec.Boolean => return printSpecBoolean(o)
        case o: bitcodec.Spec.Bits => return printSpecBits(o)
        case o: bitcodec.Spec.BytesImpl => return printSpecBytes(o)
        case o: bitcodec.Spec.ShortsImpl => return printSpecShorts(o)
        case o: bitcodec.Spec.IntsImpl => return printSpecInts(o)
        case o: bitcodec.Spec.LongsImpl => return printSpecLongs(o)
        case o: bitcodec.Spec.FloatsImpl => return printSpecFloats(o)
        case o: bitcodec.Spec.DoublesImpl => return printSpecDoubles(o)
        case o: bitcodec.Spec.Enum => return printSpecEnum(o)
        case o: bitcodec.Spec.ConcatImpl => return printSpecConcat(o)
        case o: bitcodec.Spec.UnionImpl[_] => return printSpecUnion(o)
        case o: bitcodec.Spec.PredUnionImpl => return printSpecPredUnion(o)
        case o: bitcodec.Spec.GenUnionImpl => return printSpecGenUnion(o)
      }
    }

    @pure def printSpecBoolean(o: bitcodec.Spec.Boolean): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Boolean""""),
        ("name", printString(o.name))
      ))
    }

    @pure def printSpecBits(o: bitcodec.Spec.Bits): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Bits""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size))
      ))
    }

    @pure def printSpecBytes(o: bitcodec.Spec.BytesImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Bytes""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size)),
        ("signed", printB(o.signed)),
        ("minOpt", printOption(T, o.minOpt, printZ _)),
        ("maxOpt", printOption(T, o.maxOpt, printZ _))
      ))
    }

    @pure def printSpecShorts(o: bitcodec.Spec.ShortsImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Shorts""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size)),
        ("signed", printB(o.signed)),
        ("minOpt", printOption(T, o.minOpt, printZ _)),
        ("maxOpt", printOption(T, o.maxOpt, printZ _))
      ))
    }

    @pure def printSpecInts(o: bitcodec.Spec.IntsImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Ints""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size)),
        ("signed", printB(o.signed)),
        ("minOpt", printOption(T, o.minOpt, printZ _)),
        ("maxOpt", printOption(T, o.maxOpt, printZ _))
      ))
    }

    @pure def printSpecLongs(o: bitcodec.Spec.LongsImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Longs""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size)),
        ("signed", printB(o.signed)),
        ("minOpt", printOption(T, o.minOpt, printZ _)),
        ("maxOpt", printOption(T, o.maxOpt, printZ _))
      ))
    }

    @pure def printSpecFloats(o: bitcodec.Spec.FloatsImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Floats""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size)),
        ("minOpt", printOption(T, o.minOpt, printF32 _)),
        ("maxOpt", printOption(T, o.maxOpt, printF32 _))
      ))
    }

    @pure def printSpecDoubles(o: bitcodec.Spec.DoublesImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Doubles""""),
        ("name", printString(o.name)),
        ("size", printZ(o.size)),
        ("minOpt", printOption(T, o.minOpt, printF64 _)),
        ("maxOpt", printOption(T, o.maxOpt, printF64 _))
      ))
    }

    @pure def printSpecEnum(o: bitcodec.Spec.Enum): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Enum""""),
        ("name", printString(o.name)),
        ("objectName", printString(o.objectName))
      ))
    }

    @pure def printSpecConcat(o: bitcodec.Spec.ConcatImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Concat""""),
        ("name", printString(o.name)),
        ("elements", printISZ(F, o.elements, printSpec _)),
        ("asOpt", printOption(T, o.asOpt, printString _))
      ))
    }

    @pure def printSpecUnion(o: bitcodec.Spec.UnionImpl[_]): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Union""""),
        ("name", printString(o.name)),
        ("dependsOn", printISZ(T, o.dependsOn, printString _)),
        ("choice", printString("")),
        ("subs", printISZ(F, o.subs, printSpec _)),
        ("asOpt", printOption(T, o.asOpt, printString _))
      ))
    }

    @pure def printSpecRepeat(o: bitcodec.Spec.RepeatImpl[_]): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Repeat""""),
        ("name", printString(o.name)),
        ("maxElements", printZ(o.maxElements)),
        ("dependsOn", printISZ(T, o.dependsOn, printString _)),
        ("size", printString("")),
        ("element", printSpec(o.element))
      ))
    }

    @pure def printSpecRaw(o: bitcodec.Spec.RawImpl[_]): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Raw""""),
        ("name", printString(o.name)),
        ("maxSize", printZ(o.maxSize)),
        ("dependsOn", printISZ(T, o.dependsOn, printString _)),
        ("size", printString(""))
      ))
    }

    @pure def printSpecPredUnion(o: bitcodec.Spec.PredUnionImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.PredUnion""""),
        ("name", printString(o.name)),
        ("subs", printISZ(F, o.subs, printSpecPredSpec _)),
        ("asOpt", printOption(T, o.asOpt, printString _))
      ))
    }

    @pure def printSpecPredRepeatWhile(o: bitcodec.Spec.PredRepeatWhileImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.PredRepeatWhile""""),
        ("name", printString(o.name)),
        ("maxElements", printZ(o.maxElements)),
        ("preds", printISZ(F, o.preds, printSpecPred _)),
        ("element", printSpecBase(o.element))
      ))
    }

    @pure def printSpecPredRepeatUntil(o: bitcodec.Spec.PredRepeatUntilImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.PredRepeatUntil""""),
        ("name", printString(o.name)),
        ("maxElements", printZ(o.maxElements)),
        ("preds", printISZ(F, o.preds, printSpecPred _)),
        ("element", printSpecBase(o.element))
      ))
    }

    @pure def printSpecGenUnion(o: bitcodec.Spec.GenUnionImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.GenUnion""""),
        ("name", printString(o.name)),
        ("subs", printISZ(F, o.subs, printSpec _)),
        ("asOpt", printOption(T, o.asOpt, printString _))
      ))
    }

    @pure def printSpecGenRepeat(o: bitcodec.Spec.GenRepeatImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.GenRepeat""""),
        ("name", printString(o.name)),
        ("maxElements", printZ(o.maxElements)),
        ("element", printSpec(o.element))
      ))
    }

    @pure def printSpecGenRaw(o: bitcodec.Spec.GenRawImpl): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.GenRaw""""),
        ("name", printString(o.name)),
        ("maxSize", printZ(o.maxSize)),
      ))
    }

    @pure def printSpecPads(o: bitcodec.Spec.Pads): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pads""""),
        ("size", printZ(o.size))
      ))
    }

    @pure def printSpecPredSpec(o: bitcodec.Spec.PredSpec): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.PredSpec""""),
        ("preds", printISZ(F, o.preds, printSpecPred _)),
        ("spec", printSpec(o.spec))
      ))
    }

    @pure def printSpecPred(o: bitcodec.Spec.Pred): ST = {
      o match {
        case o: bitcodec.Spec.Pred.Boolean => printSpecPredBoolean(o)
        case o: bitcodec.Spec.Pred.Bits => printSpecPredBits(o)
        case o: bitcodec.Spec.Pred.Bytes => printSpecPredBytes(o)
        case o: bitcodec.Spec.Pred.Shorts => printSpecPredShorts(o)
        case o: bitcodec.Spec.Pred.Ints => printSpecPredInts(o)
        case o: bitcodec.Spec.Pred.Longs => printSpecPredLongs(o)
        case o: bitcodec.Spec.Pred.Floats => printSpecPredFloats(o)
        case o: bitcodec.Spec.Pred.Doubles => printSpecPredDoubles(o)
        case o: bitcodec.Spec.Pred.Skip => printSpecPredSkip(o)
        case o: bitcodec.Spec.Pred.Between => printSpecPredBetween(o)
        case o: bitcodec.Spec.Pred.Not => printSpecPredNot(o)
        case o: bitcodec.Spec.Pred.Or => printSpecPredOr(o)
      }
    }

    @pure def printSpecPredBoolean(o: bitcodec.Spec.Pred.Boolean): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Boolean""""),
        ("value", printB(o.value))
      ))
    }

    @pure def printSpecPredBits(o: bitcodec.Spec.Pred.Bits): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Bits""""),
        ("size", printZ(o.size)),
        ("value", printZ(o.value))
      ))
    }

    @pure def printSpecPredBytes(o: bitcodec.Spec.Pred.Bytes): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Bytes""""),
        ("value", printISZ(T, o.value, printZ _))
      ))
    }

    @pure def printSpecPredShorts(o: bitcodec.Spec.Pred.Shorts): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Shorts""""),
        ("value", printISZ(T, o.value, printZ _))
      ))
    }

    @pure def printSpecPredInts(o: bitcodec.Spec.Pred.Ints): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Ints""""),
        ("value", printISZ(T, o.value, printZ _))
      ))
    }

    @pure def printSpecPredLongs(o: bitcodec.Spec.Pred.Longs): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Longs""""),
        ("value", printISZ(T, o.value, printZ _))
      ))
    }

    @pure def printSpecPredFloats(o: bitcodec.Spec.Pred.Floats): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Floats""""),
        ("value", printISZ(T, o.value, printF32 _))
      ))
    }

    @pure def printSpecPredDoubles(o: bitcodec.Spec.Pred.Doubles): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Doubles""""),
        ("value", printISZ(T, o.value, printF64 _))
      ))
    }

    @pure def printSpecPredSkip(o: bitcodec.Spec.Pred.Skip): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Skip""""),
        ("size", printZ(o.size))
      ))
    }

    @pure def printSpecPredBetween(o: bitcodec.Spec.Pred.Between): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Between""""),
        ("size", printZ(o.size)),
        ("lo", printZ(o.lo)),
        ("hi", printZ(o.hi))
      ))
    }

    @pure def printSpecPredNot(o: bitcodec.Spec.Pred.Not): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Not""""),
        ("pred", printSpecPred(o.pred))
      ))
    }

    @pure def printSpecPredOr(o: bitcodec.Spec.Pred.Or): ST = {
      return printObject(ISZ(
        ("type", st""""Spec.Pred.Or""""),
        ("preds", printISZ(F, o.preds, printSpecPred _))
      ))
    }

  }

  @record class Parser(input: String) {
    val parser: Json.Parser = Json.Parser.create(input)

    def errorOpt: Option[Json.ErrorMsg] = {
      return parser.errorOpt
    }

    def parseSpec(): bitcodec.Spec = {
      val t = parser.parseObjectTypes(ISZ("Spec.Boolean", "Spec.Bits", "Spec.Bytes", "Spec.Shorts", "Spec.Ints", "Spec.Longs", "Spec.Floats", "Spec.Doubles", "Spec.Enum", "Spec.Concat", "Spec.Union", "Spec.Repeat", "Spec.Raw", "Spec.PredUnion", "Spec.PredRepeatWhile", "Spec.PredRepeatUntil", "Spec.GenUnion", "Spec.GenRepeat", "Spec.GenRaw", "Spec.Pads"))
      t.native match {
        case "Spec.Boolean" => val r = parseSpecBooleanT(T); return r
        case "Spec.Bits" => val r = parseSpecBitsT(T); return r
        case "Spec.Bytes" => val r = parseSpecBytesT(T); return r
        case "Spec.Shorts" => val r = parseSpecShortsT(T); return r
        case "Spec.Ints" => val r = parseSpecIntsT(T); return r
        case "Spec.Longs" => val r = parseSpecLongsT(T); return r
        case "Spec.Floats" => val r = parseSpecFloatsT(T); return r
        case "Spec.Doubles" => val r = parseSpecDoublesT(T); return r
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

    def parseSpecBase(): bitcodec.Spec.Base = {
      val t = parser.parseObjectTypes(ISZ("Spec.Boolean", "Spec.Bits", "Spec.Bytes", "Spec.Shorts", "Spec.Ints", "Spec.Longs", "Spec.Floats", "Spec.Doubles", "Spec.Enum", "Spec.Concat", "Spec.Union", "Spec.PredUnion", "Spec.GenUnion"))
      t.native match {
        case "Spec.Boolean" => val r = parseSpecBooleanT(T); return r
        case "Spec.Bits" => val r = parseSpecBitsT(T); return r
        case "Spec.Bytes" => val r = parseSpecBytesT(T); return r
        case "Spec.Shorts" => val r = parseSpecShortsT(T); return r
        case "Spec.Ints" => val r = parseSpecIntsT(T); return r
        case "Spec.Longs" => val r = parseSpecLongsT(T); return r
        case "Spec.Floats" => val r = parseSpecFloatsT(T); return r
        case "Spec.Doubles" => val r = parseSpecDoublesT(T); return r
        case "Spec.Enum" => val r = parseSpecEnumT(T); return r
        case "Spec.Concat" => val r = parseSpecConcatT(T); return r
        case "Spec.Union" => val r = parseSpecUnionT(T); return r
        case "Spec.PredUnion" => val r = parseSpecPredUnionT(T); return r
        case "Spec.GenUnion" => val r = parseSpecGenUnionT(T); return r
        case _ => val r = parseSpecGenUnionT(T); return r
      }
    }

    def parseSpecBoolean(): bitcodec.Spec.Boolean = {
      val r = parseSpecBooleanT(F)
      return r
    }

    def parseSpecBooleanT(typeParsed: B): bitcodec.Spec.Boolean = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Boolean")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      return bitcodec.Spec.Boolean(name)
    }

    def parseSpecBits(): bitcodec.Spec.Bits = {
      val r = parseSpecBitsT(F)
      return r
    }

    def parseSpecBitsT(typeParsed: B): bitcodec.Spec.Bits = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Bits")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return bitcodec.Spec.Bits(name, size)
    }

    def parseSpecBytes(): bitcodec.Spec.BytesImpl = {
      val r = parseSpecBytesT(F)
      return r
    }

    def parseSpecBytesT(typeParsed: B): bitcodec.Spec.BytesImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Bytes")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("signed")
      val signed = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("minOpt")
      val minOpt = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      parser.parseObjectKey("maxOpt")
      val maxOpt = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      return bitcodec.Spec.BytesImpl(name, size, signed, minOpt, maxOpt)
    }

    def parseSpecShorts(): bitcodec.Spec.ShortsImpl = {
      val r = parseSpecShortsT(F)
      return r
    }

    def parseSpecShortsT(typeParsed: B): bitcodec.Spec.ShortsImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Shorts")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("signed")
      val signed = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("minOpt")
      val minOpt = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      parser.parseObjectKey("maxOpt")
      val maxOpt = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      return bitcodec.Spec.ShortsImpl(name, size, signed, minOpt, maxOpt)
    }

    def parseSpecInts(): bitcodec.Spec.IntsImpl = {
      val r = parseSpecIntsT(F)
      return r
    }

    def parseSpecIntsT(typeParsed: B): bitcodec.Spec.IntsImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Ints")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("signed")
      val signed = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("minOpt")
      val minOpt = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      parser.parseObjectKey("maxOpt")
      val maxOpt = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      return bitcodec.Spec.IntsImpl(name, size, signed, minOpt, maxOpt)
    }

    def parseSpecLongs(): bitcodec.Spec.LongsImpl = {
      val r = parseSpecLongsT(F)
      return r
    }

    def parseSpecLongsT(typeParsed: B): bitcodec.Spec.LongsImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Longs")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("signed")
      val signed = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("minOpt")
      val minOpt = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      parser.parseObjectKey("maxOpt")
      val maxOpt = parser.parseOption(parser.parseZ _)
      parser.parseObjectNext()
      return bitcodec.Spec.LongsImpl(name, size, signed, minOpt, maxOpt)
    }

    def parseSpecFloats(): bitcodec.Spec.FloatsImpl = {
      val r = parseSpecFloatsT(F)
      return r
    }

    def parseSpecFloatsT(typeParsed: B): bitcodec.Spec.FloatsImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Floats")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("minOpt")
      val minOpt = parser.parseOption(parser.parseF32 _)
      parser.parseObjectNext()
      parser.parseObjectKey("maxOpt")
      val maxOpt = parser.parseOption(parser.parseF32 _)
      parser.parseObjectNext()
      return bitcodec.Spec.FloatsImpl(name, size, minOpt, maxOpt)
    }

    def parseSpecDoubles(): bitcodec.Spec.DoublesImpl = {
      val r = parseSpecDoublesT(F)
      return r
    }

    def parseSpecDoublesT(typeParsed: B): bitcodec.Spec.DoublesImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Doubles")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("minOpt")
      val minOpt = parser.parseOption(parser.parseF64 _)
      parser.parseObjectNext()
      parser.parseObjectKey("maxOpt")
      val maxOpt = parser.parseOption(parser.parseF64 _)
      parser.parseObjectNext()
      return bitcodec.Spec.DoublesImpl(name, size, minOpt, maxOpt)
    }

    def parseSpecEnum(): bitcodec.Spec.Enum = {
      val r = parseSpecEnumT(F)
      return r
    }

    def parseSpecEnumT(typeParsed: B): bitcodec.Spec.Enum = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Enum")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("objectName")
      val objectName = parser.parseString()
      parser.parseObjectNext()
      return bitcodec.Spec.Enum(name, objectName)
    }

    def parseSpecConcat(): bitcodec.Spec.ConcatImpl = {
      val r = parseSpecConcatT(F)
      return r
    }

    def parseSpecConcatT(typeParsed: B): bitcodec.Spec.ConcatImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Concat")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("elements")
      val elements = parser.parseISZ(parseSpec _)
      parser.parseObjectNext()
      parser.parseObjectKey("asOpt")
      val asOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      return bitcodec.Spec.ConcatImpl(name, elements, asOpt)
    }

    def parseSpecUnion(): bitcodec.Spec.UnionImpl[_] = {
      val r = parseSpecUnionT(F)
      return r
    }

    def parseSpecUnionT(typeParsed: B): bitcodec.Spec.UnionImpl[_] = {
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
      parser.parseObjectKey("asOpt")
      val asOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      return bitcodec.Spec.UnionImpl[Any](name, dependsOn, _ => ???, subs, asOpt)
    }

    def parseSpecRepeat(): bitcodec.Spec.RepeatImpl[_] = {
      val r = parseSpecRepeatT(F)
      return r
    }

    def parseSpecRepeatT(typeParsed: B): bitcodec.Spec.RepeatImpl[_] = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Repeat")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("maxElements")
      val maxElements = parser.parseZ()
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
      return bitcodec.Spec.RepeatImpl[Any](name, maxElements, dependsOn, _ => ???, element)
    }

    def parseSpecRaw(): bitcodec.Spec.RawImpl[_] = {
      val r = parseSpecRawT(F)
      return r
    }

    def parseSpecRawT(typeParsed: B): bitcodec.Spec.RawImpl[_] = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Raw")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("maxSize")
      val maxSize = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("dependsOn")
      val dependsOn = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("size")
      parser.parseString()
      parser.parseObjectNext()
      return bitcodec.Spec.RawImpl[Any](name, maxSize, dependsOn, _ => ???)
    }

    def parseSpecPredUnion(): bitcodec.Spec.PredUnionImpl = {
      val r = parseSpecPredUnionT(F)
      return r
    }

    def parseSpecPredUnionT(typeParsed: B): bitcodec.Spec.PredUnionImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.PredUnion")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("subs")
      val subs = parser.parseISZ(parseSpecPredSpec _)
      parser.parseObjectNext()
      parser.parseObjectKey("asOpt")
      val asOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      return bitcodec.Spec.PredUnionImpl(name, subs, asOpt)
    }

    def parseSpecPredRepeatWhile(): bitcodec.Spec.PredRepeatWhileImpl = {
      val r = parseSpecPredRepeatWhileT(F)
      return r
    }

    def parseSpecPredRepeatWhileT(typeParsed: B): bitcodec.Spec.PredRepeatWhileImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.PredRepeatWhile")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("maxElements")
      val maxElements = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("preds")
      val preds = parser.parseISZ(parseSpecPred _)
      parser.parseObjectNext()
      parser.parseObjectKey("element")
      val element = parseSpecBase()
      parser.parseObjectNext()
      return bitcodec.Spec.PredRepeatWhileImpl(name, maxElements, preds, element)
    }

    def parseSpecPredRepeatUntil(): bitcodec.Spec.PredRepeatUntilImpl = {
      val r = parseSpecPredRepeatUntilT(F)
      return r
    }

    def parseSpecPredRepeatUntilT(typeParsed: B): bitcodec.Spec.PredRepeatUntilImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.PredRepeatUntil")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("maxElements")
      val maxElements = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("preds")
      val preds = parser.parseISZ(parseSpecPred _)
      parser.parseObjectNext()
      parser.parseObjectKey("element")
      val element = parseSpecBase()
      parser.parseObjectNext()
      return bitcodec.Spec.PredRepeatUntilImpl(name, maxElements, preds, element)
    }

    def parseSpecGenUnion(): bitcodec.Spec.GenUnionImpl = {
      val r = parseSpecGenUnionT(F)
      return r
    }

    def parseSpecGenUnionT(typeParsed: B): bitcodec.Spec.GenUnionImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.GenUnion")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("subs")
      val subs = parser.parseISZ(parseSpec _)
      parser.parseObjectNext()
      parser.parseObjectKey("asOpt")
      val asOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      return bitcodec.Spec.GenUnionImpl(name, subs, asOpt)
    }

    def parseSpecGenRepeat(): bitcodec.Spec.GenRepeatImpl = {
      val r = parseSpecGenRepeatT(F)
      return r
    }

    def parseSpecGenRepeatT(typeParsed: B): bitcodec.Spec.GenRepeatImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.GenRepeat")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("maxElements")
      val maxElements = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("element")
      val element = parseSpecBase()
      parser.parseObjectNext()
      return bitcodec.Spec.GenRepeatImpl(name, maxElements, element)
    }

    def parseSpecGenRaw(): bitcodec.Spec.GenRawImpl = {
      val r = parseSpecGenRawT(F)
      return r
    }

    def parseSpecGenRawT(typeParsed: B): bitcodec.Spec.GenRawImpl = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.GenRaw")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("maxSize")
      val maxSize = parser.parseZ()
      parser.parseObjectNext()
      return bitcodec.Spec.GenRawImpl(name, maxSize)
    }

    def parseSpecPads(): bitcodec.Spec.Pads = {
      val r = parseSpecPadsT(F)
      return r
    }

    def parseSpecPadsT(typeParsed: B): bitcodec.Spec.Pads = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pads")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return bitcodec.Spec.Pads(size)
    }

    def parseSpecPredSpec(): bitcodec.Spec.PredSpec = {
      val r = parseSpecPredSpecT(F)
      return r
    }

    def parseSpecPredSpecT(typeParsed: B): bitcodec.Spec.PredSpec = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.PredSpec")
      }
      parser.parseObjectKey("preds")
      val preds = parser.parseISZ(parseSpecPred _)
      parser.parseObjectNext()
      parser.parseObjectKey("spec")
      val spec = parseSpec()
      parser.parseObjectNext()
      return bitcodec.Spec.PredSpec(preds, spec)
    }

    def parseSpecPred(): bitcodec.Spec.Pred = {
      val t = parser.parseObjectTypes(ISZ("Spec.Pred.Boolean", "Spec.Pred.Bits", "Spec.Pred.Bytes", "Spec.Pred.Shorts", "Spec.Pred.Ints", "Spec.Pred.Longs", "Spec.Pred.Floats", "Spec.Pred.Doubles", "Spec.Pred.Skip", "Spec.Pred.Between", "Spec.Pred.Not", "Spec.Pred.Or"))
      t.native match {
        case "Spec.Pred.Boolean" => val r = parseSpecPredBooleanT(T); return r
        case "Spec.Pred.Bits" => val r = parseSpecPredBitsT(T); return r
        case "Spec.Pred.Bytes" => val r = parseSpecPredBytesT(T); return r
        case "Spec.Pred.Shorts" => val r = parseSpecPredShortsT(T); return r
        case "Spec.Pred.Ints" => val r = parseSpecPredIntsT(T); return r
        case "Spec.Pred.Longs" => val r = parseSpecPredLongsT(T); return r
        case "Spec.Pred.Floats" => val r = parseSpecPredFloatsT(T); return r
        case "Spec.Pred.Doubles" => val r = parseSpecPredDoublesT(T); return r
        case "Spec.Pred.Skip" => val r = parseSpecPredSkipT(T); return r
        case "Spec.Pred.Between" => val r = parseSpecPredBetweenT(T); return r
        case "Spec.Pred.Not" => val r = parseSpecPredNotT(T); return r
        case "Spec.Pred.Or" => val r = parseSpecPredOrT(T); return r
        case _ => val r = parseSpecPredSkipT(T); return r
      }
    }

    def parseSpecPredBoolean(): bitcodec.Spec.Pred.Boolean = {
      val r = parseSpecPredBooleanT(F)
      return r
    }

    def parseSpecPredBooleanT(typeParsed: B): bitcodec.Spec.Pred.Boolean = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Boolean")
      }
      parser.parseObjectKey("value")
      val value = parser.parseB()
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Boolean(value)
    }

    def parseSpecPredBits(): bitcodec.Spec.Pred.Bits = {
      val r = parseSpecPredBitsT(F)
      return r
    }

    def parseSpecPredBitsT(typeParsed: B): bitcodec.Spec.Pred.Bits = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Bits")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("value")
      val value = parser.parseZ()
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Bits(size, value)
    }

    def parseSpecPredBytes(): bitcodec.Spec.Pred.Bytes = {
      val r = parseSpecPredBytesT(F)
      return r
    }

    def parseSpecPredBytesT(typeParsed: B): bitcodec.Spec.Pred.Bytes = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Bytes")
      }
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseZ _)
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Bytes(value)
    }

    def parseSpecPredShorts(): bitcodec.Spec.Pred.Shorts = {
      val r = parseSpecPredShortsT(F)
      return r
    }

    def parseSpecPredShortsT(typeParsed: B): bitcodec.Spec.Pred.Shorts = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Shorts")
      }
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseZ _)
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Shorts(value)
    }

    def parseSpecPredInts(): bitcodec.Spec.Pred.Ints = {
      val r = parseSpecPredIntsT(F)
      return r
    }

    def parseSpecPredIntsT(typeParsed: B): bitcodec.Spec.Pred.Ints = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Ints")
      }
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseZ _)
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Ints(value)
    }

    def parseSpecPredLongs(): bitcodec.Spec.Pred.Longs = {
      val r = parseSpecPredLongsT(F)
      return r
    }

    def parseSpecPredLongsT(typeParsed: B): bitcodec.Spec.Pred.Longs = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Longs")
      }
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseZ _)
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Longs(value)
    }

    def parseSpecPredFloats(): bitcodec.Spec.Pred.Floats = {
      val r = parseSpecPredFloatsT(F)
      return r
    }

    def parseSpecPredFloatsT(typeParsed: B): bitcodec.Spec.Pred.Floats = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Floats")
      }
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseF32 _)
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Floats(value)
    }

    def parseSpecPredDoubles(): bitcodec.Spec.Pred.Doubles = {
      val r = parseSpecPredDoublesT(F)
      return r
    }

    def parseSpecPredDoublesT(typeParsed: B): bitcodec.Spec.Pred.Doubles = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Doubles")
      }
      parser.parseObjectKey("value")
      val value = parser.parseISZ(parser.parseF64 _)
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Doubles(value)
    }

    def parseSpecPredSkip(): bitcodec.Spec.Pred.Skip = {
      val r = parseSpecPredSkipT(F)
      return r
    }

    def parseSpecPredSkipT(typeParsed: B): bitcodec.Spec.Pred.Skip = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Skip")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Skip(size)
    }

    def parseSpecPredBetween(): bitcodec.Spec.Pred.Between = {
      val r = parseSpecPredBetweenT(F)
      return r
    }

    def parseSpecPredBetweenT(typeParsed: B): bitcodec.Spec.Pred.Between = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Between")
      }
      parser.parseObjectKey("size")
      val size = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("lo")
      val lo = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("hi")
      val hi = parser.parseZ()
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Between(size, lo, hi)
    }

    def parseSpecPredNot(): bitcodec.Spec.Pred.Not = {
      val r = parseSpecPredNotT(F)
      return r
    }

    def parseSpecPredNotT(typeParsed: B): bitcodec.Spec.Pred.Not = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Not")
      }
      parser.parseObjectKey("pred")
      val pred = parseSpecPred()
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Not(pred)
    }

    def parseSpecPredOr(): bitcodec.Spec.Pred.Or = {
      val r = parseSpecPredOrT(F)
      return r
    }

    def parseSpecPredOrT(typeParsed: B): bitcodec.Spec.Pred.Or = {
      if (!typeParsed) {
        parser.parseObjectType("Spec.Pred.Or")
      }
      parser.parseObjectKey("preds")
      val preds = parser.parseISZ(parseSpecPred _)
      parser.parseObjectNext()
      return bitcodec.Spec.Pred.Or(preds)
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

  def fromSpec(o: bitcodec.Spec, isCompact: B): String = {
    val st = Printer.printSpec(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toSpec(s: String): Either[Spec, Json.ErrorMsg] = {
    def fSpec(parser: Parser): bitcodec.Spec = {
      val r = parser.parseSpec()
      return r
    }
    val r = to(s, fSpec _)
    return r
  }
}