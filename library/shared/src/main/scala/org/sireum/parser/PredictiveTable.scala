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

/** A compact predictive parsing table stored as a trie with default-based compression.
  *
  * Converts the sparse flat parsing table (which enumerates every k-token lookahead
  * combination) into a trie where each level branches on one lookahead token.
  * When most branches at a level resolve to the same alternative, that alternative
  * is stored as a default and only exceptions are kept, dramatically reducing size.
  *
  * All rule and token names are mapped to unique integer identifiers via `nameMap`
  * for fast integer-keyed lookup throughout the trie.
  *
  * Usage example:
  * {{{
  * grammar.computePredictiveTableOpt match {
  *   case Some(table) =>
  *     val nm = table.nameMap
  *     val atom = nm.get("atom").get
  *     val char = nm.get("CHAR").get
  *     val dotdot = nm.get("..").get
  *     val pid = nm.get("PID").get
  *     table.predict(atom, ISZ(char, dotdot))  // Some(0) — selects 'range'
  *     table.predict(atom, ISZ(char, pid))     // Some(1) — selects 'terminal'
  *   case _ => // grammar is not LL(k)
  * }
  * }}}
  *
  * @param k       the lookahead depth this table was built for
  * @param nameMap maps rule and token names to unique U32 identifiers
  * @param rules   array-indexed from rule U32 ID to its root [[PredictiveNode]] (sentinel for unused slots)
  */
@datatype class PredictiveTable(val k: Z,
                                val nameMap: HashSMap[String, U32],
                                val rules: IS[U32, PredictiveNode]) {
  /** Predicts which alternative to use for the given rule and lookahead token IDs.
    *
    * The `tokens` sequence must have at most `k` elements. Passing more than `k`
    * tokens is a programming error and will trigger a halt. Passing fewer than `k`
    * tokens is allowed and returns `None` if the available tokens are insufficient
    * to reach a decision.
    *
    * @param rule   the rule's U32 ID (from `nameMap`)
    * @param tokens the lookahead token ID sequence (at most k tokens, each from `nameMap`)
    * @return `Some(altIndex)` as a Z if the tokens uniquely identify an alternative,
    *         or `None` if the rule is not found, the tokens are insufficient,
    *         or no matching entry is found.
    */
  def predict(rule: U32, tokens: ISZ[U32]): Option[Z] = {
    assert(tokens.size <= k, s"Expected at most $k lookahead tokens, but got ${tokens.size}")
    val node = rules(rule)
    if (node.isSentinel) {
      return None()
    }
    return node.predict(tokens, 0)
  }

  @memoize def reverseNameMap: HashSMap[U32, String] = {
    var r = HashSMap.empty[U32, String]
    for (p <- nameMap.entries) {
      r = r + p._2 ~> p._1
    }
    return r
  }

  /** Generates a Slang expression that programmatically constructs this [[PredictiveTable]].
    *
    * The rendered ST produces a self-contained Slang expression of type `PredictiveTable`.
    * The caller's scope must import `org.sireum._`, `org.sireum.U32._`, and
    * `org.sireum.parser._`.
    *
    * @return an ST whose `render` produces the Slang construction expression
    */
  @strictpure def toST: ST = {
    val nameMapST: ST = if (nameMap.isEmpty) {
      st"HashSMap.empty[String, U32]"
    } else {
      val entries: ISZ[ST] = for (e <- nameMap.entries) yield
        st""""${e._1}" ~> u32"${conversions.U32.toZ(e._2)}""""
      st"""HashSMap.empty[String, U32] +
          |  ${(entries, " +\n")}"""
    }
    val ruleEntries: ISZ[ST] = for (i <- u32"0" until conversions.Z.toU32(rules.size)) yield
      if (rules(i).isSentinel) st"PredictiveNode.sentinel"
      else rules(i).toST
    val rulesST: ST = st"""IS[U32, PredictiveNode](${(ruleEntries, ",\n")})"""
    st"""PredictiveTable(
        |  $k,
        |  $nameMapST,
        |  $rulesST)"""
  }

  def toCompact: String = {
    val w = MessagePack.writer(T)
    PredictiveTable.writePredictiveTable(w, this)
    return ops.StringOps.toBase64(w.result)
  }

  def toCompactST: ST = {
    return st"""PredictiveTable.fromCompact("${toCompact}")"""
  }
}

object PredictiveTable {

  def fromCompact(s: String): PredictiveTable = {
    val data = ops.StringOps.fromBase64(s).left
    val r = MessagePack.reader(data)
    r.init()
    return readPredictiveTable(r)
  }

  def writePredictiveNode(w: MessagePack.Writer.Impl, node: PredictiveNode): Unit = {
    node match {
      case leaf: PredictiveNode.Leaf =>
        w.writeZ(0)
        w.writeU32(leaf.alt)
      case branch: PredictiveNode.Branch =>
        w.writeZ(1)
        w.writeZ(branch.entries.size)
        for (e <- branch.entries.entries) {
          w.writeU32(e._1)
          writePredictiveNode(w, e._2)
        }
        branch.defaultOpt match {
          case Some(d) =>
            w.writeB(T)
            writePredictiveNode(w, d)
          case _ =>
            w.writeB(F)
        }
    }
  }

  def readPredictiveNode(r: MessagePack.Reader.Impl): PredictiveNode = {
    val tag = r.readZ()
    if (tag == 0) {
      val alt = r.readU32()
      return PredictiveNode.Leaf(alt)
    } else {
      val size = r.readZ()
      var entries = HashSMap.empty[U32, PredictiveNode]
      var i: Z = 0
      while (i < size) {
        val key = r.readU32()
        val node = readPredictiveNode(r)
        entries = entries + key ~> node
        i = i + 1
      }
      val hasDefault = r.readB()
      val defaultOpt: Option[PredictiveNode] = if (hasDefault) Some(readPredictiveNode(r)) else None()
      return PredictiveNode.Branch(entries, defaultOpt)
    }
  }

  def writePredictiveTable(w: MessagePack.Writer.Impl, pt: PredictiveTable): Unit = {
    w.writeZ(pt.k)
    w.writeZ(pt.nameMap.size)
    for (e <- pt.nameMap.entries) {
      w.writeString(e._1)
      w.writeU32(e._2)
    }
    var count: Z = 0
    var i: Z = 0
    while (i < pt.rules.size) {
      if (!pt.rules(conversions.Z.toU32(i)).isSentinel) {
        count = count + 1
      }
      i = i + 1
    }
    w.writeZ(count)
    i = 0
    while (i < pt.rules.size) {
      val r = pt.rules(conversions.Z.toU32(i))
      if (!r.isSentinel) {
        w.writeU32(conversions.Z.toU32(i))
        writePredictiveNode(w, r)
      }
      i = i + 1
    }
  }

  def readPredictiveTable(r: MessagePack.Reader.Impl): PredictiveTable = {
    val k = r.readZ()
    val nameMapSize = r.readZ()
    var nameMap = HashSMap.empty[String, U32]
    var i: Z = 0
    while (i < nameMapSize) {
      val key = r.readString()
      val value = r.readU32()
      nameMap = nameMap + key ~> value
      i = i + 1
    }
    val entryCount = r.readZ()
    var maxKey: Z = -1
    var entries = ISZ[(U32, PredictiveNode)]()
    i = 0
    while (i < entryCount) {
      val key = r.readU32()
      val node = readPredictiveNode(r)
      entries = entries :+ ((key, node))
      val keyZ = conversions.U32.toZ(key)
      if (keyZ > maxKey) {
        maxKey = keyZ
      }
      i = i + 1
    }
    var rules = MS.create[U32, PredictiveNode](maxKey + 1, PredictiveNode.sentinel)
    for (e <- entries) {
      rules(e._1) = e._2
    }
    return PredictiveTable(k = k, nameMap = nameMap, rules = rules.toIS)
  }

  /** Builds a [[PredictiveTable]] from the flat parsing table produced by
    * [[GrammarAst.Grammar.computeParsingTableOpt]].
    *
    * @param k     the lookahead depth the table was computed with
    * @param table the flat parsing table mapping rule names to lookahead-to-alt maps
    * @return a compact trie-based table with U32-keyed lookup
    */
  def build(k: Z, table: HashSMap[String, HashSMap[ISZ[String], Z]]): PredictiveTable = {
    var nameMap = HashSMap.empty[String, U32]
    var nextId: U32 = u32"0"
    for (entry <- table.entries) {
      if (!nameMap.contains(entry._1)) {
        nameMap = nameMap + entry._1 ~> nextId
        nextId = nextId + u32"1"
      }
      for (cell <- entry._2.entries) {
        for (token <- cell._1) {
          if (!nameMap.contains(token)) {
            nameMap = nameMap + token ~> nextId
            nextId = nextId + u32"1"
          }
        }
      }
    }
    var rulesMS = MS.create[U32, PredictiveNode](nameMap.size, PredictiveNode.sentinel)
    for (entry <- table.entries) {
      val ruleId = nameMap.get(entry._1).get
      val idEntries: ISZ[(ISZ[U32], Z)] = for (cell <- entry._2.entries) yield
        (for (token <- cell._1) yield nameMap.get(token).get, cell._2)
      rulesMS(ruleId) = buildNode(idEntries, 0)
    }
    return PredictiveTable(k, nameMap, rulesMS.toIS)
  }

  def buildNode(entries: ISZ[(ISZ[U32], Z)], depth: Z): PredictiveNode = {
    if (entries.isEmpty) {
      return PredictiveNode.Branch(HashSMap.empty, None())
    }
    if (entries.size <= 1 && depth >= entries(0)._1.size) {
      return PredictiveNode.Leaf(conversions.Z.toU32(entries(0)._2))
    }
    var groups = HashSMap.empty[U32, ISZ[(ISZ[U32], Z)]]
    for (e <- entries) {
      if (depth < e._1.size) {
        val tokenId = e._1(depth)
        val existing: ISZ[(ISZ[U32], Z)] = groups.get(tokenId) match {
          case Some(v) => v
          case _ => ISZ()
        }
        groups = groups + tokenId ~> (existing :+ e)
      }
    }
    var children = HashSMap.empty[U32, PredictiveNode]
    for (g <- groups.entries) {
      children = children + g._1 ~> buildNode(g._2, depth + 1)
    }
    var altCounts = HashSMap.empty[U32, Z]
    for (c <- children.entries) {
      c._2 match {
        case leaf: PredictiveNode.Leaf =>
          val count: Z = altCounts.get(leaf.alt) match {
            case Some(n) => n
            case _ => 0
          }
          altCounts = altCounts + leaf.alt ~> (count + 1)
        case _ =>
      }
    }
    var maxAltOpt: Option[U32] = None()
    var maxCount: Z = 0
    for (ac <- altCounts.entries) {
      if (ac._2 > maxCount) {
        maxCount = ac._2
        maxAltOpt = Some(ac._1)
      }
    }
    if (maxCount > 1) {
      val maxAlt = maxAltOpt.get
      var filtered = HashSMap.empty[U32, PredictiveNode]
      for (c <- children.entries) {
        var isDefault: B = F
        c._2 match {
          case leaf: PredictiveNode.Leaf =>
            if (leaf.alt == maxAlt) {
              isDefault = T
            }
          case _ =>
        }
        if (!isDefault) {
          filtered = filtered + c._1 ~> c._2
        }
      }
      if (filtered.isEmpty) {
        return PredictiveNode.Branch(children, None())
      }
      return PredictiveNode.Branch(filtered, Some(PredictiveNode.Leaf(maxAlt)))
    } else {
      return PredictiveNode.Branch(children, None())
    }
  }
}

/** A node in the predictive parsing trie.
  *
  * Each node represents a decision point during LL(k) prediction.
  * A [[PredictiveNode.Leaf]] terminates the decision with a resolved alternative,
  * while a [[PredictiveNode.Branch]] inspects the next lookahead token to continue.
  */
@datatype trait PredictiveNode {
  /** Predicts the alternative index by consuming lookahead token IDs starting at position `i`.
    *
    * @param tokens the full lookahead token ID sequence (each ID from [[PredictiveTable.nameMap]])
    * @param i      the current position in the token sequence to inspect
    * @return `Some(altIndex)` if prediction succeeds, or `None` if the tokens
    *         are exhausted before reaching a decision or no matching entry is found.
    */
  def predict(tokens: ISZ[U32], i: Z): Option[Z]

  /** Returns `T` if this node is the sentinel (unused slot marker). */
  @strictpure def isSentinel: B

  @strictpure def toST: ST
}

object PredictiveNode {
  val sentinel: PredictiveNode = Leaf(U32.Max)

  /** A terminal decision node that always returns the resolved alternative.
    *
    * @param alt the 0-based alternative index into the rule's alternatives
    */
  @datatype class Leaf(val alt: U32) extends PredictiveNode {
    override def predict(tokens: ISZ[U32], i: Z): Option[Z] = {
      return Some(conversions.U32.toZ(alt))
    }

    @strictpure override def isSentinel: B = alt == U32.Max

    @strictpure override def toST: ST = st"""PredictiveNode.Leaf(u32"${conversions.U32.toZ(alt)}")"""
  }

  /** A branching decision node that inspects the next lookahead token ID.
    *
    * Looks up `tokens(i)` in `entries`; if not found, falls back to `defaultOpt`.
    * The default captures the most common alternative at this level, so only
    * exceptional token IDs need explicit entries.
    *
    * @param entries    explicit token-ID-to-node mappings for non-default alternatives
    * @param defaultOpt fallback node used when the token ID is not in `entries`
    */
  @datatype class Branch(val entries: HashSMap[U32, PredictiveNode],
                         val defaultOpt: Option[PredictiveNode]) extends PredictiveNode {
    override def predict(tokens: ISZ[U32], i: Z): Option[Z] = {
      if (i >= tokens.size) {
        return None()
      }
      entries.get(tokens(i)) match {
        case Some(node) => return node.predict(tokens, i + 1)
        case _ =>
          defaultOpt match {
            case Some(node) => return node.predict(tokens, i + 1)
            case _ => return None()
          }
      }
    }

    @strictpure override def isSentinel: B = F

    @strictpure override def toST: ST = {
      val entriesST: ST = if (entries.isEmpty) {
        st"HashSMap.empty[U32, PredictiveNode]"
      } else {
        val es: ISZ[ST] = for (e <- entries.entries) yield
          st"""u32"${conversions.U32.toZ(e._1)}" ~> ${e._2.toST}"""
        st"""HashSMap.empty[U32, PredictiveNode] +
            |  ${(es, " +\n")}"""
      }
      val defaultST: ST = defaultOpt match {
        case Some(d) => st"Some(${d.toST})"
        case _ => st"None()"
      }
      st"""PredictiveNode.Branch(
          |  $entriesST,
          |  $defaultST)"""
    }
  }
}
