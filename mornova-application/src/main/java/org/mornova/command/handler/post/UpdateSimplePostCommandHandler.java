package org.mornova.command.handler.post;

import org.mornova.command.command.post.simple.UpdateSimplePostCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.mapper.post.SimplePostMapper;
import org.mornova.domain.core.model.entities.post.SimplePost;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.domain.core.repository.SimplePostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateSimplePostCommandHandler implements CommandHandler<UpdateSimplePostCommand> {
    SimplePostRepository simplePostRepository;

    SimplePostMapper simplePostMapper;

    public UpdateSimplePostCommandHandler(SimplePostRepository simplePostRepository, SimplePostMapper simplePostMapper) {
        this.simplePostRepository = simplePostRepository;
        this.simplePostMapper = simplePostMapper;
    }

    @Override
    public void handle(UpdateSimplePostCommand command, HandlingContext handlingContext) {
      SimplePost simplePost = simplePostRepository.findOrThrow(PostId.valueOf(command.getId()));
      SimplePost updatedSimplePost= simplePostMapper.toDomain(command,simplePost.persistenceDetailBuilderConsumer());
      simplePostRepository.save(updatedSimplePost);
    }
}
