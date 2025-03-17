// #Sireum
package org.sireum.parser.json

import org.sireum._
import org.sireum.parser.ParseTree

object ParserUtil {
  @ext("ParserUtil_Ext") object Ext {
    def parseJson(uriOpt: Option[String],
                  input: String,
                  reporter: message.Reporter): Option[ParseTree] = $
  }

  def build(uriOpt: Option[String],
            input: String,
            reporter: message.Reporter): Option[AST] = {
    Ext.parseJson(uriOpt, input, reporter) match {
      case Some(t) => return Some(AST.build(t))
      case _ => return None()
    }
  }
}