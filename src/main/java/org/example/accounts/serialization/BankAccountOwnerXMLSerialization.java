package org.example.accounts.serialization;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.people.BankAccountOwner;

public class BankAccountOwnerXMLSerialization implements Serialization {

    private final XmlMapper xmlmapper = new XmlMapper();

    @Override
    public String serialization(Object serializationObject) {
            try {
                return xmlmapper.writeValueAsString(serializationObject);
            } catch (Exception e){
                throw new RuntimeException(e);
            }
    }

    @Override
    public Object deserialization(String serializedObject) {
        try {
            return xmlmapper.readValue(serializedObject, BankAccountOwner.class);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
