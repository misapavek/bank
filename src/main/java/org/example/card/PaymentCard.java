package org.example.card;

import org.example.accounts.BaseBankAccount;
import org.example.people.BankAccountOwner;

public class PaymentCard {

    private final String cardNumber;

    private final String cvv;

    private final String expireMonth;

    private final String expireYear;

    private final BaseBankAccount bankAccount;

    public PaymentCard(String cardNumber, String cvv, String expireMonth, String expireYear, BaseBankAccount bankAccount) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.bankAccount = bankAccount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public BaseBankAccount getBankAccount() {
        return bankAccount;
    }
}