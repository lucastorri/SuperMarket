package supermarket.util;

import org.junit.Before;
import org.junit.Test;
import supermarket.Customer;
import supermarket.ShoppingList;
import supermarket.Supermarket;
import supermarket.SupermarketItem;
import supermarket.payment.CreditCard;
import supermarket.payment.Price;
import supermarket.payment.SupermarketBill;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static supermarket.util.ItemCounter.countItem;

public class ShoppingBotTest {

    private Customer customer;
    private ShoppingBot shoppingBot;
    private ShoppingList shoppingList1;
    private ShoppingList shoppingList2;
    private CreditCard creditCardMock;
    private SupermarketItem rice;
    private SupermarketItem beans;
    private SupermarketItem orange;
    private SupermarketItem apple;
    private Supermarket supermarketMock;

    @Before
    public void setUp() throws Exception {
        rice = new SupermarketItem("Rice", new Price(1,14));
        beans = new SupermarketItem("Beans", new Price(1,14));
        orange = new SupermarketItem("Orange", new Price(1,14));
        apple = new SupermarketItem("Apple", new Price(1,14));

        creditCardMock = mock(CreditCard.class);
        supermarketMock = mock(Supermarket.class);
        when(supermarketMock.getShoppingCart()).thenCallRealMethod();
        when(supermarketMock.getCheckoutCounter()).thenCallRealMethod();

        customer = new Customer(supermarketMock, creditCardMock);
        shoppingList1 = new ShoppingList().jotDown(2, "Rice").jotDown(4, "Beans");
        shoppingList2 = new ShoppingList().jotDown(3, "Orange").jotDown(5, "Apple");

        shoppingBot = new ShoppingBot(customer);
    }

    @Test
    public void doesAllShoppingStepsAutomatically() {
        when(creditCardMock.authorize(any(SupermarketBill.class))).thenReturn(true);
        when(supermarketMock.getMerchandise()).thenReturn(new HashSet<SupermarketItem>(Arrays.asList(rice, beans, orange, apple)));

        SupermarketBill bill = shoppingBot.shopAll(shoppingList1, shoppingList2);

        assertTrue(bill.isPayed());
        List<SupermarketItem> items = bill.getItems();
        assertThat(items.size(), is(14));
        assertThat(countItem(rice, items), is(2));
        assertThat(countItem(beans, items), is(4));
        assertThat(countItem(orange, items), is(3));
        assertThat(countItem(apple, items), is(5));
    }
}
