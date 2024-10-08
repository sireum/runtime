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

  @memoize def pwd7z: Os.Path = {
    return homeBin / platform(Os.kind) / (if (kind == Os.Kind.Win) "7za.exe" else "7za")
  }

  def installJava(vs: Map[String, String]): B = {
    homeBinPlatform.mkdirAll()

    if (Os.env("SIREUM_PROVIDED_JAVA") != Some("true")) {
      val javaHome = Os.javaHomeOpt(kind, Some(home)).get
      val javaVersion = vs.get("org.sireum.version.java").get
      val nikVersion = vs.get("org.sireum.version.nik").get
      val nikJavaVersion = ops.StringOps(s"$nikVersion-$javaVersion").replaceAllLiterally("+", "%2B")
      var isNik = T
      var javaUrl: String = ""
      kind match {
        case Os.Kind.LinuxArm =>
          isNik = F
          javaUrl = s"https://download.bell-sw.com/java/$javaVersion/bellsoft-jdk$javaVersion-linux-aarch64-full.tar.gz"
        case Os.Kind.Linux =>
          javaUrl = s"https://github.com/bell-sw/LibericaNIK/releases/download/$nikJavaVersion/bellsoft-liberica-vm-full-openjdk$javaVersion-$nikVersion-linux-amd64.tar.gz"
        case Os.Kind.Win =>
          if (Os.isWinArm) {
            isNik = F
            javaUrl = s"https://download.bell-sw.com/java/$javaVersion/bellsoft-jdk$nikVersion-windows-aarch64-full.zip"
          } else {
            javaUrl = s"https://github.com/bell-sw/LibericaNIK/releases/download/$nikJavaVersion/bellsoft-liberica-vm-full-openjdk$javaVersion-$nikVersion-windows-amd64.zip"
          }
        case Os.Kind.Mac =>
          if (Os.isMacArm) {
            javaUrl = s"https://github.com/bell-sw/LibericaNIK/releases/download/$nikJavaVersion/bellsoft-liberica-vm-full-openjdk$javaVersion-$nikVersion-macos-aarch64.tar.gz"
          } else {
            javaUrl = s"https://github.com/bell-sw/LibericaNIK/releases/download/$nikJavaVersion/bellsoft-liberica-vm-full-openjdk$javaVersion-$nikVersion-macos-amd64.tar.gz"
          }
        case _ =>
          return F
      }

      val VER: String = if (isNik) s"$javaVersion-$nikVersion" else javaVersion
      val javaVer = javaHome / "VER"

      def diffVersion: B = {
        val content = ops.StringOps(javaVer.read).trim
        return content != VER && content != javaVersion
      }

      if (!javaVer.exists || diffVersion) {
        val drop = cache / ops.StringOps(javaUrl).substring(ops.StringOps(javaUrl).lastIndexOf('/') + 1, javaUrl.size)
        if (!drop.exists) {
          println(s"Please wait while downloading ${if (isNik) s"Liberica Native Image Kit JDK Full $javaVersion-$nikVersion" else s"Liberica JDK Full $javaVersion"} ...")
          drop.downloadFrom(javaUrl)
          println()
        }

        println(s"Extracting JDK $VER ...")
        val d = Os.tempDir()
        if (Os.isWin) {
          drop.unzipTo(d)
        } else {
          drop.unTarGzTo(d)
        }
        javaHome.removeAll()
        if (Os.isMac) {
          (d.list(0) / "Contents" / "Home").moveTo(javaHome)
        } else {
          d.list(0).moveTo(javaHome)
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
      case Os.Kind.Win => homeBinPlatform / "cs.exe.ver"
      case Os.Kind.Unsupported => homeLib / "coursier.jar.ver"
      case _ => homeBinPlatform / "cs.ver"
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

  def pwd7zUrl: String = {
    Os.kind match {
      case Os.Kind.Win =>
        val sha = GitHub.repo("sireum", "kekinian").submoduleShaOf("bin/win", sireumV)
        return s"https://github.com/sireum/bin-windows/raw/$sha/7za.exe"
      case Os.Kind.Mac =>
        val sha = GitHub.repo("sireum", "kekinian").submoduleShaOf("bin/mac", sireumV)
        return s"https://github.com/sireum/bin-mac/raw/$sha/7za"
      case Os.Kind.Linux =>
        val sha = GitHub.repo("sireum", "kekinian").submoduleShaOf("bin/linux", sireumV)
        return s"https://github.com/sireum/bin-linux/raw/$sha/7za"
      case Os.Kind.LinuxArm =>
        val sha = GitHub.repo("sireum", "kekinian").submoduleShaOf("bin/linux", sireumV)
        return s"https://github.com/sireum/bin-linux/raw/$sha/arm/7za"
      case _ => halt("Infeasible")
    }
  }

  def install7z(): Unit = {
    if (!pwd7z.exists) {
      pwd7z.up.mkdirAll()
      println(s"Please wait while downloading 7z ...")
      pwd7z.downloadFrom(pwd7zUrl)
      pwd7z.chmod("+x")
      println()
    }
    if (kind == Os.Kind.Win && Os.env("PROCESSOR_ARCHITECTURE") == Some("ARM64")) {
      val d = homeBin / "win" / "7z"
      if (!d.exists) {
        val bundle = d.up.canon / "7z-win-arm64.zip"
        val url = s"https://github.com/sireum/rolling/releases/download/7z/${bundle.name}"
        bundle.removeAll()
        bundle.downloadFrom(url)
        bundle.unzipTo(d)
        bundle.removeAll()
      }
    }
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
        else s"z3-$version-win-amd64.zip"
      case Os.Kind.Mac =>
        if (Os.isMacArm) s"z3-$version-mac-arm64.zip"
        else s"z3-$version-mac-amd64.zip"
      case Os.Kind.Linux => s"z3-$version-linux-amd64.zip"
      case Os.Kind.LinuxArm => s"z3-$version-linux-arm64.zip"
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
    bundle.unzipTo(dir.up)

    for (p <- dir.up.list if ops.StringOps(p.name).startsWith("z3-")) {
      dir.removeAll()
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
    val ver = home / "bin" / "mill.ver"
    if (ver.exists && ver.read == millVersion) {
      return
    }
    val url = s"https://github.com/com-lihaoyi/mill/releases/download/$millVersion/$millVersion-assembly"
    val mill: Os.Path = (home / "bin" / (if (Os.isWin) "mill.bat" else "mill")).canon
    if (verbose) {
      println(s"Downloading mill $millVersion ...")
    }
    mill.downloadFrom(url)
    if (verbose) {
      println()
      println(s"Patching $mill ...")
    }
    patchMill(mill)
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

  def installVSCodium(existingInstallOpt: Option[Os.Path], extensionsDirOpt: Option[Os.Path], extensions: ISZ[String]): Unit = {
    val isInUserHome = ops.StringOps(s"${homeBin.up.canon}${Os.fileSep}").startsWith(Os.home.string)
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
        tmlf.writeOver(content)
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
        f.writeOver(patchPrefix(patchPrefix(f.read, "comment("), "textualRep("))
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
      val extDirArg: String = if (isInUserHome) "" else s" --extensions-dir $extensionsDir"
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
      for (f <- extensionsDir.list if ops.StringOps(f.name).startsWith("sensmetry.sysml-")) {
        patchSysIDE(f)
      }
      return extDirArg
    }

    def patchCodium(codium: Os.Path, anchor: String, sireumHome: String, isWin: B): Unit = {
      var codiumContent = codium.read
      val cis = conversions.String.toCis(codiumContent)
      if (ops.StringOps.stringIndexOfFrom(cis, conversions.String.toCis("SIREUM_HOME"), 0) >= 0) {
        return
      }
      println(s"Patching $codium ...")
      val i = ops.StringOps.stringIndexOfFrom(cis, conversions.String.toCis(anchor), 0)
      val set: String = if (isWin) "set" else "export"
      val javaHomeOpt: String = Os.kind match {
        case Os.Kind.Mac => s"$set JAVA_HOME=$$SIREUM_HOME/bin/mac/java${Os.lineSep}"
        case Os.Kind.Linux => s"$set JAVA_HOME=$$SIREUM_HOME/bin/linux/java${Os.lineSep}"
        case Os.Kind.LinuxArm => s"$set JAVA_HOME=$$SIREUM_HOME/bin/linux/arm/java${Os.lineSep}"
        case Os.Kind.Win => s"$set JAVA_HOME=%SIREUM_HOME%\\bin\\win\\java${Os.lineSep}"
        case _ => ""
      }
      codiumContent = s"${ops.StringOps.substring(cis, 0, i)}$set SIREUM_HOME=$sireumHome${Os.lineSep}$javaHomeOpt${ops.StringOps.substring(cis, i, cis.size)}"
      codium.writeOver(codiumContent)
      if (!isWin) {
        codium.chmod("+x")
      }
      println()
    }

    def mac(): Unit = {
      val drop = cache / s"VSCodium-darwin-${if (Os.isMacArm) "arm64" else "x64"}-$vscodiumVersion.zip"
      val platform = homeBin / "mac"
      var vscodium = platform / "VSCodium.app"
      val ver = vscodium / "Contents" / "VER"
      var updated = F
      val codium: Os.Path = vsCodiumOpt match {
        case Some(p) =>
          vscodium = p.up.up.up.up.up.canon
          p
        case _ =>
          val c = vscodium / "Contents"/ "Resources" / "app" / "bin" / "codium"
          if (!ver.exists || ver.read != vscodiumVersion) {
            downloadVSCodium(drop)
            vscodium.removeAll()
            println("Extracting VSCodium ...")
            drop.unzipTo(platform)
            ver.write(vscodiumVersion)
            println()
            updated = T
          }
          c
      }
      patchCodium(codium, "ELECTRON_RUN_AS_NODE=", "$(readlink -f `dirname $0`/../../../../../../..)", F)
      proc"xattr -rd com.apple.quarantine $vscodium".run()
      proc"codesign --force --deep --sign - $vscodium".run()
      val extensionsDir: Os.Path = extDirOpt match {
        case Some(ed) => ed
        case _ =>
          if (Os.env("GITHUB_ACTION").nonEmpty || isInUserHome) {
            val d = platform / "codium-portable-data" / "extensions"
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
        if (isInUserHome) {
          println(s"To launch VSCodium: open $vscodium")
        } else {
          println(s"To launch VSCodium: $codium$extDirArg")
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
          val c = vscodium / "bin" / "codium"
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
            ver.write(vscodiumVersion)
            println()
            updated = T
          }
          c
      }
      patchCodium(codium, "ELECTRON_RUN_AS_NODE=",
        s"$$(readlink -f `dirname $$0`/../../../..${if (isArm) "/.." else ""})", F)
      val extensionsDir: Os.Path = extDirOpt match {
        case Some(ed) => ed
        case _ =>
          if (Os.env("GITHUB_ACTION").nonEmpty || isInUserHome) {
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
        println(s"To launch VSCodium: $codium$extDirArg")
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
          val c =  vscodium / "bin" / "codium.cmd"
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
            ver.write(vscodiumVersion)
            println()
            updated = T
          }
          c
      }
      patchCodium(codium, "\"%~dp0..",
        s""""%~dp0..\\..\\..\\..${Os.lineSep}pushd %SIREUM_HOME%${Os.lineSep}set SIREUM_HOME=%CD%${Os.lineSep}popd""".stripMargin, T)
      val extensionsDir: Os.Path = extDirOpt match {
        case Some(ed) => ed
        case _ =>
          if (Os.env("GITHUB_ACTION").nonEmpty || isInUserHome) {
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
        println(s"To launch VSCodium: $codium$extDirArg")
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

  def distro(isDev: B, buildSfx: B, buildIve: B, buildHamrPackage: B, isUltimate: B, isServer: B): Unit = {
    assert(buildIve | buildHamrPackage)
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

    val hamrDistroMap = HashMap.empty[Os.Kind.Type, ISZ[ISZ[String]]] +
      Os.Kind.Win ~> ISZ(
        ISZ("bin", "win", "7za.exe"),
        ISZ("bin", "win", "cvc.exe"),
        ISZ("bin", "win", "cvc5.exe"),
        ISZ("bin", "win", "sireum.exe"),
        ISZ("bin", "win", "vscodium"),
        ISZ("bin", "win", "z3"),
        ISZ("bin", "install", "antlrworks.cmd"),
        ISZ("bin", "install", "brave.cmd"),
        ISZ("bin", "install", "clion.cmd"),
        ISZ("bin", "install", "fmide.cmd"),
        ISZ("bin", "install", "graal.cmd"),
        ISZ("bin", "install", "graal-openjdk.cmd"),
        ISZ("bin", "install", "isabelle.cmd"),
        ISZ("bin", "install", "rust.cmd"),
        ISZ("bin", "install", "rustrover.cmd"),
        ISZ("bin", "sireum.bat"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties")
      ) +
      Os.Kind.Linux ~> ISZ(
        ISZ("bin", "linux", "7za"),
        ISZ("bin", "linux", "cvc"),
        ISZ("bin", "linux", "cvc5"),
        ISZ("bin", "linux", "sireum"),
        ISZ("bin", "linux", "vscodium"),
        ISZ("bin", "linux", "z3"),
        ISZ("bin", "install", "acl2.cmd"),
        ISZ("bin", "install", "alt-ergo.cmd"),
        ISZ("bin", "install", "antlrworks.cmd"),
        ISZ("bin", "install", "brave.cmd"),
        ISZ("bin", "install", "clion.cmd"),
        ISZ("bin", "install", "compcert.cmd"),
        ISZ("bin", "install", "coq.cmd"),
        ISZ("bin", "install", "fmide.cmd"),
        ISZ("bin", "install", "graal.cmd"),
        ISZ("bin", "install", "graal-openjdk.cmd"),
        ISZ("bin", "install", "isabelle.cmd"),
        ISZ("bin", "install", "menhir.cmd"),
        ISZ("bin", "install", "opam.cmd"),
        ISZ("bin", "install", "rust.cmd"),
        ISZ("bin", "install", "rustrover.cmd"),
        ISZ("bin", "sireum"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties")
      ) +
      Os.Kind.LinuxArm ~> ISZ(
        ISZ("bin", "linux", "arm", "7za"),
        ISZ("bin", "linux", "arm", "cvc"),
        ISZ("bin", "linux", "arm", "cvc5"),
        ISZ("bin", "linux", "arm", "sireum"),
        ISZ("bin", "linux", "arm", "vscodium"),
        ISZ("bin", "linux", "arm", "z3"),
        ISZ("bin", "install", "antlrworks.cmd"),
        ISZ("bin", "install", "brave.cmd"),
        ISZ("bin", "install", "clion.cmd"),
        ISZ("bin", "install", "graal.cmd"),
        ISZ("bin", "install", "graal-openjdk.cmd"),
        ISZ("bin", "install", "isabelle.cmd"),
        ISZ("bin", "install", "rust.cmd"),
        ISZ("bin", "install", "rustrover.cmd"),
        ISZ("bin", "sireum"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties")
      ) +
      Os.Kind.Mac ~> ISZ(
        ISZ("bin", "mac", "7za"),
        ISZ("bin", "mac", "codium-portable-data"),
        ISZ("bin", "mac", "cvc"),
        ISZ("bin", "mac", "cvc5"),
        ISZ("bin", "mac", "sireum"),
        ISZ("bin", "mac", "VSCodium.app"),
        ISZ("bin", "mac", "z3"),
        ISZ("bin", "install", "alt-ergo.cmd"),
        ISZ("bin", "install", "antlrworks.cmd"),
        ISZ("bin", "install", "brave.cmd"),
        ISZ("bin", "install", "clion.cmd"),
        ISZ("bin", "install", "compcert.cmd"),
        ISZ("bin", "install", "coq.cmd"),
        ISZ("bin", "install", "fmide.cmd"),
        ISZ("bin", "install", "graal.cmd"),
        ISZ("bin", "install", "graal-openjdk.cmd"),
        ISZ("bin", "install", "isabelle.cmd"),
        ISZ("bin", "install", "menhir.cmd"),
        ISZ("bin", "install", "opam.cmd"),
        ISZ("bin", "install", "rust.cmd"),
        ISZ("bin", "install", "rustrover.cmd"),
        ISZ("bin", "sireum"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties")
      )

    val distroMap = hamrDistroMap +
      Os.Kind.Win ~> (hamrDistroMap.get(Os.Kind.Win).get ++ ISZ(
        ISZ(".settings"),
        ISZ("bin", "scala"),
        ISZ("bin", "win", "cs.exe"),
        ISZ("bin", "win", "idea"),
        ISZ("bin", "win", "java"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "slang-run.bat"),
        ISZ("lib"),
        ISZ("..", "setup.bat")
      )) +
      Os.Kind.Linux ~> (hamrDistroMap.get(Os.Kind.Linux).get ++ ISZ(
        ISZ(".settings"),
        ISZ("bin", "scala"),
        ISZ("bin", "linux", "cs"),
        ISZ("bin", "linux", "idea"),
        ISZ("bin", "linux", "java"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "slang-run.sh"),
        ISZ("lib"),
        ISZ("..", "setup")
      )) +
      Os.Kind.LinuxArm ~> (hamrDistroMap.get(Os.Kind.LinuxArm).get ++ ISZ(
        ISZ(".settings"),
        ISZ("bin", "scala"),
        ISZ("bin", "linux", "arm", "cs"),
        ISZ("bin", "linux", "arm", "idea"),
        ISZ("bin", "linux", "arm", "java"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "slang-run.sh"),
        ISZ("lib"),
        ISZ("..", "setup")
      )) +
      Os.Kind.Mac ~> (hamrDistroMap.get(Os.Kind.Mac).get ++ ISZ(
        ISZ(".settings"),
        ISZ("bin", "scala"),
        ISZ("bin", "mac", "cs"),
        ISZ("bin", "mac", "idea"),
        ISZ("bin", "mac", "java"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "slang-run.sh"),
        ISZ("lib"),
        ISZ("..", "setup")
      ))

    val pluginsDir: Os.Path =
      if (kind == Os.Kind.Mac) sireumAppDir / "Contents" / "plugins"
      else ideaDir / "plugins"

    val settingsPluginDir = ideaPlugins(isDev, isUltimate, None())

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

    val pwd7zsfx = pwd7z.up / "7z.sfx"

    if (buildSfx && !pwd7zsfx.exists) {
      println(s"Please wait while downloading ${pwd7zsfx.name} ...")

      def downloadSfx(kind: Os.Kind.Type): Unit = {
        val baseUrl = "https://github.com/sireum/rolling/releases/download/7z.sfx"
        val (f, url): (Os.Path, String) = kind match {
          case Os.Kind.Mac => (homeBin / "mac" / pwd7zsfx.name, s"$baseUrl/7z-mac-${if (Os.isMacArm) "arm" else "amd"}64.sfx")
          case Os.Kind.Linux => (homeBin / "linux" / pwd7zsfx.name, s"$baseUrl/7z-linux-amd64.sfx")
          case Os.Kind.LinuxArm => (homeBin / "linux" / "arm" / pwd7zsfx.name, s"$baseUrl/7z-linux-arm64.sfx")
          case Os.Kind.Win => (homeBin / "win" / pwd7zsfx.name, s"$baseUrl/7z-win-${if (kind == Os.Kind.Win && Os.env("PROCESSOR_ARCHITECTURE") == Some("ARM64")) "arm" else "amd"}64.sfx")
          case _ => halt("Infeasible")
        }
        f.downloadFrom(url)
        f.chmod("+x")
      }

      for (kind <- Os.Kind.elements if kind != Os.Kind.Unsupported) {
        downloadSfx(kind)
      }
      println()
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
      if (kind == Os.Kind.Win && Os.env("PROCESSOR_ARCHITECTURE") == Some("ARM64")) {
        proc"${homeBin / "win" / "7z" / "7z.exe"} x $ideaDrop".at(ideaDir).runCheck()
      } else {
        ideaDrop.unzipTo(ideaDir)
      }
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
      if (buildSfx) {
        (homeBin / "sireum.jar").copyOverTo(ideaDir / "plugins" / "sireum-intellij-plugin" / "lib" / "sireum.jar")
      }
    }

    def pack(): Unit = {
      val plat = ops.StringOps(platform(kind)).replaceAllChars('/', '-')
      val sfxSuffix: String = if (kind == Os.Kind.Win) ".exe" else ".sfx"
      val r = home / "distro" / s"$plat$devSuffix$sfxSuffix"
      r.removeAll()
      print(s"Packaging $r ... ")
      val distro7z = s"$plat.7z"
      val setupDir = home / "distro" / (if (isDev) "dev" else "release")
      val oldPwd = home
      val (repoDir, distroDir): (Os.Path, Os.Path) = {
        val dir = setupDir / s"Sireum$devSuffix"
        for (rp <- distroMap.get(kind).get if rp(0) != "..") {
          (dir /+ rp).up.mkdirAll()
          val orp = oldPwd /+ rp
          if (orp.exists) {
            orp.copyOverTo(dir /+ rp)
          } else {
            println()
            println(s"Warning: Could not find $orp")
          }
        }
        (oldPwd, dir)
      }
      val sfx = repoDir / "distro" / s"$plat$devSuffix$sfxSuffix"
      val files: ISZ[String] =
        for (p <- distroMap.get(kind).get.map((rp: ISZ[String]) => Os.path(distroDir.name) /+ rp)) yield p.string
      val cmd = ISZ[String](pwd7z.string, "a", "-mx9", "-mmt4", distro7z) ++ files

      Os.proc(cmd).at(distroDir.up).runCheck()
      kind match {
        case Os.Kind.Win =>
          sfx.mergeFrom(ISZ(repoDir / "bin" / "win" / "7z.sfx", distroDir.up / "config.txt", distroDir.up / distro7z))
        case _ =>
          sfx.mergeFrom(ISZ(repoDir / "bin" / platform(kind) / "7z.sfx", distroDir.up / distro7z))
      }
      (distroDir.up / distro7z).removeAll()
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
      if (buildSfx) {
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

    def hamr(): Unit = {
      val sireumName = home.name
      val files: ISZ[String] =
        for (p <- hamrDistroMap.get(kind).get.map((rp: ISZ[String]) => st"${(sireumName +: rp, Os.fileSep)}")) yield p.render
      val hamrName = "hamr-sysmlv2"
      var rname: String = kind match {
        case Os.Kind.Mac => if (Os.isMacArm) s"$sireumName-$hamrName-mac-arm64.tar" else s"$sireumName-$hamrName-mac-amd64.tar"
        case Os.Kind.Win => if (Os.isWinArm) s"$sireumName-$hamrName-win-arm64.zip" else s"$sireumName-$hamrName-win-amd64.zip"
        case Os.Kind.Linux => s"$sireumName-$hamrName-linux-amd64.tar"
        case Os.Kind.LinuxArm => s"$sireumName-$hamrName-linux-arm64.tar"
        case _ => halt("Infeasible")
      }
      rname = ops.StringOps(rname).toLower
      (home.up.canon / rname).removeAll()
      kind match {
        case Os.Kind.Win =>
          Os.proc(ISZ[String](pwd7z.string, "a", "-tzip", "-mx9", "-mmt4", rname) ++ files).at(home.up.canon).runCheck()
        case _ =>
          val rnameGz = s"$rname.gz"
          (home.up.canon / rnameGz).removeAll()
          Os.proc(ISZ[String]("tar", "cf", rname) ++ files).at(home.up.canon).runCheck()
          Os.proc(ISZ[String]("gzip", "-9", rname)).at(home.up.canon).runCheck()
          rname = rnameGz
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

    if (buildHamrPackage) {
      hamr()
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
    install7z()
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
    if (!installJava(vs)) {
      eprintln("Unsupported platform")
      return F
    }

    if (setup && Os.env("SIREUM_NO_SETUP") != Some("true")) {
      distro(isDev = F, buildSfx = F, buildIve = F, buildHamrPackage = F, isUltimate = F, isServer = F)
    }
    return T
  }

}
