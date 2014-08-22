package com.payit.invoice.http.apis

import com.payit.invoice.http.JsonImplicits
import com.payit.invoice.models.Customer
import com.payit.invoice.testing.apis.{APISpec, APIScope}
import spray.http.StatusCodes._

class CustomerAPIModuleSpec extends APISpec {

  trait APITest extends APIScope with CustomerAPIModule with JsonImplicits
  {

    override lazy val customerService: CustomerService = mock[CustomerService]

  }

  "CustomerAPI" should {

    "return a list of customers" in new APITest {

      val customers = Seq(Customer("Customer A", Some(1)))
      customerService.list returns customers

      Get("/customers") ~> CustomerAPI.routes ~> check {

        status must_== OK
        there was one(customerService).list
        responseAs[Seq[Customer]] must_== customers

      }

    }

    "return a single customer for a given pk" in new APITest {

      val customer = Customer("Customer A", Some(1))
      customerService.getCustomer(1) returns Some(customer)


      Get("/customers/1") ~> CustomerAPI.routes ~> check {

        status must_== OK
        there was one(customerService).getCustomer(1)
        responseAs[Customer] must_== customer

      }

    }

  }

}
