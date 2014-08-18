package com.payit.invoice.data.daos

import com.payit.invoice.data.tables.UserTable
import com.payit.invoice.models.User

trait UserDAOModule { self: UserTable =>

  val userDAO: UserDAO

  import profile.simple._

  trait UserDAO {

    def findAllByCustomerID(customerID: Long)(implicit s: Session): Seq[User]
    def findByPK(pk: Long)(implicit s: Session): Option[User]

  }

  class UserDAOImpl extends UserDAO {

    def findAllByCustomerID(customerID: Long)(implicit s: Session): Seq[User] = {
      userTable.filter(_.customerID === customerID).list
    }

    def findByPK(pk: Long)(implicit s: Session): Option[User] = userTable.filter(_.id === pk).firstOption

  }

}
