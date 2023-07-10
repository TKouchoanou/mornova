package org.mornova.repository.jpa;

import org.mornova.repository.jpa.entity.ProjectPostJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectPostJPARepository extends JpaRepository<ProjectPostJPA, UUID> {
}
