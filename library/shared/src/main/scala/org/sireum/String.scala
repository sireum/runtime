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

object String {

  object Boxer extends $internal.Boxer {
    def box[T](o: scala.Any): T = o match {
      case o: Predef.String => String(o).asInstanceOf[T]
    }

    def unbox(o: scala.Any): Predef.String = o match {
      case o: String => o.value
    }

    override def copyMut(src: AnyRef, srcPos: Z, dest: AnyRef, destPos: Z, length: Z): Unit =
      copy(src, srcPos, dest, destPos, length)
  }

  def escape(o: scala.Any): Predef.String = {
    def z2s(n: scala.Long): Predef.String =
      if (scala.Int.MinValue <= n && n <= scala.Int.MaxValue) n.toString
      else n.toString + 'l'

    o match {
      case o: String => helper.escape(o.value)
      case o: C => val s = helper.escape(o.toString); "'" + s.substring(1, s.length - 1) + "'"
      case o: F32 => o.toString + "f"
      case o: R => "r\"" + o + '"'
      case o: Z.MP.Long => z2s(o.value)
      case o: Z.MP.BigInt => o.pack match {
        case o2: Z.MP.Long => z2s(o2.value)
        case _ => "z\"" + o + '"'
      }
      case o: Z => o.Name.toLowerCase + '"' + o + '"'
      case _ => o.toString
    }
  }

  def random: String = scala.util.Random.alphanumeric.take((N16.random.toZ % 1024).toInt).mkString

  def unapply(s: String): scala.Option[Predef.String] = scala.Some(s.value)

  import scala.language.implicitConversions

  implicit def apply(s: Predef.String): String = new String(s)
}

final class String(val value: Predef.String) extends AnyVal with Immutable with StepId with $internal.HasBoxer {

  def apply(args: scala.Any*): Contract.Justification = halt("")

  @inline def native: Predef.String = value

  @inline def string: String = this

  @inline override def toString: Predef.String = value

  def stripPrefix(prefix: Predef.String): Predef.String = value.stripPrefix(prefix)

  @inline def boxer: $internal.Boxer = String.Boxer

  @inline def size: Z = value.codePointCount(0, value.length)

  @inline def <(other: String): B = value < other.value

  @inline def <=(other: String): B = value <= other.value

  @inline def >(other: String): B = value > other.value

  @inline def >=(other: String): B = value >= other.value

  @inline def ===(other: String): B = value == other.value

  @inline def =!=(other: String): B = value != other.value

  @inline def stripMargin: String = value.stripMargin

  import org.sireum.Contract._

  def #>(cond: B): StepBuilder2 = halt("Cannot be used in code")

  def #>(assume: Assume): ProofStep = halt("Cannot be used in code")

  def #>(assert: Assert): ProofStep = halt("Cannot be used in code")

  def #>(let: Let): ProofStep = halt("Cannot be used in code")

  def #>(subProof: SubProof): ProofStep = halt("Cannot be used in code")

  def apply(cond: B): StepBuilder2 = halt("Cannot be used in code")

  def Assume(cond: B): ProofStep = halt("Cannot be used in code")

  def Assert(cond: B): ProofStep = halt("Cannot be used in code")

  def Let[T](body: T => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2](body: (T1, T2) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3](body: (T1, T2, T3) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4](body: (T1, T2, T3, T4) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5](body: (T1, T2, T3, T4, T5) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6](body: (T1, T2, T3, T4, T5, T6) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7](body: (T1, T2, T3, T4, T5, T6, T7) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8](body: (T1, T2, T3, T4, T5, T6, T7, T8) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def Let[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](body: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Contract.SubProof): ProofStep = halt("Cannot be used in code")

  def SubProof(steps: Contract.ProofStep*): ProofStep = halt("Cannot be used in code")
}
