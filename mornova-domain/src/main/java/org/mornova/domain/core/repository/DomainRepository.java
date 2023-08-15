package org.mornova.domain.core.repository;

import java.util.Optional;

public interface DomainRepository <T,ID>{
     T save(T entity);
     Optional<T> find(ID id);
    T delete(ID id);
    default T  findOrThrow(ID id){
       return this.find(id).orElseThrow(()->new RuntimeException("entity "+this.getClass().getName()+" with id "+id+" not found"));
    }
}
