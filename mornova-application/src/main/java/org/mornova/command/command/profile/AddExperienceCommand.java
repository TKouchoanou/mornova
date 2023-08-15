package org.mornova.command.command.profile;

import lombok.Builder;
import lombok.Getter;
import org.mornova.command.command.Command;
@Builder
@Getter
public class AddExperienceCommand implements Command {
    private  String profileId;
    private String title;
    private String company;
    private int yearsOfExperience;

    @Override
    public boolean isSatisfied() {
        return true;
    }
}
