package org.mornova.domain.core.model.objectValue.ids;


public class CommentId implements BaseId<Long> {
    Long id;


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

    public static CommentId valueOf(String uuid){
        return new CommentId(BaseId.longFromString(uuid));
    }
}
