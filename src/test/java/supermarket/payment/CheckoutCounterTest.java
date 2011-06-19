package supermarket.payment;

import org.junit.Before;
import org.junit.Test;
import supermarket.ShoppingCart;
import supermarket.SupermarketItem;
import supermarket.taxes.Tax;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckoutCounterTest {

    private CheckoutCounter counter;
    private ShoppingCart cart;
    private SupermarketItem riceItem;
    private SupermarketItem beansItem;

    @Before
    public void setUp() {
        counter = new CheckoutCounter();

        cart = new ShoppingCart();
        PlusOneTaxStub tax = new PlusOneTaxStub();
        riceItem = new SupermarketItem("Rice", new Price(2,47), tax);
        beansItem = new SupermarketItem("Beans", new Price(0,99), tax);
        cart.add(riceItem).add(riceItem).add(beansItem).add(beansItem);
    }

    @Test
    public void knowsThatAEmptyCartHasPriceZero() {
        SupermarketBill bill = counter.checkout(new ShoppingCart());
        assertThat(bill.getTotalPrice(), is(new Price(0,0)));
    }

    @Test
    public void sumsTheTotalPriceOfTheItemsInACart() {
        SupermarketBill bill = counter.checkout(cart);
        assertThat(bill.getTotalPrice(), is(new Price(10,92)));
    }

    private class PlusOneTaxStub implements Tax {

        public Price calculate(Price originalPrice) {
            return new Price(1,0);
        }
    }

}
