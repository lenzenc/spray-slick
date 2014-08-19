package com.payit.invoice.config.database

trait DB extends DBProfile {

  type Database = profile.simple.Database
  type Session = profile.simple.Session

  def database(): Database

  def database(databaseName: String): Database

}
