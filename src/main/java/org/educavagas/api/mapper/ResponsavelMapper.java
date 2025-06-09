package org.educavagas.api.mapper;

import org.educavagas.api.dto.ResponsavelDto;
import org.educavagas.api.model.Responsavel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = CentralConfig.class,
        uses = { UsuarioMapper.class }
)
public interface ResponsavelMapper {

    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToIds")
    ResponsavelDto toDto(Responsavel entity);

    @Mapping(target = "ativo", ignore = true)                  // definimos no service
    @Mapping(source = "roles", target = "roles", qualifiedByName = "idsToRoles")
    Responsavel toEntity(ResponsavelDto dto);
}