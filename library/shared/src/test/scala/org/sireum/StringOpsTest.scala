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

package org.sireum

import org.sireum.test._

class StringOpsTest extends TestSuite {

  val tests = Tests {

    * - {
      val input = "1234567"
      val output = ops.StringOps(input).replaceAllLiterally("123abc", "_")
      assert(output === input)
    }

    * - {
      val input = "1234567"
      val output = ops.StringOps(input).replaceAllLiterally("12345678", "_")
      assert(output === input)
    }

    * - {
      val input = "1234567"
      val output = ops.StringOps(input).replaceAllLiterally("123", "_")
      assert(output === "_4567")
    }

    * - assert(ops.StringOps("a b").contains("b"))

    // chunk: exact multiple
    * - {
      val chunks = ops.StringOps("abcdef").chunk(z"3")
      assert(chunks.size == z"2")
      assert(chunks(0) == string"abc")
      assert(chunks(1) == string"def")
    }

    // chunk: remainder
    * - {
      val chunks = ops.StringOps("abcdefg").chunk(z"3")
      assert(chunks.size == z"3")
      assert(chunks(0) == string"abc")
      assert(chunks(1) == string"def")
      assert(chunks(2) == string"g")
    }

    // chunk: size larger than string
    * - {
      val chunks = ops.StringOps("ab").chunk(z"10")
      assert(chunks.size == z"1")
      assert(chunks(0) == string"ab")
    }

    // chunk: size equals string length
    * - {
      val chunks = ops.StringOps("abc").chunk(z"3")
      assert(chunks.size == z"1")
      assert(chunks(0) == string"abc")
    }

    // chunk: empty string
    * - {
      val chunks = ops.StringOps("").chunk(z"5")
      assert(chunks.size == z"0")
    }

    // chunk: size 1
    * - {
      val chunks = ops.StringOps("abc").chunk(z"1")
      assert(chunks.size == z"3")
      assert(chunks(0) == string"a")
      assert(chunks(1) == string"b")
      assert(chunks(2) == string"c")
    }

  }
}
