// #Sireum
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

import org.sireum.S32._
import org.sireum.U64._

@sig trait Indexable[T] {
  @pure def at(i: Z): T
  @pure def has(i: Z): B
  @strictpure def atS32(i: S32): T = at(conversions.S32.toZ(i))
  @strictpure def hasS32(i: S32): B = has(conversions.S32.toZ(i))
}

object Indexable {

  @ext("Indexable_Ext") object Ext {
    @pure def fromString(uriOpt: Option[String], s: String): Indexable.PosC = $
  }

  @sig trait Pos[T] extends Indexable[T] {
    @pure def posOpt(offset: Z, length: Z): Option[message.Position]
    @pure def posOptS32(offset: S32, length: S32): Option[message.Position] = {
      return posOpt(conversions.S32.toZ(offset), conversions.S32.toZ(length))
    }
  }

  @datatype class Isz[T](val is: IS[S32, T]) extends Indexable[T] {
    @strictpure override def at(i: Z): T = is(conversions.Z.toS32(i))
    @strictpure override def has(i: Z): B = i < is.size
    @strictpure override def atS32(i: S32): T = is.atS32(i)
    @strictpure override def hasS32(i: S32): B = i < is.sizeS32
  }

  @datatype class IszZ[T](val is: ISZ[T]) extends Indexable[T] {
    @strictpure override def at(i: Z): T = is(i)
    @strictpure override def has(i: Z): B = i < is.size
  }

  @datatype class IszDocInfo[T](val is: ISZ[T], val info: message.DocInfo) extends Indexable.Pos[T] {
    @strictpure override def at(i: Z): T = is(i)
    @strictpure override def has(i: Z): B = i < is.size
    @pure def posOpt(offset: Z, length: Z): Option[message.Position] = {
      return Some(message.PosInfo(info, (conversions.Z.toU64(offset) << u64"32") | conversions.Z.toU64(length)))
    }
  }

  @sig trait PosC extends Pos[C] {
    @pure def substring(start: Z, until: Z): String
    @pure def substringS32(start: S32, until: S32): String = {
      return substring(conversions.S32.toZ(start), conversions.S32.toZ(until))
    }
  }

  @datatype class IszDocInfoC(val is: ISZ[C], val info: message.DocInfo) extends Indexable.PosC {
    @strictpure override def at(i: Z): C = is(i)
    @strictpure override def has(i: Z): B = i < is.size
    @pure override def posOpt(offset: Z, length: Z): Option[message.Position] = {
      return Some(message.PosInfo(info, (conversions.Z.toU64(offset) << u64"32") | conversions.Z.toU64(length)))
    }
    @pure override def substring(start: Z, until: Z): String = {
      return ops.StringOps.Ext.nativeSubstring(is, start, until)
    }
  }

  @strictpure def fromIs[T](is: IS[S32, T]): Indexable[T] = Isz(is)

  @strictpure def fromIsz[T](is: ISZ[T]): Indexable[T] = IszZ(is)

  @strictpure def fromIszDocInfo[T](is: ISZ[T], info: message.DocInfo): Indexable.Pos[T] = IszDocInfo(is, info)

  @strictpure def fromIszDocInfoC(is: ISZ[C], info: message.DocInfo): Indexable.PosC = IszDocInfoC(is, info)
}
