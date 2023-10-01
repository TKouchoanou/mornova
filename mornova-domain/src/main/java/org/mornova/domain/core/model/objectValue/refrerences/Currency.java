package org.mornova.domain.core.model.objectValue.refrerences;

import org.mornova.domain.core.model.objectValue.MapToString;

public enum Currency  implements MapToString {
    EUR,DOLLAR,DIRAM;



    @Override
    public String mapToString() {
        return this.name();
    }
    public static Currency getInstance(String value) {
       System.out.println(EUR.name());
       return Currency.valueOf(value);
    }
}
