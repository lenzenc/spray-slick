package com.payit.invoice

import com.payit.invoice.config.Configuration
import com.payit.invoice.config.app.{APIs, AppConfig, DAOs, Services}
import com.payit.invoice.config.database.DBInitializer
import com.payit.invoice.config.database.ds.{BoneCPProvider, DataSourceProvider}
import com.payit.invoice.config.database.profile.DefaultDBProfile

trait DefaultAppConfig extends AppConfig with DBInitializer with APIs with Services with DAOs with DefaultDBProfile {

  lazy val configuration: Configuration = Configuration.load
  protected lazy val provider: DataSourceProvider = new BoneCPProvider(configuration)

  import profile.simple._

  def database(): Database = database("default")

  def database(databaseName: String): Database = Database.forDataSource(provider.getDataSource(databaseName))

  def initApplication() = {
    initDB
  }

}
