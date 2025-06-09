package org.educavagas.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

public record AvisoDto(
        @NotNull UUID uuid,
        @NotBlank @Size(min = 3, max = 500) String mensagem,
        @NotNull LocalDateTime dateEnvio,
        @NotBlank String origemAviso,
        @NotNull UUID criadoPorUuid,
        UUID escolaUuid
) {}