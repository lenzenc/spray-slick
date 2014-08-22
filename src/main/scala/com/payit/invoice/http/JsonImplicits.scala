package com.payit.invoice.http

import com.payit.invoice.models.Customer
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonImplicits extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val formatCustomer = jsonFormat2(Customer)

}
