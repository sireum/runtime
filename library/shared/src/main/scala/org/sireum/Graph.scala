// #Sireum
/*
 Copyright (c) 2017-2024, Robby, Kansas State University
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

object Graph {

  type Index = Z

  @datatype trait Edge[W, E] {
    @pure def source: W
    @pure def dest: W
    @pure def toInternal(map: HashSMap[W, Graph.Index]): Internal.Edge[E]
  }

  object Edge {

    @datatype class Plain[W, E](val source: W, val dest: W) extends Edge[W, E] {

      @pure override def toInternal(map: HashSMap[W, Graph.Index]): Internal.Edge[E] = {
        return Internal.Edge.Plain(map.get(source).get, map.get(dest).get)
      }

    }

    @datatype class Data[W, E](val source: W, val dest: W, val data: E) extends Edge[W, E] {

      @pure override def toInternal(map: HashSMap[W, Graph.Index]): Internal.Edge[E] = {
        return Internal.Edge.Data(map.get(source).get, map.get(dest).get, data)
      }

    }

  }

  object Internal {

    @datatype trait Edge[E] {
      @pure def source: Graph.Index
      @pure def dest: Graph.Index
      @pure def toEdge[W](map: ISZ[W]): Graph.Edge[W, E]
    }

    @datatype trait Edges[E] {
      @pure def elements: ISZ[Internal.Edge[E]]
      @pure def size: Z
      @pure def +(e: Internal.Edge[E]): Edges[E]
      @pure def ++(es: ISZ[Internal.Edge[E]]): Edges[E]
      @pure def -#(p: (Internal.Edge[E], Z)): Edges[E]
    }

    object Edges {

      @datatype class Set[E](val set: HashSSet[Internal.Edge[E]]) extends Edges[E] {

        @pure override def elements: ISZ[Internal.Edge[E]] = {
          return set.elements
        }

        @pure override def size: Z = {
          return set.size
        }

        @pure override def +(e: Graph.Internal.Edge[E]): Edges[E] = {
          return this(set + e)
        }

        @pure override def ++(es: ISZ[Internal.Edge[E]]): Edges[E] = {
          return this(set ++ es)
        }

        @pure override def -#(p: (Internal.Edge[E], Z)): Edges[E] = {
          return this(set - p._1)
        }
      }

      @datatype class Bag[E](val set: HashSBag[Internal.Edge[E]]) extends Edges[E] {

        @pure override def elements: ISZ[Internal.Edge[E]] = {
          return set.elements
        }

        @pure override def size: Z = {
          return set.size
        }

        @pure override def +(e: Internal.Edge[E]): Edges[E] = {
          return this(set + e)
        }

        @pure override def ++(es: ISZ[Internal.Edge[E]]): Edges[E] = {
          return this(set ++ es)
        }

        @pure override def -#(p: (Internal.Edge[E], Z)): Edges[E] = {
          return this(set -# p)
        }
      }

      @pure def empty[E](multi: B): Edges[E] = {
        return if (multi) Bag(HashSBag.empty) else Set(HashSSet.empty)
      }

    }

    object Edge {

      @datatype class Plain[E](val source: Graph.Index, val dest: Graph.Index) extends Edge[E] {

        @pure override def toEdge[W](map: ISZ[W]): Graph.Edge[W, E] = {
          return Graph.Edge.Plain(map(source), map(dest))
        }

      }

      @datatype class Data[E](val source: Graph.Index, val dest: Graph.Index, val data: E) extends Edge[E] {

        @pure override def toEdge[W](map: ISZ[W]): Graph.Edge[W, E] = {
          return Graph.Edge.Data(map(source), map(dest), data)
        }

      }

    }

    @pure def addEdge[W, E](g: Graph[W, E], e: Internal.Edge[E]): Graph[W, E] = {
      return g(
        incomingEdges = g.incomingEdges + e.dest ~> (g.incomingEdges
          .get(e.dest)
          .getOrElse(Edges.empty[E](g.multi)) + e),
        outgoingEdges = g.outgoingEdges + e.source ~> (g.outgoingEdges
          .get(e.source)
          .getOrElse(Edges.empty[E](g.multi)) + e)
      )
    }

    @pure def addPlainEdge[W, E](g: Graph[W, E], src: Graph.Index, dst: Graph.Index): Graph[W, E] = {
      return addEdge(g, Graph.Internal.Edge.Plain[E](src, dst))
    }

    @pure def addDataEdge[W, E](g: Graph[W, E], data: E, src: Graph.Index, dst: Graph.Index): Graph[W, E] = {
      return addEdge(g, Graph.Internal.Edge.Data(src, dst, data))
    }

    @pure def removeEdge[W, E](g: Graph[W, E], e: Graph.Internal.Edge[E], n: Z): Graph[W, E] = {
      if (g.incomingEdges.get(e.dest).isEmpty) {
        return g
      }
      return g(
        incomingEdges = g.incomingEdges + e.dest ~> (g.incomingEdges.get(e.dest).get -# e ~> n),
        outgoingEdges = g.outgoingEdges + e.source ~> (g.outgoingEdges.get(e.source).get -# e ~> n)
      )
    }

    @pure def incoming[W, E](g: Graph[W, E], dst: Graph.Index): ISZ[Graph.Internal.Edge[E]] = {
      g.incomingEdges.get(dst) match {
        case Some(s) => return s.elements
        case _ => return ISZ()
      }
    }

    @pure def outgoing[W, E](g: Graph[W, E], src: Graph.Index): ISZ[Graph.Internal.Edge[E]] = {
      g.outgoingEdges.get(src) match {
        case Some(s) => return s.elements
        case _ => return ISZ()
      }
    }

  }

  @pure def empty[W, E]: Graph[W, E] = {
    return Graph(HashSMap.empty, ISZ(), HashSMap.empty, HashSMap.empty, 0, F)
  }

  @pure def emptyMulti[W, E]: Graph[W, E] = {
    return Graph(HashSMap.empty, ISZ(), HashSMap.empty, HashSMap.empty, 0, T)
  }
}

@datatype class Graph[W, E](
  val nodes: HashSMap[W, Graph.Index],
  val nodesInverse: IS[Graph.Index, W],
  val incomingEdges: HashSMap[Graph.Index, Graph.Internal.Edges[E]],
  val outgoingEdges: HashSMap[Graph.Index, Graph.Internal.Edges[E]],
  val nextNodeId: Graph.Index,
  val multi: B
) {

  @pure def *(node: W): Graph[W, E] = {
    nodes.get(node) match {
      case Some(_) => return this
      case _ =>
        return this(
          nodes + node ~> nextNodeId,
          nodesInverse :+ node,
          incomingEdges,
          outgoingEdges,
          nextNodeId + 1,
          multi
        )
    }
  }

  @pure def --*[@index I](ns: IS[I, W]): Graph[W, E] = {
    var r: Graph[W, E] = if (multi) Graph.emptyMulti[W, E] else Graph.empty[W, E]
    val ins = HashSet ++ ns.map[Z](n => nodes.get(n).get)
    for (es <- incomingEdges.values) {
      for (e <- es.elements) {
        if (ins.contains(e.source) && ins.contains(e.dest)) {
          r = r.addEdge(e.toEdge(nodesInverse))
        }
      }
    }
    return r
  }

  @pure def +(edge: (W, W)): Graph[W, E] = {
    return addPlainEdge(edge._1, edge._2)
  }

  @pure def +@(edge: ((W, W), E)): Graph[W, E] = {
    return addDataEdge(edge._2, edge._1._1, edge._1._2)
  }

  @pure def -(edge: Graph.Edge[W, E]): Graph[W, E] = {
    return removeEdgeN(edge, 1)
  }

  @pure def -#(p: (Graph.Edge[W, E], Z)): Graph[W, E] = {
    return removeEdgeN(p._1, p._2)
  }

  @pure def --[@index I](edges: IS[I, Graph.Edge[W, E]]): Graph[W, E] = {
    var r = this
    for (e <- edges) {
      r = r - e
    }
    return r
  }

  @pure def incoming(dest: W): ISZ[Graph.Edge[W, E]] = {
    nodes.get(dest) match {
      case Some(dst) => return Graph.Internal.incoming(this, dst).map(e => e.toEdge(nodesInverse))
      case _ => return ISZ()
    }
  }

  @pure def outgoing(source: W): ISZ[Graph.Edge[W, E]] = {
    nodes.get(source) match {
      case Some(src) => return Graph.Internal.outgoing(this, src).map[Graph.Edge[W, E]](e => e.toEdge(nodesInverse))
      case _ => return ISZ()
    }
  }

  @pure def addEdge(edge: Graph.Edge[W, E]): Graph[W, E] = {
    return Graph.Internal.addEdge(this * edge.source * edge.dest, edge.toInternal(nodes))
  }

  @pure def addPlainEdge(source: W, dest: W): Graph[W, E] = {
    val r = this * source * dest
    return Graph.Internal.addPlainEdge(r, r.nodes.get(source).get, r.nodes.get(dest).get)
  }

  @pure def addDataEdge(data: E, source: W, dest: W): Graph[W, E] = {
    val r = this * source * dest
    return Graph.Internal.addDataEdge(r, data, r.nodes.get(source).get, r.nodes.get(dest).get)
  }

  @pure def allEdges: ISZ[Graph.Edge[W, E]] = {
    return for (es <- incomingEdges.values; e <- es.elements) yield e.toEdge(nodesInverse)
  }

  @pure def removeEdgeN(edge: Graph.Edge[W, E], n: Z): Graph[W, E] = {
    return Graph.Internal.removeEdge(this, edge.toInternal(nodes), n)
  }

  @pure def edges(source: W, dest: W): ISZ[Graph.Edge[W, E]] = {
    return outgoing(source).filter(e => e.dest == dest)
  }

  @pure def numOfNodes: Z = {
    return nodes.size
  }

  @pure def numOfEdges: Z = {
    var r = z"0"
    for (n <- incomingEdges.values.map[Z](s => s.size)) {
      r = r + n
    }
    return r
  }

  @pure override def hash: Z = {
    return (numOfNodes, numOfEdges).hash
  }

  @pure def isEqual(other: Graph[W, E]): B = {
    if (nodes.size != other.nodes.size || incomingEdges.size != incomingEdges.size) {
      return F
    }
    if (nodes.keySet != other.nodes.keySet) {
      return F
    }
    val thisEdges: ISZ[Graph.Edge[W, E]] =
      for (ess <- incomingEdges.values; es <- ess.elements) yield es.toEdge(nodesInverse)
    val otherEdges: ISZ[Graph.Edge[W, E]] =
      for (ess <- other.incomingEdges.values; es <- ess.elements) yield es.toEdge(other.nodesInverse)
    return (HashSet ++ thisEdges ++ otherEdges).size == thisEdges.size
  }

  @pure def toST(attributes: ISZ[ST], f: W => ST @pure, g: E => ST @pure): ST = {
    @pure def e2st(e: Graph.Internal.Edge[E]): ST = {
      e match {
        case Graph.Internal.Edge.Data(source, dest, data) => return st"""n$source -> n$dest ${g(data)}"""
        case Graph.Internal.Edge.Plain(source, dest) => return st"""n$source -> n$dest"""
      }
    }
    val nodes: ISZ[ST] = for (e <- this.nodes.entries) yield st"""n${e._2} ${f(e._1)}"""
    val edges: ISZ[ST] = for (es <- incomingEdges.values; e <- es.elements) yield e2st(e)
    val r =
      st"""digraph G {
      |
      |  ${(attributes, "\n")}
      |
      |  ${(nodes, "\n")}
      |
      |  ${(edges, "\n")}
      |
      |}"""
    return r

  }

  @pure override def string: String = {
    return toST(ISZ(), v => st"""[label="$v"]""", e => st"""[label="$e"]""").render
  }
}
