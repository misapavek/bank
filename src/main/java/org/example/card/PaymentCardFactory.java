
package org.example.card;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.accounts.BaseBankAccount;

import java.util.UUID;

@Singleton
public class PaymentCardFactory {

    @Inject
    private PaymentCardNumberGenerator paymentCardNumberGenerator;

    @Inject
    private PaymentCardCvvGenerator paymentCardCvvGenerator;

    @Inject
    private PaymentCardExpireCalculator paymentCardExpirationCalculator;

    public PaymentCard create(BaseBankAccount  baseBankAccount) {

        String cardNUmber = this.paymentCardNumberGenerator.generateCardNumber();
        String cvv = this.paymentCardCvvGenerator.generate();
        String expireMonth = this.paymentCardExpirationCalculator.calculateMonthExpire();
        String expireYear = this.paymentCardExpirationCalculator.calculateYearExpire();

        return new PaymentCard( cardNUmber, cvv, expireMonth, expireYear, baseBankAccount);
    }
}
