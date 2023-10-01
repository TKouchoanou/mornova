package org.mornova.repository.jpa.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.repository.jpa.entity.ExperienceJPA;
import org.mornova.repository.jpa.entity.ProfileJPA;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

public interface CustomProfileJPARepository {
    Stream<ProfileJPA> findWithSkillsAndExperiencesByOwnerIdIn(List<UserId> ownerIds);
    List<ProfileJPA> findAllWithSkillsAndExperiences();

    @Repository
    class CustomProfileJPARepositoryImpl implements CustomProfileJPARepository {
        @PersistenceContext
        private final EntityManager entityManager;

        public CustomProfileJPARepositoryImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        @Transactional
        public Stream<ProfileJPA> findWithSkillsAndExperiencesByOwnerIdIn(List<UserId> ownerIds) {
            List<ExperienceJPA> experienceJPAS= entityManager
                    .createQuery("SELECT e From ExperienceJPA e", ExperienceJPA.class)
                    .getResultList();

            List<ProfileJPA> profiles = entityManager.createQuery(
                            "SELECT p FROM ProfileJPA p " +
                                    "JOIN FETCH p.skills " +
                                    "WHERE p.owner.id IN :ownerIds", ProfileJPA.class)
                    .setParameter("ownerIds", ownerIds)
                    .getResultList();

            return profiles.stream();
        }

        @Override
        @Transactional
        public List<ProfileJPA> findAllWithSkillsAndExperiences() {
            List<ProfileJPA> profiles = entityManager.createQuery(
                            "SELECT DISTINCT p FROM ProfileJPA p " +
                                    "LEFT JOIN FETCH p.skills "+
                                    "LEFT JOIN FETCH p.owner", ProfileJPA.class)
                    .getResultList();

            profiles = ! profiles.isEmpty()? entityManager.createQuery(
                            "SELECT DISTINCT p FROM ProfileJPA p " +
                                    "LEFT JOIN FETCH p.experiences", ProfileJPA.class)
                    .getResultList():profiles;
            return profiles;
        }
    }
}
