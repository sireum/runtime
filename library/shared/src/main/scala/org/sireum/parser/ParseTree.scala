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

// Use (patched) AntlrWorks at https://github.com/sireum/antlrworks to generate the parser/lexer

package org.sireum.parser

import org.sireum._
import org.sireum.message.Position

@datatype trait ParseTree {
  @pure def ruleName: String
  @pure def toST: ST
  @pure def tipe: Z
  @pure def posOpt: Option[Position]

  override def string: String = {
    return toST.render
  }
}

object ParseTree {

  @datatype class Leaf(val text: String,
                       @hidden val ruleName: String,
                       @hidden val tipe: Z,
                       @hidden val isHidden: B,
                       @hidden val posOpt: Option[Position]) extends ParseTree with Token {
    @strictpure override def toST: ST =
      if (ops.StringOps(ruleName).startsWith("'")) st""""${ops.StringOps(text).escapeST}""""
      else st"""$ruleName["${ops.StringOps(text).escapeST}"]"""
    @strictpure def toLeaf: ParseTree.Leaf = this
    @strictpure def num: Z = tipe
  }

  @datatype class Node(val children: ISZ[ParseTree],
                       @hidden val ruleName: String,
                       @hidden val tipe: Z) extends ParseTree {
    @strictpure override def toST: ST =
      st"""$ruleName(
          |  ${(for (child <- children) yield child.toST, ",\n")}
          |)"""
    @memoize def posOpt: Option[Position] = {
      if (children.isEmpty) {
        return None()
      }
      (children(0).posOpt, children(children.size - 1).posOpt) match {
        case (Some(pos1), Some(pos2)) => return Some(pos1.to(pos2))
        case (Some(pos1), _) => return Some(pos1)
        case (_, Some(pos2)) => return Some(pos2)
        case (_, _) => return None()
      }
    }
  }

  @record class DotGenerator {
    var fresh: Z = -1
    var nodes: ISZ[ST] = ISZ()
    var edges: ISZ[ST] = ISZ()

    def toDot(root: ParseTree): ST = {
      def rec(tree: ParseTree): ST = {
        fresh = fresh + 1
        val name = st"n$fresh"
        tree match {
          case tree: Node =>
            nodes = nodes :+ st"""$name [label = "${tree.ruleName}"]"""
            for (child <- tree.children) {
              val childName = rec(child)
              edges = edges :+ st"""$name -> $childName"""
            }
          case tree: Leaf =>
            nodes = nodes :+ st"""$name [label = ${tree.toST}]"""
        }
        return name
      }

      rec(root)
      val r =
        st"""digraph G {
            |  node [shape = "rect"]
            |
            |  ${(nodes, "\n")}
            |
            |  ${(edges, "\n")}
            |}"""
      return r
    }
  }

  object Node {
    @strictpure def empty: Node = Node(ISZ(), "Tree", -1)
  }

  @sig trait BinaryPrecedenceOps[Builder, T1, T2] {
    @pure def messageKind: String

    @pure def isBinary(t: T2): B

    @pure def isRightAssoc(t: T2): B

    @pure def isHigherPrecedence(n1: Z, n2: Z): B

    @pure def lowestPrecedence: Z

    @pure def shouldParenthesizeOperands(t: T2): B

    @pure def precedence(t: T2): Option[Z]

    @pure def posOpt(t: T2): Option[message.Position]

    @pure def parenthesize(builder: Builder, t: T2): T2

    @pure def binary(builder: Builder, left: T2, op: T2, right: T2): T2

    @pure def transform(builder: Builder, tree: T1): T2

    @pure def toString(t: T2): String
  }


  // T1[exp] ( T1[op] T1[exp] )* => T2[exp]
  // Uses divide-and-conquer: finds the lowest-precedence operator as the root,
  // then recursively builds the left and right subtrees.
  // For same-precedence operators: picks the rightmost for left-associative (so the
  // left subtree is larger), or the leftmost for right-associative (so the right
  // subtree is larger).
  def rewriteBinary[Builder, T1, T2](builder: Builder,
                                     bp: BinaryPrecedenceOps[Builder, T1, T2],
                                     trees: ISZ[T1],
                                     reporter: message.Reporter): T2 = {
    val acs: ISZ[T2] = for (t <- trees) yield bp.transform(builder, t)
    // acs layout: [operand0, op0, operand1, op1, operand2, ...]
    // Operand at acs(i * 2), operator at acs(i * 2 + 1)
    // lo..hi are operand indices (inclusive), with hi - lo operators between them
    def build(lo: Z, hi: Z): T2 = {
      if (lo == hi) {
        return acs(lo * 2)
      }
      // Find the split operator: lowest precedence to be the root
      var splitIdx: Z = lo
      var splitPrec: Z = bp.precedence(acs(lo * 2 + 1)) match {
        case Some(n) => n
        case _ => bp.lowestPrecedence
      }
      for (i <- lo + 1 until hi) {
        val op = acs(i * 2 + 1)
        val p: Z = bp.precedence(op) match {
          case Some(n) => n
          case _ => bp.lowestPrecedence
        }
        val isLower = bp.isHigherPrecedence(splitPrec, p)
        val isEqual = !isLower && !bp.isHigherPrecedence(p, splitPrec)
        if (isLower || (isEqual && !bp.isRightAssoc(op))) {
          splitPrec = p
          splitIdx = i
        }
      }
      val left = build(lo, splitIdx)
      val right = build(splitIdx + 1, hi)
      val op = acs(splitIdx * 2 + 1)
      var l = left
      var r = right
      if (bp.shouldParenthesizeOperands(op)) {
        if (bp.isBinary(l)) {
          l = bp.parenthesize(builder, l)
        }
        if (bp.isBinary(r)) {
          r = bp.parenthesize(builder, r)
        }
      }
      return bp.binary(builder, l, op, r)
    }
    return build(0, (acs.size - 1) / 2)
  }

  // T1[exp] ( T1[op] T1[exp] )* => T2[exp]
  def rewriteBinaryOld[Builder, T1, T2](builder: Builder,
                                        bp: BinaryPrecedenceOps[Builder, T1, T2],
                                        trees: ISZ[T1],
                                        reporter: message.Reporter): T2 = {
    def construct(ts: ISZ[T2], rightAssoc: B, start: Z, stop: Z): T2 = {
      if (rightAssoc) {
        var r = ts(stop)
        for (i <- stop - 2 to start by -2) {
          val op = ts(i + 1)
          var l = ts(i)
          if (bp.shouldParenthesizeOperands(op)) {
            if (bp.isBinary(l)) {
              l = bp.parenthesize(builder, l)
            }
            if (bp.shouldParenthesizeOperands(op)) {
              r = bp.parenthesize(builder, r)
            }
          }
          r = bp.binary(builder, l, op, r)
        }
        return r
      } else {
        var l = ts(start)
        for (i <- start + 2 to stop by 2) {
          val op = ts(i - 1)
          var r = ts(i)
          if (bp.shouldParenthesizeOperands(op)) {
            if (bp.isBinary(l)) {
              l = bp.parenthesize(builder, l)
            }
            if (bp.shouldParenthesizeOperands(op)) {
              r = bp.parenthesize(builder, r)
            }
          }
          l = bp.binary(builder, l, op, r)
        }
        return l
      }
    }

    def maxPrecedence(ts: ISZ[T2]): Z = {
      var max = bp.lowestPrecedence
      for (e <- ts) {
        bp.precedence(e) match {
          case Some(n) if bp.isHigherPrecedence(n, max) => max = n
          case _ =>
        }
      }
      return max
    }

    def reduceHighestPrecedence(acs: ISZ[T2]): ISZ[T2] = {
      val max = maxPrecedence(acs)
      val maxOpt: Option[Z] = Some(max)

      def findMaxPrecedenceIndex(from: Z): Z = {
        for (i <- from until acs.size if bp.precedence(acs(i)) == maxOpt) {
          return i
        }
        return acs.size
      }

      var newAcs = ISZ[T2]()
      var start = findMaxPrecedenceIndex(1)
      for (j <- 0 to start - 2) {
        newAcs = newAcs :+ acs(j)
      }
      while (start < acs.size) {
        val startOp = acs(start)
        var i = start + 2
        var found = false
        while (!found && i < acs.size) {
          val op = acs(i)
          if (bp.precedence(op) == bp.precedence(startOp)) {
            if (bp.isRightAssoc(op) != bp.isRightAssoc(op)) {
              reporter.error(bp.posOpt(op), bp.messageKind,
                s"Could not mix right/left associative operators with the same precedence: ${bp.toString(op)}, ${bp.toString(startOp)}")
            }
            i = i + 2
          } else {
            found = true
          }
        }
        if (i - start >= 2) {
          newAcs = newAcs :+ construct(acs, bp.isRightAssoc(startOp), start - 1, i - 1)
        } else {
          for (j <- start - 1 until i) {
            newAcs = newAcs :+ acs(j)
          }
        }

        start = findMaxPrecedenceIndex(i + 2)
        if (start < acs.size) {
          for (j <- i to start - 2) {
            newAcs = newAcs :+ acs(j)
          }
        } else {
          for (j <- i until acs.size) {
            newAcs = newAcs :+ acs(j)
          }
        }
      }
      return newAcs
    }

    var acs: ISZ[T2] = for (t <- trees) yield bp.transform(builder, t)
    while (acs.size != 1) {
      acs = reduceHighestPrecedence(acs)
    }
    return acs(0)
  }
}
