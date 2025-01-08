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

// Use (patched) AntlrWorks at https://github.com/sireum/antlrworks to generate the parser/lexer

package org.sireum.parser

import org.sireum._
import org.sireum.U32._
import org.sireum.message.Position

@datatype trait ParseTree {
  @pure def ruleName: String
  @pure def toST: ST
  @pure def tipe: U32

  override def string: String = {
    return toST.render
  }
}

object ParseTree {

  @datatype class Leaf(val text: String,
                       @hidden val ruleName: String,
                       @hidden val tipe: U32,
                       @hidden val isHidden: B,
                       @hidden val posOpt: Option[Position]) extends ParseTree {
    @pure override def toST: ST = {
      return if (ops.StringOps(ruleName).startsWith("'")) st""""${ops.StringOps(text).escapeST}""""
      else st"""$ruleName["${ops.StringOps(text).escapeST}"]"""
    }
  }

  @datatype class Node(val children: ISZ[ParseTree],
                       @hidden val ruleName: String,
                       @hidden val tipe: U32) extends ParseTree {
    @pure override def toST: ST = {
      return st"""$ruleName(${(for (child <- children) yield child.toST, ", ")})"""
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
    @strictpure def empty: Node = Node(ISZ(), "Tree", u32"-1")
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
  def rewriteBinary[Builder, T1, T2](builder: Builder,
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
