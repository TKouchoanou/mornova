package org.mornova.command.command.post.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mornova.command.command.Command;
import org.mornova.command.handler.post.UpdateProjectPostCommandHandler;

@Getter
@SuperBuilder
@Command.Usecase(handlers={UpdateProjectPostCommandHandler.class})
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectPostCommand extends ProjectPostCommand implements Command {
    @Setter
    String id;
    @Override
    public boolean isSatisfied() {
        return true;
    }
}
