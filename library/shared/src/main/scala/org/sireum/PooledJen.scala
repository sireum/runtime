// #Sireum
package org.sireum

object PooledJen extends App {

  def main(args: ISZ[String]): Z = {
    var n = 3
    println(s"Printing $n random Z values ...")
    for (data <- PooledJen.createRandomZPooledJen(n).toISZ) {
      val PooledJen.Data.Z(n) = data
      println(n)
    }
    println()

    n = 4
    println(s"Printing $n random Z values ...")
    for (data <- PooledJen.createRandomZPooledJen(-1).take(n)) { // does not pass Tipe because PooledJen is currently not a for-loop iterator
      val PooledJen.Data.Z(n) = data
      println(n)
    }
    println()
    return 0
  }

  def createRandomZPooledJen(n: Z): PooledJen = {  // n < 0 means infinite stream
    return PooledJen(IS[PooledJen.NodeIndex, PooledJen.Node](Node.RandomZJen(n)), MS[PooledJen.FunIndex, PooledJen.Fun]())
  }

  @range(min = 0) class NodeIndex
  @range(min = 0) class FunIndex

  @sig trait Data // top-level container class for generator payload

  object Data {
    @datatype class Z(val value: org.sireum.Z) extends Data
  }

  @datatype trait Node {
    def gen(pj: PooledJen, f: FunIndex): Jen.Action
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
    }


    // needed for for-loop/for-yield in the JVM
    @datatype class Mapped(val node: NodeIndex, val f: PooledJen.Data => PooledJen.Data) extends Node {
      override def gen(pj: PooledJen, g: FunIndex): Jen.Action = {
        pj.funs = pj.funs :+ Fun.Apply(g, f)
        return pj.nodes(node).gen(pj, pj.lastFunIndex)
      }

      override def string: String = {
        return s"Mapped"
      }
    }

    @datatype class Sliced(val node: NodeIndex, val start: Z, val end: Z) extends Node {
      override def gen(pj: PooledJen, g: FunIndex): Jen.Action = {
        var pj2 = pj
        pj2 = pj2(funs = pj2.funs :+ Fun.Sliced(g, start, end, 0))
        val r = pj2.nodes(node).gen(pj2, pj2.lastFunIndex)
        return r
      }

      override def string: String = {
        return if (end < 0) s"Sliced($start, ~)" else s"Sliced($start, $end)"
      }
    }

  }

  // Fun is a record because it may store some states (e.g., Sliced#count below)
  @record trait Fun {
    def appl(pj: PooledJen, o: Data): Jen.Action
  }

  object Fun {
    // needed for for-loop in the JVM
    @record class ForEach(val f: Data => Unit) extends Fun {
      def appl(pj: PooledJen, o: Data): Jen.Action = {
        f(o)
        return Jen.Continue
      }
    }

    @record class Apply(val g: FunIndex, val f: Data => Data) extends Fun {
      def appl(pj: PooledJen, o: Data): Jen.Action = {
        val fun = pj.funs(g)
        val r = fun.appl(pj, f(o))
        pj.funs(g) = fun
        return r
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
  def map(f: PooledJen.Data => PooledJen.Data): PooledJen = {
    return PooledJen(nodes :+ PooledJen.Node.Mapped(lastNodeIndex, f), funs)
  }

  // needed for for-loop/for-yield in the JVM
  def flatMap(f: PooledJen.Data => ISZ[PooledJen.Data]): PooledJen = {
    halt("TODO")
  }

  // needed for for-loop/for-yield in the JVM
  def withFilter(p: PooledJen.Data => B): PooledJen = {
    halt("TODO")
  }

  def toISZ: ISZ[PooledJen.Data] = {
    var r = ISZ[PooledJen.Data]()
    for (d <- this) { // does not pass Tipe because PooledJen is currently not a for-loop iterator
      r = r :+ d
    }
    return r
  }

  override def string: String = {
    if (nodes.isEmpty) {
      return "PooledJen()"
    }
    return nodes(lastNodeIndex).string
  }
}
