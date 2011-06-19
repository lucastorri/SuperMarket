package supermarket.payment;

import org.apache.commons.lang.builder.EqualsBuilder;

public class Price {

    private static final int HUNDRED_CENTS = 100;
    private final int cents;
    private final int amount;

    public Price(int amount, int cents) {
        this.amount = amount;
        this.cents = cents;
    }

    public Price add(Price otherPrice) {
        int newCents = cents + otherPrice.cents;
        int carryOver;
        if (newCents > HUNDRED_CENTS) {
            carryOver = newCents / HUNDRED_CENTS;
            newCents %= HUNDRED_CENTS;
        } else {
            carryOver = 0;
        }
        return new Price(amount + otherPrice.amount + carryOver, newCents);
    }

    public int getCents() {
        return cents;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Price && EqualsBuilder.reflectionEquals(this, o));
    }

    @Override
    public String toString() {
        return "" + amount + "," + cents;
    }
}
