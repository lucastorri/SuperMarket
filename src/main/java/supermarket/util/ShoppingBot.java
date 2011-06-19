package supermarket.util;

import supermarket.Customer;
import supermarket.ShoppingList;
import supermarket.payment.SupermarketBill;

public class ShoppingBot {

    private Customer customer;

    public ShoppingBot(Customer customer) {
        this.customer = customer;
    }

    public SupermarketBill shopAll(ShoppingList... lists) {
        for (ShoppingList list : lists) {
            customer.shop(list);
        }
        SupermarketBill checkoutBill = customer.checkout();
        customer.pay(checkoutBill);
        return checkoutBill;
    }
}
