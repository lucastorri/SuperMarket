package supermarket.util;

import org.junit.Test;
import supermarket.SupermarketItem;
import supermarket.payment.Price;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static supermarket.util.ItemCounter.countItem;

public class ItemCounterTest {

    @Test
    public void countItems() {
        SupermarketItem rice = new SupermarketItem("Rice", new Price(1, 49));
        SupermarketItem beans = new SupermarketItem("Beans", new Price(2, 13));
        SupermarketItem orange = new SupermarketItem("Orange", new Price(0, 39));
        List<SupermarketItem> merchandise = Arrays.asList(rice, beans, orange, rice, rice, orange);

        assertThat(countItem(rice, merchandise), is(3));
        assertThat(countItem(orange, merchandise), is(2));
        assertThat(countItem(beans, merchandise), is(1));
    }
}
