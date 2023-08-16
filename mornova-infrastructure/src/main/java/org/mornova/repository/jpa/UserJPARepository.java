package org.mornova.repository.jpa;

import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.repository.jpa.entity.UserJPA;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<UserJPA, UserId> {
    @Override
    @EntityGraph(attributePaths = "roles")
    Optional<UserJPA> findById(UserId userId);
}
