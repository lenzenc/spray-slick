package com.payit.invoice.testing.services

import com.payit.invoice.config.database.DB
import com.payit.invoice.testing.TestDBProfile
import org.specs2.matcher.{ThrownExpectations, Scope}
import org.specs2.mock.Mockito

trait ServiceScope extends Scope with DB with Mockito with TestDBProfile with ThrownExpectations {

  protected val mockDatabase = mock[Database]
  protected implicit lazy val session: Session = mock[Session]

  def database(): Database = database("default")

  def database(databaseName: String): Database = mockDatabase

  mockDatabase.createSession() returns session

}
