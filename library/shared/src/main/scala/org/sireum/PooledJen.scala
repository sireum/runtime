// #Sireum
package org.sireum

object PooledJen extends App {

  def main(args: ISZ[String]): Z = {
    var n = 3

    println(s"Printing $n random Z values (ISZ) ...")
    for (data <- PooledJen.createRandomZs(n)) {
      println(data)
    }
    println()

    println(s"Printing $n random Z values (PooledJen/for-loop) ...")
    for (data <- PooledJen.createRandomZPooledJen(n).toISZ) { // without toISZ, this does not pass Tipe because PooledJen is currently not a for-loop iterator
      val PooledJen.Data.Z(v) = data
      println(v)
    }
    println()

    n = 4
    println(s"Printing $n random Z values (PooledJen/for-loop) ...")
    for (data <- PooledJen.createRandomZPooledJen(-1).take(n).toISZ) { // without toISZ, this does not pass Tipe because PooledJen is currently not a for-loop iterator
      val PooledJen.Data.Z(v) = data
      println(v)
    }
    println()

    println(s"Printing $n random Z values (PooledJen/for-yield) ...")
    var vs: PooledJen = for (data <- PooledJen.createRandomZPooledJen(-1).take(n)) yield data
    for (data <- vs.toISZ) {
      val PooledJen.Data.Z(v) = data
      println(v)
    }
    println()

    println(s"Printing $n*$n random Z values (PooledJen/for-yields) ...")
    vs = for (data <- PooledJen.createRandomZPooledJen(-1).take(n);
              data2 <- PooledJen.createRandomZPooledJen(-1).take(n)) yield
      Data.PairZ(data.asInstanceOf[Data.Z].value, data2.asInstanceOf[Data.Z].value)

    for (v <- vs.toISZ) {
      val PooledJen.Data.PairZ(v1, v2) = v
      println(s"($v1, $v2)")
    }
    return 0
  }

  def createRandomZs(n: Z): ISZ[Z] = {
    assert(n >= 0)
    var r = ISZ[Z]()
    for (_ <- 0 until n) {
      r = r :+ Z.random
    }
    return r
  }

  def createRandomZPooledJen(n: Z): PooledJen = {  // n < 0 means infinite stream
    return PooledJen(IS[PooledJen.NodeIndex, PooledJen.Node](Node.RandomZJen(n)), MS[PooledJen.FunIndex, PooledJen.Fun]())
  }

  @range(min = 0) class NodeIndex
  @range(min = 0) class FunIndex

  @sig trait Data // top-level container class for generator payload

  object Data {
    @datatype class Z(val value: org.sireum.Z) extends Data
    @datatype class PairZ(val value1: org.sireum.Z, val value2: org.sireum.Z) extends Data
  }

  @datatype trait Node {
    def gen(pj: PooledJen, f: FunIndex): Jen.Action
    def addIndex(offset: NodeIndex): Node
  }

  object Node {
    @datatype class RandomZJen(val num: Z) extends PooledJen.Node {
      def gen(pj: PooledJen, f: PooledJen.FunIndex): Jen.Action = {
        var last = Jen.Continue
        if (num >= 0) {
          for (_ <- 0 until num) {
            val fun = pj.funs(f)
            last = fun.appl(pj, PooledJen.Data.Z(Z.random))
            pj.funs(f) = fun
            if (!last) {
              return Jen.End
            }
          }
        } else {
          while (T) {
            val fun = pj.funs(f)
            last = fun.appl(pj, PooledJen.Data.Z(Z.random))
            pj.funs(f) = fun
            if (!last) {
              return Jen.End
            }
          }
        }
        return last
      }

      def addIndex(offset: NodeIndex): Node = {
        return this
      }

      override def string: String = {
        return s"RandomZJen($num)"
      }
    }

    @datatype class Sliced(val node: NodeIndex, val start: Z, val end: Z) extends Node {
      override def gen(pj: PooledJen, g: FunIndex): Jen.Action = {
        pj.funs = pj.funs :+ Fun.Sliced(g, start, end, 0)
        val r = pj.nodes(node).gen(pj, pj.lastFunIndex)
        return r
      }

      def addIndex(offset: NodeIndex): Node = {
        return Sliced(node + offset, start, end)
      }

      override def string: String = {
        return if (end < 0) s"Sliced($node, $start, ~)" else s"Sliced($node, $start, $end)"
      }
    }

    // needed for for-loop/for-yield in the JVM
    // see: https://stackoverflow.com/questions/14598990/confused-with-the-for-comprehension-to-flatmap-map-transformation
    @datatype class Mapped(val node: NodeIndex, val f: PooledJen.Data => PooledJen.Data) extends Node {
      override def gen(pj: PooledJen, g: FunIndex): Jen.Action = {
        pj.funs = pj.funs :+ Fun.Mapped(g, f)
        return pj.nodes(node).gen(pj, pj.lastFunIndex)
      }

      def addIndex(offset: NodeIndex): Node = {
        return Mapped(node + offset, f)
      }

      override def string: String = {
        return s"Mapped($node)"
      }
    }

    // needed for for-loop/for-yield in the JVM
    // see: https://stackoverflow.com/questions/14598990/confused-with-the-for-comprehension-to-flatmap-map-transformation
    @datatype class FlatMapped(val node: NodeIndex, val f: Data => PooledJen) extends Node {
      override def gen(pj: PooledJen, g: FunIndex): Jen.Action = {
        pj.funs = pj.funs :+ Fun.FlatMapped(g, f)
        val r = pj.nodes(node).gen(pj, pj.lastFunIndex)
        return r
      }

      def addIndex(offset: NodeIndex): Node = {
        return FlatMapped(node + offset, f)
      }

      override def string: String = {
        return s"FlatMapped"
      }
    }
  }

  // Fun is a record because it may store some states (e.g., Sliced#count below)
  @record trait Fun {
    def appl(pj: PooledJen, o: Data): Jen.Action
    def addIndex(offset: FunIndex): Fun
  }

  object Fun {

    @record class Acc(var data: ISZ[Data]) extends Fun {
      def appl(pj: PooledJen, o: Data): Jen.Action = {
        data = data :+ o
        return Jen.Continue
      }

      def addIndex(offset: FunIndex): Fun = {
        return Acc(ISZ())
      }

      override def string: String = {
        return s"Acc($data)"
      }
    }

    @record class Sliced(val g: FunIndex, val start: Z, val end: Z, var count: Z) extends Fun {
      def appl(pj: PooledJen, o: Data): Jen.Action = {
        if (count < start) {
          count = count + 1
          return Jen.Continue
        } else if (count < end || end < 0) {
          count = count + 1
          if (count != end) {
            val fun = pj.funs(g)
            val r = fun.appl(pj, o)
            pj.funs(g) = fun
            return r
          } else {
            val fun = pj.funs(g)
            fun.appl(pj, o)
            pj.funs(g) = fun
            return Jen.End
          }
        } else {
          return Jen.End
        }
      }

      def addIndex(offset: FunIndex): Fun = {
        return Sliced(g + offset, start, end, 0)
      }

      override def string: String = {
        return s"Sliced($g, $start, $end, $count)"
      }
    }

    // needed for for-loop/for-yield in the JVM
    @record class ForEach(val f: Data => Unit) extends Fun {
      def appl(pj: PooledJen, o: Data): Jen.Action = {
        f(o)
        return Jen.Continue
      }

      def addIndex(offset: FunIndex): Fun = {
        return this
      }

      override def string: String = {
        return s"ForEach"
      }
    }

    // needed for for-loop/for-yield in the JVM
    // see: https://stackoverflow.com/questions/14598990/confused-with-the-for-comprehension-to-flatmap-map-transformation
    @record class Mapped(val g: FunIndex, val f: Data => Data) extends Fun {
      def appl(pj: PooledJen, o: Data): Jen.Action = {
        val fun = pj.funs(g)
        val r = fun.appl(pj, f(o))
        pj.funs(g) = fun
        return r
      }

      def addIndex(offset: FunIndex): Fun = {
        return Mapped(g + offset, f)
      }

      override def string: String = {
        return s"Mapped($g)"
      }
    }

    // needed for for-loop/for-yield in the JVM
    // see: https://stackoverflow.com/questions/14598990/confused-with-the-for-comprehension-to-flatmap-map-transformation
    @record class FlatMapped(val g: FunIndex, val f: Data => PooledJen) extends Fun {
      def appl(pj: PooledJen, o: Data): Jen.Action = {
        var last = Jen.Continue
        for (e <- f(o)) {
          val fun = pj.funs(g)
          last = fun.appl(pj, e)
          pj.funs(g) = fun
          if (!last) {
            return last
          }
        }
        return last
      }

      def addIndex(offset: FunIndex): Fun = {
        return FlatMapped(g + offset, f)
      }

      override def string: String = {
        return s"FlatMapped($g)"
      }
    }

  }
}

@record class PooledJen(val nodes: IS[PooledJen.NodeIndex, PooledJen.Node],
                        var funs: MS[PooledJen.FunIndex, PooledJen.Fun]) {

  @strictpure def lastNodeIndex: PooledJen.NodeIndex = PooledJen.NodeIndex.fromZ(nodes.size - 1)

  def lastFunIndex: PooledJen.FunIndex = {
    return PooledJen.FunIndex.fromZ(funs.size - 1)
  }

  def generate(f: PooledJen.FunIndex): Jen.Action = {
    if (nodes.isEmpty) {
      return Jen.End
    }
    return nodes(lastNodeIndex).gen(this, f)
  }

  def take(n: Z): PooledJen = {
    return PooledJen(nodes :+ PooledJen.Node.Sliced(lastNodeIndex, 0, n), funs)
  }

  // needed for for-loop in the JVM
  def foreach(f: PooledJen.Data => Unit): Unit = {
    funs = funs :+ PooledJen.Fun.ForEach(f)
    generate(lastFunIndex)
  }

  // needed for for-loop/for-yield in the JVM
  // see: https://stackoverflow.com/questions/14598990/confused-with-the-for-comprehension-to-flatmap-map-transformation
  def map(f: PooledJen.Data => PooledJen.Data): PooledJen = {
    return PooledJen(nodes :+ PooledJen.Node.Mapped(lastNodeIndex, f), funs)
  }

  // needed for for-loop/for-yield in the JVM
  // see: https://stackoverflow.com/questions/14598990/confused-with-the-for-comprehension-to-flatmap-map-transformation
  def flatMap(f: PooledJen.Data => PooledJen): PooledJen = {
    return PooledJen(nodes :+ PooledJen.Node.FlatMapped(lastNodeIndex, f), funs)
  }

  // needed for for-loop/for-yield in the JVM
  def withFilter(p: PooledJen.Data => B): PooledJen = {
    halt("TODO")
  }

  def toISZ: ISZ[PooledJen.Data] = {
    val index = PooledJen.FunIndex.fromZ(funs.size)
    funs = funs :+ PooledJen.Fun.Acc(ISZ())
    generate(index)
    return funs(index).asInstanceOf[PooledJen.Fun.Acc].data
  }

  override def string: String = {
    if (nodes.isEmpty) {
      return "PooledJen()"
    }
    return nodes(lastNodeIndex).string
  }
}
