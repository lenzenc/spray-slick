package com.payit.invoice.services

import com.payit.invoice.config.database.DB
import com.payit.invoice.data.daos.DAO
import com.payit.invoice.data.tables.Tables

trait Service
  extends CustomerServiceModule
  with UserServiceModule
  with InvoiceServiceModule
  with DB
  with DAO
  with Tables
{

  lazy val customerService: CustomerService = new CustomerServiceImpl
  lazy val userService: UserService = new UserServiceImpl
  lazy val invoiceService: InvoiceService = new InvoiceServiceImpl

}
