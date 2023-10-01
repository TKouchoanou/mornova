package org.mornova.domain.core.model.entities.post;

import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.PostId;

import java.util.List;
import java.util.function.Consumer;

public class SimplePost extends Post {
    private String content;

    public SimplePost(User user, String content) {
        super(user);
        this.content = content;
    }

    private SimplePost(Builder builder) {
        author = builder.user;
        comments = builder.comments;
        likes = builder.likes;
        content = builder.content;
        if(builder.postPD!=null){
            id=builder.postPD.id();
            this.initPersistenceDetail(builder.postPD);
        }
    }

    public SimplePost() {
        super();
    }

    public String getContent() {
        return content;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private User user;
        private List<Comment> comments;
        private List<Like> likes;
        private String content;
        private PersistenceDetailBuilder.PersistenceDetail<PostId> postPD;

        private Builder() {
        }



        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder comments(List<Comment> val) {
            comments = val;
            return this;
        }

        public Builder likes(List<Like> val) {
            likes = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }
        public Builder persistenceDetail(Consumer<PersistenceDetailBuilder<PostId>> consumer ) {
            PersistenceDetailBuilder<PostId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            postPD=persistenceDetailBuilder.build();
            return this;
        }
        public SimplePost build() {
            return new SimplePost(this);
        }
    }
}
