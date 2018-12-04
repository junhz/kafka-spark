name := "spark-app"

organization := "junhz.home"

version := "0.0.1"

scalaVersion := "2.11.11"

scalacOptions := Seq("-Xexperimental", "-unchecked", "-deprecation")

libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "2.4.0", "junhz.home" %% "spark-func" % "0.0.1")