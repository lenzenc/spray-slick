package com.payit.invoice.data.daos

import com.payit.invoice.data.tables.Tables

trait DAO extends CustomerDAOModule with UserDAOModule with InvoiceDAOModule with Tables {

  lazy val customerDAO: CustomerDAO = new CustomerDAOImpl
  lazy val userDAO: UserDAO = new UserDAOImpl
  lazy val invoiceDAO: InvoiceDAO = new InvoiceDAOImpl

}
