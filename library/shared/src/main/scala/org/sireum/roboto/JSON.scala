// #Sireum
// @formatter:off

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

// This file is auto-generated from Script.scala

package org.sireum.roboto

import org.sireum._
import org.sireum.Json.Printer._
import org.sireum.roboto.Command
import org.sireum.roboto.TypeText
import org.sireum.roboto.PressKey
import org.sireum.roboto.TypeChar
import org.sireum.roboto.MouseMove
import org.sireum.roboto.MouseClick
import org.sireum.roboto.MouseDoubleClick
import org.sireum.roboto.MouseDrag
import org.sireum.roboto.MouseWheel
import org.sireum.roboto.ClickImage
import org.sireum.roboto.WaitForImage
import org.sireum.roboto.Wait
import org.sireum.roboto.Notify
import org.sireum.roboto.Speak
import org.sireum.roboto.WaitForSpeech
import org.sireum.roboto.ScreenCapture
import org.sireum.roboto.ClickText
import org.sireum.roboto.WaitForText
import org.sireum.roboto.HideCursor
import org.sireum.roboto.ShowCursor
import org.sireum.roboto.Action
import org.sireum.roboto.Script

object JSON {

  object Printer {

    @pure def printKeyType(o: Key.Type): ST = {
      val value: String = o match {
        case Key.Enter => "Enter"
        case Key.Escape => "Escape"
        case Key.Tab => "Tab"
        case Key.Space => "Space"
        case Key.Backspace => "Backspace"
        case Key.Delete => "Delete"
        case Key.Up => "Up"
        case Key.Down => "Down"
        case Key.Left => "Left"
        case Key.Right => "Right"
        case Key.Home => "Home"
        case Key.End => "End"
        case Key.PageUp => "PageUp"
        case Key.PageDown => "PageDown"
        case Key.F1 => "F1"
        case Key.F2 => "F2"
        case Key.F3 => "F3"
        case Key.F4 => "F4"
        case Key.F5 => "F5"
        case Key.F6 => "F6"
        case Key.F7 => "F7"
        case Key.F8 => "F8"
        case Key.F9 => "F9"
        case Key.F10 => "F10"
        case Key.F11 => "F11"
        case Key.F12 => "F12"
      }
      return printObject(ISZ(
        ("type", printString("Key")),
        ("value", printString(value))
      ))
    }

    @pure def printModifierType(o: Modifier.Type): ST = {
      val value: String = o match {
        case Modifier.Ctrl => "Ctrl"
        case Modifier.Shift => "Shift"
        case Modifier.Alt => "Alt"
        case Modifier.Meta => "Meta"
      }
      return printObject(ISZ(
        ("type", printString("Modifier")),
        ("value", printString(value))
      ))
    }

    @pure def printMouseButtonType(o: MouseButton.Type): ST = {
      val value: String = o match {
        case MouseButton.Left => "Left"
        case MouseButton.Middle => "Middle"
        case MouseButton.Right => "Right"
      }
      return printObject(ISZ(
        ("type", printString("MouseButton")),
        ("value", printString(value))
      ))
    }

    @pure def printCommand(o: Command): ST = {
      o match {
        case o: TypeText => return printTypeText(o)
        case o: PressKey => return printPressKey(o)
        case o: TypeChar => return printTypeChar(o)
        case o: MouseMove => return printMouseMove(o)
        case o: MouseClick => return printMouseClick(o)
        case o: MouseDoubleClick => return printMouseDoubleClick(o)
        case o: MouseDrag => return printMouseDrag(o)
        case o: MouseWheel => return printMouseWheel(o)
        case o: ClickImage => return printClickImage(o)
        case o: WaitForImage => return printWaitForImage(o)
        case o: Wait => return printWait(o)
        case o: Notify => return printNotify(o)
        case o: Speak => return printSpeak(o)
        case o: WaitForSpeech => return printWaitForSpeech(o)
        case o: ScreenCapture => return printScreenCapture(o)
        case o: ClickText => return printClickText(o)
        case o: WaitForText => return printWaitForText(o)
        case o: HideCursor => return printHideCursor(o)
        case o: ShowCursor => return printShowCursor(o)
      }
    }

    @pure def printTypeText(o: TypeText): ST = {
      return printObject(ISZ(
        ("type", st""""TypeText""""),
        ("text", printString(o.text)),
        ("delayMs", printZ(o.delayMs))
      ))
    }

    @pure def printPressKey(o: PressKey): ST = {
      return printObject(ISZ(
        ("type", st""""PressKey""""),
        ("key", printKeyType(o.key)),
        ("modifiers", printISZ(F, o.modifiers, printModifierType _))
      ))
    }

    @pure def printTypeChar(o: TypeChar): ST = {
      return printObject(ISZ(
        ("type", st""""TypeChar""""),
        ("char", printC(o.char)),
        ("modifiers", printISZ(F, o.modifiers, printModifierType _))
      ))
    }

    @pure def printMouseMove(o: MouseMove): ST = {
      return printObject(ISZ(
        ("type", st""""MouseMove""""),
        ("x", printZ(o.x)),
        ("y", printZ(o.y))
      ))
    }

    @pure def printMouseClick(o: MouseClick): ST = {
      return printObject(ISZ(
        ("type", st""""MouseClick""""),
        ("button", printMouseButtonType(o.button)),
        ("x", printZ(o.x)),
        ("y", printZ(o.y))
      ))
    }

    @pure def printMouseDoubleClick(o: MouseDoubleClick): ST = {
      return printObject(ISZ(
        ("type", st""""MouseDoubleClick""""),
        ("button", printMouseButtonType(o.button)),
        ("x", printZ(o.x)),
        ("y", printZ(o.y))
      ))
    }

    @pure def printMouseDrag(o: MouseDrag): ST = {
      return printObject(ISZ(
        ("type", st""""MouseDrag""""),
        ("fromX", printZ(o.fromX)),
        ("fromY", printZ(o.fromY)),
        ("toX", printZ(o.toX)),
        ("toY", printZ(o.toY))
      ))
    }

    @pure def printMouseWheel(o: MouseWheel): ST = {
      return printObject(ISZ(
        ("type", st""""MouseWheel""""),
        ("notches", printZ(o.notches)),
        ("durationMs", printZ(o.durationMs))
      ))
    }

    @pure def printClickImage(o: ClickImage): ST = {
      return printObject(ISZ(
        ("type", st""""ClickImage""""),
        ("imagePath", printString(o.imagePath)),
        ("similarity", printF64(o.similarity)),
        ("xOffset", printZ(o.xOffset)),
        ("yOffset", printZ(o.yOffset))
      ))
    }

    @pure def printWaitForImage(o: WaitForImage): ST = {
      return printObject(ISZ(
        ("type", st""""WaitForImage""""),
        ("imagePath", printString(o.imagePath)),
        ("similarity", printF64(o.similarity)),
        ("timeoutMs", printZ(o.timeoutMs))
      ))
    }

    @pure def printWait(o: Wait): ST = {
      return printObject(ISZ(
        ("type", st""""Wait""""),
        ("ms", printZ(o.ms))
      ))
    }

    @pure def printNotify(o: Notify): ST = {
      return printObject(ISZ(
        ("type", st""""Notify""""),
        ("message", printString(o.message))
      ))
    }

    @pure def printSpeak(o: Speak): ST = {
      return printObject(ISZ(
        ("type", st""""Speak""""),
        ("text", printString(o.text)),
        ("async", printB(o.async))
      ))
    }

    @pure def printWaitForSpeech(o: WaitForSpeech): ST = {
      return printObject(ISZ(
        ("type", st""""WaitForSpeech"""")
      ))
    }

    @pure def printScreenCapture(o: ScreenCapture): ST = {
      return printObject(ISZ(
        ("type", st""""ScreenCapture""""),
        ("outputPath", printString(o.outputPath))
      ))
    }

    @pure def printClickText(o: ClickText): ST = {
      return printObject(ISZ(
        ("type", st""""ClickText""""),
        ("text", printString(o.text)),
        ("timeoutMs", printZ(o.timeoutMs)),
        ("xOffset", printZ(o.xOffset)),
        ("yOffset", printZ(o.yOffset))
      ))
    }

    @pure def printWaitForText(o: WaitForText): ST = {
      return printObject(ISZ(
        ("type", st""""WaitForText""""),
        ("text", printString(o.text)),
        ("timeoutMs", printZ(o.timeoutMs))
      ))
    }

    @pure def printHideCursor(o: HideCursor): ST = {
      return printObject(ISZ(
        ("type", st""""HideCursor"""")
      ))
    }

    @pure def printShowCursor(o: ShowCursor): ST = {
      return printObject(ISZ(
        ("type", st""""ShowCursor"""")
      ))
    }

    @pure def printAction(o: Action): ST = {
      return printObject(ISZ(
        ("type", st""""Action""""),
        ("name", printString(o.name)),
        ("commands", printISZ(F, o.commands, printCommand _))
      ))
    }

    @pure def printScript(o: Script): ST = {
      return printObject(ISZ(
        ("type", st""""Script""""),
        ("name", printString(o.name)),
        ("defaultCharDelayMs", printZ(o.defaultCharDelayMs)),
        ("defaultActionDelayMs", printZ(o.defaultActionDelayMs)),
        ("defaultSpeakGapMs", printZ(o.defaultSpeakGapMs)),
        ("actions", printISZ(F, o.actions, printAction _))
      ))
    }

  }

  @record class Parser(val input: String) {
    val parser: Json.Parser = Json.Parser.create(input)

    def errorOpt: Option[Json.ErrorMsg] = {
      return parser.errorOpt
    }

    def parseKeyType(): Key.Type = {
      val r = parseKeyT(F)
      return r
    }

    def parseKeyT(typeParsed: B): Key.Type = {
      if (!typeParsed) {
        parser.parseObjectType("Key")
      }
      parser.parseObjectKey("value")
      var i = parser.offset
      val s = parser.parseString()
      parser.parseObjectNext()
      Key.byName(s) match {
        case Some(r) => return r
        case _ =>
          parser.parseException(i, s"Invalid element name '$s' for Key.")
          return Key.byOrdinal(0).get
      }
    }

    def parseModifierType(): Modifier.Type = {
      val r = parseModifierT(F)
      return r
    }

    def parseModifierT(typeParsed: B): Modifier.Type = {
      if (!typeParsed) {
        parser.parseObjectType("Modifier")
      }
      parser.parseObjectKey("value")
      var i = parser.offset
      val s = parser.parseString()
      parser.parseObjectNext()
      Modifier.byName(s) match {
        case Some(r) => return r
        case _ =>
          parser.parseException(i, s"Invalid element name '$s' for Modifier.")
          return Modifier.byOrdinal(0).get
      }
    }

    def parseMouseButtonType(): MouseButton.Type = {
      val r = parseMouseButtonT(F)
      return r
    }

    def parseMouseButtonT(typeParsed: B): MouseButton.Type = {
      if (!typeParsed) {
        parser.parseObjectType("MouseButton")
      }
      parser.parseObjectKey("value")
      var i = parser.offset
      val s = parser.parseString()
      parser.parseObjectNext()
      MouseButton.byName(s) match {
        case Some(r) => return r
        case _ =>
          parser.parseException(i, s"Invalid element name '$s' for MouseButton.")
          return MouseButton.byOrdinal(0).get
      }
    }

    def parseCommand(): Command = {
      val t = parser.parseObjectTypes(ISZ("TypeText", "PressKey", "TypeChar", "MouseMove", "MouseClick", "MouseDoubleClick", "MouseDrag", "MouseWheel", "ClickImage", "WaitForImage", "Wait", "Notify", "Speak", "WaitForSpeech", "ScreenCapture", "ClickText", "WaitForText", "HideCursor", "ShowCursor"))
      t.native match {
        case "TypeText" => val r = parseTypeTextT(T); return r
        case "PressKey" => val r = parsePressKeyT(T); return r
        case "TypeChar" => val r = parseTypeCharT(T); return r
        case "MouseMove" => val r = parseMouseMoveT(T); return r
        case "MouseClick" => val r = parseMouseClickT(T); return r
        case "MouseDoubleClick" => val r = parseMouseDoubleClickT(T); return r
        case "MouseDrag" => val r = parseMouseDragT(T); return r
        case "MouseWheel" => val r = parseMouseWheelT(T); return r
        case "ClickImage" => val r = parseClickImageT(T); return r
        case "WaitForImage" => val r = parseWaitForImageT(T); return r
        case "Wait" => val r = parseWaitT(T); return r
        case "Notify" => val r = parseNotifyT(T); return r
        case "Speak" => val r = parseSpeakT(T); return r
        case "WaitForSpeech" => val r = parseWaitForSpeechT(T); return r
        case "ScreenCapture" => val r = parseScreenCaptureT(T); return r
        case "ClickText" => val r = parseClickTextT(T); return r
        case "WaitForText" => val r = parseWaitForTextT(T); return r
        case "HideCursor" => val r = parseHideCursorT(T); return r
        case "ShowCursor" => val r = parseShowCursorT(T); return r
        case _ => val r = parseShowCursorT(T); return r
      }
    }

    def parseTypeText(): TypeText = {
      val r = parseTypeTextT(F)
      return r
    }

    def parseTypeTextT(typeParsed: B): TypeText = {
      if (!typeParsed) {
        parser.parseObjectType("TypeText")
      }
      parser.parseObjectKey("text")
      val text = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("delayMs")
      val delayMs = parser.parseZ()
      parser.parseObjectNext()
      return TypeText(text, delayMs)
    }

    def parsePressKey(): PressKey = {
      val r = parsePressKeyT(F)
      return r
    }

    def parsePressKeyT(typeParsed: B): PressKey = {
      if (!typeParsed) {
        parser.parseObjectType("PressKey")
      }
      parser.parseObjectKey("key")
      val key = parseKeyType()
      parser.parseObjectNext()
      parser.parseObjectKey("modifiers")
      val modifiers = parser.parseISZ(parseModifierType _)
      parser.parseObjectNext()
      return PressKey(key, modifiers)
    }

    def parseTypeChar(): TypeChar = {
      val r = parseTypeCharT(F)
      return r
    }

    def parseTypeCharT(typeParsed: B): TypeChar = {
      if (!typeParsed) {
        parser.parseObjectType("TypeChar")
      }
      parser.parseObjectKey("char")
      val char = parser.parseC()
      parser.parseObjectNext()
      parser.parseObjectKey("modifiers")
      val modifiers = parser.parseISZ(parseModifierType _)
      parser.parseObjectNext()
      return TypeChar(char, modifiers)
    }

    def parseMouseMove(): MouseMove = {
      val r = parseMouseMoveT(F)
      return r
    }

    def parseMouseMoveT(typeParsed: B): MouseMove = {
      if (!typeParsed) {
        parser.parseObjectType("MouseMove")
      }
      parser.parseObjectKey("x")
      val x = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("y")
      val y = parser.parseZ()
      parser.parseObjectNext()
      return MouseMove(x, y)
    }

    def parseMouseClick(): MouseClick = {
      val r = parseMouseClickT(F)
      return r
    }

    def parseMouseClickT(typeParsed: B): MouseClick = {
      if (!typeParsed) {
        parser.parseObjectType("MouseClick")
      }
      parser.parseObjectKey("button")
      val button = parseMouseButtonType()
      parser.parseObjectNext()
      parser.parseObjectKey("x")
      val x = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("y")
      val y = parser.parseZ()
      parser.parseObjectNext()
      return MouseClick(button, x, y)
    }

    def parseMouseDoubleClick(): MouseDoubleClick = {
      val r = parseMouseDoubleClickT(F)
      return r
    }

    def parseMouseDoubleClickT(typeParsed: B): MouseDoubleClick = {
      if (!typeParsed) {
        parser.parseObjectType("MouseDoubleClick")
      }
      parser.parseObjectKey("button")
      val button = parseMouseButtonType()
      parser.parseObjectNext()
      parser.parseObjectKey("x")
      val x = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("y")
      val y = parser.parseZ()
      parser.parseObjectNext()
      return MouseDoubleClick(button, x, y)
    }

    def parseMouseDrag(): MouseDrag = {
      val r = parseMouseDragT(F)
      return r
    }

    def parseMouseDragT(typeParsed: B): MouseDrag = {
      if (!typeParsed) {
        parser.parseObjectType("MouseDrag")
      }
      parser.parseObjectKey("fromX")
      val fromX = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("fromY")
      val fromY = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("toX")
      val toX = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("toY")
      val toY = parser.parseZ()
      parser.parseObjectNext()
      return MouseDrag(fromX, fromY, toX, toY)
    }

    def parseMouseWheel(): MouseWheel = {
      val r = parseMouseWheelT(F)
      return r
    }

    def parseMouseWheelT(typeParsed: B): MouseWheel = {
      if (!typeParsed) {
        parser.parseObjectType("MouseWheel")
      }
      parser.parseObjectKey("notches")
      val notches = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("durationMs")
      val durationMs = parser.parseZ()
      parser.parseObjectNext()
      return MouseWheel(notches, durationMs)
    }

    def parseClickImage(): ClickImage = {
      val r = parseClickImageT(F)
      return r
    }

    def parseClickImageT(typeParsed: B): ClickImage = {
      if (!typeParsed) {
        parser.parseObjectType("ClickImage")
      }
      parser.parseObjectKey("imagePath")
      val imagePath = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("similarity")
      val similarity = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("xOffset")
      val xOffset = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("yOffset")
      val yOffset = parser.parseZ()
      parser.parseObjectNext()
      return ClickImage(imagePath, similarity, xOffset, yOffset)
    }

    def parseWaitForImage(): WaitForImage = {
      val r = parseWaitForImageT(F)
      return r
    }

    def parseWaitForImageT(typeParsed: B): WaitForImage = {
      if (!typeParsed) {
        parser.parseObjectType("WaitForImage")
      }
      parser.parseObjectKey("imagePath")
      val imagePath = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("similarity")
      val similarity = parser.parseF64()
      parser.parseObjectNext()
      parser.parseObjectKey("timeoutMs")
      val timeoutMs = parser.parseZ()
      parser.parseObjectNext()
      return WaitForImage(imagePath, similarity, timeoutMs)
    }

    def parseWait(): Wait = {
      val r = parseWaitT(F)
      return r
    }

    def parseWaitT(typeParsed: B): Wait = {
      if (!typeParsed) {
        parser.parseObjectType("Wait")
      }
      parser.parseObjectKey("ms")
      val ms = parser.parseZ()
      parser.parseObjectNext()
      return Wait(ms)
    }

    def parseNotify(): Notify = {
      val r = parseNotifyT(F)
      return r
    }

    def parseNotifyT(typeParsed: B): Notify = {
      if (!typeParsed) {
        parser.parseObjectType("Notify")
      }
      parser.parseObjectKey("message")
      val message = parser.parseString()
      parser.parseObjectNext()
      return Notify(message)
    }

    def parseSpeak(): Speak = {
      val r = parseSpeakT(F)
      return r
    }

    def parseSpeakT(typeParsed: B): Speak = {
      if (!typeParsed) {
        parser.parseObjectType("Speak")
      }
      parser.parseObjectKey("text")
      val text = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("async")
      val async = parser.parseB()
      parser.parseObjectNext()
      return Speak(text, async)
    }

    def parseWaitForSpeech(): WaitForSpeech = {
      val r = parseWaitForSpeechT(F)
      return r
    }

    def parseWaitForSpeechT(typeParsed: B): WaitForSpeech = {
      if (!typeParsed) {
        parser.parseObjectType("WaitForSpeech")
      }
      return WaitForSpeech()
    }

    def parseScreenCapture(): ScreenCapture = {
      val r = parseScreenCaptureT(F)
      return r
    }

    def parseScreenCaptureT(typeParsed: B): ScreenCapture = {
      if (!typeParsed) {
        parser.parseObjectType("ScreenCapture")
      }
      parser.parseObjectKey("outputPath")
      val outputPath = parser.parseString()
      parser.parseObjectNext()
      return ScreenCapture(outputPath)
    }

    def parseClickText(): ClickText = {
      val r = parseClickTextT(F)
      return r
    }

    def parseClickTextT(typeParsed: B): ClickText = {
      if (!typeParsed) {
        parser.parseObjectType("ClickText")
      }
      parser.parseObjectKey("text")
      val text = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("timeoutMs")
      val timeoutMs = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("xOffset")
      val xOffset = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("yOffset")
      val yOffset = parser.parseZ()
      parser.parseObjectNext()
      return ClickText(text, timeoutMs, xOffset, yOffset)
    }

    def parseWaitForText(): WaitForText = {
      val r = parseWaitForTextT(F)
      return r
    }

    def parseWaitForTextT(typeParsed: B): WaitForText = {
      if (!typeParsed) {
        parser.parseObjectType("WaitForText")
      }
      parser.parseObjectKey("text")
      val text = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("timeoutMs")
      val timeoutMs = parser.parseZ()
      parser.parseObjectNext()
      return WaitForText(text, timeoutMs)
    }

    def parseHideCursor(): HideCursor = {
      val r = parseHideCursorT(F)
      return r
    }

    def parseHideCursorT(typeParsed: B): HideCursor = {
      if (!typeParsed) {
        parser.parseObjectType("HideCursor")
      }
      return HideCursor()
    }

    def parseShowCursor(): ShowCursor = {
      val r = parseShowCursorT(F)
      return r
    }

    def parseShowCursorT(typeParsed: B): ShowCursor = {
      if (!typeParsed) {
        parser.parseObjectType("ShowCursor")
      }
      return ShowCursor()
    }

    def parseAction(): Action = {
      val r = parseActionT(F)
      return r
    }

    def parseActionT(typeParsed: B): Action = {
      if (!typeParsed) {
        parser.parseObjectType("Action")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("commands")
      val commands = parser.parseISZ(parseCommand _)
      parser.parseObjectNext()
      return Action(name, commands)
    }

    def parseScript(): Script = {
      val r = parseScriptT(F)
      return r
    }

    def parseScriptT(typeParsed: B): Script = {
      if (!typeParsed) {
        parser.parseObjectType("Script")
      }
      parser.parseObjectKey("name")
      val name = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("defaultCharDelayMs")
      val defaultCharDelayMs = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("defaultActionDelayMs")
      val defaultActionDelayMs = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("defaultSpeakGapMs")
      val defaultSpeakGapMs = parser.parseZ()
      parser.parseObjectNext()
      parser.parseObjectKey("actions")
      val actions = parser.parseISZ(parseAction _)
      parser.parseObjectNext()
      return Script(name, defaultCharDelayMs, defaultActionDelayMs, defaultSpeakGapMs, actions)
    }

    def eof(): B = {
      val r = parser.eof()
      return r
    }

  }

  def to[T](s: String, f: Parser => T): Either[T, Json.ErrorMsg] = {
    val parser = Parser(s)
    val r = f(parser)
    parser.eof()
    parser.errorOpt match {
      case Some(e) => return Either.Right(e)
      case _ => return Either.Left(r)
    }
  }

  def fromCommand(o: Command, isCompact: B): String = {
    val st = Printer.printCommand(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCommand(s: String): Either[Command, Json.ErrorMsg] = {
    def fCommand(parser: Parser): Command = {
      val r = parser.parseCommand()
      return r
    }
    val r = to(s, fCommand _)
    return r
  }

  def fromTypeText(o: TypeText, isCompact: B): String = {
    val st = Printer.printTypeText(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toTypeText(s: String): Either[TypeText, Json.ErrorMsg] = {
    def fTypeText(parser: Parser): TypeText = {
      val r = parser.parseTypeText()
      return r
    }
    val r = to(s, fTypeText _)
    return r
  }

  def fromPressKey(o: PressKey, isCompact: B): String = {
    val st = Printer.printPressKey(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toPressKey(s: String): Either[PressKey, Json.ErrorMsg] = {
    def fPressKey(parser: Parser): PressKey = {
      val r = parser.parsePressKey()
      return r
    }
    val r = to(s, fPressKey _)
    return r
  }

  def fromTypeChar(o: TypeChar, isCompact: B): String = {
    val st = Printer.printTypeChar(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toTypeChar(s: String): Either[TypeChar, Json.ErrorMsg] = {
    def fTypeChar(parser: Parser): TypeChar = {
      val r = parser.parseTypeChar()
      return r
    }
    val r = to(s, fTypeChar _)
    return r
  }

  def fromMouseMove(o: MouseMove, isCompact: B): String = {
    val st = Printer.printMouseMove(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toMouseMove(s: String): Either[MouseMove, Json.ErrorMsg] = {
    def fMouseMove(parser: Parser): MouseMove = {
      val r = parser.parseMouseMove()
      return r
    }
    val r = to(s, fMouseMove _)
    return r
  }

  def fromMouseClick(o: MouseClick, isCompact: B): String = {
    val st = Printer.printMouseClick(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toMouseClick(s: String): Either[MouseClick, Json.ErrorMsg] = {
    def fMouseClick(parser: Parser): MouseClick = {
      val r = parser.parseMouseClick()
      return r
    }
    val r = to(s, fMouseClick _)
    return r
  }

  def fromMouseDoubleClick(o: MouseDoubleClick, isCompact: B): String = {
    val st = Printer.printMouseDoubleClick(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toMouseDoubleClick(s: String): Either[MouseDoubleClick, Json.ErrorMsg] = {
    def fMouseDoubleClick(parser: Parser): MouseDoubleClick = {
      val r = parser.parseMouseDoubleClick()
      return r
    }
    val r = to(s, fMouseDoubleClick _)
    return r
  }

  def fromMouseDrag(o: MouseDrag, isCompact: B): String = {
    val st = Printer.printMouseDrag(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toMouseDrag(s: String): Either[MouseDrag, Json.ErrorMsg] = {
    def fMouseDrag(parser: Parser): MouseDrag = {
      val r = parser.parseMouseDrag()
      return r
    }
    val r = to(s, fMouseDrag _)
    return r
  }

  def fromMouseWheel(o: MouseWheel, isCompact: B): String = {
    val st = Printer.printMouseWheel(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toMouseWheel(s: String): Either[MouseWheel, Json.ErrorMsg] = {
    def fMouseWheel(parser: Parser): MouseWheel = {
      val r = parser.parseMouseWheel()
      return r
    }
    val r = to(s, fMouseWheel _)
    return r
  }

  def fromClickImage(o: ClickImage, isCompact: B): String = {
    val st = Printer.printClickImage(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toClickImage(s: String): Either[ClickImage, Json.ErrorMsg] = {
    def fClickImage(parser: Parser): ClickImage = {
      val r = parser.parseClickImage()
      return r
    }
    val r = to(s, fClickImage _)
    return r
  }

  def fromWaitForImage(o: WaitForImage, isCompact: B): String = {
    val st = Printer.printWaitForImage(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toWaitForImage(s: String): Either[WaitForImage, Json.ErrorMsg] = {
    def fWaitForImage(parser: Parser): WaitForImage = {
      val r = parser.parseWaitForImage()
      return r
    }
    val r = to(s, fWaitForImage _)
    return r
  }

  def fromWait(o: Wait, isCompact: B): String = {
    val st = Printer.printWait(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toWait(s: String): Either[Wait, Json.ErrorMsg] = {
    def fWait(parser: Parser): Wait = {
      val r = parser.parseWait()
      return r
    }
    val r = to(s, fWait _)
    return r
  }

  def fromNotify(o: Notify, isCompact: B): String = {
    val st = Printer.printNotify(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toNotify(s: String): Either[Notify, Json.ErrorMsg] = {
    def fNotify(parser: Parser): Notify = {
      val r = parser.parseNotify()
      return r
    }
    val r = to(s, fNotify _)
    return r
  }

  def fromSpeak(o: Speak, isCompact: B): String = {
    val st = Printer.printSpeak(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toSpeak(s: String): Either[Speak, Json.ErrorMsg] = {
    def fSpeak(parser: Parser): Speak = {
      val r = parser.parseSpeak()
      return r
    }
    val r = to(s, fSpeak _)
    return r
  }

  def fromWaitForSpeech(o: WaitForSpeech, isCompact: B): String = {
    val st = Printer.printWaitForSpeech(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toWaitForSpeech(s: String): Either[WaitForSpeech, Json.ErrorMsg] = {
    def fWaitForSpeech(parser: Parser): WaitForSpeech = {
      val r = parser.parseWaitForSpeech()
      return r
    }
    val r = to(s, fWaitForSpeech _)
    return r
  }

  def fromScreenCapture(o: ScreenCapture, isCompact: B): String = {
    val st = Printer.printScreenCapture(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toScreenCapture(s: String): Either[ScreenCapture, Json.ErrorMsg] = {
    def fScreenCapture(parser: Parser): ScreenCapture = {
      val r = parser.parseScreenCapture()
      return r
    }
    val r = to(s, fScreenCapture _)
    return r
  }

  def fromClickText(o: ClickText, isCompact: B): String = {
    val st = Printer.printClickText(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toClickText(s: String): Either[ClickText, Json.ErrorMsg] = {
    def fClickText(parser: Parser): ClickText = {
      val r = parser.parseClickText()
      return r
    }
    val r = to(s, fClickText _)
    return r
  }

  def fromWaitForText(o: WaitForText, isCompact: B): String = {
    val st = Printer.printWaitForText(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toWaitForText(s: String): Either[WaitForText, Json.ErrorMsg] = {
    def fWaitForText(parser: Parser): WaitForText = {
      val r = parser.parseWaitForText()
      return r
    }
    val r = to(s, fWaitForText _)
    return r
  }

  def fromHideCursor(o: HideCursor, isCompact: B): String = {
    val st = Printer.printHideCursor(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toHideCursor(s: String): Either[HideCursor, Json.ErrorMsg] = {
    def fHideCursor(parser: Parser): HideCursor = {
      val r = parser.parseHideCursor()
      return r
    }
    val r = to(s, fHideCursor _)
    return r
  }

  def fromShowCursor(o: ShowCursor, isCompact: B): String = {
    val st = Printer.printShowCursor(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toShowCursor(s: String): Either[ShowCursor, Json.ErrorMsg] = {
    def fShowCursor(parser: Parser): ShowCursor = {
      val r = parser.parseShowCursor()
      return r
    }
    val r = to(s, fShowCursor _)
    return r
  }

  def fromAction(o: Action, isCompact: B): String = {
    val st = Printer.printAction(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toAction(s: String): Either[Action, Json.ErrorMsg] = {
    def fAction(parser: Parser): Action = {
      val r = parser.parseAction()
      return r
    }
    val r = to(s, fAction _)
    return r
  }

  def fromScript(o: Script, isCompact: B): String = {
    val st = Printer.printScript(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toScript(s: String): Either[Script, Json.ErrorMsg] = {
    def fScript(parser: Parser): Script = {
      val r = parser.parseScript()
      return r
    }
    val r = to(s, fScript _)
    return r
  }

}