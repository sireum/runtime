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

@datatype class StringOps(s: String) {

  def substring(start: Z, until: Z): String = {
    l""" requires 0 ≤ start ∧ start < s.size
                  start ≤ until
                  until ≤ s.size
         ensures  result.size ≡ until - start
                  ∀i: [0, result.size) result(i) ≡ s(start + i) """
    var ms = MSZ.create[C](until - start, '\u0000')
    var i = start
    var j = 0
    while (i < until) {
      ms(j) = s(i)
      i = i + 1
      j = j + 1
    }
    return conversions.String.fromCms(ms)
  }

  def startsWith(other: String): B = {
    l""" ensures  result ≡ ((size >= other.size) ∧
                            ∀i: [0, other.size) s(i) ≡ other(i)) """
    if (s.size < other.size) {
      return F
    }
    for (i <- z"0" until other.size) {
      if (other(i) != s(i)) {
        return F
      }
    }
    return T
  }

  def endsWith(other: String): B = {
    l""" ensures  result ≡ ((size >= other.size) ∧
                            ∀i: [0, other.size) s(i + other.size - s.size) ≡ other(i)) """
    if (s.size < other.size) {
      return F
    }
    for (i <- other.size - 1 to 0 by -1) {
      if (other(i) != s(i)) {
        return F
      }
    }
    return T
  }

  def firstToUpper: String = {
    l""" requires s.size > 0
         ensures  result.size ≡ s.size
                  result(0) ≡ conversions.COps(s(0))
                  ∀i: [1, s.size) result(i) ≡ s(i)   """
    val cms = conversions.String.toCms(s)
    cms(0) = COps(cms(0)).toUpper
    return conversions.String.fromCms(cms)
  }
}