package org.mornova.domain.core.model.entities;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class BasedEntity <ID extends Serializable>{
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

    public static class PersistenceDetailBuilder <ID> {
        record PersistenceDetail<ID>(   ID id,
                                        LocalDateTime createdAt,
                                        LocalDateTime updatedAt){}
        ID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        PersistenceDetailBuilder(){}
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

        protected PersistenceDetail <ID>build(){
            return new PersistenceDetail<>(id,createdAt,updatedAt);
        }

    }
}
