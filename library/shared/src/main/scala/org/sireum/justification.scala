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

object justification {

  @just def Premise: Unit = $

  @just def Subst_>(x: StepId, y: StepId): Unit = $

  @just def Subst_<(x: StepId, y: StepId): Unit = $

  @just def Algebra: Unit = $

  @just def ClaimOf[T](name: T): Unit = $

  @just def Auto: Unit = $

  @just def SameDiff(from: StepId): Unit = $

  @just def Smt2(options: String, timeoutInMs: Z, rlimit: Z): Unit = $

  @just def Lift(app: Unit): Unit = $

  @just def Unfold(from: StepId): Unit = $

  @just def Fold(from: StepId): Unit = $

  @just def ValI(from: StepId): Unit = $

  @just def ValE(from: StepId): Unit = $

  @just def Admit: Unit = $

  @just def Rewrite(rs: RS, from: StepId): Unit = $

  @just def RSimpl(rs: RS): Unit = $

  @just def Eval(from: StepId): Unit = $

  @just def Simpl: Unit = $

  object natded {

    object prop {

      @just("andI") def AndI(p: StepId, q: StepId): Unit = $

      @pure def andI(p: B, q: B): Unit = {
        Deduce((p, q) |- (p & q))
      }

      @just("andE1") def AndE1(pAndQ: StepId): Unit = $

      @pure def andE1(p: B, q: B): Unit = {
        Deduce((p & q) |- p)
      }

      @just("andE2") def AndE2(pAndQ: StepId): Unit = $

      @pure def andE2(p: B, q: B): Unit = {
        Deduce((p & q) |- q)
      }

      @just("orI1") def OrI1(p: StepId): Unit = $

      @pure def orI1(p: B, q: B): Unit = {
        Deduce(p |- (p | q))
      }

      @just("orI2") def OrI2(q: StepId): Unit = $

      @pure def orI2(p: B, q: B): Unit = {
        Deduce(q |- (p | q))
      }

      /* OrE Schematic Pattern:
         N   (P | Q),
         M1  SubProof(
               M11  Assume(P),
               ...,
               M1K  (R)
             ),
         M2  SubProof(
               M21  Assume(Q),
               ...,
               M2L  (R)
             ),
         O   (R)                 by OrE(N, M1, M2)
       */
      @just def OrE(pOrQ: StepId, pToRSub: StepId, qToRSub: StepId): Unit = $

      /* ImplyI Schematic Pattern:
         N  SubProof(
              N1  Assume(P),
              ...,
              NK  (Q)
            ),
         M  (P __>: R)            by ImplyI(N)
       */
      @just def ImplyI(assumePToQSub: StepId): Unit = $

      @just("implyE") def ImplyE(pImplyQ: StepId, q: StepId): Unit = $

      @pure def implyE(p: B, q: B): Unit = {
        Deduce((p ->: q, p) |- q)
      }

      @just def NegI(assumePToBottomSub: StepId): Unit = $

      @just("negE") def NegE(p: StepId, notP: StepId): Unit = $

      @pure def negE(p: B): Unit = {
        Deduce((p, !p) |- F)
      }

      @just("bottomE") def BottomE(bottom: StepId): Unit = $

      @pure def bottomE(p: B): Unit = {
        Deduce(F |- p)
      }

      /* ImplyI Schematic Pattern:
         N  SubProof(
              N1  Assume(!P),
              ...,
              NK  (F)
            ),
         M  (P)                 by PbC(N)
       */
      @just def PbC(assumeNotRToBottom: StepId): Unit = $

      @just("sandI") def SAndI(p: StepId, q: StepId): Unit = $

      @pure def sandI(p: B, q: B): Unit = {
        Deduce((p, q) |- (p && q))
      }

      @just("sandE1") def SAndE1(pAndQ: StepId): Unit = $

      @pure def sandE1(p: B, q: B): Unit = {
        Deduce((p && q) |- p)
      }

      @just("sandE2") def SAndE2(pAndQ: StepId): Unit = $

      @pure def sandE2(p: B, q: B): Unit = {
        Deduce((p && q) |- q)
      }

      @just("sorI1") def SOrI1(p: StepId): Unit = $

      @pure def sorI1(p: B, q: B): Unit = {
        Deduce(p |- (p || q))
      }

      @just("sorI2") def SOrI2(q: StepId): Unit = $

      @pure def sorI2(p: B, q: B): Unit = {
        Deduce(q |- (p || q))
      }

      /* SOrE Schematic Pattern:
         N   (P || Q),
         M1  SubProof(
               M11  Assume(P),
               ...,
               M1K  (R)
             ),
         M2  SubProof(
               M21  Assume(Q),
               ...,
               M2L  (R)
             ),
         O   (R)                 by SOrE(N, M1, M2)
       */
      @just def SOrE(pOrQ: StepId, pToRSub: StepId, qToRSub: StepId): Unit = $

      /* SImplyI Schematic Pattern:
         N  SubProof(
              N1  Assume(P),
              ...,
              NK  (Q)
            ),
         M  (P ___>: R)            by ImplyI(N)
       */
      @just def SImplyI(assumePToQSub: StepId): Unit = $

      @just("simplyE") def SImplyE(pImplyQ: StepId, q: StepId): Unit = $

      @pure def simplyE(p: B, q: B): Unit = {
        Deduce((p -->: q, p) |- q)
      }

    }

    object pred {

      /* AllI Schematic Pattern (note: a is a fresh var of some type T):
         N  Let { (a: T) => SubProof(
              ...,
              NK  (P(a))
            ),
         M   (∀{(a: T) => P(a)}         by AllI[T](N)
      */
      @just def AllI[@mut T](assumeAToAllSub: StepId): Unit = $

      @just("allE") def AllE[@mut T](allP: StepId): Unit = $

      @pure def allE[@mut T](P: T => B@pure, E: T): Unit = {
        Deduce(All { (x: T) => P(x) } |- P(E))
      }

      @just("existsI") def ExistsI[@mut T](PE: StepId): Unit = $

      @pure def existsI[@mut T](P: T => B@pure, E: T): Unit = {
        Deduce(P(E) |- Exists { (x: T) => P(x) })
      }

      /* ExistsE Schematic Pattern (note: for some type T):
         N  (∃{(x: T) => P(x)})
         M  Let {(a: T) => SubProof(
              M1  Assume(P(a))
              ...,
              MK  (Q)
            ),
         O  (Q)                        by ExistsE[T](N, M)
      */
      @just def ExistsE[@mut T](existsP: StepId, aPaToQSub: StepId): Unit = $

    }

  }
}
