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

import GitHub._
import org.kohsuke.github.{GHRelease, GHRepository, GitHub => GH}
import org.sireum.$internal.CollectionCompat.Converters._

object GitHub_Ext {
  def connectRepo(owner: String, name: String): GHRepository = {
    val token = System.getenv("GITHUB_TOKEN")
    if (token != null) GH.connectUsingOAuth(token).getRepository(s"$owner/$name")
    else GH.connectAnonymously().getRepository(s"$owner/$name")
  }

  def toRelease(repo: Repository, ghRelease: GHRelease): Release =
    Release(
      repo,
      ghRelease.getId,
      ghRelease.getName,
      ghRelease.getPublished_at.getTime,
      ghRelease.isDraft,
      ghRelease.isPrerelease,
      ghRelease.getTagName,
      ghRelease.getTargetCommitish,
      ghRelease.getTarballUrl,
      ghRelease.getZipballUrl
    )

  def latestRelease(repo: Repository): Release = {
    val ghRepo = connectRepo(repo.owner, repo.name)
    toRelease(repo, ghRepo.getLatestRelease)
  }

  def releases(repo: Repository): Jen[Release] = {
    val ghRepo = connectRepo(repo.owner, repo.name)
    val ghReleases = ghRepo.listReleases
    new Jen[Release] {
      override def generate(f: Release => Jen.Action): Jen.Action = {
        var last = Jen.Continue
        for (e <- ghReleases.asScala) {
          last = f(toRelease(repo, e))
          if (!last) {
            return Jen.End
          }
        }
        return last
      }

      override def string: String = {
        return s"Jen($repo.releases)"
      }
    }
  }

  def assets(release: Release): Jen[Asset] = {
    val ghRepo = connectRepo(release.repo.owner, release.repo.name)
    val ghRelease = ghRepo.getRelease(release.id.toLong)
    new Jen[Asset] {
      override def generate(f: Asset => Jen.Action): Jen.Action = {
        var last = Jen.Continue
        for (ghAsset <- ghRelease.listAssets.asScala) {
          last = f(GitHub.Asset(
            release,
            ghAsset.getId,
            ghAsset.getName,
            ghAsset.getLabel,
            ghAsset.getState,
            ghAsset.getSize,
            ghAsset.getContentType,
            ghAsset.getBrowserDownloadUrl,
            ghAsset.getDownloadCount
          ))
          if (!last) {
            return Jen.End
          }
        }
        return last
      }

      override def string: String = {
        return s"Jen($release.listAssets)"
      }
    }
  }

  def submoduleShaOf(repo: Repository, path: String, ref: String): String =
    connectRepo(repo.owner, repo.name).getFileContent(path.value, ref.value).getSha
}
