name := """student-rest"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.mongodb" % "mongo-java-driver" % "2.12.4",
  "com.google.inject" % "guice" % "3.0",
  "com.google.inject.extensions" % "guice-assistedinject" % "3.0",
  "com.trinhtv3.fsoft" % "service-student" % "1.0-SNAPSHOT"
)

resolvers ++= Seq(
	Resolver.sonatypeRepo("snapshots"),
	Resolver.mavenLocal
)