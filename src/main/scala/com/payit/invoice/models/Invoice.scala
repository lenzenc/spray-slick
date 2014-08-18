package com.payit.invoice.models

case class Invoice(
  var invoiceNumber: String,
  var description: String,
  var total: BigDecimal,
  var userID: Long,
  var id: Option[Long] = None
)
