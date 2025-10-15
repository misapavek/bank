package org.example.bankAccounts.services;

import org.example.bankAccounts.BaseBankAccount;

public class BankAccountService {

    public void addBalance(BaseBankAccount bankAccount, double amount) {
        if(amount > 0 && amount < 10000){
            bankAccount.setbalance(bankAccount.getBalance() +  amount);
        }
    }
    public void subtractBalance(BaseBankAccount bankAccount, double amount) {
        if(amount > 0){
            bankAccount.setbalance(bankAccount.getBalance() -  amount);
        }
    }
}
