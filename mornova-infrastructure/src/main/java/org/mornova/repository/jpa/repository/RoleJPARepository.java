package org.mornova.repository.jpa.repository;

import org.mornova.repository.jpa.entity.RoleJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface RoleJPARepository  extends JpaRepository<RoleJPA, Long> {
    Optional<RoleJPA> findByName(String name);


    default  Map<String, RoleJPA> roleByName(){
    return findAll().stream().collect(Collectors.toMap(RoleJPA::getName, Function.identity()));
    }
}
