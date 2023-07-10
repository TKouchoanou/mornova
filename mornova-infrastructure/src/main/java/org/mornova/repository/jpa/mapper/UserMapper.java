package org.mornova.repository.jpa.mapper;

import org.mornova.domain.core.model.entities.User;
import org.mornova.repository.jpa.entity.UserJPA;

import java.util.stream.Collectors;
@Mapper
public class UserMapper implements DomainToJpaMapper<User, UserJPA>{
    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public User toDomainEntity(UserJPA userJPA) {

        User.Builder builder = User.builder()
                .persistenceDetail(pD -> pD.
                                id(userJPA.getId())
                                .createdAt(userJPA.getCreatedAt())
                                .updatedAt(userJPA.getUpdatedAt())
                )
                .email(userJPA.getEmail())
                .firstName(userJPA.getFirstName())
                .lastName(userJPA.getLastName());

        if (userJPA.getRoles() != null) {
            builder.roles(userJPA.getRoles().stream()
                    .map(roleMapper::toDomainEntity)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }

    @Override
    public UserJPA toJpaEntity(User user) {
        UserJPA.UserJPABuilder builder = UserJPA.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail().getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt());

        if(user.getId()!=null){
            builder.id(user.getId());
        }

        if (user.getRoles() != null) {
            builder.roles(user.getRoles().stream()
                    .map(roleMapper::toJpaEntity)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }

}
