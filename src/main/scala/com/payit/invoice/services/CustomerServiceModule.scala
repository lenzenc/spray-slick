package com.payit.invoice.services

import com.payit.invoice.SortOrder
import com.payit.invoice.config.database.DB
import com.payit.invoice.data.daos.CustomerDAOModule
import com.payit.invoice.models.Customer

trait CustomerServiceModule { self: CustomerDAOModule with DB =>

  val customerService: CustomerService

  trait CustomerService {

    def list: Seq[Customer]
    def getCustomer(customerID: Long): Option[Customer]

  }

  class CustomerServiceImpl extends CustomerService {

    implicit lazy val session: Session = database.createSession

    def list: Seq[Customer] = customerDAO.findAll(SortOrder.ASC)

    def getCustomer(customerID: Long): Option[Customer] = customerDAO.findByPK(customerID)

  }

}
