package org.mornova.command.command.post.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProjectPostCommand {
    public  record Media(String type,byte[] file,String name) {}
    private List<Media> mediaList;
    private String projectName;
    private String description;
    private String status;
    private String type;
    private String amountOfFunding;
   private  String availableCapital;
   private   String launchDate;
   private  String sector;
   private  String subSector;
   private  String authorId;

}
