package org.mornova.query.result;

import lombok.*;

import java.util.List;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileQR {
    private String id;
    private UserQR owner;
    private List<ExperienceQR> experiences;
    private List<SkillQR> skills;
}
