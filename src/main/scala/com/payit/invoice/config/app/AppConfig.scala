package com.payit.invoice.config.app

import com.payit.invoice.config.Configuration
import com.payit.invoice.config.database.ds.DataSourceProvider

trait AppConfig {

  val configuration: Configuration
  protected val provider: DataSourceProvider

  def initApplication()

}
