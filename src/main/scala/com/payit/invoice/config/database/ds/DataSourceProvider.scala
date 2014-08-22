package com.payit.invoice.config.database.ds

import javax.sql.DataSource

trait DataSourceProvider {

  protected val dataSources: Map[String, DataSource]

  def getDataSource(name: String = "default"): DataSource

  def getDriver(name: String = "default"): String

}
