name := "rest-driver-scala"
organization := "com.kelveden"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-language:higherKinds",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

libraryDependencies ++= Seq(
  "com.github.rest-driver" % "rest-client-driver" % "2.0.0" % "provided",
  "com.github.rest-driver" % "rest-server-driver" % "2.0.0" % "provided",
  "org.scalatest" %% "scalatest" % "3.0.1" % "provided",
  "ch.qos.logback" % "logback-classic" % "1.1.7" % "test"
)


