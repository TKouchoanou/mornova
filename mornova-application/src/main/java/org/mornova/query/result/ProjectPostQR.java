package org.mornova.query.result;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPostQR extends Auditable{
    private String id;
    private String projectName;
    private String description;
    private String status;
    private String type;
    private String amountOfFunding;
    private String availableCapital;
    private String launchDate;
    private String sector;
    private String subSector;
    private List<LikeQR> likes;
    private List<CommentQR> comments;
    private UserQR author;
}
