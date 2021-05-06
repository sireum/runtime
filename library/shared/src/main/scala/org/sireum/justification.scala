// #Sireum
/*
 Copyright (c) 2021, Robby, Kansas State University
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

object justification {

  @just def Premise: Unit = $

  @just def Auto(stepNumbers: ISZ[Z]): Unit = $

  object natded {

    object prop {

      @just("andI") def AndI(p: Z, q: Z): Unit = $

      @just("andE1") def AndE1(pAndQ: Z): Unit = $

      @just("andE2") def AndE2(pAndQ: Z): Unit = $

      @just("orI1") def OrI1(p: Z): Unit = $

      @just("orI2") def OrI2(q: Z): Unit = $

      @just def OrE(pOrQ: Z, pToRSub: Z, qToRSub: Z): Unit = $

      @just def ImplyI(assumePToQSub: Z): Unit = $

      @just("implyE") def ImplyE(pImplyQ: Z, q: Z): Unit = $

      @just def NegI(assumePToBottomSub: Z): Unit = $

      @just("negE") def NegE(p: Z, notP: Z): Unit = $

      @just def BottomE(bottom: Z): Unit = $

      @just def PbC(assumeNotRToBottom: Z): Unit = $

      @pure def andI(p: B, q: B): Unit = {
        Contract(
          Requires(p, q),
          Ensures(p & q)
        )
      }

      @pure def andE1(p: B, q: B): Unit = {
        Contract(
          Requires(p & q),
          Ensures(p)
        )
      }

      @pure def andE2(p: B, q: B): Unit = {
        Contract(
          Requires(p & q),
          Ensures(q)
        )
      }

      @pure def orI1(p: B, q: B): Unit = {
        Contract(
          Requires(p),
          Ensures(p | q)
        )
      }

      @pure def orI2(p: B, q: B): Unit = {
        Contract(
          Requires(q),
          Ensures(p | q)
        )
      }

      @pure def implyE(p: B, q: B): Unit = {
        Contract(
          Requires(p ->: q, p),
          Ensures(q)
        )
      }

      @pure def negE(p: B): Unit = {
        Contract(
          Requires(p, !p),
          Ensures(F)
        )
      }
    }

    object pred {

      @just def AllI(assumeAToAllSub: Z): Unit = $

      @just("allE") def AllE[T](allP: Z): Unit = $

      @just("existsI") def ExistsI[T](PE: Z): Unit = $

      @just def ExistsE[T](existsP: Z, aPaToQSub: Z): Unit = $

      @pure def allE[T](P: T => B @pure, E: T): Unit = {
        Contract(
          Requires(All{(x: T) => P(x)}),
          Ensures(P(E))
        )
      }

      @pure def existsI[T](P: T => B @pure, E: T): Unit = {
        Contract(
          Requires(P(E)),
          Ensures(Exists{(x: T) => P(x)})
        )
      }

    }

  }
}
