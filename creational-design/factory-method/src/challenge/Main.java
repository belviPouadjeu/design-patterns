package challenge;

import challenge.creatorFactory.CreditCardPaymentFactory;
import challenge.creatorFactory.CryptoPaymentFactory;
import challenge.creatorFactory.MobileMoneyPaymentFactory;
import challenge.creatorFactory.PaypalPaymentFactory;

public class Main {
    public static void main(String[] args) {
        processPayment(new CreditCardPaymentFactory(), 10000);
        processPayment(new PaypalPaymentFactory(), 15000);
        processPayment(new CryptoPaymentFactory(), 20000);
        processPayment(new MobileMoneyPaymentFactory("MTN MoMo"), 5000);
        processPayment(new MobileMoneyPaymentFactory("Orange Money"), 7000);
    }

    public static void processPayment(PaymentFactory factory, double amount) {
        Payment payment = factory.createPayment();
        payment.processPayment(amount);
    }


}
