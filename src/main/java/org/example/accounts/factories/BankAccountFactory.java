
package org.example.accounts.factories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.accounts.BankAccount;
import org.example.accounts.SavingBankAccount;
import org.example.accounts.StudentBankAccount;
import org.example.accounts.services.BankAccountNumberGenerator;
import org.example.people.BankAccountOwner;


@Singleton
public class BankAccountFactory {

    @Inject
    private BankAccountNumberGenerator bankAccountNumberGenerator;

    public BankAccount createBankAccount(String uuid, BankAccountOwner customer) {
        return new BankAccount(
                uuid,
                bankAccountNumberGenerator.generateNumber(),
                customer,
                0
        );
    }

    public SavingBankAccount createSaveAccount(String uuid, BankAccountOwner customer, float interestRate) {
        return new SavingBankAccount(
                uuid,
                bankAccountNumberGenerator.generateNumber(),
                customer,
                0,
                interestRate
        );
    }

    public StudentBankAccount createStudentAccount(String uuid, BankAccountOwner customer, String schoolName) {
        return new StudentBankAccount(
                uuid,
                bankAccountNumberGenerator.generateNumber(),
                customer,
                0,
                schoolName

        );
    }

}
