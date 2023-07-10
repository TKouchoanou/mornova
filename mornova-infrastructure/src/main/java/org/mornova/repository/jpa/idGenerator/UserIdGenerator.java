package org.mornova.repository.jpa.idGenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.repository.jpa.entity.UserJPA;

import java.util.UUID;

public class UserIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object) throws HibernateException {
        if (object instanceof UserJPA) {
            UserJPA user = (UserJPA) object;
            UserId userId = user.getId();
            if (userId != null && userId.value() != null) {
                return userId;
            } else {
                UUID id = UUID.randomUUID();
                userId = new UserId(id);
                return userId;
            }
        }
        throw new HibernateException("Invalid object type for UserIdGenerator");
    }
}
