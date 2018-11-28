name := "spark-app"

organization := "junhz.home"

version := "0.0.1"

scalaVersion := "2.11.11"

scalacOptions := Seq("-Xexperimental", "-unchecked", "-deprecation")

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.4.0"