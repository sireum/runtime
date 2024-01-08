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

object ObjPrinter {
  val stTrue: ST = st"true"
  val stFalse: ST = st"false"
  val stZ: ST = st"Z"
  val stZ8: ST = st"Z8"
  val stZ16: ST = st"Z16"
  val stZ32: ST = st"Z32"
  val stZ64: ST = st"Z64"
  val stN: ST = st"N"
  val stN8: ST = st"N8"
  val stN16: ST = st"N16"
  val stN32: ST = st"N32"
  val stN64: ST = st"N64"
  val stS8: ST = st"S8"
  val stS16: ST = st"S16"
  val stS32: ST = st"S32"
  val stS64: ST = st"S64"
  val stU8: ST = st"U8"
  val stU16: ST = st"U16"
  val stU32: ST = st"U32"
  val stU64: ST = st"U64"
  val stString: ST = st"String"
  val stDocInfo: ST = st"message.DocInfo"
  val stPosition: ST = st"message.Position"
  val stMessage: ST = st"message.Message"

  val sGroupMax: Z = 10

  @strictpure def printB(b: B): ST = if (b) stTrue else stFalse

  @pure def printC(c: C): ST = {
    return st"'${ops.COps(c).escapeString}'"
  }

  @strictpure def printZ(n: Z): ST = printNumber("z", n.string)

  @strictpure def printZ8(n: Z8): ST = printNumber("z8", n.string)

  @strictpure def printZ16(n: Z16): ST = printNumber("z16", n.string)

  @strictpure def printZ32(n: Z32): ST = printNumber("z32", n.string)

  @strictpure def printZ64(n: Z64): ST = printNumber("z64", n.string)

  @strictpure def printN(n: N): ST = printNumber("n", n.string)

  @strictpure def printN8(n: N8): ST = printNumber("n8", n.string)

  @strictpure def printN16(n: N16): ST = printNumber("n16", n.string)

  @strictpure def printN32(n: N32): ST = printNumber("n32", n.string)

  @strictpure def printN64(n: N64): ST = printNumber("n64", n.string)

  @strictpure def printS8(n: S8): ST = printNumber("s8", n.string)

  @strictpure def printS16(n: S16): ST = printNumber("s16", n.string)

  @strictpure def printS32(n: S32): ST = printNumber("s32", n.string)

  @strictpure def printS64(n: S64): ST = printNumber("s64", n.string)

  @pure def printU8(n: U8): ST = {
    return printNumber("u8", conversions.U8.toZ(n).string)
  }

  @pure def printU16(n: U16): ST = {
    return printNumber("u16", conversions.U16.toZ(n).string)
  }

  @pure def printU32(n: U32): ST = {
    return printNumber("u32", conversions.U32.toZ(n).string)
  }

  @pure def printU64(n: U64): ST = {
    return printNumber("u64", conversions.U64.toZ(n).string)
  }

  @strictpure def printF32(n: F32): ST = printNumber("f32", n.string)

  @strictpure def printF64(n: F64): ST = printNumber("f64", n.string)

  @strictpure def printR(n: R): ST = printNumber("r", n.string)

  @strictpure def printNumber(prefix: String, s: String): ST = st"""$prefix"$s""""

  @pure def printString(s: String): ST = {
    return st""""${ops.StringOps(s).escapeST}""""
  }

  @pure def printOption[T](eType: ST, o: Option[T], f: T => ST): ST = {
    o match {
      case Some(v) => return st"Option.some(${f(v)})"
      case _ => return st"Option.none()"
    }
  }

  @pure def printMOption[T](eType: ST, o: MOption[T], f: T => ST): ST = {
    o match {
      case MSome(v) => return st"MOption.some(${f(v)})"
      case _ => return st"MOption.none()"
    }
  }

  @pure def printEither[L, R](lType: ST, rType: ST, o: Either[L, R], f0: L => ST, f1: R => ST): ST = {
    o match {
      case Either.Left(v) => return st"Either.left(${f0(v)})"
      case Either.Right(v) => return st"Either.right(${f1(v)})"
    }
  }

  @pure def printMEither[L, R](lType: ST, rType: ST, o: MEither[L, R], f0: L => ST, f1: R => ST): ST = {
    o match {
      case MEither.Left(v) => return st"MEither.left(${f0(v)})"
      case MEither.Right(v) => return st"MEither.right(${f1(v)})"
    }
  }

}

import ObjPrinter._

@msig trait ObjPrinter {

  def freshNum(): Z

  def write(content: ST): Unit

  def cache[@mut T](o: T, f: () => ST): ST

  def addMethod(tipe: ST, isStrictPure: B, body: ST): ST = {
    val num = freshNum()
    val ann: String = if (isStrictPure) "@strictpure" else "@pure"
    val name = st"object$num"
    write(
      st"""  $ann def $name: $tipe =
          |    $body""")
    return name
  }

  def printISZ[E](elementType: ST, s: ISZ[E], e: E => ST): ST = {
    return printIS(stZ, elementType, s, printZ _, e)
  }

  def printIS[@index I, E](indexType: ST, elementType: ST, s: IS[I, E], i: I => ST, e: E => ST): ST = {
    val f: () => ST = { () =>
      val t = st"IS[$indexType, $elementType]"
      val elements = s.map(e)
      if (elements.isEmpty) {
        return st"$t()"
      }
      var ll = ISZ[ST]()
      var l = ISZ[ST]()
      for (e <- elements) {
        l = l :+ e
        if (l.size == sGroupMax) {
          ll = ll :+ st"${(l, ", ")}"
          l = ISZ()
        }
      }
      if (l.size > 0) {
        ll = ll :+ st"${(l, ", ")}"
      }
      addMethod(t, T,
        if (ll.isEmpty) st"$t()"
        else if (ll.size == 1) st"$t(${(ll(0), ", ")})"
        else
          st"""$t(
              |  ${(ll, ",\n")})""")
    }
    return cache(s, f)
  }

  def printMS[@index I, @mut E](indexType: ST, elementType: ST, s: MS[I, E], i: I => ST, e: E => ST): ST = {
    val f: () => ST = { () =>
      val t = st"MS[$indexType, $elementType]"
      val elements = s.map(e)
      if (elements.isEmpty) {
        return st"$t()"
      }
      var ll = ISZ[ST]()
      var l = ISZ[ST]()
      for (e <- elements) {
        l = l :+ e
        if (l.size == sGroupMax) {
          ll = ll :+ st"${(l, ", ")}"
          l = ISZ()
        }
      }
      if (l.size > 0) {
        ll = ll :+ st"${(l, ", ")}"
      }
      addMethod(t, T,
        if (ll.isEmpty) st"$t()"
        else if (ll.size == 1) st"$t(${(ll(0), ", ")})"
        else
          st"""$t(
              |  ${(ll, ",\n")}""")
    }
    return cache(s, f)
  }

  def printMap[K, V](keyType: ST, valueType: ST, o: Map[K, V], k: K => ST, v: V => ST): ST = {
    if (o.isEmpty) {
      return st"Map.empty[$keyType, $valueType]"
    }
    val entries = printISZ[(K, V)](st"($keyType, $valueType)", o.entries, (p: (K, V)) => st"(${k(p._1)}, ${v(p._2)})")
    return st"(Map ++ $entries)"
  }

  def printSet[T](elementType: ST, o: Set[T], f: T => ST): ST = {
    if (o.isEmpty) {
      return st"Set.empty[$elementType]"
    }
    val elements = printISZ[T](elementType, o.elements, f)
    return st"(Set ++ $elements)"
  }

  def printHashMap[K, V](keyType: ST, valueType: ST, o: HashMap[K, V], k: K => ST, v: V => ST): ST = {
    if (o.isEmpty) {
      return st"HashMap.empty[$keyType, $valueType]"
    }
    val entries = printISZ[(K, V)](st"($keyType, $valueType)", o.entries, (p: (K, V)) => st"(${k(p._1)}, ${v(p._2)})")
    return st"(HashMap ++ $entries)"
  }

  def printHashSet[T](elementType: ST, o: HashSet[T], f: T => ST): ST = {
    if (o.isEmpty) {
      return st"HashSet.empty[$elementType]"
    }
    val elements = printISZ[T](elementType, o.elements, f)
    return st"(HashSet ++ $elements)"
  }

  def printHashSMap[K, V](keyType: ST, valueType: ST, o: HashSMap[K, V], k: K => ST, v: V => ST): ST = {
    if (o.isEmpty) {
      return st"HashSMap.empty[$keyType, $valueType]"
    }
    val entries = printISZ[(K, V)](st"($keyType, $valueType)", o.entries, (p: (K, V)) => st"(${k(p._1)}, ${v(p._2)})")
    return st"(HashSMap ++ $entries)"
  }

  def printHashSSet[T](elementType: ST, o: HashSSet[T], f: T => ST): ST = {
    if (o.isEmpty) {
      return st"HashSSet.empty[$elementType]"
    }
    val elements = printISZ[T](elementType, o.elements, f)
    return st"(HashSSet ++ $elements)"
  }

  def printStack[T](elementType: ST, o: Stack[T], f: T => ST): ST = {
    val elements = printISZ[T](elementType, o.elements, f)
    return st"Stack($elements)"
  }

  def printBag[T](elementType: ST, o: Bag[T], f: T => ST): ST = {
    val map = printMap(elementType, stZ, o.map, f, printZ _)
    return st"Bag($map)"
  }

  def printHashBag[T](elementType: ST, o: HashBag[T], f: T => ST): ST = {
    val map = printHashMap(elementType, stZ, o.map, f, printZ _)
    return st"HashBag($map)"
  }

  def printHashSBag[T](elementType: ST, o: HashSBag[T], f: T => ST): ST = {
    val map = printHashSMap(elementType, stZ, o.map, f, printZ _)
    return st"HashSBag($map)"
  }

  def printPoset[T](elementType: ST, o: Poset[T], e: T => ST): ST = {
    if (o.nodesInverse.isEmpty) {
      return st"Poset.empty[$elementType]"
    }
    val f = () => addMethod(st"Poset[$elementType]", F,
      st"""{
          |  var r = Poset.empty[$elementType]
          |  for (n <- o.nodesInverse) {
          |    r = r.addNode(n)
          |  }
          |  for (i <- 0 until o.nodesInverse.size) {
          |    o.parents.get(i) match {
          |      case Some(v) => r = Poset.Internal.addParents(r, i, v.elements)
          |      case _ =>
          |    }
          |  }
          |  return r
          |}""")
    return cache(o, f)
  }

  def printGraph[V, E](vType: ST, eType: ST, o: Graph[V, E], v: V => ST, e: E => ST): ST = {
    if (o.nodesInverse.isEmpty) {
      return st"Graph.empty${if (o.multi) "Multi" else ""}[$vType, $eType]"
    }

    def printEdge(edge: Graph.Internal.Edge[E]): ST = {
      edge match {
        case Graph.Internal.Edge.Plain(source, dest) => return st"Graph.Internal.Edge.Plain($source, $dest)"
        case Graph.Internal.Edge.Data(source, dest, data) => return st"Graph.Internal.Edge.Data($source, $dest, ${e(data)})"
      }
    }

    val f = () => addMethod(st"Graph[$vType, $eType]", F,
      st"""{
          |  var r = Graph.empty${if (o.multi) "Multi" else ""}[$vType, $eType]
          |  ${(for (n <- o.nodesInverse) yield st"r = r * ${v(n)}", "\n")}
          |  ${(for (es <- o.outgoingEdges.values; e <- es.elements) yield st"r = Graph.Internal.addEdge(r, ${printEdge(e)})", "\n")}
          |  return r
          |}""")
    return cache(o, f)
  }

  def printUnionFind[E](eType: ST, o: UnionFind[E], e: E => ST): ST = {
    if (o.size == 0) {
      return st"UnionFind.create[$eType](ISZ())"
    }
    val f = () => addMethod(st"UnionFind[$eType]", F,
      st"""{
          |  val elementsInverse = ${printISZ(eType, o.elementsInverse, e)}
          |  var elements = HashSMap.empty[T, UnionFind.Index]
          |  for (i <- z"0" until elementsInverse.size) {
          |    elements = elements + elementsInverse(i) ~> i
          |  }
          |  val parentsOf = ${printISZ(stZ, o.parentOf, printZ _)}
          |  val sizeOf = ${printISZ(stZ, o.sizeOf, printZ _)}
          |  return UnionFind[$eType](elements, elementsInverse, parentsOf, sizeOf)
          |}""")
    return cache(o, f)
  }

  def printMessage(o: message.Message): ST = {
    val f = () => addMethod(stMessage, T,
      st"""message.Message(
          |  message.Level.${o.level},
          |  ${printOption(stPosition, o.posOpt, printPosition _)},
          |  ${printString(o.kind)},
          |  ${printString(o.text)})""")
    return cache(o, f)
  }

  def printPosition(o: message.Position): ST = {
    o match {
      case o: message.FlatPos =>
        val f = () => addMethod(stPosition, T,
          st"""message.FlatPos(
              |  uriOpt = ${printOption(stString, o.uriOpt, printString _)},
              |  beginLine32 = ${printU32(o.beginLine32)},
              |  beginColumn32 = ${printU32(o.beginColumn32)},
              |  endLine32 = ${printU32(o.endLine32)},
              |  endColumn32 = ${printU32(o.endColumn32)},
              |  offset32 = ${printU32(o.offset32)},
              |  length32 = ${printU32(o.length32)})""")
        return cache(o, f)
      case o: message.PosInfo =>
        val f = () => st"message.PosInfo(${printDocInfo(o.info)}, ${printU64(o.offsetLength)})"
        return cache(o, f)
    }
  }

  def printDocInfo(o: message.DocInfo): ST = {
    val f = () => addMethod(stDocInfo, T,
      st"""message.DocInfo(
          |  ${printOption[String](stString, o.uriOpt, printString _)},
          |  ${printISZ(stU32, o.lineOffsets, printU32 _)})""")
    return cache(o, f)
  }

}
