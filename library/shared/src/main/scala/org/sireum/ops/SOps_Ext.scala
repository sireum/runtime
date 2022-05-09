/*
 Copyright (c) 2017-2022, Robby, Kansas State University
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

object ISOps_Ext {
  val MinimumParallelThreshold: Int = 2
  val poolRef: _root_.java.util.concurrent.atomic.AtomicReference[AnyRef] = new _root_.java.util.concurrent.atomic.AtomicReference(null)

  def invokeAny[R](fs: ISZ[() => Option[R]], default: () => R, isSequential: B): R = {
    val sz = fs.size.toInt

    def anySeq(): R = {
      for (f <- fs) {
        f() match {
          case Some(r) => return r
          case _ =>
        }
      }
      return default()
    }

    def anyPar(): R = {
      val pool = new _root_.java.util.concurrent.ForkJoinPool(sz)
      var r: R = null.asInstanceOf[R]
      try {
        val a = new _root_.java.util.ArrayList[_root_.java.util.concurrent.Callable[R]](sz)
        val count = new _root_.java.util.concurrent.atomic.AtomicInteger(0)
        for (f <- fs) {
          a.add(new _root_.java.util.concurrent.Callable[R] {
            override def call(): R = {
              val frOpt = f()
              count.incrementAndGet()
              frOpt match {
                case _root_.org.sireum.Some(fr) => return fr
                case _ =>
                  while (count.get() < a.size) {
                    try {
                      Thread.sleep(100)
                    } catch {
                      case _: Exception =>
                    }
                  }
                  throw new RuntimeException()
              }
            }
          })
        }
        r = pool.invokeAny(a)
        pool.shutdown()
      } catch {
        case _: Exception =>
      }
      return if (r == null) default() else r
    }

    if (isSequential) anySeq() else anyPar()
  }

  def mParMap[I, V, U](s: IS[I, V], f: V => U, numOfCores: Z = Runtime.getRuntime.availableProcessors): IS[I, U] = {
    val elements = s.elements
    val ies = elements.indices.zip(elements)
    val t = Thread.currentThread
    val cores = if (numOfCores >= 1) numOfCores.toInt else Runtime.getRuntime.availableProcessors
    val irs =
      if (ies.size >= MinimumParallelThreshold) $internal.Macro.parMap(poolRef, cores, ies, { p: (Int, V) =>
        if (t.isInterrupted) (p._1, null) else (p._1, f(p._2))
      }) else ies.map { p => (p._1, f(p._2)) }
    if (Thread.interrupted()) throw new InterruptedException
    val a = new Array[scala.Any](elements.length)
    irs.foreach { p => a(p._1) = p._2 }
    IS[I, U](irs.map(_._2.asInstanceOf[U]).toSeq: _*)(s.companion)
  }

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
  def invokeAny[R](fs: ISZ[() => Option[R]], default: () => R, isSequential: B): R = ISOps_Ext.invokeAny(fs, default, isSequential)

  def mParMap[V, U](s: IS[Z, V], f: V => U): IS[Z, U] = ISOps_Ext.mParMap(s, f)

  def parMap[V, U](s: IS[Z, V], f: V => U): IS[Z, U] = ISOps_Ext.mParMap(s, f)

  def mParMapCores[V, U](s: IS[Z, V], f: V => U, numOfCores: Z): IS[Z, U] = ISOps_Ext.mParMap(s, f, numOfCores)

  def parMapCores[V, U](s: IS[Z, V], f: V => U, numOfCores: Z): IS[Z, U] = ISOps_Ext.mParMap(s, f, numOfCores)

  @pure def sortWith[V](s: IS[Z, V], lt: (V, V) => B): IS[Z, V] = ISOps_Ext.sortWith(s, lt)
}

object MSOps_Ext {

  def mParMap[I, V, U](s: MS[I, V], f: V => U, numOfCores: Z = Runtime.getRuntime.availableProcessors): MS[I, U] = {
    val elements = s.elements
    val ies = elements.indices.zip(elements)
    val cores = if (numOfCores >= 1) numOfCores.toInt else Runtime.getRuntime.availableProcessors
    val irs =
      if (ies.size >= ISOps_Ext.MinimumParallelThreshold) $internal.Macro.parMap(ISOps_Ext.poolRef, cores, ies,
        { p: (Int, V) => (p._1, f(p._2)) })
      else ies.map { p => (p._1, f(p._2)) }
    val a = new Array[scala.Any](elements.length)
    irs.foreach { p => a(p._1) = p._2 }
    MS[I, U](irs.map(_._2.asInstanceOf[U]).toSeq: _*)(s.companion)
  }

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

  def mParMapCores[V, U](s: MS[Z, V], f: V => U, numOfCores: Z): MS[Z, U] = MSOps_Ext.mParMap(s, f, numOfCores)

  def parMapCores[V, U](s: MS[Z, V], f: V => U, numOfCores: Z): MS[Z, U] = MSOps_Ext.mParMap(s, f, numOfCores)

  @pure def sortWith[V](s: MS[Z, V], lt: (V, V) => B): MS[Z, V] = MSOps_Ext.sortWith(s, lt)
}
