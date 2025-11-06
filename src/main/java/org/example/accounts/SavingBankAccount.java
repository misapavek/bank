package org.example.accounts;

import org.example.people.BankAccountOwner;

public class SavingBankAccount extends BaseBankAccount {

    private float interestRate;

    public SavingBankAccount(String uuid, String accountNumber, BankAccountOwner owner, double balance, float  interestRate) {
        super(uuid, accountNumber, owner, balance);
        this.interestRate = interestRate;
    }

    public float getInterestRate() {
        return interestRate;
    }
}
