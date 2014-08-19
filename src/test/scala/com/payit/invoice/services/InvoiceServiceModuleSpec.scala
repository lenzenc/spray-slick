package com.payit.invoice.services

import com.payit.invoice.data.daos.InvoiceDAOModule
import com.payit.invoice.data.tables.InvoiceTable
import com.payit.invoice.models.Invoice
import com.payit.invoice.testing.{ServiceScope, ServiceSpec}

class InvoiceServiceModuleSpec extends ServiceSpec {

  trait ServiceTest extends ServiceScope with InvoiceServiceModule with InvoiceDAOModule with InvoiceTable {

    val invoiceService: InvoiceService = new InvoiceServiceImpl
    val invoiceDAO: InvoiceDAO = mock[InvoiceDAO]

  }

  ".listByUserID" should {

    "return a list of expected Invoices for a given userID" in new ServiceTest {

      override implicit lazy val session: Session = mock[Session]

      val expectedInvoiceList = Seq(Invoice("INV100", "Desc", 100.00, 5, Some(1)))
      invoiceDAO.findAllByUserID(5) returns expectedInvoiceList
      val invoices = invoiceService.listByUserID(5)
      there was one(invoiceDAO).findAllByUserID(5)
      invoices must containTheSameElementsAs(expectedInvoiceList)

    }

  }

  ".listByCustomerID" should {

    "return a list of expected Invoices for a given customerID" in new ServiceTest {

      override implicit lazy val session: Session = mock[Session]

      val expectedInvoiceList = Seq(Invoice("INV100", "Desc", 100.00, 5, Some(1)))
      invoiceDAO.findAllByCustomerID(1) returns expectedInvoiceList
      val invoices = invoiceService.listByCustomerID(1)
      there was one(invoiceDAO).findAllByCustomerID(1)
      invoices must containTheSameElementsAs(expectedInvoiceList)

    }

  }

  ".getInvoice" should {

    "return an Invoice for a given invoiceID" in new ServiceTest {

      override implicit lazy val session: Session = mock[Session]

      val expectedInvoice = Invoice("INV100", "Desc", 100.00, 5, Some(1))
      invoiceDAO.findByPK(5) returns Some(expectedInvoice)
      val invoice = invoiceService.getInvoice(5)
      there was one(invoiceDAO).findByPK(5)
      invoice must_== expectedInvoice

    }

    "throw an exception if an Invoice is not found for a given invoiceID" in new ServiceTest {

      override implicit lazy val session: Session = mock[Session]

      invoiceDAO.findByPK(1) returns None
      invoiceService.getInvoice(1) must throwA[IllegalArgumentException].like {
        case e => e.getMessage must startWith("Invoice not found")
      }
      there was one(invoiceDAO).findByPK(1)

    }

  }

}
