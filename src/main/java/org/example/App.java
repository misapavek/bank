package org.example;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.accounts.BankAccount;
import org.example.accounts.BaseBankAccount;
import org.example.accounts.SavingBankAccount;
import org.example.accounts.services.BankAccountDepositManipulationService;
import org.example.accounts.services.BankAccountCardManipulationService;
import org.example.accounts.services.BankAccountService;
import org.example.accounts.services.InterestCronService;
import org.example.card.PaymentCard;
import org.example.card.PaymentCardService;
import org.example.logger.Logger;
import org.example.people.BankAccountOwner;
import org.example.people.factories.OwnerFactory;

@Singleton
public class App {

    @Inject
    private OwnerFactory customerFactory;

    @Inject
    private BankAccountDepositManipulationService bankAccountDepositManipulationService;

    @Inject
    private BankAccountCardManipulationService bankAccountCardManipulationService;

    @Inject
    private BankAccountService bankAccountService;

    @Inject
    private Logger logger;

    @Inject
    private PaymentCardService paymentCardService;

    @Inject
    private InterestCronService interestCronService;

    public void run() {

        BankAccountOwner customer = customerFactory.createOwner("c-123", "pepa", "srandy");
        this.logger.log(customer.getUuid() + ": " + customer.getFirstName() + " " + customer.getLastName());

        this.logger.log("=== TEST BANK ACCOUNT");
        BaseBankAccount account1 = testBankAccount(customer);

        this.logger.log(account1 instanceof BankAccount ? "Bank" : "Save");

        this.logger.log("=== TEST SAVE ACCOUNT");
        BaseBankAccount account2 = testSaveAccount(customer);
        this.logger.log(account2 instanceof  BankAccount ? "Bank" : "Save");

        if (account2 instanceof SavingBankAccount) {
            float interestRate = ((SavingBankAccount)account2).getInterestRate();
            this.logger.log("Interest Rate: " + interestRate);
        }

        this.testPaymentCard(customer);
    }

    private BaseBankAccount testSaveAccount(BankAccountOwner customer) {
        BaseBankAccount account = this.bankAccountService.createSaveBankAccount(
                "u-123",
                customer,
                0.1f
        );

        try{
            this.logger.log(account.getUuid() + "(" + account.getAccountNumber() + "): " + account.getBalance());

            // account.addBalance(500);
            this.bankAccountDepositManipulationService.deposit(account, 500);
            this.logger.log(account.getUuid() + ": " + account.getBalance());

            // account.subtractBalance(400);
            this.bankAccountDepositManipulationService.withdraw(account, 500);
            this.logger.log(account.getUuid() + ": " + account.getBalance());

        } catch (Exception e) {
            this.logger.log("Error: " + e.getMessage());
        }

        return account;
    }

    private BaseBankAccount testBankAccount(BankAccountOwner customer) {
        BaseBankAccount account = this.bankAccountService.createBankAccount(
                "u-123",
                customer
        );

        try {
            this.logger.log(account.getUuid() + " (" + account.getAccountNumber() + "): " + account.getBalance());

            // account.addBalance(500);
            this.bankAccountDepositManipulationService.deposit(account, 500);
            this.logger.log(account.getUuid() + ": " + account.getBalance());

            this.bankAccountDepositManipulationService.deposit(account, 400);
            this.logger.log(account.getUuid() + ": " + account.getBalance());

            // account.subtractBalance(300);
            this.bankAccountDepositManipulationService.withdraw(account, 300);

        } catch (Exception e) {
            this.logger.log("Error: " + e.getMessage());
        }

        return account;
    }

    private void testPaymentCard(BankAccountOwner customer) {

        this.logger.log("=== PAYMENT CARD test ===");

        BaseBankAccount bankAccount = this.bankAccountService.createBankAccount("c-123", customer);
        this.bankAccountDepositManipulationService.deposit(bankAccount, 50000);

        PaymentCard paymentCard = this.paymentCardService.create(bankAccount);

        this.logger.log("cardNumber: " + paymentCard.getCardNumber());
        this.logger.log("balance: " + paymentCard.getBankAccount().getBalance());

        this.bankAccountCardManipulationService.atmDeposit(paymentCard.getCardNumber(), 500);
        this.logger.log("balance: " + paymentCard.getBankAccount().getBalance());
    }

    private void testInterestCronService(BankAccountOwner customer) {
        SavingBankAccount testAccount = (SavingBankAccount) this.bankAccountService.createSaveBankAccount(
                "cron-test-001",
                customer,
                0.1f
        );
        this.bankAccountDepositManipulationService.deposit(testAccount, 500);

        this.logger.log("Created test account: " + testAccount.getUuid() +
                ", Balance: " + testAccount.getBalance());

        this.logger.log("\nStarting cron service (will run every minute)...");
        this.interestCronService.start();

        this.logger.log("Cron service is running: " + this.interestCronService.isRunning());


        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            this.logger.log("Sleep interrupted: " + e.getMessage());
        }

        this.logger.log("\nStopping cron service...");
        this.interestCronService.stop();

        this.logger.log("Cron service is running: " + this.interestCronService.isRunning());

        this.logger.log("\nFinal balance: " + testAccount.getBalance());

        this.logger.log("\nTesting manual run...");
        this.interestCronService.runManually();
        this.logger.log("Balance after manual run: " + testAccount.getBalance());
    }
}