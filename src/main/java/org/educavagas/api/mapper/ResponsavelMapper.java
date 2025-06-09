package org.educavagas.api.mapper;

import org.educavagas.api.dto.ResponsavelDto;
import org.educavagas.api.model.Responsavel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper {
    Responsavel toEntity(ResponsavelDto dto);
    ResponsavelDto toDto(Responsavel responsavel);
}
