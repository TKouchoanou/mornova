package org.mornova.repository.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mornova.domain.core.model.entities.profile.Profile;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.domain.core.repository.ProfileRepository;
import org.mornova.repository.jpa.entity.ProfileJPA;
import org.mornova.repository.jpa.mapper.domain.ProfileMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public class ProfileRepositoryImpl implements ProfileRepository {
    private static final  Profile DEFAULT_PROFILE = Profile.builder().build() ;
    ProfileJPARepository profileJPARepository;
    ProfileMapper profileMapper;
    @PersistenceContext
    EntityManager entityManager;

    public ProfileRepositoryImpl(ProfileJPARepository profileJPARepository, ProfileMapper profileMapper, EntityManager entityManager) {
        this.profileJPARepository = profileJPARepository;
        this.profileMapper = profileMapper;
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public Profile save(Profile profile) {
        ProfileJPA profileJPA=profileMapper.toJpaEntity(profile);
        ProfileJPA savedProfileJPA = profileJPARepository.save(profileJPA);
        return  profileMapper.toDomainEntity(savedProfileJPA);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> find(ProfileId profileId) {
        Optional<ProfileJPA> optionalProfileJPA=profileJPARepository.findWithExperienceAndOwnerById(profileId.value());

         return  optionalProfileJPA.isPresent() ?
                 profileJPARepository.findWithSkillById(profileId.value()).map(profileMapper::toDomainEntity)
                 :
                 Optional.empty();
    }

    @Override
    public Profile delete(ProfileId profileId) {
        Optional<ProfileJPA>  optionalProfile=profileJPARepository.findById(profileId.value());
        profileJPARepository.deleteById(profileId.value());
        return optionalProfile.map(profileJPA -> profileMapper.toDomainEntity(profileJPA)).orElse(DEFAULT_PROFILE);

    }
}
