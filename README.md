# SBT Scala multiproject template

Template of SBT Scala with:

 * one `common` project,
 * two modules independent of each other,
 * Scalariform configuration,
 * Scoverage configuration,
 * Scalastyle configuration,
 * predefined [sub]tasks: `integration`, `functional`, `unit`. 

## Running main class:

    sbt "project first" run
    
    sbt "project second" run

## Running all tests with coverage and style check:

    sbt clean coverage test coverageAggregate scalastyle

If you measure coverage you have to clean project otherwise it will not instrument properly.

## Selecting test suites

Running selected suite:

    sbt first/test
    sbt first/unit:test
    sbt first/unit:functional
    sbt first/unit:integration
    sbt second/test
    sbt second/unit:test
    sbt second/unit:functional
    sbt second/unit:integration
