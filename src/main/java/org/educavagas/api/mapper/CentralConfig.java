package org.educavagas.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


import org.educavagas.api.model.Role;

@MapperConfig(componentModel = "spring")
public interface CentralConfig {

    @Named("instantToLocalDateTime")
    default LocalDateTime instantToLocalDateTime(Instant inst) {
        return inst == null
                ? null
                : LocalDateTime.ofInstant(inst, ZoneId.systemDefault());
    }

    @Named("rolesToIds")
    default List<UUID> rolesToIds(Set<Role> roles) {
        if (roles == null) return Collections.emptyList();
        return roles.stream()
                .map(Role::getUuid)
                .collect(Collectors.toList());
    }

    @Named("idsToRoles")
    default Set<Role> idsToRoles(List<UUID> ids) {
        if (ids == null) return Collections.emptySet();
        return ids.stream()
                .map(uuid -> {
                    Role r = new Role();
                    r.setUuid(uuid);
                    return r;
                })
                .collect(Collectors.toSet());
    }
}