package org.mornova.command.command.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mornova.command.command.Command;
import org.mornova.command.handler.user.CreateUserCommandHandler;

import java.util.List;
@Builder
@Getter
@Command.Usecase(handlers = {CreateUserCommandHandler.class})
public class CreateUserCommand implements Command {
    @Setter
    String id;
    private  String firstName;
    private String lastName;
    private String email;
    private List<String> roles;

    @Override
    public boolean isSatisfied() {
        return id!=null && !id.isEmpty();
    }
}
