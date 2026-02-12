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

import org.sireum.automaton._
import org.sireum.test._

class DfaTest extends TestSuite {

  // Groups transitions by label equality (discrete split for String labels)
  def splitDiscrete(transitions: ISZ[(String, Z)]): ISZ[(String, ISZ[Z])] = {
    var map = HashSMap.empty[String, ISZ[Z]]
    for (t <- transitions) {
      map.get(t._1) match {
        case Some(targets) => map = map + t._1 ~> (targets :+ t._2)
        case _ => map = map + t._1 ~> ISZ(t._2)
      }
    }
    var result = ISZ[(String, ISZ[Z])]()
    for (entry <- map.entries) {
      result = result :+ entry
    }
    result
  }

  // Sort comparison for (String, Z) edge signatures
  def sortEdge(a: (String, Z), b: (String, Z)): B = {
    a._1 < b._1
  }

  // Builds a simple NFA with one labeled transition: 0 --label--> 1
  def singleEdgeNfa(label: String): Nfa[String] = {
    var g = Graph.empty[Z, Option[String]]
    g = g * z"0"
    g = g * z"1"
    g = g.addDataEdge(Some(label), z"0", z"1")
    Nfa(initial = z"0", accept = z"1", g = g, nextState = z"2")
  }

  val tests = Tests {

    // toDfa: single labeled transition
    * - {
      val nfa = singleEdgeNfa("a")
      val dfa = nfa.toDfa(splitDiscrete)
      assert(dfa.initial == z"0")
      assert(dfa.accepting.contains(z"1"))
      assert(dfa.g.nodesInverse.size == 2)
      val edges = dfa.g.outgoing(z"0")
      assert(edges.elements.size == 1)
      val de = edges.elements(0).asInstanceOf[Graph.Edge.Data[Z, String]]
      assert(de.data == string"a")
      assert(de.dest == z"1")
    }

    // toDfa: epsilon chain then labeled transition
    * - {
      var g = Graph.empty[Z, Option[String]]
      g = g * z"0"
      g = g * z"1"
      g = g * z"2"
      g = g.addDataEdge(None[String](), z"0", z"1")
      g = g.addDataEdge(Some("x"), z"1", z"2")
      val nfa = Nfa(initial = z"0", accept = z"2", g = g, nextState = z"3")
      val dfa = nfa.toDfa(splitDiscrete)
      // Epsilon closure of {0} = {0, 1}, so DFA state 0 has "x" transition
      assert(dfa.initial == z"0")
      assert(dfa.accepting.size == 1)
      assert(dfa.g.outgoing(z"0").elements.size == 1)
      val de = dfa.g.outgoing(z"0").elements(0).asInstanceOf[Graph.Edge.Data[Z, String]]
      assert(de.data == string"x")
      assert(dfa.accepting.contains(de.dest))
    }

    // toDfa: alternation (a|b)
    * - {
      // NFA:  0 --eps--> 1 --"a"--> 2 --eps--> 5
      //       0 --eps--> 3 --"b"--> 4 --eps--> 5 (accept)
      var g = Graph.empty[Z, Option[String]]
      g = g * z"0"
      g = g * z"1"
      g = g * z"2"
      g = g * z"3"
      g = g * z"4"
      g = g * z"5"
      g = g.addDataEdge(None[String](), z"0", z"1")
      g = g.addDataEdge(Some("a"), z"1", z"2")
      g = g.addDataEdge(None[String](), z"2", z"5")
      g = g.addDataEdge(None[String](), z"0", z"3")
      g = g.addDataEdge(Some("b"), z"3", z"4")
      g = g.addDataEdge(None[String](), z"4", z"5")
      val nfa = Nfa(initial = z"0", accept = z"5", g = g, nextState = z"6")
      val dfa = nfa.toDfa(splitDiscrete)
      assert(dfa.initial == z"0")
      // DFA initial state should have 2 outgoing edges (for "a" and "b")
      assert(dfa.g.outgoing(z"0").elements.size == 2)
      // Both targets should be accepting
      for (edge <- dfa.g.outgoing(z"0").elements) {
        val de = edge.asInstanceOf[Graph.Edge.Data[Z, String]]
        assert(dfa.accepting.contains(de.dest))
      }
    }

    // toDfa: concatenation using Nfa.concat
    * - {
      val nfaA = singleEdgeNfa("a")
      var g2 = Graph.empty[Z, Option[String]]
      g2 = g2 * z"2"
      g2 = g2 * z"3"
      g2 = g2.addDataEdge(Some("b"), z"2", z"3")
      val nfaB = Nfa(initial = z"2", accept = z"3", g = g2, nextState = z"4")
      val nfaAB = Nfa.concat(nfaA, nfaB)
      val dfa = nfaAB.toDfa(splitDiscrete)
      assert(dfa.initial == z"0")
      assert(!dfa.accepting.contains(z"0"))
      // From initial, "a" goes to intermediate (non-accepting)
      val edgesFrom0 = dfa.g.outgoing(z"0").elements
      assert(edgesFrom0.size == 1)
      val first = edgesFrom0(0).asInstanceOf[Graph.Edge.Data[Z, String]]
      assert(first.data == string"a")
      assert(!dfa.accepting.contains(first.dest))
      // From intermediate, "b" goes to accepting
      val edgesFrom1 = dfa.g.outgoing(first.dest).elements
      assert(edgesFrom1.size == 1)
      val second = edgesFrom1(0).asInstanceOf[Graph.Edge.Data[Z, String]]
      assert(second.data == string"b")
      assert(dfa.accepting.contains(second.dest))
    }

    // toDfa: NFA whose start state is also the accept state
    * - {
      // NFA that accepts the empty string: initial == accept, with one optional "a" transition
      var g = Graph.empty[Z, Option[String]]
      g = g * z"0"
      g = g * z"1"
      g = g.addDataEdge(Some("a"), z"0", z"1")
      g = g.addDataEdge(None[String](), z"1", z"0")
      val nfa = Nfa(initial = z"0", accept = z"0", g = g, nextState = z"2")
      val dfa = nfa.toDfa(splitDiscrete)
      // DFA initial state should be accepting (since NFA start == accept)
      assert(dfa.accepting.contains(dfa.initial))
    }

    // minimize: single-state DFA returns itself
    * - {
      var g = Graph.empty[Z, String]
      g = g * z"0"
      val dfa = Dfa(initial = z"0", accepting = HashSSet.empty[Z] + z"0", g = g)
      val min = dfa.minimize(sortEdge)
      assert(min.initial == z"0")
      assert(min.accepting.contains(z"0"))
      assert(min.g.nodesInverse.size == 1)
    }

    // minimize: already minimal DFA unchanged
    * - {
      var g = Graph.empty[Z, String]
      g = g * z"0"
      g = g * z"1"
      g = g.addDataEdge(string"a", z"0", z"1")
      val dfa = Dfa(initial = z"0", accepting = HashSSet.empty[Z] + z"1", g = g)
      val min = dfa.minimize(sortEdge)
      assert(min.initial == z"0")
      assert(min.g.nodesInverse.size == 2)
      assert(min.accepting.size == 1)
    }

    // minimize: merge equivalent accepting states
    * - {
      // DFA: 0 --"a"--> 1, 0 --"b"--> 2  (both 1, 2 accepting with no outgoing)
      // States 1 and 2 are equivalent and should be merged
      var g = Graph.empty[Z, String]
      g = g * z"0"
      g = g * z"1"
      g = g * z"2"
      g = g.addDataEdge(string"a", z"0", z"1")
      g = g.addDataEdge(string"b", z"0", z"2")
      val dfa = Dfa(initial = z"0", accepting = HashSSet.empty[Z] + z"1" + z"2", g = g)
      val min = dfa.minimize(sortEdge)
      assert(min.g.nodesInverse.size == 2)
      assert(min.accepting.size == 1)
      // Both "a" and "b" should go to the same merged target
      val edges = min.g.outgoing(z"0").elements
      assert(edges.size == 2)
      val dest0 = edges(0).asInstanceOf[Graph.Edge.Data[Z, String]].dest
      val dest1 = edges(1).asInstanceOf[Graph.Edge.Data[Z, String]].dest
      assert(dest0 == dest1)
    }

    // minimize: non-equivalent states stay separate
    * - {
      // DFA: 0 --"a"--> 1 --"b"--> 2 (only 2 is accepting)
      // All three states have different signatures, so nothing merges
      var g = Graph.empty[Z, String]
      g = g * z"0"
      g = g * z"1"
      g = g * z"2"
      g = g.addDataEdge(string"a", z"0", z"1")
      g = g.addDataEdge(string"b", z"1", z"2")
      val dfa = Dfa(initial = z"0", accepting = HashSSet.empty[Z] + z"2", g = g)
      val min = dfa.minimize(sortEdge)
      assert(min.g.nodesInverse.size == 3)
      assert(min.accepting.size == 1)
      assert(!min.accepting.contains(z"0"))
    }

    // minimize: initial state is always 0 in result
    * - {
      // DFA: 0 --"a"--> 1 --"a"--> 2 (only 2 is accepting)
      // Partition: {0,1} non-accept, {2} accept — but 0 and 1 have different
      // transitions, so they won't merge. Initial should remain 0.
      var g = Graph.empty[Z, String]
      g = g * z"0"
      g = g * z"1"
      g = g * z"2"
      g = g.addDataEdge(string"a", z"0", z"1")
      g = g.addDataEdge(string"a", z"1", z"2")
      val dfa = Dfa(initial = z"0", accepting = HashSSet.empty[Z] + z"2", g = g)
      val min = dfa.minimize(sortEdge)
      assert(min.initial == z"0")
      assert(!min.accepting.contains(z"0"))
    }

    // toDfa + minimize roundtrip: NFA(a|b) -> DFA -> minimized DFA
    * - {
      // NFA for (a|b) has two accepting target states that are equivalent
      var g = Graph.empty[Z, Option[String]]
      g = g * z"0"
      g = g * z"1"
      g = g * z"2"
      g = g * z"3"
      g = g * z"4"
      g = g * z"5"
      g = g.addDataEdge(None[String](), z"0", z"1")
      g = g.addDataEdge(Some("a"), z"1", z"2")
      g = g.addDataEdge(None[String](), z"2", z"5")
      g = g.addDataEdge(None[String](), z"0", z"3")
      g = g.addDataEdge(Some("b"), z"3", z"4")
      g = g.addDataEdge(None[String](), z"4", z"5")
      val nfa = Nfa(initial = z"0", accept = z"5", g = g, nextState = z"6")
      val dfa = nfa.toDfa(splitDiscrete)
      val min = dfa.minimize(sortEdge)
      // The two accepting DFA states should merge into one
      assert(min.initial == z"0")
      assert(min.g.nodesInverse.size == 2)
      assert(min.accepting.size == 1)
      assert(!min.accepting.contains(z"0"))
    }

    // toDfa + minimize roundtrip: NFA(a·b|a·c) -> DFA -> minimize shares prefix
    * - {
      // NFA for a·b | a·c:
      //   0 --eps--> 1 --"a"--> 2 --"b"--> 3 --eps--> 8
      //   0 --eps--> 4 --"a"--> 5 --"c"--> 6 --eps--> 8  (accept = 8, but let's use 7)
      // Actually let me simplify:
      //   0 --eps--> 1 --"a"--> 2 --eps--> 3 --"b"--> 4
      //   0 --eps--> 5 --"a"--> 6 --eps--> 7 --"c"--> 8
      //   4 --eps--> 9, 8 --eps--> 9, accept = 9
      var g = Graph.empty[Z, Option[String]]
      var i: Z = 0
      while (i <= z"9") {
        g = g * i
        i = i + 1
      }
      g = g.addDataEdge(None[String](), z"0", z"1")
      g = g.addDataEdge(Some("a"), z"1", z"2")
      g = g.addDataEdge(None[String](), z"2", z"3")
      g = g.addDataEdge(Some("b"), z"3", z"4")
      g = g.addDataEdge(None[String](), z"4", z"9")
      g = g.addDataEdge(None[String](), z"0", z"5")
      g = g.addDataEdge(Some("a"), z"5", z"6")
      g = g.addDataEdge(None[String](), z"6", z"7")
      g = g.addDataEdge(Some("c"), z"7", z"8")
      g = g.addDataEdge(None[String](), z"8", z"9")
      val nfa = Nfa(initial = z"0", accept = z"9", g = g, nextState = z"10")
      val dfa = nfa.toDfa(splitDiscrete)
      val min = dfa.minimize(sortEdge)
      // Minimized: 0 --"a"--> 1 --"b"--> 2(accept), 1 --"c"--> 2(accept)
      // States for "b" and "c" targets are both accepting with no outgoing, so they merge
      assert(min.initial == z"0")
      assert(min.g.nodesInverse.size == 3)
      // From initial, only "a"
      val fromInit = min.g.outgoing(z"0").elements
      assert(fromInit.size == 1)
      val mid = fromInit(0).asInstanceOf[Graph.Edge.Data[Z, String]]
      assert(mid.data == string"a")
      // From middle state, "b" and "c" go to same (merged) accept state
      val fromMid = min.g.outgoing(mid.dest).elements
      assert(fromMid.size == 2)
      val d0 = fromMid(0).asInstanceOf[Graph.Edge.Data[Z, String]].dest
      val d1 = fromMid(1).asInstanceOf[Graph.Edge.Data[Z, String]].dest
      assert(d0 == d1)
      assert(min.accepting.contains(d0))
    }

    // minimize: triggers initial state reordering
    * - {
      // DFA: 0 (initial, accepting) --"a"--> 1 (non-accepting) --"a"--> 0
      // Partition starts as [{1}, {0}] (non-accept first, accept second).
      // Initial state 0 is in group 1, triggering reordering to [{0}, {1}].
      var g = Graph.empty[Z, String]
      g = g * z"0"
      g = g * z"1"
      g = g.addDataEdge(string"a", z"0", z"1")
      g = g.addDataEdge(string"a", z"1", z"0")
      val dfa = Dfa(initial = z"0", accepting = HashSSet.empty[Z] + z"0", g = g)
      val min = dfa.minimize(sortEdge)
      assert(min.initial == z"0")
      assert(min.accepting.contains(z"0"))
      assert(min.g.nodesInverse.size == 2)
    }

    // minimize: all states accepting (empty non-accept group)
    * - {
      // DFA: 0 --"a"--> 1, both accepting
      // nonAcceptGroup is empty, so that branch is skipped
      var g = Graph.empty[Z, String]
      g = g * z"0"
      g = g * z"1"
      g = g.addDataEdge(string"a", z"0", z"1")
      val dfa = Dfa(initial = z"0", accepting = HashSSet.empty[Z] + z"0" + z"1", g = g)
      val min = dfa.minimize(sortEdge)
      assert(min.initial == z"0")
      assert(min.g.nodesInverse.size == 2)
      assert(min.accepting.size == 2)
    }

    // Dfa.isReject: self-loop covering full char range
    * - {
      var g = Graph.empty[Z, (C, C)]
      g = g * z"0"
      g = g.addDataEdge(('\u0000', Dfa.maxChar), z"0", z"0")
      val rejectEdge = g.outgoing(z"0").elements(0)
      assert(Dfa.isReject(rejectEdge))
    }

    // Dfa.isReject: non-reject edges
    * - {
      var g = Graph.empty[Z, (C, C)]
      g = g * z"0"
      g = g * z"1"
      // Different source and dest
      g = g.addDataEdge(('\u0000', Dfa.maxChar), z"0", z"1")
      val nonReject1 = g.outgoing(z"0").elements(0)
      assert(!Dfa.isReject(nonReject1))
      // Self-loop but partial range
      var g2 = Graph.empty[Z, (C, C)]
      g2 = g2 * z"0"
      g2 = g2.addDataEdge(('a', 'z'), z"0", z"0")
      val nonReject2 = g2.outgoing(z"0").elements(0)
      assert(!Dfa.isReject(nonReject2))
    }
  }
}
