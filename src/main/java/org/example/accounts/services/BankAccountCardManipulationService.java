package org.example.accounts.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.card.PaymentCard;
import org.example.card.PaymentCardService;

@Singleton
public class BankAccountCardManipulationService {

    @Inject
    private PaymentCardService paymentCardService;

    @Inject
    private BankAccountDepositManipulationService  bankAccountDepositManipulationService;

    public void atmDeposit(String cardNumber, double amount) {
        PaymentCard card = paymentCardService.getPaymentCard(cardNumber);

        if (card == null) {
            throw new IllegalArgumentException("Card not found.");
        }


        if (card.getBankAccount().getBalance() < amount) {
            throw new IllegalArgumentException("Not enough balance.");
        }

        this.bankAccountDepositManipulationService.withdraw(card.getBankAccount(), amount);
    }

}