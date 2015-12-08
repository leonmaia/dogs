name := "dogs"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-language:postfixOps",
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Ywarn-unused-import",
  "-unchecked",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture",
  "-Xlint"
)

scalacOptions in(Compile, console) ~= (_ filterNot (_ == "-Ywarn-unused-import"))

scalacOptions in(Test, console) := (scalacOptions in(Compile, console)).value

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Twitter repo" at "http://maven.twttr.com/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Mesosphere Public Repository" at "http://downloads.mesosphere.io/maven",
  Resolver.bintrayRepo("websudos", "oss-releases")
)

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-zipkin" % "6.30.0",
  "com.twitter" %% "finagle-redis" % "6.30.0",
  "com.github.finagle" %% "finch-core" % "0.9.1",
  "com.github.finagle" %% "finch-test" % "0.9.1",
  "com.github.finagle" %% "finch-jackson" % "0.9.1",
  "mesosphere" %% "jackson-case-class-module" % "0.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.3"
)

//test
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

ScoverageSbtPlugin.ScoverageKeys.coverageMinimum := 80

addCommandAlias("test", "testQuick")

addCommandAlias("devrun", "~re-start")

addCommandAlias("cov", "; clean; coverage; test")

Revolver.settings