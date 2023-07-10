package org.mornova.repository.jpa;

import org.mornova.domain.core.model.entities.SimplePost;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.domain.core.repository.SimplePostRepository;
import org.mornova.repository.jpa.entity.SimplePostJPA;
import org.mornova.repository.jpa.mapper.SimplePostMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class SimplePostRepositoryImpl implements SimplePostRepository {

    private static final SimplePost DEFAULT_SIMPLE = new SimplePost() ;
    SimplePostJPARepository simplePostJPARepository;
    SimplePostMapper simplePostMapper;

    public SimplePostRepositoryImpl(SimplePostJPARepository simplePostJPARepository, SimplePostMapper simplePostMapper) {
        this.simplePostJPARepository = simplePostJPARepository;
        this.simplePostMapper = simplePostMapper;
    }

    @Override
    public SimplePost save(SimplePost simplePost) {
        SimplePostJPA simplePostJPA=simplePostMapper.toJpaEntity(simplePost);
        return simplePostMapper.toDomainEntity(simplePostJPARepository.save(simplePostJPA));
    }

    @Override
    public Optional<SimplePost> find(PostId postId) {
        return simplePostJPARepository.findById(postId.value()).map(simplePostMapper::toDomainEntity);
    }

    @Override
    public SimplePost delete(PostId postId) {
       Optional<SimplePost> optionalSimplePost= find(postId);
       optionalSimplePost.ifPresent(post->simplePostJPARepository.deleteById(postId.value()));
       return optionalSimplePost.orElse(DEFAULT_SIMPLE);
    }
}
