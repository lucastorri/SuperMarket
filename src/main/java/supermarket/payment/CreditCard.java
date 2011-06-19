package supermarket.payment;

public class CreditCard {

    public boolean authorize(SupermarketBill bill) {
        return someSuperWeirdValidationTransaction();
    }

    private boolean someSuperWeirdValidationTransaction() {
        return true;
    }

}
