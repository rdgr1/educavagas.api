package org.educavagas.api.mapper;

import org.educavagas.api.dto.AvisoDto;
import org.educavagas.api.model.Aviso;
import org.educavagas.api.model.Escola;
import org.educavagas.api.model.Responsavel;
import org.educavagas.api.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface AvisoMapper {
    @Mapping(source = "criadoPor.uuid", target = "criadoPorUuid")
    @Mapping(source = "escola.uuid",    target = "escolaUuid")
    AvisoDto toDto(Aviso e);

    @Mapping(source = "criadoPorUuid", target = "criadoPor", qualifiedByName = "uuidToUsuario")
    @Mapping(source = "escolaUuid",    target = "escola",      qualifiedByName = "uuidToEscola")
    Aviso toEntity(AvisoDto d);

    @Named("uuidToUsuario")
    default Usuario uuidToUsuario(UUID id) {
        Usuario u = new Responsavel(); // ou instância genérica
        u.setUuid(id);
        return u;
    }

    @Named("uuidToEscola")
    default Escola uuidToEscola(UUID id) {
        Escola e = new Escola();
        e.setUuid(id);
        return e;
    }
}
