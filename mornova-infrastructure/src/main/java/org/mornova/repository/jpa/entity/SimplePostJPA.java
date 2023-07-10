package org.mornova.repository.jpa.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SimplePostJPA extends PostJPA {
    private String content;
}
