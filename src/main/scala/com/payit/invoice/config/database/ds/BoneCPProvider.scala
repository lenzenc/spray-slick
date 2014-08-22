package com.payit.invoice.config.database.ds

import java.sql.Connection
import javax.sql.DataSource

import com.jolbox.bonecp.hooks.AbstractConnectionHook
import com.jolbox.bonecp.{BoneCPDataSource, ConnectionHandle}
import com.payit.invoice.config.Configuration

class BoneCPProvider(config: Configuration) extends DataSourceProvider {

  val dbConfig = config.getConfig("db").getOrElse(Configuration.empty)
  val dbs: Set[String] = dbConfig.subKeys

  def getDataSource(name: String = "default"): DataSource = {
    dataSources get name match {
      case Some(value) => value
      case None => sys.error(s"DataSource with name: $name is not configured")
    }
  }

  val dataSources: Map[String,DataSource] = dbs.map { dbName => (dbName, createDataSource(dbName)) }.toMap

  def getDriver(name: String = "default"): String = {
    dbConfig.getString(name + ".driver").getOrElse(error(name, s"Missing config: [db.$name.driver]"))
  }

  protected def createDataSource(name: String): DataSource = {

    val dataSource = new BoneCPDataSource
    val driver = getDriver(name)
    val url = dbConfig.getString(name + ".url").getOrElse(error(name, s"Missing config: [db.$name.url]"))
    val conf = dbConfig.getConfig(name).getOrElse(error(name, s"Missing config: [db.$name]"))

    val autocommit = conf.getBoolean("autocommit").getOrElse(true)
    val readOnly = conf.getBoolean("readOnly").getOrElse(false)
    val isolation = conf.getString("isolation").map {
      case "NONE" => Connection.TRANSACTION_NONE
      case "READ_COMMITTED" => Connection.TRANSACTION_READ_COMMITTED
      case "READ_UNCOMMITTED" => Connection.TRANSACTION_READ_UNCOMMITTED
      case "REPEATABLE_READ" => Connection.TRANSACTION_REPEATABLE_READ
      case "SERIALIZABLE" => Connection.TRANSACTION_SERIALIZABLE
      case unknown => error(name, s"Unknown isolation level [$unknown]")
    }
    val catalog = conf.getString("defaultCatalog")

    dataSource.setDriverClass(driver)
    dataSource.setJdbcUrl(url)
    conf.getString("user").map(dataSource.setUsername)
    conf.getString("pass").map(dataSource.setPassword)
    conf.getString("password").map(dataSource.setPassword)

    // Pool configuration
    dataSource.setPartitionCount(conf.getInt("partitionCount").getOrElse(1))
    dataSource.setMaxConnectionsPerPartition(conf.getInt("maxConnectionsPerPartition").getOrElse(30))
    dataSource.setMinConnectionsPerPartition(conf.getInt("minConnectionsPerPartition").getOrElse(5))
    dataSource.setAcquireIncrement(conf.getInt("acquireIncrement").getOrElse(1))
    dataSource.setAcquireRetryAttempts(conf.getInt("acquireRetryAttempts").getOrElse(10))
    dataSource.setAcquireRetryDelayInMs(conf.getMilliseconds("acquireRetryDelay").getOrElse(1000))
    dataSource.setConnectionTimeoutInMs(conf.getMilliseconds("connectionTimeout").getOrElse(1000))
    dataSource.setIdleMaxAge(conf.getMilliseconds("idleMaxAge").getOrElse(1000 * 60 * 10), java.util.concurrent.TimeUnit.MILLISECONDS)
    dataSource.setMaxConnectionAge(conf.getMilliseconds("maxConnectionAge").getOrElse(1000 * 60 * 60), java.util.concurrent.TimeUnit.MILLISECONDS)
    dataSource.setDisableJMX(conf.getBoolean("disableJMX").getOrElse(true))
    dataSource.setStatisticsEnabled(conf.getBoolean("statisticsEnabled").getOrElse(false))
    dataSource.setIdleConnectionTestPeriod(conf.getMilliseconds("idleConnectionTestPeriod").getOrElse(1000 * 60), java.util.concurrent.TimeUnit.MILLISECONDS)
    dataSource.setDisableConnectionTracking(conf.getBoolean("disableConnectionTracking").getOrElse(true))
    dataSource.setQueryExecuteTimeLimitInMs(conf.getMilliseconds("queryExecuteTimeLimit").getOrElse(0))

    conf.getString("initSQL").map(dataSource.setInitSQL)
    conf.getBoolean("logStatements").map(dataSource.setLogStatementsEnabled)
    conf.getString("connectionTestStatement").map(dataSource.setConnectionTestStatement)

    dataSource.setConnectionHook(new AbstractConnectionHook {

      override def onCheckOut(connection: ConnectionHandle) {
        connection.setAutoCommit(autocommit)
        connection.setReadOnly(readOnly)
        isolation.map(connection.setTransactionIsolation)
        catalog.map(connection.setCatalog)
      }

    })

    dataSource
  }

  private def error(dbName: String, message:String) = throw new RuntimeException(s"Database: $dbName - $message")

}
