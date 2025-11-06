package org.example.accounts;

import org.example.people.BankAccountOwner;

public class BankAccount extends BankAccountWithPaymentCards {
    public BankAccount(String uuid, String accountNumber, BankAccountOwner owner, double balance) {
        super(uuid, accountNumber, owner, balance);
    }
}
