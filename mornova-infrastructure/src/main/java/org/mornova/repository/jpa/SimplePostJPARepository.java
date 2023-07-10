package org.mornova.repository.jpa;

import org.mornova.repository.jpa.entity.SimplePostJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SimplePostJPARepository extends JpaRepository<SimplePostJPA, UUID> {
}
