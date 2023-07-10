package org.mornova.domain.core.model.entities;

import lombok.Getter;
import org.mornova.domain.core.model.objectValue.ids.ExperienceId;

import java.util.function.Consumer;

@Getter
public class Experience extends BasedEntity<ExperienceId> {

    ExperienceId id;
    private String title;
    private String company;
    private int yearsOfExperience;
    public Experience(String title, String company, int yearsOfExperience) {
        this.title = title;
        this.company = company;
        this.yearsOfExperience = yearsOfExperience;
    }

    private Experience(Builder builder) {
        title = builder.title;
        company = builder.company;
        yearsOfExperience = builder.yearsOfExperience;
        if(builder.experiencePD!=null){
            id=builder.experiencePD.id();
            this.initPersistenceDetail(builder.experiencePD);
        }
    }

    public static Experience.Builder builder() {
        return new Experience.Builder();
    }
    public static final class Builder {
        private String title;
        private String company;
        private int yearsOfExperience;
        PersistenceDetailBuilder.PersistenceDetail<ExperienceId> experiencePD;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder company(String val) {
            company = val;
            return this;
        }

        public Builder yearsOfExperience(int val) {
            yearsOfExperience = val;
            return this;
        }
        public Builder persistenceDetail(Consumer<PersistenceDetailBuilder<ExperienceId>> consumer ) {
            PersistenceDetailBuilder<ExperienceId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            experiencePD=persistenceDetailBuilder.build();
            return this;
        }

        public Experience build() {
            return new Experience(this);
        }
    }
}
