package org.mornova.command.command.profile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mornova.command.command.Command;
@Builder
@Getter
public class CreateProfileCommand implements Command {
    @Setter
    private String id;
    private String ownerId;


    @Override
    public boolean isSatisfied() {
        return id!=null && !id.isEmpty();
    }
}
