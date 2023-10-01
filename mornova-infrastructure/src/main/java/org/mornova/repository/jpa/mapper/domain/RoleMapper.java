package org.mornova.repository.jpa.mapper.domain;

import org.mornova.domain.core.model.entities.Role;
import org.mornova.domain.core.model.objectValue.ids.RoleId;
import org.mornova.repository.jpa.repository.RoleJPARepository;
import org.mornova.repository.jpa.entity.RoleJPA;

import java.util.Map;

@Mapper
public class RoleMapper implements DomainToJpaMapper<Role, RoleJPA>{
    RoleJPARepository roleJPARepository;
    Map<String,RoleJPA> roleByNameMap;
    public RoleMapper(RoleJPARepository roleJPARepository) {
        this.roleJPARepository = roleJPARepository;
    }

    public Map<String, RoleJPA> getRoleByNameMap() {
        if(roleByNameMap==null){
           refreshRoleByNameMap();
        }
        return roleByNameMap;
    }

    public void refreshRoleByNameMap(){
        roleByNameMap=this.roleJPARepository.roleByName();
    }

    RoleJPA getRole(String name){
        RoleJPA roleJPA= getRoleByNameMap().get(name);
        if(roleJPA==null){
            refreshRoleByNameMap();
            roleJPA=getRoleByNameMap().get(name);
        }
        return roleJPA;
    }

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
        RoleJPA roleJPA=getRole(role.getName());
        if(roleJPA==null){
         roleJPA= RoleJPA.builder()
                    .name(role.getName())
                    .createdAt(role.getCreatedAt())
                    .updatedAt(role.getUpdatedAt())
                    .build();
        }

        return roleJPA;
    }
}
