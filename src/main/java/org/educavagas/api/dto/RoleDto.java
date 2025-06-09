package org.educavagas.api.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record RoleDto(
        @NotNull UUID uuid,
        @NotBlank @Size(min = 3, max = 50) String nome
) {}