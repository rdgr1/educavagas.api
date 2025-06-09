package org.educavagas.api.mapper;

import org.educavagas.api.dto.EscolaDto;
import org.educavagas.api.model.Escola;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EscolaMapper {
    Escola toEntity(EscolaDto dto);
    EscolaDto toDto(Escola escola);
}
