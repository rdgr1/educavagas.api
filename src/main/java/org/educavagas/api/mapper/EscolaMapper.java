package org.educavagas.api.mapper;

import org.educavagas.api.dto.EscolaDto;
import org.educavagas.api.model.Escola;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EscolaMapper {
    EscolaDto toDto(Escola entidade);
    Escola toEntity(EscolaDto dto);
}