package org.mornova.repository.jpa.repository;

import org.mornova.domain.core.model.entities.post.ProjectPost;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.domain.core.repository.ProjectPostRepository;
import org.mornova.repository.jpa.entity.ProjectPostJPA;
import org.mornova.repository.jpa.mapper.domain.ProjectPostMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class ProjectPostRepositoryImpl implements ProjectPostRepository {

    private static final ProjectPost DEFAULT_PROJECT = new ProjectPost();
    ProjectPostJPARepository projectPostJPARepository;
   ProjectPostMapper projectPostMapper;

    public ProjectPostRepositoryImpl(ProjectPostJPARepository projectPostJPARepository, ProjectPostMapper projectPostMapper) {
        this.projectPostJPARepository = projectPostJPARepository;
        this.projectPostMapper = projectPostMapper;
    }


    @Override
    public ProjectPost save(ProjectPost entity) {
        ProjectPostJPA postJPA=projectPostMapper.toJpaEntity(entity);
       postJPA =  projectPostJPARepository.save(postJPA);
        return projectPostMapper.toDomainEntity(projectPostJPARepository.findWithAllAssociationById(postJPA.getId()));
    }

    @Override
    public Optional<ProjectPost> find(PostId postId) {
        return projectPostJPARepository.findById(postId.value()).map(postJPA ->  projectPostMapper.toDomainEntity(postJPA));
    }

    @Override
    public ProjectPost delete(PostId postId) {
        Optional<ProjectPost> optionalProjectPost=find(postId);
        optionalProjectPost.ifPresent(post->projectPostJPARepository.deleteById(postId.value()));
        return  optionalProjectPost.orElse(DEFAULT_PROJECT);
    }
}
