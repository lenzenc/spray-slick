package com.payit.invoice.config.database

import scala.slick.driver.JdbcProfile

trait DBProfile {

  val profile: JdbcProfile

}
