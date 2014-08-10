name := "spray-slick"

version := "1.0.0"

scalaVersion := "2.11.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases",
  "Sonatype Releases"  at "http://oss.sonatype.org/content/repositories/releases",
  "spray repo" at "http://repo.spray.io/"
)


libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.7",
  "org.slf4j" % "slf4j-simple" % "1.7.7",
  "com.typesafe.slick" % "slick_2.11" % "2.1.0",
  "com.h2database" % "h2" % "1.4.180",
  "com.typesafe"%"config"%"1.2.1",
  "com.jolbox"%"bonecp"%"0.8.0.RELEASE",
  "io.spray" %% "spray-can" % "1.3.1-20140423",
  "io.spray" %% "spray-routing" % "1.3.1-20140423",
  "io.spray" %% "spray-testkit" % "1.3.1-20140423",
  "io.spray"%%"spray-json"%"1.2.6",
  "org.specs2"%%"specs2"%"2.4"%"test",
  "com.typesafe.slick" %% "slick-codegen" % "2.1.0"
)

Revolver.settings

Twirl.settings

scalacOptions in Test ++= Seq("-Yrangepos")