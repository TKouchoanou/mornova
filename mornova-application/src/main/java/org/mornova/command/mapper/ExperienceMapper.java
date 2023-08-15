package org.mornova.command.mapper;

import org.mapstruct.Mapper;
import org.mornova.command.command.profile.AddExperienceCommand;
import org.mornova.command.mapper.mapStruct.ToNewDomainEntity;
import org.mornova.domain.core.model.entities.Experience;
@Mapper
public interface ExperienceMapper{
    @ToNewDomainEntity
    Experience toDomain(AddExperienceCommand command);
}
