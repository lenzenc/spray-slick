package com.payit.invoice.services

import com.payit.invoice.config.database.DB
import com.payit.invoice.data.daos.CustomerDAOModule
import com.payit.invoice.models.Customer

trait CustomerServiceModule { self: CustomerDAOModule with DB =>

  val customerService: CustomerService

  protected implicit lazy val session: Session = database.createSession

  trait CustomerService {

    def list: Seq[Customer]
    def getCustomer(customerID: Long): Customer

  }

  class CustomerServiceImpl extends CustomerService {

    def list: Seq[Customer] = customerDAO.findAll()

    def getCustomer(customerID: Long): Customer = {
      customerDAO.findByPK(customerID).getOrElse(
        throw new IllegalArgumentException(s"Customer not found for customerID: $customerID")
      )
    }

  }

}
