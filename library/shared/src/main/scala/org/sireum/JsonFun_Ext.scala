/*
 Copyright (c) 2017-2025, Robby, Kansas State University
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

import _root_.java.io._
import _root_.java.util.Base64

object JsonFun_Ext {

  def printObj[T](o: T): Array[Byte] = {
    val baos = new ByteArrayOutputStream()
    val oos = new ObjectOutputStream(baos)
    oos.writeObject(o)
    oos.close()
    return baos.toByteArray
  }

  def parseObj[T](o: Array[Byte]): Option[T] = {
    try {
      val bais = new ObjectInputStream(new ByteArrayInputStream(o))
      return Some(bais.readObject().asInstanceOf[T])
    } catch {
      case _: Throwable => None()
    }
  }

  def print[T](f: T): String = Base64.getEncoder.encodeToString(printObj(f))

  def parse[T](parser: Json.Parser, f: String): Option[T] = {
    try {
      parseObj(Base64.getDecoder.decode(f.value))
    } catch {
      case _: Throwable => None()
    }
  }

  def printPure0[R](f: () => R@pure): String = print(f)

  def parsePure0[R](parser: Json.Parser, f: String): () => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print0[R](f: () => R): String = print(f)

  def parse0[R](parser: Json.Parser, f: String): () => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure1[T1, R](f: T1 => R@pure): String = print(f)

  def parsePure1[T1, R](parser: Json.Parser, f: String): T1 => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print1[T1, R](f: T1 => R): String = print(f)

  def parse1[T1, R](parser: Json.Parser, f: String): T1 => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure2[T1, T2, R](f: (T1, T2) => R@pure): String = print(f)

  def parsePure2[T1, T2, R](parser: Json.Parser, f: String): (T1, T2) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print2[T1, T2, R](f: (T1, T2) => R): String = print(f)

  def parse2[T1, T2, R](parser: Json.Parser, f: String): (T1, T2) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure3[T1, T2, T3, R](f: (T1, T2, T3) => R@pure): String = print(f)

  def parsePure3[T1, T2, T3, R](parser: Json.Parser, f: String): (T1, T2, T3) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print3[T1, T2, T3, R](f: (T1, T2, T3) => R): String = print(f)

  def parse3[T1, T2, T3, R](parser: Json.Parser, f: String): (T1, T2, T3) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure4[T1, T2, T3, T4, R](f: (T1, T2, T3, T4) => R@pure): String = print(f)

  def parsePure4[T1, T2, T3, T4, R](parser: Json.Parser, f: String): (T1, T2, T3, T4) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print4[T1, T2, T3, T4, R](f: (T1, T2, T3, T4) => R): String = print(f)

  def parse4[T1, T2, T3, T4, R](parser: Json.Parser, f: String): (T1, T2, T3, T4) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure5[T1, T2, T3, T4, T5, R](f: (T1, T2, T3, T4, T5) => R@pure): String = print(f)

  def parsePure5[T1, T2, T3, T4, T5, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print5[T1, T2, T3, T4, T5, R](f: (T1, T2, T3, T4, T5) => R): String = print(f)

  def parse5[T1, T2, T3, T4, T5, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure6[T1, T2, T3, T4, T5, T6, R](f: (T1, T2, T3, T4, T5, T6) => R@pure): String = print(f)

  def parsePure6[T1, T2, T3, T4, T5, T6, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print6[T1, T2, T3, T4, T5, T6, R](f: (T1, T2, T3, T4, T5, T6) => R): String = print(f)

  def parse6[T1, T2, T3, T4, T5, T6, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure7[T1, T2, T3, T4, T5, T6, T7, R](f: (T1, T2, T3, T4, T5, T6, T7) => R@pure): String = print(f)

  def parsePure7[T1, T2, T3, T4, T5, T6, T7, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print7[T1, T2, T3, T4, T5, T6, T7, R](f: (T1, T2, T3, T4, T5, T6, T7) => R): String = print(f)

  def parse7[T1, T2, T3, T4, T5, T6, T7, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure8[T1, T2, T3, T4, T5, T6, T7, T8, R](f: (T1, T2, T3, T4, T5, T6, T7, T8) => R@pure): String = print(f)

  def parsePure8[T1, T2, T3, T4, T5, T6, T7, T8, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print8[T1, T2, T3, T4, T5, T6, T7, T8, R](f: (T1, T2, T3, T4, T5, T6, T7, T8) => R): String = print(f)

  def parse8[T1, T2, T3, T4, T5, T6, T7, T8, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => R@pure): String = print(f)

  def parsePure9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => R): String = print(f)

  def parse9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => R@pure): String = print(f)

  def parsePure10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => R): String = print(f)

  def parse10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => R@pure): String = print(f)

  def parsePure11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => R): String = print(f)

  def parse11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => R@pure): String = print(f)

  def parsePure12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => R): String = print(f)

  def parse12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => R@pure): String = print(f)

  def parsePure13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => R): String = print(f)

  def parse13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => R@pure): String = print(f)

  def parsePure14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => R): String = print(f)

  def parse14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => R@pure): String = print(f)

  def parsePure15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => R): String = print(f)

  def parse15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => R@pure): String = print(f)

  def parsePure16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => R): String = print(f)

  def parse16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => R@pure): String = print(f)

  def parsePure17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => R): String = print(f)

  def parse17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => R@pure): String = print(f)

  def parsePure18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => R): String = print(f)

  def parse18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => R@pure): String = print(f)

  def parsePure19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => R): String = print(f)

  def parse19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => R@pure): String = print(f)

  def parsePure20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => R): String = print(f)

  def parse20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => R@pure): String = print(f)

  def parsePure21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => R): String = print(f)

  def parse21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def printPure22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R@pure): String = print(f)

  def parsePure22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R@pure = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }

  def print22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R): String = print(f)

  def parse22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](parser: Json.Parser, f: String): (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R = parse(parser, f) match {
    case Some(g) => g
    case _ => parser.parseException(parser.offset, "Failed to parse closure");
    {
      case _ => null.asInstanceOf[R]
    }
  }
}
