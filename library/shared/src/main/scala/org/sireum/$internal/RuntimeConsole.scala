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
package org.sireum.$internal

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

object RuntimeConsole {
  private sealed trait Sink {
    def outWrite(bytes: Array[Byte], offset: Int, len: Int): Unit
    def errWrite(bytes: Array[Byte], offset: Int, len: Int): Unit
    def outFlush(): Unit
    def errFlush(): Unit
  }

  private object SystemSink extends Sink {
    override def outWrite(bytes: Array[Byte], offset: Int, len: Int): Unit = {
      System.out.write(bytes, offset, len)
    }

    override def errWrite(bytes: Array[Byte], offset: Int, len: Int): Unit = {
      System.err.write(bytes, offset, len)
    }

    override def outFlush(): Unit = System.out.flush()

    override def errFlush(): Unit = System.err.flush()
  }

  private final class CaptureSink(val parent: Sink) extends Sink {
    private val out = new ByteArrayOutputStream()
    private val err = new ByteArrayOutputStream()

    override def outWrite(bytes: Array[Byte], offset: Int, len: Int): Unit = this.synchronized {
      out.write(bytes, offset, len)
    }

    override def errWrite(bytes: Array[Byte], offset: Int, len: Int): Unit = this.synchronized {
      err.write(bytes, offset, len)
    }

    override def outFlush(): Unit = ()

    override def errFlush(): Unit = ()

    def result(): Array[String] = this.synchronized {
      Array(out.toString(StandardCharsets.UTF_8.name()), err.toString(StandardCharsets.UTF_8.name()))
    }
  }

  private val lineSep = System.lineSeparator().getBytes(StandardCharsets.UTF_8)

  private val sinkThreadLocal = new InheritableThreadLocal[Sink] {
    override def initialValue(): Sink = SystemSink
  }

  private def sink: Sink = {
    val r = sinkThreadLocal.get()
    if (r != null) r else SystemSink
  }

  def beginCapture(): Unit = {
    sinkThreadLocal.set(new CaptureSink(sink))
  }

  def endCapture(): Array[String] = sink match {
    case s: CaptureSink =>
      sinkThreadLocal.set(s.parent)
      s.result()
    case _ =>
      throw new IllegalStateException("RuntimeConsole capture is not active")
  }

  def outWrite(bytes: Array[Byte], offset: Int, len: Int): Unit = sink.outWrite(bytes, offset, len)

  def errWrite(bytes: Array[Byte], offset: Int, len: Int): Unit = sink.errWrite(bytes, offset, len)

  def outWrite(bytes: Array[Byte]): Unit = outWrite(bytes, 0, bytes.length)

  def errWrite(bytes: Array[Byte]): Unit = errWrite(bytes, 0, bytes.length)

  def outPrint(s: String): Unit = outWrite(s.getBytes(StandardCharsets.UTF_8))

  def errPrint(s: String): Unit = errWrite(s.getBytes(StandardCharsets.UTF_8))

  def outPrintln(): Unit = outWrite(lineSep)

  def errPrintln(): Unit = errWrite(lineSep)

  def outPrintln(s: String): Unit = {
    outPrint(s)
    outPrintln()
  }

  def errPrintln(s: String): Unit = {
    errPrint(s)
    errPrintln()
  }

  def outFlush(): Unit = sink.outFlush()

  def errFlush(): Unit = sink.errFlush()

  def flush(): Unit = {
    outFlush()
    errFlush()
  }
}
