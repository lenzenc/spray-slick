package com.payit.invoice.config.app

import com.payit.invoice.config.database.profile.DBProfile
import com.payit.invoice.data.daos.{CustomerDAOModule, InvoiceDAOModule, UserDAOModule}

trait DAOs extends CustomerDAOModule with UserDAOModule with InvoiceDAOModule with Tables with DBProfile {

  lazy val customerDAO: CustomerDAO = new CustomerDAOImpl
  lazy val userDAO: UserDAO = new UserDAOImpl
  lazy val invoiceDAO: InvoiceDAO = new InvoiceDAOImpl

}
