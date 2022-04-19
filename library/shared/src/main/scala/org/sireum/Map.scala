// #Sireum #Logika
/*
 Copyright (c) 2017-2022, Robby, Kansas State University
 ∀ rights reserved.

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

  @strictpure def empty[K, T]: Map[K, T] = Map[K, T](ISZ())

  @strictpure def of[K, T]: Map[K, T] = Map.empty

  @strictpure def ++[K, T, I](s: IS[I, (K, T)]): Map[K, T] = Map.empty[K, T] ++ s

  @strictpure def entriesOf[K, T](m: Map[K, T]): ISZ[(K, T)] = m.entries

  object Entries {

    @strictpure def uniqueKeys[K, T](entries: ISZ[(K, T)]): B =
      ∀(entries.indices)(i => ∀(entries.indices)(j => (i != j) ->: (entries(i)._1 != entries(j)._1)))

    @strictpure def containKey[K, T](entries: ISZ[(K, T)], key: K): B = ∃(entries.indices)(j => key == entries(j)._1)

    @strictpure def keyIndexOfFrom[K, T](entries: ISZ[(K, T)], key: K, from: Z): Z =
      if (from < 0 | from >= entries.size) -1
      else if (entries(from)._1 == key) from
      else keyIndexOfFrom(entries, key, from + 1)

    @strictpure def valueIndexOfFrom[K, T](entries: ISZ[(K, T)], value: T, from: Z): Z =
      if (from < 0 | from >= entries.size) -1
      else if (entries(from)._2 == value) from
      else valueIndexOfFrom(entries, value, from + 1)

    @strictpure def indexOfFrom[K, T](entries: ISZ[(K, T)], kv: (K, T), from: Z): Z =
      if (from < 0 | from >= entries.size) -1
      else if (entries(from)== kv) from
      else indexOfFrom(entries, kv, from + 1)

  }

}

import SeqUtil.IS

@datatype class Map[K, T](val entries: ISZ[(K, T)]) {

  @spec def uniqueKeys = Invariant(Map.Entries.uniqueKeys(entries))

  @pure def keys: ISZ[K] = {
    Contract(
      Ensures(
        IS.pair1Eq(entries, Res),
        IS.unique(Res),
      )
    )
    var r = ISZ[K]()
    var i: Z = 0
    while (i < entries.size) {
      Invariant(
        Modifies(r, i),
        0 <= i,
        i <= entries.size,
        i == r.size,
        ∀(0 until i)(j => r(j) == entries(j)._1)
      )
      r = r :+ entries(i)._1
      i = i + 1
    }
    return r
  }

  @pure def values: ISZ[T] = {
    Contract(Ensures(IS.pair2Eq(entries, Res)))
    var r = ISZ[T]()
    var i: Z = 0
    while (i < entries.size) {
      Invariant(
        Modifies(r, i),
        0 <= i,
        i <= entries.size,
        i == r.size,
        ∀(0 until i)(j => r(j) == entries(j)._2)
      )
      r = r :+ entries(i)._2
      i = i + 1
    }
    return r
  }

  @strictpure def keySet: Set[K] = Set.empty[K] ++ keys

  @strictpure def valueSet: Set[T] =  Set.empty[T] ++ values

  @pure def +(p: (K, T)): Map[K, T] = {
    Contract(
      Case(
        "Mapped",
        Requires(Map.Entries.containKey(entries, p._1)),
        Ensures(
          Map.entriesOf(Res).size == size,
          Map.Entries.containKey(Map.entriesOf(Res), p._1),
          ∃(Map.entriesOf(Res).indices)(j => Map.entriesOf(Res)(j) == p)
        )
      ),
      Case(
        "Unmapped",
        Requires(!Map.Entries.containKey(entries, p._1)),
        Ensures(
          Map.entriesOf(Res).size == size + 1,
          Map.Entries.containKey(Map.entriesOf(Res), p._1),
          Map.entriesOf(Res)(Map.entriesOf(Res).size - 1) == p
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
        "Mapped",
        Requires(Map.Entries.containKey(entries, key)),
        Ensures(∃(entries.indices)(j => (key == entries(j)._1) & (Res == Some[T](entries(j)._2))))
      ),
      Case(
        "Unmapped",
        Requires(!Map.Entries.containKey(entries, key)),
        Ensures(Res == None[T]())
      )
    )
    val index = indexOf(key)
    val r: Option[T] = if (index < 0) None[T]() else Some(entries(index)._2)
    return r
  }

  @pure def getOrElse(key: K, default: => T): T = {
    val index = indexOf(key)
    return if (index < 0) default else entries(index)._2
  }

  @pure def getOrElseEager(key: K, default: T): T = {
    Contract(
      Case(
        "Mapped",
        Requires(Map.Entries.containKey(entries, key)),
        Ensures(∃(entries.indices)(j => Res == entries(j)._2))
      ),
      Case(
        "Unmapped",
        Requires(!Map.Entries.containKey(entries, key)),
        Ensures(Res == default)
      )
    )
    val index = indexOf(key)
    return if (index < 0) default else entries(index)._2
  }

  @pure def entry(key: K): Option[(K, T)] = {
    Contract(
      Case(
        "Mapped",
        Requires(Map.Entries.containKey(entries, key)),
        Ensures(∃(entries.indices)(j => Res == Some(entries(j))))
      ),
      Case(
        "Unmapped",
        Requires(!Map.Entries.containKey(entries, key)),
        Ensures(Res == None[(K, T)]())
      )
    )
    val index = indexOf(key)
    val r: Option[(K, T)] = if (index < 0) None[(K, T)]() else Some(entries(index))
    return r
  }

  @pure def indexOf(key: K): Z = {
    Contract(
      Case(
        "Mapped",
        Requires(Map.Entries.containKey(entries, key)),
        Ensures(
          0 <= Res[Z],
          Res[Z] < entries.size,
          entries(Res[Z])._1 == key
        )
      ),
      Case(
        "Unmapped",
        Requires(!Map.Entries.containKey(entries, key)),
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
        (index == -1) ->: ∀(0 until i)(j => key != entries(j)._1)
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
    Contract(
      Ensures(
        ∀(Res[Map[K, T]].entries.indices)(j => (Res[Map[K, T]].entries(j) != p) ->:
          ∃(entries.indices)(k => entries(k) == Res[Map[K, T]].entries(j)))
      )
    )
    var newEntries = ISZ[(K, T)]()
    var i = 0
    while (i < entries.size) {
      Invariant(
        Modifies(i, newEntries),
        0 <= i,
        i <= entries.size,
        ∀(newEntries.indices)(j => ∀(i until entries.size)(k => newEntries(j)._1 != entries(k)._1)),
        ∀(newEntries.indices)(j => (newEntries(j) != p) ->: ∃(entries.indices)(k => entries(k) == newEntries(j))),
        Map.Entries.uniqueKeys(newEntries),
      )
      val kv = entries(i)
      if (kv != p) {
        newEntries = newEntries :+ kv
      }
      i = i + 1
    }
    return Map(newEntries)
  }

  @pure def contains(key: K): B = {
    Contract(Ensures(Res == ∃(0 until entries.size)(j => key == entries(j)._1)))
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
    Contract(
      Case(
        "Equal",
        Requires(
          size == other.size,
          ∀(entries.indices)(j => ∃(0 until size)(k => entries(j) == other.entries(k))),
        ),
        Ensures(Res[B])
      ),
      Case(
        "Inequal-diff-key",
        Requires(
          size == other.size,
          ∃(entries.indices)(j => !Map.Entries.containKey(other.entries, entries(j)._1)),
        ),
        Ensures(!Res[B])
      ),
      Case(
        "Inequal-diff-value",
        Requires(
          size == other.size,
          ∃(entries.indices)(j => ∀(other.entries.indices)(k => entries(j) != other.entries(k))),
        ),
        Ensures(!Res[B])
      ),
      Case(
        "Inequal-size",
        Requires(size != other.size),
        Ensures(!Res[B])
      )
    )
    val sz = size
    if (sz != other.size) {
      return F
    } else {
      var i: Z = 0
      var r = T
      while (r & i < sz) {
        Invariant(
          Modifies(i, r),
          0 <= i,
          i <= sz,
          r ->: ∀(0 until i)(j => ∃(0 until sz)(k => entries(j) == other.entries(k))),
          !r ->: ∃(entries.indices)(j =>
            !Map.Entries.containKey(other.entries, entries(j)._1) |
              ∀(other.entries.indices)(k => entries(j) != other.entries(k)))
        )
        val (key, v) = entries(i)
        val v2Opt = other.get(key)
        v2Opt match {
          case Some(v2) =>
            if (v2 != v) {
              r = F
            }
          case _ =>
            r = F
        }
        i = i + 1
      }

      return r
    }
  }
}
