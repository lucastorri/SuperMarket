package supermarket;

import supermarket.payment.Price;
import supermarket.taxes.Tax;

public class SupermarketItem {

    private final String name;
    private final Price price;
    private Tax[] taxes;

    public SupermarketItem(String name, Price price) {
        this(name, price, new Tax[] {});
    }

    public SupermarketItem(String name, Price price, Tax... taxes) {
        this.name = name;
        this.price = price;
        this.taxes = taxes;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public Price getPriceWithTaxes() {
        Price priceWithTaxes = price;
        for (Tax tax : taxes) {
            priceWithTaxes = priceWithTaxes.add(tax.calculate(price));
        }
        return priceWithTaxes;
    }
}
