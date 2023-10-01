package org.mornova.query.result;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeQR {
    private String id;
    private UserQR liker;
}
