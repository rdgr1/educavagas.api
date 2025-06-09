package org.educavagas.api.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record TurmaDto(
        @NotNull UUID uuid,
        @NotBlank @Size(min = 1, max = 50) String nome,
        @NotNull UUID serieUuid
) {}