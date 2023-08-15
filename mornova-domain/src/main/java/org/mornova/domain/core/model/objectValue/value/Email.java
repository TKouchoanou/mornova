package org.mornova.domain.core.model.objectValue.value;

import org.mornova.domain.core.model.objectValue.refrerences.EmailRegex;

import java.util.regex.Pattern;

public class Email {
    private final String email;

    public Email(String address) {
        assertIsValid(address);
        this.email = address;
    }
     boolean isValid(String email) {
        // Expression régulière pour valider l'adresse e-mail
        Pattern pattern = Pattern.compile(EmailRegex.value);

         if (email == null || email.isEmpty()) {
             return false;
         }
        return pattern.matcher(email).matches();
    }

    void assertIsValid(String email){
        if(!isValid(email)){
            throw new RuntimeException(email+" is not valid");
        }
    }

    public String getEmail() {
        return email;
    }
}
