package other

import org.specs2.mutable._
import org.junit.runner.RunWith

@RunWith(classOf[org.specs2.runner.JUnitRunner])
class NestedSpecifications extends Specification {
  
  "A shopping cart".title
  
  "when in use" should {

    "receive items" in {
      success
    }

    "know how many items of the same type it has" in {
      success
    }

  }

}
