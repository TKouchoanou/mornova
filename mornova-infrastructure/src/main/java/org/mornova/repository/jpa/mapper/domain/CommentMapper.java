package org.mornova.repository.jpa.mapper.domain;

import org.mornova.domain.core.model.entities.post.Comment;
import org.mornova.domain.core.model.objectValue.ids.CommentId;
import org.mornova.repository.jpa.entity.CommentJPA;

import java.time.LocalDateTime;
@Mapper
public class CommentMapper implements DomainToJpaMapper<Comment, CommentJPA> {
    private final UserMapper userMapper;

    public CommentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Comment toDomainEntity(CommentJPA commentJPA) {
        CommentId commentId=new CommentId(commentJPA.getId());
        LocalDateTime createdAt=commentJPA.getCreatedAt();
        LocalDateTime updatedAt=commentJPA.getUpdatedAt();
        return Comment.builder()
                .persistenceDetail(pd-> pd.id(commentId)
                        .updatedAt(updatedAt)
                        .createdAt(createdAt)
                )
                .author(userMapper.toDomainEntity(commentJPA.getAuthor()))
                .content(commentJPA.getContent())
                .build();
    }

    @Override
    public CommentJPA toJpaEntity(Comment comment) {
        CommentJPA.CommentJPABuilder builder = CommentJPA
                .builder()
                .content(comment.getContent())
                .updatedAt(comment.getUpdatedAt())
                .createdAt(comment.getCreatedAt());

        if (comment.getId() != null) {
            builder.id(comment.getId().value());
        }

        if (comment.getAuthor() != null) {
            builder.author(userMapper.toJpaEntity(comment.getAuthor()));
        }


        return builder.build();
    }
}
