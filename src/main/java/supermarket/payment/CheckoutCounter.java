package supermarket.payment;

import supermarket.ShoppingCart;
import supermarket.SupermarketItem;

import java.util.List;

public class CheckoutCounter {

    public SupermarketBill checkout(ShoppingCart cart) {
        List<SupermarketItem> items = cart.getItems();
        Price totalPrice = new Price(0,0);
        for (SupermarketItem i : items) {
            totalPrice = totalPrice.add(i.getPriceWithTaxes());
        }
        return new SupermarketBill(items, totalPrice);
    }
}
