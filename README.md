# SBT Scala multiproject template

Template of SBT Scala with:

 * two modules independent of each other: `first` and `second`,
 * one `common` project - used by both `first` and `second`,
 * [Scalariform](https://github.com/scala-ide/scalariform) configuration,
 * [Scoverage](https://github.com/scoverage/sbt-scoverage) configuration,
 * [Scalastyle](http://www.scalastyle.org/) configuration,
 * predefined [sub]tasks: `integration:test`, `functional:test`, `unit:test` which run tests tagged as
   `UnitTest`/`FunctionalTest`/`IntegrationTest` respectively,
 * filtering out tests tagged as `DisabledTest`.

## Customization

Start with changing module namespace to your own both inside Scala files as well as in `project/Settings.scala`. Make
sure to update initial command if you want to run `console` task of SBT. Update organization name and version number.

Within `build.sbt` use existing modules as basis how to use small DSL for applying common settings:

 * `project.from("name")` will create module from `modules/name` directory and set its SBT name to `name`,
 * `setName("name")` and `setDescription("description")` can be used for settings up artifact name and description with
   fluent interface,
 * `setInitialCommand("command")` will set console starting point to `your.namespace.{command}`,
 * `configureModule` will apply all common settings from `Settings.scala`,
 * `configureUnitTests`/`configureFunctionalTests`/`configureIntegrationTests` will add `unit:test`/`functional:test`/
   `integration:test` task to your module,
 * `dependsOnProjects(projects)` will set up both compile and test dependency (so tests will succeed only if module's
    own and it's dependency's tests will pass),
 * each of those commands will return project allowing normal `.settings(settings)` application. For individual settings
   one can also use `modules/name/build.sbt` individual per-module settings.

You should also take a closer look at `project/Settings.scala` for tweaking `scalacOptions`,
`ScalariformKeys.preferences` and `scalastyle-config.xml` for defining defaults best suiting your project.

Last but not least, edit common resolvers and dependencies within `project/Dependencies.scala`

After understanding how template works you are encourage to remove `activator.properties`, `tutorial/index.html` and
providing your own `README` and `LICENSE` files.

## Overriding defaults checks

If possible make defaults as strict as possible and just loosen them where absolutely needed:

 * coverage disabling:
 
   ```scala
   // $COVERAGE:OFF$ [reason]
   // not measured 
   // $COVERAGE:ON$</code>
   ```
 * formatting disabling:
 
   ```scala
   // format: OFF
   // not formatted
   // format: ON
   ```
 * style check disabling:
 
   ```scala
   // scalastyle:off [rule id]
   // not checked
   // scalastyle:on
   ```
It can be used for e.g disabling measurement of automatically generated code, formatting that merges lines into
something exceeding character limit or allowing null where interacting with Java code.

## Running main classes:

```bash
sbt "project first" run // or
sbt first/run

sbt "project second" run // or
sbt second/run
```

## Tests

### Running all tests with coverage and style check:

```bash
sbt clean coverage test coverageAggregate scalastyle
```

If you measure coverage you have to clean project otherwise it will not instrument properly.

### Selecting test suites

Running selected suite:

```bash
sbt first/test
sbt first/unit:test
sbt first/unit:functional
sbt first/unit:integration
sbt second/test
sbt second/unit:test
sbt second/unit:functional
sbt second/unit:integration
```

### Creating tagged test:

```scala
class ClassUnderTestSpec extends Specification with Mockito {

  "ClassUnderTest" should {

    "be nice" in {
      // given
      ...

      // when
      ...

      // then
      ...
    } tag UnitTest
    
    "be nice" in {
      // given
      ...

      // when
      ...

      // then
      ...
    } tag FunctionalTest
  }
  
}
```
