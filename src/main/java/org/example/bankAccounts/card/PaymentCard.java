package org.example.bankAccounts.card;

import org.example.people.BankAccountOwner;

public class PaymentCard {

    private final String cardNumber;

    private final String cvv;

    private final String expireMonth;

    private final String expireYear;

    private final BankAccountOwner owner;

    public PaymentCard(String cardNumber, String cvv, String expireMonth, String expireYear, BankAccountOwner owner) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.owner = owner;
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

    public BankAccountOwner getOwner() {
        return owner;
    }
}