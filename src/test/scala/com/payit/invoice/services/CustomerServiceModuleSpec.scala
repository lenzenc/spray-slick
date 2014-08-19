package com.payit.invoice.services

import com.payit.invoice.data.daos.CustomerDAOModule
import com.payit.invoice.data.tables.CustomerTable
import com.payit.invoice.models.Customer
import com.payit.invoice.testing.{ServiceScope, ServiceSpec}

class CustomerServiceModuleSpec extends ServiceSpec {

  trait ServiceTest extends ServiceScope with CustomerServiceModule with CustomerDAOModule with CustomerTable {

    val customerService: CustomerService = new CustomerServiceImpl
    val customerDAO: CustomerDAO = mock[CustomerDAO]

  }

  ".list" should {

    "return a list of expected customers" in new ServiceTest {

      override implicit lazy val session: Session = mock[Session]

      val customerList = Seq(Customer("Customer A", Some(1)))
      customerDAO.findAll() returns customerList
      val customers = customerService.list
      there was one(customerDAO).findAll()
      customers must containTheSameElementsAs(customerList)

    }

  }

  ".getCustomer" should {

    "return a Customer for a given customerID" in new ServiceTest {

      override implicit lazy val session: Session = mock[Session]

      val expectedCustomer = Customer("Customer A", Some(1))
      customerDAO.findByPK(1) returns Some(expectedCustomer)
      val customer = customerService.getCustomer(1)
      there was one(customerDAO).findByPK(1)
      customer must_== expectedCustomer

    }

    "should throw exception if no Customer existing for given customerID" in new ServiceTest {

      override implicit lazy val session: Session = mock[Session]

      customerDAO.findByPK(1) returns None
      customerService.getCustomer(1) must throwA[IllegalArgumentException].like {
        case e => e.getMessage must startWith("Customer not found")
      }
      there was one(customerDAO).findByPK(1)

    }

  }

}
