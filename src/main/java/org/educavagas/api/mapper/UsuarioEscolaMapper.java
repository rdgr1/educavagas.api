package org.educavagas.api.mapper;

import org.educavagas.api.dto.UsuarioEscolaDto;
import org.educavagas.api.model.UsuarioEscola;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEscolaMapper {
    UsuarioEscola toEntity(UsuarioEscolaDto dto);
    UsuarioEscolaDto toDto(UsuarioEscola usuarioEscola);
}
