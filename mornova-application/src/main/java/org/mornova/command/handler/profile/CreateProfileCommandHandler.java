package org.mornova.command.handler.profile;

import org.mornova.command.command.profile.CreateProfileCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.domain.core.model.entities.Profile;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.BaseId;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.domain.core.repository.ProfileRepository;
import org.mornova.domain.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        User owner=userRepository.findOrThrow(UserId.fromString(command.getId()));
        Profile profile=profileRepository.save(Profile.builder()
                .owner(owner)
                .build());
        String profileId= Optional.ofNullable(profile.getId()).map(BaseId::mapToStringOrThrow).orElse(null);
        command.setId(profileId);
    }
}
