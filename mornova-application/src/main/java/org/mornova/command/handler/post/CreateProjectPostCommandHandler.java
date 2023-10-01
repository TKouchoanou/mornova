package org.mornova.command.handler.post;

import org.mornova.command.command.post.project.CreateProjectPostCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.mapper.post.ProjectPostMapper;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.entities.post.ProjectPost;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.domain.core.repository.ProjectPostRepository;
import org.mornova.domain.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectPostCommandHandler implements CommandHandler<CreateProjectPostCommand> {
    ProjectPostRepository projectPostRepository;

    UserRepository userRepository;

    ProjectPostMapper projectPostMapper;

    public CreateProjectPostCommandHandler(ProjectPostRepository projectPostRepository, UserRepository userRepository, ProjectPostMapper projectPostMapper) {
        this.projectPostRepository = projectPostRepository;
        this.userRepository = userRepository;
        this.projectPostMapper = projectPostMapper;
    }


    @Override
    public void handle(CreateProjectPostCommand command, HandlingContext handlingContext) {
        User author = userRepository.findOrThrow(UserId.valueOf(command.getAuthorId()));
        ProjectPost newPost=projectPostMapper.toDomain(command, builder -> builder.author(author));
        ProjectPost projectPost = projectPostRepository.save(newPost);
        command.setId(projectPost.getId().mapToString());
    }
}
