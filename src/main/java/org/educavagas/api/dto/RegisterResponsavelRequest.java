package org.educavagas.api.dto;

import jakarta.validation.constraints.*;

public record RegisterResponsavelRequest(
        @NotBlank @Size(min = 3, max = 100) String nomeCompleto,
        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String senha,
        @NotBlank @Size(min = 8, max = 20) String telefone,
        @NotBlank @Size(min = 3, max = 200) String endereco
) {}