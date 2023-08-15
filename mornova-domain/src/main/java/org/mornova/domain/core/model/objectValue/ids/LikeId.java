package org.mornova.domain.core.model.objectValue.ids;

public class LikeId implements BaseId<Long> {

    Long id;
   public LikeId(Long value){
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
