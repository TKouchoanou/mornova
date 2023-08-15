package org.mornova.command.command;

import org.mornova.command.handler.CommandHandler;
import org.springframework.transaction.annotation.Isolation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@SuppressWarnings("unused")
public interface Command {
    boolean isSatisfied();
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
     @interface Usecase{
        Class<? extends CommandHandler>[] handlers();

        boolean parallelHandling() default false;

        Isolation isolation() default Isolation.DEFAULT;

        String requiredAuthority() default "";

        boolean secured() default false;
    }
}
