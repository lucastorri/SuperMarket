package supermarket

import supermarket.payment.{CheckoutCounter, CreditCard, SupermarketBill}
import scalaj.collection.Imports._

import org.specs2.mutable._
import org.specs2.specification.Scope
import org.mockito.Matchers.same
import org.mockito.Mockito._
import supermarket.payment.PriceConverter


object CustomerTestScala_v3 {
  private final val RICE_PRODUCT_NAME = "Rice"
  private final val BEANS_PRODUCT_NAME = "Beans"
  private final val ORANGE_PRODUCT_NAME = "Orange"
}

class CustomerTestScala_v3 extends Specification with PriceConverter {
  import CustomerTestScala_v3._

  "gets a new shopping cart from the supermarket" in {
    val supermarketMock = mock(classOf[Supermarket])
    val returnedShoppingCart = new ShoppingCart
    when(supermarketMock.getShoppingCart).thenReturn(returnedShoppingCart)

    val customer = new Customer(supermarketMock, creditCard)
    customer.getCart must be (returnedShoppingCart)
  }

  "shops following a shopping list" in new merchandiseContext {
    when(supermarketMock.getMerchandise).thenReturn(merchandise)
    customer.shop(shoppingList)

    val items = customer.getCart.getItems
    items.asScala must haveTheSameElementsAs(List(rice, rice, orange))
  }

  "gets a counter to checkout and pay" in new normalContext {
    val checkoutCounter = mock(classOf[CheckoutCounter])
    when(supermarketMock.getCheckoutCounter).thenReturn(checkoutCounter)

    val bill = customer.checkout
    verify(checkoutCounter).checkout(customer.getCart)
  }

  "pays bills with credit card" in new normalContext {
    customer.pay(billMock)
    verify(billMock).payWithCreditCard(same(creditCard))
  }

  val creditCard = new CreditCard

  trait normalContext extends Scope {
    val supermarketMock = mock(classOf[Supermarket])
    when(supermarketMock.getShoppingCart).thenCallRealMethod
    val customer = new Customer(supermarketMock, creditCard)
    val billMock = mock(classOf[SupermarketBill])
    val shoppingList = new ShoppingList().jotDown(2, RICE_PRODUCT_NAME).jotDown(1, ORANGE_PRODUCT_NAME)
  }

  trait merchandiseContext extends normalContext {
    val rice = new SupermarketItem(RICE_PRODUCT_NAME, (1, 49))
    val beans = new SupermarketItem(BEANS_PRODUCT_NAME, (2, 13))
    val orange = new SupermarketItem(ORANGE_PRODUCT_NAME, (0, 39))
    val merchandise = Set(rice, beans, orange)
  }

}
