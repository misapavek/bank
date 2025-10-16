package org.example.bankAccounts.card.services;

import org.example.bankAccounts.BankAccount;
import org.example.bankAccounts.BankAccountWithPaymentCards;
import org.example.bankAccounts.card.PaymentCard;
import java.time.LocalDate;
import java.time.YearMonth;

public class PaymentCardService {
    public void makePayment(BankAccountWithPaymentCards bankAccountWithPaymentCards, PaymentCard paymentCard, double amount) {


        if(!bankAccountWithPaymentCards.getPaymentCardList().contains(paymentCard))
        {
            throw new IllegalArgumentException("Card does not belong to this account");
        }

        if(bankAccountWithPaymentCards.getBalance() < amount){
            throw new IllegalArgumentException("Not enough balance");
        }

        if(this.isCardExpired(paymentCard)){
            throw new IllegalArgumentException("Card is expired");
        }

        bankAccountWithPaymentCards.setbalance(bankAccountWithPaymentCards.getBalance()-amount);
    }

    public boolean isCardExpired(PaymentCard paymentCard){
        int expireMonth = Integer.parseInt(paymentCard.getExpireMonth());
        int expireYear = Integer.parseInt(paymentCard.getExpireYear());

        expireYear += 2000;

        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        if (expireYear < currentYear) {
            return true;
        }
        if (expireYear == currentYear && expireMonth < currentMonth) {
            return true;
        }

        return false;
    }
}
