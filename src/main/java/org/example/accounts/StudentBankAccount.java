package org.example.accounts;

import org.example.people.BankAccountOwner;

public class StudentBankAccount extends BankAccountWithPaymentCards {

    private String school;

    public StudentBankAccount(String uuid, String accountNumber, BankAccountOwner owner, double balance, String school) {
        super(uuid, accountNumber, owner, balance);
        this.school = school;
    }

    public String getSchool() {
        return school;
    }
}
