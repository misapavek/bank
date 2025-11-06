package org.example.accounts.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.checkerframework.checker.units.qual.C;
import org.example.accounts.BaseBankAccount;
import org.example.accounts.factories.BankAccountFactory;
import org.example.people.BankAccountOwner;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class BankAccountService {

    private Map<String, BaseBankAccount> bankAccounts;

    @Inject
    private BankAccountFactory bankAccountFactory;

    public BankAccountService() {
        this.bankAccounts = new HashMap<>();
    }

    public BaseBankAccount createBankAccount(String uuid, BankAccountOwner customer) {
        BaseBankAccount bankAccount = this.bankAccountFactory.createBankAccount(uuid, customer);
        bankAccounts.put(bankAccount.getUuid(), bankAccount);

        return bankAccount;
    }

    public BaseBankAccount createStudentBankAccount(String uuid, BankAccountOwner customer, String schoolName) {
        BaseBankAccount bankAccount = this.bankAccountFactory.createStudentAccount(uuid, customer, schoolName);
        bankAccounts.put(bankAccount.getUuid(), bankAccount);

        return bankAccount;
    }

    public BaseBankAccount createSaveBankAccount(String uuid, BankAccountOwner customer, float interestRate) {
        BaseBankAccount bankAccount = this.bankAccountFactory.createSaveAccount(uuid, customer, interestRate);
        bankAccounts.put(bankAccount.getUuid(), bankAccount);

        return bankAccount;
    }

    public Collection<BaseBankAccount> getAllAccounts() {
        return bankAccounts.values();
    }
    public BaseBankAccount getAccountByUuid(String uuid) {
        return bankAccounts.get(uuid);
    }


}