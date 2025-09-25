package org.example.bankAccounts;

import org.example.people.BankAccountOwner;

public class BaseBankAccount {

    private String uuid;

    private String accountNumber;

    private BankAccountOwner owner;
    double balance;

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

    public void AddBalance(int amount){
        this.balance += amount;
    }

    public void SubtractBalance(int amount){
        double subtraction = this.balance - amount;
        if (subtraction > 0){
            this.balance -= amount;
        }
    }


}
