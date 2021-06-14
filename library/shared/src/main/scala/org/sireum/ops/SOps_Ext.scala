/*
 Copyright (c) 2017-2021, Robby, Kansas State University
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
import org.sireum.$internal.CollectionCompat.ParConverters._

object ISOps_Ext {
  val MinimumParallelThreshold: Int = 8

  def mParMap[I, V, U](s: IS[I, V], f: V => U): IS[I, U] = {
    val elements = s.elements
    val ies = elements.indices.zip(elements)
    val t = Thread.currentThread
    val irs =
      if (ies.size >= MinimumParallelThreshold) $internal.Macro.parMap(ies, { p: (Int, V) =>
        if (t.isInterrupted) (p._1, null) else (p._1, f(p._2))
      }) else ies.map { p => (p._1, f(p._2))}
    if (Thread.interrupted()) throw new InterruptedException
    val a = new Array[scala.Any](elements.length)
    irs.foreach { p => a(p._1) = p._2 }
    IS[I, U](irs.map(_._2.asInstanceOf[U]).toSeq: _*)(s.companion)
  }

  def mParMapFoldLeft[I, V, U, R](s: IS[I, V], f: V => U, g: (R, U) => R, init: R): R =
    mParMap(s, f).elements.foldLeft(init)((r, u) => if (u == null) r else g(r, u))

  def mParMapFoldRight[I, V, U, R](s: IS[I, V], f: V => U, g: (R, U) => R, init: R): R =
    mParMap(s, f).elements.foldRight(init)((u, r) => if (u == null) r else g(r, u))

  @pure def sortWith[I, V](s: IS[I, V], lt: (V, V) => B): IS[I, V] = {
    val es = s.elements.sortWith((e1, e2) => lt(e1, e2).value)
    val a = s.boxer.create(s.length)
    var i = Z.MP.zero
    for (e <- es) {
      s.boxer.store(a, i, e)
      i = i.increase
    }
    new IS[I, V](s.companion, a, s.length, s.boxer)
  }
}

object ISZOpsUtil_Ext {
  def mParMap[V, U](s: IS[Z, V], f: V => U): IS[Z, U] = ISOps_Ext.mParMap(s, f)

  def parMap[V, U](s: IS[Z, V], f: V => U): IS[Z, U] = ISOps_Ext.mParMap(s, f)

  def mParMapFoldLeft[V, U, R](s: IS[Z, V], f: V => U, g: (R, U) => R, init: R): R = ISOps_Ext.mParMapFoldLeft(s, f, g, init)

  def parMapFoldLeft[V, U, R](s: IS[Z, V], f: V => U, g: (R, U) => R, init: R): R = ISOps_Ext.mParMapFoldLeft(s, f, g, init)

  def mParMapFoldRight[V, U, R](s: IS[Z, V], f: V => U, g: (R, U) => R, init: R): R = ISOps_Ext.mParMapFoldRight(s, f, g, init)

  def parMapFoldRight[V, U, R](s: IS[Z, V], f: V => U, g: (R, U) => R, init: R): R = ISOps_Ext.mParMapFoldRight(s, f, g, init)

  @pure def sortWith[V](s: IS[Z, V], lt: (V, V) => B): IS[Z, V] = ISOps_Ext.sortWith(s, lt)
}

object MSOps_Ext {

  def mParMap[I, V, U](s: MS[I, V], f: V => U): MS[I, U] = {
    val elements = s.elements
    val ies = elements.indices.zip(elements)
    val irs =
      if (ies.size >= ISOps_Ext.MinimumParallelThreshold) $internal.Macro.parMap(ies, { p: (Int, V) => (p._1, f(p._2)) })
      else ies.map { p => (p._1, f(p._2))}
    val a = new Array[scala.Any](elements.length)
    irs.foreach { p => a(p._1) = p._2 }
    MS[I, U](irs.map(_._2.asInstanceOf[U]).toSeq: _*)(s.companion)
  }

  def mParMapFoldLeft[I, V, U, R](s: MS[I, V], f: V => U, g: (R, U) => R, init: R): R =
    mParMap(s, f).elements.foldLeft(init)((r, u) => g(r, u))

  def mParMapFoldRight[I, V, U, R](s: MS[I, V], f: V => U, g: (R, U) => R, init: R): R =
    mParMap(s, f).elements.foldRight(init)((u, r) => if (u == null) r else g(r, u))

  @pure def sortWith[I, V](s: MS[I, V], lt: (V, V) => B): MS[I, V] = {
    val es = s.elements.sortWith((e1, e2) => lt(e1, e2).value)
    val a = s.boxer.create(s.length)
    var i = Z.MP.zero
    for (e <- es) {
      s.boxer.store(a, i, helper.cloneAssign(e))
      i = i.increase
    }
    new MS[I, V](s.companion, a, s.length, s.boxer)
  }
}

object MSZOpsUtil_Ext {
  def mParMap[V, U](s: MS[Z, V], f: V => U): MS[Z, U] = MSOps_Ext.mParMap(s, f)

  def parMap[V, U](s: MS[Z, V], f: V => U): MS[Z, U] = MSOps_Ext.mParMap(s, f)

  def mParMapFoldLeft[V, U, R](s: MS[Z, V], f: V => U, g: (R, U) => R, init: R): R = MSOps_Ext.mParMapFoldLeft(s, f, g, init)

  def parMapFoldLeft[V, U, R](s: MS[Z, V], f: V => U, g: (R, U) => R, init: R): R = MSOps_Ext.mParMapFoldLeft(s, f, g, init)

  def mParMapFoldRight[V, U, R](s: MS[Z, V], f: V => U, g: (R, U) => R, init: R): R = MSOps_Ext.mParMapFoldRight(s, f, g, init)

  def parMapFoldRight[V, U, R](s: MS[Z, V], f: V => U, g: (R, U) => R, init: R): R = MSOps_Ext.mParMapFoldRight(s, f, g, init)

  @pure def sortWith[V](s: MS[Z, V], lt: (V, V) => B): MS[Z, V] = MSOps_Ext.sortWith(s, lt)
}
