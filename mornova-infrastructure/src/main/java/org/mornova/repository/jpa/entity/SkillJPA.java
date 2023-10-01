package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mornova.domain.core.model.objectValue.refrerences.SkillLevel;

;import java.util.Objects;

@Entity
@SuperBuilder
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Table(name = "skills")
public class SkillJPA extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private SkillLevel level;
    @ManyToOne
    @Setter
    private ProfileJPA profile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillJPA skillJPA)) return false;
        if(Objects.nonNull(id) && Objects.nonNull(skillJPA.id) && Objects.equals(id, skillJPA.id)) return true;
        return Objects.equals(name, skillJPA.name)
                && level == skillJPA.level
                && Objects.equals(profile, skillJPA.profile)
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, profile);
    }
}
