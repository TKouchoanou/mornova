package org.mornova.command.handler.post;

import org.mornova.command.command.post.simple.CreateSimplePostCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.mapper.post.SimplePostMapper;
import org.mornova.domain.core.model.entities.post.SimplePost;
import org.mornova.domain.core.repository.SimplePostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateSimplePostCommandHandler implements CommandHandler<CreateSimplePostCommand> {
    SimplePostRepository simplePostRepository;

    SimplePostMapper simplePostMapper;

    public CreateSimplePostCommandHandler(SimplePostRepository simplePostRepository, SimplePostMapper simplePostMapper) {
        this.simplePostRepository = simplePostRepository;
        this.simplePostMapper = simplePostMapper;
    }

    @Override
    public void handle(CreateSimplePostCommand command, HandlingContext handlingContext) {
       SimplePost simplePost= simplePostRepository.save(simplePostMapper.toDomain(command));
       command.setId(simplePost.getId().mapToString());
    }
}
