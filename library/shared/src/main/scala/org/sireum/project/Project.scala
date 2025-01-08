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


package org.sireum.project

import org.sireum._

object Project {
  @strictpure def empty: Project = Project(HashSMap.empty, Poset.empty, ISZ())
}

@datatype class Project(val modules: HashSMap[String, Module],
                        val poset: Poset[String],
                        val mavenRepoUrls: ISZ[String]) {

  @pure def +(module: Module): Project = {
    return Project(
      modules = modules + module.id ~> module,
      poset = poset.addParents(module.id, module.deps),
      mavenRepoUrls = mavenRepoUrls
    )
  }

  @strictpure def addMavenRepository(url: String): Project = Project(
    modules = modules, poset = poset, mavenRepoUrls = mavenRepoUrls :+ url
  )

  @strictpure def addMavenRepositories(urls: ISZ[String]): Project = Project(
    modules = modules, poset = poset, mavenRepoUrls = mavenRepoUrls ++ urls
  )

  @pure override def hash: Z = {
    return modules.hash
  }

  @strictpure def isEqual(other: Project): B = modules == other.modules

  @pure def <=(other: Project): B = {
    return modules == (other.modules -- (other.modules.keys -- modules.keys))
  }

  @pure def >=(other: Project): B = {
    return (modules -- (modules.keys -- other.modules.keys)) == other.modules
  }

  @pure def ++(other: Project): Project = {
    var r = this
    var names = other.poset.rootNodes
    while (names.nonEmpty) {
      var newNames = HashSSet.empty[String]
      for (name <- names) {
        other.modules.get(name) match {
          case Some(m) => r = r + m
          case _ =>
        }
        newNames = newNames.union(other.poset.childrenOf(name))
      }
      names = newNames.elements
    }
    r = r(mavenRepoUrls = (HashSSet ++ r.mavenRepoUrls ++ other.mavenRepoUrls).elements)
    return r
  }

  @pure def stripPubInfo: Project = {
    var r = Project.empty
    for (m <- modules.values) {
      r = r + m(publishInfoOpt = None())
    }
    return r
  }

  @pure def slice(mids: ISZ[String]): Project = {
    if (mids.isEmpty) {
      return this
    }
    var tmids = HashSet.empty[String]
    def rec(mid: String): Unit = {
      tmids = tmids + mid
      modules.get(mid) match {
        case Some(m) =>
          for (mDep <- m.deps) {
            rec(mDep)
          }
        case _ =>
      }
    }
    for (mid <- mids) {
      rec(mid)
    }
    var r = Project.empty
    for (p <- modules.entries if tmids.contains(p._1)) {
      r = r + p._2
    }
    return r
  }

  @pure def openDeps: Map[String, ISZ[String]] = {
    val mids = modules.keys
    var r = Map.empty[String, ISZ[String]]
    for (m <- modules.values) {
      val diff = m.deps -- mids
      if (diff.nonEmpty) {
        r = r + m.id ~> diff
      }
    }
    return r
  }

  @pure def illTargets: Map[String, Map[String, ISZ[Target.Type]]] = {
    var r = Map.empty[String, Map[String, ISZ[Target.Type]]]
    for (m <- modules.values) {
      var map = Map.empty[String, ISZ[Target.Type]]
      for (mDep <- m.deps) {
        modules.get(mDep) match {
          case Some(m2) =>
            val diff = m.targets -- m2.targets
            if (diff.nonEmpty) {
              map = map + m2.id ~> diff
            }
          case _ =>
        }
      }
      if (map.nonEmpty) {
        r = r + m.id ~> map
      }
    }
    return r
  }

}

@enum object Target {
  "Jvm"
  "Js"
}

@datatype class Module(val id: String,
                       val basePath: String,
                       val subPathOpt: Option[String],
                       val deps: ISZ[String],
                       val targets: ISZ[Target.Type],
                       val ivyDeps: ISZ[String],
                       val sources: ISZ[String],
                       val resources: ISZ[String],
                       val testSources: ISZ[String],
                       val testResources: ISZ[String],
                       val publishInfoOpt: Option[PublishInfo]) {

  @pure def hasTarget(target: Target.Type): B = {
    return ops.ISZOps(targets).contains(target)
  }

}

object Module {
  val allTargets: ISZ[Target.Type] = ISZ(Target.Jvm, Target.Js)
}

@datatype class PublishInfo(val description: String,
                            val url: String,
                            val licenses: ISZ[PublishInfo.License],
                            val developers: ISZ[PublishInfo.Developer])

object PublishInfo {

  @datatype class License(val name: String,
                          val url: String,
                          val distribution: String)

  @datatype class Developer(val id: String,
                            val name: String)

}
