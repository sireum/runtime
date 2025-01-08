// #Sireum #Logika
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

object Map {

  @strictpure def empty[K, T]: Map[K, T] = Map[K, T](ISZ())

  @strictpure def of[K, T]: Map[K, T] = Map.empty

  @pure def ++[K, T, @index I](s: IS[I, (K, T)]): Map[K, T] = {
    return Map.empty[K, T] ++ s
  }

  @strictpure def entriesOf[K, T](m: Map[K, T]): Entries.Type[K, T] = m.entries

  object Entries {

    type Type[K, T] = AssocS.Entries.Type[K, T]

  }

}

@datatype class Map[K, T](val entries: Map.Entries.Type[K, T]) {

  @spec def uniqueKeys = Invariant(AssocS.Entries.uniqueKeys(entries))

  @pure def keys: ISZ[K] = {
    Contract(
      Ensures(
        entries.size == Res[ISZ[K]].size,
        ∀(entries.indices)(i => entries(i)._1 ≡ Res[ISZ[K]](i)),
        LibUtil.IS.unique(Res)
      )
    )
    return AssocS.Entries.keys(entries)
  }

  @pure def values: ISZ[T] = {
    Contract(
      Ensures(
        entries.size == Res[ISZ[T]].size,
        ∀(entries.indices)(i => entries(i)._2 ≡ Res[ISZ[T]](i))
      )
    )
    return AssocS.Entries.values(entries)
  }

  @pure def keySet: Set[K] = {
    return Set.empty[K] ++ keys
  }

  @pure def valueSet: Set[T] = {
    return Set.empty[T] ++ values
  }

  @pure def +(p: (K, T)): Map[K, T] = {
    Contract(
      Ensures(
        Map.entriesOf(Res).size == entries.size | Map.entriesOf(Res).size == entries.size + 1,
        AssocS.Entries.contain(Map.entriesOf(Res), p),
        ∀(Map.entriesOf(Res).indices)(j =>
          (Map.entriesOf(Res)(j) != p) ->: AssocS.Entries.contain(entries, Map.entriesOf(Res)(j))),
        ∀(entries.indices)(j =>
          (entries(j)._1 != p._1) ->: AssocS.Entries.contain(Map.entriesOf(Res), entries(j)))
      )
    )
    return Map(AssocS.Entries.add(entries, p))
  }

  @pure def ++[@index I](kvs: IS[I, (K, T)]): Map[K, T] = {
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
        Requires(AssocS.Entries.containKey(entries, key)),
        Ensures(∃(entries.indices)(j => (key == entries(j)._1) & (Some(entries(j)._2) ≡ Res)))
      ),
      Case(
        "Unmapped",
        Requires(!AssocS.Entries.containKey(entries, key)),
        Ensures(None[T]() ≡ Res)
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
        Requires(AssocS.Entries.containKey(entries, key)),
        Ensures(AssocS.Entries.contain(entries, (key, Res)))
      ),
      Case(
        "Unmapped",
        Requires(!AssocS.Entries.containKey(entries, key)),
        Ensures(default ≡ Res)
      )
    )
    val index = indexOf(key)
    return if (index < 0) default else entries(index)._2
  }

  @pure def entry(key: K): Option[(K, T)] = {
    Contract(
      Case(
        "Mapped",
        Requires(AssocS.Entries.containKey(entries, key)),
        Ensures(∃(entries.indices)(j => (Some(entries(j)) ≡ Res) & entries(j)._1 == key))
      ),
      Case(
        "Unmapped",
        Requires(!AssocS.Entries.containKey(entries, key)),
        Ensures(None[(K, T)]() ≡ Res)
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
        Requires(AssocS.Entries.containKey(entries, key)),
        Ensures(
          0 <= Res[Z],
          Res[Z] < entries.size,
          entries(Res[Z])._1 == key
        )
      ),
      Case(
        "Unmapped",
        Requires(!AssocS.Entries.containKey(entries, key)),
        Ensures(Res[Z] == -1)
      )
    )
    return AssocS.Entries.indexOf(entries, key)
  }

  @pure def --[@index I](keys: IS[I, K]): Map[K, T] = {
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
        Map.entriesOf(Res).size == entries.size | Map.entriesOf(Res).size == entries.size - 1,
        ∀(Map.entriesOf(Res).indices)(j =>
          Map.entriesOf(Res)(j) != p & AssocS.Entries.contain(entries, Map.entriesOf(Res)(j))),
        ∀(entries.indices)(j =>
          (entries(j) != p) ->: AssocS.Entries.contain(Map.entriesOf(Res), entries(j)))
      )
    )
    return Map(AssocS.Entries.remove(entries, p))
  }

  @pure def contains(key: K): B = {
    Contract(Ensures(AssocS.Entries.containKey(entries, key) == Res))
    return indexOf(key) >= 0
  }

  @pure def isEmpty: B = {
    Contract(Ensures((entries.size == 0) == Res))
    return size == z"0"
  }

  @pure def nonEmpty: B = {
    Contract(Ensures((entries.size == 0) != Res))
    return size != z"0"
  }

  @pure def size: Z = {
    Contract(Ensures(entries.size == Res))
    return entries.size
  }

  @pure override def string: String = {
    val r = st"""{
    |  ${(for (e <- entries) yield st"${e._1} -> ${e._2}", ",\n")}
    |}"""
    return r.render
  }

  @pure override def hash: Z = {
    return entries.hash
  }

  @pure def isEqual(other: Map[K, T]): B = {
    Contract(
      Case(
        "Equal",
        Requires(
          entries.size == other.entries.size,
          ∀(entries.indices)(j => AssocS.Entries.contain(other.entries, entries(j)))
        ),
        Ensures(Res[B])
      ),
      Case(
        "Inequal-diff-key",
        Requires(
          entries.size == other.entries.size,
          ∃(entries.indices)(j => !AssocS.Entries.containKey(other.entries, entries(j)._1))
        ),
        Ensures(!Res[B])
      ),
      Case(
        "Inequal-diff-value",
        Requires(
          entries.size == other.entries.size,
          ∃(entries.indices)(j => ∀(other.entries.indices)(k => entries(j) != other.entries(k)))
        ),
        Ensures(!Res[B])
      ),
      Case(
        "Inequal-size",
        Requires(entries.size != other.entries.size),
        Ensures(!Res[B])
      )
    )
    val sz = size
    var r = T
    if (sz != other.size) {
      r = F
    } else {
      var i: Z = 0
      while (r & i < sz) {
        Invariant(
          Modifies(i, r),
          0 <= i,
          i <= sz,
          r ->: ∀(0 until i)(j => AssocS.Entries.contain(other.entries, entries(j))),
          !r ->: ∃(entries.indices)(j =>
            !AssocS.Entries.containKey(other.entries, entries(j)._1) |
              !AssocS.Entries.contain(other.entries, entries(j)))
        )
        val (key, v) = entries(i)
        val v2Opt = other.get(key)
        v2Opt match {
          case Some(v2) =>
            if (v2 != v) {
              r = F
              Deduce(|- (
                !r ->: ∃(entries.indices)(j =>
                  !AssocS.Entries.containKey(other.entries, entries(j)._1) |
                    !AssocS.Entries.contain(other.entries, entries(j))))
              )
            }
          case _ =>
            r = F
        }
        i = i + 1
      }
    }
    return r
  }
}
