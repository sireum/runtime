// #Sireum
/*
 Copyright (c) 2017-2026, Robby, Kansas State University
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
package org.sireum.roboto

import org.sireum._

@enum object Key {
  "Enter"
  "Escape"
  "Tab"
  "Space"
  "Backspace"
  "Delete"
  "Up"
  "Down"
  "Left"
  "Right"
  "Home"
  "End"
  "PageUp"
  "PageDown"
  "F1"
  "F2"
  "F3"
  "F4"
  "F5"
  "F6"
  "F7"
  "F8"
  "F9"
  "F10"
  "F11"
  "F12"
}

@enum object Modifier {
  "Ctrl"
  "Shift"
  "Alt"
  "Meta"
}

@enum object MouseButton {
  "Left"
  "Middle"
  "Right"
}

@datatype trait Command

@datatype class TypeText(val text: String,
                         val delayMs: Z) extends Command

@datatype class PressKey(val key: Key.Type,
                         val modifiers: ISZ[Modifier.Type]) extends Command

@datatype class TypeChar(val char: C,
                         val modifiers: ISZ[Modifier.Type]) extends Command

@datatype class MouseMove(val x: Z,
                          val y: Z) extends Command

@datatype class MouseClick(val button: MouseButton.Type,
                           val x: Z,
                           val y: Z) extends Command

@datatype class MouseDoubleClick(val button: MouseButton.Type,
                                 val x: Z,
                                 val y: Z) extends Command

@datatype class MouseDrag(val fromX: Z,
                          val fromY: Z,
                          val toX: Z,
                          val toY: Z) extends Command

// Rotate the mouse scroll wheel by `notches` ticks: positive scrolls
// down/toward the user, negative scrolls up/away (matches
// java.awt.Robot.mouseWheel's contract).  When `durationMs > 0` the
// runner emits one tick every `durationMs / |notches|` milliseconds for
// a smooth visual scroll; `durationMs = 0` rotates all ticks at once.
@datatype class MouseWheel(val notches: Z,
                           val durationMs: Z) extends Command

@datatype class ClickImage(val imagePath: String,
                           val similarity: F64,
                           val xOffset: Z,
                           val yOffset: Z) extends Command

@datatype class WaitForImage(val imagePath: String,
                             val similarity: F64,
                             val timeoutMs: Z) extends Command

@datatype class Wait(val ms: Z) extends Command

@datatype class Notify(val message: String) extends Command

@datatype class Speak(val text: String,
                      val async: B) extends Command

@datatype class WaitForSpeech() extends Command

@datatype class ScreenCapture(val outputPath: String) extends Command

@datatype class ClickText(val text: String,
                          val timeoutMs: Z,
                          val xOffset: Z,
                          val yOffset: Z) extends Command

@datatype class WaitForText(val text: String,
                            val timeoutMs: Z) extends Command

// Hide the mouse cursor for the rest of the script: stops compositing the
// cursor overlay onto recorded frames and parks the OS pointer offscreen
// so it doesn't appear anywhere in the capture.
@datatype class HideCursor() extends Command

// Re-enable the cursor overlay (and leave the OS pointer wherever it
// currently is — callers can `mouseMove` to reposition).
@datatype class ShowCursor() extends Command

@datatype class Action(val name: String,
                       val commands: ISZ[Command])

@datatype class Script(val name: String,
                       val defaultCharDelayMs: Z,
                       val defaultActionDelayMs: Z,
                       val actions: ISZ[Action])
