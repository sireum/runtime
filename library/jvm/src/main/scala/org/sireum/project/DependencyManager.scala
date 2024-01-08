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

package org.sireum.project

import org.sireum._

object DependencyManager {
  @datatype class Lib(val name: String,
                      val org: String,
                      val module: String,
                      val version: String,
                      val main: String,
                      val sourcesOpt: Option[String],
                      val javadocOpt: Option[String])

  val javaKey: String = "org.sireum.version.java"
  val scalacPluginKey: String = "org.sireum::scalac-plugin:"
  val scalaKey: String = "org.scala-lang:scala-library:"
  val scalaJsKey: String = "org.scala-js::scalajs-library:"
  val scalaJsCompilerKey: String = "org.scala-js:::scalajs-compiler:"
  val scalaTestKey: String = "org.scalatest::scalatest::"
  val macrosKey: String = "org.sireum.kekinian::macros:"
  val testKey: String = "org.sireum.kekinian::test:"
  val libraryKey: String = "org.sireum.kekinian::library:"
  val librarySharedKey: String = "org.sireum.kekinian::library-shared:"

  val jarSuffix: String = ".jar"
  val sourceJarSuffix: String = "-sources.jar"
  val docJarSuffix: String = "-javadoc.jar"

  val ignoredLibraryNames: HashSet[String] = HashSet ++ ISZ[String](
    "org.scala-lang.scala-library", "org.scala-lang.scala-reflect", "org.scala-lang.scala-compiler"
  )

  @pure def buildClassifiers(withSource: B, withDoc: B): ISZ[CoursierClassifier.Type] = {
    var classifiers = ISZ[CoursierClassifier.Type](CoursierClassifier.Default)
    if (withSource) {
      classifiers = classifiers :+ CoursierClassifier.Sources
    }
    if (withDoc) {
      classifiers = classifiers :+ CoursierClassifier.Javadoc
    }
    return classifiers
  }

  @strictpure def libName(cif: CoursierFileInfo): String = s"${cif.org}.${cif.module}"
}

import DependencyManager._

@record class DependencyManager(val project: Project,
                                val versions: HashSMap[String, String],
                                val isJs: B,
                                val withSource: B,
                                val withDoc: B,
                                val javaHome: Os.Path,
                                val scalaHome: Os.Path,
                                val sireumHome: Os.Path,
                                val cacheOpt: Option[Os.Path]) {

  val sireumJar: Os.Path = sireumHome / "bin" / "sireum.jar"

  val _scalacPlugin: Os.Path = sireumHome / "lib" / s"scalac-plugin-${versions.get(scalacPluginKey).get}.jar"

  def scalacPlugin: Os.Path = {
    if (_scalacPlugin.exists) {
      return _scalacPlugin
    }
    val version = versions.get(scalacPluginKey).get
    val url = s"https://github.com/sireum/scalac-plugin/releases/download/$version/scalac-plugin-$version.jar"
    val cacheDir: Os.Path = Os.env("SIREUM_CACHE") match {
      case Some(v) => Os.path(v)
      case _ => Os.home / "Downloads" / "sireum"
    }
    cacheDir.mkdirAll()
    _scalacPlugin.up.mkdirAll()
    val pluginCache = cacheDir / _scalacPlugin.name
    if (!pluginCache.exists) {
      println(s"Please wait while downloading Slang scalac plugin $version ...")
      pluginCache.downloadFrom(url)
      println()
    }
    pluginCache.copyOverTo(_scalacPlugin)
    return _scalacPlugin
  }

  val javaVersion: String = versions.get(DependencyManager.javaKey) match {
    case Some(v) => v
    case _ => halt(s"Could not find Java version (key: ${DependencyManager.javaKey})")
  }

  val scalaVersion: String = versions.get(DependencyManager.scalaKey) match {
    case Some(v) => v
    case _ => halt(s"Could not find Scala version (key: ${DependencyManager.scalaKey})")
  }

  val scalaMajorVersion: String = {
    val verOps = ops.StringOps(scalaVersion)
    val i = verOps.indexOf('.')
    val j = verOps.indexOfFrom('.', i + 1)
    verOps.substring(0, j)
  }

  val scalaJsVersion: String = versions.get(DependencyManager.scalaJsCompilerKey) match {
    case Some(v) => v
    case _ => halt(s"Could not find Scala.js version (key: ${DependencyManager.scalaJsCompilerKey})")
  }

  val sjsSuffix: String = {
    val verOps = ops.StringOps(scalaJsVersion)
    val i = verOps.indexOf('.')
    s"_sjs${verOps.substring(0, i)}"
  }

  val scalaTestVersion: String = versions.get(DependencyManager.scalaTestKey) match {
    case Some(v) => v
    case _ => halt(s"Could not find ScalaTest version (key: ${DependencyManager.scalaTestKey})")
  }

  val ivyDeps: HashSMap[String, String] = {
    var r = HashSMap.empty[String, String]
    def addIvyDep(ivyDep: String): Unit = {
      val v = getVersion(ivyDep)
      r = r + ivyDep ~> s"${toJsDep(ivyDep)}$v"
    }
    for (m <- project.modules.values) {
      for (ivyDep <- m.ivyDeps) {
        addIvyDep(ivyDep)
      }
    }
    var hasTest = F
    for (m <- project.modules.values if ProjectUtil.moduleTestSources(m).nonEmpty) {
      hasTest = T
    }
    if (hasTest) {
      addIvyDep(scalaTestKey)
    }
    r
  }

  var _libMapInit: B = F
  var _libMap: HashSMap[String, Lib] = HashSMap.empty
  var fetchedDeps: HashMap[(ISZ[String], ISZ[CoursierClassifier.Type]), ISZ[CoursierFileInfo]] = HashMap.empty

  def toJsDep(ivyDep: String): String = {
    val ivyDepOps = ops.StringOps(ivyDep)
    if (ivyDepOps.endsWith("::")) {
      if (isJs) {
        return s"${ivyDepOps.substring(0, ivyDep.size - 2)}$sjsSuffix:"
      } else {
        return s"${ivyDepOps.substring(0, ivyDep.size - 1)}"
      }
    } else {
      return ivyDep
    }
  }

  def libMap: HashSMap[String, Lib] = {
    if (!_libMapInit) {
      var r = HashSMap.empty[String, Lib]
      for (cif <- fetchClassifiers(ivyDeps.values, buildClassifiers(withSource, withDoc))) {
        val name = libName(cif)
        val p = cif.path
        val pNameOps = ops.StringOps(p.string)
        if (!ignoredLibraryNames.contains(name)) {
          var lib: Lib = r.get(name) match {
            case Some(l) => l
            case _ => Lib(name, cif.org, cif.module, cif.version, "", None(), None())
          }
          if (pNameOps.endsWith(sourceJarSuffix)) {
            lib = lib(sourcesOpt = Some(p.string))
          } else if (pNameOps.endsWith(docJarSuffix)) {
            lib = lib(javadocOpt = Some(p.string))
          } else if (pNameOps.endsWith(jarSuffix)) {
            lib = lib(main = p.string)
          } else {
            halt(s"Expecting a file with .jar extension but found '$p'")
          }
          r = r + name ~> lib
        }
      }
      _libMap = r
      _libMapInit = T
    }
    return _libMap
  }

  var tLibMap: HashMap[String, ISZ[Lib]] = HashMap.empty

  var dLibMap: HashMap[String, ISZ[Lib]] = HashMap.empty

  @pure def getVersion(ivyDep: String): String = {
    versions.get(ivyDep) match {
      case Some(v) => return v
      case _ => halt(s"Could not find version information for '$ivyDep' in $versions")
    }
  }

  @pure def getModule(id: String): Module = {
    project.modules.get(id) match {
      case Some(m) => return m
      case _ => halt(s"Could not find module with ID '$id'")
    }
  }

  @memoize def computeTransitiveDeps(m: Module): ISZ[String] = {
    var r = HashSSet.empty[String]
    for (mDep <- m.deps) {
      r = r + mDep ++ computeTransitiveDeps(getModule(mDep))
    }
    return r.elements
  }

  @memoize def computeTransitiveIvyDeps(m: Module): ISZ[String] = {
    var r = HashSSet.empty[String]
    for (mid <- m.deps) {
      r = r ++ computeTransitiveIvyDeps(project.modules.get(mid).get)
    }
    for (id <- m.ivyDeps) {
      r = r + ivyDeps.get(id).get
    }
    return r.elements
  }

  def fetchTransitiveLibs(m: Module): ISZ[Lib] = {
    tLibMap.get(m.id) match {
      case Some(libs) => return libs
      case _ =>
    }
    val r: ISZ[Lib] =
      for (cif <- fetch(computeTransitiveIvyDeps(m)) if !ignoredLibraryNames.contains(libName(cif))) yield libMap.get(libName(cif)).get
    tLibMap = tLibMap + m.id ~> r
    return r
  }

  def fetchDiffLibs(m: Module): ISZ[Lib] = {
    dLibMap.get(m.id) match {
      case Some(libs) => return libs
      case _ =>
    }
    var s = HashSSet ++ fetchTransitiveLibs(m)
    for (mDep <- m.deps) {
      s = s -- fetchTransitiveLibs(getModule(mDep))
    }
    val r = s.elements
    dLibMap = dLibMap + m.id ~> r
    return r
  }

  def fetch(ivyDeps: ISZ[String]): ISZ[CoursierFileInfo] = {
    return fetchClassifiers(ivyDeps, ISZ(CoursierClassifier.Default))
  }

  def fetchClassifiers(ivyDeps: ISZ[String], classifiers: ISZ[CoursierClassifier.Type]): ISZ[CoursierFileInfo] = {
    val key = (ivyDeps, classifiers)
    fetchedDeps.get(key) match {
      case Some(r) => return r
      case _ =>
        val r = Coursier.fetchClassifiers(scalaVersion, cacheOpt, project.mavenRepoUrls, ivyDeps, classifiers)
        fetchedDeps = fetchedDeps + key ~> r
        return r
    }
  }
}

