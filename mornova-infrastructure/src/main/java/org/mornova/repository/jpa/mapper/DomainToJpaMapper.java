package org.mornova.repository.jpa.mapper;

import java.util.function.Consumer;

public interface DomainToJpaMapper <D,J>{
    D toDomainEntity(J jpaEntity);
    J toJpaEntity(D domainEntity);


    default D toDomainEntity(J jpaEntity, Consumer<D> onComplete){
       D domainEntity= toDomainEntity(jpaEntity);
        onComplete.accept(domainEntity);
        return domainEntity;
    }

    default J toJpaEntity(D domainEntity, Consumer<J> onComplete){
        J jpaEntity=toJpaEntity(domainEntity);
        onComplete.accept(jpaEntity);
        return jpaEntity;
    }
}
