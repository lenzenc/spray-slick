package com.payit.invoice.config.app

import com.payit.invoice.config.database.profile.DBProfile
import com.payit.invoice.data.tables.{CustomerTable, InvoiceTable, UserTable}

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
