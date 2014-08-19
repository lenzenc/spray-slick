package com.payit.invoice.config

import org.specs2.mutable._

class ConfigurationSpec extends Specification {

  lazy val config: Configuration = Configuration.load

  ".getString" should {

    "return a String value for an existing configuration key" in {
      val value = config.getString("spec.testing.string")
      value must beSome[String]
      value.get must_== "value"
    }

    "return None for a configuration key that does not exist" in {
      val value = config.getString("doesnotexist")
      value must beNone
    }

  }

  ".getBoolean" should {

    "return a Boolean value for an existing configuration key" in {
      val value = config.getBoolean("spec.testing.boolean")
      value must beSome[Boolean]
      value.get must beTrue
    }

    "return None for a configuration key that does not exist" in {
      val value = config.getBoolean("doesnotexist")
      value must beNone
    }

  }

  ".getInt" should {

    "return a Int value for an existing configuration key" in {
      val value = config.getInt("spec.testing.int")
      value must beSome[Int]
      value.get must_== 10
    }

    "return None for a configuration key that does not exist" in {
      val value = config.getInt("doesnotexist")
      value must beNone
    }

  }

  ".getLong" should {

    "return a Long value for an existing configuration key" in {
      val value = config.getLong("spec.testing.long")
      value must beSome[Long]
      value.get must_== 1000000000
    }

    "return None for a configuration key that does not exist" in {
      val value = config.getLong("doesnotexist")
      value must beNone
    }

  }

  ".getMilliseconds" should {

    "return a Long value for an existing configuration key" in {
      val value = config.getMilliseconds("spec.testing.milliseconds")
      value must beSome[Long]
      value.get must_== 1000000000
    }

    "return None for a configuration key that does not exist" in {
      val value = config.getMilliseconds("doesnotexist")
      value must beNone
    }

  }

  ".getConfig" should {

    "return a Configuration value for an existing configuration key" in {
      val configVal = config.getConfig("spec.testing")
      configVal must beSome[Configuration]
      val value = configVal.get.getString("string")
      value.get must_== "value"
    }

    "return None for a configuration key that does not exist" in {
      val value = config.getConfig("doesnotexist")
      value must beNone
    }

  }

}
