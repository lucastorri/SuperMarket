package supermarket.taxes;

import supermarket.payment.Price;

public interface Tax {

    public Price calculate(Price originalPrice);
}
