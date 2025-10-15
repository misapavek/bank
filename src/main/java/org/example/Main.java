package org.example;

import org.example.bankAccounts.BaseBankAccount;
import org.example.bankAccounts.SavingBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.bankAccounts.factories.BankAccountFactory;
import org.example.bankAccounts.services.BankAccountNumberGenerator;
import org.example.bankAccounts.serialization.BankAccountOwnerJsonSerializationService;
import org.example.bankAccounts.serialization.Serialization;
import org.example.bankAccounts.services.BankAccountService;
import org.example.people.BankAccountOwner;
import org.example.people.factories.OwnerFactory;

public class Main {

    public static void main(String[] args) {


            BankAccountFactory bankAccountFactory = new BankAccountFactory();
            OwnerFactory bankAccountOwnerFactory = new OwnerFactory();
            BankAccountService bankAccountBalanceService = new BankAccountService();

            BankAccountOwner owner = bankAccountOwnerFactory.createOwner("O-123", "jon", "jon");
            System.out.println(owner.getUuid() + ": " + owner.getFullName());


            Serialization bankAccountOwnerJsonSerializationService = new BankAccountOwnerJsonSerializationService();
            bankAccountOwnerJsonSerializationService.serialization(owner);


            BaseBankAccount account1 = bankAccountFactory.createBankAccount("base-bank-account-123", owner, 500);
            BaseBankAccount account2 = bankAccountFactory.createSavingBankAccount("saving-bank-account-123", owner, 500);
            BaseBankAccount account3 = bankAccountFactory.createStudentBankAccount("student-bank-account-123", owner, 500, "DELTA");

            System.out.println(account1.getUuid() + " (" + account1.getAccountNumber() + ") : " + account1.getBalance());
            System.out.println(account2.getUuid() + " (" + account2.getAccountNumber() + ") : " + ": " + account1.getBalance());
            System.out.println(account3.getUuid() + " (" + account3.getAccountNumber() + ") : " + ": " + account1.getBalance());

            // account1.addBalance(300);
            bankAccountBalanceService.addBalance(account1, 300);
            System.out.println(account1.getUuid() + ": " + account1.getBalance());

            // account1.addBalance(200);
            bankAccountBalanceService.addBalance(account1, 200);
            System.out.println(account1.getUuid() + ": " + account1.getBalance());

            // account1.addBalance(100);
            bankAccountBalanceService.addBalance(account1, 100);
            System.out.println(account1.getUuid() + ": " + account1.getBalance());

            //account1.subtractBalance(500);
            bankAccountBalanceService.subtractBalance(account1, 500);
            System.out.println(account1.getUuid() + ": " + account1.getBalance());

            // account1.subtractBalance(5000);
            // bankAccountBalanceService.withdraw(account1, 5000);
            // System.out.println(account1.getUuid() + ": " + account1.getBalance());

    }

}