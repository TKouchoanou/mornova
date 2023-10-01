package org.mornova.command.command.post.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mornova.command.command.Command;
import org.mornova.command.handler.post.CreateSimplePostCommandHandler;
import org.mornova.utils.StringUtils;
@SuperBuilder
@Getter
@Command.Usecase(handlers = {CreateSimplePostCommandHandler.class})
@AllArgsConstructor
@NoArgsConstructor
public class CreateSimplePostCommand extends SimplePostCommand implements Command {
    @Setter
    private String id;
    @Override
    public boolean isSatisfied() {
        return StringUtils.isNotEmpty(id);
    }
}
