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

package org.sireum

import org.sireum.$internal.{RC, Trie}

object Library_Ext {
  def sharedMap: scala.collection.SortedMap[scala.Vector[Predef.String], Predef.String] = RC.text(Vector("../..")) { (p, f) =>
    val filename = p.last
    if (filename.endsWith(".slang")) true
    else if (filename.endsWith(".scala")) {
      val r = _root_.java.nio.file.Files.newBufferedReader(f.toPath, _root_.java.nio.charset.StandardCharsets.UTF_8)
      val line: Predef.String = r.readLine
      r.close()
      line != null && line.replaceAllLiterally(" ", "").contains("#Sireum")
    } else false
  }

  def jvmMap: scala.collection.SortedMap[scala.Vector[Predef.String], Predef.String] = RC.text(Vector("../../../../../../jvm/src/main/scala")) { (p, f) =>
    val filename = p.last
    if (filename.endsWith(".slang")) true
    else if (filename.endsWith(".scala")) {
      val r = _root_.java.nio.file.Files.newBufferedReader(f.toPath, _root_.java.nio.charset.StandardCharsets.UTF_8)
      val line: Predef.String = r.readLine
      r.close()
      line != null && line.replaceAllLiterally(" ", "").contains("#Sireum")
    } else false
  }

  def fontMap: scala.collection.SortedMap[scala.Vector[Predef.String], Predef.String] = RC.base64(Vector("../../../../../../../../resources/fonts/ttf")) { (p, f) =>
    p.last.endsWith("-Regular.ttf")
  }

  def vscodeImageMap: scala.collection.SortedMap[scala.Vector[Predef.String], Predef.String] = RC.base64(Vector("../../../../../../../../resources/distro/icons")) { (p, f) =>
    val name = p.last
    name.startsWith("code") || name.startsWith("letterpress") || name == "favicon.ico" || name == "VSCodium.icns"
  }

  def trie: Trie.Node[Predef.String, Predef.String] = RC.toTrie(sharedMap ++ jvmMap)

  def sharedFiles: ISZ[(Option[String], String)] =
    ISZ(sharedMap.toSeq.
      map(p => (Some(String(p._1.mkString("/"))), String(p._2))): _*)

  def jvmFiles: ISZ[(Option[String], String)] =
    ISZ(jvmMap.toSeq.
      map(p => (Some(String(p._1.mkString("/"))), String(p._2))): _*)

  def fontFiles: ISZ[(Option[String], String)] =
    ISZ(fontMap.toSeq.
      map(p => (Some(String(p._1.mkString("/"))), String(p._2))): _*)

  def vscodeImageFiles: ISZ[(Option[String], String)] =
    ISZ(vscodeImageMap.toSeq.
      map(p => (Some(String(p._1.mkString("/"))), String(p._2))): _*)

  def files: ISZ[(Option[String], String)] = sharedFiles ++ jvmFiles

}