package org.example.bankAccounts.factories;

import org.example.bankAccounts.BankAccount;
import org.example.bankAccounts.SavingBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.bankAccounts.services.BankAccountNumberGenerator;
import org.example.people.BankAccountOwner;

public class BankAccountFactory {
    private BankAccountNumberGenerator generator = new  BankAccountNumberGenerator();

    public BankAccount createBankAccount(String uuid, BankAccountOwner bankAccountOwner, double balance){
        return new BankAccount(uuid,generator.generateNumber() , bankAccountOwner, balance);
    }

    public SavingBankAccount createSavingBankAccount(String uuid, BankAccountOwner bankAccountOwner, double balance){
        return new SavingBankAccount(uuid,generator.generateNumber() , bankAccountOwner, balance);
    }

    public StudentBankAccount createStudentBankAccount(String uuid, BankAccountOwner bankAccountOwner,double balance ,String school){
        return new StudentBankAccount(uuid, generator.generateNumber(), bankAccountOwner, 0, school);
    }
}
