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

package org.sireum.$internal

import org.sireum.{Z, String, helper, Mutable}

object Boxer {
  val MaxArraySize: Z = Z.MP(Int.MaxValue - 8)

  def boxer(o: scala.Any): Boxer = o match {
    case hb: HasBoxer => hb.boxer
    case _ => IdentityBoxer
  }

  def boxerSeq(o: scala.Seq[scala.Any]): Boxer =
    if (o.nonEmpty) boxer(o.head) else IdentityBoxer

}

trait Boxer {
  def box[T](o: scala.Any): T

  def unbox(o: scala.Any): scala.Any

  def create(length: Z): scala.AnyRef = new Array[scala.Any](length)

  def lookup[T](a: scala.AnyRef, i: Z): T = a match {
    case a: Array[_] => box(a(i))
  }

  def store(a: scala.AnyRef, i: Z, v: scala.Any): Unit = a match {
    case a: Array[scala.Any] => a(i) = unbox(v)
  }

  def size(a: scala.AnyRef): Z = a match {
    case a: Array[_] => Z.MP(a.length)
  }

  def copy(src: scala.AnyRef, srcPos: Z, dest: scala.AnyRef, destPos: Z, length: Z): Unit =
    if (length != Z.MP.zero) System.arraycopy(src, srcPos, dest, destPos, length)

  def copyMut(src: scala.AnyRef, srcPos: Z, dest: scala.AnyRef, destPos: Z, length: Z): Unit = {
    if (length == Z.MP.zero) return
    var srcIndex: scala.Int = srcPos
    var destIndex: scala.Int = destPos
    val len: scala.Int = length
    ((src, dest): @unchecked) match {
      case (src: Array[scala.Any], dest: Array[scala.Any]) =>
        for (_ <- 0 until len) {
          src(srcIndex) match {
            case o: Mutable => dest(destIndex) = helper.cloneAssign(o)
            case o => dest(destIndex) = helper.cloneAssign(o)
          }
          srcIndex += 1
          destIndex += 1
        }
    }
  }

  def clone(a: scala.AnyRef, length: Z, newLength: Z, offset: Z): scala.AnyRef = {
    val size = this.size(a)
    if (newLength <= size) {
      val r = create(size)
      copy(a, Z.MP.zero, r, offset, length)
      r
    } else {
      assert(newLength <= Boxer.MaxArraySize, s"Slang currently only supports IS/MS size up to ${Boxer.MaxArraySize}.")
      var newSize = newLength * 3 / 2
      if (newSize > Boxer.MaxArraySize) newSize = Boxer.MaxArraySize
      val r = create(newSize)
      copy(a, Z.MP.zero, r, offset, length)
      r
    }
  }

  def cloneMut(a: scala.AnyRef, length: Z, newLength: Z, offset: Z): scala.AnyRef = {
    val size = this.size(a)
    if (newLength <= size) {
      val r = create(size)
      copyMut(a, Z.MP.zero, r, offset, length)
      r
    } else {
      assert(newLength <= Boxer.MaxArraySize, s"Slang currently only supports IS/MS size up to ${Boxer.MaxArraySize}.")
      var newSize = newLength * 3 / 2
      if (newSize > Boxer.MaxArraySize) newSize = Boxer.MaxArraySize
      val r = create(newSize)
      copyMut(a, Z.MP.zero, r, offset, length)
      r
    }
  }

  def toString(a: scala.AnyRef, length: Z): Predef.String = {
    val sb = new java.lang.StringBuilder
    sb.append('[')
    if (length > 0) {
      sb.append(String.escape(lookup(a, Z.MP.zero)))
      var i = Z.MP.one
      while (i < length) {
        sb.append(", ")
        sb.append(String.escape(lookup(a, i)))
        i = i.increase
      }
    }
    sb.append(']')
    sb.toString
  }

  import scala.language.implicitConversions

  protected implicit def toInt(n: Z): scala.Int = n match {
    case n: Z.MP.Long => n.value.toInt
    case n: Z.MP.BigInt => n.value.toInt
  }

  protected implicit def toLong(n: Z): scala.Long = n match {
    case n: Z.MP.Long => n.value
    case n: Z.MP.BigInt => n.value.toLong
  }
}

trait HasBoxer extends Any {
  def boxer: Boxer
}

object IdentityBoxer extends Boxer {
  def box[T](o: scala.Any): T = o.asInstanceOf[T]

  def unbox(o: scala.Any): scala.Any = o
}

