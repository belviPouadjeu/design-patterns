package challenge.concretePaymentMethods;

import challenge.Payment;

public class CryptoPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing crypto payment of " + amount + " xaf in Bitcoin/ethereum");
    }
}
