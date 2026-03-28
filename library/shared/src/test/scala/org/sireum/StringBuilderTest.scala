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

package org.sireum

import org.sireum.test._

class StringBuilderTest extends TestSuite {

  val tests = Tests {

    // create: empty
    * - {
      val sb = StringBuilder.create()
      assert(sb.size == z"0")
      assert(sb.isEmpty)
      assert(sb.result == string"")
    }

    // fromString
    * - {
      val sb = StringBuilder.fromString("hello")
      assert(sb.size == z"5")
      assert(sb.nonEmpty)
      assert(sb.result == string"hello")
    }

    // append string
    * - {
      val sb = StringBuilder.create()
      sb.append("hello")
      sb.append(" ")
      sb.append("world")
      assert(sb.result == string"hello world")
      assert(sb.size == z"11")
    }

    // appendC
    * - {
      val sb = StringBuilder.create()
      sb.appendC('a')
      sb.appendC('b')
      sb.appendC('c')
      assert(sb.result == string"abc")
    }

    // appendZ
    * - {
      val sb = StringBuilder.create()
      sb.append("value=")
      sb.appendZ(z"42")
      assert(sb.result == string"value=42")
    }

    // appendB
    * - {
      val sb = StringBuilder.create()
      sb.appendB(T)
      sb.append(",")
      sb.appendB(F)
      assert(sb.result == string"true,false")
    }

    // clear
    * - {
      val sb = StringBuilder.fromString("hello")
      assert(sb.nonEmpty)
      sb.clear()
      assert(sb.isEmpty)
      assert(sb.size == z"0")
      assert(sb.result == string"")
      // can append after clear
      sb.append("world")
      assert(sb.result == string"world")
    }

    // grow past initial capacity
    * - {
      val sb = StringBuilder.createWithCapacity(z"4")
      sb.append("abcdefghij") // 10 chars, exceeds cap 4
      assert(sb.size == z"10")
      assert(sb.result == string"abcdefghij")
    }

    // string method matches result
    * - {
      val sb = StringBuilder.fromString("test")
      assert(sb.string == sb.result)
    }

    // unicode characters
    * - {
      val sb = StringBuilder.create()
      sb.append("\u001b[2K")
      sb.appendC('\n')
      assert(sb.size == z"5")
      val r = sb.result
      val cis = conversions.String.toCis(r)
      assert(cis(0) == C('\u001b'))
      assert(cis(1) == C('['))
      assert(cis(4) == C('\n'))
    }

    // multiple grows
    * - {
      val sb = StringBuilder.createWithCapacity(z"2")
      var i: Z = 0
      while (i < z"100") {
        sb.appendC('x')
        i = i + 1
      }
      assert(sb.size == z"100")
      val r = sb.result
      assert(r.size == z"100")
    }

  }
}
