// #Sireum #Logika
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

import org.sireum.justification.{Auto, Premise}

object AssocS {

  type Keys[K] = ISZ[K]
  type Values[V] = ISZ[V]

  @strictpure def empty[K, V]: AssocS[K, V] = AssocS[K, V](ISZ())

  @strictpure def of[K, V]: AssocS[K, V] = AssocS.empty

  @pure def ++[K, V, @index I](s: IS[I, (K, V)]): AssocS[K, V] = {
    return AssocS.empty[K, V] ++ s
  }

  @strictpure def entriesOf[K, V](m: AssocS[K, V]): Entries.Type[K, V] = m.entries

  object Entries {

    type Type[K, V] = ISZ[(K, V)]

    @strictpure def uniqueKeys[K, V](entries: Entries.Type[K, V]): B =
      ∀(entries.indices)(i => ∀(entries.indices)(j => (i != j) ->: (entries(i)._1 != entries(j)._1)))

    @strictpure def contain[K, V](entries: Entries.Type[K, V], kv: (K, V)): B = ∃(entries.indices)(j => kv == entries(j))

    @strictpure def containKey[K, V](entries: Entries.Type[K, V], key: K): B = ∃(entries.indices)(j => key == entries(j)._1)

    @strictpure def containValue[K, V](entries: Entries.Type[K, V], value: V): B = ∃(entries.indices)(j => value == entries(j)._2)

    @strictpure def keyIndexOfFrom[K, V](entries: Entries.Type[K, V], key: K, from: Z): Z =
      if (from < 0 | from >= entries.size) -1
      else if (entries(from)._1 == key) from
      else keyIndexOfFrom(entries, key, from + 1)

    @strictpure def valueIndexOfFrom[K, V](entries: Entries.Type[K, V], value: V, from: Z): Z =
      if (from < 0 | from >= entries.size) -1
      else if (entries(from)._2 == value) from
      else valueIndexOfFrom(entries, value, from + 1)

    @strictpure def indexOfFrom[K, V](entries: Entries.Type[K, V], kv: (K, V), from: Z): Z =
      if (from < 0 | from >= entries.size) -1
      else if (entries(from) == kv) from
      else indexOfFrom(entries, kv, from + 1)

    @pure def keys[K, V](entries: Entries.Type[K, V]): Keys[K] = {
      Contract(
        Requires(
          uniqueKeys(entries)
        ),
        Ensures(
          entries.size == Res[Keys[K]].size,
          ∀(entries.indices)(i => entries(i)._1 ≡ Res[Keys[K]](i)),
          LibUtil.IS.unique(Res)
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
          ∀(0 until i)(j => r(j) ≡ entries(j)._1)
        )
        r = r :+ entries(i)._1
        i = i + 1
      }
      return r
    }

    @pure def values[K, V](entries: Entries.Type[K, V]): Values[V] = {
      Contract(
        Requires(
          uniqueKeys(entries)
        ),
        Ensures(
          entries.size == Res[Values[V]].size,
          ∀(entries.indices)(i => entries(i)._2 ≡ Res[Values[V]](i))
        )
      )
      var r = ISZ[V]()
      var i: Z = 0
      while (i < entries.size) {
        Invariant(
          Modifies(r, i),
          0 <= i,
          i <= entries.size,
          i == r.size,
          ∀(0 until i)(j => r(j) ≡ entries(j)._2)
        )
        r = r :+ entries(i)._2
        i = i + 1
      }
      return r
    }

    @strictpure def addPost[K, V](entries: Entries.Type[K, V], p: (K, V), res: Entries.Type[K, V]): B =
      (res.size == entries.size | res.size == entries.size + 1) & contain(res, p) &
        ∀(res.indices)(j => (res(j) != p) ->: contain(entries, res(j))) &
        ∀(entries.indices)(j => (entries(j)._1 != p._1) ->: contain(res, entries(j)))

    @pure def add[K, V](entries: Entries.Type[K, V], p: (K, V)): Entries.Type[K, V] = {
      Contract(
        Requires(
          uniqueKeys(entries)
        ),
        Ensures(
          uniqueKeys(Res),
          addPost(entries, p, Res)
        )
      )
      val (key, value) = p
      val index = indexOf(entries, key)
      val newEntries: AssocS.Entries.Type[K, V] = if (index < 0) {
        val r = entries :+ ((key, value))
        Deduce(
          //@formatter:off
          r(r.size - 1) ≡ p                                                   by Auto,
          contain(r, p)                                                       by Auto,
          uniqueKeys(r)                                                       by Auto,
          ∀(r.indices)(j => (r(j) != p) ->: contain(entries, r(j)))           by Auto,
          ∀(entries.indices)(j => contain(r, entries(j)))                     by Auto
          //@formatter:on
        )
        r
      } else {
        val r = entries(index ~> p)
        Deduce(
          //@formatter:off
          r(index) ≡ p                                                        by Auto,
          contain(r, p)                                                       by Auto,
          uniqueKeys(r)                                                       by Auto,
          ∀(r.indices)(j => (r(j) != p) ->: contain(entries, r(j)))           by Auto,
          ∀(entries.indices)(j => (index != j) ->: contain(r, entries(j)))    by Auto
          //@formatter:on
        )
        r
      }
      return newEntries
    }

    @pure def indexOf[K, V](entries: Entries.Type[K, V], key: K): Z = {
      Contract(
        Case(
          "Mapped",
          Requires(
            uniqueKeys(entries),
            containKey(entries, key)
          ),
          Ensures(
            0 <= Res[Z],
            Res[Z] < entries.size,
            entries(Res[Z])._1 == key
          )
        ),
        Case(
          "Unmapped",
          Requires(
            uniqueKeys(entries),
            !containKey(entries, key)
          ),
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
          (index != -1) ->: ((0 <= index & index < entries.size) && entries(index)._1 == key),
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

    @pure def remove[K, V](entries: Entries.Type[K, V], p: (K, V)): Entries.Type[K, V] = {
      Contract(
        Requires(
          uniqueKeys(entries)
        ),
        Ensures(
          uniqueKeys(Res),
          Res[Entries.Type[K, V]].size == entries.size | Res[Entries.Type[K, V]].size == entries.size - 1,
          ∀(Res[Entries.Type[K, V]].indices)(j => Res[Entries.Type[K, V]](j) != p & contain(entries, Res[Entries.Type[K, V]](j))),
          ∀(entries.indices)(j => (entries(j) != p) ->: contain(Res[Entries.Type[K, V]], entries(j)))
        )
      )
      var newEntries = ISZ[(K, V)]()
      var i: Z = 0
      while (i < entries.size) {
        Invariant(
          Modifies(i, newEntries),
          0 <= i,
          i <= entries.size,
          ∀(newEntries.indices)(j => ∀(i until entries.size)(k => newEntries(j)._1 != entries(k)._1)),
          ∀(newEntries.indices)(j => newEntries(j) != p & AssocS.Entries.contain(entries, newEntries(j))),
          ∃(0 until i)(j => p == entries(j)) ->: (newEntries.size == i - 1),
          ∀(0 until i)(j => p != entries(j)) ->: (newEntries.size == i),
          ∀(0 until i)(j => (p != entries(j)) ->: AssocS.Entries.contain(newEntries, entries(j))),
          uniqueKeys(newEntries)
        )
        val kv = entries(i)
        i = i + 1
        if (kv != p) {
          newEntries = newEntries :+ kv
          Deduce(
            ⊢ (∀(newEntries.indices)(j => newEntries(j) != p & AssocS.Entries.contain(entries, newEntries(j)))),
            ⊢ (∀(0 until i)(j => (p != entries(j)) ->: AssocS.Entries.contain(newEntries, entries(j)))),
            ⊢ (∃(0 until i)(j => p == entries(j)) ->: (newEntries.size == i - 1)),
            ⊢ (∀(0 until i)(j => p != entries(j)) ->: (newEntries.size == i)),
            ⊢ (uniqueKeys(newEntries))
          )
        } else {
          Deduce(
            ⊢ (∀(newEntries.indices)(j => newEntries(j) != p & AssocS.Entries.contain(entries, newEntries(j)))),
            ⊢ (∀(0 until i)(j => (p != entries(j)) ->: AssocS.Entries.contain(newEntries, entries(j)))),
            ⊢ (∃(0 until i)(j => p == entries(j)) ->: (newEntries.size == i - 1)),
            ⊢ (∀(0 until i)(j => p != entries(j)) ->: (newEntries.size == i)),
            ⊢ (uniqueKeys(newEntries))
          )
        }
        Deduce(
          //@formatter:off
          (kv != p) -->: (newEntries ≡ (At(newEntries, 1) :+ kv))                                                by Premise,
          !(kv != p) -->: (newEntries ≡ At(newEntries, 1))                                                       by Premise,
          ∀(newEntries.indices)(j => newEntries(j) != p & AssocS.Entries.contain(entries, newEntries(j)))        by Premise,
          ∀(0 until i)(j => (p != entries(j)) ->: AssocS.Entries.contain(newEntries, entries(j)))                by Premise,
          ∃(0 until i)(j => p == entries(j)) ->: (newEntries.size == i - 1)                                      by Premise,
          ∀(0 until i)(j => p != entries(j)) ->: (newEntries.size == i)                                          by Premise,
          ∀(0 until i)(j => (p != entries(j)) ->: AssocS.Entries.contain(newEntries, entries(j)))                by Premise,
          uniqueKeys(newEntries)                                                                                 by Premise
          //@formatter:on
        )
      }
      return newEntries
    }

  }
}

@datatype class AssocS[K, V](val entries: AssocS.Entries.Type[K, V]) {

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

  @pure def values: ISZ[V] = {
    Contract(
      Ensures(
        entries.size == Res[ISZ[V]].size,
        ∀(entries.indices)(i => entries(i)._2 ≡ Res[ISZ[V]](i))
      )
    )
    return AssocS.Entries.values(entries)
  }

  @pure def keySet: Set[K] = {
    return Set.empty[K] ++ keys
  }

  @pure def valueSet: Set[V] = {
    return Set.empty[V] ++ values
  }

  @pure def +(p: (K, V)): AssocS[K, V] = {
    Contract(Ensures(AssocS.Entries.addPost(entries, p, Res[AssocS[K, V]].entries)))
    return AssocS(AssocS.Entries.add(entries, p))
  }

  @pure def ++[@index I](kvs: IS[I, (K, V)]): AssocS[K, V] = {
    var r = this
    for (kv <- kvs) {
      r = r + kv
    }
    return r
  }

  @pure def get(key: K): Option[V] = {
    Contract(
      Case(
        "Mapped",
        Requires(AssocS.Entries.containKey(entries, key)),
        Ensures(∃(entries.indices)(j => (key == entries(j)._1) & (Some(entries(j)._2) ≡ Res)))
      ),
      Case(
        "Unmapped",
        Requires(!AssocS.Entries.containKey(entries, key)),
        Ensures(None[V]() ≡ Res)
      )
    )
    val index = indexOf(key)
    val r: Option[V] = if (index < 0) None[V]() else Some(entries(index)._2)
    return r
  }

  @pure def getOrElse(key: K, default: => V): V = {
    val index = indexOf(key)
    return if (index < 0) default else entries(index)._2
  }

  @pure def getOrElseEager(key: K, default: V): V = {
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

  @pure def entry(key: K): Option[(K, V)] = {
    Contract(
      Case(
        "Mapped",
        Requires(AssocS.Entries.containKey(entries, key)),
        Ensures(∃(entries.indices)(j => (Some(entries(j)) ≡ Res) & entries(j)._1 == key))
      ),
      Case(
        "Unmapped",
        Requires(!AssocS.Entries.containKey(entries, key)),
        Ensures(None[(K, V)]() ≡ Res)
      )
    )
    val index = indexOf(key)
    val r: Option[(K, V)] = if (index < 0) None[(K, V)]() else Some(entries(index))
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

  @pure def --[@index I](keys: IS[I, K]): AssocS[K, V] = {
    var deletedAssocSpings = ISZ[(K, V)]()
    for (key <- keys) {
      get(key) match {
        case Some(value) => deletedAssocSpings = deletedAssocSpings :+ ((key, value))
        case _ =>
      }
    }
    if (deletedAssocSpings.nonEmpty) {
      return AssocS(entries -- deletedAssocSpings)
    } else {
      return this
    }
  }

  @pure def -(p: (K, V)): AssocS[K, V] = {
    Contract(
      Ensures(
        AssocS.entriesOf(Res).size == entries.size | AssocS.entriesOf(Res).size == entries.size - 1,
        ∀(AssocS.entriesOf(Res).indices)(j =>
          AssocS.entriesOf(Res)(j) != p & AssocS.Entries.contain(entries, AssocS.entriesOf(Res)(j))),
        ∀(entries.indices)(j =>
          (entries(j) != p) ->: AssocS.Entries.contain(AssocS.entriesOf(Res), entries(j)))
      )
    )
    return AssocS(AssocS.Entries.remove(entries, p))
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
    val r =
      st"""[
          |  ${(for (e <- entries) yield st"${e._1} -> ${e._2}", ",\n")}
          |]"""
    return r.render
  }

}
