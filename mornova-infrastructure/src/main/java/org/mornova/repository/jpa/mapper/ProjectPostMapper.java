package org.mornova.repository.jpa.mapper;

import org.mornova.domain.core.model.entities.Comment;
import org.mornova.domain.core.model.entities.Like;
import org.mornova.domain.core.model.entities.Media;
import org.mornova.domain.core.model.entities.ProjectPost;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.repository.jpa.entity.CommentJPA;
import org.mornova.repository.jpa.entity.LikeJPA;
import org.mornova.repository.jpa.entity.MediaJPA;
import org.mornova.repository.jpa.entity.ProjectPostJPA;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Mapper
public class ProjectPostMapper implements DomainToJpaMapper<ProjectPost, ProjectPostJPA>{
    CommentMapper commentMapper;
    UserMapper userMapper;
    LikeMapper likeMapper;
    MediaMapper mediaMapper;

    public ProjectPostMapper(CommentMapper commentMapper, UserMapper userMapper, LikeMapper likeMapper, MediaMapper mediaMapper) {
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
        this.likeMapper = likeMapper;
        this.mediaMapper = mediaMapper;
    }


    @Override
    public ProjectPost toDomainEntity(ProjectPostJPA projectPostJPA) {
        if (projectPostJPA == null) {
            return null;
        }
        List<Like> likes=null;

        if(projectPostJPA.getLikes()!=null) {
         likes= projectPostJPA.getLikes().stream().map(likeMapper::toDomainEntity).collect(Collectors.toList());
        }

        List<Comment> comments=null;
     if(projectPostJPA.getComments()!=null) {
         comments = projectPostJPA.getComments().stream().map(commentMapper::toDomainEntity).collect(Collectors.toList());
     }

     List<Media> medias=null;
     if(projectPostJPA.getMediaList()!=null) {
         medias = projectPostJPA.getMediaList().stream().map(mediaMapper::toDomainEntity).collect(Collectors.toList());
     }
        PostId postId= new PostId(projectPostJPA.getId());

        ProjectPost.Builder builder = ProjectPost.Builder.builder()
                .author(userMapper.toDomainEntity(projectPostJPA.getAuthor()))
                .comments(comments)
                .likes(likes)
                .projectName(projectPostJPA.getProjectName())
                .description(projectPostJPA.getDescription())
                .status(projectPostJPA.getStatus())
                .type(projectPostJPA.getType())
                .amountOfFunding(projectPostJPA.getAmountOfFunding())
                .availableCapital(projectPostJPA.getAvailableCapital())
                .launchDate(projectPostJPA.getLaunchDate())
                .mediaList(medias)
                .sector(projectPostJPA.getSector())
                .subSector(projectPostJPA.getSubSector())
                .persistenceDetail(pdBuilder -> pdBuilder.id(postId)
                        .createdAt(projectPostJPA.getCreatedAt())
                        .updatedAt(projectPostJPA.getUpdatedAt())
                );

        return builder.build();
    }

    @Override
    public ProjectPostJPA toJpaEntity(ProjectPost domainEntity) {
      final   ProjectPostJPA projectPostJPA=toJpaEntityWithoutParentAssociation(domainEntity);
        //set parent association
        if(projectPostJPA.getMediaList()!=null)
        projectPostJPA.getMediaList().forEach(mediaJPA -> mediaJPA.setPost(projectPostJPA));
        if(projectPostJPA.getComments()!=null)
        projectPostJPA.getComments().forEach(commentJPA -> commentJPA.setPost(projectPostJPA));
        if(projectPostJPA.getLikes()!=null)
        projectPostJPA.getLikes().forEach(likeJPA -> likeJPA.setPost(projectPostJPA));

        return  projectPostJPA;
    }
   public ProjectPostJPA toJpaEntityWithoutParentAssociation(ProjectPost post){
       if (post == null) {
           return null;
       }

       UUID id = post.getId() != null ? post.getId().value() : null;

       List<MediaJPA> mediaList = null;
       if (post.getMediaList() != null) {
           mediaList = post.getMediaList().stream()
                   .map(mediaMapper::toJpaEntity)
                   .collect(Collectors.toList());
       }

       List<CommentJPA> commentJPAList = null;
       if (post.getComments() != null) {
           commentJPAList = post.getComments().stream()
                   .map(commentMapper::toJpaEntity)
                   .collect(Collectors.toList());
       }

       List<LikeJPA> likeJPAList = null;
       if (post.getLikes() != null) {
           likeJPAList = post.getLikes().stream()
                   .map(likeMapper::toJpaEntity)
                   .collect(Collectors.toList());
       }

       return ProjectPostJPA.builder()
               .author(userMapper.toJpaEntity(post.getAuthor()))
               .comments(commentJPAList)
               .likes(likeJPAList)
               .projectName(post.getProjectName())
               .description(post.getDescription())
               .status(post.getStatus())
               .type(post.getType())
               .amountOfFunding(post.getAmountOfFunding())
               .availableCapital(post.getAvailableCapital())
               .launchDate(post.getLaunchDate())
               .sector(post.getSector())
               .subSector(post.getSubSector())
               .mediaList(mediaList)
               .createdAt(post.getCreatedAt())
               .updatedAt(post.getUpdatedAt())
               .id(id)
               .build()
               ;
   }

}
