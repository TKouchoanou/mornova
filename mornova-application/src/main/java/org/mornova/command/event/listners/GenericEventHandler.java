package org.mornova.command.event.listners;

import org.mornova.command.CommandManager;
import org.mornova.command.command.profile.CreateProfileCommand;
import org.mornova.command.event.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@SuppressWarnings("unused")
public class GenericEventHandler {
    CommandManager commandManager;

    public GenericEventHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @EventListener(value = UserCreatedEvent.class)
    public void OnUserCreated(UserCreatedEvent userCreated){
        Runnable runCreateProfile= ()-> commandManager.process(CreateProfileCommand.builder()
                .id(userCreated.userId())
                .build());
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(runCreateProfile , 5, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}
