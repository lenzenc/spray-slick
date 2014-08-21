package com.payit.invoice.data.daos

import com.payit.invoice.SortOrder
import com.payit.invoice.data.tables.CustomerTable
import com.payit.invoice.models.Customer
import com.payit.invoice.testing.TestDBProfile
import com.payit.invoice.testing.daos.DAOSpec
import org.specs2.matcher.Scope

class CustomerDAOModuleSpec extends DAOSpec {

  import profile.simple._

  trait DAOTest extends Scope with CustomerDAOModule with CustomerTable with TestDBProfile {

    val customerDAO = new CustomerDAOImpl

  }

  trait UnOrderedCustomers {

    customerTable ++= Seq(
      Customer("Customer B"),
      Customer("Customer A"),
      Customer("Customer C")
    )

  }

  "findAll" should {

    "by default return a list of ASC ordered customers" in new DAOTest with UnOrderedCustomers {

      val customers = customerDAO.findAll()
      customers must not(beNull)
      customers.size must_== 3
      customers.map(_.name) must contain(exactly("Customer A", "Customer B", "Customer C").inOrder)

    }
    "allow to be ordered by ascending order" in new DAOTest with UnOrderedCustomers {

      val customers = customerDAO.findAll(SortOrder.ASC)
      customers must not(beNull)
      customers.size must_== 3
      customers.map(_.name) must contain(exactly("Customer A", "Customer B", "Customer C").inOrder)

    }
    "allow to be ordered by decending order" in new DAOTest with UnOrderedCustomers {

      val customers = customerDAO.findAll(SortOrder.DESC)
      customers must not(beNull)
      customers.size must_== 3
      customers.map(_.name) must contain(exactly("Customer C", "Customer B", "Customer A").inOrder)

    }

  }

  ".findByPK" should {

    "return an Option containing the expected customer for the given PK" in new DAOTest {

      val customerA = Customer("Customer A", Some(1))
      customerTable.forceInsert(customerA)

      val customer = customerDAO.findByPK(customerA.id.get)
      customer must not(beNull)
      customer must beSome[Customer]
      customer.get.name must_== customerA.name

    }
    "return on Option of None if no customer is found for a given PK" in new DAOTest {

      val customer = customerDAO.findByPK(-1)
      customer must not(beNull)
      customer must beNone

    }

  }

}
