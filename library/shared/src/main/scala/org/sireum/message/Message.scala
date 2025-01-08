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

package org.sireum.message

import org.sireum._
import org.sireum.U64._

@enum object Level {
  "InternalError"
  "Error"
  "Warning"
  "Info"
}

@datatype class Message(val level: Level.Type, val posOpt: Option[Position], val kind: String, val text: String) {

  def isInternalError: B = {
    return level == Level.InternalError
  }

  def isError: B = {
    return level == Level.Error
  }

  def isWarning: B = {
    return level == Level.Warning
  }

  def isInfo: B = {
    return level == Level.Info
  }

  def fileUriOpt: Option[String] = {
    posOpt match {
      case Some(pos) => return pos.uriOpt
      case _ => return None()
    }
  }
}

import org.sireum.U32._

object Position {
  val none: Position = FlatPos(None(), u32"1", u32"1", u32"1", u32"1", u32"0", u32"0")
}

@datatype trait Position {

//  l""" invariant LinePos:            beginLine ≥ 1
//                 ColumnPos:          beginColumn ≥ 1
//                 EndLinePos:         endLine ≥ 1
//                 EncColumnPos:       endColumn ≥ 1
//                 OffsetNonNegative:  offset ≥ 0
//                 LengthNonNegative:  length ≥ 0 """

  @pure def uriOpt: Option[String]

  @pure def beginLine: Z

  @pure def beginColumn: Z

  @pure def endLine: Z

  @pure def endColumn: Z

  @pure def offset: Z

  @pure def length: Z

  @pure override def hash: Z = {
    return (uriOpt, beginLine, endLine, endColumn, offset, length).hash
  }

  @pure def isEqual(other: Position): B = {
    return uriOpt == other.uriOpt && beginLine == other.beginLine && beginColumn == other.beginColumn &&
      endLine == other.endLine && endColumn == other.endColumn && offset == other.offset && length == other.length
  }

  @pure override def string: String = {
    uriOpt match {
      case Some(fileUri) =>
        var i = ops.StringOps(fileUri).lastIndexOf('/')
        if (i < 0) {
          i = 0
        }
        return s"[${ops.StringOps(fileUri).substring(i, fileUri.size)}, $beginLine, $beginColumn]"
      case _ => return s"[$beginLine, $beginColumn]"
    }

  }

  @pure def to(other: Position): Position = {
    val pos1 = this
    val pos2 = other
    var docInfoOpt: Option[message.DocInfo] = None()
    pos1 match {
      case pos1: PosInfo => docInfoOpt = Some(pos1.info)
      case _ =>
        pos2 match {
          case pos2: PosInfo => docInfoOpt = Some(pos2.info)
          case _ =>
        }
    }
    docInfoOpt match {
      case Some(info) =>
        return PosInfo(info, (conversions.Z.toU64(pos1.offset) << u64"32") |
          conversions.Z.toU64(pos2.offset + pos2.length - pos1.offset))
      case _ =>
        return FlatPos(
          uriOpt = pos1.uriOpt,
          beginLine32 = conversions.Z.toU32(pos1.beginLine),
          beginColumn32 = conversions.Z.toU32(pos1.beginColumn),
          endLine32 = conversions.Z.toU32(pos2.endLine),
          endColumn32 = conversions.Z.toU32(pos2.endColumn),
          offset32 = conversions.Z.toU32(pos1.offset),
          length32 = conversions.Z.toU32(pos2.offset + pos2.length - pos1.offset)
        )
    }
  }

}

@datatype class FlatPos(
  val uriOpt: Option[String],
  val beginLine32: U32,
  val beginColumn32: U32,
  val endLine32: U32,
  val endColumn32: U32,
  val offset32: U32,
  val length32: U32
) extends Position {

  @pure override def beginLine: Z = {
    return conversions.U32.toZ(beginLine32)
  }

  @pure override def beginColumn: Z = {
    return conversions.U32.toZ(beginColumn32)
  }

  @pure override def endLine: Z = {
    return conversions.U32.toZ(endLine32)
  }

  @pure override def endColumn: Z = {
    return conversions.U32.toZ(endColumn32)
  }

  @pure override def offset: Z = {
    return conversions.U32.toZ(offset32)
  }

  @pure override def length: Z = {
    return conversions.U32.toZ(length32)
  }
}

@datatype class PosInfo(val info: DocInfo, val offsetLength: U64) extends Position {

  @pure override def uriOpt: Option[String] = {
    return info.uriOpt
  }

  @pure override def beginLine: Z = {
    return conversions.U64.toZ(info.lineColumn(offsetLength) >>> u64"32")
  }

  @pure override def beginColumn: Z = {
    return conversions.U64.toZ(info.lineColumn(offsetLength) & u64"0xFFFFFFFF")
  }

  @pure override def endLine: Z = {
    val endOffset = offsetLength + ((offsetLength - u64"1") << u64"32")
    return conversions.U64.toZ(info.lineColumn(endOffset) >>> u64"32")
  }

  @pure override def endColumn: Z = {
    val endOffset = offsetLength + ((offsetLength - u64"1") << u64"32")
    return conversions.U64.toZ(info.lineColumn(endOffset) & u64"0xFFFFFFFF")
  }

  @pure override def offset: Z = {
    return conversions.U64.toZ(offsetLength >>> u64"32")
  }

  @pure override def length: Z = {
    return conversions.U64.toZ(offsetLength & u64"0xFFFFFFFF")
  }
}

@datatype class DocInfo(val uriOpt: Option[String], val lineOffsets: ISZ[U32]) {

  @pure def lineColumn(offsetLength: U64): U64 = {
    val offsetLine = conversions.U64.toU32(offsetLength >>> u64"32")
    @pure def computeLC(i: Z): U64 = {
      val line = conversions.Z.toU64(i + 1) << u64"32"
      val column = conversions.U32.toU64(offsetLine - lineOffsets(i)) + u64"1"
      return line | column
    }
    val size = lineOffsets.size
    var i = size / 2
    var max = size - 1
    var min = z"0"
    while (min < i && i <= max) {
      val lineOffsetsI = lineOffsets(i)
      if (offsetLine < lineOffsetsI) {
        if (lineOffsets(i - 1) <= offsetLine) {
          return computeLC(i - 1)
        }
        max = i
        i = i - ((i - min) / 2)
      } else if (offsetLine == lineOffsetsI) {
        return computeLC(i)
      } else {
        min = i
        i = i + (max - i) / 2
      }
    }
    return if (i <= min) computeLC(min) else computeLC(max)
  }
}

object DocInfo {

  @pure def createFromCis(uriOpt: Option[String], cis: ISZ[C]): DocInfo = {
    var i = ops.StringOps.indexOfFrom(cis, '\n', 0)
    var lineOffsets = ISZ[U32](u32"0")
    while (0 <= i && i < cis.size) {
      lineOffsets = lineOffsets :+ conversions.Z.toU32(i + 1)
      i = ops.StringOps.indexOfFrom(cis, '\n', i + 1)
    }
    return DocInfo(uriOpt, lineOffsets)
  }

  @pure def create(uriOpt: Option[String], input: String): DocInfo = {
    return createFromCis(uriOpt, conversions.String.toCis(input))
  }

}
