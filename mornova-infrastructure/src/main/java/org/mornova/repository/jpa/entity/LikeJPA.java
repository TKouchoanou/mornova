package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "post_likes") //like is reserved word
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeJPA extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @Setter
    PostJPA post;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserJPA liker;

}
