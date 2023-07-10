package org.mornova.repository.jpa.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
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



}
