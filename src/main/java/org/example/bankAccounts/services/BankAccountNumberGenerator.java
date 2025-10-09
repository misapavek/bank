package org.example.bankAccounts;

import java.lang.classfile.constantpool.IntegerEntry;

public class BankAccountNumberGenerator {

    public String generateNumber() {
        int number = (int)(Math.random()*100);
        return Integer.toString(number);
    }
}
