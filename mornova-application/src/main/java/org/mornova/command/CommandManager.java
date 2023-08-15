package org.mornova.command;

import org.mornova.command.command.Command;

public interface CommandManager {
    <T extends Command> T process(T command);
}
