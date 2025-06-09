package org.educavagas.api.dto;

import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.UUID;
import org.educavagas.api.util.Turno;

public record VagaDto(
        @NotNull UUID uuid,
        @NotNull Integer capacidade,
        @NotNull Integer matriculados,
        @NotNull Turno turno,
        @NotNull UUID turmaUuid
) {}