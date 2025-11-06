package org.example.accounts.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.accounts.BaseBankAccount;
import org.example.accounts.SavingBankAccount;
import org.example.logger.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class InterestProcessingFacade {

    @Inject
    private BankAccountService bankAccountService;

    @Inject
    private InterestCalculationService interestCalculationService;

    @Inject
    private Logger logger;

    private Map<String, LocalDateTime> nextInterestDates = new HashMap<>();

    public void processAllSavingAccounts() {
        logger.log("Starting interest processing at " + LocalDateTime.now());

        int processedCount = 0;
        double totalInterest = 0;
        int totalAccounts = 0;
        int savingAccountsFound = 0;

        for (BaseBankAccount account : bankAccountService.getAllAccounts()) {
            totalAccounts++;
            logger.log("DEBUG: Found account " + account.getUuid() + " of type " + account.getClass().getSimpleName());

            if (account instanceof SavingBankAccount) {
                savingAccountsFound++;
                SavingBankAccount savingAccount = (SavingBankAccount) account;
                logger.log("DEBUG: Processing saving account " + savingAccount.getUuid() +
                        ", balance: " + savingAccount.getBalance() +
                        ", rate: " + savingAccount.getInterestRate());

                if (shouldProcessInterest(savingAccount)) {
                    double interest = interestCalculationService.calculateAndApplyInterest(savingAccount);
                    setNextInterestDate(savingAccount);

                    processedCount++;
                    totalInterest += interest;

                    logger.log("Processed interest for account " + savingAccount.getUuid() +
                            ", interest: " + interest);
                } else {
                    logger.log("DEBUG: Skipped account " + savingAccount.getUuid() + " (not time for interest yet)");
                }
            }
        }

        logger.log("Interest processing completed. Total accounts: " + totalAccounts +
                ", Saving accounts found: " + savingAccountsFound +
                ", Processed accounts: " + processedCount +
                ", total interest: " + totalInterest);
    }

    private boolean shouldProcessInterest(SavingBankAccount account) {
        String uuid = account.getUuid();
        LocalDateTime now = LocalDateTime.now();

        if (!nextInterestDates.containsKey(uuid)) {
            return true;
        }

        LocalDateTime nextDate = nextInterestDates.get(uuid);
        return now.isAfter(nextDate) || now.isEqual(nextDate);
    }

    private void setNextInterestDate(SavingBankAccount account) {
        LocalDateTime nextDate = LocalDateTime.now().plusMonths(1);
        nextInterestDates.put(account.getUuid(), nextDate);
        logger.log("Next interest date for account " + account.getUuid() + ": " + nextDate);
    }
}