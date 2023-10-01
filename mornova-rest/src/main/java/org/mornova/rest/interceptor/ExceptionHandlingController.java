package org.mornova.rest.interceptor;

import org.mornova.exceptions.BusinessGenericException;
import org.mornova.exceptions.TechnicalGenericException;
import org.mornova.exceptions.codes.TechnicalErrorCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlingController  extends ResponseEntityExceptionHandler {

    @ResponseStatus(value= HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorMessage conflict(DataIntegrityViolationException exception) {
        return new ErrorMessage(exception.getMessage(), "Data intégrity violation", TechnicalErrorCode.DUPLICATE_ENTRY.getCode());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BusinessGenericException.class})
    public ErrorMessage resourceNotFoundException(BusinessGenericException exception) {
        return new ErrorMessage(exception.getMessage(), exception.getCustomerMessage(), exception.getCode().getCode());
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({TechnicalGenericException.class})
    public ErrorMessage resourceNotFoundException(TechnicalGenericException exception) {
        return new ErrorMessage(exception.getMessage(), exception.getCustomerMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public ErrorMessage resourceNotFoundException(RuntimeException exception) {
        return new ErrorMessage(exception.getMessage(), "Erreur inattendu c'est produite");
    }
}
