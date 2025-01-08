// #Sireum
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
package org.sireum.presentasi

import org.sireum._

object Presentation {

  @datatype trait Entry {
    @pure def path: String
    @pure def delay: Z
  }

  @datatype class SlideEntry(val path: String,
                             val delay: Z,
                             val text: String) extends Entry

  @datatype class VideoEntry(val path: String,
                             val delay: Z,
                             val volume: F64,
                             val rate: F64,
                             val start: F64,
                             val end: F64,
                             val useVideoDuration: B,
                             val textOpt: Option[String]) extends Entry

  type Slide = SlideEntry
  type Video = VideoEntry

  val empty: Presentation = Presentation("Presentasi", ISZ(), 2000, 2000, 250, 1.0, 2000, 1, ISZ())

  val Slide: Slide = SlideEntry("", 0, "")

  val Video: Video = VideoEntry("", 0, 1.0, 1.0, 0.0, 0.0, F, None())

}

@datatype class Presentation(val name: String,
                             val args: ISZ[String],
                             val delay: Z,
                             val textDelay: Z,
                             val vseekDelay: Z,
                             val textVolume: F64,
                             val trailing: Z,
                             val granularity: Z,
                             val entries: ISZ[Presentation.Entry]) {

  @strictpure def +(entry: Presentation.Entry): Presentation =
    Presentation(name, args, delay, textDelay, vseekDelay, textVolume, trailing, granularity, entries :+ entry)

  @strictpure def ++(es: ISZ[Presentation.Entry]): Presentation =
    Presentation(name, args, delay, textDelay, vseekDelay, textVolume, trailing, granularity, entries ++ es)

  def cli(args: ISZ[String]): Unit = {
    val thisL = this
    println(JSON.fromPresentation(thisL(args = args), T))
  }

}
