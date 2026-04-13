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
package org.sireum.$internal

import org.sireum._
import org.sireum.$internal.CollectionCompat.Converters._

object RuntimeEnv {
  private final case class Frame(parent: Frame, overrides: scala.collection.immutable.Map[Predef.String, Predef.String])

  private val emptyFrame = Frame(null, scala.collection.immutable.Map.empty)

  private val frameThreadLocal = new InheritableThreadLocal[Frame] {
    override def initialValue(): Frame = emptyFrame
  }

  private def frame: Frame = {
    val r = frameThreadLocal.get()
    if (r != null) r else emptyFrame
  }

  def beginOverrides(overrides: ISZ[(String, String)]): Unit = {
    val m = scala.collection.immutable.Map.empty[Predef.String, Predef.String] ++
      (for (p <- overrides.elements) yield (p._1.value, p._2.value))
    frameThreadLocal.set(Frame(frame, m))
  }

  def endOverrides(): Unit = {
    val current = frame
    frameThreadLocal.set(if (current.parent != null) current.parent else emptyFrame)
  }

  private def lookup(current: Frame, name: Predef.String): scala.Option[Predef.String] = {
    var f = current
    while (f != null) {
      f.overrides.get(name) match {
        case s @ scala.Some(_) => return s
        case _ =>
      }
      f = f.parent
    }
    scala.None
  }

  def env(name: Predef.String): Predef.String = {
    lookup(frame, name) match {
      case scala.Some(v) => v
      case _ =>
        val v = System.getenv(name)
        if (v != null) v else ""
    }
  }

  private def merged(): scala.collection.immutable.Map[Predef.String, Predef.String] = {
    val r = scala.collection.mutable.LinkedHashMap.empty[Predef.String, Predef.String]
    for ((k, v) <- System.getenv().asScala) {
      r(k) = v
    }
    def add(f: Frame): Unit = {
      if (f != null && (f ne emptyFrame)) {
        add(f.parent)
        for ((k, v) <- f.overrides) {
          r(k) = v
        }
      }
    }
    add(frame)
    r.toMap
  }

  def envs(): ISZ[(String, String)] = {
    var r = ISZ[(String, String)]()
    for ((k, v) <- merged()) {
      r = r :+ (String(k), String(v))
    }
    r
  }

  def envMap(): scala.collection.immutable.Map[Predef.String, Predef.String] = merged()
}
