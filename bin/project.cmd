::#! 2> /dev/null                                             #
@ 2>/dev/null # 2>nul & echo off & goto BOF                   #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then             #
  exec "$0.com" "$@"                                          #
fi                                                            #
rm -f "$0.com"                                                #
if [ -z ${SIREUM_HOME} ]; then                                #
  echo "Please set SIREUM_HOME env var"                       #
  exit -1                                                     #
fi                                                            #
exec ${SIREUM_HOME}/bin/sireum slang run -n "$0" "$@"         #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
set NEWER=False
if exist %~dpnx0.com for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dpnx0).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dpnx0.com" > nul 2>&1
%SIREUM_HOME%\bin\sireum.bat slang run -n "%0" %*
exit /B %errorlevel%
:native
%~dpnx0.com %*
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
    "org.scala-lang:scala-reflect:",
    "org.scala-lang.modules::scala-parallel-collections:",
    "org.scala-lang.modules::scala-java8-compat:"
  )
)

val (libraryShared, libraryJvm) = moduleSharedJvm(
  baseId = library,
  baseDir = homeDir / library,
  sharedDeps = ISZ(macrosShared.id),
  sharedIvyDeps = ISZ(),
  jvmDeps = ISZ(),
  jvmIvyDeps = ISZ(
    "com.zaxxer:nuprocess:",
    "com.lihaoyi::os-lib:",
    "org.kohsuke:github-api:"
  )
)

val testShared = moduleShared(
  id = test,
  baseDir = homeDir / test,
  sharedDeps = ISZ(macrosShared.id),
  sharedIvyDeps = ISZ("org.scalatest::scalatest::")
)

val project = Project.empty + macrosShared + libraryShared + libraryJvm + testShared

println(JSON.fromProject(project, T))
