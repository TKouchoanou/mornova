package org.mornova.repository.jpa;

import org.mornova.domain.core.model.entities.Profile;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.domain.core.repository.ProfileRepository;
import org.mornova.repository.jpa.entity.ProfileJPA;
import org.mornova.repository.jpa.mapper.ProfileMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class ProfileRepositoryImpl implements ProfileRepository {
    private static final  Profile DEFAULT_PROFILE = Profile.builder().build() ;
    ProfileJPARepository profileJPARepository;
    ProfileMapper profileMapper;

    public ProfileRepositoryImpl(ProfileJPARepository profileJPARepository, ProfileMapper profileMapper) {
        this.profileJPARepository = profileJPARepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public Profile save(Profile profile) {
        ProfileJPA profileJPA=profileMapper.toJpaEntity(profile);
        return  profileMapper.toDomainEntity(profileJPARepository.save(profileJPA));
    }

    @Override
    public Optional<Profile> find(ProfileId profileId) {
        return profileJPARepository.findById(profileId.value()).map(profileMapper::toDomainEntity);
    }

    @Override
    public Profile delete(ProfileId profileId) {
        Optional<ProfileJPA>  optionalProfile=profileJPARepository.findById(profileId.value());
        profileJPARepository.deleteById(profileId.value());
        return optionalProfile.map(profileJPA -> profileMapper.toDomainEntity(profileJPA)).orElse(DEFAULT_PROFILE);

    }
}
