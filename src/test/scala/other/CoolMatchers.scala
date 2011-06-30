package other

import org.specs2.mutable._
import org.specs2.mock.Mockito
import org.specs2.matcher.DataTables

class CoolMatchers extends Specification with Tags with DataTables {

  "file matchers" in {
    "/etc/passwd" must beAnExistingPath
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

  // there is a big list, better check them all here:
  // http://etorreborre.github.com/specs2/guide/org.specs2.guide.Matchers.html#Matchers+guide

}

object SuperCalculator {

  def add(a: Int, b: Int) = a + b
}
