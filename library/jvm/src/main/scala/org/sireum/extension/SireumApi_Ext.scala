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

package org.sireum.extension

import org.sireum._

object SireumApi_Ext {
  def run(args: ISZ[String]): Z = {
    def err(): Z = {
      eprintln(s"Could not launch a nested Sireum instance")
      return -1
    }
    val sireumObject = Class.forName("org.sireum.Sireum$").getField("MODULE$").get(null)
    val sireumApiRun = Class.forName("org.sireum.SireumApi").getMethods.filter(m => m.getName == "run")
    if (sireumApiRun.isEmpty) {
      Os.env("SIREUM_HOME") match {
        case Some(home) =>
          val bin = Os.path(home) / "bin"
          val binPlatform: Os.Path = Os.kind match {
            case Os.Kind.Mac => bin / "mac"
            case Os.Kind.Win => bin / "win"
            case Os.Kind.Linux => bin / "linux"
            case Os.Kind.LinuxArm => bin / "linux" / "arm"
            case _ => return err()
          }
          val javaExe = binPlatform / "java" / "bin" / (if (Os.isWin) "java.exe" else "java")
          Os.proc(ISZ[String](javaExe.string, "-jar", (bin / "sireum.jar").string) ++ args).console.runCheck().exitCode
        case _ =>
          return err()
      }
    } else {
      sireumApiRun.head.invoke(sireumObject, args).asInstanceOf[Z]
    }
  }
}