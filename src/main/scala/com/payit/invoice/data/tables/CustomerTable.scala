package com.payit.invoice.data.tables

import com.payit.invoice.config.database.profile.DBProfile
import com.payit.invoice.models.Customer

trait CustomerTable extends DBProfile {

  // Because there is likely no need to override the customerTable then it might just be easier to create a private
  // singleton(object) within this trait and declare the tables val to be injected into other traits instead of doing
  // that in a normal cake fashion.

  val customerTable = Customers.table

  import profile.simple._

  class Customers(tag: Tag) extends Table[Customer](tag, "customers") {
    val id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name", O.NotNull, O.Length(255, varying=true))
    def * = (name, id) <> (Customer.tupled, Customer.unapply _)
    def uniqueName = index("UNIQUE_NAME_IDX", name, unique = true)
  }

  private object Customers {
    var table = TableQuery[Customers]
  }

}