package org.example;

import org.example.bankAccounts.BankAccount;
import org.example.bankAccounts.BaseBankAccount;
import org.example.bankAccounts.SavingBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.people.BankAccountOwner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BankAccountOwner owner = new BankAccountOwner( "0-123", "jmeno", "prijmeni");
        BaseBankAccount saccount = new StudentBankAccount("123", "1", owner, -100, "skola");

        if (saccount instanceof StudentBankAccount) {
            System.out.println("Student: " + saccount.getOwner().getFirstName() + " " + saccount.getOwner().getLastName() + ", School: " + ((StudentBankAccount)saccount).getSchool());
            System.out.println("Account: " + saccount.getAccountNumber() + ", uuid: " + saccount.getUuid() + ", Balance: " + saccount.getBalance());
        }


    }
}