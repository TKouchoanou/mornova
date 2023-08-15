package org.mornova.command.mapper;

import org.mornova.domain.core.model.entities.Role;
import org.mornova.domain.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public interface RoleMapper {
    default List<Role> mapRoleNamesToRoles(List<String> roleNames, @Autowired RoleRepository roleRepository) {
        List<Role> roles = new ArrayList<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(()->new RuntimeException("role with name "+roleName+" not found"));
            roles.add(role);
        }
        return roles;
    }
}
