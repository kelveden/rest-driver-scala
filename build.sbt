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
  "com.github.rest-driver" % "rest-client-driver" % "2.0.0",
  "com.github.rest-driver" % "rest-server-driver" % "2.0.0",
  "org.scalactic" %% "scalactic" % "2.2.6" % "provided",
  "org.scalatest" %% "scalatest" % "2.2.6" % "provided"
)


