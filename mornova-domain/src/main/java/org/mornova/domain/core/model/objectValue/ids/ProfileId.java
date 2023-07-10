package org.mornova.domain.core.model.objectValue.ids;

import java.util.UUID;

public class ProfileId implements BaseId<UUID> {
    UUID id;

    public ProfileId() {
    }

  public   ProfileId(UUID value){
        this.id=value;
    }

    @Override
    public UUID value() {
        return id;
    }

    public UUID getId() {
        return id;
    }
}
