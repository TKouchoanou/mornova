package org.mornova.repository.jpa.repository;

import org.mornova.domain.core.model.entities.Role;
import org.mornova.domain.core.model.objectValue.ids.RoleId;
import org.mornova.domain.core.repository.RoleRepository;
import org.mornova.repository.jpa.mapper.domain.RoleMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class RoleRepositoryImpl implements RoleRepository {
    RoleJPARepository jpaRepository;
    RoleMapper roleMapper;

    public RoleRepositoryImpl(RoleJPARepository jpaRepository, RoleMapper roleMapper) {
        this.jpaRepository = jpaRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role save(Role role) {
        return roleMapper.toDomainEntity(jpaRepository.save(roleMapper.toJpaEntity(role)));
    }

    @Override
    public Optional<Role> find(RoleId roleId) {
        return jpaRepository.findById(roleId.value()).map(roleMapper::toDomainEntity);
    }

    @Override
    public Role delete(RoleId roleId) {
       throw new RuntimeException("delete role is not implemented");
    }

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRepository.findByName(name).map(roleMapper::toDomainEntity);
    }
}
