package org.educavagas.api.mapper;

import org.educavagas.api.dto.TurmaDto;
import org.educavagas.api.model.Turma;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TurmaMapper {
    @Mapping(source = "serie.uuid", target = "serieUuid")
    TurmaDto toDto(Turma entity);

    @Mapping(source = "serieUuid", target = "serie.uuid")
    Turma toEntity(TurmaDto dto);
}