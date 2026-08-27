/*
 * Copyright (c) 2017-2026,Robby, Kansas State University
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sireum.test

import org.scalatest.ResourcefulReporter
import org.scalatest.events._

/**
 * Default `proyek test` ScalaTest stdout reporter: full stacks on failures,
 * no stacks on canceled tests. Lives next to `SireumSpec` so every proyek
 * test classpath already has it; runners pass `-C org.sireum.test.ScalaTestReporter`.
 *
 * Implemented on the public `ResourcefulReporter` / `Event` API only.
 * ScalaTest's console printers are `private[scalatest]` and are not a
 * supported extension point.
 */
final class ScalaTestReporter extends ResourcefulReporter {

  private val Reset = "\u001b[0m"
  private val Green = "\u001b[32m"
  private val Cyan = "\u001b[36m"
  private val Yellow = "\u001b[33m"
  private val Red = "\u001b[31m"

  def apply(event: Event): Unit = {
    event match {
      case canceled: TestCanceled =>
        printEvent(Yellow, canceledLine(canceled), None)
      case failed: TestFailed =>
        printEvent(Red, failedLine(failed), failed.throwable)
      case aborted: SuiteAborted =>
        printEvent(Red, suiteAbortedLine(aborted), aborted.throwable)
      case aborted: RunAborted =>
        printEvent(Red, runAbortedLine(aborted), aborted.throwable)
      case other =>
        lineOf(other).foreach(printEvent(colorOf(other), _, None))
    }
    Console.out.flush()
  }

  def dispose(): Unit = {
    Console.out.flush()
  }

  private def printEvent(color: String, line: String, throwable: Option[Throwable]): Unit = {
    Console.out.println(color + line + Reset)
    throwable.foreach(printStack)
  }

  private def printStack(t: Throwable): Unit = {
    val buf = new java.io.StringWriter()
    t.printStackTrace(new java.io.PrintWriter(buf, true))
    Console.out.print(buf.toString)
  }

  private def canceledLine(event: TestCanceled): String = {
    formatted(event).getOrElse(
      s"Test Canceled - ${event.suiteName}: ${event.testName}: ${event.message}")
  }

  private def failedLine(event: TestFailed): String = {
    formatted(event).getOrElse(
      s"TEST FAILED - ${event.suiteName}: ${event.testName}: ${event.message}")
  }

  private def suiteAbortedLine(event: SuiteAborted): String = {
    formatted(event).getOrElse(
      s"SUITE ABORTED - ${event.suiteName}: ${event.message}")
  }

  private def runAbortedLine(event: RunAborted): String = {
    formatted(event).getOrElse(s"*** RUN ABORTED *** ${event.message}")
  }

  private def formatted(event: Event): Option[String] = {
    event.formatter match {
      case Some(MotionToSuppress) => None
      case Some(IndentedText(formattedText, _, _)) => Some(formattedText)
      case _ => None
    }
  }

  private def lineOf(event: Event): Option[String] = {
    formatted(event).orElse {
      event match {
        case _: DiscoveryStarting => Some("Discovery starting.")
        case completed: DiscoveryCompleted =>
          Some(completed.duration match {
            case Some(ms) => s"Discovery completed in $ms milliseconds."
            case None => "Discovery completed."
          })
        case starting: RunStarting =>
          Some(s"Run starting. Expected test count is: ${starting.testCount}")
        case completed: RunCompleted =>
          Some(completed.summary match {
            case Some(summary) =>
              s"Run completed.${durationSuffix(completed.duration)}\n" +
                s"Total number of tests run: ${summary.testsCompletedCount}\n" +
                s"Suites: completed ${summary.suitesCompletedCount}, aborted ${summary.suitesAbortedCount}\n" +
                s"Tests: succeeded ${summary.testsSucceededCount}, failed ${summary.testsFailedCount}, " +
                s"canceled ${summary.testsCanceledCount}, ignored ${summary.testsIgnoredCount}, pending ${summary.testsPendingCount}"
            case None => s"Run completed.${durationSuffix(completed.duration)}"
          })
        case stopped: RunStopped =>
          Some(s"Run stopped.${durationSuffix(stopped.duration)}")
        case starting: SuiteStarting => Some(s"${starting.suiteName}:")
        case _: SuiteCompleted => None
        case succeeded: TestSucceeded =>
          Some(s"- ${succeeded.testName}")
        case ignored: TestIgnored =>
          Some(s"- ${ignored.testName} !!! IGNORED !!!")
        case pending: TestPending =>
          Some(s"- ${pending.testName} (pending)")
        case info: InfoProvided => Some(info.message)
        case alert: AlertProvided => Some(alert.message)
        case note: NoteProvided => Some(note.message)
        case opened: ScopeOpened => Some(opened.message)
        case _: ScopeClosed => None
        case pending: ScopePending => Some(s"${pending.message} (pending)")
        case markup: MarkupProvided => Some(markup.text)
        case _ => None
      }
    }
  }

  private def durationSuffix(duration: Option[Long]): String = {
    duration match {
      case Some(ms) => s" ${ms} milliseconds."
      case None => ""
    }
  }

  private def colorOf(event: Event): String = {
    event match {
      case _: TestSucceeded | _: SuiteCompleted => Green
      case _: TestIgnored | _: TestPending | _: ScopePending | _: AlertProvided => Yellow
      case _: DiscoveryStarting | _: DiscoveryCompleted | _: RunStarting |
           _: RunCompleted | _: RunStopped | _: SuiteStarting => Cyan
      case _ => Green
    }
  }
}
