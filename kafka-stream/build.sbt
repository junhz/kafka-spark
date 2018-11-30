name := "kafka-stream"

organization := "junhz.home"

version := "0.0.1"

scalaVersion := "2.11.11"

scalacOptions := Seq("-Xexperimental", "-unchecked", "-deprecation")

libraryDependencies ++= Seq("com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.7", "org.apache.kafka" %% "kafka-streams" % "2.1.0", "junhz.home" %% "spark-app_2.11" % "0.0.1")

val workaround = {sys.props += "packaging.type" -> "jar"}