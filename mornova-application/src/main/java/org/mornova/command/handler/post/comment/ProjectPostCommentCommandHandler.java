package org.mornova.command.handler.post.comment;

import org.mornova.command.command.post.comment.ProjectPostCommentCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.domain.core.model.entities.post.Comment;
import org.mornova.domain.core.model.entities.post.ProjectPost;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.domain.core.repository.ProjectPostRepository;
import org.mornova.domain.core.repository.UserRepository;
import org.mornova.exceptions.BusinessGenericException;
import org.mornova.exceptions.TechnicalGenericException;
import org.mornova.exceptions.codes.BusinessErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectPostCommentCommandHandler extends PostCommentCommandHandler implements CommandHandler<ProjectPostCommentCommand> {
    ProjectPostRepository projectPostRepository;

    UserRepository userRepository;

    public ProjectPostCommentCommandHandler(ProjectPostRepository projectPostRepository, UserRepository userRepository) {
        super(userRepository);
        this.projectPostRepository = projectPostRepository;
    }


    @Override
    public void handle(ProjectPostCommentCommand command, HandlingContext handlingContext) {
        ProjectPost projectPost = projectPostRepository.findOrThrow(PostId.valueOf(command.getPostId()));
       Comment comment = updateProjectPostComment(projectPost,command);
       command.setId(comment.getId().mapToString());
    }

    Comment updateProjectPostComment(ProjectPost projectPost, ProjectPostCommentCommand command) {
        Comment comment = getCommentId(command).flatMap(projectPost::findCommentById).orElse(null);

        switch (command.getActionType()) {
            case ADD -> {
                Comment newComment = createComment(command);
                List<Comment> old=projectPost.getComments();
                projectPost.addComment(newComment);
               return projectPostRepository.save(projectPost)
                       .getComments()
                       .stream()
                       .filter(cmt->!old.contains(cmt))
                       .findFirst().orElseThrow(()->new TechnicalGenericException("echec ajout commentaire"));

            }
            case UPDATE -> {
                Comment updatedComment = updateComment(command, comment);
                projectPost.updateComment(updatedComment);
                projectPostRepository.save(projectPost);
                return updatedComment;
            }
            case DELETE -> {
                if (comment != null) {
                    projectPost.removeComment(comment);
                    projectPostRepository.save(projectPost);
                    return comment;
                }
                throw new BusinessGenericException(BusinessErrorCode.REQUIRED_ENTITY_NOT_FOUND_TO_PERFORM_ACTION);
            }
            default -> throw new BusinessGenericException(BusinessErrorCode.NOT_SUPPORTED_OR_EMPTY_ACTION);
        }
    }
}
