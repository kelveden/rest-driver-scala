name := "rest-driver-scala"
organization := "com.kelveden"
version := "1.0.1"
scalaVersion := "2.11.8"
crossScalaVersions := Seq(scalaVersion.value, "2.12.4")
homepage := Some(url("https://github.com/kelveden"))
licenses += ("MIT", url("https://opensource.org/licenses/MIT"))
bintrayPackageLabels := Seq("rest-driver", "testing", "acceptance")

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

publishArtifact in Test := false
pomExtra := (
  <scm>
    <url>git@github.com:kelveden/rest-driver-scala.git</url>
    <connection>scm:git:git@github.com:kelveden/rest-driver-scala.git</connection>
  </scm>
    <developers>
      <developer>
        <id>kelveden</id>
        <name>Alistair Dutton</name>
      </developer>
    </developers>)
