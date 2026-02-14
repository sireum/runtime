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

class JsonAstBuilderTest extends TestSuite {

  def parseJson(input: Predef.String): parser.json.AST = {
    val reporter = message.Reporter.create
    val treeOpt = parser.JsonParser.parse(None(), input, reporter)
    reporter.printMessages()
    assert(!reporter.hasError)
    parser.json.JsonAstBuilder(treeOpt.get).build()
  }

  def check(input: Predef.String, expected: Predef.String): Unit = {
    val ast = parseJson(input)
    val rendered = ast.toCompactST.render.value
    assert(rendered == expected)
  }

  def roundTrip(input: Predef.String): Unit = {
    val ast1 = parseJson(input)
    val rendered = ast1.toCompactST.render.value
    val ast2 = parseJson(rendered)
    assert(ast1 == ast2)
  }

  val tests = Tests {

    // String
    * - check("\"hello\"", "\"hello\"")

    // Integer
    * - check("42", "42")

    // Negative integer
    * - check("-1", "-1")

    // Boolean true
    * - check("true", "true")

    // Boolean false
    * - check("false", "false")

    // Null
    * - check("null", "null")

    // Empty object
    * - check("{}", "{}")

    // Empty array
    * - check("[]", "[]")

    // Array of integers
    * - check("[1, 2, 3]", "[1,2,3]")

    // Array with booleans and null
    * - check("[true, false, null]", "[true,false,null]")

    // Simple object
    * - check("{\"a\": 1}", "{\"a\":1}")

    // Object with multiple keys
    * - check("{\"a\": 1, \"b\": \"hello\"}", "{\"a\":1,\"b\":\"hello\"}")

    // Object with boolean and null values
    * - check("{\"x\": true, \"y\": false, \"z\": null}", "{\"x\":true,\"y\":false,\"z\":null}")

    // Nested object in array
    * - check("[{\"a\": 1}, {\"b\": 2}]", "[{\"a\":1},{\"b\":2}]")

    // Nested array in object
    * - check("{\"arr\": [1, true, null]}", "{\"arr\":[1,true,null]}")

    // Round-trip: mixed object
    * - roundTrip("{\"name\": \"test\", \"value\": 42, \"active\": true, \"data\": null}")

    // Round-trip: mixed array
    * - roundTrip("[1, \"two\", true, false, null]")

    // Round-trip: deeply nested
    * - roundTrip("{\"nested\": {\"arr\": [1, 2, 3], \"obj\": {\"key\": \"val\"}}}")

    // Round-trip: array of arrays
    * - roundTrip("[[1, 2], [3, 4]]")

    // Double value
    * - roundTrip("3.14")

    // Double in object
    * - roundTrip("{\"pi\": 3.14}")

    // Lexer error: unrecognized character
    * - {
      val reporter = message.Reporter.create
      val result = parser.JsonParser.parse(None(), "@invalid", reporter)
      assert(reporter.hasError)
      assert(result.isEmpty)
    }
  }
}
