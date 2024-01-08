// #Sireum
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

object OsProto {

  @sig trait Path

  object Proc {
    @sig trait Result {
      def ok: B = {
        return exitCode == 0
      }
      def out: String
      def err: String
      def exitCode: Z
    }
  }

  @sig trait Proc {

    @pure def commands(cs: ISZ[String]): Proc

    @pure def at(dir: Path): Proc

    @pure def env(m: ISZ[(String, String)]): Proc

    @pure def input(content: String): Proc

    @pure def timeout(millis: Z): Proc

    @pure def dontInheritEnv: Proc

    @pure def redirectErr: Proc

    @pure def bufferErr: Proc

    @pure def console: Proc

    @pure def echoEnv: Proc

    @pure def echo: Proc

    @pure def standard: Proc

    @pure def script: Proc

    @pure def outLineAction(f: String => B): Proc

    @pure def errLineAction(f: String => B): Proc

    @pure def shouldPrintCommands: B

    @pure def shouldOutputConsole: B

    @pure def isErrAsOut: B

    @pure def in: Option[String]

    @pure def cmds: ISZ[String]

    def run(): Proc.Result

    def runCheck(): Proc.Result
  }

}
