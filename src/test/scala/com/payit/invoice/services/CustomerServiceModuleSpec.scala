package com.payit.invoice.services

import com.payit.invoice.SortOrder
import com.payit.invoice.data.daos.CustomerDAOModule
import com.payit.invoice.data.tables.CustomerTable
import com.payit.invoice.models.Customer
import com.payit.invoice.testing.services.{ServiceSpec, ServiceScope}

class CustomerServiceModuleSpec extends ServiceSpec {

  trait ServiceTest extends ServiceScope with CustomerServiceModule with CustomerDAOModule with CustomerTable {

    val customerService: CustomerService = new CustomerServiceImpl
    val customerDAO: CustomerDAO = mock[CustomerDAO]

  }

  ".list" should {

    "return a list of expected customers" in new ServiceTest {

      val customerList = Seq(Customer("Customer A", Some(1)))
      customerDAO.findAll(SortOrder.ASC) returns customerList
      val customers = customerService.list
      there was one(customerDAO).findAll(SortOrder.ASC)
      customers must containTheSameElementsAs(customerList)

    }

  }

  ".getCustomer" should {

    "return a Customer for a given customerID" in new ServiceTest {

      val expectedCustomer = Customer("Customer A", Some(1))
      customerDAO.findByPK(1) returns Some(expectedCustomer)
      val customer = customerService.getCustomer(1)
      there was one(customerDAO).findByPK(1)
      customer.get must_== expectedCustomer

    }

    "return None if no Customer existing for given customerID" in new ServiceTest {

      customerDAO.findByPK(1) returns None
      val customer = customerService.getCustomer(1)
      there was one(customerDAO).findByPK(1)
      customer must beNone

    }

  }

}
