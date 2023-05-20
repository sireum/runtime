// #Sireum #Logika
/*
 Copyright (c) 2017-2023, Robby, Kansas State University
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

  @just def Auto: Unit = $

  @just def SameDiff(from: StepId): Unit = $

  @just def Smt2(options: String, timeoutInMs: Z, rlimit: Z): Unit = $

  @just def Lift(app: Unit): Unit = $

  @just def ClaimOf[T](name: T): Unit = $

  @just def Unfold(from: StepId): Unit = $

  @just def Fold(from: StepId): Unit = $

  @just def ValI(from: StepId): Unit = $

  @just def ValE(from: StepId): Unit = $

  object natded {

    object prop {

      @just("andI") def AndI(p: StepId, q: StepId): Unit = $

      @just("andE1") def AndE1(pAndQ: StepId): Unit = $

      @just("andE2") def AndE2(pAndQ: StepId): Unit = $

      @just("orI1") def OrI1(p: StepId): Unit = $

      @just("orI2") def OrI2(q: StepId): Unit = $

      @just def OrE(pOrQ: StepId, pToRSub: StepId, qToRSub: StepId): Unit = $

      @just def ImplyI(assumePToQSub: StepId): Unit = $

      @just def SImplyI(assumePToQSub: StepId): Unit = $

      @just("implyE") def ImplyE(pImplyQ: StepId, q: StepId): Unit = $

      @just def NegI(assumePToBottomSub: StepId): Unit = $

      @just("negE") def NegE(p: StepId, notP: StepId): Unit = $

      @just def BottomE(bottom: StepId): Unit = $

      @just def PbC(assumeNotRToBottom: StepId): Unit = $

      @pure def andI(p: B, q: B): Unit = {
        Deduce((p, q) |- (p & q))
      }

      @pure def sandI(p: B, q: B): Unit = {
        Deduce((p, q) |- (p && q))
      }

      @pure def andE1(p: B, q: B): Unit = {
        Deduce((p & q) |- p)
      }

      @pure def sandE1(p: B, q: B): Unit = {
        Deduce((p && q) |- p)
      }

      @pure def andE2(p: B, q: B): Unit = {
        Deduce((p & q) |- q)
      }

      @pure def sandE2(p: B, q: B): Unit = {
        Deduce((p && q) |- q)
      }

      @pure def orI1(p: B, q: B): Unit = {
        Deduce(p |- (p | q))
      }

      @pure def sorI1(p: B, q: B): Unit = {
        Deduce(p |- (p || q))
      }

      @pure def orI2(p: B, q: B): Unit = {
        Deduce(q |- (p | q))
      }

      @pure def sorI2(p: B, q: B): Unit = {
        Deduce(q |- (p || q))
      }

      @pure def implyE(p: B, q: B): Unit = {
        Deduce((p ->: q, p) |- q)
      }

      @pure def simplyE(p: B, q: B): Unit = {
        Deduce((p -->: q, p) |- q)
      }

      @pure def negE(p: B): Unit = {
        Deduce((p, !p) |- F)
      }
    }

    object pred {

      @just def AllI(assumeAToAllSub: StepId): Unit = $

      @just("allE") def AllE[@mut T](allP: StepId): Unit = $

      @just("existsI") def ExistsI[@mut T](PE: StepId): Unit = $

      @just def ExistsE[T](existsP: StepId, aPaToQSub: StepId): Unit = $

      @pure def allE[@mut T](P: T => B@pure, E: T): Unit = {
        Deduce(All { (x: T) => P(x) } |- P(E))
      }

      @pure def existsI[@mut T](P: T => B@pure, E: T): Unit = {
        Deduce(P(E) |- Exists { (x: T) => P(x) })
      }

    }

  }
}
