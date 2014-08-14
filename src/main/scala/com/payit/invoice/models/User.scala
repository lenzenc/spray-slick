package com.payit.invoice.models

case class User(
  var firstName: String,
  var lastName: String,
  var customerID: Long,
  var id: Option[Long] = None
)
