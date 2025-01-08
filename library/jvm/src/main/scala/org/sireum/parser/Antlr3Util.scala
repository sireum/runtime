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

// Use (patched) AntlrWorks at https://github.com/sireum/antlrworks to generate the parser/lexer
package org.sireum.parser

import org.antlr.runtime.tree.TreeAdaptor
import org.antlr.runtime.{RecognitionException, TokenStream}
import org.sireum.helper.halt
import scala.collection.mutable.ListBuffer

object Antlr3Util {

  final case class Node(buffer: ListBuffer[ParseTree], var start: Int, var stop: Int)

  final class Adaptor(tokenNames: Array[String], docInfo: org.sireum.message.DocInfo,
                      var top: ParseTree.Node = ParseTree.Node.empty) extends TreeAdaptor {

    import org.antlr.runtime.{Token => AntlrToken}

    var cache: scala.collection.mutable.HashMap[String, org.sireum.U32] = scala.collection.mutable.HashMap.empty
    def sha3(s: String): org.sireum.U32 = {
      cache.get(s) match {
        case Some(r) => return r
        case _ =>
      }
      import org.sireum._
      val sha3 = crypto.SHA3.init256
      sha3.update(conversions.String.toU8is(s))
      val r = U32(st"0x${(ops.ISZOps(sha3.finalise()).take(4), "")}".render).get
      cache(s) = r
      return r
    }

    override def create(payload: AntlrToken): AnyRef = {
      val t = payload.getType
      val name = if (t == -1) "EOF" else tokenNames(t)
      val offset = docInfo.lineOffsets(org.sireum.Z(payload.getLine - 1)).toZ + org.sireum.Z(payload.getCharPositionInLine)
      val length = payload.getText.length
      val offsetLength = (org.sireum.conversions.Z.toU64(offset) << org.sireum.U64(32)) | org.sireum.U64(length)
      ParseTree.Leaf(payload.getText, name, org.sireum.U32(payload.getType), false,
        org.sireum.Some(org.sireum.message.PosInfo(docInfo, offsetLength)))
    }

    override def dupNode(treeNode: Any): AnyRef = halt("Infeasible")

    override def dupTree(tree: Any): AnyRef = halt("Infeasible")

    override def nil(): AnyRef = Node(ListBuffer(), -1, -1)

    override def errorNode(input: TokenStream, start: AntlrToken, stop: AntlrToken, e: RecognitionException): AnyRef = {
      import org.sireum._
      val name = Thread.currentThread.getStackTrace()(2).getMethodName
      ParseTree.Node(ISZ(), s"$name: ${if (e.getMessage == null) e.getClass.getName else e.getMessage}", U32(-1))
    }

    override def isNil(tree: Any): Boolean = halt("Infeasible")

    override def addChild(t: Any, child: Any): Unit = {
      val node = t.asInstanceOf[Node]
      node.buffer.addOne(child.asInstanceOf[ParseTree])
    }

    override def becomeRoot(newRoot: Any, oldRoot: Any): AnyRef = halt("Infeasible")

    override def rulePostProcessing(root: Any): AnyRef = root match {
      case root: ParseTree => root
      case _ =>
        val node = root.asInstanceOf[Node]
        val name = Thread.currentThread.getStackTrace()(2).getMethodName
        ParseTree.Node(org.sireum.ISZ.apply(node.buffer.toSeq: _*), name, sha3(name))
    }

    override def getUniqueID(node: Any): Int = halt("Infeasible")

    override def becomeRoot(newRoot: AntlrToken, oldRoot: Any): AnyRef = halt("Infeasible")

    override def create(tokenType: Int, fromToken: AntlrToken): AnyRef = halt("Infeasible")

    override def create(tokenType: Int, fromToken: AntlrToken, text: String): AnyRef = halt("Infeasible")

    override def create(tokenType: Int, text: String): AnyRef = halt("Infeasible")

    override def getType(t: Any): Int = halt("Infeasible")

    override def setType(t: Any, `type`: Int): Unit = halt("Infeasible")

    override def getText(t: Any): String = halt("Infeasible")

    override def setText(t: Any, text: String): Unit = halt("Infeasible")

    override def getToken(t: Any): AntlrToken = halt("Infeasible")

    override def setTokenBoundaries(t: Any, startToken: AntlrToken, stopToken: AntlrToken): Unit = {
      t match {
        case node: Node =>
          node.start = startToken.getTokenIndex
          node.stop = stopToken.getTokenIndex
        case _: ParseTree.Leaf =>
          halt("Please generate the lexer/parser using the (patched) AntlrWorks at https://github.com/sireum/antlrworks")
        case _ => halt(s"Infeasible: $t")
      }
    }

    override def getTokenStartIndex(t: Any): Int = halt("Infeasible")

    override def getTokenStopIndex(t: Any): Int = halt("Infeasible")

    override def getChild(t: Any, i: Int): AnyRef = halt("Infeasible")

    override def setChild(t: Any, i: Int, child: Any): Unit = halt("Infeasible")

    override def deleteChild(t: Any, i: Int): AnyRef = halt("Infeasible")

    override def getChildCount(t: Any): Int = halt("Infeasible")

    override def getParent(t: Any): AnyRef = halt("Infeasible")

    override def setParent(t: Any, parent: Any): Unit = halt("Infeasible")

    override def getChildIndex(t: Any): Int = halt("Infeasible")

    override def setChildIndex(t: Any, index: Int): Unit = halt("Infeasible")

    override def replaceChildren(parent: Any, startChildIndex: Int, stopChildIndex: Int, t: Any): Unit = halt("Infeasible")
  }

}
