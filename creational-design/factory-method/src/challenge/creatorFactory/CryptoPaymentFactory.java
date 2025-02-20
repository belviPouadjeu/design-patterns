package challenge.creatorFactory;

import challenge.Payment;
import challenge.PaymentFactory;
import challenge.concretePaymentMethods.CryptoPayment;

public class CryptoPaymentFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new CryptoPayment();
    }
}
