val scalaVer = "2.12.2"

val metaVersion = "1.7.0"

val paradiseVersion = "3.0.0-M8"

val silencerVersion = "0.5"

val runtimeVersion = "3.0.1-1-SNAPSHOT"

val sireumScalacVersion = "3.0.0-3"

val commonSettings = Seq(
  organization := "org.sireum",
  incOptions := incOptions.value.withNameHashing(true),
  incOptions := incOptions.value.withLogRecompileOnMacro(false),
  retrieveManaged := true,
  version := runtimeVersion,
  scalaVersion := scalaVer,
  scalacOptions := Seq("-target:jvm-1.8", "-deprecation",
    "-Ydelambdafy:method", "-feature", "-unchecked", "-Xfatal-warnings"),
  parallelExecution in Test := true,
  libraryDependencies ++= Seq(
    "org.scalameta" %% "scalameta" % metaVersion
  ),
  resolvers += Resolver.sonatypeRepo("public"),
  addCompilerPlugin("org.scalameta" % "paradise" % paradiseVersion cross CrossVersion.full),
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  pomExtra :=
    <url>https://github.com/sireum/v3-runtime/</url>
      <licenses>
        <license>
          <name>Simplified BSD License</name>
          <url>https://github.com/sireum/v3-runtime/blob/master/license.md</url>
        </license>
      </licenses>
      <scm>
        <url>https://github.com/sireum/v3-runtime.git</url>
        <connection>scm:git:https://github.com/sireum/v3-runtime.git</connection>
      </scm>
      <developers>
        <developer>
          <id>robby-phd</id>
          <name>Robby</name>
          <url>http://cs.ksu.edu/~robby</url>
        </developer>
      </developers>
)

lazy val sireumRuntime = Project(
  id = "sireum-runtime",
  base = file("native/jvm"),
  settings = commonSettings ++ Seq(
    name := "runtime",
    unmanagedSourceDirectories in Compile += baseDirectory.value / "src-pure/main/scala",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVer,
      "org.spire-math" %% "spire" % "0.13.0"
    )
  )
)

lazy val sireumPrelude = Project(
  id = "sireum-prelude",
  base = file("api/jvm"),
  settings = commonSettings ++ Seq(
    name := "prelude",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.1" % "test",
      "com.github.ghik" %% "silencer-lib" % silencerVersion
    ),
    addCompilerPlugin("com.github.ghik" %% "silencer-plugin" % silencerVersion),
    addCompilerPlugin("org.sireum" %% "scalac-plugin" % sireumScalacVersion)
  )
).dependsOn(sireumRuntime)