package org.mornova.command.handler.profile;

import org.mornova.command.command.profile.AddSkillCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.mapper.SkillMapper;
import org.mornova.domain.core.model.entities.Profile;
import org.mornova.domain.core.model.entities.Skill;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.domain.core.model.objectValue.refrerences.SkillLevel;
import org.mornova.domain.core.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class AddSkillCommandHandler implements CommandHandler<AddSkillCommand> {

    ProfileRepository profileRepository;
    SkillMapper skillMapper;

    public AddSkillCommandHandler(ProfileRepository profileRepository, SkillMapper skillMapper) {
        this.profileRepository = profileRepository;
        this.skillMapper = skillMapper;
    }


    @Override
    public void handle(AddSkillCommand command, HandlingContext handlingContext) {
        Profile profile=profileRepository.findOrThrow(ProfileId.fromString(command.getProfileId()));
        profile.addSkill(Skill
                .builder()
                .level(SkillLevel.valueOf(command.getLevel()))
                .name(command.getName())
                .build());
        profileRepository.save(profile);
    }
}
/*
 Skill skill= Skill
                .builder()
                .level(SkillLevel.valueOf(command.getLevel()))
                .name(command.getName())
                .build();
*/
