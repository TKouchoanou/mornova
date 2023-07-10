package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mornova.domain.core.model.objectValue.refrerences.MediaType;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Table(name = "medias")
public class MediaJPA extends BasedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @Setter
   private ProjectPostJPA post;
   private MediaType type;
   private byte[] file;
   private  String path;//byte[] if store in database

}
