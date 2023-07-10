package org.mornova.repository.jpa.mapper;

import org.mornova.domain.core.model.entities.Media;
import org.mornova.domain.core.model.objectValue.ids.MediaId;
import org.mornova.repository.jpa.entity.MediaJPA;
@Mapper
public class MediaMapper implements DomainToJpaMapper<Media, MediaJPA>{
    @Override
    public Media toDomainEntity(MediaJPA mediaJPA) {
        if (mediaJPA == null) {
            return null;
        }
        MediaId mediaId= new MediaId(mediaJPA.getId());
       return Media.Builder.builder()
                .type(mediaJPA.getType())
                .file(mediaJPA.getFile())
                .path(mediaJPA.getPath())
                .persistenceDetail(persistenceDetailBuilder -> {
                    persistenceDetailBuilder.id(mediaId)
                            .updatedAt(mediaJPA.getUpdatedAt())
                            .createdAt(mediaJPA.getCreatedAt()) ;
                }).build();
    }

    @Override
    public MediaJPA toJpaEntity(Media media) {

        if (media == null) {
            return null;
        }

       return MediaJPA.builder()
                .type(media.getType())
                .file(media.getFile())
                .path(media.getPath())
                .id(media.getId().value())
                .build();


    }
}
