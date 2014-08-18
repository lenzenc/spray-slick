package com.payit.invoice.data.daos

import com.payit.invoice.SortOrder
import com.payit.invoice.SortOrder._
import com.payit.invoice.data.tables.CustomerTable
import com.payit.invoice.models.Customer

trait CustomerDAOModule { self: CustomerTable =>

  val customerDAO: CustomerDAO

  import profile.simple._

  trait CustomerDAO {

    def findAll(sortOrder: SortOrder = SortOrder.ASC)(implicit s: Session): Seq[Customer]
    def findByPK(pk: Long)(implicit s: Session): Option[Customer]

  }

  class CustomerDAOImpl extends CustomerDAO {

    def findAll(sortOrder: SortOrder = SortOrder.ASC)(implicit s: Session): Seq[Customer] = {
      customerTable.sortBy(
        sortOrder match {
          case SortOrder.ASC => _.name.asc
          case SortOrder.DESC => _.name.desc
        }
      ).list
    }

    def findByPK(pk: Long)(implicit s: Session): Option[Customer] = customerTable.filter(_.id === pk).firstOption

  }

}
