// #Sireum
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

package org.sireum

object Map {

  @pure def empty[K, T]: Map[K, T] = {
    return Map[K, T](ISZ())
  }

  @pure def of[K, T]: Map[K, T] = {
    return Map.empty
  }

  @pure def ++[K, T, I](s: IS[I, (K, T)]): Map[K, T] = {
    return Map.empty[K, T] ++ s
  }

}

@datatype class Map[K, T](val entries: ISZ[(K, T)]) {

  @pure def keys: ISZ[K] = {
    var r = ISZ[K]()
    for (kv <- entries) {
      r = r :+ kv._1
    }
    return r
  }

  @pure def values: ISZ[T] = {
    var r = ISZ[T]()
    for (kv <- entries) {
      r = r :+ kv._2
    }
    return r
  }

  @pure def keySet: Set[K] = {
    return Set.empty[K] ++ keys
  }

  @pure def valueSet: Set[T] = {
    return Set.empty[T] ++ values
  }

  @pure def +(p: (K, T)): Map[K, T] = {
    val (key, value) = p
    val index = indexOf(key)
    val newEntries: ISZ[(K, T)] =
      if (index < 0) entries :+ ((key, value))
      else entries((index, (key, value)))
    return Map(newEntries)
  }

  @pure def ++[I](kvs: IS[I, (K, T)]): Map[K, T] = {
    var r = this
    for (kv <- kvs) {
      r = r + kv._1 ~> kv._2
    }
    return r
  }

  @pure def get(key: K): Option[T] = {
    val index = indexOf(key)
    return if (index < 0) None[T]() else Some(entries(index)._2)
  }

  @pure def getOrElse(key: K, default: => T): T = {
    val index = indexOf(key)
    return if (index < 0) default else entries(index)._2
  }

  @pure def getOrElseEager(key: K, default: T): T = {
    val index = indexOf(key)
    return if (index < 0) default else entries(index)._2
  }

  @pure def entry(key: K): Option[(K, T)] = {
    val index = indexOf(key)
    return if (index < 0) None[(K, T)]() else Some(entries(index))
  }

  @pure def indexOf(key: K): Z = {
    var index = z"-1"
    for (i <- entries.indices if index == z"-1") {
      if (entries(i)._1 == key) {
        index = i
      }
    }
    return index
  }

  @pure def --[I](keys: IS[I, K]): Map[K, T] = {
    var deletedMappings = ISZ[(K, T)]()
    for (key <- keys) {
      get(key) match {
        case Some(value) => deletedMappings = deletedMappings :+ ((key, value))
        case _ =>
      }
    }
    if (deletedMappings.nonEmpty) {
      return Map(entries -- deletedMappings)
    } else {
      return this
    }
  }

  @pure def -(p: (K, T)): Map[K, T] = {
    return Map(entries - p)
  }

  @pure def contains(key: K): B = {
    return indexOf(key) >= 0
  }

  @pure def isEmpty: B = {
    return size == z"0"
  }

  @pure def nonEmpty: B = {
    return size != z"0"
  }

  @pure def size: Z = {
    return entries.size
  }

  @pure override def string: String = {
    val r = st"""{
    |  ${(for (e <- entries) yield st"${e._1} -> ${e._2}", ",\n")}
    |}"""
    return r.render
  }

  @pure override def hash: Z = {
    return entries.size
  }

  @pure def isEqual(other: Map[K, T]): B = {
    if (size != other.size) {
      return F
    }

    var seen = Set.empty[K]
    for (kv <- entries) {
      val k = kv._1
      seen = seen + k
      other.get(k) match {
        case Some(v) =>
          if (v != kv._2) {
            return F
          }
        case _ => return F
      }
    }
    for (kv <- other.entries) {
      val k = kv._1
      if (!seen.contains(k)) {
        get(k) match {
          case Some(v) =>
            if (v != kv._2) {
              return F
            }
          case _ => return F
        }
      }
    }

    return T
  }
}
