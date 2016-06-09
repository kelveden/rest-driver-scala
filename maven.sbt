licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))
homepage := Some(url("https://github.com/kelveden"))

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
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