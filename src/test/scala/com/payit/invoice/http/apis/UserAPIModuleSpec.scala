package com.payit.invoice.http.apis

import com.payit.invoice.models.{User, Customer}
import com.payit.invoice.testing.apis.{APIScope, APISpec}
import spray.http.StatusCodes._

class UserAPIModuleSpec extends APISpec {

  trait APITest extends APIScope with UserAPIModule {

    override lazy val userService = mock[UserService]

  }

  "UserAPI" should {

    "return a list of users for a given customer id" in new APITest {

      val customer = Customer("Customer A", Some(1))
      val users = Seq(User("Bob", "Smith", customer.id.get, Some(5)))
      userService.listByCustomerID(10) returns users

      Get("/users?customerID=10") ~> UserAPI.routes ~> check {

        status must_== OK
        there was one(userService).listByCustomerID(10)
        responseAs[Seq[User]] must_== users

      }

    }

    "return a user for a given user id" in new APITest {

      val customer = Customer("Customer A", Some(1))
      val user = User("Bob", "Smith", customer.id.get, Some(5))
      userService.getUser(5) returns Some(user)

      Get("/users/5") ~> UserAPI.routes ~> check {

        status must_== OK
        there was one(userService).getUser(5)
        responseAs[User] must_== user

      }

    }

  }

}
