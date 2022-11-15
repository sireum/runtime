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

import coursier._

object Coursier_Ext {

  val localMavenRepo: Repository = MavenRepository((Os.home / ".m2" / "repository").toUri.value)
  val sonatypeReleaseRepo: Repository = Repositories.sonatype("releases")

  val repositories: ISZ[Repository] = ISZ(
    localMavenRepo,
    sonatypeReleaseRepo,
    Repositories.jitpack
  )

  def toDeps(scalaVersion: String, deps: ISZ[String]): Seq[Dependency] =
    parse.DependencyParser.dependencies(deps.elements.map(_.value), scalaVersion.value).
      either.getOrElse(halt(s"Invalid dependencies: $deps"))

  def fetch(deps: ISZ[String]): ISZ[CoursierFileInfo] = fetchClassifiers(None(), ISZ(), deps, ISZ(CoursierClassifier.Default))

  def fetchClassifiers(cacheOpt: Option[Os.Path], mavenRepoUrls: ISZ[String], deps: ISZ[String],
                       cls: ISZ[CoursierClassifier.Type]): ISZ[CoursierFileInfo] = {
    val scalaVersion: String = scala.util.Properties.versionNumberString
    var fetch = Fetch().
      addDependencies(toDeps(scalaVersion, deps): _*).
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
        q._4.getCanonicalPath)): _*)
  }

  def isRuntimePublishedLocally(version: String): B = {
    val scalaVersion: String = scala.util.Properties.versionNumberString
    val scalaVer = ops.StringOps(scalaVersion).substring(0, ops.StringOps(scalaVersion).lastIndexOf('.'))
    val path = Os.home / ".m2" / "repository" / "org" / "sireum" / "kekinian" / s"library_$scalaVer" / version /
      s"library_$scalaVer-$version.jar"
    path.exists
  }
}
