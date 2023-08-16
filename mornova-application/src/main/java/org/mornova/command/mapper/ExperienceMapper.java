package org.mornova.command.mapper;

import org.mapstruct.Mapper;
import org.mornova.command.command.profile.AddExperienceCommand;
import org.mornova.domain.core.model.entities.Experience;
@Mapper(componentModel = "spring")
public interface ExperienceMapper{

    default Experience toDomain(AddExperienceCommand command){
      return   Experience
                .builder()
                .company(command.getCompany())
                .yearsOfExperience(command.getYearsOfExperience())
                .title(command.getTitle())
                .build();
    }
}
