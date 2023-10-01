package org.mornova.command.handler.post;

import org.mornova.command.command.post.project.UpdateProjectPostCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.mapper.post.ProjectPostMapper;
import org.mornova.domain.core.model.entities.post.ProjectPost;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.domain.core.repository.ProjectPostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateProjectPostCommandHandler implements CommandHandler<UpdateProjectPostCommand> {
    ProjectPostRepository projectPostRepository;
    ProjectPostMapper projectPostMapper;

    public UpdateProjectPostCommandHandler(ProjectPostRepository projectPostRepository, ProjectPostMapper projectPostMapper) {
        this.projectPostRepository = projectPostRepository;
        this.projectPostMapper = projectPostMapper;
    }

    @Override
    public void handle(UpdateProjectPostCommand command, HandlingContext handlingContext) {
        ProjectPost projectPost = projectPostRepository.findOrThrow(PostId.valueOf(command.getId()));
      ProjectPost updatedProjectPost= projectPostMapper.toDomain(command, projectPost.persistenceDetailBuilderConsumer());
      projectPostRepository.save(updatedProjectPost);

    }
}
