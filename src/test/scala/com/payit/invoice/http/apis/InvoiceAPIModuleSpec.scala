package com.payit.invoice.http.apis

import com.payit.invoice.models.Invoice
import com.payit.invoice.testing.apis.{APIScope, APISpec}
import spray.http.StatusCodes._

class InvoiceAPIModuleSpec extends APISpec {

  trait APITest extends APIScope with InvoiceAPIModule {

    override lazy val invoiceService = mock[InvoiceService]

    val invoices = Seq(Invoice("INV1000", "Description", 500.75, 5, Some(1)))

  }

  "InvoiceAPI" should {

    "return a list of invoices for a given user id" in new APITest {

      invoiceService.listByUserID(5) returns invoices

      Get("/invoices?userID=5") ~> InvoiceAPI.routes ~> check {

        status must_== OK
        there was one(invoiceService).listByUserID(5)
        responseAs[Seq[Invoice]] must_== invoices

      }

    }

    "return a list of invoices for a given customer id" in new APITest {

      invoiceService.listByCustomerID(20) returns invoices

      Get("/invoices?customerID=20") ~> InvoiceAPI.routes ~> check {

        status must_== OK
        there was one(invoiceService).listByCustomerID(20)
        responseAs[Seq[Invoice]] must_== invoices

      }

    }

    "return a single invoice for a given invoice id" in new APITest {

      invoiceService.getInvoice(1) returns Some(invoices(0))

      Get("/invoices/1") ~> InvoiceAPI.routes ~> check {

        status must_== OK
        there was one(invoiceService).getInvoice(1)
        responseAs[Invoice] must_== invoices(0)

      }

    }

  }

}
