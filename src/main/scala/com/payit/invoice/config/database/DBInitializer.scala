package com.payit.invoice.config.database

import com.payit.invoice.config.app.Tables
import com.payit.invoice.models.{User, Invoice, Customer}

trait DBInitializer extends Tables with DBSession {

  protected implicit lazy val session = database.createSession

  import profile.simple._

  def initDB = {

    tables.create

    session.withTransaction {

      val customersAutoInc = customerTable returning customerTable.map(_.id) into {
        case (c, id) => c.copy(id = id)
      }

      val usersAutoInc = userTable returning userTable.map(_.id) into {
        case (u, id) => u.copy(id = id)
      }

      val customer1 = customersAutoInc.insert(Customer("Customer One"))
      val customer2 = customersAutoInc.insert(Customer("Customer Two"))
      val customer3 = customersAutoInc.insert(Customer("Customer Three"))

      val user1 = usersAutoInc.insert(User("John", "Smith", customer1.id.get))
      val user2 = usersAutoInc.insert(User("Bob", "Smith", customer1.id.get))
      val user3 = usersAutoInc.insert(User("Joe", "Blow", customer2.id.get))
      val user4 = usersAutoInc.insert(User("Steve", "Martin", customer3.id.get))

      invoiceTable ++= Seq(
        Invoice("INV1000", "Description of INV1000", 1000.00, user1.id.get),
        Invoice("INV2000", "Description of INV2000", 16700.00, user1.id.get),
        Invoice("INV3000", "Description of INV3000", 156.43, user2.id.get),
        Invoice("INV6000", "Description of INV6000", 3000.00, user3.id.get),
        Invoice("INV6001", "Description of INV6001", 13300.00, user3.id.get),
        Invoice("INV8888", "Description of INV8888", 188.43, user4.id.get)
      )

    }

  }

}
