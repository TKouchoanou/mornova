package org.mornova.domain.core.model.objectValue.value;

import org.mornova.domain.core.model.objectValue.Formatter;
import org.mornova.domain.core.model.objectValue.refrerences.Currency;

import java.math.BigDecimal;


public class Amount implements Formatter {
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

    public String getFormattedValue() {
        return value+" "+currency;
    }
    public static Amount valueOf(String value){
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        String[] parts = value.trim().split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid value format: " + value);
        }

        try {
            BigDecimal amountValue = new BigDecimal(parts[0]);
            Currency currency = Currency.getInstance(parts[1].trim());
            return new Amount(amountValue, currency);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric value: " + parts[0]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency code: " + parts[1]);
        }
    }
}
