package org.mornova.repository.jpa.queryProjector;

import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.query.projector.ProfileProjector;
import org.mornova.query.query.UserProfileQuery;
import org.mornova.query.result.ProfileQR;
import org.mornova.repository.jpa.entity.ProfileJPA;
import org.mornova.repository.jpa.mapper.queryResult.QRGenericMapper;
import org.mornova.repository.jpa.repository.ProfileJPARepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProfileProjectorImpl implements ProfileProjector {

   ProfileJPARepository profileJPARepository;

   QRGenericMapper genericMapper;

    public ProfileProjectorImpl(ProfileJPARepository profileJPARepository, QRGenericMapper genericMapper) {
        this.profileJPARepository = profileJPARepository;
        this.genericMapper = genericMapper;
    }


    @Override
    public List<ProfileQR> findAll(UserProfileQuery query) {
        List<UserId> ownerIds = query.getOwnerIds()!=null? query.getOwnerIds().stream().map(UserId::valueOf).toList(): Collections.emptyList();
        if (!ownerIds.isEmpty()){
          return   profileJPARepository.findWithSkillsAndExperiencesByOwnerIdIn(ownerIds)
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }
        return profileJPARepository.findAllWithSkillsAndExperiences()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProfileQR convertToDTO(ProfileJPA profileJPA) {
      return genericMapper.convertProfileToDTO(profileJPA);
    }
}
