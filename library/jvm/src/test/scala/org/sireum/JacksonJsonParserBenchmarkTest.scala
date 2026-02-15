/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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

import org.sireum.test._

class JacksonJsonParserBenchmarkTest extends TestSuite {

  val benchmarkFiles: ISZ[(String, String)] = ISZ(
    ("citm_catalog.json", "https://raw.githubusercontent.com/miloyip/nativejson-benchmark/master/data/citm_catalog.json"),
    ("twitter.json", "https://raw.githubusercontent.com/miloyip/nativejson-benchmark/master/data/twitter.json"),
    ("canada.json", "https://raw.githubusercontent.com/miloyip/nativejson-benchmark/master/data/canada.json"),
    ("mesh.json", "https://raw.githubusercontent.com/simdjson/simdjson-data/master/jsonexamples/mesh.json"),
    ("apache_builds.json", "https://raw.githubusercontent.com/simdjson/simdjson-data/master/jsonexamples/apache_builds.json"),
    ("instruments.json", "https://raw.githubusercontent.com/simdjson/simdjson-data/master/jsonexamples/instruments.json"),
    ("update-center.json", "https://raw.githubusercontent.com/simdjson/simdjson-data/master/jsonexamples/update-center.json"),
    ("numbers.json", "https://raw.githubusercontent.com/simdjson/simdjson-data/master/jsonexamples/numbers.json"),
    ("gsoc-2018.json", "https://raw.githubusercontent.com/simdjson/simdjson-data/master/jsonexamples/gsoc-2018.json")
  )

  val warmupIterations: Int = 3
  val benchmarkIterations: Int = 10

  def parseJackson(content: String): parser.json.AST = {
    JacksonJsonParser.parse(None(), content)
  }

  def parseJacksonJsonc(content: String): parser.json.AST = {
    JacksonJsonParser.parseJsonc(None(), content)
  }

  def lexJson(content: String): Indexable[parser.Token] = {
    val cis = conversions.String.toCis(content)
    val docInfo = message.DocInfo.createFromCis(None(), cis)
    val chars = Indexable.IszDocInfo[C](cis, docInfo)
    val (errorIndex, tokens) = parser.JsonParser.lexerDfas.tokens(chars, T)
    assert(errorIndex < 0, s"Lex error at $errorIndex")
    Indexable.fromIsz(tokens)
  }

  def lexJsonc(content: String): Indexable[parser.Token] = {
    val cis = conversions.String.toCis(content)
    val docInfo = message.DocInfo.createFromCis(None(), cis)
    val chars = Indexable.IszDocInfo[C](cis, docInfo)
    val (errorIndex, tokens) = parser.JsoncParser.lexerDfas.tokens(chars, T)
    assert(errorIndex < 0, s"Lex error at $errorIndex")
    Indexable.fromIsz(tokens)
  }

  def parseLLkOpt(indexable: Indexable[parser.Token]): scala.Option[parser.json.AST] = {
    try {
      val reporter = message.Reporter.create
      val treeOpt = parser.JsonParser.g.parse("valueFile", indexable, reporter)
      treeOpt match {
        case Some(tree) if !reporter.hasError => scala.Some(parser.json.JsonAstBuilder(tree).build())
        case _ => scala.None
      }
    } catch {
      case _: Throwable => scala.None
    }
  }

  def parseLLkJsoncOpt(indexable: Indexable[parser.Token]): scala.Option[parser.json.AST] = {
    try {
      val reporter = message.Reporter.create
      val treeOpt = parser.JsoncParser.g.parse("valueFile", indexable, reporter)
      treeOpt match {
        case Some(tree) if !reporter.hasError => scala.Some(parser.json.JsonAstBuilder(tree).build())
        case _ => scala.None
      }
    } catch {
      case _: Throwable => scala.None
    }
  }

  def benchmarkJson(name: String, content: String): Unit = {
    val nameStr: Predef.String = name.value
    val sizeStr: Predef.String = content.size.toString

    val indexable = lexJson(content)
    val llkResult = parseLLkOpt(indexable)

    if (llkResult.isEmpty) {
      for (_ <- 0 until warmupIterations) {
        parseJackson(content)
      }
      val jacksonStart = System.nanoTime()
      for (_ <- 0 until benchmarkIterations) {
        parseJackson(content)
      }
      val jacksonMs = (System.nanoTime() - jacksonStart) / 1000000.0
      println(f"  $nameStr%-25s size=$sizeStr%10s  Jackson: ${jacksonMs}%10.2f ms  LLk:        N/A")
      return
    }

    val jackson = parseJackson(content)
    assert(jackson == llkResult.get, s"AST mismatch for ${name.value}")

    for (_ <- 0 until warmupIterations) {
      parseJackson(content)
      parseLLkOpt(indexable)
    }

    val jacksonStart = System.nanoTime()
    for (_ <- 0 until benchmarkIterations) {
      parseJackson(content)
    }
    val jacksonElapsed = System.nanoTime() - jacksonStart

    val llkStart = System.nanoTime()
    for (_ <- 0 until benchmarkIterations) {
      parseLLkOpt(indexable)
    }
    val llkElapsed = System.nanoTime() - llkStart

    val jacksonMs = jacksonElapsed / 1000000.0
    val llkMs = llkElapsed / 1000000.0
    val ratio = llkMs / jacksonMs

    println(f"  $nameStr%-25s size=$sizeStr%10s  Jackson: ${jacksonMs}%10.2f ms  LLk: ${llkMs}%10.2f ms (${ratio}%5.2fx)")
  }

  def benchmarkJsonc(name: String, content: String): Unit = {
    val nameStr: Predef.String = name.value
    val sizeStr: Predef.String = content.size.toString

    val indexable = lexJsonc(content)
    val llkResult = parseLLkJsoncOpt(indexable)

    if (llkResult.isEmpty) {
      for (_ <- 0 until warmupIterations) {
        parseJacksonJsonc(content)
      }
      val jacksonStart = System.nanoTime()
      for (_ <- 0 until benchmarkIterations) {
        parseJacksonJsonc(content)
      }
      val jacksonMs = (System.nanoTime() - jacksonStart) / 1000000.0
      println(f"  $nameStr%-25s size=$sizeStr%10s  Jackson: ${jacksonMs}%10.2f ms  LLk:        N/A")
      return
    }

    val jackson = parseJacksonJsonc(content)
    assert(jackson == llkResult.get, s"JSONC AST mismatch for ${name.value}")

    for (_ <- 0 until warmupIterations) {
      parseJacksonJsonc(content)
      parseLLkJsoncOpt(indexable)
    }

    val jacksonStart = System.nanoTime()
    for (_ <- 0 until benchmarkIterations) {
      parseJacksonJsonc(content)
    }
    val jacksonElapsed = System.nanoTime() - jacksonStart

    val llkStart = System.nanoTime()
    for (_ <- 0 until benchmarkIterations) {
      parseLLkJsoncOpt(indexable)
    }
    val llkElapsed = System.nanoTime() - llkStart

    val jacksonMs = jacksonElapsed / 1000000.0
    val llkMs = llkElapsed / 1000000.0
    val ratio = llkMs / jacksonMs

    println(f"  $nameStr%-25s size=$sizeStr%10s  Jackson: ${jacksonMs}%10.2f ms  LLk: ${llkMs}%10.2f ms (${ratio}%5.2fx)")
  }

  val tests = Tests {

    * - {
      if (Os.env("GITHUB_ACTION").isEmpty) {
        val tmpDir = Os.tempDir()
        val sep = "=" * 90

        // Download all files
        for (p <- benchmarkFiles) {
          val file = tmpDir / p._1
          if (!file.exists) {
            val ok = file.downloadFrom(p._2)
            assert(ok, s"Failed to download ${p._2.value}")
          }
        }

        // JSON benchmark
        println()
        println(s"JSON Parser Benchmark ($benchmarkIterations iterations, $warmupIterations warmup)")
        println(sep)
        for (p <- benchmarkFiles) {
          val content: String = (tmpDir / p._1).read
          benchmarkJson(p._1, content)
        }
        println(sep)

        // JSONC benchmark (same files — valid JSON is valid JSONC)
        println()
        println(s"JSONC Parser Benchmark ($benchmarkIterations iterations, $warmupIterations warmup)")
        println(sep)
        for (p <- benchmarkFiles) {
          val content: String = (tmpDir / p._1).read
          benchmarkJsonc(p._1, content)
        }
        println(sep)

        tmpDir.removeAll()
      }
    }
  }
}
