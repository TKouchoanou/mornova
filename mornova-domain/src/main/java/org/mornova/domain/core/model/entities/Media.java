package org.mornova.domain.core.model.entities;

import lombok.Getter;
import org.mornova.domain.core.model.objectValue.ids.MediaId;
import org.mornova.domain.core.model.objectValue.refrerences.MediaType;

import java.util.function.Consumer;

@Getter
public class Media extends BasedEntity<MediaId>{

 private    MediaId id;
private MediaType type;
private byte[] file;
String path;//byte[] if store in database


    public Media() {
    }

    private Media(Builder builder) {
        type = builder.type;
        file = builder.file;
        path = builder.path;

        if(builder.mediaPD!=null){
            id=builder.mediaPD.id();
            this.initPersistenceDetail(builder.mediaPD);
        }
    }

    public static final class Builder {
        private MediaType type;
        private byte[] file;
        private String path;
        PersistenceDetailBuilder.PersistenceDetail<MediaId> mediaPD;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder type(MediaType val) {
            type = val;
            return this;
        }

        public Builder file(byte[] val) {
            file = val;
            return this;
        }

        public Builder path(String val) {
            path = val;
            return this;
        }
        public Builder persistenceDetail(Consumer<PersistenceDetailBuilder<MediaId>> consumer ) {
            PersistenceDetailBuilder<MediaId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            mediaPD=persistenceDetailBuilder.build();
            return this;
        }
        public Media build() {
            return new Media(this);
        }
    }
}
