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

import org.sireum.justification.Auto

object Set {

  @strictpure def empty[T]: Set[T] = Set[T](ISZ())

  @pure def ++[@index I, T](s: IS[I, T]): Set[T] = {
    return empty[T] ++ s
  }

  @strictpure def elementsOf[T](s: Set[T]): ISZ[T] = s.elements

  object Elements {

    @strictpure def unique[T](elements: ISZ[T]): B =
      ∀(elements.indices)(i => ∀(elements.indices)(j => (i != j) ->: (elements(i) != elements(j))))

    @strictpure def contain[T](elements: ISZ[T], e: T): B = ∃(elements.indices)(j => e == elements(j))

    @strictpure def indexOfFrom[T](elements: ISZ[T], e: T, from: Z): Z =
      if (from < 0 | from >= elements.size) -1
      else if (elements(from) == e) from
      else indexOfFrom(elements, e, from + 1)

  }
}

@datatype class Set[T](val elements: ISZ[T]) {

  @spec def uniqueElements = Invariant(Set.Elements.unique(elements))

  @pure def +(e: T): Set[T] = {
    Contract(
      Ensures(
        Set.elementsOf(Res).size == elements.size | Set.elementsOf(Res).size == elements.size + 1,
        Set.Elements.contain(Set.elementsOf(Res), e),
        ∀(Set.elementsOf(Res).indices)(j =>
          (e != Set.elementsOf(Res)(j)) ->: Set.Elements.contain(elements, Set.elementsOf(Res)(j))),
        ∀(elements.indices)(j =>
          (elements(j) != e) ->: Set.Elements.contain(Set.elementsOf(Res), elements(j)))
      )
    )
    val index = indexOf(e)
    val newElements: ISZ[T] = if (index < 0) {
      val r = elements :+ e
      Deduce(
        //@formatter:off
        r(r.size - 1) ≡ e                                                         by Auto,
        Set.Elements.contain(r, e)                                                by Auto,
        LibUtil.IS.unique(r)                                                      by Auto,
        ∀(r.indices)(j => (e != r(j)) ->: Set.Elements.contain(elements, r(j)))   by Auto,
        ∀(elements.indices)(j => Set.Elements.contain(r, elements(j)))            by Auto
        //@formatter:on
      )
      r
    } else {
      val r = elements(index ~> e)
      Deduce(
        //@formatter:off
        r(index) ≡ e                                                              by Auto,
        Set.Elements.contain(r, e)                                                by Auto,
        LibUtil.IS.unique(r)                                                      by Auto,
        ∀(r.indices)(j => (e != r(j)) ->: Set.Elements.contain(elements, r(j)))   by Auto,
        ∀(elements.indices)(j => Set.Elements.contain(r, elements(j)))            by Auto
        //@formatter:on
      )
      r
    }
    return Set(newElements)
  }

  @pure def ++[@index I](is: IS[I, T]): Set[T] = {
    var r = this
    for (e <- is) {
      r = r + e
    }
    return r
  }

  @pure def -(e: T): Set[T] = {
    Contract(
      Ensures(
        Set.elementsOf(Res).size == elements.size | Set.elementsOf(Res).size == elements.size - 1,
        ∀(Set.elementsOf(Res).indices)(j => e != Set.elementsOf(Res)(j) & Set.Elements.contain(elements, Set.elementsOf(Res)(j))),
        ∀(elements.indices)(j => (elements(j) != e) ->: Set.Elements.contain(Set.elementsOf(Res), elements(j)))
      )
    )
    var newElements = ISZ[T]()
    var i: Z = 0
    while (i < elements.size) {
      Invariant(
        Modifies(i, newElements),
        0 <= i,
        i <= elements.size,
        ∀(newElements.indices)(j => ∀(i until elements.size)(k => newElements(j) != elements(k))),
        ∀(newElements.indices)(j => newElements(j) != e & Set.Elements.contain(elements, newElements(j))),
        ∃(0 until i)(j => e == elements(j)) ->: (newElements.size == i - 1),
        ∀(0 until i)(j => e != elements(j)) ->: (newElements.size == i),
        ∀(0 until i)(j => (e != elements(j)) ->: Set.Elements.contain(newElements, elements(j))),
        Set.Elements.unique(newElements)
      )
      val kv = elements(i)
      if (kv != e) {
        newElements = newElements :+ kv
      }
      i = i + 1
    }
    return Set(newElements)
  }

  @pure def --[@index I](is: IS[I, T]): Set[T] = {
    var r = this
    for (e <- is) {
      r = r - e
    }
    return r
  }

  @pure def contains(e: T): B = {
    Contract(Ensures(Set.Elements.contain(elements, e) == Res))
    return indexOf(e) >= 0
  }

  @pure def union(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Set.elementsOf(Res).size >= elements.size,
        Set.elementsOf(Res).size <= elements.size + other.elements.size,
        ∀(Set.elementsOf(Res).indices)(j =>
          Set.Elements.contain(elements, Set.elementsOf(Res)(j)) |
            Set.Elements.contain(other.elements, Set.elementsOf(Res)(j))),
        ∀(elements.indices)(j => elements(j) == Set.elementsOf(Res)(j)),
        ∀(other.elements.indices)(j => Set.Elements.contain(Set.elementsOf(Res), other.elements(j)))
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
        newElements.size <= elements.size + i,
        ∀(newElements.indices)(j => Set.Elements.contain(elements, newElements(j)) |
          Set.Elements.contain(other.elements, newElements(j))),
        ∀(elements.indices)(j => elements(j) == newElements(j)),
        ∀(elements.size until newElements.size)(j =>
          ∀(i until other.elements.size)(k => newElements(j) != other.elements(k))),
        ∀(0 until i)(j => Set.Elements.contain(newElements, other.elements(j))),
        Set.Elements.unique(newElements)
      )
      val e = other.elements(i)
      if (!contains(e)) {
        newElements = newElements :+ e
        Deduce(
          //@formatter:off
          ∀(0 to i)(j => Set.Elements.contain(newElements, other.elements(j)))  by Auto,
          Set.Elements.unique(newElements)                                      by Auto
          //@formatter:on
        )
      } else {
        Deduce(
          //@formatter:off
          ∀(0 to i)(j => Set.Elements.contain(newElements, other.elements(j)))  by Auto,
          Set.Elements.unique(newElements)                                      by Auto
          //@formatter:on
        )
      }
      i = i + 1
    }
    return Set(newElements)
  }

  @pure def ∪(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Set.elementsOf(Res).size >= elements.size,
        Set.elementsOf(Res).size <= elements.size + other.elements.size,
        ∀(Set.elementsOf(Res).indices)(j =>
          Set.Elements.contain(elements, Set.elementsOf(Res)(j)) |
            Set.Elements.contain(other.elements, Set.elementsOf(Res)(j))),
        ∀(elements.indices)(j => elements(j) == Set.elementsOf(Res)(j)),
        ∀(other.elements.indices)(j => Set.Elements.contain(Set.elementsOf(Res), other.elements(j)))
      )
    )
    return union(other)
  }

  @pure def intersect(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Set.elementsOf(Res).size <= elements.size,
        ∀(Set.elementsOf(Res).indices)(j =>
          Set.Elements.contain(elements, Set.elementsOf(Res)(j)) &
            Set.Elements.contain(other.elements, Set.elementsOf(Res)(j))),
        ∀(elements.indices)(j =>
          Set.Elements.contain(other.elements, elements(j)) ->: Set.Elements.contain(Set.elementsOf(Res), elements(j))),
        ∀(other.elements.indices)(j =>
          Set.Elements.contain(elements, other.elements(j)) ->: Set.Elements.contain(Set.elementsOf(Res), other.elements(j)))
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
        ∀(0 until i)(j => Set.Elements.contain(other.elements, elements(j)) ->: Set.Elements.contain(newElements, elements(j))),
        Set.Elements.unique(newElements)
      )
      val e = elements(i)
      if (other.contains(e)) {
        newElements = newElements :+ e
        Deduce(
          //@formatter:off
          ∀(0 to i)(j => Set.Elements.contain(other.elements, elements(j)) ->:
            Set.Elements.contain(newElements, elements(j)))                      by Auto,
          Set.Elements.unique(newElements)                                       by Auto
          //@formatter:on
        )
      }

      i = i + 1
    }
    return Set(newElements)
  }

  @pure def ∩(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Set.elementsOf(Res).size <= elements.size,
        ∀(Set.elementsOf(Res).indices)(j =>
          Set.Elements.contain(elements, Set.elementsOf(Res)(j)) &
            Set.Elements.contain(other.elements, Set.elementsOf(Res)(j))),
        ∀(elements.indices)(j =>
          Set.Elements.contain(other.elements, elements(j)) ->: Set.Elements.contain(Set.elementsOf(Res), elements(j))),
        ∀(other.elements.indices)(j =>
          Set.Elements.contain(elements, other.elements(j)) ->: Set.Elements.contain(Set.elementsOf(Res), other.elements(j)))
      )
    )
    return intersect(other)
  }

  @pure def \(other: Set[T]): Set[T] = {
    Contract(
      Ensures(
        Set.elementsOf(Res).size <= elements.size,
        ∀(Set.elementsOf(Res).indices)(j =>
          Set.Elements.contain(elements, Set.elementsOf(Res)(j)) &
            !Set.Elements.contain(other.elements, Set.elementsOf(Res)(j))),
        ∀(elements.indices)(j =>
          !Set.Elements.contain(other.elements, elements(j)) ->: Set.Elements.contain(Set.elementsOf(Res), elements(j))),
        ∀(other.elements.indices)(j => !Set.Elements.contain(Set.elementsOf(Res), other.elements(j)))
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
        ∀(0 until i)(j => !Set.Elements.contain(other.elements, elements(j)) ->: Set.Elements.contain(newElements, elements(j))),
        Set.Elements.unique(newElements)
      )
      val e = elements(i)
      if (!other.contains(e)) {
        newElements = newElements :+ e
        Deduce(
          //@formatter:off
          ∀(0 to i)(j => !Set.Elements.contain(other.elements, elements(j)) ->:
            Set.Elements.contain(newElements, elements(j)))                            by Auto,
          Set.Elements.unique(newElements)                                             by Auto
          //@formatter:on
        )
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
          elements.size == other.elements.size,
          ∀(elements.indices)(j => Set.Elements.contain(other.elements, elements(j)))
        ),
        Ensures(Res[B])
      ),
      Case(
        "Inequal-diff",
        Requires(
          elements.size == other.elements.size,
          ∃(elements.indices)(j => !Set.Elements.contain(other.elements, elements(j)))
        ),
        Ensures(!Res[B])
      ),
      Case(
        "Inequal-size",
        Requires(elements.size != other.elements.size),
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
          r ->: ∀(0 until i)(j => Set.Elements.contain(other.elements, elements(j))),
          !r ->: ∃(elements.indices)(j => !Set.Elements.contain(other.elements, elements(j)))
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
    Contract(Ensures((elements.size == 0) == Res))
    return size == z"0"
  }

  @pure def nonEmpty: B = {
    Contract(Ensures((elements.size != 0) == Res))
    return size != z"0"
  }

  @pure def size: Z = {
    Contract(Ensures(elements.size == Res))
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
        (index != -1) ->: ((0 <= index & index < elements.size) && elements(index) == e),
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
