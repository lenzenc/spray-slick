package com.payit.invoice.apis

import com.payit.invoice.models.Customer
import com.payit.invoice.testing.apis.{APISpec, APIScope}
import spray.http.StatusCodes._

class CustomerAPIModuleSpec extends APISpec {

  trait APITest extends APIScope with CustomerAPIModule {

    override lazy val customerService: CustomerService = mock[CustomerService]

  }

  "CustomerAPI" should {

    "return a list of customers" in new APITest {

      Get("/customers") ~> CustomerAPI.routes ~> check {

        val customers = Seq(Customer("Customer A", Some(1)))
        customerService.list returns customers
        status must_== OK
        there was one(customerService).list

      }

    }

    "return a single customer for a given pk" in new APITest {

      Get("/customers/1") ~> CustomerAPI.routes ~> check {

        val customer = Customer("Customer A", Some(1))
        customerService.getCustomer(1) returns Some(customer)
        status must_== OK
        there was one(customerService).getCustomer(1)

      }

    }

    "how to test/handling exceptions coming from the service layer?" in pending

  }

}
