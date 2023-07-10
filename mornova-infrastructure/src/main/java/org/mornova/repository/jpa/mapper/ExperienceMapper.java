package org.mornova.repository.jpa.mapper;

import org.mornova.domain.core.model.entities.Experience;
import org.mornova.domain.core.model.objectValue.ids.ExperienceId;
import org.mornova.repository.jpa.entity.ExperienceJPA;
@Mapper
public class ExperienceMapper implements DomainToJpaMapper<Experience, ExperienceJPA>{
    @Override
    public Experience toDomainEntity(ExperienceJPA experienceJPA) {
        ExperienceId id=new ExperienceId(experienceJPA.getId());
        return Experience.builder()
                .title(experienceJPA.getTitle())
                .company(experienceJPA.getCompany())
                .yearsOfExperience(experienceJPA.getYearsOfExperience())
                .persistenceDetail(pd->pd.id(id)
                        .createdAt(experienceJPA.getCreatedAt())
                        .updatedAt(experienceJPA.getUpdatedAt())
                )
                .build();
    }

    @Override
    public ExperienceJPA toJpaEntity(Experience experience) {
        Long id=experience.getId()!=null?experience.getId().value():null;

        return ExperienceJPA.builder()
                .title(experience.getTitle())
                .company(experience.getCompany())
                .yearsOfExperience(experience.getYearsOfExperience())
                .id(id)
                .createdAt(experience.getCreatedAt())
                .updatedAt(experience.getUpdatedAt())
                .build();
    }
}
