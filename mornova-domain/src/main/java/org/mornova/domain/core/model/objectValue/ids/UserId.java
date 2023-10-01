package org.mornova.domain.core.model.objectValue.ids;

import java.util.UUID;
public class UserId implements BaseId<UUID>  {

    private  UUID id;
    public UserId(UUID value) {
      this.id=value;
    }

    @Override
    public UUID value() {
        return id;
    }

    @Override
    public String toString() {
        return "UserId{" +
                "id=" + id +
                '}';
    }
    public static UserId valueOf(String uuid){
        return new UserId(BaseId.uuidFromString(uuid));
    }
    @Override
    public String mapToString(){
        return id!=null? id.toString():null;
    }

    public UUID getId() {
        return id;
    }
}
