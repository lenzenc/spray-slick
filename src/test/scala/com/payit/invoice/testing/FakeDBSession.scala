package com.payit.invoice.testing

import com.payit.invoice.config.database.DBSession
import org.specs2.mock.Mockito

import scala.slick.driver.{H2Driver, JdbcProfile}

trait FakeDBSession extends DBSession with Mockito {

  lazy val profile: JdbcProfile = H2Driver

  protected val mockDatabase = mock[Database]
  protected implicit lazy val session: Session = mock[Session]

  def database(): Database = database("default")

  def database(databaseName: String): Database = mockDatabase

  mockDatabase.createSession() returns session

}
