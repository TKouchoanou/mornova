package org.mornova.domain.core.model.entities;

import lombok.Getter;
import org.mornova.domain.core.model.objectValue.ids.PostId;

import java.util.ArrayList;
import java.util.List;
@Getter
public abstract class Post extends BasedEntity<PostId> {

   PostId id;

    protected User author;

    protected List<Comment> comments;

    protected List<Like> likes;


    public Post(User author) {
        super();
        this.author = author;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public Post() {
        super();
    }

    // Add a like to the post
    public void addLike(Like like) {
        likes.add(like);
    }

    // Abstract method for getting the post content
    public abstract String getContent();
}
