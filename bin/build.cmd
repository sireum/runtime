::#! 2> /dev/null                                              #
@ 2>/dev/null # 2>nul & echo off & goto BOF                    #
exec $(cd `dirname $0` && pwd)/sireum slang run -s "$0" "$@"   #
:BOF
%~dp0sireum.bat slang run -s "%0" %*
exit /B %errorlevel%
::!#
// #Sireum
import org.sireum._

if (Os.cliArgs.size != 2) {
  println("Usage: ( compile | test | m2 )")
}

val homeBin = Os.path(Os.cliArgs(0))
val home = homeBin.up
val sireumJar = homeBin / "sireum.jar"
val mill = homeBin / "mill.bat"

def downloadMill(): Unit = {
  if (!mill.exists) {
    println("Downloading mill ...")
    mill.downloadFrom("http://files.sireum.org/mill-standalone")
    mill.chmod("+x")
    println()
  }
}

def compile(): Unit = {
  def triggerJitPack(): Unit = {
    if (Os.isMac) {
      println("Triggering jitpack ...")
      val r = Os.proc(ISZ(mill.string, "jitPack", "--owner", "sireum", "--repo", "runtime", "--lib", "library")).run()
      r match {
        case r: Os.Proc.Result.Normal =>
          println(r.out)
          println(r.err)
          if (!r.ok) {
            eprintln(s"Exit code: ${r.exitCode}")
          }
        case r: Os.Proc.Result.Exception =>
          eprintln(s"Exception: ${r.err}")
        case _: Os.Proc.Result.Timeout =>
          eprintln("Timout")
          eprintln()
      }
      println()
    }
  }

  def tipe(): Unit = {
    println("Slang type checking ...")
    Os.proc(ISZ("java", "-jar", sireumJar.string,
      "slang", "tipe", "--verbose", "-r", "-s", "library")).at(home).console.runCheck()
    println()
  }

  triggerJitPack()
  tipe()
  println("Compiling ...")
  Os.proc(ISZ(mill.string, "all", "runtime.library.jvm.tests.compile")).at(home).console.runCheck()
  println()
}

def test(): Unit = {
  compile()
  println("Running shared tests ...")
  Os.proc(ISZ(mill.string, "runtime.library.shared.tests")).at(home).console.runCheck()
  println()

  println("Running jvm tests ...")
  Os.proc(ISZ(mill.string, "runtime.library.jvm.tests")).at(home).console.runCheck()

  if (Os.proc(ISZ("node", "-v")).run().ok) {
    println()
    println("Running js tests ...")
    Os.proc(ISZ(mill.string, "runtime.library.js.tests")).at(home).console.runCheck()
  }
}

def m2(): Unit = {
  Os.proc(ISZ(mill.string, "all",
    "runtime.macros.shared.m2", "runtime.macros.jvm.m2", "runtime.macros.js.m2",
    "runtime.library.shared.m2", "runtime.library.jvm.m2", "runtime.library.js.m2",
    "runtime.test.shared.m2", "runtime.test.jvm.m2", "runtime.test.js.m2")).
    at(home).env(ISZ("SIREUM_SOURCE_BUILD" ~> "false")).console.runCheck()

  val repository = Os.home / ".m2" / "repository"
  repository.mkdirAll()

  println()
  println("Artifacts")
  for (cd <- ISZ(
    Os.cwd.up / "out" / "runtime" / "macros" / "shared" / "m2" / "dest",
    Os.cwd.up / "out" / "runtime" / "macros" / "jvm" / "m2" / "dest",
    Os.cwd.up / "out" / "runtime" / "macros" / "js" / "m2" / "dest",
    Os.cwd.up / "out" / "runtime" / "library" / "shared" / "m2" / "dest",
    Os.cwd.up / "out" / "runtime" / "library" / "jvm" / "m2" / "dest",
    Os.cwd.up / "out" / "runtime" / "library" / "js" / "m2" / "dest",
    Os.cwd.up / "out" / "runtime" / "test" / "shared" / "m2" / "dest",
    Os.cwd.up / "out" / "runtime" / "test" / "jvm" / "m2" / "dest",
    Os.cwd.up / "out" / "runtime" / "test" / "js" / "m2" / "dest"
  )
  ) {
    for (p <- cd.overlayMove(repository, F, F, _ => T, T).keys) {
      println(s"* $p")
    }
  }
  println()
}


downloadMill()

Os.cliArgs(1) match {
  case string"compile" => compile()
  case string"test" => test()
  case string"m2" => m2()
}