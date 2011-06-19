package supermarket.payment

import org.junit.Before
import org.junit.Test
import supermarket.ShoppingCart
import supermarket.SupermarketItem
import supermarket.taxes.Tax
import org.hamcrest.core.Is.is
import org.junit.Assert.assertThat

class CheckoutCounterTestScala_v1 {

  private var counter: CheckoutCounter = null
  private var cart: ShoppingCart = null
  private var riceItem: SupermarketItem = null
  private var beansItem: SupermarketItem = null

  @Before
  def setUp: Unit = {
    counter = new CheckoutCounter

    cart = new ShoppingCart
    val tax = new PlusOneTaxStub
    riceItem = new SupermarketItem("Rice", new Price(2, 47), tax)
    beansItem = new SupermarketItem("Beans", new Price(0, 99), tax)
    cart.add(riceItem).add(riceItem).add(beansItem).add(beansItem)
  }

  @Test
  def knowsThatAEmptyCartHasPriceZero: Unit = {
    var bill: SupermarketBill = counter.checkout(new ShoppingCart)
    assertThat(bill.getTotalPrice, is(new Price(0, 0)))
  }

  @Test
  def sumsTheTotalPriceOfTheItemsInACart: Unit = {
    var bill: SupermarketBill = counter.checkout(cart)
    assertThat(bill.getTotalPrice, is(new Price(10, 92)))
  }

  private class PlusOneTaxStub extends Tax {
    def calculate(originalPrice: Price): Price = {
      return new Price(1, 0)
    }
  }

}
