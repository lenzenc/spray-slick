package com.payit.invoice.services

import com.payit.invoice.data.daos.UserDAOModule
import com.payit.invoice.data.tables.UserTable
import com.payit.invoice.models.User
import com.payit.invoice.testing.services.{ServiceSpec, ServiceScope}

class UserServiceModuleSpec extends ServiceSpec {

  trait ServiceTest extends ServiceScope with UserServiceModule with UserDAOModule with UserTable {

    val userService: UserService = new UserServiceImpl
    val userDAO: UserDAO = mock[UserDAO]

  }

  ".listByCustomerID" should {

    "return a list of Users for a given customerID" in new ServiceTest {

      val expectedUserList = Seq(User("Bob", "Smith", 1, Some(1)))
      userDAO.findAllByCustomerID(1) returns expectedUserList
      val users = userService.listByCustomerID(1)
      there was one(userDAO).findAllByCustomerID(1)
      users must containTheSameElementsAs(expectedUserList)

    }

  }

  ".getUser" should {

    "return a User for a given userID" in new ServiceTest {

      val expectedUser = User("Bob", "Smith", 1, Some(1))
      userDAO.findByPK(1) returns Some(expectedUser)
      val user = userService.getUser(1)
      there was one(userDAO).findByPK(1)
      user.get must_== expectedUser

    }

    "return None if no User exists for a given userID" in new ServiceTest {

      userDAO.findByPK(1) returns None
      val user = userService.getUser(1)
      there was one(userDAO).findByPK(1)
      user must beNone

    }

  }

}
