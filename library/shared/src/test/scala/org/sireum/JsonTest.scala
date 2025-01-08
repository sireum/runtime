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

import org.sireum.test._

class JsonTest extends TestSuite {

  val tests = Tests {

    * - assert(parseString("\"a\\rbc\"") =~ "a\rbc")

    * - assert(parseString("\"a\\\"\\rbc\"") =~ "a\"\rbc")

    * - assert(parseNumber("-0") =~ "-0")

    * - assert(parseNumber("12.33") =~ "12.33")

    * - assert(parseNumber("12e23") =~ "12e23")

    * - {
      val o = Either.Left[Z, String](Z.random)
      val s = Json.Printer
        .printEither(T, o, Json.Printer.printZ _, Json.Printer.printString _)
        .render
      val p = Json.Parser.create(s)
      val o2 = p.parseEither[Z, String](p.parseZ _, p.parseString _)
      assert(p.errorOpt.isEmpty)
      assert(o == o2)
    }

    * - {
      val o = MEither.Right[Z, String](String.random)
      val s = Json.Printer
        .printMEither(T, o, Json.Printer.printZ _, Json.Printer.printString _)
        .render
      val p = Json.Parser.create(s)
      val o2 = p.parseMEither[Z, String](p.parseZ _, p.parseString _)
      assert(p.errorOpt.isEmpty)
      assert(o == o2)
    }

    * - {
      for (is <- (0 until 3).map(i =>
        (z"0" until i).map(_ => (String.random, Z.random)))) {
        val o = Map ++ is
        val s = Json.Printer
          .printMap(B.random,
            o,
            Json.Printer.printString _,
            Json.Printer.printZ _)
          .render
        val p = Json.Parser.create(s)
        val o2 = p.parseMap[String, Z](p.parseString _, p.parseZ _)
        assert(p.errorOpt.isEmpty)
        assert(o == o2)
      }
    }

    * - {
      for (is <- (0 until 3).map(i => (z"0" until i).map(_ => String.random))) {
        val o = Set ++ is
        val s =
          Json.Printer.printSet(B.random, o, Json.Printer.printString _).render
        val p = Json.Parser.create(s)
        val o2 = p.parseSet[String](p.parseString _)
        assert(p.errorOpt.isEmpty)
        assert(o == o2)
      }
    }

    * - {
      for (is <- (0 until 3).map(i =>
        (z"0" until i).map(_ => (String.random, Z.random)))) {
        val o = HashSMap ++ is
        val s = Json.Printer
          .printHashSMap(B.random,
            o,
            Json.Printer.printString _,
            Json.Printer.printZ _)
          .render
        val p = Json.Parser.create(s)
        val o2 = p.parseHashSMap[String, Z](p.parseString _, p.parseZ _)
        assert(p.errorOpt.isEmpty)
        assert(o == o2)
      }
    }

    * - {
      for (is <- (0 until 3).map(i => (z"0" until i).map(_ => String.random))) {
        val o = HashSet ++ is
        val s = Json.Printer
          .printHashSet(B.random, o, Json.Printer.printString _)
          .render
        val p = Json.Parser.create(s)
        val o2 = p.parseHashSet[String](p.parseString _)
        assert(p.errorOpt.isEmpty)
        assert(o == o2)
      }
    }

    * - {
      for (is <- (0 until 3).map(i =>
        (z"0" until i).map(_ => (String.random, Z.random)))) {
        val o = HashSMap ++ is
        val s = Json.Printer
          .printHashSMap(B.random,
            o,
            Json.Printer.printString _,
            Json.Printer.printZ _)
          .render
        val p = Json.Parser.create(s)
        val o2 = p.parseHashSMap[String, Z](p.parseString _, p.parseZ _)
        assert(p.errorOpt.isEmpty)
        assert(o == o2)
      }
    }

    * - {
      for (is <- (0 until 3).map(i => (z"0" until i).map(_ => String.random))) {
        val o = HashSSet ++ is
        val s = Json.Printer
          .printHashSSet(B.random, o, Json.Printer.printString _)
          .render
        val p = Json.Parser.create(s)
        val o2 = p.parseHashSSet[String](p.parseString _)
        assert(p.errorOpt.isEmpty)
        assert(o == o2)
      }
    }

    * - {
      for (is <- (0 until 3).map(i => (z"0" until i).map(_ => String.random))) {
        val o = Stack(is)
        val s = Json.Printer
          .printStack(B.random, o, Json.Printer.printString _)
          .render
        val p = Json.Parser.create(s)
        val o2 = p.parseStack[String](p.parseString _)
        assert(p.errorOpt.isEmpty)
        assert(o == o2)
      }
    }

    * - {
      for (is <- (0 until 3).map(i => (z"0" until i).map(_ => String.random))) {
        val o = Bag ++ is
        val s =
          Json.Printer.printBag(B.random, o, Json.Printer.printString _).render
        val p = Json.Parser.create(s)
        val o2 = p.parseBag[String](p.parseString _)
        assert(p.errorOpt.isEmpty)
        assert(o == o2)
      }
    }

    * - {
      for (is <- (0 until 3).map(i => (z"0" until i).map(_ => String.random))) {
        val o = HashSBag ++ is
        val s = Json.Printer
          .printHashSBag(B.random, o, Json.Printer.printString _)
          .render
        val p = Json.Parser.create(s)
        val o2 = p.parseHashSBag[String](p.parseString _)
        assert(p.errorOpt.isEmpty)
        assert(o == o2)
      }
    }

    * - {
      val o = PosetTest.poset
      val s =
        Json.Printer.printPoset(B.random, o, Json.Printer.printString _).render
      val p = Json.Parser.create(s)
      val o2 = p.parsePoset[String](p.parseString _)
      assert(p.errorOpt.isEmpty)
      assert(o == o2)
    }

    * - {
      val o = {
        val graph = Graph.empty[Z, String]
        val n1 = Z.random
        val n2 = Z.random
        var g = graph + n1 ~> n2
        g = g + n2 ~> n1
        g = g + n1 ~> n2
        g
      }
      val s = Json.Printer
        .printGraph(B.random,
          o,
          Json.Printer.printZ _,
          Json.Printer.printString _)
        .render
      val p = Json.Parser.create(s)
      val o2 = p.parseGraph[Z, String](p.parseZ _, p.parseString _)
      assert(p.errorOpt.isEmpty)
      assert(o == o2)
    }

    * - {
      val o = {
        var uf = UnionFind.create[Z](ISZ(1, 2, 3, 4, 5))
        uf = uf.merge(1, 2)
        uf = uf.merge(3, 4)
        uf = uf.merge(4, 5)
        uf
      }
      val s =
        Json.Printer.printUnionFind(B.random, o, Json.Printer.printZ _).render
      val p = Json.Parser.create(s)
      val o2 = p.parseUnionFind[Z](p.parseZ _)
      assert(p.errorOpt.isEmpty)
      assert(o == o2)
    }

    * - {
      import org.sireum.U32._
      val o = message.FlatPos(Some("Hi.txt"),
        u32"1",
        u32"1",
        u32"1",
        u32"1",
        u32"1",
        u32"1")
      val s = Json.Printer.printPosition(o).render
      val p = Json.Parser.create(s)
      val o2 = p.parsePosition()
      assert(p.errorOpt.isEmpty)
      assert(o == o2)
    }

    * - {
      import org.sireum.U32._
      val o = message.DocInfo(None(), ISZ(u32"0", u32"10", u32"40"))
      val s = Json.Printer.printDocInfo(o).render
      val p = Json.Parser.create(s)
      val o2 = p.parseDocInfo()
      assert(p.errorOpt.isEmpty)
      assert(o == o2)
    }

    * - {
      val o = message.Message(message.Level.Info, None(), "test", "test info")
      val s = Json.Printer.printMessage(o).render
      val p = Json.Parser.create(s)
      val o2 = p.parseMessage()
      assert(p.errorOpt.isEmpty)
      assert(o == o2)
    }
  }

  def parseString(s: Predef.String): Predef.String =
    parse(s, _.parseString().value)

  def parseNumber(s: Predef.String): Predef.String =
    parse(s, _.parseNumber().value)

  def parse[T](s: Predef.String, f: Json.Parser => T): T = {
    val parser = Json.Parser(conversions.String.toCis(String(s)), 0, None())
    val r = f(parser)
    parser.eof()
    assert(parser.errorOpt.isEmpty)
    r

  }
}
