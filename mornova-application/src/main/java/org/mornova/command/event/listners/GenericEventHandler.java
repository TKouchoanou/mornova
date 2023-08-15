package org.mornova.command.event.listners;

import org.mornova.command.CommandManager;
import org.mornova.command.command.profile.CreateProfileCommand;
import org.mornova.command.event.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class GenericEventHandler {
    CommandManager commandManager;

    public GenericEventHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @EventListener(value = UserCreatedEvent.class)
    public void OnUserCreated(UserCreatedEvent userCreated){
        commandManager.process(CreateProfileCommand.builder()
                .id(userCreated.userId())
                .build());
    }
}
