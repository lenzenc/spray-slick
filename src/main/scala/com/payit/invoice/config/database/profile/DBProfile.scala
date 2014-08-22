package com.payit.invoice.config.database.profile

import scala.slick.driver.JdbcProfile

trait DBProfile {

  protected val profile: JdbcProfile

}
