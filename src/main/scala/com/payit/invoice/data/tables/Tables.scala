package com.payit.invoice.data.tables

import com.payit.invoice.config.database.DBProfile

trait Tables
  extends CustomerTable
  with UserTable
  with InvoiceTable
  with DBProfile
{

  import profile.simple._

  lazy val tables =
    customerTable.ddl ++
    userTable.ddl ++
    invoiceTable.ddl

}
