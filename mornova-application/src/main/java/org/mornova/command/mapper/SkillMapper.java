package org.mornova.command.mapper;

import org.mapstruct.Mapper;
import org.mornova.command.command.profile.AddSkillCommand;
import org.mornova.domain.core.model.entities.Skill;
import org.mornova.domain.core.model.objectValue.refrerences.SkillLevel;

@Mapper(componentModel = "spring")
public interface SkillMapper{

    default Skill toDomain(AddSkillCommand command){
       return Skill
                .builder()
                .level(SkillLevel.valueOf(command.getLevel()))
                .name(command.getName())
                .build();
    }

}
