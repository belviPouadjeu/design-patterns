package challenge.concretePaymentMethods;

import challenge.Payment;

public class MobileMoneypayment implements Payment { // Mobile Money Payment (MTN MoMo & Orange Money
    private String provider;

    public MobileMoneypayment(String provider) {
        this.provider = provider;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Mobile money payment via " + provider + " of " + amount + " xaf");
    }
}
