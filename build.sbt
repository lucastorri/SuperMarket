name := "tests with specs2"

version := "1.0"

organization := "co.torri"

scalaVersion := "2.9.0-1"

libraryDependencies ++= Seq(
  "commons-lang" % "commons-lang" % "2.6",
  "org.specs2" %% "specs2" % "1.4" % "test",
  "org.specs2" %% "specs2-scalaz-core" % "6.0.RC2" % "test",
  "org.mockito" % "mockito-all" % "1.8.5" % "test",
  "junit" % "junit" % "4.8.2" % "test",
  "com.novocode" % "junit-interface" % "0.7" % "test",
  "org.scalaj" %% "scalaj-collection" % "1.1" % "test",
  "org.scala-tools.testing" %% "scalacheck" % "1.9" % "test",
  "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test"
)

testFrameworks += new TestFramework("org.specs2.runner.SpecsFramework")

resolvers ++= Seq(
  "snapshots" at "http://scala-tools.org/repo-snapshots",
  "releases" at "http://scala-tools.org/repo-releases",
  "maven" at "http://repo1.maven.org/maven2"
)

logLevel in compile := Level.Error
