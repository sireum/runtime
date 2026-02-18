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

object LexerDfa {
  @datatype class Transition(val lo: C, val hi: C, val target: S32)
}

/** A single DFA stored as parallel arrays for O(1) state lookup.
  *
  * @param accepting    bit set where bit s is set if state s is an accepting state
  * @param transitions  transitions(s) holds the outgoing (lo, hi, target) edges for state s
  */
@datatype class LexerDfa(
  val accepting: BitSet,
  val transitions: IS[S32, IS[S32, LexerDfa.Transition]]
)

object LexerDfas {

  /** Sentinel token returned by `LexerDfas.lex` when no match is found.
    * Check via `token.tipe == s32"-1"`.
    */
  val sentinelToken: Token = ParseTree.Leaf(text = "", ruleName = "", tipe = s32"-1", isHidden = F, posOpt = None())

  /** Convenience factory that computes first-character dispatch tables from
    * the given DFAs and constructs a LexerDfas.
    */
  def create(dfas: IS[S32, LexerDfa],
             names: IS[S32, String],
             types: IS[S32, S32],
             hiddens: BitSet,
             eofTypeOpt: Option[S32]): LexerDfas = {
    val (ad, nai) = computeDispatch(dfas)
    return LexerDfas(
      dfas = dfas,
      names = names,
      types = types,
      hiddens = hiddens,
      eofTypeOpt = eofTypeOpt,
      asciiDispatch = ad,
      nonAsciiIndices = nai
    )
  }

  /** Computes first-character dispatch tables from the given DFAs.
    *
    * For each ASCII character (0-127), collects the indices of DFAs whose
    * state 0 has a transition covering that character. Also collects DFA
    * indices that have any non-ASCII transition from state 0.
    *
    * @param dfas the individual DFAs
    * @return (asciiDispatch, nonAsciiIndices) dispatch tables
    */
  def computeDispatch(dfas: IS[S32, LexerDfa]): (IS[S32, IS[S32, S32]], IS[S32, S32]) = {
    val n: S32 = dfas.sizeS32
    val asciiMs = MS.create[S32, IS[S32, S32]](128, IS[S32, S32]())
    var nonAscii = IS[S32, S32]()

    var di: S32 = s32"0"
    while (di < n) {
      val dfa = dfas.atS32(di)
      if (dfa.transitions.nonEmpty) {
        val trans0 = dfa.transitions.atS32(s32"0")
        var hasNonAscii = F
        var ti: S32 = s32"0"
        val trans0Size: S32 = trans0.sizeS32
        while (ti < trans0Size) {
          val t = trans0.atS32(ti)
          if (t.lo <= '\u007F') {
            val startC: C = t.lo
            val endC: C = if (t.hi > '\u007F') '\u007F' else t.hi
            var ci: S32 = conversions.C.toS32(startC)
            val endS32: S32 = conversions.C.toS32(endC)
            while (ci <= endS32) {
              asciiMs(ci) = asciiMs.atS32(ci) :+ di
              ci = ci + s32"1"
            }
          }
          if (t.hi > '\u007F') {
            hasNonAscii = T
          }
          ti = ti + s32"1"
        }
        if (hasNonAscii) {
          nonAscii = nonAscii :+ di
        }
      }
      di = di + s32"1"
    }

    return (asciiMs.toIS, nonAscii)
  }

  /** Constructs LexerDfas from Graph-based automaton DFAs.
    *
    * For each (dfa, name, type, hidden) tuple, converts the Graph-based DFA
    * into a compact LexerDfa by iterating states and filtering out reject
    * self-loops.
    *
    * @param dfaInfos   parallel array of (Dfa, rule name, token type, hidden flag)
    * @param eofTypeOpt optional token type for the synthetic EOF token
    * @return a LexerDfas ready for lexing or serialization
    */
  def fromDfas(dfaInfos: ISZ[(automaton.Dfa[(C, C)], String, Z, B)],
               eofTypeOpt: Option[Z]): LexerDfas = {
    val n = dfaInfos.size
    val nS32: S32 = conversions.Z.toS32(n)
    val defaultDfa = LexerDfa(accepting = BitSet.Ext.fromISB(IS[S32, B]()), transitions = IS[S32, IS[S32, LexerDfa.Transition]]())
    val lexerDfasMs = MS.create[S32, LexerDfa](n, defaultDfa)
    val namesMs = MS.create[S32, String](n, "")
    val typesMs = MS.create[S32, S32](n, s32"0")
    val hiddensMs = MS.create[S32, B](n, F)
    var ii: S32 = s32"0"
    while (ii < nS32) {
      val info = dfaInfos(conversions.S32.toZ(ii))
      val dfa = info._1
      val name = info._2
      val tipe = info._3
      val hidden = info._4
      val numStates = dfa.g.nodesInverse.size

      val acceptingMs = MS.create[S32, B](numStates, F)
      val transitionsMs = MS.create[S32, IS[S32, LexerDfa.Transition]](numStates, IS[S32, LexerDfa.Transition]())
      var si: Z = 0
      while (si < numStates) {
        val siS32 = conversions.Z.toS32(si)
        acceptingMs(siS32) = dfa.accepting.contains(si)
        var trans = IS[S32, LexerDfa.Transition]()
        for (edge <- dfa.g.outgoing(si)) {
          if (!automaton.Dfa.isReject(edge)) {
            val de = edge.asInstanceOf[Graph.Edge.Data[Z, (C, C)]]
            trans = trans :+ LexerDfa.Transition(lo = de.data._1, hi = de.data._2, target = conversions.Z.toS32(de.dest))
          }
        }
        transitionsMs(siS32) = trans
        si = si + 1
      }

      lexerDfasMs(ii) = LexerDfa(accepting = BitSet.Ext.fromISB(acceptingMs.toIS), transitions = transitionsMs.toIS)
      namesMs(ii) = name
      typesMs(ii) = conversions.Z.toS32(tipe)
      hiddensMs(ii) = hidden
      ii = ii + s32"1"
    }

    val eofTypeOptS32: Option[S32] = eofTypeOpt match {
      case Some(v) => Some(conversions.Z.toS32(v))
      case _ => None()
    }

    return create(
      dfas = lexerDfasMs.toIS,
      names = namesMs.toIS,
      types = typesMs.toIS,
      hiddens = BitSet.Ext.fromISB(hiddensMs.toIS),
      eofTypeOpt = eofTypeOptS32
    )
  }

  /** Writes a LexerDfas to a MessagePack writer.
    *
    * Format: numDfas, then per DFA (numStates, accepting bits, transitions
    * with targets as S32 state indices), then names, types, hiddens, eofTypeOpt.
    *
    * @param w   the MessagePack writer
    * @param lds the LexerDfas to serialize
    */
  def writeLexerDfas(w: MessagePack.Writer.Impl, lds: LexerDfas): Unit = {
    val numDfas: S32 = lds.dfas.sizeS32
    w.writeZ(lds.dfas.size)
    var di: S32 = s32"0"
    while (di < numDfas) {
      val d = lds.dfas.atS32(di)
      val numStatesS32: S32 = d.transitions.sizeS32
      w.writeZ(d.transitions.size)
      var si: S32 = s32"0"
      while (si < numStatesS32) {
        w.writeB(d.accepting.isSetS32(si))
        si = si + s32"1"
      }
      si = s32"0"
      while (si < numStatesS32) {
        val trans = d.transitions.atS32(si)
        val transSizeS32: S32 = trans.sizeS32
        w.writeZ(trans.size)
        var ti: S32 = s32"0"
        while (ti < transSizeS32) {
          val t = trans.atS32(ti)
          w.writeC(t.lo)
          w.writeC(t.hi)
          w.writeS32(t.target)
          ti = ti + s32"1"
        }
        si = si + s32"1"
      }
      di = di + s32"1"
    }
    var ni: S32 = s32"0"
    while (ni < numDfas) {
      w.writeString(lds.names.atS32(ni))
      ni = ni + s32"1"
    }
    var ti: S32 = s32"0"
    while (ti < numDfas) {
      w.writeU32(conversions.S32.toU32(lds.types.atS32(ti)))
      ti = ti + s32"1"
    }
    var hi: S32 = s32"0"
    while (hi < numDfas) {
      w.writeB(lds.hiddens.isSetS32(hi))
      hi = hi + s32"1"
    }
    lds.eofTypeOpt match {
      case Some(eofType) =>
        w.writeB(T)
        w.writeU32(conversions.S32.toU32(eofType))
      case _ =>
        w.writeB(F)
    }
  }

  /** Reads a LexerDfas from a MessagePack reader.
    *
    * @param r the MessagePack reader (must have been initialized with init())
    * @return the deserialized LexerDfas
    */
  def readLexerDfas(r: MessagePack.Reader.Impl): LexerDfas = {
    val numDfas = r.readZ()
    val numDfasS32: S32 = conversions.Z.toS32(numDfas)
    val defaultTransition = LexerDfa.Transition(lo = '\u0000', hi = '\u0000', target = s32"0")
    val defaultDfa = LexerDfa(accepting = BitSet.Ext.fromISB(IS[S32, B]()), transitions = IS[S32, IS[S32, LexerDfa.Transition]]())
    val lexerDfasMs = MS.create[S32, LexerDfa](numDfas, defaultDfa)
    var di: S32 = s32"0"
    while (di < numDfasS32) {
      val numStates = r.readZ()
      val numStatesS32 = conversions.Z.toS32(numStates)

      val acceptingMs = MS.create[S32, B](numStates, F)
      var si: S32 = s32"0"
      while (si < numStatesS32) {
        acceptingMs(si) = r.readB()
        si = si + s32"1"
      }

      val transitionsMs = MS.create[S32, IS[S32, LexerDfa.Transition]](numStates, IS[S32, LexerDfa.Transition]())
      si = s32"0"
      while (si < numStatesS32) {
        val numTrans = r.readZ()
        val numTransS32 = conversions.Z.toS32(numTrans)
        val transMs = MS.create[S32, LexerDfa.Transition](numTrans, defaultTransition)
        var ti: S32 = s32"0"
        while (ti < numTransS32) {
          transMs(ti) = LexerDfa.Transition(lo = r.readC(), hi = r.readC(), target = r.readS32())
          ti = ti + s32"1"
        }
        transitionsMs(si) = transMs.toIS
        si = si + s32"1"
      }

      lexerDfasMs(di) = LexerDfa(accepting = BitSet.Ext.fromISB(acceptingMs.toIS), transitions = transitionsMs.toIS)
      di = di + s32"1"
    }

    val namesMs = MS.create[S32, String](numDfas, "")
    var ni: S32 = s32"0"
    while (ni < numDfasS32) {
      namesMs(ni) = r.readString()
      ni = ni + s32"1"
    }

    val typesMs = MS.create[S32, S32](numDfas, s32"0")
    var ui: S32 = s32"0"
    while (ui < numDfasS32) {
      typesMs(ui) = conversions.U32.toS32(r.readU32())
      ui = ui + s32"1"
    }

    val hiddensMs = MS.create[S32, B](numDfas, F)
    var hi: S32 = s32"0"
    while (hi < numDfasS32) {
      hiddensMs(hi) = r.readB()
      hi = hi + s32"1"
    }

    val hasEof = r.readB()
    val eofTypeOpt: Option[S32] = if (hasEof) Some(conversions.U32.toS32(r.readU32())) else None()

    return create(
      dfas = lexerDfasMs.toIS,
      names = namesMs.toIS,
      types = typesMs.toIS,
      hiddens = BitSet.Ext.fromISB(hiddensMs.toIS),
      eofTypeOpt = eofTypeOpt
    )
  }

  /** Deserializes a LexerDfas from a compact base64-encoded string.
    *
    * Reverses the toCompact encoding: base64 decode, lz4 decompress,
    * MessagePack deserialize.
    *
    * @param s the compact string produced by toCompact
    * @return the deserialized LexerDfas
    */
  def fromCompact(s: String): LexerDfas = {
    val data = ops.ISZOps.lz4Decompress(ops.StringOps.fromBase64(s).left).left
    val r = MessagePack.reader(data)
    r.init()
    return readLexerDfas(r)
  }
}

/** A collection of DFAs with execution and serialization support.
  *
  * All parallel arrays: dfas(i) has name names(i), token type types(i),
  * hidden flag hiddens(i).
  *
  * @param dfas            the individual DFAs
  * @param names           rule name for each DFA
  * @param types           token type ID for each DFA
  * @param hiddens         hidden flag for each DFA
  * @param eofTypeOpt      optional token type for the synthetic EOF token
  * @param asciiDispatch   for each ASCII char (0-127), the DFA indices whose state 0 covers it
  * @param nonAsciiIndices DFA indices whose state 0 has any non-ASCII transition
  */
@datatype class LexerDfas(
  val dfas: IS[S32, LexerDfa],
  val names: IS[S32, String],
  val types: IS[S32, S32],
  val hiddens: BitSet,
  val eofTypeOpt: Option[S32],
  val asciiDispatch: IS[S32, IS[S32, S32]],
  val nonAsciiIndices: IS[S32, S32]
) {

  /** Executes a single DFA from position i in the character stream.
    *
    * Uses longest-match semantics: tracks the furthest accepting position
    * seen during the run and returns it. Returns -1 if the DFA never reaches
    * an accepting state.
    *
    * @param dfa   the DFA to execute
    * @param chars the input character stream
    * @param i     the starting position
    * @return the position after the last accepted character, or -1 if no match
    */
  def runDfa(dfa: LexerDfa, chars: Indexable.PosC, i: S32): S32 = {
    if (dfa.transitions.isEmpty) {
      return s32"-1"
    }
    var state: S32 = s32"0"
    var pos: S32 = i
    var lastAccept: S32 = s32"-1"
    if (dfa.accepting.isSetS32(state)) {
      lastAccept = pos
    }
    while (chars.hasS32(pos)) {
      val c = chars.atS32(pos)
      val trans = dfa.transitions.atS32(state)
      var found = F
      var ti: S32 = s32"0"
      val transSize: S32 = trans.sizeS32
      while (!found && ti < transSize) {
        val t = trans.atS32(ti)
        if (t.lo <= c && c <= t.hi) {
          state = t.target
          pos = pos + s32"1"
          found = T
        }
        ti = ti + s32"1"
      }
      if (!found) {
        return lastAccept
      }
      if (dfa.accepting.isSetS32(state)) {
        lastAccept = pos
      }
    }
    return lastAccept
  }

  /** Tries candidate DFAs at position i and picks the longest match.
    *
    * Uses first-character dispatch to only run DFAs whose state 0 has a
    * transition for the character at position i. For ASCII input this
    * typically reduces the number of DFA runs from N to 1-2.
    *
    * On success, returns the matched Token and advances `i.value` to the
    * position after the match. On failure (no match or only empty match),
    * returns the sentinel token (`sentinelToken`) and leaves `i.value` unchanged.
    *
    * @param chars the input character stream
    * @param i     mutable box holding the starting position; updated on success
    * @return the matched Token, or `sentinelToken` if no match
    */
  def lex(chars: Indexable.PosC, i: MBox[S32]): Token = {
    var bestEnd: S32 = s32"-1"
    var bestIdx: S32 = s32"-1"
    val start: S32 = i.value
    val c = chars.atS32(start)
    val candidates: IS[S32, S32] = if (c <= '\u007F') {
      asciiDispatch.atS32(conversions.C.toS32(c))
    } else {
      nonAsciiIndices
    }
    var ki: S32 = s32"0"
    val candidatesSize: S32 = candidates.sizeS32
    while (ki < candidatesSize) {
      val di = candidates.atS32(ki)
      val end = runDfa(dfas.atS32(di), chars, start)
      if (end > bestEnd) {
        bestEnd = end
        bestIdx = di
      }
      ki = ki + s32"1"
    }
    if (bestEnd <= start) {
      return LexerDfas.sentinelToken
    }
    i.value = bestEnd
    val leaf = ParseTree.Leaf(
      text = chars.substringS32(start, bestEnd),
      ruleName = names.atS32(bestIdx),
      tipe = types.atS32(bestIdx),
      isHidden = hiddens.isSetS32(bestIdx),
      posOpt = chars.posOptS32(start, bestEnd - start)
    )
    return leaf
  }

  /** Tokenizes the full input stream.
    *
    * Calls lex repeatedly until the input is consumed or a lexing error occurs.
    * Appends a synthetic EOF token if eofTypeOpt is set.
    *
    * @param chars      the input character stream
    * @param skipHidden if true, hidden tokens are not included in the result
    * @return (errorIndex, tokens) where errorIndex is -1 on success,
    *         or the position of the first unlexable character on failure
    */
  def tokens(chars: Indexable.PosC, skipHidden: B): (S32, IS[S32, Token]) = {
    val defaultToken: Token = ParseTree.Leaf(text = "", ruleName = "", tipe = s32"0", isHidden = F, posOpt = None())
    var buf: MIStack[Token] = MIStack.create[Token](defaultToken, s32"256", s32"2")
    val iBox: MBox[S32] = MBox(s32"0")
    while (chars.hasS32(iBox.value)) {
      val token = lex(chars, iBox)
      if (token.num == s32"-1") {
        return (iBox.value, buf.toIS)
      }
      if (!skipHidden || !token.isHidden) {
        buf.push(token.toLeaf)
      }
    }
    eofTypeOpt match {
      case Some(eofType) =>
        buf.push(ParseTree.Leaf(
          text = "",
          ruleName = "EOF",
          tipe = eofType,
          isHidden = F,
          posOpt = chars.posOptS32(iBox.value, s32"0")
        ))
      case _ =>
    }
    return (s32"-1", buf.toIS)
  }

  /** Serializes this LexerDfas to a compact base64-encoded string.
    *
    * Encoding: MessagePack serialize, lz4 compress, base64 encode.
    *
    * @return the compact string representation
    */
  def toCompact: String = {
    val w = MessagePack.writer(T)
    LexerDfas.writeLexerDfas(w, this)
    return ops.StringOps.toBase64(ops.ISZOps.lz4Compress(w.result))
  }

  /** Generates a Slang expression that deserializes this LexerDfas from its
    * compact representation.
    *
    * @return an ST whose render produces a fromCompact call with the encoded data
    */
  def toCompactST: ST = {
    return st"""LexerDfas.fromCompact("${toCompact}")"""
  }

  /** Generates a human-readable representation of all DFAs.
    *
    * Shows each DFA's states, accepting flags, and transitions with
    * character ranges and target state indices.
    *
    * @return an ST describing the LexerDfas structure
    */
  def toST: ST = {
    var dfaSTs = IS[S32, ST]()
    var di: S32 = s32"0"
    val numDfas: S32 = dfas.sizeS32
    while (di < numDfas) {
      val d = dfas.atS32(di)
      var transSTs = IS[S32, ST]()
      var si: S32 = s32"0"
      val numStates: S32 = d.transitions.sizeS32
      while (si < numStates) {
        val trans = d.transitions.atS32(si)
        val transItemSTs: IS[S32, ST] = for (t <- trans) yield
          st"(${ops.COps(t.lo).escapeString} .. ${ops.COps(t.hi).escapeString} -> ${t.target})"
        val acceptST: ST = if (d.accepting.isSetS32(si)) st"*" else st""
        transSTs = transSTs :+ st"$si$acceptST: ${(transItemSTs, ", ")}"
        si = si + s32"1"
      }
      dfaSTs = dfaSTs :+
        st"""DFA ${names.atS32(di)} (type=${types.atS32(di)}, hidden=${hiddens.isSetS32(di)}):
            |  ${(transSTs, "\n")}"""
      di = di + s32"1"
    }
    val eofST: ST = eofTypeOpt match {
      case Some(t) => st"EOF type: $t"
      case _ => st"No EOF"
    }
    return st"""LexerDfas:
               |  $eofST
               |  ${(dfaSTs, "\n\n")}"""
  }
}
