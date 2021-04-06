::#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF         #
if [ -z ${SIREUM_HOME} ]; then                      #
  echo "Please set SIREUM_HOME env var"             #
  exit -1                                           #
fi                                                  #
exec ${SIREUM_HOME}/bin/sireum slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
%SIREUM_HOME%\bin\sireum.bat slang run -n "%0" %*
exit /B %errorlevel%
::!#
// #Sireum

import org.sireum._
import org.sireum.project.{Project, JSON}
import org.sireum.project.ProjectUtil._

val macros = "macros"
val library = "library"
val test = "test"

val homeDir = Os.slashDir.up.canon

val macrosShared = moduleShared(
  id = macros,
  baseDir = homeDir / macros,
  sharedDeps = ISZ(),
  sharedIvyDeps = ISZ(
    "org.scala-lang:scala-library:",
    "org.scala-lang.modules::scala-parallel-collections:",
    "org.scala-lang.modules::scala-java8-compat:"
  )
)

val testShared = moduleShared(
  id = test,
  baseDir = homeDir / test,
  sharedDeps = ISZ(macrosShared.id),
  sharedIvyDeps = ISZ("org.scalatest::scalatest::")
)

val (libraryShared, libraryJvm) = moduleSharedJvm(
  baseId = library,
  baseDir = homeDir / library,
  sharedDeps = ISZ(testShared.id),
  sharedIvyDeps = ISZ(),
  jvmDeps = ISZ(),
  jvmIvyDeps = ISZ(
    "com.zaxxer:nuprocess:",
    "com.lihaoyi::os-lib:",
    "org.kohsuke:github-api:",
    "io.get-coursier::coursier:"
  )
)

val project = Project.empty + macrosShared + testShared + libraryShared + libraryJvm

projectCli(Os.cliArgs, project)