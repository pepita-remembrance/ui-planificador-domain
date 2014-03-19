name := """hello-scala"""

version := "1.0"

scalaVersion := "2.10.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

transitiveClassifiers in Global := Seq(Artifact.SourceClassifier)