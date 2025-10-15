package org.example.people.factories;

import org.example.people.BankAccountOwner;

public class OwnerFactory {
    public BankAccountOwner createOwner(String uuid, String firstname, String lastname) {
        return new  BankAccountOwner(uuid, firstname, lastname);
    }
}
