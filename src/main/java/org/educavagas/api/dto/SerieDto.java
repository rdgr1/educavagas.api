package org.educavagas.api.dto;
import java.util.UUID;
import jakarta.validation.constraints.*;
public record SerieDto(
        @NotNull(message = "UUID da série é obrigatório")
        UUID uuid,

        @NotBlank(message = "Nome da série é obrigatório")
        @Size(min = 1, max = 50, message = "Nome da série deve ter entre {min} e {max} caracteres")
        String nome
) { }

