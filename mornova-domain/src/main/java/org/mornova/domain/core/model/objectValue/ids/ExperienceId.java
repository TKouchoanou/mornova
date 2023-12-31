package org.mornova.domain.core.model.objectValue.ids;


public class ExperienceId implements BaseId<Long> {
    Long id;


   public ExperienceId(Long id){
        this.id=id;
    }

    @Override
    public Long value() {
        return id;
    }

    public Long getId() {
        return id;
    }
    public static ExperienceId valueOf(String uuid){
        return new ExperienceId(BaseId.longFromString(uuid));
    }
}
