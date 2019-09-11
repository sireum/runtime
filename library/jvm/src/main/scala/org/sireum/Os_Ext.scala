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

import java.io.{PrintWriter, File => JFile, FileInputStream => FIS, FileOutputStream => FOS, FileReader => FR, InputStreamReader => ISR, OutputStreamWriter => OSW}
import java.nio.{ByteBuffer => BB}
import java.nio.charset.{StandardCharsets => SC}
import java.nio.file.{AtomicMoveNotSupportedException, FileAlreadyExistsException, Files => JFiles, LinkOption => LO, Path => JPath, Paths => JPaths, StandardCopyOption => SCO}
import java.util.concurrent.{TimeUnit => TU}
import java.util.zip.{ZipEntry => ZE, ZipInputStream => ZIS, ZipOutputStream => ZOS}

import com.zaxxer.nuprocess._
import org.sireum.$internal.CollectionCompat
import org.sireum.$internal.CollectionCompat.Converters._

object Os_Ext {

  { // for nuprocess
    java.util.logging.LogManager.getLogManager.reset()
    System.setProperty("jna.nosys", "true")
  }


  val fileSep: String = java.io.File.separator

  val lineSep: String = System.lineSeparator

  val pathSep: String = java.io.File.pathSeparator

  lazy val cwd: String = canon(System.getProperty("user.dir"))

  lazy val home: String = canon(System.getProperty("user.home"))

  var isNative: B = Os_ExtJava.isNative

  lazy val roots: ISZ[String] = ISZ((for (f <- java.io.File.listRoots) yield String(f.getCanonicalPath)).toIndexedSeq: _*)

  lazy val downloadCommand: ISZ[String] =
    if (Os.proc(ISZ("curl", "--version")).run().ok) ISZ("curl", "-c", "/dev/null", "-JLso")
    else if (Os.proc(ISZ("wget", "--version")).run().ok) ISZ("wget", "-qO")
    else ISZ()

  lazy val hasWget: B = Os.proc(ISZ("wget", "--version")).run().ok

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
    if (all) Os.proc(ISZ("sh", "-c", s"chmod -fR $mask $path")).run()
    else Os.proc(ISZ("sh", "-c", s"chmod $mask $path")).runCheck()
  }

  def copy(path: String, target: String, over: B): Unit = {
    val p = toNIO(path)
    val t = toNIO(target)
    if (over) {
      removeAll(target)
      mkdir(parent(target), T)
    }
    if (isDir(path)) {
      if (Os.isWin) {
        val r = Os.proc(ISZ("robocopy.exe", "/MIR", path, target)).run()
        if (r.exitCode > 7) halt(s"Failed to copy $path to $target")
      }
      else Os.proc(ISZ("sh", "-c", s"cp -a $path $target")).runCheck()
    } else JFiles.copy(p, t, SCO.COPY_ATTRIBUTES)
  }

  def download(path: String, url: String): Unit = {
    def nativ(): Unit = {
      if (Os.isWin) {
        Os.proc(ISZ("powershell.exe", "-Command", s"""Invoke-WebRequest -Uri "$url" -OutFile "$path"""")).runCheck()
      } else {
        if (downloadCommand.nonEmpty) Os.proc(downloadCommand :+ path :+ url).runCheck()
        else halt("Either curl or wget is required")
      }
    }
    def jvm(): Unit = {
      val cookieManager = new java.net.CookieManager()
      val default = java.net.CookieHandler.getDefault
      java.net.CookieHandler.setDefault(cookieManager)

      def fetch(loc: Predef.String): java.net.HttpURLConnection = {
        val c = new java.net.URL(loc).openConnection().asInstanceOf[java.net.HttpURLConnection]
        c.setInstanceFollowRedirects(false)
        c.setUseCaches(false)
        val responseCode = c.getResponseCode
        if (301 <= responseCode && responseCode <= 303 || responseCode == 307 || responseCode == 308) {
          var newLoc = c.getHeaderField("Location")
          if (newLoc.startsWith("/")) {
            val locUrl = new java.net.URL(loc)
            newLoc = s"${locUrl.getProtocol}://${locUrl.getHost}$newLoc"
          }
          fetch(newLoc)
        } else c
      }

      val c = fetch(url.value)
      val in = c.getInputStream
      try JFiles.copy(in, toNIO(path)) finally {
        in.close()
        java.net.CookieHandler.setDefault(default)
      }
    }
    if (isNative) {
      nativ()
    } else {
      try {
        jvm()
      } catch {
        case _: UnsatisfiedLinkError => nativ()
      }
    }
  }

  def env(name: String): Option[String] = {
    val value = System.getenv(name.value)
    if (value != null) Some(value) else None()
  }

  def envs: Map[String, String] = {
    var r = Map.empty[String, String]
    for ((k, v) <- System.getenv().asScala) {
      r = r + k ~> v
    }
    r
  }

  def exists(path: String): B = JFiles.exists(toNIO(path), LO.NOFOLLOW_LINKS)

  def exit(code: Z): Unit = System.exit(code.toInt)

  def isAbs(path: String): B = toIO(path).isAbsolute

  def isDir(path: String): B = toIO(path).isDirectory

  def isFile(path: String): B = toIO(path).isFile

  def isSymLink(path: String): B = JFiles.isSymbolicLink(toNIO(path))

  def kind(path: String): Os.Path.Kind.Type = {
    val p = toNIO(path)
    if (JFiles.isSymbolicLink(p)) Os.Path.Kind.SymLink
    else if (JFiles.isDirectory(p)) Os.Path.Kind.Dir
    else if (JFiles.isRegularFile(p)) Os.Path.Kind.File
    else Os.Path.Kind.Other
  }

  def lastModified(path: String): Z = toIO(path).lastModified

  @pure def lines(path: String, count: Z): ISZ[String] =
    if (count <= 0) ISZ(JFiles.lines(toNIO(path), SC.UTF_8).toArray.toIndexedSeq.map(s => String(s.toString)): _*)
    else ISZ(JFiles.lines(toNIO(path), SC.UTF_8).limit(count.toLong).toArray.toIndexedSeq.map(s => String(s.toString)): _*)

  def list(path: String): ISZ[String] = ISZ(scala.Option(toIO(path).list).getOrElse(Array()).toIndexedSeq.map(String(_)): _*)

  def move(path: String, target: String, over: B): Unit = {
    val p = toNIO(path)
    val t = toNIO(target)
    try {
      if (over) {
        if (t.toFile.exists()) {
          removeAll(target)
          mkdir(parent(target), T)
        }
        JFiles.move(p, t, SCO.ATOMIC_MOVE)
      } else {
        JFiles.move(p, t, SCO.ATOMIC_MOVE)
      }
    } catch {
      case _: AtomicMoveNotSupportedException =>
        if (over) {
          if (t.toFile.exists()) {
            removeAll(target)
            mkdir(parent(target), T)
          }
          JFiles.move(p, t, SCO.COPY_ATTRIBUTES)
        } else {
          JFiles.move(p, t, SCO.COPY_ATTRIBUTES)
        }
    }
  }

  def mkdir(path: String, all: B): Unit = {
    if (all) JFiles.createDirectories(toNIO(path))
    else JFiles.createDirectory(toNIO(path))
  }

  def mklink(path: String, target: String): Unit = {
    removeAll(path)
    val f = toIO(path)
    val fParent = f.getParent
    if (Os.isWin) {
      Os.proc(ISZ("cmd", "/c", s"""cd /d $fParent && dir && mklink ${f.getName} "${toNIO(relativize(fParent, target))}"""")).runCheck()
    } else {
      JFiles.createSymbolicLink(toNIO(path), toNIO(relativize(fParent, target)))
    }
  }

  def name(path: String): String = toIO(path).getName

  @pure def norm(path: String): String = toIO(path).getPath

  def properties(path: String): Map[String, String] = {
    val p = new java.util.Properties()
    val reader = new FR(toIO(path))
    try p.load(reader)
    finally reader.close()
    var r = Map.empty[String, String]
    for (k <- p.stringPropertyNames.asScala) {
      r = r + k ~> p.getProperty(k)
    }
    r
  }

  def readSymLink(path: String): Option[String] = {
    try Some(JFiles.readSymbolicLink(toNIO(path)).toFile.getCanonicalPath)
    catch {
      case _: Throwable => None()
    }
  }

  def relativize(path: String, other: String): String =
    toIO(path).getCanonicalFile.toPath.relativize(toIO(other).getCanonicalFile.toPath).toString

  def read(path: String): String = new Predef.String(JFiles.readAllBytes(toNIO(path)), SC.UTF_8)

  def readLines(path: String): ISZ[String] =
    ISZ(JFiles.readAllLines(toNIO(path), SC.UTF_8).asScala.toIndexedSeq.map(String.apply): _*)

  def readU8s(path: String): ISZ[U8] = {
    val data = JFiles.readAllBytes(toNIO(path))
    new IS[Z, U8](Z, data, data.length, U8.Boxer)
  }

  def readU8ms(path: String): MSZ[U8] = {
    val data = JFiles.readAllBytes(toNIO(path))
    new MS[Z, U8](Z, data, data.length, U8.Boxer)
  }

  def readLineStream(p: String): Os.Path.Jen[String] =
    new Os.Path.Jen[String] {
      override def path: Os.Path = Os.Path.Impl(p)

      override def generate(f: String => Jen.Action): Jen.Action = {
        val stream = JFiles.lines(toNIO(p), SC.UTF_8)
        var last = Jen.Continue
        for (e <- stream.iterator.asScala) {
          last = f(e)
          if (!last) {
            return Jen.End
          }
        }
        return last
      }
    }

  def readU8Stream(p: String): Os.Path.Jen[U8] =
    new Os.Path.Jen[U8] {
      override def path: Os.Path = Os.Path.Impl(p)

      override def generate(f: U8 => Jen.Action): Jen.Action = {
        val is = new FIS(toIO(p))
        try {
          var last = Jen.Continue
          var b = is.read()
          while (b != -1) {
            last = f(U8(b))
            if (!last) {
              return Jen.End
            }
            b = is.read()
          }
          return last
        } finally is.close()
      }
    }

  def readCStream(p: String): Os.Path.Jen[C] =
    new Os.Path.Jen[C] {
      override def path: Os.Path = Os.Path.Impl(p)

      override def generate(f: C => Jen.Action): Jen.Action = {
        val fr = new ISR(new FIS(toIO(p)), SC.UTF_8)
        try {
          var last = Jen.Continue
          var c = fr.read()
          while (c != -1) {
            last = f(C(c))
            if (!last) {
              return Jen.End
            }
            c = fr.read()
          }
          return last
        } finally fr.close()
      }
    }

  def readLineMStream(p: String): Os.Path.MJen[String] = {
    class G extends Os.Path.MJen[String] {
      private var _owned: Boolean = false

      def $owned_=(owned: Boolean): G = {
        _owned = owned
        this
      }

      def $owned: Boolean = _owned

      def $clone: G = new G

      override def path: Os.Path = Os.Path.Impl(p)

      override def generate(f: String => Jen.Action): Jen.Action = {
        val stream = JFiles.lines(toNIO(p), SC.UTF_8)
        var last = Jen.Continue
        for (e <- stream.iterator.asScala) {
          last = f(e)
          if (!last) {
            return Jen.End
          }
        }
        return last
      }
    }
    new G
  }

  def readU8MStream(p: String): Os.Path.MJen[U8] = {
    class G extends Os.Path.MJen[U8] {
      private var _owned: Boolean = false

      def $owned_=(owned: Boolean): G = {
        _owned = owned
        this
      }

      def $owned: Boolean = _owned

      def $clone: G = new G

      override def path: Os.Path = Os.Path.Impl(p)

      override def generate(f: U8 => Jen.Action): Jen.Action = {
        val is = new FIS(toIO(p))
        try {
          var last = Jen.Continue
          var b = is.read()
          while (b != -1) {
            last = f(U8(b))
            if (!last) {
              return Jen.End
            }
            b = is.read()
          }
          return last
        } finally is.close()
      }
    }
    new G
  }

  def readCMStream(p: String): Os.Path.MJen[C] = {
    class G extends Os.Path.MJen[C] {
      private var _owned: Boolean = false

      def $owned_=(owned: Boolean): G = {
        _owned = owned
        this
      }

      def $owned: Boolean = _owned

      def $clone: G = new G

      override def path: Os.Path = Os.Path.Impl(p)

      override def generate(f: C => Jen.Action): Jen.Action = {
        val fr = new ISR(new FIS(toIO(p)), SC.UTF_8)
        try {
          var last = Jen.Continue
          var c = fr.read()
          while (c != -1) {
            last = f(C(c))
            if (!last) {
              return Jen.End
            }
            c = fr.read()
          }
          return last
        } finally fr.close()
      }
    }
    new G
  }

  def remove(path: String): Unit = {
    JFiles.delete(toNIO(path))
  }

  def removeAll(path: String): Unit = if (exists(path)) {
    if (isDir(path)) {
      os match {
        case Os.Kind.Win => Os.proc(ISZ("cmd", "/c", "RD", "/S", "/Q", path)).run()
        case _ => Os.proc(ISZ("sh", "-c", s"rm -fR $path")).run()
      }
    } else try {
      remove(path)
    } catch {
      case _: Throwable =>
    }
  }

  def removeOnExit(path: String): Unit = {
    toIO(path).deleteOnExit()
  }

  def slashDir: String = {
    try if (isNative) return parent(Class.forName("org.graalvm.nativeimage.ProcessProperties").
      getMethod("getExecutableName").invoke(null).toString)
    catch {
      case _: Throwable =>
    }
    val r = System.getenv("SLASH_DIR")
    if (r == null) {
      eprintln("Could not detect Slash directory")
      eprintln("Please run the script using the Sireum Slang runner or define SLASH_DIR env var to point to the script directory")
      System.exit(-1)
    }
    return r
  }

  def temp(prefix: String, suffix: String): String = {
    JFiles.createTempFile(prefix.value, suffix.value).toFile.getCanonicalPath
  }

  def tempDir(prefix: String): String = {
    JFiles.createTempDirectory(prefix.value).toFile.getCanonicalPath
  }

  def toUri(path: String): String = {
    toNIO(canon(path).value).toUri.toASCIIString
  }

  def fromUri(uri: String): String = {
    new java.io.File(new java.net.URI(uri.value)).getCanonicalPath
  }

  def zip(path: String, target: String): Unit = {
    val zip = new ZOS(JFiles.newOutputStream(toNIO(target)))
    try {
      for (file <- Os.Path.walk(Os.path(path), F, T, _ => T)) {
        zip.putNextEntry(new ZE(relativize(path, file.string).value))
        JFiles.copy(toNIO(file.string), zip)
        zip.closeEntry()
      }
    } finally zip.close()
  }

  def unzip(path: String, target: String): Unit = {
    val zis = new ZIS(new FIS(toIO(path)))
    try {
      val t = toNIO(target)
      for (file <- CollectionCompat.LazyList.continually(zis.getNextEntry).takeWhile(_ != null)) {
        if (!file.isDirectory) {
          val p = t.resolve(file.getName)
          p.getParent.toFile.mkdirs()
          JFiles.copy(zis, p)
        }
      }
    } finally zis.close()
  }

  def writeAppend(path: String, mode: Os.Path.WriteMode.Type): Boolean = {
    val f = toIO(path)
    if (f.exists && mode == Os.Path.WriteMode.Regular)
      throw new FileAlreadyExistsException(s"$path already exists")
    mkdir(f.getParentFile.getCanonicalPath, T)
    if (mode == Os.Path.WriteMode.Append) true
    else {
      removeAll(path)
      mkdir(parent(path), T)
      false
    }
  }

  def write(path: String, content: String, mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    try os.write(content.value.getBytes(SC.UTF_8))
    finally os.close()
  }

  def writeU8s(path: String, content: ISZ[U8], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    try if (content.nonEmpty) os.write(content.data.asInstanceOf[Array[Byte]], 0, content.size.toInt)
    finally os.close()
  }

  def writeU8ms(path: String, content: MSZ[U8], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    try if (content.nonEmpty) os.write(content.data.asInstanceOf[Array[Byte]], 0, content.size.toInt)
    finally os.close()
  }

  def writeLineStream(path: String, lines: Jen[String], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    try for (l <- lines) os.write(l.value.getBytes(SC.UTF_8))
    finally os.close()
  }

  def writeU8Stream(path: String, u8s: Jen[U8], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    try for (b <- u8s) os.write(b.value)
    finally os.close()
  }

  def writeCStream(path: String, cs: Jen[C], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new OSW(new FOS(toIO(path), writeAppend(path, mode)), SC.UTF_8)
    try for (c <- cs) os.write(c.value)
    finally os.close()
  }

  def writeLineMStream(path: String, lines: MJen[String], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    try for (l <- lines) os.write(l.value.getBytes(SC.UTF_8))
    finally os.close()
  }

  def writeU8MStream(path: String, u8s: MJen[U8], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    try for (b <- u8s) os.write(b.value)
    finally os.close()
  }

  def writeCMStream(path: String, cs: MJen[C], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new OSW(new FOS(toIO(path), writeAppend(path, mode)), SC.UTF_8)
    try for (c <- cs) os.write(c.value)
    finally os.close()
  }

  def parent(path: String): String = toIO(path).getParent

  def proc(e: Os.Proc): Os.Proc.Result = {
    def nativ(): Os.Proc.Result = {
      val m = scala.collection.mutable.Map[Predef.String, Predef.String]()
      if (e.addEnv) {
        for ((k, v) <- System.getenv().asScala) {
          val key = k.toString
          val value = v.toString
          m(key) = value
        }
      }
      for ((k, v) <- e.envMap.entries.elements) {
        val key = k.toString
        val value = v.toString
        m(key) = value
      }
      if (e.outputEnv) {
        for ((k, v) <- m) {
          println(s"$k = $v")
        }
      }
      if (e.outputCommands) {
        println(e.cmds.elements.mkString(" "))
      }
      val (stdout, stderr) =
        if (e.outputConsole) (_root_.os.Inherit: _root_.os.ProcessOutput, _root_.os.Inherit: _root_.os.ProcessOutput)
        else (_root_.os.Pipe: _root_.os.ProcessOutput, _root_.os.Pipe: _root_.os.ProcessOutput)
      val stdin: _root_.os.ProcessInput = e.in match {
        case Some(s) => s.value
        case _ => _root_.os.Pipe
      }
      val sp = _root_.os.proc(e.cmds.elements.map(_.value: _root_.os.Shellable)).
        spawn(cwd = _root_.os.Path(toIO(e.wd.value).getCanonicalPath),
          env = m.toMap, stdin = stdin, stdout = stdout, stderr = stderr,
          mergeErrIntoOut = e.errAsOut, propagateEnv = false)
      val term = sp.waitFor(if (e.timeoutInMillis > 0) e.timeoutInMillis.toLong else -1)
      if (term) return Os.Proc.Result.Normal(sp.exitCode, new Predef.String(sp.stdout.bytes(), SC.UTF_8),
        new Predef.String(sp.stderr.bytes(), SC.UTF_8))
      if (sp.isAlive()) {
        try {
          sp.destroy()
          sp.wrapped.waitFor(500, TU.MICROSECONDS)
        } catch {
          case _: Throwable =>
        }
        if (sp.isAlive)
          try sp.destroyForcibly()
          catch {
            case _: Throwable =>
          }
      }
      Os.Proc.Result.Timeout(new Predef.String(sp.stdout.bytes(), SC.UTF_8),
        new Predef.String(sp.stderr.bytes(), SC.UTF_8))
    }
    def jvm(): Os.Proc.Result = {
      val commands = new java.util.ArrayList(e.cmds.elements.map(_.value).asJavaCollection)
      val m = scala.collection.mutable.Map[Predef.String, Predef.String]()
      if (e.addEnv) {
        for ((k, v) <- System.getenv().asScala) {
          val key = k.toString
          val value = v.toString
          m(key) = value
        }
      }
      for ((k, v) <- e.envMap.entries.elements) {
        val key = k.toString
        val value = v.toString
        m(key) = value
      }
      val npb = new NuProcessBuilder(commands, m.asJava)
      npb.setCwd(toNIO(e.wd.value))
      val out = new java.lang.StringBuilder()
      val err = new java.lang.StringBuilder()
      npb.setProcessListener(new NuAbstractProcessHandler {
        def append(isOut: B, buffer: BB): Unit = {
          val bytes = new Array[Byte](buffer.remaining)
          buffer.get(bytes)
          val s = new Predef.String(bytes, SC.UTF_8)
          if (isOut || e.errAsOut)
            if (e.outputConsole) System.out.print(s) else out.append(s)
          else if (e.outputConsole) System.err.print(s) else err.append(s)
        }

        override def onStderr(buffer: BB, closed: Boolean): Unit = {
          if (!closed) append(F, buffer)
        }

        override def onStdout(buffer: BB, closed: Boolean): Unit = {
          if (!closed) append(T, buffer)
        }
      })
      val p = npb.start()
      if (p != null && p.isRunning) {
        e.in match {
          case Some(in) => p.writeStdin(BB.wrap(in.value.getBytes(SC.UTF_8)))
          case _ =>
        }
        p.closeStdin(false)
        val exitCode = p.waitFor(e.timeoutInMillis.toLong, TU.MILLISECONDS)
        if (exitCode != scala.Int.MinValue) return Os.Proc.Result.Normal(exitCode, out.toString, err.toString)
        if (p.isRunning) try {
          p.destroy(false)
          p.waitFor(500, TU.MICROSECONDS)
        } catch {
          case _: Throwable =>
        }
        if (p.isRunning)
          try p.destroy(true)
          catch {
            case _: Throwable =>
          }
        Os.Proc.Result.Timeout(out.toString, err.toString)
      } else Os.Proc.Result.Exception(s"Could not execute command: ${e.cmds.elements.mkString(" ")}")
    }
    try {
      if (isNative || e.standardLib) {
        nativ()
      } else {
        try {
          jvm()
        } catch {
          case _: UnsatisfiedLinkError =>
            isNative = T
            nativ()
        }
      }
    } catch {
      case t: Throwable =>
        val sw = new java.io.StringWriter
        t.printStackTrace(new PrintWriter(sw))
        Os.Proc.Result.Exception(sw.toString)
    }
  }


  private def toIO(path: String): JFile = new JFile(path.value)

  private def toNIO(path: String): JPath = JPaths.get(path.value)
}
