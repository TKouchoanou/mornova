package org.mornova.command.mapper.mapStruct;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
/*
@Mapping(target = "persistenceDetail",ignore = true)
*/
@Mapping(target = "roles", ignore = true)
@Mapping(target = "id", ignore = true)
@Mapping(target = "createdAt", ignore = true)
@Mapping(target = "updatedAt",ignore = true)
public @interface ToNewDomainEntity {
}
