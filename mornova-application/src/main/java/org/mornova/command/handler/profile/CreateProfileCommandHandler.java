package org.mornova.command.handler.profile;

import org.mornova.command.command.profile.CreateProfileCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.domain.core.model.entities.profile.Profile;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.domain.core.repository.ProfileRepository;
import org.mornova.domain.core.repository.UserRepository;
import org.mornova.exceptions.BusinessGenericException;
import org.mornova.exceptions.codes.BusinessErrorCode;
import org.springframework.stereotype.Service;
@Service
public class CreateProfileCommandHandler implements CommandHandler<CreateProfileCommand> {
    ProfileRepository profileRepository;
    UserRepository userRepository;

    public CreateProfileCommandHandler(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void handle(CreateProfileCommand command, HandlingContext handlingContext) {
        Profile profile=profileRepository.save(map(command));

        if(profile.getId()!=null){
            command.setId(profile.getId().mapToString());
        }else {
            throw new BusinessGenericException(BusinessErrorCode.EMPTY_ID_AFTER_PERSISTENCE);
        }

    }

    Profile map(CreateProfileCommand command){
        User owner=userRepository.findOrThrow(UserId.valueOf(command.getId()));
        return Profile.builder()
                .owner(owner)
                .build();
    }
}
