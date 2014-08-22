package com.payit.invoice.testing

import com.payit.invoice.config.database.profile.DBProfile

import scala.slick.driver.{H2Driver, JdbcProfile}

trait TestDBProfile extends DBProfile {

  val profile: JdbcProfile = H2Driver

}
