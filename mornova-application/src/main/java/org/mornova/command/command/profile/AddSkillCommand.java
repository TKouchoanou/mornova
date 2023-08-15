package org.mornova.command.command.profile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mornova.command.command.Command;
@Builder
@Getter
public class AddSkillCommand implements Command {
    private  String profileId;
    @Setter
    private String id;
    private String name;
    private String level;

    @Override
    public boolean isSatisfied() {
        return id!=null && !id.isEmpty();
    }
}
