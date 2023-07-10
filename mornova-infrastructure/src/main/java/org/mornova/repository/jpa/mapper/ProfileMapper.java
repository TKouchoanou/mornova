package org.mornova.repository.jpa.mapper;

import org.mornova.domain.core.model.entities.Experience;
import org.mornova.domain.core.model.entities.Profile;
import org.mornova.domain.core.model.entities.Skill;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.repository.jpa.entity.ExperienceJPA;
import org.mornova.repository.jpa.entity.ProfileJPA;
import org.mornova.repository.jpa.entity.SkillJPA;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Mapper
public class ProfileMapper implements DomainToJpaMapper<Profile, ProfileJPA>{
    private final ExperienceMapper experienceMapper;
    private final SkillMapper skillMapper;
    private  final UserMapper userMapper;


    public ProfileMapper(ExperienceMapper experienceMapper, SkillMapper skillMapper, UserMapper userMapper) {
        this.experienceMapper = experienceMapper;
        this.skillMapper = skillMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Profile toDomainEntity(ProfileJPA jpaEntity) {
        ProfileId profileId=new ProfileId(jpaEntity.getId());

        List<Experience> experiences=jpaEntity.getExperiences().stream()
                .map(experienceMapper::toDomainEntity)
                .collect(Collectors.toList());

        List<Skill> skills=jpaEntity.getSkills().stream()
                .map(skillMapper::toDomainEntity)
                .collect(Collectors.toList());

        return Profile.builder()
                .owner(userMapper.toDomainEntity(jpaEntity.getUser()))
                .experiences(experiences)
                .skills(skills)
                .persistenceDetail(pd->pd.id(profileId)
                        .updatedAt(jpaEntity.getUpdatedAt())
                        .createdAt(jpaEntity.getCreatedAt())
                )
                .build();
    }

    @Override
    public ProfileJPA toJpaEntity(Profile domainEntity) {
      ProfileJPA profileJPA= toJpaEntityWithoutParentAssociation(domainEntity);
      profileJPA.getExperiences().forEach(experienceJPA -> experienceJPA.setProfile(profileJPA));
      profileJPA.getSkills().forEach(skillJPA -> skillJPA.setProfile(profileJPA));
      return profileJPA;
    }
    public ProfileJPA toJpaEntityWithoutParentAssociation(Profile profile) {
        List<ExperienceJPA> experiencesJPA=profile.getExperiences().stream()
                .map(experienceMapper::toJpaEntity)
                .collect(Collectors.toList());

        List<SkillJPA> skillsJPA=profile.getSkills().stream()
                .map(skillMapper::toJpaEntity)
                .collect(Collectors.toList());
        UUID id= profile.getId()!=null?profile.getId().value():null;

        return ProfileJPA.builder()
                .user(userMapper.toJpaEntity(profile.getOwner()))
                .experiences(experiencesJPA)
                .skills(skillsJPA)
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .id(id)
                .build();
    }


}
