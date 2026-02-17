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
import org.sireum.S32._

@datatype trait NElement {
  @strictpure def toST: ST
}
object NElement {
  @datatype class Str(val value: String, val num: Z) extends NElement {
    @strictpure override def toST: ST =
      st"""NElement.Str("$value", $num)"""
  }
  @datatype class Ref(val isTerminal: B, val ruleName: String, val num: Z) extends NElement {
    @strictpure override def toST: ST =
      st"""NElement.Ref($isTerminal, "$ruleName", $num)"""
  }
}
@datatype trait NRule {
  @pure def name: String
  @pure def num: Z
  @pure def isSynthetic: B
  /** Returns `T` if this rule is the sentinel (unused slot marker). */
  @strictpure def isSentinel: B = num == -1
  @strictpure def toST: ST
}
object NRule {
  @datatype class Elements(val name: String, val num: Z, val isSynthetic: B, val elements: ISZ[NElement]) extends NRule {
    @strictpure override def toST: ST = {
      val es: ISZ[ST] = for (e <- elements) yield e.toST
      st"""NRule.Elements(name = "$name", num = $num, isSynthetic = $isSynthetic, elements = ISZ(
          |  ${(es, ",\n")}))"""
    }
  }
  @datatype class Alts(val name: String, val num: Z, val isSynthetic: B, val alts: ISZ[Z]) extends NRule {
    @strictpure override def toST: ST = {
      val as: ISZ[ST] = for (a <- alts) yield st"""$a"""
      st"""NRule.Alts(name = "$name", num = $num, isSynthetic = $isSynthetic, alts = ISZ(${(as, ", ")}))"""
    }
  }
  val sentinel: NRule = Elements(name = "", num = -1, isSynthetic = F, elements = ISZ())
}

object NGrammar {

  @datatype trait ParseFrame

  object ParseFrame {
    @datatype class ElementsCont(val rule: NRule.Elements, val elemIdx: S32, val treeStart: S32) extends ParseFrame
    @datatype class AltsWrap(val name: String, val num: S32) extends ParseFrame
  }

  def fromCompact(s: String): NGrammar = {
    val data = ops.ISZOps.lz4Decompress(ops.StringOps.fromBase64(s).left).left
    val r = MessagePack.reader(data)
    r.init()
    val entryCount = r.readZ()
    var maxKey: S32 = s32"-1"
    var entries = ISZ[(S32, NRule)]()
    var i: Z = 0
    while (i < entryCount) {
      val key = conversions.U32.toS32(r.readU32())
      val rule = readNRule(r)
      entries = entries :+ ((key, rule))
      if (key > maxKey) {
        maxKey = key
      }
      i = i + 1
    }
    var ruleMap = MS.create[S32, NRule](conversions.S32.toZ(maxKey + s32"1"), NRule.sentinel)
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
        w.writeU32(conversions.Z.toU32(e.num))
      case e: NElement.Ref =>
        w.writeZ(1)
        w.writeB(e.isTerminal)
        w.writeString(e.ruleName)
        w.writeU32(conversions.Z.toU32(e.num))
    }
  }

  def readNElement(r: MessagePack.Reader.Impl): NElement = {
    val tag = r.readZ()
    tag match {
      case 0 =>
        val value = r.readString()
        val num = conversions.U32.toZ(r.readU32())
        return NElement.Str(value = value, num = num)
      case 1 =>
        val isTerminal = r.readB()
        val ruleName = r.readString()
        val num = conversions.U32.toZ(r.readU32())
        return NElement.Ref(isTerminal = isTerminal, ruleName = ruleName, num = num)
      case _ => halt(s"Invalid NElement tag: $tag")
    }
  }

  def writeNRule(w: MessagePack.Writer.Impl, rule: NRule): Unit = {
    rule match {
      case rule: NRule.Elements =>
        w.writeZ(0)
        w.writeString(rule.name)
        w.writeU32(conversions.Z.toU32(rule.num))
        w.writeB(rule.isSynthetic)
        w.writeZ(rule.elements.size)
        for (e <- rule.elements) {
          writeNElement(w, e)
        }
      case rule: NRule.Alts =>
        w.writeZ(1)
        w.writeString(rule.name)
        w.writeU32(conversions.Z.toU32(rule.num))
        w.writeB(rule.isSynthetic)
        w.writeZ(rule.alts.size)
        for (a <- rule.alts) {
          w.writeU32(conversions.Z.toU32(a))
        }
    }
  }

  def readNRule(r: MessagePack.Reader.Impl): NRule = {
    val tag = r.readZ()
    tag match {
      case 0 =>
        val name = r.readString()
        val num = conversions.U32.toZ(r.readU32())
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
        val num = conversions.U32.toZ(r.readU32())
        val isSynthetic = r.readB()
        val altsSize = r.readZ()
        var alts = ISZ[Z]()
        var i: Z = 0
        while (i < altsSize) {
          alts = alts :+ conversions.U32.toZ(r.readU32())
          i = i + 1
        }
        return NRule.Alts(name = name, num = num, isSynthetic = isSynthetic, alts = alts)
      case _ => halt(s"Invalid NRule tag: $tag")
    }
  }
}

@datatype class NGrammar(val ruleMap: IS[S32, NRule], val pt: PredictiveTable) {
  @strictpure def k: Z = pt.k

  /** Generates a Slang expression that programmatically constructs this [[NGrammar]].
    *
    * The rendered ST produces a self-contained Slang expression of type `NGrammar`.
    * The caller's scope must import `org.sireum._` and `org.sireum.parser._`.
    *
    * @return an ST whose `render` produces the Slang construction expression
    */
  @strictpure def toST: ST = {
    val rulesST: ISZ[ST] = for (i <- s32"0" until ruleMap.sizeS32) yield
      if (ruleMap.atS32(i).isSentinel) st"NRule.sentinel"
      else ruleMap.atS32(i).toST
    st"""NGrammar(
        |  ISZ[NRule](${(rulesST, ",\n")}),
        |  ${pt.toST})"""
  }

  def toCompact: String = {
    val w = MessagePack.writer(T)
    val ruleMapSize: S32 = ruleMap.sizeS32
    var count: Z = 0
    var i: S32 = s32"0"
    while (i < ruleMapSize) {
      if (!ruleMap.atS32(i).isSentinel) {
        count = count + 1
      }
      i = i + s32"1"
    }
    w.writeZ(count)
    i = s32"0"
    while (i < ruleMapSize) {
      val r = ruleMap.atS32(i)
      if (!r.isSentinel) {
        w.writeU32(conversions.S32.toU32(i))
        NGrammar.writeNRule(w, r)
      }
      i = i + s32"1"
    }
    PredictiveTable.writePredictiveTable(w, pt)
    return ops.StringOps.toBase64(ops.ISZOps.lz4Compress(w.result))
  }

  def toCompactST: ST = {
    return st"""NGrammar.fromCompact("${toCompact}")"""
  }

  def parseRec(ruleName: String, tokens: Indexable[Token], reporter: message.Reporter): Option[ParseTree] = {
    def lookahead(i: S32): ISZ[Z] = {
      var r = ISZ[Z]()
      var j: S32 = s32"0"
      val kk: S32 = pt.kS32
      while (j < kk && tokens.hasS32(i + j)) {
        val t = tokens.atS32(i + j)
        r = r :+ t.num
        j = j + s32"1"
      }
      return r
    }
    def parseElement(e: NElement, i: S32): Option[(S32, IS[S32, ParseTree])] = {
      e match {
        case e: NElement.Str =>
          if (!tokens.hasS32(i)) {
            reporter.error(None(), "Parser", s"Unexpected end of input, expecting '${e.value}'")
            return None()
          }
          val r = tokens.atS32(i)
          if (r.num == e.num) {
            return Some((i + s32"1", IS[S32, ParseTree](r.toLeaf)))
          } else {
            reporter.error(r.toLeaf.posOpt, "Parser", s"Expecting '${e.value}', but found '${r.text}'")
            return None()
          }
        case e: NElement.Ref =>
          if (e.isTerminal) {
            if (!tokens.hasS32(i)) {
              reporter.error(None(), "Parser", s"Unexpected end of input, expecting ${pt.reverseNameMap.get(e.num).get}")
              return None()
            }
            val r = tokens.atS32(i)
            if (r.num == e.num) {
              return Some((i + s32"1", IS[S32, ParseTree](r.toLeaf)))
            } else {
              reporter.error(r.toLeaf.posOpt, "Parser", s"Expecting ${pt.reverseNameMap.get(e.num).get}, but found ${pt.reverseNameMap.get(r.num).get}")
              return None()
            }
          } else {
            return parseRule(conversions.Z.toS32(e.num), i)
          }
      }
    }
    def parseElements(elements: NRule.Elements, i: S32): Option[(S32, IS[S32, ParseTree])] = {
      var trees = IS[S32, ParseTree]()
      var j: S32 = i
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
      return Some((j, IS[S32, ParseTree](ParseTree.Node(trees, elements.name, elements.num))))
    }
    def parseAlts(alts: NRule.Alts, i: S32): Option[(S32, IS[S32, ParseTree])] = {
      pt.predict(alts.num, lookahead(i)) match {
        case Some(n) =>
          if (alts.isSynthetic) {
            return parseRule(conversions.Z.toS32(alts.alts(n)), i)
          } else {
            parseRule(conversions.Z.toS32(alts.alts(n)), i) match {
              case Some((j, trees)) => return Some((j, IS[S32, ParseTree](ParseTree.Node(trees, alts.name, alts.num))))
              case _ => return None()
            }
          }
        case _ =>
          if (alts.isSynthetic) {
            val lastAltNum = conversions.Z.toS32(alts.alts(alts.alts.size - 1))
            ruleMap.atS32(lastAltNum) match {
              case lastAlt: NRule.Elements if lastAlt.isSynthetic && lastAlt.elements.isEmpty =>
                return parseElements(lastAlt, i)
              case _ =>
            }
          }
          pt.rules.atS32(conversions.Z.toS32(alts.num)) match {
            case pn: PredictiveNode.Branch =>
              val m = pt.reverseNameMap
              var expectedTokens = ISZ[String]()
              var ii: S32 = s32"0"
              val entriesSize: S32 = pn.entries.sizeS32
              while (ii < entriesSize) {
                if (!pn.entries.atS32(ii).isSentinel) {
                  expectedTokens = expectedTokens :+ m.get(conversions.S32.toZ(ii)).get
                }
                ii = ii + s32"1"
              }
              if (!tokens.hasS32(i)) {
                reporter.error(None(), "Parser", st"Unexpected end of input, expecting ${(expectedTokens, ", ")}".render)
              } else {
                val token = tokens.atS32(i).toLeaf
                reporter.error(token.posOpt, "Parser", st"Expecting ${(expectedTokens, ", ")}, but found ${token.text}".render)
              }
            case _ => halt(s"Infeasible")
          }
          return None()
      }
    }

    def parseRule(ruleNum: S32, i: S32): Option[(S32, IS[S32, ParseTree])] = {
      ruleMap.atS32(ruleNum) match {
        case r: NRule.Alts => return parseAlts(r, i)
        case r: NRule.Elements => return parseElements(r, i)
      }
    }

    val num = conversions.Z.toS32(pt.nameMap.get(ruleName).get)
    parseRule(num, s32"0") match {
      case Some((j, trees)) if trees.sizeS32 == s32"1" =>
        if (tokens.hasS32(j)) {
          val token = tokens.atS32(j).toLeaf
          reporter.error(token.posOpt, "Parser", s"Unexpected token '${token.text}' after successful parse")
          return None()
        }
        return Some(trees.atS32(s32"0"))
      case _ => return None()
    }
  }

  def parse(ruleName: String, tokens: Indexable[Token], reporter: message.Reporter): Option[ParseTree] = {
    // Inline predict: returns S32 alt index, or -1 if no prediction.
    // k=1 special case: unrolled single-level trie lookup (no loop, no var reassignment)
    def predictK1(ruleNum: S32, p: S32): S32 = {
      val node = pt.rules.atS32(ruleNum)
      if (node.isSentinel) {
        return s32"-1"
      }
      node match {
        case leaf: PredictiveNode.Leaf => return conversions.Z.toS32(leaf.alt)
        case branch: PredictiveNode.Branch =>
          if (!tokens.hasS32(p)) {
            return s32"-1"
          }
          val tNum = conversions.Z.toS32(tokens.atS32(p).num)
          if (tNum >= branch.entries.sizeS32) {
            return s32"-1"
          }
          val child = branch.entries.atS32(tNum)
          if (child.isSentinel) {
            return s32"-1"
          }
          child match {
            case leaf: PredictiveNode.Leaf => return conversions.Z.toS32(leaf.alt)
            case _ => return s32"-1"
          }
      }
    }

    // k=2 special case: unrolled two-level trie lookup
    def predictK2(ruleNum: S32, p: S32): S32 = {
      val node = pt.rules.atS32(ruleNum)
      if (node.isSentinel) {
        return s32"-1"
      }
      node match {
        case leaf: PredictiveNode.Leaf => return conversions.Z.toS32(leaf.alt)
        case branch0: PredictiveNode.Branch =>
          if (!tokens.hasS32(p)) {
            return s32"-1"
          }
          val tNum0 = conversions.Z.toS32(tokens.atS32(p).num)
          if (tNum0 >= branch0.entries.sizeS32) {
            return s32"-1"
          }
          val child0 = branch0.entries.atS32(tNum0)
          if (child0.isSentinel) {
            return s32"-1"
          }
          child0 match {
            case leaf: PredictiveNode.Leaf => return conversions.Z.toS32(leaf.alt)
            case branch1: PredictiveNode.Branch =>
              if (!tokens.hasS32(p + s32"1")) {
                return s32"-1"
              }
              val tNum1 = conversions.Z.toS32(tokens.atS32(p + s32"1").num)
              if (tNum1 >= branch1.entries.sizeS32) {
                return s32"-1"
              }
              val child1 = branch1.entries.atS32(tNum1)
              if (child1.isSentinel) {
                return s32"-1"
              }
              child1 match {
                case leaf: PredictiveNode.Leaf => return conversions.Z.toS32(leaf.alt)
                case _ => return s32"-1"
              }
          }
      }
    }

    // General case for k>2: loop-based trie traversal
    def predictGeneral(ruleNum: S32, p: S32): S32 = {
      val node = pt.rules.atS32(ruleNum)
      if (node.isSentinel) {
        return s32"-1"
      }
      var n: PredictiveNode = node
      var i: S32 = p
      var result: S32 = s32"-1"
      var searching: B = T
      while (searching) {
        n match {
          case leaf: PredictiveNode.Leaf =>
            result = conversions.Z.toS32(leaf.alt)
            searching = F
          case branch: PredictiveNode.Branch =>
            if (!tokens.hasS32(i)) {
              searching = F
            } else {
              val tNum = conversions.Z.toS32(tokens.atS32(i).num)
              if (tNum >= branch.entries.sizeS32) {
                searching = F
              } else {
                val child = branch.entries.atS32(tNum)
                if (child.isSentinel) {
                  searching = F
                } else {
                  n = child
                  i = i + s32"1"
                }
              }
            }
        }
      }
      return result
    }

    val kkS32: S32 = pt.kS32

    def predict(ruleNum: S32, p: S32): S32 = {
      if (kkS32 == s32"1") {
        return predictK1(ruleNum, p)
      } else if (kkS32 == s32"2") {
        return predictK2(ruleNum, p)
      } else {
        return predictGeneral(ruleNum, p)
      }
    }

    // Shared empty constant avoids repeated IS() allocation
    val emptyTrees: IS[S32, ParseTree] = IS[S32, ParseTree]()

    // Optimization 2+4: Flat tree buffer — O(1) amortized push instead of ISZ :+ O(n) copy.
    // ElementsCont stores treeStart index instead of accumulated ISZ[ParseTree].
    val emptyLeaf: ParseTree = ParseTree.Leaf(text = "", ruleName = "", tipe = 0, isHidden = F, posOpt = None())
    var treeBuf: MStack[ParseTree] = MStack.create(emptyLeaf, s32"256", s32"2")

    val defaultFrame: NGrammar.ParseFrame = NGrammar.ParseFrame.AltsWrap(name = "", num = s32"0")
    var stack: MStack[NGrammar.ParseFrame] = MStack.create(defaultFrame, s32"64", s32"2")
    var pos: S32 = s32"0"
    var enterRuleNum: S32 = conversions.Z.toS32(pt.nameMap.get(ruleName).get)
    var resultTrees: IS[S32, ParseTree] = emptyTrees
    var entering: B = T

    // Helper: extract treeBuf[from..treeBuf.sz) as IS[S32, ParseTree]
    def extractTrees(from: S32): IS[S32, ParseTree] = {
      if (treeBuf.sz <= from) {
        return emptyTrees
      }
      return ops.MSZOpsUtil.slice(treeBuf.elements, from, treeBuf.sz)
    }

    var done: B = F
    while (!done) {
      if (entering) {
        // Resolve Alts chain until we reach an Elements rule
        var resolving: B = T
        while (resolving) {
          ruleMap.atS32(enterRuleNum) match {
            case alts: NRule.Alts =>
              val predResult = predict(conversions.Z.toS32(alts.num), pos)
              if (predResult >= s32"0") {
                if (!alts.isSynthetic) {
                  stack.push(NGrammar.ParseFrame.AltsWrap(name = alts.name, num = conversions.Z.toS32(alts.num)))
                }
                enterRuleNum = conversions.Z.toS32(alts.alts(conversions.S32.toZ(predResult)))
              } else {
                var fallback: B = F
                if (alts.isSynthetic) {
                  val lastAltNum = conversions.Z.toS32(alts.alts(alts.alts.size - 1))
                  ruleMap.atS32(lastAltNum) match {
                    case lastAlt: NRule.Elements if lastAlt.isSynthetic && lastAlt.elements.isEmpty =>
                      resultTrees = emptyTrees
                      entering = F
                      resolving = F
                      fallback = T
                    case _ =>
                  }
                }
                if (!fallback) {
                  pt.rules.atS32(conversions.Z.toS32(alts.num)) match {
                    case pn: PredictiveNode.Branch =>
                      val m = pt.reverseNameMap
                      var expectedTokens = ISZ[String]()
                      var ii: S32 = s32"0"
                      val entriesSize: S32 = pn.entries.sizeS32
                      while (ii < entriesSize) {
                        if (!pn.entries.atS32(ii).isSentinel) {
                          expectedTokens = expectedTokens :+ m.get(conversions.S32.toZ(ii)).get
                        }
                        ii = ii + s32"1"
                      }
                      if (!tokens.hasS32(pos)) {
                        reporter.error(None(), "Parser", st"Unexpected end of input, expecting ${(expectedTokens, ", ")}".render)
                      } else {
                        val token = tokens.atS32(pos).toLeaf
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
              stack.push(NGrammar.ParseFrame.ElementsCont(rule = elements, elemIdx = s32"0", treeStart = treeBuf.sizeS32))
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
              var ri: S32 = s32"0"
              val riSize: S32 = resultTrees.sizeS32
              while (ri < riSize) {
                treeBuf.push(resultTrees.atS32(ri))
                ri = ri + s32"1"
              }
              var j: S32 = pos
              var elemIdx: S32 = ec.elemIdx
              val elemSize: S32 = elements.elements.sizeS32
              var needSubRule: B = F
              while (elemIdx < elemSize && !needSubRule) {
                elements.elements(conversions.S32.toZ(elemIdx)) match {
                  case e: NElement.Str =>
                    if (!tokens.hasS32(j)) {
                      reporter.error(None(), "Parser", s"Unexpected end of input, expecting '${e.value}'")
                      return None()
                    }
                    val token = tokens.atS32(j)
                    if (token.num == e.num) {
                      treeBuf.push(token.toLeaf)
                      j = j + s32"1"
                      elemIdx = elemIdx + s32"1"
                    } else {
                      reporter.error(token.toLeaf.posOpt, "Parser", s"Expecting '${e.value}', but found '${token.text}'")
                      return None()
                    }
                  case e: NElement.Ref =>
                    if (e.isTerminal) {
                      if (!tokens.hasS32(j)) {
                        reporter.error(None(), "Parser", s"Unexpected end of input, expecting ${pt.reverseNameMap.get(e.num).get}")
                        return None()
                      }
                      val token = tokens.atS32(j)
                      if (token.num == e.num) {
                        treeBuf.push(token.toLeaf)
                        j = j + s32"1"
                        elemIdx = elemIdx + s32"1"
                      } else {
                        reporter.error(token.toLeaf.posOpt, "Parser", s"Expecting ${pt.reverseNameMap.get(e.num).get}, but found ${pt.reverseNameMap.get(token.num).get}")
                        return None()
                      }
                    } else {
                      // Push continuation for remaining elements, enter sub-rule
                      stack.push(NGrammar.ParseFrame.ElementsCont(
                        rule = elements, elemIdx = elemIdx + s32"1", treeStart = ec.treeStart))
                      pos = j
                      enterRuleNum = conversions.Z.toS32(e.num)
                      entering = T
                      needSubRule = T
                    }
                }
              }
              if (!needSubRule) {
                pos = j
                if (elements.isSynthetic) {
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
                  resultTrees = IS[S32, ParseTree](ParseTree.Node(children, elements.name, elements.num))
                }
              }
            case aw: NGrammar.ParseFrame.AltsWrap =>
              resultTrees = IS[S32, ParseTree](ParseTree.Node(resultTrees, aw.name, conversions.S32.toZ(aw.num)))
          }
        }
      }
    }

    // Final result after loop exits (stack empty)
    if (resultTrees.sizeS32 == s32"1") {
      if (tokens.hasS32(pos)) {
        val token = tokens.atS32(pos).toLeaf
        reporter.error(token.posOpt, "Parser", s"Unexpected token '${token.text}' after successful parse")
        return None()
      }
      return Some(resultTrees.atS32(s32"0"))
    }
    return None()
  }
}
