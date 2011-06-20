package supermarket

import org.junit.Before
import org.junit.Test
import supermarket.payment.CheckoutCounter
import supermarket.payment.CreditCard
import supermarket.payment.Price
import supermarket.payment.SupermarketBill
import java.util.Arrays
import java.util.HashSet
import java.util.List
import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.mockito.Matchers.same
import org.mockito.Mockito._
import supermarket.util.ItemCounter.countItem

object CustomerTestScala_v1 {
  private final val RICE_PRODUCT_NAME: String = "Rice"
  private final val BEANS_PRODUCT_NAME: String = "Beans"
  private final val ORANGE_PRODUCT_NAME: String = "Orange"
}

class CustomerTestScala_v1 {
  import CustomerTestScala_v1._

  private var customer: Customer = null
  private var creditCard: CreditCard = null
  private var billMock: SupermarketBill = null
  private var shoppingList: ShoppingList = null
  private var supermarketMock: Supermarket = null

  @Before
  def setUp: Unit = {
    creditCard = new CreditCard
    supermarketMock = mock(classOf[Supermarket])
    when(supermarketMock.getShoppingCart).thenCallRealMethod
    customer = new Customer(supermarketMock, creditCard)
    billMock = mock(classOf[SupermarketBill])
    shoppingList = new ShoppingList().jotDown(2, RICE_PRODUCT_NAME).jotDown(1, ORANGE_PRODUCT_NAME)
  }

  @Test
  def getsANewShoppingCartFromTheSupermarket: Unit = {
    var supermarketMock: Supermarket = mock(classOf[Supermarket])
    var returnedShoppingCart: ShoppingCart = new ShoppingCart
    when(supermarketMock.getShoppingCart).thenReturn(returnedShoppingCart)

    var customer: Customer = new Customer(supermarketMock, creditCard)
    assertThat(customer.getCart, is(returnedShoppingCart))
  }

  @Test
  def shopsFollowingAShoppingList: Unit = {
    var rice: SupermarketItem = new SupermarketItem(RICE_PRODUCT_NAME, new Price(1, 49))
    var beans: SupermarketItem = new SupermarketItem(BEANS_PRODUCT_NAME, new Price(2, 13))
    var orange: SupermarketItem = new SupermarketItem(ORANGE_PRODUCT_NAME, new Price(0, 39))
    var merchandise: List[SupermarketItem] = Arrays.asList(rice, beans, orange)
    when(supermarketMock.getMerchandise).thenReturn(new HashSet[SupermarketItem](merchandise))

    customer.shop(shoppingList)

    var items: List[SupermarketItem] = customer.getCart.getItems
    assertThat(items.size, is(3))
    assertThat(countItem(rice, items), is(2))
    assertThat(countItem(orange, items), is(1))
    assertThat(countItem(beans, items), is(0))
  }

  @Test
  def getsACounterToCheckoutAndPay: Unit = {
    var checkoutCounterClass: CheckoutCounter = mock(classOf[CheckoutCounter])
    when(supermarketMock.getCheckoutCounter).thenReturn(checkoutCounterClass)

    var bill: SupermarketBill = customer.checkout
    verify(checkoutCounterClass).checkout(customer.getCart)
  }

  @Test
  def paysBillsWithCreditCard: Unit = {
    customer.pay(billMock)
    verify(billMock).payWithCreditCard(same(creditCard))
  }

}
