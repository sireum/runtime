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

object Indexable_Ext {

  def fromString(uriOpt: Option[String], s: String): Indexable.PosC = {
    val codepoints = s.value.codePoints.toArray
    val size = codepoints.length
    // Two-pass: count newlines, then fill pre-sized array — avoids O(n²) ISZ :+ cloning
    var nlCount = 0
    var i = 0
    while (i < size) {
      if (codepoints(i) == '\n') nlCount += 1
      i += 1
    }
    val rawArr = new Array[scala.Int](nlCount + 1)
    rawArr(0) = 0
    var li = 1
    i = 0
    while (i < size) {
      if (codepoints(i) == '\n') {
        rawArr(li) = i + 1
        li += 1
      }
      i += 1
    }
    val lineOffsets = new IS[Z, U32](Z, rawArr, Z.MP(nlCount + 1), U32.fromZ(0).boxer)
    val info = message.DocInfo(uriOpt, lineOffsets)
    new Indexable.PosC {
      override def at(i: Z): C = C(codepoints(i.toInt))

      def at(i: scala.Long): C = C(codepoints(i.toInt))

      override def atS32(i: S32): C = C(codepoints(i.value))

      override def has(i: Z): B = 0 <= i && i < size

      def has(i: scala.Long): scala.Boolean = 0L <= i && i < size

      override def hasS32(i: S32): B = i.value >= 0 && i.value < size

      override def posOpt(offset: Z, length: Z): Option[message.Position] = {
        Some(message.PosInfo(info, (conversions.Z.toU64(offset) << U64.fromZ(32)) | conversions.Z.toU64(length)))
      }

      override def posOptS32(offset: S32, length: S32): Option[message.Position] = {
        Some(message.PosInfo(info, (conversions.Z.toU64(conversions.S32.toZ(offset)) << U64.fromZ(32)) | conversions.Z.toU64(conversions.S32.toZ(length))))
      }

      override def substring(start: Z, until: Z): String = {
        val s = start.toInt
        val u = until.toInt
        if (u - s <= 0) return ""
        new Predef.String(codepoints, s, u - s)
      }

      override def substringS32(start: S32, until: S32): String = {
        val s = start.value
        val u = until.value
        if (u - s <= 0) return ""
        new Predef.String(codepoints, s, u - s)
      }

      override def string: String = s"Indexable.PosC(${uriOpt})"
    }
  }
}
