package supermarket;

import org.junit.Before;
import org.junit.Test;
import supermarket.ShoppingCart;
import supermarket.SupermarketItem;
import supermarket.payment.Price;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static supermarket.util.ItemCounter.countItem;

public class ShoppingCartTest {

    private ShoppingCart cart;
    private SupermarketItem riceItem;
    private SupermarketItem beansItem;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
        riceItem = new SupermarketItem("Rice", new Price(1,57));
        beansItem = new SupermarketItem("beans", new Price(1,32));
    }

    @Test
    public void doesNotContainAnyItemAtFirst() {
        assertFalse(cart.contains(riceItem));
        assertThat(cart.getQuantity(riceItem), is(0));
    }

    @Test
    public void canReceiveItems() {
        cart.add(riceItem);
        assertTrue(cart.contains(riceItem));
    }

    @Test
    public void knowsHowManyItemsOftheSameTypeItHas() {
        cart.add(riceItem).add(riceItem);
        assertThat(cart.getQuantity(riceItem), is(2));
    }

    @Test
    public void returnsAListOfAddedItems() {
        cart.add(riceItem).add(riceItem).add(beansItem);
        List<SupermarketItem> items = cart.getItems();

        assertThat(items.size(), is(3));
        assertThat(countItem(riceItem, items), is(2));
        assertThat(countItem(beansItem, items), is(1));
    }

    @Test
    public void isEqualIfHaveTheSameItemsAndWithSameQuantity() {
        cart.add(riceItem).add(riceItem).add(beansItem);
        assertTrue(cart.equals(new ShoppingCart().add(beansItem).add(riceItem).add(riceItem)));
    }

    @Test
    public void isNotEqualIfHaveDifferentItemsOrDifferentQuantity() {
        cart.add(riceItem).add(riceItem).add(beansItem);
        assertFalse(cart.equals(new ShoppingCart().add(riceItem).add(riceItem)));
        assertFalse(cart.equals(new ShoppingCart().add(riceItem).add(beansItem)));
    }
}
