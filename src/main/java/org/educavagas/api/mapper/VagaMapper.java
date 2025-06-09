package org.educavagas.api.mapper;

import org.educavagas.api.dto.VagaDto;
import org.educavagas.api.model.Vaga;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = CentralConfig.class,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface VagaMapper {

    @Mapping(source = "turma.uuid",   target = "turmaUuid")
    VagaDto toDto(Vaga entity);

    @Mapping(target = "uuid",        ignore = true)
    @Mapping(source = "turmaUuid",   target = "turma.uuid")
    Vaga toEntity(VagaDto dto);
}