package com.payit.invoice.http

import akka.actor.{Actor, ActorLogging}
import spray.routing.{HttpService, Route}

class Router(route: Route) extends Actor with HttpService with ActorLogging {

  implicit def actorRefFactory = context

  def receive: Receive = {
    runRoute(route)
  }

}
