// #Sireum
/*
 Copyright (c) 2017-2022, Robby, Kansas State University
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

package org.sireum.parser.json


import org.sireum._
import org.sireum.U32._
import org.sireum.U64._
import org.sireum.conversions.U32.toC
import org.sireum.parser.ParseTree

@range(min = 0, max = 9) class State

import State._

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

    def update(newState: State): Unit = {
      initial = F
      state = newState
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

  @record class LContext(val accepting: IS[State, B], var state: State, var j: Z, var afterAcceptIndex: Z) {
    def update(newState: State): Unit = {
      state = newState
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
      return LContext(accepting = accepting.toIS, state = state"0", j = i, afterAcceptIndex = -1)
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

  val errorLeaf: ParseTree.Leaf = ParseTree.Leaf("", "<ERROR>", u32"0xE3CDEDDA", F, None())
  val eofLeaf: ParseTree.Leaf = ParseTree.Leaf("", "EOF", u32"0xFC5CB374", F, None())

  def parse(uriOpt: Option[String], input: String, reporter: message.Reporter): Option[ParseTree.Result] = {
    val docInfo = message.DocInfo.create(uriOpt, input)
    val tokens = lex(input, docInfo, T, T, reporter)
    if (reporter.hasError) {
      return None()
    }
    val r = JsonParser(tokens).parseValueFile(0)
    r.kind match {
      case Result.Kind.Normal => return Some(ParseTree.Result(r.tree, docInfo))
      case Result.Kind.LexicalError =>
        reporter.error(Some(message.PosInfo(docInfo, offsetLength(r.newIndex, 1))), kind, s"Could not recognize token")
        return None()
      case Result.Kind.GrammaticalError =>
        val idx: Z = if (r.newIndex < 0) -r.newIndex else r.newIndex
        if (idx < tokens.size) {
          reporter.error(tokens(idx).posOpt, kind, s"Could not parse token: ${tokens(idx).text}")
        } else {
          reporter.error(tokens(idx - 1).posOpt, kind, "Expecting more input but reached the end")
        }
        return None()
    }
  }

  def lex(input: String, docInfo: message.DocInfo, skipHidden: B, stopAtError: B,
          reporter: message.Reporter): ISZ[ParseTree.Leaf] = {
    return JsonLexer(input, docInfo).tokenizeAll(skipHidden, stopAtError, reporter)
  }

  @strictpure def offsetLength(offset: Z, length: Z): U64 =
    (conversions.Z.toU64(offset) << u64"32") | (conversions.Z.toU64(length) & u64"0xFFFFFFFF")

}

import JsonParser._

@datatype class JsonParser(tokens: ISZ[ParseTree.Leaf]) {

  @pure def parseValueFile(i: Z): Result = {
    val ctx = Context.create("valueFile", u32"0x94F3E412" /* valueFile */, ISZ(state"2"), i)

    while (ctx.j < tokens.size) {
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
          tokens(ctx.j).tipe match {
            case u32"0xFC5CB374" /* EOF */ =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"2")
              ctx.found = T
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
    val ctx = Context.create("value", u32"0x82EEA07A" /* value */, ISZ(state"1"), i)

    while (ctx.j < tokens.size) {
      ctx.state match {
        case state"0" =>
          ctx.found = F
          val n_object = predictObject(ctx.j)
          val n_array = predictArray(ctx.j)
          for (n <- 1 to 1 by -1 if !ctx.found) {
            if (n_object == n && parseObjectH(ctx, state"1")) {
              return Result.error(ctx.isLexical, ctx.failIndex)
            }
            if (!ctx.found && n_array == n && parseArrayH(ctx, state"1")) {
              return Result.error(ctx.isLexical, ctx.failIndex)
            }
          }
          tokens(ctx.j).tipe match {
            case u32"0xA7CF0FE0" /* STRING */ if !ctx.found =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"1")
              ctx.found = T
            case u32"0x28C20CF1" /* NUMBER */ if !ctx.found =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"1")
              ctx.found = T
            case u32"0xAFEF039D" /* "true" */ if !ctx.found =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"1")
              ctx.found = T
            case u32"0xD8AFD1B9" /* "false" */ if !ctx.found =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"1")
              ctx.found = T
            case u32"0x3EA44541" /* "null" */ if !ctx.found =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"1")
              ctx.found = T
            case _ =>
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
    val ctx = Context.create("object", u32"0x5ED5358F" /* object */, ISZ(state"8"), i)

    while (ctx.j < tokens.size) {
      ctx.state match {
        case state"0" =>
          ctx.found = F
          tokens(ctx.j).tipe match {
            case u32"0xFDCE65E5" /* "{" */ =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"1")
              ctx.found = T
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"1" =>
          ctx.found = F
          tokens(ctx.j).tipe match {
            case u32"0xA7CF0FE0" /* STRING */ =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"2")
              ctx.found = T
            case u32"0x5BF60471" /* "}" */ if !ctx.found =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"8")
              ctx.found = T
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"2" =>
          ctx.found = F
          tokens(ctx.j).tipe match {
            case u32"0x763C38BE" /* ":" */ =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"3")
              ctx.found = T
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
          tokens(ctx.j).tipe match {
            case u32"0x45445E21" /* "," */ =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"5")
              ctx.found = T
            case u32"0x5BF60471" /* "}" */ if !ctx.found =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"8")
              ctx.found = T
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"5" =>
          ctx.found = F
          tokens(ctx.j).tipe match {
            case u32"0xA7CF0FE0" /* STRING */ =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"6")
              ctx.found = T
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"6" =>
          ctx.found = F
          tokens(ctx.j).tipe match {
            case u32"0x763C38BE" /* ":" */ =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"7")
              ctx.found = T
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
    val ctx = Context.create("array", u32"0xB11A9723" /* array */, ISZ(state"4"), i)

    while (ctx.j < tokens.size) {
      ctx.state match {
        case state"0" =>
          ctx.found = F
          tokens(ctx.j).tipe match {
            case u32"0xA44269E9" /* "[" */ =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"1")
              ctx.found = T
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
          tokens(ctx.j).tipe match {
            case u32"0x9977908D" /* "]" */ if !ctx.found =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"4")
              ctx.found = T
            case _ =>
          }
          if (!ctx.found) {
            return retVal(ctx.max, ctx.resOpt, ctx.initial, T)
          }
        case state"2" =>
          ctx.found = F
          tokens(ctx.j).tipe match {
            case u32"0x45445E21" /* "," */ =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"3")
              ctx.found = T
            case u32"0x9977908D" /* "]" */ if !ctx.found =>
              ctx.trees = ctx.trees :+ tokens(ctx.j)
              ctx.j = ctx.j + 1
              ctx.update(state"4")
              ctx.found = T
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
      case Result.Kind.Normal =>
        ctx.trees = ctx.trees :+ r.tree
        ctx.j = r.newIndex
        ctx.update(nextState)
        ctx.found = T
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
      case Result.Kind.Normal =>
        ctx.trees = ctx.trees :+ r.tree
        ctx.j = r.newIndex
        ctx.update(nextState)
        ctx.found = T
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
      case Result.Kind.Normal =>
        ctx.trees = ctx.trees :+ r.tree
        ctx.j = r.newIndex
        ctx.update(nextState)
        ctx.found = T
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
    tokens(j).tipe match {
      case u32"0xA7CF0FE0" /* STRING */ => return 1
      case u32"0x28C20CF1" /* NUMBER */ => return 1
      case u32"0xFDCE65E5" /* "{" */ => return 1
      case u32"0xA44269E9" /* "[" */ => return 1
      case u32"0xAFEF039D" /* "true" */ => return 1
      case u32"0xD8AFD1B9" /* "false" */ => return 1
      case u32"0x3EA44541" /* "null" */ => return 1
      case _ =>
    }
    return 0
  }

  @pure def predictArray(j: Z): Z = {
    tokens(j).tipe match {
      case u32"0xA44269E9" /* "[" */ => return 1
      case _ =>
    }
    return 0
  }

  @pure def predictValue(j: Z): Z = {
    tokens(j).tipe match {
      case u32"0xA7CF0FE0" /* STRING */ => return 1
      case u32"0x28C20CF1" /* NUMBER */ => return 1
      case u32"0xFDCE65E5" /* "{" */ => return 1
      case u32"0xA44269E9" /* "[" */ => return 1
      case u32"0xAFEF039D" /* "true" */ => return 1
      case u32"0xD8AFD1B9" /* "false" */ => return 1
      case u32"0x3EA44541" /* "null" */ => return 1
      case _ =>
    }
    return 0
  }

  @pure def predictObject(j: Z): Z = {
    tokens(j).tipe match {
      case u32"0xFDCE65E5" /* "{" */ => return 1
      case _ =>
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

@datatype class JsonLexer(input: String, docInfo: message.DocInfo) {

  val cis: ISZ[C] = conversions.String.toCis(input)

  def tokenizeAll(skipHidden: B, stopAtError: B, reporter: message.Reporter): ISZ[ParseTree.Leaf] = {
    var i: Z = 0
    var r = ISZ[ParseTree.Leaf]()
    while (i < cis.size) {
      val result = tokenize(i)
      result match {
        case Result(Result.Kind.Normal, token: ParseTree.Leaf, _) =>
          i = result.newIndex
          if (!(skipHidden && token.isHidden)) {
            r = r :+ token
          }
        case _ =>
          val posOpt: Option[message.Position] = Some(message.PosInfo(docInfo, offsetLength(i, 1)))
          reporter.error(posOpt, kind, s"Could not recognize token")
          if (stopAtError) {
            return r
          }
          r = r :+ errorLeaf(text = conversions.String.fromCis(ISZ(cis(i))), posOpt = posOpt)
          i = i + 1
      }
    }
    r = r :+ eofLeaf
    return r
  }

  @pure def tokenize(i: Z): Result = {
    val r = MBox(Result.error(T, i))
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
    return r.value
  }

  def updateToken(r: MBox[Result], rOpt: Option[Result]): Unit = {
    rOpt match {
      case Some(newR) if newR.newIndex > r.value.newIndex => r.value = newR
      case _ =>
    }
  }

  @pure def lit_true(i: Z): Z = {
    if (i + 4 >= cis.size) {
      return -1
    }
    if (cis(i) === 't' && cis(i + 1) === 'r' && cis(i + 2) === 'u' && cis(i + 3) === 'e') {
      return i + 4
    }
    return -1
  }

  @strictpure def lex_true(index: Z): Option[Result] =
     lexH(index, lit_true(index), """'true'""", u32"0xAFEF039D" /* "true" */, F)

  @pure def lit_false(i: Z): Z = {
    if (i + 5 >= cis.size) {
      return -1
    }
    if (cis(i) === 'f' && cis(i + 1) === 'a' && cis(i + 2) === 'l' && cis(i + 3) === 's' && cis(i + 4) === 'e') {
      return i + 5
    }
    return -1
  }

  @strictpure def lex_false(index: Z): Option[Result] =
     lexH(index, lit_false(index), """'false'""", u32"0xD8AFD1B9" /* "false" */, F)

  @pure def lit_null(i: Z): Z = {
    if (i + 4 >= cis.size) {
      return -1
    }
    if (cis(i) === 'n' && cis(i + 1) === 'u' && cis(i + 2) === 'l' && cis(i + 3) === 'l') {
      return i + 4
    }
    return -1
  }

  @strictpure def lex_null(index: Z): Option[Result] =
     lexH(index, lit_null(index), """'null'""", u32"0x3EA44541" /* "null" */, F)

  @pure def lit_u007B(i: Z): Z = {
    if (i < cis.size && cis(i) === '{') {
      return i + 1
    }
    return -1
  }

  @strictpure def lex_u007B(index: Z): Option[Result] =
     lexH(index, lit_u007B(index), """'{'""", u32"0xFDCE65E5" /* "{" */, F)

  @pure def lit_u003A(i: Z): Z = {
    if (i < cis.size && cis(i) === ':') {
      return i + 1
    }
    return -1
  }

  @strictpure def lex_u003A(index: Z): Option[Result] =
     lexH(index, lit_u003A(index), """':'""", u32"0x763C38BE" /* ":" */, F)

  @pure def lit_u002C(i: Z): Z = {
    if (i < cis.size && cis(i) === ',') {
      return i + 1
    }
    return -1
  }

  @strictpure def lex_u002C(index: Z): Option[Result] =
     lexH(index, lit_u002C(index), """','""", u32"0x45445E21" /* "," */, F)

  @pure def lit_u007D(i: Z): Z = {
    if (i < cis.size && cis(i) === '}') {
      return i + 1
    }
    return -1
  }

  @strictpure def lex_u007D(index: Z): Option[Result] =
     lexH(index, lit_u007D(index), """'}'""", u32"0x5BF60471" /* "}" */, F)

  @pure def lit_u005B(i: Z): Z = {
    if (i < cis.size && cis(i) === '[') {
      return i + 1
    }
    return -1
  }

  @strictpure def lex_u005B(index: Z): Option[Result] =
     lexH(index, lit_u005B(index), """'['""", u32"0xA44269E9" /* "[" */, F)

  @pure def lit_u005D(i: Z): Z = {
    if (i < cis.size && cis(i) === ']') {
      return i + 1
    }
    return -1
  }

  @strictpure def lex_u005D(index: Z): Option[Result] =
     lexH(index, lit_u005D(index), """']'""", u32"0x9977908D" /* "]" */, F)

  @pure def dfa_STRING(i: Z): Z = {
    val ctx = LContext.create(ISZ(state"2"), i)

    while (ctx.j < cis.size) {
      ctx.state match {
        case state"0" =>
          val c = cis(ctx.j)
          var found = F
          if (c === '"') {
            ctx.update(state"1")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"1" =>
          val c = cis(ctx.j)
          var found = F
          if (' ' <= c && c <= '!' || '#' <= c && c <= '[' || ']' <= c && c <= maxChar) {
            ctx.update(state"1")
            found = T
          }
          if (!found && (c === '"')) {
            ctx.update(state"2")
            found = T
          }
          if (!found && (c === '\\')) {
            ctx.update(state"3")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"2" => return ctx.afterAcceptIndex
        case state"3" =>
          val c = cis(ctx.j)
          var found = F
          if (c === '"' || c === '/' || c === '\\' || c === 'b' || c === 'f' || c === 'n' || c === 'r' || c === 't') {
            ctx.update(state"1")
            found = T
          }
          if (!found && (c === 'u')) {
            ctx.update(state"4")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"4" =>
          val c = cis(ctx.j)
          var found = F
          if ('0' <= c && c <= '9' || 'A' <= c && c <= 'F' || 'a' <= c && c <= 'f') {
            ctx.update(state"5")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"5" =>
          val c = cis(ctx.j)
          var found = F
          if ('0' <= c && c <= '9' || 'A' <= c && c <= 'F' || 'a' <= c && c <= 'f') {
            ctx.update(state"6")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"6" =>
          val c = cis(ctx.j)
          var found = F
          if ('0' <= c && c <= '9' || 'A' <= c && c <= 'F' || 'a' <= c && c <= 'f') {
            ctx.update(state"7")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"7" =>
          val c = cis(ctx.j)
          var found = F
          if ('0' <= c && c <= '9' || 'A' <= c && c <= 'F' || 'a' <= c && c <= 'f') {
            ctx.update(state"1")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case _ => halt("Infeasible")
      }
      ctx.j = ctx.j + 1
    }
    return ctx.afterAcceptIndex
  }

  @strictpure def lex_STRING(index: Z): Option[Result] =
     lexH(index, dfa_STRING(index), """STRING""", u32"0xA7CF0FE0", F)

  @pure def dfa_NUMBER(i: Z): Z = {
    val ctx = LContext.create(ISZ(state"2", state"4", state"7", state"8", state"9"), i)

    while (ctx.j < cis.size) {
      ctx.state match {
        case state"0" =>
          val c = cis(ctx.j)
          var found = F
          if (c === '-') {
            ctx.update(state"1")
            found = T
          }
          if (!found && (c === '0')) {
            ctx.update(state"2")
            found = T
          }
          if (!found && ('1' <= c && c <= '9')) {
            ctx.update(state"9")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"1" =>
          val c = cis(ctx.j)
          var found = F
          if (c === '0') {
            ctx.update(state"2")
            found = T
          }
          if (!found && ('1' <= c && c <= '9')) {
            ctx.update(state"9")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"2" =>
          val c = cis(ctx.j)
          var found = F
          if (c === '.') {
            ctx.update(state"3")
            found = T
          }
          if (!found && (c === 'E' || c === 'e')) {
            ctx.update(state"5")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"3" =>
          val c = cis(ctx.j)
          var found = F
          if ('0' <= c && c <= '9') {
            ctx.update(state"4")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"4" =>
          val c = cis(ctx.j)
          var found = F
          if ('0' <= c && c <= '9') {
            ctx.update(state"4")
            found = T
          }
          if (!found && (c === 'E' || c === 'e')) {
            ctx.update(state"5")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"5" =>
          val c = cis(ctx.j)
          var found = F
          if (c === '+' || c === '-') {
            ctx.update(state"6")
            found = T
          }
          if (!found && (c === '0')) {
            ctx.update(state"7")
            found = T
          }
          if (!found && ('1' <= c && c <= '9')) {
            ctx.update(state"8")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"6" =>
          val c = cis(ctx.j)
          var found = F
          if (c === '0') {
            ctx.update(state"7")
            found = T
          }
          if (!found && ('1' <= c && c <= '9')) {
            ctx.update(state"8")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"7" => return ctx.afterAcceptIndex
        case state"8" =>
          val c = cis(ctx.j)
          var found = F
          if ('0' <= c && c <= '9') {
            ctx.update(state"8")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"9" =>
          val c = cis(ctx.j)
          var found = F
          if (c === '.') {
            ctx.update(state"3")
            found = T
          }
          if (!found && ('0' <= c && c <= '9')) {
            ctx.update(state"9")
            found = T
          }
          if (!found && (c === 'E' || c === 'e')) {
            ctx.update(state"5")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case _ => halt("Infeasible")
      }
      ctx.j = ctx.j + 1
    }
    return ctx.afterAcceptIndex
  }

  @strictpure def lex_NUMBER(index: Z): Option[Result] =
     lexH(index, dfa_NUMBER(index), """NUMBER""", u32"0x28C20CF1", F)

  @pure def dfa_WS(i: Z): Z = {
    val ctx = LContext.create(ISZ(state"1"), i)

    while (ctx.j < cis.size) {
      ctx.state match {
        case state"0" =>
          val c = cis(ctx.j)
          var found = F
          if ('\u0009' <= c && c <= '\u000A' || c === '\u000D' || c === ' ') {
            ctx.update(state"1")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case state"1" =>
          val c = cis(ctx.j)
          var found = F
          if ('\u0009' <= c && c <= '\u000A' || c === '\u000D' || c === ' ') {
            ctx.update(state"1")
            found = T
          }
          if (!found) {
            return ctx.afterAcceptIndex
          }
        case _ => halt("Infeasible")
      }
      ctx.j = ctx.j + 1
    }
    return ctx.afterAcceptIndex
  }

  @strictpure def lex_WS(index: Z): Option[Result] =
     lexH(index, dfa_WS(index), """WS""", u32"0x0E3F5D1E", T)

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
      return Some(Result.create(ParseTree.Leaf(ops.StringOps.substring(cis, index, newIndex),
        name, tipe, isHidden, Some(message.PosInfo(docInfo, offsetLength(index, newIndex - index)))),
        newIndex))
    } else {
      return None()
    }
  }

}