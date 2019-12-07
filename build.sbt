import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.github.jhnaldo",
      scalaVersion := "2.13.1",
      version      := "0.0"
    )),
    name := "scala-algorithm"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"

scalacOptions in ThisBuild ++= Seq("-deprecation", "-feature",
                                   "-language:postfixOps",
                                   "-language:implicitConversions")

javacOptions ++= Seq("-encoding", "UTF-8")
