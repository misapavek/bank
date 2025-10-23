package org.example.bankAccounts.services;

import org.example.bankAccounts.BaseBankAccount;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

public class BankAccountService {

    private static final Logger logger = Logger.getLogger(BankAccountService.class.getName());

    public void addBalance(BaseBankAccount bankAccount, double amount) {

        String timestamp = LocalDate.now().toString();
        logger.info(String.format("[%s] Deposit attempt - Amount: %.2f, Account: %s", timestamp, amount, bankAccount.getAccountNumber()));
        double previousBalance = bankAccount.getBalance();

        if (amount < 0){
            logger.warning("[%s] Deposit failed - Invalid amount");
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (amount > 10000){
            Scanner input = new Scanner(System.in);
            System.out.println("Amount to be deposited is greater than 10000" +
                    "\ncontinue? (y/n)");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("y")){
                bankAccount.setbalance(bankAccount.getBalance() + amount);
                logger.info(String.format("[%s] Deposit successful - Amount: %.2f, Account: %s, Balance: %.2f -> %.2f", timestamp, amount, bankAccount.getAccountNumber(), previousBalance, bankAccount.getBalance()));
            }
            else
                amount=0;

        }else{
            bankAccount.setbalance(bankAccount.getBalance()+amount);
            logger.info(String.format("[%s] Deposit successful - Amount: %.2f, Account: %s, Balance: %.2f -> %.2f", timestamp, amount, bankAccount.getAccountNumber(), previousBalance, bankAccount.getBalance()));
        }
    }
    public void subtractBalance(BaseBankAccount bankAccount, double amount) {
        if(amount > 0){
            bankAccount.setbalance(bankAccount.getBalance() -  amount);
        }
    }
}
