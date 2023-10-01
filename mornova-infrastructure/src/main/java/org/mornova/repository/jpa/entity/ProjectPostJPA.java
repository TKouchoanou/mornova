package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.mornova.domain.core.model.objectValue.refrerences.ProjectStatus;
import org.mornova.domain.core.model.objectValue.refrerences.ProjectType;
import org.mornova.domain.core.model.objectValue.value.Amount;
import org.mornova.repository.jpa.converter.AmountConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPostJPA extends PostJPA {

    @OneToMany(mappedBy = "post")
    @Builder.Default
    private List<MediaJPA> mediaList=new ArrayList<>();
    private String projectName;
    private String description;
    ProjectStatus status;
    ProjectType type;
    @Convert(converter = AmountConverter.class)
    Amount amountOfFunding;
    @Convert(converter = AmountConverter.class)
    Amount availableCapital;
    LocalDate launchDate;
    String sector;
    String subSector;

    @Override
    public String getContent() {
        return description;
    }
    public class EntityGraphs {
        public static final String POST_WITH_ALL_ASSOCIATIONS = "post-with-all-associations";

        public static EntityGraph<ProjectPostJPA> createPostWithAllAssociationsGraph(EntityManager entityManager) {
            EntityGraph<ProjectPostJPA> entityGraph =  entityManager.createEntityGraph(ProjectPostJPA.class);

            // Charger les commentaires avec auteurs
            Subgraph<List<CommentJPA>> commentsSubgraph = entityGraph.addSubgraph("comments");
            commentsSubgraph.addAttributeNodes("author");

            // Charger les likes avec auteurs
            Subgraph<List<LikeJPA>> likesSubgraph = entityGraph.addSubgraph("likes");
            likesSubgraph.addAttributeNodes("liker");

            // Charger l'auteur du post
            entityGraph.addAttributeNodes("author");

            // Charger les médias
            entityGraph.addAttributeNodes("mediaList");

            return entityGraph;
        }
    }
}
