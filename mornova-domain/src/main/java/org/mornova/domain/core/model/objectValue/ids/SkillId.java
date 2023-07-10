package org.mornova.domain.core.model.objectValue.ids;


public class SkillId implements BaseId<Long> {
    Long id;

    public SkillId() {
    }

    public SkillId(Long value){
        this.id=value;
    }

    @Override
    public Long value() {
        return id;
    }

    public Long getId() {
        return id;
    }
}
