package other

import org.specs2.mutable._
import org.specs2.mock.Mockito

class CoolMatchers extends Specification with Tags {

  "more mocking" in {
    success
  }

  "data tables" in {
    success
  }

  "there are tags" in {
    success
  } tag("tagName")

}
