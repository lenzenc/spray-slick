package com.payit.invoice.testing

import com.payit.invoice.data.tables.Tables

trait TestDB extends Tables with TestDBProfile {

  import profile.simple._

  lazy val database = Database.forURL("jdbc:h2:mem:test1", driver = "org.h2.Driver")
  implicit lazy val session = database.createSession

}
