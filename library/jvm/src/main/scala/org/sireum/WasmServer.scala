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

import java.io.{InputStream, OutputStream}
import java.util.concurrent.locks.ReentrantLock

/**
 * Shared companion object for WasmServer instances.
 *
 * Holds the shared GraalVM Polyglot Engine used for JIT code caching across
 * all WasmServer instances, and the JVMCI availability flag.
 */
object WasmServer {

  /** Type alias for the GraalVM Context.Builder to avoid Scala 2 inner-class path issues. */
  type ContextBuilder = org.graalvm.polyglot.Context#Builder

  /** True if GraalVM Polyglot (WASM) is available and the JVMCI compiler is active. */
  val hasJvmci: Boolean = {
    try {
      Class.forName("org.graalvm.polyglot.Context")
      val args = java.lang.management.ManagementFactory.getRuntimeMXBean.getInputArguments
      var jvmciEnabled = false
      val it = args.iterator()
      while (it.hasNext) {
        val arg = it.next()
        if (arg.contains("EnableJVMCI") || arg.contains("upgrade-module-path")) {
          jvmciEnabled = true
        }
      }
      jvmciEnabled
    } catch {
      case _: ClassNotFoundException => false
    }
  }

  /**
   * Shared GraalVM Polyglot Engine for JIT code caching across all WasmServer instances.
   * Lazily initialised so that processes that do not use WASM do not pay the
   * engine start-up cost.
   */
  private[sireum] lazy val engine: org.graalvm.polyglot.Engine = org.graalvm.polyglot.Engine.create()

  /**
   * Ring buffer with ReentrantLock + Condition, replacing Java's PipedInputStream
   * which has a 1-second polling delay on blocking reads.
   *
   * The pipe is reset()-able so that a WasmServer can be restarted without
   * allocating new buffer memory.
   */
  private[sireum] final class FastPipe(capacity: Int = 1024 * 1024) {
    private val buf = new Array[Byte](capacity)
    private var readPos = 0
    private var writePos = 0
    private var count = 0
    private var closed = false
    private val lock = new ReentrantLock()
    private val notEmpty = lock.newCondition()
    private val notFull = lock.newCondition()

    val inputStream: InputStream = new InputStream {
      override def read(): Int = {
        lock.lock()
        try {
          while (count == 0 && !closed) notEmpty.await()
          if (count == 0) return -1
          val b = buf(readPos) & 0xFF
          readPos = (readPos + 1) % capacity
          count -= 1
          notFull.signal()
          b
        } finally {
          lock.unlock()
        }
      }

      override def read(b: Array[Byte], off: Int, len: Int): Int = {
        if (len == 0) return 0
        lock.lock()
        try {
          while (count == 0 && !closed) notEmpty.await()
          if (count == 0) return -1
          val n = math.min(len, count)
          var i = 0
          while (i < n) {
            b(off + i) = buf(readPos)
            readPos = (readPos + 1) % capacity
            i += 1
          }
          count -= n
          notFull.signal()
          n
        } finally {
          lock.unlock()
        }
      }
    }

    val outputStream: OutputStream = new OutputStream {
      override def write(b: Int): Unit = {
        lock.lock()
        try {
          while (count == capacity && !closed) notFull.await()
          if (closed) throw new java.io.IOException("Pipe closed")
          buf(writePos) = b.toByte
          writePos = (writePos + 1) % capacity
          count += 1
          notEmpty.signal()
        } finally {
          lock.unlock()
        }
      }

      override def write(b: Array[Byte], off: Int, len: Int): Unit = {
        if (len == 0) return
        lock.lock()
        try {
          var written = 0
          while (written < len) {
            while (count == capacity && !closed) notFull.await()
            if (closed) throw new java.io.IOException("Pipe closed")
            val space = capacity - count
            val n = math.min(len - written, space)
            var i = 0
            while (i < n) {
              buf(writePos) = b(off + written + i)
              writePos = (writePos + 1) % capacity
              i += 1
            }
            count += n
            written += n
            notEmpty.signal()
          }
        } finally {
          lock.unlock()
        }
      }

      override def flush(): Unit = {}
    }

    /** Signal EOF to readers and unblock writers. */
    def close(): Unit = {
      lock.lock()
      try {
        closed = true
        notEmpty.signalAll()
        notFull.signalAll()
      } finally {
        lock.unlock()
      }
    }

    /** Reset the pipe to a fresh, open state for reuse after a restart. */
    def reset(): Unit = {
      lock.lock()
      try {
        readPos = 0
        writePos = 0
        count = 0
        closed = false
      } finally {
        lock.unlock()
      }
    }
  }
}

/**
 * Reusable base class for a persistent GraalWasm server process.
 *
 * Subclasses supply the WASM binary, a module name, command-line arguments,
 * and an optional context-configuration callback, then call [[start]] to launch
 * the server thread.  The server communicates with the WASM module via
 * [[stdinStream]] / [[stdoutStream]]; protocol framing (e.g. writeU32/readU32)
 * is provided as helpers.
 *
 * @param wasmBytes        Raw WASM binary to load.
 * @param moduleName       Name passed to the GraalVM Source builder (used in error messages).
 * @param args             Command-line arguments forwarded to the WASM ``_start`` entry point.
 * @param configureContext Callback that lets subclasses add WASI options (e.g. WasiMapDirs,
 *                         WasiConstantRandomGet) to the Context.Builder before it is built.
 *                         Uses [[WasmServer.ContextBuilder]] to avoid Scala 2 inner-class path issues.
 */
class WasmServer(
  wasmBytes: Array[Byte],
  moduleName: Predef.String,
  args: Array[Predef.String],
  configureContext: WasmServer.ContextBuilder => WasmServer.ContextBuilder
) {
  import WasmServer._

  /** Cached GraalVM Source object built from [[wasmBytes]] and [[moduleName]]. */
  private val source: org.graalvm.polyglot.Source =
    org.graalvm.polyglot.Source.newBuilder(
      "wasm",
      org.graalvm.polyglot.io.ByteSequence.create(wasmBytes),
      moduleName
    ).build()

  private val stdinPipe = new FastPipe()
  private val stdoutPipe = new FastPipe()
  private val stderrPipe = new FastPipe()

  @volatile private var alive: Boolean = false
  @volatile private var error: scala.Option[Throwable] = scala.None
  private var thread: Thread = _

  /**
   * Create a new GraalVM Context backed by the shared Engine and launch the
   * WASM ``_start`` entry point in a daemon thread.
   *
   * The method blocks for up to 10 seconds waiting for the WASM module to
   * finish loading (i.e. for [[alive]] to become true, which happens after
   * successful module instantiation but before entering the server loop).
   */
  def start(): Unit = {
    stdinPipe.reset()
    stdoutPipe.reset()
    stderrPipe.reset()
    alive = false
    error = scala.None

    thread = new Thread(() => {
      var context: org.graalvm.polyglot.Context = null
      try {
        val baseBuilder: ContextBuilder = org.graalvm.polyglot.Context.newBuilder("wasm")
          .engine(engine)
          .option("wasm.Builtins", "wasi_snapshot_preview1")
          .option("engine.WarnInterpreterOnly", "false")
          .arguments("wasm", args)
          .in(stdinPipe.inputStream)
          .out(stdoutPipe.outputStream)
          .err(stderrPipe.outputStream)
          .allowAllAccess(true)

        context = configureContext(baseBuilder).build()

        val module = context.eval(source)
        val instance = module.newInstance()
        val exports = instance.getMember("exports")
        val startFn = exports.getMember("_start")

        alive = true

        try {
          startFn.executeVoid()
        } catch {
          case e: org.graalvm.polyglot.PolyglotException if e.isExit && e.getExitStatus == 0 =>
            // Normal WASI shutdown — not an error.
          case e: org.graalvm.polyglot.PolyglotException if e.isExit =>
            error = scala.Some(new RuntimeException(s"$moduleName exited with code ${e.getExitStatus}", e))
          case e: Throwable =>
            error = scala.Some(new RuntimeException(s"$moduleName WASM error: ${e.getMessage}", e))
        }
      } catch {
        case e: Throwable =>
          error = scala.Some(new RuntimeException(s"$moduleName Context error: ${e.getMessage}", e))
      } finally {
        alive = false
        try { stdoutPipe.close() } catch { case _: Throwable => }
        try { stderrPipe.close() } catch { case _: Throwable => }
        if (context != null) {
          try { context.close() } catch { case _: Throwable => }
        }
      }
    })
    thread.setDaemon(true)
    thread.setName(s"wasm-$moduleName-${thread.getId}")
    thread.start()

    // Wait up to 10 s for the WASM module to initialise (alive set before context.eval).
    val deadline = System.currentTimeMillis() + 10000L
    while (!alive && error.isEmpty && System.currentTimeMillis() < deadline) {
      Thread.sleep(50)
    }
  }

  /**
   * Signal the WASM server to shut down by closing the stdin pipe (EOF on WASM
   * stdin causes ``_start`` to return), then wait up to 5 seconds for the
   * thread to terminate.
   *
   * Subclasses that use a protocol-level shutdown handshake should write their
   * shutdown message to [[stdinStream]] before calling this method.
   */
  def shutdown(): Unit = {
    if (thread != null) {
      stdinPipe.close()
      stdoutPipe.close()
      stderrPipe.close()
      try { thread.join(5000) } catch { case _: Throwable => }
      if (thread.isAlive) thread.interrupt()
    }
  }

  /** Shutdown and immediately restart the server with fresh pipes. */
  def restart(): Unit = {
    shutdown()
    start()
  }

  /** True when the server thread is running and no error has been recorded. */
  def isAlive: Boolean = alive && error.isEmpty

  /**
   * The last error recorded by the server thread, or [[scala.None]] if the server
   * is healthy.
   */
  def lastError: scala.Option[Throwable] = error

  // ---------------------------------------------------------------------------
  // Protocol helpers — big-endian u32 framing (matching cvc5 WASM server wire format)
  // ---------------------------------------------------------------------------

  /**
   * Write a big-endian unsigned 32-bit integer to [[out]].
   *
   * The cvc5 WASM server protocol uses big-endian framing (most-significant
   * byte first).  Other subclasses should adopt the same convention for
   * consistency.
   */
  def writeU32(out: OutputStream, value: Int): Unit = {
    out.write((value >> 24) & 0xFF)
    out.write((value >> 16) & 0xFF)
    out.write((value >> 8) & 0xFF)
    out.write(value & 0xFF)
    out.flush()
  }

  /**
   * Read a big-endian unsigned 32-bit integer from [[in]].
   *
   * Returns -1 if the stream ends before 4 bytes can be read.
   */
  def readU32(in: InputStream): Int = {
    val buf = new Array[Byte](4)
    var n = 0
    while (n < 4) {
      val r = in.read(buf, n, 4 - n)
      if (r < 0) return -1
      n += r
    }
    ((buf(0) & 0xFF) << 24) | ((buf(1) & 0xFF) << 16) |
      ((buf(2) & 0xFF) << 8) | (buf(3) & 0xFF)
  }

  // ---------------------------------------------------------------------------
  // Exposed streams for subclass protocol implementation
  // ---------------------------------------------------------------------------

  /** Output stream that writes to the WASM module's stdin. */
  def stdinStream: OutputStream = stdinPipe.outputStream

  /** Input stream that reads from the WASM module's stdout. */
  def stdoutStream: InputStream = stdoutPipe.inputStream

  /** Input stream that reads from the WASM module's stderr. */
  def stderrStream: InputStream = stderrPipe.inputStream
}
