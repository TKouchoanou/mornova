package org.mornova.query.result;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SimplePostQR extends Auditable{
    private String id;
    private String content;
    List<LikeQR> likes;
    List<CommentQR> comments;
    UserQR author;
}
