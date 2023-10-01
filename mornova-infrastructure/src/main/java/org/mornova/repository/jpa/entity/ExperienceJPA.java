package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperienceJPA that)) return false;
        if(Objects.nonNull(id) && Objects.nonNull(that.id) && Objects.equals(id, that.id)) return true;

        return yearsOfExperience == that.yearsOfExperience
                && Objects.equals(title, that.title)
                && Objects.equals(company, that.company)
                && Objects.equals(profile, that.profile)
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, company, yearsOfExperience,createdAt,updatedAt);
    }
}
