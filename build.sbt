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

scalacOptions in (Compile, console) ~= (_ filterNot (_ == "-Ywarn-unused-import"))

scalacOptions in (Test, console) := (scalacOptions in (Compile, console)).value

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Twitter repo" at "http://maven.twttr.com/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Mesosphere Public Repository" at "http://downloads.mesosphere.io/maven"
)
libraryDependencies += "com.github.finagle" %% "finch-core" % "0.9.1"

ScoverageSbtPlugin.ScoverageKeys.coverageMinimum := 80

addCommandAlias("test", "testQuick")

addCommandAlias("devrun", "~re-start")

addCommandAlias("cov", "; clean; coverage; test")

Revolver.settings