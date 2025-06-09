package org.educavagas.api.mapper;

import jakarta.validation.Valid;
import org.educavagas.api.dto.RegisterEscolaRequest;
import org.educavagas.api.dto.UsuarioEscolaDto;
import org.educavagas.api.model.UsuarioEscola;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        config = CentralConfig.class,
        uses = { UsuarioMapper.class },
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface UsuarioEscolaMapper {

    // DTO => response
    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToIds")
    @Mapping(source = "escola.uuid", target = "escolaUuid")
    UsuarioEscolaDto toDto(UsuarioEscola entity);

    // request => entidade
    @Mapping(target = "escola", ignore = true)   // setamos no service
    @Mapping(target = "ativo", ignore = true)    // definimos no service
    // removi o mapping source="roles" aqui
    UsuarioEscola toEntity(@Valid RegisterEscolaRequest dto);
}