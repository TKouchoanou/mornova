package org.mornova.rest.interceptor;

import java.io.Serializable;
import java.time.LocalDate;

public class ErrorMessage implements Serializable {
    String developperMessage;

    String customerMessage;
    String code;

    LocalDate date= LocalDate.now();

    public ErrorMessage(String message, String customerMessage, String code) {
        this.developperMessage = message;
        this.customerMessage = customerMessage;
        this.code = code;
    }

    public ErrorMessage(String message, String customerMessage) {
        this.developperMessage = message;
        this.customerMessage = customerMessage;
    }

    public String getCode() {
        return code;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public String getDeveloperMessage() {
        return developperMessage;
    }

    public LocalDate getDate() {
       return date;
    }
}
