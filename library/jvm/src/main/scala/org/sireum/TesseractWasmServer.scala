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

import java.nio.charset.StandardCharsets

/**
 * A persistent Tesseract OCR WASM server that communicates via a binary
 * protocol over stdin/stdout.
 *
 * The English traineddata is embedded in the WASM binary, so no filesystem
 * access or init handshake is needed.
 *
 * Protocol (binary, big-endian u32 lengths):
 *
 * Request:
 *   u32  image_len             (0 = shutdown)
 *   byte image[image_len]      (PNG or BMP)
 *
 * Response:
 *   u32  result_len
 *   byte result[result_len]    (UTF-8, one line per word)
 *
 * Each result line:   x1 y1 x2 y2 confidence text
 *
 * @param wasmBytes Raw Tesseract WASM binary (with embedded eng.traineddata).
 */
class TesseractWasmServer(wasmBytes: Array[Byte]) extends WasmServer(
  wasmBytes,
  "tesseract_server",
  Array("tesseract_server"),
  _.option("wasm.Threads", "true")
) {

  /** A single OCR word result with bounding box, confidence, and text. */
  case class Word(x1: Int, y1: Int, x2: Int, y2: Int, confidence: Int, text: Predef.String)

  /**
   * Send an image to the Tesseract server and return OCR results.
   *
   * @param imageBytes PNG or BMP image bytes.
   * @return Sequence of detected words with bounding boxes.
   */
  def ocr(imageBytes: Array[Byte]): Seq[Word] = synchronized {
    val os = stdinStream
    val is = stdoutStream

    writeU32(os, imageBytes.length)
    os.write(imageBytes)
    os.flush()

    val resultLen = readU32(is)
    if (resultLen <= 0) return Seq.empty

    val resultBytes = readExact(is, resultLen)
    val resultStr = new Predef.String(resultBytes, StandardCharsets.UTF_8)

    if (resultStr.startsWith("ERROR")) {
      System.err.println(s"Tesseract OCR error: $resultStr")
      return Seq.empty
    }

    val lines = resultStr.trim.split('\n')
    lines.flatMap { line =>
      val parts = line.trim.split(' ')
      if (parts.length >= 6) {
        scala.util.Try {
          Word(
            x1 = parts(0).toInt,
            y1 = parts(1).toInt,
            x2 = parts(2).toInt,
            y2 = parts(3).toInt,
            confidence = parts(4).toInt,
            text = parts.drop(5).mkString(" ")
          )
        }.toOption
      } else scala.None
    }.toSeq
  }

  /**
   * Send the shutdown signal (image_len=0) and close the server.
   */
  override def shutdown(): Unit = {
    try {
      writeU32(stdinStream, 0)
      stdinStream.flush()
    } catch {
      case _: Throwable =>
    }
    super.shutdown()
  }

  private def readExact(in: java.io.InputStream, n: Int): Array[Byte] = {
    if (n <= 0) return Array.emptyByteArray
    val buf = new Array[Byte](n)
    var read = 0
    while (read < n) {
      val r = in.read(buf, read, n - read)
      if (r < 0) return buf.take(read)
      read += r
    }
    buf
  }
}
