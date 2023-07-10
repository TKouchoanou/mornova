package org.mornova.domain.core.model.objectValue.ids;



import java.util.UUID;

public class PostId implements BaseId<UUID> {
    private UUID id;

    public PostId() {
    }

   public PostId(UUID value){
        this.id=value;
    }

    @Override
    public UUID value() {
        return this.id;
    }

    public UUID getId() {
        return id;
    }
}
