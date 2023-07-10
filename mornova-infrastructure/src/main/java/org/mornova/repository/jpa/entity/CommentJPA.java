package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/*
aide moi à implementer Role.java Experience.java  Message.java  Profile.java      SimplePost.java  User.java
Comment.java     Like.java        Post.java     ProjectPost.java  Skill.java. un post appartient à un utilisateur , il est abstrait  les classes concrètes sont ProjectPost et SimplePost, un post à des commentaire et des les like.  Un Profile appartient à un utilisateur , et contient les Experience et les compétence Skill , une user a des Role ici
*/
@Entity
@Table(name="post_comments")
@NoArgsConstructor
@SuperBuilder
@Getter
@AllArgsConstructor
public class CommentJPA extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private UserJPA author;
    @ManyToOne
    @Setter
    private PostJPA post;
    private String content;

}
