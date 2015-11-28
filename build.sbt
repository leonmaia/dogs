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
  "Twitter repo" at "com.dogs.http://maven.twttr.com/",
  "Typesafe Repository" at "com.dogs.http://repo.typesafe.com/typesafe/releases/",
  "Mesosphere Public Repository" at "com.dogs.http://downloads.mesosphere.io/maven"
)
libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.9.1",
  "com.twitter" %% "finagle-zipkin" % "6.30.0",
  "com.twitter" %% "finagle-redis" % "6.30.0",
  "com.github.finagle" % "finch-jackson_2.11" % "0.9.1",
  "mesosphere" % "jackson-case-class-module_2.11" % "0.1.2",
  "org.slf4j" % "slf4j-api" % "1.7.12",
  "org.slf4j" % "jul-to-slf4j" % "1.7.12",
  "org.slf4j" % "jcl-over-slf4j" % "1.7.12",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.12",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "net.logstash.logback" % "logstash-logback-encoder" % "4.5.1"
)

ScoverageSbtPlugin.ScoverageKeys.coverageMinimum := 80

addCommandAlias("test", "testQuick")

addCommandAlias("devrun", "~re-start")

addCommandAlias("cov", "; clean; coverage; test")

Revolver.settings