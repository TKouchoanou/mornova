package org.mornova.domain.core.model.objectValue.ids;


public class CommentId implements BaseId<Long> {
    Long id;

    public CommentId() {
    }

    public CommentId(Long value){
        this.id=value;
    }

    @Override
    public Long value() {
        return id;
    }

    public Long getId() {
        return id;
    }
}
