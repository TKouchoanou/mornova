package org.mornova.domain.core.model.objectValue.ids;

import java.io.Serializable;
import java.util.function.Supplier;

public interface BaseId <ID> extends Serializable {
    ID value();

   default String mapToString(){
       return value().toString();
    }
    default String mapToStringOrThrowIfNull(Supplier<? extends RuntimeException> exceptionSupplier){
       if(value()==null){
           throw exceptionSupplier.get();
       }
        return value().toString();
    }

    default String mapToStringOrThrow(){
       return mapToStringOrThrowIfNull(()->new RuntimeException(this.getClass().getName()+"id value is null"));
    }



}
