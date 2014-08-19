package com.payit.invoice.services

import com.payit.invoice.config.database.DB
import com.payit.invoice.data.daos.InvoiceDAOModule
import com.payit.invoice.models.Invoice

trait InvoiceServiceModule { self: InvoiceDAOModule with DB =>

  val invoiceService: InvoiceService

  protected implicit lazy val session: Session = database.createSession

  trait InvoiceService {

    def listByUserID(userID: Long): Seq[Invoice]
    def listByCustomerID(customerID: Long): Seq[Invoice]
    def getInvoice(invoiceID: Long): Invoice

  }

  class InvoiceServiceImpl extends InvoiceService {

    def listByUserID(userID: Long): Seq[Invoice] = invoiceDAO.findAllByUserID(userID)

    def listByCustomerID(customerID: Long): Seq[Invoice] = invoiceDAO.findAllByCustomerID(customerID)

    def getInvoice(invoiceID: Long): Invoice = invoiceDAO.findByPK(invoiceID).getOrElse(
      throw new IllegalArgumentException(s"Invoice not found for invoiceID: $invoiceID")
    )

  }

}
