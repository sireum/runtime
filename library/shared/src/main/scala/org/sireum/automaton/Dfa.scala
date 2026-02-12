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
import org.sireum.U32._

@datatype class Dfa[E](val initial: Z, val accepting: HashSSet[Z], val g: Graph[Z, E]) {

  /** Minimizes this DFA using partition refinement.
    *
    * Iteratively splits groups of states whose outgoing transitions
    * disagree on which group the target belongs to, until stable.
    *
    * @param sortEdge  comparison function for sorting edge signatures;
    *                  given two `(label, targetGroup)` pairs, returns
    *                  `T` if the first should come before the second
    */
  def minimize(sortEdge: ((E, Z), (E, Z)) => B @pure): Dfa[E] = {
    val states = g.nodesInverse
    if (states.size <= 1) {
      return this
    }
    var partition = ISZ[ISZ[Z]]()
    var acceptGroup = ISZ[Z]()
    var nonAcceptGroup = ISZ[Z]()
    for (s <- states) {
      if (accepting.contains(s)) {
        acceptGroup = acceptGroup :+ s
      } else {
        nonAcceptGroup = nonAcceptGroup :+ s
      }
    }
    if (nonAcceptGroup.nonEmpty) {
      partition = partition :+ nonAcceptGroup
    }
    if (acceptGroup.nonEmpty) {
      partition = partition :+ acceptGroup
    }
    var changed: B = T
    while (changed) {
      changed = F
      var stateToGroup = HashSMap.empty[Z, Z]
      var gi: Z = 0
      while (gi < partition.size) {
        for (s <- partition(gi)) {
          stateToGroup = stateToGroup + s ~> gi
        }
        gi = gi + 1
      }
      var newPartition = ISZ[ISZ[Z]]()
      for (group <- partition) {
        if (group.size <= 1) {
          newPartition = newPartition :+ group
        } else {
          var sigMap = HashSMap.empty[ISZ[(E, Z)], ISZ[Z]]
          for (s <- group) {
            var sig = ISZ[(E, Z)]()
            for (edge <- g.outgoing(s)) {
              val de = edge.asInstanceOf[Graph.Edge.Data[Z, E]]
              sig = sig :+ (de.data, stateToGroup.get(de.dest).get)
            }
            sig = ops.ISZOps(sig).sortWith(sortEdge)
            sigMap.get(sig) match {
              case Some(ss) => sigMap = sigMap + sig ~> (ss :+ s)
              case _ => sigMap = sigMap + sig ~> ISZ(s)
            }
          }
          for (subGroup <- sigMap.values) {
            newPartition = newPartition :+ subGroup
          }
          if (sigMap.size > 1) {
            changed = T
          }
        }
      }
      partition = newPartition
    }
    var stateToGroup = HashSMap.empty[Z, Z]
    var gi: Z = 0
    while (gi < partition.size) {
      for (s <- partition(gi)) {
        stateToGroup = stateToGroup + s ~> gi
      }
      gi = gi + 1
    }
    val initialGroupIdx = stateToGroup.get(initial).get
    if (initialGroupIdx != z"0") {
      var reordered = ISZ[ISZ[Z]]()
      reordered = reordered :+ partition(initialGroupIdx)
      var ri: Z = 0
      while (ri < partition.size) {
        if (ri != initialGroupIdx) {
          reordered = reordered :+ partition(ri)
        }
        ri = ri + 1
      }
      partition = reordered
      stateToGroup = HashSMap.empty[Z, Z]
      gi = 0
      while (gi < partition.size) {
        for (s <- partition(gi)) {
          stateToGroup = stateToGroup + s ~> gi
        }
        gi = gi + 1
      }
    }
    var newAccepting = HashSSet.empty[Z]
    var ai: Z = 0
    while (ai < partition.size) {
      if (accepting.contains(partition(ai)(0))) {
        newAccepting = newAccepting + ai
      }
      ai = ai + 1
    }
    var newG = Graph.empty[Z, E]
    var ni: Z = 0
    while (ni < partition.size) {
      newG = newG * ni
      ni = ni + 1
    }
    var ei: Z = 0
    while (ei < partition.size) {
      val rep = partition(ei)(0)
      for (edge <- g.outgoing(rep)) {
        val de = edge.asInstanceOf[Graph.Edge.Data[Z, E]]
        val targetGroup = stateToGroup.get(de.dest).get
        newG = newG.addDataEdge(de.data, ei, targetGroup)
      }
      ei = ei + 1
    }
    return Dfa(initial = z"0", accepting = newAccepting, g = newG)
  }

}

object Dfa {
  val maxChar: C = conversions.U32.toC(u32"0x0010FFFF")

  @pure def isReject(edge: Graph.Edge[Z, (C, C)]): B = {
    val e = edge.asInstanceOf[Graph.Edge.Data[Z, (C, C)]]
    return e.source == e.dest && e.data._1 == '\u0000' && e.data._2 == maxChar
  }

}
