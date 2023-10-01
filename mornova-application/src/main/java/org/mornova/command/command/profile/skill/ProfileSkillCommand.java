package org.mornova.command.command.profile.skill;

import lombok.*;
import org.mornova.command.command.Command;
import org.mornova.command.command.CommandWithActionToPerform;
import org.mornova.command.handler.profile.skill.ProfileSkillCommandHandler;
import org.mornova.utils.StringUtils;

@Builder
@Getter
@Command.Usecase(handlers = {ProfileSkillCommandHandler.class})
@AllArgsConstructor
@NoArgsConstructor
public class ProfileSkillCommand implements CommandWithActionToPerform {
    private  String profileId;
    @Setter
    private String id;
    private String name;
    private String level;
    @Setter
    private Action actionType;

    @Override
    public boolean isSatisfied() {
      return !Action.ADD.equals(actionType) || StringUtils.isNotEmpty(id);
    }
}
