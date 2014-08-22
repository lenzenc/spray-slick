package com.payit.invoice.http.apis

import com.payit.invoice.http.JsonImplicits
import com.payit.invoice.services.CustomerServiceModule
import spray.routing.{Route, Directives}

trait CustomerAPIModule { self: CustomerServiceModule =>

  object CustomerAPI extends Directives with JsonImplicits {

    val routes: Route = {
      get {
        path("customers") {
          complete {
            customerService.list
          }
        } ~
        path("customers" / LongNumber) { pk =>
          complete {
            customerService.getCustomer(pk).getOrElse(sys.error(s"Unable to find customer with id: $pk"))
          }
        }
      }
    }

  }

}
