package org.mornova.command.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mornova.command.command.user.CreateUserCommand;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.repository.RoleRepository;

@Mapper(uses = {RoleRepository.class},componentModel="spring")
public interface UserMapper extends RoleMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
/*
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })*/
    default User toDomain(CreateUserCommand command){
       return User .builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .build();
    }

}
