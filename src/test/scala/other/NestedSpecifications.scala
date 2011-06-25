package other

import org.specs2.mutable._

object NestedSpecifications extends Specification {
  
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
