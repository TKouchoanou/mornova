package org.mornova.command.handler.profile.skill;

import org.mornova.command.command.profile.skill.ProfileSkillCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.mapper.profile.SkillMapper;
import org.mornova.domain.core.model.entities.profile.Profile;
import org.mornova.domain.core.model.entities.profile.Skill;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.domain.core.model.objectValue.ids.SkillId;
import org.mornova.domain.core.repository.ProfileRepository;
import org.mornova.exceptions.BusinessGenericException;
import org.mornova.exceptions.codes.BusinessErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileSkillCommandHandler implements CommandHandler<ProfileSkillCommand> {

    ProfileRepository profileRepository;
    SkillMapper skillMapper;

    public ProfileSkillCommandHandler(ProfileRepository profileRepository, SkillMapper skillMapper) {
        this.profileRepository = profileRepository;
        this.skillMapper = skillMapper;
    }


    @Override
    public void handle(ProfileSkillCommand command, HandlingContext handlingContext) {
        Profile profile = profileRepository.findOrThrow(ProfileId.valueOf(command.getProfileId()));
        Skill skill = updateProfileSkills(profile, command);
        command.setId(skill.getId().mapToString());
    }

    private Skill updateProfileSkills(Profile profile, ProfileSkillCommand command) {
        SkillId id = SkillId.valueOf(command.getId());
        Skill skill = profile.findSkillById(id).orElse(null);

        switch (command.getActionType()) {
            case ADD -> {
                List<Skill> oldSkills = profile.getSkills();
                profile.addSkill(skillMapper.toDomain(command));
                List<Skill> newSkills = profileRepository.save(profile).getSkills();
                return newSkills.stream()
                        .filter(skl -> !oldSkills.contains(skl))
                        .findAny()
                        .orElseThrow();//TODO specified exception to throw
            }
            case UPDATE -> {
                assertNotNullSkill(skill);
                Skill updatedSkill = skillMapper.toDomain(command, skill.persistenceDetailBuilderConsumer());
                profile.updateSkill(updatedSkill);
                profileRepository.save(profile);
                return updatedSkill;
            }
            case DELETE -> {
                assertNotNullSkill(skill);
                profile.removeSkill(skill);
                profileRepository.save(profile);
                return skill;
            }
            default -> throw new BusinessGenericException(BusinessErrorCode.NOT_SUPPORTED_OR_EMPTY_ACTION);
        }
    }


    private void assertNotNullSkill(Skill skill) {
        if (skill == null)
            throw new BusinessGenericException(BusinessErrorCode.REQUIRED_ENTITY_NOT_FOUND_TO_PERFORM_ACTION);
    }
}







