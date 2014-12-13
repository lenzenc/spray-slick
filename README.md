spray-slick 
===========
[![Build Status](https://travis-ci.org/lenzenc/spray-slick.svg?branch=ISSUE-1)](https://travis-ci.org/lenzenc/spray-slick)
[![Coverage Status](https://coveralls.io/repos/lenzenc/spray-slick/badge.png?branch=master)](https://coveralls.io/r/lenzenc/spray-slick?branch=master)

This is just a sample Scala application for demo purposes, it is intended to show how you might use Spray IO and Slick to create a service layer in Scala.

The application is ment to show a headless service layer, meaning this is a service layer that just provides a RESTful API for services.  If you wanted to 
add a simple UI to this you certainly could using something like [Twirl](https://github.com/spray/twirl) but there are much better framework out there in Scala for 
full fledged UIs like [Play](http://www.playframework.com/) or [Lift](http://liftweb.net/).

### Running this application
This application is using the [Revolver Plugin](https://github.com/spray/sbt-revolver) which makes it really easy to start the application and leave it running as you 
continue to make changes and auto recompile.  Do run, type the following into a terminal.

    sbt
    ~ re-start
    
#### End Points

    http://localhost:8080/customers
    http://localhost:8080/users?customerID=1
    http://localhost:8080/invoices?customerID=1
    http://localhost:8080/invoices?userID=1

### Running Unit Tests
Do run unit test for this application, type the following commands into a terminal;
    sbt
    test
    
### Technologies Being Used
* Scala
* Slick
* Spray Can
* Spray Routing
* Spray JSON
* Specs2

### Cake Pattern
This sample application is currently using the scala "Cake Pattern" for DI, see [Jonas Boner](http://jonasboner.com/2008/10/06/real-world-scala-dependency-injection-di/)
for basic information about the cake pattern.
 
I'm not yet a fan of the cake pattern but think using native scala to do DI is better than using IOC containers like
Spring, Guice and other scala IOCs.

I will likely be creating branches to this project to test out other DI approaches...for example;

* Pure constructor based DI
* Implicit constructor DI
* [subcut](https://github.com/dickwall/subcut)
* And others