package org.mornova.command.handler.profile;

import org.mornova.command.command.profile.AddExperienceCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.mapper.ExperienceMapper;
import org.mornova.domain.core.model.entities.Profile;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.domain.core.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class AddExperienceCommandHandler implements CommandHandler<AddExperienceCommand> {
    ExperienceMapper experienceMapper;
    ProfileRepository profileRepository;

    public AddExperienceCommandHandler(ExperienceMapper experienceMapper, ProfileRepository profileRepository) {
        this.experienceMapper = experienceMapper;
        this.profileRepository = profileRepository;
    }


    @Override
    public void handle(AddExperienceCommand command, HandlingContext handlingContext) {
        Profile profile=profileRepository.findOrThrow(ProfileId.fromString(command.getProfileId()));
        profile.addExperience(experienceMapper.toDomain(command));
        profileRepository.save(profile);
    }
}
/*
Experience experience= Experience
                .builder()
                .company(command.getCompany())
                .yearsOfExperience(command.getYearsOfExperience())
                .title(command.getTitle())
                .build();

*/
