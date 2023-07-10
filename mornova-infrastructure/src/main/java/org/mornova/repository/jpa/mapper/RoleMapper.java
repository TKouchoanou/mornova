package org.mornova.repository.jpa.mapper;

import org.mornova.domain.core.model.entities.Role;
import org.mornova.domain.core.model.objectValue.ids.RoleId;
import org.mornova.repository.jpa.entity.RoleJPA;
@Mapper
public class RoleMapper implements DomainToJpaMapper<Role, RoleJPA>{
    @Override
    public Role toDomainEntity(RoleJPA roleJPA) {
        RoleId roleId=new RoleId(roleJPA.getId());
        return Role.builder()
                .persistenceDetail(pd->pd.id(roleId)
                        .createdAt(roleJPA.getCreatedAt())
                        .updatedAt(roleJPA.getUpdatedAt())
                )
                .name(roleJPA.getName())
                .build();
    }

    @Override
    public RoleJPA toJpaEntity(Role role) {
        RoleJPA.RoleJPABuilder builder =RoleJPA.builder()
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt());

        if(role.getId()!=null) {
            builder.id(role.getId().value());
        }

        return builder.build();
    }
}
