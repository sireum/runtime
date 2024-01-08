/*
 Copyright (c) 2017-2024, Robby, Kansas State University
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

import org.sireum.$internal.{Boxer, MSMarker}

object MS {

  class WithFilter[@index I, V](ms: MS[I, V], p: V => B) {
    def foreach[U](f: V => U): Unit = {
      var i = Z.MP.zero
      while (i < ms.length) {
        val v = ms.boxer.lookup[V](ms.data, i)
        if (p(v)) {
          f(v)
        }
        i = i.increase
      }
    }

    def map[V2](f: V => V2): MS[I, V2] =
      if (ms.isEmpty) MS[I, V2]()(ms.companion)
      else {
        var a: AnyRef = Array()
        var boxer2: Boxer = null
        var i = Z.MP.zero
        var j = Z.MP.zero
        while (i < ms.length) {
          val v = ms.boxer.lookup[V](ms.data, i)
          if (p(v)) {
            val v2 = f(v)
            if (boxer2 == null) {
              boxer2 = Boxer.boxer(v2)
              a = boxer2.create(ms.length)
            }
            boxer2.store(a, j, helper.assign(v2))
            j = j.increase
          }
          i = i.increase
        }
        MS[I, V2](ms.companion, a, j, if (boxer2 == null) $internal.IdentityBoxer else boxer2)
      }

    def flatMap[V2](f: V => MS[I, V2]): MS[I, V2] =
      if (ms.isEmpty) MS[I, V2]()(ms.companion)
      else {
        val es = ms.elements
        var r = f(es.head)
        for (e <- es.tail if p(e)) {
          r = r ++ f(e)
        }
        r
      }

    def withFilter(p2: V => B): WithFilter[I, V] = new WithFilter[I, V](ms, v => p(v) && p2(v))
  }

  def checkSize[@index I](size: Z)(implicit companion: $ZCompanion[I]): Unit = {
    assert(Z.MP.zero <= size, s"Slang MS requires a non-negative size.")
    assert(
      !companion.hasMax || companion.Index.asInstanceOf[ZLike[_]].toMP + size - 1 <= companion.Max
        .asInstanceOf[ZLike[_]]
        .toMP,
      s"Slang IS requires its index (${companion.Index}) plus its size ($size) less than or equal to its Max (${companion.Max}) plus one."
    )
  }

  def apply[@index I, V](args: V*)(implicit companion: $ZCompanion[I]): MS[I, V] = {
    MS.checkSize(Z.MP(args.length))(companion)
    val boxer = Boxer.boxerSeq(args)
    val length = Z.MP(args.length)
    val a = boxer.create(length)
    var i = Z.MP.zero
    for (arg <- args) {
      boxer.store(a, i, helper.assign(arg))
      i = i.increase
    }
    MS[I, V](companion, a, length, boxer)
  }

  def create[@index I, V](size: Z, default: V)(implicit companion: $ZCompanion[I]): MS[I, V] = {
    val length = size
    MS.checkSize(length)(companion)
    val boxer = Boxer.boxer(default)
    val a = boxer.create(length)
    var i = Z.MP.zero
    while (i < length) {
      boxer.store(a, i, helper.assign(default))
      i = i.increase
    }
    MS[I, V](companion, a, length, boxer)
  }

  def apply[@index I, V](companion: $ZCompanion[I], data: scala.AnyRef, length: Z, boxer: Boxer): MS[I, V] =
    new MS[I, V](companion, data, length, boxer)

  def unapplySeq[@index I, V](o: MS[I, V]): scala.Option[scala.Seq[V]] = scala.Some(o.elements.map(helper.cloneAssign))
}

final class MS[@index I, V](val companion: $ZCompanion[I], val data: scala.AnyRef, val length: Z, val boxer: Boxer)
    extends Mutable with MSMarker with _root_.java.lang.Iterable[V] {

  private var isClonable: scala.Boolean = true
  private var isOwned: scala.Boolean = false

  override def $clonable: Boolean = isClonable

  override def $clonable_=(b: Boolean): this.type = {
    isClonable = false
    this
  }

  override def $owned: scala.Boolean = isOwned

  override def $owned_=(b: scala.Boolean): this.type = {
    isOwned = b
    this
  }

  def hash: Z = hashCode

  def $clone: MS[I, V] = {
    val a = boxer.cloneMut(data, length, length, Z.MP.zero)
    MS[I, V](companion, a, length, boxer)
  }

  def isEmpty: B = length == Z.MP.zero

  def nonEmpty: B = length != Z.MP.zero

  def isInBound(i: I): B = {
    val iMP = i.asInstanceOf[ZLike[_]].toMP
    0 <= iMP && iMP < length
  }

  def :+(e: V): MS[I, V] =
    if (isEmpty) MS[I, V](e)(companion)
    else {
      val newLength = length.increase
      MS.checkSize(newLength)(companion)
      val a = boxer.cloneMut(data, length, newLength, Z.MP.zero)
      boxer.store(a, length, helper.assign(e))
      MS[I, V](companion, a, newLength, boxer)
    }

  def +:(e: V): MS[I, V] =
    if (isEmpty) MS[I, V](e)(companion)
    else {
      val newLength = length.increase
      MS.checkSize(newLength)(companion)
      val a = boxer.cloneMut(data, length, newLength, Z.MP.one)
      boxer.store(a, Z.MP.zero, helper.assign(e))
      MS[I, V](companion, a, newLength, boxer)
    }

  def ++[@index I2](other: MS[I2, V]): MS[I, V] = {
    val bxr = if (isEmpty) other.boxer else boxer
    if (other.length == Z.MP.zero) return this
    val newLength = length + other.length
    MS.checkSize(newLength)(companion)
    val a = bxr.cloneMut(data, length, newLength, Z.MP.zero)
    var i = length
    var j = Z.MP.zero
    while (i < newLength) {
      bxr.store(a, i, helper.assign(other.boxer.lookup[V](other.data, j)))
      i = i.increase
      j = j.increase
    }
    MS[I, V](companion, a, newLength, bxr)
  }

  def --[@index I2](other: MS[I2, V]): MS[I, V] =
    if (isEmpty || other.length == Z.MP.zero) this
    else {
      val otherElements = other.elements
      var sm = elements.withFilter(_ != otherElements.head)
      for (e <- other.elements.tail) {
        sm = sm.withFilter(_ != e)
      }
      val s = sm.map(identity)
      val newLength = Z.MP(s.size)
      val a = boxer.create(newLength)
      var i = Z.MP.zero
      for (e <- s) {
        boxer.store(a, i, helper.assign(e))
        i = i.increase
      }
      MS[I, V](companion, a, newLength, boxer)
    }

  def -(e: V): MS[I, V] = if (isEmpty) this else filter(_ != e)

  def ===(other: MS[I, V]): B =
    if (this eq other.asInstanceOf[scala.AnyRef]) true
    else
      other match {
        case other: MS[_, _] =>
          if (companion ne other.companion) return false
          if (length != other.length) return false
          if (data eq other.data) return true
          val b1 = boxer
          val b2 = other.boxer
          val data1 = data
          val data2 = other.data
          for (i <- Z.MP.zero until length) {
            val iMP = i.toMP
            if (b1.lookup[V](data1, iMP) =!= b2.lookup[V](data2, iMP)) return false
          }
          true
        case _ => false
      }

  @inline def =!=(other: MS[I, V]): B = !(this === other)

  def atZ(i: Z): V = {
    assert(Z.MP.zero <= i && i < length, s"Indexing out of bounds: $i")
    boxer.lookup[V](data, i)
  }

  def indices: ZRange[I] = {
    val j = companion.Index.asInstanceOf[ZLike[_]].add(length - 1, "indices", companion).asInstanceOf[I]
    ZRange[I](
      T,
      companion.Index,
      j,
      1,
      new ZRange.CondIncDec[I] {
        @pure def cond(i: I): B = T
        @pure override def increase(i: I): I = i.asInstanceOf[ZLike[_]].increase.asInstanceOf[I]
        @pure override def decrease(i: I): I = i.asInstanceOf[ZLike[_]].decrease.asInstanceOf[I]
      }
    )
  }

  def map[V2](f: V => V2): MS[I, V2] =
    if (isEmpty) MS[I, V2]()(companion)
    else {
      var a: AnyRef = Array()
      var boxer2: Boxer = null
      var i = Z.MP.zero
      while (i < length) {
        val v2 = f(boxer.lookup[V](data, i))
        if (boxer2 == null) {
          boxer2 = Boxer.boxer(v2)
          a = boxer2.create(length)
        }
        boxer2.store(a, i, helper.assign(v2))
        i = i.increase
      }
      MS[I, V2](companion, a, length, if (boxer2 == null) $internal.IdentityBoxer else boxer2)
    }

  def foreach[U](f: V => U): Unit = {
    var i = Z.MP.zero
    while (i < length) {
      f(boxer.lookup[V](data, i))
      i = i.increase
    }
  }

  def flatMap[V2](f: V => MS[I, V2]): MS[I, V2] =
    if (isEmpty) MS[I, V2]()(companion)
    else {
      val es = elements
      var r = f(es.head)
      for (e <- es.tail) {
        r = r ++ f(e)
      }
      r
    }

  def filter(p: V => B @pure): MS[I, V] =
    if (isEmpty) this
    else {
      val a: AnyRef = boxer.create(length)
      var i = Z.MP.zero
      var j = Z.MP.zero
      while (i < length) {
        val v = boxer.lookup[V](data, i)
        if (p(v)) {
          boxer.store(a, j, helper.assign(v))
          j = j.increase
        }
        i = i.increase
      }
      MS[I, V](companion, a, j, boxer)
    }

  def withFilter(p: V => B): MS.WithFilter[I, V] = new MS.WithFilter(this, p)

  def iterator(): _root_.java.util.Iterator[V] = new _root_.java.util.Iterator[V] {
    var i: scala.Int = 0
    val es: Seq[V] = elements

    override def next(): V = {
      assert(hasNext)
      val r = helper.cloneAssign(es(i))
      i = i + 1
      r
    }

    override def hasNext: scala.Boolean = i <= es.length
  }


  def size: Z = length

  def firstIndex: I = {
    assert(nonEmpty, "firstIndex can only be used on non-empty MS")
    companion.Index
  }

  def lastIndex: I = {
    assert(nonEmpty, "lastIndex can only be used on non-empty MS")
    if (companion.isZeroIndex) companion(length - 1)
    else companion.Min.asInstanceOf[ZLike[_]].add(length - 1, "lastIndex", companion).asInstanceOf[I]
  }

  def toIS[V2](implicit ev: V =:= V2): IS[I, V2] = {
    new IS[I, V2](companion, boxer.clone(data, length, length, Z.MP.zero), length, boxer)
  }

  def toISZ[V2](implicit ev: V =:= V2): ISZ[V2] =
    new IS[Z, V2]($ZCompanion, boxer.clone(data, length, length, Z.MP.zero), length, boxer)

  def toMSZ: MSZ[V] = MSZ(elements: _*)

  def apply(index: I): V = atZ(index.asInstanceOf[ZLike[_]].toIndex)

  def apply(args: (I, V)*): MS[I, V] = {
    val a = boxer.clone(data, length, length, Z.MP.zero)
    for ((index, v) <- args) {
      val i = index.asInstanceOf[ZLike[_]].toIndex
      assert(Z.MP.zero <= i && i < length, s"Indexing out of bounds: $index")
      boxer.store(a, i, v)
    }
    MS[I, V](companion, a, length, boxer)
  }

  def update(index: I, value: V): Unit = {
    val i = index.asInstanceOf[ZLike[_]].toIndex
    assert(Z.MP.zero <= i && i < length, s"Indexing out of bounds: $index")
    boxer.store(data, i, helper.assign(value))
  }

  def elements: scala.Seq[V] = {
    val r = new Array[Any](length.toInt)
    var i = Z.MP.zero
    while (i < length) {
      r(i.toInt) = boxer.lookup[V](data, i)
      i = i.increase
    }
    r.toSeq.asInstanceOf[scala.Seq[V]]
  }

  override def hashCode: scala.Int = {
    (companion, elements).hashCode
  }

  override def equals(other: scala.Any): scala.Boolean =
    if (this eq other.asInstanceOf[scala.AnyRef]) true
    else
      other match {
        case other: MS[_, _] =>
          if (companion ne other.companion) return false
          if (length != other.length) return false
          if (data eq other.data) return true
          val b1 = boxer
          val b2 = other.boxer
          val data1 = data
          val data2 = other.data
          for (i <- Z.MP.zero until length) {
            val iMP = i.toMP
            if (b1.lookup[V](data1, iMP) != b2.lookup[V](data2, iMP)) return false
          }
          true
        case _ => false
      }

  def string: String = toString

  override def toString: Predef.String = boxer.toString(data, length)
}
