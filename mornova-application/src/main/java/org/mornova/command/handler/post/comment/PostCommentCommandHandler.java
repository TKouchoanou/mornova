package org.mornova.command.handler.post.comment;

import org.mornova.command.command.post.comment.PostCommentCommand;
import org.mornova.domain.core.model.entities.post.Comment;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.CommentId;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.domain.core.repository.UserRepository;
import org.mornova.exceptions.BusinessGenericException;
import org.mornova.exceptions.codes.BusinessErrorCode;

import java.util.Optional;

public abstract class PostCommentCommandHandler {
    UserRepository userRepository;

    public PostCommentCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Optional<CommentId> getCommentId(PostCommentCommand command){
        return Optional.ofNullable(command.getId()).map(CommentId::valueOf);
    }

    Comment createComment(PostCommentCommand command) {
        User author = userRepository.findOrThrow(UserId.valueOf(command.getAuthorId()));
        return Comment.builder()
                .author(author)
                .content(command.getContent())
                .build();
    }

    Comment updateComment(PostCommentCommand command, Comment comment) {
        if (command.getId() == null || comment == null) {
            throw new BusinessGenericException(BusinessErrorCode.INCONSISTENCY_ACTION);
        }
        comment.setContent(command.getContent());
        return comment;
    }
}
