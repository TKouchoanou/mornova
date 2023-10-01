package org.mornova.repository.jpa.queryProjector;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.mornova.query.projector.UserProjector;
import org.mornova.query.query.AllUserQuery;
import org.mornova.query.result.UserQR;
import org.mornova.repository.jpa.transformer.UserQRResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserProjectorImpl implements UserProjector {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserProjectorImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UserQR> findAll(AllUserQuery query) {
        String queryString = "SELECT u.id, u.firstName, u.lastName, u.email, r.name " +
                "FROM UserJPA u " +
                "LEFT JOIN u.roles r";
        TypedQuery<UserQR> typedQuery = entityManager.createQuery(queryString, UserQR.class);
        typedQuery.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new UserQRResultTransformer());

        return typedQuery.getResultList();
    }
}
