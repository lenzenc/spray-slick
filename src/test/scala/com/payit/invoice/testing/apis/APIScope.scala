package com.payit.invoice.testing.apis

import com.payit.invoice.config.app.Services
import com.payit.invoice.testing.FakeDBSession
import org.specs2.matcher.{ThrownExpectations, Scope}
import org.specs2.mock.Mockito

trait APIScope extends Scope with Mockito with Services with FakeDBSession with ThrownExpectations {

}
