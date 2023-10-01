package org.mornova.query.result;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceQR {
    private String id;
    private String title;
    private String company;
    private int yearsOfExperience;
}
