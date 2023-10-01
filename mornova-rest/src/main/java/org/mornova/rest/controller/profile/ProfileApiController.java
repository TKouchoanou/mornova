package org.mornova.rest.controller.profile;

import org.mornova.command.CommandManager;
import org.mornova.command.command.HasActionToPerform;
import org.mornova.command.command.profile.experience.ProfileExperienceCommand;
import org.mornova.command.command.profile.skill.ProfileSkillCommand;
import org.mornova.command.command.profile.CreateProfileCommand;
import org.mornova.query.projector.ProfileProjector;
import org.mornova.query.query.UserProfileQuery;
import org.mornova.query.result.ProfileQR;
import org.mornova.rest.controller.Endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(Endpoint.PROFILES_API_ENDPOINT)
@RestController
public class ProfileApiController {
    CommandManager commandManager;
    ProfileProjector profileProjector;

    public ProfileApiController(CommandManager commandManager, ProfileProjector profileProjector) {
        this.commandManager = commandManager;
        this.profileProjector = profileProjector;
    }

    @GetMapping
    List<ProfileQR> index(){
        return profileProjector.findAll(new UserProfileQuery());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateProfileCommand createProfileCommand){
         commandManager.process(createProfileCommand);
         return ResponseEntity.ok(createProfileCommand.getId());
    }
    @PostMapping("/addExperience")
    ResponseEntity<String> addExperience(@RequestBody ProfileExperienceCommand addExperienceCommand){
        addExperienceCommand.setActionType(HasActionToPerform.Action.ADD);
        commandManager.process(addExperienceCommand);
        return ResponseEntity.ok(addExperienceCommand.getProfileId());
    }
    @PostMapping("/addSkill")
    ResponseEntity<String> addExperience(@RequestBody ProfileSkillCommand addSkillCommand){
        addSkillCommand.setActionType(HasActionToPerform.Action.ADD);
        commandManager.process(addSkillCommand);
        return ResponseEntity.ok(addSkillCommand.getProfileId());
    }
}
