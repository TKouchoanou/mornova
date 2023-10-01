package org.mornova.domain.core.model.entities;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.Consumer;

@Getter
public abstract class BasedEntity <ID extends Serializable>{
   protected LocalDateTime createdAt;
   protected LocalDateTime updatedAt;

    public BasedEntity() {
        this.createdAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
    }
    public void initPersistenceDetail(PersistenceDetailBuilder.PersistenceDetail<ID> persistenceDetail){
        this.createdAt=persistenceDetail.createdAt;
        this.updatedAt=persistenceDetail.updatedAt;
    }
    public abstract ID getId();
    public static class PersistenceDetailBuilder <ID> {
        public record PersistenceDetail<ID>(ID id,
                                            LocalDateTime createdAt,
                                            LocalDateTime updatedAt){}
        ID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public PersistenceDetailBuilder(){}
        public PersistenceDetailBuilder<ID> createdAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public PersistenceDetailBuilder<ID> updatedAt(LocalDateTime val) {
            updatedAt = val;
            return this;
        }

        public PersistenceDetailBuilder<ID> id(ID id){
            this.id=id;
            return this;
        }

        public PersistenceDetail <ID>build(){
            return new PersistenceDetail<>(id,createdAt,updatedAt);
        }

    }
   public Consumer<PersistenceDetailBuilder<ID>> persistenceDetailBuilderConsumer(){
        return  (PersistenceDetailBuilder<ID> persistenceDetailBuilder) ->persistenceDetailBuilder.createdAt(createdAt).updatedAt(updatedAt).id(getId());
    }
}
