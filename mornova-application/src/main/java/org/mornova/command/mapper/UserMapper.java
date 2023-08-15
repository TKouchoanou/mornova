package org.mornova.command.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mornova.command.command.user.CreateUserCommand;
import org.mornova.command.mapper.mapStruct.ToNewDomainEntity;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.repository.RoleRepository;

@Mapper(uses = {RoleRepository.class})
public interface UserMapper extends RoleMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

   @ToNewDomainEntity
    User toDomain(CreateUserCommand command);

}
