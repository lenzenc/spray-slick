package com.payit.invoice.data.daos

import com.payit.invoice.data.tables.CustomerTable
import com.payit.invoice.models.Customer

trait CustomerDAOModule { self: CustomerTable =>

  val customerDAO: CustomerDAO

  import profile.simple._

  trait CustomerDAO {

    def findAll(implicit s: Session): Seq[Customer]
    def findByPK(pk: Long)(implicit s: Session): Option[Customer]

  }

  class CustomerDAOImpl extends CustomerDAO {

    def findAll(implicit s: Session): Seq[Customer] = null
    def findByPK(pk: Long)(implicit s: Session): Option[Customer] = null

  }

}
