package org.mornova.repository.jpa.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@SuperBuilder
@Getter
public class BasedEntity {

   protected LocalDateTime createdAt;
   protected LocalDateTime updatedAt;



    public BasedEntity() {
        this.createdAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
    }


    public BasedEntity(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    @Override
    public boolean equals(Object o) {
        BasedEntity that= (BasedEntity) o;
        return Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

}
