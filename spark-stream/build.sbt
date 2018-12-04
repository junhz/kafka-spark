name := "spark-stream"

organization := "junhz.home"

version := "0.0.1"

scalaVersion := "2.11.11"

scalacOptions := Seq("-Xexperimental", "-unchecked", "-deprecation")

libraryDependencies ++= Seq("org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.0", "org.apache.spark" %% "spark-streaming" % "2.4.0", "org.apache.spark" %% "spark-sql" % "2.4.0")
