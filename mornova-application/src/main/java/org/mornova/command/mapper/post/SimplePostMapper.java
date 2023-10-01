package org.mornova.command.mapper.post;

import org.mapstruct.Mapper;
import org.mornova.command.command.post.simple.CreateSimplePostCommand;
import org.mornova.command.command.post.simple.UpdateSimplePostCommand;
import org.mornova.domain.core.model.entities.BasedEntity;
import org.mornova.domain.core.model.entities.post.SimplePost;
import org.mornova.domain.core.model.objectValue.ids.PostId;

import java.util.function.Consumer;
@Mapper
public interface SimplePostMapper {
    default SimplePost toDomain(CreateSimplePostCommand command) {
        return SimplePost.builder()
                .content(command.getContent())
                .build();
    }
    default SimplePost toDomain(UpdateSimplePostCommand command, Consumer<BasedEntity.PersistenceDetailBuilder<PostId>> consumer) {
        return SimplePost.builder()
                .content(command.getContent())
                .persistenceDetail(consumer)
                .build();
    }
}
