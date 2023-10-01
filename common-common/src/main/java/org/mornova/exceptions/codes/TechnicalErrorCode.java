package org.mornova.exceptions.codes;

public enum TechnicalErrorCode implements ErrorCode{
    DUPLICATE_ENTRY(1001,"Conflict redondance d'entité non autorisé")
    ;
    final static String TECHNICAL_CODE_PREFIX="T";
    final int code;
    final String messageKey;

    TechnicalErrorCode(int code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }

    @Override
    public String getCode() {
        return ErrorCode.makeCode(TECHNICAL_CODE_PREFIX,code);
    }
}
