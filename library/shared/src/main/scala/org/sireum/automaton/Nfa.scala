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

package org.sireum.automaton

import org.sireum._

/** NFA fragment used in Thompson's construction.
  *
  * Uses `Graph[Z, Option[E]]` where `None` labels represent
  * epsilon transitions and `Some(e)` labels represent
  * data-carrying transitions.
  */
@datatype class Nfa[E](
  val initial: Z,
  val accept: Z,
  val g: Graph[Z, Option[E]],
  val nextState: Z
) {

  /** Converts this NFA to a DFA via the subset construction algorithm.
    *
    * @param splitTransitions  abstracts label-specific interval splitting;
    *                          given collected `(label, targetState)` pairs,
    *                          returns `(label, targetStates)` groups
    */
  def toDfa(splitTransitions: ISZ[(E, Z)] => ISZ[(E, ISZ[Z])]): Dfa[E] = {
    val startStates = Nfa.epsilonClosure(ISZ(initial), g)
    var stateMap = HashSMap.empty[ISZ[Z], Z]
    stateMap = stateMap + startStates ~> z"0"
    var worklist = ISZ(startStates)
    var dfaNextState: Z = 1
    var accepting = HashSSet.empty[Z]
    var edges = ISZ[(E, Z, Z)]()
    if (ops.ISZOps(startStates).contains(accept)) {
      accepting = accepting + z"0"
    }
    while (worklist.nonEmpty) {
      val current = worklist(0)
      worklist = ops.ISZOps(worklist).tail
      val currentDfa = stateMap.get(current).get
      var transitions = ISZ[(E, Z)]()
      for (s <- current) {
        for (edge <- g.outgoing(s)) {
          val de = edge.asInstanceOf[Graph.Edge.Data[Z, Option[E]]]
          de.data match {
            case Some(label) =>
              transitions = transitions :+ (label, de.dest)
            case _ =>
          }
        }
      }
      val intervals = splitTransitions(transitions)
      for (interval <- intervals) {
        val targetClosure = Nfa.epsilonClosure(interval._2, g)
        if (targetClosure.nonEmpty) {
          var targetDfa: Z = -1
          stateMap.get(targetClosure) match {
            case Some(n) =>
              targetDfa = n
            case _ =>
              targetDfa = dfaNextState
              dfaNextState = dfaNextState + 1
              stateMap = stateMap + targetClosure ~> targetDfa
              worklist = worklist :+ targetClosure
              if (ops.ISZOps(targetClosure).contains(accept)) {
                accepting = accepting + targetDfa
              }
          }
          edges = edges :+ (interval._1, currentDfa, targetDfa)
        }
      }
    }
    var dfaG = Graph.empty[Z, E]
    var ni: Z = 0
    while (ni < dfaNextState) {
      dfaG = dfaG * ni
      ni = ni + 1
    }
    for (edge <- edges) {
      dfaG = dfaG.addDataEdge(edge._1, edge._2, edge._3)
    }
    return Dfa(initial = z"0", accepting = accepting, g = dfaG)
  }

}

object Nfa {

  /** Merges all nodes and edges from `b` into `a`. */
  def mergeGraphs[E](a: Graph[Z, E], b: Graph[Z, E]): Graph[Z, E] = {
    var g = a
    for (node <- b.nodesInverse) {
      g = g * node
    }
    for (edge <- b.allEdges) {
      g = g.addEdge(edge)
    }
    return g
  }

  /** Concatenates two NFA fragments via an epsilon transition. */
  def concat[E](a: Nfa[E], b: Nfa[E]): Nfa[E] = {
    var g = mergeGraphs(a.g, b.g)
    g = g.addDataEdge(None[E](), a.accept, b.initial)
    return Nfa(initial = a.initial, accept = b.accept, g = g, nextState = b.nextState)
  }

  /** Computes the epsilon-closure of a set of NFA states. */
  def epsilonClosure[E](states: ISZ[Z], g: Graph[Z, Option[E]]): ISZ[Z] = {
    var result = HashSSet.empty[Z]
    for (s <- states) {
      result = result + s
    }
    var worklist = states
    while (worklist.nonEmpty) {
      val s = worklist(0)
      worklist = ops.ISZOps(worklist).tail
      for (edge <- g.outgoing(s)) {
        val de = edge.asInstanceOf[Graph.Edge.Data[Z, Option[E]]]
        if (de.data.isEmpty && !result.contains(de.dest)) {
          result = result + de.dest
          worklist = worklist :+ de.dest
        }
      }
    }
    return ops.ISZOps(result.elements).sortWith((a, b) => a < b)
  }

}
