javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

organization := "gumtree"

name := "gumtree"

autoScalaLibrary := false

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "com.opencsv" % "opencsv" % "4.1"
)
