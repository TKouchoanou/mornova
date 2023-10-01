package org.mornova.repository.jpa.repository;

import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.repository.jpa.entity.ProjectPostJPA;
import org.mornova.repository.jpa.repository.custom.CustomProjectPostJPARepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectPostJPARepository extends JpaRepository<ProjectPostJPA, UUID> , CustomProjectPostJPARepository {
    List<ProjectPostJPA> findByAuthorIdInAndIdIn(List<UserId> authorIds, List<UUID> postIds);
    List<ProjectPostJPA> findByAuthorIdIn(List<UserId> authorIds);

    List<ProjectPostJPA> findByIdIn(List<UUID> postIds);



}
