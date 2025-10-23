package org.example.bankAccounts;

import org.example.card.PaymentCard;
import org.example.people.BankAccountOwner;
import java.util.ArrayList;
import java.util.List;

public class BankAccountWithPaymentCards extends BaseBankAccount {
    private List<PaymentCard> paymentCardList;

    public BankAccountWithPaymentCards(String uuid, String accountNumber, BankAccountOwner owner, double balance) {
        super(uuid, accountNumber, owner, balance);
        this.paymentCardList = new ArrayList<>();
    }

    public void addPaymentCard(PaymentCard card) {
        paymentCardList.add(card);
    }

    public List<PaymentCard> getPaymentCardList() {
        return paymentCardList;
    }
}