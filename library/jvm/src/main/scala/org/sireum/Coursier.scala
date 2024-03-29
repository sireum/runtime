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


@enum object CoursierClassifier {
  'Default
  'Javadoc
  'Sources
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

  def commandPrefix(isResolve: B, scalaVersion: String, cacheOpt: Option[Os.Path], mavenRepoUrls: ISZ[String]): ISZ[String] = {
    val sireumHome = Os.sireumHomeOpt.get
    val csPrefix: ISZ[String] = if (Os.isWin) {
      val coursierExe = sireumHome / "bin" / "win" / "cs.exe"
      ISZ(coursierExe.string)
    } else {
      val javaExe = Os.javaExe(Some(sireumHome))
      val coursierJar = sireumHome / "lib" / "coursier.jar"
      ISZ[String](javaExe.string, "-jar", coursierJar.string)
    }
    val cache: ISZ[String] = cacheOpt match {
      case Some(c) => ISZ("--cache", c.string)
      case _ =>
        val c = sireumHome / "lib" / "cache"
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
              printTree: B): HashMap[String, HashMap[String, String]] = {
    val resolveCommands = commandPrefix(T, scalaVersion, cacheOpt, mavenRepoUrls) ++
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

  def fetch(scalaVersion: String, deps: ISZ[String]): ISZ[CoursierFileInfo] = {
    return fetchClassifiers(scalaVersion, None(), ISZ(), deps, ISZ(CoursierClassifier.Default))
  }

  def fetchClassifiers(scalaVersion: String, cacheOpt: Option[Os.Path], mavenRepoUrls: ISZ[String], deps: ISZ[String],
                       cls: ISZ[CoursierClassifier.Type]): ISZ[CoursierFileInfo] = {

    val moduleVersionOrgMap = resolve(scalaVersion, cacheOpt, mavenRepoUrls, deps, F)

    var classifiers = ISZ[String]()
    for (cl <- cls) {
      cl match {
        case CoursierClassifier.Default => classifiers = classifiers :+ "--default"
        case CoursierClassifier.Sources => classifiers = classifiers :+ "--sources"
        case CoursierClassifier.Javadoc => classifiers = classifiers :+ "--javadoc"
        case _ =>
      }
    }

    val fetchCommands = commandPrefix(F, scalaVersion, cacheOpt, mavenRepoUrls) ++ classifiers ++ deps

    var cifs = ISZ[CoursierFileInfo]()

    val fetchLines = ops.StringOps(Os.proc(fetchCommands).env(ISZ("JAVA_HOME" ~> Os.javaHomeOpt(Os.kind, Os.sireumHomeOpt).get.string)).runCheck().out).split((c: C) => c == '\n' || c == '\r')
    for (line <- fetchLines) {
      val path = Os.path(ops.StringOps(ops.StringOps(line).trim).replaceAllLiterally("%2B", "+"))
      val pOps = ops.StringOps(path.toUri)
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
