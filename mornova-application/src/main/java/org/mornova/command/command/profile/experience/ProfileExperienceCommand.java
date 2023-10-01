package org.mornova.command.command.profile.experience;

import lombok.*;
import org.mornova.command.command.Command;
import org.mornova.command.command.CommandWithActionToPerform;
import org.mornova.command.handler.profile.experience.ProfileExperienceCommandHandler;
import org.mornova.utils.StringUtils;

@Builder
@Getter
@Command.Usecase(handlers = {ProfileExperienceCommandHandler.class})
@AllArgsConstructor
@NoArgsConstructor
public class ProfileExperienceCommand implements CommandWithActionToPerform {
    @Setter
    private String id;
    private String profileId;
    private String title;
    private String company;
    private int yearsOfExperience;
    @Setter
    private Action actionType;

    @Override
    public boolean isSatisfied() {
        return !Action.ADD.equals(actionType) || StringUtils.isNotEmpty(id);
    }
}
