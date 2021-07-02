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

import coursier._

object Coursier_Ext {

  val localMavenRepo: Repository = MavenRepository((Os.home / ".m2" / "repository").toUri.value)
  val sonatypeReleaseRepo: Repository = Repositories.sonatype("releases")

  var scalaVersion: String = scala.util.Properties.versionNumberString
  var cacheOpt: Option[Os.Path] = None()
  var mavenRepoUrls: ISZ[String] = ISZ()

  val repositories: ISZ[Repository] = ISZ(
    localMavenRepo,
    sonatypeReleaseRepo,
    Repositories.jitpack
  )

  def toDeps(deps: ISZ[String]): Seq[Dependency] =
    parse.DependencyParser.dependencies(deps.elements.map(_.value), scalaVersion.value).
      either.getOrElse(halt(s"Invalid dependencies: $deps"))

  def setScalaVersion(version: String): Unit = {
    scalaVersion = version
  }

  def setCache(pathOpt: Option[Os.Path]): Unit = {
    cacheOpt = pathOpt
  }

  def addMavenRepositories(urls: ISZ[String]): Unit = {
    mavenRepoUrls = (HashSSet ++ mavenRepoUrls ++ urls).elements
  }

  def setMavenRepositories(urls: ISZ[String]): Unit = {
    mavenRepoUrls = (HashSSet ++ urls).elements
  }

  def fetch(deps: ISZ[String]): ISZ[CoursierFileInfo] =
    fetchClassifiers(deps, ISZ(CoursierClassifier.Default))

  def fetchClassifiers(deps: ISZ[String], cls: ISZ[CoursierClassifier.Type]): ISZ[CoursierFileInfo] = {
    var fetch = Fetch().
      addDependencies(toDeps(deps): _*).
      withRepositories(repositories.elements ++ (for (url <- mavenRepoUrls.elements) yield MavenRepository(url.value)))
    for (cl <- cls.elements) cl match {
      case CoursierClassifier.Default => fetch = fetch.withMainArtifacts()
      case CoursierClassifier.Javadoc => fetch = fetch.addClassifiers(Classifier.javadoc)
      case CoursierClassifier.Sources => fetch = fetch.addClassifiers(Classifier.sources)
      case CoursierClassifier.Tests => fetch = fetch.addClassifiers(Classifier.tests)
    }
    cacheOpt match {
      case Some(cache) => fetch = fetch.withCache(coursier.cache.FileCache().withLocation(cache.string.value))
      case _ =>
    }
    ISZ((for (q <- fetch.runResult().detailedArtifacts) yield
      CoursierFileInfo(
        q._1.module.organization.value,
        q._1.module.name.value,
        q._1.version,
        Os.path(q._4.getCanonicalPath))): _*)
  }

  def isRuntimePublishedLocally(version: String): B = {
    val libKey = project.DependencyManager.libraryKey
    try {
      var fetch = Fetch().addDependencies(toDeps(ISZ(s"$libKey$version")): _*).
        withRepositories(Seq(localMavenRepo,sonatypeReleaseRepo)).
        withMainArtifacts()
      cacheOpt match {
        case Some(cache) => fetch = fetch.withCache(coursier.cache.FileCache().withLocation(cache.string.value))
        case _ =>
      }
      fetch.run()
      return T
    } catch {
      case _: Throwable => return F
    }
  }
}
