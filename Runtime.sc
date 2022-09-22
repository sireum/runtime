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

import mill._
import mill.scalalib._
import org.sireum.mill.SireumModule._
import org.sireum.mill.SireumModule.scalaVersion

//interp.load.ivy("com.lihaoyi" %% "mill-contrib-bsp" % System.getProperty("MILL_VERSION"))

trait Module extends CrossJvmJsJitPack {

  final override def subUrl: String = "runtime"

  final override def developers = Seq(Developers.robby)

  final override lazy val scalacPluginIvyDeps = Agg(
    ivy"org.sireum::scalac-plugin:$scalacPluginVersion"
  )

  final override def jvmTestIvyDeps = Agg.empty

  final override def jsTestIvyDeps = Agg.empty

  final override def jvmDeps = Seq()

  final override def jsDeps = Seq()

}

object Module {

  trait Macros extends Module {

    final override def description: String = "Sireum Runtime Macros"

    final override def artifactName = "macros"

    final override def ivyDeps = Agg(
      ivy"org.scala-lang:scala-reflect:$scalaVersion",
      ivy"org.scala-lang.modules::scala-parallel-collections:$parCollectionVersion",
      ivy"org.scala-lang.modules::scala-java8-compat:$java8CompatVersion"
    )

    final override def testIvyDeps = Agg.empty

    final override def testScalacPluginIvyDeps = Agg.empty

    final override def deps = Seq()

    final override def jvmTestFrameworks = Seq()

    final override def jsTestFrameworks = Seq()
  }

  trait Test extends Module {

    final override def description: String = "Sireum Test Library"

    final override def artifactName = "test"

    final override def testIvyDeps = Agg.empty

    final override def ivyDeps = Agg(
      ivy"org.scalatest::scalatest::$scalaTestVersion"
    )

    final override def testScalacPluginIvyDeps = scalacPluginIvyDeps

    final override def deps = Seq(macrosObject)

    final override val jvmTestFrameworks = Seq("org.scalatest.tools.Framework")

    final override def jsTestFrameworks = jvmTestFrameworks

    def macrosObject: Macros
  }

  trait TestProvider {

    def testObject: CrossJvmJsPublish

  }

  trait Library extends Module with TestProvider {

    final override def description: String = "Sireum Runtime Library"

    final override def artifactName = "library"

    final override def testIvyDeps = Agg.empty

    final override def ivyDeps = Agg.empty

    final override def jvmIvyDeps = Agg(
      ivy"com.zaxxer:nuprocess:$nuProcessVersion",
      ivy"com.lihaoyi::os-lib:$osLibVersion",
      ivy"org.kohsuke:github-api:$githubVersion",
      ivy"io.get-coursier::coursier:$coursierVersion",
      ivy"org.ow2.asm:asm:$asmVersion",
      ivy"org.antlr:antlr-runtime:$antlr3Version",
      ivy"org.sireum:automaton:$automatonVersion",
      ivy"org.sireum:presentasi-jfx:$presentasiJfxVersion",
    )

    final override def testScalacPluginIvyDeps = scalacPluginIvyDeps

    final override def deps = Seq(macrosObject)

    final override def testDeps = Seq(testObject.shared)

    final override def jsTestDeps = Seq(testObject.js)

    final override val jvmTestFrameworks = Seq("org.scalatest.tools.Framework")

    final override def jsTestFrameworks = jvmTestFrameworks

    def macrosObject: Macros

  }

}
