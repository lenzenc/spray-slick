spray-slick [![Build Status](https://travis-ci.org/lenzenc/spray-slick.svg?branch=ISSUE-1)](https://travis-ci.org/lenzenc/spray-slick)
===========

This is just a sample Scala application for demo purposes, it is intended to show how you might use Spray IO and Slick to create a service layer in Scala.

The application is ment to show a headless service layer, meaning this is a service layer that just provides a RESTful API for services.  If you wanted to 
add a simple UI to this you certainly could using something like [Twirl](https://github.com/spray/twirl) but there are much better framework out there in Scala for 
full fledged UIs like [Play](http://www.playframework.com/) or [Lift](http://liftweb.net/).

### Running this application
This application is using the [Revolver Plugin](https://github.com/spray/sbt-revolver) which makes it really easy to start the application and leave it running as you 
continue to make changes and auto recompile.  Do run, type the following into a terminal.

    sbt
    ~ re-start
    
### Technologies Being Used
* Scala 2.11.X
* Slick 2.1.X
* Spray Can 1.3.1
* Spray Routing 1.3.1
* Spray JSON 1.2.6
* Specs2