package org.mornova.repository.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.mornova.domain.core.model.objectValue.refrerences.Currency;
import org.mornova.domain.core.model.objectValue.value.Amount;

import java.math.BigDecimal;

@Converter
public class AmountConverter implements AttributeConverter<Amount, String> {

    private final static String amount_currency_separator="@";
    @Override
    public String convertToDatabaseColumn(Amount amount) {
        if (amount == null) {
            return null;
        }
        String value = amount.value().toEngineeringString();
        String currencyCode = amount.currency().name();
        return value + amount_currency_separator + currencyCode;

    }

    @Override
    public Amount convertToEntityAttribute(String amount) {
        if (amount == null) {
            return null;
        }
        String[] parts = amount.split(amount_currency_separator);
        BigDecimal value = new BigDecimal(parts[0]);
        Currency currency = Currency.valueOf(parts[1]);
        return new Amount(value, currency);
    }
}
