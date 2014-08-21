package com.payit.invoice.testing.apis

import com.payit.invoice.services.Service
import com.payit.invoice.testing.TestDBProfile
import org.specs2.matcher.{ThrownExpectations, Scope}
import org.specs2.mock.Mockito

trait APIScope extends Scope with Mockito with Service with TestDBProfile with ThrownExpectations {

  protected val mockDatabase = mock[Database]
  protected implicit lazy val session: Session = mock[Session]

  def database(): Database = database("default")

  def database(databaseName: String): Database = mockDatabase

  mockDatabase.createSession() returns session

}
