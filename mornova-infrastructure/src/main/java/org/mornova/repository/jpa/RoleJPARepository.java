package org.mornova.repository.jpa;

import org.mornova.repository.jpa.entity.RoleJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJPARepository  extends JpaRepository<RoleJPA, Long> {
    Optional<RoleJPA> findByName(String name);
}
