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

@datatype class IndexMap[@index K, V](val s: IS[K, V]) {

  @strictpure def +(p: (K, V)): IndexMap[K, V] = {
    Contract(
      Requires(contains(p._1)),
      Ensures(IndexMap(s(p)) ≡ Res)
    )
    IndexMap(s(p))
  }

  @strictpure def contains(k: K): B = s.isInBound(k)

  @strictpure def get(k: K): Option[V] = if (contains(k)) Some(s(k)) else None()

  @strictpure def size: Z = s.size

  @strictpure def entries: ISZ[(K, V)] = for (i <- s.indices) yield (i, s(i))

  @strictpure def keys: ISZ[K] = s.indices

  @strictpure def values: ISZ[V] = for (i <- s.indices) yield s(i)

  @strictpure def prettyST: ST =
    st"""{
        |  ${(for (k <- s.indices) yield st"$k ~> ${s(k)}", ",\n")}
        |}"""

  @strictpure override def string: String = prettyST.render
}
