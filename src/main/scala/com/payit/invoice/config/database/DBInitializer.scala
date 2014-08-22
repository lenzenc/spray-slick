package com.payit.invoice.config.database

import com.payit.invoice.config.app.Tables
import com.payit.invoice.models.Customer

trait DBInitializer extends Tables with DBSession {

  protected implicit lazy val session = database.createSession

  import profile.simple._

  def initDB = {

    tables.create

    session.withTransaction {

      customerTable ++= Seq(
        Customer("Customer A"),
        Customer("Customer B"),
        Customer("Customer C"),
        Customer("Customer D")
      )

    }

  }

}
