package com.payit.invoice.services

import com.payit.invoice.config.database.DB
import com.payit.invoice.data.daos.UserDAOModule
import com.payit.invoice.models.User

trait UserServiceModule { self: UserDAOModule with DB =>

  val userService: UserService

  protected implicit lazy val session: Session = database.createSession

  trait UserService {

    def listByCustomerID(customerID: Long): Seq[User]
    def getUser(userID: Long): User

  }

  class UserServiceImpl extends UserService {

    def listByCustomerID(customerID: Long): Seq[User] = userDAO.findAllByCustomerID(customerID)

    def getUser(userID: Long): User = {
      userDAO.findByPK(userID).getOrElse(
        throw new IllegalArgumentException(s"User not found for userID: $userID")
      )
    }

  }

}
