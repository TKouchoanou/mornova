package org.mornova.domain.core.model.entities;

import lombok.Getter;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter
public class Profile extends BasedEntity<ProfileId> {

    private ProfileId id;
    private User owner;

    private List<Experience> experiences;

    private List<Skill> skills;

    private Profile(Builder builder) {
        owner = builder.owner;
        experiences = builder.experiences;
        skills = builder.skills;
        if(builder.profilePD!=null){
            id=builder.profilePD.id();
            this.initPersistenceDetail(builder.profilePD);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public void addExperience(Experience experience) {
        experiences.add(experience);
    }
    public void addSkill(Skill skill) {
        skills.add(skill);
    }



    public static final class Builder {
        private User owner;
        private List<Experience> experiences=new ArrayList<>();
        private List<Skill> skills=new ArrayList<>();

        private PersistenceDetailBuilder.PersistenceDetail<ProfileId> profilePD;

        private Builder() {
        }

        public Builder owner(User owner) {
            this.owner = owner;
            return this;
        }

        public Builder experiences(List<Experience> val) {
            experiences = val;
            return this;
        }

        public Builder skills(List<Skill> val) {
            skills = val;
            return this;
        }
        public Builder persistenceDetail(Consumer<PersistenceDetailBuilder<ProfileId>> consumer ) {
            PersistenceDetailBuilder<ProfileId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            profilePD=persistenceDetailBuilder.build();
            return this;
        }
        public Profile build() {
            return new Profile(this);
        }
    }
}
