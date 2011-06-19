package supermarket;

import supermarket.payment.CheckoutCounter;

import java.util.LinkedList;
import java.util.List;

public class Supermarket {

    public ShoppingCart getShoppingCart() {
        return new ShoppingCart();
    }

    public List<SupermarketItem> getMerchandise() {
        // goes to database
        return new LinkedList<SupermarketItem>();
    }

    public CheckoutCounter getCheckoutCounter() {
        return new CheckoutCounter();
    }
}
