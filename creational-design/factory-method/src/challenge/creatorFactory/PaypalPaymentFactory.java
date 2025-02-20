package challenge.creatorFactory;

import challenge.Payment;
import challenge.PaymentFactory;
import challenge.concretePaymentMethods.PaypalPayment;

public class PaypalPaymentFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new PaypalPayment();
    }
}
