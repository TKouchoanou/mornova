package org.mornova.command.mapper.post;

import org.mapstruct.Mapper;
import org.mornova.command.command.post.project.CreateProjectPostCommand;
import org.mornova.command.command.post.project.ProjectPostCommand;
import org.mornova.command.command.post.project.UpdateProjectPostCommand;
import org.mornova.domain.core.model.entities.BasedEntity;
import org.mornova.domain.core.model.entities.Media;
import org.mornova.domain.core.model.entities.post.ProjectPost;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.domain.core.model.objectValue.refrerences.MediaType;
import org.mornova.domain.core.model.objectValue.refrerences.ProjectStatus;
import org.mornova.domain.core.model.objectValue.refrerences.ProjectType;
import org.mornova.domain.core.model.objectValue.value.Amount;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Mapper
public interface ProjectPostMapper {

   default ProjectPost toDomain(CreateProjectPostCommand command,Consumer<ProjectPost.Builder> projetcPostBuilderConsumer){
       ProjectPost.Builder postBuilder = ProjectPost.builder()
               .subSector(command.getSubSector())
               .projectName(command.getProjectName())
               .type(ProjectType.valueOf(command.getType()))
               .description(command.getDescription())
               .status(ProjectStatus.valueOf(command.getStatus()))
               .amountOfFunding(Amount.valueOf(command.getAmountOfFunding()))
               .availableCapital(Amount.valueOf(command.getAvailableCapital()))
               .launchDate(LocalDate.parse(command.getLaunchDate()))
               .sector(command.getSector())
               .mediaList(mapMediaList(command.getMediaList()));
       projetcPostBuilderConsumer.accept(postBuilder);

       return postBuilder.build();
    }

    default ProjectPost toDomain(UpdateProjectPostCommand command, Consumer<BasedEntity.PersistenceDetailBuilder<PostId>> consumer ){
        return ProjectPost.builder()
                .subSector(command.getSubSector())
                .projectName(command.getProjectName())
                .type(ProjectType.valueOf(command.getType()))
                .description(command.getDescription())
                .status(ProjectStatus.valueOf(command.getStatus()))
                .amountOfFunding(Amount.valueOf(command.getAmountOfFunding()))
                .availableCapital(Amount.valueOf(command.getAvailableCapital()))
                .launchDate(LocalDate.parse(command.getLaunchDate()))
                .sector(command.getSector())
                .mediaList(mapMediaList(command.getMediaList()))
                .persistenceDetail(consumer)
                .build();
    }


    default Media mapMedia(CreateProjectPostCommand.Media media){
        return Media.builder()
                .file(media.file())
                .type(MediaType.valueOf(media.type()))
                .name(media.name())
                .build();
    }
    default CreateProjectPostCommand.Media mapToCammandMedia(Media media){
        return new  ProjectPostCommand.Media(media.getType().mapToString(),media.getFile(),media.getName());
    }


    default List<Media> mapMediaList(List<CreateProjectPostCommand.Media> mediaList) {
        return mediaList.stream()
                .map(this::mapMedia)
                .collect(Collectors.toList());
    }
    default List<ProjectPostCommand.Media> mapToCommandMediaList(List<Media> mediaList) {
        return mediaList.stream()
                .map(this::mapToCammandMedia)
                .collect(Collectors.toList());
    }
   default String map(Amount value){
        return value.getFormattedValue();
    }

    default  String map(PostId value){
       return value.mapToString();
    }
    default CreateProjectPostCommand createFromDomain(ProjectPost projectPost){
        return CreateProjectPostCommand.builder()
                .projectName(projectPost.getProjectName())
                .description(projectPost.getDescription())
                .status(projectPost.getStatus().toString())
                .type(projectPost.getType().toString())
                .amountOfFunding(map(projectPost.getAmountOfFunding()))
                .availableCapital(map(projectPost.getAvailableCapital()))
                .launchDate(projectPost.getLaunchDate().toString())
                .sector(projectPost.getSector())
                .subSector(projectPost.getSubSector())
                .mediaList(mapToCommandMediaList(projectPost.getMediaList())) // Utiliser la méthode mapMediaList
                .build();
    }

    default UpdateProjectPostCommand updateFromDomain(ProjectPost projectPost){
        return UpdateProjectPostCommand.builder()
                .projectName(projectPost.getProjectName())
                .description(projectPost.getDescription())
                .status(projectPost.getStatus().toString())
                .type(projectPost.getType().toString())
                .amountOfFunding(map(projectPost.getAmountOfFunding()))
                .availableCapital(map(projectPost.getAvailableCapital()))
                .launchDate(projectPost.getLaunchDate().toString())
                .sector(projectPost.getSector())
                .subSector(projectPost.getSubSector())
                .mediaList(mapToCommandMediaList(projectPost.getMediaList()))
                .id(projectPost.getId().mapToString())
                .build();
    }
}
