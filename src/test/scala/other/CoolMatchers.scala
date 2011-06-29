package other

import org.specs2.mutable._
import org.specs2.mock.Mockito
import org.specs2.matcher.DataTables

class CoolMatchers extends Specification with Tags with DataTables {

  "more mocking" in {
    success
  }

  "data tables" in {
    "a" | "b" | "r" |
     1  !  7  !  8  |
     3  !  2  !  5  |
     0  !  9  !  9  |> { (a,b,r) => SuperCalculator.add(a,b) must be equalTo(r) }
  }

  "there are tags" in {
    success
  } tag("tagName")


  // "I want to fail :(" in {
  //   failure
  // }

}

object SuperCalculator {

  def add(a: Int, b: Int) = a + b
}
