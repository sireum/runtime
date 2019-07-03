/*
 Copyright (c) 2019, Robby, Kansas State University
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

  object Contract {

    def apply(arg0: Requires): Unit = macro Macro.lUnit1

    def apply(arg0: Modifies): Unit = macro Macro.lUnit1

    def apply(arg0: Ensures): Unit = macro Macro.lUnit1

    def apply(arg0: Requires, arg1: Ensures): Unit = macro Macro.lUnit2

    def apply(arg0: Requires, arg1: Modifies): Unit = macro Macro.lUnit2

    def apply(arg0: Modifies, arg1: Ensures): Unit = macro Macro.lUnit2

    def apply(arg0: Requires, arg1: Modifies, arg2: Ensures): Unit = macro Macro.lUnit3

    def apply(arg0: CCase, arg1: CCase*): Unit = macro Macro.lUnit1S

    def apply(arg0: Modifies, arg1: CCase*): Unit = macro Macro.lUnit1S

    def apply(arg0: => Unit): Unit = macro Macro.lUnit1

    object Only {

      def apply[T](arg0: Requires): T = macro Macro.lNothing1[T]

      def apply[T](arg0: Modifies): T = macro Macro.lNothing1[T]

      def apply[T](arg0: Ensures): T = macro Macro.lNothing1[T]

      def apply[T](arg0: Requires, arg1: Ensures): T = macro Macro.lNothing2[T]

      def apply[T](arg0: Requires, arg1: Modifies): T = macro Macro.lNothing2[T]

      def apply[T](arg0: Modifies, arg1: Ensures): T = macro Macro.lNothing2[T]

      def apply[T](arg0: Requires, arg1: Modifies, arg2: Ensures): T = macro Macro.lNothing3[T]

      def apply[T](arg0: String, arg1: Requires): T = macro Macro.lNothing2[T]

      def apply[T](arg0: String, arg1: Modifies): T = macro Macro.lNothing2[T]

      def apply[T](arg0: String, arg1: Ensures): T = macro Macro.lNothing2[T]

      def apply[T](arg0: String, arg1: Requires, arg2: Ensures): T = macro Macro.lNothing3[T]

      def apply[T](arg0: String, arg1: Requires, arg2: Modifies): T = macro Macro.lNothing3[T]

      def apply[T](arg0: String, arg1: Modifies, arg2: Ensures): T = macro Macro.lNothing3[T]

      def apply[T](arg0: String, arg1: Requires, arg2: Modifies, arg3: Ensures): T = macro Macro.lNothing4[T]

      def apply[T](arg0: CCase*): T = macro Macro.lNothing0S[T]

      def apply[T](arg0: Modifies, arg1: CCase*): T = macro Macro.lNothing1S[T]
    }

  }

  trait CCase

  trait Requires

  trait Modifies

  trait Ensures

  trait Proof

  trait ProofStep

  trait Justification

  def Requires(claims: B*): Requires = halt("Contract.Requires should be erased")

  def Modifies(claims: Any*): Modifies = halt("Contract.Requires should be erased")

  def Ensures(claims: B*): Ensures = halt("Contract.Requires should be erased")

  def Invariant(arg0: B*): Unit = macro Macro.lUnit0S

  def Invariant(arg0: String, arg1: B*): Unit = macro Macro.lUnit1S

  def Axiom(arg0: B*): Unit = macro Macro.lUnit0S

  def Axiom(arg0: String, arg1: B*): Unit = macro Macro.lUnit1S

  def Case(arg0: Requires): CCase = macro Macro.lNothing1[CCase]

  def Case(arg0: Ensures): CCase = macro Macro.lNothing1[CCase]

  def Case(arg0: Requires, arg1: Ensures): CCase = macro Macro.lNothing2[CCase]

  def Case(arg0: String, arg1: Requires): CCase = macro Macro.lNothing2[CCase]

  def Case(arg0: String, arg1: Ensures): CCase = macro Macro.lNothing2[CCase]

  def Case(arg0: String, arg1: Requires, arg2: Ensures): CCase = macro Macro.lNothing3[CCase]

  def LoopInvariant(arg0: Modifies, arg1: B*): Unit = macro Macro.lUnit1S

  def Lemma(arg0: B, arg1: Proof): Unit = macro Macro.lUnit2

  def Lemma(arg0: String, arg1: B, arg2: Proof): Unit = macro Macro.lUnit3

  def Theorem(arg0: B, arg1: Proof): Unit = macro Macro.lUnit2

  def Theorem(arg0: String, arg1: B, arg2: Proof): Unit = macro Macro.lUnit3

  def Proof(arg0: ProofStep*): Proof = macro Macro.lNothing0S[Proof]

  def Step(arg0: Int, arg1: B): ProofStep = macro Macro.lNothing2[ProofStep]

  def Step(arg0: Int, arg1: B, arg2: Justification): ProofStep = macro Macro.lNothing3[ProofStep]

  def SubProof(arg0: ProofStep*): B = macro Macro.lNothing0S[B]

  def SubProof[T1](arg0: T1 => Unit): B = macro Macro.lNothing1[B]

  def Assume(arg0: B): B = macro Macro.lNothing1[B]

  def Assert(arg0: B): B = macro Macro.lNothing1[B]

  def SubProof[T1, T2](arg0: (T1, T2) => ISZ[ProofStep]): B = macro Macro.lNothing1[B]

  def StructuralInduction(arg0: ISZ[ProofStep]): Justification = macro Macro.lNothing1[B]

  def In[T](v: T): T = halt("In should be erased")

  def Old[T](v: T): T = halt("Old should be erased")

  def Result[T]: T = halt("Result should be erased")

  object All {

    def apply[T](p: T => Boolean): B = halt("All should be erased")

    def apply[T](seq: ISZ[T])(p: T => Boolean): B = halt("All should be erased")

    def apply[T](seq: MSZ[T])(p: T => Boolean): B = halt("All should be erased")

  }

  object ∀ {

    def apply[T](p: T => Boolean): B = halt("∀ should be erased")

    def apply[T1, T2](p: (T1, T2) => Boolean): B = halt("∀ should be erased")

    def apply[T](seq: ISZ[T])(p: T => Boolean): B = halt("∀ should be erased")

    def apply[T](seq: MSZ[T])(p: T => Boolean): B =  halt("∀ should be erased")

  }

  object Exists {

    def apply[T](p: T => Boolean): B = halt("Exists should be erased")

    def apply[T](seq: ISZ[T])(p: T => Boolean): B = halt("Exists should be erased")

    def apply[T](seq: MSZ[T])(p: T => Boolean): B = halt("Exists should be erased")

  }

  object ∃ {

    def apply[T](p: T => Boolean): B = halt("∃ should be erased")

    def apply[T](seq: ISZ[T])(p: T => Boolean): B = halt("∃ should be erased")

    def apply[T](seq: MSZ[T])(p: T => Boolean): B = halt("∃ should be erased")

  }

  import language.implicitConversions

  implicit def toStepBuilder(stepNo: Int): StepBuilder = halt("toStepBuilder should be erased")

  implicit def toStepBuilder(stepNo: Z): StepBuilder = halt("toStepBuilder should be erased")

  implicit def toJustificationBuilder(name: String): JustificationBuilder = halt("toJustificationBuilder should be erased")

  implicit def toJustification(name: String): Justification = halt("toJustificationBuilder should be erased")

  implicit def toProofStep(sbb: StepBuilderBy): ProofStep = halt("toProofStep should be erased")


  trait StepBuilder {
    def #>(cond: B): StepBuilderBy
  }

  trait StepBuilderBy {
    def by(just: Justification): ProofStep
  }

  trait JustificationBuilder {
    def apply(args: Any*): Justification
  }
}