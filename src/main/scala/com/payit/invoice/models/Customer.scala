package com.payit.invoice.models

case class Customer(
  var name: String,
  var id: Option[Long] = None
)
