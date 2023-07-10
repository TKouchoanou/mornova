package org.mornova.repository.jpa;

import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.repository.jpa.entity.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<UserJPA, UserId> {
}
