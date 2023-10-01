package org.mornova.repository.jpa.mapper.domain;

import org.mornova.domain.core.model.entities.profile.Skill;
import org.mornova.domain.core.model.objectValue.ids.SkillId;
import org.mornova.repository.jpa.entity.SkillJPA;
@Mapper
public class SkillMapper implements DomainToJpaMapper<Skill, SkillJPA>{

    @Override
    public Skill toDomainEntity(SkillJPA skillJPA) {
        SkillId skillId=new SkillId(skillJPA.getId());
        return Skill.builder()
                .persistenceDetail(pD->pD.id(skillId)
                        .updatedAt(skillJPA.getUpdatedAt())
                        .createdAt(skillJPA.getCreatedAt())
                )
                .name(skillJPA.getName())
                .level(skillJPA.getLevel())
                .build();
    }

    @Override
    public SkillJPA toJpaEntity(Skill skill) {
        SkillJPA.SkillJPABuilder builder = SkillJPA.builder()
                .name(skill.getName())
                .level(skill.getLevel())
                .updatedAt(skill.getUpdatedAt())
                .createdAt(skill.getCreatedAt());

        if (skill.getId() != null) {
            builder.id(skill.getId().value());
        }

        return builder.build();
    }
}
