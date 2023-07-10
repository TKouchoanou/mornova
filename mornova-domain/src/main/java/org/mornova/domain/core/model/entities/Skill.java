package org.mornova.domain.core.model.entities;

import lombok.Getter;
import org.mornova.domain.core.model.objectValue.ids.SkillId;
import org.mornova.domain.core.model.objectValue.refrerences.SkillLevel;

;import java.util.function.Consumer;

@Getter
public class Skill extends BasedEntity<SkillId> {

    private SkillId id;
    private String name;
    private SkillLevel level;


    private Skill(Builder builder) {
        name = builder.name;
        level = builder.level;
        if(builder.skillPD!=null){
            id=builder.skillPD.id();
            this.initPersistenceDetail(builder.skillPD);
        }
    }



    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;
        private SkillLevel level;
        PersistenceDetailBuilder.PersistenceDetail<SkillId> skillPD;

        private Builder() {
        }



        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder level(SkillLevel val) {
            level = val;
            return this;
        }

        public Skill build() {
            return new Skill(this);
        }

        public  Builder  persistenceDetail(Consumer<PersistenceDetailBuilder<SkillId>> consumer ) {
            PersistenceDetailBuilder<SkillId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            skillPD=persistenceDetailBuilder.build();
            return this;
        }
    }
}
