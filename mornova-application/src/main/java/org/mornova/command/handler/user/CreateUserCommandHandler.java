package org.mornova.command.handler.user;

import org.mornova.command.command.user.CreateUserCommand;
import org.mornova.command.event.event.UserCreatedEvent;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.mapper.UserMapper;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.domain.core.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {
    UserRepository userRepository;
    UserMapper userMapper;
    ApplicationEventPublisher eventPublisher;

    public CreateUserCommandHandler(UserRepository userRepository, UserMapper userMapper, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void handle(CreateUserCommand command, HandlingContext handlingContext) {

    User  user= userRepository.save(userMapper.toDomain(command));
      String userId= Optional.ofNullable(user.getId())
              .map(UserId::mapToString)
              .orElseThrow(()->new RuntimeException("id is null on persisted user"));
        command.setId(userId);

       handlingContext.doOnSuccess(()->eventPublisher.publishEvent(new UserCreatedEvent(userId)));
    }

}
/*


*/