package org.mornova.command.mapper;

import org.mapstruct.Mapper;
import org.mornova.command.command.profile.AddSkillCommand;
import org.mornova.command.mapper.mapStruct.ToNewDomainEntity;
import org.mornova.domain.core.model.entities.Skill;
@Mapper
public interface SkillMapper{
    @ToNewDomainEntity
    Skill toDomain(AddSkillCommand command);
}
