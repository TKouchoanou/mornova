package org.mornova.domain.core.model.objectValue.ids;

import java.util.UUID;

public record MediaId (UUID value) implements BaseId<UUID>{
    public static MediaId valueOf(String uuid){
        return new MediaId(BaseId.uuidFromString(uuid));
    }
}
