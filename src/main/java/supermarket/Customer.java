package supermarket;

import supermarket.payment.CreditCard;
import supermarket.payment.SupermarketBill;

public class Customer {

    private Supermarket supermarket;
    private ShoppingCart cart;
    private CreditCard creditCard;

    public Customer(Supermarket supermarket, ShoppingCart cart, CreditCard creditCard) {
        this.supermarket = supermarket;
        this.cart = cart;
        this.creditCard = creditCard;
    }

    public Customer(Supermarket supermarket, CreditCard creditCard) {
        this(supermarket, supermarket.getShoppingCart(), creditCard);
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void pay(SupermarketBill bill) {
        bill.payWithCreditCard(creditCard);
    }

    public void shop(ShoppingList shoppingList) {
        for(SupermarketItem item : supermarket.getMerchandise()) {
            int itemCount = shoppingList.find(item.getName());
            for (int i = 0; i < itemCount; i++) {
                cart.add(item);
            }
        }
    }

    public SupermarketBill checkout() {
        return supermarket.getCheckoutCounter().checkout(cart);
    }
}
