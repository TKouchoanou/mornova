package org.mornova.repository.jpa.queryProjector;

import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.query.projector.PostProjector;
import org.mornova.query.query.UserPostQuery;
import org.mornova.query.result.ProjectPostQR;
import org.mornova.query.result.SimplePostQR;
import org.mornova.repository.jpa.entity.ProjectPostJPA;
import org.mornova.repository.jpa.entity.SimplePostJPA;
import org.mornova.repository.jpa.mapper.queryResult.QRGenericMapper;
import org.mornova.repository.jpa.repository.ProjectPostJPARepository;
import org.mornova.repository.jpa.repository.SimplePostJPARepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.mornova.utils.CollectionUtils.isNullOrEmpty;

@Repository
public class PostProjectorImpl implements PostProjector {
    ProjectPostJPARepository projectPostJPARepository;

    SimplePostJPARepository simplePostJPARepository;

    QRGenericMapper genericMapper;

    public PostProjectorImpl(ProjectPostJPARepository projectPostJPARepository, SimplePostJPARepository simplePostJPARepository, QRGenericMapper genericMapper) {
        this.projectPostJPARepository = projectPostJPARepository;
        this.simplePostJPARepository = simplePostJPARepository;
        this.genericMapper = genericMapper;
    }


    @Override
    public List<ProjectPostQR> findProjectPost(UserPostQuery post) {
        List<String> userIds = post.getUserIds();
        List<String> postsId = post.getPostsId();

        if(isNullOrEmpty(userIds) && isNullOrEmpty(postsId)){
            return projectPostJPARepository.findAll()
                    .stream().map(this::convertToDTO)
                    .collect(Collectors.toList());
        }
        List<UserId> authorIdList = userIds != null ? userIds.stream().map(UserId::valueOf).toList() : null;
        List<UUID> postsIdUUID = postsId != null ? postsId.stream().map(UUID::fromString).toList() : null;

        if(!isNullOrEmpty(authorIdList) && !isNullOrEmpty(postsIdUUID)){
            return  projectPostJPARepository.findByAuthorIdInAndIdIn(authorIdList, postsIdUUID)
                    .stream().map(this::convertToDTO)
                    .collect(Collectors.toList());

        }else if(!isNullOrEmpty(authorIdList)){
            return projectPostJPARepository.findByAuthorIdIn(authorIdList)
                    .stream().map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        return projectPostJPARepository.findByIdIn(postsIdUUID)
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplePostQR> findSimplePost(UserPostQuery post) {
        List<String> userIds = post.getUserIds();
        List<String> postsId = post.getPostsId();

        if(isNullOrEmpty(userIds) && isNullOrEmpty(postsId)){
            return simplePostJPARepository.findAll()
                    .stream().map(this::convertToDTO)
                    .collect(Collectors.toList());
        }
        List<UserId> authorIdList = userIds != null ? userIds.stream().map(UserId::valueOf).toList() : null;
        List<UUID> postsIdUUID = postsId != null ? postsId.stream().map(UUID::fromString).toList() : null;

        if(!isNullOrEmpty(userIds) && !isNullOrEmpty(postsId)){
            return  simplePostJPARepository.findByAuthorIdInAndIdIn(authorIdList, postsIdUUID)
                    .stream().map(this::convertToDTO)
                    .collect(Collectors.toList());

        }else if(isNullOrEmpty(authorIdList)){
            return simplePostJPARepository.findByAuthorIdIn(authorIdList)
                    .stream().map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        return simplePostJPARepository.findByIdIn(postsIdUUID)
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public SimplePostQR convertToDTO(SimplePostJPA simplePostJPA) {
        return this.genericMapper.convertSimplePostToDTO(simplePostJPA);
    }

    public ProjectPostQR convertToDTO(ProjectPostJPA projectPostJPA) {
        return genericMapper.convertProjectPostToDTO(projectPostJPA);
    }

}
