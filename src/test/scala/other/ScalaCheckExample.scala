package other

import org.specs2.ScalaCheck
import org.specs2.mutable._
import org.specs2.mock.Mockito

class ScalaCheckExample extends Specification with ScalaCheck {

  "it only accepts positive integers" ! check { i: Int => 
    // it should fail
    IOnlyAcceptPositiveIntegers.accepted(i) must beTrue
  }
}

object IOnlyAcceptPositiveIntegers {

  def accepted(i: Int) = i >= 0
}
