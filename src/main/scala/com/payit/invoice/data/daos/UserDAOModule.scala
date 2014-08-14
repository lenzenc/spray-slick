package com.payit.invoice.data.daos

import com.payit.invoice.data.tables.UserTable
import com.payit.invoice.models.User

trait UserDAOModule { self: UserTable =>

  val userDAO: UserDAO

  import profile.simple._

  trait UserDAO {

    def findAllByCustomerID(customerID: Long)(implicit s: Session)
    def findByPK(pk: Long)(implicit s: Session): Option[User]

  }

  class UserDAOImpl extends UserDAO {

    def findAllByCustomerID(customerID: Long)(implicit s: Session) = null
    def findByPK(pk: Long)(implicit s: Session): Option[User] = null

  }

}
