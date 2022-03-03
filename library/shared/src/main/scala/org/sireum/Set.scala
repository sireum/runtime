// #Sireum #Logika
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

import org.sireum.justification.Premise

object Set {

  @strictpure def empty[T]: Set[T] = Set[T](ISZ())

  @strictpure def ++[I, T](s: IS[I, T]): Set[T] = empty[T] ++ s

  object Elements {

    @strictpure def unique[T](elements: ISZ[T]): B =
      ∀(elements.indices)(i => ∀(elements.indices)(j => (i != j) ->: (elements(i) != elements(j))))

    @strictpure def contain[T](elements: ISZ[T], e: T): B = ∃(elements.indices)(j => e == elements(j))

    @strictpure def indexOfFrom[T](elements: ISZ[T], e: T, from: Z): Z =
      if (from < 0 | from >= elements.size) -1
      else if (elements(from)== e) from
      else indexOfFrom(elements, e, from + 1)

  }
}

@datatype class Set[T](val elements: ISZ[T]) {

  @spec def uniqueElements = Invariant(Set.Elements.unique(elements))

  @pure def +(e: T): Set[T] = {
    Contract(
      Case(
        "In",
        Requires(Set.Elements.contain(elements, e)),
        Ensures(
          Res[Set[T]].elements.size == size,
          Set.Elements.contain(Res[Set[T]].elements, e),
          ∃(Res[Set[T]].elements.indices)(j => Res[Set[T]].elements(j) == e)
        )
      ),
      Case(
        "Not-in",
        Requires(!Set.Elements.contain(elements, e)),
        Ensures(
          Res[Set[T]].elements.size == size + 1,
          Set.Elements.contain(Res[Set[T]].elements, e),
          Res[Set[T]].elements(Res[Set[T]].elements.size - 1) == e
        )
      )
    )
    val index = indexOf(e)
    val newElements: ISZ[T] = if (index < 0) elements :+ e else elements((index, e))
    return Set(newElements)
  }

  @pure def ++[I](is: IS[I, T]): Set[T] = {
    var r = this
    for (e <- is) {
      r = r + e
    }
    return r
  }

  @pure def -(e: T): Set[T] = {
    Contract(
      Ensures(∀(Res[Set[T]].elements.indices)(j => (Res[Set[T]].elements(j) != e) ->:
        ∃(elements.indices)(k => elements(k) == Res[Set[T]].elements(j))))
    )
    var newElements = ISZ[T]()
    var i = 0
    while (i < elements.size) {
      Invariant(
        Modifies(i, newElements),
        0 <= i,
        i <= elements.size,
        ∀(newElements.indices)(j => ∀(i until elements.size)(k => newElements(j) != elements(k))),
        ∀(newElements.indices)(j => (newElements(j) != e) ->: ∃(elements.indices)(k => elements(k) == newElements(j))),
        Set.Elements.unique(newElements),
      )
      val kv = elements(i)
      if (kv != e) {
        newElements = newElements :+ kv
      }
      i = i + 1
    }
    return Set(newElements)
  }

  @pure def --[I](is: IS[I, T]): Set[T] = {
    var r = this
    for (e <- is) {
      r = r - e
    }
    return r
  }

  @pure def contains(e: T): B = {
    Contract(Ensures(Res == ∃(0 until elements.size)(j => e == elements(j))))
    return indexOf(e) >= 0
  }

  @pure def union(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Res[Set[T]].elements.size >= elements.size,
        ∀(Res[Set[T]].elements.indices)(j =>
          Set.Elements.contain(elements, Res[Set[T]].elements(j)) |
            Set.Elements.contain(other.elements, Res[Set[T]].elements(j))),
        ∀(elements.indices)(j => elements(j) == Res[Set[T]].elements(j)),
      )
    )
    var newElements = elements
    var i: Z = 0
    while (i < other.elements.size) {
      Invariant(
        Modifies(i, newElements),
        0 <= i,
        i <= other.elements.size,
        newElements.size >= elements.size,
        ∀(newElements.indices)(j => Set.Elements.contain(elements, newElements(j)) |
          Set.Elements.contain(other.elements, newElements(j))),
        ∀(elements.indices)(j => elements(j) == newElements(j)),
        ∀(elements.size until newElements.size)(j =>
          ∀(i until other.elements.size)(k => newElements(j) != other.elements(k))),
        Set.Elements.unique(newElements),
      )
      val e = other.elements(i)
      if (!contains(e)) {
        Deduce(
          //@formatter:off
          1 #> !Set.Elements.contain(newElements, e)                            by Premise,
          2 #> ∀(i + 1 until other.elements.size)(j => e != other.elements(j))  by Premise
          //@formatter:on
        )
        newElements = newElements :+ e
      }

      i = i + 1
    }
    return Set(newElements)
  }

  @pure def ∪(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Res[Set[T]].elements.size >= elements.size,
        ∀(Res[Set[T]].elements.indices)(j =>
          Set.Elements.contain(elements, Res[Set[T]].elements(j)) |
            Set.Elements.contain(other.elements, Res[Set[T]].elements(j))),
        ∀(elements.indices)(j => elements(j) == Res[Set[T]].elements(j)),
      )
    )
    return union(other)
  }

  @pure def intersect(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Res[Set[T]].elements.size <= elements.size,
        ∀(Res[Set[T]].elements.indices)(j =>
          Set.Elements.contain(elements, Res[Set[T]].elements(j)) &
            Set.Elements.contain(other.elements, Res[Set[T]].elements(j)))
      )
    )
    var newElements = ISZ[T]()
    var i: Z = 0
    while (i < elements.size) {
      Invariant(
        Modifies(i, newElements),
        0 <= i,
        i <= elements.size,
        newElements.size <= i,
        ∀(newElements.indices)(j => Set.Elements.contain(other.elements, newElements(j))),
        ∀(newElements.indices)(j => Set.Elements.contain(elements, newElements(j))),
        ∀(newElements.indices)(j => ∀(i until elements.size)(k => newElements(j) != elements(k))),
        Set.Elements.unique(newElements),
      )
      val e = elements(i)
      if (other.contains(e)) {
        newElements = newElements :+ e
      }

      i = i + 1
    }
    return Set(newElements)
  }

  @pure def ∩(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Res[Set[T]].elements.size <= size,
        ∀(Res[Set[T]].elements.indices)(j =>
          Set.Elements.contain(elements, Res[Set[T]].elements(j)) &
            Set.Elements.contain(other.elements, Res[Set[T]].elements(j)))
      )
    )
    return intersect(other)
  }

  @pure def \(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Res[Set[T]].elements.size <= size,
        ∀(Res[Set[T]].elements.indices)(j =>
          Set.Elements.contain(elements, Res[Set[T]].elements(j)) &
            !Set.Elements.contain(other.elements, Res[Set[T]].elements(j)))
      )
    )
    var newElements = ISZ[T]()
    var i: Z = 0
    while (i < elements.size) {
      Invariant(
        Modifies(i, newElements),
        0 <= i,
        i <= elements.size,
        newElements.size <= i,
        ∀(newElements.indices)(j => !Set.Elements.contain(other.elements, newElements(j))),
        ∀(newElements.indices)(j => Set.Elements.contain(elements, newElements(j))),
        ∀(newElements.indices)(j => ∀(i until elements.size)(k => newElements(j) != elements(k))),
        Set.Elements.unique(newElements),
      )
      val e = elements(i)
      if (!other.contains(e)) {
        newElements = newElements :+ e
      }

      i = i + 1
    }
    return Set(newElements)
  }

  @pure def isEqual(other: Set[T]): B = {
    Contract(
      Case(
        "Equal",
        Requires(
          size == other.size,
          ∀(elements.indices)(j => ∃(0 until size)(k => elements(j) == other.elements(k))),
        ),
        Ensures(Res[B])
      ),
      Case(
        "Inequal-diff",
        Requires(
          size == other.size,
          ∃(elements.indices)(j => !Set.Elements.contain(other.elements, elements(j))),
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
          r ->: ∀(0 until i)(j => ∃(0 until sz)(k => elements(j) == other.elements(k))),
          !r ->: ∃(elements.indices)(j =>
            !Set.Elements.contain(other.elements, elements(j)) |
              ∀(other.elements.indices)(k => elements(j) != other.elements(k)))
        )
        if (!other.contains(elements(i))) {
          r = F
        }
        i = i + 1
      }

      return r
    }
  }

  @pure override def hash: Z = {
    return elements.hash
  }

  @pure def isEmpty: B = {
    Contract(Ensures(Res == (elements.size == 0)))
    return size == z"0"
  }

  @pure def nonEmpty: B = {
    Contract(Ensures(Res == (elements.size != 0)))
    return size != z"0"
  }

  @pure def size: Z = {
    Contract(Ensures(Res == elements.size))
    return elements.size
  }

  @pure def indexOf(e: T): Z = {
    Contract(
      Case(
        "In",
        Requires(Set.Elements.contain(elements, e)),
        Ensures(
          0 <= Res[Z],
          Res[Z] < elements.size,
          elements(Res[Z]) == e
        )
      ),
      Case(
        "Not-in",
        Requires(!Set.Elements.contain(elements, e)),
        Ensures(Res[Z] == -1)
      )
    )
    var index: Z = -1
    var i: Z = 0
    while (i < elements.size) {
      Invariant(
        Modifies(index, i),
        0 <= i,
        i <= elements.size,
        (index != -1) ->: (0 <= index & index < elements.size & elements(index) == e),
        (index == -1) ->: ∀(0 until i)(j => e != elements(j))
      )
      if (elements(i) == e) {
        index = i
        i = elements.size - 1
      }
      i = i + 1
    }
    return index
  }

  @pure override def string: String = {
    val r =
      st"""{
      |  ${(elements, ",\n")}
      |}"""
    return r.render
  }
}
