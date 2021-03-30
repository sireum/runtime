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

object Project {
  @strictpure def empty: Project = Project(HashSMap.empty, Poset.empty)
}

@datatype class Project(val modules: HashSMap[String, Module],
                        val poset: Poset[String]) {

  @strictpure def +(module: Module): Project = Project(
    modules = modules + module.id ~> module,
    poset = poset.addParents(module.id, module.deps)
  )

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
    return r
  }

}

@enum object Target {
  'Jvm
  'Js
}

@datatype class Module(val id: String,
                       val basePath: String,
                       val deps: ISZ[String],
                       val targets: ISZ[Target.Type],
                       val ivyDeps: ISZ[String],
                       val sources: ISZ[String],
                       val resources: ISZ[String],
                       val testSources: ISZ[String])

object Module {
  val allTargets: ISZ[Target.Type] = ISZ(Target.Jvm, Target.Js)
}
