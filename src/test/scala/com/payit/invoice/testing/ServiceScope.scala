package com.payit.invoice.testing

import com.payit.invoice.config.database.DB
import org.specs2.matcher.Scope
import org.specs2.mock.Mockito

trait ServiceScope extends Scope with DB with Mockito with TestDBProfile {

  val databaseName = "default"

  def database(): Database = database(databaseName)

  def database(databaseName: String): Database = mock[Database]

}
