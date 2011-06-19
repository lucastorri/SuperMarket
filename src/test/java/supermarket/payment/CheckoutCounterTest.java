package supermarket.payment;

import org.junit.Before;
import org.junit.Test;
import supermarket.Customer;
import supermarket.ShoppingCart;
import supermarket.Supermarket;
import supermarket.SupermarketItem;
import supermarket.payment.CheckoutCounter;
import supermarket.payment.CreditCard;
import supermarket.payment.Price;
import supermarket.payment.SupermarketBill;
import supermarket.taxes.Tax;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CheckoutCounterTest {

    private CheckoutCounter counter;
    private ShoppingCart cart;
    private SupermarketItem riceItem;
    private SupermarketItem beansItem;
    private Price expectedTotalPrice;

    @Before
    public void setUp() {
        counter = new CheckoutCounter();

        cart = new ShoppingCart();
        PlusOneTaxStub tax = new PlusOneTaxStub();
        riceItem = new SupermarketItem("Rice", new Price(2,47), tax);
        beansItem = new SupermarketItem("Beans", new Price(0,99), tax);
        cart.add(riceItem).add(riceItem).add(beansItem).add(beansItem);
        expectedTotalPrice = new Price(10,92);
    }

    @Test
    public void knowsThatAEmptyCartHasPriceZero() {
        SupermarketBill bill = counter.checkout(new ShoppingCart());
        assertThat(bill.getTotalPrice(), is(new Price(0,0)));
    }

    @Test
    public void sumsTheTotalPriceOfTheItemsInACart() {
        SupermarketBill bill = counter.checkout(cart);
        assertThat(bill.getTotalPrice(), is(expectedTotalPrice));
    }

    private class PlusOneTaxStub implements Tax {

        public Price calculate(Price originalPrice) {
            return new Price(1,0);
        }
    }

}
