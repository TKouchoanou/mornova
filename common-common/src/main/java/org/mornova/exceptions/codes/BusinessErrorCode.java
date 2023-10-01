package org.mornova.exceptions.codes;

public enum BusinessErrorCode implements ErrorCode{
    UNKNOWN_ERROR(0,"erreur inconnue"),
    USER_ALREADY_EXIST(1,"utilisateur existant déjà"),

    INCONSISTENCY_ACTION(2,"opération incohérente"),

    EMPTY_ID_AFTER_PERSISTENCE(3,"id null après persistence"),

    REQUIRED_ENTITY_NOT_FOUND_TO_PERFORM_ACTION(4,"required entity not found to perform action"),
    NOT_SUPPORTED_OR_EMPTY_ACTION(5,"you are trying to perform not supported or empty action");
    ;
    final String BUSINESS_CODE_PREFIX="B";

    final int code;
    final String messageKey;

    BusinessErrorCode(int code, String message) {
        this.code = code;
        this.messageKey = message;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getCode() {
        return BUSINESS_CODE_PREFIX+code;
    }
}
