package supermarket.payment

import supermarket.{SupermarketItem, ShoppingCart}
import supermarket.taxes.Tax
import org.specs2.mutable._

class CheckoutCounterTestScala_v4 extends Specification with PriceConverter {

  private val riceItem = new SupermarketItem("Rice", (2,47), PlusOneTaxStub)
  private val beansItem = new SupermarketItem("Beans", (0,99), PlusOneTaxStub)
  def counter = new CheckoutCounter
  def cart = new ShoppingCart() add riceItem add riceItem add beansItem add beansItem

  "knows that an empty cart has price zero" in {
    val bill = counter checkout new ShoppingCart
    bill.getTotalPrice must be equalTo((0,0))
  }

  "sums the total price of the items in the cart" in {
     val bill = counter checkout cart
     bill.getTotalPrice must be equalTo((10,92))
  }

  object PlusOneTaxStub extends Tax {
    def calculate(p: Price) = (1,0)
  }
}
