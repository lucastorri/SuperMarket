name := "tests with specs2"

version := "1.0"

organization := "co.torri"

scalaVersion := "2.9.0"

libraryDependencies ++= Seq(
  "commons-lang" % "commons-lang" % "2.6",
  "org.specs2" %% "specs2" % "1.4-SNAPSHOT" % "test",
  "org.specs2" %% "specs2-scalaz-core" % "6.0.RC2" % "test",
  "org.mockito" % "mockito-all" % "1.8.5" % "test",
  "junit" % "junit" % "4.8.2" % "test"
)

testFrameworks += new TestFramework("org.specs2.runner.SpecsFramework")

resolvers ++= Seq(
  "snapshots" at "http://scala-tools.org/repo-snapshots",
  "releases" at "http://scala-tools.org/repo-releases",
  "maven" at "http://repo1.maven.org/maven2"
)
