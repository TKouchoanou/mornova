package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Table(name = "posts")
public abstract class PostJPA extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    protected UserJPA author;
    @OneToMany(mappedBy = "post",cascade = CascadeType.PERSIST)
    protected List<CommentJPA> comments;
    @OneToMany(mappedBy = "post",cascade = CascadeType.PERSIST)
    protected List<LikeJPA> likes;
    public void addLike(LikeJPA like) {
        likes.add(like);
    }

    // Abstract method for getting the post content
    public abstract String getContent();
}
