package org.educavagas.api.mapper;

import jakarta.validation.Valid;
import org.educavagas.api.dto.RegisterResponsavelRequest;
import org.educavagas.api.dto.ResponsavelDto;
import org.educavagas.api.model.Responsavel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        config = CentralConfig.class,
        uses = { UsuarioMapper.class },
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface ResponsavelMapper {

    // entidade => response
    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToIds")
    ResponsavelDto toDto(Responsavel entity);

    // request => entidade
    @Mapping(target = "ativo", ignore = true)    // definimos no service
    // removi também o mapping de roles aqui
    Responsavel toEntity(@Valid RegisterResponsavelRequest dto);
}