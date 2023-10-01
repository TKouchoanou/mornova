package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Table(name = "profiles")
@NamedEntityGraph(name = "ProfileJPA.skillsAndExperiences",
        attributeNodes = {
                @NamedAttributeNode(value = "owner",subgraph = "owner-subgraph"),
                @NamedAttributeNode("skills"),
                @NamedAttributeNode("experiences")
        },subgraphs = {
        @NamedSubgraph(name = "owner-subgraph",attributeNodes = {@NamedAttributeNode("roles")})
})
@NamedEntityGraph(name = "ProfileJPA.experiences",
        attributeNodes = {
                @NamedAttributeNode(value = "owner",subgraph = "owner-subgraph"),
                @NamedAttributeNode("experiences")
        },subgraphs = {
        @NamedSubgraph(name = "owner-subgraph",attributeNodes = {@NamedAttributeNode("roles")})
})
@NamedEntityGraph(name = "ProfileJPA.skills",
        attributeNodes = { @NamedAttributeNode("skills")})
public class ProfileJPA extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserJPA owner;
    @OneToMany(mappedBy = "profile",cascade = CascadeType.MERGE)
    private Set<ExperienceJPA> experiences;
    @OneToMany(mappedBy = "profile",cascade = CascadeType.MERGE)
    private Set<SkillJPA> skills;
}
