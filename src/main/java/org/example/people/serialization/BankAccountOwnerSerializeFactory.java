package org.example.people.serialization;

import org.example.accounts.serialization.BankAccountOwnerSerialization;
import org.example.people.BankAccountOwner;

public class BankAccountOwnerSerializeFactory {

    public static BankAccountOwnerSerialization createBankAccountOwnerSerialization(BankAccountOwner bankAccountOwner) {
        BankAccountOwnerSerialization bankAccountOwnerSerialization = new BankAccountOwnerSerialization();

        bankAccountOwnerSerialization.uuid = bankAccountOwner.getUuid();
        bankAccountOwnerSerialization.firstName = bankAccountOwner.getFirstName();
        bankAccountOwnerSerialization.lastName = bankAccountOwner.getLastName();

        return bankAccountOwnerSerialization;
    }

}