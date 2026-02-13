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

class LZ4Test extends TestSuite {

  val tests = Tests {

    // LZ4: empty input
    * - {
      val data = ISZ[U8]()
      val compressed = ops.ISZOps.lz4Compress(data)
      assert(compressed == ISZ[U8](u8"0", u8"0", u8"0", u8"0"))
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: 1 byte (smaller than min match)
    * - {
      val data = ISZ[U8](u8"0x42")
      val compressed = ops.ISZOps.lz4Compress(data)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: 3 bytes (smaller than min match)
    * - {
      val data = ISZ[U8](u8"0x01", u8"0x02", u8"0x03")
      val compressed = ops.ISZOps.lz4Compress(data)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: 4 bytes (exactly min match size, but no match possible)
    * - {
      val data = ISZ[U8](u8"0x01", u8"0x02", u8"0x03", u8"0x04")
      val compressed = ops.ISZOps.lz4Compress(data)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: repeated pattern (highly compressible)
    * - {
      var data = ISZ[U8]()
      var i: Z = 0
      while (i < 1000) {
        data = data :+ u8"0xAB"
        i = i + 1
      }
      val compressed = ops.ISZOps.lz4Compress(data)
      assert(compressed.size < data.size)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: repeating 4-byte pattern (should find matches)
    * - {
      var data = ISZ[U8]()
      var i: Z = 0
      while (i < 200) {
        data = data :+ u8"0xDE"
        data = data :+ u8"0xAD"
        data = data :+ u8"0xBE"
        data = data :+ u8"0xEF"
        i = i + 1
      }
      val compressed = ops.ISZOps.lz4Compress(data)
      assert(compressed.size < data.size)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: sequential bytes 0x00..0xFF (low compressibility)
    * - {
      var data = ISZ[U8]()
      var b: Z = 0
      while (b < 256) {
        data = data :+ conversions.Z.toU8(b)
        b = b + 1
      }
      val compressed = ops.ISZOps.lz4Compress(data)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: larger data with repeated blocks
    * - {
      var data = ISZ[U8]()
      var i: Z = 0
      while (i < 50) {
        var b: Z = 0
        while (b < 20) {
          data = data :+ conversions.Z.toU8(b)
          b = b + 1
        }
        i = i + 1
      }
      val compressed = ops.ISZOps.lz4Compress(data)
      assert(compressed.size < data.size)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: decompression of too-short input returns error
    * - {
      val result = ops.ISZOps.lz4Decompress(ISZ[U8](u8"0x01", u8"0x02"))
      assert(result.isRight)
    }

    // LZ4: decompression of corrupted data returns error
    * - {
      val data = ISZ[U8](u8"0x0A", u8"0x00", u8"0x00", u8"0x00", u8"0xFF")
      val result = ops.ISZOps.lz4Decompress(data)
      assert(result.isRight)
    }

    // LZ4: long literal run (> 15 bytes with no matches)
    * - {
      var data = ISZ[U8]()
      var i: Z = 0
      while (i < 300) {
        // Use position-dependent values to avoid matches
        data = data :+ conversions.Z.toU8(((i * 7 + 13) % 256))
        i = i + 1
      }
      val compressed = ops.ISZOps.lz4Compress(data)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: data exactly 5 bytes (minimum for hash-based matching)
    * - {
      val data = ISZ[U8](u8"0x01", u8"0x02", u8"0x03", u8"0x04", u8"0x05")
      val compressed = ops.ISZOps.lz4Compress(data)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }

    // LZ4: long match (> 19 bytes = 4 + 15, exercises extra match length encoding)
    * - {
      var block = ISZ[U8]()
      var i: Z = 0
      while (i < 30) {
        block = block :+ conversions.Z.toU8(i)
        i = i + 1
      }
      // Repeat the block to create a long match
      val data = block ++ block ++ block
      val compressed = ops.ISZOps.lz4Compress(data)
      assert(compressed.size < data.size)
      val decompressed = ops.ISZOps.lz4Decompress(compressed)
      assert(decompressed.isLeft)
      assert(decompressed.left == data)
    }
  }
}
