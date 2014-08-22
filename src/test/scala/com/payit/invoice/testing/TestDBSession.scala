package com.payit.invoice.testing

import com.payit.invoice.config.database.DBSession

import scala.slick.driver.{H2Driver, JdbcProfile}

trait TestDBSession extends DBSession {

  lazy val profile: JdbcProfile = H2Driver

  import profile.simple._

  lazy val db = Database.forURL("jdbc:h2:mem:test1", driver = "org.h2.Driver")
  implicit lazy val session = database.createSession

  def database(): Database = database("default")

  def database(databaseName: String): Database = db

}
