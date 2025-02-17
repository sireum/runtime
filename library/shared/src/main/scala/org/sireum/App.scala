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

package org.sireum

import org.sireum.$internal.###

trait App {

  def main(args: ISZ[String]): Z

  final def main(args: Array[Predef.String]): Unit = {
    ###(!("true" == System.getenv("PROYEK_JS") || scala.util.Try(Class.forName("scala.scalajs.js.Any", false, getClass.getClassLoader)).isSuccess)) {
      Runtime.getRuntime.addShutdownHook(new Thread {
        override def run(): Unit = {
          atExit()
        }
      })
    }
    App.args = ISZ(args.toIndexedSeq.map(String(_)): _*)
    val r = main(App.args).toInt
    ###(!("true" == System.getenv("PROYEK_JS") || scala.util.Try(Class.forName("scala.scalajs.js.Any", false, getClass.getClassLoader)).isSuccess)) {
      System.exit(r)
    }
    return
  }

  def atExit(): Unit = {}
}

object App {
  var args: ISZ[String] = ISZ()
}