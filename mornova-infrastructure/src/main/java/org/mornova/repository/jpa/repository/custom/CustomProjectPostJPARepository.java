package org.mornova.repository.jpa.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mornova.repository.jpa.entity.CommentJPA;
import org.mornova.repository.jpa.entity.LikeJPA;
import org.mornova.repository.jpa.entity.ProjectPostJPA;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CustomProjectPostJPARepository {

    ProjectPostJPA findWithAllAssociationById(UUID id);
   @Repository
    class CustomProjectPostJPARepositoryImpl implements CustomProjectPostJPARepository  {
       @PersistenceContext
       private final EntityManager entityManager;

       public CustomProjectPostJPARepositoryImpl(EntityManager entityManager) {
           this.entityManager = entityManager;
       }

       @Override
       @Transactional
        public ProjectPostJPA findWithAllAssociationById(UUID id) {
           ProjectPostJPA projectPost = entityManager.createQuery(
                           "SELECT DISTINCT pp FROM ProjectPostJPA pp " +
                                   "LEFT JOIN FETCH pp.author " +
                                   "LEFT JOIN FETCH pp.mediaList " +
                                   "WHERE pp.id = :id", ProjectPostJPA.class)
                   .setParameter("id", id)
                   .getSingleResult();
         List<CommentJPA> commentJPAList=entityManager.createQuery(
                         "SELECT DISTINCT c FROM CommentJPA c " +
                                 "LEFT JOIN FETCH c.author " +
                                 "WHERE c.post = :projectPost", CommentJPA.class)
                 .setParameter("projectPost", projectPost)
                 .getResultList();
           projectPost.setComments(commentJPAList);
         List<LikeJPA> likeJPAList=entityManager.createQuery(
                         "SELECT DISTINCT l FROM LikeJPA l " +
                                 "LEFT JOIN FETCH l.liker " +
                                 "WHERE l.post = :projectPost", LikeJPA.class)
                 .setParameter("projectPost", projectPost)
                 .getResultList();
           projectPost.setLikes(likeJPAList);

           return projectPost;
        }
    }
}
