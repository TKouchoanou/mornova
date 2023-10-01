package org.mornova.rest.controller;

import org.mornova.command.CommandManager;
import org.mornova.command.command.profile.experience.ProfileExperienceCommand;
import org.mornova.command.command.profile.skill.ProfileSkillCommand;
import org.mornova.domain.core.model.entities.profile.Profile;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.domain.core.repository.ProfileRepository;
import org.mornova.query.projector.ProfileProjector;
import org.mornova.query.query.UserProfileQuery;
import org.mornova.query.result.ProfileQR;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("profiles")
public class ProfileController {
    ProfileRepository profileRepository;
    DataFaker faker;

    ProfileProjector profileProjector;

    CommandManager commandManager;

    public ProfileController(ProfileRepository profileRepository, DataFaker faker, ProfileProjector profileProjector, CommandManager commandManager) {
        this.profileRepository = profileRepository;
        this.faker = faker;
        this.profileProjector = profileProjector;
        this.commandManager = commandManager;
    }


    @GetMapping("/create")
    public Profile create(){
        Profile profile= faker.fakeProfile(faker.fakeUserPersistedUser());
        return profileRepository.save(profile);
    }
    @PostMapping
    public Profile create(@RequestBody Profile profile){
        return profileRepository.save(profile);
    }
    @GetMapping("/{profileId}")
    Profile get(@PathVariable String profileId){
        return profileRepository.find(new ProfileId(UUID.fromString(profileId))).orElse(null);
    }
    @GetMapping
    List<ProfileQR> index(){
       return profileProjector.findAll(new UserProfileQuery());
    }

    @PostMapping("/addExperience")
    ResponseEntity addExperience(@RequestBody ProfileExperienceCommand addExperienceCommand){
        commandManager.process(addExperienceCommand);
        return ResponseEntity.ok(addExperienceCommand.getProfileId());
    }
    @PostMapping("/addSkill")
    ResponseEntity addExperience(@RequestBody ProfileSkillCommand addExperienceCommand){
        commandManager.process(addExperienceCommand);
        return ResponseEntity.ok(addExperienceCommand.getProfileId());
    }

}
