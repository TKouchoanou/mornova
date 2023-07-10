package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mornova.domain.core.model.objectValue.refrerences.SkillLevel;

;

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
}
