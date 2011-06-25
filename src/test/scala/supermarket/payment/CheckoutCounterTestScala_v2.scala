package supermarket.payment

import supermarket.{SupermarketItem, ShoppingCart}
import supermarket.taxes.Tax
import org.specs2.mutable._
import org.specs2.specification.BeforeExample

class CheckoutCounterTestScala_v2 extends Specification with BeforeExample {
  sequential

  private var counter: CheckoutCounter = _
  private var cart: ShoppingCart = _
  private var riceItem: SupermarketItem = _
  private var beansItem: SupermarketItem = _

  implicit def intTuple2Price(t: (Int, Int)) = new Price(t._1, t._2)

  def before = {
    counter = new CheckoutCounter

    cart = new ShoppingCart
    riceItem = new SupermarketItem("Rice", (2,47), PlusOneTaxStub)
    beansItem = new SupermarketItem("Beans", (0,99), PlusOneTaxStub)
    cart.add(riceItem).add(riceItem).add(beansItem).add(beansItem)
  }

  "knows that an empty cart has price zero" in {
    val bill = counter.checkout(new ShoppingCart)
    bill.getTotalPrice must be equalTo((0,0))
  }

  "sums the total price of the items in the cart" in {
     val bill = counter.checkout(cart)
     bill.getTotalPrice must be equalTo((10,92))
  }

  object PlusOneTaxStub extends Tax {
    def calculate(p: Price) = (1,0)
  }
}
