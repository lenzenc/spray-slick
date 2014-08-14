package com.payit.invoice.config

import scala.slick.driver.JdbcProfile

trait DBProfile {

  val profile: JdbcProfile

}
