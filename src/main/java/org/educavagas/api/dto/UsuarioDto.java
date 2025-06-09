package org.educavagas.api.dto;

import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.*;
public record UsuarioDto(
        @NotNull(message = "UUID de usuário é obrigatório")
        UUID uuid,

        @NotBlank(message = "Nome completo é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre {min} e {max} caracteres")
        String nomeCompleto,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail deve ser válido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, max = 100, message = "Senha deve ter entre {min} e {max} caracteres")
        String senha,

        @NotEmpty(message = "Pelo menos uma role deve ser atribuída")
        List<@NotNull(message = "UUID da role não pode ser nulo") UUID> roles
) { }
