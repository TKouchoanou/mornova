package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Table(name = "profiles")
public class ProfileJPA extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserJPA user;
    @OneToMany(mappedBy = "profile",cascade = CascadeType.PERSIST)
    private List<ExperienceJPA> experiences;
    @OneToMany(mappedBy = "profile",cascade = CascadeType.PERSIST)
    private List<SkillJPA> skills;
}
