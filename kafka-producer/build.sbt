name := "kafka-producer"

organization := "junhz.home"

version := "0.0.1"

scalaVersion := "2.11.11"

scalacOptions := Seq("-Xexperimental", "-unchecked", "-deprecation")

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.1.0"