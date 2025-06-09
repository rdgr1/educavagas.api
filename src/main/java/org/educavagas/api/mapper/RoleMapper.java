package org.educavagas.api.mapper;

import org.educavagas.api.dto.RoleDto;
import org.educavagas.api.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface RoleMapper {
    Role toEntity(RoleDto dto);
    RoleDto toDto(Role role);
}
