package org.educavagas.api.mapper;

import org.educavagas.api.dto.TurmaDto;
import org.educavagas.api.model.Turma;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TurmaMapper {
    Turma toEntity(TurmaDto dto);
    TurmaDto toDto(Turma turma);
}
