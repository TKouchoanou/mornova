package org.mornova.domain.core.model.objectValue.ids;

import org.mornova.domain.core.model.objectValue.MapToString;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Supplier;

public interface BaseId <ID> extends Serializable, MapToString {
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
    static UUID uuidFromString(String value){
       return UUID.fromString(value);
    }
    static Long longFromString(String value){
     return Long.valueOf(value);
    }



}
