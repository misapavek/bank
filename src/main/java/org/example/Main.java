package org.example;

import org.example.bankAccounts.BankAccountWithPaymentCards;
import org.example.card.PaymentCard;
import org.example.card.PaymentCardFactory;
import org.example.card.services.PaymentCardService;
import org.example.bankAccounts.factories.BankAccountFactory;
import org.example.bankAccounts.services.BankAccountService;
import org.example.people.BankAccountOwner;
import org.example.people.factories.OwnerFactory;

public class Main {

    public static void main(String[] args) {
        OwnerFactory ownerFactory = new OwnerFactory();
        BankAccountOwner owner = ownerFactory.createOwner("1234","pepa","novak");

        BankAccountFactory bankAccountFactory = new BankAccountFactory();
        BankAccountWithPaymentCards bankAccount = bankAccountFactory.createBankAcountWithPaymentCards("123",owner, 500);
        BankAccountService bankAccountService = new BankAccountService();

        PaymentCardFactory  paymentCardFactory = new PaymentCardFactory();
        PaymentCard paymentCard = paymentCardFactory.create(owner);
        PaymentCard paymentCard2 = paymentCardFactory.create(owner);
        PaymentCardService paymentCardService = new PaymentCardService();



        bankAccount.addPaymentCard(paymentCard);
        bankAccount.addPaymentCard(paymentCard2);

        System.out.println(owner.getUuid() + " " + owner.getFullName() + ": " + bankAccount.getAccountNumber() + " " + bankAccount.getBalance());
        paymentCardService.makePayment(bankAccount, paymentCard, 499);
        System.out.println(bankAccount.getAccountNumber() + " " + bankAccount.getBalance());
        bankAccountService.addBalance(bankAccount, 100000);
        System.out.println(bankAccount.getAccountNumber() + " " + bankAccount.getBalance());
    }

}