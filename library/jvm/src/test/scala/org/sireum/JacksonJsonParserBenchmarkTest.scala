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

  def parseLLkOpt(content: String): scala.Option[parser.json.AST] = {
    try {
      val reporter = message.Reporter.create
      val treeOpt = parser.JsonParser.parse(None(), content, reporter)
      treeOpt match {
        case Some(tree) if !reporter.hasError => scala.Some(parser.json.JsonAstBuilder(tree).build())
        case _ => scala.None
      }
    } catch {
      case _: StackOverflowError => scala.None
      case _: Throwable => scala.None
    }
  }

  def benchmark(name: String, content: String): Unit = {
    val nameStr: Predef.String = name.value
    val sizeStr: Predef.String = content.size.toString

    // Check if LLk can parse this file
    val llkResult = parseLLkOpt(content)

    if (llkResult.isEmpty) {
      // Warmup Jackson only
      for (_ <- 0 until warmupIterations) {
        parseJackson(content)
      }

      // Benchmark Jackson only
      val jacksonStart = System.nanoTime()
      for (_ <- 0 until benchmarkIterations) {
        parseJackson(content)
      }
      val jacksonMs = (System.nanoTime() - jacksonStart) / 1000000.0
      println(f"  $nameStr%-25s size=$sizeStr%10s  Jackson: ${jacksonMs}%10.2f ms  LLk:        N/A  ratio:    N/A")
      return
    }

    // Verify both produce the same AST
    val jackson = parseJackson(content)
    assert(jackson == llkResult.get, s"AST mismatch for ${name.value}")

    // Warmup
    for (_ <- 0 until warmupIterations) {
      parseJackson(content)
      parseLLkOpt(content)
    }

    // Benchmark Jackson
    val jacksonStart = System.nanoTime()
    for (_ <- 0 until benchmarkIterations) {
      parseJackson(content)
    }
    val jacksonElapsed = System.nanoTime() - jacksonStart

    // Benchmark LLk
    val llkStart = System.nanoTime()
    for (_ <- 0 until benchmarkIterations) {
      parseLLkOpt(content)
    }
    val llkElapsed = System.nanoTime() - llkStart

    val jacksonMs = jacksonElapsed / 1000000.0
    val llkMs = llkElapsed / 1000000.0
    val ratio = llkMs / jacksonMs

    println(f"  $nameStr%-25s size=$sizeStr%10s  Jackson: ${jacksonMs}%10.2f ms  LLk: ${llkMs}%10.2f ms  ratio: ${ratio}%6.2fx")
  }

  val tests = Tests {

    * - {
      val tmpDir = Os.tempDir()

      println()
      println(s"JSON Parser Benchmark ($benchmarkIterations iterations, $warmupIterations warmup)")
      println(s"${"=" * 110}")

      for (p <- benchmarkFiles) {
        val name = p._1
        val url = p._2
        val file = tmpDir / name
        if (!file.exists) {
          val ok = file.downloadFrom(url)
          assert(ok, s"Failed to download ${url.value}")
        }
        val content: String = file.read
        benchmark(name, content)
      }

      println(s"${"=" * 110}")

      tmpDir.removeAll()
    }

  }
}
