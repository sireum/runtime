// #Sireum
// @formatter:off

/*
 Copyright (c) 2017-2021, Robby, Kansas State University
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

object JSON {

  object Printer {

    @pure def printPresentationEntry(o: Presentation.Entry): ST = {
      o match {
        case o: Presentation.Slide => return printPresentationSlide(o)
        case o: Presentation.Video => return printPresentationVideo(o)
      }
    }

    @pure def printPresentationSlide(o: Presentation.Slide): ST = {
      return printObject(ISZ(
        ("type", st""""Presentation.Slide""""),
        ("path", printString(o.path)),
        ("delay", printZ(o.delay)),
        ("text", printString(o.text))
      ))
    }

    @pure def printPresentationVideo(o: Presentation.Video): ST = {
      return printObject(ISZ(
        ("type", st""""Presentation.Video""""),
        ("path", printString(o.path)),
        ("delay", printZ(o.delay)),
        ("volume", printF64(o.volume)),
        ("start", printF64(o.start)),
        ("end", printF64(o.end)),
        ("textOpt", printOption(T, o.textOpt, printString _))
      ))
    }

    @pure def printPresentation(o: Presentation): ST = {
      return printObject(ISZ(
        ("type", st""""Presentation""""),
        ("name", printString(o.name)),
        ("gap", printZ(o.gap)),
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
      val t = parser.parseObjectTypes(ISZ("Presentation.Slide", "Presentation.Video"))
      t.native match {
        case "Presentation.Slide" => val r = parsePresentationSlideT(T); return r
        case "Presentation.Video" => val r = parsePresentationVideoT(T); return r
        case _ => val r = parsePresentationVideoT(T); return r
      }
    }

    def parsePresentationSlide(): Presentation.Slide = {
      val r = parsePresentationSlideT(F)
      return r
    }

    def parsePresentationSlideT(typeParsed: B): Presentation.Slide = {
      if (!typeParsed) {
        parser.parseObjectType("Presentation.Slide")
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
      return Presentation.Slide(path, delay, text)
    }

    def parsePresentationVideo(): Presentation.Video = {
      val r = parsePresentationVideoT(F)
      return r
    }

    def parsePresentationVideoT(typeParsed: B): Presentation.Video = {
      if (!typeParsed) {
        parser.parseObjectType("Presentation.Video")
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
      parser.parseObjectKey("start")
      val start = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("end")
      val end = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("textOpt")
      val textOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      return Presentation.Video(path, delay, volume, start, end, textOpt)
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
      parser.parseObjectKey("gap")
      val gap = parser.parseZ()
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
      return Presentation(name, gap, textVolume, trailing, granularity, entries)
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

  def fromPresentationSlide(o: Presentation.Slide, isCompact: B): String = {
    val st = Printer.printPresentationSlide(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toPresentationSlide(s: String): Either[Presentation.Slide, Json.ErrorMsg] = {
    def fPresentationSlide(parser: Parser): Presentation.Slide = {
      val r = parser.parsePresentationSlide()
      return r
    }
    val r = to(s, fPresentationSlide _)
    return r
  }

  def fromPresentationVideo(o: Presentation.Video, isCompact: B): String = {
    val st = Printer.printPresentationVideo(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toPresentationVideo(s: String): Either[Presentation.Video, Json.ErrorMsg] = {
    def fPresentationVideo(parser: Parser): Presentation.Video = {
      val r = parser.parsePresentationVideo()
      return r
    }
    val r = to(s, fPresentationVideo _)
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