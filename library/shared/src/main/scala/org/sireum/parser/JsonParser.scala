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

// This file is auto-generated from JSON.g

package org.sireum.parser


import org.sireum._
import org.sireum.U32._
import org.sireum.U64._
import org.sireum.conversions.U32.toC

object Json {
  @range(min = 0, max = 9) class State
}

import Json.State
import Json.State._

object JsonParser {

  @datatype class Result(val kind: Result.Kind.Type, val tree: ParseTree, val newIndex: Z) {
    def leaf: ParseTree.Leaf = {
      return tree.asInstanceOf[ParseTree.Leaf]
    }
  }

  object Result {

    @enum object Kind {
      'Normal
      'LexicalError
      'GrammaticalError
    }

    @strictpure def create(tree: ParseTree, newIndex: Z): Result =
      Result(Result.Kind.Normal, tree, newIndex)

    @strictpure def error(isLexical: B, index: Z): Result =
      Result(if (isLexical) Result.Kind.LexicalError else Result.Kind.GrammaticalError, errorLeaf, index)

  }

  @record class Context(val ruleName: String,
                        val ruleType: U32,
                        val accepting: IS[State, B],
                        var state: State,
                        var resOpt: Option[Result],
                        var j: Z,
                        var max: Z,
                        var initial: B,
                        var trees: ISZ[ParseTree],
                        var found: B,
                        var failIndex: Z,
                        var isLexical: B) {

    def updateTerminal(token: ParseTree.Leaf, newState: State): Unit = {
      found = T
      j = j + 1
      initial = F
      state = newState
      trees = trees :+ token
      if (accepting(state)) {
        resOpt = Some(Result.create(ParseTree.Node(trees, ruleName, ruleType), j))
      }
    }

    def updateNonTerminal(r: Result, newState: State): Unit = {
      found = T
      initial = F
      j = r.newIndex
      state = newState
      trees = trees :+ r.tree
      if (accepting(state)) {
        resOpt = Some(Result.create(ParseTree.Node(trees, ruleName, ruleType), j))
      }
    }
  }

  object Context {
    @pure def create(ruleName: String, ruleType: U32, accepts: ISZ[State], i: Z): Context = {
      val accepting = MS.create[State, B](10, F)
      for (accept <- accepts) {
        accepting(accept) = T
      }
      return Context(
        ruleName = ruleName,
        ruleType = ruleType,
        accepting = accepting.toIS,
        state = state"0",
        resOpt = None(),
        trees = ISZ[ParseTree](),
        j = i,
        max = i,
        initial = T,
        found = F,
        failIndex = 0,
        isLexical = F
      )
    }
  }

  @record class LContext(val accepting: IS[State, B], var state: State, var j: Z, var afterAcceptIndex: Z, var found: B) {
    def update(newState: State): Unit = {
      state = newState
      found = T
      if (accepting(state)) {
        afterAcceptIndex = j + 1
      }
    }
  }

  object LContext {
    @pure def create(accepts: ISZ[State], i: Z): LContext = {
      val accepting = MS.create[State, B](10, F)
      for (accept <- accepts) {
        accepting(accept) = T
      }
      return LContext(accepting = accepting.toIS, state = state"0", j = i, afterAcceptIndex = -1, found = F)
    }
  }

  @datatype class IndexableToken(val input: Indexable.Pos[C], val skipHidden: B) extends Indexable[Result] {
    val lexer: JsonLexer = JsonLexer(input)

    override def at(i: Z): Result = {
      return _at(i)
    }

    override def has(i: Z): B = {
      return _has(i)
    }

    @memoize def _has(i: Z): B = {
      assert(i >= 0)
      if (i == 0) {
        return T
      }
      if (!_has(i - 1)) {
        return F
      }
      val prev = _at(i - 1)
      return prev.kind == Result.Kind.Normal && prev.newIndex != -1
    }

    @memoize def _at(i: Z): Result = {
      if (i == 0) {
        if (input.has(0)) {
          lexer.tokenize(0, skipHidden) match {
            case Some(result) => return result
            case _ =>
          }
        }
      } else {
        val prev = _at(i - 1)
        if (input.has(prev.newIndex)) {
          lexer.tokenize(prev.newIndex, skipHidden) match {
            case Some(result) => return result
            case _ =>
          }
        }
      }
      return Result(Result.Kind.Normal, eofLeaf, -1)
    }

  }

  val kind: String = "JsonParser"

  val minChar: C = '\u0000'
  val maxChar: C = toC(u32"0x0010FFFF")

  val T_AFEF039D: U32 = u32"0xAFEF039D" /* "true" */
  val T_D8AFD1B9: U32 = u32"0xD8AFD1B9" /* "false" */
  val T_3EA44541: U32 = u32"0x3EA44541" /* "null" */
  val T_FDCE65E5: U32 = u32"0xFDCE65E5" /* "{" */
  val T_763C38BE: U32 = u32"0x763C38BE" /* ":" */
  val T_45445E21: U32 = u32"0x45445E21" /* "," */
  val T_5BF60471: U32 = u32"0x5BF60471" /* "}" */
  val T_A44269E9: U32 = u32"0xA44269E9" /* "[" */
  val T_9977908D: U32 = u32"0x9977908D" /* "]" */
  val T_STRING: U32 = u32"0xA7CF0FE0"
  val T_NUMBER: U32 = u32"0x28C20CF1"
  val T_WS: U32 = u32"0x0E3F5D1E"
  val T_valueFile: U32 = u32"0x94F3E412"
  val T_value: U32 = u32"0x82EEA07A"
  val T_object: U32 = u32"0x5ED5358F"
  val T_array: U32 = u32"0xB11A9723"

  val errorLeaf: ParseTree.Leaf = ParseTree.Leaf("<ERROR>", "<ERROR>", u32"0xE3CDEDDA", F, None())
  val eofLeaf: ParseTree.Leaf = ParseTree.Leaf("<EOF>", "EOF", u32"0xFC5CB374", F, None())

  def parse(uriOpt: Option[String], input: String, reporter: message.Reporter): Option[ParseTree] = {
    val docInfo = message.DocInfo.create(uriOpt, input)
    val tokens = lex(input, docInfo, T, T, reporter)
    if (reporter.hasError) {
      return None()
    }
    val r = JsonParser(Indexable.fromIsz(tokens)).parseValueFile(0)
    r.kind match {
      case Result.Kind.Normal => return Some(r.tree)
      case Result.Kind.LexicalError =>
        reporter.error(Some(message.PosInfo(docInfo, offsetLength(r.newIndex, 1))), kind, s"Could not recognize token")
        return None()
      case Result.Kind.GrammaticalError =>
        val idx: Z = if (r.newIndex < 0) -r.newIndex else r.newIndex
        if (idx < tokens.size) {
          val token = tokens(idx).leaf
          reporter.error(token.posOpt, kind, s"Could not parse token: \"${ops.StringOps(token.text).escapeST.render}\"")
        } else {
          val token = tokens(idx - 1).leaf
          reporter.error(token.posOpt, kind, "Expecting more input but reached the end")
        }
        return None()
    }
  }

  def parseIndexable(input: Indexable.Pos[C], reporter: message.Reporter): Option[ParseTree] = {
    val it = IndexableToken(input, T)
    val r = JsonParser(it).parseValueFile(0)
    r.kind match {
      case Result.Kind.Normal => return Some(r.tree)
      case Result.Kind.LexicalError =>
        reporter.error(input.posOpt(r.newIndex, 1), kind, s"Could not recognize token")
        return None()
      case Result.Kind.GrammaticalError =>
        val idx: Z = if (r.newIndex < 0) -r.newIndex else r.newIndex
        if (it.has(idx)) {
          val token = it.at(idx).leaf
          reporter.error(token.posOpt, kind, s"Could not parse token: \"${ops.StringOps(token.text).escapeST.render}\"")
        } else {
          val token = it.at(idx - 1).leaf
          reporter.error(token.posOpt, kind, "Expecting more input but reached the end")
        }
        return None()
    }
  }

  def lex(input: String, docInfo: message.DocInfo, skipHidden: B, stopAtError: B,
          reporter: message.Reporter): ISZ[Result] = {
    return JsonLexer(Indexable.fromIszDocInfo(conversions.String.toCis(input), docInfo)).tokenizeAll(skipHidden, stopAtError, reporter)
  }

  @pure def offsetLength(offset: Z, length: Z): U64 = {
    return (conversions.Z.toU64(offset) << u64"32") | (conversions.Z.toU64(length) & u64"0xFFFFFFFF")
  }

}

import JsonParser._

@datatype class JsonParser(tokens: Indexable[Result]) {

  @pure def parseValueFile(i: Z): Result = {
    val ctx = Context.create("valueFile", u32"0x94F3E412", ISZ(state"2"), i)

    while (tokens.has(ctx.j)) {
      val token: ParseTree.Leaf = {
        val result = tokens.at(ctx.j)
        if (result.kind != Result.Kind.Normal) {
          return result
        }
        result.leaf
      }
      ctx.state match {
        case state"0" =>
          ctx.found = F
          val n_value = predictValue(ctx.j)
          if (n_value > 0 && parseValueH(ctx, state"1")) {
            return Result.error(ctx.isLexical, ctx.failIndex)
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"1" =>
          ctx.found = F
          token.tipe match {
            case u32"0xFC5CB374" /* EOF */ => ctx.updateTerminal(token, state"2")
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"2" => return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
        case _ => halt("Infeasible")
      }
      if (ctx.max < ctx.j) {
        ctx.max = ctx.j
      }
    }

    return retVal(ctx.j, ctx.resOpt, ctx.initial, T)
  }

  @pure def parseValue(i: Z): Result = {
    val ctx = Context.create("value", u32"0x82EEA07A", ISZ(state"1"), i)

    while (tokens.has(ctx.j)) {
      val token: ParseTree.Leaf = {
        val result = tokens.at(ctx.j)
        if (result.kind != Result.Kind.Normal) {
          return result
        }
        result.leaf
      }
      ctx.state match {
        case state"0" =>
          ctx.found = F
          val n_object = predictObject(ctx.j)
          val n_array = predictArray(ctx.j)
          for (n <- 1 to 1 by -1 if !ctx.found) {
            if (n_object == n && parseObjectH(ctx, state"1")) {
              return Result.error(ctx.isLexical, ctx.failIndex)
            } else if (n_array == n && parseArrayH(ctx, state"1")) {
              return Result.error(ctx.isLexical, ctx.failIndex)
            }
          }
          if (!ctx.found) {
            token.tipe match {
              case u32"0xA7CF0FE0" /* STRING */ => ctx.updateTerminal(token, state"1")
              case u32"0x28C20CF1" /* NUMBER */ => ctx.updateTerminal(token, state"1")
              case u32"0xAFEF039D" /* "true" */ => ctx.updateTerminal(token, state"1")
              case u32"0xD8AFD1B9" /* "false" */ => ctx.updateTerminal(token, state"1")
              case u32"0x3EA44541" /* "null" */ => ctx.updateTerminal(token, state"1")
              case _ =>
            }
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"1" => return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
        case _ => halt("Infeasible")
      }
      if (ctx.max < ctx.j) {
        ctx.max = ctx.j
      }
    }

    return retVal(ctx.j, ctx.resOpt, ctx.initial, T)
  }

  @pure def parseObject(i: Z): Result = {
    val ctx = Context.create("object", u32"0x5ED5358F", ISZ(state"8"), i)

    while (tokens.has(ctx.j)) {
      val token: ParseTree.Leaf = {
        val result = tokens.at(ctx.j)
        if (result.kind != Result.Kind.Normal) {
          return result
        }
        result.leaf
      }
      ctx.state match {
        case state"0" =>
          ctx.found = F
          token.tipe match {
            case u32"0xFDCE65E5" /* "{" */ => ctx.updateTerminal(token, state"1")
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"1" =>
          ctx.found = F
          token.tipe match {
            case u32"0xA7CF0FE0" /* STRING */ => ctx.updateTerminal(token, state"2")
            case u32"0x5BF60471" /* "}" */ => ctx.updateTerminal(token, state"8")
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"2" =>
          ctx.found = F
          token.tipe match {
            case u32"0x763C38BE" /* ":" */ => ctx.updateTerminal(token, state"3")
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"3" =>
          ctx.found = F
          val n_value = predictValue(ctx.j)
          if (n_value > 0 && parseValueH(ctx, state"4")) {
            return Result.error(ctx.isLexical, ctx.failIndex)
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"4" =>
          ctx.found = F
          token.tipe match {
            case u32"0x45445E21" /* "," */ => ctx.updateTerminal(token, state"5")
            case u32"0x5BF60471" /* "}" */ => ctx.updateTerminal(token, state"8")
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"5" =>
          ctx.found = F
          token.tipe match {
            case u32"0xA7CF0FE0" /* STRING */ => ctx.updateTerminal(token, state"6")
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"6" =>
          ctx.found = F
          token.tipe match {
            case u32"0x763C38BE" /* ":" */ => ctx.updateTerminal(token, state"7")
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"7" =>
          ctx.found = F
          val n_value = predictValue(ctx.j)
          if (n_value > 0 && parseValueH(ctx, state"4")) {
            return Result.error(ctx.isLexical, ctx.failIndex)
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"8" => return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
        case _ => halt("Infeasible")
      }
      if (ctx.max < ctx.j) {
        ctx.max = ctx.j
      }
    }

    return retVal(ctx.j, ctx.resOpt, ctx.initial, T)
  }

  @pure def parseArray(i: Z): Result = {
    val ctx = Context.create("array", u32"0xB11A9723", ISZ(state"4"), i)

    while (tokens.has(ctx.j)) {
      val token: ParseTree.Leaf = {
        val result = tokens.at(ctx.j)
        if (result.kind != Result.Kind.Normal) {
          return result
        }
        result.leaf
      }
      ctx.state match {
        case state"0" =>
          ctx.found = F
          token.tipe match {
            case u32"0xA44269E9" /* "[" */ => ctx.updateTerminal(token, state"1")
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"1" =>
          ctx.found = F
          val n_value = predictValue(ctx.j)
          if (n_value > 0 && parseValueH(ctx, state"2")) {
            return Result.error(ctx.isLexical, ctx.failIndex)
          }
          if (!ctx.found) {
            token.tipe match {
              case u32"0x9977908D" /* "]" */ => ctx.updateTerminal(token, state"4")
              case _ =>
            }
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"2" =>
          ctx.found = F
          token.tipe match {
            case u32"0x45445E21" /* "," */ => ctx.updateTerminal(token, state"3")
            case u32"0x9977908D" /* "]" */ => ctx.updateTerminal(token, state"4")
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"3" =>
          ctx.found = F
          val n_value = predictValue(ctx.j)
          if (n_value > 0 && parseValueH(ctx, state"2")) {
            return Result.error(ctx.isLexical, ctx.failIndex)
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"4" => return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
        case _ => halt("Infeasible")
      }
      if (ctx.max < ctx.j) {
        ctx.max = ctx.j
      }
    }

    return retVal(ctx.j, ctx.resOpt, ctx.initial, T)
  }

  def parseValueH(ctx: Context, nextState: State): B = {
    val r = parseValue(ctx.j)
    r.kind match {
      case Result.Kind.Normal => ctx.updateNonTerminal(r, nextState)
      case Result.Kind.LexicalError =>
        ctx.failIndex = r.newIndex
        ctx.isLexical = T
        return T
      case Result.Kind.GrammaticalError =>
        val index = r.newIndex
        if (index < 0) {
          ctx.failIndex = index
          return T
        } else if (ctx.max < index) {
          ctx.max = index
        }
    }
    return F
  }

  def parseObjectH(ctx: Context, nextState: State): B = {
    val r = parseObject(ctx.j)
    r.kind match {
      case Result.Kind.Normal => ctx.updateNonTerminal(r, nextState)
      case Result.Kind.LexicalError =>
        ctx.failIndex = r.newIndex
        ctx.isLexical = T
        return T
      case Result.Kind.GrammaticalError =>
        val index = r.newIndex
        if (index < 0) {
          ctx.failIndex = index
          return T
        } else if (ctx.max < index) {
          ctx.max = index
        }
    }
    return F
  }

  def parseArrayH(ctx: Context, nextState: State): B = {
    val r = parseArray(ctx.j)
    r.kind match {
      case Result.Kind.Normal => ctx.updateNonTerminal(r, nextState)
      case Result.Kind.LexicalError =>
        ctx.failIndex = r.newIndex
        ctx.isLexical = T
        return T
      case Result.Kind.GrammaticalError =>
        val index = r.newIndex
        if (index < 0) {
          ctx.failIndex = index
          return T
        } else if (ctx.max < index) {
          ctx.max = index
        }
    }
    return F
  }

  @pure def predictValueFile(j: Z): Z = {
    val tokenJ = tokens.at(j)
    if (tokenJ.kind == Result.Kind.Normal) {
      tokenJ.leaf.tipe match {
        case u32"0xA7CF0FE0" /* STRING */ => return 1
        case u32"0x28C20CF1" /* NUMBER */ => return 1
        case u32"0xFDCE65E5" /* "{" */ => return 1
        case u32"0xA44269E9" /* "[" */ => return 1
        case u32"0xAFEF039D" /* "true" */ => return 1
        case u32"0xD8AFD1B9" /* "false" */ => return 1
        case u32"0x3EA44541" /* "null" */ => return 1
        case _ =>
      }
    }
    return 0
  }

  @pure def predictArray(j: Z): Z = {
    val tokenJ = tokens.at(j)
    if (tokenJ.kind == Result.Kind.Normal) {
      tokenJ.leaf.tipe match {
        case u32"0xA44269E9" /* "[" */ => return 1
        case _ =>
      }
    }
    return 0
  }

  @pure def predictValue(j: Z): Z = {
    val tokenJ = tokens.at(j)
    if (tokenJ.kind == Result.Kind.Normal) {
      tokenJ.leaf.tipe match {
        case u32"0xA7CF0FE0" /* STRING */ => return 1
        case u32"0x28C20CF1" /* NUMBER */ => return 1
        case u32"0xFDCE65E5" /* "{" */ => return 1
        case u32"0xA44269E9" /* "[" */ => return 1
        case u32"0xAFEF039D" /* "true" */ => return 1
        case u32"0xD8AFD1B9" /* "false" */ => return 1
        case u32"0x3EA44541" /* "null" */ => return 1
        case _ =>
      }
    }
    return 0
  }

  @pure def predictObject(j: Z): Z = {
    val tokenJ = tokens.at(j)
    if (tokenJ.kind == Result.Kind.Normal) {
      tokenJ.leaf.tipe match {
        case u32"0xFDCE65E5" /* "{" */ => return 1
        case _ =>
      }
    }
    return 0
  }

  def retVal(n: Z, resOpt: Option[Result], initial: B, noBacktrack: B): Result = {
    resOpt match {
      case Some(res) => return res
      case _ => return Result.error(F, if (noBacktrack && !initial) -n else n)
    }
  }

  @pure def posOpts(docInfo: message.DocInfo,
                    posOpt1: Option[message.Position],
                    posOpt2: Option[message.Position]): Option[message.Position] = {
    val pos1 = posOpt1.get
    val pos2 = posOpt2.get
    return Some(message.PosInfo(docInfo, offsetLength(pos1.offset,
      pos2.offset + pos2.length - pos1.offset)))
  }

}

@datatype class JsonLexer(cis: Indexable.Pos[C]) {

  def tokenizeAll(skipHidden: B, stopAtError: B, reporter: message.Reporter): ISZ[Result] = {
    var i: Z = 0
    var r = ISZ[Result]()
    var done = F
    while (!done && cis.has(i)) {
      tokenize(i, skipHidden) match {
        case Some(result) =>
          if (result.kind == Result.Kind.Normal) {
            i = result.newIndex
            r = r :+ result
          } else {
            val posOpt = cis.posOpt(i, 1)
            reporter.error(posOpt, kind, s"Could not recognize token")
            if (stopAtError) {
              return r
            }
            r = r :+ result(tree = errorLeaf(text = conversions.String.fromCis(ISZ(cis.at(i))), posOpt = posOpt))
            i = i + 1
          }
        case _ => done = T
      }
    }
    r = r :+ Result.create(eofLeaf, -1)
    return r
  }

  @pure def tokenize(i: Z, skipHidden: B): Option[Result] = {
    val r = MBox(Result.error(T, i))
    tokenizeH(r, i)
    while (skipHidden && r.value.leaf.isHidden && cis.has(r.value.newIndex)) {
      tokenizeH(r, r.value.newIndex)
    }
    return if (skipHidden && r.value.leaf.isHidden) None() else Some(r.value)
  }

  def tokenizeH(r: MBox[Result], i: Z): Unit = {
    updateToken(r, lex_true(i))
    updateToken(r, lex_false(i))
    updateToken(r, lex_null(i))
    updateToken(r, lex_u007B(i))
    updateToken(r, lex_u003A(i))
    updateToken(r, lex_u002C(i))
    updateToken(r, lex_u007D(i))
    updateToken(r, lex_u005B(i))
    updateToken(r, lex_u005D(i))
    updateToken(r, lex_STRING(i))
    updateToken(r, lex_NUMBER(i))
    updateToken(r, lex_WS(i))
  }

  def updateToken(r: MBox[Result], rOpt: Option[Result]): Unit = {
    rOpt match {
      case Some(newR) if newR.newIndex > r.value.newIndex => r.value = newR
      case _ =>
    }
  }

  @pure def lit_true(i: Z): Z = {
    if (!cis.has(i + 4)) {
      return -1
    }
    if (cis.at(i) == 't' && cis.at(i + 1) == 'r' && cis.at(i + 2) == 'u' && cis.at(i + 3) == 'e') {
      return i + 4
    }
    return -1
  }

  @pure def lex_true(index: Z): Option[Result] = { return lexH(index, lit_true(index), """'true'""", u32"0xAFEF039D" /* "true" */, F) }

  @pure def lit_false(i: Z): Z = {
    if (!cis.has(i + 5)) {
      return -1
    }
    if (cis.at(i) == 'f' && cis.at(i + 1) == 'a' && cis.at(i + 2) == 'l' && cis.at(i + 3) == 's' && cis.at(i + 4) == 'e') {
      return i + 5
    }
    return -1
  }

  @pure def lex_false(index: Z): Option[Result] = { return lexH(index, lit_false(index), """'false'""", u32"0xD8AFD1B9" /* "false" */, F) }

  @pure def lit_null(i: Z): Z = {
    if (!cis.has(i + 4)) {
      return -1
    }
    if (cis.at(i) == 'n' && cis.at(i + 1) == 'u' && cis.at(i + 2) == 'l' && cis.at(i + 3) == 'l') {
      return i + 4
    }
    return -1
  }

  @pure def lex_null(index: Z): Option[Result] = { return lexH(index, lit_null(index), """'null'""", u32"0x3EA44541" /* "null" */, F) }

  @pure def lit_u007B(i: Z): Z = {
    if (cis.has(i) && cis.at(i) == '{') {
      return i + 1
    }
    return -1
  }

  @pure def lex_u007B(index: Z): Option[Result] = { return lexH(index, lit_u007B(index), """'{'""", u32"0xFDCE65E5" /* "{" */, F) }

  @pure def lit_u003A(i: Z): Z = {
    if (cis.has(i) && cis.at(i) == ':') {
      return i + 1
    }
    return -1
  }

  @pure def lex_u003A(index: Z): Option[Result] = { return lexH(index, lit_u003A(index), """':'""", u32"0x763C38BE" /* ":" */, F) }

  @pure def lit_u002C(i: Z): Z = {
    if (cis.has(i) && cis.at(i) == ',') {
      return i + 1
    }
    return -1
  }

  @pure def lex_u002C(index: Z): Option[Result] = { return lexH(index, lit_u002C(index), """','""", u32"0x45445E21" /* "," */, F) }

  @pure def lit_u007D(i: Z): Z = {
    if (cis.has(i) && cis.at(i) == '}') {
      return i + 1
    }
    return -1
  }

  @pure def lex_u007D(index: Z): Option[Result] = { return lexH(index, lit_u007D(index), """'}'""", u32"0x5BF60471" /* "}" */, F) }

  @pure def lit_u005B(i: Z): Z = {
    if (cis.has(i) && cis.at(i) == '[') {
      return i + 1
    }
    return -1
  }

  @pure def lex_u005B(index: Z): Option[Result] = { return lexH(index, lit_u005B(index), """'['""", u32"0xA44269E9" /* "[" */, F) }

  @pure def lit_u005D(i: Z): Z = {
    if (cis.has(i) && cis.at(i) == ']') {
      return i + 1
    }
    return -1
  }

  @pure def lex_u005D(index: Z): Option[Result] = { return lexH(index, lit_u005D(index), """']'""", u32"0x9977908D" /* "]" */, F) }

  @pure def dfa_STRING(i: Z): Z = {
    val ctx = LContext.create(ISZ(state"2"), i)

    while (cis.has(ctx.j)) {
      ctx.state match {
        case state"0" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if (c == '"') {
            ctx.update(state"1")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"1" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if (' ' <= c && c <= '!' || '#' <= c && c <= '[' || ']' <= c && c <= maxChar) {
            ctx.update(state"1")
          } else if (c == '"') {
            ctx.update(state"2")
          } else if (c == '\\') {
            ctx.update(state"3")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"2" => return ctx.afterAcceptIndex
        case state"3" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if (c == '"' || c == '/' || c == '\\' || c == 'b' || c == 'f' || c == 'n' || c == 'r' || c == 't') {
            ctx.update(state"1")
          } else if (c == 'u') {
            ctx.update(state"4")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"4" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if ('0' <= c && c <= '9' || 'A' <= c && c <= 'F' || 'a' <= c && c <= 'f') {
            ctx.update(state"5")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"5" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if ('0' <= c && c <= '9' || 'A' <= c && c <= 'F' || 'a' <= c && c <= 'f') {
            ctx.update(state"6")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"6" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if ('0' <= c && c <= '9' || 'A' <= c && c <= 'F' || 'a' <= c && c <= 'f') {
            ctx.update(state"7")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"7" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if ('0' <= c && c <= '9' || 'A' <= c && c <= 'F' || 'a' <= c && c <= 'f') {
            ctx.update(state"1")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case _ => halt("Infeasible")
      }
      ctx.j = ctx.j + 1
    }
    return ctx.afterAcceptIndex
  }

  @pure def lex_STRING(index: Z): Option[Result] = { return lexH(index, dfa_STRING(index), """STRING""", u32"0xA7CF0FE0", F) }

  @pure def dfa_NUMBER(i: Z): Z = {
    val ctx = LContext.create(ISZ(state"2", state"4", state"7", state"8", state"9"), i)

    while (cis.has(ctx.j)) {
      ctx.state match {
        case state"0" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if (c == '-') {
            ctx.update(state"1")
          } else if (c == '0') {
            ctx.update(state"2")
          } else if ('1' <= c && c <= '9') {
            ctx.update(state"9")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"1" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if (c == '0') {
            ctx.update(state"2")
          } else if ('1' <= c && c <= '9') {
            ctx.update(state"9")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"2" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if (c == '.') {
            ctx.update(state"3")
          } else if (c == 'E' || c == 'e') {
            ctx.update(state"5")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"3" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if ('0' <= c && c <= '9') {
            ctx.update(state"4")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"4" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if ('0' <= c && c <= '9') {
            ctx.update(state"4")
          } else if (c == 'E' || c == 'e') {
            ctx.update(state"5")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"5" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if (c == '+' || c == '-') {
            ctx.update(state"6")
          } else if (c == '0') {
            ctx.update(state"7")
          } else if ('1' <= c && c <= '9') {
            ctx.update(state"8")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"6" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if (c == '0') {
            ctx.update(state"7")
          } else if ('1' <= c && c <= '9') {
            ctx.update(state"8")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"7" => return ctx.afterAcceptIndex
        case state"8" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if ('0' <= c && c <= '9') {
            ctx.update(state"8")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"9" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if (c == '.') {
            ctx.update(state"3")
          } else if ('0' <= c && c <= '9') {
            ctx.update(state"9")
          } else if (c == 'E' || c == 'e') {
            ctx.update(state"5")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case _ => halt("Infeasible")
      }
      ctx.j = ctx.j + 1
    }
    return ctx.afterAcceptIndex
  }

  @pure def lex_NUMBER(index: Z): Option[Result] = { return lexH(index, dfa_NUMBER(index), """NUMBER""", u32"0x28C20CF1", F) }

  @pure def dfa_WS(i: Z): Z = {
    val ctx = LContext.create(ISZ(state"1"), i)

    while (cis.has(ctx.j)) {
      ctx.state match {
        case state"0" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if ('\u0009' <= c && c <= '\u000A' || c == '\u000D' || c == ' ') {
            ctx.update(state"1")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case state"1" =>
          val c = cis.at(ctx.j)
          ctx.found = F
          if ('\u0009' <= c && c <= '\u000A' || c == '\u000D' || c == ' ') {
            ctx.update(state"1")
          }
          if (!ctx.found) {
            return ctx.afterAcceptIndex
          }
        case _ => halt("Infeasible")
      }
      ctx.j = ctx.j + 1
    }
    return ctx.afterAcceptIndex
  }

  @pure def lex_WS(index: Z): Option[Result] = { return lexH(index, dfa_WS(index), """WS""", u32"0x0E3F5D1E", T) }

  @pure def hidden(i: Z): Z = {
     var j: Z = -1
     j = dfa_WS(i)
     if (j > 0) {
       return j
     }
     return -1
  }

  @pure def lexH(index: Z, newIndex: Z, name: String, tipe: U32, isHidden: B): Option[Result] = {
    if (newIndex > 0) {
      return Some(Result.create(ParseTree.Leaf(conversions.String.fromCis(for (i <- index until newIndex) yield cis.at(i)),
        name, tipe, isHidden, cis.posOpt(index, newIndex - index)), newIndex))
    } else {
      return None()
    }
  }

}