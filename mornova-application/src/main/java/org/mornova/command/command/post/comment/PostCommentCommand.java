package org.mornova.command.command.post.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mornova.command.command.CommandWithActionToPerform;
import org.mornova.utils.StringUtils;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class PostCommentCommand  implements CommandWithActionToPerform {
    @Setter
    String id;
    String postId;
    String content;
    String authorId;
    @Setter
    Action actionType;
    public boolean isSatisfied() {
        return !Action.ADD.equals(getActionType()) || StringUtils.isNotEmpty(this.id);
    }
}
