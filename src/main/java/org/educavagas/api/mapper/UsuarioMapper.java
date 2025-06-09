package org.educavagas.api.mapper;

import org.educavagas.api.dto.UsuarioDto;
import org.educavagas.api.model.Role;
import org.educavagas.api.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    //@Mapping(source = "roles", target = "roles", qualifiedByName = "idsToRoles")
    //Usuario toEntity(UsuarioDto dto);
    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToIds")
    UsuarioDto toDto(Usuario u);

    @Named("rolesToIds")
    default Set<UUID> rolesToIds(Set<Role> roles) {
        return roles == null ? Set.of() : roles.stream().map(Role::getUuid).collect(Collectors.toSet());
    }
    @Named("idsToRoles")
    default Set<Role> idsToRoles(Set<UUID> ids) {
        return ids == null ? Set.of() : ids.stream().map(id -> {
            var r = new Role(); r.setUuid(id); return r;
        }).collect(Collectors.toSet());
    }

}