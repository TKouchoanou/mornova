package org.mornova.domain.core.model.entities;

import lombok.Getter;
import org.mornova.domain.core.model.objectValue.ids.CommentId;

import java.util.function.Consumer;

@Getter

public class Comment extends BasedEntity<CommentId> {
    CommentId id;
    private User author;
    private String content;

    public Comment() {
    }

    private Comment(Builder builder) {
        author = builder.author;
        content = builder.content;
        if(builder.commentPD!=null && builder.commentPD.id()!=null){
            id=builder.commentPD.id();
            this.initPersistenceDetail(builder.commentPD);
        }
    }



    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private User author;
        private String content;
        private PersistenceDetailBuilder.PersistenceDetail<CommentId> commentPD;
        public Builder() {
        }

        public Builder author(User author) {
           this.author = author;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Comment build() {
            if(author==null)
                throw new RuntimeException(" comment author cannot be null");
            return new Comment(this);
        }

        public Builder persistenceDetail(Consumer<PersistenceDetailBuilder<CommentId>> consumer ) {
            PersistenceDetailBuilder<CommentId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            commentPD=persistenceDetailBuilder.build();
            return this;
        }
    }
}
