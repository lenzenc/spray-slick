package com.payit.invoice.apis

import com.payit.invoice.services.CustomerServiceModule
import spray.routing.{Route, Directives}

trait CustomerAPIModule { self: CustomerServiceModule =>

  object CustomerAPI extends Directives {

    val routes: Route = {
      get {
        path("customers") {
          complete {
            val customers = customerService.list
            "done"
          }
        } ~
        path("customers" / LongNumber) { pk =>
          complete {
            val customer = customerService.getCustomer(pk)
            "done"
          }
        }
      }
    }

  }

}
