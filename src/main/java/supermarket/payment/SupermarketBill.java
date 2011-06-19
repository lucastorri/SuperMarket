package supermarket.payment;

import supermarket.SupermarketItem;

import java.util.List;

import static supermarket.util.ItemCounter.countItem;

public class SupermarketBill {

    private List<SupermarketItem> items;
    private Price totalPrice;
    private boolean payed;

    public SupermarketBill(List<SupermarketItem> items, Price totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.payed = false;
    }

    public List<SupermarketItem> getItems() {
        return items;
    }

    public Price getTotalPrice() {
        return totalPrice;
    }

    public boolean isPayed() {
        return payed;
    }

    public void payWithCreditCard(CreditCard creditCard) {
        payed = creditCard.authorize(this);
    }

    @Override
    public boolean equals(Object o) {
        SupermarketBill other = (SupermarketBill) o;
        return this == o || (
            o instanceof SupermarketBill &&
            other.totalPrice.equals(totalPrice) && hasSameItems(other)
        );
    }

    private boolean hasSameItems(SupermarketBill other) {
        boolean has = items.size() == other.items.size();
        for(int i = 0; has && i < items.size(); i++) {
            SupermarketItem item = items.get(i);
            has = countItem(item, items) == countItem(item, other.items);
        }
        return has;
    }

}
