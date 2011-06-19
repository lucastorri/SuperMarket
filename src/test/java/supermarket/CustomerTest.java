package supermarket;

import org.junit.Before;
import org.junit.Test;
import supermarket.payment.CheckoutCounter;
import supermarket.payment.CreditCard;
import supermarket.payment.Price;
import supermarket.payment.SupermarketBill;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static supermarket.util.ItemCounter.countItem;

public class CustomerTest {


    private static final String RICE_PRODUCT_NAME = "Rice";
    private static final String BEANS_PRODUCT_NAME = "Beans";
    private static final String ORANGE_PRODUCT_NAME = "Orange";
    private Customer customer;
    private CreditCard creditCard;
    private SupermarketBill billMock;
    private ShoppingList shoppingList;
    private Supermarket supermarketMock;

    @Before
    public void setUp() throws Exception {
        creditCard = new CreditCard();
        supermarketMock = mock(Supermarket.class);
        when(supermarketMock.getShoppingCart()).thenCallRealMethod();
        customer = new Customer(supermarketMock, creditCard);
        billMock = mock(SupermarketBill.class);
        shoppingList = new ShoppingList().jotDown(2, RICE_PRODUCT_NAME).jotDown(1, ORANGE_PRODUCT_NAME);
    }

    @Test
    public void getsANewShoppingCartFromTheSupermarket() {
        Supermarket supermarketMock = mock(Supermarket.class);
        ShoppingCart returnedShoppingCart = new ShoppingCart();
        when(supermarketMock.getShoppingCart()).thenReturn(returnedShoppingCart);

        Customer customer = new Customer(supermarketMock, creditCard);
        assertThat(customer.getCart(), is(returnedShoppingCart));
    }

    @Test
    public void shopsFollowingAShoppingList() {
        SupermarketItem rice = new SupermarketItem(RICE_PRODUCT_NAME, new Price(1, 49));
        SupermarketItem beans = new SupermarketItem(BEANS_PRODUCT_NAME, new Price(2, 13));
        SupermarketItem orange = new SupermarketItem(ORANGE_PRODUCT_NAME, new Price(0, 39));
        List<SupermarketItem> merchandise = Arrays.asList(rice, beans, orange);
        when(supermarketMock.getMerchandise()).thenReturn(new HashSet<SupermarketItem>(merchandise));

        customer.shop(shoppingList);

        List<SupermarketItem> items = customer.getCart().getItems();
        assertThat(items.size(), is(3));
        assertThat(countItem(rice, items), is(2));
        assertThat(countItem(orange, items), is(1));
        assertThat(countItem(beans, items), is(0));
    }

    @Test
    public void getsACounterToCheckoutAndPay() {
        CheckoutCounter checkoutCounterClass = mock(CheckoutCounter.class);
        when(supermarketMock.getCheckoutCounter()).thenReturn(checkoutCounterClass);

        SupermarketBill bill = customer.checkout();
        verify(checkoutCounterClass).checkout(customer.getCart());
    }

    @Test
    public void paysBillsWithCreditCard() {
        customer.pay(billMock);
        verify(billMock).payWithCreditCard(same(creditCard));
    }
}
