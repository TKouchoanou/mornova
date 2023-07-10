package org.mornova.domain.core.model.objectValue.value;

import org.mornova.domain.core.model.objectValue.refrerences.Currency;

import java.math.BigDecimal;


public class Amount {
    BigDecimal value;

    Currency currency;

    public Amount(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }
    public Amount() {

    }

    public Currency currency() {
        return currency;
    }

    public BigDecimal value() {
        return value;
    }

    //TODO getter  a supprimer  des objects values, mis seulement pour permettre à jackson de sérialiser

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }
}
