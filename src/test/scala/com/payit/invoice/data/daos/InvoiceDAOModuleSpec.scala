package com.payit.invoice.data.daos

import com.payit.invoice.data.tables.InvoiceTable
import com.payit.invoice.models.{Invoice, User, Customer}
import com.payit.invoice.testing.daos.{DAOScope, DAOSpec}

class InvoiceDAOModuleSpec extends DAOSpec {

  import profile.simple._

  trait DAOTest extends DAOScope with InvoiceDAOModule with InvoiceTable {

    val invoiceDAO: InvoiceDAO = new InvoiceDAOImpl

  }

  trait Invoices {

    val customerA = Customer("Customer A", Some(1))
    val customerB = Customer("Customer B", Some(2))
    customerTable.forceInsertAll(customerA, customerB)
    val user1 = User("Bob", "Smith", customerA.id.get, Some(1))
    val user2 = User("Joe", "Blow", customerA.id.get, Some(2))
    val user3 = User("Joe", "Blow", customerB.id.get, Some(3))
    userTable.forceInsertAll(user1, user2, user3)
    val invoice100 = Invoice("INV100", "Desc of INVOICE#100", 1900.00, user3.id.get, Some(1))
    invoiceTable.forceInsertAll(
      invoice100,
      Invoice("INV200", "Desc of INVOICE#200", 1900.00, user1.id.get, Some(2)),
      Invoice("INV300", "Desc of INVOICE#300", 1900.00, user2.id.get, Some(3)),
      Invoice("INV400", "Desc of INVOICE#400", 1900.00, user1.id.get, Some(4)),
      Invoice("INV500", "Desc of INVOICE#500", 1900.00, user2.id.get, Some(5)),
      Invoice("INV600", "Desc of INVOICE#600", 1900.00, user3.id.get, Some(6))
    )

  }

  ".findAllByUserID" should {

    "return a list of expected invoices for a given user ID" in new DAOTest with Invoices {

      val invoices = invoiceDAO.findAllByUserID(user1.id.get)
      invoices must not(beNull)
      invoices.size must_== 2
      invoices.map(_.id.get) must contain(exactly(2L, 4L))

    }

    "return an empty list of invoices if none exist for a given user ID" in new DAOTest with Invoices {

      val invoices = invoiceDAO.findAllByUserID(-1)
      invoices must not(beNull)
      invoices must beEmpty

    }

  }

  ".findAllByCustomerID" should {

    "return a list of expected invoices for a given customer ID" in new DAOTest with Invoices {

      val invoices = invoiceDAO.findAllByCustomerID(customerA.id.get)
      invoices must not(beNull)
      invoices.size must_== 4
      invoices.map(_.id.get) must contain(exactly(2L, 3L, 4L, 5L))

    }

    "return an empty list of invoices if none exist for given customer ID" in new DAOTest with Invoices {

      val invoices = invoiceDAO.findAllByCustomerID(-1)
      invoices must not(beNull)
      invoices must beEmpty

    }

  }

  ".findByPK" should {

    "return an Option containing the expected invoice for the given PK" in new DAOTest with Invoices {

      val invoice = invoiceDAO.findByPK(invoice100.id.get)
      invoice must not(beNull)
      invoice must beSome[Invoice]
      invoice.get.id must_== Some(1)

    }

    "return on Option of None if no invoice is found for a given PK" in new DAOTest {

      val invoice = invoiceDAO.findByPK(-1)
      invoice must not(beNull)
      invoice must beNone

    }

  }

}
