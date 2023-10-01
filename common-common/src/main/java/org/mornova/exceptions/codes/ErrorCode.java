package org.mornova.exceptions.codes;

public interface ErrorCode {

    String getMessageKey();

     String getCode() ;

     static String makeCode(String prefix,int code){
         return prefix+"-"+code;
     }
}
