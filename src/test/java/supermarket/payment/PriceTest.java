package supermarket.payment;

import org.junit.Test;
import supermarket.payment.Price;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PriceTest {

    @Test
    public void canBeAddedToAnotherPrice() {
        Price beansPrice = new Price(1,40);
        Price ricePrice = new Price(0,89);

        assertThat(ricePrice.add(beansPrice), is(equalTo(new Price(2,29))));
    }
}
