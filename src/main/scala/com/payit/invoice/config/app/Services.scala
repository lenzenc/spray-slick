package com.payit.invoice.config.app

import com.payit.invoice.config.database.DBSession
import com.payit.invoice.services.{CustomerServiceModule, InvoiceServiceModule, UserServiceModule}

trait Services
  extends CustomerServiceModule
  with UserServiceModule
  with InvoiceServiceModule
  with DAOs
  with DBSession
{

  lazy val customerService: CustomerService = new CustomerServiceImpl
  lazy val userService: UserService = new UserServiceImpl
  lazy val invoiceService: InvoiceService = new InvoiceServiceImpl

}
