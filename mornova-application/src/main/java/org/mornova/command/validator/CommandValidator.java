package org.mornova.command.validator;

import org.mornova.command.command.Command;

public interface CommandValidator {
    void  validBeforeHandling(Command command);
    void  validAfterHandling(Command command);
}
