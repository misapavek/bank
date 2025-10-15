package org.example.serialization;

import org.example.people.BankAccountOwner;

public interface Serialization {

    public String serialize(Object object);

    public Object deserialize(String serializedData);

}