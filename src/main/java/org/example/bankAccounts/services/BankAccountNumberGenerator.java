package org.example.bankAccounts.services;

public class BankAccountNumberGenerator {

    public String generateNumber() {
        int number = (int)(Math.random()*10000);
        return Integer.toString(number);
    }
}
