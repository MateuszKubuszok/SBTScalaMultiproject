package pl.combosolutions

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import pl.combosolutions.TestTag._

class CommonSpec extends Specification with Mockito {

  "Common" should {

    "integrate with Cauchy" in {
      1 mustEqual 1
    } tag IntegrationTest

    "function in Church" in {
      1 mustEqual 1
    } tag FunctionalTest

    "measure units" in {
      1 mustEqual 1
    } tag UnitTest

    "do nothing" in {
      1 mustEqual 1
    } tag DisabledTest
  }
}
