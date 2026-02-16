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
  * @param nameMap maps rule and token names to unique Z identifiers
  * @param rules   array-indexed from rule Z ID to its root [[PredictiveNode]] (sentinel for unused slots)
  */
@datatype class PredictiveTable(val k: Z,
                                val nameMap: HashSMap[String, Z],
                                val rules: ISZ[PredictiveNode]) {
  /** Predicts which alternative to use for the given rule and lookahead token IDs.
    *
    * The `tokens` sequence must have at most `k` elements. Passing more than `k`
    * tokens is a programming error and will trigger a halt. Passing fewer than `k`
    * tokens is allowed and returns `None` if the available tokens are insufficient
    * to reach a decision.
    *
    * @param rule   the rule's Z ID (from `nameMap`)
    * @param tokens the lookahead token ID sequence (at most k tokens, each from `nameMap`)
    * @return `Some(altIndex)` as a Z if the tokens uniquely identify an alternative,
    *         or `None` if the rule is not found, the tokens are insufficient,
    *         or no matching entry is found.
    */
  def predict(rule: Z, tokens: ISZ[Z]): Option[Z] = {
    assert(tokens.size <= k, s"Expected at most $k lookahead tokens, but got ${tokens.size}")
    val node = rules(rule)
    if (node.isSentinel) {
      return None()
    }
    return node.predict(tokens, 0)
  }

  @memoize def reverseNameMap: HashSMap[Z, String] = {
    var r = HashSMap.empty[Z, String]
    for (p <- nameMap.entries) {
      r = r + p._2 ~> p._1
    }
    return r
  }

  /** Generates a Slang expression that programmatically constructs this [[PredictiveTable]].
    *
    * The rendered ST produces a self-contained Slang expression of type `PredictiveTable`.
    * The caller's scope must import `org.sireum._` and `org.sireum.parser._`.
    *
    * @return an ST whose `render` produces the Slang construction expression
    */
  @strictpure def toST: ST = {
    val nameMapST: ST = if (nameMap.isEmpty) {
      st"HashSMap.empty[String, Z]"
    } else {
      val entries: ISZ[ST] = for (e <- nameMap.entries) yield
        st""""${e._1}" ~> ${e._2}"""
      st"""HashSMap.empty[String, Z] +
          |  ${(entries, " +\n")}"""
    }
    val ruleEntries: ISZ[ST] = for (i <- z"0" until rules.size) yield
      if (rules(i).isSentinel) st"PredictiveNode.sentinel"
      else rules(i).toST
    val rulesST: ST = st"""ISZ[PredictiveNode](${(ruleEntries, ",\n")})"""
    st"""PredictiveTable(
        |  $k,
        |  $nameMapST,
        |  $rulesST)"""
  }

  def toCompact: String = {
    val w = MessagePack.writer(T)
    PredictiveTable.writePredictiveTable(w, this)
    return ops.StringOps.toBase64(ops.ISZOps.lz4Compress(w.result))
  }

  def toCompactST: ST = {
    return st"""PredictiveTable.fromCompact("${toCompact}")"""
  }
}

object PredictiveTable {

  def fromCompact(s: String): PredictiveTable = {
    val data = ops.ISZOps.lz4Decompress(ops.StringOps.fromBase64(s).left).left
    val r = MessagePack.reader(data)
    r.init()
    return readPredictiveTable(r)
  }

  def writePredictiveNode(w: MessagePack.Writer.Impl, node: PredictiveNode): Unit = {
    node match {
      case leaf: PredictiveNode.Leaf =>
        w.writeZ(0)
        w.writeU32(conversions.Z.toU32(leaf.alt))
      case branch: PredictiveNode.Branch =>
        w.writeZ(1)
        branch.defaultOpt match {
          case Some(defaultNode) =>
            // Count entries that differ from the default
            var count: Z = 0
            var i: Z = 0
            while (i < branch.entries.size) {
              val e = branch.entries(i)
              if (!e.isSentinel && e != defaultNode) {
                count = count + 1
              }
              i = i + 1
            }
            w.writeZ(count)
            i = 0
            while (i < branch.entries.size) {
              val e = branch.entries(i)
              if (!e.isSentinel && e != defaultNode) {
                w.writeU32(conversions.Z.toU32(i))
                writePredictiveNode(w, e)
              }
              i = i + 1
            }
            w.writeB(T)
            writePredictiveNode(w, defaultNode)
          case _ =>
            // No default — write all non-sentinel entries
            var count: Z = 0
            var i: Z = 0
            while (i < branch.entries.size) {
              if (!branch.entries(i).isSentinel) {
                count = count + 1
              }
              i = i + 1
            }
            w.writeZ(count)
            i = 0
            while (i < branch.entries.size) {
              val e = branch.entries(i)
              if (!e.isSentinel) {
                w.writeU32(conversions.Z.toU32(i))
                writePredictiveNode(w, e)
              }
              i = i + 1
            }
            w.writeB(F)
        }
    }
  }

  def readPredictiveNode(r: MessagePack.Reader.Impl, numNames: Z): PredictiveNode = {
    val tag = r.readZ()
    if (tag == 0) {
      val alt = conversions.U32.toZ(r.readU32())
      return PredictiveNode.Leaf(alt)
    } else {
      val size = r.readZ()
      var ms = MSZ.create[PredictiveNode](numNames, PredictiveNode.sentinel)
      var i: Z = 0
      while (i < size) {
        val key = conversions.U32.toZ(r.readU32())
        val node = readPredictiveNode(r, numNames)
        ms(key) = node
        i = i + 1
      }
      val hasDefault = r.readB()
      var defaultOpt: Option[PredictiveNode] = None()
      if (hasDefault) {
        val defaultNode = readPredictiveNode(r, numNames)
        var j: Z = 0
        while (j < numNames) {
          if (ms(j).isSentinel) {
            ms(j) = defaultNode
          }
          j = j + 1
        }
        defaultOpt = Some(defaultNode)
      }
      return PredictiveNode.Branch(entries = ms.toIS, defaultOpt = defaultOpt)
    }
  }

  def writePredictiveTable(w: MessagePack.Writer.Impl, pt: PredictiveTable): Unit = {
    w.writeZ(pt.k)
    w.writeZ(pt.nameMap.size)
    for (e <- pt.nameMap.entries) {
      w.writeString(e._1)
      w.writeU32(conversions.Z.toU32(e._2))
    }
    var count: Z = 0
    var i: Z = 0
    while (i < pt.rules.size) {
      if (!pt.rules(i).isSentinel) {
        count = count + 1
      }
      i = i + 1
    }
    w.writeZ(count)
    i = 0
    while (i < pt.rules.size) {
      val r = pt.rules(i)
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
    var nameMap = HashSMap.empty[String, Z]
    var i: Z = 0
    while (i < nameMapSize) {
      val key = r.readString()
      val value = conversions.U32.toZ(r.readU32())
      nameMap = nameMap + key ~> value
      i = i + 1
    }
    val numNames = nameMap.size
    val entryCount = r.readZ()
    var maxKey: Z = -1
    var entries = ISZ[(Z, PredictiveNode)]()
    i = 0
    while (i < entryCount) {
      val key = conversions.U32.toZ(r.readU32())
      val node = readPredictiveNode(r, numNames)
      entries = entries :+ ((key, node))
      if (key > maxKey) {
        maxKey = key
      }
      i = i + 1
    }
    var rules = MSZ.create[PredictiveNode](maxKey + 1, PredictiveNode.sentinel)
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
    * @return a compact trie-based table with Z-keyed lookup
    */
  def build(k: Z, table: HashSMap[String, HashSMap[ISZ[String], Z]]): PredictiveTable = {
    var nameMap = HashSMap.empty[String, Z]
    var nextId: Z = 0
    for (entry <- table.entries) {
      if (!nameMap.contains(entry._1)) {
        nameMap = nameMap + entry._1 ~> nextId
        nextId = nextId + 1
      }
      for (cell <- entry._2.entries) {
        for (token <- cell._1) {
          if (!nameMap.contains(token)) {
            nameMap = nameMap + token ~> nextId
            nextId = nextId + 1
          }
        }
      }
    }
    val numNames = nextId
    val rulesMS = MSZ.create[PredictiveNode](nameMap.size, PredictiveNode.sentinel)
    for (entry <- table.entries) {
      val ruleId = nameMap.get(entry._1).get
      val idEntries: ISZ[(ISZ[Z], Z)] = for (cell <- entry._2.entries) yield
        (for (token <- cell._1) yield nameMap.get(token).get, cell._2)
      rulesMS(ruleId) = buildNode(idEntries, 0, numNames)
    }
    return PredictiveTable(k, nameMap, rulesMS.toIS)
  }

  def buildNode(entries: ISZ[(ISZ[Z], Z)], depth: Z, numNames: Z): PredictiveNode = {
    if (entries.isEmpty) {
      return PredictiveNode.Branch(
        entries = ISZ.create[PredictiveNode](numNames, PredictiveNode.sentinel),
        defaultOpt = None())
    }
    if (entries.size <= 1 && depth >= entries(0)._1.size) {
      return PredictiveNode.Leaf(entries(0)._2)
    }
    var groups = HashSMap.empty[Z, ISZ[(ISZ[Z], Z)]]
    for (e <- entries) {
      if (depth < e._1.size) {
        val tokenId = e._1(depth)
        val existing: ISZ[(ISZ[Z], Z)] = groups.get(tokenId) match {
          case Some(v) => v
          case _ => ISZ()
        }
        groups = groups + tokenId ~> (existing :+ e)
      }
    }
    var ms = MSZ.create[PredictiveNode](numNames, PredictiveNode.sentinel)
    for (g <- groups.entries) {
      ms(g._1) = buildNode(g._2, depth + 1, numNames)
    }
    var altCounts = HashSMap.empty[Z, Z]
    for (g <- groups.entries) {
      ms(g._1) match {
        case leaf: PredictiveNode.Leaf =>
          val count: Z = altCounts.get(leaf.alt) match {
            case Some(n) => n
            case _ => 0
          }
          altCounts = altCounts + leaf.alt ~> (count + 1)
        case _ =>
      }
    }
    var maxAltOpt: Option[Z] = None()
    var maxCount: Z = 0
    for (ac <- altCounts.entries) {
      if (ac._2 > maxCount) {
        maxCount = ac._2
        maxAltOpt = Some(ac._1)
      }
    }
    if (maxCount > 1) {
      val maxAlt = maxAltOpt.get
      var allDefault: B = T
      for (g <- groups.entries) {
        ms(g._1) match {
          case leaf: PredictiveNode.Leaf =>
            if (leaf.alt != maxAlt) {
              allDefault = F
            }
          case _ => allDefault = F
        }
      }
      if (allDefault) {
        return PredictiveNode.Branch(entries = ms.toIS, defaultOpt = None())
      } else {
        val defaultNode = PredictiveNode.Leaf(maxAlt)
        var i: Z = 0
        while (i < numNames) {
          if (ms(i).isSentinel) {
            ms(i) = defaultNode
          }
          i = i + 1
        }
        return PredictiveNode.Branch(entries = ms.toIS, defaultOpt = Some(defaultNode))
      }
    } else {
      return PredictiveNode.Branch(entries = ms.toIS, defaultOpt = None())
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
  def predict(tokens: ISZ[Z], i: Z): Option[Z]

  /** Returns `T` if this node is the sentinel (unused slot marker). */
  @strictpure def isSentinel: B

  @strictpure def toST: ST
}

object PredictiveNode {
  val sentinel: PredictiveNode = Leaf(-1)

  /** A terminal decision node that always returns the resolved alternative.
    *
    * @param alt the 0-based alternative index into the rule's alternatives
    */
  @datatype class Leaf(val alt: Z) extends PredictiveNode {
    override def predict(tokens: ISZ[Z], i: Z): Option[Z] = {
      return Some(alt)
    }

    @strictpure override def isSentinel: B = alt == -1

    @strictpure override def toST: ST = st"""PredictiveNode.Leaf($alt)"""
  }

  /** A branching decision node that inspects the next lookahead token ID.
    *
    * Uses a flat array indexed by token ID for O(1) lookup. Sentinel entries
    * mark token IDs that are not expected at this branch level. The `defaultOpt`
    * field is used only for serialization (to compress repeated entries).
    *
    * @param entries    flat array indexed by token ID; sentinel marks unused slots
    * @param defaultOpt the default node used during serialization compression
    */
  @datatype class Branch(val entries: ISZ[PredictiveNode],
                         val defaultOpt: Option[PredictiveNode]) extends PredictiveNode {
    override def predict(tokens: ISZ[Z], i: Z): Option[Z] = {
      if (i >= tokens.size) {
        return None()
      }
      val tNum = tokens(i)
      if (tNum >= entries.size) {
        return None()
      }
      val child = entries(tNum)
      if (child.isSentinel) {
        return None()
      }
      return child.predict(tokens, i + 1)
    }

    @strictpure override def isSentinel: B = F

    @strictpure override def toST: ST = {
      val es: ISZ[ST] = for (i <- z"0" until entries.size) yield
        if (entries(i).isSentinel) st"PredictiveNode.sentinel"
        else entries(i).toST
      val defaultST: ST = defaultOpt match {
        case Some(d) => st"Some(${d.toST})"
        case _ => st"None()"
      }
      st"""PredictiveNode.Branch(
          |  ISZ[PredictiveNode](${(es, ",\n")}),
          |  $defaultST)"""
    }
  }
}
