package supermarket;

import org.junit.Test;
import supermarket.payment.Price;
import supermarket.taxes.Tax;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SupermarketItemTest {

    @Test
    public void hasNoTaxesOriginally() {
        SupermarketItem item = new SupermarketItem("Beans", new Price(3,57));
        assertThat(item.getPriceWithTaxes(), is(new Price(3,57)));
    }

    @Test
    public void knowsItPriceWithTaxes() {
        SupermarketItem item = new SupermarketItem("Rice", new Price(1,50), new OneMoneyTaxStub(), new TwoMoneysTaxStub());
        assertThat(item.getPriceWithTaxes(), is(new Price(4,50)));
    }

    private class OneMoneyTaxStub implements Tax {

        public Price calculate(Price originalPrice) {
            return new Price(1,00);
        }
    }

    private class TwoMoneysTaxStub implements Tax {

        public Price calculate(Price originalPrice) {
            return new Price(2,00);
        }
    }
}
