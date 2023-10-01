package org.mornova.command.command.post.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class SimplePostCommand{

    private String content;

}
