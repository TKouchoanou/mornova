package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Getter
@SuperBuilder
@AllArgsConstructor
@Table(name = "experiences")
public class ExperienceJPA extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    private String title;
    private String company;
    private int yearsOfExperience;
    @ManyToOne
    @Setter
    private ProfileJPA profile;
}
