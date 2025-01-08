// #Sireum
/*
 Copyright (c) 2017-2025, Robby, Kansas State University
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

import org.sireum.U8._

object Init {
  @datatype class Plugin(val id: String,
                         val isCommunity: B,
                         val isJar: B,
                         val version: String)
}

import Init._

@datatype class Init(val home: Os.Path,
                     val kind: Os.Kind.Type,
                     val versions: Map[String, String]) {

  val sireumV: String = Os.env("SIREUM_V") match {
    case Some(tip) => tip
    case _ => "master"
  }

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

  @memoize def binfmt: Os.Path = {
    return homeBin / ".binfmt"
  }

  val cache: Os.Path = {
    val r: Os.Path = Os.env("SIREUM_CACHE") match {
      case Some(d) => Os.path(d)
      case _ => Os.home / "Downloads" / "sireum"
    }
    r.mkdirAll()
    r
  }

  val pluginPrefix: String = "org.sireum.version.plugin."
  val ideaCacheDir: Os.Path = cache / "idea"
  val pluginsCacheDir: Os.Path = ideaCacheDir / "plugins"

  @memoize def distroPlugins: HashMap[String, Plugin] = {
    var r = HashMap.empty[String, Plugin]
    for (key <- versions.keys if ops.StringOps(key).startsWith(pluginPrefix)) {
      val id = ops.StringOps(key).substring(pluginPrefix.size, key.size)
      ops.StringOps(versions.get(key).get).split((c: C) => c == ',') match {
        case ISZ(isCommunity, isJar, ver) =>
          r = r + id ~> Plugin(id, isCommunity == "true", isJar == "true", ver)
      }
    }
    return r
  }

  @memoize def scalacPluginVersion: String = {
    return versions.get("org.sireum%%scalac-plugin%").get
  }

  @memoize def coursierVersion: String = {
    return versions.get("org.sireum.version.coursier").get
  }

  @memoize def jacocoVersion: String = {
    return versions.get("org.sireum.version.jacoco").get
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

  @memoize def maryTtsJar: Os.Path = {
    return homeLib / "marytts_text2wav.jar"
  }

  @memoize def checkstack: Os.Path = {
    return homeBinPlatform / ".checkstack"
  }

  @memoize def checkstackVersion: String = {
    return versions.get("org.sireum.version.checkstack").get
  }

  @memoize def javaVersion: String = {
    return versions.get("org.sireum.version.java").get
  }

  @pure def platform(k: Os.Kind.Type): String = {
    k match {
      case Os.Kind.Mac => return "mac"
      case Os.Kind.LinuxArm => return "linux/arm"
      case Os.Kind.Linux => return "linux"
      case Os.Kind.Win => return "win"
      case _ => halt("Unsupported platform")
    }
  }

  def installJava(vs: Map[String, String], useNik: B, force: B): B = {
    homeBinPlatform.mkdirAll()

    if (force || Os.env("SIREUM_PROVIDED_JAVA") != Some("true")) {
      val javaHome = Os.javaHomeOpt(kind, Some(home)).get
      val javaVersion = vs.get("org.sireum.version.java").get
      val ISZ(nikJavaVersion, nikVersion) = ops.StringOps(vs.get("org.sireum.version.nik").get).split((c: C) => c == ',')
      val nikFullVersion = ops.StringOps(s"$nikVersion-$nikJavaVersion").replaceAllLiterally("+", "%2B")
      var isNik = useNik
      var javaUrl: String = ""
      kind match {
        case Os.Kind.LinuxArm =>
          isNik = F
          javaUrl = s"https://download.bell-sw.com/java/$javaVersion/bellsoft-jdk$javaVersion-linux-aarch64-full.tar.gz"
        case Os.Kind.Linux =>
          if (useNik) {
            javaUrl = s"https://github.com/bell-sw/LibericaNIK/releases/download/$nikFullVersion/bellsoft-liberica-vm-full-openjdk$nikJavaVersion-$nikVersion-linux-amd64.tar.gz"
          } else {
            javaUrl = s"https://download.bell-sw.com/java/$javaVersion/bellsoft-jdk$javaVersion-linux-amd64-full.tar.gz"
          }
        case Os.Kind.Win =>
          if (Os.isWinArm) {
            isNik = F
            javaUrl = s"https://download.bell-sw.com/java/$javaVersion/bellsoft-jdk$nikVersion-windows-aarch64-full.zip"
          } else {
            if (useNik) {
              javaUrl = s"https://github.com/bell-sw/LibericaNIK/releases/download/$nikFullVersion/bellsoft-liberica-vm-full-openjdk$nikJavaVersion-$nikVersion-windows-amd64.zip"
            } else {
              javaUrl = s"https://download.bell-sw.com/java/$javaVersion/bellsoft-jdk$javaVersion-windows-amd64-full.zip"
            }
          }
        case Os.Kind.Mac =>
          if (Os.isMacArm) {
            if (useNik) {
              javaUrl = s"https://github.com/bell-sw/LibericaNIK/releases/download/$nikFullVersion/bellsoft-liberica-vm-full-openjdk$nikJavaVersion-$nikVersion-macos-aarch64.tar.gz"
            } else {
              javaUrl = s"https://download.bell-sw.com/java/$javaVersion/bellsoft-jdk$javaVersion-macos-aarch64-full.tar.gz"
            }
          } else {
            if (useNik) {
              javaUrl = s"https://github.com/bell-sw/LibericaNIK/releases/download/$nikFullVersion/bellsoft-liberica-vm-full-openjdk$nikJavaVersion-$nikVersion-macos-amd64.tar.gz"
            } else {
              javaUrl = s"https://download.bell-sw.com/java/$javaVersion/bellsoft-jdk$javaVersion-macos-amd64-full.tar.gz"
            }
          }
        case _ =>
          return F
      }

      val VER: String = if (isNik) nikFullVersion else javaVersion
      val javaVer = javaHome / "VER"

      def diffVersion: B = {
        val content = ops.StringOps(javaVer.read).trim
        return content != nikFullVersion && content != javaVersion
      }

      if (force || !javaVer.exists || diffVersion) {
        val drop = cache / ops.StringOps(javaUrl).substring(ops.StringOps(javaUrl).lastIndexOf('/') + 1, javaUrl.size)
        val jdk: String = if (isNik) s"Liberica Native Image Kit JDK Full $javaVersion-$nikVersion" else s"Liberica JDK Full $javaVersion"
        if (!drop.exists) {
          println(s"Please wait while downloading $jdk ...")
          drop.downloadFrom(javaUrl)
          println()
        }

        println(s"Extracting $jdk ...")
        val d = Os.tempDir()
        if (Os.isWin) {
          drop.unzipTo(d)
        } else {
          drop.unTarGzTo(d)
        }
        javaHome.removeAll()
        val javaTemp = d.list(0)
        if (Os.isMac) {
          val javaTempHome = javaTemp / "Contents" / "Home"
          if (javaTempHome.exists) {
            javaTempHome.moveTo(javaHome)
          } else {
            javaTemp.moveTo(javaHome)
          }
        } else {
          javaTemp.moveTo(javaHome)
        }
        d.removeAll()

        println()

        javaVer.writeOver(VER)
      }
    }
    return T
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
      if (!drop.exists) {
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

    val coursierVer: Os.Path = kind match {
      case Os.Kind.Unsupported => homeLib / ".cs.ver"
      case _ => homeBinPlatform / ".cs.ver"
    }

    if (coursierVer.exists && ops.StringOps(coursierVer.read).trim == coursierVersion) {
      return
    }

    kind match {
      case Os.Kind.Win =>
        val drop = cache / s"cs-$coursierVersion-x86_64-pc-win32.zip"
        if (!drop.exists) {
          println(s"Downloading Coursier $coursierVersion ...")
          val url = s"https://github.com/coursier/coursier/releases/download/v$coursierVersion/cs-x86_64-pc-win32.zip"
          drop.downloadFrom(url)
          println()
        }
        drop.unzipTo(homeBinPlatform)
        (homeBinPlatform / "cs-x86_64-pc-win32.exe").moveOverTo(homeBinPlatform / "cs.exe")
      case Os.Kind.Linux =>
        val drop = cache / s"cs-$coursierVersion-x86_64-pc-linux-static.gz"
        if (!drop.exists) {
          println(s"Downloading Coursier $coursierVersion ...")
          val url = s"https://github.com/coursier/coursier/releases/download/v$coursierVersion/cs-x86_64-pc-linux-static.gz"
          drop.downloadFrom(url)
          println()
        }
        proc"gunzip $drop".runCheck()
        (cache / s"cs-$coursierVersion-x86_64-pc-linux-static").moveOverTo(homeBinPlatform / "cs")
        (homeBinPlatform / "cs").chmod("+x")
      case Os.Kind.LinuxArm =>
        val drop = cache / s"cs-$coursierVersion-aarch64-pc-linux-static-sdk.zip"
        if (!drop.exists) {
          println(s"Downloading Coursier $coursierVersion ...")
          val url = s"https://github.com/VirtusLab/coursier-m1/releases/download/v$coursierVersion/cs-aarch64-pc-linux-static-sdk.zip"
          drop.downloadFrom(url)
          println()
        }
        drop.unzipTo(homeBinPlatform)
        (homeBinPlatform / "cs-aarch64-pc-linux-static-sdk" / "bin" / "cs").moveOverTo(homeBinPlatform / "cs")
        (homeBinPlatform / "cs").chmod("+x")
        (homeBinPlatform / "cs-aarch64-pc-linux-static-sdk").removeAll()
      case Os.Kind.Mac =>
        val arch: String = if (Os.isMacArm) "aarch64" else "x86_64"
        val drop = cache / s"cs-$coursierVersion-$arch-apple-darwin.gz"
        if (!drop.exists) {
          println(s"Downloading Coursier $coursierVersion ...")
          val url = s"https://github.com/VirtusLab/coursier-m1/releases/download/v$coursierVersion/cs-$arch-apple-darwin.gz"
          drop.downloadFrom(url)
          println()
        }
        proc"gunzip $drop".runCheck()
        (cache / s"cs-$coursierVersion-$arch-apple-darwin").moveOverTo(homeBinPlatform / "cs")
        (homeBinPlatform / "cs").chmod("+x")
      case _ =>
        val drop = cache / s"coursier-$coursierVersion.jar"
        if (!drop.exists) {
          println(s"Downloading Coursier $coursierVersion ...")
          val url = s"https://github.com/coursier/coursier/releases/download/v$coursierVersion/coursier.jar"
          drop.downloadFrom(url)
          println()
        }

        val coursierJar = home / "lib" / "coursier.jar"
        drop.copyOverTo(coursierJar)
    }
    coursierVer.writeOver(coursierVersion)
  }

  def installJacoco(): Unit = {
    homeLib.mkdirAll()

    val jacocoVer = homeLib / "jacoco.ver"
    if (jacocoVer.exists && ops.StringOps(jacocoVer.read).trim == jacocoVersion) {
      return
    }

    val agentDrop = cache / s"jacocoagent-$jacocoVersion.jar"
    if (!agentDrop.exists) {
      println(s"Downloading JaCoCo Agent $jacocoVersion ...")
      val url = s"https://github.com/sireum/rolling/releases/download/jacoco/${agentDrop.name}"
      agentDrop.downloadFrom(url)
      println()
    }

    val agentJar = home / "lib" / "jacocoagent.jar"
    agentDrop.copyOverTo(agentJar)

    val cliDrop = cache / s"jacococli-$jacocoVersion.jar"
    if (!cliDrop.exists) {
      println(s"Downloading JaCoCo CLI $jacocoVersion ...")
      val url = s"https://github.com/sireum/rolling/releases/download/jacoco/${cliDrop.name}"
      cliDrop.downloadFrom(url)
      println()
    }

    val cliJar = home / "lib" / "jacococli.jar"
    cliDrop.copyOverTo(cliJar)

    jacocoVer.writeOver(jacocoVersion)
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
      case Os.Kind.Win =>
        if (Os.isWinArm) s"z3-$version-win-arm64.zip"
        else s"z3-exe-static-$version-win-amd64.zip"
      case Os.Kind.Mac =>
        if (Os.isMacArm) s"z3-exe-gmp-$version-mac-arm64.zip"
        else s"z3-exe-$version-mac-amd64.zip"
      case Os.Kind.Linux => s"z3-exe-static-musl-gmp-$version-linux-amd64.zip"
      case Os.Kind.LinuxArm => s"z3-exe-static-musl-gmp-$version-linux-arm64.zip"
      case _ => return
    }
    val url: String = s"https://github.com/sireum/rolling/releases/download/z3/$filename"
    val bundle = cache / filename
    if (!bundle.exists) {
      println(s"Please wait while downloading Z3 $version ...")
      bundle.up.mkdirAll()
      bundle.downloadFrom(url)
      println()
    }

    println("Extracting Z3 ...")
    dir.removeAll()
    bundle.unzipTo(dir.up)

    for (p <- dir.up.list if ops.StringOps(p.name).startsWith("z3-")) {
      p.moveTo(dir)
    }

    if (kind != Os.Kind.Win) {
      (dir / "bin" / "z3").chmod("+x")
    }
    println()

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

      val dropname: String = (gen, kind) match {
        case (string"5", Os.Kind.Win) => s"cvc$gen-$version-Win64-static.zip"
        case (string"5", Os.Kind.LinuxArm) => s"cvc$gen-$version-Linux-arm64-static.zip"
        case (string"5", Os.Kind.Linux) => s"cvc$gen-$version-Linux-static.zip"
        case (string"5", Os.Kind.Mac) =>
          if (Os.isMacArm) s"cvc$gen-$version-macOS-arm64-static.zip"
          else s"cvc$gen-$version-macOS-static.zip"
        case (string"4", Os.Kind.Win) => s"cvc$gen-$version-win64-opt.exe"
        case (string"4", Os.Kind.Linux) => s"cvc$gen-$version-x86_64-linux-opt.8-linux"
        case (string"4", Os.Kind.Mac) => s"cvc$gen-$version-macos-opt.8-mac"
        case _ => return
      }

      val drop = cache / dropname

      if (!drop.exists) {
        val url = s"https://github.com/sireum/rolling/releases/download/cvc$gen/$dropname"
        println(s"Please wait while downloading CVC$gen $version ...")
        drop.up.mkdirAll()
        drop.downloadFrom(url)
        println()
      }

      if (gen == "5") {
        val d = Os.tempDir()
        drop.unzipTo(d)
        for (p <- d.list if ops.StringOps(p.name).startsWith(s"cvc5-")) {
          (d / p.name / "bin" / (if (kind == Os.Kind.Win) "cvc5.exe" else "cvc5")).copyOverTo(exe)
        }
        d.removeAll()
      } else {
        drop.copyOverTo(exe)
      }

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

  def installMaryTTS(): Unit = {
    if (!maryTtsJar.exists) {
      val cacheJar = cache / maryTtsJar.name
      if (!cacheJar.exists) {
        cacheJar.up.mkdirAll()
        println("Please wait while downloading MaryTTS text2wav ...")
        cacheJar.downloadFrom("https://github.com/sireum/rolling/releases/download/marytts-text2wav/txt2wav.jar")
        cacheJar.copyOverTo(maryTtsJar)
      }
      println()
    }
  }

  def installCheckStack(): Unit = {
    kind match {
      case Os.Kind.Linux =>
      case Os.Kind.LinuxArm =>
      case _ => return
    }
    val ver = homeBinPlatform / ".checkstack.ver"
    if (!checkstack.exists || !ver.exists || ver.read != checkstackVersion) {
      println(s"Please wait while downloading checkstack.pl $checkstackVersion ...")
      checkstack.downloadFrom(s"https://raw.githubusercontent.com/torvalds/linux/$checkstackVersion/scripts/checkstack.pl")
      ver.writeOver(checkstackVersion)
      println()
    }
  }

  def installMill(verbose: B): Unit = {
    def patchMill(mill: Os.Path): Unit = {
      val ansiPatch = mill.up.canon / "windows-ansi-patch.zip"
      ansiPatch.downloadFrom("https://github.com/sireum/rolling/releases/download/mill/windows-ansi-0.0.5-patch.zip")
      ansiPatch.unzipTo(mill.up)
      ansiPatch.removeAll()
      if (kind == Os.Kind.Win) {
        val p7zz = install7zz()
        proc"$p7zz d mill.jar io/github/alexarchambault/windowsansi/WindowsAnsi.class".at(mill.up).runCheck()
        proc"$p7zz a mill.jar io/github/alexarchambault/windowsansi/WindowsAnsi.class".at(mill.up).runCheck()
      }
      val content = mill.readU8s
      mill.removeAll()
      val size = content.size
      val newContent = MSZ.create(content.size + 1024, u8"0")
      val newSize = newContent.size

      @strictpure def at(i: Z): C = if (0 <= i & i < size) conversions.U32.toC(conversions.U8.toU32(content(i))) else '\u0000'

      @pure def isAt(s: String, i: Z): B = {
        val cis = conversions.String.toCis(s)
        var j = 0
        while (j < cis.size) {
          if (cis(j) != at(i + j)) {
            return F
          }
          j = j + 1
        }
        return T
      }

      def put(j: Z, c: C): B = {
        if (0 <= j & j < newSize) {
          val b = conversions.U32.toU8(conversions.C.toU32(c))
          newContent(j) = b
          return T
        }
        return F
      }

      def putString(s: String, i: Z): Z = {
        val cis = conversions.String.toCis(s)
        var j = 0
        while (j < cis.size) {
          if (put(i + j, cis(j))) {
            j = j + 1
          } else {
            return i + j
          }
        }
        return i + j
      }

      var i: Z = 0
      var j: Z = 0
      var stop = F
      val javaPrefix: String = "=\"java\""
      val javaExePrefix: String = "=java.exe\""
      while (i < size) {
        if (!stop) {
          if (isAt("%*", i) || isAt("\"$@\"", i)) {
            j = putString("-i ", j)
          } else if (isAt(javaPrefix, i)) {
            val platform: String = Os.kind match {
              case Os.Kind.Win => "win"
              case Os.Kind.Mac => "mac"
              case Os.Kind.Linux => "linux"
              case Os.Kind.LinuxArm => "linux/arm"
              case _ => ""
            }
            if (platform.size > 0) {
              j = putString(s"=\"$$SIREUM_HOME/bin/$platform/java/bin/java\"", j)
              i = i + javaPrefix.size
            }
          } else if (isAt(javaExePrefix, i)) {
            j = putString(s"=%SIREUM_HOME%\\bin\\win\\java\\bin\\java.exe\"", j)
            i = i + javaExePrefix.size
          } else if (isAt("exit /B %errorlevel%", i)) {
            stop = T
          }
        }
        put(j, at(i))
        j = j + 1
        i = i + 1
      }
      mill.writeU8Partms(newContent, 0, j)
      mill.chmod("+x")
    }

    val millVersion = versions.get("org.sireum.version.mill").get
    val ver = home / "bin" / ".mill.ver"
    if (ver.exists && ver.read == millVersion) {
      return
    }
    val url = s"https://github.com/com-lihaoyi/mill/releases/download/$millVersion/$millVersion-assembly"
    val mill: Os.Path = (home / "bin" / (if (Os.isWin) "mill.bat" else "mill")).canon
    if (verbose) {
      println(s"Downloading mill $millVersion ...")
    }
    val d = Os.tempDir()
    val millJar = d / "mill.jar"
    millJar.downloadFrom(url)
    if (verbose) {
      println()
      println(s"Patching mill ...")
    }
    patchMill(millJar)
    millJar.moveOverTo(mill)
    d.removeAll()
    if (verbose) {
      println()
    }
    ver.writeOver(millVersion)
  }

  @pure def ideaDirPath(isUltimate: B, isServer: B): Os.Path = {
    return homeBinPlatform / (if (isServer) "idea-server" else if (isUltimate) "idea-ultimate" else "idea")
  }

  def installScripts(): Unit = {
    val install = homeBin / "install"
    if (!install.exists) {
      val sha = GitHub.repo("sireum", "kekinian").submoduleShaOf("bin/install", sireumV)
      val drop = cache / s"install-$sha.zip"
      if (!drop.exists) {
        println("Please wait while downloading additional installation scripts ...")
        drop.downloadFrom(s"https://github.com/sireum/bin-install/archive/$sha.zip")
        println()
      }
      install.mkdirAll()
      drop.unzipTo(home)
      (home / s"bin-install-$sha").moveOverTo(install)

      if (kind != Os.Kind.Win) {
        for (p <- Os.Path.walk(install, F, F, (path: Os.Path) => ops.StringOps(path.name).endsWith(".cmd"))) {
          p.chmod("+x")
        }
      }
    }
  }

  def installFonts(force: B): Unit = {
    val d: Os.Path = kind match {
      case Os.Kind.Mac => Os.home / "Library" / "Fonts"
      case Os.Kind.Win => Os.tempDir()
      case _ => Os.home / ".local" / "share" / "fonts"
    }
    d.mkdirAll()
    var map = Map.empty[String, String]
    for (p <- Library.fontFiles if !ops.StringOps(p._1.get).endsWith("-Bold.ttf")) {
      val filename = p._1.get
      val f = d / filename
      if (!force) {
        if (kind == Os.Kind.Win) {
          if ((Os.path("C:\\Windows\\Fonts") / f.name).exists) {
            println("Sireum fonts have already been installed")
            return
          }
        } else {
          if (f.exists) {
            println("Sireum fonts have already been installed")
            return
          }
        }
      } else {
        println("Forcing Sireum fonts reinstallation")
      }

      val fontName: String = filename match {
        case string"SireumMono-Regular.ttf" => "Sireum Mono"
        case string"SireumMonoPlus-Regular.ttf" => "Sireum Mono Plus"
        case _ => ""
      }
      if (fontName.size > 0) {
        map = map + fontName ~> f.canon.string
        f.writeU8s(conversions.String.fromBase64(p._2).left)
      }
    }
    kind match {
      case Os.Kind.Mac =>
        println(s"Sireum fonts installed at: $d")
      case Os.Kind.Win =>
        var ps1Content =
          st"""if (!([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)) {
              |  Start-Process PowerShell -Verb RunAs "-NoProfile -ExecutionPolicy Bypass -Command `"cd '$$pwd'; & '$$PSCommandPath';`"";
              |  exit;
              |}
              |""".render
        for (p <- map.entries) {
          val (fontName, fontPath) = p
          ps1Content = s"${ps1Content}Copy-Item \"$fontPath\" \"C:\\Windows\\Fonts\"; New-ItemProperty -Name \"$fontName\" -Path \"HKLM:\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Fonts\" -PropertyType string -Value \"${Os.path(fontPath).name}\";"
        }
        val ps1 = d / "install-font.ps1"
        ps1.writeOver(ps1Content)
        Os.proc(ISZ[String]("powershell", "-noprofile", "-executionpolicy", "bypass", "-file", ps1.canon.string)).runCheck()
        println("Sireum fonts installed at: C:\\Windows\\Fonts")
      case _ =>
        println(s"Sireum fonts installed at: $d")
        proc"fc-cache -f".run()
    }
  }

  def install7zz(): Os.Path = {
    val p7zz = homeBin / (if (Os.isWin) "7zz.com" else "7zz")
    def check7zz(force: B): Unit = {
      kind match {
        case Os.Kind.Win =>
        case Os.Kind.Mac =>
        case _ =>
          if (!force && !binfmt.exists) {
            return
          }
          binfmt.removeAll()
          if (!Os.proc(ISZ[String]("bash", "-c", s"$p7zz -h")).run().ok) {
            val cosmosVersion = versions.get("org.sireum.version.cosmos").get
            if (Os.path("/proc/sys/fs/binfmt_misc/WSLInterop").exists) {
              println(
                st"""Please run the following to enable $p7zz:
                    |
                    |sudo sh -c "echo -1 > /proc/sys/fs/binfmt_misc/WSLInterop"
                    |""".render
              )
            } else {
              println(
                st"""Please run the following to enable $p7zz:
                    |
                    |sudo wget -O /usr/bin/ape https://cosmo.zip/pub/cosmos/v/$cosmosVersion/bin/ape-$$(uname -m).elf
                    |sudo chmod +x /usr/bin/ape
                    |sudo sh -c "echo ':APE:M::MZqFpD::/usr/bin/ape:' >/proc/sys/fs/binfmt_misc/register"
                    |sudo sh -c "echo ':APE-jart:M::jartsr::/usr/bin/ape:' >/proc/sys/fs/binfmt_misc/register"
                    |
                    |or, by disabling binfmt completely:
                    |
                    |sudo sh -c 'echo -1 > /proc/sys/fs/binfmt_misc/cli'
                    |sudo sh -c 'echo -1 > /proc/sys/fs/binfmt_misc/status'
                    |""".render
              )
            }
          }
      }
    }
    val cosmoccVersion = versions.get("org.sireum.version.cosmocc").get
    val p7zipVersion = versions.get("org.sireum.version.7zip").get
    val p7zzVer = homeBin / s".7zz.ver"
    val ver = s"$p7zipVersion-$cosmoccVersion"
    if (p7zzVer.exists && p7zzVer.read == ver) {
      check7zz(F)
      return p7zz
    }
    val drop = cache / s"7zz-$p7zipVersion-cosmo-$cosmoccVersion.com"
    if (!drop.exists) {
      println("Downloading 7zz ...")
      drop.downloadFrom(s"https://github.com/sireum/rolling/releases/download/misc/${drop.name}")
      println()
    }
    drop.copyOverTo(p7zz)
    p7zz.chmod("+x")
    p7zzVer.writeOver(ver)
    check7zz(T)
    return p7zz
  }

  def installVSCodium(existingInstallOpt: Option[Os.Path], extensionsDirOpt: Option[Os.Path], extensions: ISZ[String]): Unit = {
    val useDefaultExtDir = Os.env("GITHUB_ACTION").nonEmpty ||
      ops.StringOps(s"${homeBin.up.canon}${Os.fileSep}").startsWith(Os.home.string)
    val vscodiumVersion = versions.get("org.sireum.version.vscodium").get
    val sireumExtVersion = versions.get("org.sireum.version.vscodium.extension").get
    val sysIdeVersion = versions.get("org.sireum.version.vscodium.extension.syside").get
    val es = extensions :+ s"Sensmetry.sysml-2ls@$sysIdeVersion"
    val urlPrefix = s"https://github.com/VSCodium/vscodium/releases/download/$vscodiumVersion"
    val sireumExtUrl = s"https://github.com/sireum/vscode-extension/releases/download/$sireumExtVersion/sireum-vscode-extension.vsix"
    val sireumExtDrop = s"sireum-vscode-extension-$sireumExtVersion.vsix"
    var vsCodiumOpt = Option.none[Os.Path]()
    var extDirOpt = Option.none[Os.Path]()

    def gumboTokens(existingKeywords: HashSet[String]): ISZ[String] = {
      val f = Os.tempFix("GUMBO", ".tokens")
      f.removeAll()
      f.downloadFrom("https://raw.githubusercontent.com/sireum/hamr-sysml-parser/master/src/org/sireum/hamr/sysml/parser/GUMBO.tokens")
      var r = ISZ[String]()
      for (key <- f.properties.keys) {
        val cis = conversions.String.toCis(key)
        if (cis(0) == '\'' && cis(cis.size - 1) == '\'' && ops.ISZOps(
          for (i <- 1 until cis.size - 1) yield
            ('a' <= cis(i) && cis(i) <= 'z') || ('A' <= cis(i) && cis(i) <= 'Z') || cis(i) == '_').forall((b: B) => b)) {
          ops.StringOps.substring(cis, 1, cis.size - 1) match {
            case string"T" =>
            case string"F" =>
            case string"" =>
            case keyword if !existingKeywords.contains(keyword) => r = r :+ keyword
            case _ =>
          }
        }
      }
      return r
    }

    def patchMetals(d: Os.Path): Unit = {
      def patchPackage(): Unit = {
        val packageJson = d / "package.json"
        val cis = conversions.String.toCis(packageJson.read)
        val activationEvents = conversions.String.toCis("\"activationEvents\"")
        var i = ops.StringOps.stringIndexOfFrom(cis, activationEvents, 0)
        i = i + activationEvents.size
        val min = ops.StringOps.indexOfFrom(cis, '[', i)
        val max = ops.StringOps.indexOfFrom(cis, ']', min)
        var newLines = ISZ[String]()
        val workspaceContains = conversions.String.toCis("workspaceContains")
        val onLanguage = conversions.String.toCis("onLanguage")
        val lines = ops.StringOps(ops.StringOps.substring(cis, min + 1, max)).split((c: C) => c == '\n')
        for (line <- lines) {
          val lineCis = conversions.String.toCis(line)
          var l = ops.StringOps.trim(lineCis)
          if (ops.StringOps(l).endsWith(",")) {
            l = ops.StringOps(l).substring(0, l.size - 1)
          }
          if (l.size > 0 && ops.StringOps.stringIndexOfFrom(lineCis, workspaceContains, 0) < 0 &&
            ops.StringOps.stringIndexOfFrom(lineCis, onLanguage, 0) < 0) {
            newLines = newLines :+ l
          }
        }
        if (lines.size != newLines.size) {
          println("Patching Scalameta Metals ...")
          val tab = "\t"
          newLines = newLines :+ s""""workspaceContains:.bloop""""
          packageJson.setWritable(T)
          packageJson.writeOver(
            st"""${ops.StringOps.substring(cis, 0, min + 1)}
                |$tab$tab${(newLines, ",\n\t\t")}
                |$tab${ops.StringOps.substring(cis, max, cis.size)}""".render)
          packageJson.setWritable(F)
          println()
        }
      }
      def patchClient(): Unit = {
        val clientJs = d / "node_modules" / "vscode-languageclient" / "lib" / "common"/ "client.js"
        val lines = clientJs.readLines
        var i = 0
        val onRequest = conversions.String.toCis("connection.onRequest(")
        val showMessageRequest = conversions.String.toCis(".ShowMessageRequest.")
        val insertLine = """                if (params.message.includes("Scala script detected")) return undefined;"""
        var newLines = ISZ[String]()
        while (i < lines.size) {
          val line = conversions.String.toCis(lines(i))
          newLines = newLines :+ lines(i)
          if (ops.StringOps.stringIndexOfFrom(line, onRequest, 0) >= 0 && ops.StringOps.stringIndexOfFrom(line, showMessageRequest, 0) >= 0) {
            val nextLine = lines(i + 1)
            if (!ops.StringOps(nextLine).startsWith(insertLine)) {
              newLines = newLines :+ insertLine
            }
          }
          i = i + 1
        }
        clientJs.setWritable(T)
        clientJs.writeOver(st"${(newLines, "\n")}".render)
        clientJs.setWritable(F)
      }
      patchPackage()
      patchClient()
    }

    def patchSysIDE(d: Os.Path): Unit = {
      val tmlf = d / "syntaxes" / "sysml.tmLanguage.json"
      var content = tmlf.read
      val contentOps = ops.StringOps(content)
      if (contentOps.stringIndexOf(""""/\\*\\*/"""") >= 0) {
        return
      }
      def patchTml(): Unit = {
        val existingKeywords: HashSet[String] = {
          val i = contentOps.stringIndexOf("\\\\b(about|")
          val j = contentOps.stringIndexOfFrom(")\\\\b", i)
          HashSet ++ ops.StringOps(contentOps.substring(i, j)).split((c: C) => c == '|') + "about"
        }
        content = {
          val patterns: String = """"patterns": ["""
          val i = contentOps.stringIndexOf(patterns) + patterns.size
          val ins =
            st"""    {
                |      "match": "/\\*\\*/",
                |      "name": "string.quoted.other.sysml"
                |    },"""
          s"${contentOps.substring(0, i)}${Os.lineSep}${ins.render}${contentOps.substring(i, content.size)}"
        }
        content = ops.StringOps(content).replaceAllLiterally("\\\\b(about|", st"\\\\b(${(gumboTokens(existingKeywords), "|")}|about|".render)
        content = ops.StringOps(content).replaceAllLiterally("\"/\\\\*\"", "\"/\\\\*[^{]\"")
        tmlf.setWritable(T)
        tmlf.writeOver(content)
        tmlf.setWritable(F)
      }
      def patchJs(f: Os.Path): Unit = {
        def patchPrefix(text: String, prefix: String): String = {
          val cis = conversions.String.toCis(text)
          var i = ops.StringOps.stringIndexOfFrom(cis, conversions.String.toCis(prefix), 0)
          i = ops.StringOps.indexOfFrom(cis, '{', i + 1) + 1
          val j = ops.StringOps.indexOfFrom(cis, '}', i) + 3
          if (cis(j - 1) != ',') {
            return text
          }
          val s = ops.StringOps.substring(cis, i, j)
          if (!ops.StringOps(s).contains(".SysMLSemanticTokenTypes.annotationBody")) {
            return text
          }
          return conversions.String.fromCis(ops.ISZOps(cis).slice(0, i) ++ ops.ISZOps(cis).slice(j, cis.size))
        }
        f.setWritable(T)
        f.writeOver(patchPrefix(patchPrefix(f.read, "comment("), "textualRep("))
        f.setWritable(F)
      }
      println("Patching SysIDE ...")
      patchTml()
      for (f <- Os.Path.walk(d / "dist", F, F, (p: Os.Path) => p.ext == "js")) {
        patchJs(f)
      }
      println()
    }

    def downloadVSCodium(drop: Os.Path): Unit = {
      val url = s"$urlPrefix/${drop.name}"
      if (!drop.exists) {
        println(s"Downloading VSCodium ...")
        drop.downloadFrom(url)
        println()
      }
    }

    def installExtensions(codium: Os.Path, extensionsDir: Os.Path): String = {
      val extDirArg: String = if (useDefaultExtDir) "" else s" --extensions-dir $extensionsDir"
      val drop = cache / sireumExtDrop
      if (!drop.exists) {
        println("Downloading Sireum VSCode Extension ...")
        drop.downloadFrom(sireumExtUrl)
        println()
      }
      for (ext <- es) {
        proc"$codium --force$extDirArg --install-extension $ext".console.runCheck()
        println()
      }
      proc"$codium --force$extDirArg --install-extension $drop".console.runCheck()
      println()
      val sysidePrefix = conversions.String.toCis("sensmetry.sysml-")
      val metalsPrefix = conversions.String.toCis("scalameta.metals-")
      for (p <- extensionsDir.list) {
        val cis = conversions.String.toCis(p.name)
        if (ops.StringOps.startsWith(cis, sysidePrefix)) {
          patchSysIDE(p)
        } else if (ops.StringOps.startsWith(cis, metalsPrefix)) {
          patchMetals(p)
        }
      }
      return extDirArg
    }

    def replaceImages(path: Os.Path): Unit = {
      if (existingInstallOpt.nonEmpty) {
        return
      }
      var map = HashMap.empty[String, ISZ[U8]]
      for (p <- Library.vscodeImageFiles) {
        map = map + p._1.get ~> conversions.String.fromBase64(p._2).left
      }
      for (p <- Os.Path.walk(path, F, F, (f: Os.Path) => f.ext == "svg" || f.ext == "png" || f.ext == "ico" || f.ext == "icns")) {
        map.get(p.name) match {
          case Some(content) =>
            p.removeAll()
            p.writeU8s(content)
          case _ =>
        }
      }
      if (Os.isWin) {
        val origExe = path / "VSCodium.exe"
        val exe = path / "CodeIVE.exe"
        val rceditVersion = "2.0.0"
        val rcedit = cache / s"rcedit-$rceditVersion.exe"
        if (!rcedit.exists) {
          val url = s"https://github.com/electron/rcedit/releases/download/v$rceditVersion/rcedit-x64.exe"
          println("Downloading rcedit ...")
          rcedit.downloadFrom(url)
          println()
        }
        println(s"Patching $origExe ...")
        val ico = Os.tempFix("code", ".ico")
        ico.removeAll()
        ico.writeU8s(map.get("code.ico").get)
        origExe.moveTo(exe)
        proc"""$rcedit $exe --set-icon $ico""".runCheck()
        ico.removeAll()
        println()
      }
    }

    def mac(): Unit = {
      val drop = cache / s"VSCodium.${if (Os.isMacArm) "arm64" else "x64"}.$vscodiumVersion.dmg"
      val platform = homeBin / "mac"
      var vscodium = platform / "vscodium" / "CodeIVE.app"
      val ver = vscodium.up.canon / "VER"
      var updated = F
      val codium: Os.Path = vsCodiumOpt match {
        case Some(p) =>
          vscodium = p.up.up.up.up.up.canon
          p
        case _ =>
          val c = vscodium / "Contents"/ "Resources" / "app" / "bin" / "codium"
          if (!ver.exists || ver.read != vscodiumVersion) {
            downloadVSCodium(drop)
            vscodium.up.removeAll()
            println("Extracting VSCodium ...")
            proc"hdiutil attach $drop".at(home).runCheck()
            val dirPath = Os.path("/Volumes/VSCodium")
            (dirPath / "VSCodium.app").copyOverTo(vscodium)
            proc"hdiutil eject $dirPath".at(home).runCheck()
            replaceImages(vscodium)
            println()
            ver.write(vscodiumVersion)
            updated = T
          }
          c
      }
      proc"xattr -rd com.apple.quarantine $vscodium".run()
      proc"codesign --force --deep --sign - $vscodium".run()
      val extensionsDir: Os.Path = extDirOpt match {
        case Some(ed) => ed
        case _ =>
          if (useDefaultExtDir) {
            val d = vscodium.up.canon / "codium-portable-data" / "extensions"
            d.mkdirAll()
            d
          } else {
            val d = vscodium.up.canon / "VSCodium-extensions"
            d.mkdirAll()
            d
          }
      }
      val extDirArg = installExtensions(codium, extensionsDir)
      if (updated) {
        if (useDefaultExtDir) {
          println(s"To launch CodeIVE: open $vscodium")
        } else {
          println(s"To launch CodeIVE: $codium$extDirArg")
        }
      }
    }

    def linux(isArm: B): Unit = {
      val drop = cache / s"VSCodium-linux-${if (isArm) "arm64" else "x64"}-$vscodiumVersion.tar.gz"
      val platform: Os.Path = if (isArm) homeBin / "linux" / "arm" else homeBin / "linux"
      var vscodium = platform / "vscodium"
      val ver = vscodium / "VER"
      var updated = F
      val codium: Os.Path = vsCodiumOpt match {
        case Some(p) =>
          vscodium = p.up.up.canon
          p
        case _ =>
          val c = vscodium / "bin" / "codeive"
          if (!ver.exists || ver.read != vscodiumVersion) {
            downloadVSCodium(drop)
            println("Extracting VSCodium ...")
            val vscodiumNew = platform / "vscodium.new"
            drop.unTarGzTo(vscodiumNew)
            if ((vscodium / "data").exists) {
              (vscodium / "data").moveTo(vscodiumNew / "data")
            }
            vscodium.removeAll()
            vscodiumNew.moveTo(vscodium)
            replaceImages(vscodium)
            (vscodium / "bin" / "codium").moveTo(c)
            (vscodium / "codium").moveTo(vscodium / c.name)
            c.writeOver(ops.StringOps(c.read).replaceAllLiterally("/codium", "/codeive"))
            c.chmod("+x")
            println()
            ver.write(vscodiumVersion)
            updated = T
          }
          c
      }
      val extensionsDir: Os.Path = extDirOpt match {
        case Some(ed) => ed
        case _ =>
          if (useDefaultExtDir) {
            val d = vscodium / "data" / "extensions"
            d.mkdirAll()
            d
          } else {
            val d = vscodium / "extensions"
            d.mkdirAll()
            d
          }
      }
      val extDirArg = installExtensions(codium, extensionsDir)
      if (updated) {
        val sandbox = vscodium / "chrome-sandbox"
        println(s"Please run the following:")
        println()
        println(s"sudo chown root:root $sandbox; sudo chmod 4755 $sandbox")
        println()
        println(s"After that, to launch CodeIVE: $codium$extDirArg")
      }
    }

    def win(): Unit = {
      val drop = cache / s"VSCodium-win32-${if (Os.isWinArm) "arm64" else "x64"}-$vscodiumVersion.zip"
      val platform = homeBin / "win"
      var vscodium = platform / "vscodium"
      val ver = vscodium / "VER"
      var updated = F
      val codium: Os.Path = vsCodiumOpt match {
        case Some(p) =>
          vscodium = p.up.up.canon
          p
        case _ =>
          val c =  vscodium / "bin" / "codeive.cmd"
          if (!ver.exists || ver.read != vscodiumVersion) {
            downloadVSCodium(drop)
            val vscodiumNew = platform / "vscodium.new"
            println("Extracting VSCodium ...")
            drop.unzipTo(vscodiumNew)
            if ((vscodium / "data").exists) {
              (vscodium / "data").moveTo(vscodiumNew / "data")
            }
            vscodium.removeAll()
            vscodiumNew.moveTo(vscodium)
            replaceImages(vscodium)
            (vscodium / "bin" / "codium.cmd").moveTo(c)
            println()
            ver.write(vscodiumVersion)
            updated = T
          }
          c.writeOver(ops.StringOps(c.read).replaceAllLiterally("VSCodium.exe", "CodeIVE.exe"))
          c
      }
      val extensionsDir: Os.Path = extDirOpt match {
        case Some(ed) => ed
        case _ =>
          if (useDefaultExtDir) {
            val d = vscodium / "data" / "extensions"
            d.mkdirAll()
            d
          } else {
            val d = vscodium / "extensions"
            d.mkdirAll()
            d
          }
      }
      val extDirArg = installExtensions(codium, extensionsDir)
      if (updated) {
        if (extDirArg.size > 0) {
          println(s"To launch CodeIVE: $codium$extDirArg")
        } else {
          println(s"To launch CodeIVE: ${vscodium / "CodeIVE.exe"}")
        }
      }
    }

    existingInstallOpt match {
      case Some(p) =>
        if (!p.exists) {
          eprintln(s"$p does not exist")
          Os.exit(-1)
        }
        val scripts: HashSSet[String] = HashSSet ++ (if (Os.isWin) ISZ[String]("code.cmd", "codium.cmd") else ISZ[String]("code", "codium"))
        for (codium <- Os.Path.walk(p, F, F, (f: Os.Path) => scripts.contains(f.name)) if vsCodiumOpt.isEmpty) {
          vsCodiumOpt = Some(codium)
          extDirOpt = Some(Os.home / (if (ops.StringOps(codium.name).startsWith("code")) ".vscode" else ".vscode-oss") / "extensions")
        }
        if (vsCodiumOpt.isEmpty) {
          eprintln(st"Could not find ${(scripts, "/")} in $p".render)
          Os.exit(-2)
        }
      case _ =>
    }
    extensionsDirOpt match {
      case Some(ped) =>
        ped.mkdirAll()
        extDirOpt = Some(ped)
      case _ =>
    }
    Os.kind match {
      case Os.Kind.Mac => mac()
      case Os.Kind.Linux => linux(F)
      case Os.Kind.LinuxArm => linux(T)
      case Os.Kind.Win => win()
      case _ =>
        eprintln("Unsupported platform")
        Os.exit(-1)
    }
  }

  @memoize def isIdeaInUserHome: B = {
    return Os.env("GITHUB_ACTION").isEmpty && ops.StringOps(home.string).startsWith(Os.home.canon.string)
  }

  def ideaConfig(isSetup: B, isDev: B, isUltimate: B, projectPathOpt: Option[Os.Path]): Os.Path = {
    val devSuffix: String = if (isDev) "-dev" else ""
    val ult: String = if (isUltimate) "-ult" else ""
    val config: Os.Path = projectPathOpt match {
      case Some(projectPath) => Os.home / ".config" / "JetBrains" / "RemoteDev-IU" /
        ops.StringOps(projectPath.string).replaceAllLiterally(Os.fileSep, "_")
      case _ =>
        if (isSetup || isIdeaInUserHome) {
          home / ".settings" / s".SireumIVE$ult$devSuffix" / "config"
        } else {
          Os.home / s".SireumIVE$ult$devSuffix" / "config"
        }
    }
    return config
  }

  def ideaPlugins(isDev: B, isUltimate: B, projectPathOpt: Option[Os.Path]): Os.Path = {
    return (ideaConfig(T, isDev, isUltimate, projectPathOpt).up / "plugins").canon
  }

  def ideaSandbox(isDev: B): Os.Path = {
    val devSuffix: String = if (isDev) "-dev" else ""
    return (if (isIdeaInUserHome) home / ".settings" else Os.home) / s".SireumIVE$devSuffix-sandbox"
  }

  @strictpure def zipName(id: String, version: String): String = s"$id-$version.zip"

  def downloadPlugins(isDev: B, pluginFilter: Plugin => B): Unit = {
    for (p <- distroPlugins.values if pluginFilter(p)) {
      val ver = p.version
      val zip = zipName(p.id, ver)
      if (!(pluginsCacheDir / zip).exists) {
        val pidOps = ops.StringOps(p.id)
        val sireumPrefix: String = "sireum-"
        val cis706Prefix: String = "ksu-cis-706-"
        val url: String =
          if (pidOps.startsWith(sireumPrefix)) {
            val repo = pidOps.substring(sireumPrefix.size, p.id.size)
            s"https://github.com/sireum/$repo/releases/download/$ver/$repo.zip"
          } else if (pidOps.startsWith(cis706Prefix)) {
            val repo = pidOps.substring(cis706Prefix.size, p.id.size)
            s"https://github.com/ksu-cis-706/$repo/releases/download/$ver/$repo.zip"
          } else {
            s"https://plugins.jetbrains.com/plugin/download?pr=idea&updateId=$ver"
          }
        print(s"Downloading ${p.id} plugin from $url ... ")
        (pluginsCacheDir / zip).downloadFrom(url)
        println("done!")
      }
    }
  }

  def extractPlugins(pluginsDir: Os.Path, pluginFilter: Plugin => B): Unit = {
    val temp = Os.tempDir()
    for (p <- distroPlugins.values if pluginFilter(p)) {
      val zip = zipName(p.id, p.version)
      val zipPath = ideaCacheDir / "plugins" / zip
      if (p.isJar) {
        print(s"Copying ${p.id} plugin ... ")
        zipPath.copyOverTo(pluginsDir / s"${p.id}.jar")
        println("done!")
      } else {
        print(s"Extracting ${p.id} plugin from $zipPath ... ")
        zipPath.unzipTo(temp)
        for (p <- temp.list) {
          val target = pluginsDir / p.name
          target.removeAll()
          p.moveOverTo(target)
        }
        println("done!")
      }
    }
  }

  def buildForms(): Unit = {
    val forms = home / "forms"
    val formsJar = home / "lib" / "forms.jar"
    val ver = formsJar.up / s"${formsJar.name}.ver"
    val version = s"${versions.get("org.sireum.version.forms").get}"
    val versionWithSireum = s"$version-${sireumJar.sha3(8)}"
    if (ver.exists && ver.read == versionWithSireum) {
      return
    }
    println("Building forms ...")
    forms.removeAll()
    proc"git clone https://github.com/sireum/forms $forms".at(home).runCheck()
    proc"git checkout $version".at(forms).runCheck()
    val sireum: Os.Path = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")
    proc"$sireum proyek assemble --main org.sireum.forms.FormsApp --exclude-jar-deps asm:,unmanaged:,org.scala-lang: $forms".runCheck()
    (forms / "out" / "forms" / "assemble" / "forms.jar").copyOverTo(formsJar)
    forms.removeAll()
    ver.writeOver(versionWithSireum)
    println()
  }

  def distro(isDev: B, buildPackage: B, buildIve: B, buildVSCodePackage: B, isUltimate: B, isServer: B): Unit = {
    assert(buildIve | buildVSCodePackage)
    val devSuffix: String = if (isDev) "-dev" else ""
    if (isServer && Os.kind != Os.Kind.Linux) {
      eprintln(s"Server setup is only available in Linux")
      Os.exit(-1)
    }
    val ideaDir: Os.Path = ideaDirPath(isUltimate, isServer)
    val sireumAppDir: Os.Path = ideaDir / s"IVE.app"
    val delPlugins = ISZ[String]("android", "smali", "Ktor", "design-tools", "space", "ml-llm")
    val ignoredIcons = HashSet ++ ISZ[String](
      "idea.icns",
      "idea-dev.icns",
      "idea.png",
      "idea-dev.png",
      "idea.svg",
      "idea-dev.svg",
      "idea-dev.ico",
      "idea-ce.svg",
      "idea-ce-eap.svg",
      "idea-ce_16.svg",
      "idea-ce-eap_16.svg",
      "idea-ce_16@2x.svg",
      "idea-ce-eap_16@2x.svg",
      "icon_CE_256.png",
      "icon_CE_256@2x.png",
      "icon_CE_512.png",
      "icon_CE_512@2x.png",
      "idea_logo_background.png"
    )
    val ideaExtMap =
      HashMap.empty[String, String] +
        "mac" ~> ".dmg" +
        "mac/arm" ~> "-aarch64.dmg" +
        "win" ~> ".win.zip" +
        "linux" ~> ".tar.gz" +
        "linux/arm" ~> "-aarch64.tar.gz"

    val vscodeDistroMap = HashMap.empty[Os.Kind.Type, ISZ[ISZ[String]]] +
      Os.Kind.Win ~> ISZ(
        ISZ("bin", ".7zz.ver"),
        ISZ("bin", ".mill.ver"),
        ISZ("bin", "7zz.com"),
        ISZ("bin", "mill.bat"),
        ISZ("bin", "scala"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "win", ".cs.ver"),
        ISZ("bin", "win", ".cvc.ver"),
        ISZ("bin", "win", ".cvc5.ver"),
        ISZ("bin", "win", "cvc.exe"),
        ISZ("bin", "win", "cvc5.exe"),
        ISZ("bin", "win", "cs.exe"),
        ISZ("bin", "win", "java"),
        ISZ("bin", "win", "sireum.exe"),
        ISZ("bin", "win", "vcruntime140.dll"),
        ISZ("bin", "win", "vcruntime140_1.dll"),
        ISZ("bin", "win", "vscodium"),
        ISZ("bin", "win", "z3"),
        ISZ("bin", "install"),
        ISZ("bin", "sireum.bat"),
        ISZ("lib"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties")
      ) +
      Os.Kind.Linux ~> ISZ(
        ISZ("bin", ".7zz.ver"),
        ISZ("bin", ".binfmt"),
        ISZ("bin", ".mill.ver"),
        ISZ("bin", "7zz"),
        ISZ("bin", "mill"),
        ISZ("bin", "scala"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "linux", ".cs.ver"),
        ISZ("bin", "linux", ".cvc.ver"),
        ISZ("bin", "linux", ".cvc5.ver"),
        ISZ("bin", "linux", "cvc"),
        ISZ("bin", "linux", "cvc5"),
        ISZ("bin", "linux", "cs"),
        ISZ("bin", "linux", "java"),
        ISZ("bin", "linux", "sireum"),
        ISZ("bin", "linux", "vscodium"),
        ISZ("bin", "linux", "z3"),
        ISZ("bin", "install"),
        ISZ("bin", "sireum"),
        ISZ("lib"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties")
      ) +
      Os.Kind.LinuxArm ~> ISZ(
        ISZ("bin", ".7zz.ver"),
        ISZ("bin", ".binfmt"),
        ISZ("bin", ".mill.ver"),
        ISZ("bin", "7zz"),
        ISZ("bin", "mill"),
        ISZ("bin", "scala"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "linux", "arm", ".cs.ver"),
        ISZ("bin", "linux", "arm", ".cvc.ver"),
        ISZ("bin", "linux", "arm", ".cvc5.ver"),
        ISZ("bin", "linux", "arm", "cvc"),
        ISZ("bin", "linux", "arm", "cvc5"),
        ISZ("bin", "linux", "arm", "cs"),
        ISZ("bin", "linux", "arm", "java"),
        ISZ("bin", "linux", "arm", "sireum"),
        ISZ("bin", "linux", "arm", "vscodium"),
        ISZ("bin", "linux", "arm", "z3"),
        ISZ("bin", "install"),
        ISZ("bin", "sireum"),
        ISZ("lib"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties")
      ) +
      Os.Kind.Mac ~> ISZ(
        ISZ("bin", ".7zz.ver"),
        ISZ("bin", ".mill.ver"),
        ISZ("bin", "7zz"),
        ISZ("bin", "mill"),
        ISZ("bin", "scala"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "mac", ".cs.ver"),
        ISZ("bin", "mac", ".cvc.ver"),
        ISZ("bin", "mac", ".cvc5.ver"),
        ISZ("bin", "mac", "cvc"),
        ISZ("bin", "mac", "cvc5"),
        ISZ("bin", "mac", "cs"),
        ISZ("bin", "mac", "java"),
        ISZ("bin", "mac", "sireum"),
        ISZ("bin", "mac", "vscodium"),
        ISZ("bin", "mac", "z3"),
        ISZ("bin", "install"),
        ISZ("bin", "sireum"),
        ISZ("lib"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties")
      )

    val distroMap = vscodeDistroMap +
      Os.Kind.Win ~> (vscodeDistroMap.get(Os.Kind.Win).get ++ ISZ(
        ISZ(".settings"),
        ISZ("bin", "win", "idea"),
        ISZ("bin", "slang-run.bat")
      )) +
      Os.Kind.Linux ~> (vscodeDistroMap.get(Os.Kind.Linux).get ++ ISZ(
        ISZ(".settings"),
        ISZ("bin", "linux", "idea"),
        ISZ("bin", "slang-run.sh")
      )) +
      Os.Kind.LinuxArm ~> (vscodeDistroMap.get(Os.Kind.LinuxArm).get ++ ISZ(
        ISZ(".settings"),
        ISZ("bin", "linux", "arm", "idea"),
        ISZ("bin", "slang-run.sh")
      )) +
      Os.Kind.Mac ~> (vscodeDistroMap.get(Os.Kind.Mac).get ++ ISZ(
        ISZ(".settings"),
        ISZ("bin", "mac", "idea"),
        ISZ("bin", "slang-run.sh")
      ))

    val pluginsDir: Os.Path =
      if (kind == Os.Kind.Mac) sireumAppDir / "Contents" / "plugins"
      else ideaDir / "plugins"

    val settingsPluginDir = ideaPlugins(isDev, isUltimate, None())
    settingsPluginDir.mkdirAll()

    val libDir: Os.Path =
      if (kind == Os.Kind.Mac) sireumAppDir / "Contents" / "lib"
      else ideaDir / "lib"

    val fontsDir: Os.Path =
      if (kind == Os.Kind.Mac) sireumAppDir / "Contents" / "jbr" / "Contents" / "Home" / "lib" / "fonts"
      else ideaDir / "jbr" / "lib" / "fonts"

    @pure def devRelVer(key: String): (String, String) = {
      ops.StringOps(versions.get(key).get).split((c: C) => c == ',') match {
        case ISZ(devVer, ver) =>
          return (ops.StringOps(devVer).trim, ops.StringOps(ver).trim)
        case ISZ(ver) =>
          val v = ops.StringOps(ver).trim
          return (v, v)
      }
    }

    val ideaVer: String = {
      val (devVer, ver) = devRelVer("org.sireum.version.idea")
      if (isDev) devVer else ver
    }

    val pluginFilter = (p: Plugin) => if (isUltimate || isServer) T else p.isCommunity
    pluginsCacheDir.mkdirAll()

    def patchIdeaProperties(p: Os.Path): Unit = {
      if (isServer) {
        return
      }
      print(s"Patching $p ... ")
      val content = p.read
      val ult: String = if (isUltimate) "-ult" else ""
      val suffix = s".SireumIVE$ult$devSuffix"
      val sireumHome: String = kind match {
        case Os.Kind.Mac => s"$${idea.home.path}/../../../../.."
        case Os.Kind.LinuxArm => s"$${idea.home.path}/../../../.."
        case _ => s"$${idea.home.path}/../../.."
      }
      val settings = s"$sireumHome/.settings/$suffix"
      val homePrefix: String = if (isIdeaInUserHome) settings else s"$${user.home}/$suffix"
      val lineSep: String = if (kind == Os.Kind.Win) "\r\n" else "\n"
      p.writeOver(s"org.sireum.home=$sireumHome${lineSep}idea.config.path=$homePrefix/config${lineSep}idea.system.path=$homePrefix/system${lineSep}idea.log.path=$homePrefix/log${lineSep}idea.plugins.path=$settings/plugins$lineSep$content")
      println("done!")
    }

    def patchInfoPlist(p: Os.Path): Unit = {
      print(s"Patching $p ... ")
      val contentOps = ops.StringOps(p.read)
      p.writeOver(contentOps.replaceAllLiterally("<key>CFBundleDevelopmentRegion</key>",
        st"""<key>LSEnvironment</key>
            |    <dict>
            |         <key>JAVA_HOME</key>
            |         <string></string>
            |         <key>SIREUM_HOME</key>
            |         <string>${if (isIdeaInUserHome) home.string else ""}</string>
            |    </dict>
            |    <key>CFBundleDevelopmentRegion</key>""".render))
      println("done!")
    }

    def patchVMOptions(p: Os.Path): Unit = {
      print(s"Patching $p ... ")
      val content = ops.StringOps(p.read).trim
      val newContent: String =
        if (kind == Os.Kind.Win) s"$content\r\n-Dorg.sireum.ive=Sireum$devSuffix\r\n-Dorg.sireum.ive.dev=$isDev\r\n-Dfile.encoding=UTF-8\r\n"
        else s"$content\n-Dorg.sireum.ive=Sireum$devSuffix\n-Dorg.sireum.ive.dev=$isDev\n-Dfile.encoding=UTF-8\n"
      p.writeOver(newContent)
      println("done!")
    }

    def patchIcon(): Unit = {
      if (isUltimate) {
        return
      }
      val iconsPath = home / "resources" / "distro" / "icons"
      val (dirPath, srcFilename, filename): (Os.Path, String, String) = kind match {
        case Os.Kind.Mac =>
          if (isDev) {
            (iconsPath / "idea-dev.svg").copyOverTo(sireumAppDir / "Contents" / "bin" / "idea.svg")
            (sireumAppDir / "Contents" / "Resources", "idea-dev.icns", "idea.icns")
          } else {
            (iconsPath / "idea.svg").copyOverTo(sireumAppDir / "Contents" / "bin" / "idea.svg")
            (sireumAppDir / "Contents" / "Resources", "idea.icns", "idea.icns")
          }
        case Os.Kind.Win =>
          if (isDev) {
            (iconsPath / "idea-dev.svg").copyOverTo(ideaDir / "bin" / "idea.svg")
            (ideaDir / "bin", "idea-dev.ico", "idea.ico")
          } else {
            (iconsPath / "idea.svg").copyOverTo(ideaDir / "bin" / "idea.svg")
            (ideaDir / "bin", "idea_CE.ico", "idea.ico")
          }
        case _ =>
          if (isDev) {
            (iconsPath / "idea-dev.svg").copyOverTo(ideaDir / "bin" / "idea.svg")
            (ideaDir / "bin", "idea-dev.png", "idea.png")
          } else {
            (iconsPath / "idea.svg").copyOverTo(ideaDir / "bin" / "idea.svg")
            (ideaDir / "bin", "idea.png", "idea.png")
          }
      }
      print(s"Replacing icon ${dirPath / filename} ... ")
      (iconsPath / srcFilename).copyOverTo(dirPath / filename)
      println("done!")
    }

    def patchApp(): Unit = {
      if (isUltimate) {
        return
      }
      val iconsPath = home / "resources" / "distro" / "icons"
      val appJar = libDir / "app.jar"
      val tempDir = libDir / "app-temp"
      tempDir.removeAll()
      tempDir.mkdirAll()
      print(s"Patching $appJar ... ")
      appJar.unzipTo(tempDir)
      val entriesToUpdate: ISZ[String] =
        for (f <- ISZ[String](
          "Logo_welcomeScreen.png",
          "Logo_welcomeScreen@2x.png",
          "Logo_welcomeScreen_CE.png",
          "Logo_welcomeScreen_CE@2x.png",
          "icon.png",
          "icon@2x.png",
          "icon_128.png",
          "icon_CE.png",
          "icon_CE@2x.png",
          "icon_CE_128.png",
          "icon_CE_128@2x.png",
          "icon_CE_64.png",
          "icon_CE_64@2x.png",
          "icon_CEsmall.png",
          "icon_CEsmall@2x.png",
          "icon_small@2x.png",
          "icon_small@2x_dark.png",
          "icon_small_dark.png",
          "icon_small.png",
          "idea_CE.ico",
          "idea_logo_welcome.png"
        ) if !ignoredIcons.contains(f)) yield f
      for (e <- entriesToUpdate) {
        (iconsPath / e).copyOverTo(tempDir / e)
      }

      val distroDir = home / "resources" / "distro"
      val d = distroDir / "images" / (if (isDev) "dev" else "release")
      for (e <- ISZ("idea_community_about.png", "idea_community_about@2x.png", "idea_community_logo.png", "idea_community_logo@2x.png", "idea-ce.svg", "idea-ce-eap.svg", "idea-ce_16.png", "idea-ce_16@2x.png")) {
        (d / e).copyOverTo(tempDir / e)
      }
      if (isDev) {
        for (e <- ISZ("idea-ce_16.svg", "idea-ce_16@2x.svg", "idea-ce-eap_16.svg", "idea-ce-eap_16@2x.svg")) {
          (iconsPath / "idea-dev.svg").copyOverTo(tempDir / e)
        }
      } else {
        for (e <- ISZ("idea-ce_16.svg", "idea-ce_16@2x.svg", "idea-ce-eap_16.svg", "idea-ce-eap_16@2x.svg")) {
          (iconsPath / "idea.svg").copyOverTo(tempDir / e)
        }
      }
      val appTempJar = libDir / "app-temp.jar"
      tempDir.zipTo(appTempJar)
      appTempJar.moveOverTo(appJar)
      tempDir.removeAll()
      println("done!")
    }

    def installFontsJbr(): Unit = {
      print("Installing Sireum Mono fonts ... ")

      val ttf = home / "resources" / "fonts" / "ttf"
      for (p <- ttf.list if p.ext == "ttf") {
        p.copyOverTo(fontsDir / p.name)
      }

      println("done!")
    }

    def deleteSources(): Unit = {
      for (p <- Os.Path.walk(ideaDir, F, T, (p: Os.Path) => p.ext == "java" || p.ext == "scala")) {
        p.removeAll()
      }
    }

    def deletePlugins(): Unit = {
      if (isServer) {
        return
      }
      for (p <- delPlugins) {
        print(s"Removing $p plugin ... ")
        (pluginsDir / p).removeAll()
        println("done!")
      }
    }

    def setupMac(ideaDrop: Os.Path): Unit = {
      proc"hdiutil attach $ideaDrop".at(home).runCheck()
      var dirPath = Os.home
      for (p <- Os.path("/Volumes").list if ops.StringOps(p.name).startsWith("IntelliJ")) {
        dirPath = p
      }
      var appPath = dirPath
      for (p <- dirPath.list if ops.StringOps(p.name).endsWith(".app")) {
        appPath = p
      }
      sireumAppDir.up.mkdirAll()
      appPath.copyOverTo(sireumAppDir)
      proc"hdiutil eject $dirPath".at(home).runCheck()
      println("done!")
      deleteSources()
      installFontsJbr()
      deletePlugins()
      extractPlugins(settingsPluginDir, pluginFilter)
      patchIcon()
      patchApp()
      patchIdeaProperties(sireumAppDir / "Contents" / "bin" / "idea.properties")
      patchVMOptions(sireumAppDir / "Contents" / "bin" / "idea.vmoptions")
      patchInfoPlist(sireumAppDir / "Contents" / "Info.plist")
      proc"codesign --force --deep --sign - $sireumAppDir".run()
    }

    def setupLinux(ideaDrop: Os.Path): Unit = {
      if (isServer) {
        val ideaVerOps = ops.StringOps(ideaVer)
        val rel: String = ideaVerOps.split((c: C) => c == '.') match {
          case ISZ(_, r, _) => r
          case ISZ(_, r) => r
        }
        val namePart = s"_ideaIU-${ideaVerOps.substring(2, 4)}$rel"
        var dist = Os.home / ".cache" / "JetBrains" / "RemoteDev" / "dist"
        for (p <- dist.list if p.isDir && ops.StringOps(p.name).contains(namePart)) {
          if (dist.name == "dist" || p.lastModified >= dist.lastModified) {
            dist = p
          }
        }
        if (dist.name == "dist") {
          eprintln(s"Could not find IntelliJ Ultimate for remote development in $dist")
          Os.exit(-1)
        }
        ideaDir.mklink(dist)
      } else {
        val ideaDirParent = ideaDir.up.canon
        ideaDrop.unTarGzTo(ideaDirParent)
        for (p <- ideaDirParent.list if ops.StringOps(p.name).startsWith(s"idea-${if (isUltimate) "IU" else "IC"}-")) {
          p.moveOverTo(ideaDir)
        }
      }
      deleteSources()
      if (!isServer) {
        println("done!")
      }
      installFontsJbr()
      deletePlugins()
      extractPlugins(settingsPluginDir, pluginFilter)
      patchIcon()
      patchApp()
      patchIdeaProperties(ideaDir / "bin" / "idea.properties")
      patchVMOptions(ideaDir / "bin" / "idea64.vmoptions")
      (ideaDir / "bin" / "idea.vmoptions").removeAll()
      val ivesh = ideaDir / "bin" / "IVE.sh"
      ivesh.downloadFrom(s"https://github.com/sireum/rolling/releases/download/ive_launcher_win/IVE${if (ideaDir.up.canon.name == "arm") "-arm" else ""}.sh")
      ivesh.chmod("+x")
    }

    def setupWin(ideaDrop: Os.Path): Unit = {
      ideaDir.mkdirAll()
      ideaDrop.unzipTo(ideaDir)
      (ideaDir / "$PLUGINSDIR").removeAll()
      deleteSources()
      println("done!")
      installFontsJbr()
      deletePlugins()
      extractPlugins(settingsPluginDir, pluginFilter)
      patchIcon()
      patchApp()
      patchIdeaProperties(ideaDir / "bin" / "idea.properties")
      patchVMOptions(ideaDir / "bin" / "idea64.exe.vmoptions")
      (ideaDir / "bin" / "idea.exe").removeAll()
      (ideaDir / "bin" / "idea64.exe").removeAll()
      (ideaDir / "bin" / "idea.exe.vmoptions").removeAll()
      (ideaDir / "bin" / "IVE.exe").downloadFrom("https://github.com/sireum/rolling/releases/download/ive_launcher_win/IVE.exe")
      if (buildPackage) {
        (homeBin / "sireum.jar").copyOverTo(ideaDir / "plugins" / "sireum-intellij-plugin" / "lib" / "sireum.jar")
      }
    }

    def pack(): Unit = {
      val plat = ops.StringOps(platform(kind)).replaceAllChars('/', '-')
      print(s"Packaging for $plat ... ")
      val setupDir = home / "distro" / (if (isDev) "dev" else "release")
      if (kind == Os.Kind.Linux || kind == Os.Kind.LinuxArm) {
        binfmt.removeAll()
        binfmt.touch()
      }
      val distroDir: Os.Path = {
        val dir = setupDir / s"Sireum$devSuffix"
        for (rp <- distroMap.get(kind).get if rp(0) != "..") {
          (dir /+ rp).up.mkdirAll()
          val orp = home /+ rp
          if (orp.exists) {
            orp.copyOverTo(dir /+ rp)
          } else {
            println()
            println(s"Warning: Could not find $orp")
          }
        }
        dir
      }
      binfmt.removeAll()
      val files: ISZ[String] =
        for (p <- distroMap.get(kind).get.map((rp: ISZ[String]) => Os.path(distroDir.name) /+ rp)) yield p.string

      val libCache = homeLib / "cache"
      val tmp = Os.tempDir()
      tmp.removeAll()
      if (libCache.exists) {
        libCache.moveTo(tmp)
      }
      if (Os.isWin) {
        val pkg = s"$plat.7z"
        val p7zz = install7zz()
        Os.proc(ISZ[String](p7zz.string, "a", pkg) ++ files).at(distroDir.up).runCheck()
        (distroDir.up / pkg).moveOverTo(setupDir.up / pkg)
      } else {
        val pkg = s"$plat.tar.xz"
        Os.proc(ISZ[String]("tar", "-c", "-J", "-f", pkg) ++ files).at(distroDir.up).runCheck()
        (distroDir.up / pkg).moveOverTo(setupDir.up / pkg)
      }
      if (tmp.exists) {
        tmp.moveTo(libCache)
      }
      println("done!")
      distroDir.removeAll()
    }

    def ive(): Unit = {
      println(s"Setting up Sireum$devSuffix IVE $kind in $ideaDir ...")
      val suffix: String =
        if (Os.isMacArm) ideaExtMap.get("mac/arm").get
        else if (kind == Os.Kind.Win && Os.env("PROCESSOR_ARCHITECTURE") == Some("ARM64")) "-aarch64.exe"
        else ideaExtMap.get(platform(kind)).get
      val url: String = s"https://download.jetbrains.com/idea/idea${if (isUltimate) "IU" else "IC"}-$ideaVer$suffix"
      val urlOps = ops.StringOps(url)
      val filename = urlOps.substring(urlOps.lastIndexOf('/') + 1, url.size)
      val buildDir = ideaDir.up.canon
      buildDir.mkdirAll()
      val resources = home / "resources"
      val noResources = !resources.exists
      if (noResources) {
        val sha = GitHub.repo("sireum", "kekinian").submoduleShaOf("resources", sireumV)
        val drop = cache / s"resources-$sha.zip"
        if (!drop.exists) {
          println("Please wait while downloading resources ...")
          drop.downloadFrom(s"https://github.com/sireum/resources/archive/$sha.zip")
          println()
        }
        resources.mkdirAll()
        drop.unzipTo(home)
        (home / s"resources-$sha").moveOverTo(resources)
      }
      val ideaDrop = ideaCacheDir / filename
      if (!ideaDrop.exists && !isServer) {
        print(s"Downloading from $url ... ")
        (ideaCacheDir / filename).downloadFrom(url)
        println("done!")
      }
      downloadPlugins(isDev, pluginFilter)
      if (!isServer) {
        print(s"Extracting $ideaDrop ... ")
      }
      ideaDir.removeAll()
      kind match {
        case Os.Kind.Mac => setupMac(ideaDrop)
        case Os.Kind.Win => setupWin(ideaDrop)
        case _ => setupLinux(ideaDrop)
      }
      val sireumJar = settingsPluginDir / "sireum-intellij-plugin" / "lib" / "sireum.jar"
      val homeBinSireumJar = homeBin / "sireum.jar"
      sireumJar.removeAll()
      println()
      if (buildPackage) {
        homeBinSireumJar.copyTo(sireumJar)
        pack()
      } else {
        sireumJar.mklink(homeBinSireumJar)
      }
      if (noResources) {
        resources.removeAll()
      }
      println("Done!")
    }

    def vscode(): Unit = {
      val sireumName = home.name
      val files: ISZ[String] =
        for (p <- vscodeDistroMap.get(kind).get.map((rp: ISZ[String]) => st"${(sireumName +: rp, Os.fileSep)}")) yield p.render
      val vscodeName = "codeive"
      var rname: String = kind match {
        case Os.Kind.Mac => s"$sireumName-$vscodeName-mac-${if (Os.isMacArm) "arm64" else "amd64"}.tar.xz"
        case Os.Kind.Win => s"$sireumName-$vscodeName-win-${if (Os.isWinArm) "arm64" else "amd64"}.7z"
        case _ => s"$sireumName-$vscodeName-linux-${if (kind == Os.Kind.LinuxArm) "arm64" else "amd64"}.tar.xz"
      }
      rname = ops.StringOps(rname).toLower
      (home.up.canon / rname).removeAll()
      val libCache = homeLib / "cache"
      val tmp = Os.tempDir()
      tmp.removeAll()
      if (libCache.exists) {
        libCache.moveTo(tmp)
      }
      if (Os.isWin) {
        val p7zz = install7zz()
        Os.proc(ISZ[String](p7zz.string, "a", rname) ++ files).at(home.up.canon).runCheck()
      } else {
        if (kind == Os.Kind.Linux || kind == Os.Kind.LinuxArm) {
          binfmt.removeAll()
          binfmt.touch()
        }
        Os.proc(ISZ[String]("tar", "-c", "-J", "-f", rname) ++ files).at(home.up.canon).runCheck()
        binfmt.removeAll()
      }
      if (tmp.exists) {
        tmp.moveTo(libCache)
      }
      (home.up.canon / rname).moveOverTo(home / "distro" / rname)
    }

    if (kind == Os.Kind.Win) {
      val slangRunScript = homeBin / "slang-run.bat"
      if (!slangRunScript.exists) {
        slangRunScript.downloadFrom(s"https://raw.githubusercontent.com/sireum/kekinian/$sireumV/bin/slang-run.bat")
      }
    } else {
      val slangRunScript = homeBin / "slang-run.sh"
      if (!slangRunScript.exists) {
        slangRunScript.downloadFrom(s"https://raw.githubusercontent.com/sireum/kekinian/$sireumV/bin/slang-run.sh")
        slangRunScript.chmod("+x")
      }
    }

    if (kind == Os.Kind.Win && (buildPackage || buildVSCodePackage)) {
      val vcr = homeBinPlatform / "vcruntime140.dll"
      val vcr1 = homeBinPlatform / "vcruntime140_1.dll"
      vcr.removeAll()
      vcr1.removeAll()
      val system32 = Os.path("C:\\Windows\\System32")
      (system32 / vcr.name).copyOverTo(vcr)
      (system32 / vcr1.name).copyOverTo(vcr1)
    }

    if (buildVSCodePackage) {
      vscode()
    }
    if (buildIve) {
      ive()
    }
  }


  def basicDeps(): Unit = {
    if (!init(F)) {
      return
    }
    installScala()
    installScalacPlugin()
    if (kind == Os.Kind.Win) {
      val sireumScript = homeBin / "sireum.bat"
      if (!sireumScript.exists) {
        sireumScript.downloadFrom(s"https://raw.githubusercontent.com/sireum/kekinian/$sireumV/bin/sireum.bat")
      }
    } else {
      val sireumScript = homeBin / "sireum"
      if (!sireumScript.exists) {
        sireumScript.downloadFrom(s"https://raw.githubusercontent.com/sireum/kekinian/$sireumV/bin/sireum")
        sireumScript.chmod("+x")
      }
    }
    install7zz()
  }

  def proyekCompileDeps(): Unit = {
    installCoursier()
  }

  def logikaDeps(): Unit = {
    installZ3()
    installCVC()
  }

  def deps(): Unit = {
    basicDeps()
    proyekCompileDeps()
    logikaDeps()
    installJacoco()
    installMaryTTS()
    installCheckStack()
    installScripts()
  }

  def init(setup: B): B = {
    if (!home.exists) {
      home.mkdirAll()
    }

    val sireumInitV: String = Os.env("SIREUM_INIT_V") match {
      case Some(v) => v
      case _ => if (sireumV == "master") "dev" else sireumV
    }

    val sireumJar = home / "bin" / "sireum.jar"
    if (!sireumJar.exists) {
      println("Please wait while downloading Sireum ...")
      sireumJar.downloadFrom(s"https://github.com/sireum/kekinian/releases/download/$sireumInitV/sireum.jar")
      println()
    }

    val sireum = home / "bin" / (if (Os.isWin) "sireum.bat" else "sireum")
    if (!sireum.exists) {
      sireum.downloadFrom(s"https://raw.githubusercontent.com/sireum/kekinian/$sireumV/bin/${sireum.name}")
    }

    val versionsPath = home / "versions.properties"
    if (!versionsPath.exists) {
      versionsPath.downloadFrom(s"https://raw.githubusercontent.com/sireum/kekinian/$sireumV/versions.properties")
    }

    val licensePath = home / "license.txt"
    if (!licensePath.exists) {
      licensePath.downloadFrom(s"https://raw.githubusercontent.com/sireum/kekinian/$sireumV/license.txt")
    }

    val readme = home / "readme.md"
    if (!readme.exists) {
      readme.downloadFrom(s"https://raw.githubusercontent.com/sireum/kekinian/$sireumV/readme.md")
    }

    val vs: Map[String, String] = if (versions.isEmpty) versionsPath.properties else versions
    if (!installJava(vs, F, F)) {
      eprintln("Unsupported platform")
      return F
    }

    if (setup && Os.env("SIREUM_NO_SETUP") != Some("true")) {
      distro(isDev = F, buildPackage = F, buildIve = F, buildVSCodePackage = F, isUltimate = F, isServer = F)
    }
    return T
  }

}
