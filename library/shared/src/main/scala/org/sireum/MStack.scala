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


object MStack {
  def create[T](default: T, initialCapacity: Z, growthFactor: Z): MStack[T] = {
    return MStack(MSZ.create(initialCapacity, default), growthFactor, 0)
  }
}

@record class MStack[T](var elements: MSZ[T], val growthFactor: Z, var sz: Z) {

  def size: Z = {
    return sz
  }

  def isEmpty: B = {
    return sz == 0
  }

  def nonEmpty: B = {
    return sz > 0
  }

  def peek: T = {
    return elements(sz - 1)
  }

  def push(e: T): Unit = {
    if (sz == elements.size) {
      val newCapacity: Z = elements.size * growthFactor
      val newElements: MSZ[T] = MSZ.create(newCapacity, e)
      var i: Z = 0
      while (i < sz) {
        newElements(i) = elements(i)
        i = i + 1
      }
      elements = newElements
    }
    elements(sz) = e
    sz = sz + 1
  }

  def pop(): T = {
    sz = sz - 1
    return elements(sz)
  }
}

object MIStack {
  def create[@imm T](default: T, initialCapacity: Z, growthFactor: Z): MIStack[T] = {
    return MIStack(MSZ.create(initialCapacity, default), growthFactor, 0)
  }
}

@record class MIStack[@imm T](var elements: MSZ[T], val growthFactor: Z, var sz: Z) {

  def size: Z = {
    return sz
  }

  def isEmpty: B = {
    return sz == 0
  }

  def nonEmpty: B = {
    return sz > 0
  }

  def peek: T = {
    return elements(sz - 1)
  }

  def push(e: T): Unit = {
    if (sz == elements.size) {
      val newCapacity: Z = elements.size * growthFactor
      val newElements: MSZ[T] = MSZ.create(newCapacity, e)
      var i: Z = 0
      while (i < sz) {
        newElements(i) = elements(i)
        i = i + 1
      }
      elements = newElements
    }
    elements(sz) = e
    sz = sz + 1
  }

  def pop(): T = {
    sz = sz - 1
    return elements(sz)
  }

  def toISZ: ISZ[T] = {
    if (sz == 0) {
      return ISZ()
    }
    val ms = MSZ.create(sz, elements(0))
    var i: Z = 1
    while (i < sz) {
      ms(i) = elements(i)
      i = i + 1
    }
    return ms.toIS
  }
}
