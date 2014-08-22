package com.payit.invoice.services

import com.payit.invoice.config.database.DBSession
import com.payit.invoice.data.daos.InvoiceDAOModule
import com.payit.invoice.models.Invoice

trait InvoiceServiceModule { self: InvoiceDAOModule with DBSession =>

  val invoiceService: InvoiceService

  trait InvoiceService {

    def listByUserID(userID: Long): Seq[Invoice]
    def listByCustomerID(customerID: Long): Seq[Invoice]
    def getInvoice(invoiceID: Long): Option[Invoice]

  }

  class InvoiceServiceImpl extends InvoiceService {

    implicit lazy val session: Session = database.createSession

    def listByUserID(userID: Long): Seq[Invoice] = invoiceDAO.findAllByUserID(userID)

    def listByCustomerID(customerID: Long): Seq[Invoice] = invoiceDAO.findAllByCustomerID(customerID)

    def getInvoice(invoiceID: Long): Option[Invoice] = invoiceDAO.findByPK(invoiceID)

  }

}
