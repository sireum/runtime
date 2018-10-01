// #Sireum
/*
 Copyright (c) 2017, Hariharan Thiagarajan, Kansas State University
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

package org.sireum.ops

import org.sireum.Graph
import org.sireum._

@datatype class GraphOps[V, E](graph: Graph[V, E]) {

  @pure def getEdgeData(e: Graph.Edge[V, E]): Option[E] = {
    e match {
      case Graph.Edge.Data(_, _, ed) => return Some[E](ed)
      case _ => return None[E]()
    }
  }

  @pure def getAllSuccessor(v: V): Set[V] = {
    if (graph.outgoingEdges.get(graph.nodes.get(v).get).nonEmpty) {
      return Set.empty[V] ++ (for (es <- graph.outgoingEdges.get(graph.nodes.get(v).get).get.elements)
        yield graph.nodesInverse(es.dest))
    } else {
      return Set.empty[V]
    }
  }

  @pure def getAllPredecessor(v: V): Set[V] = {
    if (graph.incomingEdges.get(graph.nodes.get(v).get).nonEmpty) {
      return Set.empty[V] ++ (for (es <- graph.incomingEdges.get(graph.nodes.get(v).get).get.elements)
        yield graph.nodesInverse(es.source))
    } else {
      return Set.empty[V]
    }

  }

  @pure def getSCC: ISZ[HashSet[V]] = {
    var result = ISZ[HashSet[V]]()
    var discoveryMap: HashMap[V, (B, B)] =
      HashMap ++ (for (v <- graph.nodes.keys) yield (v, (F, F)))

    def resetDiscoveryMap(): Unit = {
      discoveryMap = HashMap ++ (for (e <- discoveryMap.entries) yield (e._1, (F, F)))
    }

    def setDiscovered(v: V): B = {
      return discoveryMap.get(v).exists { cf =>
        discoveryMap = discoveryMap + ((v, (T, cf._2)))
        T
      }
    }

    def setBoth(v: V): Unit = {
      discoveryMap = discoveryMap + ((v, (T, T)))
    }

    def isAllMySuccDiscovered(v: V): B = {
      return ISZOps(for (s <- getAllSuccessor(v).elements; e <- discoveryMap.get(s).toIS) yield e._1)
        .foldLeft((c: B, n: B) => c & n, T)
    }

    def dfs(v: V, isFirst: B): ISZ[V] = {
      var r = ISZ[V]()
      var stack = Stack.empty[V]
      stack = stack.push(v)

      while (stack.nonEmpty) {
        val current = stack.pop().get
        stack = current._2
        if (discoveryMap.get(current._1).nonEmpty
          && !discoveryMap.get(current._1).get._1) {
          setDiscovered(current._1)
          if (!isFirst) {
            r = r :+ current._1
          }
          setBoth(current._1)
          stack = stack.push(current._1)

          val nexts: Set[V] = if (isFirst) getAllSuccessor(current._1) else getAllPredecessor(current._1)

          for (n <- nexts.elements) {
            if (!discoveryMap.get(n).get._1) {
              stack = stack.push(n)
            }
          }

        } else if (discoveryMap.get(current._1).get._2 && isFirst) {
          r = current._1 +: r
        }
      }
      return r
    }

    var orderedNodes = ISZ[V]()

    for (k <- graph.nodes.keys) {
      if (!discoveryMap.get(k).get._1) {
        orderedNodes = dfs(k, T) ++ orderedNodes
      }
    }
    resetDiscoveryMap()
    for (k <- orderedNodes) {
      if (!discoveryMap.get(k).get._1) {
        result = result :+ HashSet.empty[V] ++ dfs(k, F)
      }
    }
    return result
  }

  @pure def getCycles: ISZ[ISZ[V]] = {
    val sccs = getSCC
    var loops = ISZ[ISZ[V]]()
    var bSets = HashMap.empty[V, Set[V]]
    var stack = Stack.empty[V]
    var marked = Set.empty[V]
    var removed = HashMap.empty[V, Set[V]]
    var position = HashMap.empty[V, Z]
    var reach = HashMap.empty[V, B] ++ (for (k <- graph.nodes.keys) yield (k, F))

    def cycle(v: V, tq: Z): B = {
      var q = tq
      var foundCycle = F
      marked = marked + v
      stack = stack.push(v)
      val t = stack.size
      position = position + ((v, t))
      if (!reach.get(v).get) {
        q = t
      }
      val avRemoved: Set[V] = removed.get(v) match {
        case Some(r) => r
        case _ => Set.empty[V]
      }

      for (wV <- getAllSuccessor(v).elements) {
        if (!avRemoved.contains(wV)) {
          if (!marked.contains(wV)) {
            val gotCycle = cycle(wV, q)
            if (gotCycle) {
              foundCycle = T
            } else {
              noCycle(v, wV)
            }
          } else if (position.get(wV).nonEmpty && position.get(wV).get <= q) {
            foundCycle = T
            var cycle = ISZ[V]()
            val elements = stack.elements
            var current = stack.peek.get
            var break = T
            var i = 0
            while (i < elements.size && break) {
              current = elements(i)
              if (wV == current) {
                break = F
              }
              i = i + 1
            }
            cycle = cycle :+ wV
            break = T
            while (i < elements.size && break) {
              current = elements(i)
              cycle = cycle :+ current
              if (current == v) {
                break = F
              }
              i = i + 1
            }
            loops = loops :+ cycle
          } else {
            noCycle(v, wV)
          }
        }
      }
      stack = stack.pop().get._2
      if (foundCycle) {
        unmark(v)
      }
      reach = reach + ((v, T))
      position = position + ((v, graph.nodes.size))
      return foundCycle
    }

    def unmark(x: V): Unit = {
      marked = marked - x
      val temp: Set[V] = bSets.get(x) match {
        case Some(bsx) => bsx
        case _ => Set.empty[V]
      }

      for (y <- temp.elements) {
        val t: Set[V] = removed.get(y) match {
          case Some(ry) => ry - x
          case _ => Set.empty[V] - x
        }
        removed = removed + ((y, t))
        if (marked.contains(y)) {
          unmark(y)
        }
      }
      bSets = bSets + ((x, Set.empty[V]))
    }

    def noCycle(x: V, y: V): Unit = {
      val t1: Set[V] = bSets.get(y) match {
        case Some(bs) => bs
        case _ => Set.empty[V]
      }
      bSets = bSets + ((y, t1))
      val t2: Set[V] = removed.get(x) match {
        case Some(rx) => rx + y
        case _ => Set.empty[V] + y
      }
      removed = removed + ((x, t2))
    }

    var startNodes = ISZ[V]()
    for (scc <- sccs) {
      var max: Z = -1
      var startNode = scc.elements(0)
      for (node <- scc.elements) {
        val inedges =  graph.incomingEdges.get(graph.nodes.get(node).get)
        val inDegree : Z = if(inedges.nonEmpty)inedges.get.size else Z(0)
        if (inDegree > max) {
          max = inDegree
          startNode = node
        }
      }
      startNodes = startNodes :+ startNode
    }

    for (n <- startNodes) {
      cycle(n, 0)
    }

    return loops
  }

  @pure def forwardReach(criteria: ISZ[V]): ISZ[V] = {
    val r = reachable(criteria, T)
    return r
  }

  @pure def backwardReach(criteria: ISZ[V]): ISZ[V] = {
    val r = reachable(criteria, F)
    return r
  }

  @pure def reachable(criteria: ISZ[V], isForward: B): ISZ[V] = {
    var workList = ISZ[V]()
    workList = workList ++ criteria
    var result = HashSet.empty[V]

    while (workList.nonEmpty) {
      val current = ISZOps(workList).first
      if (!result.contains(current)) {
        val next: Set[V] =
          if (isForward)
            getAllSuccessor(current)
          else getAllPredecessor(current)
        workList = workList ++ next.elements
        result = result + current
      }
      workList = ISZOps(workList).tail
    }
    return result.elements
  }

}
