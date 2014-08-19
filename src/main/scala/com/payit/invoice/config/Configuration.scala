package com.payit.invoice.config

import com.typesafe.config.{ConfigException, ConfigFactory, Config}
import scala.collection.JavaConverters._
import scala.util.control.NonFatal

/**
 * Provides a wrapper configuration around Typesafe's configuration library. Given's Typesafe's library is writting in
 * java it does not take advantage of scala's Option class so that you can choose "else" conditions is a configuration
 * is not available.
 *
 * See more about Typesafe's configuration library @ https://github.com/typesafehub/config
 */
case class Configuration(underlying: Config) {

  import Configuration.asScalaList

  def getConfig(path: String): Option[Configuration] = getValue(path, underlying.getConfig(path)).map(Configuration(_))

  def getString(path: String): Option[String] = getValue(path, underlying.getString(path))

  def getBoolean(path: String): Option[Boolean] = getValue(path, underlying.getBoolean(path))

  def getInt(path: String): Option[Int] = getValue(path, underlying.getInt(path))

  def getLong(path: String): Option[Long] = getValue(path, underlying.getLong(path))

  def getMilliseconds(path: String): Option[Long] = getLong(path)

  private def getValue[T](path: String, v: => T): Option[T] = {
    try {
      Option(v)
    } catch {
      case e: ConfigException.Missing => None
      case NonFatal(e) => throw e
    }
  }

}

object Configuration {

  private lazy val config: Config = ConfigFactory.load

  def load: Configuration = Configuration(config)

  private[Configuration] def asScalaList[A](l: java.util.List[A]): Seq[A] = asScalaBufferConverter(l).asScala.toList

}
