package org.mornova.command;

import org.mornova.command.command.Command;
import org.mornova.command.handler.CommandHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@SuppressWarnings("unused")
@Component
public class CommandRoutingImpl implements CommandRouting{
    ApplicationContext context;
    public CommandRoutingImpl(ApplicationContext context){
        this.context=context;
    }

    @Override
    public List<CommandHandler> getHandlers(Command cmd) {
        Command.Usecase usecase= cmd.getClass().getAnnotation(Command.Usecase.class);
        return Stream.of(usecase.handlers()).map(context::getBean).collect(Collectors.toList());
    }

    @Override
    public boolean isParallelHandling(Command cmd) {
        return cmd.getClass().getAnnotation(Command.Usecase.class).parallelHandling();
    }

    @Override
    public Isolation getIsolation(Command cmd) {
        return cmd.getClass().getAnnotation(Command.Usecase.class).isolation();
    }
}
