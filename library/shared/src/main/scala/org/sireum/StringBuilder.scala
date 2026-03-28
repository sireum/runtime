// #Sireum
/*
 Copyright (c) 2017-2026, Robby, Kansas State University
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

object StringBuilder {

  def create(): StringBuilder = {
    return StringBuilder(MSZ.create[C](16, '\u0000'), 0)
  }

  def createWithCapacity(cap: Z): StringBuilder = {
    return StringBuilder(MSZ.create[C](cap, '\u0000'), 0)
  }

  def fromString(s: String): StringBuilder = {
    val cis = conversions.String.toCis(s)
    val data = MSZ.create[C](cis.size, '\u0000')
    var i: Z = 0
    while (i < cis.size) {
      data(i) = cis(i)
      i = i + 1
    }
    return StringBuilder(data, cis.size)
  }

}

@record class StringBuilder(var data: MSZ[C], var len: Z) {

  @pure def size: Z = {
    return len
  }

  @pure def isEmpty: B = {
    return len == 0
  }

  @pure def nonEmpty: B = {
    return len != 0
  }

  def append(s: String): Unit = {
    val cis = conversions.String.toCis(s)
    var i: Z = 0
    while (i < cis.size) {
      appendC(cis(i))
      i = i + 1
    }
  }

  def appendC(c: C): Unit = {
    if (len == data.size) {
      grow()
    }
    data(len) = c
    len = len + 1
  }

  def appendZ(n: Z): Unit = {
    append(n.string)
  }

  def appendB(b: B): Unit = {
    if (b) {
      append("true")
    } else {
      append("false")
    }
  }

  def clear(): Unit = {
    len = 0
  }

  def result: String = {
    val cs = MSZ.create[C](len, '\u0000')
    var i: Z = 0
    while (i < len) {
      cs(i) = data(i)
      i = i + 1
    }
    return conversions.String.fromCms(cs)
  }

  @pure override def string: String = {
    val cs = MSZ.create[C](len, '\u0000')
    var i: Z = 0
    while (i < len) {
      cs(i) = data(i)
      i = i + 1
    }
    return conversions.String.fromCms(cs)
  }

  // ── Internal ──────────────────────────────────────────────────────────

  def grow(): Unit = {
    val oldCap = data.size
    val newCap: Z = if (oldCap == 0) 16 else oldCap * 2
    val nd = MSZ.create[C](newCap, '\u0000')
    var i: Z = 0
    while (i < len) {
      nd(i) = data(i)
      i = i + 1
    }
    data = nd
  }

}
