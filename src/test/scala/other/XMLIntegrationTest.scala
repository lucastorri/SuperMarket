package other

import org.specs2.mutable._

class XMLIntegrationTest extends Specification {

  "xml is a first class citizen" in {
    val magicValue = "71"

    val xml =
      <some>
        <with>
          <something v={magicValue} />
        </with>
      </some>.toString


    ImAXMLHogUnderTest.devourXML(xml) must be ("delicious!")
  }

}

object ImAXMLHogUnderTest {

  def devourXML(xml: String) = "delicious!"

}
