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

object SeqUtil {

  object IS {

    @strictpure def sizeEq[I, T](s: IS[I, T], size: Z): B = s.size == size

    @strictpure def unique[I, T](s: IS[I, T]): B = ∀(s.indices)(i => ∀(s.indices)(j => (i != j) ->: (s(i) != s(j))))

    @strictpure def pair1Eq[I, T1, T2](s1: IS[I, (T1, T2)], s2: IS[I, T1]): B =
      sizeEq(s1, s2.size) && ∀(s1.indices)(i => s1(i)._1 == s2(i))

    @strictpure def pair2Eq[I, T1, T2](s1: IS[I, (T1, T2)], s2: IS[I, T2]): B =
      sizeEq(s1, s2.size) && ∀(s1.indices)(i => s1(i)._2 == s2(i))
  }


}
