package org.mornova.domain.core.model.objectValue.ids;

public class RoleId implements BaseId<Long>{

   private Long id;

    public RoleId() {
    }

   public RoleId(Long value){
        this.id=value;
    }

    @Override
    public Long value() {
        return id;
    }

    public Long getId() {
        return id;
    }
    public static RoleId valueOf(String uuid){
        return new RoleId(BaseId.longFromString(uuid));
    }
}
