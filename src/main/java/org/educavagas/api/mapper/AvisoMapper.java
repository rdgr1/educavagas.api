package org.educavagas.api.mapper;

import org.educavagas.api.dto.AvisoDto;
import org.educavagas.api.model.Aviso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvisoMapper {
    Aviso toEntity(AvisoDto aviso);
    AvisoDto toDto(Aviso aviso);
}
