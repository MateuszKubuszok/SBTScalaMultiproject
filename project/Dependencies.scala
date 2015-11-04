import sbt._

import Dependencies._

object Dependencies {

  // scala version
  val scalaVersion = "2.11.7"

  // resolvers
  val resolvers = Seq(
    Resolver sonatypeRepo "public",
    Resolver typesafeRepo "releases"
  )

  // functional utils
  val scalaz        = "org.scalaz" %% "scalaz-core" % "7.1.3"
  val scalazContrib = "org.typelevel" %% "scalaz-contrib-210" % "0.2" exclude ("org.scalaz", "scalaz-core_2.11")

  // Apache common utils
  val commonsIo   = "commons-io" % "commons-io" % "2.4"
  val commonsLang = "org.apache.commons" % "commons-lang3" % "3.4"

  // command line
  val scopt = "com.github.scopt" %% "scopt" % "3.3.0"

  // logging
  val logback = "ch.qos.logback" % "logback-classic" % "1.1.3"

  // testing
  val mockito    = "org.mockito" % "mockito-core" % "1.10.8"
  val spec2      = "org.specs2" %% "specs2" % "2.4.1"
  val spec2Core  = "org.specs2" %% "specs2-core" % "2.4.1"
  val spec2JUnit = "org.specs2" %% "specs2-junit" % "2.4.1"
}

trait Dependencies {

  val scalaVersionUsed = "2.11.7"

  // resolvers
  val commonResolvers = resolvers

  val mainDeps = Seq(scalaz, scalazContrib, commonsIo, commonsLang, scopt, logback)

  val testDeps = Seq(mockito, spec2, spec2Core, spec2JUnit)

  implicit class ProjectRoot(project: Project) {

    def root = project in file(".")
  }

  implicit class ProjectFrom(project: Project) {

    private val commonDir = "modules"

    def from(dir: String) = project in file(s"$commonDir/$dir")
  }

  implicit class DependsOnProject(project: Project) {

    val dependsOnCompileAndTest = "test->test;compile->compile"

    def dependsOnProjects(projects: Project*) = project dependsOn (projects.map(_ % dependsOnCompileAndTest): _*)
  }
}
