package org.educavagas.api.mapper;

import org.educavagas.api.dto.UsuarioDto;
import org.educavagas.api.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDto dto);
    UsuarioDto toDto(Usuario usuario);
}
