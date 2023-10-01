package org.mornova.domain.core.model.entities.profile;

import lombok.Getter;
import org.mornova.domain.core.model.entities.BasedEntity;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.ExperienceId;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.domain.core.model.objectValue.ids.SkillId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    public void updateExperience(Experience experience) {
        findExperienceById(experience.getId(),false).ifPresentOrElse(exp->experiences.remove(exp),()->{throw new IllegalArgumentException();});
        experiences.add(experience);
    }
    public void removeExperience(Experience experience) {
        experiences.removeIf(exp->exp.getId().equals(experience.getId()));
    }
    public void addSkill(Skill skill) {
        skills.add(skill);
    }
    public void updateSkill(Skill skill) {
       findSkillById(skill.getId(),false)
               .ifPresentOrElse(skl->skills.remove(skl),()->{throw new IllegalArgumentException();});
        skills.add(skill);
    }

    public void removeSkill(Skill skill) {
        skills.removeIf(skl->skl.getId().equals(skill.getId()));
    }

    public Optional<Skill> findSkillById(SkillId skillId) {
        return findSkillById(skillId,true);
    }


     Optional<Skill> findSkillById(SkillId skillId, boolean makeCopy) {
        return skills.stream()
                .filter(skill -> skill.getId().equals(skillId))
                .findFirst()
                .map(skill -> makeCopy ? skill.copy() : skill);
    }
    public Optional<Experience> findExperienceById( ExperienceId experienceId) {
        return findExperienceById(experienceId,true);
    }
    Optional<Experience> findExperienceById( ExperienceId experienceId,boolean makeCopy) {
        return experiences.stream()
                .filter(experience -> experience.getId().equals(experienceId))
                .findFirst()
                .map(experience -> makeCopy?experience.copy():experience);
    }

    public List<Skill> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    public List<Experience> getExperiences() {
        return Collections.unmodifiableList(experiences);
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
