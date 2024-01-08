// #Sireum
/*
 Copyright (c) 2017-2024, Robby, Kansas State University
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

object Os {

  @pure def cliArgs: ISZ[String] = {
    return Ext.cliArgs
  }

  def cwd: Path = {
    return path(prop("user.dir").get).canon
  }

  def exit(code: Z): Unit = {
    Ext.exit(code)
  }

  def env(name: String): Option[String] = {
    return Ext.env(name)
  }

  def envs: Map[String, String] = {
    return Ext.envs
  }

  @pure def fileSep: String = {
    return Ext.fileSep
  }

  def freeMemory: Z = {
    return Ext.freeMemory
  }

  def home: Path = {
    Os.env("HOME") match {
      case Some(d) => return path(d).canon
      case _ => return path(prop("user.home").get).canon
    }
  }

  @pure def isLinux: B = {
    return kind == Kind.Linux
  }

  @pure def isMac: B = {
    return kind == Kind.Mac
  }

  def isMacArm: B = {
    return isMac && prop("os.arch").get == "aarch64"
  }

  def isWinArm: B = {
    return isWin && env("PROCESSOR_ARCHITECTURE") == Some("ARM64")
  }

  @pure def isWin: B = {
    return kind == Kind.Win
  }

  def javaHomeOpt(kind: Os.Kind.Type, homeOpt: Option[Os.Path]): Option[Path] = {
    homeOpt match {
      case Some(sireumHome) => kind match {
        case Os.Kind.Win => return Some(sireumHome / "bin" / "win" / "java")
        case Os.Kind.Mac => return Some(sireumHome / "bin" / "mac" / "java")
        case Os.Kind.Linux => return Some(sireumHome / "bin" / "linux" / "java")
        case Os.Kind.LinuxArm => return Some(sireumHome / "bin" / "linux" / "arm" / "java")
        case Os.Kind.Unsupported => return None()
      }
      case _ => return None()
    }
  }

  def javaExe(homeOpt: Option[Os.Path]): Path = {
    javaHomeOpt(Os.kind, homeOpt) match {
      case Some(d) => return d / "bin" / (if (Os.isWin) "java.exe" else "java")
      case _ => return Os.path("java")
    }
  }

  @pure def kind: Kind.Type = {
    return Ext.osKind
  }

  @pure def lineSep: String = {
    return Ext.lineSep
  }

  @pure def maxMemory: Z = {
    return Ext.maxMemory
  }

  @pure def numOfProcessors: Z = {
    return Ext.numOfProcessors
  }

  @pure def pathSep: String = {
    return Ext.pathSep
  }

  @pure def pathSepChar: C = {
    return Ext.pathSepChar
  }

  @pure def path(value: String): Path = {
    if (isWin) {
      val sOps = ops.StringOps(value)
      val cygPrefix: String = "/cygdrive/"
      if (sOps.startsWith(cygPrefix)) {
        return Path.Impl(
          ops.StringOps(s"${sOps.substring(cygPrefix.size, cygPrefix.size + 1)}:${sOps.substring(cygPrefix.size + 1, value.size)}").replaceAllChars('/', '\\'))

      } else if (sOps.startsWith("/") && env("OSTYPE") == Some("msys")) {
        return Path.Impl(ops.StringOps(s"${sOps.substring(1, 2)}:${sOps.substring(2, value.size)}").
          replaceAllChars('/', '\\'))
      }
    }
    return Path.Impl(Ext.norm(value))
  }

  @pure def proc(commands: ISZ[String]): Proc = {
    return Proc(commands, cwd, Map.empty, T, None(), F, F, F, F, F, 0, F, F, None(), None())
  }

  @pure def procs(commands: String): Proc = {
    val cmds: ISZ[String] = for (cmd <- ops.StringOps(commands).split((c: C) => c == ' ')) yield
      ops.StringOps(cmd).replaceAllChars('␣', ' ')
    return proc(cmds)
  }

  def prop(name: String): Option[String] = {
    return Ext.prop(name)
  }

  def props: Map[String, String] = {
    return Ext.props
  }

  @pure def readIndexableCFrom(url: String): Indexable.Pos[C] = {
    return Ext.readIndexableCUrl(url)
  }

  @pure def roots: ISZ[Path] = {
    return for (root <- Ext.roots) yield Path.Impl(root)
  }

  def scalaHomeOpt(homeOpt: Option[Os.Path]): Option[Path] = {
    homeOpt match {
      case Some(d) => return Some(d / "bin" / "scala")
      case _ => return None()
    }
  }

  def scalaScript(homeOpt: Option[Os.Path]): Os.Path = {
    scalaHomeOpt(homeOpt) match {
      case Some(scalaHome) => return scalaHome / "bin" / (if (Os.isWin) "scala.bat" else "scala")
      case _ => return Os.path("scala")
    }
  }

  def scalacScript(homeOpt: Option[Os.Path]): Os.Path = {
    scalaHomeOpt(homeOpt) match {
      case Some(scalaHome) => return scalaHome / "bin" / (if (Os.isWin) "scalac.bat" else "scalac")
      case _ => return Os.path("scalac")
    }
  }

  def sireumHomeOpt: Option[Os.Path] = {
    Os.env("SIREUM_HOME") match {
      case Some(d) => return Some(Os.path(d).canon)
      case _ => Os.prop("org.sireum.home") match {
        case Some(d) => return Some(Os.path(d).canon)
        case _ => return Ext.detectSireumHome
      }
    }
  }

  def slashDir: Os.Path = {
    return Os.path(Ext.slashDir)
  }

  def temp(): Path = {
    val r = tempFix("", "")
    return r
  }

  def tempFix(prefix: String, suffix: String): Path = {
    val r = Ext.temp(prefix, suffix)
    return Path.Impl(r)
  }

  def tempDir(): Path = {
    val r = tempDirFix("")
    return r
  }

  def tempDirFix(prefix: String): Path = {
    val r = Ext.tempDir(prefix)
    return Path.Impl(r)
  }

  def totalMemory: Z = {
    return Ext.totalMemory
  }

  @pure def uriToPath(uri: String): Os.Path = {
    return Path.Impl(Ext.fromUri(uri))
  }


  @enum object Kind {
    'Mac
    'Linux
    'LinuxArm
    'Win
    'Unsupported
  }

  object Path {

    @enum object Kind {
      'Dir
      'File
      'SymLink
      'Other
    }

    @enum object WriteMode {
      'Regular
      'Over
      'Append
    }

    @datatype class Impl(val value: String) extends Path {
      override def string: String = {
        return value
      }
    }

    @sig trait Jen[T] extends org.sireum.Jen[T] {
      def path: Path
      override def string: String = {
        return s"Jen($path)"
      }
    }

    @msig trait MJen[T] extends org.sireum.MJen[T] {
      def path: Path
      override def string: String = {
        return s"MJen($path)"
      }
    }

    def overlay(isMove: B, path: Os.Path, target: Os.Path, includeDir: B, followLink: B,
                pred: Os.Path => B @pure, report: B): HashSMap[Os.Path, Os.Path] = {
      var r = HashSMap.empty[Os.Path, Os.Path]
      val files = ops.ISZOps(walk(path, includeDir, T, pred)).reverse
      for (p <- files) {
        val t = target / path.relativize(p).string
        p.kind match {
          case Kind.Dir =>
            t.mkdirAll()
            if (isMove) {
              p.remove()
            }
            if (report) {
              r = r + p ~> t
            }
          case Kind.File =>
            t.up.mkdirAll()
            if (isMove) {
              p.moveOverTo(t)
            } else {
              p.copyOverTo(t)
            }
            if (report) {
              r = r + p ~> t
            }
          case Kind.SymLink if isMove =>
            p.remove()
          case _ =>
        }
      }
      return r
    }

    def walk(path: Os.Path, includeDir: B, followLink: B, pred: Os.Path => B @pure): ISZ[Os.Path] = {
      var rSet = HashSSet.empty[Os.Path]
      var rIS = ISZ[Os.Path]()
      def add(p: Os.Path): Unit = {
        val pCanon = p.canon
        if (followLink) {
          rSet = rSet + pCanon
        } else {
          rIS = rIS :+ pCanon
        }
      }
      def rec(p: Os.Path): Unit = {
        if (!followLink && rSet.contains(p.canon)) {
          return
        }
        p.kind match {
          case Os.Path.Kind.Dir =>
            if (includeDir && pred(p)) {
              add(p)
            }
            for (p2 <- p.list) {
              rec(p2)
            }
          case Os.Path.Kind.File if pred(p) => add(p)
          case Os.Path.Kind.SymLink if followLink =>
            if (pred(p)) {
              add(p)
            }
            val tOpt = p.readSymLink
            tOpt match {
              case Some(t) => rec(t)
              case _ =>
            }
          case _ =>
        }
      }
      rec(path)
      return if (followLink) rSet.map.keys else rIS
    }

  }

  object Proc {

    @sig sealed trait Result extends OsProto.Proc.Result

    object Result {

      @datatype class Normal(val exitCode: Z, val out: String, val err: String) extends Result

      @datatype class Exception(val err: String) extends Result {
        def out: String = {
          return ""
        }
        def exitCode: Z = {
          return -100
        }
      }

      @datatype class Timeout(val out: String, val err: String) extends Result {
        def exitCode: Z = {
          return -101
        }
      }

    }
  }

  @datatype class Proc(val cmds: ISZ[String],
                       val wd: Path,
                       val envMap: Map[String, String],
                       val shouldAddEnv: B,
                       val in: Option[String],
                       val isErrAsOut: B,
                       val shouldOutputConsole: B,
                       val isErrBuffered: B,
                       val shouldPrintEnv: B,
                       val shouldPrintCommands: B,
                       val timeoutInMillis: Z,
                       val shouldUseStandardLib: B,
                       val isScript: B,
                       val outLineActionOpt: Option[String => B],
                       val errLineActionOpt: Option[String => B]) extends OsProto.Proc {

    @pure def commands(cs: ISZ[String]): Proc = {
      val thisL = this
      return thisL(cmds = cmds ++ cs)
    }

    @pure def at(dir: OsProto.Path): Proc = {
      val thisL = this
      return thisL(wd = Os.Path.Impl(dir.string))
    }

    @pure def env(m: ISZ[(String, String)]): Proc = {
      val thisL = this
      return thisL(envMap = this.envMap ++ m)
    }

    @pure def input(content: String): Proc = {
      val thisL = this
      return thisL(in = Some(content))
    }

    @pure def timeout(millis: Z): Proc = {
      val thisL = this
      return thisL(timeoutInMillis = millis)
    }

    @pure def dontInheritEnv: Proc = {
      val thisL = this
      return thisL(shouldAddEnv = F)
    }

    @pure def redirectErr: Proc = {
      val thisL = this
      return thisL(isErrAsOut = T)
    }

    @pure def bufferErr: Proc = {
      val thisL = this
      return thisL(isErrBuffered = T)
    }

    @pure def console: Proc = {
      val thisL = this
      return thisL(shouldOutputConsole = T)
    }

    @pure def echoEnv: Proc = {
      val thisL = this
      return thisL(shouldPrintEnv = T)
    }

    @pure def echo: Proc = {
      val thisL = this
      return thisL(shouldPrintCommands = T)
    }

    @pure def standard: Proc = {
      val thisL = this
      return thisL(shouldUseStandardLib = T)
    }

    @pure def script: Proc = {
      val thisL = this
      return thisL(isScript = T)
    }

    @pure def outLineAction(f: String => B): Proc = {
      val thisL = this
      return thisL(outLineActionOpt = Some(f))
    }

    @pure def errLineAction(f: String => B): Proc = {
      val thisL = this
      return thisL(errLineActionOpt = Some(f))
    }

    def run(): Proc.Result = {
      val r = Ext.proc(this)
      return r
    }

    def runCheck(): Proc.Result = {
      val r = run()
      if (!r.ok) {
        eprintln(
          st"""Error encountered when running: ${(cmds, " ")}, exit code: ${r.exitCode}
              |${r.err}""".render)
        Os.exit(-1)
      }
      return r
    }
  }

  @sig sealed trait Path extends OsProto.Path {

    @pure def value: String

    @pure def procString: String = {
      return ops.StringOps(value).replaceAllChars(' ', '␣')
    }

    @pure def /(name: String): Path = {
      return Path.Impl(s"$value$fileSep$name")
    }

    @pure def /+(names: ISZ[String]): Path = {
      return Path.Impl(st"$value$fileSep${(names, fileSep)}".render)
    }

    def call(args: ISZ[String]): Os.Proc = {
      return Os.proc(((if (Os.isWin) ISZ[String]("cmd", "/c") else ISZ[String]("sh")) :+ string) ++ args)
    }

    def canon: Path = {
      val p = Ext.canon(value)
      return if (p == value) this else Path.Impl(p)
    }

    def abs: Path = {
      val p = Ext.abs(value)
      return if (p == value) this else Path.Impl(p)
    }

    def chmod(mask: String): Unit = {
      Ext.chmod(value, mask, F)
    }

    def chmodAll(mask: String): Unit = {
      Ext.chmod(value, mask, T)
    }

    def combineFrom(sources: ISZ[Os.Path]): Unit = {
      val script = Os.tempFix("", ".bat")
      script.removeOnExit()
      removeAll()
      if (Os.isWin) {
        script.writeOver(st"""copy /b ${(sources, "+")} $this""".render)
        proc"cmd /c $script".runCheck()
      } else {
        script.writeOver(st"""cat ${(sources, " ")} > $this""".render)
        proc"sh $script".runCheck()
      }
    }

    def copyTo(target: Path): Unit = {
      Ext.copy(value, target.value, F)
    }

    def copyOverTo(target: Path): Unit = {
      Ext.copy(value, target.value, T)
    }

    def downloadFrom(url: String): B = {
      up.mkdirAll()
      return Ext.download(value, url)
    }

    def exists: B = {
      return Ext.exists(value)
    }

    @pure def ext: String = {
      val nameOps = ops.StringOps(name)
      val i = nameOps.lastIndexOf('.')
      return if (i >= 0) nameOps.substring(i + 1, name.size) else ""
    }

    def isAbs: B = {
      return Ext.isAbs(value)
    }

    def isDir: B = {
      return Ext.isDir(value)
    }

    def isFile: B = {
      return Ext.isFile(value)
    }

    def isSymLink: B = {
      return Ext.isSymLink(value)
    }

    def kind: Path.Kind.Type = {
      return Ext.kind(value)
    }

    def lastModified: Z = {
      return Ext.lastModified(value)
    }

    def length: Z = {
      return Ext.length(value)
    }

    def list: ISZ[Path] = {
      return for (filename <- Ext.list(value)) yield this / filename
    }

    def mergeFrom(sources: ISZ[Os.Path]): Unit = {
      Ext.mergeFrom(value, for (p <- sources) yield p.value)
    }

    def md5: String = {
      return Ext.md5(value)
    }

    def moveTo(target: Path): Unit = {
      Ext.move(value, target.value, F)
    }

    def moveOverTo(target: Path): Unit = {
      Ext.move(value, target.value, T)
    }

    def mkdir(): Unit = {
      Ext.mkdir(value, F)
    }

    def mkdirAll(): Unit = {
      Ext.mkdir(value, T)
    }

    def mklink(target: Path): Unit = {
      Ext.mklink(value, target.value)
    }

    @pure def name: String = {
      return Ext.name(value)
    }

    def overlayCopy(target: Os.Path, includeDir: B, followLink: B,
                    pred: Os.Path => B @pure, report: B): HashSMap[Os.Path, Os.Path] = {
      return Path.overlay(F, this, target, includeDir, followLink, pred, report)
    }

    def overlayMove(target: Os.Path, includeDir: B, followLink: B,
                    pred: Os.Path => B @pure, report: B): HashSMap[Os.Path, Os.Path] = {
      return Path.overlay(T, this, target, includeDir, followLink, pred, report)
    }

    def prependWith(other: Os.Path): Unit = {
      val temp = Os.temp()
      temp.removeOnExit()
      temp.combineFrom(ISZ(other, this))
      temp.moveTo(this)
    }

    def properties: Map[String, String] = {
      return Ext.properties(value)
    }

    def readSymLink: Option[Path] = {
      val r = Ext.readSymLink(value)
      return r.map(o => Path.Impl(o))
    }

    @pure def relativize(other: Path): Path = {
      return Path.Impl(Ext.relativize(value, other.value))
    }

    def read: String = {
      return Ext.read(value)
    }

    def readLines: ISZ[String] = {
      return Ext.readLineStream(value).toISZ
    }

    def readLineStream: Jen[String] = {
      return Ext.readLineStream(value)
    }

    def readLineMStream: MJen[String] = {
      return Ext.readLineMStream(value)
    }

    def readU8s: ISZ[U8] = {
      return Ext.readU8s(value)
    }

    def readU8ms: MSZ[U8] = {
      return Ext.readU8ms(value)
    }

    def readU8Stream: Jen[U8] = {
      return Ext.readU8Stream(value)
    }

    def readU8MStream: MJen[U8] = {
      return Ext.readU8MStream(value)
    }

    def readCStream: Jen[C] = {
      return Ext.readCStream(value)
    }

    def readIndexableC: Indexable.Pos[C] = {
      return Ext.readIndexableCPath(value)
    }

    def readCMStream: MJen[C] = {
      return Ext.readCMStream(value)
    }

    def remove(): Unit = {
      Ext.remove(value)
    }

    def removeAll(): Unit = {
      Ext.removeAll(value)
    }

    def removeOnExit(): Unit = {
      if (isDir) {
        val paths = Os.Path.walk(this, T, F, (_: Path) => T)
        for (i <- paths.size - 1 to 0 by -1) {
          Ext.removeOnExit(paths(i).value)
        }
      } else {
        Ext.removeOnExit(value)
      }
    }

    def sha1: String = {
      return Ext.sha1(value)
    }

    def size: Z = {
      return Ext.size(value)
    }

    def slash(args: ISZ[String]): Unit = {
      val nativ = this / ".com"
      if (nativ.exists && this.lastModified < nativ.lastModified) {
        Os.proc(string +: args).console.runCheck()
      } else {
        nativ.removeAll()
        proc(string +: args).script.console.runCheck()
      }
    }

    def toUri: String = {
      return Ext.toUri(value)
    }

    def write(content: String): Unit = {
      Ext.write(value, content, Path.WriteMode.Regular)
    }

    def writeOver(content: String): Unit = {
      Ext.write(value, content, Path.WriteMode.Over)
    }

    def writeAppend(content: String): Unit = {
      Ext.write(value, content, Path.WriteMode.Append)
    }

    def writeLineStream(content: Jen[String]): Unit = {
      Ext.writeLineStream(value, content, Path.WriteMode.Regular)
    }

    def writeOverLineStream(content: Jen[String]): Unit = {
      Ext.writeLineStream(value, content, Path.WriteMode.Over)
    }

    def writeAppendLineStream(content: Jen[String]): Unit = {
      Ext.writeLineStream(value, content, Path.WriteMode.Append)
    }

    def writeLineMStream(content: MJen[String]): Unit = {
      Ext.writeLineMStream(value, content, Path.WriteMode.Regular)
    }

    def writeOverLineMStream(content: MJen[String]): Unit = {
      Ext.writeLineMStream(value, content, Path.WriteMode.Over)
    }

    def writeAppendLineMStream(content: MJen[String]): Unit = {
      Ext.writeLineMStream(value, content, Path.WriteMode.Append)
    }

    def writeU8s(content: ISZ[U8]): Unit = {
      Ext.writeU8s(value, content, 0, content.size, Path.WriteMode.Regular)
    }

    def writeU8Parts(content: ISZ[U8], offset: Z, len: Z): Unit = {
      Ext.writeU8s(value, content, offset, len, Path.WriteMode.Regular)
    }

    def writeOverU8s(content: ISZ[U8]): Unit = {
      Ext.writeU8s(value, content, 0, content.size, Path.WriteMode.Over)
    }

    def writeOverU8Parts(content: ISZ[U8], offset: Z, len: Z): Unit = {
      Ext.writeU8s(value, content, offset, len, Path.WriteMode.Over)
    }

    def writeAppendU8s(content: ISZ[U8]): Unit = {
      Ext.writeU8s(value, content, 0, content.size, Path.WriteMode.Append)
    }

    def writeAppendU8Parts(content: ISZ[U8], offset: Z, len: Z): Unit = {
      Ext.writeU8s(value, content, offset, len, Path.WriteMode.Append)
    }

    def writeU8ms(content: MSZ[U8]): Unit = {
      Ext.writeU8ms(value, content, 0, content.size, Path.WriteMode.Regular)
    }

    def writeU8Partms(content: MSZ[U8], offset: Z, len: Z): Unit = {
      Ext.writeU8ms(value, content, offset, len, Path.WriteMode.Regular)
    }

    def writeOverU8ms(content: MSZ[U8]): Unit = {
      Ext.writeU8ms(value, content, 0, content.size, Path.WriteMode.Over)
    }

    def writeOverU8Partms(content: MSZ[U8], offset: Z, len: Z): Unit = {
      Ext.writeU8ms(value, content, offset, len, Path.WriteMode.Over)
    }

    def writeAppendU8ms(content: MSZ[U8]): Unit = {
      Ext.writeU8ms(value, content, 0, content.size, Path.WriteMode.Append)
    }

    def writeAppendU8Partms(content: MSZ[U8], offset: Z, len: Z): Unit = {
      Ext.writeU8ms(value, content, offset, len, Path.WriteMode.Append)
    }

    def writeU8Stream(content: Jen[U8]): Unit = {
      Ext.writeU8Stream(value, content, Path.WriteMode.Regular)
    }

    def writeOverU8Stream(content: Jen[U8]): Unit = {
      Ext.writeU8Stream(value, content, Path.WriteMode.Over)
    }

    def writeAppendU8Stream(content: Jen[U8]): Unit = {
      Ext.writeU8Stream(value, content, Path.WriteMode.Append)
    }

    def writeU8MStream(content: MJen[U8]): Unit = {
      Ext.writeU8MStream(value, content, Path.WriteMode.Regular)
    }

    def writeOverU8MStream(content: MJen[U8]): Unit = {
      Ext.writeU8MStream(value, content, Path.WriteMode.Over)
    }

    def writeAppendU8MStream(content: MJen[U8]): Unit = {
      Ext.writeU8MStream(value, content, Path.WriteMode.Append)
    }

    def writeCStream(content: Jen[C]): Unit = {
      Ext.writeCStream(value, content, Path.WriteMode.Regular)
    }

    def writeOverCStream(content: Jen[C]): Unit = {
      Ext.writeCStream(value, content, Path.WriteMode.Over)
    }

    def writeAppendCStream(content: Jen[C]): Unit = {
      Ext.writeCStream(value, content, Path.WriteMode.Append)
    }

    def writeCMStream(content: MJen[C]): Unit = {
      Ext.writeCMStream(value, content, Path.WriteMode.Regular)
    }

    def writeOverCMStream(content: MJen[C]): Unit = {
      Ext.writeCMStream(value, content, Path.WriteMode.Over)
    }

    def writeAppendCMStream(content: MJen[C]): Unit = {
      Ext.writeCMStream(value, content, Path.WriteMode.Append)
    }

    def zipTo(target: Os.Path): Unit = {
      Ext.zip(value, target.value)
    }

    def unzipTo(target: Os.Path): Unit = {
      Ext.unzip(value, target.value)
    }

    def unTarGzTo(target: Os.Path): Unit = {
      Ext.unTarGz(value, target.value)
    }

    @pure def up: Path = {
      return Path.Impl(Ext.parent(value))
    }
  }

  @ext("Os_Ext") object Ext {

    @pure def cliArgs: ISZ[String] = $

    @pure def fileSep: String = $

    @pure def lineSep: String = $

    @pure def pathSep: String = $

    @pure def pathSepChar: C = $

    @pure def osKind: Kind.Type = $

    @pure def roots: ISZ[String] = $

    @pure def abs(path: String): String = $

    @pure def canon(path: String): String = $

    def chmod(path: String, mask: String, all: B): Unit = $

    def copy(path: String, target: String, over: B): Unit = $

    def detectSireumHome: Option[Os.Path] = $

    def download(path: String, url: String): B = $

    def env(name: String): Option[String] = $

    def envs: Map[String, String] = $

    def exists(path: String): B = $

    def exit(code: Z): Unit = $

    def freeMemory: Z = $

    @pure def fromUri(uri: String): String = $

    @pure def isAbs(path: String): B = $

    def isDir(path: String): B = $

    def isFile(path: String): B = $

    def isSymLink(path: String): B = $

    def kind(path: String): Path.Kind.Type = $

    def lastModified(path: String): Z = $

    def length(path: String): Z = $

    def list(path: String): ISZ[String] = $

    @pure def maxMemory: Z = $

    @pure def numOfProcessors: Z = $

    def mergeFrom(path: String, sources: ISZ[String]): Unit = $

    def md5(path: String): String = $

    def move(path: String, target: String, over: B): Unit = $

    def mkdir(path: String, all: B): Unit = $

    def mklink(path: String, target: String): Unit = $

    @pure def name(path: String): String = $

    @pure def norm(path: String): String = $

    def prop(name: String): Option[String] = $

    def props: Map[String, String] = $

    def properties(path: String): Map[String, String] = $

    def readSymLink(path: String): Option[String] = $

    @pure def relativize(path: String, other: String): String = $

    def read(path: String): String = $

    def readU8s(path: String): ISZ[U8] = $

    def readU8ms(path: String): MSZ[U8] = $

    def readLineStream(path: String): Path.Jen[String] = $

    def readU8Stream(path: String): Path.Jen[U8] = $

    def readCStream(path: String): Path.Jen[C] = $

    def readIndexableCPath(path: String): Indexable.Pos[C] = $

    def readIndexableCUrl(url: String): Indexable.Pos[C] = $

    def readLineMStream(path: String): Path.MJen[String] = $

    def readCMStream(path: String): Path.MJen[C] = $

    def readU8MStream(path: String): Path.MJen[U8] = $

    def remove(path: String): Unit = $

    def removeAll(path: String): Unit = $

    def removeOnExit(path: String): Unit = $

    def sha1(path: String): String = $

    @pure def slashDir: String = $

    def size(path: String): Z = $

    def temp(prefix: String, suffix: String): String = $

    def tempDir(prefix: String): String = $

    def totalMemory: Z = $

    @pure def toUri(path: String): String = $

    def write(path: String, content: String, mode: Path.WriteMode.Type): Unit = $

    def writeU8s(path: String, u8s: ISZ[U8], offset: Z, len: Z, mode: Path.WriteMode.Type): Unit = $

    def writeU8ms(path: String, u8s: MSZ[U8], offset: Z, len: Z, mode: Path.WriteMode.Type): Unit = $

    def writeLineStream(path: String, lines: Jen[String], mode: Path.WriteMode.Type): Unit = $

    def writeU8Stream(path: String, u8s: Jen[U8], mode: Path.WriteMode.Type): Unit = $

    def writeCStream(path: String, cs: Jen[C], mode: Path.WriteMode.Type): Unit = $

    def writeLineMStream(path: String, lines: MJen[String], mode: Path.WriteMode.Type): Unit = $

    def writeU8MStream(path: String, u8s: MJen[U8], mode: Path.WriteMode.Type): Unit = $

    def writeCMStream(path: String, cs: MJen[C], mode: Path.WriteMode.Type): Unit = $

    def zip(path: String, target: String): Unit = $

    def unzip(path: String, target: String): Unit = $

    def unTarGz(path: String, target: String): Unit = $

    @pure def parent(path: String): String = $

    def proc(e: Proc): Proc.Result = $

  }

}

