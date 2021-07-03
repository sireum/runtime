/*
 Copyright (c) 2017-2021, Robby, Kansas State University
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

import java.io.{
  PrintWriter, File => JFile, BufferedInputStream => BIS, BufferedOutputStream => BOS, FileOutputStream => FOS,
  FileInputStream => FIS, FileReader => FR, InputStreamReader => ISR, OutputStreamWriter => OSW
}
import java.nio.{ByteBuffer => BB}
import java.nio.charset.{StandardCharsets => SC}
import java.nio.file.{
  AtomicMoveNotSupportedException, FileAlreadyExistsException, Files => JFiles, LinkOption => LO, Path => JPath,
  Paths => JPaths, StandardCopyOption => SCO, StandardOpenOption => SOO,
}
import java.util.concurrent.{TimeUnit => TU}
import java.util.zip.{ZipEntry => ZE, ZipInputStream => ZIS, ZipOutputStream => ZOS}

import com.zaxxer.nuprocess._
import org.sireum.$internal.CollectionCompat
import org.sireum.$internal.CollectionCompat.Converters._

object Os_Ext {

  {
    java.util.logging.LogManager.getLogManager.reset()
    System.setProperty("jna.nosys", "true")
    System.setProperty("java.net.useSystemProxies", "true")
  }

  lazy val buffSize: scala.Int = {
    var bufferProp = System.getenv("SIREUM_OS_IOBUFFER")
    if (bufferProp != null) bufferProp.toInt
    else {
      bufferProp = System.getProperty("org.sireum.os.iobuffer")
      if (bufferProp != null) bufferProp.toInt else 1024 * 1024
    }
  }

  val fileSep: String = java.io.File.separator

  val lineSep: String = System.lineSeparator

  val pathSep: String = java.io.File.pathSeparator

  val pathSepChar: C = java.io.File.pathSeparatorChar

  lazy val cwd: String = canon(System.getProperty("user.dir"))

  lazy val home: String = canon(System.getProperty("user.home"))

  var isNative: B = Os_ExtJava.isNative

  lazy val roots: ISZ[String] = ISZ((for (f <- java.io.File.listRoots) yield String(f.getCanonicalPath)).toIndexedSeq: _*)

  lazy val downloadCommand: ISZ[String] =
    if (proc"curl --version".run().ok) ISZ("curl", "-c", "/dev/null", "-JLso")
    else if (proc"wget --version".run().ok) ISZ("wget", "-qO")
    else ISZ()

  lazy val hasWget: B = proc"wget --version".run().ok

  lazy val os: Os.Kind.Type =
    if (scala.util.Properties.isMac) Os.Kind.Mac
    else if (scala.util.Properties.isLinux)
      if (proc"uname -m".run().out.value.trim == "aarch64") Os.Kind.LinuxArm
      else Os.Kind.Linux
    else if (scala.util.Properties.isWin) Os.Kind.Win
    else Os.Kind.Unsupported

  def abs(path: String): String = toIO(path).getAbsolutePath

  def canon(path: String): String = toIO(path).getCanonicalPath

  def cliArgs: ISZ[String] = App.args

  def chmod(path: String, mask: String, all: B): Unit = {
    if (os == Os.Kind.Win) return
    val p = path.value.replace(' ', '␣')
    if (all) proc"""sh -c chmod␣-fR␣$mask␣"$p"""".run()
    else proc"""sh -c chmod␣$mask␣"$p"""".run()
  }

  def copy(path: String, target: String, over: B): Unit = {
    val p = path.value.replace(' ', '␣')
    val t = target.value.replace(' ', '␣')
    if (over) {
      removeAll(target)
      mkdir(parent(target), T)
    }
    if (isDir(path)) {
      if (Os.isWin) {
        val r = proc"robocopy.exe /MIR $p $t".run()
        if (r.exitCode > 7) halt(s"Failed to copy $path to $target")
      }
      else proc"""sh -c cp␣-a␣"$p"␣"$t"""".runCheck()
    } else JFiles.copy(toNIO(path), toNIO(target), SCO.COPY_ATTRIBUTES)
  }

  def download(path: String, url: String): B = {
    def nativ(): Unit = {
      if (Os.isWin) {
        val p = path.value.replace(' ', '␣')
        proc"""powershell.exe -Command Invoke-WebRequest -Uri "$url" -OutFile "$p"""".runCheck()
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
    try {
      if (isNative) {
        nativ()
      } else {
        try {
          jvm()
        } catch {
          case _: UnsatisfiedLinkError => nativ()
        }
      }
      T
    } catch {
      case _: Throwable => F
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

  def length(path: String): Z = toIO(path).length

  @pure def lines(path: String, count: Z): ISZ[String] =
    if (count <= 0) ISZ(JFiles.lines(toNIO(path), SC.UTF_8).toArray.toIndexedSeq.map(s => String(s.toString)): _*)
    else ISZ(JFiles.lines(toNIO(path), SC.UTF_8).limit(count.toLong).toArray.toIndexedSeq.map(s => String(s.toString)): _*)

  def list(path: String): ISZ[String] = ISZ(scala.Option(toIO(path).list).getOrElse(Array()).toIndexedSeq.map(String(_)): _*)

  def mergeFrom(path: String, sources: ISZ[String]): Unit = {
    copy(sources(0), path, T)
    val outOs = new FOS(toIO(path), writeAppend(path, Os.Path.WriteMode.Append))
    try {
      val buffer = new Array[Byte](1000000)
      for (i <- 1 until sources.length) {
        val p = sources(i)
        val is = new FIS(toIO(p))
        try {
          var n = is.read(buffer)
          while (n > 0) {
            outOs.write(buffer, 0, n)
            n = is.read(buffer)
          }
        } finally is.close()
      }
    } finally {
      outOs.flush()
      outOs.close()
    }
  }

  def md5(path: String): String = digest(path, "MD5")

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
    val p = toNIO(path)
    if (JFiles.isRegularFile(p)) {
      halt(s"Cannot create a directory on existing an existing file $path")
    }
    if (JFiles.exists(p)) return
    if (all) JFiles.createDirectories(p)
    else JFiles.createDirectory(p)
  }

  def mklink(path: String, target: String): Unit = {
    removeAll(path)
    val f = toIO(path)
    val fParent = f.getParent
    if (Os.isWin) {
      val mklinkOption: String = if (isDir(target)) "/J " else ""
      val rel = relativize(fParent, target)
      val bat = Os.path(fParent) / ".symlink.bat"
      bat.writeOver(s"mklink $mklinkOption${f.getName} $rel")
      proc"cmd /C ${bat.name}".at(bat.up).runCheck()
      bat.removeOnExit()
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
        val is = new BIS(JFiles.newInputStream(toNIO(p)), buffSize)
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
        val fr = new ISR(new BIS(JFiles.newInputStream(toNIO(p)), buffSize), SC.UTF_8)
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
        val is = new BIS(JFiles.newInputStream(toNIO(p)), buffSize)
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
        val fr = new ISR(new BIS(JFiles.newInputStream(toNIO(p)), buffSize), SC.UTF_8)
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
    val p = path.value.replace(' ', '␣')
    if (isDir(path)) {
      os match {
        case Os.Kind.Win => proc"""cmd /c RD /S /Q $p""".run()
        case _ => proc"""sh -c rm␣-fR␣"$p"""".run()
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

  def sha1(path: String): String = digest(path, "SHA1")

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
    def normPath(p: String): Predef.String = if (Os.isWin) p.value.replace('\\', '/') else p.value
    val f = toNIO(target)
    val zip = new ZOS(
      if (f.toFile.exists) JFiles.newOutputStream(f, SOO.WRITE, SOO.APPEND)
      else JFiles.newOutputStream(f, SOO.WRITE, SOO.CREATE_NEW))
    try {
      for (file <- Os.Path.walk(Os.path(path), F, T, _ => T)) {
        zip.putNextEntry(new ZE(normPath(relativize(path, file.string))))
        JFiles.copy(toNIO(file.string), zip)
        zip.flush()
        zip.closeEntry()
      }
    } finally {
      zip.flush()
      zip.finish()
      zip.close()
    }
  }

  def unzip(path: String, target: String): Unit = {
    val zis = new ZIS(new BIS(JFiles.newInputStream(toNIO(path)), buffSize))
    try {
      val t = toNIO(target)
      for (file <- CollectionCompat.LazyList.continually(zis.getNextEntry).takeWhile(_ != null)) {
        if (!file.isDirectory) {
          val p = t.resolve(file.getName)
          p.getParent.toFile.mkdirs()
          JFiles.copy(zis, p, SCO.REPLACE_EXISTING)
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
    val bos = new BOS(os, buffSize)
    try {
      bos.write(content.value.getBytes(SC.UTF_8))
      bos.flush()
      os.getFD.sync()
    } finally bos.close()
  }

  def writeU8s(path: String, content: ISZ[U8], offset: Z, len: Z, mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    val bos = new BOS(os, buffSize)
    try {
      if (content.nonEmpty) bos.write(content.data.asInstanceOf[Array[Byte]], offset.toInt, len.toInt)
      bos.flush()
      os.getFD.sync()
    } finally bos.close()
  }

  def writeU8ms(path: String, content: MSZ[U8], offset: Z, len: Z, mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    val bos = new BOS(os, buffSize)
    try {
      if (content.nonEmpty) bos.write(content.data.asInstanceOf[Array[Byte]], offset.toInt, len.toInt)
      bos.flush()
      os.getFD.sync()
    } finally bos.close()
  }

  def writeLineStream(path: String, lines: Jen[String], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    val bos = new BOS(os, buffSize)
    try {
      for (l <- lines) bos.write(l.value.getBytes(SC.UTF_8))
      bos.flush()
      os.getFD.sync()
    } finally bos.close()
  }

  def writeU8Stream(path: String, u8s: Jen[U8], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    val bos = new BOS(os, buffSize)
    try {
      for (b <- u8s) bos.write(b.value)
      bos.flush()
      os.getFD.sync()
    }
    finally bos.close()
  }

  def writeCStream(path: String, cs: Jen[C], mode: Os.Path.WriteMode.Type): Unit = {
    val fos = new FOS(toIO(path), writeAppend(path, mode))
    val os = new OSW(new BOS(fos, buffSize), SC.UTF_8)
    try {
      for (c <- cs) os.write(c.value)
      os.flush()
      fos.flush()
      fos.getFD.sync()
    } finally os.close()
  }

  def writeLineMStream(path: String, lines: MJen[String], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    val bos = new BOS(os, buffSize)
    try {
      for (l <- lines) bos.write(l.value.getBytes(SC.UTF_8))
      bos.flush()
      os.getFD.sync()
    } finally bos.close()
  }

  def writeU8MStream(path: String, u8s: MJen[U8], mode: Os.Path.WriteMode.Type): Unit = {
    val os = new FOS(toIO(path), writeAppend(path, mode))
    val bos = new BOS(os, buffSize)
    try {
      for (b <- u8s) bos.write(b.value)
      bos.flush()
      os.getFD.sync()
    } finally bos.close()
  }

  def writeCMStream(path: String, cs: MJen[C], mode: Os.Path.WriteMode.Type): Unit = {
    val fos = new FOS(toIO(path), writeAppend(path, mode))
    val os = new OSW(new BOS(fos, buffSize), SC.UTF_8)
    try {
      for (c <- cs) os.write(c.value)
      os.flush()
      fos.flush()
      fos.getFD.sync()
    } finally os.close()
  }

  def parent(path: String): String = {
    val p = toIO(path).getParent
    if (p == null) return toIO(abs(path)).getParent
    return p
  }

  final class ProcOutput(p: Os.Proc) {
    val out = new java.io.ByteArrayOutputStream()
    val err = new java.io.ByteArrayOutputStream()
    val outLine = new java.io.ByteArrayOutputStream()
    val errLine = new java.io.ByteArrayOutputStream()
    val newLine: Byte  = '\n'.toByte

    def fEnd(): (String, String) = {
      if (outLine.size != 0) {
        val bs = outLine.toByteArray
        out.write(bs)
        if (p.shouldOutputConsole) {
          System.out.write(bs)
          System.out.flush()
        }
        outLine.reset()
      }
      if (errLine.size != 0) {
        val bs = errLine.toByteArray
        err.write(bs)
        if (p.shouldOutputConsole) {
          if (p.isErrAsOut) {
            System.out.write(bs)
            System.out.flush()
          } else if (!p.isErrBuffered) {
            System.err.write(bs)
            System.err.flush()
          }
        }
        errLine.reset()
      }
      if (p.isErrAsOut) {
        err.writeTo(out)
        return (out.toString(SC.UTF_8.name), "")
      } else {
        return (out.toString(SC.UTF_8.name), err.toString(SC.UTF_8.name))
      }
    }

    def fOut(bytes: Array[Byte], n: Int): Unit = f(F, bytes, n)

    def fErr(bytes: Array[Byte], n: Int): Unit = f(T, bytes, n)

    def f(isErr: B, bytes: Array[Byte], n: Int): Unit = {
      val (baos, baosLine, pw, laOpt) =
        if (isErr) (err, errLine, System.err, p.errLineActionOpt)
        else (out, outLine, System.out, p.outLineActionOpt)
      val shouldOutputConsole = if (isErr) p.shouldOutputConsole && !p.isErrBuffered else p.shouldOutputConsole
      laOpt match {
        case Some(la) =>
          baosLine.write(bytes, 0, n)
          val i = bytes.indexOf(newLine)
          if (0 <= i && i < n) {
            val s = new Predef.String(baosLine.toString(SC.UTF_8.name))
            val ss = s.split('\n')
            for (i <- 0 until ss.length - 1) {
              val line = ss(i)
              if (la(line).asInstanceOf[B]) {
                if (shouldOutputConsole) {
                  pw.println(line)
                  pw.flush()
                }
                baos.write(line.getBytes(SC.UTF_8))
                baos.write('\n')
              }
            }
            val line = ss(ss.length - 1)
            baosLine.reset()
            baos.write(line.getBytes(SC.UTF_8))
            if (bytes(n - 1) == newLine) {
              if (la(line).asInstanceOf[B]) {
                if (shouldOutputConsole) {
                  pw.println(line)
                  pw.flush()
                }
                baos.write('\n')
              }
            }
          }
        case _ =>
          baos.write(bytes, 0, n)
          if (shouldOutputConsole) {
            pw.write(bytes, 0, n)
            pw.flush()
          }
      }
    }
  }

  def proc(pr: Os.Proc): Os.Proc.Result = {
    val p: Os.Proc =
      if (pr.isScript)
        if (os == Os.Kind.Win) pr(cmds = ISZ[String]("cmd", "/c") ++ pr.cmds)
        else pr(cmds = "sh" +: pr.cmds)
      else pr
    def standardLib(): Os.Proc.Result = {
      val m = scala.collection.mutable.Map[Predef.String, Predef.String]()
      if (p.shouldAddEnv) {
        for ((key, value) <- System.getenv().asScala) {
          m(key) = value
        }
      }
      for ((k, v) <- p.envMap.entries.elements) {
        m(k.value) = v.value
      }
      if (p.shouldPrintEnv) {
        for ((k, v) <- m) {
          println(s"$k = $v")
        }
      }
      if (p.shouldPrintCommands) {
        println(p.cmds.elements.mkString(" "))
      }
      val po = new ProcOutput(p)
      val pOut = _root_.os.ProcessOutput(po.fOut)
      def pErr: _root_.os.ProcessOutput =
        if (p.isErrAsOut) pOut else _root_.os.ProcessOutput(po.fErr)
      val stdin: _root_.os.ProcessInput = p.in match {
        case Some(s) => s.value
        case _ => _root_.os.Inherit
      }
      val sp = _root_.os.proc(p.cmds.elements.map(_.value: _root_.os.Shellable)).
        spawn(cwd = _root_.os.Path(toIO(p.wd.value).getCanonicalPath),
          env = m.toMap, stdin = stdin, stdout = pOut, stderr = pErr,
          mergeErrIntoOut = p.isErrAsOut, propagateEnv = false)
      var term: Boolean = false
      term = sp.join(if (p.timeoutInMillis > 0) p.timeoutInMillis.toLong else -1)
      if (term) {
        val (pout, perr) = po.fEnd()
        return Os.Proc.Result.Normal(sp.exitCode(), pout, perr)
      }
      if (sp.isAlive()) {
        try {
          sp.destroy()
          sp.wrapped.waitFor(500, TU.MICROSECONDS)
        } catch {
          case _: Throwable =>
        }
        if (sp.isAlive())
          try sp.destroyForcibly()
          catch {
            case _: Throwable =>
          }
      }
      val (pout, perr) = po.fEnd()
      Os.Proc.Result.Timeout(pout, perr)
    }
    def nuProcess(): Os.Proc.Result = {
      val commands = new java.util.ArrayList(p.cmds.elements.map(_.value).asJavaCollection)
      val m = scala.collection.mutable.Map[Predef.String, Predef.String]()
      if (p.shouldAddEnv) {
        for ((key, value) <- System.getenv().asScala) {
          m(key) = value
        }
      }
      for ((k, v) <- p.envMap.entries.elements) {
        val key = k.toString
        val value = v.toString
        m(key) = value
      }
      if (p.shouldPrintEnv) {
        for ((k, v) <- m) {
          println(s"$k = $v")
        }
      }
      if (p.shouldPrintCommands) {
        println(p.cmds.elements.mkString(" "))
      }
      val npb = new NuProcessBuilder(commands, m.asJava)
      npb.setCwd(toNIO(p.wd.value))
      val po = new ProcOutput(p)
      npb.setProcessListener(new NuAbstractProcessHandler {
        def append(isOut: B, buffer: BB): Unit = {
          val size = buffer.remaining
          val bytes = new Array[Byte](size)
          for (i <- 0 until size) bytes(i) = (buffer.get)
          if (isOut) po.f(F, bytes, bytes.length) else po.f(T, bytes, bytes.length)
        }

        override def onStderr(buffer: BB, closed: Boolean): Unit = {
          if (!closed) append(F, buffer)
        }

        override def onStdout(buffer: BB, closed: Boolean): Unit = {
          if (!closed) append(T, buffer)
        }
      })
      val np = npb.start()
      if (np != null && np.isRunning) {
        p.in match {
          case Some(in) =>
            np.writeStdin(BB.wrap(in.value.getBytes(SC.UTF_8)))
            np.closeStdin(false)
          case _ =>
        }
        val exitCode = np.waitFor(p.timeoutInMillis.toLong, TU.MILLISECONDS)
        if (exitCode != scala.Int.MinValue) {
          val (pout, perr) = po.fEnd()
          return Os.Proc.Result.Normal(exitCode, pout, perr)
        }
        if (np.isRunning) try {
          np.destroy(false)
          np.waitFor(500, TU.MICROSECONDS)
        } catch {
          case _: Throwable =>
        }
        if (np.isRunning)
          try np.destroy(true)
          catch {
            case _: Throwable =>
          }
        val (pout, perr) = po.fEnd()
        Os.Proc.Result.Timeout(pout, perr)
      } else Os.Proc.Result.Exception(s"Could not execute command: ${p.cmds.elements.mkString(" ")}")
    }
    try {
      if (isNative || p.shouldUseStandardLib) {
        standardLib()
      } else {
        try {
          nuProcess()
        } catch {
          case _: UnsatisfiedLinkError | _: NumberFormatException | _: ExceptionInInitializerError =>
            isNative = T
            standardLib()
        }
      }
    } catch {
      case t: Throwable =>
        val sw = new java.io.StringWriter
        t.printStackTrace(new PrintWriter(sw))
        Os.Proc.Result.Exception(sw.toString)
    }
  }

  def pathString(o: Any): Predef.String = {
    val r = o match {
      case o: OsProto.Path => o.string.value.replace(' ', '␣')
      case (o: OsProto.Path, sep) => st"${(o.string.value.replace(' ', '␣'), sep.toString)}".render.value
      case _ => o.toString
    }
    r
  }

  private def digest(path: String, name: String): String = {
    val md = java.security.MessageDigest.getInstance(name.value)
    val digest = md.digest(readU8s(path).elements.map(_.value).toArray)
    st"${(for (d <- digest) yield U8(d)).toSeq}".render.value.toLowerCase
  }

  private def toIO(path: String): JFile = new JFile(path.value)

  private def toNIO(path: String): JPath = JPaths.get(path.value)
}
