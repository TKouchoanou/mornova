package org.mornova.exceptions;

import org.mornova.exceptions.codes.BusinessErrorCode;
import org.mornova.utils.StringUtils;

public class BusinessGenericException extends RuntimeException implements BusinessException{
    BusinessErrorCode code;
    String customerMessage;
    public BusinessGenericException(BusinessErrorCode code, String message) {
        super(message);
        this.code = code;
    }
    public BusinessGenericException(BusinessErrorCode code) {
        super(code.getMessageKey());
        this.code = code;
    }

    public BusinessGenericException( BusinessErrorCode code, String message,String customerMessage) {
        super(message);
        this.code = code;
        this.customerMessage = customerMessage;
    }


    @Override
    public BusinessErrorCode getCode() {
        return code;
    }

    @Override
    public String getCustomerMessage() {
        return StringUtils.defaultIfEmpty(customerMessage,code.getMessageKey());
    }


}
