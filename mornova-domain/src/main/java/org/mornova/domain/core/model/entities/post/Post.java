package org.mornova.domain.core.model.entities.post;

import lombok.Getter;
import org.mornova.domain.core.model.entities.BasedEntity;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.CommentId;
import org.mornova.domain.core.model.objectValue.ids.PostId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public void removeLike(Like like) {
        likes.remove(like);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
    public void updateComment(Comment comment){
        findCommentById(comment.getId(),false)
                .ifPresentOrElse(com-> com.setContent(comment.getContent()),()->{throw new IllegalArgumentException();});
    }

    public void removeComment(Comment comment){
      comments.removeIf(cmt->cmt.getId().equals(comment.getId()));
    }
    public Optional<Comment> findCommentById(CommentId commentId) {
        return findCommentById(commentId,true);
    }
    Optional <Comment> findCommentById(CommentId commentId,boolean makeCopy) {
        return comments.stream()
                .filter(comment -> comment.getId().equals(commentId))
                .findFirst()
                .map(comment -> makeCopy?comment.copy():comment);
    }

    // Abstract method for getting the post content
    public abstract String getContent();

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public List<Like> getLikes() {
        return Collections.unmodifiableList(likes);
    }
}
