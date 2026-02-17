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

object MStack {
  def create[T](default: T, initialCapacity: S32, growthFactor: S32): MStack[T] = {
    return MStack(MS.create[S32, T](conversions.S32.toZ(initialCapacity), default), growthFactor, s32"0")
  }
}

@record class MStack[T](var elements: MS[S32, T], val growthFactor: S32, var sz: S32) {

  def size: Z = {
    return conversions.S32.toZ(sz)
  }

  def sizeS32: S32 = {
    return sz
  }

  def isEmpty: B = {
    return sz == s32"0"
  }

  def nonEmpty: B = {
    return sz > s32"0"
  }

  def peek: T = {
    return elements.atS32(sz - s32"1")
  }

  def push(e: T): Unit = {
    if (sz == elements.sizeS32) {
      val newCapacity: S32 = elements.sizeS32 * growthFactor
      val newElements: MS[S32, T] = MS.create[S32, T](conversions.S32.toZ(newCapacity), e)
      var i: S32 = s32"0"
      while (i < sz) {
        newElements(i) = elements.atS32(i)
        i = i + s32"1"
      }
      elements = newElements
    }
    elements(sz) = e
    sz = sz + s32"1"
  }

  def pop(): T = {
    sz = sz - s32"1"
    return elements.atS32(sz)
  }
}

object MIStack {
  def create[@imm T](default: T, initialCapacity: S32, growthFactor: S32): MIStack[T] = {
    return MIStack(MS.create[S32, T](conversions.S32.toZ(initialCapacity), default), growthFactor, s32"0")
  }
}

@record class MIStack[@imm T](var elements: MS[S32, T], val growthFactor: S32, var sz: S32) {

  def size: Z = {
    return conversions.S32.toZ(sz)
  }

  def sizeS32: S32 = {
    return sz
  }

  def isEmpty: B = {
    return sz == s32"0"
  }

  def nonEmpty: B = {
    return sz > s32"0"
  }

  def peek: T = {
    return elements.atS32(sz - s32"1")
  }

  def push(e: T): Unit = {
    if (sz == elements.sizeS32) {
      val newCapacity: S32 = elements.sizeS32 * growthFactor
      val newElements: MS[S32, T] = MS.create[S32, T](conversions.S32.toZ(newCapacity), e)
      var i: S32 = s32"0"
      while (i < sz) {
        newElements(i) = elements.atS32(i)
        i = i + s32"1"
      }
      elements = newElements
    }
    elements(sz) = e
    sz = sz + s32"1"
  }

  def pop(): T = {
    sz = sz - s32"1"
    return elements.atS32(sz)
  }

  def toIS: IS[S32, T] = {
    if (sz == s32"0") {
      return IS[S32, T]()
    }
    return ops.MSZOpsUtil.slice(elements, s32"0", sz)
  }
}
