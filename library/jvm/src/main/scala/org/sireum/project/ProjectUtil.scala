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
import org.sireum.project.PublishInfo._

object ProjectUtil {

  val sharedSuffix: String = "shared"
  val jvmSuffix: String = "jvm"
  val jsSuffix: String = "js"

  val bsd2License: License = License(
    name = "BSD 2-Clause",
    url = "https://github.com/sireum/kekinian/blob/master/license.txt",
    distribution = "repo"
  )
  val bsd2: ISZ[License] = ISZ(bsd2License)

  val robby: Developer = Developer(id = "robby-phd", name = "Robby")
  val johnHatcliff: Developer = Developer(id = "John-Hatcliff", name = "John Hatcliff")
  val jasonBelt: Developer = Developer(id = "jasonbelt", name = "Jason Belt")
  val thari: Developer = Developer(id = "thari", name = "Hariharan Thiagarajan")
  val matthewWeis: Developer = Developer(id = "matthewweis", name = "Matthew Weis")

  @strictpure def id(baseId: String): ISZ[String] = ISZ(baseId)

  @strictpure def sharedId(baseId: String): ISZ[String] = ISZ(s"$baseId-$sharedSuffix")

  @strictpure def jvmId(baseId: String): ISZ[String] = ISZ(s"$baseId-$jvmSuffix")

  @strictpure def jsId(baseId: String): ISZ[String] = ISZ(s"$baseId-$jsSuffix")

  @strictpure def sharedJvmId(baseId: String): ISZ[String] = sharedId(baseId) ++ jvmId(baseId)

  @strictpure def sharedJsId(baseId: String): ISZ[String] = sharedId(baseId) ++ jsId(baseId)

  def dirs(base: Os.Path, segss: ISZ[ISZ[String]]): ISZ[String] = {
    var r = ISZ[String]()
    for (segs <- segss) {
      r = r :+ st"${(segs, Os.fileSep)}".render
    }
    return r
  }

  def mavenSourceDirs(base: Os.Path): ISZ[String] = {
    return dirs(base, ISZ(
      ISZ("src", "main", "java"),
      ISZ("src", "main", "scala")))
  }

  def mavenTestSourceDirs(base: Os.Path): ISZ[String] = {
    return dirs(base, ISZ(
      ISZ("src", "test", "java"),
      ISZ("src", "test", "scala")))
  }

  def mavenResourceDirs(base: Os.Path): ISZ[String] = {
    return dirs(base, ISZ(ISZ("src", "main", "resources")))
  }

  def mavenTestResourceDirs(base: Os.Path): ISZ[String] = {
    return dirs(base, ISZ(ISZ("src", "test", "resources")))
  }

  @pure def moduleSharedPub(id: String,
                            baseDir: Os.Path,
                            sharedDeps: ISZ[String],
                            sharedIvyDeps: ISZ[String],
                            pubOpt: Option[PublishInfo]): Module = {
    val sharedDir = baseDir / sharedSuffix
    val shared = Module(
      id = id,
      basePath = baseDir.string,
      subPathOpt = Some(s"${Os.fileSep}$sharedSuffix"),
      deps = sharedDeps,
      targets = Module.allTargets,
      ivyDeps = sharedIvyDeps,
      sources = mavenSourceDirs(sharedDir),
      resources = mavenResourceDirs(sharedDir),
      testSources = mavenTestSourceDirs(sharedDir),
      testResources = mavenTestResourceDirs(sharedDir),
      publishInfoOpt = pubOpt
    )
    return shared
  }

  @pure def moduleShared(id: String,
                               baseDir: Os.Path,
                               sharedDeps: ISZ[String],
                               sharedIvyDeps: ISZ[String]): Module = {
    return moduleSharedPub(
      id = id,
      baseDir = baseDir,
      sharedDeps = sharedDeps,
      sharedIvyDeps = sharedIvyDeps,
      pubOpt = None()
    )
  }

  @pure def moduleJvmPub(id: String,
                         baseDir: Os.Path,
                         jvmDeps: ISZ[String],
                         jvmIvyDeps: ISZ[String],
                         pubOpt: Option[PublishInfo]): Module = {
    val jvmDir = baseDir / jvmSuffix
    val jvm = Module(
      id = id,
      basePath = baseDir.string,
      subPathOpt = Some(s"${Os.fileSep}$jvmSuffix"),
      deps = jvmDeps,
      targets = ISZ(Target.Jvm),
      ivyDeps = jvmIvyDeps,
      sources = mavenSourceDirs(jvmDir),
      resources = mavenResourceDirs(jvmDir),
      testSources = mavenTestSourceDirs(jvmDir),
      testResources = mavenTestResourceDirs(jvmDir),
      publishInfoOpt = pubOpt
    )
    return jvm
  }

  @pure def moduleJvm(id: String,
                            baseDir: Os.Path,
                            jvmDeps: ISZ[String],
                            jvmIvyDeps: ISZ[String]): Module = {
    return moduleJvmPub(id, baseDir, jvmDeps, jvmIvyDeps, None())
  }

  @pure def moduleJsPub(id: String,
                        baseDir: Os.Path,
                        jsDeps: ISZ[String],
                        jsIvyDeps: ISZ[String],
                        pubOpt: Option[PublishInfo]): Module = {
    val jsDir = baseDir / jsSuffix
    val js = Module(
      id = id,
      basePath = baseDir.string,
      subPathOpt = Some(s"${Os.fileSep}$jsSuffix"),
      deps = jsDeps,
      targets = ISZ(Target.Js),
      ivyDeps = jsIvyDeps,
      sources = mavenSourceDirs(jsDir),
      resources = mavenResourceDirs(jsDir),
      testSources = mavenTestSourceDirs(jsDir),
      testResources = mavenTestResourceDirs(jsDir),
      publishInfoOpt = pubOpt
    )
    return js
  }

  @pure def moduleJs(id: String,
                     baseDir: Os.Path,
                     jsDeps: ISZ[String],
                     jsIvyDeps: ISZ[String]): Module = {
    return moduleJsPub(
      id = id,
      baseDir = baseDir,
      jsDeps = jsDeps,
      jsIvyDeps = jsIvyDeps,
      pubOpt = None()
    )
  }

  @pure def moduleSharedJvmPub(baseId: String,
                               baseDir: Os.Path,
                               sharedDeps: ISZ[String],
                               sharedIvyDeps: ISZ[String],
                               jvmDeps: ISZ[String],
                               jvmIvyDeps: ISZ[String],
                               pubOpt: Option[PublishInfo]): (Module, Module) = {
    val shared = moduleSharedPub(s"$baseId-$sharedSuffix", baseDir, sharedDeps, sharedIvyDeps, pubOpt)
    val jvm = moduleJvmPub(baseId, baseDir, jvmDeps :+ shared.id, jvmIvyDeps, pubOpt)
    return (shared, jvm)
  }

  @pure def moduleSharedJvm(baseId: String,
                            baseDir: Os.Path,
                            sharedDeps: ISZ[String],
                            sharedIvyDeps: ISZ[String],
                            jvmDeps: ISZ[String],
                            jvmIvyDeps: ISZ[String]): (Module, Module) = {
    return moduleSharedJvmPub(
      baseId = baseId,
      baseDir = baseDir,
      sharedDeps = sharedDeps,
      sharedIvyDeps = sharedIvyDeps,
      jvmDeps = jvmDeps,
      jvmIvyDeps = jvmIvyDeps,
      pubOpt = None()
    )
  }


  @pure def moduleSharedJsPub(baseId: String,
                              baseDir: Os.Path,
                              sharedDeps: ISZ[String],
                              sharedIvyDeps: ISZ[String],
                              jsDeps: ISZ[String],
                              jsIvyDeps: ISZ[String],
                              pubOpt: Option[PublishInfo]): (Module, Module) = {
    val shared = moduleSharedPub(s"$baseId-$sharedSuffix", baseDir, sharedDeps, sharedIvyDeps, pubOpt)
    val js = moduleJsPub(baseId, baseDir, jsDeps :+ shared.id, jsIvyDeps, pubOpt)
    return (shared, js)
  }

  @pure def moduleSharedJs(baseId: String,
                           baseDir: Os.Path,
                           sharedDeps: ISZ[String],
                           sharedIvyDeps: ISZ[String],
                           jsDeps: ISZ[String],
                           jsIvyDeps: ISZ[String]): (Module, Module) = {
    return moduleSharedJsPub(
      baseId = baseId,
      baseDir = baseDir,
      sharedDeps = sharedDeps,
      sharedIvyDeps = sharedIvyDeps,
      jsDeps = jsDeps,
      jsIvyDeps = jsIvyDeps,
      pubOpt = None()
    )
  }

  @pure def moduleSharedJvmJsPub(baseId: String,
                                 baseDir: Os.Path,
                                 sharedDeps: ISZ[String],
                                 sharedIvyDeps: ISZ[String],
                                 jvmDeps: ISZ[String],
                                 jvmIvyDeps: ISZ[String],
                                 jsDeps: ISZ[String],
                                 jsIvyDeps: ISZ[String],
                                 pubOpt: Option[PublishInfo]): (Module, Module, Module) = {
    val (shared, js) = moduleSharedJsPub(baseId, baseDir, sharedDeps, sharedIvyDeps, jsDeps, jsIvyDeps, pubOpt)
    val jvm = moduleJvmPub(baseId, baseDir, jvmDeps :+ shared.id, jvmIvyDeps, pubOpt)
    return (shared, jvm, js(id = s"$baseId-$jsSuffix"))
  }

  @pure def moduleSharedJvmJs(baseId: String,
                              baseDir: Os.Path,
                              sharedDeps: ISZ[String],
                              sharedIvyDeps: ISZ[String],
                              jvmDeps: ISZ[String],
                              jvmIvyDeps: ISZ[String],
                              jsDeps: ISZ[String],
                              jsIvyDeps: ISZ[String]): (Module, Module, Module) = {
    return moduleSharedJvmJsPub(
      baseId = baseId,
      baseDir = baseDir,
      sharedDeps = sharedDeps,
      sharedIvyDeps = sharedIvyDeps,
      jvmDeps = jvmDeps,
      jvmIvyDeps = jvmIvyDeps,
      jsDeps = jsDeps,
      jsIvyDeps = jsIvyDeps,
      pubOpt = None()
    )
  }

  @pure def pub(desc: String,
                url: String,
                licenses: ISZ[PublishInfo.License],
                devs: ISZ[PublishInfo.Developer]): Option[PublishInfo] = {
    return Some(
      PublishInfo(
        description = desc,
        url = url,
        licenses = licenses,
        developers = devs
      )
    )
  }

  @pure def toDot(p: Project): String = {
    @pure def node2st(name: String): ST = {
      p.modules.get(name) match {
        case Some(m) =>
          val targets = HashSet.empty[Target.Type] ++ m.targets
          if (targets.contains(Target.Jvm) && targets.contains(Target.Js)) {
            return st"""[shape = "rect", label="$name"]"""
          } else if (targets.contains(Target.Jvm)) {
            return st"""[shape = "trapezium", label="$name"]"""
          } else {
            return st"""[shape = "invtrapezium", label="$name"]"""
          }
        case _ => return st"""[shape = "octagon", label="$name"]"""
      }
    }

    return p.poset.toST(node2st _).render
  }

  def projectCli(args: ISZ[String], project: Project): Unit = {

    def usage(): Unit = {
      println("Usage: [ json ]")
    }

    var isDot = T

    args match {
      case ISZ(string"json") => isDot = F
      case ISZ(string"-h") =>
        usage()
        Os.exit(0)
      case ISZ() =>
      case _ =>
        usage()
        Os.exit(-1)
    }

    if (isDot) {
      println(toDot(project))
    } else {
      println(JSON.fromProject(project, T))
    }
  }

  @pure def projectJsonLine(text: String): Option[String] = {
    val prefix = "{  \"type\" : \"Project\""
    for (line <- ops.StringOps(text).split((c: C) => c == '\n')) {
      var first = 0
      var last = line.size - 1
      val lineCis = conversions.String.toCis(line)
      while (first < line.size && lineCis(first) != '{') {
        first = first + 1
      }
      while (last >= 0 && lineCis(last) != '}') {
        last = last - 1
      }
      val slicedLine: String =
        if (first == 0 && last == line.size) line
        else conversions.String.fromCis(ops.ISZOps(lineCis).slice(first, last + 1))
      if (ops.StringOps(slicedLine).startsWith(prefix)) {
        return Some(line)
      }
    }
    return None()
  }

  def loadFromBaseDirs(baseDirs: ISZ[Os.Path]): Option[Project] = {
    var r = Project.empty
    for (baseDir <- baseDirs) {
      val pJsonFile = baseDir / "project.json"
      val cmdFile = baseDir / "bin" / "project.cmd"
      var loaded = F
      if (pJsonFile.exists && cmdFile.exists && cmdFile.lastModified < pJsonFile.lastModified) {
        load(pJsonFile) match {
          case Some(prj) =>
            println(s"Loading from $pJsonFile ...")
            r = r ++ prj
            loaded = T
          case _ =>
        }
      }
      if (!loaded) {
        println(s"Loading from $cmdFile ...")
        val cmds: ISZ[String] =
          if (Os.isWin) ISZ[String]("cmd", "/C", cmdFile.name, "json")
          else ISZ[String]("bash", "-c", s""""${cmdFile.canon.string}" json""")
        val pr = Os.proc(cmds).at(cmdFile.up.canon).env(ISZ("SIREUM_HOME" ~> Os.sireumHomeOpt.get.string)).
          console.outLineAction((s: String) => project.ProjectUtil.projectJsonLine(s).isEmpty).run()
        if (pr.ok) {
          projectJsonLine(pr.out) match {
            case Some(line) => JSON.toProject(line) match {
              case Either.Left(prj) =>
                r = r ++ prj
                loaded = T
                store(pJsonFile, prj)
                println(s"Wrote $pJsonFile")
              case _ =>
            }
            case _ =>
          }
        } else {
          eprintln(s"Failed to load from $cmdFile")
          println(pr.out)
          eprintln(pr.err)
        }
      }
      if (!loaded) {
        return None()
      }
    }
    return Some(r)
  }

  def load(path: Os.Path): Option[Project] = {
    if (!path.isFile) {
      return None()
    }
    val parser = org.sireum.project.JSON.Parser(path.read)
    val p = parser.parseProject()
    if (parser.errorOpt.nonEmpty) {
      return None()
    }
    var r = Project.empty(mavenRepoUrls = p.mavenRepoUrls)
    for (m <- p.modules.values) {
      r = r + m
    }
    return Some(r)
  }

  def store(path: Os.Path, prj: Project): Unit = {
    path.writeOver(org.sireum.project.JSON.fromProject(prj(poset = Poset.empty), F))
  }

  @pure def pathSep(base: Os.Path, sub: String): Os.Path = {
    return if (ops.StringOps(sub).startsWith(Os.fileSep)) Os.path(s"$base$sub")
    else base / sub
  }

  @pure def moduleBasePath(m: Module): Os.Path = {
    m.subPathOpt match {
      case Some(sp) => return pathSep(Os.path(m.basePath), sp)
      case _ => return Os.path(m.basePath)
    }
  }

  @pure def moduleSources(m: Module): ISZ[Os.Path] = {
    return for (p <- for (source <- m.sources) yield pathSep(moduleBasePath(m), source) if p.exists) yield p
  }

  @pure def moduleResources(m: Module): ISZ[Os.Path] = {
    return for (p <- for (resource <- m.resources) yield pathSep(moduleBasePath(m), resource) if p.exists) yield p
  }

  @pure def moduleTestSources(m: Module): ISZ[Os.Path] = {
    return for (p <- for (testSource <- m.testSources) yield pathSep(moduleBasePath(m), testSource) if p.exists) yield p
  }

  @pure def moduleTestResources(m: Module): ISZ[Os.Path] = {
    return for (p <- for (testResource <- m.testResources) yield pathSep(moduleBasePath(m), testResource) if p.exists) yield p
  }
}
