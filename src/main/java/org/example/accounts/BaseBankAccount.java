package org.example.accounts;

import org.example.accounts.services.BankAccountNumberGenerator;
import org.example.people.BankAccountOwner;

public class BaseBankAccount {

    private String uuid;

    private String accountNumber;

    private BankAccountOwner owner;
    double balance;

    private BankAccountNumberGenerator bankAccountNumberGenerator;

    public BaseBankAccount(String uuid, String accountNumber, BankAccountOwner owner, double balance) {
        this.uuid = uuid;
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public String getUuid() {
        return uuid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public BankAccountOwner getOwner() {
        return owner;
    }
    public double getBalance() {
        return balance;
    }
    public void setbalance(double balance) {
        this.balance = balance;
    }



}
