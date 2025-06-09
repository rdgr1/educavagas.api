package org.educavagas.api.mapper;

import org.educavagas.api.dto.UsuarioEscolaDto;
import org.educavagas.api.model.UsuarioEscola;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = CentralConfig.class,
        uses = { UsuarioMapper.class }
)
public interface UsuarioEscolaMapper {

    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToIds")
    @Mapping(source = "escola.uuid", target = "escolaUuid")
    UsuarioEscolaDto toDto(UsuarioEscola entity);

    @Mapping(target = "escola", ignore = true)                 // vamos setar a Escola no service
    @Mapping(target = "ativo", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "idsToRoles")
    UsuarioEscola toEntity(UsuarioEscolaDto dto);
}