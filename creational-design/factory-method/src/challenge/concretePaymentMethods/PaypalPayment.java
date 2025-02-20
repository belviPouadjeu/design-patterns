package challenge.concretePaymentMethods;

import challenge.Payment;

public class PaypalPayment implements Payment { // MTN MoMo or Orange Money


    @Override
    public void processPayment(double amount) {
        System.out.println("Processing paypal payment via "+ amount + " xaf");
    }
}
