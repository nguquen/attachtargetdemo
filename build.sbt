name := "attachdemo"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.springframework" % "spring-context" % "4.0.9.RELEASE",
  "com.google.guava" % "guava" % "15.0",
  "com.typesafe" % "config" % "1.3.0"
)

mainClass in Compile := Some("demo.LoopMain")
enablePlugins(JavaServerAppPackaging)

import NativePackagerHelper._
mappings in Universal ++= directory("conf")