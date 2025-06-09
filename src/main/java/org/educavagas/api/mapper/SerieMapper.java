package org.educavagas.api.mapper;

import org.educavagas.api.dto.SerieDto;
import org.educavagas.api.model.Serie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SerieMapper {
    Serie toEntity(SerieDto dto);
    SerieDto toDto(Serie serie);
}
