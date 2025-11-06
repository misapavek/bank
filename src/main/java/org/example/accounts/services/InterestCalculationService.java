package org.example.accounts.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.accounts.SavingBankAccount;
import org.example.logger.Logger;

import java.time.LocalDateTime;

@Singleton
public class InterestCalculationService {

    @Inject
    private Logger logger;

    @Inject
    private BankAccountDepositManipulationService depositService;

    public double calculateInterest(SavingBankAccount account) {
        double balance = account.getBalance();
        float interestRate = account.getInterestRate();

        double interest = balance * interestRate;

        logger.log("Calculated interest for account " + account.getUuid() +
                ": " + interest + " (rate: " + interestRate + ")");

        return interest;
    }

    public void applyInterest(SavingBankAccount account, double interest) {
        if (interest > 0) {
            depositService.deposit(account, interest);
            logger.log("Applied interest to account " + account.getUuid() + ": " + interest);
        }
    }

    public double calculateAndApplyInterest(SavingBankAccount account) {
        double interest = calculateInterest(account);
        applyInterest(account, interest);
        return interest;
    }
}