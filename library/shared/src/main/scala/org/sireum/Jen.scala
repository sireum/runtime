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

@sig trait Jen[T] {

  def generate(f: T => Jen.Action): Jen.Action

  def foreach[V](f: T => V): Unit = {
    def ap(o: T): Jen.Action = {
      f(o)
      return Jen.Continue
    }

    generate(ap _)
  }

  def find(f: T => B): Option[T] = {
    var result: Option[T] = None()

    def ap(o: T): Jen.Action = {
      val r = f(o)
      if (!r) {
        return Jen.Continue
      } else {
        result = Some(o)
        return Jen.End
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

    def ap(o: T): Jen.Action = {
      val r = p(o)
      if (r) {
        result = result + 1
      }
      return Jen.Continue
    }

    generate(ap _)
    return result
  }

  @pure def fold[U](initial: U, f: (U, T) => U@pure): U = {
    return foldLeft(initial, f)
  }

  @pure def foldLeft[U](initial: U, f: (U, T) => U@pure): U = {
    var r = initial

    def ap(o: T): Jen.Action = {
      r = f(r, o)
      return Jen.Continue
    }

    generate(ap _)
    return r
  }

  @pure def reduce(f: (T, T) => T@pure): Option[T] = {
    return reduceLeft(f)
  }

  @pure def reduceLeft(f: (T, T) => T@pure): Option[T] = {
    var r: Option[T] = None()

    def ap(o: T): Jen.Action = {
      r = r match {
        case Some(prev) => Some(f(prev, o))
        case _ => Some(o)
      }
      return Jen.Continue
    }

    generate(ap _)
    return r
  }

  @pure def filter(p: T => B@pure): Jen[T] = {
    return Jen.Internal.Filtered(this, p)
  }

  def withFilter(p: T => B): Jen[T] = {
    return Jen.Internal.Filtered(this, p)
  }

  @pure def map[U](f: T => U@pure): Jen[U] = {
    return Jen.Internal.Mapped(this, f)
  }

  @pure def flatMap[U](f: T => Jen[U]@pure): Jen[U] = {
    return Jen.Internal.FlatMapped(this, f)
  }

  @pure def flatten[U](f: T => Jen[U]@pure): Jen[U] = {
    return this.flatMap(o => f(o))
  }

  @pure def slice(start: Z, end: Z): Jen[T] = {
    return Jen.Internal.Sliced(this, start, end)
  }

  @pure def take(n: Z): Jen[T] = {
    return slice(0, n)
  }

  @pure def drop(n: Z): Jen[T] = {
    return slice(n, -1)
  }

  @pure def takeWhile(p: T => B): Jen[T] = {
    return Jen.Internal.TakeWhile(this, p)
  }

  @pure def dropWhile(p: T => B): Jen[T] = {
    return Jen.Internal.DropWhile(this, p)
  }

  @pure def zipWithIndex: Jen[(T, Z)] = {
    return Jen.Internal.ZipWithIndexed(this)
  }

  @pure def zip[U](other: Jen[U]): Jen[(T, U)] = {
    return Jen.Internal.Zipped(this, other)
  }

  @pure def product[U](other: Jen[U]): Jen[(T, U)] = {
    return Jen.Internal.Product(this, other)
  }

  @pure def ++(other: Jen[T]): Jen[T] = {
    return Jen.Internal.Concat(this, other)
  }

  @pure def head: T = {
    return take(1).toISZ(0)
  }

  @pure def headOption: Option[T] = {
    val s = take(1).toISZ
    return if (s.isEmpty) None() else Some(s(0))
  }

  @pure def toISZ: ISZ[T] = {
    val r = toIS(ISZ[T]())
    return r
  }

  @pure def toIS[@index I](init: IS[I, T]): IS[I, T] = {
    var r = init

    def append(o: T): Unit = {
      r = r :+ o
    }

    foreach(append _)
    return r
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
    return st"$start${(toISZ, sep)}$end".render
  }

  @pure def mkString(sep: String): String = {
    return mkStringWrap("", sep, "")
  }

}

object Jen {

  type Action = B
  val Continue: Action = T
  val End: Action = F

  object Internal {

    @datatype class ISImpl[@index I, T](val s: IS[I, T]) extends Jen[T] {
      override def generate(f: T => Jen.Action): Jen.Action = {
        var last = Jen.Continue
        for (e <- s) {
          last = f(e)
          if (!last) {
            return Jen.End
          }
        }
        return last
      }

      override def string: String = {
        return s"Jen($s)"
      }
    }

    @datatype class MapImpl[K, T](val m: Map[K, T]) extends Jen[(K, T)] {
      override def generate(f: ((K, T)) => Jen.Action): Jen.Action = {
        var last = Jen.Continue
        for (e <- m.entries) {
          last = f(e)
          if (!last) {
            return Jen.End
          }
        }
        return last
      }

      override def string: String = {
        return s"Jen($m)"
      }
    }

    @datatype class HashMapImpl[K, T](val m: HashMap[K, T]) extends Jen[(K, T)] {
      override def generate(f: ((K, T)) => Jen.Action): Jen.Action = {
        var last = Jen.Continue
        for (ms <- m.mapEntries) {
          if (ms.nonEmpty) {
            for (e <- ms.entries) {
              last = f(e)
              if (!last) {
                return Jen.End
              }
            }
          }
        }
        return last
      }

      override def string: String = {
        return s"Jen($m)"
      }
    }

    @datatype class Filtered[T](val gen: Jen[T], val p: T => B) extends Jen[T] {
      override def generate(f: T => Jen.Action): Jen.Action = {
        def ap(o: T): Jen.Action = {
          var r = p(o)
          if (r) {
            r = f(o)
            return r
          } else {
            return Jen.Continue
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.filter($p)"
      }
    }

    @datatype class Mapped[U, T](val gen: Jen[T], val f: T => U@pure) extends Jen[U] {
      override def generate(g: U => Jen.Action): Jen.Action = {
        def ap(o: T): Jen.Action = {
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

    @datatype class FlatMapped[U, T](val gen: Jen[T], val f: T => Jen[U]@pure) extends Jen[U] {
      override def generate(g: U => Jen.Action): Jen.Action = {
        def ap(o: T): Jen.Action = {
          def ap2(o2: U): Jen.Action = {
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

    @datatype class Sliced[T](val gen: Jen[T], val start: Z, val end: Z) extends Jen[T] {
      def generate(f: T => Jen.Action): Jen.Action = {
        var count = 0

        def ap(o: T): Jen.Action = {
          if (count < start) {
            count = count + 1
            return Jen.Continue
          } else if (count < end || end < 0) {
            count = count + 1
            if (count != end) {
              return f(o)
            } else {
              f(o)
              return Jen.End
            }
          } else {
            return Jen.End
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return if (end < 0) s"$gen.slice($start, ~)" else s"$gen.slice($start, $end)"
      }
    }

    @datatype class TakeWhile[T](val gen: Jen[T], val p: T => B) extends Jen[T] {
      def generate(f: T => Jen.Action): Jen.Action = {
        def ap(o: T): Jen.Action = {
          var r = p(o)
          if (r) {
            r = f(o)
            return r
          } else {
            return Jen.End
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.takeWhile($p)"
      }
    }

    @datatype class DropWhile[T](val gen: Jen[T], val p: T => B) extends Jen[T] {
      def generate(f: T => Jen.Action): Jen.Action = {
        var started = F

        def ap(o: T): Jen.Action = {
          if (!started) {
            var r = p(o)
            if (r) {
              return Jen.Continue
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

    @datatype class ZipWithIndexed[T](val gen: Jen[T]) extends Jen[(T, Z)] {
      def generate(f: ((T, Z)) => Jen.Action): Jen.Action = {
        var i = 0

        def ap(o: T): Jen.Action = {
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

    @datatype class Zipped[T, U](val gen: Jen[T], val gen2: Jen[U]) extends Jen[(T, U)] {
      def generate(f: ((T, U)) => Jen.Action): Jen.Action = {
        var g = gen
        var g2 = gen2
        var i = 1
        while (true) {
          (g.headOption, g2.headOption) match {
            case (Some(h), Some(h2)) =>
              val r = f((h, h2))
              if (r) {
                g = gen.drop(i)
                g2 = gen2.drop(i)
              } else {
                return Jen.End
              }
            case _ => return Jen.End
          }
          i = i + 1
        }
        return Jen.End
      }

      override def string: String = {
        return s"$gen.zip($gen2)"
      }
    }

    @datatype class Concat[T](val gen: Jen[T], val gen2: Jen[T]) extends Jen[T] {
      def generate(f: T => Jen.Action): Jen.Action = {
        var r = gen.generate(f)
        if (!r) {
          return Jen.End
        }
        r = gen2.generate(f)
        return r
      }

      override def string: String = {
        return s"$gen ++ $gen2"
      }
    }

    @datatype class Product[T, U](val gen: Jen[T], val gen2: Jen[U]) extends Jen[(T, U)] {
      def generate(f: ((T, U)) => Jen.Action): Jen.Action = {
        def ap(o: T): Jen.Action = {
          def ap2(o2: U): Jen.Action = {
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

  @pure def IS[@index I, T](s: IS[I, T]): Jen[T] = {
    return Internal.ISImpl(s)
  }

  @pure def Map[K, T](m: Map[K, T]): Jen[(K, T)] = {
    return Internal.MapImpl(m)
  }

  @pure def Set[T](s: Set[T]): Jen[T] = {
    return Internal.ISImpl(s.elements)
  }

  @pure def HashMap[K, T](m: HashMap[K, T]): Jen[(K, T)] = {
    return Internal.HashMapImpl(m)
  }

  @pure def HashSet[T](s: HashSet[T]): Jen[T] = {
    return Internal.HashMapImpl(s.map).map(p => p._1)
  }

  @pure def HashSMap[K, T](m: HashSMap[K, T]): Jen[(K, T)] = {
    return IS(m.keys).map(k => (k, m.get(k).get))
  }

  @pure def HashSSet[T](s: HashSSet[T]): Jen[T] = {
    return HashSMap(s.map).map(p => p._1)
  }
}
