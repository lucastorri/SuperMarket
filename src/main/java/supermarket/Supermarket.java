package supermarket;

import supermarket.payment.CheckoutCounter;

import java.util.HashSet;
import java.util.Set;

public class Supermarket {

    public ShoppingCart getShoppingCart() {
        return new ShoppingCart();
    }

    public Set<SupermarketItem> getMerchandise() {
        // goes to database
        return new HashSet<SupermarketItem>();
    }

    public CheckoutCounter getCheckoutCounter() {
        return new CheckoutCounter();
    }
}
