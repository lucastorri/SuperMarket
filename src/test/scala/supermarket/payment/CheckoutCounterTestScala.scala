package supermarket.payment

import supermarket.{SupermarketItem, ShoppingCart}
import supermarket.taxes.Tax
import supermarket.payment.{Price, CheckoutCounter}

import org.specs2.mutable._
import org.specs2.specification.BeforeExample

class CheckoutCounterTestScala extends Specification with BeforeExample {
  sequential

  private var counter: CheckoutCounter = _
  private var cart: ShoppingCart = _
  private var riceItem: SupermarketItem = _
  private var beansItem: SupermarketItem = _

  def before = {
    counter = new CheckoutCounter

    cart = new ShoppingCart
    riceItem = new SupermarketItem("Rice", new Price(2,47), PlusOneTaxStub);
    beansItem = new SupermarketItem("Beans", new Price(0,99), PlusOneTaxStub);
    cart.add(riceItem).add(riceItem).add(beansItem).add(beansItem)
  }

  implicit def intTuple2Price(t: (Int, Int)) = new Price(t._1, t._2)

  "knows that an empty cart has price zero" in {
    val bill = counter.checkout(new ShoppingCart)
    bill.getTotalPrice must be equalTo((0,0))
  }

  "sums the total price of the items in the cart" in {
     val bill = counter.checkout(cart)
     bill.getTotalPrice must be equalTo((10,92))
  }

  object PlusOneTaxStub extends Tax {
    def calculate(p: Price) = new Price(1,0)
  }
}
