package org.mornova.command.command.post.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.mornova.command.command.Command;
import org.mornova.command.handler.post.comment.ProjectPostCommentCommandHandler;

@SuperBuilder
@Getter
@AllArgsConstructor
@Command.Usecase(handlers = {ProjectPostCommentCommandHandler.class})
public class ProjectPostCommentCommand extends PostCommentCommand {
}
