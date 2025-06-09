package org.educavagas.api.dto;

import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.UUID;

public record InscricaoInteresseDto(
        @NotNull UUID uuid,
        @NotNull UUID responsavelUuid,
        @NotNull UUID vagaUuid
) {}