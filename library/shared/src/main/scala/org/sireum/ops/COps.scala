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

@datatype class COps(val c: C) {

  @pure def category: COps.Category.Type = {
    return COps.Ext.categoryOf(c)
  }

  @pure def toUnicodeHex: (C, C, C, C) = {
    return (COps.hex2c(c >>> '\u000C'), COps.hex2c((c >>> '\u0008') & '\u000F'), COps.hex2c((c >>> '\u0004') & '\u000F'), COps.hex2c(c & '\u000F'))
  }

  @pure def toUpper: C = {
    if ('a' <= c && c <= 'z') {
      return c - '\u0020'
    } else {
      return c
    }
  }

  @pure def toLower: C = {
    if ('A' <= c && c <= 'Z') {
      return c + '\u0020'
    } else {
      return c
    }
  }

  @strictpure def isScalaOp: B = c match {
    case '!' => T
    case '#' => T
    case '%' => T
    case '&' => T
    case '*' => T
    case '+' => T
    case '-' => T
    case '/' => T
    case ':' => T
    case '<' => T
    case '=' => T
    case '>' => T
    case '?' => T
    case '@' => T
    case '\\' => T
    case '^' => T
    case '|' => T
    case '~' => T
    case _ =>
      val cat = category
      cat == COps.Category.Sm || cat == COps.Category.So
  }

  @pure def escapeString: String = {
    c match {
      case '\b' => return "\\b"
      case '\t' => return "\\t"
      case '\n' => return "\\n"
      case '\f' => return "\\f"
      case '\r' => return "\\r"
      case '"' =>  return "\\\""
      case '\'' => return "\\\'"
      case '\\' => return "\\\\"
      case _ =>
    }
    if ('\u0020' <= c && c <= '\u007e') {
      return c.string
    } else if (c > '\uFFFF') {
      var r: String = ""
      for (cpc <- conversions.C.toCodePoints(c)) {
        r = s"$r${COps(cpc).escapeString}"
      }
      return r
    } else {
      val q = toUnicodeHex
      return s"\\u${q._1}${q._2}${q._3}${q._4}"
    }
  }

}

object COps {

  @enum object Category {
    "Mc"
    "Pc"
    "Cc"
    "Sc"
    "Pd"
    "Nd"
    "Me"
    "Pe"
    "Pf"
    "Cf"
    "Pi"
    "Nl"
    "Zl"
    "Ll"
    "Sm"
    "Lm"
    "Sk"
    "Mn"
    "Lo"
    "No"
    "Po"
    "So"
    "Zp"
    "Co"
    "Zs"
    "Ps"
    "Cs"
    "Lt"
    "Cn"
    "Lu"
  }

  @ext("COps_Ext") object Ext {
    @pure def categoryOf(c: C): Category.Type = $
  }

  @pure def c2hex(c: C): Option[C] = {
    c.native match {
      case '0' => return Some('\u0000')
      case '1' => return Some('\u0001')
      case '2' => return Some('\u0002')
      case '3' => return Some('\u0003')
      case '4' => return Some('\u0004')
      case '5' => return Some('\u0005')
      case '6' => return Some('\u0006')
      case '7' => return Some('\u0007')
      case '8' => return Some('\u0008')
      case '9' => return Some('\u0009')
      case 'a' => return Some('\u000A')
      case 'A' => return Some('\u000A')
      case 'b' => return Some('\u000B')
      case 'B' => return Some('\u000B')
      case 'c' => return Some('\u000C')
      case 'C' => return Some('\u000C')
      case 'd' => return Some('\u000D')
      case 'D' => return Some('\u000D')
      case 'e' => return Some('\u000E')
      case 'E' => return Some('\u000E')
      case 'f' => return Some('\u000F')
      case 'F' => return Some('\u000F')
      case _ => return None[C]()
    }
  }

  @pure def hex2c(c: C): C = {
    val r: C = (c & '\u000F').native match {
      case '\u0000' => '0'
      case '\u0001' => '1'
      case '\u0002' => '2'
      case '\u0003' => '3'
      case '\u0004' => '4'
      case '\u0005' => '5'
      case '\u0006' => '6'
      case '\u0007' => '7'
      case '\u0008' => '8'
      case '\u0009' => '9'
      case '\u000A' => 'A'
      case '\u000B' => 'B'
      case '\u000C' => 'C'
      case '\u000D' => 'D'
      case '\u000E' => 'E'
      case '\u000F' => 'F'
    }
    return r
  }

  @pure def fromUnicodeHex(hex: ISZ[C]): Option[C] = {
    if (hex.size != 4) {
      return None[C]()
    }
    (c2hex(hex(0)), c2hex(hex(1)), c2hex(hex(2)), c2hex(hex(3))) match {
      case (Some(c1), Some(c2), Some(c3), Some(c4)) =>
        return Some((c1 << '\u000c') | (c2 << '\u0008') | (c3 << '\u0004') | c4)
      case _ => return None[C]()
    }
  }
}