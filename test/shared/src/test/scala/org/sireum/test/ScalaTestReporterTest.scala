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

import java.io.{ByteArrayOutputStream, PrintStream}
import java.nio.charset.StandardCharsets

import org.scalatest.events.{Ordinal, TestCanceled, TestFailed}
import org.scalatest.exceptions.{TestCanceledException, TestFailedException}

class ScalaTestReporterTest extends TestSuite {

  private def capture(f: => Unit): Predef.String = {
    val buf = new ByteArrayOutputStream()
    val ps = new PrintStream(buf, true, StandardCharsets.UTF_8.name)
    Console.withOut(ps) {
      f
      ps.flush()
    }
    new Predef.String(buf.toByteArray, StandardCharsets.UTF_8)
  }

  "canceled tests print the reason without a stack trace" in {
    val ex = new TestCanceledException("toolchain missing", 0)
    val out = capture {
      new ScalaTestReporter().apply(TestCanceled(
        new Ordinal(1),
        "toolchain missing",
        "SomeSuite",
        "SomeSuite",
        Some("SomeSuite"),
        "some test",
        "some test",
        Vector.empty,
        Some(ex)))
    }
    assert(out.toLowerCase.contains("canceled") || out.contains("toolchain missing"), out)
    assert(!out.contains("at org."), out)
    assert(!out.contains("TestCanceledException"), out)
  }

  "failed tests still print a stack trace" in {
    val ex = new TestFailedException("boom", 0)
    val out = capture {
      new ScalaTestReporter().apply(TestFailed(
        new Ordinal(1),
        "boom",
        "SomeSuite",
        "SomeSuite",
        Some("SomeSuite"),
        "some test",
        "some test",
        Vector.empty,
        Vector.empty,
        Some(ex)))
    }
    assert(out.contains("boom") || out.toLowerCase.contains("failed"), out)
    assert(out.contains("at "), out)
  }
}
