// #Sireum
package org.sireum.parser.json

import org.sireum._

object ParserUtil {
  @ext("ParserUtil_Ext") object Ext {
    def parseJson(uriOpt: Option[String],
                  input: String,
                  reporter: message.Reporter): Option[AST] = $
  }

  def build(uriOpt: Option[String],
            input: String,
            reporter: message.Reporter): Option[AST] = {
    return Ext.parseJson(uriOpt, input, reporter)
  }
}