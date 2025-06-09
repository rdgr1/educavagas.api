package org.educavagas.api.mapper;

import org.educavagas.api.dto.InscricaoInteresseDto;
import org.educavagas.api.model.InscricaoInteresse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralConfig.class,unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface InscricaoInteresseMapper {

    @Mapping(source = "responsavel.uuid", target = "responsavelUuid")
    @Mapping(source = "vaga.uuid",       target = "vagaUuid")
    InscricaoInteresseDto toDto(InscricaoInteresse entity);

    @Mapping(target = "uuid",       ignore = true)
    @Mapping(target = "responsavel",ignore = true)  // setar no service
    @Mapping(target = "vaga",       ignore = true)  // setar no service
    InscricaoInteresse toEntity(InscricaoInteresseDto dto);
}