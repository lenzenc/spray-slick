package com.payit.invoice.config.database.profile

import com.payit.invoice.config.Configuration

import scala.slick.driver._

trait ConfigDBProfile extends DBProfile {

  protected val configuration: Configuration
  protected lazy val profile: JdbcProfile = Map(
    "org.apache.derby.jdbc.EmbeddedDriver" -> DerbyDriver,
    "org.h2.Driver" -> H2Driver,
    "org.hsqldb.jdbcDriver" -> HsqldbDriver,
    "org.hsqldb.jdbc.JDBCDriver" -> HsqldbDriver,
    "com.mysql.jdbc.Driver" -> MySQLDriver,
    "org.postgresql.Driver" -> PostgresDriver,
    "org.sqlite.JDBC" -> SQLiteDriver).getOrElse(
      configuration.getString(s"db.$databaseName.driver").getOrElse(
        sys.error(s"Unable to find database configuration for db.$databaseName.driver")),
      sys.error(s"Unable to find Slick DB Profile from driver $databaseName"))

  protected def databaseName: String

}
