package org.example.card.services;

import org.example.bankAccounts.BankAccountWithPaymentCards;
import org.example.card.PaymentCard;
import java.time.LocalDate;
import java.util.logging.Logger;

public class PaymentCardService {

    private static final Logger logger = Logger.getLogger("CardServiceLog");

    public void makePayment(BankAccountWithPaymentCards bankAccountWithPaymentCards, PaymentCard paymentCard, double amount) {

        String timestamp = LocalDate.now().toString();
        logger.info(String.format("[%s] Payment attempt - Amount: %.2f, Card: %s", timestamp, amount, paymentCard.getCardNumber()));

        if(!bankAccountWithPaymentCards.getPaymentCardList().contains(paymentCard))
        {
            logger.warning(String.format("[%s] Payment failed - Card ****%s does not belong to account",
                    timestamp, paymentCard.getCardNumber()));
            throw new IllegalArgumentException("Card does not belong to this account");
        }

        if(bankAccountWithPaymentCards.getBalance() < amount){
            logger.warning(String.format("[%s] Payment failed - Insufficient funds. Required: %.2f, Available: %.2f",
                    timestamp, amount, bankAccountWithPaymentCards.getBalance()));
            throw new IllegalArgumentException("Not enough balance");
        }

        if(this.isCardExpired(paymentCard)){
            logger.warning(String.format("[%s] Payment failed - Card expired", timestamp));
            throw new IllegalArgumentException("Card is expired");
        }

        double previousBalance = bankAccountWithPaymentCards.getBalance();
        bankAccountWithPaymentCards.setbalance(bankAccountWithPaymentCards.getBalance()-amount);

        logger.info(String.format("[%s] Payment successful - Amount: %.2f, Card: %s, Balance: %.2f -> %.2f", timestamp, amount, paymentCard.getCardNumber(), previousBalance, bankAccountWithPaymentCards.getBalance()));
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
