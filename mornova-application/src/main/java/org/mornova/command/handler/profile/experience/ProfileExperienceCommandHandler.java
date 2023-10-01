package org.mornova.command.handler.profile.experience;

import org.mornova.command.command.profile.experience.ProfileExperienceCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.mapper.profile.ExperienceMapper;
import org.mornova.domain.core.model.entities.profile.Experience;
import org.mornova.domain.core.model.entities.profile.Profile;
import org.mornova.domain.core.model.objectValue.ids.ExperienceId;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.domain.core.repository.ProfileRepository;
import org.mornova.exceptions.BusinessGenericException;
import org.mornova.exceptions.codes.BusinessErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileExperienceCommandHandler implements CommandHandler<ProfileExperienceCommand> {
    ExperienceMapper experienceMapper;
    ProfileRepository profileRepository;

    public ProfileExperienceCommandHandler(ExperienceMapper experienceMapper, ProfileRepository profileRepository) {
        this.experienceMapper = experienceMapper;
        this.profileRepository = profileRepository;
    }


    @Override
    public void handle(ProfileExperienceCommand command, HandlingContext handlingContext) {
        Profile profile=profileRepository.findOrThrow(ProfileId.valueOf(command.getProfileId()));
        Experience experience= updateProfileExperiences(profile,command);
        command.setId(experience.getId().mapToString());
    }

    Experience updateProfileExperiences(Profile profile, ProfileExperienceCommand command){
        //use strategy pattern if this code become too long ex: ActionProcessor.process(..)
        ExperienceId id = ExperienceId.valueOf(command.getId());
        Experience experience =profile.findExperienceById(id).orElse(null);
        switch (command.getActionType()){
            case ADD -> {
                List<Experience> oldExperiences = profile.getExperiences();
                profile.addExperience(experienceMapper.toDomain(command));
                List<Experience> newExperiences = profileRepository.save(profile).getExperiences();
                return newExperiences.stream().filter(exp->!oldExperiences.contains(exp)).findAny().orElseThrow();
            }
            case UPDATE -> {
                assertNotNullExperience(experience);
                assert experience != null;
                Experience updatedExperience=experienceMapper.toDomain(command,experience.persistenceDetailBuilderConsumer());
                profile.updateExperience(experience);
                profileRepository.save(profile);
                return  updatedExperience;
            }
            case DELETE ->{
                assertNotNullExperience(experience);
                assert experience != null;
                profile.removeExperience(experience);
                profileRepository.save(profile);
                return experience;
            }
        }
        throw new  BusinessGenericException(BusinessErrorCode.NOT_SUPPORTED_OR_EMPTY_ACTION);
    }

    public void assertNotNullExperience(Experience experience){
        if(experience==null)
            throw new BusinessGenericException(BusinessErrorCode.REQUIRED_ENTITY_NOT_FOUND_TO_PERFORM_ACTION);
    }

}
