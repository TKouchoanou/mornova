package org.mornova.repository.jpa.repository;

import org.mornova.repository.jpa.entity.ProfileJPA;
import org.mornova.repository.jpa.repository.custom.CustomProfileJPARepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileJPARepository extends JpaRepository<ProfileJPA, UUID>, CustomProfileJPARepository {

    @EntityGraph("ProfileJPA.skills")
    Optional<ProfileJPA> findWithSkillById(UUID uuid);
    @EntityGraph("ProfileJPA.experiences")
    Optional<ProfileJPA> findWithExperienceAndOwnerById(UUID uuid);


    @Override
    @EntityGraph("ProfileJPA.skillsAndExperiences")
    List<ProfileJPA> findAll();
}
