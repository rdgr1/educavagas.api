package org.educavagas.api.dto;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.*;
public record EscolaDto(
        @NotNull(message = "UUID da escola é obrigatório")
        UUID uuid,

        @NotBlank(message = "Nome da escola é obrigatório")
        @Size(min = 3, max = 100, message = "Nome da escola deve ter entre {min} e {max} caracteres")
        String nome,

        @NotBlank(message = "Localização é obrigatória")
        @Size(min = 3, max = 200, message = "Localização deve ter entre {min} e {max} caracteres")
        String localizacao
) { }
