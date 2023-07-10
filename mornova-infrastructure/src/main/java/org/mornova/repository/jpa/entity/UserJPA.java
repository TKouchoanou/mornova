package org.mornova.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.mornova.domain.core.model.objectValue.ids.UserId;

import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Table(name = "users")
public class UserJPA extends BasedEntity{
    @Id
    @GeneratedValue(generator = "userIdGenerator")
    @GenericGenerator(name = "userIdGenerator", strategy = "org.mornova.repository.jpa.idGenerator.UserIdGenerator")
    private UserId id;
    private  String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @ManyToMany
    private  List<RoleJPA> roles;

}
