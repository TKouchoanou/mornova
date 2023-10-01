package org.mornova.command.command.post.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mornova.command.command.Command;
import org.mornova.command.handler.post.CreateProjectPostCommandHandler;
import org.mornova.utils.StringUtils;
@SuperBuilder
@Getter
@Command.Usecase(handlers = {CreateProjectPostCommandHandler.class})
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectPostCommand extends ProjectPostCommand implements Command {
    @Setter
    private String id;
    @Override
    public boolean isSatisfied() {
        return StringUtils.isNotEmpty(id);
    }
}
