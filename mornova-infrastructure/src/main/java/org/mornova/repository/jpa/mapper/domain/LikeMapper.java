package org.mornova.repository.jpa.mapper.domain;

import org.mornova.domain.core.model.entities.post.Like;
import org.mornova.domain.core.model.objectValue.ids.LikeId;
import org.mornova.repository.jpa.entity.LikeJPA;
import org.mornova.repository.jpa.entity.UserJPA;
@Mapper
public class LikeMapper implements DomainToJpaMapper<Like, LikeJPA> {

   private final UserMapper userMapper;

    public LikeMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Like toDomainEntity(LikeJPA likeJPA) {
        return Like
                .builder()
                .liker(userMapper.toDomainEntity(likeJPA.getLiker()))
                .persistenceDetail(detail->detail
                        .id(new LikeId(likeJPA.getId()))
                        .createdAt(likeJPA.getCreatedAt())
                        .updatedAt(likeJPA.getUpdatedAt()))
                .build();
    }

    @Override
    public LikeJPA toJpaEntity(Like like) {
        Long id=like.getId()!=null?like.getId().value():null;

        UserJPA likerJPA=userMapper.toJpaEntity(like.getLiker());
        return LikeJPA.builder()
                .liker(likerJPA)
                .id(id)
                .createdAt(like.getCreatedAt())
                .updatedAt(like.getUpdatedAt())
                .build();
    }
}
