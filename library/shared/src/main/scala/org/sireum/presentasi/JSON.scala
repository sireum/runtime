// #Sireum
// @formatter:off

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

// This file is auto-generated from Presentation.scala

package org.sireum.presentasi

import org.sireum._
import org.sireum.Json.Printer._
import org.sireum.presentasi.Presentation

object JSON {

  object Printer {

    @pure def printPresentationEntry(o: Presentation.Entry): ST = {
      o match {
        case o: Presentation.SlideEntry => return printPresentationSlideEntry(o)
        case o: Presentation.VideoEntry => return printPresentationVideoEntry(o)
      }
    }

    @pure def printPresentationSlideEntry(o: Presentation.SlideEntry): ST = {
      return printObject(ISZ(
        ("type", st""""Presentation.SlideEntry""""),
        ("path", printString(o.path)),
        ("delay", printZ(o.delay)),
        ("text", printString(o.text))
      ))
    }

    @pure def printPresentationVideoEntry(o: Presentation.VideoEntry): ST = {
      return printObject(ISZ(
        ("type", st""""Presentation.VideoEntry""""),
        ("path", printString(o.path)),
        ("delay", printZ(o.delay)),
        ("volume", printF64(o.volume)),
        ("rate", printF64(o.rate)),
        ("start", printF64(o.start)),
        ("end", printF64(o.end)),
        ("useVideoDuration", printB(o.useVideoDuration)),
        ("textOpt", printOption(T, o.textOpt, printString _))
      ))
    }

    @pure def printPresentation(o: Presentation): ST = {
      return printObject(ISZ(
        ("type", st""""Presentation""""),
        ("name", printString(o.name)),
        ("args", printISZ(T, o.args, printString _)),
        ("delay", printZ(o.delay)),
        ("textDelay", printZ(o.textDelay)),
        ("vseekDelay", printZ(o.vseekDelay)),
        ("textVolume", printF64(o.textVolume)),
        ("trailing", printZ(o.trailing)),
        ("granularity", printZ(o.granularity)),
        ("entries", printISZ(F, o.entries, printPresentationEntry _))
      ))
    }

  }

  @record class Parser(val input: String) {
    val parser: Json.Parser = Json.Parser.create(input)

    def errorOpt: Option[Json.ErrorMsg] = {
      return parser.errorOpt
    }

    def parsePresentationEntry(): Presentation.Entry = {
      val t = parser.parseObjectTypes(ISZ("Presentation.SlideEntry", "Presentation.VideoEntry"))
      t.native match {
        case "Presentation.SlideEntry" => val r = parsePresentationSlideEntryT(T); return r
        case "Presentation.VideoEntry" => val r = parsePresentationVideoEntryT(T); return r
        case _ => val r = parsePresentationVideoEntryT(T); return r
      }
    }

    def parsePresentationSlideEntry(): Presentation.SlideEntry = {
      val r = parsePresentationSlideEntryT(F)
      return r
    }

    def parsePresentationSlideEntryT(typeParsed: B): Presentation.SlideEntry = {
      if (!typeParsed) {
        parser.parseObjectType("Presentation.SlideEntry")
      }
      parser.parseObjectKey("path")
      val path = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("delay")
      val delay = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("text")
      val text = parser.parseString()
      parser.parseObjectNext()
      return Presentation.SlideEntry(path, delay, text)
    }

    def parsePresentationVideoEntry(): Presentation.VideoEntry = {
      val r = parsePresentationVideoEntryT(F)
      return r
    }

    def parsePresentationVideoEntryT(typeParsed: B): Presentation.VideoEntry = {
      if (!typeParsed) {
        parser.parseObjectType("Presentation.VideoEntry")
      }
      parser.parseObjectKey("path")
      val path = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("delay")
      val delay = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("volume")
      val volume = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("rate")
      val rate = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("start")
      val start = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("end")
      val end = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("useVideoDuration")
      val useVideoDuration = parser.parseB()
      parser.parseObjectNext()
      parser.parseObjectKey("textOpt")
      val textOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      return Presentation.VideoEntry(path, delay, volume, rate, start, end, useVideoDuration, textOpt)
    }

    def parsePresentation(): Presentation = {
      val r = parsePresentationT(F)
      return r
    }

    def parsePresentationT(typeParsed: B): Presentation = {
      if (!typeParsed) {
        parser.parseObjectType("Presentation")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("args")
      val args = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("delay")
      val delay = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("textDelay")
      val textDelay = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("vseekDelay")
      val vseekDelay = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("textVolume")
      val textVolume = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("trailing")
      val trailing = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("granularity")
      val granularity = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("entries")
      val entries = parser.parseISZ(parsePresentationEntry _)
      parser.parseObjectNext()
      return Presentation(name, args, delay, textDelay, vseekDelay, textVolume, trailing, granularity, entries)
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

  def fromPresentationEntry(o: Presentation.Entry, isCompact: B): String = {
    val st = Printer.printPresentationEntry(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toPresentationEntry(s: String): Either[Presentation.Entry, Json.ErrorMsg] = {
    def fPresentationEntry(parser: Parser): Presentation.Entry = {
      val r = parser.parsePresentationEntry()
      return r
    }
    val r = to(s, fPresentationEntry _)
    return r
  }

  def fromPresentationSlideEntry(o: Presentation.SlideEntry, isCompact: B): String = {
    val st = Printer.printPresentationSlideEntry(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toPresentationSlideEntry(s: String): Either[Presentation.SlideEntry, Json.ErrorMsg] = {
    def fPresentationSlideEntry(parser: Parser): Presentation.SlideEntry = {
      val r = parser.parsePresentationSlideEntry()
      return r
    }
    val r = to(s, fPresentationSlideEntry _)
    return r
  }

  def fromPresentationVideoEntry(o: Presentation.VideoEntry, isCompact: B): String = {
    val st = Printer.printPresentationVideoEntry(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toPresentationVideoEntry(s: String): Either[Presentation.VideoEntry, Json.ErrorMsg] = {
    def fPresentationVideoEntry(parser: Parser): Presentation.VideoEntry = {
      val r = parser.parsePresentationVideoEntry()
      return r
    }
    val r = to(s, fPresentationVideoEntry _)
    return r
  }

  def fromPresentation(o: Presentation, isCompact: B): String = {
    val st = Printer.printPresentation(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toPresentation(s: String): Either[Presentation, Json.ErrorMsg] = {
    def fPresentation(parser: Parser): Presentation = {
      val r = parser.parsePresentation()
      return r
    }
    val r = to(s, fPresentation _)
    return r
  }

}