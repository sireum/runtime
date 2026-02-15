// #Sireum
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

// This file is auto-generated from JSON.g

package org.sireum.parser


import org.sireum._
import org.sireum.parser._

object JsonParser {
  val g: NGrammar = NGrammar.fromCompact("SAQAAOHHRAAJdmFsdWVGaWxlBQoA4gNFT0YGb2JqZWN0AXsICQDxECQ1AX0Ia2V5VmFsdWUGU1RSSU5HAToFYXJyYXkBWwgIAPAlJDEyAV0Jc3RyaW5nTGl0CWRvdWJsZUxpdAZOVU1CRVIGaW50TGl0B0lOVEVHRVIHYm9vbBAAgG51bGxMaXQECADlCm9iamVjdCQwJDEBLAwNAPYAMiQzCm9iamVjdCQwJDIMCwDDJDQIb2JqZWN0JDAKCQDkNSQ2CWFycmF5JDckOAwKAPUAOSQxMAlhcnJheSQ3JDkMCgDCJDExB2FycmF5JDcLCAD2BjEyJDEzCmJvb2xMaXQkMTQEdHJ1ZRAA8RA1BWZhbHNlAyd7JwMnWycGJ251bGwnBid0cnVlJwcnHQDwjScDJywnAyd9JwMnXScDJzonAyciJwMnXCcDJy8nAydiJwMnZicDJ24nAydyJwMndCcDJ3UnAyctJwMnMCcDJy4nAydFJwMnZScDJysnAldTAycgJwMnCicDJw0nAycJJ8cAARoAAAAAwgIBwgEJAcMCJQkBAQnCBw0ODwoMEBEKAAMKwgMABAQBwgUaAAYWCwAHC8IDAcMIAQAJJjIA8A8MAAoMwgMACwUBwgwiAA0eDQAODcIBAcMIAQ4ADw4KAPA7EAIPABEPwgEBwxIDEAETEMICIyQRABQRwgEAFQYSABYSwwIAFxMBwgcLFAAYFMMCAcIWEgHCGRcVABoVwwAXARkXwwIUFRgAGxgcAPAyBwsBwhkXGQAcGcMAGgEFGsMCGBkbAB0bwwIAFxMBwgEJHAAeHMMCAcIdGwHCHx8dACAdwwAfAR8fwwIcHSAAISAcAPGvAQkBwh8fIQAiIcMAIgEMIsMCICEjACMjwwEAJAckACUkwwEAJggBOwAACAEQAhIDJwQoBSkGKgcrCAEJAwoHCwoMDg0PDhEPExAUERYSLBMYFBoVLRYZFxsYHBkFGh0bHhwgHS4eHx8hICIhDCIjIyUkAiUvJjAnMSgyKTMqNCs1LDYtNy44LzkwOjE7MjwzPTQ+NT82QDdBOEI5QzoaAAEIAQAAAgAAAwAABAAABQAABgAABwAACAAAwgkBBhwA8hcBAwACBAADBQAEBgAGwwAFCgEBBAAAwgsBAQEAAMIMAQEFAADCDQ4A8hgOAQECAADCDwEBAwAAwhABAgcAAAgAAcIRAQEGAADCEgEBEwAAwhQHAPIKFQEBFgAAwhcBAhMAABYAAcIYAQEBAADCGRgA8gMaAQIBAAAWAAHCGwEBEwAAwhwHAPIfHQEBHgAAwh8BAhMAAB4AAcIgAQgBAAACAAADAAAEAAAFAAAGAAAHAAAIAADCIS0A8AgiAQEeAAHDAAAjAQEHAADCJAEBCAAAwg==")
  val lexerDfas: LexerDfas = LexerDfas.fromCompact("4QEAAPNWxw0ABidudWxsJwYndHJ1ZScHJ2ZhbHNlJwMneycDJ30nAyc6JwMnWycDJ10nAycsJwZTVFJJTkcHSU5URUdFUgZOVU1CRVICV1PHAAENBcLCwsLDAW5uAQF1dQIBbGwDAWxsBAAXAPADdHQBAXJyAgF1dQMBZWUEAAbCAQDxDsMBZmYBAWFhAgFsbAMBc3MEAWVlBQACwsMBe3sBCACifX0BAALCwwE6OggAoltbAQACwsMBXV0IAGIsLAEACMIBAPJfwwEiIgEFICEBIiIHI1sBXFwCXc4AEP//AQkiIgEvLwFcXAFiYgFmZgFubgFycgF0dAF1dQMDMDkEQUYEYWYEAzA5BUFGBWFmBQMwOQZBRgZhZgYDMDkBQUYBYWYBAATCwsPDAy0tATAwAjE5AwIHAHAAATA5AwnCAQDyAMPDw8MDLS0BMDAFMTkGAgcA8AABMDkHATA5CAMrKwMtLQMKAPIBLi4CRUUEZWUEBC4uAjA5Bg0A+Q8DMDkHRUUEZWUEATA5CALCwwQJCQEKCgENDQEgIAENAPcMAAECAwQFBgcICQoLDAYHCAQWJgUeEwEDAjbCAQAww8Ml")

  def parseRule(uriOpt: Option[String], content: String, ruleName: String, reporter: message.Reporter): Option[ParseTree] = {
    val cis = conversions.String.toCis(content)
    val docInfo = message.DocInfo.createFromCis(uriOpt, cis)
    val chars = Indexable.IszDocInfoC(cis, docInfo)
    val (errorIndex, tokens) = lexerDfas.tokens(chars, T)
    if (errorIndex >= 0) {
      reporter.error(chars.posOpt(errorIndex, 1), "JsonParser", st"Unrecognized character '${ops.COps(cis(errorIndex)).escapeString}'".render)
      return None()
    }
    return g.parse(ruleName, Indexable.fromIsz(tokens), reporter)
  }

  def parse(uriOpt: Option[String], content: String, reporter: message.Reporter): Option[ParseTree] = {
    return parseRule(uriOpt, content, "valueFile", reporter)
  }
}