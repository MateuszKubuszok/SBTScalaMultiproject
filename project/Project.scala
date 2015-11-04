import sbt._

object Project extends Build with Settings with Dependencies {

  lazy val root = project.root
    .setName("SBT template")
    .setDescription("Backup DSL")
    .setInitialCommand("_")
    .configureRoot
    .aggregate(common, first, second)

  lazy val common = project.from("common")
    .setName("common")
    .setDescription("Common utilities")
    .setInitialCommand("_")
    .configureModule

  lazy val first = project.from("first")
    .setName("first")
    .setDescription("First project")
    .setInitialCommand("first._")
    .configureModule
    .configureIntegrationTests
    .configureFunctionalTests
    .configureUnitTests
    .dependsOnProjects(common)

  lazy val second = project.from("second")
    .setName("second")
    .setDescription("Second project")
    .setInitialCommand("second._")
    .configureModule
    .configureIntegrationTests
    .configureFunctionalTests
    .configureUnitTests
    .dependsOnProjects(common)
}
