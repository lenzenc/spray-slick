package com.payit.invoice.config.database

import com.payit.invoice.config.database.profile.DBProfile

trait DBSession extends DBProfile {

  type Database = profile.simple.Database
  type Session = profile.simple.Session

  def database(): Database

  def database(databaseName: String): Database

}
