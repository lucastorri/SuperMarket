package other

import org.specs2.mutable._
import org.specs2.mock.Mockito

class SubjectUnderTest(
  var something: AnyRef,
  var somethingElse: AnyRef,
  var anotherOne: AnyRef,
  var lastOne: AnyRef
)

class UsingDefaultValuesInMethods extends Specification with Mockito {

  def createSubjectUnderTest(
    something: AnyRef = AnyRefStub,
    somethingElse: AnyRef = AnyRefStub,
    anotherOne: AnyRef = AnyRefStub,
    lastOne: AnyRef = AnyRefStub
  ) = new SubjectUnderTest(something, somethingElse, anotherOne, lastOne)

  "first test, that needs something" in {
    val somethingMock = mock[AnyRef]
    val subject = createSubjectUnderTest(something = somethingMock)

    there was no(somethingMock).hashCode
  }

  "seconds test, that uses somethingElse" in {
    val somethingElseMock = mock[AnyRef]
    val subject = createSubjectUnderTest(somethingElse = somethingElseMock)

    there was no(somethingElseMock).hashCode
  }

  //...

  object AnyRefStub

}
