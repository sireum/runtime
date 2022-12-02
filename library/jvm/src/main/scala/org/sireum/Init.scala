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


object Init {
  @datatype class Plugin(val id: String,
                         val isDev: B,
                         val isJar: B,
                         val devVer: String,
                         val ver: String) {
    @strictpure def version(isDev: B): String =
      if (isDev) devVer else ver
  }
}

import Init._

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

    val releaseName = s"z3-$version"
    var filename: String = kind match {
      case Os.Kind.Win => s"z3-$version-x64-win.zip"
      case Os.Kind.Linux => s"z3-$version-x64-glibc-2.31.zip"
      case Os.Kind.Mac => if (Os.isMacArm) s"z3-$version-arm64-osx-11.0.zip" else s"z3-$version-x64-osx-10.16.zip"
      case _ => return
    }
    var url: String = s"https://github.com/Z3Prover/z3/releases/download/z3-$version/$filename"

    def updateFilenameUrl(): Unit = {
      GitHub.repo("Z3Prover", "z3").releases.
        find((r: GitHub.Release) => r.name == releaseName) match {
        case Some(r) =>
          val desc: String = kind match {
            case Os.Kind.Win => "x64-win"
            case Os.Kind.Linux => "x64-glibc"
            case Os.Kind.Mac => if (Os.isMacArm) "arm64-osx" else "x64-osx"
            case _ => halt("Infeasible")
          }
          r.assets.find((asset: GitHub.Asset) => ops.StringOps(asset.name).endsWith(".zip") &&
            ops.StringOps(asset.name).contains(desc)) match {
            case Some(asset) =>
              url = asset.url
              filename = asset.name
            case _ => halt(s"Could not find Z3 v$version for $kind")
          }
        case _ => halt(s"Could not find Z3 v$version")
      }
    }

    var bundle = cache / filename

    def tryDownload(): B = {
      if (!bundle.exists) {
        println(s"Please wait while downloading Z3 $version ...")
        bundle.up.mkdirAll()
        val r = bundle.downloadFrom(url)
        println()
        return r
      }
      return T
    }

    if (!tryDownload()) {
      updateFilenameUrl()
      bundle = cache / filename
      tryDownload()
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

      val (sub, filename, dropname): (String, String, String) = (gen, kind) match {
        case (string"5", Os.Kind.Win) => (s"cvc$gen-$version", s"cvc$gen-Win64.exe", s"cvc$gen-$version-Win64.exe")
        case (string"5", Os.Kind.Linux) => (s"cvc$gen-$version", s"cvc$gen-Linux", s"cvc$gen-$version-Linux")
        case (string"5", Os.Kind.Mac) =>
          if (Os.isMacArm) (s"cvc$gen-$version", s"cvc$gen-macOS-arm64", s"cvc$gen-$version-macOS-arm64")
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

  @strictpure def ideaDirPath(isUltimate: B, isServer: B): Os.Path =
    homeBinPlatform / (if (isServer) "idea-server" else if (isUltimate) "idea-ultimate" else "idea")

  def distro(isDev: B, buildSfx: B, isUltimate: B, isServer: B): Unit = {
    deps()
    val devSuffix: String = if (isDev) "-dev" else ""
    if (isServer && Os.kind != Os.Kind.Linux) {
      eprintln(s"Server setup is only available in Linux")
      Os.exit(-1)
    }
    val ideaDir: Os.Path = ideaDirPath(isUltimate, isServer)
    val sireumAppDir: Os.Path = ideaDir / s"IVE.app"
    val delPlugins = ISZ[String]("android", "smali", "Ktor", "design-tools")
    val pluginPrefix: String = "org.sireum.version.plugin."
    val versions = HashMap ++ (home / "versions.properties").properties.entries
    val isLocal: B = ops.StringOps(home.string).startsWith(Os.home.canon.string)
    val settingsDir: String = if (isLocal) if (Os.isWin) ops.StringOps((home / ".settings").string).replaceAllChars('\\', '/') else (home / ".settings").string else "${user.home}"
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
    val ideaCacheDir = cache / "idea"
    val pluginsCacheDir = ideaCacheDir / "plugins"

    def platform(k: Os.Kind.Type): String = {
      k match {
        case Os.Kind.Mac => return "mac"
        case Os.Kind.LinuxArm => return "linux/arm"
        case Os.Kind.Linux => return "linux"
        case Os.Kind.Win => return "win"
        case _ => halt("Unsupported platform")
      }
    }

    val plugins: HashMap[String, Plugin] = {
      var r = HashMap.empty[String, Plugin]
      for (key <- versions.keys if ops.StringOps(key).startsWith(pluginPrefix)) {
        val id = ops.StringOps(key).substring(pluginPrefix.size, key.size)
        ops.StringOps(versions.get(key).get).split((c: C) => c == ',') match {
          case ISZ(isDev, isJar, devVer) =>
            r = r + id ~> Plugin(id, isDev == "true", isJar == "true", devVer, devVer)
          case ISZ(isDev, isJar, devVer, ver) =>
            r = r + id ~> Plugin(id, isDev == "true", isJar == "true", devVer, ver)
        }
      }
      r
    }

    val distroMap = HashMap.empty[Os.Kind.Type, ISZ[ISZ[String]]] +
      Os.Kind.Win ~> ISZ(
        ISZ("bin", "scala"),
        ISZ("bin", "win", "idea"),
        ISZ("bin", "win", "java"),
        ISZ("bin", "win", "z3"),
        ISZ("bin", "win", "7za.exe"),
        ISZ("bin", "win", "cvc.exe"),
        ISZ("bin", "install", "antlrworks.cmd"),
        ISZ("bin", "install", "clion.cmd"),
        ISZ("bin", "install", "fmide.cmd"),
        ISZ("bin", "install", "graal.cmd"),
        ISZ("bin", "sireum.bat"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "slang-run.bat"),
        ISZ("bin", "VER"),
        ISZ("lib"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties"),
        ISZ("..", "setup.bat")
      ) +
      Os.Kind.Linux ~> ISZ(
        ISZ("bin", "scala"),
        ISZ("bin", "linux", "idea"),
        ISZ("bin", "linux", "java"),
        ISZ("bin", "linux", "z3"),
        ISZ("bin", "linux", "7za"),
        ISZ("bin", "linux", "cvc"),
        ISZ("bin", "linux", "alt-ergo-open"),
        ISZ("bin", "install", "acl2.cmd"),
        ISZ("bin", "install", "alt-ergo.cmd"),
        ISZ("bin", "install", "antlrworks.cmd"),
        ISZ("bin", "install", "clion.cmd"),
        ISZ("bin", "install", "compcert.cmd"),
        ISZ("bin", "install", "coq.cmd"),
        ISZ("bin", "install", "ffmpeg-libs.cmd"),
        ISZ("bin", "install", "fmide.cmd"),
        ISZ("bin", "install", "graal.cmd"),
        ISZ("bin", "install", "menhir.cmd"),
        ISZ("bin", "install", "opam.cmd"),
        ISZ("bin", "install", "projector-server.cmd"),
        ISZ("bin", "sireum"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "slang-run.sh"),
        ISZ("bin", "VER"),
        ISZ("lib"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties"),
        ISZ("..", "setup")
      ) +
      Os.Kind.LinuxArm ~> ISZ(
        ISZ("bin", "scala"),
        ISZ("bin", "linux", "arm", "idea"),
        ISZ("bin", "linux", "arm", "java"),
        ISZ("bin", "linux", "arm", "7za"),
        ISZ("bin", "linux", "arm", "fsnotifier"),
        ISZ("bin", "install", "clion.cmd"),
        ISZ("bin", "sireum"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "slang-run.sh"),
        ISZ("bin", "VER"),
        ISZ("lib"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties"),
        ISZ("..", "setup")
      ) +
      Os.Kind.Mac ~> ISZ(
        ISZ("bin", "scala"),
        ISZ("bin", "mac", "idea"),
        ISZ("bin", "mac", "java"),
        ISZ("bin", "mac", "z3"),
        ISZ("bin", "mac", "7za"),
        ISZ("bin", "mac", "cvc"),
        ISZ("bin", "mac", "alt-ergo-open"),
        ISZ("bin", "install", "alt-ergo.cmd"),
        ISZ("bin", "install", "antlrworks.cmd"),
        ISZ("bin", "install", "clion.cmd"),
        ISZ("bin", "install", "compcert.cmd"),
        ISZ("bin", "install", "coq.cmd"),
        ISZ("bin", "install", "fmide.cmd"),
        ISZ("bin", "install", "graal.cmd"),
        ISZ("bin", "install", "menhir.cmd"),
        ISZ("bin", "install", "opam.cmd"),
        ISZ("bin", "sireum"),
        ISZ("bin", "sireum.jar"),
        ISZ("bin", "slang-run.sh"),
        ISZ("bin", "VER"),
        ISZ("lib"),
        ISZ("license.txt"),
        ISZ("readme.md"),
        ISZ("versions.properties"),
        ISZ("..", "setup")
      )

    val pluginsDir: Os.Path =
      if (kind == Os.Kind.Mac) sireumAppDir / "Contents" / "plugins"
      else ideaDir / "plugins"

    val libDir: Os.Path =
      if (kind == Os.Kind.Mac) sireumAppDir / "Contents" / "lib"
      else ideaDir / "lib"

    @strictpure def zipName(id: String, version: String): String = s"$id-$version.zip"

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

    val pwd7z = homeBin / platform(Os.kind) / (if (Os.isWin) "7za.exe" else "7za")
    val pwd7zsfx = pwd7z.up / s"7z.sfx"
    val pwd7zUrl: String = Os.kind match {
      case Os.Kind.Win => "https://github.com/sireum/bin-windows/raw/master/7za.exe"
      case Os.Kind.Mac => "https://github.com/sireum/bin-mac/raw/master/7za"
      case Os.Kind.Linux => "https://github.com/sireum/bin-linux/raw/master/7za"
      case Os.Kind.LinuxArm => "https://github.com/sireum/bin-linux/raw/master/arm/7za"
      case _ => halt("Infeasible")
    }

    if (!pwd7z.exists) {
      pwd7z.up.mkdirAll()
      println(s"Please wait while downloading ${pwd7z.name} ...")
      pwd7z.downloadFrom(pwd7zUrl)
      pwd7z.chmod("+x")
      println()
    }

    if (buildSfx && !pwd7zsfx.exists) {
      println(s"Please wait while downloading ${pwd7zsfx.name} ...")
      pwd7zsfx.downloadFrom(
        if (Os.isWin) ops.StringOps(pwd7zUrl).replaceAllLiterally("7za.exe", "7z.sfx")
        else ops.StringOps(pwd7zUrl).replaceAllLiterally("7za", "7z.sfx")
      )
      pwd7zsfx.chmod("+x")
      println()
    }

    pluginsCacheDir.mkdirAll()

    def downloadPlugins(): Unit = {
      for (p <- plugins.values) {
        val ver = p.version(isDev)
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

    def extractPlugins(): Unit = {
      for (p <- plugins.values) {
        val zip = zipName(p.id, p.version(isDev))
        val zipPath = ideaCacheDir / "plugins" / zip
        if (p.isJar) {
          print(s"Copying ${p.id} plugin ... ")
          zipPath.copyOverTo(pluginsDir / s"${p.id}.jar")
          println("done!")
        } else {
          print(s"Extracting ${p.id} plugin from $zipPath ... ")
          proc"$pwd7z x -y $zipPath".at(pluginsDir).runCheck()
          println("done!")
        }
      }
    }

    def patchIdeaProperties(p: Os.Path): Unit = {
      if (isServer) {
        return
      }
      print(s"Patching $p ... ")
      val content = p.read
      val ult: String = if (isUltimate) "-ult" else ""
      val newContent: String = kind match {
        case Os.Kind.Mac =>
          val contentOps = ops.StringOps(content)
          val i = contentOps.stringIndexOf("idea.paths.selector")
          val j = contentOps.stringIndexOfFrom("<string>", i)
          val k = contentOps.stringIndexOfFrom("</string>", j)
          if (isLocal) s"${contentOps.substring(0, j)}<string>.SireumIVE$ult$devSuffix</string>\n        <key>idea.config.path</key>\n        <string>$settingsDir/.SireumIVE$ult$devSuffix/config</string>\n        <key>idea.system.path</key>\n        <string>$settingsDir/.SireumIVE$ult$devSuffix/system</string>\n        <key>idea.log.path</key>\n        <string>$settingsDir/.SireumIVE$ult$devSuffix/log</string>\n        <key>idea.plugins.path</key>\n        <string>$settingsDir/.SireumIVE$ult$devSuffix/plugins${contentOps.substring(k, content.size)}"
          else s"${contentOps.substring(0, j)}<string>SireumIVE$ult$devSuffix${contentOps.substring(k, content.size)}"
        case Os.Kind.Win =>
          s"idea.config.path=$settingsDir/.SireumIVE$ult$devSuffix/config\r\nidea.system.path=$settingsDir/.SireumIVE$ult$devSuffix/system\r\nidea.log.path=$settingsDir/.SireumIVE$ult$devSuffix/log\r\nidea.plugins.path=$settingsDir/.SireumIVE$ult$devSuffix/plugins\r\n$content"
        case _ =>
          s"idea.config.path=$settingsDir/.SireumIVE$ult$devSuffix/config\nidea.system.path=$settingsDir/.SireumIVE$ult$devSuffix/system\nidea.log.path=$settingsDir/.SireumIVE$ult$devSuffix/log\nidea.plugins.path=$settingsDir/.SireumIVE$ult$devSuffix/plugins\n$content"
      }
      p.writeOver(newContent)
      println("done!")
    }

    def patchVMOptions(p: Os.Path): Unit = {
      print(s"Patching $p ... ")
      val content = ops.StringOps(p.read).trim
      val newContent: String =
        if (Os.isWin) s"$content\r\n-Dorg.sireum.ive=Sireum$devSuffix\r\n-Dorg.sireum.ive.dev=$isDev\r\n-Dfile.encoding=UTF-8\r\n"
        else s"$content\n-Dorg.sireum.ive=Sireum$devSuffix\n-Dorg.sireum.ive.dev=$isDev\n-Dfile.encoding=UTF-8\n"
      p.writeOver(newContent)
      println("done!")
    }

    def patchExe(): Unit = {
      if (isUltimate) {
        return
      }
      val rhExe = "ResourceHacker.exe"
      val rhDir = home / "resources" / "rh"
      rhDir.mkdirAll()
      if (!(rhDir / rhExe).exists) {
        print("Downloading ResourceHacker ... ")
        (rhDir / "rh.zip").downloadFrom("http://angusj.com/resourcehacker/resource_hacker.zip")
        proc"$pwd7z x rh.zip $rhExe".at(rhDir).runCheck()
        println("done!")
      }
      val binDir = ideaDir / "bin"
      val idea64Exe = binDir / "idea64.exe"
      print(s"Patching $idea64Exe ... ")
      if (Os.isWin) {
        proc"${rhDir / rhExe} -open .\\${idea64Exe.name} -save .\\${idea64Exe.name} -action addoverwrite -res .\\idea.ico -mask ICONGROUP,2000,1033".at(binDir).runCheck()
      } else {
        proc"wine ${rhDir / rhExe} -open .\\${idea64Exe.name} -save .\\${idea64Exe.name} -action addoverwrite -res .\\idea.ico -mask ICONGROUP,2000,1033".at(binDir).runCheck()
      }
      println("done!")
    }

    def patchIcon(isWin: B): Unit = {
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
      if (isWin) {
        patchExe()
      }
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
      val iai = tempDir / "idea" / "IdeaApplicationInfo.xml"
      val content = iai.read
      iai.writeOver(
        ops.StringOps(ops.StringOps(content).
          replaceAllLiterally("svg-small=\"/idea-ce_16.svg\"", "svg-small=\"/idea-ce_16.png\"")).
          replaceAllLiterally("svg-small=\"/idea-ce-eap_16.svg\"", "svg-small=\"/idea-ce_16.png\""))
      for (e <- ISZ("idea-ce_16.svg", "idea-ce_16@2x.svg", "idea-ce-eap_16.svg", "idea-ce-eap_16@2x.svg")) {
        (tempDir / e).removeAll()
      }
      val d = distroDir / "images" / (if (isDev) "dev" else "release")
      for (e <- ISZ("idea_community_about.png", "idea_community_about@2x.png", "idea_community_logo.png", "idea_community_logo@2x.png", "idea-ce.svg", "idea-ce-eap.svg", "idea-ce_16.png", "idea-ce_16@2x.png")) {
        (d / e).copyOverTo(tempDir / e)
      }
      val appTempJar = libDir / "app-temp.jar"
      tempDir.zipTo(appTempJar)
      appTempJar.moveOverTo(appJar)
      tempDir.removeAll()
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
      deletePlugins()
      extractPlugins()
      patchIcon(F)
      patchApp()
      patchIdeaProperties(sireumAppDir / "Contents" / "Info.plist")
      patchVMOptions(sireumAppDir / "Contents" / "bin" / "idea.vmoptions")
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
        proc"tar xfz $ideaDrop".at(ideaDirParent).runCheck()

        for (p <- ideaDirParent.list if ops.StringOps(p.name).startsWith(s"idea-${if (isUltimate) "IU" else "IC"}-")) {
          p.moveOverTo(ideaDir)
        }
      }
      deleteSources()
      if (!isServer) {
        println("done!")
      }
      deletePlugins()
      extractPlugins()
      patchIcon(F)
      patchApp()
      patchIdeaProperties(ideaDir / "bin" / "idea.properties")
      patchVMOptions(ideaDir / "bin" / "idea64.vmoptions")
      (ideaDir / "bin" / "idea.vmoptions").removeAll()
      if (!isServer) {
        val ideash = ideaDir / "bin" / "idea.sh"
        val ivesh = ideaDir / "bin" / "IVE.sh"
        ideash.moveOverTo(ivesh)
        ivesh.chmod("+x")
        ideash.mklink(ivesh)
      }
    }

    def setupWin(ideaDrop: Os.Path): Unit = {
      ideaDir.mkdirAll()
      proc"$pwd7z x -y $ideaDrop".at(ideaDir).runCheck()
      (ideaDir / "$PLUGINSDIR").removeAll()
      deleteSources()
      println("done!")
      deletePlugins()
      extractPlugins()
      patchIcon(T)
      patchApp()
      patchIdeaProperties(ideaDir / "bin" / "idea.properties")
      patchVMOptions(ideaDir / "bin" / "idea64.exe.vmoptions")
      (ideaDir / "bin" / "idea.exe").removeAll()
      (ideaDir / "bin" / "idea.exe.vmoptions").removeAll()
      (ideaDir / "bin" / "idea64.exe").moveOverTo(ideaDir / "bin" / "IVE.exe")
      (ideaDir / "bin" / "idea64.exe.vmoptions").moveOverTo(ideaDir / "bin" / "IVE.exe.vmoptions")
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
      val cmd = ISZ[String](pwd7z.string, "a", distro7z) ++ files

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

    def build(): Unit = {
      println(s"Setting up Sireum$devSuffix IVE $kind in $ideaDir ...")
      val suffix: String =
        if (Os.isMacArm) ideaExtMap.get("mac/arm").get
        else ideaExtMap.get(platform(kind)).get
      val url: String = s"https://download.jetbrains.com/idea/idea${if (isUltimate) "IU" else "IC"}-$ideaVer$suffix"
      val urlOps = ops.StringOps(url)
      val filename = urlOps.substring(urlOps.lastIndexOf('/') + 1, url.size)
      val buildDir = ideaDir.up.canon
      buildDir.mkdirAll()
      val ideaDrop = ideaCacheDir / filename
      if (!ideaDrop.exists && !isServer) {
        print(s"Downloading from $url ... ")
        (ideaCacheDir / filename).downloadFrom(url)
        println("done!")
      }
      downloadPlugins()
      if (!isServer) {
        print(s"Extracting $ideaDrop ... ")
      }
      ideaDir.removeAll()
      kind match {
        case Os.Kind.Mac => setupMac(ideaDrop)
        case Os.Kind.Win => setupWin(ideaDrop)
        case _ => setupLinux(ideaDrop)
      }
      val sireumJar = pluginsDir / "sireum-intellij-plugin" / "lib" / "sireum.jar"
      val homeBinSireumJar = homeBin / "sireum.jar"
      sireumJar.removeAll()
      if (buildSfx) {
        homeBinSireumJar.copyTo(sireumJar)
        pack()
      } else {
        sireumJar.mklink(homeBinSireumJar)
      }
      println("Done!")
    }

    build()

    (home / "bin" / "VER").writeOver(
      proc"git log -n 1 --date=format:%Y%m%d --pretty=format:4.%cd.%h".at(home).runCheck().out)
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
