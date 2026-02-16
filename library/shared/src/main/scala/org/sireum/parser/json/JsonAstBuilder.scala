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
import org.sireum.parser.{ParseTree => Tree}

object JsonAstBuilder {
  @ext("JsonAstBuilder_Ext") object Ext {
    @pure def unescapeJsonString(raw: String): String = $
  }
  val rnValueFile: String = "valueFile"
  val rnObject: String = "object"
  val rnArray: String = "array"
  val rnStringLit: String = "stringLit"
  val rnDoubleLit: String = "doubleLit"
  val rnIntLit: String = "intLit"
  val rnNullLit: String = "nullLit"
  val rnTrue: String = "'true'"
  val rnFalse: String = "'false'"
  val rnBoolLit: String = "boolLit"
  val rnValue: String = "value"
  val rnKeyValue: String = "keyValue"
}

@datatype class JsonAstBuilder(tree: Tree) {

  @strictpure def asNode(t: Tree): Tree.Node = t.asInstanceOf[Tree.Node]

  @strictpure def asLeaf(t: Tree): Tree.Leaf = t.asInstanceOf[Tree.Leaf]

  @strictpure def posOf(t: Tree): message.Position = t match {
    case t: Tree.Node => posOf(t.children(0)).to(posOf(t.children(t.children.size - 1)))
    case t: Tree.Leaf => t.posOpt.get
  }

  def toStr(t: Tree): AST.Str = {
    val leaf = asLeaf(t)
    val text = JsonAstBuilder.Ext.unescapeJsonString(leaf.text)
    return AST.Str(text, Some(posOf(leaf)))
  }

  def build(): AST = {
    return buildValue(tree)
  }

  def buildValue(tree: Tree): AST = {
    val rn = tree.ruleName
    if (rn == JsonAstBuilder.rnValueFile) {
      return buildValue(asNode(tree).children(0))
    } else if (rn == JsonAstBuilder.rnObject) {
      return buildObject(tree)
    } else if (rn == JsonAstBuilder.rnArray) {
      return buildArray(tree)
    } else if (rn == JsonAstBuilder.rnStringLit) {
      return toStr(asNode(tree).children(0))
    } else if (rn == JsonAstBuilder.rnDoubleLit) {
      val leaf = asLeaf(asNode(tree).children(0))
      return AST.Dbl(F64(leaf.text).get, Some(posOf(leaf)))
    } else if (rn == JsonAstBuilder.rnIntLit) {
      val leaf = asLeaf(asNode(tree).children(0))
      return AST.Int(Z(leaf.text).get, Some(posOf(leaf)))
    } else if (rn == JsonAstBuilder.rnNullLit) {
      val leaf = asLeaf(asNode(tree).children(0))
      return AST.Null(Some(posOf(leaf)))
    } else if (rn == JsonAstBuilder.rnTrue) {
      return AST.Bool(T, Some(posOf(tree)))
    } else if (rn == JsonAstBuilder.rnFalse) {
      return AST.Bool(F, Some(posOf(tree)))
    } else if (rn == JsonAstBuilder.rnBoolLit) {
      return buildValue(asNode(tree).children(0))
    } else if (rn == JsonAstBuilder.rnValue) {
      return buildValue(asNode(tree).children(0))
    } else {
      halt(s"Infeasible: $tree")
    }
  }

  def buildObject(tree: Tree): AST.Obj = {
    val children = asNode(tree).children
    // Count keyValue children, then fill pre-sized MSZ — avoids O(n²) ISZ :+ cloning
    var count: Z = 0
    var ci: Z = 1
    while (ci < children.size - 1) {
      if (children(ci).ruleName == JsonAstBuilder.rnKeyValue) {
        count = count + 1
      }
      ci = ci + 1
    }
    val defaultKV = AST.KeyValue(AST.Str("", None()), AST.Null(None()))
    val keyValuesMs = MSZ.create[AST.KeyValue](count, defaultKV)
    var ki: Z = 0
    ci = 1
    while (ci < children.size - 1) {
      val child = children(ci)
      if (child.ruleName == JsonAstBuilder.rnKeyValue) {
        val kv = asNode(child)
        keyValuesMs(ki) = AST.KeyValue(toStr(kv.children(0)), buildValue(kv.children(2)))
        ki = ki + 1
      }
      ci = ci + 1
    }
    return AST.Obj(keyValuesMs.toIS, Some(posOf(tree)))
  }

  def buildArray(tree: Tree): AST.Arr = {
    val children = asNode(tree).children
    // Count value children, then fill pre-sized MSZ — avoids O(n²) ISZ :+ cloning
    var count: Z = 0
    var ci: Z = 1
    while (ci < children.size - 1) {
      if (children(ci).isInstanceOf[Tree.Node]) {
        count = count + 1
      }
      ci = ci + 1
    }
    val valuesMs = MSZ.create[AST](count, AST.Null(None()))
    var vi: Z = 0
    ci = 1
    while (ci < children.size - 1) {
      val child = children(ci)
      if (child.isInstanceOf[Tree.Node]) {
        valuesMs(vi) = buildValue(child)
        vi = vi + 1
      }
      ci = ci + 1
    }
    return AST.Arr(valuesMs.toIS, Some(posOf(tree)))
  }
}
