package org.educavagas.api.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record RegisterEscolaRequest(
        @NotBlank @Size(min = 3, max = 100) String nomeCompleto,
        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String senha,
        @NotNull UUID escolaUuid
) {}