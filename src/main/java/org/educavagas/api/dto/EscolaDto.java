package org.educavagas.api.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record EscolaDto(
        @NotNull UUID uuid,
        @NotBlank @Size(min = 3, max = 100) String nome,
        @NotBlank @Size(min = 3, max = 200) String localizacao
) {}