package org.example.card;

import org.example.people.BankAccountOwner;

public class PaymentCardFactory {

    PaymentCardNumberGenerator paymentCardNumberGenerator = new PaymentCardNumberGenerator();
    PaymentCardCvvGenerator paymentCardCvvGenerator = new PaymentCardCvvGenerator();
    PaymentCardExpireCalculator paymentCardExpireCalculator = new PaymentCardExpireCalculator();

    public PaymentCard create(BankAccountOwner owner) {
        String cardNumber = paymentCardNumberGenerator.generateCardNumber();
        String cvv = paymentCardCvvGenerator.generate();
        String expireMonth = paymentCardExpireCalculator.calculateMonthExpire();
        String expireYear = paymentCardExpireCalculator.calculateYearExpire();

        return new PaymentCard(cardNumber, cvv, expireMonth, expireYear, owner);
    }
}