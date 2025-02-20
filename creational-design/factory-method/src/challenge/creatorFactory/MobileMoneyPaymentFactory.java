package challenge.creatorFactory;

import challenge.Payment;
import challenge.PaymentFactory;
import challenge.concretePaymentMethods.MobileMoneypayment;

public class MobileMoneyPaymentFactory extends PaymentFactory {
    private String provider;

    public MobileMoneyPaymentFactory(String provider) {
        this.provider = provider;
    }

    @Override
    public Payment createPayment() {
        return new MobileMoneypayment(provider);
    }
}
