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

  def parseLLkJson(input: Predef.String): parser.json.AST = {
    val reporter = message.Reporter.create
    val treeOpt = parser.JsonParser.parse(None(), input, reporter)
    reporter.printMessages()
    assert(!reporter.hasError)
    parser.json.JsonAstBuilder(treeOpt.get).build()
  }

  def parseLLkJsonc(input: Predef.String): parser.json.AST = {
    val reporter = message.Reporter.create
    val treeOpt = parser.JsoncParser.parse(None(), input, reporter)
    reporter.printMessages()
    assert(!reporter.hasError)
    parser.json.JsonAstBuilder(treeOpt.get).build()
  }

  def parseJackson(input: Predef.String): parser.json.AST = {
    JacksonJsonParser.parse(None(), input)
  }

  def parseJacksonJsonc(input: Predef.String): parser.json.AST = {
    JacksonJsonParser.parseJsonc(None(), input)
  }

  def check(input: Predef.String, expected: Predef.String): Unit = {
    val llkJson = parseLLkJson(input)
    val jackson = parseJackson(input)
    assert(llkJson == jackson)
    val rendered = llkJson.toCompactST.render.value
    assert(rendered == expected)
  }

  def roundTrip(input: Predef.String): Unit = {
    val llkJson = parseLLkJson(input)
    val jackson = parseJackson(input)
    assert(llkJson == jackson)
    val rendered = llkJson.toCompactST.render.value
    val llkJson2 = parseLLkJson(rendered)
    assert(llkJson == llkJson2)
  }

  def checkJsonc(jsonInput: Predef.String, jsoncInput: Predef.String): Unit = {
    val llkJson = parseLLkJson(jsonInput)
    val jackson = parseJackson(jsonInput)
    val llkJsonc = parseLLkJsonc(jsoncInput)
    val jacksonJsonc = parseJacksonJsonc(jsoncInput)
    assert(llkJson == jackson)
    assert(llkJson == llkJsonc)
    assert(llkJson == jacksonJsonc)
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

    // String with escape sequences
    * - check("\"hello\\nworld\"", "\"hello\\nworld\"")

    // String with unicode escape
    * - check("\"\\u0041\"", "\"A\"")

    // Zero integer
    * - check("0", "0")

    // Exponent number
    * - roundTrip("1.5e10")

    // Negative exponent
    * - roundTrip("1.5E-3")

    // Single element array
    * - check("[42]", "[42]")

    // Single key-value object
    * - roundTrip("{\"key\": \"value\"}")

    // Lexer error: JSON
    * - {
      val reporter = message.Reporter.create
      val result = parser.JsonParser.parse(None(), "@invalid", reporter)
      assert(reporter.hasError)
      assert(result.isEmpty)
    }

    // Lexer error: JSONC
    * - {
      val reporter = message.Reporter.create
      val result = parser.JsoncParser.parse(None(), "@invalid", reporter)
      assert(reporter.hasError)
      assert(result.isEmpty)
    }

    // JSONC: trailing comma in object
    * - checkJsonc("{\"a\": 1, \"b\": 2}", "{\"a\": 1, \"b\": 2,}")

    // JSONC: trailing comma in array
    * - checkJsonc("[1, 2, 3]", "[1, 2, 3,]")

    // JSONC: line comments
    * - checkJsonc("{\"x\": true, \"y\": false}", "{\n  // x value\n  \"x\": true,\n  // y value\n  \"y\": false\n}")

    // JSONC: block comments
    * - checkJsonc("{\"key\": \"value\"}", "{ /* key */ \"key\": /* val */ \"value\" }")

    // JSONC: trailing commas in nested structures
    * - checkJsonc(
      "{\"a\": {\"b\": 1}, \"c\": [2, 3]}",
      "{ /* outer */ \"a\": { /* inner */ \"b\": 1,}, \"c\": [2, 3,],}"
    )

    // JSONC: all value types with comments
    * - checkJsonc(
      "{\"i\": 42, \"d\": 3.14, \"s\": \"hello\", \"b\": true, \"n\": null}",
      "{\n  // int\n  \"i\": 42,\n  // dbl\n  \"d\": 3.14,\n  // str\n  \"s\": \"hello\",\n  // bool\n  \"b\": true,\n  // null\n  \"n\": null,\n}"
    )

    // JSONC: empty containers with comments
    * - checkJsonc("{}", "{ /* empty */ }")

    // JSONC: empty array with comment
    * - checkJsonc("[]", "[ /* empty */ ]")

    // JSONC: header/footer block comments
    * - checkJsonc("{\"version\": 1}", "/* config */\n{\"version\": 1}\n/* end */")

    // JSONC: single element with trailing comma
    * - checkJsonc("{\"a\": 1}", "{\"a\": 1,}")

    // JSONC: single element array with trailing comma
    * - checkJsonc("[42]", "[42,]")

    // JSONC: array of objects with trailing commas and comments
    * - checkJsonc(
      "[{\"id\": 1}, {\"id\": 2}]",
      "[\n  // first\n  {\"id\": 1,},\n  // second\n  {\"id\": 2,},\n]"
    )

    // JSONC: deeply nested with mixed comments and trailing commas
    * - checkJsonc(
      "{\"a\": {\"b\": {\"c\": [1, 2]}}}",
      "{ /* outer */ \"a\": { /* middle */ \"b\": { /* inner */ \"c\": [1, 2,],},},}"
    )

    // JSONC: multiple line comments
    * - checkJsonc(
      "{\"a\": 1, \"b\": 2}",
      "{\n  // comment 1\n  \"a\": 1,\n  // comment 2\n  \"b\": 2\n}"
    )

    // JSONC: block comment at start
    * - checkJsonc("{\"a\": 1}", "/* header */ {\"a\": 1}")

    // JSONC: all value types with comments and trailing commas
    * - checkJsonc(
      "{\"i\": 42, \"d\": 3.14, \"s\": \"hello\", \"b\": true, \"f\": false, \"n\": null}",
      "{\n  // integer\n  \"i\": 42,\n  // double\n  \"d\": 3.14,\n  // string\n  \"s\": \"hello\",\n  // bool\n  \"b\": true,\n  \"f\": false,\n  // null\n  \"n\": null,\n}"
    )

    // JSONC: round-trip through JSON (trailing commas stripped)
    * - {
      val jsoncAst = parseLLkJsonc("{\"a\": [1, 2,], \"b\": {\"x\": true,},}")
      val jacksonJsoncAst = parseJacksonJsonc("{\"a\": [1, 2,], \"b\": {\"x\": true,},}")
      assert(jsoncAst == jacksonJsoncAst)
      val compact = jsoncAst.toCompactST.render.value
      val jsonAst = parseLLkJson(compact)
      val jacksonAst = parseJackson(compact)
      assert(jsoncAst == jsonAst)
      assert(jsoncAst == jacksonAst)
    }

    // JSONC: parseRule directly
    * - {
      val reporter = message.Reporter.create
      val treeOpt = parser.JsoncParser.parseRule(None(), "{\"a\": 1}", "valueFile", reporter)
      assert(!reporter.hasError)
      val ast = parser.json.JsonAstBuilder(treeOpt.get).build()
      assert(ast.isInstanceOf[parser.json.AST.Obj])
    }

    // JSON: parseRule directly
    * - {
      val reporter = message.Reporter.create
      val treeOpt = parser.JsonParser.parseRule(None(), "\"hello\"", "valueFile", reporter)
      assert(!reporter.hasError)
      val ast = parser.json.JsonAstBuilder(treeOpt.get).build()
      assert(ast.isInstanceOf[parser.json.AST.Str])
    }

    // AST.toST: formatted object output
    * - {
      val ast = parseLLkJson("{\"a\": 1, \"b\": \"hello\"}")
      val formatted = ast.toST.render.value
      assert(formatted.contains("\n"))
      assert(formatted.contains("\"a\""))
    }

    // AST.toST: formatted array with primitives (inline)
    * - {
      val ast = parseLLkJson("[1, 2, 3]")
      val formatted = ast.toST.render.value
      assert(formatted == "[ 1, 2, 3 ]")
    }

    // AST.toST: formatted array with objects (multi-line)
    * - {
      val ast = parseLLkJson("[{\"a\": 1}, {\"b\": 2}]")
      val formatted = ast.toST.render.value
      assert(formatted.contains("\n"))
    }

    // AST.toST: formatted array with nested arrays (multi-line)
    * - {
      val ast = parseLLkJson("[[1], [2]]")
      val formatted = ast.toST.render.value
      assert(formatted.contains("\n"))
    }

    // AST.toST: empty array
    * - {
      val ast = parseLLkJson("[]")
      assert(ast.toST.render.value == "[]")
    }

    // AST.toST: scalar types
    * - {
      assert(parseLLkJson("42").toST.render.value == "42")
      assert(parseLLkJson("3.14").toST.render.value == "3.14")
      assert(parseLLkJson("\"hi\"").toST.render.value == "\"hi\"")
      assert(parseLLkJson("true").toST.render.value == "true")
      assert(parseLLkJson("false").toST.render.value == "false")
      assert(parseLLkJson("null").toST.render.value == "null")
    }

    // AST.Obj.asMap and Map accessors
    * - {
      val ast = parseLLkJson(
        "{\"i\": 42, \"d\": 3.14, \"s\": \"hello\", \"b\": true, \"n\": null, \"arr\": [1], \"obj\": {\"x\": 1}}"
      )
      val map = ast.asInstanceOf[parser.json.AST.Obj].asMap

      // get / getOpt
      assert(map.get("i").isInstanceOf[parser.json.AST.Int])
      assert(map.getOpt("i").nonEmpty)
      assert(map.getOpt("missing").isEmpty)

      // getInt / getIntOpt / getIntValueOpt
      assert(map.getInt("i").value == Z(42))
      assert(map.getIntOpt("i").nonEmpty)
      assert(map.getIntValueOpt("i") == Some(Z(42)))

      // getDbl / getDblOpt / getDblValueOpt
      assert(map.getDbl("d").value == F64(3.14))
      assert(map.getDblOpt("d").nonEmpty)
      assert(map.getDblValueOpt("d").nonEmpty)

      // getStr / getStrOpt / getStrValueOpt
      assert(map.getStr("s").value == String("hello"))
      assert(map.getStrOpt("s").nonEmpty)
      assert(map.getStrValueOpt("s") == Some(String("hello")))

      // getBool / getBoolOpt / getBoolValueOpt
      assert(map.getBool("b").value == T)
      assert(map.getBoolOpt("b").nonEmpty)
      assert(map.getBoolValueOpt("b") == Some(T))

      // getNull / getNullOpt
      assert(map.getNull("n").isInstanceOf[parser.json.AST.Null])
      assert(map.getNullOpt("n").nonEmpty)

      // getArr / getArrOpt
      assert(map.getArr("arr").values.size == 1)
      assert(map.getArrOpt("arr").nonEmpty)

      // getObj / getObjOpt
      assert(map.getObj("obj").keyValues.size == 1)
      assert(map.getObjOpt("obj").nonEmpty)
    }

    // AST equality and posOpt
    * - {
      val ast1 = parseLLkJson("{\"a\": 1}")
      val ast2 = parseLLkJson("{\"a\": 1}")
      assert(ast1 == ast2)
      assert(ast1.posOpt.nonEmpty)
    }
  }
}
