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

package org.sireum

import org.sireum.U32._
import org.sireum.S32._
import org.sireum.automaton._
import org.sireum.parser._
import org.sireum.test._

class LexerDfaCompactTest extends TestSuite {

  def toInput(s: String): Indexable.PosC = {
    val cis = conversions.String.toCis(s)
    val info = message.DocInfo(uriOpt = None(), lineOffsets = ISZ(u32"0"))
    Indexable.fromIszDocInfoC(cis, info)
  }

  // Helpers to construct IS[S32, ...] from varargs
  def bools(args: B*): IS[S32, B] = IS[S32, B](args: _*)
  def trans(args: LexerDfa.Transition*): IS[S32, LexerDfa.Transition] = IS[S32, LexerDfa.Transition](args: _*)
  def transRows(args: IS[S32, LexerDfa.Transition]*): IS[S32, IS[S32, LexerDfa.Transition]] =
    IS[S32, IS[S32, LexerDfa.Transition]](args: _*)
  def t(lo: C, hi: C, target: scala.Int): LexerDfa.Transition =
    LexerDfa.Transition(lo = lo, hi = hi, target = S32(target))
  def dfaArr(args: LexerDfa*): IS[S32, LexerDfa] = IS[S32, LexerDfa](args: _*)
  def strArr(args: String*): IS[S32, String] = IS[S32, String](args: _*)
  def typeArr(args: scala.Int*): IS[S32, S32] = IS[S32, S32](args.map(S32(_)): _*)

  val tests = Tests {

    // ---- compact roundtrip ----

    // Empty LexerDfas (no DFAs, no EOF)
    * - {
      val lds = LexerDfas.create(
        dfas = dfaArr(),
        names = strArr(),
        types = typeArr(),
        hiddens = bools(),
        eofTypeOpt = None()
      )
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // Single DFA with one accepting state (no transitions)
    * - {
      val dfa = LexerDfa(
        accepting = bools(T),
        transitions = transRows(trans())
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("EMPTY"),
        types = typeArr(0),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // Single DFA: 2 states, transition a-z
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("LOWER"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // Multiple transitions per state
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T, T),
        transitions = transRows(
          trans(t('a', 'z', 1), t('0', '9', 2)),
          trans(),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ALNUM"),
        types = typeArr(2),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // Multiple DFAs
    * - {
      val dfa1 = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans(t('a', 'z', 1))
        )
      )
      val dfa2 = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('0', '9', 1)),
          trans(t('0', '9', 1))
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa1, dfa2),
        names = strArr("ID", "NUM"),
        types = typeArr(1, 2),
        hiddens = bools(F, F),
        eofTypeOpt = None()
      )
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // DFAs with different state counts
    * - {
      val dfa1 = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'a', 1)),
          trans()
        )
      )
      val dfa2 = LexerDfa(
        accepting = bools(F, F, T),
        transitions = transRows(
          trans(t('b', 'b', 1)),
          trans(t('c', 'c', 2)),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa1, dfa2),
        names = strArr("A", "BC"),
        types = typeArr(1, 2),
        hiddens = bools(F, F),
        eofTypeOpt = None()
      )
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // With eofTypeOpt
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ID"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = Some(S32(99))
      )
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // Hidden DFA
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t(' ', ' ', 1)),
          trans(t(' ', ' ', 1))
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("WS"),
        types = typeArr(0),
        hiddens = bools(T),
        eofTypeOpt = None()
      )
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // toCompactST produces correct format and roundtrips
    * - {
      val dfa = LexerDfa(
        accepting = bools(T),
        transitions = transRows(trans())
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("TOK"),
        types = typeArr(0),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      val rendered = lds.toCompactST.render
      assert(ops.StringOps(rendered).startsWith("LexerDfas.fromCompact(\""))
      val inner = ops.StringOps(rendered).substring(23, rendered.size - 2)
      val decoded = LexerDfas.fromCompact(inner)
      assert(decoded == lds)
    }

    // toST produces non-empty output
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ID"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = Some(S32(0))
      )
      val rendered = lds.toST.render
      assert(ops.StringOps(rendered).contains("LexerDfas"))
      assert(ops.StringOps(rendered).contains("DFA ID"))
      assert(ops.StringOps(rendered).contains("EOF type: 0"))
    }

    // ---- fromDfas ----

    // fromDfas: convert Graph-based DFA, verify structure, and roundtrip compact
    * - {
      var g = Graph.empty[Z, (C, C)]
      g = g * z"0"
      g = g * z"1"
      g = g.addDataEdge(('a', 'z'), z"0", z"1")
      // Reject self-loop on state 1 (should be filtered out)
      g = g.addDataEdge(('\u0000', Dfa.maxChar), z"1", z"1")
      val dfa = Dfa[(C, C)](
        initial = z"0",
        accepting = HashSSet.empty[Z] + z"1",
        g = g
      )
      val lds = LexerDfas.fromDfas(
        dfaInfos = ISZ((dfa, "LOWER", Z(1), F)),
        eofTypeOpt = Some(Z(0))
      )
      assert(lds.dfas.size == 1)
      assert(lds.names == strArr("LOWER"))
      assert(lds.types == typeArr(1))
      assert(lds.hiddens == bools(F))
      assert(lds.eofTypeOpt == Some(S32(0)))
      assert(!lds.dfas(S32(0)).accepting(S32(0)))
      assert(lds.dfas(S32(0)).accepting(S32(1)))
      assert(lds.dfas(S32(0)).transitions(S32(0)).size == 1)
      assert(lds.dfas(S32(0)).transitions(S32(1)).size == 0)
      // Compact roundtrip
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // fromDfas: multiple DFAs with different sizes
    * - {
      // DFA1: single letter [a-z]
      var g1 = Graph.empty[Z, (C, C)]
      g1 = g1 * z"0"
      g1 = g1 * z"1"
      g1 = g1.addDataEdge(('a', 'z'), z"0", z"1")
      val dfa1 = Dfa[(C, C)](initial = z"0", accepting = HashSSet.empty[Z] + z"1", g = g1)

      // DFA2: two-digit number [0-9][0-9]
      var g2 = Graph.empty[Z, (C, C)]
      g2 = g2 * z"0"
      g2 = g2 * z"1"
      g2 = g2 * z"2"
      g2 = g2.addDataEdge(('0', '9'), z"0", z"1")
      g2 = g2.addDataEdge(('0', '9'), z"1", z"2")
      val dfa2 = Dfa[(C, C)](initial = z"0", accepting = HashSSet.empty[Z] + z"2", g = g2)

      val lds = LexerDfas.fromDfas(
        dfaInfos = ISZ((dfa1, "LETTER", Z(1), F), (dfa2, "TWODIGIT", Z(2), F)),
        eofTypeOpt = None()
      )
      assert(lds.dfas.size == 2)
      assert(lds.dfas(S32(0)).accepting.size == 2)
      assert(lds.dfas(S32(1)).accepting.size == 3)
      // Compact roundtrip
      val decoded = LexerDfas.fromCompact(lds.toCompact)
      assert(decoded == lds)
    }

    // ---- lex ----

    // Lex single token
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans(t('a', 'z', 1))
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ID"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      val result = lds.lex(toInput("hello"), S32(0))
      assert(result.nonEmpty)
      assert(result.get._1 == S32(5))
      assert(result.get._2.text == String("hello"))
      assert(result.get._2.ruleName == String("ID"))
      assert(result.get._2.num == S32(1))
    }

    // Lex no match returns None
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("LOWER"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      assert(lds.lex(toInput("123"), S32(0)).isEmpty)
    }

    // Lex longest match across DFAs
    * - {
      // DFA1: matches single letter
      val dfa1 = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans()
        )
      )
      // DFA2: matches one or more letters
      val dfa2 = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans(t('a', 'z', 1))
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa1, dfa2),
        names = strArr("LETTER", "WORD"),
        types = typeArr(1, 2),
        hiddens = bools(F, F),
        eofTypeOpt = None()
      )
      val result = lds.lex(toInput("abc"), S32(0))
      assert(result.nonEmpty)
      assert(result.get._1 == S32(3))
      assert(result.get._2.text == String("abc"))
      assert(result.get._2.ruleName == String("WORD"))
      assert(result.get._2.num == S32(2))
    }

    // ---- tokens ----

    // Tokens: tokenize with hidden WS and EOF
    * - {
      val idDfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans(t('a', 'z', 1))
        )
      )
      val wsDfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t(' ', ' ', 1)),
          trans(t(' ', ' ', 1))
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(idDfa, wsDfa),
        names = strArr("ID", "WS"),
        types = typeArr(1, 2),
        hiddens = bools(F, T),
        eofTypeOpt = Some(S32(0))
      )
      val (errIdx, toks) = lds.tokens(toInput("abc def"), T)
      assert(errIdx == S32(-1))
      // skipHidden=T: ID("abc"), ID("def"), EOF
      assert(toks.size == 3)
      assert(toks(0).text == String("abc"))
      assert(toks(0).num == S32(1))
      assert(toks(1).text == String("def"))
      assert(toks(1).num == S32(1))
      assert(toks(2).text == String(""))
      assert(toks(2).num == S32(0))
    }

    // Tokens: skipHidden=F includes hidden tokens
    * - {
      val idDfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans(t('a', 'z', 1))
        )
      )
      val wsDfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t(' ', ' ', 1)),
          trans(t(' ', ' ', 1))
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(idDfa, wsDfa),
        names = strArr("ID", "WS"),
        types = typeArr(1, 2),
        hiddens = bools(F, T),
        eofTypeOpt = None()
      )
      val (errIdx, toks) = lds.tokens(toInput("a b"), F)
      assert(errIdx == S32(-1))
      // skipHidden=F: ID("a"), WS(" "), ID("b")
      assert(toks.size == 3)
      assert(toks(0).text == String("a"))
      assert(toks(1).text == String(" "))
      assert(toks(1).isHidden)
      assert(toks(2).text == String("b"))
    }

    // Tokens: error on unlexable character
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans(t('a', 'z', 1))
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ID"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      val (errIdx, toks) = lds.tokens(toInput("abc!def"), F)
      assert(errIdx == S32(3))
      assert(toks.size == 1)
      assert(toks(0).text == String("abc"))
    }

    // Tokens: empty input with EOF
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ID"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = Some(S32(0))
      )
      val (errIdx, toks) = lds.tokens(toInput(""), F)
      assert(errIdx == S32(-1))
      assert(toks.size == 1)
      assert(toks(0).text == String(""))
      assert(toks(0).num == S32(0))
    }

    // Tokens: empty input without EOF
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ID"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      val (errIdx, toks) = lds.tokens(toInput(""), F)
      assert(errIdx == S32(-1))
      assert(toks.size == 0)
    }

    // fromDfas + lex end-to-end
    * - {
      var g = Graph.empty[Z, (C, C)]
      g = g * z"0"
      g = g * z"1"
      g = g.addDataEdge(('a', 'z'), z"0", z"1")
      g = g.addDataEdge(('a', 'z'), z"1", z"1")
      val dfa = Dfa[(C, C)](initial = z"0", accepting = HashSSet.empty[Z] + z"1", g = g)
      val lds = LexerDfas.fromDfas(
        dfaInfos = ISZ((dfa, "ID", Z(1), F)),
        eofTypeOpt = None()
      )
      val result = lds.lex(toInput("test"), S32(0))
      assert(result.nonEmpty)
      assert(result.get._1 == S32(4))
      assert(result.get._2.text == String("test"))
    }

    // ---- runDfa edge cases ----

    // runDfa: empty DFA (0 states) returns -1
    * - {
      val dfa = LexerDfa(
        accepting = IS[S32, B](),
        transitions = IS[S32, IS[S32, LexerDfa.Transition]]()
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("EMPTY"),
        types = typeArr(0),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      assert(lds.runDfa(dfa, toInput("abc"), S32(0)) == S32(-1))
    }

    // runDfa: initial state is accepting (empty match)
    * - {
      val dfa = LexerDfa(
        accepting = bools(T, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans(t('a', 'z', 1))
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ID"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      // Initial state accepts, then keeps matching
      assert(lds.runDfa(dfa, toInput("abc"), S32(0)) == S32(3))
      // Even on non-matching input, initial state still accepts at pos 0
      assert(lds.runDfa(dfa, toInput("123"), S32(0)) == S32(0))
    }

    // runDfa: transition to non-accepting state (multi-state DFA with non-accepting intermediate)
    * - {
      // DFA: state 0 -[a-z]-> state 1 (not accepting) -[0-9]-> state 2 (accepting)
      val dfa = LexerDfa(
        accepting = bools(F, F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans(t('0', '9', 2)),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ALPHA_DIGIT"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      // "a1" matches fully
      assert(lds.runDfa(dfa, toInput("a1"), S32(0)) == S32(2))
      // "ab" transitions to state 1 (non-accepting), then fails -> returns -1
      assert(lds.runDfa(dfa, toInput("ab"), S32(0)) == S32(-1))
    }

    // toST: without EOF shows "No EOF"
    * - {
      val dfa = LexerDfa(
        accepting = bools(F, T),
        transitions = transRows(
          trans(t('a', 'z', 1)),
          trans()
        )
      )
      val lds = LexerDfas.create(
        dfas = dfaArr(dfa),
        names = strArr("ID"),
        types = typeArr(1),
        hiddens = bools(F),
        eofTypeOpt = None()
      )
      val rendered = lds.toST.render
      assert(ops.StringOps(rendered).contains("No EOF"))
      assert(ops.StringOps(rendered).contains("DFA ID"))
    }
  }
}
