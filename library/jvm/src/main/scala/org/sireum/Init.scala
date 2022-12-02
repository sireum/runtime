// #Sireum
/*
 Copyright (c) 2017-2022, Robby, Kansas State University
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

@datatype class Init(val home: Os.Path,
                     val kind: Os.Kind.Type,
                     val versions: Map[String, String]) {

  @memoize def homeBin: Os.Path = {
    return home / "bin"
  }

  @memoize def homeLib: Os.Path = {
    return home / "lib"
  }

  @memoize def homeBinPlatform: Os.Path = {
    kind match {
      case Os.Kind.Win => return homeBin / "win"
      case Os.Kind.Linux => return homeBin / "linux"
      case Os.Kind.LinuxArm => return homeBin / "linux" / "arm"
      case Os.Kind.Mac => return homeBin / "mac"
      case _ => return homeBin / "unsupported"
    }
  }

  @memoize def cache: Os.Path = {
    Os.env("SIREUM_CACHE") match {
      case Some(d) => return Os.path(d)
      case _ => return Os.home / "Downloads" / "sireum"
    }
  }

  @memoize def scalacPluginVersion: String = {
    return versions.get("org.sireum%%scalac-plugin%").get
  }

  @memoize def coursierVersion: String = {
    return versions.get("org.sireum.version.coursier").get
  }

  @memoize def scalacPlugin: Os.Path = {
    return homeLib / s"scalac-plugin-$scalacPluginVersion.jar"
  }

  @memoize def scalaVersion: String = {
    return versions.get("org.scala-lang%scala-library%").get
  }

  @memoize def scalaHome: Os.Path = {
    return Os.scalaHomeOpt(Some(home)).get
  }

  @memoize def sireumJar: Os.Path = {
    return homeBin / "sireum.jar"
  }

  @memoize def javaVersion: String = {
    return versions.get("org.sireum.version.zulu").get
  }

  @memoize def javaHome: Os.Path = {
    return Os.javaHomeOpt(kind, Some(home)).get
  }

  def installJava(): Unit = {
    homeBinPlatform.mkdirAll()

    if (Os.env("SIREUM_PROVIDED_JAVA") != Some("true")) {
      val javaVer = javaHome / "VER"
      if (!javaVer.exists || ops.StringOps(javaVer.read).trim != javaVersion) {
        val dropName: String = kind match {
          case Os.Kind.Mac =>
            if (Os.isMacArm) s"zulu$javaVersion-macosx_aarch64.tar.gz"
            else s"zulu$javaVersion-macosx_x64.tar.gz"
          case Os.Kind.Linux => s"zulu$javaVersion-linux_x64.tar.gz"
          case Os.Kind.LinuxArm => s"zulu$javaVersion-linux_aarch64.tar.gz"
          case Os.Kind.Win => s"zulu$javaVersion-win_x64.zip"
          case Os.Kind.Unsupported => return
        }
        val url = s"https://cdn.azul.com/zulu/bin/$dropName"
        val drop = cache / dropName
        if (!drop.exists) {
          println(s"Please wait while downloading Zulu $javaVersion ...")
          drop.downloadFrom(url)
          println()
        }
        javaHome.removeAll()
        if (Os.isWin) {
          drop.unzipTo(homeBinPlatform)
        } else {
          proc"tar xfz $drop".at(homeBinPlatform).runCheck()
        }
        for (d <- homeBinPlatform.list if d.isDir && ops.StringOps(d.name).startsWith("zulu")) {
          d.moveTo(javaHome)
        }
        javaVer.writeOver(javaVersion)
      }
    }
  }

  def installScala(): Unit = {
    homeBin.mkdirAll()

    if (Os.env("SIREUM_PROVIDED_SCALA") != Some("true")) {
      val scalaVer = scalaHome / "VER"
      if (!scalaVer.exists || ops.StringOps(scalaVer.read).trim != scalaVersion) {
        val dropName = s"scala-$scalaVersion.zip"
        val scalaUrl = s"https://github.com/sireum/rolling/releases/download/scala/$dropName"
        val drop = cache / dropName
        if (!drop.exists) {
          println(s"Please wait while downloading Scala $scalaVersion ...")
          drop.downloadFrom(scalaUrl)
          println()
        }
        drop.unzipTo(homeBin)
        scalaHome.removeAll()
        (homeBin / s"scala-$scalaVersion").moveOverTo(scalaHome)
        (scalaHome / "bin").chmodAll("+x")
        scalaVer.write(scalaVersion)
        println()
      }
    }
  }

  def installScalacPlugin(): Unit = {
    homeLib.mkdirAll()

    if (!scalacPlugin.exists) {
      val dropName = s"scalac-plugin-$scalacPluginVersion.jar"
      val scalacPluginUrl = s"https://github.com/sireum/scalac-plugin/releases/download/$scalacPluginVersion/$dropName"
      val drop = cache / dropName
      if (drop.exists) {
        println(s"Please wait while downloading Slang scalac plugin $scalacPluginVersion ...")
        drop.downloadFrom(scalacPluginUrl)
        println()
      }
      for (p <- homeLib.list if ops.StringOps(p.name).startsWith("scalac-plugin")) {
        p.removeAll()
      }
      drop.copyOverTo(scalacPlugin)
    }
  }

  def installCoursier(): Unit = {
    homeLib.mkdirAll()

    val coursierVer = homeLib / "coursier.jar.ver"
    if (coursierVer.exists && ops.StringOps(coursierVer.read).trim == coursierVersion) {
      return
    }

    val drop = cache / s"coursier-$coursierVersion.jar"
    if (!drop.exists) {
      println(s"Downloading Coursier $coursierVersion ...")
      val url = s"https://github.com/coursier/coursier/releases/download/v$coursierVersion/coursier.jar"
      drop.downloadFrom(url)
      println()
    }

    val coursierJar = home / "lib" / "coursier.jar"
    drop.copyOverTo(coursierJar)

    coursierVer.writeOver(coursierVersion)
  }

  def installZ3(): Unit = {
    homeBinPlatform.mkdirAll()

    val version = versions.get("org.sireum.version.z3").get
    val dir = homeBinPlatform / "z3"
    val ver = dir / "VER"

    if (ver.exists && ver.read == version) {
      return
    }

    val filename: String = kind match {
      case Os.Kind.Win => s"z3-$version-x64-win.zip"
      case Os.Kind.Linux => s"z3-$version-x64-glibc-2.31.zip"
      case Os.Kind.Mac =>
        if (ops.StringOps(proc"uname -m".run().out).trim == "arm64") s"z3-$version-arm64-osx-11.0.zip"
        else s"z3-$version-x64-osx-10.16.zip"
      case _ => return
    }

    val bundle = cache / filename

    if (!bundle.exists) {
      println(s"Please wait while downloading Z3 $version ...")
      bundle.up.mkdirAll()
      bundle.downloadFrom(s"https://github.com/Z3Prover/z3/releases/download/z3-$version/$filename")
      println()
    }

    println("Extracting Z3 ...")
    bundle.unzipTo(dir.up)

    for (p <- dir.up.list if ops.StringOps(p.name).startsWith("z3-")) {
      dir.removeAll()
      p.moveTo(dir)
    }

    kind match {
      case Os.Kind.Linux => (dir / "bin" / "z3").chmod("+x")
      case Os.Kind.Mac => (dir / "bin" / "z3").chmod("+x")
      case _ =>
    }

    ver.writeOver(version)
  }

  def installCVC(): Unit = {
    homeBinPlatform.mkdirAll()

    def installCVCGen(gen: String, version: String): Unit = {
      val genOpt: Option[String] = if (gen == "4") None() else Some(gen)
      val exe = homeBinPlatform / (if (kind == Os.Kind.Win) st"cvc$genOpt.exe" else st"cvc$genOpt").render
      val ver = homeBinPlatform / st".cvc$genOpt.ver".render

      val VER = s"$gen-$version"

      if (ver.exists && ver.read == VER) {
        return
      }

      val (sub, filename, dropname): (String, String, String) = (gen, kind) match {
        case (string"5", Os.Kind.Win) => (s"cvc$gen-$version", s"cvc$gen-Win64.exe", s"cvc$gen-$version-Win64.exe")
        case (string"5", Os.Kind.Linux) => (s"cvc$gen-$version", s"cvc$gen-Linux", s"cvc$gen-$version-Linux")
        case (string"5", Os.Kind.Mac) =>
          if (ops.StringOps(proc"uname -m".run().out).trim == "arm64")
            (s"cvc$gen-$version", s"cvc$gen-macOS-arm64", s"cvc$gen-$version-macOS-arm64")
          else (s"cvc$gen-$version", s"cvc$gen-macOS", s"cvc$gen-$version-macOS")
        case (string"4", Os.Kind.Win) => (version, s"cvc$gen-$version-win64-opt.exe", s"cvc$gen-$version-win64-opt.exe")
        case (string"4", Os.Kind.Linux) => (version, s"cvc$gen-$version-x86_64-linux-opt", s"cvc$gen-$version-x86_64-linux-opt")
        case (string"4", Os.Kind.Mac) => (version, s"cvc$gen-$version-macos-opt", s"cvc$gen-$version-macos-opt")
        case _ => return
      }

      val drop = cache / dropname

      if (!drop.exists) {
        println(s"Please wait while downloading CVC$gen $version ...")
        drop.up.mkdirAll()
        drop.downloadFrom(s"https://github.com/cvc5/cvc5/releases/download/$sub/$filename")
        println()
      }

      drop.copyOverTo(exe)

      kind match {
        case Os.Kind.Linux => exe.chmod("+x")
        case Os.Kind.Mac => exe.chmod("+x")
        case _ =>
      }

      ver.writeOver(VER)
      println()
    }

    val (gen1, genVersion1, gen2, genVersion2): (String, String, String, String) =
      ops.StringOps(versions.get("org.sireum.version.cvc").get).split((c: C) => c == '-' || c == ',') match {
        case ISZ(g1, gv1, g2, gv2) => (g1, gv1, g2, gv2)
        case ISZ(string"1.8") => ("4", "1.8", "4", "1.8")
        case ISZ(version) => ("5", version, "5", version)
      }
    installCVCGen(gen1, genVersion1)
    if (gen1 != gen2) {
      installCVCGen(gen2, genVersion2)
    }
  }

  def installAltErgoOpen(): Unit = {
    homeBinPlatform.mkdirAll()

    val version = versions.get("org.sireum.version.alt-ergo-open").get
    val dir = homeBinPlatform
    val exe = dir / "alt-ergo-open"
    val ver = dir / ".alt-ergo-open.ver"

    if (ver.exists && ver.read == version) {
      return
    }

    val filename: String = kind match {
      case Os.Kind.Linux => s"alt-ergo-open-$version-linux"
      case Os.Kind.Mac => s"alt-ergo-open-$version-mac"
      case _ => return
    }

    val drop = cache / filename

    if (!drop.exists) {
      println(s"Please wait while downloading Alt-Ergo $version (Apache 2.0 Licence) ...")
      drop.up.mkdirAll()
      drop.downloadFrom(s"https://github.com/sireum/rolling/releases/download/alt-ergo-open/$filename")
      println()
    }

    drop.copyOverTo(exe)

    exe.chmod("+x")

    ver.writeOver(version)
  }


  def basicDeps(): Unit = {
    installJava()
    installScala()
    installScalacPlugin()
  }

  def proyekCompileDeps(): Unit = {
    installCoursier()
  }

  def logikaDeps(): Unit = {
    installZ3()
    installCVC()
    installAltErgoOpen()
  }

  def deps(): Unit = {
    basicDeps()
    proyekCompileDeps()
    logikaDeps()
  }

}
