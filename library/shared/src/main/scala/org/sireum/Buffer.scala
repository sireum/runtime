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

object Buffer {

  def create[@imm E](): Buffer[E] = {
    return Buffer[E](MSZ[E](), 0)
  }

  def createWithCapacity[@imm E](cap: Z, default: E): Buffer[E] = {
    return Buffer[E](MSZ.create(cap, default), 0)
  }

}

@record class Buffer[@imm E](var data: MSZ[E], var len: Z) {

  var extracted: B = F

  @pure def size: Z = {
    return len
  }

  @pure def isEmpty: B = {
    return len == 0
  }

  @pure def nonEmpty: B = {
    return len != 0
  }

  def get(i: Z): E = {
    if (i < 0 || i >= len) {
      halt(st"Buffer index out of bounds: $i (size: $len)".render)
    }
    return data(i)
  }

  def set(i: Z, e: E): Unit = {
    if (i < 0 || i >= len) {
      halt(st"Buffer index out of bounds: $i (size: $len)".render)
    }
    data(i) = e
  }

  def append(e: E): Unit = {
    if (len == data.size) {
      grow(e)
    }
    data(len) = e
    len = len + 1
  }

  def +=(e: E): Unit = {
    append(e)
  }

  def clear(): Unit = {
    len = 0
  }

  def toIS: ISZ[E] = {
    if (extracted) {
      halt("The buffer has been extracted")
    }
    extracted = T
    return ops.MSZOpsUtil.slice(data, 0, len)
  }

  def toMS: MSZ[E] = {
    if (extracted) {
      halt("The buffer has been extracted")
    }
    extracted = T
    return data
  }

  // ── Internal ──────────────────────────────────────────────────────────

  def grow(e: E): Unit = {
    val oldCap = data.size
    val newCap: Z = if (oldCap == 0) 8 else oldCap * 2
    val nd = MSZ.create[E](newCap, e)
    var i: Z = 0
    while (i < len) {
      nd(i) = data(i)
      i = i + 1
    }
    data = nd
  }

}
