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
import java.nio.file.Path

/**
 * A persistent 7zz WASM server that communicates via a binary protocol over
 * stdin/stdout.
 *
 * The server is started by calling [[start]] and can be reused for multiple
 * invocations via [[run]].  Send a shutdown signal via [[shutdown]].
 *
 * Protocol (binary, big-endian u32 lengths — matching cvc5 server pattern):
 *
 * Request:
 *   u32  workDir_len
 *   byte workDir[workDir_len]   (UTF-8, absolute path for chdir)
 *   u32  argc
 *   for each arg:
 *     u32  arg_len
 *     byte arg[arg_len]         (UTF-8)
 *
 * Response:
 *   i32  exitCode               (7zz exit code, interpreted as signed int)
 *   u32  stdout_len
 *   byte stdout[stdout_len]     (UTF-8)
 *   u32  stderr_len
 *   byte stderr[stderr_len]     (UTF-8)
 *
 * Sending argc=0 is the shutdown signal; the server responds with
 * exitCode=0 + empty stdout/stderr before exiting.
 *
 * @param wasmBytes Raw 7zz WASM binary.
 */
class P7zzWasmServer(wasmBytes: Array[Byte]) extends WasmServer(
  wasmBytes,
  "7zz_server",
  Array("7zz_server"),
  _.option("wasm.WasiMapDirs", "/::/")  // preopen root for full filesystem access
) {

  /**
   * Send one invocation to the running 7zz WASM server and return its output.
   *
   * The method is synchronized to prevent concurrent protocol interleaving —
   * the server processes one request at a time in its loop.
   *
   * The args sequence should include argv[0] (typically "7zz") as the first
   * element, matching the C main() convention expected by 7zz.
   *
   * @param workDir Absolute path the server should chdir to before running.
   * @param args    Command-line arguments (including argv[0]).
   * @return        (exitCode, stdout, stderr) triple.
   */
  def run(workDir: Path, args: Seq[Predef.String]): (Int, Predef.String, Predef.String) = synchronized {
    val os = stdinStream
    val is = stdoutStream

    // Send working directory (length-prefixed UTF-8 string)
    val workDirBytes = workDir.toAbsolutePath.toString.getBytes(StandardCharsets.UTF_8)
    writeU32(os, workDirBytes.length)
    os.write(workDirBytes)
    os.flush()

    // Send argc
    writeU32(os, args.length)

    // Send each arg as a length-prefixed UTF-8 string
    for (arg <- args) {
      val argBytes = arg.getBytes(StandardCharsets.UTF_8)
      writeU32(os, argBytes.length)
      os.write(argBytes)
      os.flush()
    }

    // Read response: exitCode (i32) + stdout (length-prefixed) + stderr (length-prefixed)
    val exitCode = readU32(is)  // i32 reinterpreted as int via readU32

    val stdoutLen = readU32(is)
    val stdoutBytes = readExact(is, stdoutLen)

    val stderrLen = readU32(is)
    val stderrBytes = readExact(is, stderrLen)

    (exitCode,
      new Predef.String(stdoutBytes, StandardCharsets.UTF_8),
      new Predef.String(stderrBytes, StandardCharsets.UTF_8))
  }

  /**
   * Send the shutdown signal (argc=0) to the server, wait for the server's
   * acknowledgement response, then call the inherited [[WasmServer.shutdown]]
   * to close pipes and join the thread.
   */
  override def shutdown(): Unit = {
    try {
      val os = stdinStream
      // Send empty workDir
      writeU32(os, 0)
      // Send argc=0 — the shutdown signal
      writeU32(os, 0)
      os.flush()
      // Drain the acknowledgement response (exitCode=0 + empty stdout + empty stderr)
      val is = stdoutStream
      readU32(is)  // exitCode (0)
      readU32(is)  // stdout_len (0)
      readU32(is)  // stderr_len (0)
    } catch {
      case _: Throwable =>
        // Server may have already shut down; proceed to close pipes.
    }
    super.shutdown()
  }

  // ---------------------------------------------------------------------------
  // Private helpers
  // ---------------------------------------------------------------------------

  /**
   * Read exactly [[n]] bytes from [[in]], blocking until all bytes arrive.
   * Returns an empty array when n == 0.
   */
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
