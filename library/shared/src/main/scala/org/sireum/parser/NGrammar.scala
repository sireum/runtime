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

package org.sireum.parser

import org.sireum._
import org.sireum.U32._

@datatype trait NElement {
  @strictpure def toST: ST
}
object NElement {
  @datatype class Str(val value: String, val num: U32) extends NElement {
    @strictpure override def toST: ST =
      st"""NElement.Str("$value", u32"${conversions.U32.toZ(num)}")"""
  }
  @datatype class Ref(val isTerminal: B, val ruleName: String, val num: U32) extends NElement {
    @strictpure override def toST: ST =
      st"""NElement.Ref($isTerminal, "$ruleName", u32"${conversions.U32.toZ(num)}")"""
  }
}
@datatype trait NRule {
  @pure def name: String
  @pure def num: U32
  @pure def isSynthetic: B
  /** Returns `T` if this rule is the sentinel (unused slot marker). */
  @strictpure def isSentinel: B = num == U32.Max
  @strictpure def toST: ST
}
object NRule {
  @datatype class Elements(val name: String, val num: U32, val isSynthetic: B, val elements: ISZ[NElement]) extends NRule {
    @strictpure override def toST: ST = {
      val es: ISZ[ST] = for (e <- elements) yield e.toST
      st"""NRule.Elements(name = "$name", num = u32"${conversions.U32.toZ(num)}", isSynthetic = $isSynthetic, elements = ISZ(
          |  ${(es, ",\n")}))"""
    }
  }
  @datatype class Alts(val name: String, val num: U32, val isSynthetic: B, val alts: ISZ[U32]) extends NRule {
    @strictpure override def toST: ST = {
      val as: ISZ[ST] = for (a <- alts) yield st"""u32"${conversions.U32.toZ(a)}""""
      st"""NRule.Alts(name = "$name", num = u32"${conversions.U32.toZ(num)}", isSynthetic = $isSynthetic, alts = ISZ(${(as, ", ")}))"""
    }
  }
  val sentinel: NRule = Elements(name = "", num = U32.Max, isSynthetic = F, elements = ISZ())
}

object NGrammar {

  @datatype trait ParseFrame

  object ParseFrame {
    @datatype class ElementsCont(val rule: NRule.Elements, val elemIdx: Z, val treeStart: Z) extends ParseFrame
    @datatype class AltsWrap(val name: String, val num: U32) extends ParseFrame
  }

  def fromCompact(s: String): NGrammar = {
    val data = ops.ISZOps.lz4Decompress(ops.StringOps.fromBase64(s).left).left
    val r = MessagePack.reader(data)
    r.init()
    val entryCount = r.readZ()
    var maxKey: Z = -1
    var entries = ISZ[(U32, NRule)]()
    var i: Z = 0
    while (i < entryCount) {
      val key = r.readU32()
      val rule = readNRule(r)
      entries = entries :+ ((key, rule))
      val keyZ = conversions.U32.toZ(key)
      if (keyZ > maxKey) {
        maxKey = keyZ
      }
      i = i + 1
    }
    var ruleMap = MS.create[U32, NRule](maxKey + 1, NRule.sentinel)
    for (e <- entries) {
      ruleMap(e._1) = e._2
    }
    val pt = PredictiveTable.readPredictiveTable(r)
    return NGrammar(ruleMap = ruleMap.toIS, pt = pt)
  }

  def writeNElement(w: MessagePack.Writer.Impl, e: NElement): Unit = {
    e match {
      case e: NElement.Str =>
        w.writeZ(0)
        w.writeString(e.value)
        w.writeU32(e.num)
      case e: NElement.Ref =>
        w.writeZ(1)
        w.writeB(e.isTerminal)
        w.writeString(e.ruleName)
        w.writeU32(e.num)
    }
  }

  def readNElement(r: MessagePack.Reader.Impl): NElement = {
    val tag = r.readZ()
    tag match {
      case 0 =>
        val value = r.readString()
        val num = r.readU32()
        return NElement.Str(value = value, num = num)
      case 1 =>
        val isTerminal = r.readB()
        val ruleName = r.readString()
        val num = r.readU32()
        return NElement.Ref(isTerminal = isTerminal, ruleName = ruleName, num = num)
      case _ => halt(s"Invalid NElement tag: $tag")
    }
  }

  def writeNRule(w: MessagePack.Writer.Impl, rule: NRule): Unit = {
    rule match {
      case rule: NRule.Elements =>
        w.writeZ(0)
        w.writeString(rule.name)
        w.writeU32(rule.num)
        w.writeB(rule.isSynthetic)
        w.writeZ(rule.elements.size)
        for (e <- rule.elements) {
          writeNElement(w, e)
        }
      case rule: NRule.Alts =>
        w.writeZ(1)
        w.writeString(rule.name)
        w.writeU32(rule.num)
        w.writeB(rule.isSynthetic)
        w.writeZ(rule.alts.size)
        for (a <- rule.alts) {
          w.writeU32(a)
        }
    }
  }

  def readNRule(r: MessagePack.Reader.Impl): NRule = {
    val tag = r.readZ()
    tag match {
      case 0 =>
        val name = r.readString()
        val num = r.readU32()
        val isSynthetic = r.readB()
        val elemSize = r.readZ()
        var elements = ISZ[NElement]()
        var i: Z = 0
        while (i < elemSize) {
          elements = elements :+ readNElement(r)
          i = i + 1
        }
        return NRule.Elements(name = name, num = num, isSynthetic = isSynthetic, elements = elements)
      case 1 =>
        val name = r.readString()
        val num = r.readU32()
        val isSynthetic = r.readB()
        val altsSize = r.readZ()
        var alts = ISZ[U32]()
        var i: Z = 0
        while (i < altsSize) {
          alts = alts :+ r.readU32()
          i = i + 1
        }
        return NRule.Alts(name = name, num = num, isSynthetic = isSynthetic, alts = alts)
      case _ => halt(s"Invalid NRule tag: $tag")
    }
  }
}

@datatype class NGrammar(val ruleMap: IS[U32, NRule], val pt: PredictiveTable) {
  @strictpure def k: Z = pt.k

  /** Generates a Slang expression that programmatically constructs this [[NGrammar]].
    *
    * The rendered ST produces a self-contained Slang expression of type `NGrammar`.
    * The caller's scope must import `org.sireum._`, `org.sireum.U32._`, and
    * `org.sireum.parser._`.
    *
    * @return an ST whose `render` produces the Slang construction expression
    */
  @strictpure def toST: ST = {
    val rulesST: ISZ[ST] = for (i <- u32"0" until conversions.Z.toU32(ruleMap.size)) yield
      if (ruleMap(i).isSentinel) st"NRule.sentinel"
      else ruleMap(i).toST
    st"""NGrammar(
        |  IS[U32, NRule](${(rulesST, ",\n")}),
        |  ${pt.toST})"""
  }

  def toCompact: String = {
    val w = MessagePack.writer(T)
    var count: Z = 0
    var i: Z = 0
    while (i < ruleMap.size) {
      if (!ruleMap(conversions.Z.toU32(i)).isSentinel) {
        count = count + 1
      }
      i = i + 1
    }
    w.writeZ(count)
    i = 0
    while (i < ruleMap.size) {
      val r = ruleMap(conversions.Z.toU32(i))
      if (!r.isSentinel) {
        w.writeU32(conversions.Z.toU32(i))
        NGrammar.writeNRule(w, r)
      }
      i = i + 1
    }
    PredictiveTable.writePredictiveTable(w, pt)
    return ops.StringOps.toBase64(ops.ISZOps.lz4Compress(w.result))
  }

  def toCompactST: ST = {
    return st"""NGrammar.fromCompact("${toCompact}")"""
  }

  def parseRec(ruleName: String, tokens: Indexable[Token], reporter: message.Reporter): Option[ParseTree] = {
    def lookahead(i: Z): ISZ[U32] = {
      var r = ISZ[U32]()
      for (j <- 0 until pt.k if tokens.has(i + j)) {
        val t = tokens.at(i + j)
        r = r :+ t.num
      }
      return r
    }
    def parseElement(e: NElement, i: Z): Option[(Z, ISZ[ParseTree])] = {
      e match {
        case e: NElement.Str =>
          if (!tokens.has(i)) {
            reporter.error(None(), "Parser", s"Unexpected end of input, expecting '${e.value}'")
            return None()
          }
          val r = tokens.at(i)
          if (r.num == e.num) {
            return Some((i + 1, ISZ(r.toLeaf)))
          } else {
            reporter.error(r.toLeaf.posOpt, "Parser", s"Expecting '${e.value}', but found '${r.text}'")
            return None()
          }
        case e: NElement.Ref =>
          if (e.isTerminal) {
            if (!tokens.has(i)) {
              reporter.error(None(), "Parser", s"Unexpected end of input, expecting ${pt.reverseNameMap.get(e.num).get}")
              return None()
            }
            val r = tokens.at(i)
            if (r.num == e.num) {
              return Some((i + 1, ISZ(r.toLeaf)))
            } else {
              reporter.error(r.toLeaf.posOpt, "Parser", s"Expecting ${pt.reverseNameMap.get(e.num).get}, but found ${pt.reverseNameMap.get(r.num).get}")
              return None()
            }
          } else {
            return parseRule(e.num, i)
          }
      }
    }
    def parseElements(elements: NRule.Elements, i: Z): Option[(Z, ISZ[ParseTree])] = {
      var trees = ISZ[ParseTree]()
      var j = i
      for (e <- elements.elements) {
        parseElement(e, j) match {
          case Some((k, ts)) =>
            j = k
            trees = trees ++ ts
          case _ => return None()
        }
      }
      if (elements.isSynthetic) {
        return Some((j, trees))
      }
      return Some((j, ISZ(ParseTree.Node(trees, elements.name, elements.num))))
    }
    def parseAlts(alts: NRule.Alts, i: Z): Option[(Z, ISZ[ParseTree])] = {
      pt.predict(alts.num, lookahead(i)) match {
        case Some(n) =>
          if (alts.isSynthetic) {
            return parseRule(alts.alts(n), i)
          } else {
            parseRule(alts.alts(n), i) match {
              case Some((j, trees)) => return Some((j, ISZ(ParseTree.Node(trees, alts.name, alts.num))))
              case _ => return None()
            }
          }
        case _ =>
          // For synthetic choice rules (star/opt), if the last alt is an empty
          // synthetic rule, use it as a default stop/skip when prediction fails.
          // This handles cases where FOLLOW_k is incomplete (e.g., the start rule
          // has no FOLLOW, or the lookahead is shorter than k near end of input).
          if (alts.isSynthetic) {
            val lastAltNum = alts.alts(alts.alts.size - 1)
            ruleMap(lastAltNum) match {
              case lastAlt: NRule.Elements if lastAlt.isSynthetic && lastAlt.elements.isEmpty =>
                return parseElements(lastAlt, i)
              case _ =>
            }
          }
          pt.rules(alts.num) match {
            case pn: PredictiveNode.Branch =>
              val m = pt.reverseNameMap
              var expectedTokens = ISZ[String]()
              var ii: Z = 0
              while (ii < pn.entries.size) {
                val u = conversions.Z.toU32(ii)
                if (!pn.entries(u).isSentinel) {
                  expectedTokens = expectedTokens :+ m.get(u).get
                }
                ii = ii + 1
              }
              if (!tokens.has(i)) {
                reporter.error(None(), "Parser", st"Unexpected end of input, expecting ${(expectedTokens, ", ")}".render)
              } else {
                val token = tokens.at(i).toLeaf
                reporter.error(token.posOpt, "Parser", st"Expecting ${(expectedTokens, ", ")}, but found ${token.text}".render)
              }
            case _ => halt(s"Infeasible")
          }
          return None()
      }
    }

    def parseRule(ruleNum: U32, i: Z): Option[(Z, ISZ[ParseTree])] = {
      ruleMap(ruleNum) match {
        case r: NRule.Alts => return parseAlts(r, i)
        case r: NRule.Elements => return parseElements(r, i)
      }
    }

    val num = pt.nameMap.get(ruleName).get
    parseRule(num, 0) match {
      case Some((j, ISZ(t))) =>
        if (tokens.has(j)) {
          val token = tokens.at(j).toLeaf
          reporter.error(token.posOpt, "Parser", s"Unexpected token '${token.text}' after successful parse")
          return None()
        }
        return Some(t)
      case _ => return None()
    }
  }

  def parse(ruleName: String, tokens: Indexable[Token], reporter: message.Reporter): Option[ParseTree] = {
    // Optimization 1: Inline predict traversal with flat array lookup.
    // Returns U32 alt index, or U32.Max if no prediction.
    def predictInline(ruleNum: U32, p: Z): U32 = {
      val node = pt.rules(ruleNum)
      if (node.isSentinel) {
        return U32.Max
      }
      var n: PredictiveNode = node
      var i = p
      var result: U32 = U32.Max
      var searching: B = T
      while (searching) {
        n match {
          case leaf: PredictiveNode.Leaf =>
            result = leaf.alt
            searching = F
          case branch: PredictiveNode.Branch =>
            if (!tokens.has(i)) {
              searching = F
            } else {
              val tNum = tokens.at(i).num
              if (conversions.U32.toZ(tNum) >= branch.entries.size) {
                searching = F
              } else {
                val child = branch.entries(tNum)
                if (child.isSentinel) {
                  searching = F
                } else {
                  n = child
                  i = i + 1
                }
              }
            }
        }
      }
      return result
    }

    // Optimization 3: Shared empty constant avoids repeated ISZ() allocation
    val emptyTrees: ISZ[ParseTree] = ISZ()

    // Optimization 2+4: Flat tree buffer — O(1) amortized push instead of ISZ :+ O(n) copy.
    // ElementsCont stores treeStart index instead of accumulated ISZ[ParseTree].
    val emptyLeaf: ParseTree = ParseTree.Leaf(text = "", ruleName = "", tipe = u32"0", isHidden = F, posOpt = None())
    var treeBuf: MStack[ParseTree] = MStack.create(emptyLeaf, 256, 2)

    val defaultFrame: NGrammar.ParseFrame = NGrammar.ParseFrame.AltsWrap(name = "", num = u32"0")
    var stack: MStack[NGrammar.ParseFrame] = MStack.create(defaultFrame, 64, 2)
    var pos: Z = 0
    var enterRuleNum: U32 = pt.nameMap.get(ruleName).get
    var resultTrees: ISZ[ParseTree] = emptyTrees
    var entering: B = T

    // Helper: extract treeBuf[from..treeBuf.size) as ISZ
    def extractTrees(from: Z): ISZ[ParseTree] = {
      val n = treeBuf.size - from
      if (n == 0) {
        return emptyTrees
      }
      val ms = MSZ.create[ParseTree](n, emptyLeaf)
      var i: Z = 0
      while (i < n) {
        ms(i) = treeBuf.elements(from + i)
        i = i + 1
      }
      return ms.toIS
    }

    var done: B = F
    while (!done) {
      if (entering) {
        // Resolve Alts chain until we reach an Elements rule
        var resolving: B = T
        while (resolving) {
          ruleMap(enterRuleNum) match {
            case alts: NRule.Alts =>
              val predResult = predictInline(alts.num, pos)
              if (predResult != U32.Max) {
                if (!alts.isSynthetic) {
                  stack.push(NGrammar.ParseFrame.AltsWrap(name = alts.name, num = alts.num))
                }
                enterRuleNum = alts.alts(conversions.U32.toZ(predResult))
              } else {
                var fallback: B = F
                if (alts.isSynthetic) {
                  val lastAltNum = alts.alts(alts.alts.size - 1)
                  ruleMap(lastAltNum) match {
                    case lastAlt: NRule.Elements if lastAlt.isSynthetic && lastAlt.elements.isEmpty =>
                      resultTrees = emptyTrees
                      entering = F
                      resolving = F
                      fallback = T
                    case _ =>
                  }
                }
                if (!fallback) {
                  pt.rules(alts.num) match {
                    case pn: PredictiveNode.Branch =>
                      val m = pt.reverseNameMap
                      var expectedTokens = ISZ[String]()
                      var ii: Z = 0
                      while (ii < pn.entries.size) {
                        val u = conversions.Z.toU32(ii)
                        if (!pn.entries(u).isSentinel) {
                          expectedTokens = expectedTokens :+ m.get(u).get
                        }
                        ii = ii + 1
                      }
                      if (!tokens.has(pos)) {
                        reporter.error(None(), "Parser", st"Unexpected end of input, expecting ${(expectedTokens, ", ")}".render)
                      } else {
                        val token = tokens.at(pos).toLeaf
                        reporter.error(token.posOpt, "Parser", st"Expecting ${(expectedTokens, ", ")}, but found ${token.text}".render)
                      }
                    case _ => halt("Infeasible")
                  }
                  return None()
                }
              }
            case elements: NRule.Elements =>
              // Push initial continuation frame and transition to returning state;
              // element processing is handled uniformly in the returning/continuation path
              stack.push(NGrammar.ParseFrame.ElementsCont(rule = elements, elemIdx = 0, treeStart = treeBuf.size))
              resultTrees = emptyTrees
              entering = F
              resolving = F
          }
        }
      } else {
        // Returning with resultTrees
        if (stack.isEmpty) {
          done = T
        } else {
          val frame = stack.pop()
          frame match {
            case ec: NGrammar.ParseFrame.ElementsCont =>
              val elements = ec.rule
              // Push resultTrees onto tree buffer (merges with existing trees from treeStart)
              var ri: Z = 0
              while (ri < resultTrees.size) {
                treeBuf.push(resultTrees(ri))
                ri = ri + 1
              }
              var j = pos
              var elemIdx = ec.elemIdx
              var needSubRule: B = F
              while (elemIdx < elements.elements.size && !needSubRule) {
                elements.elements(elemIdx) match {
                  case e: NElement.Str =>
                    if (!tokens.has(j)) {
                      reporter.error(None(), "Parser", s"Unexpected end of input, expecting '${e.value}'")
                      return None()
                    }
                    val token = tokens.at(j)
                    if (token.num == e.num) {
                      treeBuf.push(token.toLeaf)
                      j = j + 1
                      elemIdx = elemIdx + 1
                    } else {
                      reporter.error(token.toLeaf.posOpt, "Parser", s"Expecting '${e.value}', but found '${token.text}'")
                      return None()
                    }
                  case e: NElement.Ref =>
                    if (e.isTerminal) {
                      if (!tokens.has(j)) {
                        reporter.error(None(), "Parser", s"Unexpected end of input, expecting ${pt.reverseNameMap.get(e.num).get}")
                        return None()
                      }
                      val token = tokens.at(j)
                      if (token.num == e.num) {
                        treeBuf.push(token.toLeaf)
                        j = j + 1
                        elemIdx = elemIdx + 1
                      } else {
                        reporter.error(token.toLeaf.posOpt, "Parser", s"Expecting ${pt.reverseNameMap.get(e.num).get}, but found ${pt.reverseNameMap.get(token.num).get}")
                        return None()
                      }
                    } else {
                      // Push continuation for remaining elements, enter sub-rule
                      stack.push(NGrammar.ParseFrame.ElementsCont(
                        rule = elements, elemIdx = elemIdx + 1, treeStart = ec.treeStart))
                      pos = j
                      enterRuleNum = e.num
                      entering = T
                      needSubRule = T
                    }
                }
              }
              if (!needSubRule) {
                pos = j
                if (elements.isSynthetic) {
                  // For synthetic rules (from */+/?), check if the parent frame is an
                  // ElementsCont. If so, leave trees in the buffer — the parent's treeStart
                  // covers them contiguously, avoiding an extract→push round-trip.
                  // If the parent is an AltsWrap (non-synthetic Alts chose this synthetic
                  // Elements), we must extract because AltsWrap reads from resultTrees.
                  var skipExtract: B = F
                  if (stack.nonEmpty) {
                    val parent = stack.peek
                    parent match {
                      case _: NGrammar.ParseFrame.ElementsCont => skipExtract = T
                      case _ =>
                    }
                  }
                  if (skipExtract) {
                    resultTrees = emptyTrees
                  } else {
                    val children = extractTrees(ec.treeStart)
                    treeBuf.sz = ec.treeStart
                    resultTrees = children
                  }
                } else {
                  val children = extractTrees(ec.treeStart)
                  treeBuf.sz = ec.treeStart
                  resultTrees = ISZ(ParseTree.Node(children, elements.name, elements.num))
                }
              }
            case aw: NGrammar.ParseFrame.AltsWrap =>
              resultTrees = ISZ(ParseTree.Node(resultTrees, aw.name, aw.num))
          }
        }
      }
    }

    // Final result after loop exits (stack empty)
    if (resultTrees.size == 1) {
      if (tokens.has(pos)) {
        val token = tokens.at(pos).toLeaf
        reporter.error(token.posOpt, "Parser", s"Unexpected token '${token.text}' after successful parse")
        return None()
      }
      return Some(resultTrees(0))
    }
    return None()
  }
}
