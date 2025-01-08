/*
 * Copyright (c) 2017-2025, Robby, Kansas State University
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sireum.$internal

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import scala.collection.SortedMap
import scala.language.experimental.macros
import scala.collection.mutable.{Map => MMap}

object RC {

  def toTrie(m: SortedMap[Vector[String], String]): Trie.Node[String, String] = {
    val root = Trie.InNode[String, String](MMap())

    def add(path: Vector[String], content: String, node: Trie.InNode[String, String]): Unit = path match {
      case Seq(s) => node.children += ((s, Trie.Leaf[String, String](content)))
      case _ =>
        node.children.get(path.head) match {
          case Some(n: Trie.InNode[String, String] @unchecked) => add(path.tail, content, n)
          case _ =>
            val n = Trie.InNode[String, String](MMap())
            node.children += ((path.head, n))
            add(path.tail, content, n)
        }
    }

    for ((path, content) <- m) add(path, content, root)

    root
  }

  def text(relPaths: Vector[String])(p: (Vector[String], File) => Boolean): SortedMap[Vector[String], String] = macro RC.textImpl

  def base64(relPaths: Vector[String])(p: (Vector[String], File) => Boolean): SortedMap[Vector[String], String] = macro RC.base64Impl
}

class RC(val c: scala.reflect.macros.blackbox.Context) {

  import c.universe._

  def commonImpl(
    isText: Boolean,
    relPaths: c.Expr[Vector[String]],
    p: c.Expr[(Vector[String], File) => Boolean]
  ): c.Expr[SortedMap[Vector[String], String]] = {
    val pf = Macro.eval[(Vector[String], File) => Boolean](c)(p.tree)
    val rps = Macro.eval[Vector[String]](c)(relPaths.tree)
    var args = Vector[c.Tree]()

    def it(rp: String): Unit = {
      val anchorDir = new File(new File(p.tree.pos.source.file.canonicalPath).getParentFile, rp)
      val anchorPath = uriOf(anchorDir)

      def rec(file: File): Unit = {
        if (file.isFile) {
          val filePath = uriOf(file)
          if (filePath.startsWith(anchorPath)) {
            val path = filePath.substring(anchorPath.length).split('/').toVector
            if (pf(path, file)) {
              val pathSegments: Vector[c.Tree] = path.map(p => Literal(Constant(p)))
              val content = if (isText) readText(file) else readBase64(file)
              val s = content.grouped(20000).toSeq.map(c => Literal(Constant(c)))
              val fs = s.indices.toVector.map(n => TermName("f" + n))
              val ms = fs.zip(s).map(p => q"def ${p._1}: Predef.String = ${p._2}")
              val b = q"..${ms :+ fs.map(f => Ident(f): c.Tree).reduce((f1, f2) => q"$f1 + $f2")}"
              args :+= q"(Vector(..$pathSegments), $b)"
            }
          }
        } else if (file.isDirectory) {
          file.listFiles.foreach(rec)
        }
      }
      rec(anchorDir)
    }

    if (rps.nonEmpty) rps.foreach(it) else it(".")
    scala.collection.immutable.TreeMap[Vector[Predef.String], Predef.String]()((v1: Vector[Predef.String], v2: Vector[Predef.String]) => {
      var r = v1.length.compare(v2.length)
      if (r != 0) r
      else {
        for (i <- v1.indices if r == 0) r = v1(i).compare(v2(i))
        r
      }
    })

    val r =
      q"""scala.collection.immutable.TreeMap[Vector[Predef.String], Predef.String](..$args)((v1: Vector[Predef.String], v2: Vector[Predef.String]) => {
            var r = v1.length.compare(v2.length)
            if (r != 0) r
            else {
              for (i <- v1.indices if r == 0) r = v1(i).compare(v2(i))
              r
            }
          })"""
    //println(showCode(r))
    c.Expr(r)
  }

  def textImpl(relPaths: c.Expr[Vector[String]])(p: c.Expr[(Vector[String], File) => Boolean]): c.Expr[SortedMap[Vector[String], String]] =
    commonImpl(isText = true, relPaths, p)

  def base64Impl(
    relPaths: c.Expr[Vector[String]]
  )(p: c.Expr[(Vector[String], File) => Boolean]): c.Expr[SortedMap[Vector[String], String]] =
    commonImpl(isText = false, relPaths, p)

  def uriOf(f: File): String = f.toURI.toASCIIString

  def readText(f: File): String = new String(Files.readAllBytes(f.toPath), StandardCharsets.UTF_8)

  def readBase64(f: File): String = java.util.Base64.getEncoder.encodeToString(Files.readAllBytes(f.toPath))
}
