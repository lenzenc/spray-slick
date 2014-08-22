package com.payit.invoice.http.apis

import com.payit.invoice.http.JsonImplicits
import com.payit.invoice.services.InvoiceServiceModule
import spray.routing.{Route, Directives}

trait InvoiceAPIModule { self: InvoiceServiceModule =>

  object InvoiceAPI extends Directives with JsonImplicits {

    val routes: Route = {
      get {
        path("invoices") {
          parameters('userID.as[Long]) { userID =>
            complete {
              invoiceService.listByUserID(userID)
            }
          }
        } ~
        path("invoices") {
          parameters('customerID.as[Long]) { customerID =>
            complete {
              invoiceService.listByCustomerID(customerID)
            }
          }
        } ~
        path("invoices" / LongNumber) { pk =>
          complete {
            invoiceService.getInvoice(pk).getOrElse(sys.error(s"Unable to find invoice for id: $pk"))
          }
        }
      }
    }

  }

}
