package org.mornova.domain.core.model.entities;

import lombok.Getter;
import org.mornova.domain.core.model.objectValue.ids.RoleId;

import java.util.function.Consumer;

@Getter
public class Role extends BasedEntity<RoleId>{

    private RoleId id;

    private String name;

    private Role(Builder builder) {
        name = builder.name;
        if(builder.rolePD!=null){
            id=builder.rolePD.id();
            this.initPersistenceDetail(builder.rolePD);
        }
    }
    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private String name;
        PersistenceDetailBuilder.PersistenceDetail<RoleId> rolePD;

        private Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }
        public Builder persistenceDetail(Consumer<PersistenceDetailBuilder<RoleId>> consumer ) {
            PersistenceDetailBuilder<RoleId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            rolePD=persistenceDetailBuilder.build();
            return this;
        }
        public Role build() {
            return new Role(this);
        }
    }
}
