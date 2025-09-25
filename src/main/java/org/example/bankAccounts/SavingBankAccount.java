package org.example.bankAccounts;

import org.example.people.BankAccountOwner;

public class SavingBankAccount extends BaseBankAccount {

    private float interestRate;

    public SavingBankAccount(String uuid, String accountNumber, BankAccountOwner owner, double balance) {
        super(uuid, accountNumber, owner, balance);
        interestRate = (float) 0.1;
    }

    public float getInterestRate() {
        return interestRate;
    }
}
