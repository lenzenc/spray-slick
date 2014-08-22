package com.payit.invoice.services

import com.payit.invoice.models.Invoice
import com.payit.invoice.testing.services.{ServiceSpec, ServiceScope}

class InvoiceServiceModuleSpec extends ServiceSpec {

  trait ServiceTest extends ServiceScope with InvoiceServiceModule {

    val invoiceService: InvoiceService = new InvoiceServiceImpl
    override lazy val invoiceDAO: InvoiceDAO = mock[InvoiceDAO]

  }

  ".listByUserID" should {

    "return a list of expected Invoices for a given userID" in new ServiceTest {

      val expectedInvoiceList = Seq(Invoice("INV100", "Desc", 100.00, 5, Some(1)))
      invoiceDAO.findAllByUserID(5) returns expectedInvoiceList
      val invoices = invoiceService.listByUserID(5)
      there was one(invoiceDAO).findAllByUserID(5)
      invoices must containTheSameElementsAs(expectedInvoiceList)

    }

  }

  ".listByCustomerID" should {

    "return a list of expected Invoices for a given customerID" in new ServiceTest {

      val expectedInvoiceList = Seq(Invoice("INV100", "Desc", 100.00, 5, Some(1)))
      invoiceDAO.findAllByCustomerID(1) returns expectedInvoiceList
      val invoices = invoiceService.listByCustomerID(1)
      there was one(invoiceDAO).findAllByCustomerID(1)
      invoices must containTheSameElementsAs(expectedInvoiceList)

    }

  }

  ".getInvoice" should {

    "return an Invoice for a given invoiceID" in new ServiceTest {

      val expectedInvoice = Invoice("INV100", "Desc", 100.00, 5, Some(1))
      invoiceDAO.findByPK(5) returns Some(expectedInvoice)
      val invoice = invoiceService.getInvoice(5)
      there was one(invoiceDAO).findByPK(5)
      invoice.get must_== expectedInvoice

    }

    "return None if an Invoice is not found for a given invoiceID" in new ServiceTest {

      invoiceDAO.findByPK(1) returns None
      val invoice = invoiceService.getInvoice(1)
      there was one(invoiceDAO).findByPK(1)
      invoice must beNone

    }

  }

}
