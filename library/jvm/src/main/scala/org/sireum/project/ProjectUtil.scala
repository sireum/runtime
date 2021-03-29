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
        r = r :+ p.string
      }
    }
    return r
  }

  def mavenSourceDirs(base: Os.Path): ISZ[String] = {
    return dirs(base, ISZ(
      ISZ("src", "main", "java"),
      ISZ("src", "main", "scala"),
      ISZ("src", "test", "java"),
      ISZ("src", "test", "scala")))
  }

  def mavenResourceDirs(base: Os.Path): ISZ[String] = {
    return dirs(base, ISZ(ISZ("src", "main", "resources"), ISZ("src", "test", "resources")))
  }

  @pure def moduleShared(id: String,
                         baseDir: Os.Path,
                         sharedDeps: ISZ[String],
                         sharedIvyDeps: ISZ[String]): Module = {
    val sharedDir = baseDir / sharedSuffix
    val shared = Module(
      id = id,
      deps = sharedDeps,
      targets = Module.allTargets,
      ivyDeps = sharedIvyDeps,
      sourcePaths = mavenSourceDirs(sharedDir),
      resourcePaths = mavenResourceDirs(sharedDir)
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
      deps = jvmDeps,
      targets = ISZ(Target.Jvm),
      ivyDeps = jvmIvyDeps,
      sourcePaths = mavenSourceDirs(jvmDir),
      resourcePaths = mavenResourceDirs(jvmDir)
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
      deps = jsDeps,
      targets = ISZ(Target.Js),
      ivyDeps = jsIvyDeps,
      sourcePaths = mavenSourceDirs(jsDir),
      resourcePaths = mavenResourceDirs(jsDir)
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
    var js = moduleJs(baseId, baseDir, jsDeps, jsIvyDeps)
    js = js(sourcePaths = shared.sourcePaths ++ js.sourcePaths, resourcePaths = shared.resourcePaths ++ js.resourcePaths)
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
        case _ => return st"$name"
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
}
