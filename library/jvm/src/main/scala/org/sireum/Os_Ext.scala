/*
 Copyright (c) 2019, Robby, Kansas State University
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

import U8._
import java.io.{File => JFile, FileInputStream => FIS, FileOutputStream => FOS}
import java.nio.ByteBuffer
import java.nio.charset.{StandardCharsets => SC}
import java.nio.file.{AtomicMoveNotSupportedException, FileAlreadyExistsException, Files => JFiles, LinkOption => LO, Path => JPath, Paths => JPaths, StandardCopyOption => SCO}
import java.util.concurrent.TimeUnit
import com.zaxxer.nuprocess._

object Os_Ext {

  val fileSep: String = java.io.File.separator

  val lineSep: String = System.lineSeparator

  val pathSep: String = java.io.File.pathSeparator

  lazy val cwd: String = canon(System.getProperty("user.dir"))

  lazy val home: String = canon(System.getProperty("user.home"))

  lazy val isNative: B = java.lang.Boolean.getBoolean("com.oracle.graalvm.isaot")

  lazy val roots: ISZ[String] = ISZ((for (f <- java.io.File.listRoots) yield String(f.getCanonicalPath)): _*)

  val os: Os.Kind.Type =
    if (scala.util.Properties.isMac) Os.Kind.Mac
    else if (scala.util.Properties.isLinux) Os.Kind.Linux
    else if (scala.util.Properties.isWin) Os.Kind.Win
    else Os.Kind.Unsupported

  def abs(path: String): String = toIO(path).getAbsolutePath

  def canon(path: String): String = toIO(path).getCanonicalPath

  def cliArgs: ISZ[String] = App.args

  def chmod(path: String, mask: String, all: B): Unit = {
    if (os == Os.Kind.Win) return
    if (all) Os.proc(ISZ("chmod", "-fR", mask, path))
    else Os.proc(ISZ("chmod", mask, path))
  }

  def copy(path: String, target: String, over: B): Unit = {
    val p = toNIO(path)
    val t = toNIO(target)
    if (over) JFiles.copy(p, t, SCO.REPLACE_EXISTING, SCO.COPY_ATTRIBUTES)
    else JFiles.copy(p, t, SCO.COPY_ATTRIBUTES)
  }

  def env(name: String): Option[String] = {
    val value = System.getenv(name.value)
    if (value != null) Some(value) else None()
  }

  def envs: Map[String, String] = {
    import scala.collection.JavaConverters._
    var r = Map.empty[String, String]
    for ((k, v) <- System.getenv().asScala) {
      r = r + k ~> v
    }
    r
  }

  def exists(path: String): B = JFiles.exists(toNIO(path), LO.NOFOLLOW_LINKS)

  def exit(code: Z): Unit = System.exit(code.toInt)

  def isAbs(path: String): B = toIO(path).isAbsolute

  def kind(path: String): Os.Path.Kind.Type = {
    val p = toNIO(path)
    if (JFiles.isSymbolicLink(p)) Os.Path.Kind.SymLink
    else if (JFiles.isDirectory(p)) Os.Path.Kind.Dir
    else if (JFiles.isRegularFile(p)) Os.Path.Kind.File
    else Os.Path.Kind.Other
  }

  def lastModified(path: String): Z = toIO(path).lastModified

  @pure def lines(path: String, count: Z): ISZ[String] =
    if (count <= 0) ISZ(JFiles.lines(toNIO(path), SC.UTF_8).toArray.map(s => String(s.toString)): _*)
    else ISZ(JFiles.lines(toNIO(path), SC.UTF_8).limit(count.toLong).toArray.map(s => String(s.toString)): _*)

  def list(path: String): ISZ[String] = ISZ(toIO(path).list.map(String(_)): _*)

  def move(path: String, target: String, over: B): Unit = {
    val p = toNIO(path)
    val t = toNIO(target)
    try if (over) {
      JFiles.move(p, t, SCO.COPY_ATTRIBUTES, SCO.ATOMIC_MOVE)
    } else {
      JFiles.move(p, t, SCO.COPY_ATTRIBUTES, SCO.REPLACE_EXISTING, SCO.ATOMIC_MOVE)
    } catch {
      case _: AtomicMoveNotSupportedException =>
        if (over) {
          JFiles.move(p, t, SCO.COPY_ATTRIBUTES)
        } else {
          JFiles.move(p, t, SCO.COPY_ATTRIBUTES, SCO.REPLACE_EXISTING)
        }
    }
  }

  def mkdir(path: String, all: B): Unit = {
    if (all) JFiles.createDirectory(toNIO(path))
    else JFiles.createDirectories(toNIO(path))
  }

  def name(path: String): String = toIO(path).getName

  @pure def norm(path: String): String = toIO(path).getPath

  def relativize(path: String, other: String): String = toNIO(path).relativize(toNIO(other)).toString

  def read(path: String): String = new Predef.String(JFiles.readAllBytes(toNIO(path)), SC.UTF_8)

  def readU8s(path: String): ISZ[U8] = {
    val f = toIO(path)
    val length = f.length
    val r = ISZ.create(length, u8"0")
    val data = r.data.asInstanceOf[Array[Byte]]
    val is = new FIS(f)
    try is.read(data, 0, length.toInt) finally is.close()
    r
  }

  def readU8ms(path: String): MSZ[U8] = {
    val f = toIO(path)
    val length = f.length
    val r = MSZ.create(length, u8"0")
    val data = r.data.asInstanceOf[Array[Byte]]
    val is = new FIS(f)
    try is.read(data, 0, length.toInt) finally is.close()
    r
  }

  def remove(path: String): Unit = {
    JFiles.delete(toNIO(path))
  }

  def removeAll(path: String): Unit = if (exists(path)) {
    os match {
      case Os.Kind.Win => proc(Os.proc(ISZ("RD", "/S", "/Q", path)))
      case _ => proc(Os.proc(ISZ("rm", "-fR", path)))
    }
  }

  def removeOnExit(path: String): Unit = {
    toIO(path).deleteOnExit()
  }

  def temp(prefix: String, suffix: String): String = {
    JFiles.createTempFile(prefix.value, suffix.value).toFile.getCanonicalPath
  }

  def tempDir(prefix: String): String = {
    JFiles.createTempDirectory(prefix.value).toFile.getCanonicalPath
  }

  def write(path: String, content: String, over: B): Unit = {
    if (exists(path) && !over) {
      throw new FileAlreadyExistsException(s"$path already exists")
    }
    mkdir(path, T)
    val os = new FOS(toIO(path))
    try {
      os.write(content.value.getBytes(SC.UTF_8))
    } finally os.close()
  }

  def writeU8s(path: String, content: ISZ[U8], over: B): Unit = {
    if (exists(path) && !over) {
      throw new FileAlreadyExistsException(s"$path already exists")
    }
    mkdir(path, T)
    val os = new FOS(toIO(path))
    try {
      os.write(content.data.asInstanceOf[Array[Byte]], 0, content.size.toInt)
    } finally os.close()
  }

  def writeU8ms(path: String, content: MSZ[U8], over: B): Unit = {
    if (exists(path) && !over) {
      throw new FileAlreadyExistsException(s"$path already exists")
    }
    mkdir(path, T)
    val os = new FOS(toIO(path))
    try {
      os.write(content.data.asInstanceOf[Array[Byte]], 0, content.size.toInt)
    } finally os.close()
  }

  def parent(path: String): String = toIO(path).getParent

  def proc(e: Os.Proc): Os.Proc.Result = if (isNative) {
    import scala.collection.JavaConverters._
    val m = scala.collection.mutable.Map[Predef.String, Predef.String]()
    val env = if (e.addEnv) System.getenv().asScala ++ e.envMap.entries.elements else e.envMap.entries.elements
    for ((k, v) <- env) {
      m(k.toString) = v.toString
    }
    val (stdout, stderr) =
      if (e.outputConsole) (_root_.os.Inherit: _root_.os.ProcessOutput, _root_.os.Inherit: _root_.os.ProcessOutput)
      else (_root_.os.Pipe: _root_.os.ProcessOutput, _root_.os.Pipe: _root_.os.ProcessOutput)
    val stdin: _root_.os.ProcessInput = e.input match {
      case Some(s) => s.value
      case _ => _root_.os.Pipe
    }
    val sp = _root_.os.proc(e.commands.elements.map(_.value: _root_.os.Shellable)).
      spawn(cwd = _root_.os.Path(e.wd.value.value), env = m.toMap, stdin = stdin, stdout = stdout, stderr = stderr,
        mergeErrIntoOut = e.errAsOut, propagateEnv = e.addEnv)
    val term = sp.waitFor(e.timeoutInMillis.toLong)
    if (term) return Os.Proc.Result.Normal(sp.exitCode, new Predef.String(sp.stdout.bytes(), SC.UTF_8),
      new Predef.String(sp.stderr.bytes(), SC.UTF_8))
    if (sp.isAlive) try {
      sp.destroy()
      sp.wrapped.waitFor(500, TimeUnit.MICROSECONDS)
    } catch {
      case _: Throwable =>
    }
    if (sp.isAlive)
      try sp.destroyForcibly()
      catch {
        case _: Throwable =>
      }
    Os.Proc.Result.Timeout()
  } else {
    import scala.collection.JavaConverters._
    val commands = new java.util.ArrayList(e.commands.elements.map(_.value).asJavaCollection)
    val m = scala.collection.mutable.Map[Predef.String, Predef.String]()
    val env = if (e.addEnv) System.getenv().asScala ++ e.envMap.entries.elements else e.envMap.entries.elements
    for ((k, v) <- env) {
      m(k.toString) = v.toString
    }
    val npb = new NuProcessBuilder(commands, m.asJava)
    val out = new java.lang.StringBuilder()
    val err = new java.lang.StringBuilder()
    npb.setProcessListener(new NuAbstractProcessHandler {
      def append(isOut: B, buffer: ByteBuffer): Unit = {
        val bytes = new Array[Byte](buffer.remaining)
        buffer.get(bytes)
        val s = new Predef.String(bytes, SC.UTF_8)
        if (isOut || e.errAsOut)
          if (e.outputConsole) System.out.print(s) else out.append(s)
        else if (e.outputConsole) System.err.print(s) else err.append(s)
      }

      override def onStderr(buffer: ByteBuffer, closed: Boolean): Unit = {
        if (!closed) append(F, buffer)
      }

      override def onStdout(buffer: ByteBuffer, closed: Boolean): Unit = {
        if (!closed) append(T, buffer)
      }
    })
    val p = npb.start()
    if (p != null && p.isRunning) {
      e.input match {
        case Some(in) => p.writeStdin(ByteBuffer.wrap(in.value.getBytes(SC.UTF_8)))
        case _ =>
      }
      p.closeStdin(false)
      val exitCode = p.waitFor(e.timeoutInMillis.toLong, TimeUnit.MILLISECONDS)
      if (exitCode != scala.Int.MinValue) return Os.Proc.Result.Normal(exitCode, out.toString, err.toString)
      if (p.isRunning) try {
        p.destroy(false)
        p.waitFor(500, TimeUnit.MICROSECONDS)
      } catch {
        case _: Throwable =>
      }
      if (p.isRunning)
        try p.destroy(true)
        catch {
          case _: Throwable =>
        }
      Os.Proc.Result.Timeout()
    } else Os.Proc.Result.Exception(s"Could not execute command: ${e.commands.elements.mkString(" ")}")
  }


  private def toIO(path: String): JFile = new JFile(path.value)

  private def toNIO(path: String): JPath = JPaths.get(path.value)
}
