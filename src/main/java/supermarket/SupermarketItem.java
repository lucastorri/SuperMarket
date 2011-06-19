package supermarket;

import supermarket.payment.Price;

public class SupermarketItem {

    private final String name;
    private final Price price;

    public SupermarketItem(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

}
