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

package org.sireum

import org.sireum.U8._
import org.sireum.test._

class Base64Test extends TestSuite {

  val tests = Tests {

    // Base64: empty input
    * - {
      val encoded = ops.StringOps.toBase64(ISZ[U8]())
      assert(encoded == string"")
      val decoded = ops.StringOps.fromBase64("")
      assert(decoded.isLeft)
      assert(decoded.left == ISZ[U8]())
    }

    // Base64: 1 byte (remaining == 1, pad == 2)
    * - {
      val data = ISZ[U8](u8"0x41") // 'A' = 65
      val encoded = ops.StringOps.toBase64(data)
      assert(encoded == string"QQ==")
      val decoded = ops.StringOps.fromBase64(encoded)
      assert(decoded.isLeft)
      assert(decoded.left == data)
    }

    // Base64: 2 bytes (remaining == 2, pad == 1)
    * - {
      val data = ISZ[U8](u8"0x41", u8"0x42") // 'A','B'
      val encoded = ops.StringOps.toBase64(data)
      assert(encoded == string"QUI=")
      val decoded = ops.StringOps.fromBase64(encoded)
      assert(decoded.isLeft)
      assert(decoded.left == data)
    }

    // Base64: 3 bytes (no padding, remaining == 0)
    * - {
      val data = ISZ[U8](u8"0x41", u8"0x42", u8"0x43") // 'A','B','C'
      val encoded = ops.StringOps.toBase64(data)
      assert(encoded == string"QUJD")
      val decoded = ops.StringOps.fromBase64(encoded)
      assert(decoded.isLeft)
      assert(decoded.left == data)
    }

    // Base64: 6 bytes (multiple groups, no padding)
    * - {
      val data = ISZ[U8](u8"0x00", u8"0xFF", u8"0x80", u8"0x7F", u8"0x01", u8"0xFE")
      val encoded = ops.StringOps.toBase64(data)
      val decoded = ops.StringOps.fromBase64(encoded)
      assert(decoded.isLeft)
      assert(decoded.left == data)
    }

    // Base64: invalid length (not multiple of 4)
    * - {
      val result = ops.StringOps.fromBase64("QQ=")
      assert(result.isRight)
    }

    // Base64: invalid character at position 0/1
    * - {
      val result = ops.StringOps.fromBase64("!Q==")
      assert(result.isRight)
    }

    // Base64: invalid character at position 2 in 1-pad group
    * - {
      val result = ops.StringOps.fromBase64("QU!=")
      assert(result.isRight)
    }

    // Base64: invalid character at position 2/3 in no-pad group
    * - {
      val result = ops.StringOps.fromBase64("QU!!")
      assert(result.isRight)
    }

    // Base64: all Base64 alphabet chars (+ and /)
    * - {
      // Encode bytes that produce + and / in output
      val data = ISZ[U8](u8"0xFB", u8"0xEF", u8"0xBE")
      val encoded = ops.StringOps.toBase64(data)
      val decoded = ops.StringOps.fromBase64(encoded)
      assert(decoded.isLeft)
      assert(decoded.left == data)
    }

    // Base64: roundtrip with all byte values 0x00..0xFF
    * - {
      var data = ISZ[U8]()
      var b: Z = 0
      while (b < 256) {
        data = data :+ conversions.Z.toU8(b)
        b = b + 1
      }
      val encoded = ops.StringOps.toBase64(data)
      val decoded = ops.StringOps.fromBase64(encoded)
      assert(decoded.isLeft)
      assert(decoded.left == data)
    }
  }
}
