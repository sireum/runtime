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

@msig trait MGenerator[T] {

  def generate(f: T => MGenerator.Action): MGenerator.Action

  def foreach(f: T => Unit): Unit = {
    def ap(o: T): MGenerator.Action = {
      f(o)
      return T
    }
    generate(ap _)
  }

  @pure def find(f: T => B@pure): Option[T] = {
    var result: Option[T] = None()
    def ap(o: T): MGenerator.Action = {
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
    def ap(o: T): MGenerator.Action = {
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
    def ap(o: T): MGenerator.Action = {
      r = f(r, o)
      return T
    }
    generate(ap _)
    return r
  }

  @pure def reduce(f: (T, T) => T@pure): MOption[T] = {
    return reduceLeft(f)
  }

  @pure def reduceLeft(f: (T, T) => T@pure): MOption[T] = {
    var r: MOption[T] = MNone()

    def ap(o: T): MGenerator.Action = {
      r = r match {
        case MSome(prev) => MSome(f(prev, o))
        case _ => MSome(o)
      }
      return T
    }

    generate(ap _)
    return r
  }

  @pure def filter(p: T => B@pure): MGenerator[T] = {
    return MGenerator.Internal.Filtered(this, p)
  }

  def withFilter(p: T => B): MGenerator[T] = {
    return MGenerator.Internal.Filtered(this, p)
  }

  @pure def map[U](f: T => U@pure): MGenerator[U] = {
    return MGenerator.Internal.Mapped(this, f)
  }

  @pure def flatMap[U](f: T => MGenerator[U]@pure): MGenerator[U] = {
    return MGenerator.Internal.FlatMapped(this, f)
  }

  @pure def flatten[U](f: T => MGenerator[U]@pure): MGenerator[U] = {
    return this.flatMap(o => f(o))
  }

  @pure def slice(start: Z, end: Z): MGenerator[T] = {
    return MGenerator.Internal.Sliced(this, start, end)
  }

  @pure def take(n: Z): MGenerator[T] = {
    return slice(0, n)
  }

  @pure def drop(n: Z): MGenerator[T] = {
    return slice(n, -1)
  }

  @pure def takeWhile(p: T => B@pure): MGenerator[T] = {
    return MGenerator.Internal.TakeWhile(this, p)
  }

  @pure def dropWhile(p: T => B@pure): MGenerator[T] = {
    return MGenerator.Internal.DropWhile(this, p)
  }

  @pure def zipWithIndex: MGenerator[(T, Z)] = {
    return MGenerator.Internal.ZipWithIndexed(this)
  }

  @pure def zip[U](other: MGenerator[U]): MGenerator[(T, U)] = {
    return MGenerator.Internal.Zipped(this, other)
  }

  @pure def product[U](other: MGenerator[U]): MGenerator[(T, U)] = {
    return MGenerator.Internal.Product(this, other)
  }

  @pure def ++(other: MGenerator[T]): MGenerator[T] = {
    return MGenerator.Internal.Concat(this, other)
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

  @pure def toMS[I](init: MS[I, T]): MS[I, T] = {
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

object MGenerator {

  type Action = B

  object Internal {

    @record class ISImpl[I, T](s: IS[I, T]) extends MGenerator[T] {
      override def generate(f: T => MGenerator.Action): MGenerator.Action = {
        var last: MGenerator.Action = T
        for (e <- s if last) {
          last = f(e)
        }
        return last
      }

      override def string: String = {
        return s"MGenerator($s)"
      }
    }

    @record class MSImpl[I, T](s: MS[I, T]) extends MGenerator[T] {
      override def generate(f: T => MGenerator.Action): MGenerator.Action = {
        var last: MGenerator.Action = T
        for (e <- s if last) {
          last = f(e)
        }
        return last
      }

      override def string: String = {
        return s"MGenerator($s)"
      }
    }

    @record class MapImpl[K, T](m: Map[K, T]) extends MGenerator[(K, T)] {
      override def generate(f: ((K, T)) => MGenerator.Action): MGenerator.Action = {
        var last: MGenerator.Action = T
        for (e <- m.entries if last) {
          last = f(e)
        }
        return last
      }

      override def string: String = {
        return s"MGenerator($m)"
      }
    }

    @record class HashMapImpl[K, T](m: HashMap[K, T]) extends MGenerator[(K, T)] {
      override def generate(f: ((K, T)) => MGenerator.Action): MGenerator.Action = {
        var last: MGenerator.Action = T
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
        return s"MGenerator($m)"
      }
    }

    @record class Filtered[T](gen: MGenerator[T], p: T => B) extends MGenerator[T] {
      override def generate(f: T => MGenerator.Action): MGenerator.Action = {
        def ap(o: T): MGenerator.Action = {
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

    @record class Mapped[U, T](gen: MGenerator[T], f: T => U@pure) extends MGenerator[U] {
      override def generate(g: U => MGenerator.Action): MGenerator.Action = {
        def ap(o: T): MGenerator.Action = {
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

    @record class FlatMapped[U, T](gen: MGenerator[T], f: T => MGenerator[U]) extends MGenerator[U] {
      override def generate(g: U => MGenerator.Action): MGenerator.Action = {
        def ap(o: T): MGenerator.Action = {
          def ap2(o2: U): MGenerator.Action = {
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

    @record class Sliced[T](gen: MGenerator[T], start: Z, end: Z) extends MGenerator[T] {
      def generate(f: T => MGenerator.Action): MGenerator.Action = {
        var count = 0

        def ap(o: T): MGenerator.Action = {
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

    @record class TakeWhile[T](gen: MGenerator[T], p: T => B) extends MGenerator[T] {
      def generate(f: T => MGenerator.Action): MGenerator.Action = {
        def ap(o: T): MGenerator.Action = {
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

    @record class DropWhile[T](gen: MGenerator[T], p: T => B) extends MGenerator[T] {
      def generate(f: T => MGenerator.Action): MGenerator.Action = {
        var started = F

        def ap(o: T): MGenerator.Action = {
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

    @record class ZipWithIndexed[T](gen: MGenerator[T]) extends MGenerator[(T, Z)] {
      def generate(f: ((T, Z)) => MGenerator.Action): MGenerator.Action = {
        var i = 0

        def ap(o: T): MGenerator.Action = {
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

    @record class Zipped[T, U](gen: MGenerator[T], gen2: MGenerator[U]) extends MGenerator[(T, U)] {
      def generate(f: ((T, U)) => MGenerator.Action): MGenerator.Action = {
        (gen.headOption, gen2.headOption) match {
          case (MSome(h), MSome(h2)) =>
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

    @record class Concat[T](gen: MGenerator[T], gen2: MGenerator[T]) extends MGenerator[T] {
      def generate(f: T => MGenerator.Action): MGenerator.Action = {
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

    @record class Product[T, U](gen: MGenerator[T], gen2: MGenerator[U]) extends MGenerator[(T, U)] {
      def generate(f: ((T, U)) => MGenerator.Action): MGenerator.Action = {
        def ap(o: T): MGenerator.Action = {
          def ap2(o2: U): MGenerator.Action = {
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

  @pure def ms[I, T](s: MS[I, T]): MGenerator[T] = {
    return Internal.MSImpl(s)
  }

  @pure def map[K, T](m: Map[K, T]): MGenerator[(K, T)] = {
    return Internal.MapImpl(m)
  }

  @pure def set[T](s: Set[T]): MGenerator[T] = {
    return Internal.MapImpl(s.map).map(p => p._1)
  }

  @pure def hashMap[K, T](m: HashMap[K, T]): MGenerator[(K, T)] = {
    return Internal.HashMapImpl(m)
  }

  @pure def hashSet[T](s: HashSet[T]): MGenerator[T] = {
    return Internal.HashMapImpl(s.map).map(p => p._1)
  }

  @pure def hashSMap[K, T](m: HashSMap[K, T]): MGenerator[(K, T)] = {
    return set(m.keys).map(k => (k, m.get(k).get))
  }

  @pure def hashSSet[T](s: HashSSet[T]): MGenerator[T] = {
    return hashSMap(s.map).map(p => p._1)
  }
}
