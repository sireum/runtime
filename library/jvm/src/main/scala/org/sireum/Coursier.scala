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


@enum object CoursierClassifier {
  "Default"
  "Javadoc"
  "Sources"
}

@datatype class CoursierFileInfo(val org: String,
                                 val module: String,
                                 val version: String,
                                 val pathString: String) {
  @pure def path: Os.Path = {
    return Os.path(pathString)
  }
}

object Coursier {

  @datatype class Proxy(val hostOpt: Option[String],
                        val nonHostsOpt: Option[String],
                        val portOpt: Option[String],
                        val protocolOpt: Option[String],
                        val protocolUserEnvKeyOpt: Option[String],
                        val protocolPasswordEnvKeyOpt: Option[String])

  object Proxy {
    @strictpure def empty: Proxy = Proxy(None(), None(), None(), None(), None(), None())
  }

  @memoize def defaultCacheDir: Os.Path = {
    Os.env("COURSIER_CACHE") match {
      case Some(p) =>
        val r = Os.path(p)
        if (!r.exists) {
          r.mkdirAll()
        }
        if (r.exists) {
          return r
        }
      case _ =>
    }
    return Os.sireumHomeOpt.get / "lib" / "cache"
  }

  def commandPrefix(isResolve: B, scalaVersion: String, cacheOpt: Option[Os.Path], mavenRepoUrls: ISZ[String], proxy: Proxy): ISZ[String] = {
    val sireumHome = Os.sireumHomeOpt.get
    var proxyOptions = ISZ[String]()
    def updateProxyOption(key: String, opt: Option[String], isEnv: B): Unit = {
      opt match {
        case Some(v) =>
          var value = v
          if (isEnv) {
            Os.env(value) match {
              case Some(value2) => value = value2
              case _ =>
                eprintln(s"Environment variable $value is not defined; skipping setting $key option")
                return
            }
          }
          proxyOptions = proxyOptions :+ s"-J-D$key=$value"
        case _ =>
      }
    }
    updateProxyOption("https.proxyProtocol", proxy.protocolOpt, F)
    updateProxyOption("https.proxyHost", proxy.hostOpt, F)
    updateProxyOption("https.proxyPort", proxy.portOpt, F)
    updateProxyOption("https.proxyUser", proxy.protocolUserEnvKeyOpt, T)
    updateProxyOption("https.proxyPassword", proxy.protocolPasswordEnvKeyOpt, T)
    updateProxyOption("http.nonProxyHosts", proxy.nonHostsOpt, F)
    val csPrefix: ISZ[String] = Os.kind match {
      case Os.Kind.Win =>
        val coursierExe = sireumHome / "bin" / "win" / "cs.exe"
        coursierExe.string +: proxyOptions
      case Os.Kind.Linux =>
        val coursierExe = sireumHome / "bin" / "linux" / "cs"
        coursierExe.string +: proxyOptions
      case Os.Kind.LinuxArm =>
        val coursierExe = sireumHome / "bin" / "linux" / "arm" / "cs"
        coursierExe.string +: proxyOptions
      case Os.Kind.Mac =>
        val coursierExe = sireumHome / "bin" / "mac" / "cs"
        coursierExe.string +: proxyOptions
      case _ =>
        val javaExe = Os.javaExe(Some(sireumHome))
        val coursierJar = sireumHome / "lib" / "coursier.jar"
        (javaExe.string +: (for (opt <- proxyOptions) yield ops.StringOps(opt).substring(2, opt.size))) ++
          ISZ[String]("-jar", coursierJar.string)
    }

    val cache: ISZ[String] = cacheOpt match {
      case Some(c) => ISZ("--cache", c.string)
      case _ =>
        val c = defaultCacheDir
        if (ops.StringOps(c.string).startsWith(Os.home.string)) ISZ("--cache", c.string) else ISZ()
    }
    var repos = ISZ[String]("-r", "sonatype:release", "-r", "jitpack", "-r", (Os.home / ".m2" / "repository").toUri)
    for (url <- mavenRepoUrls) {
      repos = repos ++ ISZ("-r", url)
    }
    return csPrefix ++ ISZ[String](if (isResolve) "resolve" else "fetch", "--quiet",
      "--scala", scalaVersion) ++ cache ++ repos
  }

  def resolve(scalaVersion: String, cacheOpt: Option[Os.Path], mavenRepoUrls: ISZ[String], deps: ISZ[String],
              printTree: B, proxy: Proxy): HashMap[String, HashMap[String, String]] = {
    val resolveCommands = commandPrefix(T, scalaVersion, cacheOpt, mavenRepoUrls, proxy) ++
      (if (printTree) ISZ[String]("--tree") else ISZ[String]()) ++ deps

    var moduleVersionOrgMap = HashMap.empty[String, HashMap[String, String]]

    if (printTree) {
      Os.proc(resolveCommands).console.env(ISZ("JAVA_HOME" ~> Os.javaHomeOpt(Os.kind, Os.sireumHomeOpt).get.string)).runCheck()
    } else {
      val resolveLines = ops.StringOps(Os.proc(resolveCommands).env(ISZ("JAVA_HOME" ~> Os.javaHomeOpt(Os.kind, Os.sireumHomeOpt).get.string)).runCheck().out).split((c: C) => c == '\n' || c == '\r')
      for (line <- resolveLines) {
        val ISZ(org, module, version, _*) = ops.StringOps(line).split((c: C) => c == ':').map((s: String) => ops.StringOps(s).trim)
        val moduleVersion = s"$module-$version"
        val orgMap = moduleVersionOrgMap.get(moduleVersion).getOrElse(HashMap.empty)
        moduleVersionOrgMap = moduleVersionOrgMap + moduleVersion ~> (orgMap + org ~> version)
      }
    }
    return moduleVersionOrgMap
  }

  def fetch(scalaVersion: String, deps: ISZ[String], proxy: Proxy): ISZ[CoursierFileInfo] = {
    return fetchClassifiers(scalaVersion, None(), ISZ(), deps, ISZ(CoursierClassifier.Default), proxy)
  }

  def fetchClassifiers(scalaVersion: String, cacheOpt: Option[Os.Path], mavenRepoUrls: ISZ[String], deps: ISZ[String],
                       cls: ISZ[CoursierClassifier.Type], proxy: Proxy): ISZ[CoursierFileInfo] = {

    val moduleVersionOrgMap = resolve(scalaVersion, cacheOpt, mavenRepoUrls, deps, F, proxy)

    var classifiers = ISZ[String]()
    for (cl <- cls) {
      cl match {
        case CoursierClassifier.Default => classifiers = classifiers :+ "--default"
        case CoursierClassifier.Sources => classifiers = classifiers :+ "--sources"
        case CoursierClassifier.Javadoc => classifiers = classifiers :+ "--javadoc"
        case _ =>
      }
    }

    val fetchCommands = commandPrefix(F, scalaVersion, cacheOpt, mavenRepoUrls, proxy) ++ classifiers ++ deps

    var cifs = ISZ[CoursierFileInfo]()

    val fetchLines = ops.StringOps(Os.proc(fetchCommands).env(ISZ("JAVA_HOME" ~> Os.javaHomeOpt(Os.kind, Os.sireumHomeOpt).get.string)).runCheck().out).split((c: C) => c == '\n' || c == '\r')
    for (line <- fetchLines) {
      val path = Os.path(ops.StringOps(line).trim)
      val pOps = ops.StringOps(ops.StringOps(path.toUri).replaceAllLiterally("%252B", "+"))
      val i = pOps.lastIndexOf('/') + 1
      val moduleVersion: String = if (pOps.endsWith("-sources.jar")) {
        pOps.substring(i, pOps.size - string"-sources.jar".size)
      } else if (pOps.endsWith("-javadoc.jar")) {
        pOps.substring(i, pOps.size - string"-javadoc.jar".size)
      } else {
        pOps.substring(i, pOps.size - string".jar".size)
      }
      val orgMap: HashMap[String, String] = moduleVersionOrgMap.get(moduleVersion) match {
        case Some(m) => m
        case _ => halt(s"Could not find $moduleVersion in $moduleVersionOrgMap")
      }
      for (p <- orgMap.entries) {
        val (org, version) = p
        val versionSuffix: String = s"-$version"
        val moduleVersionOps = ops.StringOps(moduleVersion)
        if (moduleVersionOps.endsWith(versionSuffix) &&
          pOps.contains(s"/${ops.StringOps(org).replaceAllChars('.', '/')}/")) {
          val module = moduleVersionOps.substring(0, moduleVersionOps.size - versionSuffix.size)
          cifs = cifs :+ CoursierFileInfo(org, module, version, path.string)
        }
      }
    }

    return cifs
  }

  def isRuntimePublishedLocally(scalaVersion: String, version: String): B = {
    val scalaVer = ops.StringOps(scalaVersion).substring(0, ops.StringOps(scalaVersion).lastIndexOf('.'))
    val path = Os.home / ".m2" / "repository" / "org" / "sireum" / "kekinian" / s"library_$scalaVer" / version /
      s"library_$scalaVer-$version.jar"
    return path.exists
  }
}
