package org.sireum.parser.json

import org.antlr.runtime.{ANTLRStringStream, CommonTokenStream, RecognitionException}
import org.sireum._
import org.sireum.parser.{Antlr3Util, ParseTree}

object ParserUtil_Ext {

  def parseJson(uriOpt: Option[String],
                input: String,
                reporter: message.Reporter): Option[ParseTree] = {
    val docInfo = message.DocInfo.create(uriOpt, input)

    val lex = new JSONLexer(new ANTLRStringStream(input.value)) {
      override def displayRecognitionError(tokenNames: Array[Predef.String], e: RecognitionException): Unit = {
        val msg = getErrorMessage(e, tokenNames)
        val line = e.line
        val column = U32(e.charPositionInLine)
        val offsetLength = (conversions.U32.toU64(docInfo.lineOffsets(line - 1) + column) << U64(32)) | U64(1)
        reporter.error(Some(message.PosInfo(docInfo, offsetLength)), "JSONLexer", msg)
      }
    }
    val cts = new CommonTokenStream(lex)
    val r = new JSONParser(cts) {
      override def displayRecognitionError(tokenNames: Array[Predef.String], e: RecognitionException): Unit = {
        val msg = getErrorMessage(e, tokenNames)
        val line = e.line
        val column = U32(e.charPositionInLine)
        val offsetLength = (conversions.U32.toU64(docInfo.lineOffsets(line - 1) + column) << U64(32)) | U64(e.token.getText.length)
        reporter.error(Some(message.PosInfo(docInfo, offsetLength)), "JSONParser", msg)
      }
    }
    r.setTreeAdaptor(new Antlr3Util.Adaptor(JSONParser.tokenNames, docInfo))
    val tree = r.valueFile().tree.asInstanceOf[ParseTree]
    return Some(tree)
  }

}
