package org.mornova.repository.jpa.mapper.domain;

import org.mornova.domain.core.model.entities.post.Comment;
import org.mornova.domain.core.model.entities.post.Like;
import org.mornova.domain.core.model.entities.post.SimplePost;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.repository.jpa.entity.CommentJPA;
import org.mornova.repository.jpa.entity.LikeJPA;
import org.mornova.repository.jpa.entity.SimplePostJPA;
import org.mornova.repository.jpa.entity.UserJPA;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Mapper
public class SimplePostMapper implements DomainToJpaMapper<SimplePost, SimplePostJPA>{
    UserMapper userMapper;
    CommentMapper commentMapper;

    LikeMapper likeMapper;

    public SimplePostMapper(UserMapper userMapper, CommentMapper commentMapper, LikeMapper likeMapper) {
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
        this.likeMapper = likeMapper;
    }


    @Override
    public SimplePost toDomainEntity(SimplePostJPA postJPA) {
        PostId postId=new PostId(postJPA.getId());
        User author = userMapper.toDomainEntity(postJPA.getAuthor());
        List<Comment> comments = postJPA.getComments().stream().map(commentJPA -> commentMapper.toDomainEntity(commentJPA)).collect(Collectors.toList());
        List<Like> likes =postJPA.getLikes().stream().map(likeMapper::toDomainEntity).collect(Collectors.toList());;
        return SimplePost.builder()
                .user(author)
                .comments(comments)
                .likes(likes)
                .content(postJPA.getContent())
                .persistenceDetail(detail -> detail.id(postId).updatedAt(postJPA.getUpdatedAt()).updatedAt(postJPA.getCreatedAt()))
                .build();
    }

    @Override
    public SimplePostJPA toJpaEntity(SimplePost simplePost) {
        SimplePostJPA postJPA=toJpaEntityWithoutParentAssociation(simplePost);
        // set Parent association
        postJPA.getComments().forEach(commentJPA -> commentJPA.setPost(postJPA));
        postJPA.getLikes().forEach(likeJPA -> likeJPA.setPost(postJPA));
        return postJPA;
    }

    public SimplePostJPA toJpaEntityWithoutParentAssociation(SimplePost simplePost){
        List<CommentJPA> jpaComments=simplePost.getComments().stream().map(comment -> commentMapper.toJpaEntity(comment)).toList();
        UserJPA author=userMapper.toJpaEntity(simplePost.getAuthor());
        List<LikeJPA> jpaLikes=simplePost.getLikes().stream().map(likeMapper::toJpaEntity).collect(Collectors.toList());
        UUID id= simplePost.getId()!=null?simplePost.getId().value():null;

        return SimplePostJPA.builder()
                .id(id)
                .author(author)
                .comments(jpaComments)
                .likes(jpaLikes)
                .content(simplePost.getContent())
                .createdAt(simplePost.getCreatedAt())
                .updatedAt(simplePost.getUpdatedAt())
                .build();
    }
}
