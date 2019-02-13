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

@sig trait Generator[T] {

  def generate(f: T => Generator.Action): Generator.Action

  def foreach(f: T => Unit): Unit = {
    def ap(o: T): Generator.Action = {
      f(o)
      return T
    }

    generate(ap _)
  }

  @pure def find(f: T => B@pure): Option[T] = {
    var result: Option[T] = None()

    def ap(o: T): Generator.Action = {
      if (!f(o)) {
        return T
      } else {
        result = Some(o)
        return F
      }
    }

    generate(ap _)
    return result
  }

  @pure def exists(f: T => B@pure): B = {
    return find(f).nonEmpty
  }

  @pure def contains(o: T): B = {
    return exists(e => e == o)
  }

  @pure def forall(f: T => B@pure): B = {
    return !exists(e => !f(e))
  }

  @pure def count(): Z = {
    return countIf(_ => T)
  }

  @pure def countIf(p: T => B@pure): Z = {
    var result = 0

    def ap(o: T): Generator.Action = {
      if (p(o)) {
        result = result + 1
      }
      return T
    }

    generate(ap _)
    return result
  }

  @pure def fold[U](initial: U, f: (U, T) => U@pure): U = {
    return foldLeft(initial, f)
  }

  @pure def foldLeft[U](initial: U, f: (U, T) => U@pure): U = {
    var r = initial

    def ap(o: T): Generator.Action = {
      r = f(r, o)
      return T
    }

    generate(ap _)
    return r
  }

  @pure def reduce(f: (T, T) => T@pure): Option[T] = {
    return reduceLeft(f)
  }

  @pure def reduceLeft(f: (T, T) => T@pure): Option[T] = {
    var r: Option[T] = None()

    def ap(o: T): Generator.Action = {
      r = r match {
        case Some(prev) => Some(f(prev, o))
        case _ => Some(o)
      }
      return T
    }

    generate(ap _)
    return r
  }

  @pure def filter(p: T => B@pure): Generator[T] = {
    return Generator.Internal.Filtered(this, p)
  }

  def withFilter(p: T => B): Generator[T] = {
    return Generator.Internal.Filtered(this, p)
  }

  @pure def map[U](f: T => U@pure): Generator[U] = {
    return Generator.Internal.Mapped(this, f)
  }

  @pure def flatMap[U](f: T => Generator[U]@pure): Generator[U] = {
    return Generator.Internal.FlatMapped(this, f)
  }

  @pure def flatten[U](f: T => Generator[U]@pure): Generator[U] = {
    return this.flatMap(o => f(o))
  }

  @pure def slice(start: Z, end: Z): Generator[T] = {
    return Generator.Internal.Sliced(this, start, end)
  }

  @pure def take(n: Z): Generator[T] = {
    return slice(0, n)
  }

  @pure def drop(n: Z): Generator[T] = {
    return slice(n, -1)
  }

  @pure def takeWhile(p: T => B@pure): Generator[T] = {
    return Generator.Internal.TakeWhile(this, p)
  }

  @pure def dropWhile(p: T => B@pure): Generator[T] = {
    return Generator.Internal.DropWhile(this, p)
  }

  @pure def zipWithIndex: Generator[(T, Z)] = {
    return Generator.Internal.ZipWithIndexed(this)
  }

  @pure def zip[U](other: Generator[U]): Generator[(T, U)] = {
    return Generator.Internal.Zipped(this, other)
  }

  @pure def product[U](other: Generator[U]): Generator[(T, U)] = {
    return Generator.Internal.Product(this, other)
  }

  @pure def ++(other: Generator[T]): Generator[T] = {
    return Generator.Internal.Concat(this, other)
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

  @pure def toIS[I](init: IS[I, T]): IS[I, T] = {
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

object Generator {

  type Action = B

  object Internal {

    @datatype class ISImpl[I, T](s: IS[I, T]) extends Generator[T] {
      override def generate(f: T => Generator.Action): Generator.Action = {
        var last: Generator.Action = T
        for (e <- s if last) {
          last = f(e)
        }
        return last
      }

      override def string: String = {
        return s"Generator($s)"
      }
    }

    @datatype class MapImpl[K, T](m: Map[K, T]) extends Generator[(K, T)] {
      override def generate(f: ((K, T)) => Generator.Action): Generator.Action = {
        var last: Generator.Action = T
        for (e <- m.entries if last) {
          last = f(e)
        }
        return last
      }

      override def string: String = {
        return s"Generator($m)"
      }
    }

    @datatype class HashMapImpl[K, T](m: HashMap[K, T]) extends Generator[(K, T)] {
      override def generate(f: ((K, T)) => Generator.Action): Generator.Action = {
        var last: Generator.Action = T
        for (ms <- m.mapEntries if last) {
          if (ms.nonEmpty) {
            for (e <- ms.entries if last) {
              last = f(e)
            }
          }
        }
        return last
      }

      override def string: String = {
        return s"Generator($m)"
      }
    }

    @datatype class Filtered[T](gen: Generator[T], p: T => B) extends Generator[T] {
      override def generate(f: T => Generator.Action): Generator.Action = {
        def ap(o: T): Generator.Action = {
          if (p(o)) {
            val r = f(o)
            return r
          } else {
            return T
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.filter($p)"
      }
    }

    @datatype class Mapped[U, T](gen: Generator[T], f: T => U@pure) extends Generator[U] {
      override def generate(g: U => Generator.Action): Generator.Action = {
        def ap(o: T): Generator.Action = {
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

    @datatype class FlatMapped[U, T](gen: Generator[T], f: T => Generator[U]) extends Generator[U] {
      override def generate(g: U => Generator.Action): Generator.Action = {
        def ap(o: T): Generator.Action = {
          def ap2(o2: U): Generator.Action = {
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

    @datatype class Sliced[T](gen: Generator[T], start: Z, end: Z) extends Generator[T] {
      def generate(f: T => Generator.Action): Generator.Action = {
        var count = 0

        def ap(o: T): Generator.Action = {
          if (count < start) {
            count = count + 1
            return T
          } else if (count < end || end < 0) {
            count = count + 1
            if (count != end) {
              return f(o)
            } else {
              f(o)
              return F
            }
          } else {
            return F
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return if (end < 0) s"$gen.slice($start, ~)" else s"$gen.slice($start, $end)"
      }
    }

    @datatype class TakeWhile[T](gen: Generator[T], p: T => B) extends Generator[T] {
      def generate(f: T => Generator.Action): Generator.Action = {
        def ap(o: T): Generator.Action = {
          if (p(o)) {
            val r = f(o)
            return r
          } else {
            return F
          }
        }

        val r = gen.generate(ap _)
        return r
      }

      override def string: String = {
        return s"$gen.takeWhile($p)"
      }
    }

    @datatype class DropWhile[T](gen: Generator[T], p: T => B) extends Generator[T] {
      def generate(f: T => Generator.Action): Generator.Action = {
        var started = F

        def ap(o: T): Generator.Action = {
          if (!started) {
            if (p(o)) {
              return T
            } else {
              started = T
              val r = f(o)
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

    @datatype class ZipWithIndexed[T](gen: Generator[T]) extends Generator[(T, Z)] {
      def generate(f: ((T, Z)) => Generator.Action): Generator.Action = {
        var i = 0

        def ap(o: T): Generator.Action = {
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

    @datatype class Zipped[T, U](gen: Generator[T], gen2: Generator[U]) extends Generator[(T, U)] {
      def generate(f: ((T, U)) => Generator.Action): Generator.Action = {
        (gen.headOption, gen2.headOption) match {
          case (Some(h), Some(h2)) =>
            val r = f((h, h2))
            if (r) {
              return Zipped(gen.drop(1), gen2.drop(1)).generate(f)
            }
          case _ =>
        }
        return F
      }

      override def string: String = {
        return s"$gen.zip($gen2)"
      }
    }

    @datatype class Concat[T](gen: Generator[T], gen2: Generator[T]) extends Generator[T] {
      def generate(f: T => Generator.Action): Generator.Action = {
        var r = gen.generate(f)
        if (r == F) {
          return F
        }
        r = gen2.generate(f)
        return r
      }

      override def string: String = {
        return s"$gen ++ $gen2"
      }
    }

    @datatype class Product[T, U](gen: Generator[T], gen2: Generator[U]) extends Generator[(T, U)] {
      def generate(f: ((T, U)) => Generator.Action): Generator.Action = {
        def ap(o: T): Generator.Action = {
          def ap2(o2: U): Generator.Action = {
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

  @pure def is[I, T](s: IS[I, T]): Generator[T] = {
    return Internal.ISImpl(s)
  }

  @pure def map[K, T](m: Map[K, T]): Generator[(K, T)] = {
    return Internal.MapImpl(m)
  }

  @pure def set[T](s: Set[T]): Generator[T] = {
    return Internal.MapImpl(s.map).map(p => p._1)
  }

  @pure def hashMap[K, T](m: HashMap[K, T]): Generator[(K, T)] = {
    return Internal.HashMapImpl(m)
  }

  @pure def hashSet[T](s: HashSet[T]): Generator[T] = {
    return Internal.HashMapImpl(s.map).map(p => p._1)
  }

  @pure def hashSMap[K, T](m: HashSMap[K, T]): Generator[(K, T)] = {
    return set(m.keys).map(k => (k, m.get(k).get))
  }

  @pure def hashSSet[T](s: HashSSet[T]): Generator[T] = {
    return hashSMap(s.map).map(p => p._1)
  }
}
