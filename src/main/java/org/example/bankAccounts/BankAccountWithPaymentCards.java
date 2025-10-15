package org.example.bankAccounts;

import org.example.people.BankAccountOwner;

public class BankAccountWithPaymentCards extends BaseBankAccount {
    public BankAccountWithPaymentCards(String uuid, String accountNumber, BankAccountOwner owner, double balance) {
        super(uuid, accountNumber, owner, balance);
    }
}