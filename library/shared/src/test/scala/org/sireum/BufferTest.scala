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

class BufferTest extends TestSuite {

  val tests = Tests {

    // create: empty
    * - {
      val buf = Buffer.create[Z]()
      assert(buf.size == z"0")
      assert(buf.isEmpty)
    }

    // append and get
    * - {
      val buf = Buffer.create[Z]()
      buf.append(z"10")
      buf.append(z"20")
      buf.append(z"30")
      assert(buf.size == z"3")
      assert(buf.get(z"0") == z"10")
      assert(buf.get(z"1") == z"20")
      assert(buf.get(z"2") == z"30")
    }

    // set
    * - {
      val buf = Buffer.create[Z]()
      buf.append(z"1")
      buf.append(z"2")
      buf.set(z"0", z"99")
      assert(buf.get(z"0") == z"99")
      assert(buf.get(z"1") == z"2")
    }

    // += operator
    * - {
      val buf = Buffer.create[String]()
      buf += "a"
      buf += "b"
      assert(buf.size == z"2")
      assert(buf.get(z"0") == string"a")
      assert(buf.get(z"1") == string"b")
    }

    // clear
    * - {
      val buf = Buffer.create[Z]()
      buf.append(z"1")
      buf.append(z"2")
      assert(buf.nonEmpty)
      buf.clear()
      assert(buf.isEmpty)
      assert(buf.size == z"0")
      // can append after clear
      buf.append(z"99")
      assert(buf.size == z"1")
      assert(buf.get(z"0") == z"99")
    }

    // createWithCapacity
    * - {
      val buf = Buffer.createWithCapacity[Z](z"4", z"0")
      assert(buf.size == z"0")
      buf.append(z"1")
      buf.append(z"2")
      assert(buf.size == z"2")
    }

    // grow past initial capacity
    * - {
      val buf = Buffer.createWithCapacity[Z](z"2", z"0")
      var i: Z = 0
      while (i < z"20") {
        buf.append(i)
        i = i + 1
      }
      assert(buf.size == z"20")
      i = 0
      while (i < z"20") {
        assert(buf.get(i) == i)
        i = i + 1
      }
    }

    // toIS
    * - {
      val buf = Buffer.create[Z]()
      buf.append(z"10")
      buf.append(z"20")
      buf.append(z"30")
      val is = buf.toIS
      assert(is.size == z"3")
      assert(is(0) == z"10")
      assert(is(1) == z"20")
      assert(is(2) == z"30")
    }

    // Buffer[B]
    * - {
      val buf = Buffer.create[B]()
      buf.append(T)
      buf.append(F)
      buf.append(T)
      assert(buf.size == z"3")
      assert(buf.get(z"0") == T)
      assert(buf.get(z"1") == F)
      assert(buf.get(z"2") == T)
    }

    // Buffer[String]
    * - {
      val buf = Buffer.create[String]()
      buf.append("hello")
      buf.append("world")
      val is = buf.toIS
      assert(is.size == z"2")
      assert(is(0) == string"hello")
      assert(is(1) == string"world")
    }

  }
}
