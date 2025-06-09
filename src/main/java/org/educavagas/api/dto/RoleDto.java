package org.educavagas.api.dto;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.*;
public record RoleDto(
        @NotNull(message = "UUID da role é obrigatório")
        UUID uuid,

        @NotBlank(message = "Nome da role é obrigatório")
        @Size(min = 3, max = 50, message = "Nome da role deve ter entre {min} e {max} caracteres")
        String nome
) { }
