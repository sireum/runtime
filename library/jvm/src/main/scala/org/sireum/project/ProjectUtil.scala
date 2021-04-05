// #Sireum
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

package org.sireum.project

import org.sireum._

object ProjectUtil {

  val sharedSuffix: String = "shared"
  val jvmSuffix: String = "jvm"
  val jsSuffix: String = "js"

  @strictpure def id(baseId: String): ISZ[String] = ISZ(baseId)

  @strictpure def sharedId(baseId: String): ISZ[String] = ISZ(s"$baseId-$sharedSuffix")

  @strictpure def jvmId(baseId: String): ISZ[String] = ISZ(s"$baseId-$jvmSuffix")

  @strictpure def jsId(baseId: String): ISZ[String] = ISZ(s"$baseId-$jsSuffix")

  @strictpure def sharedJvmId(baseId: String): ISZ[String] = sharedId(baseId) ++ jvmId(baseId)

  @strictpure def sharedJsId(baseId: String): ISZ[String] = sharedId(baseId) ++ jsId(baseId)

  def dirs(base: Os.Path, segss: ISZ[ISZ[String]]): ISZ[String] = {
    var r = ISZ[String]()
    for (segs <- segss) {
      var p = base
      for (seg <- segs) {
        p = p / seg
      }
      if (p.exists) {
        r = r :+ st"${Os.fileSep}${(segs, Os.fileSep)}".render
      }
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

  @pure def moduleShared(id: String,
                         baseDir: Os.Path,
                         sharedDeps: ISZ[String],
                         sharedIvyDeps: ISZ[String]): Module = {
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
      testResources = mavenTestResourceDirs(sharedDir)
    )
    return shared
  }

  @pure def moduleJvm(id: String,
                      baseDir: Os.Path,
                      jvmDeps: ISZ[String],
                      jvmIvyDeps: ISZ[String]): Module = {
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
      testResources = mavenTestResourceDirs(jvmDir)
    )
    return jvm
  }

  @pure def moduleJs(id: String,
                     baseDir: Os.Path,
                     jsDeps: ISZ[String],
                     jsIvyDeps: ISZ[String]): Module = {
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
      testResources = mavenTestResourceDirs(jsDir)
    )
    return js
  }

  @pure def moduleSharedJvm(baseId: String,
                            baseDir: Os.Path,
                            sharedDeps: ISZ[String],
                            sharedIvyDeps: ISZ[String],
                            jvmDeps: ISZ[String],
                            jvmIvyDeps: ISZ[String]): (Module, Module) = {
    val shared = moduleShared(s"$baseId-$sharedSuffix", baseDir, sharedDeps, sharedIvyDeps)
    val jvm = moduleJvm(baseId, baseDir, jvmDeps :+ shared.id, jvmIvyDeps)
    return (shared, jvm)
  }

  @pure def moduleSharedJs(baseId: String,
                           baseDir: Os.Path,
                           sharedDeps: ISZ[String],
                           sharedIvyDeps: ISZ[String],
                           jsDeps: ISZ[String],
                           jsIvyDeps: ISZ[String]): (Module, Module) = {
    val shared = moduleShared(s"$baseId-$sharedSuffix", baseDir, sharedDeps, sharedIvyDeps)
    val js = moduleJs(baseId, baseDir, jsDeps :+ shared.id, jsIvyDeps)
    return (shared, js)
  }

  @pure def moduleSharedJvmJs(baseId: String,
                              baseDir: Os.Path,
                              sharedDeps: ISZ[String],
                              sharedIvyDeps: ISZ[String],
                              jvmDeps: ISZ[String],
                              jvmIvyDeps: ISZ[String],
                              jsDeps: ISZ[String],
                              jsIvyDeps: ISZ[String]): (Module, Module, Module) = {
    val (shared, js) = moduleSharedJs(baseId, baseDir, sharedDeps, sharedIvyDeps, jsDeps, jsIvyDeps)
    val jvm = moduleJvm(baseId, baseDir, jvmDeps :+ shared.id, jvmIvyDeps)
    return (shared, jvm, js(id = s"$baseId-$jsSuffix"))
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
    for (line <- ops.StringOps(text).split((c: C) => c === '\n')) {
      if (ops.StringOps(line).startsWith("{  \"type\" : \"Project\"")) {
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
        JSON.toProject(pJsonFile.read) match {
          case Either.Left(prj) =>
            println(s"Loading from $pJsonFile ...")
            r = r ++ prj
            loaded = T
          case _ =>
        }
      }
      if (!loaded) {
        println(s"Loading from $cmdFile ...")
        val pr = proc"$cmdFile json".run()
        if (pr.ok) {
          projectJsonLine(pr.out) match {
            case Some(line) => JSON.toProject(line) match {
              case Either.Left(prj) =>
                r = r ++ prj
                loaded = T
                pJsonFile.writeOver(JSON.fromProject(prj, F))
                println(s"Wrote $pJsonFile")
              case _ =>
            }
            case _ =>
          }
        }
      }
      if (!loaded) {
        eprintln(s"Failed to load from $cmdFile")
        return None()
      }
    }
    return Some(r)
  }
}
