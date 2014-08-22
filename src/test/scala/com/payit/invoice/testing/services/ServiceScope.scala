package com.payit.invoice.testing.services

import com.payit.invoice.config.app.DAOs
import com.payit.invoice.testing.FakeDBSession
import org.specs2.matcher.{ThrownExpectations, Scope}
import org.specs2.mock.Mockito

trait ServiceScope extends Scope with DAOs with FakeDBSession with Mockito with ThrownExpectations {

}
