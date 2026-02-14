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

class JacksonJsonParserTest extends TestSuite {

  def parseJackson(input: Predef.String): parser.json.AST = {
    JacksonJsonParser.parse(None(), input)
  }

  def parseLLk(input: Predef.String): parser.json.AST = {
    val reporter = message.Reporter.create
    val tree = parser.JsonParser.parse(None(), input, reporter).get
    assert(!reporter.hasError)
    parser.json.JsonAstBuilder(tree).build()
  }

  def check(input: Predef.String): Unit = {
    val jackson = parseJackson(input)
    val llk = parseLLk(input)
    assert(jackson == llk)
  }

  val tests = Tests {

    // String
    * - check("\"hello\"")

    // Integer
    * - check("42")

    // Negative integer
    * - check("-1")

    // Boolean true
    * - check("true")

    // Boolean false
    * - check("false")

    // Null
    * - check("null")

    // Double
    * - check("3.14")

    // Empty object
    * - check("{}")

    // Empty array
    * - check("[]")

    // Array of integers
    * - check("[1, 2, 3]")

    // Array with booleans and null
    * - check("[true, false, null]")

    // Simple object
    * - check("{\"a\": 1}")

    // Object with multiple keys
    * - check("{\"a\": 1, \"b\": \"hello\"}")

    // Object with boolean and null values
    * - check("{\"x\": true, \"y\": false, \"z\": null}")

    // Nested object in array
    * - check("[{\"a\": 1}, {\"b\": 2}]")

    // Nested array in object
    * - check("{\"arr\": [1, true, null]}")

    // Deeply nested
    * - check("{\"nested\": {\"arr\": [1, 2, 3], \"obj\": {\"key\": \"val\"}}}")

    // Array of arrays
    * - check("[[1, 2], [3, 4]]")

    // Mixed object
    * - check("{\"name\": \"test\", \"value\": 42, \"active\": true, \"data\": null}")

    // Double in object
    * - check("{\"pi\": 3.14}")
  }
}
