/*
 Copyright (c) 2017-2025, Robby, Kansas State University
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

package org.sireum.$internal

import scala.language.experimental.macros

object Macro {
  val templateString = "st\"...\""

  def parMap[T, U](poolRef: _root_.java.util.concurrent.atomic.AtomicReference[AnyRef],
                   cores: Int, arg: scala.collection.Seq[T], f: T => U): scala.collection.IndexedSeq[U] = macro Macro.parMapImpl

  def any[R](fs: _root_.scala.Seq[() => _root_.scala.Option[R]], default: () => R, isSequential: Boolean): (R, _root_.scala.Long) = macro Macro.anyImpl

  def anyEither[R, S](fs: _root_.scala.Seq[() => _root_.scala.Either[R, S]], isSequential: Boolean): (_root_.scala.Either[R, _root_.scala.Seq[S]], _root_.scala.Long) = macro Macro.anyEitherImpl

  def sync[T](o: AnyRef, arg: T): T = macro Macro.syncImpl

  def isJs: Boolean = macro Macro.isJsImpl

  def version: String = macro Macro.versionImpl

  def commitHash: String = macro Macro.commitHashImpl

  def f32Bit(n: Float): Int = macro Macro.f32BitImpl

  def f64Bit(n: Double): Long = macro Macro.f64BitImpl

  def isNative: Boolean = macro Macro.isNativeImpl

  def eval[T](c: scala.reflect.macros.blackbox.Context)(
    t: Any, n: Int = 6): T = { // HACK: eval may non-deterministically fail, so try n times!
    val tree = t.asInstanceOf[c.Tree]
    val expr = c.Expr(c.untypecheck(tree))
    for (_ <- 0 until n) {
      scala.util.Try(c.eval[T](expr)) match {
        case scala.util.Success(x) => return x
        case _ =>
      }
      synchronized { wait(100) }
    }
    c.eval[T](expr)
  }

}

import Macro._

class Macro(val c: scala.reflect.macros.blackbox.Context) {

  val isJsCheck: Boolean = "true" == System.getenv("PROYEK_JS") || scala.util.Try(Class.forName("scala.scalajs.js.Any", false, getClass.getClassLoader)).isSuccess

  import c.universe._

  def l[T](args: c.Expr[Any]*): c.Expr[T] =
    c.Expr[T]( q"""halt("Slang l\"\"\"...\"\"\" should have been erased by the Sireum Scala plugin.")""")

  def lUnit(args: c.Expr[Any]*): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit1(arg0: c.Tree): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit2(arg0: c.Tree, arg1: c.Tree): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit3(arg0: c.Tree, arg1: c.Tree, arg2: c.Tree): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit4(arg0: c.Tree, arg1: c.Tree, arg2: c.Tree, arg3: c.Tree): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit5(arg0: c.Tree, arg1: c.Tree, arg2: c.Tree, arg3: c.Tree, arg4: c.Tree): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit0S(arg0: c.Tree*): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit1S(arg0: c.Tree, arg1: c.Tree*): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit2S(arg0: c.Tree, arg1: c.Tree, arg2: c.Tree*): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit3S(arg0: c.Tree, arg1: c.Tree, arg2: c.Tree, arg3: c.Tree*): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit4S(arg0: c.Tree, arg1: c.Tree, arg2: c.Tree, arg3: c.Tree, arg4: c.Tree*): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit5S(arg0: c.Tree, arg1: c.Tree, arg2: c.Tree, arg3: c.Tree, arg4: c.Tree, arg5: c.Tree*): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lUnit6S(arg0: c.Tree, arg1: c.Tree, arg2: c.Tree, arg3: c.Tree, arg4: c.Tree, arg5: c.Tree, arg6: c.Tree*): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def lDef[T](args: c.Expr[Any]*): c.Expr[T] =
    c.Expr[T]( q"""halt("Slang l\"\"\"...\"\"\" should have been erased by the Sireum Scala plugin.")""")

  def setOptions(tool: c.Tree, options: c.Tree): c.Expr[Unit] = c.Expr[Unit](q"{}")

  def $[T]: c.Expr[T] = c.Expr[T]( q"""halt("Cannot invoke this method")""")

  def extractParts: Seq[c.Tree] = (c.prefix.tree match {
    case q"org.sireum.`package`.$$Slang(scala.StringContext.apply(..$ps)).$_" => ps
    case q"sireum.this.`package`.$$Slang(scala.StringContext.apply(..$ps)).$_" => ps
    case q"org.sireum.`package`.$$Slang(scala.StringContext.apply(..$ps))" => ps
    case q"sireum.this.`package`.$$Slang(scala.StringContext.apply(..$ps))" => ps
  }).asInstanceOf[Seq[c.Tree]]

  def zApply(args: c.Tree*): c.Tree = {
    val parts = extractParts
    if (parts.size != 1) c.abort(c.prefix.tree.pos, "Slang z\"...\" should not contain $$ arguments.")
    q"_root_.org.sireum.Z.$$String(${parts.head})"
  }

  def cApply(args: c.Tree*): c.Tree = {
    val parts = extractParts
    if (parts.size != 1) c.abort(c.prefix.tree.pos, "Slang c\"...\" should not contain $$ arguments.")
    val s = Macro.eval[String](c)(parts.head)
    if (s.codePointCount(0, s.length) != 1) c.abort(c.prefix.tree.pos, "Slang c\"...\" can only have a single character.")
    q"_root_.org.sireum.C(${parts.head}.codePointAt(0))"
  }

  def f32Apply(args: c.Tree*): c.Tree = {
    val parts = extractParts
    if (parts.size != 1) c.abort(c.prefix.tree.pos, "Slang f32\"...\" should not contain $$ arguments.")
    q"_root_.org.sireum.F32.$$String(${parts.head})"
  }

  def f32BitImpl(n: c.Tree): c.Tree = {
    if (isJsCheck) q"_root_.java.lang.Float.floatToIntBits($n)"
    else q"_root_.java.lang.Float.floatToRawIntBits($n)"
  }

  def f64BitImpl(n: c.Tree): c.Tree = {
    if (isJsCheck) q"_root_.java.lang.Double.doubleToLongBits($n)"
    else q"_root_.java.lang.Double.doubleToRawLongBits($n)"
  }

  def isNativeImpl: c.Tree = {
    if (isJsCheck) q"false"
    else q"_root_.org.sireum.NativeUtil.isNative"
  }

  def f64Apply(args: c.Tree*): c.Tree = {
    val parts = extractParts
    if (parts.size != 1) c.abort(c.prefix.tree.pos, "Slang f64\"...\" should not contain $$ arguments.")
    q"_root_.org.sireum.F64.$$String(${parts.head})"
  }

  def rApply(args: c.Tree*): c.Tree = {
    val parts = extractParts
    if (parts.size != 1) c.abort(c.prefix.tree.pos, "Slang r\"...\" should not contain $$ arguments.")
    q"_root_.org.sireum.R.$$String(${parts.head})"
  }

  def stringApply(args: c.Tree*): c.Tree = {
    val parts = extractParts
    if (parts.size != 1) c.abort(c.prefix.tree.pos, "Slang string\"...\" should not contain $$ arguments.")
    q"_root_.org.sireum.String(${parts.head})"
  }

  def $assign(arg: c.Tree): c.Tree = {
    def args(n: Int): c.Tree = {
      val l = (for (i <- 1 to n) yield
        Apply(q"_root_.org.sireum.helper.assign", List(Select(Ident(TermName("x")), TermName(s"_$i"))))).toList
      Block(List(q"val x = $arg"),
        Apply(Select(Ident(TermName("scala")), TermName(s"Tuple$n")), l))
    }

    //println(showRaw(arg))
    val mm = c.typeOf[MutableMarker]
    val r = arg match {
      case q"(..$args)" if args.size > 1 => arg
      case _ =>
        if (arg.tpe <:< mm) q"_root_.org.sireum.helper.assignMut($arg)"
        else if (arg.tpe.typeSymbol.fullName.startsWith("scala.Tuple")) {
          val n = arg.tpe.typeSymbol.fullName.substring("scala.Tuple".length).toInt
          args(n)
        }
        else arg
    }
    //println(showRaw(r))
    //println(showCode(r))
    r
  }

  def $ret(arg: c.Tree): c.Tree = {
    def args(n: Int): c.Tree = {
      val l = (for (i <- 1 to n) yield
        Apply(q"_root_.org.sireum.helper.ret", List(Select(Ident(TermName("x")), TermName(s"_$i"))))).toList
      Block(List(q"val x = $arg"),
        Apply(Select(Ident(TermName("scala")), TermName(s"Tuple$n")), l))
    }

    //println(showRaw(arg))
    val mm = c.typeOf[MutableMarker]
    val r = arg match {
      case q"(..$args)" if args.size > 1 => arg
      case _ =>
        if (arg.tpe <:< mm) q"_root_.org.sireum.helper.retMut($arg)"
        else if (arg.tpe.typeSymbol.fullName.startsWith("scala.Tuple")) {
          val n = arg.tpe.typeSymbol.fullName.substring("scala.Tuple".length).toInt
          args(n)
        }
        else arg
    }
    //println(showRaw(r))
    //println(showCode(r))
    r
  }

  def $tmatch(arg: c.Tree): c.Tree = {
    def args(n: Int): c.Tree = {
      val l = (for (i <- 1 to n) yield
        Apply(q"_root_.org.sireum.helper.assign", List(Select(Ident(TermName("x")), TermName(s"_$i"))))).toList
      Block(List(q"val x = $arg"),
        Apply(Select(Ident(TermName("scala")), TermName(s"Tuple$n")), l))
    }

    //println(showRaw(arg))
    val r = arg match {
      case q"(..$args)" if args.size > 1 => arg
      case _ =>
        if (arg.tpe.typeSymbol.fullName.startsWith("scala.Tuple")) {
          val n = arg.tpe.typeSymbol.fullName.substring("scala.Tuple".length).toInt
          args(n)
        }
        else arg
    }
    //println(showRaw(r))
    //println(showCode(r))
    r
  }

  def anyImpl(fs: c.Tree, default: c.Tree, isSequential: c.Tree): c.Tree =
    if (isJsCheck)
      q"""{
  def anySeq(): R = {
    for (f <- $fs) {
      f() match {
        case _root_.scala.Some(r) => return r
        case _ =>
      }
    }
    return $default()
  }
  val start = _root_.java.lang.System.currentTimeMillis
  val r = anySeq()
  (r, _root_.java.lang.System.currentTimeMillis - start)
}"""
    else
      q"""{
  def anySeq(): (R, _root_.scala.Long) = {
    val start = _root_.java.lang.System.currentTimeMillis
    for (f <- $fs) {
      f() match {
        case _root_.scala.Some(r) => return (r, _root_.java.lang.System.currentTimeMillis - start)
        case _ =>
      }
    }
    return ($default(), _root_.java.lang.System.currentTimeMillis - start)
  }

  def anyPar(): (R, _root_.scala.Long) = {
    val pool = new _root_.java.util.concurrent.ForkJoinPool(sz)
    val time = new _root_.java.util.concurrent.atomic.AtomicLong(0)
    var r: R = null.asInstanceOf[R]
    try {
      val a = new _root_.java.util.ArrayList[_root_.java.util.concurrent.Callable[R]](sz)
      val count = new _root_.java.util.concurrent.atomic.AtomicInteger(0)
      for (f <- $fs) {
        a.add(new _root_.java.util.concurrent.Callable[R] {
          override def call(): R = {
            val start = _root_.java.lang.System.currentTimeMillis
            val frOpt = f()
            count.incrementAndGet()
            frOpt match {
              case _root_.scala.Some(fr) =>
                time.addAndGet(_root_.java.lang.System.currentTimeMillis - start)
                return fr
              case _ =>
                while (count.get() < a.size) {
                  try {
                    Thread.sleep(100)
                  } catch {
                    case _: Exception =>
                  }
                }
                time.addAndGet(_root_.java.lang.System.currentTimeMillis - start)
                throw new RuntimeException()
            }
          }
        })
      }
      r = pool.invokeAny(a)
      pool.shutdown()
    } catch {
      case _: Exception =>
    }
    return (if (r == null) $default() else r, time.get)
  }

  if ($isSequential) anySeq() else anyPar()
}"""


  def anyEitherImpl(fs: c.Tree, isSequential: c.Tree): c.Tree =
    if (isJsCheck)
      q"""{
  def anySeq(): _root_.scala.Either[R, _root_.scala.Seq[S]] = {
    var ss = _root_.scala.Vector[S]()
    for (f <- $fs) {
      f() match {
        case _root_.scala.Left(r) => return _root_.scala.Left(r)
        case _root_.scala.Right(r) => ss = ss :+ r
      }
    }
    return _root_.scala.Right(ss)
  }
  val start = _root_.java.lang.System.currentTimeMillis
  val r = anySeq()
  (r, _root_.java.lang.System.currentTimeMillis - start)
}"""
    else
      q"""{
  def anySeq(): (_root_.scala.Either[R, _root_.scala.Seq[S]], _root_.scala.Long) = {
    val start = _root_.java.lang.System.currentTimeMillis
    var ss = _root_.scala.Vector[S]()
    for (f <- $fs) {
      f() match {
        case _root_.scala.Left(r) => return (_root_.scala.Left(r), _root_.java.lang.System.currentTimeMillis - start)
        case _root_.scala.Right(r) => ss = ss :+ r
      }
    }
    return (_root_.scala.Right(ss), _root_.java.lang.System.currentTimeMillis - start)
  }
  def anyPar(): (_root_.scala.Either[R, _root_.scala.Seq[S]], _root_.scala.Long) = {
    val pool = new _root_.java.util.concurrent.ForkJoinPool(sz)
    val time = new _root_.java.util.concurrent.atomic.AtomicLong(0)
    var r = null.asInstanceOf[R]
    try {
      val a = new _root_.java.util.ArrayList[_root_.java.util.concurrent.Callable[R]](sz)
      val count = new _root_.java.util.concurrent.atomic.AtomicInteger(0)
      for (f <- $fs) {
        a.add(new _root_.java.util.concurrent.Callable[R] {
          override def call(): R = {
            val start = _root_.java.lang.System.currentTimeMillis
            val frEither = f()
            count.incrementAndGet()
            frEither match {
              case _root_.scala.Left(fr) =>
                time.addAndGet(_root_.java.lang.System.currentTimeMillis - start)
                return fr
              case _root_.scala.Right(fr) =>
                addS(fr)
                while (count.get() < a.size) {
                  try {
                    Thread.sleep(100)
                  } catch {
                    case _: Exception =>
                  }
                }
                time.addAndGet(_root_.java.lang.System.currentTimeMillis - start)
                throw new RuntimeException()
            }
          }
        })
      }
      r = pool.invokeAny(a)
      pool.shutdown()
    } catch {
      case _: Exception =>
    }
    return (if (r == null) _root_.scala.Right(ss) else _root_.scala.Left(r), time.get)
  }

  if ($isSequential) anySeq() else anyPar()
}"""

  def parMapImpl(poolRef: c.Tree, cores: c.Tree, arg: c.Tree, f: c.Tree): c.Tree =
    if (isJsCheck) q"$arg.map($f).toIndexedSeq"
    else
      q"""{
  val newPool = new _root_.java.util.concurrent.ForkJoinPool($cores)
  val success = $poolRef.compareAndSet(null, newPool)
  if (!success) newPool.shutdown()
  val pc = _root_.scala.collection.parallel.mutable.ParArray($arg: _*)
  pc.tasksupport = new _root_.scala.collection.parallel.ForkJoinTaskSupport($poolRef.get.asInstanceOf[_root_.java.util.concurrent.ForkJoinPool])
  val r = pc.map($f).toIndexedSeq
  if (success) $poolRef.getAndSet(null).asInstanceOf[_root_.java.util.concurrent.ForkJoinPool].shutdown()
  r
}"""

  def syncImpl(o: c.Tree, arg: c.Tree): c.Tree = if (isJsCheck) arg else q"$o.synchronized { $arg }"

  def st(args: c.Tree*): c.Tree = {
    def processArg(e: c.Tree, sep: c.Tree): c.Tree = {
      val t = e.tpe.dealias
      val templ = c.typeOf[org.sireum.$internal.STMarker]
      val r =
        if (t <:< templ) q"ST.Templ(scala.Seq($e), $sep)"
        else if (t <:< c.typeOf[ISMarker] || t <:< c.typeOf[MSMarker]) {
          t.typeArgs.length match {
            case 1 if t.typeArgs.head <:< templ => q"ST.Templ($e.elements, $sep)"
            case 2 if t.typeArgs(1) <:< templ => q"ST.Templ($e.elements, $sep)"
            case _ => q"ST.Any($e.elements.map($$internal.Option.apply), $sep)"
          }
        } else if (t.erasure <:< c.typeOf[CollectionCompat.IterableOnce[Any]].erasure) {
          if (t.typeArgs.head <:< templ) q"ST.Templ($e.toSeq, $sep)"
          else q"ST.Any($e.toSeq.map($$internal.Option.apply), $sep)"
        } else q"ST.Any(scala.Seq($$internal.Option($e)), $sep)"
      //println(showCode(r))
      r
    }

    //println(showRaw(c.prefix.tree))
    //println(showCode(c.prefix.tree))
    val pos = c.prefix.tree.pos
    val isSingle =
      if (pos.source.content.length >= pos.start + 5)
        new String(pos.source.content.slice(pos.start, pos.start + 5)) != "st\"\"\""
      else true
    val parts = {
      val ps = extractParts
      if (isSingle) ps.map(p => q"StringContext.processEscapes($p)") else ps
    }
    val stArgs = for (arg <- args) yield arg match {
      case q"(..$exprs)" if exprs.size > 1 =>
        if (exprs.size != 2) c.abort(arg.pos, s"Expecting a pair instead of a ${exprs.size}-tuple.")
        val e = exprs(1).asInstanceOf[c.Tree]
        val first = exprs.head.asInstanceOf[c.Tree]
        val t = e.tpe
        if (t <:< c.typeOf[Predef.String]) processArg(first, e)
        else if (t.typeSymbol.fullName == "org.sireum.String") processArg(first, q"$e.value")
        else c.abort(e.pos, s"Expecting a separator string instead of '${showCode(e)}'.")
      case _ =>
        processArg(arg, Literal(Constant("")))
    }
    val source = if (pos.isRange) {
      val text = pos.source.content
      val sb = new java.lang.StringBuilder
      for (_ <- 0 until pos.column - 1) sb.append(' ')
      for (i <- pos.start until pos.end) {
        sb.append(text(i))
      }
      sb.toString
    } else templateString
    q"ST(scala.Seq(..$parts), scala.Seq[ST.Arg](..$stArgs), ${Literal(Constant(source))})"
  }

  def proc(args: c.Tree*): c.Tree = {
    val pos = c.prefix.tree.pos
    val isSingle =
      if (pos.source.content.length >= pos.start + 7)
        new String(pos.source.content.slice(pos.start, pos.start + 7)) != "proc\"\"\""
      else true
    val parts = {
      val ps = extractParts
      if (isSingle) ps.map(p => q"StringContext.processEscapes($p)") else ps
    }
    val stArgs = for (arg <- args) yield q"""ST.Any(scala.Seq($$internal.Option(Os_Ext.pathString($arg))), "")"""
    val source = if (pos.isRange) {
      val text = pos.source.content
      val sb = new java.lang.StringBuilder
      for (_ <- 0 until pos.column - 1) sb.append(' ')
      for (i <- pos.start until pos.end) {
        sb.append(text(i))
      }
      sb.toString
    } else templateString
    val r = q"Os.procs(ST(scala.Seq(..$parts), scala.Seq[ST.Arg](..$stArgs), ${Literal(Constant(source))}).render)"
    //println(showCode(r))
    r
  }

  def isJsImpl: c.Tree = if (isJsCheck) q"true" else q"false"

  def exec(command: Array[String], dir: java.io.File): String = {
    val pb = new ProcessBuilder(command: _*)
    //pb.redirectErrorStream(true)
    pb.directory(dir)
    val exec = pb.start()

    val br = new java.io.BufferedReader(new java.io.InputStreamReader(exec.getInputStream))
    val sb = new StringBuilder
    var c = br.read()
    while (c != -1) {
      sb.append(c.toChar)
      c = br.read()
    }
    exec.waitFor()
    return sb.toString
  }

  def commitHashImpl: c.Tree = {
    val f = c.enclosingPosition.pos.source.file.file
    //print(s"Retrieving commit hash for ${f.getName} from ${f.getParent}: ")
    val star = if (exec(Array("git", "status", "--porcelain"), f.getParentFile).trim == "") "" else "*"
    val hash = exec(Array("git", "log", "-n", "1", "--pretty=format:%H"), f.getParentFile).trim
    val r = s"$hash$star"
    //println(r)
    c.universe.Literal(c.universe.Constant(r))
  }

  def versionImpl: c.Tree = {
    val f = c.enclosingPosition.pos.source.file.file
    //print(s"Retrieving version for ${f.getName} from ${f.getParent}: ")
    val star = if (exec(Array("git", "status", "--porcelain"), f.getParentFile).trim == "") "" else "*"
    val v = exec(Array("git", "log", "-n", "1", "--date=format:%Y%m%d", "--pretty=format:4.%cd.%h"), f.getParentFile).trim
    val r = s"$v$star"
    //println(r)
    c.universe.Literal(c.universe.Constant(r))
  }
}