import sbt._
import sbt.Keys._

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := "event-sourcing-aws-akka-showcase-java",
    organization := "com.github.dpfeiffer",
    version := "1.0",
    scalaVersion := "2.11.11",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-persistence-dynamodb" % "1.0.1",
      "com.typesafe.akka" %% "akka-persistence" % "2.4.17",
      "com.typesafe.akka" %% "akka-protobuf" % "2.4.17",
      "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.7.8",
      "com.amazonaws" % "aws-java-sdk-dynamodb" % "1.11.126",
      "com.lightbend.akka" %% "akka-stream-alpakka-sns" % "0.8",
      "com.lightbend.akka" %% "akka-stream-alpakka-sqs" % "0.8"
    )
  )
