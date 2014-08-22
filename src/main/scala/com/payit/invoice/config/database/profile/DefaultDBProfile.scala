package com.payit.invoice.config.database.profile

trait DefaultDBProfile extends ConfigDBProfile {

  protected def databaseName: String = "default"

}
