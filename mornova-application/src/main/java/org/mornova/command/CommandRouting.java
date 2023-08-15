package org.mornova.command;

import org.mornova.command.command.Command;
import org.mornova.command.handler.CommandHandler;
import org.springframework.transaction.annotation.Isolation;

import java.util.List;

public interface CommandRouting {
    List<CommandHandler> getHandlers(Command cmd);
    boolean isParallelHandling(Command cmd);

    Isolation getIsolation(Command cmd);
}
