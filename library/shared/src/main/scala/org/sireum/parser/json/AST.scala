// #Sireum
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
package org.sireum.parser.json

import org.sireum._

@datatype trait AST {
  @pure def posOpt: Option[message.Position]
  @pure def toST: ST
  @pure def toCompactST: ST
}


object AST {

  @datatype class Obj(val keyValues: ISZ[KeyValue], val posOpt: Option[message.Position]) extends AST {
    @strictpure def asMap: Map = Map(HashSMap ++ (for (kv <- keyValues) yield (kv.id.value, kv.value)))
    @strictpure def toST: ST =
      st"""{
          |  ${(for (kv <- keyValues) yield kv.toST, ",\n")}
          |}"""
    @strictpure override def toCompactST: ST = st"{${(for (kv <- keyValues) yield kv.toCompactST, ",")}}"
  }

  @datatype class Map(val value: HashSMap[String, AST]) {
    @strictpure def get(id: String): AST = getOpt(id).get
    @strictpure def getOpt(id: String): Option[AST] = value.get(id)
    @strictpure def getObj(id: String): Obj = value.get(id).get.asInstanceOf[Obj]
    @strictpure def getObjOpt(id: String): Option[Obj] = value.get(id).map((o: AST) => o.asInstanceOf[Obj])
    @strictpure def getArr(id: String): Arr = value.get(id).get.asInstanceOf[Arr]
    @strictpure def getArrOpt(id: String): Option[Arr] = value.get(id).map((o: AST) => o.asInstanceOf[Arr])
    @strictpure def getInt(id: String): Int = value.get(id).get.asInstanceOf[Int]
    @strictpure def getIntOpt(id: String): Option[Int] = value.get(id).map((o: AST) => o.asInstanceOf[Int])
    @strictpure def getIntValueOpt(id: String): Option[Z] = value.get(id).map((o: AST) => o.asInstanceOf[Int].value)
    @strictpure def getDbl(id: String): Dbl = value.get(id).get.asInstanceOf[Dbl]
    @strictpure def getDblOpt(id: String): Option[Dbl] = value.get(id).map((o: AST) => o.asInstanceOf[Dbl])
    @strictpure def getDblValueOpt(id: String): Option[F64] = value.get(id).map((o: AST) => o.asInstanceOf[Dbl].value)
    @strictpure def getStr(id: String): Str = value.get(id).get.asInstanceOf[Str]
    @strictpure def getStrOpt(id: String): Option[Str] = value.get(id).map((o: AST) => o.asInstanceOf[Str])
    @strictpure def getStrValueOpt(id: String): Option[String] = value.get(id).map((o: AST) => o.asInstanceOf[Str].value)
    @strictpure def getBool(id: String): Bool = value.get(id).get.asInstanceOf[Bool]
    @strictpure def getBoolOpt(id: String): Option[Bool] = value.get(id).map((o: AST) => o.asInstanceOf[Bool])
    @strictpure def getBoolValueOpt(id: String): Option[B] = value.get(id).map((o: AST) => o.asInstanceOf[Bool].value)
    @strictpure def getNull(id: String): Null = value.get(id).get.asInstanceOf[Null]
    @strictpure def getNullOpt(id: String): Option[Null] = value.get(id).map((o: AST) => o.asInstanceOf[Null])

  }

  @datatype class KeyValue(val id: Str, val value: AST) {
    @strictpure def toST: ST = st"${id.toST}: ${value.toST}"
    @strictpure def toCompactST: ST = st"${id.toCompactST}:${value.toCompactST}"
  }

  @datatype class Arr(val values: ISZ[AST], val posOpt: Option[message.Position]) extends AST {
    @strictpure def toST: ST =
      if (values.isEmpty) st"[]"
      else if (values(0).isInstanceOf[AST.Obj] || values(0).isInstanceOf[AST.Arr])
        st"""[
            |  ${(for (v <- values) yield v.toST, ",\n")}
            |]"""
      else st"[ ${(for (v <- values) yield v.toST, ", ")} ]"
    @strictpure def toCompactST: ST = st"[${(for (v <- values) yield v.toCompactST,",")}]"
  }

  @datatype class Int(val value: Z, val posOpt: Option[message.Position]) extends AST {
    @strictpure def toST: ST = toCompactST
    @strictpure def toCompactST: ST = Json.Printer.printZ(value)
  }

  @datatype class Dbl(val value: F64, val posOpt: Option[message.Position]) extends AST {
    @strictpure def toST: ST = toCompactST
    @strictpure def toCompactST: ST = Json.Printer.printF64(value)
  }

  @datatype class Str(val value: String, val posOpt: Option[message.Position]) extends AST {
    @strictpure def toST: ST = toCompactST
    @strictpure def toCompactST: ST = Json.Printer.printString(value)
  }

  @datatype class Bool(val value: B, val posOpt: Option[message.Position]) extends AST {
    @strictpure def toST: ST = toCompactST
    @strictpure def toCompactST: ST = Json.Printer.printB(value)
  }

  @datatype class Null(val posOpt: Option[message.Position]) extends AST {
    @strictpure def toST: ST = toCompactST
    @strictpure def toCompactST: ST = st"null"
  }

  def build(tree: parser.ParseTree): AST = {
    @strictpure def asNode(t: parser.ParseTree): parser.ParseTree.Node = t.asInstanceOf[parser.ParseTree.Node]
    @strictpure def asLeaf(t: parser.ParseTree): parser.ParseTree.Leaf = t.asInstanceOf[parser.ParseTree.Leaf]
    @strictpure def posOf(t: parser.ParseTree): message.Position = t match {
      case t: parser.ParseTree.Node => posOf(t.children(0)).to(posOf(t.children(t.children.size - 1)))
      case t: parser.ParseTree.Leaf => t.posOpt.get
    }
    @pure def toStr(t: parser.ParseTree): AST.Str = {
      val leaf = asLeaf(t)
      val text = Json.Parser(conversions.String.toCis(leaf.text), 0, None()).parseString()
      return AST.Str(text, Some(posOf(leaf)))
    }
    tree.ruleName match {
      case string"valueFile" => return build(asNode(tree).children(0))
      case string"value" => return build(asNode(tree).children(0))
      case string"object" =>
        return AST.Obj(for (v <- asNode(tree).children if v.isInstanceOf[parser.ParseTree.Node]) yield
          AST.KeyValue(toStr(asNode(v).children(0)), build(asNode(v).children(2))), Some(posOf(tree)))
      case string"array" =>
        return AST.Arr(for (v <- asNode(tree).children if v.isInstanceOf[parser.ParseTree.Node]) yield build(v), Some(posOf(tree)))
      case string"stringLit" =>
        return toStr(asNode(tree).children(0))
      case string"doubleLit" =>
        val leaf = asLeaf(asNode(tree).children(0))
        return AST.Dbl(F64(leaf.text).get, Some(posOf(leaf)))
      case string"intLit" =>
        val leaf = asLeaf(asNode(tree).children(0))
        return AST.Int(Z(leaf.text).get, Some(posOf(leaf)))
      case string"boolLit" =>
        val leaf = asLeaf(asNode(tree).children(0))
        return AST.Bool(leaf.text == "true", Some(posOf(leaf)))
      case string"nullLit" =>
        val leaf = asLeaf(asNode(tree).children(0))
        return AST.Null(Some(posOf(leaf)))
      case _ => halt(s"Infeasible: $tree")
    }
  }
}