// #Sireum
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

object Os {

  def cliArgs: ISZ[String] = {
    return Ext.cliArgs
  }

  @memoize def cwd: Path = {
    return Path.Impl(Ext.cwd)
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

  def fileSep: String = {
    return Ext.fileSep
  }

  @memoize def home: Path = {
    return Path.Impl(Ext.home)
  }

  def pathSep: String = {
    return Ext.pathSep
  }

  def kind: Kind.Type = {
    return Ext.os
  }

  def path(value: String): Path = {
    if (kind == Kind.Win) {
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
    return Proc(commands, cwd, Map.empty, T, None(), F, F, 0)
  }

  @memoize def roots: ISZ[Path] = {
    return for (root <- Ext.roots) yield Path.Impl(root)
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


  @enum object Kind {
    'Mac
    'Linux
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

  }

  object Proc {

    @sig sealed trait Result

    object Result {

      @datatype class Normal(exitCode: Z, out: String, err: String) extends Result

      @datatype class Exception(err: String) extends Result

      @datatype class Timeout extends Result

    }
  }

  @datatype class Proc(cmds: ISZ[String],
                       wd: Path,
                       envMap: Map[String, String],
                       addEnv: B,
                       in: Option[String],
                       errAsOut: B,
                       outputConsole: B,
                       timeoutInMillis: Z) {

    @pure def commands(cs: ISZ[String]): Proc = {
      return this(cmds = cmds ++ cs)
    }

    @pure def at(dir: Path): Proc = {
      return this(wd = dir)
    }

    @pure def env(m: ISZ[(String, String)]): Proc = {
      return this(envMap = this.envMap ++ m)
    }

    @pure def input(content: String): Proc = {
      return this(in = Some(content))
    }

    @pure def timeout(millis: Z): Proc = {
      return this(timeoutInMillis = millis)
    }

    @pure def dontInheritEnv: Proc = {
      return this(addEnv = F)
    }

    @pure def redirectErr: Proc = {
      return this(errAsOut = T)
    }

    @pure def console: Proc = {
      return this(outputConsole = T)
    }

    def run(): Proc.Result = {
      val r = Ext.proc(this)
      return r
    }

    def runCheck(): Proc.Result = {
      val r = run()
      r match {
        case r: Proc.Result.Normal =>
          if (r.exitCode == 0) {
            return r
          } else {
            eprintln(r.err)
            exit(-1)
            halt("")
          }
        case r: Proc.Result.Exception => halt(r.err)
        case _: Proc.Result.Timeout => halt("Timeout")
      }
    }
  }

  @sig sealed trait Path {

    @pure def value: String

    @pure def /(name: String): Path = {
      return Path.Impl(s"$value$fileSep$name")
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

    def copyTo(target: Path): Unit = {
      Ext.copy(value, target.value, F)
    }

    def copyOverTo(target: Path): Unit = {
      Ext.copy(value, target.value, T)
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

    def kind: Path.Kind.Type = {
      return Ext.kind(value)
    }

    def lastModified: Z = {
      return Ext.lastModified(value)
    }

    def list: ISZ[Path] = {
      return for (filename <- Ext.list(value)) yield this / filename
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

    @pure def name: String = {
      return Ext.name(value)
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

    def readLineStream: Path.Jen[String] = {
      return Ext.readLineStream(value)
    }

    def readLineMStream: Path.MJen[String] = {
      return Ext.readLineMStream(value)
    }

    def readU8s: ISZ[U8] = {
      return Ext.readU8s(value)
    }

    def readU8Stream: Path.Jen[U8] = {
      return Ext.readU8Stream(value)
    }

    def readU8MStream: Path.MJen[U8] = {
      return Ext.readU8MStream(value)
    }

    def readCStream: Path.Jen[C] = {
      return Ext.readCStream(value)
    }

    def readCMStream: Path.MJen[C] = {
      return Ext.readCMStream(value)
    }

    def remove(): Unit = {
      Ext.remove(value)
    }

    def removeAll(): Unit = {
      Ext.removeAll(value)
    }

    def removeOnExit(): Unit = {
      Ext.removeOnExit(value)
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
      Ext.writeU8s(value, content, Path.WriteMode.Regular)
    }

    def writeOverU8s(content: ISZ[U8]): Unit = {
      Ext.writeU8s(value, content, Path.WriteMode.Over)
    }

    def writeAppendU8s(content: ISZ[U8]): Unit = {
      Ext.writeU8s(value, content, Path.WriteMode.Append)
    }

    def writeU8ms(content: MSZ[U8]): Unit = {
      Ext.writeU8ms(value, content, Path.WriteMode.Regular)
    }

    def writeOverU8ms(content: MSZ[U8]): Unit = {
      Ext.writeU8ms(value, content, Path.WriteMode.Over)
    }

    def writeAppendU8ms(content: MSZ[U8]): Unit = {
      Ext.writeU8ms(value, content, Path.WriteMode.Append)
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

    @pure def up: Path = {
      return Path.Impl(Ext.parent(value))
    }
  }

  @ext("Os_Ext") object Ext {

    @pure def cliArgs: ISZ[String] = $

    @pure def cwd: String = $

    @pure def fileSep: String = $

    @pure def home: String = $

    @pure def lineSep: String = $

    @pure def pathSep: String = $

    @pure def os: Kind.Type = $

    def roots: ISZ[String] = $

    def abs(path: String): String = $

    def canon(path: String): String = $

    def chmod(path: String, mask: String, all: B): Unit = $

    def copy(path: String, target: String, over: B): Unit = $

    def env(name: String): Option[String] = $

    def envs: Map[String, String] = $

    def exists(path: String): B = $

    def exit(code: Z): Unit = $

    @pure def isAbs(path: String): B = $

    def kind(path: String): Path.Kind.Type = $

    def lastModified(path: String): Z = $

    def list(path: String): ISZ[String] = $

    def move(path: String, target: String, over: B): Unit = $

    def mkdir(path: String, all: B): Unit = $

    @pure def name(path: String): String = $

    @pure def norm(path: String): String = $

    def properties(path: String): Map[String, String] = $

    def readSymLink(path: String): Option[String] = $

    @pure def relativize(path: String, other: String): String = $

    def read(path: String): String = $

    def readU8s(path: String): ISZ[U8] = $

    def readU8ms(path: String): MSZ[U8] = $

    def readLineStream(path: String): Path.Jen[String] = $

    def readU8Stream(path: String): Path.Jen[U8] = $

    def readCStream(path: String): Path.Jen[C] = $

    def readLineMStream(path: String): Path.MJen[String] = $

    def readCMStream(path: String): Path.MJen[C] = $

    def readU8MStream(path: String): Path.MJen[U8] = $

    def remove(path: String): Unit = $

    def removeAll(path: String): Unit = $

    def removeOnExit(path: String): Unit = $

    def temp(prefix: String, suffix: String): String = $

    def tempDir(prefix: String): String = $

    def write(path: String, content: String, mode: Path.WriteMode.Type): Unit = $

    def writeU8s(path: String, u8s: ISZ[U8], mode: Path.WriteMode.Type): Unit = $

    def writeU8ms(path: String, u8s: MSZ[U8], mode: Path.WriteMode.Type): Unit = $

    def writeLineStream(path: String, lines: Jen[String], mode: Path.WriteMode.Type): Unit = $

    def writeU8Stream(path: String, u8s: Jen[U8], mode: Path.WriteMode.Type): Unit = $

    def writeCStream(path: String, cs: Jen[C], mode: Path.WriteMode.Type): Unit = $

    def writeLineMStream(path: String, lines: MJen[String], mode: Path.WriteMode.Type): Unit = $

    def writeU8MStream(path: String, u8s: MJen[U8], mode: Path.WriteMode.Type): Unit = $

    def writeCMStream(path: String, cs: MJen[C], mode: Path.WriteMode.Type): Unit = $

    @pure def parent(path: String): String = $

    def proc(e: Proc): Proc.Result = $

  }

}

