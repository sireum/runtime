package org.sireum.parser.json

import org.antlr.runtime.tree.TreeAdaptor
import org.antlr.runtime.{ANTLRStringStream, CommonTokenStream, RecognitionException, TokenStream}
import org.sireum._
import org.sireum.helper.halt

import scala.collection.mutable.ListBuffer

object ParserUtil_Ext {
  import org.antlr.runtime.{Token => AntlrToken}

  private final case class Node(buffer: ListBuffer[Any]) {
    def asLeaf: AntlrToken = buffer.head.asInstanceOf[AntlrToken]
    def pos(docInfo: message.DocInfo): Option[message.Position] = {
      var r: message.Position = ParserUtil_Ext.pos(docInfo, buffer.head.asInstanceOf[AntlrToken]).get
      if (buffer.size > 1) r = r.to(ParserUtil_Ext.pos(docInfo, buffer.last.asInstanceOf[AntlrToken]).get)
      Some(r)
    }
  }

  private def pos(docInfo: message.DocInfo, token: AntlrToken): Option[message.Position] = {
    val offset = docInfo.lineOffsets(org.sireum.Z(token.getLine - 1)).toZ + org.sireum.Z(token.getCharPositionInLine)
    val length = token.getText.length
    val offsetLength = (org.sireum.conversions.Z.toU64(offset) << org.sireum.U64(32)) | org.sireum.U64(length)
    Some(org.sireum.message.PosInfo(docInfo, offsetLength))
  }

  private final class Adaptor(docInfo: message.DocInfo) extends TreeAdaptor {

    def pos(token: AntlrToken): Option[message.Position] = ParserUtil_Ext.pos(docInfo, token)

    override def create(payload: AntlrToken): AntlrToken = {
      payload
    }

    override def dupNode(treeNode: Any): AnyRef = halt("Infeasible")

    override def dupTree(tree: Any): AnyRef = halt("Infeasible")

    override def nil(): AnyRef = Node(ListBuffer())

    override def errorNode(input: TokenStream, start: AntlrToken, stop: AntlrToken, e: RecognitionException): AnyRef = {
      val name = Thread.currentThread.getStackTrace()(2).getMethodName
      AST.Str(s"$name: ${if (e.getMessage == null) e.getClass.getName else e.getMessage}",
        Some(pos(start).get.to(pos(stop).get)))
    }

    override def isNil(tree: Any): Boolean = halt("Infeasible")

    override def addChild(t: Any, child: Any): Unit = {
      val node = t.asInstanceOf[Node]
      node.buffer.addOne(child)
    }

    override def becomeRoot(newRoot: Any, oldRoot: Any): Any = halt("Infeasible")

    override def rulePostProcessing(root: Any): Any = {
      val node = root.asInstanceOf[Node]
      val name = Thread.currentThread.getStackTrace()(2).getMethodName
      name match {
        case "valueFile" => node.buffer.head
        case "value" => node.buffer.head
        case "object" =>
          val kvs = ISZ((for (e <- node.buffer.toSeq if e.isInstanceOf[AST.KeyValue]) yield e.asInstanceOf[AST.KeyValue]): _*)
          AST.Obj(kvs, node.pos(docInfo))
        case "array" =>
          val values = ISZ((for (e <- node.buffer.toSeq if e.isInstanceOf[AST]) yield e.asInstanceOf[AST]): _*)
          AST.Arr(values, node.pos(docInfo))
        case "keyValue" =>
          val keyToken = node.asLeaf
          val keyText = Json.Parser(conversions.String.toCis(keyToken.getText), 0, None()).parseString()
          val key = AST.Str(keyText, pos(keyToken))
          val value = node.buffer(2).asInstanceOf[AST]
          AST.KeyValue(key, value)
        case "stringLit" =>
          val token = node.asLeaf
          val text = Json.Parser(conversions.String.toCis(node.asLeaf.getText), 0, None()).parseString()
          AST.Str(text, pos(token))
        case "doubleLit" =>
          val token = node.asLeaf
          AST.Dbl(F64(token.getText).get, pos(node.asLeaf))
        case "intLit" =>
          val token = node.asLeaf
          AST.Int(Z(token.getText).get, pos(node.asLeaf))
        case "boolLit" =>
          val token = node.asLeaf
          AST.Bool("true" == token.getText, pos(node.asLeaf))
        case "nullLit" => AST.Null(pos(node.asLeaf))
        case _ => halt(s"Infeasible: $name")
      }
    }

    override def getUniqueID(node: Any): Int = halt("Infeasible")

    override def becomeRoot(newRoot: AntlrToken, oldRoot: Any): AnyRef = halt("Infeasible")

    override def create(tokenType: Int, fromToken: AntlrToken): AnyRef = halt("Infeasible")

    override def create(tokenType: Int, fromToken: AntlrToken, text: Predef.String): AnyRef = halt("Infeasible")

    override def create(tokenType: Int, text: Predef.String): AnyRef = halt("Infeasible")

    override def getType(t: Any): Int = halt("Infeasible")

    override def setType(t: Any, `type`: Int): Unit = halt("Infeasible")

    override def getText(t: Any): Predef.String = halt("Infeasible")

    override def setText(t: Any, text: Predef.String): Unit = halt("Infeasible")

    override def getToken(t: Any): AntlrToken = halt("Infeasible")

    override def setTokenBoundaries(t: Any, startToken: AntlrToken, stopToken: AntlrToken): Unit = {
      t match {
        case _: Node =>
        case _ => halt(s"Infeasible: $t")
      }
    }

    override def getTokenStartIndex(t: Any): Int = halt("Infeasible")

    override def getTokenStopIndex(t: Any): Int = halt("Infeasible")

    override def getChild(t: Any, i: Int): AnyRef = halt("Infeasible")

    override def setChild(t: Any, i: Int, child: Any): Unit = halt("Infeasible")

    override def deleteChild(t: Any, i: Int): AnyRef = halt("Infeasible")

    override def getChildCount(t: Any): Int = halt("Infeasible")

    override def getParent(t: Any): AnyRef = halt("Infeasible")

    override def setParent(t: Any, parent: Any): Unit = halt("Infeasible")

    override def getChildIndex(t: Any): Int = halt("Infeasible")

    override def setChildIndex(t: Any, index: Int): Unit = halt("Infeasible")

    override def replaceChildren(parent: Any, startChildIndex: Int, stopChildIndex: Int, t: Any): Unit = halt("Infeasible")
  }

  def parseJson(uriOpt: Option[String],
                input: String,
                reporter: message.Reporter): Option[AST] = {
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
    r.setTreeAdaptor(new Adaptor(docInfo))
    val tree = r.valueFile().tree.asInstanceOf[AST]
    return Some(tree)
  }

}
