package org.mornova.command.mapper.profile;

import org.mapstruct.Mapper;
import org.mornova.command.command.profile.skill.ProfileSkillCommand;
import org.mornova.domain.core.model.entities.BasedEntity;
import org.mornova.domain.core.model.entities.profile.Skill;
import org.mornova.domain.core.model.objectValue.ids.SkillId;
import org.mornova.domain.core.model.objectValue.refrerences.SkillLevel;

import java.util.function.Consumer;

@Mapper(componentModel = "spring")
public interface SkillMapper{

    default Skill toDomain(ProfileSkillCommand command){
       return mapCommonPart(command)
                .build();
    }
    default Skill toDomain(ProfileSkillCommand command, Consumer<BasedEntity.PersistenceDetailBuilder<SkillId>> consumer){
        return mapCommonPart(command)
                .persistenceDetail(consumer)
                .build();
    }

    private Skill.Builder mapCommonPart(ProfileSkillCommand command){
        return Skill
                .builder()
                .level(SkillLevel.valueOf(command.getLevel()))
                .name(command.getName());
    }

}
