/*
 Copyright (c) 2021, Robby, Kansas State University
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

  var scalaVersion: String = scala.util.Properties.versionNumberString
  var cacheOpt: Option[cache.FileCache[util.Task]] = None()

  var repositories: ISZ[Repository] = ISZ(
    Repositories.sonatype("releases"),
    Repositories.jitpack
  )

  def toDeps(deps: ISZ[String]): Seq[Dependency] =
    parse.DependencyParser.dependencies(deps.elements.map(_.value), scalaVersion.value).
      either.getOrElse(halt(s"Invalid dependencies: $deps"))

  def setScalaVersion(version: String): Unit = {
    scalaVersion = version
  }

  def setCache(path: Os.Path): Unit = {
    cacheOpt = Some(cache.FileCache().withLocation(path.string.value))
  }

  def addMavenRepositories(urls: ISZ[String]): Unit = {
    repositories = repositories ++ (for (url <- urls) yield MavenRepository(url.value))
  }

  def fetch(deps: ISZ[String]): ISZ[CoursierFileInfo] =
    fetchClassifiers(deps, ISZ(CoursierClassifier.Default))

  def fetchClassifiers(deps: ISZ[String], cls: ISZ[CoursierClassifier.Type]): ISZ[CoursierFileInfo] = {
    var fetch = Fetch().
      addDependencies(toDeps(deps): _*).
      withRepositories(repositories.elements)
    for (cl <- cls.elements) cl match {
      case CoursierClassifier.Default => fetch = fetch.withMainArtifacts()
      case CoursierClassifier.Javadoc => fetch = fetch.addClassifiers(Classifier.javadoc)
      case CoursierClassifier.Sources => fetch = fetch.addClassifiers(Classifier.sources)
      case CoursierClassifier.Tests => fetch = fetch.addClassifiers(Classifier.tests)
    }
    cacheOpt match {
      case Some(cache) => fetch = fetch.withCache(cache)
      case _ =>
    }
    ISZ((for (q <- fetch.runResult().detailedArtifacts) yield
      CoursierFileInfo(
        q._1.module.organization.value,
        q._1.module.name.value,
        q._1.version,
        Os.path(q._4.getCanonicalPath))): _*)
  }
}
