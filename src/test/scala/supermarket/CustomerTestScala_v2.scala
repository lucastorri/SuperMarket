package supermarket

import supermarket.payment.{CheckoutCounter, CreditCard, SupermarketBill}
import java.util.{Arrays, HashSet}
import org.mockito.Matchers.same
import org.mockito.Mockito._
import supermarket.util.ItemCounter.countItem

import org.specs2.mutable._
import org.specs2.specification.Scope
import supermarket.payment.PriceConverter


object CustomerTestScala_v2 {
  private final val RICE_PRODUCT_NAME = "Rice"
  private final val BEANS_PRODUCT_NAME = "Beans"
  private final val ORANGE_PRODUCT_NAME = "Orange"
}

class CustomerTestScala_v2 extends Specification with PriceConverter {
  import CustomerTestScala_v2._

  "gets a new shopping cart from the supermarket" in {
    var supermarketMock = mock(classOf[Supermarket])
    var returnedShoppingCart = new ShoppingCart
    when(supermarketMock.getShoppingCart).thenReturn(returnedShoppingCart)

    var customer = new Customer(supermarketMock, creditCard)
    customer.getCart must be (returnedShoppingCart)
  }

  "shops following a shopping list" in new merchandiseContext {
    when(supermarketMock.getMerchandise).thenReturn(new HashSet[SupermarketItem](merchandise))
    customer.shop(shoppingList)

    val items = customer.getCart.getItems
    items.size must be equalTo(3)
    countItem(rice, items) must be equalTo(2)
    countItem(orange, items) must be equalTo(1)
    countItem(beans, items) must be equalTo(0)
  }

  "gets a counter to checkout and pay" in new normalContext {
    var checkoutCounter = mock(classOf[CheckoutCounter])
    when(supermarketMock.getCheckoutCounter).thenReturn(checkoutCounter)

    var bill = customer.checkout
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
    var rice = new SupermarketItem(RICE_PRODUCT_NAME, (1, 49))
    var beans = new SupermarketItem(BEANS_PRODUCT_NAME, (2, 13))
    var orange = new SupermarketItem(ORANGE_PRODUCT_NAME, (0, 39))
    var merchandise = Arrays.asList(rice, beans, orange)
  }

}
