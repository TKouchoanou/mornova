package org.mornova.domain.core.model.entities.post;

import lombok.Getter;
import org.mornova.domain.core.model.entities.BasedEntity;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.LikeId;

import java.util.function.Consumer;
@Getter
public class Like extends BasedEntity<LikeId> {

    LikeId id;

    private User liker;
    public Like() {
    }
    public Like(User liker) {
        this.liker = liker;
    }

    private Like(Builder builder) {
        liker = builder.liker;
        if(builder.likePD!=null && builder.likePD.id()!=null){
            id=builder.likePD.id();
            this.initPersistenceDetail(builder.likePD);
        }
    }
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private User liker;
        PersistenceDetailBuilder.PersistenceDetail<LikeId> likePD;

        private Builder() {
        }



        public Builder liker(User val) {
            liker = val;
            return this;
        }
        public Builder persistenceDetail(Consumer<PersistenceDetailBuilder<LikeId>> consumer ) {
            PersistenceDetailBuilder<LikeId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            likePD=persistenceDetailBuilder.build();
            return this;
        }
        public Like build() {
            if(liker==null)
            throw new RuntimeException(" post liker cannot be null");
            return new Like(this);
        }
    }
}
