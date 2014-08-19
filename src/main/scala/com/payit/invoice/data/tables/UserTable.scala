package com.payit.invoice.data.tables

import com.payit.invoice.config.database.DBProfile
import com.payit.invoice.models.User

trait UserTable extends DBProfile with CustomerTable {

  val userTable = Users.table

  import profile.simple._

  class Users(tag: Tag) extends Table[User](tag, "users") {
    val id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    val firstName = column[String]("first_name", O.NotNull, O.Length(255, varying = true))
    val lastName = column[String]("last_name", O.NotNull, O.Length(255, varying = true))
    val customerID = column[Long]("customer_id", O.NotNull)
    def * = (firstName, lastName, customerID, id) <> (User.tupled, User.unapply _)
    def customerFK = foreignKey("USER_CUSTOMER_FK", customerID, customerTable)(
      r => r.id.get,
      onUpdate = ForeignKeyAction.NoAction,
      onDelete = ForeignKeyAction.NoAction
    )
  }

  private object Users {
    val table = TableQuery[Users]
  }

}
