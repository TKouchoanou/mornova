package org.mornova.repository.jpa.mapper.queryResult;

import org.mapstruct.Mapper;
import org.mornova.query.result.*;
import org.mornova.repository.jpa.entity.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring")
public interface QRGenericMapper {

    default ProfileQR convertProfileToDTO(ProfileJPA profileJPA) {
        List<ExperienceQR> experienceDTOs = profileJPA.getExperiences().stream()
                .map(this::convertExperienceToDTO)
                .collect(Collectors.toList());

        List<SkillQR> skillDTOs = profileJPA.getSkills().stream()
                .map(this::convertSkillToDTO)
                .collect(Collectors.toList());

        return ProfileQR.builder()
                .id(profileJPA.getId().toString())
                .owner(convertUserToDTO(profileJPA.getOwner()))
                .experiences(experienceDTOs)
                .skills(skillDTOs)
                .build();
    }

    default ExperienceQR convertExperienceToDTO(ExperienceJPA experienceJPA){
        return ExperienceQR.builder()
                .id(experienceJPA.getId().toString())
                .title(experienceJPA.getTitle())
                .company(experienceJPA.getCompany())
                .yearsOfExperience(experienceJPA.getYearsOfExperience())
                .build();
    }
    default SkillQR convertSkillToDTO(SkillJPA skillJPA){
        return SkillQR.builder()
                .id(skillJPA.getId().toString())
                .name(skillJPA.getName())
                .level(skillJPA.getLevel().toString())
                .build();
    }
    default UserQR convertUserToDTO(UserJPA userJPA) {
        return UserQR.builder()
                .createdAt(userJPA.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .updatedAt(userJPA.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .id(userJPA.getId().mapToString())
                .firstName(userJPA.getFirstName())
                .lastName(userJPA.getLastName())
                .email(userJPA.getEmail())
                .roles(userJPA.getRoles().stream()
                        .map(RoleJPA::getName)
                        .collect(Collectors.toList()))
                .build();
    }

     default SimplePostQR convertSimplePostToDTO(SimplePostJPA simplePostJPA) {
        return SimplePostQR.builder()
                .content(simplePostJPA.getContent())
                .likes(convertLikesToDto(simplePostJPA.getLikes()))
                .comments(convertCommentsToDto(simplePostJPA.getComments()))
                .author(convertUserToDTO(simplePostJPA.getAuthor()))
                .createdAt(simplePostJPA.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .updatedAt(simplePostJPA.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }

    default ProjectPostQR convertProjectPostToDTO(ProjectPostJPA projectPostJPA) {
        return ProjectPostQR.builder()
                .id(projectPostJPA.getId().toString())
                .projectName(projectPostJPA.getProjectName())
                .description(projectPostJPA.getDescription())
                .status(projectPostJPA.getStatus().name()) //?
                .type(projectPostJPA.getType().name()) // ?
                .amountOfFunding(projectPostJPA.getAmountOfFunding().getFormattedValue()) //?
                .availableCapital(projectPostJPA.getAvailableCapital().getFormattedValue())
                .launchDate(projectPostJPA.getLaunchDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .sector(projectPostJPA.getSector())
                .subSector(projectPostJPA.getSubSector())
                .likes(convertLikesToDto(projectPostJPA.getLikes()))
                .comments(convertCommentsToDto(projectPostJPA.getComments()))
                .author(convertUserToDTO(projectPostJPA.getAuthor()))
                .createdAt(projectPostJPA.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .updatedAt(projectPostJPA.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }
    private List<LikeQR> convertLikesToDto(List<LikeJPA> likeJPAs) {
        return likeJPAs.stream()
                .map(this::convertLikeToDto)
                .collect(Collectors.toList());
    }

    private LikeQR convertLikeToDto(LikeJPA likeJPA) {
        return LikeQR.builder()
                .id(likeJPA.getId().toString())
                .liker(convertUserToDTO(likeJPA.getLiker()))
                .build();
    }

    private List<CommentQR> convertCommentsToDto(List<CommentJPA> commentJPAs) {
        return commentJPAs.stream()
                .map(this::convertCommentToDto)
                .collect(Collectors.toList());
    }

    private CommentQR convertCommentToDto(CommentJPA commentJPA) {
        return CommentQR.builder()
                .id(commentJPA.getId().toString())
                .author(convertUserToDTO(commentJPA.getAuthor()))
                .content(commentJPA.getContent())
                .createdAt(commentJPA.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .updatedAt(commentJPA.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }
}
