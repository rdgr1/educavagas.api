package org.educavagas.api.mapper;

import org.educavagas.api.dto.InscricaoInteresseDto;
import org.educavagas.api.model.InscricaoInteresse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InscricaoInteresseMapper {
    InscricaoInteresse toEntity(InscricaoInteresseDto dto);
    InscricaoInteresseDto toDto(InscricaoInteresse inscricaoInteresse);
}
