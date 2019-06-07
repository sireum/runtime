// #Sireum
/*
 Copyright (c) 2018, Robby, Kansas State University
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
import org.sireum.message.Reporter

object StringOps {
  @pure def substring(cis: ISZ[C], start: Z, until: Z): String = {
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
      while (i + j + fromSize < size && j < fromSize && found) {
        if (cis(i + j) != fromCis(j)) {
          found = F
        }
        j = j + 1
      }
      if (found) {
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
}

@datatype class StringOps(s: String) {

  @pure def first: C = {
    l""" requires s.size > 0 """
    return conversions.String.toCis(s)(0)
  }

  @pure def substring(start: Z, until: Z): String = {
    l""" requires 0 ≤ start ∧ start < s.size
                  start ≤ until
                  until ≤ s.size
         ensures  result.size ≡ until - start
                  ∀i: [0, result.size) result(i) ≡ s(start + i) """
    return StringOps.substring(conversions.String.toCis(s), start, until)
  }

  @pure def startsWith(other: String): B = {
    l""" ensures  result ≡ ((size >= other.size) ∧
                            ∀i: [0, other.size) s(i) ≡ other(i)) """
    if (s.size < other.size) {
      return F
    }
    val cis = conversions.String.toCis(s)
    val otherCis = conversions.String.toCis(other)
    for (i <- z"0" until other.size) {
      if (otherCis(i) != cis(i)) {
        return F
      }
    }
    return T
  }

  @pure def endsWith(other: String): B = {
    l""" ensures  result ≡ ((size >= other.size) ∧
                            ∀i: [0, other.size) s(i + other.size - s.size) ≡ other(i)) """
    if (s.size < other.size) {
      return F
    }
    val cis = conversions.String.toCis(s)
    val otherCis = conversions.String.toCis(other)
    val offset = s.size - other.size
    for (i <- other.size - 1 to 0 by -1) {
      if (otherCis(i) != cis(offset + i)) {
        return F
      }
    }
    return T
  }

  @pure def firstToUpper: String = {
    l""" requires s.size > 0
         ensures  result.size ≡ s.size
                  result(0) ≡ conversions.COps(s(0)).toUpper
                  ∀i: [1, s.size) result(i) ≡ s(i)   """
    val cms = conversions.String.toCms(s)
    cms(0) = COps(cms(0)).toUpper
    return conversions.String.fromCms(cms)
  }

  @pure def firstToLower: String = {
    l""" requires s.size > 0
         ensures  result.size ≡ s.size
                  result(0) ≡ conversions.COps(s(0)).toLower
                  ∀i: [1, s.size) result(i) ≡ s(i)   """
    val cms = conversions.String.toCms(s)
    cms(0) = COps(cms(0)).toLower
    return conversions.String.fromCms(cms)
  }

  @pure def contains(other: String): B = {
    return stringIndexOf(other) >= 0
  }

  @pure def stringIndexOf(other: String): Z = {
    return stringIndexOfFrom(other, 0)
  }

  @pure def stringIndexOfFrom(other: String, offset: Z): Z = {
    val size = s.size
    if (!(0 <= offset && offset < size)) {
      return -1
    }
    val cis = conversions.String.toCis(s)
    val otherCis = conversions.String.toCis(other)
    val otherSize = other.size
    var i = offset
    while (i + otherSize < size) {
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

  @pure def indexOf(c: C): Z = {
    return indexOfFrom(c, 0)
  }

  @pure def indexOfFrom(c: C, offset: Z): Z = {
    if (!(0 <= offset && offset < s.size)) {
      return -1
    }
    val cis = conversions.String.toCis(s)
    for (i <- offset until s.size) {
      if (cis(i) == c) {
        return i
      }
    }
    return -1
  }

  @pure def lastIndexOf(c: C): Z = {
    return lastIndexOfFrom(c, s.size - 1)
  }

  @pure def lastIndexOfFrom(c: C, offset: Z): Z = {
    if (!(0 <= offset && offset < s.size)) {
      return -1
    }
    val cis = conversions.String.toCis(s)
    for (i <- offset to 0 by -1) {
      if (cis(i) == c) {
        return i
      }
    }
    return -1
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
    var i = 0
    val size = s.size
    val cis = conversions.String.toCis(s)
    while (i < size && cis(i).isWhitespace) {
      i = i + 1
    }
    var j = size - 1
    while (j >= 0 && cis(j).isWhitespace) {
      j = j - 1
    }
    return if (i <= j) StringOps.substring(cis, i, j + 1) else ""
  }

  @pure def size: Z = {
    return s.size
  }


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
            code = code :+ line2
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
}
