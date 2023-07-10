package org.mornova.repository.jpa.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
}
