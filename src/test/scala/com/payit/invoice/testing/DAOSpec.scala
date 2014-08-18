package com.payit.invoice.testing

import java.sql.SQLException

import org.specs2.execute.{Result, AsResult}
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.specification.AroundExample

trait DAOSpec extends Specification with Mockito with TestDB with Before with AroundExample {

  // Because we rely on setup data in a database we want to make sure all tests are sequential instead of parallel,
  // which of course would result in DB constraint issues.
  sequential

  import profile.simple._

  def before = {
    try {
      tables.drop
    } catch {
      case _: Exception => true
    }
    tables.create
  }

  def after = {
    tables.drop
  }

  def around[T : AsResult](t: =>T): Result = {
    session.withTransaction { try AsResult(t) finally session.rollback() }
  }
}
