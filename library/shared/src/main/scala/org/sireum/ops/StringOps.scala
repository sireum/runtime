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

package org.sireum.ops

import org.sireum._
import org.sireum.U64._
import org.sireum.message.Reporter

object StringOps {
  @pure def isJavaId(cis: ISZ[C]): B = {
    @strictpure def isDigit(c: C): B = '0' <= c && c <= '9'
    @strictpure def isLetter(c: C): B = 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z'
    if (cis.isEmpty) {
      return F
    }
    val first = cis(0)
    if (!(isLetter(first) || first == '_' || first == '$')) {
      return F
    }
    for (i <- 1 until cis.size) {
      val c = cis(i)
      if (!(isLetter(c) || isDigit(c) || c == '_' || c == '$')) {
        return F
      }
    }
    return T
  }

  @strictpure def isScalaOp(cis: ISZ[C]): B = ops.ISZOps(cis).forall((c: C) => ops.COps(c).isScalaOp)

  @pure def trim(cis: ISZ[C]): String = {
    var i = 0
    val size = cis.size
    while (i < size && cis(i).isWhitespace) {
      i = i + 1
    }
    var j = size - 1
    while (j >= 0 && cis(j).isWhitespace) {
      j = j - 1
    }
    return if (i <= j) StringOps.substring(cis, i, j + 1) else ""
  }

  @pure def trimTrailing(cis: ISZ[C]): String = {
    val size = cis.size
    var j = size - 1
    while (j >= 0 && cis(j).isWhitespace) {
      j = j - 1
    }
    return if (0 <= j) StringOps.substring(cis, 0, j + 1) else ""
  }

  @pure def replace(content: ISZ[C], offsetOldNewStringMap: HashMap[Z, (String, String)]): Either[String, String] = {
    if (offsetOldNewStringMap.isEmpty) {
      return Either.Left(conversions.String.fromCis(content))
    }
    var m = offsetOldNewStringMap
    var r = ISZ[C]()
    val size = content.size
    var i: Z = 0
    while (i < size) {
      m.get(i) match {
        case Some(pair@(oldString, newString)) =>
          val oldChars = conversions.String.toCis(oldString)
          for (j <- 0 until oldChars.size) {
            if (i + j >= size || content(i + j) != oldChars(j)) {
              var cs = ISZ[C]()
              for (k <- 0 until oldChars.size if i + k < size) {
                cs = cs :+ content(i + k)
              }
              return Either.Right(st"""Expecting "${(oldChars, "")}" at offset $i, but found "${(cs, "")}" instead""".render)
            }
          }
          r = r ++ conversions.String.toCis(newString)
          i = i + oldString.size
          m = m - i ~> pair
        case _ =>
          r = r :+ content(i)
          i = i + 1
      }
    }
    return Either.Left(conversions.String.fromCis(r))
  }

  @pure def startsWith(cis: ISZ[C], otherCis: ISZ[C]): B = {
    if (cis.size < otherCis.size) {
      return F
    }
    for (i <- z"0" until otherCis.size) {
      if (otherCis(i) != cis(i)) {
        return F
      }
    }
    return T
  }

  @pure def endsWith(cis: ISZ[C], otherCis: ISZ[C]): B = {
    if (cis.size < otherCis.size) {
      return F
    }
    val offset = cis.size - otherCis.size
    for (i <- otherCis.size - 1 to 0 by -1) {
      if (otherCis(i) != cis(offset + i)) {
        return F
      }
    }
    return T
  }

  @pure def stringIndexOfFrom(cis: ISZ[C], otherCis: ISZ[C], offset: Z): Z = {
    val size = cis.size
    val otherSize = otherCis.size
    if (!(0 <= offset && offset < size)) {
      return -1
    }
    var i = offset
    while (i + otherSize <= size) {
      var j = 0
      var found = T
      while (j < otherSize && found) {
        if (cis(i + j) != otherCis(j)) {
          found = F
        }
        j = j + 1
      }
      if (found) {
        return i
      }
      i = i + 1
    }
    return -1
  }

  @pure def indexOfFrom(cis: ISZ[C], c: C, offset: Z): Z = {
    if (!(0 <= offset && offset < cis.size)) {
      return -1
    }
    for (i <- offset until cis.size) {
      if (cis(i) == c) {
        return i
      }
    }
    return -1
  }

  @pure def lastIndexOfFrom(cis: ISZ[C], c: C, offset: Z): Z = {
    if (!(0 <= offset && offset < cis.size)) {
      return -1
    }
    for (i <- offset to 0 by -1) {
      if (cis(i) == c) {
        return i
      }
    }
    return -1
  }
  @pure def substring(cis: ISZ[C], start: Z, until: Z): String = {
    if (until - start <= 0) {
      return ""
    }
    val ms = MSZ.create[C](until - start, '\u0000')
    var i = start
    var j = 0
    while (i < until) {
      ms(j) = cis(i)
      i = i + 1
      j = j + 1
    }
    return conversions.String.fromCms(ms)
  }

  @pure def replaceAllLiterally(cis: ISZ[C], from: String, to: String): String = {
    var r = ISZ[C]()
    var i: Z = 0
    val toSize = to.size
    val fromSize = from.size
    val fromCis = conversions.String.toCis(from)
    val toCis = conversions.String.toCis(to)
    val size = cis.size
    while (i + fromSize < size) {
      var j = 0
      var found = T
      while (i + j < size && j < fromSize && found) {
        if (cis(i + j) != fromCis(j)) {
          found = F
        }
        j = j + 1
      }
      if (found && j == fromSize) {
        for (j <- 0 until toSize) {
          r = r :+ toCis(j)
        }
        i = i + fromSize - 1
      } else {
        r = r :+ cis(i)
      }
      i = i + 1
    }

    var isSuffix = F
    if(i + fromSize == size) {
      var index = i
      while(index < size && cis(index) == fromCis(index - i)) {
        index = index + 1
      }
      isSuffix = index == size
    }

    if(isSuffix) {
      r = r ++ toCis
    } else {
      while (i < size) {
        r = r :+ cis(i)
        i = i + 1
      }
    }
    return conversions.String.fromCis(r)
  }

  @ext("StringOps_Ext") object Ext {
    @pure def sha3(is256: B, s: String): ISZ[U8] = $
  }
}

@datatype class StringOps(val s: String) {

  @pure def first: C = {
//    l""" requires s.size > 0 """
    return conversions.String.toCis(s)(0)
  }

  @pure def substring(start: Z, until: Z): String = {
//    l""" requires 0 ≤ start ∧ start < s.size
//                  start ≤ until
//                  until ≤ s.size
//         ensures  result.size ≡ until - start
//                  ∀i: [0, result.size) result(i) ≡ s(start + i) """
    return StringOps.substring(conversions.String.toCis(s), start, until)
  }

  @pure def startsWith(other: String): B = {
//    l""" ensures  result ≡ ((size >= other.size) ∧
//                            ∀i: [0, other.size) s(i) ≡ other(i)) """
    if (s.size < other.size) {
      return F
    }
    val cis = conversions.String.toCis(s)
    val otherCis = conversions.String.toCis(other)
    return StringOps.startsWith(cis, otherCis)
  }

  @pure def endsWith(other: String): B = {
//    l""" ensures  result ≡ ((size >= other.size) ∧
//                            ∀i: [0, other.size) s(i + other.size - s.size) ≡ other(i)) """
    if (s.size < other.size) {
      return F
    }
    val cis = conversions.String.toCis(s)
    val otherCis = conversions.String.toCis(other)
    return StringOps.endsWith(cis, otherCis)
  }

  @pure def firstToUpper: String = {
//    l""" requires s.size > 0
//         ensures  result.size ≡ s.size
//                  result(0) ≡ conversions.COps(s(0)).toUpper
//                  ∀i: [1, s.size) result(i) ≡ s(i)   """
    val cms = conversions.String.toCms(s)
    cms(0) = COps(cms(0)).toUpper
    return conversions.String.fromCms(cms)
  }

  @pure def toUpper: String = {
    val cms = conversions.String.toCms(s)
    for (i <- 0 until cms.size) {
      cms(i) = COps(cms(i)).toUpper
    }
    return conversions.String.fromCms(cms)
  }

  @pure def firstToLower: String = {
//    l""" requires s.size > 0
//         ensures  result.size ≡ s.size
//                  result(0) ≡ conversions.COps(s(0)).toLower
//                  ∀i: [1, s.size) result(i) ≡ s(i)   """
    val cms = conversions.String.toCms(s)
    cms(0) = COps(cms(0)).toLower
    return conversions.String.fromCms(cms)
  }

  @pure def toLower: String = {
    val cms = conversions.String.toCms(s)
    for (i <- 0 until cms.size) {
      cms(i) = COps(cms(i)).toLower
    }
    return conversions.String.fromCms(cms)
  }

  @pure def contains(other: String): B = {
    return stringIndexOf(other) >= 0
  }

  @pure def stringIndexOf(other: String): Z = {
    return stringIndexOfFrom(other, 0)
  }

  @pure def stringIndexOfFrom(other: String, offset: Z): Z = {
    if (!(0 <= offset && offset < s.size)) {
      return -1
    }
    val cis = conversions.String.toCis(s)
    val otherCis = conversions.String.toCis(other)
    return StringOps.stringIndexOfFrom(cis, otherCis, offset)
  }

  @pure def indexOf(c: C): Z = {
    return indexOfFrom(c, 0)
  }

  @pure def indexOfFrom(c: C, offset: Z): Z = {
    if (!(0 <= offset && offset < s.size)) {
      return -1
    }
    val cis = conversions.String.toCis(s)
    return StringOps.indexOfFrom(cis, c, offset)
  }

  @pure def lastIndexOf(c: C): Z = {
    return lastIndexOfFrom(c, s.size - 1)
  }

  @pure def lastIndexOfFrom(c: C, offset: Z): Z = {
    if (!(0 <= offset && offset < s.size)) {
      return -1
    }
    val cis = conversions.String.toCis(s)
    return StringOps.lastIndexOfFrom(cis, c, offset)
  }

  @pure def replaceAllChars(from: C, to: C): String = {
    val cs = conversions.String.toCms(s)
    for (i <- 0 until cs.size if cs(i) == from) {
      cs(i) = to
    }
    return conversions.String.fromCms(cs)
  }

  @pure def replaceAllLiterally(from: String, to: String): String = {
    return StringOps.replaceAllLiterally(conversions.String.toCis(s), from, to)
  }

  @pure def split(isSep: C => B @pure): ISZ[String] = {
    var r = ISZ[String]()
    val cis = conversions.String.toCis(s)
    var last = 0
    val size = s.size
    while (last < size && isSep(cis(last))) {
      last = last + 1
    }
    var i = last
    while (i < size) {
      if (isSep(cis(i)) && last != i) {
        r = r :+ StringOps.substring(cis, last, i)
        i = i + 1
        while (i < size && isSep(cis(i))) {
          i = i + 1
        }
        last = i
      }
      i = i + 1
    }
    if (last < size) {
      r = r :+ StringOps.substring(cis, last, i)
    }
    return r
  }

  @pure def trim: String = {
    return StringOps.trim(conversions.String.toCis(s))
  }

  @pure def trimTrailing: String = {
    return StringOps.trimTrailing(conversions.String.toCis(s))
  }

  @pure def size: Z = {
    return s.size
  }

  @strictpure def isScalaOp: B = StringOps.isScalaOp(conversions.String.toCis(s))

  def collectSections(errorKind: String,
                      beginMarker: String,
                      endMarker: String,
                      reporter: Reporter): HashSMap[String, String] = {
    var r = HashSMap.empty[String, String]
    val lines = ops.StringOps(s).split(c => c == '\n')
    val size = lines.size
    var i = 0
    while (i < size) {
      val line = lines(i)
      val lOps = ops.StringOps(ops.StringOps(line).trim)
      if (lOps.startsWith(beginMarker)) {
        val name = ops.StringOps(lOps.substring(beginMarker.size, lOps.size)).trim
        val beginLine = i
        i = i + 1
        var found = F
        var code = ISZ[String]()
        while (i < size && !found) {
          val line2 = lines(i)
          val lOps2 = ops.StringOps(ops.StringOps(line2).trim)
          if (lOps2.startsWith(endMarker)) {
            found = T
            val name2 = ops.StringOps(lOps2.substring(endMarker.size, lOps2.size)).trim
            if (name != name2) {
              reporter.error(None(), errorKind,
                s"Mismatch marker at lines ${beginLine + 1} and ${i + 1} ($name != $name2)")
              return r
            }
            r = r + name ~> st"${(code, "\n")}".render
          } else {
            code = code :+ ops.StringOps(line2).trimTrailing
          }
          i = i + 1
        }
        if (!found) {
          reporter.error(None(), errorKind, s"Unclosed marker at line ${beginLine + 1} for $name")
        }
      }
      i = i + 1
    }
    return r
  }

  @pure def escapeST: ST = {
    return st"""${(for (c <- conversions.String.toCis(s)) yield ops.COps(c).escapeString, "")}"""
  }

  @pure def replaceStrings(offsetOldNewStringMap: HashMap[Z, (String, String)]): Either[String, String] = {
    return StringOps.replace(conversions.String.toCis(s), offsetOldNewStringMap)
  }

  @pure def compareVersion(other: String): Z = {
    @pure def removePrefix(str: String): String = {
      val cis = conversions.String.toCis(str)
      var i = 0
      while (i < cis.size) {
        val c = cis(i)
        if (c == '_' || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '$') {
          i = i + 1
        } else {
          return ops.StringOps(str).substring(i, str.size)
        }
      }
      return ""
    }
    val v1Ops = ops.StringOps(removePrefix(s))
    val v2Ops = ops.StringOps(removePrefix(other))
    val p: C => B @pure = (c: C) => c == '.'
    val v1s = v1Ops.split(p)
    val v2s = v2Ops.split(p)
    val size: Z = if (v1s.size <= v2s.size) v1s.size else v2s.size
    for (i <- 0 until size) {
      (Z(v1s(i)), Z(v2s(i))) match {
        case (Some(v1), Some(v2)) =>
          if (v1 < v2) {
            return -1
          } else if (v1 > v2) {
            return 1
          }
        case (_, _) =>
          return 0
      }
    }
    return 0
  }

  @pure def sha3(isNative: B, is256: B): ISZ[U8] = {
    if (isNative) {
      return StringOps.Ext.sha3(is256, s)
    }
    val sha3: crypto.SHA3 = if (is256) crypto.SHA3.init256 else crypto.SHA3.init512
    sha3.update(conversions.String.toU8is(s))
    return sha3.finalise()
  }

  @pure def sha3U64(isNative: B, is256: B): U64 = {
    val digest = sha3(isNative, is256)
    return (conversions.U8.toU64(digest(0)) & u64"0xFF") << u64"56" |
      (conversions.U8.toU64(digest(1)) & u64"0xFF") << u64"48" |
      (conversions.U8.toU64(digest(2)) & u64"0xFF") << u64"40" |
      (conversions.U8.toU64(digest(3)) & u64"0xFF") << u64"32" |
      (conversions.U8.toU64(digest(4)) & u64"0xFF") << u64"24" |
      (conversions.U8.toU64(digest(5)) & u64"0xFF") << u64"16" |
      (conversions.U8.toU64(digest(6)) & u64"0xFF") << u64"8" |
      (conversions.U8.toU64(digest(7)) & u64"0xFF")
  }

  @strictpure def cisLineStream: Jen[ISZ[C]] = conversions.String.toCisLineStream(s)

  @pure def isJavaId: B = {
    return StringOps.isJavaId(conversions.String.toCis(s))
  }
}
