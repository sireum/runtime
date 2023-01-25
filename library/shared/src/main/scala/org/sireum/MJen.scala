// #Sireum
/*
The MIT License (MIT)

Copyright (c) 2016 Li Haoyi (haoyi.sg@gmail.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
DEALINGS IN THE SOFTWARE.
 */

package org.sireum

// Adapted from https://github.com/lihaoyi/geny

@msig trait MJen[T] {

  def generate(f: T => MJen.Action): MJen.Action

  def foreach[V](f: T => V): Unit = {
    def ap(o: T): MJen.Action = {
      f(o)
      return MJen.Continue
    }

    generate(ap _)
  }

  def find(f: T => B): MOption[T] = {
    var result: MOption[T] = MNone()

    def ap(o: T): MJen.Action = {
      val r = f(o)
      if (!r) {
        return MJen.Continue
      } else {
        result = MSome(o)
        return MJen.End
      }
    }

    generate(ap _)
    return result
  }

  def exists(f: T => B): B = {
    val r = find(f)
    return r.nonEmpty
  }

  @pure def contains(o: T): B = {
    return exists(e => e == o)
  }

  def forall(f: T => B): B = {
    def ap(o: T): B = {
      val r = f(o)
      return !r
    }
    val r = exists(ap _)
    return !r
  }

  @pure def count(): Z = {
    return countIf(_ => T)
  }

  def countIf(p: T => B): Z = {
    var result = 0

    def ap(o: T): MJen.Action = {
      val r = p(o)
      if (r) {
        result = result + 1
      }
      return MJen.Continue
    }

    generate(ap _)
    return result
  }

  @pure def fold[U](initial: U, f: (U, T) => U@pure): U = {
    return foldLeft(initial, f)
  }

  @pure def foldLeft[U](initial: U, f: (U, T) => U@pure): U = {
    var r = initial

    def ap(o: T): MJen.Action = {
      r = f(r, o)
      return MJen.Continue
    }

    generate(ap _)
    return r
  }

  @pure def reduce(f: (T, T) => T@pure): MOption[T] = {
    return reduceLeft(f)
  }

  @pure def reduceLeft(f: (T, T) => T@pure): MOption[T] = {
    var r: MOption[T] = MNone()

    def ap(o: T): MJen.Action = {
      r = r match {
        case MSome(prev) => MSome(f(prev, o))
        case _ => MSome(o)
      }
      return MJen.Continue
    }

    generate(ap _)
    return r
  }

  @pure def filter(p: T => B@pure): MJen[T] = {
    return MJen.Internal.Filtered(this, p)
  }

  def withFilter(p: T => B): MJen[T] = {
    return MJen.Internal.Filtered(this, p)
  }

  @pure def map[U](f: T => U@pure): MJen[U] = {
    return MJen.Internal.Mapped(this, f)
  }

  @pure def flatMap[U](f: T => MJen[U]@pure): MJen[U] = {
    return MJen.Internal.FlatMapped(this, f)
  }

  @pure def flatten[U](f: T => MJen[U]@pure): MJen[U] = {
    return this.flatMap(o => f(o))
  }

  @pure def slice(start: Z, end: Z): MJen[T] = {
    return MJen.Internal.Sliced(this, start, end)
  }

  @pure def take(n: Z): MJen[T] = {
    return slice(0, n)
  }

  @pure def drop(n: Z): MJen[T] = {
    return slice(n, -1)
  }

  @pure def takeWhile(p: T => B): MJen[T] = {
    return MJen.Internal.TakeWhile(this, p)
  }

  @pure def dropWhile(p: T => B): MJen[T] = {
    return MJen.Internal.DropWhile(this, p)
  }

  @pure def zipWithIndex: MJen[(T, Z)] = {
    return MJen.Internal.ZipWithIndexed(this)
  }

  @pure def zip[U](other: MJen[U]): MJen[(T, U)] = {
    return MJen.Internal.Zipped(this, other)
  }

  @pure def product[U](other: MJen[U]): MJen[(T, U)] = {
    return MJen.Internal.Product(this, other)
  }

  @pure def ++(other: MJen[T]): MJen[T] = {
    return MJen.Internal.Concat(this, other)
  }

  @pure def head: T = {
    return take(1).toMSZ(0)
  }

  @pure def headOption: MOption[T] = {
    val s = take(1).toMSZ
    return if (s.isEmpty) MNone() else MSome(s(0))
  }

  @pure def toMSZ: MSZ[T] = {
    val r = toMS(MSZ[T]())
    return r
  }

  @pure def toMS[@index I](init: MS[I, T]): MS[I, T] = {
    var r = init

    def append(o: T): Unit = {
      r = r :+ o
    }

    foreach(append _)
    return r
  }

  @pure def mkStringWrap(start: String, sep: String, end: String): String = {
    return st"$start${(toMSZ, sep)}$end".render
  }

  @pure def mkString(sep: String): String = {
    return mkStringWrap("", sep, "")
  }

}

object MJen {

  type Action = B
  val Continue: Action = T
  val End: Action = F

  object Internal {

    @record class ISImpl[@index I, @imm T](val s: IS[I, T]) extends MJen[T] {
      override def generate(f: T => MJen.Action): MJen.Action = {
        var last = MJen.Continue
        for (e <- s) {
          last = f(e)
          if (!last) {
            return MJen.End
          }
        }
        return last
      }

      override def string: String = {
        return s"MJen($s)"
      }
    }

    @record class MSImpl[@index I, T](val s: MS[I, T]) extends MJen[T] {
      override def generate(f: T => MJen.Action): MJen.Action = {
        var last = MJen.Continue
        for (e <- s) {
          last = f(e)
          if (!last) {
            return MJen.End
          }
        }
        return last
      }

      override def string: String = {
        return s"MJen($s)"
      }
    }

    @record class MapImpl[@imm K, @imm T](val m: Map[K, T]) extends MJen[(K, T)] {
      override def generate(f: ((K, T)) => MJen.Action): MJen.Action = {
        var last = MJen.Continue
        for (e <- m.entries) {
          last = f(e)
          if (!last) {
            return MJen.End
          }
        }
        return last
      }

      override def string: String = {
        return s"MJen($m)"
      }
    }

    @record class HashMapImpl[@imm K, @imm T](val m: HashMap[K, T]) extends MJen[(K, T)] {
      override def generate(f: ((K, T)) => MJen.Action): MJen.Action = {
        var last = MJen.Continue
        for (ms <- m.mapEntries) {
          if (ms.nonEmpty) {
            for (e <- ms.entries) {
              last = f(e)
              if (!last) {
                return MJen.End
              }
            }
          }
        }
        return last
      }

      override def string: String = {
        return s"MJen($m)"
      }
    }

    @record class Filtered[T](val gen: MJen[T], val p: T => B) extends MJen[T] {
      override def generate(f: T => MJen.Action): MJen.Action = {
        def ap(o: T): MJen.Action = {
          var r = p(o)
          if (r) {
            r = f(o)
            return r
          } else {
            return MJen.Continue
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.filter($p)"
      }
    }

    @record class Mapped[U, T](val gen: MJen[T], val f: T => U@pure) extends MJen[U] {
      override def generate(g: U => MJen.Action): MJen.Action = {
        def ap(o: T): MJen.Action = {
          val r = g(f(o))
          return r
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.map($f)"
      }
    }

    @record class FlatMapped[U, T](val gen: MJen[T], val f: T => MJen[U]@pure) extends MJen[U] {
      override def generate(g: U => MJen.Action): MJen.Action = {
        def ap(o: T): MJen.Action = {
          def ap2(o2: U): MJen.Action = {
            val r = g(o2)
            return r
          }

          val r = f(o).generate(ap2 _)
          return r
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.flatMap($f)"
      }
    }

    @record class Sliced[T](val gen: MJen[T], val start: Z, val end: Z) extends MJen[T] {
      def generate(f: T => MJen.Action): MJen.Action = {
        var count = 0

        def ap(o: T): MJen.Action = {
          if (count < start) {
            count = count + 1
            return MJen.Continue
          } else if (count < end || end < 0) {
            count = count + 1
            if (count != end) {
              return f(o)
            } else {
              f(o)
              return MJen.End
            }
          } else {
            return MJen.End
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return if (end < 0) s"$gen.slice($start, ~)" else s"$gen.slice($start, $end)"
      }
    }

    @record class TakeWhile[T](val gen: MJen[T], val p: T => B) extends MJen[T] {
      def generate(f: T => MJen.Action): MJen.Action = {
        def ap(o: T): MJen.Action = {
          var r = p(o)
          if (r) {
            r = f(o)
            return r
          } else {
            return MJen.End
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.takeWhile($p)"
      }
    }

    @record class DropWhile[T](val gen: MJen[T], val p: T => B) extends MJen[T] {
      def generate(f: T => MJen.Action): MJen.Action = {
        var started = F

        def ap(o: T): MJen.Action = {
          if (!started) {
            var r = p(o)
            if (r) {
              return MJen.Continue
            } else {
              started = T
              r = f(o)
              return r
            }
          } else {
            val r = f(o)
            return r
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.dropWhile($p)"
      }
    }

    @record class ZipWithIndexed[T](val gen: MJen[T]) extends MJen[(T, Z)] {
      def generate(f: ((T, Z)) => MJen.Action): MJen.Action = {
        var i = 0

        def ap(o: T): MJen.Action = {
          val r = f((o, i))
          i = i + 1
          return r
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.zipWithIndex"
      }
    }

    @record class Zipped[T, U](val gen: MJen[T], val gen2: MJen[U]) extends MJen[(T, U)] {
      def generate(f: ((T, U)) => MJen.Action): MJen.Action = {
        var g = gen
        var g2 = gen2
        var i = 1
        while (true) {
          (g.headOption, g2.headOption) match {
            case (MSome(h), MSome(h2)) =>
              val r = f((h, h2))
              if (r) {
                g = gen.drop(i)
                g2 = gen2.drop(i)
              } else {
                return MJen.End
              }
            case _ => return MJen.End
          }
          i = i + 1
        }
        return MJen.End
      }

      override def string: String = {
        return s"$gen.zip($gen2)"
      }
    }

    @record class Concat[T](val gen: MJen[T], val gen2: MJen[T]) extends MJen[T] {
      def generate(f: T => MJen.Action): MJen.Action = {
        var r = gen.generate(f)
        if (!r) {
          return MJen.End
        }
        r = gen2.generate(f)
        return r
      }

      override def string: String = {
        return s"$gen ++ $gen2"
      }
    }

    @record class Product[T, U](val gen: MJen[T], val gen2: MJen[U]) extends MJen[(T, U)] {
      def generate(f: ((T, U)) => MJen.Action): MJen.Action = {
        def ap(o: T): MJen.Action = {
          def ap2(o2: U): MJen.Action = {
            val r = f((o, o2))
            return r
          }

          val r = gen2.generate(ap2 _)
          return r
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.zip($gen2)"
      }
    }

  }

  @pure def IS[@index I, T](s: IS[I, T]): MJen[T] = {
    return Internal.ISImpl(s)
  }

  @pure def MS[@index I, T](s: MS[I, T]): MJen[T] = {
    return Internal.MSImpl(s)
  }

  @pure def Map[K, T](m: Map[K, T]): MJen[(K, T)] = {
    return Internal.MapImpl(m)
  }

  @pure def Set[T](s: Set[T]): MJen[T] = {
    return Internal.ISImpl(s.elements)
  }

  @pure def HashMap[K, T](m: HashMap[K, T]): MJen[(K, T)] = {
    return Internal.HashMapImpl(m)
  }

  @pure def HashSet[T](s: HashSet[T]): MJen[T] = {
    return Internal.HashMapImpl(s.map).map(p => p._1)
  }

  @pure def HashSMap[K, T](m: HashSMap[K, T]): MJen[(K, T)] = {
    return IS(m.keys).map(k => (k, m.get(k).get))
  }

  @pure def HashSSet[T](s: HashSSet[T]): MJen[T] = {
    return HashSMap(s.map).map(p => p._1)
  }
}
