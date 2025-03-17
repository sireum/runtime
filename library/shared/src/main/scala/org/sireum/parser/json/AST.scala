// #Sireum
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
package org.sireum.parser.json

import org.sireum._

@datatype trait AST {
  @pure def pos: message.Position
}


object AST {

  @datatype class Obj(val keyValues: ISZ[KeyValue], val pos: message.Position) extends AST {
    @strictpure def asMap: Map = Map(HashSMap ++ (for (kv <- keyValues) yield (kv.id.value, kv.value)))
  }

  @datatype class Map(val value: HashSMap[String, AST]) {
    @strictpure def getOpt(id: String): Option[AST] = value.get(id)
    @strictpure def get(id: String): AST = getOpt(id).get
    @strictpure def getObj(id: String): Obj = value.get(id).get.asInstanceOf[Obj]
    @strictpure def getArr(id: String): Arr = value.get(id).get.asInstanceOf[Arr]
    @strictpure def getInt(id: String): Int = value.get(id).get.asInstanceOf[Int]
    @strictpure def getDbl(id: String): Dbl = value.get(id).get.asInstanceOf[Dbl]
    @strictpure def getStr(id: String): Str = value.get(id).get.asInstanceOf[Str]
    @strictpure def getBool(id: String): Bool = value.get(id).get.asInstanceOf[Bool]
    @strictpure def getNull(id: String): Null = value.get(id).get.asInstanceOf[Null]
    @strictpure def getStrValueOpt(id: String): Option[String] = value.get(id) match {
      case Some(v: AST.Str) => Some(v.value)
      case _ => None()
    }

  }

  @datatype class KeyValue(val id: Str, val value: AST)

  @datatype class Arr(val values: ISZ[AST], val pos: message.Position) extends AST

  @datatype class Int(val value: Z, val pos: message.Position) extends AST

  @datatype class Dbl(val value: F64, val pos: message.Position) extends AST

  @datatype class Str(val value: org.sireum.String, val pos: message.Position) extends AST

  @datatype class Bool(val value: B, val pos: message.Position) extends AST

  @datatype class Null(val pos: message.Position) extends AST

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
      return AST.Str(text, posOf(leaf))
    }
    tree.ruleName match {
      case string"valueFile" => return build(asNode(tree).children(0))
      case string"value" => return build(asNode(tree).children(0))
      case string"object" =>
        return AST.Obj(for (v <- asNode(tree).children if v.isInstanceOf[parser.ParseTree.Node]) yield
          AST.KeyValue(toStr(asNode(v).children(0)), build(asNode(v).children(2))), posOf(tree))
      case string"array" =>
        return AST.Arr(for (v <- asNode(tree).children if v.isInstanceOf[parser.ParseTree.Node]) yield build(v), posOf(tree))
      case string"stringLit" =>
        return toStr(asNode(tree).children(0))
      case string"doubleLit" =>
        val leaf = asLeaf(asNode(tree).children(0))
        return AST.Dbl(F64(leaf.text).get, posOf(leaf))
      case string"intLit" =>
        val leaf = asLeaf(asNode(tree).children(0))
        return AST.Int(Z(leaf.text).get, posOf(leaf))
      case string"boolLit" =>
        val leaf = asLeaf(asNode(tree).children(0))
        return AST.Bool(leaf.text == "true", posOf(leaf))
      case string"nullLit" =>
        val leaf = asLeaf(asNode(tree).children(0))
        return AST.Null(posOf(leaf))
      case _ => halt(s"Infeasible: $tree")
    }
  }
}