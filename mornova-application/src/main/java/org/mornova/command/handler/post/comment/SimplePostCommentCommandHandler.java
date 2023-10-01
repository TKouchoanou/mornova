package org.mornova.command.handler.post.comment;

import org.mornova.command.command.post.comment.SimplePostCommentCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.domain.core.model.entities.post.Comment;
import org.mornova.domain.core.model.entities.post.SimplePost;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.domain.core.repository.SimplePostRepository;
import org.mornova.domain.core.repository.UserRepository;
import org.mornova.exceptions.BusinessGenericException;
import org.mornova.exceptions.codes.BusinessErrorCode;

import java.util.List;

public class SimplePostCommentCommandHandler extends PostCommentCommandHandler implements CommandHandler<SimplePostCommentCommand>  {

    SimplePostRepository simplePostRepository;

    public SimplePostCommentCommandHandler(SimplePostRepository SimplePostRepository, UserRepository userRepository) {
        super(userRepository);
        this.simplePostRepository = SimplePostRepository;
    }


    @Override
    public void handle(SimplePostCommentCommand command, HandlingContext handlingContext) {
        SimplePost SimplePost = simplePostRepository.findOrThrow(PostId.valueOf(command.getPostId()));
        Comment comment= updateSimplePostComment(SimplePost,command);
        command.setId(comment.getId().mapToString());
    }
    Comment updateSimplePostComment(SimplePost simplePost, SimplePostCommentCommand command) {
        Comment comment = getCommentId(command).flatMap(simplePost::findCommentById).orElse(null);

        switch (command.getActionType()) {
            case ADD -> {
                Comment newComment = createComment(command);
                List<Comment> old=simplePost.getComments();
                simplePost.addComment(newComment);
               return simplePostRepository.save(simplePost).getComments()
                       .stream()
                       .filter(cmt->!old.contains(cmt))
                       .findFirst().orElseThrow(()-> new RuntimeException("echec ajout commentaire simple post"));

            }
            case UPDATE -> {
                Comment updatedComment = updateComment(command, comment);
                simplePost.updateComment(updatedComment);
                simplePostRepository.save(simplePost);
                return updatedComment;
            }
            case DELETE -> {
                if (comment != null) {
                    simplePost.removeComment(comment);
                    simplePostRepository.save(simplePost);
                    return comment;
                }
                throw new BusinessGenericException(BusinessErrorCode.REQUIRED_ENTITY_NOT_FOUND_TO_PERFORM_ACTION);
            }
            default -> throw new BusinessGenericException(BusinessErrorCode.NOT_SUPPORTED_OR_EMPTY_ACTION);
        }
    }

 
}
