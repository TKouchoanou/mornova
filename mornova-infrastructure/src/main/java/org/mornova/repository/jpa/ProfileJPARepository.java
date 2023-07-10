package org.mornova.repository.jpa;

import org.mornova.repository.jpa.entity.ProfileJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileJPARepository extends JpaRepository<ProfileJPA, UUID> {
}
