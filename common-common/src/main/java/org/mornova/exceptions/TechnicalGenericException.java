package org.mornova.exceptions;

import org.mornova.utils.StringUtils;

public class TechnicalGenericException  extends  RuntimeException implements TechnicalException {

    String customerMessage;

    static String DEFAULT_MESSAGE;

    public TechnicalGenericException(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    public TechnicalGenericException(String message, String customerMessage) {
        super(message);
        this.customerMessage = customerMessage;
    }

    public TechnicalGenericException(String message, Throwable cause, String customerMessage) {
        super(message, cause);
        this.customerMessage = customerMessage;
    }

    @Override
    public String getCustomerMessage() {
        return StringUtils.defaultIfEmpty(customerMessage,DEFAULT_MESSAGE);
    }
}
