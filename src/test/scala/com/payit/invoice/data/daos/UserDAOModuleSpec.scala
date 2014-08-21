package com.payit.invoice.data.daos

import com.payit.invoice.data.tables.UserTable
import com.payit.invoice.models.{User, Customer}
import com.payit.invoice.testing.TestDBProfile
import com.payit.invoice.testing.daos.DAOSpec
import org.specs2.matcher.Scope

class UserDAOModuleSpec extends DAOSpec {

  import profile.simple._

  trait DAOTest extends Scope with UserDAOModule with UserTable with TestDBProfile {

    val userDAO: UserDAO = new UserDAOImpl

  }

  trait UsersAndCustomers {

    val customerA = Customer("Customer A", Some(1))
    val customerB = Customer("Customer B", Some(2))
    customerTable.forceInsertAll(customerA, customerB)
    val bobSmith = User("Bob", "Smith", customerA.id.get, Some(1))
    userTable.forceInsertAll(
      bobSmith,
      User("Joe", "Blow", customerB.id.get, Some(2)),
      User("Henry", "Bar", customerA.id.get, Some(3)),
      User("John", "Wilson", customerB.id.get, Some(4))
    )

  }

  ".findAllByCustomerID" should {

    "only return customers associated to the given customer ID" in new DAOTest with UsersAndCustomers {

      val users = userDAO.findAllByCustomerID(customerA.id.get)
      users must not(beNull)
      users.size must_== 2
      users.map(_.id.get) must contain(exactly(1L, 3L))

    }

    "return empty Seq if not customers are found for a given customer ID" in new DAOTest with UsersAndCustomers {

      val users = userDAO.findAllByCustomerID(-1)
      users must not(beNull)
      users must be empty

    }

  }

  ".findByPK" should {

    "return an Option containing the expected user for the given PK" in new DAOTest with UsersAndCustomers {

      val user = userDAO.findByPK(bobSmith.id.get)
      user must not(beNull)
      user must beSome[User]
      user.get.id must_== bobSmith.id

    }

    "return on Option of None if no user is found for a given PK" in new DAOTest {

      val user = userDAO.findByPK(-1)
      user must not(beNull)
      user must beNone

    }

  }

}
