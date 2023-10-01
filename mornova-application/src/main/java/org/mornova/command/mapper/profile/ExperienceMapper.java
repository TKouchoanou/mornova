package org.mornova.command.mapper.profile;

import org.mapstruct.Mapper;
import org.mornova.command.command.profile.experience.ProfileExperienceCommand;
import org.mornova.domain.core.model.entities.BasedEntity;
import org.mornova.domain.core.model.entities.profile.Experience;
import org.mornova.domain.core.model.objectValue.ids.ExperienceId;

import java.util.function.Consumer;

@Mapper(componentModel = "spring")
public interface ExperienceMapper{

    default Experience toDomain(ProfileExperienceCommand command){
      return   Experience
                .builder()
                .company(command.getCompany())
                .yearsOfExperience(command.getYearsOfExperience())
                .title(command.getTitle())
                .build();
    }
    default Experience toDomain(ProfileExperienceCommand command, Consumer<BasedEntity.PersistenceDetailBuilder<ExperienceId>> consumer ){
        return   Experience
                .builder()
                .company(command.getCompany())
                .yearsOfExperience(command.getYearsOfExperience())
                .title(command.getTitle())
                .persistenceDetail(consumer)
                .build();
    }
}
