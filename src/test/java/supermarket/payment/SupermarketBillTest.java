package supermarket.payment;

import org.junit.Before;
import org.junit.Test;
import supermarket.SupermarketItem;

import java.util.Arrays;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupermarketBillTest {

    private SupermarketBill bill;
    private SupermarketItem riceItem;
    private SupermarketItem beansItem;
    private CreditCard creditCardMock;

    @Before
    public void setUp() {
        riceItem = new SupermarketItem("Rice", new Price(2,97));
        beansItem = new SupermarketItem("Beans", new Price(1,75));
        bill = new SupermarketBill(Arrays.asList(riceItem, riceItem, beansItem), new Price(10,0));
        creditCardMock = mock(CreditCard.class);
    }

    @Test
    public void canBePayedWithCreditCard() {
        when(creditCardMock.authorize(bill)).thenReturn(true);
        bill.payWithCreditCard(creditCardMock);
        assertTrue(bill.isPayed());
    }

    @Test
    public void isNotPayedIfThePaymentWasNotAuthorized() {
        when(creditCardMock.authorize(bill)).thenReturn(false);
        bill.payWithCreditCard(creditCardMock);
        assertFalse(bill.isPayed());
    }

    @Test
    public void isOnlyEqualIfHasTheSameItemsWithSameQuantityAndPrice() {
        assertTrue(bill.equals(new SupermarketBill(Arrays.asList(riceItem, beansItem, riceItem), new Price(10,00))));

        assertFalse(bill.equals(new SupermarketBill(Arrays.asList(riceItem, beansItem, riceItem), new Price(7,00))));
        assertFalse(bill.equals(new SupermarketBill(Arrays.asList(beansItem, riceItem), new Price(7,00))));
    }
}
