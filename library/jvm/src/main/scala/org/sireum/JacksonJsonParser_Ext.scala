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

import com.fasterxml.jackson.core.{JsonFactory, JsonParser => JacksonParser, JsonToken}
import com.fasterxml.jackson.core.json.JsonReadFeature

object JacksonJsonParser_Ext {

  private val factory: JsonFactory = new JsonFactory()

  private val jsoncFactory: JsonFactory = JsonFactory.builder()
    .enable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
    .enable(JsonReadFeature.ALLOW_TRAILING_COMMA)
    .build()

  def parse(uriOpt: Option[String], content: String): parser.json.AST = {
    doParse(factory, uriOpt, content)
  }

  def parseJsonc(uriOpt: Option[String], content: String): parser.json.AST = {
    doParse(jsoncFactory, uriOpt, content)
  }

  private def doParse(f: JsonFactory, uriOpt: Option[String], content: String): parser.json.AST = {
    val jp = f.createParser(content.value)
    jp.nextToken()
    val result = parseValue(uriOpt, jp)
    jp.close()
    result
  }

  private def posOpt(uriOpt: Option[String], jp: JacksonParser): Option[message.Position] = {
    val loc = jp.currentTokenLocation()
    val line = loc.getLineNr
    val col = loc.getColumnNr
    val offset = loc.getCharOffset
    val endLoc = jp.currentLocation()
    val endLine = endLoc.getLineNr
    val endCol = endLoc.getColumnNr
    val endOffset = endLoc.getCharOffset
    val length = endOffset - offset
    Some(message.FlatPos(
      uriOpt = uriOpt,
      beginLine32 = conversions.Z.toU32(line),
      beginColumn32 = conversions.Z.toU32(col),
      endLine32 = conversions.Z.toU32(endLine),
      endColumn32 = conversions.Z.toU32(endCol),
      offset32 = conversions.Z.toU32(offset),
      length32 = conversions.Z.toU32(length)
    ))
  }

  private def parseValue(uriOpt: Option[String], jp: JacksonParser): parser.json.AST = {
    jp.currentToken() match {
      case JsonToken.START_OBJECT => parseObject(uriOpt, jp)
      case JsonToken.START_ARRAY => parseArray(uriOpt, jp)
      case JsonToken.VALUE_STRING =>
        val pOpt = posOpt(uriOpt, jp)
        val v = parser.json.AST.Str(String(jp.getText), pOpt)
        v
      case JsonToken.VALUE_NUMBER_INT =>
        val pOpt = posOpt(uriOpt, jp)
        parser.json.AST.Int(Z.$String(jp.getText), pOpt)
      case JsonToken.VALUE_NUMBER_FLOAT =>
        val pOpt = posOpt(uriOpt, jp)
        parser.json.AST.Dbl(F64(jp.getDoubleValue), pOpt)
      case JsonToken.VALUE_TRUE =>
        val pOpt = posOpt(uriOpt, jp)
        parser.json.AST.Bool(T, pOpt)
      case JsonToken.VALUE_FALSE =>
        val pOpt = posOpt(uriOpt, jp)
        parser.json.AST.Bool(F, pOpt)
      case JsonToken.VALUE_NULL =>
        val pOpt = posOpt(uriOpt, jp)
        parser.json.AST.Null(pOpt)
      case token => halt(s"Unexpected JSON token: $token")
    }
  }

  private def parseObject(uriOpt: Option[String], jp: JacksonParser): parser.json.AST.Obj = {
    val pOpt = posOpt(uriOpt, jp)
    var keyValues = ISZ[parser.json.AST.KeyValue]()
    jp.nextToken()
    while (jp.currentToken() != JsonToken.END_OBJECT) {
      val keyPosOpt = posOpt(uriOpt, jp)
      val key = parser.json.AST.Str(String(jp.getText), keyPosOpt)
      jp.nextToken()
      val value = parseValue(uriOpt, jp)
      keyValues = keyValues :+ parser.json.AST.KeyValue(key, value)
      jp.nextToken()
    }
    parser.json.AST.Obj(keyValues, pOpt)
  }

  private def parseArray(uriOpt: Option[String], jp: JacksonParser): parser.json.AST.Arr = {
    val pOpt = posOpt(uriOpt, jp)
    var values = ISZ[parser.json.AST]()
    jp.nextToken()
    while (jp.currentToken() != JsonToken.END_ARRAY) {
      values = values :+ parseValue(uriOpt, jp)
      jp.nextToken()
    }
    parser.json.AST.Arr(values, pOpt)
  }
}
