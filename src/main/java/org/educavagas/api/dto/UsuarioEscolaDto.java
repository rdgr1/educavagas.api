package org.educavagas.api.dto;

import jakarta.validation.constraints.*;
import org.aspectj.weaver.ast.Not;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UsuarioEscolaDto(
        @NotNull UUID uuid,
        @NotBlank @Size(min = 3, max = 100) String nomeCompleto,
        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6, max = 100) String senha,
        @NotNull Instant criadoEm,
        @NotNull Instant atualizadoEm,
        LocalDateTime ultimoLogin,
        @NotEmpty Set<@NotNull UUID> roles,
        @NotBlank String matricula,

        @NotNull
        UUID escolaUuid
) {}