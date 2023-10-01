package org.mornova.query.result;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillQR {
    private String id;
    private String name;
    private String level;
}
