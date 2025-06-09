package org.educavagas.api.mapper;

import org.educavagas.api.dto.VagaDto;
import org.educavagas.api.model.Vaga;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VagaMapper {
    Vaga toEntity(VagaDto dto);
    VagaDto toDto(Vaga vaga);
}
