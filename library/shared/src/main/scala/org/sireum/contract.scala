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

trait contract {

  import language.experimental.macros

  import $internal.Macro

  object Spec {
    def apply(arg0: => Unit): Unit = macro Macro.lUnit1
  }

  object Contract {

    def apply(arg0: Reads): Unit = macro Macro.lUnit1

    def apply(arg0: Requires): Unit = macro Macro.lUnit1

    def apply(arg0: Modifies): Unit = macro Macro.lUnit1

    def apply(arg0: Ensures): Unit = macro Macro.lUnit1

    def apply(arg0: Reads, arg1: Requires): Unit = macro Macro.lUnit2

    def apply(arg0: Reads, arg1: Modifies): Unit = macro Macro.lUnit2

    def apply(arg0: Reads, arg1: Ensures): Unit = macro Macro.lUnit2

    def apply(arg0: Requires, arg1: Modifies): Unit = macro Macro.lUnit2

    def apply(arg0: Requires, arg1: Ensures): Unit = macro Macro.lUnit2

    def apply(arg0: Modifies, arg1: Ensures): Unit = macro Macro.lUnit2

    def apply(arg0: Reads, arg1: Requires, arg2: Modifies): Unit = macro Macro.lUnit3

    def apply(arg0: Reads, arg1: Modifies, arg2: Ensures): Unit = macro Macro.lUnit3

    def apply(arg0: Reads, arg1: Requires, arg2: Ensures): Unit = macro Macro.lUnit3

    def apply(arg0: Requires, arg1: Modifies, arg2: Ensures): Unit = macro Macro.lUnit3

    def apply(arg0: Reads, arg1: Requires, arg2: Modifies, arg3: Ensures): Unit = macro Macro.lUnit4

    def apply(arg0: Case, arg1: Case*): Unit = macro Macro.lUnit1S

    def apply(arg0: Reads, arg1: Case, arg2: Case*): Unit = macro Macro.lUnit2S

    def apply(arg0: Requires, arg1: Case, arg2: Case*): Unit = macro Macro.lUnit2S

    def apply(arg0: Modifies, arg1: Case, arg2: Case*): Unit = macro Macro.lUnit2S

    def apply(arg0: Ensures, arg1: Case, arg2: Case*): Unit = macro Macro.lUnit2S

    def apply(arg0: Reads, arg1: Requires, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Reads, arg1: Modifies, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Reads, arg1: Ensures, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Requires, arg1: Modifies, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Requires, arg1: Ensures, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Modifies, arg1: Ensures, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Reads, arg1: Requires, arg2: Modifies, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Reads, arg1: Modifies, arg2: Ensures, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Reads, arg1: Requires, arg2: Ensures, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Requires, arg1: Modifies, arg2: Ensures, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Reads, arg1: Requires, arg2: Modifies, arg3: Ensures, arg4: Case, arg5: Case*): Unit = macro Macro.lUnit5S

    def apply(arg0: Contract.Cases): Unit = macro Macro.lUnit1

    def apply(arg0: String): Unit = macro Macro.lUnit1

    def apply(arg0: DataRefinement): Unit = macro Macro.lUnit1

    def Havoc(arg0: Any, arg1: Any*): Unit = macro Macro.lUnit1S

    // begin InfoFlow apply
    def apply(arg0: InfoFlows): Unit = macro Macro.lUnit1

    def apply(arg0: Reads, arg1: InfoFlows): Unit = macro Macro.lUnit2

    def apply(arg0: Requires, arg1: InfoFlows): Unit = macro Macro.lUnit2

    def apply(arg0: Modifies, arg1: InfoFlows): Unit = macro Macro.lUnit2

    def apply(arg0: Ensures, arg1: InfoFlows): Unit = macro Macro.lUnit2

    def apply(arg0: InfoFlows, arg1: Case, arg2: Case*): Unit = macro Macro.lUnit2S

    def apply(arg0: Reads, arg1: Requires, arg2: InfoFlows): Unit = macro Macro.lUnit3

    def apply(arg0: Reads, arg1: Modifies, arg2: InfoFlows): Unit = macro Macro.lUnit3

    def apply(arg0: Requires, arg1: Modifies, arg2: InfoFlows): Unit = macro Macro.lUnit3

    def apply(arg0: Reads, arg1: Ensures, arg2: InfoFlows): Unit = macro Macro.lUnit3

    def apply(arg0: Requires, arg1: Ensures, arg2: InfoFlows): Unit = macro Macro.lUnit3

    def apply(arg0: Modifies, arg1: Ensures, arg2: InfoFlows): Unit = macro Macro.lUnit3

    def apply(arg0: Reads, arg1: InfoFlows, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Requires, arg1: InfoFlows, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Modifies, arg1: InfoFlows, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Ensures, arg1: InfoFlows, arg2: Case, arg3: Case*): Unit = macro Macro.lUnit3S

    def apply(arg0: Reads, arg1: Requires, arg2: Modifies, arg3: InfoFlows): Unit = macro Macro.lUnit4

    def apply(arg0: Reads, arg1: Requires, arg2: Ensures, arg3: InfoFlows): Unit = macro Macro.lUnit4

    def apply(arg0: Reads, arg1: Modifies, arg2: Ensures, arg3: InfoFlows): Unit = macro Macro.lUnit4

    def apply(arg0: Requires, arg1: Modifies, arg2: Ensures, arg3: InfoFlows): Unit = macro Macro.lUnit4

    def apply(arg0: Reads, arg1: Requires, arg2: InfoFlows, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Reads, arg1: Modifies, arg2: InfoFlows, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Requires, arg1: Modifies, arg2: InfoFlows, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Reads, arg1: Ensures, arg2: InfoFlows, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Requires, arg1: Ensures, arg2: InfoFlows, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Modifies, arg1: Ensures, arg2: InfoFlows, arg3: Case, arg4: Case*): Unit = macro Macro.lUnit4S

    def apply(arg0: Reads, arg1: Requires, arg2: Modifies, arg3: Ensures, arg4: InfoFlows): Unit = macro Macro.lUnit5

    def apply(arg0: Reads, arg1: Requires, arg2: Modifies, arg3: InfoFlows, arg4: Case, arg5: Case*): Unit = macro Macro.lUnit5S

    def apply(arg0: Reads, arg1: Requires, arg2: Ensures, arg3: InfoFlows, arg4: Case, arg5: Case*): Unit = macro Macro.lUnit5S

    def apply(arg0: Reads, arg1: Modifies, arg2: Ensures, arg3: InfoFlows, arg4: Case, arg5: Case*): Unit = macro Macro.lUnit5S

    def apply(arg0: Requires, arg1: Modifies, arg2: Ensures, arg3: InfoFlows, arg4: Case, arg5: Case*): Unit = macro Macro.lUnit5S

    def apply(arg0: Reads, arg1: Requires, arg2: Modifies, arg3: Ensures, arg4: InfoFlows, arg5: Case, arg6: Case*): Unit = macro Macro.lUnit6S
    // end InfoFlow apply

    object Only {

      def apply[T](reads: Reads): T = ???

      def apply[T](requires: Requires): T = ???

      def apply[T](modifies: Modifies): T = ???

      def apply[T](ensures: Ensures): T = ???

      def apply[T](reads: Reads, requires: Requires): T = ???

      def apply[T](reads: Reads, modifies: Modifies): T = ???

      def apply[T](reads: Reads, ensures: Ensures): T = ???

      def apply[T](requires: Requires, modifies: Modifies): T = ???

      def apply[T](requires: Requires, ensures: Ensures): T = ???

      def apply[T](modifies: Modifies, ensures: Ensures): T = ???

      def apply[T](reads: Reads, requires: Requires, modifies: Modifies): T = ???

      def apply[T](reads: Reads, modifies: Modifies, ensures: Ensures): T = ???

      def apply[T](requires: Requires, modifies: Modifies, ensures: Ensures): T = ???

      def apply[T](reads: Reads, requires: Requires, modifies: Modifies, ensures: Ensures): T = ???

      def apply[T](case0: Case, cases: Case*): T = ???

      def apply[T](reads: Reads, case0: Case, cases: Case*): T = ???

      def apply[T](modifies: Modifies, case0: Case, cases: Case*): T = ???

      def apply[T](reads: Reads, modifies: Modifies, case0: Case, cases: Case*): T = ???

      def apply[T](cases: Cases): T = ???

      // begin supported InfoFlow apply
      def apply[T](infoFlows: InfoFlows): T = ???

      def apply[T](reads: Reads, infoFlows: InfoFlows): T = ???

      def apply[T](requires: Requires, infoFlows: InfoFlows): T = ???

      def apply[T](modifies: Modifies, infoFlows: InfoFlows): T = ???

      def apply[T](ensures: Ensures, infoFlows: InfoFlows): T = ???

      def apply[T](reads: Reads, requires: Requires, infoFlows: InfoFlows): T = ???

      def apply[T](reads: Reads, modifies: Modifies, infoFlows: InfoFlows): T = ???

      def apply[T](requires: Requires, modifies: Modifies, infoFlows: InfoFlows): T = ???

      def apply[T](reads: Reads, ensures: Ensures, infoFlows: InfoFlows): T = ???

      def apply[T](requires: Requires, ensures: Ensures, infoFlows: InfoFlows): T = ???

      def apply[T](modifies: Modifies, ensures: Ensures, infoFlows: InfoFlows): T = ???

      def apply[T](reads: Reads, requires: Requires, modifies: Modifies, infoFlows: InfoFlows): T = ???

      def apply[T](reads: Reads, requires: Requires, ensures: Ensures, infoFlows: InfoFlows): T = ???

      def apply[T](reads: Reads, modifies: Modifies, ensures: Ensures, infoFlows: InfoFlows): T = ???

      def apply[T](requires: Requires, modifies: Modifies, ensures: Ensures, infoFlows: InfoFlows): T = ???

      def apply[T](reads: Reads, requires: Requires, modifies: Modifies, ensures: Ensures, infoFlows: InfoFlows): T = ???
      // end supported InfoFlow apply
   }

    trait State {
      def apply[T](o: T): T
      def ~[T](o: T): StatePost
    }

    trait StateCont {
      def ~[T](o: T): StatePost
    }

    trait StatePost {
      def ~(post: State): StateCont
    }

    trait Cases

    trait Case

    trait Requires

    trait Reads

    trait Modifies

    trait Ensures

    trait MaxIt

    trait Sequent {
      def Proof(steps: ProofStep*): Sequent
    }

    trait Proof

    trait SubProof

    trait ProofStep

    trait Justification

    trait Invariant

    trait DataRefinement

    trait Lemma

    trait Theorem

    trait Fact

    trait Assume

    trait Assert

    trait Let

    trait StepBuilder {
      def #>(cond: B): StepBuilder2
      def #>(assume: Assume): ProofStep
      def #>(assert: Assert): ProofStep
      def #>(let: Let): ProofStep
      def #>(subProof: SubProof): ProofStep
      def apply(cond: B): StepBuilder2
      def Assume(cond: B): ProofStep
      def Assert(cond: B): ProofStep
      def Let[T](body: T => Contract.SubProof): ProofStep
      def Let[T1, T2](body: (T1, T2) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3](body: (T1, T2, T3) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4](body: (T1, T2, T3, T4) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5](body: (T1, T2, T3, T4, T5) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6](body: (T1, T2, T3, T4, T5, T6) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7](body: (T1, T2, T3, T4, T5, T6, T7) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8](body: (T1, T2, T3, T4, T5, T6, T7, T8) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Contract.SubProof): ProofStep
      def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Contract.SubProof): ProofStep
      def SubProof(steps: Contract.ProofStep*): ProofStep
      def SubProof[T](let: T => Any): ProofStep
      def SubProof[T1, T2](let: (T1, T2) => Any): ProofStep
      def SubProof[T1, T2, T3](let: (T1, T2, T3) => Any): ProofStep
      def SubProof[T1, T2, T3, T4](let: (T1, T2, T3, T4) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5](let: (T1, T2, T3, T4, T5) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6](let: (T1, T2, T3, T4, T5, T6) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7](let: (T1, T2, T3, T4, T5, T6, T7) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8](let: (T1, T2, T3, T4, T5, T6, T7, T8) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Any): ProofStep
      def SubProof[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](let: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Any): ProofStep
    }

    trait StepBuilder2 extends Any {
      def by(just: Justification): ProofStep = ???
      def by(fact: Fact): ProofStep = ???
      def by(theorem: Theorem): ProofStep = ???
      def by(lemma: Lemma): ProofStep = ???
      def by(just: Predef.String): ProofStep = ???
      def by(o: Unit): StepBuilder3 = ???
      def by[T1](f: T1 => Unit): StepBuilder3 = ???
      def by[T1, T2](f: (T1, T2) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3](f: (T1, T2, T3) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4](f: (T1, T2, T3, T4) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5](f: (T1, T2, T3, T4, T5) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6](f: (T1, T2, T3, T4, T5, T6) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7](f: (T1, T2, T3, T4, T5, T6, T7) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8](f: (T1, T2, T3, T4, T5, T6, T7, T8) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Unit): StepBuilder3 = ???
      def by[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Unit): StepBuilder3 = ???
    }

    trait StepBuilder3 {
      def and(args: StepId*): ProofStep
      def *(args: StepId*): ProofStep
      def T: ProofStep
    }

    object StepBuilder3 {
      import language.implicitConversions
      implicit def toProofStep(o: StepBuilder3): ProofStep = o.and()
    }

    trait SequentBuilder extends Any {
      def |-(conclusion: B): Sequent = ???
      def ⊢(conclusion: B): Sequent = ???
    }

    // begin InfoFlow traits
    trait InfoFlows

    trait InfoFlowInvariant

    trait FlowElement

    trait FlowFlow extends FlowElement

    trait FlowFrom

    trait FlowTo

    trait FlowCase extends FlowElement

    trait InAgree

    trait OutAgree

    trait Groups extends FlowElement

    trait Vars

    // end InfoFlow traits
  }

  def MaxIt(num: Z): Contract.MaxIt = ???

  def Reads(accesses: Any*): Contract.Reads = ???

  def Requires(claims: B*): Contract.Requires = ???

  def Modifies(accesses: Any*): Contract.Modifies = ???

  def Ensures(claims: B*): Contract.Ensures = ???

  def Invariant(claims: B*): Contract.Invariant = ???

  def Invariant(desc: String, claims: B*): Contract.Invariant = ???

  def DataRefinement(rep: Any)(refs: Any*)(claims: B*): Contract.DataRefinement = ???

  def Case(requires: Contract.Requires): Contract.Case = ???

  def Case(ensures: Contract.Ensures): Contract.Case = ???

  def Case(requires: Contract.Requires, ensures: Contract.Ensures): Contract.Case = ???

  def Case(name: String, requires: Contract.Requires): Contract.Case = ???

  def Case(name: String, ensures: Contract.Ensures): Contract.Case = ???

  def Case(name: String, requires: Contract.Requires, ensures: Contract.Ensures): Contract.Case = ???

  def Case(name: String, case0: Contract.Case, cases: Contract.Case*): Contract.Case = ???

  def Case(requires: Contract.Requires, case0: Contract.Case, cases: Contract.Case*): Contract.Case = ???

  def Case(ensures: Contract.Ensures, case0: Contract.Case, cases: Contract.Case*): Contract.Case = ???

  def Case(requires: Contract.Requires, ensures: Contract.Ensures, case0: Contract.Case, cases: Contract.Case*): Contract.Case = ???

  def Case(name: String, requires: Contract.Requires, case0: Contract.Case, cases: Contract.Case*): Contract.Case = ???

  def Case(name: String, ensures: Contract.Ensures, case0: Contract.Case, cases: Contract.Case*): Contract.Case = ???

  def Case(name: String, requires: Contract.Requires, ensures: Contract.Ensures, case0: Contract.Case, cases: Contract.Case*): Contract.Case = ???

  def Case(name: String, cases: Contract.Cases): Contract.Case = ???

  def Case(requires: Contract.Requires, cases: Contract.Cases): Contract.Case = ???

  def Case(ensures: Contract.Ensures, cases: Contract.Cases): Contract.Case = ???

  def Case(requires: Contract.Requires, ensures: Contract.Ensures, cases: Contract.Cases): Contract.Case = ???

  def Case(name: String, requires: Contract.Requires, cases: Contract.Cases): Contract.Case = ???

  def Case(name: String, ensures: Contract.Ensures, cases: Contract.Cases): Contract.Case = ???

  def Case(name: String, requires: Contract.Requires, ensures: Contract.Ensures, cases: Contract.Cases): Contract.Case = ???

  def Cases(arg0: Contract.Case, arg1: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Reads, arg1: Contract.Case, arg2: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Requires, arg1: Contract.Case, arg2: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Modifies, arg1: Contract.Case, arg2: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Ensures, arg1: Contract.Case, arg2: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Reads, arg1: Contract.Requires, arg2: Contract.Case, arg3: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Reads, arg1: Contract.Modifies, arg2: Contract.Case, arg3: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Reads, arg1: Contract.Ensures, arg2: Contract.Case, arg3: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Requires, arg1: Contract.Modifies, arg2: Contract.Case, arg3: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Requires, arg1: Contract.Ensures, arg2: Contract.Case, arg3: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Modifies, arg1: Contract.Ensures, arg2: Contract.Case, arg3: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Reads, arg1: Contract.Requires, arg2: Contract.Modifies, arg3: Contract.Case, arg4: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Reads, arg1: Contract.Modifies, arg2: Contract.Ensures, arg3: Contract.Case, arg4: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Reads, arg1: Contract.Requires, arg2: Contract.Ensures, arg3: Contract.Case, arg4: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Requires, arg1: Contract.Modifies, arg2: Contract.Ensures, arg3: Contract.Case, arg4: Contract.Case*): Contract.Cases = ???

  def Cases(arg0: Contract.Reads, arg1: Contract.Requires, arg2: Contract.Modifies, arg3: Contract.Ensures, arg4: Contract.Case, arg5: Contract.Case*): Contract.Cases = ???

  def Invariant(arg0: Contract.Modifies, arg1: B*): Unit = macro Macro.lUnit1S

  def Invariant(arg0: Contract.MaxIt, arg1: B*): Unit = macro Macro.lUnit1S

  def Invariant(arg0: Contract.MaxIt, arg1: Contract.Modifies, arg2: B*): Unit = macro Macro.lUnit2S

  def Fact(claims: B*): Contract.Fact = ???

  def Fact(desc: String, claims: B*): Contract.Fact = ???

  def Lemma(claim: B, proof: Contract.Proof): Contract.Lemma = ???

  def Lemma(desc: String, claim: B, proof: Contract.Proof): Contract.Lemma = ???

  def Theorem(claim: B): Contract.Theorem = ???

  def Theorem(claim: B, proof: Contract.Proof): Contract.Theorem = ???

  def Theorem(desc: String, claim: B, proof: Contract.Proof): Contract.Theorem = ???

  def Proof(steps: Contract.ProofStep*): Contract.Proof = ???

  def Deduce(arg0: Contract.ProofStep, arg1: Contract.ProofStep*): Unit = macro Macro.lUnit1S

  def Deduce(arg0: Contract.Sequent, arg1: Contract.Sequent*): Unit = macro Macro.lUnit1S

  def Deduce(arg0: String, arg1: Contract.Sequent, arg2: Contract.Sequent*): Unit = macro Macro.lUnit2S

  def Step(no: Int, claim: B): Contract.ProofStep = ???

  def Step(no: Int, claim: B, just: Contract.Justification): Contract.ProofStep = ???

  def SubProof(steps: Contract.ProofStep*): Contract.SubProof = ???

  def Assume(claim: B): Contract.Assume = ???

  def Assert(claim: B, subProof: Contract.SubProof): Contract.Assert = ???

  def Let[T](body: T => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2](body: (T1, T2) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3](body: (T1, T2, T3) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4](body: (T1, T2, T3, T4) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5](body: (T1, T2, T3, T4, T5) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6](body: (T1, T2, T3, T4, T5, T6) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7](body: (T1, T2, T3, T4, T5, T6, T7) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8](body: (T1, T2, T3, T4, T5, T6, T7, T8) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Contract.SubProof): Contract.Let = ???

  def In[T](v: T): T = ???
  def Old[T](v: T): T = ???

  def Res[T]: T = ???

  def Idx[@index T](v: Any): T = ???

  def |-(conclusion: B): Contract.Sequent = ???

  def ⊢(conclusion: B): Contract.Sequent = ???

  // begin InfoFlow nodes
  def InfoFlows(flowCases: Contract.FlowElement*): Contract.InfoFlows = ???

  def InfoFlowInvariant(flowCases: Contract.FlowCase*): B = ???

  def AssumeAgree(channel: String): B = ???

  def AssumeAgree(channel: String, requires: Contract.Requires): B = ???

  def AssumeAgree(channel: String, inAgree: Contract.InAgree): B = ???

  def AssumeAgree(channel: String, requires: Contract.Requires, inAgree: Contract.InAgree): B = ???


  def AssertAgree(channel: String): B = ???

  def AssertAgree(channel: String, outAgree: Contract.OutAgree): B = ???


  def Flow(channel: String, from: Contract.FlowFrom, to: Contract.FlowTo): Contract.FlowFlow = ???

  def From(from: Any*): Contract.FlowFrom = ???

  def To(to: Any*): Contract.FlowTo = ???


  def FlowCase(channel: String, inAgreements: Contract.InAgree, outAgreements: Contract.OutAgree): Contract.FlowCase = ???

  def FlowCase(channel: String, requires: Contract.Requires, inAgreements: Contract.InAgree, outAgreements: Contract.OutAgree): Contract.FlowCase = ???

  def InAgree(inAgree: Any*): Contract.InAgree = ???

  def OutAgree(outAgree: Any*): Contract.OutAgree = ???


  def Groups(label: String, vars: Contract.Vars): Contract.Groups = ???

  def Vars(v: Any*): Contract.Vars = ???

  // end InfoFlow nodes

  object All {

    def apply[T](p: T => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2](p: (T1, T2) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3](p: (T1, T2, T3) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4](p: (T1, T2, T3, T4) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5](p: (T1, T2, T3, T4, T5) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6](p: (T1, T2, T3, T4, T5, T6) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7](p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8](p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = halt("This form of All is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = halt("This form of All is not executable")


    def apply[I](seq: ZRange[I])(p: I => Boolean): B = {
      for (e <- seq) {
        if (!p(e)) {
          return F
        }
      }
      return T
    }

    def apply(seq: scala.collection.immutable.Range)(p: Z => Boolean): B = {
      for (e <- seq) {
        if (!p(e)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T](seq: IS[I, T])(p: T => Boolean): B = {
      for (e <- seq) {
        if (!p(e)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2](seq: IS[I, (T1, T2)])(p: (T1, T2) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3](seq: IS[I, (T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4](seq: IS[I, (T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5](seq: IS[I, (T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6](seq: IS[I, (T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21, e._22)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T](seq: MS[I, T])(p: T => Boolean): B = {
      for (e <- seq) {
        if (!p(e)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2](seq: MS[I, (T1, T2)])(p: (T1, T2) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3](seq: MS[I, (T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4](seq: MS[I, (T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5](seq: MS[I, (T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6](seq: MS[I, (T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21)) {
          return F
        }
      }
      return T
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21, e._22)) {
          return F
        }
      }
      return T
    }

  }

  object ∀ {

    @inline def apply[T](p: T => Boolean): B = All(p)
    @inline def apply[T1, T2](p: (T1, T2) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3](p: (T1, T2, T3) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4](p: (T1, T2, T3, T4) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5](p: (T1, T2, T3, T4, T5) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6](p: (T1, T2, T3, T4, T5, T6) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7](p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8](p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = All(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = All(p)

    @inline def apply[I](seq: ZRange[I])(p: I => Boolean): B = All(seq)(p)

    @inline def apply[@index I, T](seq: IS[I, T])(p: T => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2](seq: IS[I, (T1, T2)])(p: (T1, T2) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3](seq: IS[I, (T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4](seq: IS[I, (T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5](seq: IS[I, (T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6](seq: IS[I, (T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = All(seq)(p)

    @inline def apply[@index I, T](seq: MS[I, T])(p: T => Boolean): B =  All(seq)(p)
    @inline def apply[@index I, T1, T2](seq: MS[I, (T1, T2)])(p: (T1, T2) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3](seq: MS[I, (T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4](seq: MS[I, (T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5](seq: MS[I, (T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6](seq: MS[I, (T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = All(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = All(seq)(p)

  }

  object Exists {

    def apply[T](p: T => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2](p: (T1, T2) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3](p: (T1, T2, T3) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4](p: (T1, T2, T3, T4) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5](p: (T1, T2, T3, T4, T5) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6](p: (T1, T2, T3, T4, T5, T6) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7](p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8](p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = halt("This form of Exists is not executable")
    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = halt("This form of Exists is not executable")

    def apply[I](seq: ZRange[I])(p: I => Boolean): B = {
      for (e <- seq) {
        if (p(e)) {
          return T
        }
      }
      return F
    }

    def apply(seq: scala.collection.immutable.Range)(p: Z => Boolean): B = {
      for (e <- seq) {
        if (p(e)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T](seq: IS[I, T])(p: T => Boolean): B = {
      for (e <- seq) {
        if (p(e)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2](seq: IS[I, (T1, T2)])(p: (T1, T2) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3](seq: IS[I, (T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4](seq: IS[I, (T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5](seq: IS[I, (T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6](seq: IS[I, (T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21, e._22)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T](seq: MS[I, T])(p: T => Boolean): B = {
      for (e <- seq) {
        if (p(e)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2](seq: MS[I, (T1, T2)])(p: (T1, T2) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3](seq: MS[I, (T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4](seq: MS[I, (T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5](seq: MS[I, (T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6](seq: MS[I, (T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21)) {
          return T
        }
      }
      return F
    }

    def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21, e._22)) {
          return T
        }
      }
      return F
    }

  }

  object ∃ {

    @inline def apply[T](p: T => Boolean): B = Exists(p)
    @inline def apply[T1, T2](p: (T1, T2) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3](p: (T1, T2, T3) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4](p: (T1, T2, T3, T4) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5](p: (T1, T2, T3, T4, T5) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6](p: (T1, T2, T3, T4, T5, T6) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7](p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8](p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = Exists(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = Exists(p)

    @inline def apply[I](seq: ZRange[I])(p: I => Boolean): B = Exists(seq)(p)

    @inline def apply[@index I, T](seq: IS[I, T])(p: T => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2](seq: IS[I, (T1, T2)])(p: (T1, T2) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3](seq: IS[I, (T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4](seq: IS[I, (T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5](seq: IS[I, (T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6](seq: IS[I, (T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: IS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = Exists(seq)(p)

    @inline def apply[@index I, T](seq: MS[I, T])(p: T => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2](seq: MS[I, (T1, T2)])(p: (T1, T2) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3](seq: MS[I, (T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4](seq: MS[I, (T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5](seq: MS[I, (T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6](seq: MS[I, (T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = Exists(seq)(p)
    @inline def apply[@index I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: MS[I, (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = Exists(seq)(p)

  }

  import language.implicitConversions

  implicit def $toStepBuilder(stepNo: Int): Contract.StepBuilder = ???

  implicit def $toStepBuilder(stepNo: Long): Contract.StepBuilder = ???

  implicit def $toStepBuilder(stepNo: Z): Contract.StepBuilder = ???

  implicit def $toStepBuilder(stepNo: Predef.String): Contract.StepBuilder = ???

  implicit def $toStepBuilder2(claim: Boolean): Contract.StepBuilder2 = ???

  implicit def $toStepBuilder3(o: Unit): Contract.StepBuilder3 = ???

  implicit def $toStepId(stepNo: Int): StepId = ???

  implicit def $toStepId(stepNo: Long): StepId = ???

  implicit def $toStepId(stepNo: Z): StepId = ???

  implicit def $toStepId(stepNo: Predef.String): StepId = ???

  implicit def $toB(state: Contract.StateCont): B = ???

  implicit def $toSequent(b: Boolean): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???
  implicit def $toSequent(bs: (B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B)): Contract.SequentBuilder = ???

}
