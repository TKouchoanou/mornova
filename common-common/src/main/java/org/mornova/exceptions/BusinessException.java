package org.mornova.exceptions;

import org.mornova.exceptions.codes.BusinessErrorCode;

public interface BusinessException {
    BusinessErrorCode getCode();
    String getCustomerMessage();
}
