package org.mornova.command.command.user;

import lombok.Builder;
import lombok.Getter;
import org.mornova.command.command.Command;
import org.mornova.command.handler.user.UpdateUserCommandHandler;

import java.util.List;
@Builder
@Getter
@Command.Usecase(handlers = {UpdateUserCommandHandler.class})
public class UpdateUserCommand implements Command {
    String id;
    private  String firstName;
    private String lastName;
    private String email;
    private List<String> roles;
    @Override
    public boolean isSatisfied() {
        return true;
    }
}
