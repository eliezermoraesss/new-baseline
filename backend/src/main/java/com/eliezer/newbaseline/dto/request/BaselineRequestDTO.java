package com.eliezer.newbaseline.dto.request;

import com.eliezer.newbaseline.enums.ProjectStatus;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.time.LocalDate;

public record BaselineRequestDTO(
        Long id,

        @Size(min = 9, message = "Código da baseline deve ter no mínimo 9 caracteres")
        @NotBlank(message = "Código da baseline é obrigatório")
        String baselineCode,

        @Size(max = 200, message = "Nome do projeto deve ter no máximo 200 caracteres")
        @NotBlank(message = "Nome do projeto é obrigatório")
        String projectName,

        @FutureOrPresent(message = "Data de emissão deve ser hoje ou futura")
        @NotNull(message = "Data de emissão é obrigatória")
        LocalDate openDate,

        @FutureOrPresent(message = "Data de entrega deve ser hoje ou futura")
        @NotNull(message = "Data de entrega é obrigatória")
        LocalDate deliveryDate,

        @FutureOrPresent(message = "Data de início do projeto deve ser hoje ou futura")
        @NotNull(message = "Data de início do projeto é obrigatória")
        LocalDate startProjectDate,

        @FutureOrPresent(message = "Data de término do projeto deve ser hoje ou futura")
        @NotNull(message = "Data de término do projeto é obrigatória")
        LocalDate endProjectDate,

        @PositiveOrZero(message = "Duração do projeto deve ser positiva")
        @NotNull(message = "Duração do projeto é obrigatória")
        Integer projectDuration,

        @NotBlank(message = "Status do projeto é obrigatório")
        ProjectStatus projectStatus,

        @NotNull
        String mediaUrl,

        Instant createdAt,

        @Size(max = 30, message = "Número da oferta deve ter no máximo 30 caracteres")
        @NotNull
        String proposalNumber,



        @NotBlank(message = "ID do usuário é obrigatório")
        Long createdById
) {}
