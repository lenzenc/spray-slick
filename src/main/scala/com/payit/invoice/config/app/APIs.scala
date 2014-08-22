package com.payit.invoice.config.app

import akka.actor.{ActorRefFactory, ActorSystem, Props}
import akka.io.IO
import com.payit.invoice.http.Router
import com.payit.invoice.http.apis.{InvoiceAPIModule, UserAPIModule, CustomerAPIModule}
import spray.can.Http
import spray.routing.RouteConcatenation

trait APIs
  extends CustomerAPIModule
  with UserAPIModule
  with InvoiceAPIModule
  with Services
  with RouteConcatenation
{

  protected implicit lazy val system = ActorSystem("default-dispatcher")
  protected lazy val actorRefFactory: ActorRefFactory = system

  val router = system.actorOf(Props(new Router(
    CustomerAPI.routes ~
    UserAPI.routes ~
    InvoiceAPI.routes
  )))

  IO(Http)(system) ! Http.Bind(router, "0.0.0.0", port = 8080)

  sys.addShutdownHook(system.shutdown())

}
