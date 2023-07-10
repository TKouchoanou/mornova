package org.mornova.domain.core.repository;

import org.mornova.domain.core.model.entities.Role;
import org.mornova.domain.core.model.objectValue.ids.RoleId;

import java.util.Optional;

public interface RoleRepository extends DomainRepository<Role, RoleId> {
    Optional<Role> findByName(String name);
}
