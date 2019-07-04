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

    def apply(arg0: Case, arg1: Case*): Unit = macro Macro.lUnit1S

    def apply(arg0: Modifies, arg1: Case*): Unit = macro Macro.lUnit1S

    def apply(arg0: => Unit): Unit = macro Macro.lUnit1

    object Only {

      def apply[T](arg0: Requires): T = ???

      def apply[T](arg0: Modifies): T = ???

      def apply[T](arg0: Ensures): T = ???

      def apply[T](arg0: Requires, arg1: Ensures): T = ???

      def apply[T](arg0: Requires, arg1: Modifies): T = ???

      def apply[T](arg0: Modifies, arg1: Ensures): T = ???

      def apply[T](arg0: Requires, arg1: Modifies, arg2: Ensures): T = ???

      def apply[T](arg0: String, arg1: Requires): T = ???

      def apply[T](arg0: String, arg1: Modifies): T = ???

      def apply[T](arg0: String, arg1: Ensures): T = ???

      def apply[T](arg0: String, arg1: Requires, arg2: Ensures): T = ???

      def apply[T](arg0: String, arg1: Requires, arg2: Modifies): T = ???

      def apply[T](arg0: String, arg1: Modifies, arg2: Ensures): T = ???

      def apply[T](arg0: String, arg1: Requires, arg2: Modifies, arg3: Ensures): T = ???

      def apply[T](arg0: Case*): T = ???

      def apply[T](arg0: Modifies, arg1: Case*): T = ???
    }

    trait Case

    trait Requires

    trait Modifies

    trait Ensures

    trait Proof

    trait SubProof

    trait ProofStep

    trait Justification

    trait Invariant

    trait Lemma

    trait Theorem

    trait Axiom

    trait Assume

    trait Assert

    trait Let

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

  def Requires(claims: B*): Contract.Requires = ???

  def Modifies(claims: Any*): Contract.Modifies = ???

  def Ensures(claims: B*): Contract.Ensures = ???

  def Invariant(arg0: B*): Contract.Invariant = ???

  def Invariant(arg0: String, arg1: B*): Contract.Invariant = ???

  def Case(arg0: Contract.Requires): Contract.Case = ???

  def Case(arg0: Contract.Ensures): Contract.Case = ???

  def Case(arg0: Contract.Requires, arg1: Contract.Ensures): Contract.Case = ???

  def Case(arg0: String, arg1: Contract.Requires): Contract.Case = ???

  def Case(arg0: String, arg1: Contract.Ensures): Contract.Case = ???

  def Case(arg0: String, arg1: Contract.Requires, arg2: Contract.Ensures): Contract.Case = ???

  def LoopInvariant(arg0: Contract.Modifies, arg1: B*): Unit = macro Macro.lUnit1S

  def Axiom(arg0: B*): Contract.Axiom = ???

  def Axiom(arg0: String, arg1: B*): Contract.Axiom = ???

  def Lemma(arg0: B, arg1: Contract.Proof): Contract.Lemma = ???

  def Lemma(arg0: String, arg1: B, arg2: Contract.Proof): Contract.Lemma = ???

  def Theorem(arg0: B, arg1: Contract.Proof): Contract.Theorem = ???

  def Theorem(arg0: String, arg1: B, arg2: Contract.Proof): Contract.Theorem = ???

  def Proof(arg0: Contract.ProofStep*): Contract.Proof = ???

  def Step(arg0: Int, arg1: B): Contract.ProofStep = ???

  def Step(arg0: Int, arg1: B, arg2: Contract.Justification): Contract.ProofStep = ???

  def SubProof(arg0: Contract.ProofStep*): Contract.SubProof = ???

  def Assume(arg0: B): Contract.Assume = ???

  def Assert(arg0: B, arg1: Contract.SubProof): Contract.Assert = ???

  def StructuralInduction(arg0: Contract.SubProof): Contract.Justification = ???

  def Let[T](arg0: T => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2](arg0: (T1, T2) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3](arg0: (T1, T2, T3) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4](arg0: (T1, T2, T3, T4) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5](arg0: (T1, T2, T3, T4, T5) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6](arg0: (T1, T2, T3, T4, T5, T6) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7](arg0: (T1, T2, T3, T4, T5, T6, T7) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8](arg0: (T1, T2, T3, T4, T5, T6, T7, T8) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Contract.SubProof): Contract.Let = ???
  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](arg0: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Contract.SubProof): Contract.Let = ???

  def In[T](v: T): T = ???

  def Old[T](v: T): T = ???

  def Result[T]: T = ???

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

    def apply[T](seq: ISZ[T])(p: T => Boolean): B = {
      for (e <- seq) {
        if (!p(e)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2](seq: ISZ[(T1, T2)])(p: (T1, T2) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3](seq: ISZ[(T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4](seq: ISZ[(T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5](seq: ISZ[(T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6](seq: ISZ[(T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21, e._22)) {
          return F
        }
      }
      return T
    }

    def apply[T](seq: MSZ[T])(p: T => Boolean): B = {
      for (e <- seq) {
        if (!p(e)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2](seq: MSZ[(T1, T2)])(p: (T1, T2) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3](seq: MSZ[(T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4](seq: MSZ[(T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5](seq: MSZ[(T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6](seq: MSZ[(T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = {
      for (e <- seq) {
        if (!p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21)) {
          return F
        }
      }
      return T
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = {
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

    @inline def apply[T](seq: ISZ[T])(p: T => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2](seq: ISZ[(T1, T2)])(p: (T1, T2) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3](seq: ISZ[(T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4](seq: ISZ[(T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5](seq: ISZ[(T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6](seq: ISZ[(T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = All(seq)(p)

    @inline def apply[T](seq: MSZ[T])(p: T => Boolean): B =  All(seq)(p)
    @inline def apply[T1, T2](seq: MSZ[(T1, T2)])(p: (T1, T2) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3](seq: MSZ[(T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4](seq: MSZ[(T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5](seq: MSZ[(T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6](seq: MSZ[(T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = All(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = All(seq)(p)

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

    def apply[T](seq: ISZ[T])(p: T => Boolean): B = {
      for (e <- seq) {
        if (p(e)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2](seq: ISZ[(T1, T2)])(p: (T1, T2) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3](seq: ISZ[(T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4](seq: ISZ[(T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5](seq: ISZ[(T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6](seq: ISZ[(T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21, e._22)) {
          return T
        }
      }
      return F
    }

    def apply[T](seq: MSZ[T])(p: T => Boolean): B = {
      for (e <- seq) {
        if (p(e)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2](seq: MSZ[(T1, T2)])(p: (T1, T2) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3](seq: MSZ[(T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4](seq: MSZ[(T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5](seq: MSZ[(T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6](seq: MSZ[(T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = {
      for (e <- seq) {
        if (p(e._1, e._2, e._3, e._4, e._5, e._6, e._7, e._8, e._9, e._10, e._11, e._12, e._13, e._14, e._15, e._16, e._17, e._18, e._19, e._20, e._21)) {
          return T
        }
      }
      return F
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = {
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

    @inline def apply[T](seq: ISZ[T])(p: T => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2](seq: ISZ[(T1, T2)])(p: (T1, T2) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3](seq: ISZ[(T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4](seq: ISZ[(T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5](seq: ISZ[(T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6](seq: ISZ[(T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: ISZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = Exists(seq)(p)

    @inline def apply[T](seq: MSZ[T])(p: T => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2](seq: MSZ[(T1, T2)])(p: (T1, T2) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3](seq: MSZ[(T1, T2, T3)])(p: (T1, T2, T3) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4](seq: MSZ[(T1, T2, T3, T4)])(p: (T1, T2, T3, T4) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5](seq: MSZ[(T1, T2, T3, T4, T5)])(p: (T1, T2, T3, T4, T5) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6](seq: MSZ[(T1, T2, T3, T4, T5, T6)])(p: (T1, T2, T3, T4, T5, T6) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7)])(p: (T1, T2, T3, T4, T5, T6, T7) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8)])(p: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Boolean): B = Exists(seq)(p)
    @inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](seq: MSZ[(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22)])(p: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Boolean): B = Exists(seq)(p)

  }

  import language.implicitConversions

  implicit def $toStepBuilder(stepNo: Int): Contract.StepBuilder = ???

  implicit def $toStepBuilder(stepNo: Z): Contract.StepBuilder = ???

  implicit def $toJustificationBuilder(name: String): Contract.JustificationBuilder = ???

  implicit def $toJustification(name: String): Contract.Justification = ???

  implicit def $toProofStep(sbb: Contract.StepBuilderBy): Contract.ProofStep = ???

  implicit def $toB(sp: Contract.SubProof): B = ???

  implicit def $toB(sp: Contract.Assume): B = ???

  implicit def $toB(sp: Contract.Assert): B = ???

  implicit def $toB(sp: Contract.Let): B = ???

}
