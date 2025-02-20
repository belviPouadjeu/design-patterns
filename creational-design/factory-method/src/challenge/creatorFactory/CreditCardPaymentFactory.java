package challenge.creatorFactory;

import challenge.Payment;
import challenge.PaymentFactory;
import challenge.concretePaymentMethods.CreditCardPayment;

public class CreditCardPaymentFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}
