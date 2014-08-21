package com.payit.invoice.services

import com.payit.invoice.config.database.DB
import com.payit.invoice.data.daos.UserDAOModule
import com.payit.invoice.models.User

trait UserServiceModule { self: UserDAOModule with DB =>

  val userService: UserService

  trait UserService {

    def listByCustomerID(customerID: Long): Seq[User]
    def getUser(userID: Long): Option[User]

  }

  class UserServiceImpl extends UserService {

    implicit lazy val session: Session = database.createSession

    def listByCustomerID(customerID: Long): Seq[User] = userDAO.findAllByCustomerID(customerID)

    def getUser(userID: Long): Option[User] = userDAO.findByPK(userID)

  }

}
