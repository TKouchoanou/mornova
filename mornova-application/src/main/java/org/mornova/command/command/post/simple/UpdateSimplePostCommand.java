package org.mornova.command.command.post.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mornova.command.command.Command;
import org.mornova.command.handler.post.UpdateSimplePostCommandHandler;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Command.Usecase(handlers = {UpdateSimplePostCommandHandler.class})
public class UpdateSimplePostCommand extends SimplePostCommand implements Command {
    @Setter
    String id;
    @Override
    public boolean isSatisfied() {
        return false;
    }
}
