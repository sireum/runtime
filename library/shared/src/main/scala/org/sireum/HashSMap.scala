// #Sireum
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

object HashSMap {

  @strictpure def empty[K, T]: HashSMap[K, T] = HashSMap(HashMap.empty, ISZ())

  @strictpure def emptyInit[K, T](initialCapacity: Z): HashSMap[K, T] =
    HashSMap(HashMap.emptyInit(initialCapacity), ISZ())

  @pure def ++[@index I, K, T](s: IS[I, (K, T)]): HashSMap[K, T] = {
    return HashSMap.emptyInit[K, T](s.size) ++ s
  }

}

@datatype class HashSMap[K, T](val map: HashMap[K, T], val keys: ISZ[K]) {

  @pure def size: Z = {
    return keys.size
  }

  @pure def entries: ISZ[(K, T)] = {
    return for (k <- keys) yield (k, map.get(k).get)
  }

  @pure def values: ISZ[T] = {
    return for (k <- keys) yield map.get(k).get
  }

  @pure def keySet: HashSSet[K] = {
    return HashSSet.empty[K] ++ keys
  }

  @pure def valueSet: HashSSet[T] = {
    return HashSSet.empty[T] ++ values
  }

  @pure def +(p: (K, T)): HashSMap[K, T] = {
    val newMap = map + p
    return HashSMap(newMap, if (newMap.size == map.size) keys else keys :+ p._1)
  }

  @pure def ++[@index I](entries: IS[I, (K, T)]): HashSMap[K, T] = {
    if (entries.isEmpty) {
      return this
    }
    var newMap = map
    var newKeys = keys
    for (kv <- entries) {
      val oldNewMapSize = newMap.size
      newMap = newMap + kv
      if (newMap.size != oldNewMapSize) {
        newKeys = newKeys :+ kv._1
      }
    }
    return HashSMap(newMap, newKeys)
  }

  @pure def get(key: K): Option[T] = {
    return map.get(key)
  }

  @pure def entry(key: K): Option[(K, T)] = {
    return map.entry(key)
  }

  @pure def --(keys: ISZ[K]): HashSMap[K, T] = {
    return HashSMap(map -- keys, this.keys -- keys)
  }

  @pure def -(p: (K, T)): HashSMap[K, T] = {
    return HashSMap(map - p, keys - p._1)
  }

  @pure def contains(key: K): B = {
    return map.contains(key)
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
    return entries.hash
  }

  @pure def isEqual(other: HashSMap[K, T]): B = {
    return map.isEqual(other.map)
  }

}
