package com.payit.invoice.http.apis

import com.payit.invoice.http.JsonImplicits
import com.payit.invoice.services.UserServiceModule
import spray.routing.{Route, Directives}

trait UserAPIModule { self: UserServiceModule =>

  object UserAPI extends Directives with JsonImplicits {

    val routes: Route = {
      get {
        path("users") {
          parameters('customerID.as[Long]) { customerID =>
            complete {
              userService.listByCustomerID(customerID)
            }
          }
        } ~
        path("users" / LongNumber) { pk =>
          complete {
            userService.getUser(pk).getOrElse(sys.error(s"Unable to find user with id: $pk"))
          }
        }
      }
    }

  }

}
