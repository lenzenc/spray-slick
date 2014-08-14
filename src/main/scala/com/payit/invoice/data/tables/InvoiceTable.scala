package com.payit.invoice.data.tables

import com.payit.invoice.config.DBProfile
import com.payit.invoice.models.Invoice

trait InvoiceTable { self: DBProfile with UserTable =>

  val invoiceTable = Invoices.table

  import profile.simple._

  class Invoices(tag: Tag) extends Table[Invoice](tag, "invoices") {
    val id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    val invoiceNumber = column[String]("invoice_number", O.NotNull, O.Length(100, varying=true))
    val description = column[String]("description", O.NotNull, O.Length(255, varying=true))
    val total = column[BigDecimal]("total", O.NotNull, O.DBType("decimal(23,8)"))
    val userID = column[Long]("user_id", O.NotNull)
    def * = (invoiceNumber, description, total, userID, id) <> (Invoice.tupled, Invoice.unapply _)
    def userFK = foreignKey("INVOICE_USER_FK", userID, userTable)(
      r => r.id.get,
      onUpdate = ForeignKeyAction.NoAction,
      onDelete = ForeignKeyAction.NoAction
    )
  }

  private object Invoices {
    val table = TableQuery[Invoices]
  }

}
