package com.payit.invoice.data.daos

import com.payit.invoice.data.tables.InvoiceTable
import com.payit.invoice.models.Invoice

trait InvoiceDAOModule { self: InvoiceTable =>

  val invoiceDAO: InvoiceDAO

  import profile.simple._

  trait InvoiceDAO {

    def findAllByUserID(userID: Long)(implicit s: Session): Seq[Invoice]
    def findAllByCustomerID(customerID: Long)(implicit s: Session): Seq[Invoice]
    def findByPK(pk: Long)(implicit s: Session): Option[Invoice]

  }

  class InvoiceDAOImpl extends InvoiceDAO {

    def findAllByUserID(userID: Long)(implicit s: Session): Seq[Invoice] = {
      invoiceTable.filter(_.userID === userID).list
    }

    def findAllByCustomerID(customerID: Long)(implicit s: Session): Seq[Invoice] = {
      (for {
        (invoice, user) <- invoiceTable leftJoin userTable on (_.userID === _.id) if user.customerID === customerID
      } yield invoice).list
    }

    def findByPK(pk: Long)(implicit s: Session): Option[Invoice] = invoiceTable.filter(_.id === pk).firstOption

  }

}
