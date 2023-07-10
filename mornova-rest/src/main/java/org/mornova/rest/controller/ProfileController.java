package org.mornova.rest.controller;

import org.mornova.domain.core.model.entities.Profile;
import org.mornova.domain.core.model.objectValue.ids.ProfileId;
import org.mornova.domain.core.repository.ProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("profiles")
public class ProfileController {
    ProfileRepository profileRepository;
    DataFaker faker;

    public ProfileController(ProfileRepository profileRepository, DataFaker faker) {
        this.profileRepository = profileRepository;
        this.faker = faker;
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


}
