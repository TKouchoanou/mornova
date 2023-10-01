package org.mornova.repository.jpa.repository;

import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.repository.jpa.entity.SimplePostJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SimplePostJPARepository extends JpaRepository<SimplePostJPA, UUID> {
    List<SimplePostJPA> findByAuthorIdInAndIdIn(List<UserId> authorIds, List<UUID> postIds);
    List<SimplePostJPA> findByAuthorIdIn(List<UserId> authorIds);

    List<SimplePostJPA> findByIdIn(List<UUID> postIds);
}
