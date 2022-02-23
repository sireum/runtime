// #Sireum
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

package org.sireum
import org.sireum.justification._
import org.sireum.justification.natded.pred.existsI

object Map {

  @strictpure def empty[K, T]: Map[K, T] = Map[K, T](ISZ())

  @strictpure def of[K, T]: Map[K, T] = Map.empty

  @strictpure def ++[K, T, I](s: IS[I, (K, T)]): Map[K, T] = Map.empty[K, T] ++ s

  @strictpure def uniqueKeys[K, T](entries: ISZ[(K, T)]): B =
    All(entries.indices)(i => All(entries.indices)(j => (i != j) ->: (entries(i)._1 != entries(j)._1)))

  @strictpure def containsKey[K, T](entries: ISZ[(K, T)], key: K): B = Exists(entries.indices)(j => key == entries(j)._1)
}

@datatype class Map[K, T](val entries: ISZ[(K, T)]) {

  // @spec def uniqueKey = Invariant(Map.uniqueKeys(entries))

  @pure def keys: ISZ[K] = {
    Contract(
      Ensures(
        Res[ISZ[K]].size == entries.size,
        All(Res[ISZ[K]].indices)(j => Res[ISZ[K]](j) == entries(j)._1)
      )
    )
    var r = ISZ[K]()
    var i = 0
    while (i < entries.size) {
      Invariant(
        Modifies(r, i),
        0 <= i,
        i <= entries.size,
        i == r.size,
        All(0 until i)(j => r(j) == entries(j)._1)
      )
      r = r :+ entries(i)._1
      i = i + 1
    }
    return r
  }

  @pure def values: ISZ[T] = {
    Contract(
      Ensures(
        Res[ISZ[T]].size == entries.size,
        All(Res[ISZ[T]].indices)(j => Res[ISZ[T]](j) == entries(j)._2)
      )
    )
    var r = ISZ[T]()
    var i = 0
    while (i < entries.size) {
      Invariant(
        Modifies(r, i),
        0 <= i,
        i <= entries.size,
        i == r.size,
        All(0 until i)(j => r(j) == entries(j)._2)
      )
      r = r :+ entries(i)._2
      i = i + 1
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
    Contract(
      Case(
        Requires(
          Map.containsKey(entries, p._1),
          Map.uniqueKeys(entries), // TODO: inv
        ),
        Ensures(
          Map.uniqueKeys(Res[Map[K, T]].entries),
          Map.containsKey(Res[Map[K, T]].entries, p._1),
          Res[Map[K, T]].entries.size == this.size,
          Exists(Res[Map[K, T]].entries.indices)(j => Res[Map[K, T]].entries(j) == p)
        )
      ),
      Case(
        Requires(
          !Map.containsKey(entries, p._1),
          Map.uniqueKeys(entries), // TODO: inv
        ),
        Ensures(
          Map.uniqueKeys(Res[Map[K, T]].entries),
          Map.containsKey(Res[Map[K, T]].entries, p._1),
          Res[Map[K, T]].entries.size == this.size + 1,
          Res[Map[K, T]].entries(Res[Map[K, T]].entries.size - 1) == p
        )
      )
    )
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
      r = r + kv
    }
    return r
  }

  @pure def get(key: K): Option[T] = {
    Contract(
      Case(
        Requires(Map.containsKey(entries, key)),
        Ensures(Exists(entries.indices)(j => Res == Some(entries(j)._2)))
      ),
      Case(
        Requires(!Map.containsKey(entries, key)),
        Ensures(Res == None[T]())
      )
    )
    val index = indexOf(key)
    val r: Option[T] = if (index < 0) None[T]() else Some(entries(index)._2)
    Deduce(contains(key) |- Exists(entries.indices)(j => r == Some(entries(j)._2)) Proof(
      //@formatter:off
      1 #> contains(key)                                                                          by Premise,
      2 #> (r == Some(entries(index)._2))                                                         by Premise,
      3 #> (((0 <= index) & (index < entries.size)) -->: (r == Some(entries(index)._2)))          by Auto(ISZ(2)),
      4 #> Exists{ j: Z => ((0 <= j) & (j < entries.size)) -->: (r == Some(entries(j)._2)) }      by existsI((j: Z) => ((0 <= j) & (j < entries.size)) -->: (r == Some(entries(j)._2)), index) and 3,
      5 #> Exists(entries.indices)(j => r == Some(entries(j)._2))                                 by Premise
      //@formatter:on
    ))
    return r
  }

  @pure def getOrElse(key: K, default: => T): T = {
    val index = indexOf(key)
    return if (index < 0) default else entries(index)._2
  }

  @pure def getOrElseEager(key: K, default: T): T = {
    Contract(
      Case(
        Requires(Map.containsKey(entries, key)),
        Ensures(Exists(entries.indices)(j => Res == entries(j)._2))
      ),
      Case(
        Requires(!Map.containsKey(entries, key)),
        Ensures(Res == default)
      )
    )
    val index = indexOf(key)
    return if (index < 0) default else entries(index)._2
  }

  @pure def entry(key: K): Option[(K, T)] = {
    Contract(
      Case(
        Requires(Map.containsKey(entries, key)),
        Ensures(Exists(entries.indices)(j => Res == Some(entries(j))))
      ),
      Case(
        Requires(!Map.containsKey(entries, key)),
        Ensures(Res == None[(K, T)]())
      )
    )
    val index = indexOf(key)
    val r: Option[(K, T)] = if (index < 0) None[(K, T)]() else Some(entries(index))
    Deduce(contains(key) |- Exists(entries.indices)(j => r == Some(entries(j))) Proof(
      //@formatter:off
      1 #> contains(key)                                                                          by Premise,
      2 #> (r == Some(entries(index)))                                                            by Premise,
      3 #> (((0 <= index) & (index < entries.size)) -->: (r == Some(entries(index))))             by Auto(ISZ(2)),
      4 #> Exists{ j: Z => ((0 <= j) & (j < entries.size)) -->: (r == Some(entries(j))) }         by existsI((j: Z) => ((0 <= j) & (j < entries.size)) -->: (r == Some(entries(j))), index) and 3,
      5 #> Exists(entries.indices)(j => r == Some(entries(j)))                                    by Premise
      //@formatter:on
    ))
    return r
  }

  @pure def indexOf(key: K): Z = {
    Contract(
      Case(
        Requires(Map.containsKey(entries, key)),
        Ensures(
          0 <= Res[Z],
          Res[Z] < entries.size,
          entries(Res[Z])._1 == key
        )
      ),
      Case(
        Requires(!Map.containsKey(entries, key)),
        Ensures(Res[Z] == -1)
      )
    )
    var index: Z = -1
    var i: Z = 0
    while (i < entries.size) {
      Invariant(
        Modifies(index, i),
        0 <= i,
        i <= entries.size,
        (index != -1) ->: (0 <= index & index < entries.size & entries(index)._1 == key),
        (index == -1) ->: (All(0 until i)(j => key != entries(j)._1))
      )
      if (entries(i)._1 == key) {
        index = i
        i = entries.size - 1
      }
      i = i + 1
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
    Contract(Ensures(Res == Exists(0 until entries.size)(j => key == entries(j)._1)))
    return indexOf(key) >= 0
  }

  @pure def isEmpty: B = {
    Contract(Ensures(Res == (entries.size == 0)))
    return size == z"0"
  }

  @pure def nonEmpty: B = {
    Contract(Ensures(Res != (entries.size == 0)))
    return size != z"0"
  }

  @pure def size: Z = {
    Contract(Ensures(Res == entries.size))
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
    val sz = size
    if (sz != other.size) {
      return F
    }
    var i = 0
    while (i < sz) {
      Invariant(
        Modifies(i),
        0 <= i,
        i <= sz
      )
      val kv = entries(i)
      val k = kv._1
      other.get(k) match {
        case Some(v) =>
          if (v != kv._2) {
            return F
          }
        case _ => return F
      }
      i = i + 1
    }

    return T
  }
}
