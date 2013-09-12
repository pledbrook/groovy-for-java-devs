Source code for "Groovy for Java Developers" talk
=================================================

These are the sample projects that I go through during my talk on Groovy for Java developers. Note that there are some branches that contain the "after" code for the live coding.

dsl
---

Shows how you can load Groovy scripts into a Java application and execute them. Uses `GroovyShell` for the script execution. It also has a simple DSL implementation for trading shares. The `dsl-customScript` branch contains an alternative implementation that uses a custom Groovy script class.

lazybones
---------

Demonstrates CodeNARC, which enforces rules on Groovy coding styles and more. Simply run

    ./gradlew :l-a:build

to execute CodeNARC and then look at the report in `lazybones-app/build/reports/codenarc/main.html`.

quicksort
---------

Uses a quicksort algorithm poached off the internet and implements it in Java, Groovy, and Groovy with `@CompileStatic`. It profiles each of them to give you an idea of the performance differences. Simply run

    ./gradlew run

from the root of the project to run it. By default it sorts a list of 10,000 random numbers, but you can change this through an argument:

    ./gradlew run -PcliArgs=1000000

The above will use an array of 1 million numbers for example.

scripts
-------

Contains several scripts to demonstrate Groovy's utility for doing scripting jobs. The `scripts-improvedCountFiles` and `scripts-improvedRestExample` branches contain the improvements to those scripts that are coded up during the talk.

* countFilesOfType - demonstration of file manipulation and directory traversal
* db - uses Groovy SQL to access a database
* restExample - accesses a RESTful, JSON web service and processes the returned data

spock
-----

Shows you how to use Spock, particularly with `where` data blocks.

spring-travel
-------------

This is a variant of the standard Spring Travel example application. It is almost exclusively Java and uses Maven for the build. But, it includes a Groovy based controller to show you that Groovy and Java mix perfectly well. You can also see in the Maven build file how to set up Groovy compilation.

Run the application with

    mvn tomcat:run

