package org.mornova.command.command.profile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mornova.command.command.Command;
import org.mornova.command.handler.profile.CreateProfileCommandHandler;

@Builder
@Getter
@ToString
@Command.Usecase(handlers = {CreateProfileCommandHandler.class})
public class CreateProfileCommand implements Command {
    @Setter
    private String id;
    private String ownerId;


    @Override
    public boolean isSatisfied() {
        return id!=null && !id.isEmpty();
    }
}
