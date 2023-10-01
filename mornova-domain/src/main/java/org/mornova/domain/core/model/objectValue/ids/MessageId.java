package org.mornova.domain.core.model.objectValue.ids;

public record MessageId(Long value) implements BaseId<Long> {
    public static MessageId valueOf(String uuid){
        return new MessageId(BaseId.longFromString(uuid));
    }
}
