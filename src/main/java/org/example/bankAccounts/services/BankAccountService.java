package org.example.bankAccounts.services;

import org.example.bankAccounts.BaseBankAccount;

import java.util.Scanner;

public class BankAccountService {

    public void addBalance(BaseBankAccount bankAccount, double amount) {

        if (amount > 10000){
            Scanner input = new Scanner(System.in);
            System.out.println("Amount to be deposited is greater than 10000" +
                    "\ncontinue? (y/n)");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("y"))
                bankAccount.setbalance(bankAccount.getBalance() + amount);
            else
                amount=0;

        }else
            bankAccount.setbalance(bankAccount.getBalance()+amount);
    }
    public void subtractBalance(BaseBankAccount bankAccount, double amount) {
        if(amount > 0){
            bankAccount.setbalance(bankAccount.getBalance() -  amount);
        }
    }
}
