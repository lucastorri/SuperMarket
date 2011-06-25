package supermarket

import supermarket.payment.{CheckoutCounter, CreditCard, SupermarketBill}
import scala.collection.JavaConversions._
import scalaj.collection.Imports._

import org.specs2.mutable._
import org.specs2.mock.Mockito
import org.specs2.specification.Scope
import supermarket.payment.PriceConverter


object CustomerTestScala_v4 {
  private final val RICE_PRODUCT_NAME = "Rice"
  private final val BEANS_PRODUCT_NAME = "Beans"
  private final val ORANGE_PRODUCT_NAME = "Orange"
}

class CustomerTestScala_v4 extends Specification with Mockito with PriceConverter {
  import CustomerTestScala_v4._

  "gets a new shopping cart from the supermarket" in {
    val supermarketMock = mock[Supermarket]
    val returnedShoppingCart = new ShoppingCart
    supermarketMock.getShoppingCart returns returnedShoppingCart

    val customer = new Customer(supermarketMock, creditCard)
    customer.getCart must be (returnedShoppingCart)
  }

  "shops following a shopping list" in new merchandiseContext {
    supermarketMock.getMerchandise returns merchandise

    customer.shop(shoppingList)

    val items = customer.getCart.getItems
    items.asScala must haveTheSameElementsAs(List(rice, rice, orange))
  }

  "gets a counter to checkout and pay" in new normalContext {
    val checkoutCounter = mock[CheckoutCounter]
    supermarketMock.getCheckoutCounter returns checkoutCounter

    val bill = customer.checkout
    there was one(checkoutCounter).checkout(customer.getCart)
  }

  "pays bills with credit card" in new normalContext {
    customer.pay(billMock)
    there was one(billMock).payWithCreditCard(creditCard)
  }

  val creditCard = new CreditCard

  trait normalContext extends Scope {
    val supermarketMock = spy(new Supermarket)
    val customer = new Customer(supermarketMock, creditCard)
    val billMock = mock[SupermarketBill]
    val shoppingList = new ShoppingList().jotDown(2, RICE_PRODUCT_NAME).jotDown(1, ORANGE_PRODUCT_NAME)
  }

  trait merchandiseContext extends normalContext {
    val rice = new SupermarketItem(RICE_PRODUCT_NAME, (1, 49))
    val beans = new SupermarketItem(BEANS_PRODUCT_NAME, (2, 13))
    val orange = new SupermarketItem(ORANGE_PRODUCT_NAME, (0, 39))
    val merchandise = Set(rice, beans, orange)
  }

}
