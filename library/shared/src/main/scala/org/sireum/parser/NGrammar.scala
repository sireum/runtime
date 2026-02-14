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
}

object NGrammar {

  def fromCompact(s: String): NGrammar = {
    val data = ops.ISZOps.lz4Decompress(ops.StringOps.fromBase64(s).left).left
    val r = MessagePack.reader(data)
    r.init()
    val ruleMapSize = r.readZ()
    var ruleMap = HashSMap.empty[U32, NRule]
    var i: Z = 0
    while (i < ruleMapSize) {
      val key = r.readU32()
      val rule = readNRule(r)
      ruleMap = ruleMap + key ~> rule
      i = i + 1
    }
    val pt = PredictiveTable.readPredictiveTable(r)
    return NGrammar(ruleMap = ruleMap, pt = pt)
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

@datatype class NGrammar(val ruleMap: HashSMap[U32, NRule], val pt: PredictiveTable) {
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
    val ruleMapST: ST = if (ruleMap.isEmpty) {
      st"HashSMap.empty[U32, NRule]"
    } else {
      val entries: ISZ[ST] = for (e <- ruleMap.entries) yield
        st"""u32"${conversions.U32.toZ(e._1)}" ~> ${e._2.toST}"""
      st"""HashSMap.empty[U32, NRule] +
          |  ${(entries, " +\n")}"""
    }
    st"""NGrammar(
        |  $ruleMapST,
        |  ${pt.toST})"""
  }

  def toCompact: String = {
    val w = MessagePack.writer(T)
    w.writeZ(ruleMap.size)
    for (e <- ruleMap.entries) {
      w.writeU32(e._1)
      NGrammar.writeNRule(w, e._2)
    }
    PredictiveTable.writePredictiveTable(w, pt)
    return ops.StringOps.toBase64(ops.ISZOps.lz4Compress(w.result))
  }

  def toCompactST: ST = {
    return st"""NGrammar.fromCompact("${toCompact}")"""
  }

  def parse(ruleName: String, tokens: Indexable[Token], reporter: message.Reporter): Option[ParseTree] = {
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
          if (r.text == e.value) {
            return Some((i + 1, ISZ(r.toLeaf)))
          } else {
            reporter.error(r.toLeaf.posOpt, "Parser", s"Expecting '${e.value}', but found '${r.text}'")
            return None()
          }
        case e: NElement.Ref =>
          if (e.isTerminal) {
            if (!tokens.has(i)) {
              reporter.error(None(), "Parser", s"Unexpected end of input, expecting ${pt.reverseNameMap.get(e.num)}")
              return None()
            }
            val r = tokens.at(i)
            if (r.num == e.num) {
              return Some((i + 1, ISZ(r.toLeaf)))
            } else {
              reporter.error(r.toLeaf.posOpt, "Parser", s"Expecting ${pt.reverseNameMap.get(e.num)}, but found ${pt.reverseNameMap.get(r.num)}")
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
        case Some(n) => return parseRule(alts.alts(n), i)
        case _ =>
          // For synthetic choice rules (star/opt), if the last alt is an empty
          // synthetic rule, use it as a default stop/skip when prediction fails.
          // This handles cases where FOLLOW_k is incomplete (e.g., the start rule
          // has no FOLLOW, or the lookahead is shorter than k near end of input).
          if (alts.isSynthetic) {
            val lastAltNum = alts.alts(alts.alts.size - 1)
            ruleMap.get(lastAltNum).get match {
              case lastAlt: NRule.Elements if lastAlt.isSynthetic && lastAlt.elements.isEmpty =>
                return parseElements(lastAlt, i)
              case _ =>
            }
          }
          val expectedTokens: ISZ[String] = pt.rules.get(alts.num) match {
            case Some(n: PredictiveNode.Branch) =>
              val m = pt.reverseNameMap
              for (k <- n.entries.keys) yield m.get(k).get
            case _ => halt(s"Infeasible")
          }
          if (!tokens.has(i)) {
            reporter.error(None(), "Parser", st"Unexpected end of input, expecting ${(expectedTokens, ", ")}".render)
          } else {
            val token = tokens.at(i).toLeaf
            reporter.error(token.posOpt, "Parser", st"Expecting ${(expectedTokens, ", ")}, but found ${token.text}".render)
          }
          return None()
      }
    }

    def parseRule(ruleNum: U32, i: Z): Option[(Z, ISZ[ParseTree])] = {
      ruleMap.get(ruleNum).get match {
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
}
