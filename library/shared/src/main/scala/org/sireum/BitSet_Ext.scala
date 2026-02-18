/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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

object BitSet_Ext {

  def fromISB(a: IS[S32, B]): BitSet = {
    val n = a.size.toLong
    val numWords = ((n - 1) / 64 + 1).toInt
    val words = new Array[scala.Long](numWords)
    // Read directly from the IS[S32, B] backing byte array (bit-packed by B.Boxer)
    var i = 0L
    while (i < n) {
      if (a(i).value) {
        val wordIndex = (i >>> 6).toInt
        words(wordIndex) = words(wordIndex) | (1L << i.toInt)
      }
      i += 1L
    }
    new Impl(words)
  }

  private final class Impl(val words: Array[scala.Long]) extends BitSet with DatatypeSig {

    override def isSet(i: Z): B = {
      val idx = i.toLong
      val wordIndex = (idx >>> 6).toInt
      B(wordIndex < words.length && (words(wordIndex) & (1L << idx.toInt)) != 0L)
    }

    override def isSetS32(i: S32): B = {
      val idx = i.value
      val wordIndex = idx >>> 6
      B(wordIndex < words.length && (words(wordIndex) & (1L << idx)) != 0L)
    }

    override def isSetS64(i: S64): B = {
      val idx = i.value
      val wordIndex = (idx >>> 6).toInt
      B(wordIndex < words.length && (words(wordIndex) & (1L << idx.toInt)) != 0L)
    }

    override def isSetU32(i: U32): B = {
      val idx = i.value
      val wordIndex = idx >>> 6
      B(wordIndex < words.length && (words(wordIndex) & (1L << idx)) != 0L)
    }

    override def isSetU64(i: U64): B = {
      val idx = i.value
      val wordIndex = (idx >>> 6).toInt
      B(wordIndex < words.length && (words(wordIndex) & (1L << idx.toInt)) != 0L)
    }

    override def string: String = {
      val sb = new java.lang.StringBuilder("BitSet(")
      var first = true
      var i = 0
      while (i < words.length) {
        var bits = words(i)
        while (bits != 0L) {
          val bit = java.lang.Long.numberOfTrailingZeros(bits)
          if (!first) sb.append(", ")
          sb.append(i * 64 + bit)
          first = false
          bits &= bits - 1L
        }
        i += 1
      }
      sb.append(")")
      String(sb.toString)
    }

    override lazy val hashCode: scala.Int = java.util.Arrays.hashCode(words)

    override def hash: Z = Z.MP(hashCode)

    override def $content: scala.Seq[(Predef.String, scala.Any)] =
      scala.Seq(("words", words.toSeq))

    override def ===(other: SigTrait): B = other match {
      case o: Impl => B(java.util.Arrays.equals(words, o.words))
      case _ => F
    }

    override def equals(other: scala.Any): scala.Boolean = other match {
      case o: SigTrait => ===(o).value
      case _ => false
    }
  }
}
