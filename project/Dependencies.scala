import sbt._

import Dependencies._

object Dependencies {

  // scala version
  val scalaVersion = "2.11.8"

  // resolvers
  val resolvers = Seq(
    Resolver sonatypeRepo "public",
    Resolver typesafeRepo "releases"
  )

  // functional utils
  val scalaz           = "org.scalaz" %% "scalaz-core" % "7.2.6"
  val scalazConcurrent = "org.scalaz" %% "scalaz-concurrent" % "7.2.6"
  val scalazContrib    = "org.typelevel" %% "scalaz-contrib-210" % "0.2" excludeAll ExclusionRule("org.scalaz")

  // command line
  val scopt = "com.github.scopt" %% "scopt" % "3.5.0"

  // logging
  val logback = "ch.qos.logback" % "logback-classic" % "1.1.7"

  // testing
  val mockito    = "org.mockito" % "mockito-core" % "1.10.19"
  val spec2      = "org.specs2" %% "specs2" % "3.7"
  val spec2Core  = "org.specs2" %% "specs2-core" % "3.8.5"
  val spec2JUnit = "org.specs2" %% "specs2-junit" % "3.8.5"
}

trait Dependencies {

  val scalaVersionUsed = scalaVersion

  // resolvers
  val commonResolvers = resolvers

  val mainDeps = Seq(scalaz, scalazConcurrent, scalazContrib, scopt, logback)

  val testDeps = Seq(mockito, spec2, spec2Core, spec2JUnit)

  implicit class ProjectRoot(project: Project) {

    def root: Project = project in file(".")
  }

  implicit class ProjectFrom(project: Project) {

    private val commonDir = "modules"

    def from(dir: String): Project = project in file(s"$commonDir/$dir")
  }

  implicit class DependsOnProject(project: Project) {

    val dependsOnCompileAndTest = "test->test;compile->compile"

    def dependsOnProjects(projects: Project*): Project =
      project dependsOn (projects.map(_ % dependsOnCompileAndTest): _*)
  }
}
