package org.example.serialization;

import com.google.gson.Gson;
import org.example.bankAccounts.serialization.BankAccountOwnerSerialization;
import org.example.bankAccounts.serialization.BankAccountOwnerSerializationFactory;
import org.example.people.BankAccountOwner;
import org.example.people.serialization.BankAccountOwnerSerialize;

public class BankAccountOwnerJsonSerializationService implements Serialization {

    Gson gson = new Gson();
    BankAccountOwnerSerializationFactory bankAccountOwnerSerializationFactory = new BankAccountOwnerSerializationFactory();

    @Override
    public String serialize(Object bankAccountOwner) {

        if (!(bankAccountOwner instanceof BankAccountOwner)) {
            throw new IllegalArgumentException("BankAccountOwner must be of type BankAccountOwner");
        }

        BankAccountOwnerSerialization bankAccountOwnerSerialize = bankAccountOwnerSerializationFactory.createBankAccountOwnerSerialization((BankAccountOwner) bankAccountOwner);

        StringBuilder builder = new StringBuilder();

        builder.append("{");
        builder.append(" \"uuid\": \" ");
        builder.append(bankAccountOwnerSerialize.firstName);
        // TODO

        builder.append("}");
        // return builder.toString();


        return gson.toJson(bankAccountOwnerSerialize);

    }

    @Override
    public Object deserialize(String serializedData) {
        return gson.fromJson("", BankAccountOwnerSerialize.class);
    }
}