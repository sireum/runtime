// #Sireum
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

package org.sireum

object HashMap {

  @strictpure def empty[K, T]: HashMap[K, T] = emptyInit[K, T](12)

  @strictpure def emptyInit[K, T](initialCapacity: Z): HashMap[K, T] = {
    val sz: Z = if (initialCapacity <= 0) 4 else initialCapacity * 4 / 3 + 1
    HashMap[K, T](ISZ.create(sz, Map.empty), 0)
  }

  @pure def ++[@index I, K, T](s: IS[I, (K, T)]): HashMap[K, T] = {
    return HashMap.emptyInit[K, T](s.size) ++ s
  }

}

@datatype class HashMap[K, T](val mapEntries: ISZ[Map[K, T]], val size: Z) {

  @pure def entries: ISZ[(K, T)] = {
    var r = ISZ[(K, T)]()
    for (ms <- mapEntries) {
      if (ms.nonEmpty) {
        r = r ++ ms.entries
      }
    }
    return r
  }

  @pure def keys: ISZ[K] = {
    var r = ISZ[K]()
    for (ms <- mapEntries) {
      if (ms.nonEmpty) {
        r = r ++ ms.keys
      }
    }
    return r
  }

  @pure def values: ISZ[T] = {
    var r = ISZ[T]()
    for (ms <- mapEntries) {
      if (ms.nonEmpty) {
        r = r ++ ms.values
      }
    }
    return r
  }

  @pure def keySet: HashSet[K] = {
    return HashSet.empty[K] ++ keys
  }

  @pure def valueSet: HashSet[T] = {
    return HashSet.empty[T] ++ values
  }

  @pure def +(p: (K, T)): HashMap[K, T] = {
    val (key, value) = p
    val r = ensureCapacity(size + 1)
    val i = r.hashIndex(key)
    val m = r.mapEntries(i)
    val newSize: Z = if (m.contains(key)) size else size + 1
    return r(mapEntries = r.mapEntries(i ~> (m + key ~> value)), size = newSize)
  }

  @pure def ++[@index I](entries: IS[I, (K, T)]): HashMap[K, T] = {
    if (entries.isEmpty) {
      return this
    }
    var r = ensureCapacity(size + entries.size)
    for (kv <- entries) {
      r = r + kv._1 ~> kv._2
    }
    return r
  }

  @pure def ensureCapacity(sz: Z): HashMap[K, T] = {
    if (mapEntries.size * 3 / 4 >= sz) {
      return this
    }
    val init = sz * 2
    var r = HashMap.emptyInit[K, T](init)
    for (ms <- mapEntries) {
      for (kv <- ms.entries) {
        r = r + kv._1 ~> kv._2
      }
    }
    return r
  }

  @pure def hashIndex(key: K): Z = {
    val sz = mapEntries.size
    val i = key.hash % sz
    return if (i < 0) i + sz else i
  }

  @pure def get(key: K): Option[T] = {
    val m = mapEntries(hashIndex(key))
    return m.get(key)
  }

  @pure def entry(key: K): Option[(K, T)] = {
    val m = mapEntries(hashIndex(key))
    return m.entry(key)
  }

  @pure def --[@index I](keys: IS[I, K]): HashMap[K, T] = {
    var r = this
    for (k <- keys) {
      r.get(k) match {
        case Some(v) => r = r - k ~> v
        case _ =>
      }
    }
    return r
  }

  @pure def -(p: (K, T)): HashMap[K, T] = {
    val (key, value) = p
    val i = hashIndex(key)
    return this(mapEntries(i ~> (mapEntries(i) - key ~> value)), size - 1)
  }

  @pure def contains(key: K): B = {
    return get(key).nonEmpty
  }

  @pure def isEmpty: B = {
    return size == z"0"
  }

  @pure def nonEmpty: B = {
    return size != z"0"
  }

  @pure override def string: String = {
    val r =
      st"""{
      |  ${(for (e <- entries) yield st"${e._1} -> ${e._2}", ",\n")}
      |}"""
    return r.render
  }

  @pure override def hash: Z = {
    return size
  }

  @pure def isEqual(other: HashMap[K, T]): B = {
    if (size != other.size) {
      return F
    }

    var comparedKeys = ISZ[K]()
    for (ms <- mapEntries) {
      for (kv <- ms.entries) {
        val k = kv._1
        comparedKeys = comparedKeys :+ k
        other.get(k) match {
          case Some(v) =>
            if (kv._2 != v) {
              return F
            }
          case _ => return F
        }
      }
    }
    for (ms <- (other -- comparedKeys).mapEntries) {
      for (kv <- ms.entries) {
        val k = kv._1
        get(k) match {
          case Some(v) =>
            if (kv._2 != v) {
              return F
            }
          case _ => return F
        }
      }
    }

    return T
  }
}
