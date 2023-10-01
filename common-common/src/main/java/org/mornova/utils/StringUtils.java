package org.mornova.utils;

public class StringUtils {
 public static boolean isEmpty(String value){
        return value==null || value.isEmpty();
    }
    public static boolean isNotEmpty(String value){
        return ! isEmpty(value) ;
    }

    public static String defaultIfEmpty(String value,String defaultValue){
        return isEmpty(value)?defaultValue:value;
    }
}
